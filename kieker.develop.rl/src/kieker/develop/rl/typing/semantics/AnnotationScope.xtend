package kieker.develop.rl.typing.semantics

import com.google.common.base.Predicate
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.impl.AbstractScope
import java.util.ArrayList
import org.eclipse.emf.ecore.InternalEObject
import org.eclipse.xtext.resource.EObjectDescription
import kieker.develop.semantics.annotations.AnnotationsFactory

class AnnotationScope extends AbstractScope {
	
	private val AnnotationProvider typeProvider;

	private val IQualifiedNameConverter qualifiedNameConverter;

	private val Predicate<IEObjectDescription> filter;
	
	new(AnnotationProvider typeProvider, IQualifiedNameConverter qualifiedNameConverter, 
		Predicate<IEObjectDescription> filter) {
		super(IScope.NULLSCOPE, false)
		this.typeProvider = typeProvider
		this.qualifiedNameConverter = qualifiedNameConverter
		this.filter = filter		
	}
	
	override protected getAllLocalElements() {
		val annotations = new ArrayList<IEObjectDescription>

		this.typeProvider.allAnnotations.forEach[
			System.out.println(">> " + it.getName())
			annotations.add(this.createScopedElement(it.getName()))
		]
		
		return annotations
	}
	
	private def IEObjectDescription createScopedElement(String fullyQualifiedName) {
		val InternalEObject proxy = this.createProxy(fullyQualifiedName)
		val IEObjectDescription eObjectDescription = EObjectDescription.create(
				this.qualifiedNameConverter.toQualifiedName(fullyQualifiedName),
				proxy);
		return eObjectDescription
	}
	
	private def InternalEObject createProxy(String fullyQualifiedName) {
		System.out.println("createProxy " + fullyQualifiedName);
		val uri = AnnotationURIHelper.getFullURIForClass(fullyQualifiedName)
		// TODO fix this: could be component or interface or method?
		val InternalEObject proxy = AnnotationsFactory.eINSTANCE.createAnnotation() as InternalEObject
		proxy.eSetProxyURI(uri)
		return proxy
	}
}
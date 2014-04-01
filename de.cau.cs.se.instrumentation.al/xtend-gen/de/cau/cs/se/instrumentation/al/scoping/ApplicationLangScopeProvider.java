/**
 * generated by Xtext
 */
package de.cau.cs.se.instrumentation.al.scoping;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import de.cau.cs.se.instrumantation.model.structure.Container;
import de.cau.cs.se.instrumantation.model.structure.Method;
import de.cau.cs.se.instrumantation.model.structure.NamedElement;
import de.cau.cs.se.instrumentation.al.applicationLang.ContainerNode;
import de.cau.cs.se.instrumentation.al.applicationLang.LocationQuery;
import de.cau.cs.se.instrumentation.al.applicationLang.Model;
import de.cau.cs.se.instrumentation.al.applicationLang.Node;
import de.cau.cs.se.instrumentation.al.applicationLang.Query;
import de.cau.cs.se.instrumentation.al.applicationLang.RegisteredPackage;
import de.cau.cs.se.instrumentation.al.modelhandling.ForeignModelTypeProviderFactory;
import de.cau.cs.se.instrumentation.al.modelhandling.IForeignModelTypeProvider;
import de.cau.cs.se.instrumentation.al.scoping.ContainerParentScope;
import de.cau.cs.se.instrumentation.al.scoping.EPackageScope;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * This class contains custom scoping description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#scoping
 * on how and when to use it
 */
@SuppressWarnings("all")
public class ApplicationLangScopeProvider extends AbstractDeclarativeScopeProvider {
  @Inject
  @Extension
  private ForeignModelTypeProviderFactory typeProviderFactory;
  
  public IScope scope_ContainerNode_container(final ContainerNode context, final EReference reference) {
    EObject _eContainer = context.eContainer();
    EObject _eContainer_1 = _eContainer.eContainer();
    if ((_eContainer_1 instanceof LocationQuery)) {
      Resource _eResource = context.eResource();
      ResourceSet _resourceSet = _eResource.getResourceSet();
      final IForeignModelTypeProvider typeProvider = this.typeProviderFactory.getTypeProvider(_resourceSet, null);
      Iterable<NamedElement> _allTypes = typeProvider.getAllTypes();
      ContainerParentScope _containerParentScope = new ContainerParentScope(_allTypes, context);
      final IScope result = _containerParentScope;
      return result;
    } else {
      return null;
    }
  }
  
  public Model getModel(final ContainerNode node) {
    EObject _eContainer = node.eContainer();
    return this.getModel(_eContainer);
  }
  
  public Model getModel(final EObject node) {
    if ((node instanceof Model)) {
      return ((Model) node);
    } else {
      EObject _eContainer = node.eContainer();
      return this.getModel(_eContainer);
    }
  }
  
  public IScope scope_Query_method(final Query context, final EReference reference) {
    System.out.println("scope_Query_method");
    LocationQuery _location = context.getLocation();
    final Node node = this.leaveNode(_location);
    Container _container = null;
    if (((ContainerNode) node)!=null) {
      _container=((ContainerNode) node).getContainer();
    }
    String _name = _container.getName();
    String _plus = ("node " + _name);
    System.out.println(_plus);
    if ((node instanceof ContainerNode)) {
      Container _container_1 = ((ContainerNode) node).getContainer();
      EList<Method> _methods = _container_1.getMethods();
      return Scopes.scopeFor(_methods);
    } else {
      return IScope.NULLSCOPE;
    }
  }
  
  public Node leaveNode(final LocationQuery query) {
    Node _xifexpression = null;
    LocationQuery _specialization = query.getSpecialization();
    boolean _notEquals = (!Objects.equal(_specialization, null));
    if (_notEquals) {
      LocationQuery _specialization_1 = query.getSpecialization();
      Node _leaveNode = this.leaveNode(_specialization_1);
      _xifexpression = _leaveNode;
    } else {
      Node _node = query.getNode();
      _xifexpression = _node;
    }
    return _xifexpression;
  }
  
  /**
   * Find scope for the package property in the MetaModel rule.
   * 
   * @param context
   *            The Package-object of the resulting model.
   * @param reference
   *            The EReference-reference object of the AST.
   * @return The scope for the package attribute.
   */
  public IScope scope_RegisteredPackage_ePackage(final RegisteredPackage context, final EReference reference) {
    Resource _eResource = context.eResource();
    ResourceSet _resourceSet = _eResource.getResourceSet();
    EPackageScope _ePackageScope = new EPackageScope(_resourceSet);
    final IScope result = _ePackageScope;
    return result;
  }
}

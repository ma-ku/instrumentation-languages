package de.cau.se.instrumentation.language.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.se.instrumentation.language.probeLang.Classifier;
import de.cau.se.instrumentation.language.probeLang.CodeElement;
import de.cau.se.instrumentation.language.probeLang.Import;
import de.cau.se.instrumentation.language.probeLang.Model;
import de.cau.se.instrumentation.language.probeLang.Parameter;
import de.cau.se.instrumentation.language.probeLang.ParameterRef;
import de.cau.se.instrumentation.language.probeLang.Pattern;
import de.cau.se.instrumentation.language.probeLang.Probe;
import de.cau.se.instrumentation.language.probeLang.ProbeLangPackage;
import de.cau.se.instrumentation.language.probeLang.Property;
import de.cau.se.instrumentation.language.probeLang.ReferenceProperty;
import de.cau.se.instrumentation.language.probeLang.XStringLiteral;
import de.cau.se.instrumentation.language.services.ProbeLangGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class ProbeLangSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private ProbeLangGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == ProbeLangPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ProbeLangPackage.CLASSIFIER:
				if(context == grammarAccess.getClassifierRule()) {
					sequence_Classifier(context, (Classifier) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.CODE_ELEMENT:
				if(context == grammarAccess.getCodeElementRule()) {
					sequence_CodeElement(context, (CodeElement) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.IMPORT:
				if(context == grammarAccess.getImportRule()) {
					sequence_Import(context, (Import) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.PACKAGE:
				if(context == grammarAccess.getPackageRule()) {
					sequence_Package(context, (de.cau.se.instrumentation.language.probeLang.Package) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.PARAMETER:
				if(context == grammarAccess.getParameterRule()) {
					sequence_Parameter(context, (Parameter) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.PARAMETER_REF:
				if(context == grammarAccess.getParameterRefRule() ||
				   context == grammarAccess.getSimpleCodeElementRule()) {
					sequence_ParameterRef(context, (ParameterRef) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.PATTERN:
				if(context == grammarAccess.getPatternRule()) {
					sequence_Pattern(context, (Pattern) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.PROBE:
				if(context == grammarAccess.getProbeRule()) {
					sequence_Probe(context, (Probe) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.PROPERTY:
				if(context == grammarAccess.getPropertyRule()) {
					sequence_Property(context, (Property) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.REFERENCE_PROPERTY:
				if(context == grammarAccess.getReferencePropertyRule()) {
					sequence_ReferenceProperty(context, (ReferenceProperty) semanticObject); 
					return; 
				}
				else break;
			case ProbeLangPackage.XSTRING_LITERAL:
				if(context == grammarAccess.getSimpleCodeElementRule() ||
				   context == grammarAccess.getXStringLiteralRule()) {
					sequence_XStringLiteral(context, (XStringLiteral) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (package=[Package|ID] class=[EClassifier|ID])
	 */
	protected void sequence_Classifier(EObject context, Classifier semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ProbeLangPackage.Literals.CLASSIFIER__PACKAGE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ProbeLangPackage.Literals.CLASSIFIER__PACKAGE));
			if(transientValues.isValueTransient(semanticObject, ProbeLangPackage.Literals.CLASSIFIER__CLASS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ProbeLangPackage.Literals.CLASSIFIER__CLASS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getClassifierAccess().getPackagePackageIDTerminalRuleCall_0_0_1(), semanticObject.getPackage());
		feeder.accept(grammarAccess.getClassifierAccess().getClassEClassifierIDTerminalRuleCall_2_0_1(), semanticObject.getClass_());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (toUpper?='#'? value=SimpleCodeElement)
	 */
	protected void sequence_CodeElement(EObject context, CodeElement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     importedNamespace=QualifiedNameWithWildcard
	 */
	protected void sequence_Import(EObject context, Import semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ProbeLangPackage.Literals.IMPORT__IMPORTED_NAMESPACE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ProbeLangPackage.Literals.IMPORT__IMPORTED_NAMESPACE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_0(), semanticObject.getImportedNamespace());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=QualifiedName packages+=Package* imports+=Import* patterns+=Pattern* probes+=Probe*)
	 */
	protected void sequence_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID package=[EPackage|STRING])
	 */
	protected void sequence_Package(EObject context, de.cau.se.instrumentation.language.probeLang.Package semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ProbeLangPackage.Literals.PACKAGE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ProbeLangPackage.Literals.PACKAGE__NAME));
			if(transientValues.isValueTransient(semanticObject, ProbeLangPackage.Literals.PACKAGE__PACKAGE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ProbeLangPackage.Literals.PACKAGE__PACKAGE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getPackageAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getPackageAccess().getPackageEPackageSTRINGTerminalRuleCall_2_0_1(), semanticObject.getPackage());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     ref=[Parameter|ID]
	 */
	protected void sequence_ParameterRef(EObject context, ParameterRef semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ProbeLangPackage.Literals.PARAMETER_REF__REF) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ProbeLangPackage.Literals.PARAMETER_REF__REF));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParameterRefAccess().getRefParameterIDTerminalRuleCall_0_1(), semanticObject.getRef());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_Parameter(EObject context, Parameter semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ProbeLangPackage.Literals.PARAMETER__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ProbeLangPackage.Literals.PARAMETER__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParameterAccess().getNameIDTerminalRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID (parameters+=Parameter parameters+=Parameter*)? codeSequcene+=CodeElement*)
	 */
	protected void sequence_Pattern(EObject context, Pattern semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID type=Classifier properties+=Property*)
	 */
	protected void sequence_Probe(EObject context, Probe semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (type=Classifier name=ID properties+=ReferenceProperty*)
	 */
	protected void sequence_Property(EObject context, Property semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ref=[EStructuralFeature|ID] properties+=ReferenceProperty*)
	 */
	protected void sequence_ReferenceProperty(EObject context, ReferenceProperty semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=STRING
	 */
	protected void sequence_XStringLiteral(EObject context, XStringLiteral semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ProbeLangPackage.Literals.XSTRING_LITERAL__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ProbeLangPackage.Literals.XSTRING_LITERAL__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getXStringLiteralAccess().getValueSTRINGTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
}

/*
 * generated by Xtext
 */
package de.cau.cs.se.instrumentation.al.serializer;

import com.google.inject.Inject;
import de.cau.cs.se.instrumentation.al.aspectLang.Advice;
import de.cau.cs.se.instrumentation.al.aspectLang.Annotation;
import de.cau.cs.se.instrumentation.al.aspectLang.ApplicationModel;
import de.cau.cs.se.instrumentation.al.aspectLang.Aspect;
import de.cau.cs.se.instrumentation.al.aspectLang.AspectLangPackage;
import de.cau.cs.se.instrumentation.al.aspectLang.AspectModel;
import de.cau.cs.se.instrumentation.al.aspectLang.Collector;
import de.cau.cs.se.instrumentation.al.aspectLang.CompositionQuery;
import de.cau.cs.se.instrumentation.al.aspectLang.ContainerNode;
import de.cau.cs.se.instrumentation.al.aspectLang.Event;
import de.cau.cs.se.instrumentation.al.aspectLang.FloatValue;
import de.cau.cs.se.instrumentation.al.aspectLang.Import;
import de.cau.cs.se.instrumentation.al.aspectLang.IntValue;
import de.cau.cs.se.instrumentation.al.aspectLang.InternalFunctionProperty;
import de.cau.cs.se.instrumentation.al.aspectLang.LocationQuery;
import de.cau.cs.se.instrumentation.al.aspectLang.MethodQuery;
import de.cau.cs.se.instrumentation.al.aspectLang.ParamExpression;
import de.cau.cs.se.instrumentation.al.aspectLang.ParamQuery;
import de.cau.cs.se.instrumentation.al.aspectLang.ParameterDeclaration;
import de.cau.cs.se.instrumentation.al.aspectLang.ParameterQuery;
import de.cau.cs.se.instrumentation.al.aspectLang.ParentNode;
import de.cau.cs.se.instrumentation.al.aspectLang.Pointcut;
import de.cau.cs.se.instrumentation.al.aspectLang.ReferenceValue;
import de.cau.cs.se.instrumentation.al.aspectLang.ReflectionProperty;
import de.cau.cs.se.instrumentation.al.aspectLang.RuntimeProperty;
import de.cau.cs.se.instrumentation.al.aspectLang.StringValue;
import de.cau.cs.se.instrumentation.al.aspectLang.SubPathNode;
import de.cau.cs.se.instrumentation.al.aspectLang.UtilizeAdvice;
import de.cau.cs.se.instrumentation.al.aspectLang.WildcardNode;
import de.cau.cs.se.instrumentation.al.services.AspectLangGrammarAccess;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class AspectLangSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private AspectLangGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == AspectLangPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case AspectLangPackage.ADVICE:
				sequence_Advice(context, (Advice) semanticObject); 
				return; 
			case AspectLangPackage.ANNOTATION:
				sequence_Annotation(context, (Annotation) semanticObject); 
				return; 
			case AspectLangPackage.APPLICATION_MODEL:
				sequence_ApplicationModel(context, (ApplicationModel) semanticObject); 
				return; 
			case AspectLangPackage.ASPECT:
				sequence_Aspect(context, (Aspect) semanticObject); 
				return; 
			case AspectLangPackage.ASPECT_MODEL:
				sequence_AspectModel(context, (AspectModel) semanticObject); 
				return; 
			case AspectLangPackage.COLLECTOR:
				sequence_Collector(context, (Collector) semanticObject); 
				return; 
			case AspectLangPackage.COMPOSITION_QUERY:
				sequence_CompositionQuery(context, (CompositionQuery) semanticObject); 
				return; 
			case AspectLangPackage.CONTAINER_NODE:
				if (rule == grammarAccess.getContainerNodeRule()) {
					sequence_ContainerNode(context, (ContainerNode) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getNodeRule()) {
					sequence_ContainerNode_Node(context, (ContainerNode) semanticObject); 
					return; 
				}
				else break;
			case AspectLangPackage.EVENT:
				sequence_Event(context, (Event) semanticObject); 
				return; 
			case AspectLangPackage.FLOAT_VALUE:
				sequence_FloatValue(context, (FloatValue) semanticObject); 
				return; 
			case AspectLangPackage.IMPORT:
				sequence_Import(context, (Import) semanticObject); 
				return; 
			case AspectLangPackage.INT_VALUE:
				sequence_IntValue(context, (IntValue) semanticObject); 
				return; 
			case AspectLangPackage.INTERNAL_FUNCTION_PROPERTY:
				sequence_InternalFunctionProperty(context, (InternalFunctionProperty) semanticObject); 
				return; 
			case AspectLangPackage.LOCATION_QUERY:
				sequence_LocationQuery(context, (LocationQuery) semanticObject); 
				return; 
			case AspectLangPackage.METHOD_QUERY:
				sequence_MethodQuery(context, (MethodQuery) semanticObject); 
				return; 
			case AspectLangPackage.PARAM_EXPRESSION:
				if (action == grammarAccess.getParamOperatorAccess().getParamExpressionLeftAction_1_0()
						|| rule == grammarAccess.getParamCompareRule()) {
					sequence_ParamCompare(context, (ParamExpression) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getParamOperatorRule()) {
					sequence_ParamCompare_ParamOperator(context, (ParamExpression) semanticObject); 
					return; 
				}
				else break;
			case AspectLangPackage.PARAM_QUERY:
				sequence_ParamQuery(context, (ParamQuery) semanticObject); 
				return; 
			case AspectLangPackage.PARAMETER_DECLARATION:
				sequence_ParameterDeclaration(context, (ParameterDeclaration) semanticObject); 
				return; 
			case AspectLangPackage.PARAMETER_QUERY:
				sequence_ParameterQuery(context, (ParameterQuery) semanticObject); 
				return; 
			case AspectLangPackage.PARENT_NODE:
				if (rule == grammarAccess.getNodeRule()) {
					sequence_Node_ParentNode(context, (ParentNode) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getParentNodeRule()) {
					sequence_ParentNode(context, (ParentNode) semanticObject); 
					return; 
				}
				else break;
			case AspectLangPackage.POINTCUT:
				sequence_Pointcut(context, (Pointcut) semanticObject); 
				return; 
			case AspectLangPackage.REFERENCE_VALUE:
				sequence_ReferenceValue(context, (ReferenceValue) semanticObject); 
				return; 
			case AspectLangPackage.REFLECTION_PROPERTY:
				sequence_ReflectionProperty(context, (ReflectionProperty) semanticObject); 
				return; 
			case AspectLangPackage.RUNTIME_PROPERTY:
				sequence_RuntimeProperty(context, (RuntimeProperty) semanticObject); 
				return; 
			case AspectLangPackage.STRING_VALUE:
				sequence_StringValue(context, (StringValue) semanticObject); 
				return; 
			case AspectLangPackage.SUB_PATH_NODE:
				if (rule == grammarAccess.getNodeRule()) {
					sequence_Node_SubPathNode(context, (SubPathNode) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getSubPathNodeRule()) {
					sequence_SubPathNode(context, (SubPathNode) semanticObject); 
					return; 
				}
				else break;
			case AspectLangPackage.UTILIZE_ADVICE:
				sequence_UtilizeAdvice(context, (UtilizeAdvice) semanticObject); 
				return; 
			case AspectLangPackage.WILDCARD_NODE:
				if (rule == grammarAccess.getNodeRule()) {
					sequence_Node_WildcardNode(context, (WildcardNode) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getWildcardNodeRule()) {
					sequence_WildcardNode(context, (WildcardNode) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     Advice returns Advice
	 *
	 * Constraint:
	 *     (name=ID (parameterDeclarations+=ParameterDeclaration parameterDeclarations+=ParameterDeclaration*)? collectors+=Collector*)
	 */
	protected void sequence_Advice(ISerializationContext context, Advice semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Annotation returns Annotation
	 *
	 * Constraint:
	 *     (name=ID value=STRING)
	 */
	protected void sequence_Annotation(ISerializationContext context, Annotation semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.ANNOTATION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.ANNOTATION__NAME));
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.ANNOTATION__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.ANNOTATION__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAnnotationAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getAnnotationAccess().getValueSTRINGTerminalRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ApplicationModel returns ApplicationModel
	 *
	 * Constraint:
	 *     (handler=ID name=ID model=STRING)
	 */
	protected void sequence_ApplicationModel(ISerializationContext context, ApplicationModel semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.APPLICATION_MODEL__HANDLER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.APPLICATION_MODEL__HANDLER));
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.APPLICATION_MODEL__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.APPLICATION_MODEL__NAME));
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.APPLICATION_MODEL__MODEL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.APPLICATION_MODEL__MODEL));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getApplicationModelAccess().getHandlerIDTerminalRuleCall_1_0(), semanticObject.getHandler());
		feeder.accept(grammarAccess.getApplicationModelAccess().getNameIDTerminalRuleCall_3_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getApplicationModelAccess().getModelSTRINGTerminalRuleCall_4_0(), semanticObject.getModel());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     AspectModel returns AspectModel
	 *
	 * Constraint:
	 *     (name=QualifiedName imports+=Import* sources+=ApplicationModel* (advices+=Advice | pointcuts+=Pointcut | aspects+=Aspect)*)
	 */
	protected void sequence_AspectModel(ISerializationContext context, AspectModel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Aspect returns Aspect
	 *
	 * Constraint:
	 *     (pointcut=[Pointcut|QualifiedName] advices+=UtilizeAdvice advices+=UtilizeAdvice*)
	 */
	protected void sequence_Aspect(ISerializationContext context, Aspect semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Collector returns Collector
	 *
	 * Constraint:
	 *     (insertionPoint=InsertionPoint events+=Event+)
	 */
	protected void sequence_Collector(ISerializationContext context, Collector semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     CompositionQuery returns CompositionQuery
	 *
	 * Constraint:
	 *     (modifier=QueryModifier? subQueries+=LocationQuery*)
	 */
	protected void sequence_CompositionQuery(ISerializationContext context, CompositionQuery semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ContainerNode returns ContainerNode
	 *
	 * Constraint:
	 *     container=[Container|ID]
	 */
	protected void sequence_ContainerNode(ISerializationContext context, ContainerNode semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.CONTAINER_NODE__CONTAINER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.CONTAINER_NODE__CONTAINER));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getContainerNodeAccess().getContainerContainerIDTerminalRuleCall_0_1(), semanticObject.getContainer());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Node returns ContainerNode
	 *
	 * Constraint:
	 *     (container=[Container|ID] parameter=ParamQuery?)
	 */
	protected void sequence_ContainerNode_Node(ISerializationContext context, ContainerNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Event returns Event
	 *
	 * Constraint:
	 *     (type=[RecordType|QualifiedName] (initializations+=Value initializations+=Value*)?)
	 */
	protected void sequence_Event(ISerializationContext context, Event semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ParamOperator returns FloatValue
	 *     ParamOperator.ParamExpression_1_0 returns FloatValue
	 *     ParamCompare returns FloatValue
	 *     ParamCompare.ParamExpression_1_0 returns FloatValue
	 *     Value returns FloatValue
	 *     FloatValue returns FloatValue
	 *
	 * Constraint:
	 *     value=FLOAT
	 */
	protected void sequence_FloatValue(ISerializationContext context, FloatValue semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.FLOAT_VALUE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.FLOAT_VALUE__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFloatValueAccess().getValueFLOATTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Import returns Import
	 *
	 * Constraint:
	 *     importedNamespace=QualifiedNameWithWildcard
	 */
	protected void sequence_Import(ISerializationContext context, Import semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.IMPORT__IMPORTED_NAMESPACE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.IMPORT__IMPORTED_NAMESPACE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_0(), semanticObject.getImportedNamespace());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ParamOperator returns IntValue
	 *     ParamOperator.ParamExpression_1_0 returns IntValue
	 *     ParamCompare returns IntValue
	 *     ParamCompare.ParamExpression_1_0 returns IntValue
	 *     Value returns IntValue
	 *     IntValue returns IntValue
	 *
	 * Constraint:
	 *     value=INT
	 */
	protected void sequence_IntValue(ISerializationContext context, IntValue semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.INT_VALUE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.INT_VALUE__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getIntValueAccess().getValueINTTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Parameter returns InternalFunctionProperty
	 *     InternalFunctionProperty returns InternalFunctionProperty
	 *
	 * Constraint:
	 *     function=InternalFunction
	 */
	protected void sequence_InternalFunctionProperty(ISerializationContext context, InternalFunctionProperty semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.INTERNAL_FUNCTION_PROPERTY__FUNCTION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.INTERNAL_FUNCTION_PROPERTY__FUNCTION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getInternalFunctionPropertyAccess().getFunctionInternalFunctionEnumRuleCall_0(), semanticObject.getFunction());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     LocationQuery returns LocationQuery
	 *
	 * Constraint:
	 *     (node=Node (specialization=LocationQuery | composition=CompositionQuery)?)
	 */
	protected void sequence_LocationQuery(ISerializationContext context, LocationQuery semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     MethodQuery returns MethodQuery
	 *
	 * Constraint:
	 *     (
	 *         modifier=[MethodModifier|ID]? 
	 *         returnType=[Type|ID]? 
	 *         (methodReference=[Method|ID] (parameterQueries+=ParameterQuery parameterQueries+=ParameterQuery*)?)?
	 *     )
	 */
	protected void sequence_MethodQuery(ISerializationContext context, MethodQuery semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Node returns ParentNode
	 *
	 * Constraint:
	 *     parameter=ParamQuery?
	 */
	protected void sequence_Node_ParentNode(ISerializationContext context, ParentNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Node returns SubPathNode
	 *
	 * Constraint:
	 *     parameter=ParamQuery?
	 */
	protected void sequence_Node_SubPathNode(ISerializationContext context, SubPathNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Node returns WildcardNode
	 *
	 * Constraint:
	 *     parameter=ParamQuery?
	 */
	protected void sequence_Node_WildcardNode(ISerializationContext context, WildcardNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ParamOperator.ParamExpression_1_0 returns ParamExpression
	 *     ParamCompare returns ParamExpression
	 *
	 * Constraint:
	 *     (left=ParamCompare_ParamExpression_1_0 operator=LogicOperator right=Value)
	 */
	protected void sequence_ParamCompare(ISerializationContext context, ParamExpression semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.PARAM_EXPRESSION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.PARAM_EXPRESSION__LEFT));
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.PARAM_EXPRESSION__OPERATOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.PARAM_EXPRESSION__OPERATOR));
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.PARAM_EXPRESSION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.PARAM_EXPRESSION__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getParamCompareAccess().getParamExpressionLeftAction_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getParamCompareAccess().getOperatorLogicOperatorEnumRuleCall_1_1_0(), semanticObject.getOperator());
		feeder.accept(grammarAccess.getParamCompareAccess().getRightValueParserRuleCall_1_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ParamOperator returns ParamExpression
	 *
	 * Constraint:
	 *     (
	 *         (left=ParamOperator_ParamExpression_1_0 logic=LogicOperator right=ParamCompare) | 
	 *         (left=ParamCompare_ParamExpression_1_0 operator=LogicOperator right=Value)
	 *     )
	 */
	protected void sequence_ParamCompare_ParamOperator(ISerializationContext context, ParamExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ParamQuery returns ParamQuery
	 *
	 * Constraint:
	 *     queries+=ParamOperator+
	 */
	protected void sequence_ParamQuery(ISerializationContext context, ParamQuery semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ParameterDeclaration returns ParameterDeclaration
	 *
	 * Constraint:
	 *     (type='type' name=ID)
	 */
	protected void sequence_ParameterDeclaration(ISerializationContext context, ParameterDeclaration semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.PARAMETER_DECLARATION__TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.PARAMETER_DECLARATION__TYPE));
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.PARAMETER_DECLARATION__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.PARAMETER_DECLARATION__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getParameterDeclarationAccess().getTypeTypeKeyword_0_0(), semanticObject.getType());
		feeder.accept(grammarAccess.getParameterDeclarationAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ParameterQuery returns ParameterQuery
	 *
	 * Constraint:
	 *     (modifier=[ParameterModifier|ID]? type=[Type|ID] parameter=[Parameter|ID]?)
	 */
	protected void sequence_ParameterQuery(ISerializationContext context, ParameterQuery semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ParentNode returns ParentNode
	 *
	 * Constraint:
	 *     {ParentNode}
	 */
	protected void sequence_ParentNode(ISerializationContext context, ParentNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Pointcut returns Pointcut
	 *
	 * Constraint:
	 *     (annotation=Annotation? name=ID location=LocationQuery method=MethodQuery?)
	 */
	protected void sequence_Pointcut(ISerializationContext context, Pointcut semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ParamOperator returns ReferenceValue
	 *     ParamOperator.ParamExpression_1_0 returns ReferenceValue
	 *     ParamCompare returns ReferenceValue
	 *     ParamCompare.ParamExpression_1_0 returns ReferenceValue
	 *     Value returns ReferenceValue
	 *     ReferenceValue returns ReferenceValue
	 *
	 * Constraint:
	 *     (query=LocationQuery? parameter=Parameter)
	 */
	protected void sequence_ReferenceValue(ISerializationContext context, ReferenceValue semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Parameter returns ReflectionProperty
	 *     ReflectionProperty returns ReflectionProperty
	 *
	 * Constraint:
	 *     function=ReflectionFunction
	 */
	protected void sequence_ReflectionProperty(ISerializationContext context, ReflectionProperty semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.REFLECTION_PROPERTY__FUNCTION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.REFLECTION_PROPERTY__FUNCTION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getReflectionPropertyAccess().getFunctionReflectionFunctionEnumRuleCall_1_0(), semanticObject.getFunction());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Parameter returns RuntimeProperty
	 *     RuntimeProperty returns RuntimeProperty
	 *
	 * Constraint:
	 *     reference=ID
	 */
	protected void sequence_RuntimeProperty(ISerializationContext context, RuntimeProperty semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.RUNTIME_PROPERTY__REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.RUNTIME_PROPERTY__REFERENCE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRuntimePropertyAccess().getReferenceIDTerminalRuleCall_1_0(), semanticObject.getReference());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ParamOperator returns StringValue
	 *     ParamOperator.ParamExpression_1_0 returns StringValue
	 *     ParamCompare returns StringValue
	 *     ParamCompare.ParamExpression_1_0 returns StringValue
	 *     Value returns StringValue
	 *     StringValue returns StringValue
	 *
	 * Constraint:
	 *     value=STRING
	 */
	protected void sequence_StringValue(ISerializationContext context, StringValue semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, AspectLangPackage.Literals.STRING_VALUE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, AspectLangPackage.Literals.STRING_VALUE__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStringValueAccess().getValueSTRINGTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     SubPathNode returns SubPathNode
	 *
	 * Constraint:
	 *     {SubPathNode}
	 */
	protected void sequence_SubPathNode(ISerializationContext context, SubPathNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     UtilizeAdvice returns UtilizeAdvice
	 *
	 * Constraint:
	 *     (advice=[Advice|QualifiedName] (parameterAssignments+=Value parameterAssignments+=Value*)?)
	 */
	protected void sequence_UtilizeAdvice(ISerializationContext context, UtilizeAdvice semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     WildcardNode returns WildcardNode
	 *
	 * Constraint:
	 *     {WildcardNode}
	 */
	protected void sequence_WildcardNode(ISerializationContext context, WildcardNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}

/*
 * generated by Xtext
 */
package de.cau.cs.se.instrumentation.rl.validation

import de.cau.cs.se.instrumentation.rl.generator.InternalErrorException
import de.cau.cs.se.instrumentation.rl.recordLang.ArrayLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.BooleanLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.Classifier
import de.cau.cs.se.instrumentation.rl.recordLang.Constant
import de.cau.cs.se.instrumentation.rl.recordLang.ConstantLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.FloatLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.IntLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.Literal
import de.cau.cs.se.instrumentation.rl.recordLang.PartialRecordType
import de.cau.cs.se.instrumentation.rl.recordLang.Property
import de.cau.cs.se.instrumentation.rl.recordLang.RecordLangFactory
import de.cau.cs.se.instrumentation.rl.recordLang.RecordLangPackage
import de.cau.cs.se.instrumentation.rl.recordLang.RecordType
import de.cau.cs.se.instrumentation.rl.recordLang.StringLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.Type
import de.cau.cs.se.instrumentation.rl.typing.PrimitiveTypes
import java.util.ArrayList
import java.util.Collection
import java.util.List
import org.eclipse.emf.ecore.EDataType
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.xbase.lib.Pair

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class RecordLangValidator extends AbstractRecordLangValidator {

  	public static val INVALID_NAME = 'invalidName'
  	
  	/**
  	 * Check if an alias is a cyclic definition.
  	 */
  	@Check
  	def checkCyclicAlias(Property property) {
  		if (property.referTo != null) { // property is just an alias
  			val List<Property> visitedProperties = new ArrayList<Property>()
  			visitedProperties.add(property)
  			var referredProperty = property.referTo
  			
  			while (referredProperty.referTo != null) {
  				if (visitedProperties.contains(referredProperty)) { // cyclic definition
  					 error('Property alias ' + property.name + ' has a cyclic definition.', 
						RecordLangPackage$Literals::PROPERTY__REFER_TO,
						INVALID_NAME)
					return
  				} 
  				visitedProperties.add(referredProperty)
  				referredProperty = referredProperty.referTo
  			}
  				
  		}
  	}

	/**
	 * Check whether a property has been declared twice with different types. 
	 */
	@Check
	def checkPropertyDeclaration(Property property) {
		if (property.eContainer instanceof Type) {
			if (PropertyEvaluation::collectAllProperties(property.eContainer as Type).exists[p | p.name.equals(property.name) && p != property]) {
				val Property otherProperty = PropertyEvaluation::collectAllProperties(property.eContainer as Type).findFirst[p | p.name.equals(property.name) && p != property]
				if (!typeAndPackageIdentical(otherProperty.type,property.type))
					error('Property has been defined in ' + (otherProperty.eContainer as Type).name + '. Cannot be declared again with a different type.', 
						RecordLangPackage$Literals::PROPERTY__NAME,
						INVALID_NAME)
			}
		}
	}
	

	/** 
	 * Check a RecordType for multiple inheritance of the same property with different types. 
	 */
	@Check
	def checkRecordTypeComposition(RecordType type) {
		val Collection<Property> properties = PropertyEvaluation::collectAllProperties(type)
		if (properties.exists[p | properties.exists[pInner | p.name.equals(pInner.name) && p != pInner]]) {
			val Collection<Pair<Property,Property>> duplicates = new ArrayList<Pair<Property,Property>>()
			properties.forEach[p | duplicates.add(p.findDuplicate(properties))]
			duplicates.forEach[entry | if (!typeAndPackageIdentical(entry.key.type,entry.value.type))
				error('Multiple property inheritance must have same type: Property ' + entry.key.name + 
						' inherited from ' + (entry.key.eContainer as Type).name + ' and ' + (entry.value.eContainer as Type).name, 
						RecordLangPackage$Literals::TYPE__PARENTS,
						INVALID_NAME)
			]
		}
	}
		
	/** 
	 * Check a PartialType for multiple inheritance of the same property with different types. 
	 */	
	@Check
	def checkPartialTypeComposition(PartialRecordType type) {
		val Collection<Property> properties = PropertyEvaluation::collectAllProperties(type)
		if (properties.exists[p | properties.exists[pInner | p.name.equals(pInner.name) && p != pInner]]) {
			val Collection<Pair<Property,Property>> duplicates = new ArrayList<Pair<Property,Property>>()
			properties.forEach[p | duplicates.add(p.findDuplicate(properties))]
			duplicates.forEach[entry | if (!typeAndPackageIdentical(entry.key.type,entry.value.type))
				error('Multiple property inheritance must have same type: Property ' + entry.key.name + 
						' inherited from ' + (entry.key.eContainer as Type).name + ' and ' + (entry.value.eContainer as Type).name, 
						RecordLangPackage$Literals::TYPE__PARENTS,
						INVALID_NAME)
			]
		}
	}
	
	/**
	 * Check it a given constant's type and the assigned value's type match.
	 */
	@Check
	def checkConstantValueTyping(Constant constant) {
		if (constant.value != null) {
			if (!compareTypesInAssignment(constant.type, constant.value.type, constant.value)) {
				error('Constant type \'' + constant.type.createFQNTypeName + '\' does not match value type \'' + constant.value.type.createFQNTypeName + '\'.', 
							RecordLangPackage$Literals::CONSTANT__TYPE,
							INVALID_NAME)
			}
		}
	}
	
	/**
	 * Check it a given property's type and the assigned value's type match.
	 */
	@Check
	def checkPropertyValueTyping(Property property) {
		if (property.value != null) {
			if (!compareTypesInAssignment(property.type, property.value.type, property.value)) {
				error('Property type \'' + property.type.createFQNTypeName + '\' does not match value type \'' + property.value.type.createFQNTypeName + '\'.', 
							RecordLangPackage$Literals::PROPERTY__TYPE,
							INVALID_NAME)
			}
		}
	}
	
	/**
	 * Check it a given type of one array element matches the other.
	 */
	@Check
	def checkValueTyping(ArrayLiteral literal) {
		if (literal.literals.size > 0) {
			val type = literal.literals.get(0).type
			if (!literal.literals.forall[element | typeEquality(element.type,type)]) {
				error('Value types ' + literal.literals.map[it.type.createFQNTypeName].join(', ') + ' do not match', 
							RecordLangPackage$Literals::ARRAY_LITERAL__LITERALS)
			}
		}
	}
	
	/**
	 * Create a full qualified type name based on a classifier.
	 * 
	 * @param classifier the classifier where the FQN is computed for
	 */
	def String createFQNTypeName(Classifier classifier) {
		classifier.class_.name + classifier.sizes.map['[' + (if (it.size != 0) it.size else '') + ']'].join
	}
	
	/**
	 * Check if types are a exact match.
	 */
	def typeEquality(Classifier left, Classifier right) {
		if (left.class_.name.equals(right.class_.name)) {
			if (left.sizes.size == right.sizes.size) {
				var i=0
				while (i<left.sizes.size) {
					if (left.sizes.get(i).size != right.sizes.get(i).size)
						return false
					i=i+1
				}
				return true
			}
		}
	}
	
	/**
	 * Compare two types for a type match in a value assignment.
	 */
	def compareTypesInAssignment(Classifier left, Classifier right, Literal literal) {
		if (left.package != null && right.package != null) {
			if (left.package.package.nsURI.equals(right.package.package.nsURI)) 
				return compareClassifierTypesInAssignment(left,right,literal)
			else
				return false
		} else if (left.package == null && right.package == null) {
			return compareClassifierTypesInAssignment(left,right,literal)
		} else
			return false	
	}
	
	/**
	 * Check if types match in an assignment.
	 */
	def compareClassifierTypesInAssignment(Classifier left, Classifier right, Literal literal) {
		if (compareClassifierTypeEquvalenceSet(left,right,literal)) {
			if (left.sizes.size == right.sizes.size) {
				var i=0
				while (i<left.sizes.size) {
					if ((left.sizes.get(i).size != right.sizes.get(i).size) &&
						(left.sizes.get(i).size != 0))
						return false
					i=i+1
				}
				return true
			} else
				return false
		} else
			return false
	}
	
	/**
	 * Check if the left and the right type are compatible. First check if they are identical. If
	 * not use checkTypeEquivalenceSet to check for compatible types. This is required for constants values.
	 */
	def compareClassifierTypeEquvalenceSet(Classifier left, Classifier right, Literal literal) {
		if (left.class_.name.equals(right.class_.name))
			true
		else 
			checkTypeEquivalenceSet(left,right,literal)
	}
	
	/**
	 * Check if types match.
	 */
	def checkTypeEquivalenceSet(Classifier left, Classifier right, Literal literal) {
		if (left.class_.name.equals('double')) {
				if (right.class_.name.equals('float')) {
					if (literal instanceof FloatLiteral)
						true
					else if (literal instanceof ArrayLiteral)
						checkAllLiteralsArtOfType(FloatLiteral,literal as ArrayLiteral)
				} else
					false
			} else if (left.class_.name.equals('long')) {
				if (right.class_.name.equals('int')) {
					if (literal instanceof IntLiteral)
						if (((literal as IntLiteral).value >= Long.MIN_VALUE) && 
							((literal as IntLiteral).value <= Long.MAX_VALUE))
							true
						else
						 	false
					else if (literal instanceof ArrayLiteral)
						checkAllLiteralsArtOfType(IntLiteral,literal as ArrayLiteral)
				} else
					false
			} else if (left.class_.name.equals('byte')) {
				if (right.class_.name.equals('int')) {
					if (literal instanceof IntLiteral)
						if (((literal as IntLiteral).value >= Byte.MIN_VALUE) && 
							((literal as IntLiteral).value <= Byte.MAX_VALUE))
							true
						else
						 	false
					else if (literal instanceof ArrayLiteral)
						checkAllLiteralsArtOfType(IntLiteral,literal as ArrayLiteral)
				} else
					false
			} else if (left.class_.name.equals('short')) {
				if (right.class_.name.equals('int')) {
					if (literal instanceof IntLiteral)
						if (((literal as IntLiteral).value >= Short.MIN_VALUE) && 
							((literal as IntLiteral).value <= Short.MAX_VALUE))
							true
						else
						 	false
					else if (literal instanceof ArrayLiteral)
						checkAllLiteralsArtOfType(IntLiteral,literal as ArrayLiteral)
				} else
					false
			} else
				false
	}
	
	
	
	/**
	 * Check in depth if all elements match the specific type.
	 */
	def boolean checkAllLiteralsArtOfType(Class<? extends Literal> type, ArrayLiteral literal) {
		literal.literals.forall[element |
			if (element instanceof ArrayLiteral) 
				checkAllLiteralsArtOfType(type, element as ArrayLiteral)
			else 
				type.isInstance(element)
		]
	}
	
	/**
	 * Compute the classifier for a literal.
	 */
	def dispatch Classifier getType(StringLiteral literal) {
		if (literal.value.length != 1) 
			createPrimitiveClassifier(PrimitiveTypes.ESTRING.EType)
		else
			createPrimitiveClassifier(PrimitiveTypes.ECHAR.EType)
	}
	def dispatch Classifier getType(IntLiteral literal) { createPrimitiveClassifier(PrimitiveTypes.EINT.EType) }
	def dispatch Classifier getType(FloatLiteral literal) { createPrimitiveClassifier(PrimitiveTypes.EFLOAT.EType) }
	def dispatch Classifier getType(BooleanLiteral literal) { createPrimitiveClassifier(PrimitiveTypes.EBOOLEAN.EType) }
	def dispatch Classifier getType(ConstantLiteral literal) { literal.value.type }
	def dispatch Classifier getType(ArrayLiteral literal) {
		val classifier = getType(literal.literals.get(0))
		val size = RecordLangFactory::eINSTANCE.createArraySize
		size.setSize(literal.literals.size)
		classifier.sizes.add(0,size)
		return classifier		
	}
		
	def dispatch Classifier getType(Literal literal) {
		throw new InternalErrorException('Unhandled literal type')
	}
	
	def createPrimitiveClassifier(EDataType type) {
		val classifier = RecordLangFactory::eINSTANCE.createClassifier()
		classifier.setClass(type)
		return classifier
	}
	
	/* -- service routines -- */
	
	/**
	 * Compare types of a property for equality including package name.
	 */
	def boolean typeAndPackageIdentical(Classifier left, Classifier right) {
		if (left.package != null) {
			if (right.package != null) {
				if (left.package.equals(right.package)) {
					return typeIdentical(left,right)
				} else
					return false
			} else
				return false
		} else {
			return typeIdentical(left,right)
		}
	}
	
	/**
	 * Compare types of a property for equality.
	 */
	def boolean typeIdentical(Classifier left, Classifier right) {
		if (left.class.equals(right.class)) {
			if (left.sizes.size == right.sizes.size) {
				var i = 0
				while (i<left.sizes.size) {
					if (left.sizes.get(i).size != right.sizes.get(i).size)
						return false
				}
				return true
			} else
				return false
		} else
			return false
	}
	
	def Pair<Property, Property> findDuplicate(Property property, Collection<Property> properties) {
		val Property second = properties.findFirst[p | property.name.equals(p.name) && p != property]
		return new Pair(property,second)
	}
	
}

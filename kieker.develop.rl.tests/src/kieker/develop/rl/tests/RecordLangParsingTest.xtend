/*
 * generated by Xtext 2.10.0
 */
package kieker.develop.rl.tests

import com.google.inject.Inject
import kieker.develop.rl.recordLang.Model
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kieker.develop.rl.typing.base.BaseTypes

import static extension kieker.develop.rl.tests.CodeTemplates.*
import kieker.develop.rl.recordLang.ComplexType
import kieker.develop.rl.recordLang.EventType
import java.util.Map
import java.util.HashMap

@RunWith(XtextRunner)
@InjectWith(RecordLangInjectorProvider)
class RecordLangParsingTest{
	
	private static String PACKAGE_NAME = "test.record"
	private static String BASE_ET_NAME = "Base"
	private static String EXTENDED_ET_NAME = "Extended"
	private static String INT_PROPERTY = "intProperty"
	private static String STRING_PROPERTY = "stringProperty"

	@Inject
	ParseHelper<Model> parseHelper

	@Test 
	def void loadModel() {
		val result = parseHelper.parse(minimalModel)
		
		/** asserts */
		result.testModel

		Assert.assertNotNull("Event type does not exist", result.types.size == 1)
		val entity = result.types.get(0)
		val properties = new HashMap<BaseTypes,String>()
		properties.put(BaseTypes.INT, INT_PROPERTY)
		
		entity.testEntity(BASE_ET_NAME, properties)
	}
	
	@Test
	def void checkInheritance() {
		val result = parseHelper.parse(inheritanceModel)
		
		/** asserts */
		result.testModel
		Assert.assertNotNull("Event types do not exist (2)", result.types.size == 2)
		
		val entity1 = result.types.get(0)
		val properties1 = new HashMap<BaseTypes,String>()
		properties1.put(BaseTypes.INT, INT_PROPERTY)
		
		entity1.testEntity(BASE_ET_NAME, properties1)
		
		val entity2 = result.types.get(1)
		val properties2 = new HashMap<BaseTypes,String>()
		properties2.put(BaseTypes.STRING, STRING_PROPERTY)
			
		entity2.testEntity(EXTENDED_ET_NAME, properties2)
		
		Assert.assertNotNull("Parent reference is null", (entity2 as EventType).parent)
		Assert.assertEquals("Wrong parent event type", entity1, (entity2 as EventType).parent)
	}
	
	/**
	 * Test whether the given type is an EventType and the properties match.
	 */
	def void testEntity(ComplexType type, String name, Map<BaseTypes,String> properties) {
		if (type instanceof EventType) {
			val entity = type as EventType
			Assert.assertEquals("Event type is not named " + name, entity.name, name)
			Assert.assertFalse("Event type has more properties than expected", (entity.properties.size > properties.size))
			Assert.assertFalse("Event type has less properties than expected", (entity.properties.size < properties.size))
		
			properties.forEach[key, value, i | 
				val property = entity.properties.get(i)
				Assert.assertTrue("Property " + name + "." + value + " does not exist", property.name.equals(value))
				Assert.assertTrue("Property " + name + "." + value + " is not typed " + key.name,
					BaseTypes.getTypeEnum(property.type.type) == key
				)
			]
		} else
			Assert.fail("Type is not an EventType; found" + type.toString)
	}
	
	private def minimalModel() {
		model(PACKAGE_NAME,eventType(BASE_ET_NAME, false, null, null, simpleProperty (BaseTypes.INT, INT_PROPERTY)))	
	}
	
	private def inheritanceModel() {
		model(PACKAGE_NAME,
			eventType(BASE_ET_NAME, false, null, null, 
				simpleProperty (BaseTypes.INT, INT_PROPERTY)
			) +
			eventType(EXTENDED_ET_NAME, false, BASE_ET_NAME, null, 
				simpleProperty (BaseTypes.STRING, STRING_PROPERTY)
			)
		)
	}
	
	private def testModel(Model result) {
		Assert.assertNotNull(result)
		Assert.assertEquals("Package name does not match", result.name, PACKAGE_NAME)
	}
}

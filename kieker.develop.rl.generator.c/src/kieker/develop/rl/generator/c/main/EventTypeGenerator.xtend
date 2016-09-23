package kieker.develop.rl.generator.c.main

import kieker.develop.rl.generator.InternalErrorException
import kieker.develop.rl.generator.TypeInputModel
import kieker.develop.rl.recordLang.Classifier
import kieker.develop.rl.recordLang.Property
import kieker.develop.rl.recordLang.EventType
import kieker.develop.rl.typing.base.BaseTypes

import static extension kieker.develop.rl.generator.c.CommonCFunctionsExtension.*
import static extension kieker.develop.rl.typing.PropertyResolution.*
import static extension kieker.develop.rl.typing.TypeResolution.*
import kieker.develop.rl.generator.AbstractTypeGenerator
import kieker.develop.rl.recordLang.Type

class EventTypeGenerator extends AbstractTypeGenerator<EventType> {

	override accepts(Type type) {
		if (type instanceof EventType)
			!(type as EventType).abstract
		else
			false
	}
			
	/**
	 * Primary code generation template.
	 * 
	 * @param type
	 * 		one record type to be used to create monitoring record
	 */
	override generate(TypeInputModel<EventType> input) {
		val definedAuthor = if (input.type.author == null) input.author else input.type.author
		val definedVersion = if (input.type.since == null) input.version else input.type.since
		'''
		«input.header»#include <stdlib.h>
		#include <kieker.h>
		#include "«input.type.directoryPathName»/«input.type.name.cstyleName».h"

		/**
		 * Author: «definedAuthor»
		 * Version: «definedVersion»
		 */
		«input.type.createSerializer»
		'''
	}
	
	/**
	 * Generate the serializer for the given record type.
	 */
	private def createSerializer(EventType type) '''
		/*
		 * Serialize an «type.name» and return the size of the written structure.
		 *
		 * buffer = the buffer to send the data
		 * id = id to identify the record type
		 * offset = store data to buffer at offset
		 * value = the value to be stored
		 *
		 * returns size of written structure
		 */
		int «type.packageName»_«type.name.cstyleName»_serialize(char *buffer, const int id, const int offset, const «type.packageName»_«type.name.cstyleName» value) {
			int length = 0;
			«type.collectAllDataProperties.map[createValueSerializerInvocation].join»
			return length;
		}
	'''
	
	/**
	 * 
	 */
	private def createValueSerializerInvocation(Property property) '''
		length += «property.findType.valueSerializerName»(buffer,offset,«property.name»);
	'''
		
	/**
	 * 
	 */
	private def valueSerializerName(Classifier classifier) throws InternalErrorException {
		'kieker_serialize_' + switch (BaseTypes.getTypeEnum(classifier.type)) {
			case STRING : 'string'
			case BYTE : 'int8'
			case SHORT : 'int16'
			case INT : 'int32'
			case LONG : 'int64'
			case FLOAT : 'float'
			case DOUBLE : 'double'
			case CHAR : 'int16'
			case BOOLEAN : 'boolean'
		}
	}
			
}
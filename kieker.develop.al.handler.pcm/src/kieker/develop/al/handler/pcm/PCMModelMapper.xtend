/***************************************************************************
 * Copyright 2017 Kieker Project (http://kieker-monitoring.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
 package kieker.develop.al.handler.pcm

import de.cau.cs.se.geco.architecture.framework.TraceModelProvider
import kieker.develop.al.aspectLang.ApplicationModel
import kieker.develop.al.mapping.MappingFactory
import kieker.develop.al.modelhandling.IModelMapper
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.palladiosimulator.pcm.core.entity.NamedElement
import kieker.develop.al.mapping.MappingModel
import java.util.HashMap

/**
 * The model mapper provides the horizontal mapping between
 * PCM and the MappingModel of the IAL and the vertical mapping
 * between the PCM and the target language Java.
 * 
 * @author Reiner Jung
 */
class PCMModelMapper implements IModelMapper<NamedElement, String /*PCMCodeNode*/> {
	
	val modelRepository = new HashMap<String, MappingModel>
	
	new() {
		println("Instantiate Model Mapper " + this)
	}
				
	override loadModel(ApplicationModel input, ResourceSet resourceSet) {
		
		var result = modelRepository.get(input.model)
		if (result == null) {
			// Get the resource
			val Resource source = resourceSet.getResource(URI::createPlatformResourceURI(input.model, true), true)
	
			// create main result model
			result = MappingFactory.eINSTANCE.createMappingModel()
			// determine all interfaces
			val interfaceMap=PCMLoadModel.createInterfaces(source)
			// compose container hierarchy
			PCMLoadModel.createContainerHierarchy(source, result, interfaceMap)
			// contents must be called via its getter otherwise xtend will used the variable which may
			// result in an null pointer result
			modelRepository.put(input.model, result)
		}
		
		return result
	}
	
	override name() { "pcm" }
	
	override traceModelProvider(URI uri, ResourceSet resourceSet) {
		val provider = new TraceModelProvider<NamedElement,String>
		val resource = resourceSet.getResource(uri, true)
		//resource.allContents.filter(typeof(PCMMapping)).forEach[mapping |
		//	provider.add(mapping.source, mapping.target)
		//]
		
		return provider
	}
	
}
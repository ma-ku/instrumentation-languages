/***************************************************************************
 * Copyright 2016 Kieker Project (http://kieker-monitoring.net)
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
package kieker.develop.al.generator.java

import java.util.Collection
import kieker.develop.rl.generator.IGeneratorProvider
import kieker.develop.rl.ouput.config.AbstractOutletConfiguration
import kieker.develop.al.generator.java.aspectj.AspectJConfigurationOutletConfiguration
import org.w3c.dom.Document
import kieker.develop.al.intermediate.IntermediateModel
import kieker.develop.al.generator.java.javaee.JavaEEConfigurationOutletConfiguration
import kieker.develop.al.generator.java.servlet.ServletConfigurationOutletConfiguration
import kieker.develop.al.generator.java.spring.SpringConfigurationOutletConfiguration

/**
 * Provider of generators for Java based pointcut
 * technologies and the appropriate outlet configurations.
 * 
 * @author Reiner Jung
 */
class JavaConfigurationGeneratorProvider implements IGeneratorProvider<IntermediateModel, Document> {
	
	override addOutletConfigurations(Collection<AbstractOutletConfiguration<IntermediateModel, Document>> configurations) {
		configurations += new AspectJConfigurationOutletConfiguration
		configurations += new JavaEEConfigurationOutletConfiguration
		configurations += new ServletConfigurationOutletConfiguration
		configurations += new SpringConfigurationOutletConfiguration
	}
	
}
package kieker.develop.rl.generator.delphi

import java.util.Collection
import kieker.develop.rl.generator.IGeneratorProvider
import kieker.develop.rl.ouput.config.AbstractOutletConfiguration
import kieker.develop.rl.recordLang.ComplexType

class GeneratorProvider implements IGeneratorProvider<ComplexType, CharSequence> {
	
	public static String LANG_DELPHI = "delphi"
	
	public static String TECH_KIEKER_DELPHI = "kieker-delphi"
	
	override void addOutletConfigurations(Collection<AbstractOutletConfiguration<ComplexType, CharSequence>> configurations) {
		configurations.add(new DelphiOutletConfiguration())		
	}
	
}
/*
 * generated by Xtext
 */
package kieker.develop.al.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import kieker.develop.al.ui.internal.AspectLangActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class AspectLangExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return AspectLangActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return AspectLangActivator.getInstance().getInjector(AspectLangActivator.KIEKER_DEVELOP_AL_ASPECTLANG);
	}
	
}
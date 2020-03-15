package osgisearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import osgisearch.filesystem.iteration.JarPathIterator;
import osgisearch.osgi.bundles.DefaultOSGIBundleFileProcessor;

@Configuration
public class AppConfig {

	@Bean(name = "jarPathIterator")
	public JarPathIterator JarPathIterator() {
		return new JarPathIterator();
	}

	@Bean(name = "defaultOSGIBundleFileProcessor")
	public DefaultOSGIBundleFileProcessor DefaultOSGIBundleFileProcessor() {
		return new DefaultOSGIBundleFileProcessor();
	}
}

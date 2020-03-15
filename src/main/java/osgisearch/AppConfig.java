package osgisearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import osgisearch.filesystem.iteration.JarPathIterator;
import osgisearch.indexing.DefaultOSGIBundleIndexer;
import osgisearch.indexing.IndexExportedPackagesStep;
import osgisearch.osgi.bundles.DefaultOSGIBundleFileProcessor;
import osgisearch.osgi.manifest.parsing.DefaultManifestParser;

@Configuration
public class AppConfig {

	@Bean(name = "defaultManifestParser")
	public DefaultManifestParser DefaultManifestParser() {
		return new DefaultManifestParser();
	}

	@Bean(name = "jarPathIterator")
	public JarPathIterator JarPathIterator() {
		return new JarPathIterator();
	}

	@Bean(name = "defaultOSGIBundleFileProcessor")
	public DefaultOSGIBundleFileProcessor DefaultOSGIBundleFileProcessor() {
		return new DefaultOSGIBundleFileProcessor();
	}

	@Bean(name = "defaultOSGIBundleIndexer")
	public DefaultOSGIBundleIndexer DefaultOSGIBundleIndexer() {
		return new DefaultOSGIBundleIndexer();
	}

	@Bean(name = "indexExportedPackagesStep")
	public IndexExportedPackagesStep IndexExportedPackagesStep() {
		return new IndexExportedPackagesStep();
	}
}

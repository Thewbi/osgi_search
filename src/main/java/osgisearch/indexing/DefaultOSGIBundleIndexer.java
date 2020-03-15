package osgisearch.indexing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import osgisearch.osgi.manifest.parsing.OSGIPackage;

public class DefaultOSGIBundleIndexer implements OSGIBundleIndexer {

	private final Map<String, List<OSGIPackage>> packages = new HashMap<>();

	public Map<String, List<OSGIPackage>> getPackages() {
		return packages;
	}

}

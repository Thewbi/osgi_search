package osgisearch.indexing;

import java.util.List;
import java.util.Map;

import osgisearch.osgi.manifest.parsing.OSGIPackage;

public interface OSGIBundleIndexer {

	Map<String, List<OSGIPackage>> getPackages();

}

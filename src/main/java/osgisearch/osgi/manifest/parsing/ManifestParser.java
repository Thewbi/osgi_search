package osgisearch.osgi.manifest.parsing;

import osgisearch.osgi.manifest.Manifest;

public interface ManifestParser {

	Manifest parse(String text);

}

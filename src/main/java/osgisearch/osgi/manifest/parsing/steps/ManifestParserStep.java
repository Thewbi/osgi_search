package osgisearch.osgi.manifest.parsing.steps;

import osgisearch.osgi.manifest.Manifest;

public interface ManifestParserStep {

	void process(Manifest manifest, Object... vargs);

}

package osgisearch.osgi.manifest.parsing;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import osgisearch.indexing.IndexExportedPackagesStep;
import osgisearch.osgi.manifest.DefaultManifest;
import osgisearch.osgi.manifest.Manifest;
import osgisearch.osgi.manifest.parsing.steps.AddExportedPackagesStep;
import osgisearch.osgi.manifest.parsing.steps.ConvertStringToManifestStep;
import osgisearch.osgi.manifest.parsing.steps.ManifestParserStep;

public class DefaultManifestParser implements ManifestParser {

	private static final Logger logger = LogManager.getLogger(DefaultManifestParser.class);

	private final ManifestParserStep convertStringToManifestStep = new ConvertStringToManifestStep();

	private final ManifestParserStep addExportedPackagesStep = new AddExportedPackagesStep();

	@Autowired
	private IndexExportedPackagesStep indexExportedPackagesStep;

	@Override
	public Manifest parse(final String manifestAsString) {

		logger.trace(manifestAsString);

		final Manifest manifest = new DefaultManifest();

		final String manifestAsStringTrimmed = StringUtils.trim(manifestAsString);

		if (StringUtils.isBlank(manifestAsStringTrimmed)) {
			return manifest;
		}

		convertStringToManifestStep.process(manifest, manifestAsStringTrimmed);
		addExportedPackagesStep.process(manifest);

		if (indexExportedPackagesStep != null) {
			indexExportedPackagesStep.process(manifest);
		}

		return manifest;
	}

	public void setIndexExportedPackagesStep(final IndexExportedPackagesStep indexExportedPackagesStep) {
		this.indexExportedPackagesStep = indexExportedPackagesStep;
	}

}

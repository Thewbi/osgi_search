package osgisearch.osgi.manifest.parsing.steps;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vdurmont.semver4j.Semver;

import osgisearch.osgi.manifest.Manifest;
import osgisearch.osgi.manifest.parsing.OSGIPackage;

public class AddExportedPackagesStep implements ManifestParserStep {

	private static final Logger logger = LogManager.getLogger(AddExportedPackagesStep.class);

	@Override
	public void process(final Manifest manifest, final Object... vargs) {

		final String exportedPackagesAsString = manifest.getExportPackage();

		logger.trace(exportedPackagesAsString);

		if (StringUtils.isBlank(exportedPackagesAsString)) {
			return;
		}

		final String[] split = StringUtils.split(exportedPackagesAsString, ",");

		for (final String packageAsString : split) {

			logger.trace(packageAsString);

			final OSGIPackage osgiPackage = new OSGIPackage();

			final String[] packageSplit = StringUtils.split(packageAsString, ";");

			osgiPackage.setName(StringUtils.trim(packageSplit[0]));

			final String versionAsString = packageSplit[1];

			final String[] versionSplit = StringUtils.split(versionAsString, "=", 2);
			final String version = StringUtils.substringBetween(versionSplit[1], "\"");

			osgiPackage
					.setVersion(new Semver(StringUtils.trim(version), com.vdurmont.semver4j.Semver.SemverType.LOOSE));

			manifest.getExportedPackages().add(osgiPackage);
			osgiPackage.setManifest(manifest);
		}
	}

}

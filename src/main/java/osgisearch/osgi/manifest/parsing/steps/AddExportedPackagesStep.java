package osgisearch.osgi.manifest.parsing.steps;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vdurmont.semver4j.Semver;
import com.vdurmont.semver4j.Semver.SemverType;

import osgisearch.osgi_manifestLexer;
import osgisearch.osgi_manifestParser;
import osgisearch.osgi_manifestParser.FqdnContext;
import osgisearch.osgi_manifestParser.OsgipackageContext;
import osgisearch.osgi_manifestParser.Osgipackage_descriptionContext;
import osgisearch.osgi_manifestParser.Osgipackage_elementContext;
import osgisearch.osgi_manifestParser.VersionContext;
import osgisearch.osgi.manifest.Manifest;
import osgisearch.osgi.manifest.parsing.OSGIPackage;

public class AddExportedPackagesStep implements ManifestParserStep {

	private static final Logger logger = LogManager.getLogger(AddExportedPackagesStep.class);

	@Override
	public void process(final Manifest manifest, final Object... vargs) {

		final String exportedPackagesAsString = manifest.getExportPackage();

		if (StringUtils.isBlank(exportedPackagesAsString)) {
			return;
		}

		logger.trace(exportedPackagesAsString);

		final osgi_manifestLexer lexer = new osgi_manifestLexer(CharStreams.fromString(exportedPackagesAsString));

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final osgi_manifestParser parser = new osgi_manifestParser(tokens);

		final Osgipackage_descriptionContext osgiPackageDescription = parser.osgipackage_description();

		for (final OsgipackageContext osgipackageContext : osgiPackageDescription.osgipackage()) {

			// add a new package
			final OSGIPackage osgiPackage = new OSGIPackage();
			manifest.getExportedPackages().add(osgiPackage);
			osgiPackage.setManifest(manifest);

			for (final Osgipackage_elementContext osgiPackageElementContext : osgipackageContext
					.osgipackage_element()) {

				final FqdnContext fqdn = osgiPackageElementContext.fqdn();
				if (fqdn != null) {

					final String fqdnAsString = fqdn.getText();

					if (StringUtils.equalsIgnoreCase(fqdnAsString, "uses")) {
						continue;
					}
					if (StringUtils.equalsIgnoreCase(fqdnAsString, "vendor")) {
						continue;
					}
					if (StringUtils.equalsIgnoreCase(fqdnAsString, "mandatory")) {
						continue;
					}
					if (StringUtils.equalsIgnoreCase(fqdnAsString, "poweruser")) {
						continue;
					}
					if (StringUtils.equalsIgnoreCase(fqdnAsString, "exclude")) {
						continue;
					}

					if (!StringUtils.equalsIgnoreCase(fqdnAsString, "version")) {
						logger.info("fqdn: " + fqdnAsString);
						osgiPackage.setName(StringUtils.trim(fqdnAsString));
					}

				}

				final VersionContext version = osgiPackageElementContext.version();
				if (version == null) {

					if (osgiPackageElementContext.getChildCount() > 1) {

						String ver = osgiPackageElementContext.getChild(2).getText();
						ver = StringUtils.substringBetween(ver, "\"");
						logger.trace("version: " + ver);

						osgiPackage.setVersion(new Semver(ver, SemverType.LOOSE));
					}
				} else {

					final String versionAsText = version.getText();
					logger.trace("version: " + versionAsText);

					osgiPackage.setVersion(new Semver(versionAsText, SemverType.LOOSE));
				}
			}
		}
	}
}

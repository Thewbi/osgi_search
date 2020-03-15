package osgisearch.osgi.manifest.parsing.steps;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import osgisearch.osgi.manifest.Manifest;

public class ConvertStringToManifestStep implements ManifestParserStep {

	private static final Logger logger = LogManager.getLogger(ConvertStringToManifestStep.class);

	@Override
	public void process(final Manifest manifest, final Object... vargs) {

		final String manifestAsStringTrimmed = (String) vargs[0];

		final String[] split = manifestAsStringTrimmed.split("\n");

		StringBuilder stringBuilder = new StringBuilder();

		for (final String line : split) {

			if (StringUtils.startsWith(line, "Manifest-") || StringUtils.startsWith(line, "Bundle-")
					|| StringUtils.startsWith(line, "Export-") || StringUtils.startsWith(line, "Import-")
					|| StringUtils.startsWith(line, "HomeDevice-MetaData") || StringUtils.startsWith(line, "X-")) {

				if (stringBuilder.length() > 0) {

					manifestEntryFound(manifest, stringBuilder.toString());
				}

				stringBuilder = new StringBuilder();
			}

			stringBuilder.append(StringUtils.trim(line));

		}

		if (stringBuilder.length() > 0) {

			manifestEntryFound(manifest, stringBuilder.toString());
		}
	}

	private void manifestEntryFound(final Manifest manifest, final String manifestEntry) {

		logger.trace(manifestEntry);

		final String[] split = manifestEntry.split(":", 2);

		final String key = StringUtils.trim(split[0]);
		final String value = StringUtils.trim(split[1]);

		if (StringUtils.startsWith(key, "X-")) {

			manifest.getExtensions().put(key, value);
			return;
		}

		switch (key) {

		case "Bundle-Activator":
			manifest.setActivator(value);
			break;

		case "Bundle-Category":
			manifest.setCategory(value);
			break;

		case "Bundle-ClassPath":
			manifest.setClassPath(value);
			break;

		case "Bundle-Description":
			manifest.setDescription(value);
			break;

		case "Bundle-DocURL":
			manifest.setDocURL(value);
			break;

		case "Bundle-ManifestVersion":
			manifest.setBundleManifestVersion(value);
			break;

		case "Bundle-Name":
			manifest.setName(value);
			break;

		case "Bundle-NativeCode":
			manifest.setNativeCode(value);
			break;

		case "Bundle-RequiredExecutionEnvironment":
			manifest.setRequiredExecutionEnvironment(value);
			break;

		case "Bundle-SymbolicName":
			manifest.setSymbolicName(value);
			break;

		case "Bundle-Vendor":
			manifest.setVendor(value);
			break;

		case "Bundle-Version":
			manifest.setVersion(value);
			break;

		case "Export-Package":
			manifest.setExportPackage(value);
			break;

		case "HomeDevice-MetaData":
			manifest.setHomeDeviceMetaData(value);
			break;

		case "Import-Package":
			manifest.setImportPackage(value);
			break;

		case "Manifest-Version":
			manifest.setManifestVersion(value);
			break;

		default:
			throw new RuntimeException("Unknown manifest entry key: " + key);
		}
	}

}

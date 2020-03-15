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
		boolean firstLine = true;

		for (String line : split) {

			if (StringUtils.isBlank(line)) {
				continue;
			}

//			Name: OSGI-OPT/src/org/osgi/framework/BundlePermission.java
//			MD5-Digest: kjL+seyOi2V40Yvt6wAyDQ==
//			SHA-Digest: 4gtbUbf28vwuC5aO8ppOQEKFBP8=

			if (StringUtils.startsWith(line, "Name: ") || StringUtils.startsWith(line, "MD5-Digest: ")
					|| StringUtils.startsWith(line, "SHA-Digest: ") || StringUtils.startsWith(line, "SHA1-Digest: ")) {
				continue;
			}

			if (StringUtils.startsWith(line, "Manifest-") || StringUtils.startsWith(line, "Bundle-")
					|| StringUtils.startsWith(line, "Export-") || StringUtils.startsWith(line, "Import-")
					|| StringUtils.startsWith(line, "Provide-") || StringUtils.startsWith(line, "HomeDevice-")
					|| StringUtils.startsWith(line, "Archiver-") || StringUtils.startsWith(line, "Created-")
					|| StringUtils.startsWith(line, "Build-") || StringUtils.startsWith(line, "Built-")
					|| StringUtils.startsWith(line, "Specification-") || StringUtils.startsWith(line, "X-")) {

				if (stringBuilder.length() > 0) {

					manifestEntryFound(manifest, stringBuilder.toString());
				}

				stringBuilder = new StringBuilder();
				firstLine = true;
			}

			// stringBuilder.append(StringUtils.trim(line));

			line = StringUtils.removeEnd(StringUtils.removeEnd(line, "\r"), "\n");

			if (firstLine) {
				stringBuilder.append(line);
			} else {
				try {
					stringBuilder.append(line.substring(1));
				} catch (final StringIndexOutOfBoundsException e) {
					logger.info(line);
				}
			}

			firstLine = false;
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

		case "Archiver-Version":
			manifest.setArchiverVersion(value);
			break;

		case "Build-Date":
			break;

		case "Build-Id":
			break;

		case "Build-Jdk":
			manifest.setBuildJdk(value);
			break;

		case "Build-Jdk-Spec":
			break;

		case "Build-Number":
			break;

		case "Build-Revision":
			break;

		case "Build-Time":
			break;

		case "Build-Version":
			break;

		case "Built-By":
			manifest.setBuiltBy(value);
			break;

		case "Built-At":
			manifest.setBuiltAt(value);
			break;

		case "Built-Time":
		case "Built-Date":
			break;

		case "Bundle-Activator":
			manifest.setActivator(value);
			break;

		case "Bundle-ActivationPolicy":
			break;

		case "Bundle-Category":
			manifest.setCategory(value);
			break;

		case "Bundle-Classpath":
		case "Bundle-ClassPath":
			manifest.setClassPath(value);
			break;

		case "Bundle-ContactAddress":
			manifest.setContactAddress(value);
			break;

		case "Bundle-Contributors":
			break;

		case "Bundle-Copyright":
			manifest.setCopyright(value);
			break;

		case "Bundle-Developers":
			manifest.setDevelopers(value);
			break;

		case "Bundle-Description":
			manifest.setDescription(value);
			break;

		case "Bundle-DocUrl":
		case "Bundle-DocURL":
			manifest.setDocURL(value);
			break;

		case "Bundle-Icon":
			break;

		case "Bundle-Localization":
			manifest.setLocalization(value);
			break;

		case "Bundle-License":
			manifest.setLicence(value);
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

		case "Bundle-Requirements":
			break;

		case "Bundle-SCM":
			manifest.setScm(value);
			break;

		case "Bundle-Source":
			break;

		case "Bundle-SymbolicName":
			manifest.setSymbolicName(value);
			break;

		case "Bundle-Url":
			break;

		case "Bundle-Vendor":
			manifest.setVendor(value);
			break;

		case "Bundle-Version":
			manifest.setVersion(value);
			break;

		case "Created-By":
			manifest.setCreatedBy(value);
			break;

		case "Export-Package":
			manifest.setExportPackage(value);
			break;

		case "Export-Service":
			manifest.setExportService(value);
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

		case "Provide-Capability":
			manifest.setProvideCapability(value);
			break;

		case "Specification-Vendor":
			break;

		case "Specification-Title":
			break;

		case "Specification-Version":
			break;

		default:
			throw new RuntimeException("Unknown manifest entry key: " + key);
		}
	}

}

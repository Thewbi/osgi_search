package osgisearch.osgi.bundles;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import osgisearch.osgi.manifest.Manifest;
import osgisearch.osgi.manifest.parsing.ManifestParser;

public class DefaultOSGIBundleFileProcessor implements OSGIBundleFileProcessor {

	private static final Logger logger = LogManager.getLogger(DefaultOSGIBundleFileProcessor.class);

	@Autowired
	private ManifestParser manifestParser;

	/**
	 * Input is a valid jar file which potentially could be a OSGI bundle.
	 */
	@Override
	public void handleFile(final Path path) {

		logger.info(path);

		// DEBUG
		if (!StringUtils.equals(
//				"C:\\dev\\mbs-sh-sdk\\runtime\\osgi\\bundles\\com.prosyst.mbs.bluetooth.le.driver.service.provider.jar",
//				"C:\\dev\\mbs-sh-sdk\\runtime\\osgi\\bundles\\com.prosyst.mbs.cameras.hdm.api.jar",
//				"C:\\dev\\mbs-sh-sdk\\runtime\\osgi\\bundles\\com.prosyst.mbs.comm.api.jar",
				"C:\\dev\\mbs-sh-sdk\\runtime\\osgi\\bundles\\com.prosyst.mbs.devstreams.api.jar",
				path.toFile().getAbsolutePath())) {
			return;
		}

		try (ZipFile zipFile = new ZipFile(path.toFile())) {

			final Enumeration<? extends ZipEntry> entries = zipFile.entries();

			while (entries.hasMoreElements()) {

				final ZipEntry zipEntry = entries.nextElement();

				if (StringUtils.contains(zipEntry.getName(), "MANIFEST.MF")) {

					logger.trace(path + " " + zipEntry.getName());

					final InputStream zipEntryInputStream = zipFile.getInputStream(zipEntry);
					final String text = IOUtils.toString(zipEntryInputStream, StandardCharsets.UTF_8.name());

					logger.trace(path + " " + zipEntry.getName());
					logger.trace("\n" + text);

					if (StringUtils.startsWith(zipEntry.getName(), "OSGI-OPT")) {

						logger.trace("OSGI-OPT\n");

					} else {

						logger.trace("Normal MANIFEST.MF");

						final Manifest manifest = manifestParser.parse(text);
						manifest.setFile(path.toFile());

					}
				}
			}
		} catch (final ZipException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}

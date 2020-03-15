package osgisearch.osgi.bundles;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class DefaultOSGIBundleFileProcessor implements OSGIBundleFileProcessor {

	@Override
	public void handleFile(final Path path) {

//		System.out.println(path);

		try (ZipFile zipFile = new ZipFile(path.toFile())) {

			final Enumeration<? extends ZipEntry> entries = zipFile.entries();

			while (entries.hasMoreElements()) {

				final ZipEntry entry = entries.nextElement();

//			final InputStream stream = zipFile.getInputStream(entry);
			}
		} catch (final ZipException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}

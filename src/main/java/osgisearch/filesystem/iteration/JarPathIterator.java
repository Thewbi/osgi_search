package osgisearch.filesystem.iteration;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class JarPathIterator implements PathIterator {

	private static final Logger logger = LogManager.getLogger(JarPathIterator.class);

	private int brokenFiles;

	@Autowired
	private FileProcessor fileProcessor;

	@Override
	public void iterate(final String filepath) throws IOException {

		brokenFiles = 0;

		final Path path = Paths.get(filepath);

		final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.jar");

		Files.walk(path).filter(Files::isRegularFile).filter(p -> matcher.matches(p)).forEach(p -> {
			if (checkHealth(p)) {
				fileProcessor.handleFile(p);
			}
		});

		if (brokenFiles == 0) {
			logger.info("No broken files detected in \"" + filepath + "\"!");
		} else {
			logger.info(brokenFiles + " broken files detected in \"" + filepath + "\"!");
		}
	}

	private boolean checkHealth(final Path path) {

		// if a jar file is not a valid zip file, it is broken!
		// Files break if restricted network connections are used.
		// Instead of downloading a jar file, the firewall will return
		// a html page with a notice saying that the connection is blocked.
		// Maven will happily take that html page and store it as the downloaded
		// jar file.
		// This in turn will serve broken dependencies to your build, breaking
		// your build in the process.
		try {

			try (ZipFile file = new ZipFile(path.toFile())) {
				final Enumeration<? extends ZipEntry> e = file.entries();
				while (e.hasMoreElements()) {
					@SuppressWarnings("unused")
					final ZipEntry entry = e.nextElement();
				}
			}

		} catch (final Exception e) {
			System.out.println(path);
			brokenFiles++;

			return false;
		}

		return true;

//		ZipFile zipFile = new ZipFile("C:/test.zip");
//
//	    Enumeration<? extends ZipEntry> entries = zipFile.entries();
//
//	    while(entries.hasMoreElements()){
//	        ZipEntry entry = entries.nextElement();
//	        InputStream stream = zipFile.getInputStream(entry);
//	    }

	}

}

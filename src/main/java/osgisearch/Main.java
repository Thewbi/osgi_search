package osgisearch;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import osgisearch.filesystem.iteration.PathIterator;
import osgisearch.indexing.OSGIBundleIndexer;
import osgisearch.osgi.manifest.parsing.OSGIPackage;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(final String[] args) throws IOException {

		logger.info("Starting application ...");

		final String path = "C:/dev/mbs-sh-sdk/runtime/osgi/bundles";

		logger.info("Indexing path: \"{}\"", path);

		final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		final PathIterator pathIterator = applicationContext.getBean(PathIterator.class);
		final OSGIBundleIndexer osgiBundleIndexer = applicationContext.getBean(OSGIBundleIndexer.class);

		pathIterator.iterate(path);

		final Map<String, List<OSGIPackage>> packages = osgiBundleIndexer.getPackages();

		logger.info("End application ...");

	}

}

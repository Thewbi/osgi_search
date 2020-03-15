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
import osgisearch.osgi.manifest.Manifest;
import osgisearch.osgi.manifest.parsing.OSGIPackage;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(final String[] args) throws IOException {

		logger.info("Starting application ...");

//		final String path = "C:/dev/mbs-sh-sdk/runtime/osgi/bundles";
		final String path = "C:/PR_TBE/GitCheckouts/cu450-cockpit/External/develop-image-security-updates/target";
//		final String path = "C:/Users/U5353/.m2/repository";

		logger.info("Indexing path: \"{}\"", path);

		final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		final PathIterator pathIterator = applicationContext.getBean(PathIterator.class);
		final OSGIBundleIndexer osgiBundleIndexer = applicationContext.getBean(OSGIBundleIndexer.class);

		pathIterator.iterate(path);

		final Map<String, List<OSGIPackage>> packages = osgiBundleIndexer.getPackages();

		String packageName = "";
//		packageName = "javax.xml";
//		packageName = "org.eclipse.jetty.websocket.servlet";
//		packageName = "org.eclipse.jetty.websocket.servlet.WebSocketServletFactory";
//		packageName = "org.apache.aries.util.manifest";
//		packageName = "com.prosyst.mprm.util.commands";
//		packageName = "com.google.gson";
//		packageName = "org.osgi.resource";
//		packageName = "org.objectweb.asm";
//		packageName = "org.apache.aries.spifly";
//		packageName = "org.eclipse.jetty.websocket.servlet";
//		packageName = "org.apache.aries.spifly.dynamic.bundle";
//		packageName = "com.prosyst.mprm.gateway.appagent";
		packageName = "com.prosyst.mbs.services.hdm.simulator.v2.dc";

		final List<OSGIPackage> list = packages.get(packageName);

//		logger.info(list);

		if (list == null) {

			logger.info("No jars found for: \"{}\"", packageName);

		} else {
			for (final OSGIPackage osgiPackage : list) {

				final Manifest manifest = osgiPackage.getManifest();

				logger.info("Found Jar for: \"{}\"", packageName);
				logger.info(manifest.getFile().getAbsolutePath());
				logger.info("Version: ", manifest.getVersion());
			}

		}

		logger.info("End application ...");

	}

}

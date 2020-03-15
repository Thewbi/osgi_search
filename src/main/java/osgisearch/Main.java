package osgisearch;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import osgisearch.filesystem.iteration.PathIterator;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(final String[] args) throws IOException {

		final String path = "C:/dev/mbs-sh-sdk/runtime/osgi/bundles";

		logger.info(path);

		final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		final PathIterator pathIterator = applicationContext.getBean(PathIterator.class);

		pathIterator.iterate(path);

	}

}

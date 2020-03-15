package osgisearch.osgi.manifest;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import osgisearch.osgi_manifestLexer;
import osgisearch.osgi_manifestParser;
import osgisearch.osgi_manifestParser.FqdnContext;
import osgisearch.osgi_manifestParser.OsgipackageContext;
import osgisearch.osgi_manifestParser.Osgipackage_descriptionContext;
import osgisearch.osgi_manifestParser.Osgipackage_elementContext;
import osgisearch.osgi_manifestParser.VersionContext;

public class AntlrTest {

	@Test
	public void parseTest() {

		// final String data = "2.3.1";
		// final String data = "\"a.b.c\"";
		// final String data = "com.google.gson";
//		final String data = "version=2.3.1";
//		final String data = "version=\"2.3.1\"";
//		final String data = "com.google.gson;version=2.3.1";
//		final String data = "com.google.gson;version=\"2.3.1\"";
//		final String data = "com.google.gson;version=1.1.1, com.google.gson.annotations;version=2.2.2";
//		final String data = "com.google.gson;version=1.1.1, com.google.gson.annotations;version=\"2.2.2\"";
//		final String data = "com.google.gson;version=1.1.1, com.google.gson.annotations;version=2.2.2, com.google.gson.reflect;version=3.3.3, com.google .gson.stream;version=4.4.4, com.google.gson.internal;version=5.5.5, com.google.gson.internal.bind;version=6.6.6";
//		final String data = "com.prosyst.mbs.comm.serial;uses:=\"com.prosyst.mbs.comm,gnu.io\";version=\"1.2.0\",com.prosyst.mbs.comm;version=\"1.1.2\",com.prosyst.mbs.comm.socket;version=\"1.2.1\",com.prosyst.mbs.comm.spi;version=\"1.0.1\",com.prosyst.mbs.comm.stream;version=\"1.1.0\",com.prosyst.mbs.services.frameanalyzer;version=\"1.0.0\"";

//		final String data = "javassist.scopedpool;uses:=\"javassist\";version=\"3.18.1.GA\"";
		final String data = "javassist.scopedpool;uses:=javassist;version=\"3.18.1.GA\"";

		final osgi_manifestLexer lexer = new osgi_manifestLexer(CharStreams.fromString(data));

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final osgi_manifestParser parser = new osgi_manifestParser(tokens);

//		System.out.println("");
//		final FqdnContext fqdn2 = parser.fqdn();
//		if (fqdn2 != null) {
//			System.out.println("fqdn2: " + fqdn2.getText());
//		}
//
//		System.out.println("");
//		final VersionContext version2 = parser.version();
//		if (version2 != null) {
//			System.out.println("version2: " + version2.getText());
//		}

//		final Osgipackage_elementContext osgipackage_element = parser.osgipackage_element();
//		if (osgipackage_element != null) {
//			System.out.println("osgipackage_element: " + osgipackage_element.getText());
//
//			final FqdnContext fqdn3 = osgipackage_element.fqdn();
//			if (fqdn3 != null) {
//				System.out.println("fqdn3: " + fqdn3.getText());
//			}
//
//			final VersionContext version = osgipackage_element.version();
//			if (version == null) {
//
//				String ver = osgipackage_element.getChild(2).getText();
//				ver = StringUtils.substringBetween(ver, "\"");
//				System.out.println("version: " + ver);
//			} else {
//				System.out.println("version: " + version.getText());
//			}
//		}

		final Osgipackage_descriptionContext osgiPackageDescription = parser.osgipackage_description();

		System.out.println("");
		System.out.println("data: " + data);

		for (final OsgipackageContext osgipackageContext : osgiPackageDescription.osgipackage()) {

			for (final Osgipackage_elementContext osgiPackageElementContext : osgipackageContext
					.osgipackage_element()) {

				System.out.println("");

				final FqdnContext fqdn = osgiPackageElementContext.fqdn();
				if (fqdn != null) {
					System.out.println("fqdn: " + fqdn.getText());
				}

				final VersionContext version = osgiPackageElementContext.version();
				if (version == null) {

					if (osgiPackageElementContext.getChildCount() > 1) {

						String ver = osgiPackageElementContext.getChild(2).getText();
						ver = StringUtils.substringBetween(ver, "\"");
						System.out.println("version: " + ver);
					}
				} else {
					System.out.println("version: " + version.getText());
				}

			}
		}
	}

}

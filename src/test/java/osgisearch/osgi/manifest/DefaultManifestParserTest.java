package osgisearch.osgi.manifest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import osgisearch.indexing.IndexExportedPackagesStep;
import osgisearch.osgi.manifest.parsing.DefaultManifestParser;

@RunWith(MockitoJUnitRunner.class)
public class DefaultManifestParserTest {

	@Test
	public void testParse() throws IOException {

		final DefaultManifestParser defaultManifestParser = new DefaultManifestParser();
		final Manifest manifest = defaultManifestParser.parse(FileUtils.readFileToString(
				new File("src/test/resources/DefaultManifestParserTest/MANIFEST_1.MF"), Charset.forName("UTF-8")));

		Assert.assertEquals("1.0", manifest.getManifestVersion());
		Assert.assertEquals("com.prosyst.mbs.bluetooth.le", manifest.getCategory());
		Assert.assertEquals(
				"Provides general Bluetooth LE interfaces, general GATT interfaces, officially adopted GATT Service, Characteristic and Descriptor interfaces and the service provider interface.",
				manifest.getDescription());
		Assert.assertEquals("http://dz.prosyst.com/pdoc/", manifest.getDocURL());
		Assert.assertEquals("2", manifest.getBundleManifestVersion());
		Assert.assertEquals("Bluetooth LE :: Driver :: API", manifest.getName());
		Assert.assertEquals("OSGi/Minimum-1.2", manifest.getRequiredExecutionEnvironment());
		Assert.assertEquals("com.prosyst.mbs.bluetooth.le.driver.api; singleton:=true", manifest.getSymbolicName());
		Assert.assertEquals("ProSyst Software GmbH", manifest.getVendor());
		Assert.assertEquals("1.1.0", manifest.getVersion());
		Assert.assertEquals(
				"com.prosyst.mbs.services.btle;version=\"1.1.0\",com.prosyst.mbs.services.btle.gatt.characteristic;version=\"1.1.0\",com.prosyst.mbs.services.btle.gatt.descriptor;version=\"1.1.0\",com.prosyst.mbs.services.btle.gatt.service;version=\"1.1.0\",com.prosyst.mbs.services.btle.gatt.type;version=\"1.1.0\",com.prosyst.mbs.services.btle.utils;version=\"1.1.0\"",
				manifest.getExportPackage());

		Assert.assertEquals("https://devzone.prosyst.bg/jira/browse/MBSBLUELE",
				manifest.getExtensions().get("X-ProSyst-Jira"));
	}

	@Test
	public void testParse2() throws IOException {

		final IndexExportedPackagesStep indexExportedPackagesStep = new IndexExportedPackagesStep();

		final DefaultManifestParser defaultManifestParser = new DefaultManifestParser();
		defaultManifestParser.setIndexExportedPackagesStep(indexExportedPackagesStep);
		final Manifest manifest = defaultManifestParser.parse(FileUtils.readFileToString(
				new File("src/test/resources/DefaultManifestParserTest/MANIFEST_2.MF"), Charset.forName("UTF-8")));

		Assert.assertEquals("1.0", manifest.getManifestVersion());
		Assert.assertEquals(".", manifest.getClassPath());

//		Bundle-Name: Gson
		Assert.assertEquals("Gson", manifest.getName());

//		Created-By: Apache Maven 3.2.1
		Assert.assertEquals("Apache Maven 3.2.1", manifest.getCreatedBy());

//		Bundle-RequiredExecutionEnvironment: J2SE-1.5, JavaSE-1.6, JavaSE-1.7,
//		  JavaSE-1.8
		Assert.assertEquals("J2SE-1.5, JavaSE-1.6, JavaSE-1.7, JavaSE-1.8", manifest.getRequiredExecutionEnvironment());

//		Bundle-Vendor: Google Gson Project
		Assert.assertEquals("Google Gson Project", manifest.getVendor());

//		Bundle-ContactAddress: http://code.google.com/p/google-gson/
		Assert.assertEquals("http://code.google.com/p/google-gson/", manifest.getContactAddress());

//		Build-Jdk: 1.6.0_65
		Assert.assertEquals("1.6.0_65", manifest.getBuildJdk());

//		Bundle-Version: 2.3.1
		Assert.assertEquals("2.3.1", manifest.getVersion());

//		Bundle-ManifestVersion: 2
		Assert.assertEquals("2", manifest.getBundleManifestVersion());

//		Manifest-Version: 1.0
		Assert.assertEquals("1.0", manifest.getManifestVersion());

//		Bundle-Description: Google Gson library
		Assert.assertEquals("Google Gson library", manifest.getDescription());

//		Bundle-SymbolicName: com.google.gson
		Assert.assertEquals("com.google.gson", manifest.getSymbolicName());

//		Archiver-Version: 
		Assert.assertEquals("Plexus Archiver", manifest.getArchiverVersion());

	}

}

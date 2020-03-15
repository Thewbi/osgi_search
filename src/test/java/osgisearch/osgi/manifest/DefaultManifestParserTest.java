package osgisearch.osgi.manifest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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

}

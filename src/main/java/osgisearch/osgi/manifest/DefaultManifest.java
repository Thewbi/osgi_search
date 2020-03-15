package osgisearch.osgi.manifest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import osgisearch.osgi.manifest.parsing.OSGIPackage;

public class DefaultManifest implements Manifest {

	private String name;

	private String symbolicName;

	private String version;

	private String activator;

	private String classPath;

	private String category;

	private String description;

	private String docURL;

	private String bundleManifestVersion;

	private String manifestVersion;

	private String nativeCode;

	private String requiredExecutionEnvironment;

	private String vendor;

	private String exportPackage;

	private String importPackage;

	private String homeDeviceMetaData;

	private final Map<String, String> extensions = new HashMap<>();

	private final List<OSGIPackage> exportedPackages = new ArrayList<>();

	private File file;

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public void setCategory(final String category) {
		this.category = category;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getSymbolicName() {
		return symbolicName;
	}

	@Override
	public void setSymbolicName(final String symbolicName) {
		this.symbolicName = symbolicName;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(final String version) {
		this.version = version;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public String getDocURL() {
		return docURL;
	}

	@Override
	public void setDocURL(final String docURL) {
		this.docURL = docURL;
	}

	@Override
	public String getManifestVersion() {
		return manifestVersion;
	}

	@Override
	public void setManifestVersion(final String manifestVersion) {
		this.manifestVersion = manifestVersion;
	}

	@Override
	public String getRequiredExecutionEnvironment() {
		return requiredExecutionEnvironment;
	}

	@Override
	public void setRequiredExecutionEnvironment(final String requiredExecutionEnvironment) {
		this.requiredExecutionEnvironment = requiredExecutionEnvironment;
	}

	@Override
	public String getVendor() {
		return vendor;
	}

	@Override
	public void setVendor(final String vendor) {
		this.vendor = vendor;
	}

	@Override
	public String getExportPackage() {
		return exportPackage;
	}

	@Override
	public void setExportPackage(final String exportPackage) {
		this.exportPackage = exportPackage;
	}

	@Override
	public Map<String, String> getExtensions() {
		return extensions;
	}

	@Override
	public String getBundleManifestVersion() {
		return bundleManifestVersion;
	}

	@Override
	public void setBundleManifestVersion(final String bundleManifestVersion) {
		this.bundleManifestVersion = bundleManifestVersion;
	}

	@Override
	public List<OSGIPackage> getExportedPackages() {
		return exportedPackages;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void setFile(final File file) {
		this.file = file;
	}

	@Override
	public String getActivator() {
		return activator;
	}

	@Override
	public void setActivator(final String activator) {
		this.activator = activator;
	}

	@Override
	public String getNativeCode() {
		return nativeCode;
	}

	@Override
	public void setNativeCode(final String nativeCode) {
		this.nativeCode = nativeCode;
	}

	@Override
	public String getImportPackage() {
		return importPackage;
	}

	@Override
	public void setImportPackage(final String importPackage) {
		this.importPackage = importPackage;
	}

	@Override
	public String getClassPath() {
		return classPath;
	}

	@Override
	public void setClassPath(final String classPath) {
		this.classPath = classPath;
	}

	@Override
	public String getHomeDeviceMetaData() {
		return homeDeviceMetaData;
	}

	@Override
	public void setHomeDeviceMetaData(final String homeDeviceMetaData) {
		this.homeDeviceMetaData = homeDeviceMetaData;
	}
}

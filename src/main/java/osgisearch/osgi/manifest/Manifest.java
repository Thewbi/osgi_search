package osgisearch.osgi.manifest;

import java.io.File;
import java.util.List;
import java.util.Map;

import osgisearch.osgi.manifest.parsing.OSGIPackage;

public interface Manifest {

	String getCategory();

	void setCategory(String category);

	String getName();

	void setName(String name);

	String getSymbolicName();

	void setSymbolicName(String symbolicName);

	String getVersion();

	void setVersion(String version);

	String getDescription();

	void setDescription(String description);

	String getDocURL();

	void setDocURL(String docURL);

	String getManifestVersion();

	void setManifestVersion(String manifestVersion);

	String getRequiredExecutionEnvironment();

	void setRequiredExecutionEnvironment(String requiredExecutionEnvironment);

	String getVendor();

	void setVendor(String vendor);

	String getExportPackage();

	void setExportPackage(String exportPackage);

	Map<String, String> getExtensions();

	String getBundleManifestVersion();

	void setBundleManifestVersion(String bundleManifestVersion);

	List<OSGIPackage> getExportedPackages();

	File getFile();

	void setFile(File file);

	void setActivator(String value);

	String getActivator();

	String getNativeCode();

	void setNativeCode(String nativeCode);

	void setImportPackage(String importPackage);

	String getImportPackage();

	String getClassPath();

	void setClassPath(String classpath);

	String getHomeDeviceMetaData();

	void setHomeDeviceMetaData(String homeDeviceMetaData);

	String getLocalization();

	void setLocalization(String localization);

	String getExportService();

	void setExportService(String exportService);

	String getContactAddress();

	void setContactAddress(String contactAddress);

	String getLicence();

	void setLicence(String licence);

	String getCopyright();

	void setCopyright(String copyright);

	String getDevelopers();

	void setDevelopers(String developers);

	String getScm();

	void setScm(String scm);

	String getProvideCapability();

	void setProvideCapability(String provideCapability);

	String getCreatedBy();

	void setCreatedBy(String createdBy);

	String getBuildJdk();

	void setBuildJdk(String buildJdk);

	String getArchiverVersion();

	void setArchiverVersion(String archiverVersion);

	String getBuiltBy();

	void setBuiltBy(String builtBy);

	String getBuiltAt();

	void setBuiltAt(String builtAt);

}
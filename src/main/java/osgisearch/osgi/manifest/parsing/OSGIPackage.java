package osgisearch.osgi.manifest.parsing;

import com.vdurmont.semver4j.Semver;

import osgisearch.osgi.manifest.Manifest;

public class OSGIPackage {

	private String name;

	private Semver version;

	private Manifest manifest;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Semver getVersion() {
		return version;
	}

	public void setVersion(final Semver version) {
		this.version = version;
	}

	public Manifest getManifest() {
		return manifest;
	}

	public void setManifest(final Manifest manifest) {
		this.manifest = manifest;
	}

}

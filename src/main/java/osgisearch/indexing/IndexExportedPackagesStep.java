package osgisearch.indexing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import osgisearch.osgi.manifest.Manifest;
import osgisearch.osgi.manifest.parsing.OSGIPackage;
import osgisearch.osgi.manifest.parsing.steps.ManifestParserStep;

public class IndexExportedPackagesStep implements ManifestParserStep {

	private static final Logger logger = LogManager.getLogger(IndexExportedPackagesStep.class);

	@Autowired
	private OSGIBundleIndexer osgiBundleIndexer;

	@Override
	public void process(final Manifest manifest, final Object... vargs) {

		final Map<String, List<OSGIPackage>> packages = osgiBundleIndexer.getPackages();

		final List<OSGIPackage> exportedPackages = manifest.getExportedPackages();

		if (CollectionUtils.isEmpty(exportedPackages)) {
			return;
		}

		for (final OSGIPackage osgiPackage : exportedPackages) {

			if (!packages.containsKey(osgiPackage.getName())) {

				packages.put(osgiPackage.getName(), new ArrayList<>());
			}

			packages.get(osgiPackage.getName()).add(osgiPackage);
		}

	}

}

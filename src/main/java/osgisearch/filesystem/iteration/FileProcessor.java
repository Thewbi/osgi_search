package osgisearch.filesystem.iteration;

import java.nio.file.Path;

public interface FileProcessor {

	void handleFile(Path path);

}

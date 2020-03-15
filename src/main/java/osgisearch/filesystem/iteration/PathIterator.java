package osgisearch.filesystem.iteration;

import java.io.IOException;

public interface PathIterator {

	void iterate(String path) throws IOException;

}

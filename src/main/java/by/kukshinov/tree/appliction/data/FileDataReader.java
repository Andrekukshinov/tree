package by.kukshinov.tree.appliction.data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileDataReader {

    private static final char NEW_LINE = '\n';

    public String readData(String filePath) throws DataException {
	   Path path = Paths.get(filePath);
	   StringBuilder resultBuilder = new StringBuilder();
	   try {
		  List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		  fileLines.forEach(line -> {
			 StringBuilder appendLine = resultBuilder.append(line);
			 appendLine.append(NEW_LINE);
		  });
		  return resultBuilder.toString();
	   } catch (IOException e) {
		  throw new DataException(e.getMessage(), e);
	   }
    }
}


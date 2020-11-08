package by.kukshinov.tree.appliction.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileDataReader {
    public String readData(String filePath) throws DataException {
	   Path path = Paths.get("file");
	   StringBuilder resultBuilder = new StringBuilder();
	   try {
		  List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		  fileLines.forEach(line -> resultBuilder.append(line).append('\n'));
		  return resultBuilder.toString();
	   } catch (IOException e) {
		  throw new DataException(e.getMessage(), e);
	   }
    }
}
// TODO: 07.11.2020 create test

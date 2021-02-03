package by.kukshinov.tree.appliction.data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileDataReader implements DataReader {
    private static final String NEW_LINE = "\n";

    public String read(String filePath) throws DataException {
        Path path = Paths.get(filePath);
        String resultBuilder;
        try {
            List<String> fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            resultBuilder = String.join(NEW_LINE, fileLines);
            return resultBuilder;
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}


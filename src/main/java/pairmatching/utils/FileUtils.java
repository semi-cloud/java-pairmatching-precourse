package pairmatching.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
    private static final String FILE_PATH = "\"/Users/kangsemi/Desktop/git/java-pairmatching-precourse/src/main/resources/";

    public static List<String> readFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(FILE_PATH + fileName));
    }
}

package io.bowdy.bookreads.Util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Log4j2
public class ImageUtil {
    private static final String FOLDER_PATH = Paths.get("").toAbsolutePath().toString();

    public static byte[] getImageFromSystem(String filename) throws IOException {
        String filepath = FOLDER_PATH + "/storage/" + filename;

        return Files.readAllBytes(new File(filepath).toPath());
    }
}

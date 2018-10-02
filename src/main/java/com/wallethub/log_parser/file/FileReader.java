package com.wallethub.log_parser.file;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class FileReader {

    private static final String SYSTEM_PROPERTY_USER_DIR = "user.dir";
    private static final String CLASSPATH = "classpath:";
    private static final String PWD = "./";
    private static final String SLASH = "/";

    public InputStream read(String path) {
        Objects.requireNonNull(path, "Path must be specified");
        try {
            if (path.startsWith(CLASSPATH))
                return new ClassPathResource(classpath(path)).getInputStream();
            if (path.startsWith(PWD))
                return new FileInputStream(pwd(path));
            return new FileInputStream(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String pwd(String path) {
        return System.getProperty(SYSTEM_PROPERTY_USER_DIR) + SLASH + path.substring(2);
    }

    private String classpath(String path) {
        return path.substring(CLASSPATH.length());
    }
}

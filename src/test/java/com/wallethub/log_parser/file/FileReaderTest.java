package com.wallethub.log_parser.file;

import com.wallethub.log_parser.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileReaderTest extends IntegrationTest {

    @Value("${log-parser.file-path}")
    String path;

    @Autowired
    private FileReader reader;

    @Test
    public void read() {
        // given

        // when
        InputStream in = reader.read(path);

        // then
        assertThat(in).isNotNull();
    }
}
package com.wallethub.log_parser.log;

import com.wallethub.log_parser.schedule.LogServiceScheduler;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

@Service
public class LogParser {

    public static final String DELIMITER = "\\|";


    public void parse(InputStream in, Consumer<String[]> onValues) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            for (String line; (line = br.readLine()) != null; ) {
                onValues.accept(line.split(DELIMITER));
            }
            sleep();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Waiting for {@link LogServiceScheduler#forceBatch()} to drain batcher
     *
     * @throws InterruptedException
     */
    private void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }

}

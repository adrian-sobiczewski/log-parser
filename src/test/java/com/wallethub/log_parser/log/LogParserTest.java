package com.wallethub.log_parser.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogParserTest {

    private static final String NEW_LINE = "\n";

    @Mock
    private LogFactory factory;

    @InjectMocks
    private LogParser parser;

    @Mock
    private SampleConsumer consumer;

    @Captor
    private ArgumentCaptor<String[]> captor;

    @Test
    public void parse_OneValuePerLineTwoLines_consumerCalledTwiceWithCorrectArguments() {
        // given
        String twoLineFile = new StringBuilder()
                .append("A1")
                .append(NEW_LINE)
                .append("B1")
                .toString();

        // when
        parser.parse(in(twoLineFile), values -> consumer.consume(values));

        //then
        verify(consumer, times(2)).consume(captor.capture());

        List<String[]> captured = captor.getAllValues();
        assertThat(captured.get(0)[0]).isEqualTo("A1");
        assertThat(captured.get(1)[0]).isEqualTo("B1");
    }

    @Test
    public void parse_TwoValuesPerLineTwoLines_consumerCalledTwiceWithCorrectArguments() {
        // given
        String twoLineFile = new StringBuilder()
                .append("A1|A2")
                .append(NEW_LINE)
                .append("B1|B2")
                .toString();

        // when
        parser.parse(in(twoLineFile), values -> consumer.consume(values));

        //then
        verify(consumer, times(2)).consume(captor.capture());

        List<String[]> captured = captor.getAllValues();
        assertThat(captured.get(0)[0]).isEqualTo("A1");
        assertThat(captured.get(0)[1]).isEqualTo("A2");
        assertThat(captured.get(1)[0]).isEqualTo("B1");
        assertThat(captured.get(1)[1]).isEqualTo("B2");
    }

    private InputStream in(String value) {
        return new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
    }

    private class SampleConsumer {
        void consume(String[] values) {
            // do nothing
        }
    }
}
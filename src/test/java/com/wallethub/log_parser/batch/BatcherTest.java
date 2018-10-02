package com.wallethub.log_parser.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class BatcherTest {

    @Mock
    private SampleConsumer consumer;

    @Captor
    private ArgumentCaptor<List<String>> captor;

    @Test
    public void add_Threshold2TwoItemsAdded_consumerCalledWithCorrectArguments() {
        // given
        Batcher<String> batcher = new Batcher<>(2, consumer::consume);

        // when
        batcher.add("1");
        batcher.add("2");

        //then
        verify(consumer, times(1)).consume(captor.capture());

        List<List<String>> allValues = captor.getAllValues();
        assertThat(allValues.get(0)).containsExactly("1", "2");
    }

    @Test
    public void add_Threshold2FourItemsAdded_consumerCalledWithCorrectArgumentsTwice() {
        // given
        Batcher<String> batcher = new Batcher<>(2, consumer::consume);

        // when
        batcher.add("1");
        batcher.add("2");
        batcher.add("3");
        batcher.add("4");

        //then
        verify(consumer, times(2)).consume(captor.capture());

        List<List<String>> allValues = captor.getAllValues();
        assertThat(allValues.get(0)).containsExactly("1", "2");
        assertThat(allValues.get(1)).containsExactly("3", "4");
    }

    @Test
    public void batch_TwoItemsAddedThreshold3_consumerCalledDisregardingThresholdNotReached() {
        // given
        Batcher<String> batcher = new Batcher<>(3, consumer::consume);
        batcher.add("1");
        batcher.add("2");

        // when
        batcher.batch();

        //then
        verify(consumer, times(1)).consume(captor.capture());

        List<List<String>> allValues = captor.getAllValues();
        assertThat(allValues.get(0)).containsExactly("1", "2");
    }

    private class SampleConsumer {
        void consume(List<String> values) {
            // do nothing
        }
    }
}
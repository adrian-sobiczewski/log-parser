package com.wallethub.log_parser.batch;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkArgument;

public class Batcher<T> {

    private final int size;
    private final Consumer<List<T>> consumer;
    private final List<T> list;

    public Batcher(int size, Consumer<List<T>> consumer) {
        checkArgument(size != 0, "Batch size must be greater than zero");
        this.size = size;
        this.consumer = consumer;
        this.list = new ArrayList<>(size);
    }

    public synchronized void add(T value) {
        list.add(value);
        if (list.size() >= size) batch();
    }

    public synchronized void batch() {
        consumer.accept(new ArrayList<>(list));
        list.clear();
    }

}

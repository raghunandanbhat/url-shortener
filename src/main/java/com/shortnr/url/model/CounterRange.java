package com.shortnr.url.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CounterRange {
    private long start;
    private long end;

    @Autowired
    public CounterRange(@Value("0")long start, @Value("0")long end){
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}

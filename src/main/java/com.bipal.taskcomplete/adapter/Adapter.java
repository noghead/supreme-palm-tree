package com.bipal.taskcomplete.adapter;

public interface Adapter<X,Y> {
    public Y convert(X object);
}

package it.mondogrua.count;

public interface Count {

    static final String RESET_METHOD = "reset";
    static final String INCREMENT_METHOD = "increment";
    static final String DECREMENT_METHOD = "decrement";
    static final String GET_VALUE_METHOD = "getCountValue";

    void reset();

    void increment();

    void decrement();

    Object getCountValue();
}

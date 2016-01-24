package it.mondogrua.count;

import it.mondogrua.utils.ValueModel;

public interface Count extends ValueModel {

	Object getResetValue();

	void setResetValue(Object aResetValue);

	void reset();

	void increment();

	void decrement();

}
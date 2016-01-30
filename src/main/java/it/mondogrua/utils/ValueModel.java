package it.mondogrua.utils;

public interface ValueModel {

    public static final String GET_VALUE_METHOD = "getModelValue";

	public Object getModelValue();
	public void setModelValue(Object aValue);
}
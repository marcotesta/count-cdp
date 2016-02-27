package it.mondogrua.utils;

public class ValueModelAdaptor extends PluggableAdaptor implements ValueModel {

    public ValueModelAdaptor(Object aModel, String anAction,
            Object[] someParameters) {
        super(aModel, anAction, someParameters);
    }

    @Override
    public String getValue() {
        return execute().toString();
    }

}

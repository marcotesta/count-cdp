package it.mondogrua.utils;

public class ValueModelAdaptor extends PluggableAdaptor implements ValueModel {

    public ValueModelAdaptor(Object aModel, String anAction) {
        super(aModel, anAction, new Object[] {});
    }

    @Override
    public String getValue() {
        return execute().toString();
    }

}

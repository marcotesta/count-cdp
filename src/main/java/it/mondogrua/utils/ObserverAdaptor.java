package it.mondogrua.utils;

public class ObserverAdaptor extends PluggableAdaptor implements Observer {

    public ObserverAdaptor(Object aModel, String anAction) {
        super(aModel, anAction);
    }

    @Override
    public void update() {
        execute().toString();
    }
}

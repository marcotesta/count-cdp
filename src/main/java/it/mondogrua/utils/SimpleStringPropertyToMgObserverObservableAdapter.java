package it.mondogrua.utils;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

public class SimpleStringPropertyToMgObserverObservableAdapter extends
        SimpleStringProperty implements Observer {

    private ValueModel valueModel;

    public SimpleStringPropertyToMgObserverObservableAdapter(
            ValueModel aValueModel) {
        super();
        this.valueModel = aValueModel;
        setValue(valueModel.getValue());
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                setValue(valueModel.getValue());
            }
        });
    }
}

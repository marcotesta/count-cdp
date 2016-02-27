package it.mondogrua.javafx_count_view;

import it.mondogrua.utils.Observer;
import it.mondogrua.utils.ValueModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

public class PropertyCountObserverObservableAdapter extends SimpleStringProperty
        implements Observer {

    private ValueModel valueModel;

    public PropertyCountObserverObservableAdapter(ValueModel aValueModel) {
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

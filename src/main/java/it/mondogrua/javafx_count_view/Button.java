package it.mondogrua.javafx_count_view;

import it.mondogrua.utils.PluggableAdaptor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class Button extends HBox {

    private javafx.scene.control.Button button;

    public Button(String label, PluggableAdaptor aCommand) {
        super(10);
        setAlignment(Pos.BOTTOM_RIGHT);

        button = new javafx.scene.control.Button(label);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                aCommand.execute();
            }
        });

        getChildren().add(this.button);

    }
}

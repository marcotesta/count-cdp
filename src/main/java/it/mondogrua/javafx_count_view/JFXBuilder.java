package it.mondogrua.javafx_count_view;

import it.mondogrua.count.Count;
import it.mondogrua.countapp.SceneBuilder;
import it.mondogrua.javafx.JFXDisplayBox;
import it.mondogrua.utils.PluggableAdaptor;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class JFXBuilder implements SceneBuilder {

    private GridPane pane;
    private SimpleStringProperty observable;
    private Count count;
    private Scene scene;

    public JFXBuilder(SimpleStringProperty aObservable, Count aCount) {
        this.observable = aObservable;
        this.count = aCount;
    }

    @Override
    public void addPane() {
        makePane();
    }

    @Override
    public void addDisplayBoxOn(int x, int y) {
        add(makeDisplayBoxOn(observable), x, y);
    }

    @Override
    public void addResetButtonOn(int x, int y) {
        add(makeButtonOn(count, "Reset", Count.RESET_METHOD), x, y);
    }

    @Override
    public void addDecrementButtonOn(int x, int y) {
        add(makeButtonOn(count, "Decrement", Count.DECREMENT_METHOD), x, y);
    }

    @Override
    public void addIncrementButtonOn(int x, int y) {
        add(makeButtonOn(count, "Increment", Count.INCREMENT_METHOD), x, y);
    }

    @Override
    public void addScene(int x, int y) {
        scene = new Scene(pane, x, y);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    protected void makePane() {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
    }

    private Button makeButtonOn(Object aModel, String label, String anAction) {

        PluggableAdaptor aCommand = new PluggableAdaptor(aModel, anAction,
                new Object[] {});
        Button button = new Button(label);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                aCommand.execute();
            }
        });

        return button;
    }

    protected Label makeDisplayBoxOn(SimpleStringProperty observable) {
        JFXDisplayBox displayBox = new JFXDisplayBox();
        displayBox.bind(observable);
        return displayBox;
    }

    private void add(Node node, int x, int y) {
        pane.add(node, x, y);
    }
}

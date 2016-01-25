package it.mondogrua.countapp;

import it.mondogrua.count.AbstractCount;
import it.mondogrua.count.DateCount;
import it.mondogrua.count.ObservableCount;
import it.mondogrua.javafx.PropertyObservableAdapter;
import it.mondogrua.javafx_count_view.AltJFXBuilder;
import it.mondogrua.javafx_count_view.JFXBuilder;
import it.mondogrua.swing.JavaUtilsObservableAdapter;
import it.mondogrua.swing_count_view.SwingBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CountApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        AbstractCount count = new DateCount();
        ObservableCount observableCount = new ObservableCount(count);

        JavaUtilsObservableAdapter javaUtilsObservableAdapter = new JavaUtilsObservableAdapter(observableCount);
        observableCount.add(javaUtilsObservableAdapter);

        PropertyObservableAdapter propertyObservableAdapter = new PropertyObservableAdapter(observableCount);
        observableCount.add(propertyObservableAdapter);

        addScene(primaryStage, "JavaFX DateCount Example", new JFXBuilder(observableCount, propertyObservableAdapter));
        addScene(new Stage(), "Alternative JavaFX DateCount Example", new AltJFXBuilder(observableCount, propertyObservableAdapter));
        addScene(new Stage(), "SWING DateCount Example", new SwingBuilder(observableCount, javaUtilsObservableAdapter));
    }

    private void addScene(Stage stage, String lable, Builder builder) {
        Scene scene = buildScene(builder);
        stage.setScene(scene);
        stage.setTitle(lable);
        stage.show();
    }

    private Scene buildScene(Builder builder) {
        builder.addPane();
        builder.addDisplayBoxOn(1, 1);
        builder.addIncrementButtonOn(0, 2);
        builder.addDecrementButtonOn(1, 2);
        builder.addResetButtonOn(2, 2);
        return builder.getScene(350, 100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

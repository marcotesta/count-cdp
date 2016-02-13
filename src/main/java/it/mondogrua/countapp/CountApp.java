package it.mondogrua.countapp;

import java.io.InputStream;

import it.mondogrua.console.ConsoleBuilder;
import it.mondogrua.count.DateCount;
import it.mondogrua.count.ObservableCount;
import it.mondogrua.javafx_count_view.AltJFXBuilder;
import it.mondogrua.javafx_count_view.JFXBuilder;
import it.mondogrua.javafx_count_view.PropertyCountObserverObservableAdapter;
import it.mondogrua.swing_count_view.JavaUtilsCountObserverObservableAdapter;
import it.mondogrua.swing_count_view.SwingBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CountApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        configureCountApp(primaryStage);
    }

    private void configureCountApp(Stage primaryStage) {
        ObservableCount count = new ObservableCount(new DateCount());

        JavaUtilsCountObserverObservableAdapter observableCountAdapter =
                new JavaUtilsCountObserverObservableAdapter();
        count.addCountObserver(observableCountAdapter);

        PropertyCountObserverObservableAdapter propertyCountAdapter =
                new PropertyCountObserverObservableAdapter();
        count.addCountObserver(propertyCountAdapter);

        ConsoleBuilder consoleBuilder = new ConsoleBuilder(count);
        InputStream[] firstPair = consoleBuilder.split(System.in);
        InputStream[] secondPair = consoleBuilder.split(firstPair[0]);

        consoleBuilder.addIncrementStreamListener("+", firstPair[1]);
        consoleBuilder.addDecrementStreamListener("-", secondPair[0]);
        consoleBuilder.addResetStreamListener("r", secondPair[1]);
        consoleBuilder.addDisplayStream(System.out);

        setupStage(primaryStage, "JavaFX DateCount Example", new JFXBuilder(
                propertyCountAdapter), 100, 500);
        setupStage(new Stage(), "Alternative JavaFX DateCount Example",
                new AltJFXBuilder(propertyCountAdapter), 500, 500);
        setupStage(new Stage(), "SWING DateCount Example", new SwingBuilder(
                observableCountAdapter), 900, 500);
    }

    private void setupStage(Stage stage, String lable, SceneBuilder builder,
            double x, double y) {
        stage.setScene(createScene(builder));
        stage.setX(x);
        stage.setY(y);
        stage.setTitle(lable);
        stage.show();
    }

    private Scene createScene(SceneBuilder builder) {
        new CountViewContainerDirector(builder).constructCountViewContainer();
        return builder.getScene();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

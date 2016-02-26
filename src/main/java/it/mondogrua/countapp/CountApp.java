package it.mondogrua.countapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import it.mondogrua.console.ConsoleBuilder;
import it.mondogrua.count.DateCount;
import it.mondogrua.count.ObservableCount;
import it.mondogrua.javafx_count_view.AltJFXBuilder;
import it.mondogrua.javafx_count_view.JFXBuilder;
import it.mondogrua.javafx_count_view.PropertyCountObserverObservableAdapter;
import it.mondogrua.swing_count_view.JavaUtilsCountObserverObservableAdapter;
import it.mondogrua.swing_count_view.SwingBuilder;
import it.mondogrua.utils.InputStreamSplitter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CountApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        configureCountApp(primaryStage);
    }

    private void configureCountApp(Stage primaryStage) throws IOException {
        ObservableCount count = new ObservableCount(new DateCount());

        JavaUtilsCountObserverObservableAdapter observableCountAdapter =
                new JavaUtilsCountObserverObservableAdapter();
        observableCountAdapter.setSubject(count);

        PropertyCountObserverObservableAdapter propertyCountAdapter =
                new PropertyCountObserverObservableAdapter();
        propertyCountAdapter.setSubject(count);

        InputStream fileInputStream = new FileInputStream("count-input.txt");
        InputStream systemInputStream = System.in;
        InputStreamSplitter streamSplitter = new InputStreamSplitter(
                systemInputStream);
        InputStream incrementIn = streamSplitter.split();
        InputStream decrementIn = streamSplitter.split();
        InputStream resetIn = streamSplitter.split();
        streamSplitter.start();

        PrintStream out = System.out;

        ConsoleBuilder consoleBuilder = new ConsoleBuilder(count);
        consoleBuilder.addIncrementStreamListener("+", fileInputStream);
        consoleBuilder.addIncrementStreamListener("+", incrementIn);
        consoleBuilder.addDecrementStreamListener("-", decrementIn);
        consoleBuilder.addResetStreamListener("r", resetIn);
        consoleBuilder.addDisplayStream(out);

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

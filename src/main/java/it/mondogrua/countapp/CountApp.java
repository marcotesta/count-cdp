package it.mondogrua.countapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import it.mondogrua.console.ConsoleBuilder;
import it.mondogrua.count.Count;
import it.mondogrua.count.DateCount;
import it.mondogrua.count.ObservableCount;
import it.mondogrua.javafx_count_view.AltJFXBuilder;
import it.mondogrua.javafx_count_view.JFXBuilder;
import it.mondogrua.swing.JavaUtilsObservableCount;
import it.mondogrua.swing_count_view.SwingBuilder;
import it.mondogrua.utils.InputStreamSplitter;
import it.mondogrua.utils.JavaUtilsToMgObserverObservableAdapter;
import it.mondogrua.utils.SimpleStringPropertyToMgObserverObservableAdapter;
import it.mondogrua.utils.ValueModel;
import it.mondogrua.utils.ValueModelAdaptor;
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

        JavaUtilsToMgObserverObservableAdapter javaUtilsObsObsAdapter =
                new JavaUtilsToMgObserverObservableAdapter();
        count.addObserver(javaUtilsObsObsAdapter);

        ValueModel valueModel = new ValueModelAdaptor(count,
                Count.GET_VALUE_METHOD, new Object[] {});
        SimpleStringPropertyToMgObserverObservableAdapter propertyObsObsAdapter =
                new SimpleStringPropertyToMgObserverObservableAdapter(valueModel);
        count.addObserver(propertyObsObsAdapter);

        InputStream fileInputStream = new FileInputStream("count-input.txt");
        InputStream systemInputStream = System.in;
        InputStreamSplitter streamSplitter = new InputStreamSplitter(
                systemInputStream);
        InputStream incrementIn = streamSplitter.split();
        InputStream decrementIn = streamSplitter.split();
        InputStream resetIn = streamSplitter.split();
        streamSplitter.start();

        PrintStream out = System.out;

        ConsoleBuilder consoleBuilder = new ConsoleBuilder(count, count);
        consoleBuilder.addIncrementStreamListener("+", fileInputStream);
        consoleBuilder.addIncrementStreamListener("+", incrementIn);
        consoleBuilder.addDecrementStreamListener("-", decrementIn);
        consoleBuilder.addResetStreamListener("r", resetIn);
        consoleBuilder.addDisplayStream(out);

        setupStage(primaryStage, "JavaFX DateCount Example", new JFXBuilder(
                propertyObsObsAdapter, count), 100, 500);
        setupStage(new Stage(), "Alternative JavaFX DateCount Example",
                new AltJFXBuilder(propertyObsObsAdapter, count), 500, 500);

        setupStage(new Stage(), "SWING DateCount Example",
                new SwingBuilder(new JavaUtilsObservableCount(javaUtilsObsObsAdapter, count)), 900, 500);
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

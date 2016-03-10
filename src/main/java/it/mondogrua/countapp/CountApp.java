package it.mondogrua.countapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import it.mondogrua.console.ConsoleBuilder;
import it.mondogrua.count.Count;
import it.mondogrua.count.DateCount;
import it.mondogrua.count.ObservableCount;
import it.mondogrua.javafx_count_view.AltJFXBuilder;
import it.mondogrua.javafx_count_view.JFXBuilder;
import it.mondogrua.javafx_count_view.SimpleStringPropertyCount;
import it.mondogrua.swing_count_view.BoundPropertyCount;
import it.mondogrua.swing_count_view.BoundPropertyCountSwingBuilder;
import it.mondogrua.swing_count_view.JavaUtilsObservableCount;
import it.mondogrua.swing_count_view.JavaUtilsObservableCountSwingBuilder;
import it.mondogrua.utils.BoundPropertyToMgObserverObservableAdapter;
import it.mondogrua.utils.InputStreamSplitter;
import it.mondogrua.utils.JavaUtilsToMgObserverObservableAdapter;
import it.mondogrua.utils.SimpleStringPropertyToMgObserverObservableAdapter;
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

        //
        JavaUtilsObservableCount javaUtilsObservableCount =
                createJavaUtilsObservableCount(count);
        JavaUtilsObservableCountSwingBuilder javaUtilsObservableCountSwingBuilder =
                new JavaUtilsObservableCountSwingBuilder(javaUtilsObservableCount);
        setupStage(new Stage(), "SWING DateCount Example",
                javaUtilsObservableCountSwingBuilder, 900, 500);

        //
        BoundPropertyCount boundPropertyCount = createBoundPropertyCount(count);
        BoundPropertyCountSwingBuilder boundPropertyCountSwingBuilder =
                new BoundPropertyCountSwingBuilder(boundPropertyCount);
        setupStage(new Stage(), "Alternative SWING DateCount Example",
                boundPropertyCountSwingBuilder, 1300, 500);

        //
        SimpleStringPropertyCount simpleStringPropertyCount =
                createSimpleStringPropertyCount(count);
        JFXBuilder jfxBuilder = new JFXBuilder(simpleStringPropertyCount);
        setupStage(primaryStage, "JavaFX DateCount Example", jfxBuilder, 100, 500);
        AltJFXBuilder altJFXBuilder = new AltJFXBuilder(simpleStringPropertyCount);
        setupStage(new Stage(), "Alternative JavaFX DateCount Example", altJFXBuilder, 500, 500);

        //
        setupConsole(count);
    }

    private SimpleStringPropertyCount createSimpleStringPropertyCount(ObservableCount count) {
        SimpleStringPropertyToMgObserverObservableAdapter simpleStringPropertyObsObsAdapter =
                new SimpleStringPropertyToMgObserverObservableAdapter(
                        new ValueModelAdaptor(count, Count.GET_VALUE_METHOD));
        count.addObserver(simpleStringPropertyObsObsAdapter);
        SimpleStringPropertyCount simpleStringPropertyCount =
                SimpleStringPropertyCount.create(simpleStringPropertyObsObsAdapter, count);
        return simpleStringPropertyCount;
    }

    private BoundPropertyCount createBoundPropertyCount(ObservableCount count) {
        BoundPropertyToMgObserverObservableAdapter boundPropertyObsObsAdapter =
                new BoundPropertyToMgObserverObservableAdapter();
        count.addObserver(boundPropertyObsObsAdapter);
        BoundPropertyCount boundPropertyCount = new BoundPropertyCount(
                boundPropertyObsObsAdapter, count);
        return boundPropertyCount;
    }

    private JavaUtilsObservableCount createJavaUtilsObservableCount(ObservableCount count) {
        JavaUtilsToMgObserverObservableAdapter javaUtilsObsObsAdapter =
                new JavaUtilsToMgObserverObservableAdapter();
        count.addObserver(javaUtilsObsObsAdapter);
        JavaUtilsObservableCount javaUtilsObservableCount =
                new JavaUtilsObservableCount(javaUtilsObsObsAdapter, count);
        return javaUtilsObservableCount;
    }

    private void setupConsole(ObservableCount count) throws FileNotFoundException, IOException {
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

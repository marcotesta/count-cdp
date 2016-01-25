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

        createCountViewContainer(primaryStage, "JavaFX DateCount Example", new JFXBuilder(observableCount, propertyObservableAdapter));
        createCountViewContainer(new Stage(), "Alternative JavaFX DateCount Example", new AltJFXBuilder(observableCount, propertyObservableAdapter));
        createCountViewContainer(new Stage(), "SWING DateCount Example", new SwingBuilder(observableCount, javaUtilsObservableAdapter));
    }

    private void createCountViewContainer(Stage stage, String lable, Builder builder) {
        CountViewContainer countViewContainer = new CountViewContainer();
        countViewContainer.openOn(builder);
        
        Scene scene = builder.getScene(350, 100);

        stage.setScene(scene);
        stage.setTitle(lable);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

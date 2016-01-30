package it.mondogrua.countapp;

import it.mondogrua.count.DateCount;
import it.mondogrua.count.ObservableCountDecorator;
import it.mondogrua.javafx.PropertyObservableObserverCount;
import it.mondogrua.javafx_count_view.AltJFXBuilder;
import it.mondogrua.javafx_count_view.JFXBuilder;
import it.mondogrua.swing.JavaUtilsObservableObserverCount;
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
		ObservableCountDecorator count = new ObservableCountDecorator(new DateCount());
        
        JavaUtilsObservableObserverCount javaUtilsObservableObserver = new JavaUtilsObservableObserverCount();
		count.addCountObserver(javaUtilsObservableObserver);
		
        PropertyObservableObserverCount propertyObservableObserver = new PropertyObservableObserverCount();
		count.addCountObserver(propertyObservableObserver);
		
        count.addCountObserver(new PrintStreamObserver(System.out ));

        setupStage(primaryStage, "JavaFX DateCount Example", new JFXBuilder(propertyObservableObserver), 100, 500);
        setupStage(new Stage(), "Alternative JavaFX DateCount Example", new AltJFXBuilder(propertyObservableObserver), 500, 500);
        setupStage(new Stage(), "SWING DateCount Example", new SwingBuilder(javaUtilsObservableObserver), 900, 500);
	}

    private void setupStage(Stage stage, String lable, Builder builder, double x, double y) {
    	stage.setScene(createScene(builder));
    	stage.setX(x);
    	stage.setY(y);
        stage.setTitle(lable);
        stage.show();
    }

	private Scene createScene(Builder builder) {
		CountViewContainerDirector director = new CountViewContainerDirector(builder);
    	director.construct();
        Scene scene = builder.getScene();
		return scene;
	}

    public static void main(String[] args) {
        launch(args);
    }
}

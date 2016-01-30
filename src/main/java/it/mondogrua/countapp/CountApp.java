package it.mondogrua.countapp;

import it.mondogrua.count.DateCount;
import it.mondogrua.count.ObservableCountDecorator;
import it.mondogrua.javafx.PropertyObservableValueModelObserver;
import it.mondogrua.javafx_count_view.AltJFXBuilder;
import it.mondogrua.javafx_count_view.JFXBuilder;
import it.mondogrua.swing.JavaUtilsObservableValueModelObserver;
import it.mondogrua.swing_count_view.SwingBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CountApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ObservableCountDecorator count = new ObservableCountDecorator(new DateCount());
        
        JavaUtilsObservableValueModelObserver javaUtilsObservableObserver = new JavaUtilsObservableValueModelObserver();
		count.addCountObserver(javaUtilsObservableObserver);
		
        PropertyObservableValueModelObserver propertyObservableObserver = new PropertyObservableValueModelObserver();
		count.addCountObserver(propertyObservableObserver);
		
        count.addCountObserver(new PrintStreamObserver(System.out ));

        createCountViewContainer(primaryStage, "JavaFX DateCount Example", new JFXBuilder(propertyObservableObserver), 100, 500);
        createCountViewContainer(new Stage(), "Alternative JavaFX DateCount Example", new AltJFXBuilder(propertyObservableObserver), 500, 500);
        createCountViewContainer(new Stage(), "SWING DateCount Example", new SwingBuilder(javaUtilsObservableObserver), 900, 500);
    }

    private void createCountViewContainer(Stage stage, String lable, Builder builder, double x, double y) {
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

package it.mondogrua.javafx_count_view;

import it.mondogrua.countapp.Builder;
import it.mondogrua.utils.PluggableAdaptor;
import it.mondogrua.utils.ValueModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class JFXBuilder implements Builder {

	private GridPane pane;
	private PropertyObservableAdapter observable;
	private ValueModel count;

	public JFXBuilder(ValueModel count, PropertyObservableAdapter aObservable) {
		this.count = count;
		this.observable = aObservable;
	}

	public void addPane() {
		makePane();
	}

	@Override
    public void addDisplayBoxOn(int x, int y) {
    	add(makeDisplayBoxOn(observable), x, y);
    }

    @Override
    public void addResetButtonOn(int x, int y) {
        add(makeButtonOn(count, "Reset", "reset"), x, y);
    }

    @Override
	public void addDecrementButtonOn(int x, int y) {
        add(makeButtonOn(count, "Decrement", "decrement"), x, y);
    }

    @Override
	public void addIncrementButtonOn(int x, int y) {
        add(makeButtonOn(count, "Increment", "increment"), x, y);
    }

	public Scene getScene(int x, int y) {
        return new Scene(pane, x, y);
	}

	protected void makePane() {
		pane = new GridPane();
    	pane.setAlignment(Pos.CENTER);
    	pane.setHgap(10);
    	pane.setVgap(10);
    	pane.setPadding(new Insets(25, 25, 25, 25));
	}

	protected Button makeButtonOn(final ValueModel count, String label, String action) {
        return new Button(label, new PluggableAdaptor(count , action, new Object[]{}));
    }

    protected Label makeDisplayBoxOn(final PropertyObservableAdapter count) {
        return new JFXDisplayBox(count);
    }

	private void add(Node node, int x, int y) {
    	pane.add(node, x, y);
    }
}

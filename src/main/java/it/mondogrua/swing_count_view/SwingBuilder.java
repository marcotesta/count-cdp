package it.mondogrua.swing_count_view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import it.mondogrua.countapp.Builder;
import it.mondogrua.utils.PluggableAdaptor;
import it.mondogrua.utils.ValueModel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class SwingBuilder implements Builder {

    private JPanel panel;
	private JavaUtilsObservableAdapter observable;
	private ValueModel count;

	public SwingBuilder(ValueModel aCount, JavaUtilsObservableAdapter observable) {
		super();
		count = aCount;
		this.observable = observable;
	}

	public void addPane() {
		panel = new JPanel();
    	panel.setLayout(new GridBagLayout());
	}
    
	@Override
    public void addDisplayBoxOn(int x, int y) {
        add(makeDisplayBoxOn(observable, "getValue"), x, y);
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

        final SwingNode swingNode = new SwingNode();
        swingNode.setContent(panel);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        return new Scene(pane, x, y);
	}

    private JButton makeButtonOn(final ValueModel count, String label, String action) {
        JButton button = new JButton(label);
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                new PluggableAdaptor(count , action, new Object[]{}).execute();
            }
        });

        return button;
    }

    private DisplayBox makeDisplayBoxOn(final JavaUtilsObservableAdapter count, String action) {
        return new DisplayBox(count, action);
    }

    private GridBagConstraints makeConstraintFrame(int x, int y) {
        GridBagConstraints constraintFrame = new GridBagConstraints();
        constraintFrame.insets = new Insets(5, 5, 5, 5);
        constraintFrame.gridx = x;
        constraintFrame.gridy = y;
        return constraintFrame;
    }
    
	private void add(JComponent node, int x, int y) {
		panel.add(node, makeConstraintFrame(x, y));
    }

}

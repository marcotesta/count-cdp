package it.mondogrua.swing_count_view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import it.mondogrua.count.Count;
import it.mondogrua.countapp.SceneBuilder;
import it.mondogrua.swing.DisplayBox;
import it.mondogrua.utils.PluggableAdaptor;
import it.mondogrua.utils.ValueModelAdaptor;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class SwingBuilder implements SceneBuilder {

    private JPanel panel;
    private Observable observable;
    private Count count;
    private Scene scene;

    public SwingBuilder(Observable anObservable, Count count) {
        super();
        this.observable = anObservable;
        this.count = count;
    }

    @Override
    public void addPane() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
    }

    @Override
    public void addDisplayBoxOn(int x, int y) {
        DisplayBox displayBox = makeDisplayBoxOn(count, Count.GET_VALUE_METHOD);
        observable.addObserver(displayBox);
        add(displayBox, x, y);

    }

    @Override
    public void addResetButtonOn(int x, int y) {
        add(makeButtonOn(count, "Reset", Count.RESET_METHOD), x, y);
    }

    @Override
    public void addDecrementButtonOn(int x, int y) {
        add(makeButtonOn(count, "Decrement", Count.DECREMENT_METHOD), x, y);
    }

    @Override
    public void addIncrementButtonOn(int x, int y) {
        add(makeButtonOn(count, "Increment", Count.INCREMENT_METHOD), x, y);
    }

    @Override
    public void addScene(int x, int y) {

        final SwingNode swingNode = new SwingNode();
        swingNode.setContent(panel);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
        scene = new Scene(pane, x, y);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    private JButton makeButtonOn(Object aModel, String label, String anAction) {
        PluggableAdaptor aCommand = new PluggableAdaptor(aModel, anAction,
                new Object[] {});
        JButton button = new JButton(label);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                aCommand.execute();
            }
        });

        return button;
    }

    private DisplayBox makeDisplayBoxOn(Object aModel, String action) {
        ValueModelAdaptor valueModel = new ValueModelAdaptor(aModel, action,
                new Object[] {});
        return new DisplayBox(valueModel);

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

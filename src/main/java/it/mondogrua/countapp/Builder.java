package it.mondogrua.countapp;

import javafx.scene.Scene;

public interface Builder {

    public void addPane();

    public void addDisplayBoxOn(int x, int y);

    public void addResetButtonOn(int x, int y);

    public void addDecrementButtonOn(int x, int y);

    public void addIncrementButtonOn(int x, int y);

    public void addScene(int x, int y);

    public Scene getScene();
}

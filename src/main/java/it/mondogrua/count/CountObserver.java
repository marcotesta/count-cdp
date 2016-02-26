package it.mondogrua.count;

public interface CountObserver {

    public void update();

    public void setSubject(ObservableCount aCount);
}

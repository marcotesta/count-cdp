package it.mondogrua.countapp;

public class CountViewContainer  {

    public void openOn(Builder builder) {
        buildViewsOn(builder);
    }

    private void buildViewsOn(Builder builder) {
    	builder.addPane();
    	builder.addDisplayBoxOn(1, 1);
    	builder.addIncrementButtonOn(0, 2);
    	builder.addDecrementButtonOn(1, 2);
    	builder.addResetButtonOn(2, 2);
    }
}

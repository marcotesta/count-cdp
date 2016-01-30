package it.mondogrua.countapp;

public class CountViewContainerDirector {

	private Builder builder;

	public CountViewContainerDirector(Builder builder) {
		this.builder = builder;
	}

    public void construct() {
    	builder.addPane();
    	builder.addDisplayBoxOn(1, 1);
    	builder.addIncrementButtonOn(0, 2);
    	builder.addDecrementButtonOn(1, 2);
    	builder.addResetButtonOn(2, 2);
        builder.addScene(350, 100);
     }
}

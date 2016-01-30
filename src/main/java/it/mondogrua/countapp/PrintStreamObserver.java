package it.mondogrua.countapp;

import java.io.PrintStream;

import it.mondogrua.count.Count;
import it.mondogrua.utils.CountObserver;

public class PrintStreamObserver implements CountObserver {
	private Count count;
	private PrintStream out;

	public PrintStreamObserver(PrintStream out) {
		this.out = out;
	}
	
	public void setSubject(Count aCount) {
		this.count = aCount;
		printValue();
	}

	@Override
	public void update() {
		printValue();
	}

	private void printValue() {
		out.println(retrieveValue());
	}

	private String retrieveValue() {
		return this.count.getModelValue().toString();
	}
}

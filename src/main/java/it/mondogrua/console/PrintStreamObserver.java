package it.mondogrua.console;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

import it.mondogrua.count.Count;
import it.mondogrua.count.CountObserver;

public class PrintStreamObserver implements CountObserver {
	private Count count;
	private PrintStream out;
	private Thread keyReader;

	public PrintStreamObserver(PrintStream out) {
		this.out = out;
		this.keyReader = new Thread(new KeyReader());
		this.keyReader.start();
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
		return this.count.getCountValue().toString();
	}
	
	private class KeyReader implements Runnable {
		@Override
		public void run() {
			try {
				Reader entrada = new InputStreamReader(System.in);
				while (!false) {
					char c = (char)entrada.read();
					if (c == 'r') {
						count.reset();
					} else if (c == '+') {
						count.increment();
					} else if (c == '-') {
						count.decrement();
					}
				}
			} catch(IOException ex){
				
			}
		}
	}
}

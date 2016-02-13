package it.mondogrua.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import it.mondogrua.count.Count;

public class StreamListener {

    private Thread keyReader;

    public StreamListener(Count count, InputStream in) {
        this.keyReader = new Thread(new KeyReader(count, in));
        this.keyReader.start();
    }

    private class KeyReader implements Runnable {

        public KeyReader(Count count, InputStream in) {
            super();
            this.count = count;
            this.in = in;
        }

        private Count count;
        private InputStream in;

        @Override
        public void run() {
            try {
                in = System.in;
                Reader entrada = new InputStreamReader(in);
                while (!false) {
                    char c = (char) entrada.read();
                    if (c == 'r') {
                        count.reset();
                    } else if (c == '+') {
                        count.increment();
                    } else if (c == '-') {
                        count.decrement();
                    }
                }
            } catch (IOException ex) {

            }
        }
    }
}

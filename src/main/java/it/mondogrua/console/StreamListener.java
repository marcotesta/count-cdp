package it.mondogrua.console;

import java.io.InputStream;
import java.util.Scanner;

import it.mondogrua.utils.PluggableAdaptor;

public class StreamListener {

    private Thread keyReader;

    public StreamListener(PluggableAdaptor aCommand, InputStream in,
            String regex) {
        this.keyReader = new Thread(new KeyReader(aCommand, in, regex));
        this.keyReader.start();
    }

    private class KeyReader implements Runnable {

        private PluggableAdaptor command;
        private InputStream in;
        private String regex;

        public KeyReader(PluggableAdaptor aCommand, InputStream in,
                String regex) {
            super();
            this.command = aCommand;
            this.in = in;
            this.regex = regex;
        }

        @Override
        public void run() {
            Scanner reader = new Scanner(in);
            try {
                while (!false) {
                    String c = reader.nextLine();
                    if (c.equals(regex)) {
                        command.execute();
                    }
                }
            } finally {
                reader.close();
            }
        }
    }
}

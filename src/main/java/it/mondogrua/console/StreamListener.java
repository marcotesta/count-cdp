package it.mondogrua.console;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import it.mondogrua.utils.PluggableAdaptor;

public class StreamListener {

    private Executor executor;

    public StreamListener(PluggableAdaptor aCommand, InputStream in,
            String regex) {
        KeyReader reader = new KeyReader(aCommand, in, regex);

        executor = Executors.newSingleThreadExecutor();
        executor.execute(reader);
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
                while (reader.hasNextLine()) {
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

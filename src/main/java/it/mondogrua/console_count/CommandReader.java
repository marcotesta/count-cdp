package it.mondogrua.console_count;

import java.io.BufferedReader;
import java.io.IOException;

import it.mondogrua.utils.PluggableAdaptor;

public class CommandReader implements Runnable {

    private PluggableAdaptor command;
    private BufferedReader reader;
    private String regex;

    public CommandReader(PluggableAdaptor aCommand, BufferedReader scanner,
            String regex) {
        super();
        this.command = aCommand;
        this.reader = scanner;
        this.regex = regex;
    }

    @Override
    public void run() {

        String c;
        try {
            while ((c = reader.readLine()) != null) {
                if (c.equals(regex)) {
                    command.execute();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }
}

package it.mondogrua.console_count;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader {

    private static final int DEFAULT_RETRY_DELAY = 1000;

    private BufferedReader reader;
    private final List<StringListener> listeners =
            new ArrayList<StringListener>();
    private volatile boolean run = true;

    public LineReader(BufferedReader scanner) {
        super();
        this.reader = scanner;
    }

    public void tail() {

        try {
            while (run) {
                readLines();
                Thread.sleep(DEFAULT_RETRY_DELAY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    private void readLines() throws IOException {
        while (run) {
            String string = reader.readLine();
            if (string == null) {
                break;
            }
            notifyListeners(string);
        }
    }

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String c) {
        for (StringListener listener : listeners) {
            listener.update(c);
        }
    }
}

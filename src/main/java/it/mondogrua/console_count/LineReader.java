package it.mondogrua.console_count;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader {

    private BufferedReader reader;
    private final List<StringListener> listeners =
            new ArrayList<StringListener>();

    public LineReader(BufferedReader scanner) {
        super();
        this.reader = scanner;
    }

    public void readLines() {

        try {
            String c;
            while ((c = reader.readLine()) != null) {
                notifyListeners(c);
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

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String c) {
        for (StringListener listener : listeners) {
            listener.update(c);
        }
    }
}

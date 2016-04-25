package it.mondogrua.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class BufferedReaderTail {

    private static final int DEFAULT_RETRY_DELAY = 1000;

    public static interface TailListener {

        void handle(String line);

        void handle(Exception ex);
    }

    private volatile boolean run = true;

    public void stop() {
        this.run = false;
    }

    public void tail(final BufferedReader aReader,
            final TailListener aListener) {

        try {
            while (run) {
                readLines(aReader, aListener);
                Thread.sleep(DEFAULT_RETRY_DELAY);
            }
        } catch (final Exception e) {
            aListener.handle(e);
            stop();
        }
    }

    private void readLines(final BufferedReader aFile, TailListener aListener)
            throws IOException {
        while (run) {
            String string = aFile.readLine();
            if (string == null) {
                break;
            }
            aListener.handle(string);
        }
    }

}

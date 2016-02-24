package it.mondogrua.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InputStreamSplitter {

    private Executor executor;

    private List<OutputStream> outputs = Collections.synchronizedList(
            new ArrayList<OutputStream>());

    private InputStream input;

    public InputStreamSplitter(InputStream input) {
        this.input = input;
    }

    public void start() {
        KeyReader reader = new KeyReader(input);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(reader);
    }

    public InputStream split() throws IOException {
        PipedOutputStream output = new PipedOutputStream();
        PipedInputStream input = new PipedInputStream(output);
        outputs.add(output);
        return input;
    }

    private class KeyReader implements Runnable {

        private InputStream in;

        public KeyReader(InputStream in) {
            super();
            this.in = in;
        }

        @Override
        public void run() {
            while (!false) {
                try {
                    int data = in.read();
                    while (data != -1) {
                        writeToOutputStreams(data);
                        data = in.read();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void writeToOutputStreams(int data) {
            for (OutputStream outputStream : outputs) {
                try {
                    outputStream.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

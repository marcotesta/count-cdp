package it.mondogrua.utils;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReaderSplitter {

    private Executor executor;

    private List<Writer> outputs = Collections.synchronizedList(
            new ArrayList<Writer>());

    private Reader input;

    public ReaderSplitter(Reader input) {
        this.input = input;
    }

    public void start() {
        KeyReader reader = new KeyReader(input);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(reader);
    }

    public Reader split() throws IOException {
        PipedWriter output = new PipedWriter();
        PipedReader input = new PipedReader(output);
        outputs.add(output);
        return input;
    }

    private class KeyReader implements Runnable {

        private Reader in;

        public KeyReader(Reader in) {
            super();
            this.in = in;
        }

        @Override
        public void run() {
            while (!false) {
                try {
                    int data = in.read();
                    while (data != -1) {
                        writeToWriters(data);
                        data = in.read();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void writeToWriters(int data) {
            for (Writer output : outputs) {
                try {
                    output.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package it.mondogrua.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriterSplitter extends BufferedWriter {

    public WriterSplitter(BufferedWriter bufferedWriter) {
        super(bufferedWriter);
        add(bufferedWriter);
    }

    private List<BufferedWriter> outputs = Collections.synchronizedList(
            new ArrayList<BufferedWriter>());

    public void add(BufferedWriter writer) {
        outputs.add(writer);
    }

    @Override
    public void write(String str) {
        for (BufferedWriter bufferedWriter : outputs) {
            try {
                bufferedWriter.write(str);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void newLine() throws IOException {
        for (BufferedWriter bufferedWriter : outputs) {
            bufferedWriter.newLine();
        }
    }

    @Override
    public void flush() throws IOException {
        for (BufferedWriter bufferedWriter : outputs) {
            bufferedWriter.flush();
        }
    }

}

package it.mondogrua.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;

public class EOFWriter extends Writer {

    private RandomAccessFile file;

    public EOFWriter(String fileName) throws FileNotFoundException {
        file = new RandomAccessFile(fileName, "rw");
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        file.seek(file.length());
        file.write(new String(cbuf).getBytes("UTF-8"), off, len);
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
        file.close();
    }

}

package com.pluralsight.deli.services;

import java.io.*;

public class FileHandler {
    //Initializing the BufferedWriter
    public static BufferedWriter getBufferedWriter(String filename) throws IOException {
        BufferedWriter bufWriter = new BufferedWriter(new FileWriter(filename));
        return bufWriter;
    }

    //Initializing the BufferedReader
    public static BufferedReader openFileReader(String filename) throws FileNotFoundException {
        BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        return bufReader;
    }
}

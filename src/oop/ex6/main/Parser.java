package oop.ex6.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final String fileName;
    private final HashMap<LineTypes, String> lineTypesRegexMap = new HashMap<>();

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public void readFile() throws IOException {
        String line;
        Pattern p;
        Matcher m;

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}

package oop.ex6.main;

import oop.ex6.handlers.LineHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final String fileName;
    private final HashMap<LineTypes, String> lineTypesRegexMap;

    public Parser(String fileName) {
        this.fileName = fileName;
        lineTypesRegexMap = RegexGlobals.createLineTypesMap();
    }

    public boolean readFile() throws IOException {
        String line;
        Pattern p;
        Matcher m;
        boolean matched = false;

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                for (Map.Entry<LineTypes, String> entry : lineTypesRegexMap.entrySet()) {
                    p = Pattern.compile(entry.getValue());
                    m = p.matcher(line);
                    if (m.matches()) {
                        matched = true;
                        LineHandler.handleLine(entry.getKey(), line);
                    }
                }
                if (!matched)
                    return false;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}

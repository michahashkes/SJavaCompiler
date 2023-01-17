package oop.ex6.main;

import oop.ex6.handlers.GlobalVariableHandler;
import oop.ex6.handlers.LineHandler;
import oop.ex6.handlers.LocalVariableHandler;
import oop.ex6.handlers.VariableHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final String fileName;
    private final  HashMap<StatementTypes, HashMap<Types, String>> lineTypesRegexMap;
    private final HashMap<StatementTypes,String> scopeTypesRegexMap;


    public Parser(String fileName) {
        this.fileName = fileName;
        lineTypesRegexMap = RegexGlobals.createLineTypesMap();
        scopeTypesRegexMap = RegexScopes.createLineTypesMap();
    }

    public boolean readFile() throws IOException {
        String line;
        Pattern p;
        Matcher m;
        boolean matched = false;
        LineHandler lineHandler = new LineHandler(new VariableHandler());
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                for (Map.Entry<StatementTypes,HashMap<Types, String>> entry : lineTypesRegexMap.entrySet()) {
                    for(Map.Entry<Types, String> lineAndType: entry.getValue().entrySet()){
                        p = Pattern.compile(lineAndType.getValue());
                        m = p.matcher(line);
                        if (m.matches()) {
                            matched = true;
                            lineHandler.handleLine(entry.getKey(),lineAndType.getKey(),m);
                        }
                    }
                }
                for (Map.Entry<StatementTypes,String> lineAndType: scopeTypesRegexMap.entrySet()){
                    p = Pattern.compile(lineAndType.getValue());
                    m = p.matcher(line);
                    if (m.matches()) {
                        matched = true;
                        lineHandler.handleLine(lineAndType.getKey(),null,m);
                    }
                }
                if (!matched)
                    return false;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

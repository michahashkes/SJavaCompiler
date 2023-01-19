package oop.ex6.main;

import oop.ex6.handlers.LineHandler;
import oop.ex6.main.regex.RegexGlobals;
import oop.ex6.main.regex.RegexScopes;
import oop.ex6.main.regex.StatementTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String ILLEGAL_VARIABLES_ERROR = "Illegal Variables";
    private static final String ILLEGAL_SCOPES_ERROR = "Illegal Scopes";
    private static final String NOT_MATCHED_ERROR = "not matched";
    private final String fileName;
    private final  HashMap<StatementTypes, Set<String>> lineTypesRegexMap;
    private final HashMap<StatementTypes,String> scopeTypesRegexMap;


    public Parser(String fileName) {
        this.fileName = fileName;
        lineTypesRegexMap = RegexGlobals.createLineTypesMap();
        scopeTypesRegexMap = RegexScopes.createLineTypesMap();
    }

    public boolean readFile() throws IOException, FileFormatException,RuntimeException {
        String line;
        Pattern p;
        Matcher m;
        boolean matched = false;
        LineHandler lineHandler = new LineHandler();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                matched = false;
                for (Map.Entry<StatementTypes,Set<String>> entry : lineTypesRegexMap.entrySet()) {
                    for(String value : entry.getValue()){
                        p = Pattern.compile(value);
                        m = p.matcher(line);
                        if (m.matches()) {
                            matched = true;
                            if (!lineHandler.handleLine(entry.getKey(), m))
                                throw new FileFormatException(ILLEGAL_VARIABLES_ERROR);
                            break;
                        }
                    }
                }
                for (Map.Entry<StatementTypes,String> lineAndType: scopeTypesRegexMap.entrySet()){
                    p = Pattern.compile(lineAndType.getValue());
                    m = p.matcher(line);
                    if (m.matches()) {
                        matched = true;
                        if (!lineHandler.handleLine(lineAndType.getKey(),m))
                            throw new FileFormatException(ILLEGAL_SCOPES_ERROR);
                        break;
                    }
                }
                if (!matched)
                    throw new FileFormatException(NOT_MATCHED_ERROR);
            }
        }
        return true;
    }
}

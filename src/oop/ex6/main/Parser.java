package oop.ex6.main;

import oop.ex6.handlers.LineHandler;
import oop.ex6.handlers.scopes.*;
import oop.ex6.handlers.variables.IllegalAssignException;
import oop.ex6.handlers.variables.IllegalDeclarationException;
import oop.ex6.handlers.variables.IllegalFinalVariableException;
import oop.ex6.handlers.variables.IllegalInitializedVariableException;
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
    private final HashMap<StatementTypes, Set<String>> lineTypesRegexMap;
    private final HashMap<StatementTypes, String> scopeTypesRegexMap;
    private Pattern p;
    private Matcher m;
    boolean matched = false;

    /*
    constructor
     */
    public Parser(String fileName) {
        this.fileName = fileName;
        lineTypesRegexMap = RegexGlobals.createLineTypesMap();
        scopeTypesRegexMap = RegexScopes.createLineTypesMap();
    }

    /**
     * This function goes through the entire document, and checks line by line whether it is correct
     *
     * @return true if the file is valid
     * @throws IOException         -If an error occurs with open file or close it
     * @throws FileFormatException - if one part of the file is not valid
     * @throws RuntimeException
     */

    public boolean readFile() throws IOException, IllegalLineException, IllegalVariablesException
            , IllegalScopesException, IllegalInitializedVariableException,
            IllegalAssignException, IllegalDeclarationException, IllegalFinalVariableException,
            IllegalIfWhileException, IllegalMethodCallException, IllegalEndOfScopesException,
            IllegalMethodDefinitionException {

        LineHandler lineHandler = new LineHandler();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                matched = false;
                validLine(line, lineHandler);
                validScope(line, lineHandler);
                if (!matched)
                    throw new IllegalLineException(NOT_MATCHED_ERROR);
            }
        }
        return true;
    }

    /**
     * valid line of declare assign , final or initialized
     **/
    private void validLine(String line, LineHandler lineHandler) throws
            IllegalVariablesException, IllegalInitializedVariableException, IllegalAssignException,
            IllegalDeclarationException, IllegalFinalVariableException, IllegalIfWhileException,
            IllegalMethodCallException, IllegalEndOfScopesException, IllegalMethodDefinitionException {
        for (Map.Entry<StatementTypes, Set<String>> entry : lineTypesRegexMap.entrySet()) {
            for (String value : entry.getValue()) {
                p = Pattern.compile(value);
                m = p.matcher(line);
                if (m.matches()) {
                    matched = true;
                    if (!lineHandler.handleLine(entry.getKey(), m))
                        throw new IllegalVariablesException(ILLEGAL_VARIABLES_ERROR);
                    break;
                }
            }
        }
    }

    /**
     * valid line of if, while, method call, method declare or return
     **/
    private void validScope(String line, LineHandler lineHandler) throws IllegalScopesException,
            IllegalInitializedVariableException, IllegalAssignException, IllegalDeclarationException,
            IllegalFinalVariableException, IllegalIfWhileException, IllegalMethodCallException,
            IllegalEndOfScopesException, IllegalMethodDefinitionException {
        for (Map.Entry<StatementTypes, String> lineAndType : scopeTypesRegexMap.entrySet()) {
            p = Pattern.compile(lineAndType.getValue());
            m = p.matcher(line);
            if (m.matches()) {
                matched = true;
                if (!lineHandler.handleLine(lineAndType.getKey(), m))
                    throw new IllegalScopesException(ILLEGAL_SCOPES_ERROR);
                break;
            }
        }

    }

}

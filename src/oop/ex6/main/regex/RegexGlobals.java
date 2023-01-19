package oop.ex6.main.regex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RegexGlobals {

    private static final Set<String> declareMap = new HashSet<>();
    private static final Set<String> assignMap = new HashSet<>();
    private static final Set<String> finalMap = new HashSet<>();


    public static HashMap<StatementTypes,Set<String>> createLineTypesMap() {
        HashMap<StatementTypes,Set<String>> lineTypesMap = new HashMap<>();
        putDeclareMap();
        putAssignMap();
        putFinalMap();
        lineTypesMap.put(StatementTypes.DECLARE,declareMap);
        lineTypesMap.put(StatementTypes.ASSIGN,assignMap);
        lineTypesMap.put(StatementTypes.FINAL,finalMap);
        return lineTypesMap;
    }

    /*
    add all declaration regex strings to the set
     */

    private static void putDeclareMap() {
        declareMap.add( INT_DECLARATION_INIT_PARSER);
        declareMap.add( DOUBLE_DECLARATION_INIT_PARSER);
        declareMap.add( STRING_DECLARATION_INIT_PARSER);
        declareMap.add( BOOLEAN_DECLARATION_INIT_PARSER);
        declareMap.add( CHAR_DECLARATION_INIT_PARSER);
    }
     /*
    add all assign regex strings to the set
     */

    private static void putAssignMap() {
        assignMap.add(ASSIGNMENT);
    }
    /*
    add all fina regex string to the set
     */

    private static void putFinalMap() {
        finalMap.add( INT_FINAL_PARSER);
        finalMap.add( DOUBLE_FINAL_PARSER);
        finalMap.add( STRING_FINAL_PARSER);
        finalMap.add( BOOLEAN_FINAL_PARSER);
        finalMap.add( CHAR_FINAL_PARSER);
    }

    public final static String VARIABLE_NAME_REGEX = "(_+\\w*[a-zA-Z]+\\w*|_*[a-zA-Z]+\\w*)";
    public final static String INT_REGEX = "[+-]?\\d+";
    public final static String DOUBLE_REGEX = "[-+]?\\d*\\.?\\d+";
    public final static String STRING_REGEX = "\".*\"";
    public final static String BOOLEAN_REGEX = DOUBLE_REGEX + "|" + INT_REGEX + "|true|false";
    public final static String CHAR_REGEX = "'.'";
    public final static String NAME_FORMAT = "(" + VARIABLE_NAME_REGEX + ")";
    public final static String INT_FORMAT = "(" + NAME_FORMAT + "|" + INT_REGEX + ")";
    public final static String DOUBLE_FORMAT =  "(" + NAME_FORMAT + "|" + DOUBLE_REGEX + ")";
    public final static String STRING_FORMAT =  "(" + NAME_FORMAT + "|" + STRING_REGEX + ")";
    public final static String BOOLEAN_FORMAT = "("+ NAME_FORMAT + "|" + BOOLEAN_REGEX + ")";
    public final static String CHAR_FORMAT =  "(" + NAME_FORMAT + "|" + CHAR_REGEX + ")";

    public final static String ALL_FORMAT = "(" + VARIABLE_NAME_REGEX + "|" + BOOLEAN_REGEX + "|" +
    CHAR_REGEX + "|" + STRING_REGEX + ")";


    //int
    public final static String INT_FINAL_PARSER = "\\s*(final\\s+int)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT + ")*)\\s*;\\s*";
    public final static String INT_DECLARATION_INIT_PARSER = "\\s*(int)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + INT_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + INT_FORMAT + ")?)*)\\s*;\\s*";
    //double
    public final static String DOUBLE_FINAL_PARSER = "\\s*(final\\s+double)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT +
            ")\\s*(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT + ")*)\\s*;\\s*";
    public final static String DOUBLE_DECLARATION_INIT_PARSER ="\\s*(double)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + DOUBLE_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + DOUBLE_FORMAT + ")?)*)\\s*;\\s*";
    //string
    public final static String STRING_FINAL_PARSER = "\\s*(final\\s+String)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + ")*)\\s*;\\s*";
    public final static String STRING_DECLARATION_INIT_PARSER ="\\s*(String)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + STRING_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + STRING_FORMAT + ")?)*)\\s*;\\s*";
    //boolean
    public final static String BOOLEAN_FINAL_PARSER = "\\s*(final\\s+boolean)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + ")*)\\s*;\\s*";
    public final static String BOOLEAN_DECLARATION_INIT_PARSER = "\\s*(boolean)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + BOOLEAN_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + BOOLEAN_FORMAT + ")?)*)\\s*;\\s*";
    //char
    public final static String CHAR_FINAL_PARSER = "\\s*(final\\s+char)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + ")*)\\s*;\\s*";
    public final static String CHAR_DECLARATION_INIT_PARSER = "\\s*(char)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + CHAR_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + CHAR_FORMAT + ")?)*)\\s*;\\s*";

    public final static String ASSIGNMENT = "\\s*(" + VARIABLE_NAME_REGEX + "\\s*=\\s*" + ALL_FORMAT + ")\\s*(\\s*," +
            "\\s*" + VARIABLE_NAME_REGEX + "\\s*=\\s*" + ALL_FORMAT + "\\s*)*;\\s*";



}

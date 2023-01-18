package oop.ex6.main;

import java.util.HashMap;

public class RegexGlobals {

    private static final HashMap<Types, String> declareMap = new HashMap<>();
    private static final HashMap<Types, String> assignMap = new HashMap<>();
    private static final HashMap<Types, String> finalMap = new HashMap<>();

//    private static final HashMap<LineTypes,String> ifMap = new HashMap<>();
//    private static final HashMap<LineTypes,String> whileMap = new HashMap<

    public static HashMap<StatementTypes, HashMap<Types, String>> createLineTypesMap() {
        HashMap<StatementTypes, HashMap<Types, String>> lineTypesMap = new HashMap<>();
        putDeclareMap();
        putAssignMap();
        putFinalMap();
        lineTypesMap.put(StatementTypes.DECLARE,declareMap);
        lineTypesMap.put(StatementTypes.ASSIGN,assignMap);
        lineTypesMap.put(StatementTypes.FINAL,finalMap);
        return lineTypesMap;
    }

    private static void putDeclareMap() {
        declareMap.put(Types.INT, INT_DECLARATION_INIT_PARSER);
        declareMap.put(Types.DOUBLE, DOUBLE_DECLARATION_INIT_PARSER);
        declareMap.put(Types.STRING, STRING_DECLARATION_INIT_PARSER);
        declareMap.put(Types.BOOLEAN, BOOLEAN_DECLARATION_INIT_PARSER);
        declareMap.put(Types.CHAR, CHAR_DECLARATION_INIT_PARSER);
    }

    private static void putAssignMap() {
//        assignMap.put(Types.INT, INT_ASSIGNMENT_PARSER);
//        assignMap.put(Types.DOUBLE, DOUBLE_ASSIGNMENT_PARSER);
//        assignMap.put(Types.STRING, STRING_ASSIGNMENT_PARSER);
//        assignMap.put(Types.BOOLEAN, BOOLEAN_ASSIGNMENT_PARSER);
//        assignMap.put(Types.CHAR, CHAR_ASSIGNMENT_PARSER);
        assignMap.put(Types.INT, ASSIGNMENT);
        assignMap.put(Types.DOUBLE, ASSIGNMENT);
        assignMap.put(Types.STRING, ASSIGNMENT);
        assignMap.put(Types.BOOLEAN, ASSIGNMENT);
        assignMap.put(Types.CHAR, ASSIGNMENT);
    }

    private static void putFinalMap() {
        finalMap.put(Types.INT, INT_FINAL_PARSER);
        finalMap.put(Types.DOUBLE, DOUBLE_FINAL_PARSER);
        finalMap.put(Types.STRING, STRING_FINAL_PARSER);
        finalMap.put(Types.BOOLEAN, BOOLEAN_FINAL_PARSER);
        finalMap.put(Types.CHAR, CHAR_FINAL_PARSER);
    }

    public final static String VARIABLE_NAME_REGEX = "_*[a-zA-Z]+\\w*";
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
    public final static String INT_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT + ")(\\s*,\\s*" +
            NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT + "\\s*)*\\s*;\\s*";
    //double
    public final static String DOUBLE_FINAL_PARSER = "\\s*(final\\s+double)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT +
            ")\\s*(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT + ")*)\\s*;\\s*";
    public final static String DOUBLE_DECLARATION_INIT_PARSER ="\\s*(double)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + DOUBLE_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + DOUBLE_FORMAT + ")?)*)\\s*;\\s*";
    public final static String DOUBLE_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT +
            ")\\s*(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT + "\\s*)*;\\s*";
    //string
    public final static String STRING_FINAL_PARSER = "\\s*(final\\s+String)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + ")*)\\s*;\\s*";
    public final static String STRING_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + "\\s*)*;\\s*";
    public final static String STRING_DECLARATION_INIT_PARSER ="\\s*(String)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + STRING_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + STRING_FORMAT + ")?)*)\\s*;\\s*";
    //boolean
    public final static String BOOLEAN_FINAL_PARSER = "\\s*(final\\s+boolean)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + ")*)\\s*;\\s*";
    public final static String BOOLEAN_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + "\\s*)*;\\s*";
    public final static String BOOLEAN_DECLARATION_INIT_PARSER = "\\s*(boolean)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + BOOLEAN_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + BOOLEAN_FORMAT + ")?)*)\\s*;\\s*";
    //char
    public final static String CHAR_FINAL_PARSER = "\\s*(final\\s+char)\\s+((" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + ")*)\\s*;\\s*";
    public final static String CHAR_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + "\\s*)*;\\s*";
    public final static String CHAR_DECLARATION_INIT_PARSER = "\\s*(char)\\s+(" + NAME_FORMAT + "(\\s*=\\s*" + CHAR_FORMAT +
            ")?\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + CHAR_FORMAT + ")?)*)\\s*;\\s*";

    public final static String ASSIGNMENT = "\\s*(" + VARIABLE_NAME_REGEX + "\\s*=\\s*" + ALL_FORMAT + ")\\s*(\\s*," +
            "\\s*" + VARIABLE_NAME_REGEX + "\\s*=\\s*" + ALL_FORMAT + "\\s*)*;\\s*";



}

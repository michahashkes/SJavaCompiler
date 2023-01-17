package oop.ex6.main;

import java.util.HashMap;

public class RegexGlobals {

    public static final HashMap<LineTypes, String> declareMap = new HashMap<>();
    public static final HashMap<LineTypes, String> assignMap = new HashMap<>();
    public static final HashMap<LineTypes, String> finalMap = new HashMap<>();

    public static HashMap<StatementTypes, HashMap<LineTypes, String>> createLineTypesMap() {
        HashMap<StatementTypes, HashMap<LineTypes, String>> lineTypesMap = new HashMap<>();
        putDeclareMap();
        putAssignMap();
        putFinalMap();
        lineTypesMap.put(StatementTypes.DECLARE,declareMap);
        lineTypesMap.put(StatementTypes.ASSIGN,assignMap);
        lineTypesMap.put(StatementTypes.FINAL,finalMap);
        return lineTypesMap;
    }

    private static void putDeclareMap() {
        declareMap.put(LineTypesDeclare.INT_DECLARATION_INIT_PARSER, INT_DECLARATION_INIT_PARSER);
        declareMap.put(LineTypesDeclare.DOUBLE_DECLARATION_INIT_PARSER, DOUBLE_DECLARATION_INIT_PARSER);
        declareMap.put(LineTypesDeclare.STRING_DECLARATION_INIT_PARSER, STRING_DECLARATION_INIT_PARSER);
        declareMap.put(LineTypesDeclare.BOOLEAN_DECLARATION_INIT_PARSER, BOOLEAN_DECLARATION_INIT_PARSER);
        declareMap.put(LineTypesDeclare.CHAR_DECLARATION_INIT_PARSER, CHAR_DECLARATION_INIT_PARSER);
    }

    private static void putAssignMap() {
        assignMap.put(LineTypesAssign.INT_ASSIGNMENT_PARSER, INT_ASSIGNMENT_PARSER);
        assignMap.put(LineTypesAssign.DOUBLE_ASSIGNMENT_PARSER, DOUBLE_ASSIGNMENT_PARSER);
        assignMap.put(LineTypesAssign.STRING_ASSIGNMENT_PARSER, STRING_ASSIGNMENT_PARSER);
        assignMap.put(LineTypesAssign.BOOLEAN_ASSIGNMENT_PARSER, BOOLEAN_ASSIGNMENT_PARSER);
        assignMap.put(LineTypesAssign.CHAR_ASSIGNMENT_PARSER, CHAR_ASSIGNMENT_PARSER);
    }

    private static void putFinalMap() {
        finalMap.put(LineTypesFinal.INT_FINAL_PARSER, INT_FINAL_PARSER);
        finalMap.put(LineTypesFinal.DOUBLE_FINAL_PARSER, DOUBLE_FINAL_PARSER);
        finalMap.put(LineTypesFinal.STRING_FINAL_PARSER, STRING_FINAL_PARSER);
        finalMap.put(LineTypesFinal.BOOLEAN_FINAL_PARSER, BOOLEAN_FINAL_PARSER);
        finalMap.put(LineTypesFinal.CHAR_FINAL_PARSER, CHAR_FINAL_PARSER);
    }


    public final static String NAME_FORMAT = "(_*[a-zA-Z]+\\w*)";
    public final static String INT_FORMAT = "("+NAME_FORMAT+"|[+-]?\\d+)";
    public final static String DOUBLE_FORMAT = "("+NAME_FORMAT+"|[+-]?.(?\\d+.?\\d*)";
    public final static String STRING_FORMAT = "("+NAME_FORMAT+"|\".*\")";
    public final static String BOOLEAN_FORMAT = "("+DOUBLE_FORMAT+"|[+-]?\\d+|true|false|)";
    public final static String CHAR_FORMAT = "("+NAME_FORMAT+"|'.')";


    //int
    public final static String INT_FINAL_PARSER = "\\s*final\\s+int\\s+(" + NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT + ")*\\s*;";
    public final static String INT_DECLARATION_INIT_PARSER = "\\s*int\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + INT_FORMAT + ")?)\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + INT_FORMAT + ")?)*;";
    public final static String INT_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT + ")(\\s*,\\s*" +
            NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT + "\\s*)*\\s*;";
    //double
    public final static String DOUBLE_FINAL_PARSER = "\\s*final\\s+double\\s+(" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT +
            ")\\s*(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT + ")*\\s*;";
    public final static String DOUBLE_DECLARATION_INIT_PARSER = "\\s*double\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + DOUBLE_FORMAT + ")?)\\s*(\\s*,\\s*\\s*" + NAME_FORMAT + "(\\s*=\\s*" + DOUBLE_FORMAT + ")?)*;";
    public final static String DOUBLE_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT +
            ")\\s*(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT + "\\s*)*;";
    //string
    public final static String STRING_FINAL_PARSER = "\\s*final\\s+String\\s+(" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + ")*\\s*;";
    public final static String STRING_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + "\\s*)*;";
    public final static String STRING_DECLARATION_INIT_PARSER = "\\s*String\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + STRING_FORMAT + ")?)\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + STRING_FORMAT + ")?)*;";
    //boolean
    public final static String BOOLEAN_FINAL_PARSER = "\\s*final\\s+boolean\\s+(" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + ")*\\s*;";
    public final static String BOOLEAN_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + "\\s*)*;";
    public final static String BOOLEAN_DECLARATION_INIT_PARSER = "\\s*boolean\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + BOOLEAN_FORMAT + ")?)\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + BOOLEAN_FORMAT + ")?)*;";
    //char
    public final static String CHAR_FINAL_PARSER = "\\s*final\\s+char\\s+(" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + ")*\\s*;";
    public final static String CHAR_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + "\\s*)*;";
    public final static String CHAR_DECLARATION_INIT_PARSER = "\\s*char\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + CHAR_FORMAT + ")?)\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + CHAR_FORMAT + ")?)*;";

}

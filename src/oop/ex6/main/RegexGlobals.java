package oop.ex6.main;

import java.util.HashMap;

public class RegexGlobals {
    public HashMap<LineTypes, String> createLineTypesMap() {
        return new HashMap<>();
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
    public final static String INT_DECLARATION_ASSIGNMENT_PARSER = "\\s*int\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + INT_FORMAT + ")?)\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + INT_FORMAT + ")?)*;";
    public final static String INT_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT + ")(\\s*,\\s*" +
            NAME_FORMAT + "\\s*=\\s*" + INT_FORMAT + "\\s*)*\\s*;";
    //double
    public final static String DOUBLE_FINAL_PARSER = "\\s*final\\s+double\\s+(" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT +
            ")\\s*(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT + ")*\\s*;";
    public final static String DOUBLE_DECLARATION_ASSIGNMENT_PARSER = "\\s*double\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + DOUBLE_FORMAT + ")?)\\s*(\\s*,\\s*\\s*" + NAME_FORMAT + "(\\s*=\\s*" + DOUBLE_FORMAT + ")?)*;";
    public final static String DOUBLE_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT +
            ")\\s*(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + DOUBLE_FORMAT + "\\s*)*;";
    //string
    public final static String STRING_FINAL_PARSER = "\\s*final\\s+String\\s+(" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + ")*\\s*;";
    public final static String STRING_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + STRING_FORMAT + "\\s*)*;";
    public final static String STRING_DECLARATION_ASSIGNMENT_PARSER = "\\s*String\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + STRING_FORMAT + ")?)\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + STRING_FORMAT + ")?)*;";
    //boolean
    public final static String BOOLEAN_FINAL_PARSER = "\\s*final\\s+boolean\\s+(" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + ")*\\s*;";
    public final static String BOOLEAN_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + BOOLEAN_FORMAT + "\\s*)*;";
    public final static String BOOLEAN_DECLARATION_ASSIGNMENT_PARSER = "\\s*boolean\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + BOOLEAN_FORMAT + ")?)\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + BOOLEAN_FORMAT + ")?)*;";
    //char
    public final static String CHAR_FINAL_PARSER = "\\s*final\\s+char\\s+(" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT +
            ")(\\s*,\\s*" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + ")*\\s*;";
    public final static String CHAR_ASSIGNMENT_PARSER = "\\s*(" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + ")\\s*(\\s*," +
            "\\s*" + NAME_FORMAT + "\\s*=\\s*" + CHAR_FORMAT + "\\s*)*;";
    public final static String CHAR_DECLARATION_ASSIGNMENT_PARSER = "\\s*char\\s+" + NAME_FORMAT + "((\\s*,\\s*" + NAME_FORMAT
            + ")*(\\s*=\\s*" + CHAR_FORMAT + ")?)\\s*(\\s*,\\s*" + NAME_FORMAT + "(\\s*=\\s*" + CHAR_FORMAT + ")?)*;";

}

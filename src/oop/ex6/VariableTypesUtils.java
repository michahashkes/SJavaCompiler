package oop.ex6;

import oop.ex6.main.RegexGlobals;
import oop.ex6.main.Types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oop.ex6.main.RegexGlobals.NAME_FORMAT;
import static oop.ex6.main.Types.*;

public class VariableTypesUtils {


    static public Types deriveTypeFromValue(String value) {
        Matcher m;
        m = Pattern.compile(RegexGlobals.NAME_FORMAT).matcher(value);
        if (m.find())
            return POSSIBLE_VARIABLE;

        m = Pattern.compile(RegexGlobals.INT_FORMAT).matcher(value);
        if (m.find())
            return INT;

        m = Pattern.compile(RegexGlobals.DOUBLE_FORMAT).matcher(value);
        if (m.find())
            return DOUBLE;

        m = Pattern.compile(RegexGlobals.STRING_FORMAT).matcher(value);
        if (m.find())
            return STRING;

        m = Pattern.compile(RegexGlobals.BOOLEAN_FORMAT).matcher(value);
        if (m.find())
            return BOOLEAN;

        m = Pattern.compile(RegexGlobals.CHAR_FORMAT).matcher(value);
        if (m.find())
            return CHAR;

        return POSSIBLE_VARIABLE;
    }

    static public Types deriveTypeFromType(String type) {
        switch (type) {
            case "int":
                return INT;
            case "double":
                return DOUBLE;
            case "String":
                return STRING;
            case "boolean":
                return BOOLEAN;
            case "char":
                return CHAR;
            default:
                return POSSIBLE_VARIABLE;
        }
    }

    static public boolean areValueTypesEqual(Types variableType, Types valueType) {
        if (variableType == valueType)
            return true;
        if (variableType == POSSIBLE_VARIABLE || valueType == POSSIBLE_VARIABLE)
            return true;
        if (variableType == DOUBLE && valueType == INT)
            return true;
        if (variableType == BOOLEAN && (valueType == INT || valueType == DOUBLE))
            return true;
        return false;
    }

    static public boolean isValueVariable(String variableValue) {
        Pattern p = Pattern.compile(NAME_FORMAT);
        Matcher m = p.matcher(variableValue);
        return m.matches();
    }

}

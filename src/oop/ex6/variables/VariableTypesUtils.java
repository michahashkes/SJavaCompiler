package oop.ex6.variables;

import oop.ex6.main.regex.RegexGlobals;
import oop.ex6.main.regex.Types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oop.ex6.main.regex.RegexGlobals.NAME_FORMAT;
import static oop.ex6.main.regex.Types.*;

public class VariableTypesUtils {
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    /*
    check if type derive from another value, return the value if is it.
     */

    static public Types deriveTypeFromValue(String value) {
        Matcher m;
        m = Pattern.compile(RegexGlobals.VARIABLE_NAME_REGEX).matcher(value);
        if (m.matches() && !value.equals(TRUE) && !value.equals(FALSE))
            return POSSIBLE_VARIABLE;

        m = Pattern.compile(RegexGlobals.INT_REGEX).matcher(value);
        if (m.matches())
            return INT;

        m = Pattern.compile(RegexGlobals.DOUBLE_REGEX).matcher(value);
        if (m.matches())
            return DOUBLE;

        m = Pattern.compile(RegexGlobals.STRING_REGEX).matcher(value);
        if (m.matches())
            return STRING;

        m = Pattern.compile(RegexGlobals.BOOLEAN_REGEX).matcher(value);
        if (m.matches())
            return BOOLEAN;

        m = Pattern.compile(RegexGlobals.CHAR_REGEX).matcher(value);
        if (m.matches())
            return CHAR;

        return POSSIBLE_VARIABLE;
    }

    /*
    check if type derive from another type
     */

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
                return UNSUPPORTED_TYPE;
        }
    }

    /*
    Checks for two types if they are equal when double contains int and boolean contains double and int
     */

    static public boolean areValueTypesEqual(Types variableType, Types valueType) {
        if (variableType == valueType)
            return true;
        if (variableType == POSSIBLE_GLOBAL_VARIABLE_UNINITIALIZED ||
                valueType == POSSIBLE_GLOBAL_VARIABLE_UNINITIALIZED)
            return true;
        if (variableType == POSSIBLE_GLOBAL_VARIABLE_INITIALIZED ||
                valueType == POSSIBLE_GLOBAL_VARIABLE_INITIALIZED)
            return true;
        if (variableType == DOUBLE && valueType == INT)
            return true;
        if (variableType == BOOLEAN && (valueType == INT || valueType == DOUBLE))
            return true;
        return false;
    }

    /*
    check if value is variable
     */

    static public boolean isValueVariable(String variableValue) {
        Pattern p = Pattern.compile(NAME_FORMAT);
        Matcher m = p.matcher(variableValue);
        return m.matches() && !variableValue.equals(TRUE) && !variableValue.equals(FALSE);
    }

}

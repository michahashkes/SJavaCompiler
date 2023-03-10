package oop.ex6.main.regex;

import java.util.HashMap;

public class RegexScopes {

    public static final String METHOD_NAME = "([a-zA-Z]+\\w*)";
    public static final String PARAMETERS_TYPES = "(int|double|String|boolean|char)";
    public static final String METHOD_CALL = "\\s*" + METHOD_NAME + "\\s*\\(((\\s*(" +
            RegexGlobals.VARIABLE_NAME_REGEX + "|" + RegexGlobals.BOOLEAN_REGEX + "|" +
            RegexGlobals.CHAR_REGEX + "|" + RegexGlobals.STRING_REGEX + ")(\\s*,\\s*(" +
            RegexGlobals.VARIABLE_NAME_REGEX + "|" + RegexGlobals.BOOLEAN_REGEX + "|" +
            RegexGlobals.CHAR_REGEX + "|" + RegexGlobals.STRING_REGEX + "))*)?\\s*)\\)\\s*;\\s*";
    public static final String METHOD_DECLARATION = "\\s*void\\s+" + METHOD_NAME +
            "\\s*\\(\\s*((final\\s+)?" + PARAMETERS_TYPES +
            "\\s+" + RegexGlobals.VARIABLE_NAME_REGEX + "+(\\s*,\\s*(final\\s+)?" +
            PARAMETERS_TYPES + "\\s+" + RegexGlobals.VARIABLE_NAME_REGEX
            + ")*)?\\s*\\)\\s*\\{\\s*";
    public static final String IF_WHILE_STATEMENT = "\\s*(if|while)\\s*\\((\\s*(" +
            RegexGlobals.VARIABLE_NAME_REGEX + "|" + RegexGlobals.BOOLEAN_REGEX +
            ")\\s*(\\s*(\\|\\||&&)\\s*(" +
            RegexGlobals.VARIABLE_NAME_REGEX + "|" + RegexGlobals.BOOLEAN_REGEX +
            ")\\s*)*)\\)\\s*\\{\\s*";
    public static final String RETURN = "\\s*return\\s*;\\s*";
    public static final String END_SCOPE = "\\s*}\\s*";

    public static final String EMPTY_LINE = "^[\\s]*$";
    public static final String COMMENT = "^\\/\\/.*$";

    public static HashMap<StatementTypes, String> createLineTypesMap() {
        HashMap<StatementTypes, String> hashMap = new HashMap<>();
        hashMap.put(StatementTypes.IF, IF_WHILE_STATEMENT);
        hashMap.put(StatementTypes.WHILE, IF_WHILE_STATEMENT);
        hashMap.put(StatementTypes.METHOD_CALL, METHOD_CALL);
        hashMap.put(StatementTypes.METHOD_DECLARE, METHOD_DECLARATION);
        hashMap.put(StatementTypes.RETURN, RETURN);
        hashMap.put(StatementTypes.END_SCOPE, END_SCOPE);
        hashMap.put(StatementTypes.EMPTY_LINE, EMPTY_LINE);
        hashMap.put(StatementTypes.COMMENT, COMMENT);
        return hashMap;
    }
}

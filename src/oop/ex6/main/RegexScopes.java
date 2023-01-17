package oop.ex6.main;

import java.util.HashMap;

public class RegexScopes {

    public static final String METHOD_NAME = "([a-zA-Z]+\\w*)";
    public static final String PARAMETERS_TYPES = "int|double|String|boolean|char";

    public static final String METHOD_CALL = "\\s*"+METHOD_NAME+"\\s*\\("+RegexGlobals.NAME_FORMAT+"(\\s*,\\s*"+
            RegexGlobals.NAME_FORMAT+")*\\s*\\)\\s*;";
    public static final String METHOD_DECLARATION = "\\s*void\\s+"+METHOD_NAME+"\\s*\\(((final\\s+)?" +PARAMETERS_TYPES+
            "\\s+"+RegexGlobals.NAME_FORMAT+"+(\\s*,\\s*(final\\s+)?"+PARAMETERS_TYPES+"\\s+"+RegexGlobals.NAME_FORMAT
            +")*)?\\)\\s*\\{";
    public static final String IF_STATEMENT = "\\s*if\\s*\\("+RegexGlobals.BOOLEAN_FORMAT+"\\s*(\\s*(\\|\\||&&)"+
            RegexGlobals.BOOLEAN_FORMAT+"\\s*)*\\){";
    public static final String WHILE_STATEMENT = "\\s*while\\s*\\("+RegexGlobals.BOOLEAN_FORMAT+"\\s*(\\s*(\\|\\||&&)"+
            RegexGlobals.BOOLEAN_FORMAT+"\\s*)*\\){";


    public static HashMap<StatementTypes,String> createLineTypesMap() {
        HashMap<StatementTypes,String> hashMap = new HashMap<>();
        hashMap.put(StatementTypes.IF,IF_STATEMENT);
        hashMap.put(StatementTypes.WHILE,WHILE_STATEMENT);
        hashMap.put(StatementTypes.METHOD_CALL,METHOD_CALL);
        hashMap.put(StatementTypes.METHOD_DECLARE,METHOD_DECLARATION);
        return hashMap;
    }
}

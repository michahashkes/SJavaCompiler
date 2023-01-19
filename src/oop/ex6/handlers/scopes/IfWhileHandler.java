package oop.ex6.handlers.scopes;

import oop.ex6.handlers.HandlerInterface;
import oop.ex6.main.regex.Types;
import oop.ex6.method.Method;
import oop.ex6.scopes.ScriptScope;
import oop.ex6.variables.PossibleGlobalVariable;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableTypesUtils;

import java.util.regex.Matcher;

public class IfWhileHandler implements HandlerInterface {

    /**
     * check if the first line of if or while is in correct format
     * @param matcher
     * @return true if line is in the correct format, else otherwise
     */
    public boolean handleIfWhile(Matcher matcher) throws IllegalIfWhileException {
        if (ScriptScope.isInGlobalScope())
            throw new IllegalIfWhileException();
        //replace multiple spaces
        String[] conditions =
                matcher.group(2).replaceAll("\\s*", "").trim().split("\\s*\\|\\||&&\\s*");
        //check boolean condition
        for (String condition : conditions) {
            if (!isConditionValid(condition))
                throw new IllegalIfWhileException();
        }
        //update current scope
        Method currentMethod = ScriptScope.getCurrentMethod();
        currentMethod.addScope();
        return true;
    }

    /**
     * check if the condition is valid
     * @param condition type of string
     * @return true if the condition is valid else otherwise
     */

    private boolean isConditionValid(String condition) {
        Types conditionType = VariableTypesUtils.deriveTypeFromValue(condition);

        if (conditionType == Types.POSSIBLE_VARIABLE) {
            //check scope
            Method currentMethod = ScriptScope.getCurrentMethod();
            Variable variable = currentMethod.getVariable(condition);

            if (variable != null) {
                if (variable.getType() == Types.POSSIBLE_GLOBAL_VARIABLE_INITIALIZED) {
                    // variable exists, but it is probably global variable
                    PossibleGlobalVariable possibleGlobalVariableAssigned =
                            new PossibleGlobalVariable(variable.getName());
                    possibleGlobalVariableAssigned.setInCondition(true);
                    possibleGlobalVariableAssigned.setInitializedLocally(variable.isInitialized());
                    ScriptScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
                    return true;
                }
                return variable.isInitialized() && isVariableTypeValid(variable.getType());
            }

            // could be global variable, check for later
            PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(condition);
            possibleGlobalVariableAssigned.setInCondition(true);
            ScriptScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
            return true;
        }

        return isVariableTypeValid(conditionType);
    }

    static public boolean isVariableTypeValid(Types variableType) {
        return variableType == Types.BOOLEAN || variableType == Types.DOUBLE || variableType == Types.INT;
    }
    /**
     *
     * @param line - line from file
     * @return bool if line is correct , false is not correct
     */

    @Override
    public boolean handleLine(Matcher line) throws IllegalIfWhileException {
       boolean handle =handleIfWhile(line);
        ScriptScope.setLastLineReturn(false);
        return handle;
    }
}

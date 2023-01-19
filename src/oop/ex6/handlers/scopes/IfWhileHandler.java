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

    public boolean handleIfWhile(Matcher matcher) {
        if (ScriptScope.isInGlobalScope())
            return false;

        String[] conditions =
                matcher.group(2).replaceAll("\\s*", "").trim().split("\\s*\\|\\||&&\\s*");

        for (String condition : conditions) {
            if (!isConditionValid(condition))
                return false;
        }
        Method currentMethod = ScriptScope.getCurrentMethod();
        currentMethod.addScope();
        return true;
    }

    private boolean isConditionValid(String condition) {
//        if (!VariableTypesUtils.isValueVariable(condition))
//            return true;

        Types conditionType = VariableTypesUtils.deriveTypeFromValue(condition);

        if (conditionType == Types.POSSIBLE_VARIABLE) {
            Method currentMethod = ScriptScope.getCurrentMethod();
            Variable variable = currentMethod.getVariable(condition);

            if (variable != null) {
                if (variable.getType() == Types.POSSIBLE_GLOBAL_VARIABLE_INITIALIZED) {
                    // variable exists, but it is probably global variable
                    PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(variable.getName());
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

    @Override
    public boolean handleLine(Matcher line) {
       boolean handle =handleIfWhile(line);
        ScriptScope.setLastLineReturn(false);
        return handle;
    }
}

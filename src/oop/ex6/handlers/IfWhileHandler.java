package oop.ex6.handlers;

import oop.ex6.*;
import oop.ex6.main.Types;

import java.util.regex.Matcher;

public class IfWhileHandler {

    public boolean handleIfWhile(Matcher matcher) {
        if (MethodScope.isIsInGlobalScope())
            return false;

        for (int i = 1; i <= matcher.groupCount(); i++) {
            String condition = matcher.group(i);
            if (!isVariableConditionValid(condition))
                return false;
        }
        return true;
    }

    private boolean isVariableConditionValid(String condition) {
        if (!VariableTypesUtils.isValueVariable(condition))
            return true;

        Method currentMethod = MethodScope.getCurrentMethod();
        Variable variable = currentMethod.getVariable(condition);

        if (variable != null) {
            if (variable.getType() == Types.POSSIBLE_VARIABLE) {
                PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(variable.getName());
                possibleGlobalVariableAssigned.setInCondition(true);
                MethodScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
                return true;
            }
            return variable.isInitialized() && isVariableTypeValid(variable.getType());
        }

        PossibleGlobalVariable possibleGlobalVariableAssigned = new PossibleGlobalVariable(condition);
        possibleGlobalVariableAssigned.setInCondition(true);
        MethodScope.addPossibleGlobalVariable(possibleGlobalVariableAssigned);
        return true;
    }

    static public boolean isVariableTypeValid(Types variableType) {
        return variableType == Types.BOOLEAN || variableType == Types.DOUBLE || variableType == Types.INT;
    }
}

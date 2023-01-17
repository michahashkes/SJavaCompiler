package oop.ex6.main;

import oop.ex6.MethodScope;
import oop.ex6.PossibleGlobalVariable;
import oop.ex6.Variable;
import oop.ex6.VariableTypesUtils;
import oop.ex6.handlers.IfWhileHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class Sjavac {
    public Sjavac() {
    }

    private boolean isPossibleGlobalVariableValid(PossibleGlobalVariable possibleGlobalVariable,
                                                  HashMap<String, Variable> globalVariables) {
        if (!globalVariables.containsKey(possibleGlobalVariable.getName()))
            return false;
        Variable globalVariable =  globalVariables.get(possibleGlobalVariable.getName());

        if (possibleGlobalVariable.isInCondition()) {
            return globalVariable.isInitialized() && IfWhileHandler.isVariableTypeValid(globalVariable.getType());
        }

        if (possibleGlobalVariable.isAssigned()) {
            // the possible variable is having something being assigned to it
            if (globalVariable.isFinal())
                return false;

            if (!possibleGlobalVariable.hasType())
                return VariableTypesUtils.areValueTypesEqual(globalVariable.getType(), possibleGlobalVariable.getValueType());

            if (!globalVariables.containsKey(possibleGlobalVariable.getValueName()))
                return false;
            Variable globalVariableAssigned = globalVariables.get(possibleGlobalVariable.getValueName());

            return globalVariableAssigned.isInitialized() && VariableTypesUtils.areValueTypesEqual(globalVariable.getType(),
                    globalVariableAssigned.getType());
        }

        // the possible variable is being assigned to a different variable
        if (!possibleGlobalVariable.hasType())
            return VariableTypesUtils.areValueTypesEqual(possibleGlobalVariable.getValueType(),
                    globalVariable.getType());

        if (!globalVariables.containsKey(possibleGlobalVariable.getValueName()))
            return false;
        Variable globalVariableAssignedTo =
                globalVariables.get(possibleGlobalVariable.getValueName());
        return (!globalVariableAssignedTo.isFinal())
                && VariableTypesUtils.areValueTypesEqual(globalVariableAssignedTo.getType(), globalVariable.getType());
    }

    private boolean checkGlobalVariableValidity() {
        HashMap<String, Variable> globalVariables = MethodScope.getGlobalVariables();
        ArrayList<PossibleGlobalVariable> possibleGlobalVariables = MethodScope.getPossibleGlobalVariables();

        for (PossibleGlobalVariable possibleGlobalVariable : possibleGlobalVariables) {
            if (!isPossibleGlobalVariableValid(possibleGlobalVariable, globalVariables))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}

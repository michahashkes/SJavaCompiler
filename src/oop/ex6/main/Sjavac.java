package oop.ex6.main;

import oop.ex6.MethodScope;
import oop.ex6.PossibleGlobalVariable;
import oop.ex6.Variable;

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

        if (possibleGlobalVariable.isAssigned()) {
            // the possible variable is having something being assigned to it
            if (globalVariable.isFinal())
                return false;

            if (!possibleGlobalVariable.hasType())
                return areValueTypesEqual(globalVariable.getType(), possibleGlobalVariable.getValueType());

            if (!globalVariables.containsKey(possibleGlobalVariable.getValueName()))
                return false;
            Variable globalVariableAssigned = globalVariables.get(possibleGlobalVariable.getValueName());

            return globalVariableAssigned.isInitialized() && areValueTypesEqual(globalVariable.getType(),
                    globalVariableAssigned.getType());
        }

        // the possible variable is being assigned to a different variable
        if (!possibleGlobalVariable.hasType())
            return areValueTypesEqual(possibleGlobalVariable.getValueType(),
                    globalVariable.getType());

        if (!globalVariables.containsKey(possibleGlobalVariable.getValueName()))
            return false;
        Variable globalVariableAssignedTo =
                globalVariables.get(possibleGlobalVariable.getValueName());
        return (!globalVariableAssignedTo.isFinal())
                && areValueTypesEqual(globalVariableAssignedTo.getType(), globalVariable.getType());
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

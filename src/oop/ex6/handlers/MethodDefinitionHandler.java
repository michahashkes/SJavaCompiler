package oop.ex6.handlers;

import oop.ex6.Method;
import oop.ex6.MethodScope;
import oop.ex6.Variable;
import oop.ex6.VariableTypesUtils;
import oop.ex6.main.Types;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MethodDefinitionHandler {

    public boolean handleMethodDefinition(Matcher matcher) {
        if (!MethodScope.isInGlobalScope())
            return false;

        String methodName = matcher.group(1);
        if (MethodScope.getMethods().containsKey(methodName))
            return false;

        String[] methodParameters;
        if (matcher.groupCount() < 2)
            methodParameters = new String[0];
        else
            methodParameters =
                matcher.group(2).replaceAll("\\s+", " ").split("\\s*,\\s*");

        ArrayList<Variable> parameters = new ArrayList<>();

        for (String methodParameter : methodParameters) {
            String[] parameterValues = methodParameter.split(" ");
            boolean isFinal = parameterValues.length == 3;
            Types variableType = VariableTypesUtils.deriveTypeFromType(isFinal ? parameterValues[1] : parameterValues[0]);
            String variableName = isFinal ? parameterValues[2] : parameterValues[1];
            if (isAlreadyParameter(variableName, parameters))
                return false;
            Variable parameterVariable = new Variable(variableType, variableName, false, isFinal);
            parameters.add(parameterVariable);
        }

        Method method = new Method(methodName, parameters);
        MethodScope.addMethod(methodName, method);
        MethodScope.setCurrentMethod(methodName);
        MethodScope.setInGlobalScope(false);
        return true;
    }

    private boolean isAlreadyParameter(String variableName, ArrayList<Variable> parameters) {
        for (Variable variable : parameters) {
            if (variable.getName().equals(variableName))
                return true;
        }
        return false;
    }
}

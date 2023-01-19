package oop.ex6.handlers.scopes;

import oop.ex6.method.Method;
import oop.ex6.handlers.HandlerInterface;
import oop.ex6.scopes.ScriptScope;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableTypesUtils;
import oop.ex6.main.regex.Types;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MethodDefinitionHandler implements HandlerInterface {

    /**
     * check if method definition is valid
     * @param matcher line from file
     * @return true if is valid, else otherwise
     */

    public boolean handleMethodDefinition(Matcher matcher) throws IllegalMethodDefinitionException {
        if (!ScriptScope.isInGlobalScope())
           throw new IllegalMethodDefinitionException();

        String methodName = matcher.group(1);
        if (ScriptScope.getMethods().containsKey(methodName))
            throw new IllegalMethodDefinitionException();

        String[] methodParameters;
        if (matcher.groupCount() < 2 || matcher.group(2) == null)
            methodParameters = new String[0];
        else
            //replace all multiple spaces in one space
            methodParameters =
                matcher.group(2).replaceAll("\\s+", " ").trim().split("\\s*,\\s*");

        ArrayList<Variable> parameters = new ArrayList<>();
        //check method parameters
        for (String methodParameter : methodParameters) {
            String[] parameterValues = methodParameter.split(" ");
            boolean isFinal = parameterValues.length == 3;
            Types variableType = VariableTypesUtils.deriveTypeFromType(
                    isFinal ? parameterValues[1] : parameterValues[0]);
            String variableName = isFinal ? parameterValues[2] : parameterValues[1];
            if (isAlreadyParameter(variableName, parameters))
                throw new IllegalMethodDefinitionException();
            Variable parameterVariable = new Variable(variableType, variableName, true, isFinal);
            parameters.add(parameterVariable);
        }

        //update method information
        Method method = new Method(methodName, parameters);
        ScriptScope.addMethod(methodName, method);
        ScriptScope.setCurrentMethod(methodName);
        ScriptScope.setInGlobalScope(false);
        return true;
    }

    /**
     * check if parameter exist
     * @param variableName
     * @param parameters
     * @return true if is valid, else otherwise
     */

    private boolean isAlreadyParameter(String variableName, ArrayList<Variable> parameters) {
        for (Variable variable : parameters) {
            if (variable.getName().equals(variableName))
                return true;
        }
        return false;
    }

    /**
     * @param line - line from file
     * @return bool if line is correct , false is not correct
     */
    @Override
    public boolean handleLine(Matcher line) throws IllegalMethodDefinitionException {
        boolean handle = handleMethodDefinition(line);
        ScriptScope.setLastLineReturn(false);
        return handle;
    }
}

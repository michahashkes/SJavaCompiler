package oop.ex6.main;

import oop.ex6.handlers.scopes.IfWhileHandler;
import oop.ex6.main.regex.Types;
import oop.ex6.method.Method;
import oop.ex6.method.MethodCall;
import oop.ex6.scopes.ScriptScope;
import oop.ex6.variables.PossibleGlobalVariable;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableTypesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sjavac {

    private final static int SINGLE_FILE_LENGTH = 1;
    private final static String NUMBER_OF_FILES_ERROR = "Usage: need to send a single file to check";
    public Sjavac() {
    }

    private boolean isPossibleGlobalVariableValid(PossibleGlobalVariable possibleGlobalVariable,
                                                  HashMap<String, Variable> globalVariables) {
        if (!globalVariables.containsKey(possibleGlobalVariable.getName()))
            return false;
        Variable globalVariable =  globalVariables.get(possibleGlobalVariable.getName());

        if (possibleGlobalVariable.isInCondition()) {
            return (globalVariable.isInitialized() || possibleGlobalVariable.isInitializedLocally())
                    && IfWhileHandler.isVariableTypeValid(globalVariable.getType());
        }

        if (possibleGlobalVariable.isInMethodCall()) {
            return globalVariable.isInitialized() || possibleGlobalVariable.isInitializedLocally();
        }

        if (possibleGlobalVariable.isAssigned()) {
            // the possible variable is having something being assigned to it
            if (globalVariable.isFinal())
                return false;

            if (possibleGlobalVariable.hasType())
                return VariableTypesUtils.areValueTypesEqual(globalVariable.getType(), possibleGlobalVariable.getValueType());

            if (!globalVariables.containsKey(possibleGlobalVariable.getValueName()))
                return false;
            // variable assigned to it, check if equal
            Variable globalVariableAssigned = globalVariables.get(possibleGlobalVariable.getValueName());

            return globalVariableAssigned.isInitialized() && VariableTypesUtils.areValueTypesEqual(globalVariable.getType(),
                    globalVariableAssigned.getType());
        }

        // the possible variable is being assigned to a different variable
        if (possibleGlobalVariable.hasType())
            return globalVariable.isInitialized() && VariableTypesUtils.areValueTypesEqual(possibleGlobalVariable.getValueType(),
                    globalVariable.getType());

        if (!globalVariables.containsKey(possibleGlobalVariable.getValueName()))
            return false;

        // it's being assigned to variable, check if equal
        Variable globalVariableAssignedTo =
                globalVariables.get(possibleGlobalVariable.getValueName());
        return (!globalVariableAssignedTo.isFinal())
                && VariableTypesUtils.areValueTypesEqual(globalVariableAssignedTo.getType(), globalVariable.getType());
    }

    private boolean checkGlobalVariableValidity() {
        HashMap<String, Variable> globalVariables = ScriptScope.getGlobalVariables();
        ArrayList<PossibleGlobalVariable> possibleGlobalVariables = ScriptScope.getPossibleGlobalVariables();

        for (PossibleGlobalVariable possibleGlobalVariable : possibleGlobalVariables) {
            if (!isPossibleGlobalVariableValid(possibleGlobalVariable, globalVariables))
                return false;
        }
        return true;
    }

    private boolean checkMethodCallsValidity() {
        HashMap<String, MethodCall> methodCalls = ScriptScope.getMethodCalls();
        HashMap<String, Method> methods = ScriptScope.getMethods();
        for (Map.Entry<String, MethodCall> methodCallEntry : methodCalls.entrySet()) {
            if (!isMethodCallValid(methodCallEntry, methods))
                return false;
        }

        for (Method method : methods.values()) {
            if (method.getCurrentScope() != 0 || (!method.isFunctionClosed()))
                return false;
        }

        return true;
    }

    private boolean isMethodCallValid(Map.Entry<String, MethodCall> methodCallEntry, HashMap<String, Method> methods) {
        if (!methods.containsKey(methodCallEntry.getKey()))
            return false;

        MethodCall methodCall = methodCallEntry.getValue();
        Method method = methods.get(methodCallEntry.getKey());
        HashMap<String, Variable> globalVariables = ScriptScope.getGlobalVariables();

        ArrayList<MethodCall.MethodCallArgument> arguments = methodCall.getMethodCallVariables();
        ArrayList<Variable> parameters = method.getParameters();

        if (arguments.size() != parameters.size())
            return false;

        for (int i = 0; i < parameters.size(); i++) {
            MethodCall.MethodCallArgument argument = arguments.get(i);
            Variable parameter = parameters.get(i);
            if (argument.isPrimitive()) {
                if (!VariableTypesUtils.areValueTypesEqual(parameter.getType(), argument.getType()))
                    return false;
            } else {
                if (!globalVariables.containsKey(argument.getName()))
                    return false;
                Variable globalVariable = globalVariables.get(argument.getName());
                if (!VariableTypesUtils.areValueTypesEqual(parameter.getType(), globalVariable.getType()))
                    return false;
                if ((!globalVariable.isInitialized()) &&
                        argument.getType() == Types.POSSIBLE_GLOBAL_VARIABLE_UNINITIALIZED)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        ScriptScope.startScriptScope();
        try {
            if(args.length != SINGLE_FILE_LENGTH){
                throw new IncorrectNumberOfFilesException(NUMBER_OF_FILES_ERROR);
            }
            Parser parser = new Parser(args[0]);
            parser.readFile();
        }
        catch (IncorrectNumberOfFilesException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println(2);
            return;
        }catch (FileFormatException | RuntimeException e) {
           System.err.println(e.getMessage());
           System.out.println(1);
           return;
        }

        Sjavac sjavac = new Sjavac();
        if (!sjavac.checkGlobalVariableValidity()) {
            System.out.println(1);
            return;
        }
        if (!sjavac.checkMethodCallsValidity()) {
            System.out.println(1);
            return;
        }
        System.out.println(0);


    }
}

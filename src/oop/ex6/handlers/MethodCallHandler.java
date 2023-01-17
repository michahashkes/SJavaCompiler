package oop.ex6.handlers;

import oop.ex6.Method;
import oop.ex6.MethodScope;
import oop.ex6.Variable;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MethodCallHandler {

    public boolean handleMethodCall(Matcher matcher) {
        if (MethodScope.isInGlobalScope())
            return false;

        String callMethodName = matcher.group(1);

        String[] methodArguments;
        if (matcher.groupCount() < 2 || matcher.group(2) == null)
            methodArguments = new String[0];
        else
            methodArguments =
                    matcher.group(2).replaceAll("\\s+", " ").split("\\s*,\\s*");

        Method currentMethod = MethodScope.getCurrentMethod();
        for (String argument : methodArguments) {

        }
        return true;
    }

}

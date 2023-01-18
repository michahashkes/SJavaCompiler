package oop.ex6.handlers;

import oop.ex6.MethodScope;

import java.util.regex.Matcher;

public class ReturnHandler {

    public boolean handleReturn(Matcher matcher) {
        return (!MethodScope.isInGlobalScope());
    }

}

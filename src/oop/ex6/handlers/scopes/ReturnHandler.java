package oop.ex6.handlers.scopes;

import oop.ex6.handlers.HandlerInterface;
import oop.ex6.scopes.ScriptScope;

import java.util.regex.Matcher;

public class ReturnHandler implements HandlerInterface {

    public boolean handleReturn(Matcher matcher) {
        return (!ScriptScope.isInGlobalScope());
    }

    @Override
    public boolean handleLine(Matcher line) {
        ScriptScope.setLastLineReturn(true);
        return handleReturn(line);
    }
}

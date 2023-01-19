package oop.ex6.handlers.scopes;

import oop.ex6.method.Method;
import oop.ex6.handlers.HandlerInterface;
import oop.ex6.scopes.ScriptScope;

import java.util.regex.Matcher;

public class EndScopeHandler implements HandlerInterface {
    /*
    handle with the end of scope check if we are in suitebale
     */
    public boolean handleEndScope() {
        if (ScriptScope.isInGlobalScope())
            return false;

        Method currentMethod = ScriptScope.getCurrentMethod();
        if (currentMethod.getCurrentScope() == 0) {
            if (!ScriptScope.isLastLineReturn())
                return false;
            ScriptScope.setInGlobalScope(true);
            currentMethod.setFunctionClosed(true);
            return true;
        }

        currentMethod.removeScope();
        return true;
    }

    @Override
    public boolean handleLine(Matcher line) {
        boolean handle = handleEndScope();
        ScriptScope.setLastLineReturn(false);
        return handle;
    }
}

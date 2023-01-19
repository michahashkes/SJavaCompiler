package oop.ex6.handlers.scopes;

import oop.ex6.method.Method;
import oop.ex6.handlers.HandlerInterface;
import oop.ex6.scopes.ScriptScope;

import java.util.regex.Matcher;

public class EndScopeHandler implements HandlerInterface {

    /**
     * check if the } is in the correct scope and update scope
     * @return true if the line is correct
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

    /**
     * @param line - line from file
     * @return bool if line is correct , false is not correct
     */

    @Override
    public boolean handleLine(Matcher line) {
        boolean handle = handleEndScope();
        ScriptScope.setLastLineReturn(false);
        return handle;
    }
}

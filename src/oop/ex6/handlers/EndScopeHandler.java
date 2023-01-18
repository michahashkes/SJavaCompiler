package oop.ex6.handlers;

import oop.ex6.Method;
import oop.ex6.MethodScope;

public class EndScopeHandler {

    public boolean handleEndScope() {
        if (MethodScope.isInGlobalScope())
            return false;

        Method currentMethod = MethodScope.getCurrentMethod();
        if (currentMethod.getCurrentScope() == 0) {
            if (!MethodScope.isLastLineReturn())
                return false;
            MethodScope.setInGlobalScope(true);
            currentMethod.setFunctionClosed(true);
            return true;
        }

        currentMethod.removeScope();
        return true;
    }

}

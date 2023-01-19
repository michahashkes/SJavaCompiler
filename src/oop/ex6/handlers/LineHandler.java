package oop.ex6.handlers;

import oop.ex6.main.regex.StatementTypes;

import java.util.regex.Matcher;


public class LineHandler {


    public boolean handleLine(StatementTypes statementTypes, Matcher line) {
        HandlersFactory handlersFactory = new HandlersFactory();
        HandlerInterface handle = handlersFactory.Handlers(statementTypes);
        if (handle == null)
            return true;
        return handle.handleLine(line);
    }

}

package oop.ex6.handlers;

import java.util.regex.Matcher;

public interface HandlerInterface {
    /**
     *
     * @param line - line from file
     * @return bool if line is correct , false is not correct
     */
    public boolean handleLine(Matcher line);
}

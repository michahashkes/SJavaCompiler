package oop.ex6.variables;

import oop.ex6.main.regex.Types;

public class Variable {
    private final Types type;
    private final String name;
    private boolean isInitialized;
    private final boolean isFinal;

    /**
     * variable class
     * @param type variable type
     * @param name variable name
     * @param isInitialized is it assigned
     * @param isFinal is it final
     */
    public Variable(Types type, String name,
                    boolean isInitialized, boolean isFinal) {
        this.type = type;
        this.name = name;
        this.isInitialized = isInitialized;
        this.isFinal = isFinal;
    }

    public String getName() {
        return name;
    }

    public Types getType() {
        return type;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setInitialized() {
        isInitialized = true;
    }

}

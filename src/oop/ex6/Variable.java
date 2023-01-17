package oop.ex6;

public class Variable {
    private final String type;
    private final String name;
    private boolean isInitialized;
    private final boolean isFinal;
    private final int scope;

    public Variable(String type, String name,
                    boolean isInitialized, boolean isFinal) {
        this.type = type;
        this.name = name;
        this.isInitialized = isInitialized;
        this.isFinal = isFinal;
        this.scope = 0;
    }

    public String getName() {
        return name;
    }

    public String getType() {
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

    public int getScope() {
        return scope;
    }
}

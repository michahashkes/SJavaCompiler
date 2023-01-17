package oop.ex6;

public class PossibleGlobalVariable {
    private String name;
    private boolean hasType = false;
    private boolean isAssigned = false;
//    private boolean isAssignedTo = false;
    private String valueType = "";
    private String valueName = "";

    public PossibleGlobalVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public boolean hasType() {
        return hasType;
    }

    public void setHasType(boolean hasType) {
        this.hasType = hasType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}

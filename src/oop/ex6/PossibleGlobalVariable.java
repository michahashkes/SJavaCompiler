package oop.ex6;

import oop.ex6.main.Types;

public class PossibleGlobalVariable {
    private String name;
    private boolean hasType = false;
    private boolean isAssigned = false;
    private boolean isInCondition = false;
    private Types valueType;
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

    public boolean isInCondition() {
        return isInCondition;
    }

    public void setInCondition(boolean inCondition) {
        isInCondition = inCondition;
    }

    public Types getValueType() {
        return valueType;
    }

    public void setValueType(Types valueType) {
        this.valueType = valueType;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}

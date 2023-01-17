package oop.ex6;

public class PossibleGlobalVariable {
    private String name;
    private boolean isUsed = false;
    private boolean isAssigned = false;
    private boolean isAssignedTo = false;
    private String typeAssigned = "";
    private String variableNameAssigned = "";
    private String typeAssignedTo = "";
    private String variableNameAssignedTo = "";

    public PossibleGlobalVariable(String name) {
        this.name = name;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public String getTypeAssignedTo() {
        return typeAssignedTo;
    }

    public void setTypeAssignedTo(String typeAssignedTo) {
        this.typeAssignedTo = typeAssignedTo;
    }

    public String getVariableNameAssignedTo() {
        return variableNameAssignedTo;
    }

    public void setVariableNameAssignedTo(String variableAssignedTo) {
        this.variableNameAssignedTo = variableAssignedTo;
    }

    public boolean isAssignedTo() {
        return isAssignedTo;
    }

    public void setAssignedTo(boolean assignedTo) {
        isAssignedTo = assignedTo;
    }

    public String getTypeAssigned() {
        return typeAssigned;
    }

    public void setTypeAssigned(String typeAssigned) {
        this.typeAssigned = typeAssigned;
    }

    public String getVariableNameAssigned() {
        return variableNameAssigned;
    }

    public void setVariableNameAssigned(String variableNameAssigned) {
        this.variableNameAssigned = variableNameAssigned;
    }
}

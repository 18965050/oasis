package cn.xyz.chaos.jakarta.common.model;

public class Character {

    private String name;
    private String description;
    private boolean protagonist;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean getProtagonist() {
        return protagonist;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public void setName(String pName) {
        name = pName;
    }

    public void setProtagonist(boolean pProtagonist) {
        protagonist = pProtagonist;
    }

}

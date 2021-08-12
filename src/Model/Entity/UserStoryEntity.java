package Model.Entity;

import Enum.StatusEnum;
import java.io.Serializable;

public class UserStoryEntity implements Comparable<UserStoryEntity>, Serializable {
    // Author: Robin Steinwarz
    private int id;
    private String title;
    private int risk;
    private int value;
    private int penalty;
    private int effort;
    private double priority;
    private StatusEnum status;

    public UserStoryEntity(int id, String title, int risk, int value, int penalty, int effort){
        this.id = id;
        this.title = title;
        this.risk = risk;
        this.value = value;
        this.penalty = penalty;
        this.effort = effort;

        priority = calculatePriority();
    }

    public void setStatus(StatusEnum status){
        this.status = status;
    }

    private double calculatePriority(){
        return (double) (value + penalty) / (effort + risk);
    }

    public String toString(){
        String story = "";
        story += "ID: " + this.id;
        story += " Description: \"" + this.title + "\"";
        story += " Risk: " + this.risk;
        story += " Value: " + this.value;
        story += " Penalty: " + this.penalty;
        story += " Effort: " + this.effort;
        story += " Priority: " + this.priority;
        story += " Status: " + ((this.status != null) ? this.status : "-");

        return story;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
        priority = calculatePriority();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        priority = calculatePriority();
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
        priority = calculatePriority();
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
        priority = calculatePriority();
    }

    public StatusEnum getStatus() {
        return status;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(UserStoryEntity userStory) {

        if ( userStory.getPriority() == this.getPriority() ) {
            return 0;
        }

        if ( userStory.getPriority() > this.getPriority() ) {
            return 1;
        }

        else return -1;
    }
}


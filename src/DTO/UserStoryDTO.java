package DTO;

import Model.Entity.UserStoryEntity;
import Enum.StatusEnum;

public class UserStoryDTO implements Comparable<UserStoryDTO>{
    // Author: Robin Steinwarz

    private int id;
    private String title;
    private double priority;
    private StatusEnum status;

    public UserStoryDTO(UserStoryEntity story){
        id = story.getID();
        title = story.getTitle();
        priority = story.getPriority();
        status = story.getStatus();
    }

    @Override
    public int compareTo(UserStoryDTO userStoryDTO) {

        if ( userStoryDTO.getPriority() == this.getPriority() ) {
            return 0;
        }

        if ( userStoryDTO.getPriority() > this.getPriority() ) {
            return 1;
        }

        else return -1;
    }

    public String toString(){
        String dto = "";
        dto += "ID: " + this.id;
        dto += " Short Description: \"" + this.title + "\"";
        dto += " Priority: " + this.priority;
        dto += " Status: " + this.status;

        return dto;
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

    public double getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}

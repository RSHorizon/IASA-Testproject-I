package Model.Entity;

import java.io.Serializable;

public class ActorEntity implements Serializable {
    // Author: Robin Steinwarz
    private String name;

    public ActorEntity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

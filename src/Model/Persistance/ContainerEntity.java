package Model.Persistance;

import DTO.UserStoryDTO;
import Model.Entity.ActorEntity;
import Model.Entity.UserStoryEntity;
import Enum.NoteTypeEnum;
import Exception.ContainerException;
import View.NotificationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContainerEntity implements Serializable {
    // Author: Robin Steinwarz
    private static ContainerEntity container;

    public static synchronized ContainerEntity getContainer(){
        if(container == null){
            container = new ContainerEntity();
        }

        return container;
    }

    private List<UserStoryEntity> stories = new ArrayList<UserStoryEntity>();
    private List<ActorEntity> actors = new ArrayList<ActorEntity>();

    private ContainerEntity(){}

    public void addStory(UserStoryEntity story) throws ContainerException {

        for(int i = 0; i < stories.size(); i++){
            if(story.getID() == stories.get(i).getID()){
                throw new ContainerException("The given UserStory with id: "+story.getID()+" exists already");
            }
        }

        stories.add(story);
    }

    public UserStoryEntity getStory(int id) throws ContainerException {
        for(int i = 0; i < stories.size(); i++){
            if(id == stories.get(i).getID()){
                return stories.get(i);
            }
        }

        throw new ContainerException("The UserStory with id: "+id+" does not exist");
    }

    public void removeStory(int id){

        for(int i = 0; i < stories.size(); i++){
            if(stories.get(i).getID() == id){
                stories.remove(i);
                return;
            }
        }

        NotificationView.notify(NoteTypeEnum.info, "The UserStory with the id: \"+id+\" did not exist and could not be deleted");
    }

    public void addActor(ActorEntity actor){
        actors.add(actor);
    }

    public void removeActor(String name){
        for(int i = 0; i < actors.size(); i++){
            if(actors.get(i).getName().equals(name)){
                actors.remove(i);
            }
        }
    }

    public boolean hasActor(String name){

        Optional<ActorEntity> actor = actors.stream().filter(c -> c.getName().equals(name)).findFirst();
        if(actor.isPresent()){
            return true;
        }

        return false;
    }

    public List<ActorEntity> getActors(){
        return actors;
    }

    public void setContainer(ContainerEntity container){
        ContainerEntity.container = container;
    }

    public int size(){
        return stories.size();
    }

    public List<UserStoryEntity> getUserStories(){
        return this.stories;
    }

    public List<UserStoryDTO> getUserStoriesAsListOfDTOs(){
        return stories.stream().map(UserStoryDTO::new).collect(Collectors.toList());
    }

}

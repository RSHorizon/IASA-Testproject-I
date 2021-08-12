package Command;

import Model.Entity.ActorEntity;
import Enum.NoteType;
import Model.Persistance.ContainerEntity;
import View.NotificationView;

public class AddElementCommand extends AbstractCommand {
    // Author: Robin Steinwarz

    public AddElementCommand(){
        super.undoable = true;
    }

    private String reverseActor = null;

    @Override
    public void execute(String parameter) {

        String[] parameters = parameter.trim().split(" ");

        if(parameter.trim().equals("") || parameters.length != 2 || !parameters[0].equals("-actor")){
            NotificationView.notify(NoteType.fail,"The format for command addElement is wrong.");
            NotificationView.notify(NoteType.info,"Please use the following format: addElement -actor \'Actor\'");
            return;
        }

        if(ContainerEntity.getContainer().hasActor(parameters[1])){
            NotificationView.notify(NoteType.info,"Actor " + parameters[1] + " already defined.");
            return;
        }

        ActorEntity newActor = new ActorEntity(parameters[1]);
        ContainerEntity.getContainer().addActor(newActor);
        this.reverseActor = newActor.getName();
        NotificationView.notify(NoteType.info,"Actor " + parameters[1] + " successfully added.");
    }

    @Override
    public void inverse() {
        if(reverseActor != null){
            ContainerEntity.getContainer().removeActor(reverseActor);
        }
    }

    @Override
    public String getHelp() {
        return "addElement     -actor \'Actor\'";
    }
}

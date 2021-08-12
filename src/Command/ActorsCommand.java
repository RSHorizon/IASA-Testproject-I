package Command;

import Model.Entity.ActorEntity;
import Enum.NoteTypeEnum;
import Model.Persistance.ContainerEntity;
import View.NotificationView;

import java.util.List;

public class ActorsCommand extends AbstractCommand {
    // Author: Robin Steinwarz
    @Override
    public void execute(String parameter) {
        if(!parameter.equals("")){
            NotificationView.notify(NoteTypeEnum.info, "The command actors has no parameters");
        }

        List<ActorEntity> actors = ContainerEntity.getContainer().getActors();
        if(actors.size() == 0){
            System.out.println("No actors defined.");
            return;
        }
        actors.stream().forEach(c -> System.out.println(" " + c.getName()));
    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "actors";
    }
}

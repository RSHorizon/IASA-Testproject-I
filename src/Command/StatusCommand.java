package Command;

import Exception.ContainerException;
import Enum.NoteTypeEnum;
import Enum.StatusEnum;
import Model.Persistance.ContainerEntity;
import View.NotificationView;

public class StatusCommand extends AbstractCommand {
    // Author: Robin Steinwarz
    @Override
    public void execute(String parameter) {

        String[] parameters = parameter.trim().split(" ");

        if(parameters.length != 2){
            NotificationView.notify(NoteTypeEnum.fail,"The given parameters did not match");
            NotificationView.notify(NoteTypeEnum.info,"Please use the following format: status \'UserStoryID\' \'todo|progress|done\' ");
        }

        int id = -1;

        try{
            id = Integer.parseInt(parameters[0]);
        }catch (NumberFormatException e){
            NotificationView.notify(NoteTypeEnum.fail,"Please enter a valid number as UserStoryID, which is not negative");
            NotificationView.notify(NoteTypeEnum.info,"Please use the following format: status \'UserStoryID\' \'todo|progress|done\' ");
            return;
        }

        StatusEnum status = StatusEnum.valueOf(parameters[1]);

        try {
            ContainerEntity.getContainer().getStory(id).setStatus(status);
        } catch (ContainerException e) {
            e.printStackTrace();
            return;
        }
        NotificationView.notify(NoteTypeEnum.info, "Successfully set status " + status + " for UserStory " + id);

    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "status         \'UserStoryID\' \'todo|progress|done\'";
    }
}

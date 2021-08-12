package Command;

import Enum.NoteTypeEnum;
import Model.Persistance.ContainerEntity;
import Model.Persistance.ContainerAccess;
import View.ConsoleView;
import View.NotificationView;

public class LoadCommand extends AbstractCommand {
    // Author: Robin Steinwarz
    @Override
    public void execute(String parameter) {

        if(parameter.trim() != "" && !parameter.contains("\"")){
            NotificationView.notify(NoteTypeEnum.fail,"No file path was submitted as parameter");
            NotificationView.notify(NoteTypeEnum.info,"Please use the following format: load \"filepath\"");
            NotificationView.notify(NoteTypeEnum.info,"It seems like you don't use \" when using the command.");
            return;
        }

        if(parameter.trim() == ""){
            NotificationView.notify(NoteTypeEnum.fail,"No file path was submitted as parameter");
            NotificationView.notify(NoteTypeEnum.info,"Please use the following format: load \"\'filepath\'\"");
            return;
        }

        NotificationView.notify(NoteTypeEnum.command,"Are you sure to load a saved container? Current unsaved UserStories will be overwritten");
        NotificationView.notify(NoteTypeEnum.command,"Please type y to continue, or anything else to abort");

        ConsoleView console = new ConsoleView();
        String answere = console.readLine();

        if(!answere.equals("y")){
            NotificationView.notify(NoteTypeEnum.info, "Abort");
            return;
        }

        String file = parameter.replace("\""," ");

        ContainerEntity loaded = ContainerAccess.load(file);

        if(loaded == null){
            NotificationView.notify(NoteTypeEnum.fail,"The requested container path does not exist");
            return;
        }

        ContainerEntity.getContainer().setContainer(loaded);
        NotificationView.notify(NoteTypeEnum.info,"Container successfully loaded");
    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "load           \"\'filepath\'\"";
    }
}

package Command;

import Enum.NoteType;
import Model.Persistance.ContainerEntity;
import Model.Entity.UserStoryEntity;
import Exception.ContainerException;
import View.NotificationView;

public class EnterCommand extends AbstractCommand {
    // Author: Robin Steinwarz

    public EnterCommand(){
        undoable = true;
    }

    private int addedID = -1;

    @Override
    public void execute(String parameter) {

        if(parameter.trim() != "" && !parameter.contains("\"")){
            NotificationView.notify(NoteType.fail,"The given parameter list did not match for a user story creation");
            NotificationView.notify(NoteType.info,"Please use the following format: enter \'UserStoryID\' \"\'short description\'\" \'risk\' \'value\' \'penalty\' \'effort\'");
            NotificationView.notify(NoteType.info, "Als \'Akteur\' [mit der Rolle \'Rolle\'] möchte ich \'Story\'[, um|damit \'Mehrwert\']");
            NotificationView.notify(NoteType.info,"Use the characters \" in \"short description\" otherwise your command won't be executed properly");
            return;
        }

        String[] parameters = parameter.split("\"");

        if(parameters.length != 3 || parameters[0].trim().equals("")){

            NotificationView.notify(NoteType.fail,"The given parameter list did not match for a user story creation");
            NotificationView.notify(NoteType.info,"Please use the following format: enter \'UserStoryID\' \"\'short description\'\" \'risk\' \'value\' \'penalty\' \'effort\'");
            NotificationView.notify(NoteType.info, "Als \'Akteur\' [mit der Rolle \'Rolle\'] möchte ich \'Story\'[, um|damit \'Mehrwert\']");
            return;
        }

        for(int i = 0; i < parameters.length; i++){
            parameters[i] = parameters[i].trim();
        }

        String[] gloeger = parameters[2].split(" ");

        if(gloeger.length != 4){
            NotificationView.notify(NoteType.fail,"The given parameter list did not match for a user story creation");
            NotificationView.notify(NoteType.info,"Please use the following format: enter \'UserStoryID\' \"\'short description\'\" \'risk\' \'value\' \'penalty\' \'effort\'");
            NotificationView.notify(NoteType.info, "Als \'Akteur\' [mit der Rolle \'Rolle\'] möchte ich \'Story\'[, um|damit \'Mehrwert\']");
            return;
        }

        int id;
        String title = parameters[1];
        int risk;
        int value;
        int penalty;
        int effort;

        try {
            id = Integer.parseInt(parameters[0]);
            risk = Integer.parseInt(gloeger[0]);
            value = Integer.parseInt(gloeger[1]);
            penalty = Integer.parseInt(gloeger[2]);
            effort = Integer.parseInt(gloeger[3]);
        }catch(NumberFormatException e){
            NotificationView.notify(NoteType.fail,"The format for id or risk or penalty or value or effort is wrong");
            NotificationView.notify(NoteType.info,"Please use valid numbers like 0,1,73,9999. (MaxNumber: 2147483647)");
            return;
        }

        if(id < 0){
            NotificationView.notify(NoteType.fail,"The format for id is wrong");
            NotificationView.notify(NoteType.info,"Please make sure that the id number is not negative");
            return;
        }

        if(risk < 1 || penalty < 1 || value < 1 || risk > 5 || penalty > 5 || value > 5){
            NotificationView.notify(NoteType.fail,"The format for risk|penalty|value is wrong");
            NotificationView.notify(NoteType.info,"Please use the following format: 1-5");
            return;
        }

        int[] effortPossibillities = new int[]{0, 1, 2, 3, 5, 8, 13, 20,40,100};
        boolean effortPossible = false;
        for(int i = 0; i < effortPossibillities.length; i++){
            if(effort == effortPossibillities[i]){
                effortPossible = true;
            }
        }

        if(!effortPossible){
            NotificationView.notify(NoteType.fail,"The effort you entered did not match the possible values");
            NotificationView.notify(NoteType.info,"Please pick on of these: 0, 1, 2, 3, 5, 8, 13, 20, 40, 100");
            return;
        }

        UserStoryEntity newStory = new UserStoryEntity(id,title,risk,value,penalty,effort);
        try {
            ContainerEntity.getContainer().addStory(newStory);
        } catch (ContainerException e) {
            e.printStackTrace();
            return;
        }

        NotificationView.notify(NoteType.info,"Successfully created UserStory");
        NotificationView.notify(NoteType.info, newStory.toString());

        addedID = id;
    }

    @Override
    public void inverse() {
        if(addedID != -1){
            ContainerEntity.getContainer().removeStory(addedID);
        }
    }

    @Override
    public String getHelp() {
        return "enter          \'UserStoryID\' \"\'short description\'\" \'risk\' \'value\' \'penalty\' \'effort\'";
    }
}

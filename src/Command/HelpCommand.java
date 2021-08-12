package Command;

import Enum.NoteTypeEnum;
import Enum.ViableCommandsEnum;
import View.NotificationView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class HelpCommand extends AbstractCommand {
    // Author: Robin Steinwarz

    @Override
    public void execute(String parameter) {
        if(parameter != ""){
            NotificationView.notify(NoteTypeEnum.info,"No parameters known for help command");
        }

        NotificationView.notify(NoteTypeEnum.command,"Prio Tool commands:");
        ViableCommandsEnum[] commands = ViableCommandsEnum.values();
        for( int i = 0; i < commands.length; i++){
                try {
                    String firstLetter = commands[i].toString().substring(0, 1).toUpperCase();
                    String body = commands[i].toString().substring(1);

                    // ich nutze die java reflection api, um ein switch case für kommandos zu vermeiden
                    Class commandClass = Class.forName("Command." + firstLetter + body + "Command");
                    Constructor con = commandClass.getConstructor();

                    // klasse wurde gefunden, deshalb ist es definitiv ein AbstractCommand
                    CommandInterface command = (CommandInterface) con.newInstance();
                    System.out.println(command.getHelp());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "help";
    }
}

package Controller;

import java.lang.reflect.*;

import Command.AbstractCommand;
import Command.CommandHistory;
import Enum.NoteType;
import Enum.ViableCommands;
import View.NotificationView;

public class CommandController {
    // Author: Robin Steinwarz

    public void executeLine(String line) {

        if(line == null){
            NotificationView.notify(NoteType.fail, "No Command was passed");
            return;
        }

        String[] splitted = line.split(" ");
        String head = line.split(" ")[0];

        splitted[0] = "";
        String parameters = String.join(" ", splitted).trim();

        // überprüfe ob der befehl bekannt ist
        ViableCommands[] commands = ViableCommands.values();
        for( int i = 0; i < commands.length; i++){
            if(head.equals(commands[i].toString())){
                // bekannt
                try {
                    String firstLetter = commands[i].toString().substring(0, 1).toUpperCase();
                    String body = commands[i].toString().substring(1);

                    // ich nutze die java reflection api, um ein switch case für kommandos zu vermeiden
                    Class commandClass = Class.forName("Command." + firstLetter + body + "Command");
                    Constructor con = commandClass.getConstructor();

                    // klasse wurde gefunden, deshalb ist es definitiv ein AbstractCommand
                    AbstractCommand command = (AbstractCommand) con.newInstance();
                    command.execute(parameters);
                    CommandHistory.getInstance().addCommand(command);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                return;
            }
        }

        // nicht bekannt
        NotificationView.notify(NoteType.fail,"Command could not be found, please try again. (enter 'help' for a command list)");
    }
}

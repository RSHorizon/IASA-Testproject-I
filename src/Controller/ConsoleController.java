package Controller;

import Enum.NoteTypeEnum;
import View.ConsoleView;
import View.NotificationView;

public class ConsoleController {
    // Author: Robin Steinwarz

    public static boolean stop = false;

    public void Start() {
        NotificationView.notify(NoteTypeEnum.info, "PRIO TOOL STARTED");
        System.out.println();

        NotificationView.notify(NoteTypeEnum.info, "Please enter UserStories in the following format: (Cohn 2004)  [X] x optional, \'X\' displace x, a|b a or b");
        NotificationView.notify(NoteTypeEnum.info, "Als \'Akteur\' [mit der Rolle \'Rolle\'] möchte ich \'Story\'[, um|damit \'Mehrwert\']");
        System.out.println();

        CommandController controller = new CommandController();
        ConsoleView con = new ConsoleView();

        NotificationView.notify(NoteTypeEnum.info, "Enter help, to view viable commands");
        while(!stop){
            NotificationView.notify(NoteTypeEnum.command,"Please enter command:");

            String line = con.readLine();

            controller.executeLine(line);
        }

    }
}

package Controller;

import Enum.NoteType;
import View.ConsoleView;
import View.NotificationView;

public class ConsoleController {
    // Author: Robin Steinwarz

    public static boolean stop = false;

    public void Start() {
        NotificationView.notify(NoteType.info, "PRIO TOOL STARTED");
        System.out.println();

        NotificationView.notify(NoteType.info, "Please enter UserStories in the following format: (Cohn 2004)  [X] x optional, \'X\' displace x, a|b a or b");
        NotificationView.notify(NoteType.info, "Als \'Akteur\' [mit der Rolle \'Rolle\'] möchte ich \'Story\'[, um|damit \'Mehrwert\']");
        System.out.println();

        CommandController controller = new CommandController();
        ConsoleView con = new ConsoleView();

        NotificationView.notify(NoteType.info, "Enter help, to view viable commands");
        while(!stop){
            NotificationView.notify(NoteType.command,"Please enter command:");

            String line = con.readLine();

            controller.executeLine(line);
        }

    }
}

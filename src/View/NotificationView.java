package View;

import Enum.NoteType;

public class NotificationView {
    // Author: Robin Steinwarz
    public static void notify(NoteType type, String message){
        if(type == NoteType.command){
            System.out.println("> " + message);
        }else if(type == NoteType.info){
            System.out.println("[INFO] " + message);
        }else if(type == NoteType.fail){
            System.out.println("[FAIL] " + message);
        }
    }
}

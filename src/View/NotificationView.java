package View;

import Enum.NoteTypeEnum;

public class NotificationView {
    // Author: Robin Steinwarz
    public static void notify(NoteTypeEnum type, String message){
        if(type == NoteTypeEnum.command){
            System.out.println("> " + message);
        }else if(type == NoteTypeEnum.info){
            System.out.println("[INFO] " + message);
        }else if(type == NoteTypeEnum.fail){
            System.out.println("[FAIL] " + message);
        }
    }
}

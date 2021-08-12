package Model.Persistance;

import Enum.NoteTypeEnum;
import View.NotificationView;

import java.io.*;

public class ContainerAccess {
    // Author: Robin Steinwarz

    public static ContainerEntity load(String file) {
        FileInputStream fileInput = null;
        ObjectInputStream inputStream = null;
        Object container = null;

        try {
            fileInput = new FileInputStream(file.trim() + "data");
            inputStream = new ObjectInputStream(fileInput);

            container = inputStream.readObject();
        }catch(IOException e){
            NotificationView.notify(NoteTypeEnum.fail, "Failed to load Container");
            return null;
        }catch( ClassNotFoundException e){
            NotificationView.notify(NoteTypeEnum.fail, "Pfad wurde nicht gefunden");
            return null;
        }finally {
            if (fileInput != null) try {
                fileInput.close();
            } catch (IOException e) {}
            if (inputStream != null) try {
                inputStream.close();
            } catch (IOException e) {}
        }

        if(container instanceof ContainerEntity){
            return (ContainerEntity) container;
        }

        return null;
    }

    public static void store(String file, ContainerEntity container) {
        FileOutputStream fileOutput = null;
        ObjectOutputStream outputStream = null;

        try {
            fileOutput = new FileOutputStream(file.trim() + "data");
            outputStream = new ObjectOutputStream(fileOutput);

            outputStream.writeObject(container);
        }catch(IOException e){
            NotificationView.notify(NoteTypeEnum.fail, "Failed to store Container");
            return;
        }finally {
            if (fileOutput != null) try {
                fileOutput.close();
            } catch (IOException e) {}
            if (outputStream != null) try {
                outputStream.close();
            } catch (IOException e) {}
        }
    }
}

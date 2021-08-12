package View;

import java.util.List;

public class QualityDialogView {
    // Author: Robin Steinwarz
    public void display(int storyID, double quality, String descriptor, List<String> details, List<String> hints){
        System.out.println("Die User Story mit der ID "+storyID+" hat folgende Qualität: ");
        System.out.println("" + quality * 100 + "% (" + descriptor + ")");
        System.out.println();

        if(details.size() != 0){
            System.out.println("Details:");
            details.stream().forEach(System.out::println);
            System.out.println();

            if(hints.size() != 0){
                System.out.println("Hints:");
                hints.stream().forEach(System.out::println);
            }
        }

    }

    public void display(int storyCount, double avgQuality, String descriptor){
        System.out.println("Ihre "+storyCount+" User Stories haben durchschnittlich folgende Qualität:");
        System.out.println("" + avgQuality * 100 + "% (" + descriptor + ")");
    }
}

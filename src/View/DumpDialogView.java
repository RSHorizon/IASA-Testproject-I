package View;

import DTO.UserStoryDTO;

import java.util.List;

public class DumpDialogView implements IDialog {
    // Author: Robin Steinwarz
    @Override
    public void display(List<UserStoryDTO> stories) {
        stories.forEach(System.out::println);
    }
}

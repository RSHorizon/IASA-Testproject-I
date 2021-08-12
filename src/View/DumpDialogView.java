package View;

import DTO.UserStoryDTO;

import java.util.List;

public class DumpDialogView implements DialogInterface {
    // Author: Robin Steinwarz
    @Override
    public void display(List<UserStoryDTO> stories) {
        stories.forEach(System.out::println);
    }
}

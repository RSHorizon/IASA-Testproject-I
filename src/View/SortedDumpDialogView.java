package View;

import DTO.UserStoryDTO;

import java.util.List;

public class SortedDumpDialogView implements DialogInterface {
    // Author: Robin Steinwarz
    @Override
    public void display(List<UserStoryDTO> stories) {
        stories.stream().sorted(UserStoryDTO::compareTo).forEach(System.out::println);
    }
}

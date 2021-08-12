package View;

import DTO.UserStoryDTO;

import java.util.List;

public interface IDialog {
    // Author: Robin Steinwarz
    void display(List<UserStoryDTO> stories);
}

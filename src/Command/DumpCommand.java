package Command;

import DTO.UserStoryDTO;
import Enum.NoteType;
import Enum.Status;
import Model.Persistance.ContainerEntity;
import View.NotificationView;
import View.SortedDumpDialogView;

import java.util.List;
import java.util.stream.Collectors;

public class DumpCommand extends AbstractCommand {
    // Author: Robin Steinwarz
    @Override
    public void execute(String parameter) {
        List<UserStoryDTO> stories = ContainerEntity.getContainer().getUserStoriesAsListOfDTOs();

        String[] parameters = parameter.trim().split(" ");

        if(parameters.length > 0 && parameters[0].equals("-status")){
            if(parameters.length == 1){
                NotificationView.notify(NoteType.fail, "Format for dump is wrong.");
                NotificationView.notify(NoteType.info, "Please use the following format: dump [-status \'Status\']");
                return;
            }

            Status status = Status.valueOf(parameters[1]);

            stories = stories.stream().filter(c -> c.getStatus() == status).collect(Collectors.toList());
        }


        // Further Filter map reduce in SortedDumpDialog View
        SortedDumpDialogView dump = new SortedDumpDialogView();
        dump.display(stories);
    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "dump           [-status \'Status\']";
    }
}

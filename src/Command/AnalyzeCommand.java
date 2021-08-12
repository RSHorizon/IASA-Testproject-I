package Command;

import QualityStrategy.ActiveMetricEntity;
import QualityStrategy.QualityConfig;
import Model.Entity.QualityResultEntity;
import Model.Entity.UserStoryEntity;
import Enum.NoteType;
import Model.Persistance.ContainerEntity;
import View.NotificationView;
import Exception.ContainerException;
import View.QualityDialogView;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeCommand extends AbstractCommand {
    // Author: Robin Steinwarz

    @Override
    public void execute(String parameter) {

        // Set up Metric
        QualityConfig config = new QualityConfig();

        if(parameter.trim().split(" ").length == 1) {
            if (parameter.trim().equals("-all")) {
                analyzeAll();
                return;
            }
        }

        if (!parameter.trim().equals("")) {

            String[] parameters = parameter.trim().split(" ");

            if(parameters.length <= 2){

                int id = -1;

                try {
                    id = Integer.parseInt(parameters[0]);
                } catch (NumberFormatException e) {
                    NotificationView.notify(NoteType.fail, "The format for id is wrong");
                    NotificationView.notify(NoteType.info, "Please use a valid id number like 0,1,73,9999.");
                    return;
                }

                boolean showDetails = false;
                boolean showHints = false;

                if(parameters.length == 2){
                    if(parameters[1].equals("-details")){
                        showDetails = true;
                    }else if(parameters[1].equals("-hints")){
                        showDetails = true;
                        showHints = true;
                    }
                }

                if(id != -1){
                    analyzeOne(id, showDetails, showHints);
                    return;
                }
            }
        }

        NotificationView.notify(NoteType.fail,"The given parameter list did not match for a UserStory creation");
        NotificationView.notify(NoteType.info,"Please use one of the following formats:");
        NotificationView.notify(NoteType.info,"analyze UserStoryID [-details|-hints]");
        NotificationView.notify(NoteType.info,"analyze -all");
    }

    private void analyzeAll(){
        List<UserStoryEntity> stories = ContainerEntity.getContainer().getUserStories();

        double sum = 0;
        int counter = 0;

        for (int i = 0; i < stories.size(); i++){
            List<QualityResultEntity> qualities = ActiveMetricEntity.getInstance().analyzeStory(stories.get(i));
            double deduction = qualities.stream().map(QualityResultEntity::getQualityDeduction).reduce(0d, Double::sum);

            if(deduction < 1){
                sum += 1-deduction;
            }
            counter++;
        }

        String descriptor = ActiveMetricEntity.getInstance().getDescriptor((double) sum/counter);

        QualityDialogView dialog = new QualityDialogView();
        dialog.display(counter, (double) sum/counter, descriptor);
    }

    private void analyzeOne(int id, boolean showDetails, boolean showHints) {
        try {
            UserStoryEntity story = ContainerEntity.getContainer().getStory(id);
            List<QualityResultEntity> qualities = ActiveMetricEntity.getInstance().analyzeStory(story);

            double quality = 1;
            String descriptor = "";
            List<String> details = new ArrayList<>();
            List<String> hints = new ArrayList<>();

            for(int i = 0; i < qualities.size(); i++){
                quality -= qualities.get(i).getQualityDeduction();
                if(showDetails && !qualities.get(i).getDetail().trim().equals("")){
                    details.add(qualities.get(i).getDetail());
                }

                if( showHints && !qualities.get(i).getHint().trim().equals("")){
                    hints.add(qualities.get(i).getHint());
                }
            }

            if(showDetails && details.size() == 0){
                details.add("Everything fine");
            }

            if(showHints && hints.size() == 0){
                hints.add("No Hints available");
            }

            descriptor = ActiveMetricEntity.getInstance().getDescriptor(quality);

            QualityDialogView dialog = new QualityDialogView();
            dialog.display(id, quality, descriptor, details, hints);


        } catch (ContainerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "analyze        -all \nanalyze        \'UserStoryID\' [-details|-hints]";
    }
}

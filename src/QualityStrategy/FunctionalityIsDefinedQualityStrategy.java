package QualityStrategy;

import Model.Entity.QualityResultEntity;
import Model.Entity.UserStoryEntity;

public class FunctionalityIsDefinedQualityStrategy extends AbstractQualityStrategy{
    // Author: Robin Steinwarz

    public FunctionalityIsDefinedQualityStrategy(){
        qualityDeduction = .5f;
    }

    @Override
    public QualityResultEntity analyze(UserStoryEntity story) {

        QualityResultEntity result = new QualityResultEntity(story.getID(),0,"","");

        String[] parameters = story.getTitle().split(" ");

        int start = -1;
        int end = -1;

        for(int i = 0; i < parameters.length; i++){
            if(parameters[i].equals("möchte")){
                if(i + 2 < parameters.length && parameters[i + 1].equals("ich")){
                    start = i + 2;
                }
            }

            if(parameters[i].equals("um") || parameters[i].equals("damit")){
                end = i;
            }
        }

        if(start != -1){
            String functionalityCut = "";
            for(int i = start; i < parameters.length; i++){
                if(i == end){
                    break;
                }

                functionalityCut += parameters[i];
            }

            if(!functionalityCut.trim().equals("")){
                return result;
            }
        }

        result.setQualityDeduction(qualityDeduction);
        result.setDetail("No functionality specification found ( -"+qualityDeduction*100+"% )");
        result.setHint("Enter a functionality to the UserStory. Format: Als \'Akteur\' [mit der Rolle \'Rolle\'] möchte ich \'Story\'[, um|damit \'Mehrwert\']");

        return result;
    }
}

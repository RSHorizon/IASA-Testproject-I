package QualityStrategy;

import Model.Entity.QualityResultEntity;
import Model.Entity.UserStoryEntity;
import Model.Persistance.ContainerEntity;

public class ActorIsDefinedQualityStrategy extends AbstractQualityStrategy {
    // Author: Robin Steinwarz

    public ActorIsDefinedQualityStrategy(){
        qualityDeduction = .2;
    }

    @Override
    public QualityResultEntity analyze(UserStoryEntity story) {
        QualityResultEntity result = new QualityResultEntity(story.getID(),0,"","");

        String[] parameters = story.getTitle().split(" ");

        if(parameters.length <= 1){
            result.setQualityDeduction(qualityDeduction);
            result.setDetail("No actor specification found ( -"+qualityDeduction*100+"% )");
            result.setHint("Enter an actor to the UserStory. Format: Als \'Akteur\' [mit der Rolle \'Rolle\'] möchte ich \'Story\'[, um|damit \'Mehrwert\']");
        }else if(!ContainerEntity.getContainer().hasActor(parameters[1])){
            result.setQualityDeduction(qualityDeduction);
            result.setDetail("The specified actor " + parameters[1] + " is not known ( -"+qualityDeduction*100+"% )" );
            result.setHint("Use the command addElement -actor to specify a new actor");
        }

        return result;
    }
}

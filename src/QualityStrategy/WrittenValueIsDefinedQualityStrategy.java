package QualityStrategy;

import Model.Entity.QualityResultEntity;
import Model.Entity.UserStoryEntity;

public class WrittenValueIsDefinedQualityStrategy extends AbstractQualityStrategy {
    // Author: Robin Steinwarz

    public WrittenValueIsDefinedQualityStrategy(){
        qualityDeduction = .2;
    }

    @Override
    public QualityResultEntity analyze(UserStoryEntity story) {

        QualityResultEntity result = new QualityResultEntity(story.getID(),0,"","");

        String[] writtenValue = story.getTitle().split("um|damit");

        if(writtenValue.length < 2 || (writtenValue.length == 2 && writtenValue[1].trim().equals(""))){
            result.setQualityDeduction(qualityDeduction);
            result.setDetail("The written value of the UserStory is missing. ( -"+qualityDeduction*100+"% )");
            result.setHint("Add a written value.");
        }

        return result;
    }
}

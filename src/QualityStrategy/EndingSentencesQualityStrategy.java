package QualityStrategy;

import Model.Entity.QualityResultEntity;
import Model.Entity.UserStoryEntity;

public class EndingSentencesQualityStrategy extends AbstractQualityStrategy{

    EndingSentencesQualityStrategy(){
        qualityDeduction = 0.1;
    }

    @Override
    public QualityResultEntity analyze(UserStoryEntity story) {

        QualityResultEntity result = new QualityResultEntity(story.getID(),0,"","");

        String title = story.getTitle();

        String[] countAnd = title.split("und");

        if(countAnd.length > 2){
            result.setQualityDeduction(qualityDeduction);
            result.setDetail("More than one \"und\" was found. ( -"+qualityDeduction*100+"% )");
            result.setHint("Reduce the amount of \"und\" words in your UserStory to increase its value.");
        }

        return result;
    }
}

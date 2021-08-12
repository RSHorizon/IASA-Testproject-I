package QualityStrategy;

import Model.Entity.QualityResultEntity;
import Model.Entity.UserStoryEntity;

public interface QualityStrategyInterface {
    // Author: Robin Steinwarz
    QualityResultEntity analyze(UserStoryEntity story);
}

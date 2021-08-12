package QualityStrategy;

import Model.Entity.QualityResultEntity;
import Model.Entity.UserStoryEntity;

public interface QualityStrategy {
    // Author: Robin Steinwarz
    QualityResultEntity analyze(UserStoryEntity story);
}

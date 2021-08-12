package QualityStrategy;

import java.util.ArrayList;
import java.util.List;

public class QualityConfig {
    // Author: Robin Steinwarz
    public QualityConfig(){
        setMetricUp();
    }

    private void setMetricUp(){
        List<QualityStrategyInterface> qualities = new ArrayList<>();
        qualities.add(new WrittenValueIsDefinedQualityStrategy());
        qualities.add(new ActorIsDefinedQualityStrategy());
        qualities.add(new FunctionalityIsDefinedQualityStrategy());
        qualities.add(new EndingSentencesQualityStrategy());

        ActiveMetricEntity.getInstance().setQualityStrategy(qualities);
    }
}

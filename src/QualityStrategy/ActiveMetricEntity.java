package QualityStrategy;

import Model.Entity.QualityResultEntity;
import Model.Entity.UserStoryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActiveMetricEntity {
    // Author: Robin Steinwarz
    List<QualityStrategy> qualities = new ArrayList<>();

    private static ActiveMetricEntity instance;

    private ActiveMetricEntity(){

    }

    public static synchronized ActiveMetricEntity getInstance(){
        if(instance == null){
            instance = new ActiveMetricEntity();
        }

        return instance;
    }

    public void setQualityStrategy(List<QualityStrategy> strategy){
        qualities = strategy;
    }

    public List<QualityResultEntity> analyzeStory(UserStoryEntity story){
        return qualities.stream().map(c -> c.analyze(story)).collect(Collectors.toList());
    }

    public String getDescriptor(double quality){
        String descriptor;

        if(quality < .5){
            descriptor = "Insufficient";
        }else if(quality < .65){
            descriptor = "Sufficient";
        }else if(quality < .8){
            descriptor = "Satisfactory";
        }else if(quality < .9){
            descriptor = "Good";
        }else{
            descriptor = "Very Good";
        }

        return descriptor;
    }
}

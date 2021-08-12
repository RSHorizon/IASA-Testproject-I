package Test;

import Command.EnterCommand;
import Model.Entity.UserStoryEntity;
import Model.Persistance.ContainerEntity;
import Exception.ContainerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EnterCommandTest {
    // Author: Robin Steinwarz
    @Test
    public void failsForNegativeValues(){
        EnterCommand command = new EnterCommand();

        command.execute("-1 \"Story\" 1 1 1 1");
        command.execute("1 \"Story\" -1 1 1 1");
        command.execute("1 \"Story\" 1 -1 1 1");
        command.execute("1 \"Story\" 1 1 -1 1");
        command.execute("1 \"Story\" 1 1 1 -1");

        UserStoryEntity story = null;
        try {
            story = ContainerEntity.getContainer().getStory(1);
        } catch (ContainerException e) {

        }

        assertNull(story);
    }

    @Test
    public void worksProperlyForGoodStory(){
        EnterCommand command = new EnterCommand();

        command.execute("2 \"Als Student möchte ich lesen könne, um weiter lernen zu können.\" 5 5 5 100");

        UserStoryEntity story = null;
        try {
            story = ContainerEntity.getContainer().getStory(2);
        } catch (ContainerException e) {

        }

        assertNotNull(story);
    }

    @Test
    public void idAlreadyExists() {
        EnterCommand command = new EnterCommand();

        command.execute("3 \"Als Student möchte ich lesen könne, um weiter lernen zu können.\" 5 5 5 100");
        command.execute("3 \"Als Student möchte ich lesen könne, um weiter lernen zu können.\" 5 5 5 100");

        List<UserStoryEntity> stories = ContainerEntity.getContainer().getUserStories();

        int count = 0;

        for (int i = 0; i < stories.size(); i++) {
            count++;
        }

        assertEquals(1, count);
    }

    @Test
    public void wrongFormat(){
        EnterCommand command = new EnterCommand();

        int storyCount = ContainerEntity.getContainer().getUserStories().size();

        command.execute("4 5 \"Als Student möchte ich lesen könne, um weiter lernen zu können.\" 5 5 100");
        command.execute("\"Als Student möchte ich lesen könne, um weiter lernen zu können.\" 5 5 5 100");
        command.execute("");
        command.execute("1 1 1 5 100");
        command.execute("4 \"Als Student möchte ich lesen könne, um weiter lernen zu können.\" 1 1 1");

        assertEquals(ContainerEntity.getContainer().getUserStories().size(), storyCount);
    }
}

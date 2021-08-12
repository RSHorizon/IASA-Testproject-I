package Command;

public abstract class AbstractCommand implements CommandInterface {
    // Author: Robin Steinwarz
    protected boolean undoable = false;

    public boolean isUndoable(){
        return undoable;
    }

}

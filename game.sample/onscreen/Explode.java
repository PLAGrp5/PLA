package onscreen;

import ui.*;

/**
 * Explode
 */
public class Explode extends Action {

    public Explode() {
    }

    public void execute(Model model, Entity e) {
        model.del(e);
    }
}
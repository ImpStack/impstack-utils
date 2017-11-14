package org.impstack.jme.debug;

/**
 * Grid class that extends the Grid class of JME to add an empty constructor for serialisation purposes.
 * @author remy
 * @since 27/09/17.
 */
public class Grid extends com.jme3.scene.debug.Grid {

    public Grid() {
        this(5, 5, 0.5f);
    }

    /**
     * Creates a grid debug shape.
     *
     * @param xLines
     * @param yLines
     * @param lineDist
     */
    public Grid(int xLines, int yLines, float lineDist) {
        super(xLines, yLines, lineDist);
    }
}

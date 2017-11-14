package org.impstack.jme.event;

public interface ResizeEvent {

    /**
     * Called when the window is resized
     * @param width new window width
     * @param height new window height
     */
    public void onResize(int width, int height);

}

package org.impstack.jme.math;

import org.impstack.jme.event.ResizeEvent;
import org.impstack.jme.post.JmeRenderer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JmeRendererTest {

    @Test
    public void resizeEventTriggered() {
        JmeRenderer sceneProcessor = new JmeRenderer();
        ResizeEvent listener = (width, height) -> {
            assertEquals(width, 10);
            assertEquals(height, 10);
        };
        sceneProcessor.registerResizeListener(listener);
        sceneProcessor.reshape(null, 10, 10);
    }
}

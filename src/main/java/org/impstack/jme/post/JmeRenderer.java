package org.impstack.jme.post;

import com.jme3.post.SceneProcessor;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.texture.FrameBuffer;
import org.impstack.jme.event.ResizeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper scene processor that can be added to a viewport.
 * The initial purpose for this implementation is to have a hook when a resize event happened.
 */
public class JmeRenderer implements SceneProcessor {

    private boolean initialized = false;
    private List<ResizeEvent> listeners = new ArrayList<>();

    @Override
    public void initialize(RenderManager rm, ViewPort vp) {
        initialized = true;
    }

    @Override
    public void reshape(ViewPort vp, int w, int h) {
        listeners.forEach(event -> event.onResize(w, h));
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void preFrame(float tpf) {
    }

    @Override
    public void postQueue(RenderQueue rq) {
    }

    @Override
    public void postFrame(FrameBuffer out) {
    }

    @Override
    public void cleanup() {
        initialized = false;
    }

    public void registerResizeListener(ResizeEvent listener) {
        listeners.add(listener);
    }

    public void unregisterResizeListener(ResizeEvent listener) {
        listeners.remove(listener);
    }

}

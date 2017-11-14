package org.impstack.jme.state;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import com.simsilica.lemur.Axis;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.FillMode;
import com.simsilica.lemur.Panel;
import com.simsilica.lemur.component.BorderLayout;
import com.simsilica.lemur.component.SpringGridLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * A Debug HUD that covers the full size of the screen. It uses a border layout for the children. You can attach panels
 * to a region using the addToHud method.
 *
 * @author remy
 * @since 20/10/17.
 */
public class DebugHudAppState extends BaseAppState {

    public enum Region { NORTH, EAST, SOUTH, WEST, CENTER; }

    private final Node node;
    private Container container;
    private Map<Region, Container> regions = new HashMap<>();

    public DebugHudAppState(Node node) {
        this.node = node;
    }

    @Override
    protected void initialize(Application app) {
        container = new Container(new BorderLayout(), "debug-hud-wrapper");

        regions.put(Region.NORTH, container.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y, FillMode.None, FillMode.Even), "debug-hud-north"), BorderLayout.Position.North));
        regions.put(Region.EAST, container.addChild(new Container(new SpringGridLayout(Axis.Y, Axis.X, FillMode.None, FillMode.Even), "debug-hud-east"), BorderLayout.Position.East));
        regions.put(Region.SOUTH, container.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y, FillMode.None, FillMode.Even), "debug-hud-south"), BorderLayout.Position.South));
        regions.put(Region.WEST, container.addChild(new Container(new SpringGridLayout(Axis.Y, Axis.X, FillMode.None, FillMode.Even), "debug-hud-west"), BorderLayout.Position.West));
        regions.put(Region.CENTER, container.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y, FillMode.None, FillMode.None), "debug-hud-center"), BorderLayout.Position.Center));

        container.setLocalTranslation(0, getApplication().getCamera().getHeight(), 0);
        container.setPreferredSize(container.getPreferredSize()
                .setX(getApplication().getCamera().getWidth())
                .setY(getApplication().getCamera().getHeight())
                .setZ(container.getPreferredSize().getZ()));
    }

    @Override
    protected void onEnable() {
        node.attachChild(container);
    }

    @Override
    protected void onDisable() {
        container.removeFromParent();
    }

    @Override
    protected void cleanup(Application app) {
        container = null;
    }

    public void addToHud(Panel panel, Region region) {
        regions.get(region).addChild(panel);
    }

}

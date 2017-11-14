package org.impstack.jme.debug;

import com.jme3.app.Application;
import com.jme3.bounding.BoundingBox;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.debug.WireBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author remy
 * @since 30/08/17.
 */
public class DebugUtils {

    private static final Logger LOG = LoggerFactory.getLogger(DebugUtils.class);
    private static final String DEBUG_MATERIAL = "Common/MatDefs/Misc/Unshaded.j3md";
    private static final String DEBUG_MATERIAL_COLOR = "Color";

    private Application application;

    public DebugUtils(Application application) {
        this.application = application;
    }

    public void drawCoordinateAxes(Node node, Vector3f position) {
        Node coordinateAxes = new Node("CoordinateAxes");
        coordinateAxes.attachChild(createDebugGeometry("X-axis", new Arrow(Vector3f.UNIT_X), ColorRGBA.Red));
        coordinateAxes.attachChild(createDebugGeometry("Y-axis", new Arrow(Vector3f.UNIT_Y), ColorRGBA.Green));
        coordinateAxes.attachChild(createDebugGeometry("Z-axis", new Arrow(Vector3f.UNIT_Z), ColorRGBA.Blue));
        coordinateAxes.setLocalTranslation(position);
        node.attachChild(coordinateAxes);
    }

    public Geometry createGeometry(String name, Mesh mesh, ColorRGBA color, boolean wireframe) {
        Geometry geometry = new Geometry(name, mesh);
        Material material = new Material(application.getAssetManager(), DEBUG_MATERIAL);
        material.getAdditionalRenderState().setWireframe(wireframe);
        material.setColor(DEBUG_MATERIAL_COLOR, color);
        material.getAdditionalRenderState().setLineWidth(1);
        geometry.setMaterial(material);
        return geometry;
    }

    public Geometry createDebugGeometry(String name, Mesh mesh, ColorRGBA color) {
        return createGeometry(name, mesh, color, true);
    }

    public Geometry createBoundingBox(Spatial spatial, ColorRGBA color) {
        Geometry geometry = WireBox.makeGeometry((BoundingBox) spatial.getWorldBound());
        Material material = new Material(application.getAssetManager(), DEBUG_MATERIAL);
        material.setColor(DEBUG_MATERIAL_COLOR, color);
        geometry.setMaterial(material);
        return geometry;
    }

    public static Vector3f getBoundingBoxSize(Spatial spatial) {
        BoundingBox boundingBox = (BoundingBox) spatial.getWorldBound();
        return new Vector3f(boundingBox.getXExtent() * 2, boundingBox.getYExtent() * 2, boundingBox.getZExtent() * 2);
    }

    public static float getBoundingBoxLargestExtend(Spatial spatial) {
        BoundingBox boundingBox = (BoundingBox) spatial.getWorldBound();
        return Math.max(boundingBox.getXExtent(), Math.max(boundingBox.getYExtent(), boundingBox.getZExtent()));
    }

}

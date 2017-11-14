package org.impstack.jme.state;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.simsilica.lemur.*;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.style.ElementId;
import org.impstack.jme.math.ColorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author remy
 * @since 11/10/17.
 */
public class LightAppState extends BaseAppState {

    private static final Logger LOG = LoggerFactory.getLogger(LightAppState.class);

    private final Node node;
    private AmbientLight ambientLight;
    private ColorRGBA ambientLightColor = new ColorRGBA(0.25f, 0.25f, 0.25f, 1);
    private DirectionalLight directionalLight;
    private ColorRGBA directionalLightColor = ColorRGBA.White.mult(2);
    private Vector3f directionalLightDirection = new Vector3f(-1, -1, -1).normalizeLocal();

    public LightAppState(Node node) {
        this.node = node;
    }

    @Override
    protected void initialize(Application app) {
        ambientLight = new AmbientLight(ambientLightColor);
        directionalLight = new DirectionalLight(directionalLightDirection, directionalLightColor);
    }

    @Override
    protected void onEnable() {
        if (node != null) {
            node.addLight(ambientLight);
            node.addLight(directionalLight);
        }
    }

    @Override
    public void update(float tpf) {
    }

    @Override
    protected void onDisable() {
        if (node != null) {
            node.removeLight(ambientLight);
            node.removeLight(directionalLight);
        }
    }

    @Override
    protected void cleanup(Application app) {
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    public Panel getDebugPanel() {
        Container container = new Container();
        container.addChild(new Label("Lights", new ElementId("window.title.label")));

        // ambient light
        Container alColor = container.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y, FillMode.None, FillMode.None)));
        alColor.setInsets(new Insets3f(5, 5, 5, 5));
        alColor.addChild(new Label("Ambient light: "));
        TextField ambientLightTextfieldColor = alColor.addChild(new TextField(ColorHelper.toHex(ambientLight.getColor())));
        ambientLightTextfieldColor.setPreferredSize(ambientLightTextfieldColor.getPreferredSize().clone());

        Container alValue = container.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y, FillMode.None, FillMode.None)));
        alValue.setInsets(new Insets3f(5, 5, 5, 5));
        Slider alSlider = alValue.addChild(new Slider(new DefaultRangedValueModel(0, 2, 1), Axis.X));
        alSlider.setDelta(0.1);
        Label alSliderLabel = alValue.addChild(new Label(String.format("%2.1f", alSlider.getModel().getValue())));
        Button alButton = container.addChild(new Button("Set"));

        alButton.addClickCommands((Command<Button>) source -> {
            ColorRGBA colorRGBA = ColorHelper.fromHex(ambientLightTextfieldColor.getText());
            if (colorRGBA != null) {
                colorRGBA.multLocal((float) alSlider.getModel().getValue());
                LOG.trace("Setting ambient light color to {}", colorRGBA);
                ambientLight.setColor(colorRGBA);
                alSliderLabel.setText(String.format("%2.1f", alSlider.getModel().getValue()));
            }
        });
        alButton.setInsets(new Insets3f(5, 5, 5, 5));
        alButton.setTextHAlignment(HAlignment.Center);

        // directional light
        Container dl = container.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y, FillMode.None, FillMode.None)));
        dl.setInsets(new Insets3f(5, 5, 5, 5));
        dl.addChild(new Label("Directional light: "));
        TextField directionalLightTextfieldColor = dl.addChild(new TextField(ColorHelper.toHex(directionalLight.getColor())));
        directionalLightTextfieldColor.setPreferredSize(directionalLightTextfieldColor.getPreferredSize().clone());

        Container dlValue = container.addChild(new Container(new SpringGridLayout(Axis.X, Axis.Y, FillMode.None, FillMode.None)));
        dlValue.setInsets(new Insets3f(5, 5, 5, 5));
        Slider dlSlider = dlValue.addChild(new Slider(new DefaultRangedValueModel(0, 2, 1), Axis.X));
        dlSlider.setDelta(0.1);
        Label dlSliderLabel = dlValue.addChild(new Label(String.format("%2.1f", dlSlider.getModel().getValue())));

        Button dlButton = container.addChild(new Button("Set"));
        dlButton.addClickCommands((Command<Button>) source -> {
            ColorRGBA colorRGBA = ColorHelper.fromHex(directionalLightTextfieldColor.getText());
            if (colorRGBA != null) {
                colorRGBA.multLocal((float) dlSlider.getModel().getValue());
                LOG.trace("Setting directional light color to {}", colorRGBA);
                directionalLight.setColor(colorRGBA);
                dlSliderLabel.setText(String.format("%2.1f", dlSlider.getModel().getValue()));
            }
        });
        dlButton.setInsets(new Insets3f(5, 5, 5, 5));
        dlButton.setTextHAlignment(HAlignment.Center);

        return container;
    }

}

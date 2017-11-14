package org.impstack.jme.state;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.controls.Trigger;

import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author remy
 * @since 25/10/17.
 */
public class ScreenshotAppState extends com.jme3.app.state.ScreenshotAppState {

    private static final DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
    private final String filename;
    private Application application;

    public ScreenshotAppState(Path path, String filename) {
        super();
        this.filename = filename;
        this.setIsNumbered(false);
        setFilePath(path.toString().endsWith(File.separator) ? path.toString() : path.toString() + File.separator);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.application = app;
    }

    @Override
    public void onAction(String name, boolean value, float tpf) {
        super.onAction(name, value, tpf);
        setFileName(createFilename());
    }

    @Override
    public void takeScreenshot() {
        super.takeScreenshot();
        setFileName(createFilename());
    }

    private String createFilename() {
        return filename + "-" + df.format(new Date());
    }

    public void setTakeScreenshotTrigger(Trigger trigger) {
        application.getInputManager().deleteMapping("ScreenShot");
        application.getInputManager().addMapping("ScreenShot", trigger);
        application.getInputManager().addListener(this, "ScreenShot");
    }
}

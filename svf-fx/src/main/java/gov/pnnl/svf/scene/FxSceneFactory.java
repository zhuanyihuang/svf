package gov.pnnl.svf.scene;

import gov.pnnl.svf.camera.Camera;
import gov.pnnl.svf.camera.DraggingCamera;
import gov.pnnl.svf.camera.DrivingCamera;
import gov.pnnl.svf.camera.FxDraggingCamera;
import gov.pnnl.svf.camera.FxDrivingCamera;
import gov.pnnl.svf.camera.FxOrbitCamera;
import gov.pnnl.svf.camera.OrbitCamera;
import gov.pnnl.svf.camera.SimpleCamera;
import gov.pnnl.svf.core.service.AbstractBusyService;
import gov.pnnl.svf.core.service.BusyService;
import gov.pnnl.svf.picking.ColorPickingCamera;
import gov.pnnl.svf.picking.FxColorPickingCamera;
import gov.pnnl.svf.picking.FxItemPickingCamera;
import gov.pnnl.svf.picking.FxPickingCamera;
import gov.pnnl.svf.picking.ItemPickingCamera;
import gov.pnnl.svf.picking.PickingCamera;
import java.util.concurrent.atomic.AtomicLong;
import javafx.application.Platform;

/**
 * Scene factory for FX scene types.
 *
 * @author Arthur Bleeker
 */
public class FxSceneFactory implements SceneFactory {

    private final AtomicLong counter = new AtomicLong();

    /**
     * Constructor
     */
    public FxSceneFactory() {
    }

    @Override
    public String newUuid(final Scene scene) {
        return scene.getId() + "-" + counter.getAndIncrement();
    }

    @Override
    public TooltipActor createTooltip(final Scene scene) {
        return new FxTooltipActor(scene);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T createService(final Scene scene, final Class<T> type) {
        if (BusyService.class.equals(type)) {
            if (scene instanceof FxAbstractScene) {
                return (T) new AbstractBusyService() {
                    @Override
                    protected void showBusy(final boolean value) {
                        // no operation
                    }
                };
            } else {
                throw new IllegalArgumentException("scene");
            }
        } else {
            throw new IllegalArgumentException("type");
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public <C extends Camera> C createCamera(final Scene scene, final Class<C> type) {
        if (DraggingCamera.class.equals(type)) {
            return (C) new FxDraggingCamera(scene);
        } else if (OrbitCamera.class.equals(type)) {
            return (C) new FxOrbitCamera(scene);
        } else if (DrivingCamera.class.equals(type)) {
            return (C) new FxDrivingCamera(scene);
        } else if (SimpleCamera.class.equals(type)) {
            return (C) new SimpleCamera(scene);
        } else {
            throw new IllegalArgumentException("type");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <P extends PickingCamera> P createPickingCamera(final Scene scene, final Camera camera, final Class<P> type) {
        if (ColorPickingCamera.class.equals(type)) {
            return (P) new FxColorPickingCamera(scene, camera);
        } else if (ItemPickingCamera.class.equals(type)) {
            return (P) new FxItemPickingCamera(scene, camera);
        } else if (PickingCamera.class.equals(type)) {
            return (P) new FxPickingCamera(scene, camera);
        } else {
            throw new IllegalArgumentException("type");
        }
    }

    @Override
    public void runOnUiThread(final Scene scene, final Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }

    @Override
    public boolean runOnUiThread(final Scene scene, final Runnable runnable, final boolean synchronous) {
        if (synchronous) {
            if (Platform.isFxApplicationThread()) {
                runnable.run();
                return true;
            } else {
                Platform.runLater(runnable);
                return false;
            }
        } else {
            Platform.runLater(runnable);
            return false;
        }
    }
}

package gov.pnnl.svf.demo.awt;

import com.jogamp.opengl.awt.GLCanvas;
import gov.pnnl.svf.awt.scene.AwtScene;
import gov.pnnl.svf.demo.Demo;
import gov.pnnl.svf.demo.ShapeDemoLoader;
import gov.pnnl.svf.scene.AbstractScene;
import gov.pnnl.svf.scene.SceneBuilder;
import java.lang.reflect.Constructor;
import javax.swing.JFrame;

/**
 *
 * @author Arthur Bleeker
 */
public class AwtShapeDemo extends AbstractAwtDemo {

    /**
     * This main method must be copied into the final class for reflection to
     * work correctly.
     *
     * @param args
     *
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        boolean debug = false;
        for (final String string : args) {
            if ("debug".equals(string)) {
                debug = true;
            }
        }
        final Class<?> current = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
        final Constructor<?> constructor = current.getConstructor((Class<?>[]) null);
        final Demo<?, ?> demo = (Demo<?, ?>) constructor.newInstance((Object[]) null);
        demo.runDemo(debug);
    }

    @Override
    public AbstractScene<GLCanvas> createScene(final JFrame window, final SceneBuilder builder) {
        final GLCanvas component = new GLCanvas(builder.getGLCapabilities());
        final AwtScene scene = new AwtScene(component, builder) {
            @Override
            public void load() {
                final ShapeDemoLoader loader = new ShapeDemoLoader();
                loader.load(this);
            }
        };
        return scene;
    }
}

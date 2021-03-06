package gov.pnnl.svf.swt.scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.gl2.GLUgl2;
import gov.pnnl.svf.actor.AbstractActor;
import gov.pnnl.svf.actor.Actor;
import gov.pnnl.svf.camera.Camera;
import gov.pnnl.svf.scene.Scene;
import gov.pnnl.svf.support.ChildSupport;
import gov.pnnl.svf.support.ColorSupport;
import gov.pnnl.svf.support.TransformSupport;
import gov.pnnl.svf.swt.SwtTestScene;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Arthur Bleeker
 */
public class SwtAbstractSceneDisposeTest {

    private SwtTestScene scene;

    /**
     * Set up for testing
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        scene = new SwtTestScene();
        scene.setUp();
    }

    /**
     * Clean up after testing
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        if (scene != null) {
            scene.tearDown();
        }
    }

    @Test
    public void testDispose1() {
        final Actor a = new ActorImpl(scene, "actor", "a");
        scene.add(a);
        scene.dispose();
        Assert.assertTrue(scene.lookupAll(Actor.class).isEmpty());
    }

    @Test
    public void testDispose2() {
        final Actor a = new ActorImpl(scene, "actor", "a");
        ColorSupport.newInstance(a);
        TransformSupport.newInstance(a);
        scene.add(a);

        scene.dispose();
        Assert.assertTrue(scene.lookupAll(Actor.class).isEmpty());
        Assert.assertTrue(scene.lookupAll(ColorSupport.class).isEmpty());
        Assert.assertTrue(scene.lookupAll(TransformSupport.class).isEmpty());
        Assert.assertNull(a.lookup(ColorSupport.class));
        Assert.assertNull(a.lookup(TransformSupport.class));
        Assert.assertNull(scene.getActor("a"));
    }

    @Test
    public void testDispose3() {
        final Actor a = new ActorImpl(scene, "actor", "a");
        ColorSupport.newInstance(a);
        TransformSupport.newInstance(a);
        final ChildSupport children = ChildSupport.newInstance(a);
        final Actor b = new ActorImpl(scene, "actor", "b");
        ColorSupport.newInstance(b);
        TransformSupport.newInstance(b);
        children.add(b);
        scene.add(a);

        scene.dispose();
        Assert.assertTrue(scene.lookupAll(Actor.class).isEmpty());
        Assert.assertTrue(scene.lookupAll(ColorSupport.class).isEmpty());
        Assert.assertTrue(scene.lookupAll(TransformSupport.class).isEmpty());
        Assert.assertNull(a.lookup(ColorSupport.class));
        Assert.assertNull(a.lookup(TransformSupport.class));
        Assert.assertNull(a.lookup(ChildSupport.class));
        Assert.assertNull(b.lookup(ColorSupport.class));
        Assert.assertNull(b.lookup(TransformSupport.class));
        Assert.assertNull(scene.getActor("a"));
        Assert.assertNull(scene.getActor("b"));
    }

    @Test
    public void testDispose4() {
        final Actor a = new ActorImpl(scene, "actor", "a");
        ColorSupport.newInstance(a);
        TransformSupport.newInstance(a);
        scene.add(a);
        final ChildSupport children = ChildSupport.newInstance(a);
        final Actor b = new ActorImpl(scene, "actor", "b");
        ColorSupport.newInstance(b);
        TransformSupport.newInstance(b);
        children.add(b);

        scene.dispose();
        Assert.assertTrue(scene.lookupAll(Actor.class).isEmpty());
        Assert.assertTrue(scene.lookupAll(ColorSupport.class).isEmpty());
        Assert.assertTrue(scene.lookupAll(TransformSupport.class).isEmpty());
        Assert.assertNull(a.lookup(ColorSupport.class));
        Assert.assertNull(a.lookup(TransformSupport.class));
        Assert.assertNull(a.lookup(ChildSupport.class));
        Assert.assertNull(b.lookup(ColorSupport.class));
        Assert.assertNull(b.lookup(TransformSupport.class));
        Assert.assertNull(scene.getActor("a"));
        Assert.assertNull(scene.getActor("b"));
    }

    @Test
    public void testDispose5() {
        final Actor a = new ActorImpl(scene, "actor", "a");
        ColorSupport.newInstance(a);
        TransformSupport.newInstance(a);
        final ChildSupport children = ChildSupport.newInstance(a);
        scene.add(a);
        final Actor b = new ActorImpl(scene, "actor", "b");
        ColorSupport.newInstance(b);
        TransformSupport.newInstance(b);
        children.add(b);

        scene.dispose();
        Assert.assertTrue(scene.lookupAll(Actor.class).isEmpty());
        Assert.assertTrue(scene.lookupAll(ColorSupport.class).isEmpty());
        Assert.assertTrue(scene.lookupAll(TransformSupport.class).isEmpty());
        Assert.assertNull(a.lookup(ColorSupport.class));
        Assert.assertNull(a.lookup(TransformSupport.class));
        Assert.assertNull(a.lookup(ChildSupport.class));
        Assert.assertNull(b.lookup(ColorSupport.class));
        Assert.assertNull(b.lookup(TransformSupport.class));
        Assert.assertNull(scene.getActor("a"));
        Assert.assertNull(scene.getActor("b"));
    }

    public static class ActorImpl extends AbstractActor {

        public ActorImpl(final Scene scene, final String type, final String id) {
            super(scene, type, id);
        }

        @Override
        public void draw(final GL2 gl, final GLUgl2 glu, final Camera camera) {
            // no operation
        }

        @Override
        public void endDraw(final GL2 gl, final GLUgl2 glu, final Camera camera) {
            // no operation
        }
    }
}

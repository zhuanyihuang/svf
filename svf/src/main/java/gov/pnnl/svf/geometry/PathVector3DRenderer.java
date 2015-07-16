package gov.pnnl.svf.geometry;

import gov.pnnl.svf.core.geometry.Border;
import gov.pnnl.svf.scene.Scene;
import gov.pnnl.svf.util.Shape3DUtil;
import javax.media.opengl.GL2;

/**
 * Shape renderer implementation.
 *
 * @author Arthur Bleeker
 */
public class PathVector3DRenderer extends AbstractShapeRenderer {

    /**
     * Constructor
     *
     * @param scene reference to the scene
     */
    public PathVector3DRenderer(final Scene scene) {
        super(scene);
    }

    @Override
    public int drawShape(final GL2 gl, final Shape shape) {
        if (shape instanceof PathVector3D) {
            return Shape3DUtil.drawShape(gl, (PathVector3D) shape);
        }
        return 0;
    }

    @Override
    public int drawBorder(final GL2 gl, final Shape shape, final Border border, final double thickness) {
        throw new IllegalArgumentException("shape");
    }

}

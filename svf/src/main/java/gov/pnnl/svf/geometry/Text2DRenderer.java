package gov.pnnl.svf.geometry;

import com.jogamp.opengl.GL2;
import gov.pnnl.svf.core.geometry.Border;
import gov.pnnl.svf.scene.Scene;
import gov.pnnl.svf.text.TextRenderer;
import gov.pnnl.svf.text.TextService;
import gov.pnnl.svf.util.Border2DUtil;
import gov.pnnl.svf.util.Shape2DUtil;

/**
 * Shape renderer implementation.
 *
 * @author Arthur Bleeker
 */
public class Text2DRenderer extends AbstractShapeRenderer {

    private TextRenderer textRenderer;

    /**
     * Constructor
     *
     * @param scene reference to the scene
     */
    public Text2DRenderer(final Scene scene) {
        super(scene);
    }

    @Override
    public void prepare(final GL2 gl, final Shape shape) {
        if (shape instanceof Text2D) {
            final Text2D text = (Text2D) shape;
            final TextService textService = scene.lookup(TextService.class);
            textRenderer = textService.getTextRenderer(text.getFont());
            textRenderer.prepare(gl, text.getText());
        }
    }

    @Override
    public int drawBackground(final GL2 gl, final Shape shape) {
        if (shape instanceof Text2D) {
            return Shape2DUtil.drawShape(gl, (Text2D) shape);
        }
        return 0;
    }

    @Override
    public int drawShape(final GL2 gl, final Shape shape) {
        if (shape instanceof Text2D) {
            final Text2D text = (Text2D) shape;
            textRenderer.draw(gl, text);
            return text.getText().length() * 4;
        }
        return 0;
    }

    @Override
    public int drawBorder(final GL2 gl, final Shape shape, final Border border, final double thickness) {
        if (shape instanceof Text2D) {
            return Border2DUtil.drawBorder(gl, (Text2D) shape, border, thickness);
        }
        return 0;
    }

    @Override
    public int pickingDrawShape(final GL2 gl, final Shape shape) {
        if (shape instanceof Text2D) {
            return Shape2DUtil.drawShape(gl, (Text2D) shape);
        }
        return 0;
    }

}

package gov.pnnl.svf.event;

import gov.pnnl.svf.picking.PickingCamera;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * Event pushed by a picking camera.
 *
 * @author Arthur Bleeker
 */
public class PickingCameraEvent extends AbstractCameraEvent<PickingCamera> {

    /**
     * Constructor
     *
     * @param source  the source camera of the event
     * @param x       the x coord in screen space
     * @param y       the y coord in screen space
     * @param context the amount which can represent button or wheel clicks or
     *                specific key
     * @param types   the types of events
     */
    public PickingCameraEvent(final PickingCamera source, final int x, final int y, final int context, final Set<CameraEventType> types) {
        super(source, x, y, context, types);
    }

    /**
     * Constructor
     *
     * @param source  the source camera of the event
     * @param x       the x coord center in screen space
     * @param y       the y coord center in screen space
     * @param width   the width in screen space
     * @param height  the height in screen space
     * @param context the amount which can represent button or wheel clicks or
     *                specific key
     * @param types   the types of events
     *
     * @throws IllegalArgumentException if the width or height is less than 1
     */
    public PickingCameraEvent(final PickingCamera source, final int x, final int y, final int width, final int height, final int context, final Set<CameraEventType> types) {
        super(source, x, y, width, height, context, types);
    }

    public static class Builder {

        private Set<CameraEventType> types = EnumSet.noneOf(CameraEventType.class);
        private PickingCamera source = null;
        private int x = 0;
        private int y = 0;
        private int width = 1;
        private int height = 1;
        private int context = 0;

        private Builder() {
        }

        public static Builder construct() {
            return new Builder();
        }

        public Builder types(final CameraEventType... types) {
            this.types = types != null ? EnumSet.copyOf(Arrays.asList(types)) : EnumSet.noneOf(CameraEventType.class);
            return this;
        }

        public Builder types(final Set<CameraEventType> types) {
            this.types = types != null ? types : EnumSet.noneOf(CameraEventType.class);
            return this;
        }

        public Builder source(final PickingCamera source) {
            this.source = source;
            return this;
        }

        public Builder x(final int x) {
            this.x = x;
            return this;
        }

        public Builder y(final int y) {
            this.y = y;
            return this;
        }

        public Builder width(final int width) {
            this.width = width;
            return this;
        }

        public Builder height(final int height) {
            this.height = height;
            return this;
        }

        public Builder context(final int context) {
            this.context = context;
            return this;
        }

        public PickingCameraEvent build() {
            return new PickingCameraEvent(source, x, y, width, height, context, types);
        }
    }
}

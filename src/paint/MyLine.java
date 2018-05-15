/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

/**
 *
 * @author Galal1
 */

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.Line2D;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.DirtyBits;
import com.sun.javafx.sg.prism.NGLine;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.javafx.sg.prism.NGShape;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.css.StyleableProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;


/**
 * This Line represents a line segment in {@code (x,y)}
 * coordinate space. Example:
 *
<PRE>
import javafx.scene.shape.*;

Line line = new Line();
line.setStartX(0.0f);
line.setStartY(0.0f);
line.setEndX(100.0f);
line.setEndY(100.0f);
}
</PRE>
 * @since JavaFX 2.0
 */
public class MyLine extends Shape implements MyShape {

    private final Line2D shape = new Line2D();

    {
        // overriding default values for fill and stroke
        // Set through CSS property so that it appears to be a UA style rather
        // that a USER style so that fill and stroke can still be set from CSS.
        ((StyleableProperty)fillProperty()).applyStyle(null, null);
        ((StyleableProperty)strokeProperty()).applyStyle(null, Color.BLACK);
    }

    /**
     * Creates an empty instance of Line.
     */
    public MyLine() {
    }

    /**
     * Creates a new instance of Line.
     * @param startX the horizontal coordinate of the start point of the line segment
     * @param startY the vertical coordinate of the start point of the line segment
     * @param endX the horizontal coordinate of the end point of the line segment
     * @param endY the vertical coordinate of the end point of the line segment
     */
    public MyLine(double startX, double startY, double endX, double endY) {
        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);
    }

    /**
     * The X coordinate of the start point of the line segment.
     *
     * @defaultValue 0.0
     */
    private final DoubleProperty startX = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return MyLine.this;
                }

                @Override
                public String getName() {
                    return "startX";
                }
            };


    public final void setStartX(double value) {
        startX.set(value);
    }

    public final double getStartX() {
        return startX.get();
    }

    public final DoubleProperty startXProperty() {
        return startX;
    }

    /**
     * The Y coordinate of the start point of the line segment.
     *
     * @defaultValue 0.0
     */
    private final DoubleProperty startY = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return MyLine.this;
                }

                @Override
                public String getName() {
                    return "startY";
                }
            };


    public final void setStartY(double value) {
        startY.set(value);
    }

    public final double getStartY() {
        return startY.get();
    }

    public final DoubleProperty startYProperty() {
        return startY;
    }

    /**
     * The X coordinate of the end point of the line segment.
     *
     * @defaultValue 0.0
     */
    private final DoubleProperty endX = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return MyLine.this;
        }

        @Override
        public String getName() {
            return "endX";
        }
    };



    public final void setEndX(double value) {
        endX.set(value);
    }

    public final double getEndX() {
        return endX.get();
    }

    public final DoubleProperty endXProperty() {
        return endX;
    }

    /**
     * The Y coordinate of the end point of the line segment.
     *
     * @defaultValue 0.0
     */
    private final DoubleProperty endY = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return MyLine.this;
        }

        @Override
        public String getName() {
            return "endY";
        }
    };

    public final void setEndY(double value) {
        endY.set(value);
    }

    public final double getEndY() {
        return endY.get();
    }

    public final DoubleProperty endYProperty() {
        return endY;
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override
    protected NGNode impl_createPeer() {
        return new NGLine();
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {

        // Since line's only draw with strokes, if the mode is FILL or EMPTY
        // then we simply return empty bounds
        if (impl_mode == NGShape.Mode.FILL || impl_mode == NGShape.Mode.EMPTY ||
            getStrokeType() == StrokeType.INSIDE)
        {
            return bounds.makeEmpty();
        }

        double x1 = getStartX();
        double x2 = getEndX();
        double y1 = getStartY();
        double y2 = getEndY();
        // Get the draw stroke, and figure out the bounds based on the stroke.
        double wpad = getStrokeWidth();
        if (getStrokeType() == StrokeType.CENTERED) {
            wpad /= 2.0f;
        }
        // fast path the case of AffineTransform being TRANSLATE or identity
        if (tx.isTranslateOrIdentity()) {
            final double xpad;
            final double ypad;
            wpad = Math.max(wpad, 0.5f);
            if (tx.getType() == BaseTransform.TYPE_TRANSLATION) {
                final double ddx = tx.getMxt();
                final double ddy = tx.getMyt();
                x1 += ddx;
                y1 += ddy;
                x2 += ddx;
                y2 += ddy;
            }
            if (y1 == y2 && x1 != x2) {
                ypad = wpad;
                xpad = (getStrokeLineCap() == StrokeLineCap.BUTT) ? 0.0f : wpad;
            } else if (x1 == x2 && y1 != y2) {
                xpad = wpad;
                ypad = (getStrokeLineCap() == StrokeLineCap.BUTT) ? 0.0f : wpad;
            } else {
                if (getStrokeLineCap() == StrokeLineCap.SQUARE) {
                    wpad *= Math.sqrt(2);
                }
                xpad = ypad = wpad;
            }
            if (x1 > x2) { final double t = x1; x1 = x2; x2 = t; }
            if (y1 > y2) { final double t = y1; y1 = y2; y2 = t; }

            x1 -= xpad;
            y1 -= ypad;
            x2 += xpad;
            y2 += ypad;
            bounds = bounds.deriveWithNewBounds((float)x1, (float)y1, 0.0f,
                    (float)x2, (float)y2, 0.0f);
            return bounds;
        }

        double dx = x2 - x1;
        double dy = y2 - y1;
        final double len = Math.sqrt(dx * dx + dy * dy);
        if (len == 0.0f) {
            dx = wpad;
            dy = 0.0f;
        } else {
            dx = wpad * dx / len;
            dy = wpad * dy / len;
        }
        final double ecx;
        final double ecy;
        if (getStrokeLineCap() != StrokeLineCap.BUTT) {
            ecx = dx;
            ecy = dy;
        } else {
            ecx = ecy = 0.0f;
        }
        final double corners[] = new double[] {
            x1-dy-ecx, y1+dx-ecy,
            x1+dy-ecx, y1-dx-ecy,
            x2+dy+ecx, y2-dx+ecy,
            x2-dy+ecx, y2+dx+ecy };
        tx.transform(corners, 0, corners, 0, 4);
        x1 = Math.min(Math.min(corners[0], corners[2]),
                             Math.min(corners[4], corners[6]));
        y1 = Math.min(Math.min(corners[1], corners[3]),
                             Math.min(corners[5], corners[7]));
        x2 = Math.max(Math.max(corners[0], corners[2]),
                             Math.max(corners[4], corners[6]));
        y2 = Math.max(Math.max(corners[1], corners[3]),
                             Math.max(corners[5], corners[7]));
        x1 -= 0.5f;
        y1 -= 0.5f;
        x2 += 0.5f;
        y2 += 0.5f;
        bounds = bounds.deriveWithNewBounds((float)x1, (float)y1, 0.0f,
                (float)x2, (float)y2, 0.0f);
        return bounds;
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override
    public Line2D impl_configShape() {
        shape.setLine((float)getStartX(), (float)getStartY(), (float)getEndX(), (float)getEndY());
        return shape;
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override
    public void impl_updatePeer() {
        super.impl_updatePeer();

        if (impl_isDirty(DirtyBits.NODE_GEOMETRY)) {
            NGLine peer = impl_getPeer();
            peer.updateLine((float)getStartX(),
                (float)getStartY(),
                (float)getEndX(),
                (float)getEndY());
        }
    }

    /***************************************************************************
     *                                                                         *
     *                         Stylesheet Handling                             *
     *                                                                         *
     **************************************************************************/

    /**
     * Some sub-class of Shape, such as {@link Line}, override the
     * default value for the {@link Shape#fill} property. This allows
     * CSS to get the correct initial value.
     * @treatAsPrivate Implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    protected Paint impl_cssGetFillInitialValue() {
        return null;
    }

    /**
     * Some sub-class of Shape, such as {@link Line}, override the
     * default value for the {@link Shape#stroke} property. This allows
     * CSS to get the correct initial value.
     * @treatAsPrivate Implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    protected Paint impl_cssGetStrokeInitialValue() {
        return Color.BLACK;
    }

    /**
     * Returns a string representation of this {@code Line} object.
     * @return a string representation of this {@code Line} object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Line[");

        String id = getId();
        if (id != null) {
            sb.append("id=").append(id).append(", ");
        }

        sb.append("startX=").append(getStartX());
        sb.append(", startY=").append(getStartY());
        sb.append(", endX=").append(getEndX());
        sb.append(", endY=").append(getEndY());

        Paint stroke = getStroke();
        if (stroke != null) {
            sb.append(", stroke=").append(stroke);
            sb.append(", strokeWidth=").append(getStrokeWidth());
        }

        return sb.append("]").toString();
    }
}


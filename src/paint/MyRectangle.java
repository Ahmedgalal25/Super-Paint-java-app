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

import javafx.scene.shape.Shape;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableDoubleProperty;
import javafx.css.StyleableProperty;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.sun.javafx.css.converters.SizeConverter;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.RoundRectangle2D;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.DirtyBits;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.javafx.sg.prism.NGRectangle;
import com.sun.javafx.sg.prism.NGShape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;


/**
 * The {@code Rectangle} class defines a rectangle
 * with the specified size and location. By default the rectangle
 * has sharp corners. Rounded corners can be specified by setting both of
 * the arcWidth and arcHeight properties to positive values {@code (> 0.0)}.

 * <p>Example code: the following code creates a rectangle with 20 pixel
 * rounded corners.</p>
 *
<PRE>
import javafx.scene.shape.*;

Rectangle r = new Rectangle();
r.setX(50);
r.setY(50);
r.setWidth(200);
r.setHeight(100);
r.setArcWidth(20);
r.setArcHeight(20);
</PRE>
 * @since JavaFX 2.0
 */
public  class MyRectangle extends Shape implements MyShape {

    private final RoundRectangle2D shape = new RoundRectangle2D();

    private static final int NON_RECTILINEAR_TYPE_MASK = ~(
            BaseTransform.TYPE_TRANSLATION |
            BaseTransform.TYPE_MASK_SCALE |
            BaseTransform.TYPE_QUADRANT_ROTATION |
            BaseTransform.TYPE_FLIP);

    /**
     * Creates an empty instance of Rectangle.
     */
    public MyRectangle() {
    }

    /**
     * Creates a new instance of Rectangle with the given size.
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public MyRectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     * Creates a new instance of Rectangle with the given size and fill.
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param fill determines how to fill the interior of the rectangle
     */
    public MyRectangle(double width, double height, Paint fill) {
        setWidth(width);
        setHeight(height);
        setFill(fill);
    }

    /**
     * Creates a new instance of Rectangle with the given position and size.
     * @param x horizontal position of the rectangle
     * @param y vertical position of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public MyRectangle(double x, double y, double width, double height) {
        this(width, height);
        setX(x);
        setY(y);
    }

    /**
     * Defines the X coordinate of the upper-left corner of the rectangle.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty x;


    public final void setX(double value) {
        if (x != null || value != 0.0) {
            xProperty().set(value);
        }
    }

    public final double getX() {
        return x == null ? 0.0 : x.get();
    }

    public final DoubleProperty xProperty() {
        if (x == null) {
            x = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return MyRectangle.this;
                }

                @Override
                public String getName() {
                    return "x";
                }
            };
        }
        return x;
    }

    /**
     * Defines the Y coordinate of the upper-left corner of the rectangle.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty y;

    public final void setY(double value) {
        if (y != null || value != 0.0) {
            yProperty().set(value);
        }
    }

    public final double getY() {
        return y == null ? 0.0 : y.get();
    }

    public final DoubleProperty yProperty() {
        if (y == null) {
            y = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return MyRectangle.this;
                }

                @Override
                public String getName() {
                    return "y";
                }
            };
        }
        return y;
    }

    /**
     * Defines the width of the rectangle.
     *
     * @defaultValue 0.0
     */
    private final DoubleProperty width = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return MyRectangle.this;
        }

        @Override
        public String getName() {
            return "width";
        }
    };

    public final void setWidth(double value) {
        width.set(value);
    }

    public final double getWidth() {
        return width.get();
    }

    public final DoubleProperty widthProperty() {
        return width;
    }

    /**
     * Defines the height of the rectangle.
     *
     * @defaultValue 0.0
     */
    private final DoubleProperty height = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return MyRectangle.this;
        }

        @Override
        public String getName() {
            return "height";
        }
    };


    public final void setHeight(double value) {
        height.set(value);
    }

    public final double getHeight() {
        return height.get();
    }

    public final DoubleProperty heightProperty() {
        return height;
    }

    /**
     * Defines the horizontal diameter of the arc
     * at the four corners of the rectangle.
     * The rectangle will have rounded corners if and only if both of
     * the arc width and arc height properties are greater than 0.0.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty arcWidth;


    public final void setArcWidth(double value) {
        if (arcWidth != null || value != 0.0) {
            arcWidthProperty().set(value);
        }
    }

    public final double getArcWidth() {
        return arcWidth == null ? 0.0 : arcWidth.get();
    }

    public final DoubleProperty arcWidthProperty() {
        if (arcWidth == null) {
            arcWidth = new StyleableDoubleProperty() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                }

                @Override
                public CssMetaData<MyRectangle, Number> getCssMetaData() {
                    return StyleableProperties.ARC_WIDTH;
                }

                @Override
                public Object getBean() {
                    return MyRectangle.this;
                }

                @Override
                public String getName() {
                    return "arcWidth";
                }
            };
        }
        return arcWidth;
    }

    /**
     * Defines the vertical diameter of the arc
     * at the four corners of the rectangle.
     * The rectangle will have rounded corners if and only if both of
     * the arc width and arc height properties are greater than 0.0.
     *
     * @defaultValue 0.0
     */
    private DoubleProperty arcHeight;


    public final void setArcHeight(double value) {
        if (arcHeight != null || value != 0.0) {
            arcHeightProperty().set(value);
        }
    }

    public final double getArcHeight() {
        return arcHeight == null ? 0.0 : arcHeight.get();
    }

    public final DoubleProperty arcHeightProperty() {
        if (arcHeight == null) {
            arcHeight = new StyleableDoubleProperty() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                }

                @Override
                public CssMetaData<MyRectangle, Number> getCssMetaData() {
                    return StyleableProperties.ARC_HEIGHT;
                }

                @Override
                public Object getBean() {
                    return MyRectangle.this;
                }

                @Override
                public String getName() {
                    return "arcHeight";
                }
            };
        }
        return arcHeight;
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override
    protected NGNode impl_createPeer() {
        return new NGRectangle();
    }

    /***************************************************************************
     *                                                                         *
     *                         Stylesheet Handling                             *
     *                                                                         *
     **************************************************************************/

      /**
      * Super-lazy instantiation pattern from Bill Pugh.
      * @treatAsPrivate implementation detail
      */
     private static class StyleableProperties {
         private static final CssMetaData<MyRectangle,Number> ARC_HEIGHT =
            new CssMetaData<MyRectangle,Number>("-fx-arc-height",
                SizeConverter.getInstance(), 0.0) {

            @Override
            public boolean isSettable(MyRectangle node) {
                return node.arcHeight == null || !node.arcHeight.isBound();
            }

            @Override
            public StyleableProperty<Number> getStyleableProperty(MyRectangle node) {
                return (StyleableProperty<Number>)node.arcHeightProperty();
            }

             
             

        };
         private static final CssMetaData<MyRectangle,Number> ARC_WIDTH =
            new CssMetaData<MyRectangle,Number>("-fx-arc-width",
                SizeConverter.getInstance(), 0.0) {

            @Override
            public boolean isSettable(MyRectangle node) {
                return node.arcWidth == null || !node.arcWidth.isBound();
            }

            @Override
            public StyleableProperty<Number> getStyleableProperty(MyRectangle node) {
                return (StyleableProperty<Number>)node.arcWidthProperty();
            }

            
            

        };

         private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
         static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<CssMetaData<? extends Styleable, ?>>(Shape.getClassCssMetaData());
            styleables.add(ARC_HEIGHT);
            styleables.add(ARC_WIDTH);
            STYLEABLES = Collections.unmodifiableList(styleables);

         }
    }

    /**
     * @return The CssMetaData associated with this class, which may include the
     * CssMetaData of its super classes.
     * @since JavaFX 8.0
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    /**
     * {@inheritDoc}
     *
     * @since JavaFX 8.0
     */


    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

    /**
     */
    StrokeLineJoin convertLineJoin(StrokeLineJoin t) {
        // If we are a round rectangle then MITER can produce anomalous
        // results for very thin or very wide corner arcs when the bezier
        // curves that approximate the arcs become so distorted that they
        // shoot out MITER-like extensions.  This effect complicates matters
        // because it makes such "round" rectangles non-round, and also
        // because it means we might have to pad the bounds to account
        // for this rare and unpredictable circumstance.
        // To avoid the problem, we set the Join style to BEVEL for any
        // rounded rect.  The BEVEL join style is more predictable for
        // anomalous angles and is the simplest join style to compute in
        // the stroking code.
        // For non-rounded rectangles, the angles are all 90 degrees and so
        // the computations are both simple and non-problematic so we pass on
        // the join style unmodified to the PG layer.
        if ((getArcWidth() > 0) && (getArcHeight() > 0)) {
            return StrokeLineJoin.BEVEL;
        }
        return t;
    }

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
    @Deprecated
    @Override
    

    /**
     * @treatAsPrivate implementation detail
     * @deprecated This is an internal API that is not intended for use and will be removed in the next version
     */
   
    public RoundRectangle2D impl_configShape() {
        if ((getArcWidth() > 0) && (getArcHeight() > 0)) {
            shape.setRoundRect((float)getX(), (float)getY(),
                    (float)getWidth(), (float)getHeight(),
                    (float)getArcWidth(), (float)getArcHeight());
        } else {
            shape.setRoundRect(
                    (float)getX(), (float)getY(),
                    (float)getWidth(), (float)getHeight(), 0, 0);
        }
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
            final NGRectangle peer = impl_getPeer();
            peer.updateRectangle((float)getX(),
                (float)getY(),
                (float)getWidth(),
                (float)getHeight(),
                (float)getArcWidth(),
                (float)getArcHeight());
        }
    }

    /**
     * Returns a string representation of this {@code Rectangle} object.
     * @return a string representation of this {@code Rectangle} object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rectangle[");

        String id = getId();
        if (id != null) {
            sb.append("id=").append(id).append(", ");
        }

        sb.append("x=").append(getX());
        sb.append(", y=").append(getY());
        sb.append(", width=").append(getWidth());
        sb.append(", height=").append(getHeight());

        sb.append(", fill=").append(getFill());

        Paint stroke = getStroke();
        if (stroke != null) {
            sb.append(", stroke=").append(stroke);
            sb.append(", strokeWidth=").append(getStrokeWidth());
        }

        return sb.append("]").toString();
    }
}
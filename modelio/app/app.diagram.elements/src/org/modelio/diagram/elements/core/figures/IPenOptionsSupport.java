/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * A figure implements this interface to indicate that it is using a pen defined by: <li>a line drawing Color <li>a line
 * thickness<li>a text color<li>a text font</li>
 */
@objid ("7fa4c440-1dec-11e2-8cad-001ec947c8cc")
public interface IPenOptionsSupport {
    /**
     * Set the line(s) color.
     * @param lineColor the line color.
     */
    @objid ("7fa4c442-1dec-11e2-8cad-001ec947c8cc")
    void setLineColor(Color lineColor);

    /**
     * Set the line(s) width.
     * @param lineWidth the line(s) width.
     */
    @objid ("7fa4c445-1dec-11e2-8cad-001ec947c8cc")
    void setLineWidth(int lineWidth);

    /**
     * Get the line color.
     * @return the line color.
     */
    @objid ("7fa4c448-1dec-11e2-8cad-001ec947c8cc")
    Color getLineColor();

    /**
     * Get the line width.
     * @return the line width.
     */
    @objid ("7fa4c44b-1dec-11e2-8cad-001ec947c8cc")
    int getLineWidth();

    /**
     * Set the text color.
     * @param textColor the text color.
     */
    @objid ("7fa4c44e-1dec-11e2-8cad-001ec947c8cc")
    void setTextColor(Color textColor);

    /**
     * Set the text font.
     * @param textFont the text font.
     */
    @objid ("7fa4c451-1dec-11e2-8cad-001ec947c8cc")
    void setTextFont(Font textFont);

    /**
     * Get the text color.
     * @return the text color.
     */
    @objid ("7fa4c454-1dec-11e2-8cad-001ec947c8cc")
    Color getTextColor();

    /**
     * Get the text font.
     * @return the text font.
     */
    @objid ("7fa72665-1dec-11e2-8cad-001ec947c8cc")
    Font getTextFont();

    /**
     * Sets the line pattern to the argument, which must be one of the constants
     * 
     * {@link LinePattern}
     * @param lineStyle the new style
     */
    @objid ("7fa72668-1dec-11e2-8cad-001ec947c8cc")
    void setLinePattern(LinePattern lineStyle);

    /**
     * Get the line pattern
     * @return lineStyle the line style See {@link LinePattern}
     */
    @objid ("7fa7266b-1dec-11e2-8cad-001ec947c8cc")
    LinePattern getLinePattern();

    /**
     * Make small effort to adapt any IFigure to IPenOptionsSupport.
     * @param fig a IFigure
     * @return the figure if it implements IPenOptionsSupport, or an adapter.
     */
    @objid ("fa961fba-2acc-470b-a09d-66dba2734d26")
    static IPenOptionsSupport adapt(IFigure fig) {
        if (fig instanceof IPenOptionsSupport)
            return (IPenOptionsSupport) fig;
        if (fig instanceof org.eclipse.draw2d.Shape)
            return new ShapeAdapter<>((Shape) fig);
        return new FigureAdapter<>(fig);
    }

    /**
     * Best effort adapter for IFigure that don't implement IPenOptionsSupport.
     * 
     * @param <T> the figure class
     */
    @objid ("0b0304ff-d4d7-4c5c-b6e6-3e4753086974")
    public static class FigureAdapter<T extends IFigure> implements IPenOptionsSupport {
        @objid ("2a839896-29c4-4041-b966-3d2727dda7d6")
        protected final T fig;

        @objid ("0127c9c3-f9d6-4a34-a846-c9788bf7951d")
        private  FigureAdapter(T fig) {
            this.fig = fig;
        }

        @objid ("98231464-1f9b-4881-987a-1e751e191383")
        @Override
        public void setTextFont(Font textFont) {
            this.fig.setFont(textFont);
        }

        @objid ("be3d625d-81d9-48f7-b4e3-1cdff90dc05f")
        @Override
        public void setTextColor(Color textColor) {
            this.fig.setForegroundColor(textColor);
        }

        @objid ("8f666e78-cf02-4df7-834d-a7506ab55c46")
        @Override
        public void setLineWidth(int lineWidth) {
            // ignore
        }

        @objid ("47248559-12df-4da7-a509-19fb3f44f85b")
        @Override
        public void setLinePattern(LinePattern lineStyle) {
            // ignore
        }

        @objid ("7f711c0d-04a9-4fd5-9763-841cbbeb2b5a")
        @Override
        public void setLineColor(Color lineColor) {
            this.fig.setForegroundColor(lineColor);
        }

        @objid ("316263a3-d569-404d-8445-f74873e4f44e")
        @Override
        public Font getTextFont() {
            return this.fig.getFont();
        }

        @objid ("3bab43f7-dc53-4784-8a2c-ed6e56a00aa3")
        @Override
        public Color getTextColor() {
            return this.fig.getForegroundColor();
        }

        @objid ("fd053d6c-0996-46b0-8a46-bdb12cf30425")
        @Override
        public int getLineWidth() {
            return 1;
        }

        @objid ("ce7a24e5-6114-42dd-81be-91f1eab662ec")
        @Override
        public LinePattern getLinePattern() {
            return LinePattern.LINE_SOLID;
        }

        @objid ("e0b6def9-d41a-47ed-8282-22232e712936")
        @Override
        public Color getLineColor() {
            return this.fig.getForegroundColor();
        }

    }

    /**
     * Best effort adapter for {@link Shape} that don't implement {@link IPenOptionsSupport}.
     * 
     * @param <T> the figure class
     */
    @objid ("83b4cdeb-72d4-4b90-8867-82b44fcec2b2")
    public static class ShapeAdapter<T extends Shape> extends FigureAdapter<T> {
        @objid ("a349c803-2f60-4083-bde5-a09b188652ac")
        public  ShapeAdapter(T fig) {
            super(fig);
        }

        @objid ("6e5cdd6b-d363-417f-a6c4-a9636908465b")
        @Override
        public void setLineWidth(int lineWidth) {
            this.fig.setLineWidth(lineWidth);
        }

        @objid ("741d0914-9ab1-41a5-9dd5-69a4564b1954")
        @Override
        public int getLineWidth() {
            return this.fig.getLineWidth();
        }

        @objid ("09cc5069-bf02-46a0-bffe-e991b016da67")
        @Override
        public void setLinePattern(LinePattern lineStyle) {
            this.fig.setLineStyle(LinePattern.LINE_DASH.toSWTConstant());
        }

        @objid ("c1236943-b35a-4f59-b268-334285fd04b2")
        @Override
        public LinePattern getLinePattern() {
            return LinePattern.fromSWTConstant(this.fig.getLineStyle());
        }

    }

}

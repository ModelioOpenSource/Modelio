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
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;
import org.eclipse.gef.Handle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.modelio.platform.ui.UIColor;

/**
 * Helper to make a rotated pin {@link IFigure} or {@link Handle} .
 * <p>
 * Usage :
 * <li> Instantiate it with the connection and the bend point index
 * <li> Use it as routing constraint : it implements {@link Locator}
 * <li> Call {@link #paintFigure(Graphics, boolean)} in your {@link Figure#paintFigure(Graphics)} implementation
 * 
 * @author cma
 * @since 5.0.2
 */
@objid ("fff54539-0c03-4c11-b33e-f2ff714f8451")
public class RotatedPinFigureHelper implements Locator {
    /**
     * The pin needle length
     */
    @objid ("3bfc9f07-0f19-40a0-a9ef-fb8e0748e26e")
    private static final int PIN_LEN = 8;

    /**
     * The pin header circle radius
     */
    @objid ("fc41108b-9124-42d0-b111-a73c173ed67b")
    private static final int PIN_RADIUS = 4;

    /**
     * The connection bendpoint index
     */
    @objid ("c0e629b1-8ac4-41a7-957e-ff97209925a7")
    private int index;

    /**
     * {@link Translatable} implementation that translates 3 points at once.
     */
    @objid ("3b69fef3-8f37-4e6a-85a3-88950313f3c8")
    private static final Translatable tmpTranslator = new Translatable() {
                @Override
                public void performTranslate(int dx, int dy) {
                    tmpPrev.performTranslate(dx, dy);
                    tmpRef.performTranslate(dx, dy);
                    tmpNext.performTranslate(dx, dy);
                }
    
                @Override
                public void performScale(double factor) {
                    tmpPrev.performScale(factor);
                    tmpRef.performScale(factor);
                    tmpNext.performScale(factor);
                }
            };

    @objid ("88e78716-8e89-422b-9d25-cbb6430b8f8b")
    private final Point pinHeader = new PrecisionPoint();

    @objid ("e79f44ba-2736-4d46-94aa-11586c944129")
    private static final Point tmpNext = new PrecisionPoint();

    @objid ("47502b1e-e7a5-4897-8c56-5a094b51ce60")
    private static final Point tmpRef = new PrecisionPoint();

    @objid ("135faf11-94d7-41c9-8e02-5eb5837828e4")
    private static final Point tmpV1 = new PrecisionPoint();

    @objid ("de81b785-d2b0-4ad8-a95d-b85118790610")
    private final Point circleCenter = new PrecisionPoint();

    @objid ("175d6c1b-99c0-4d27-a55a-036ce5c1c7a8")
    private static final Point tmpV2 = new PrecisionPoint();

    @objid ("9bd18282-ba80-4fc4-bcef-9297dc81769c")
    private final Point circleBase = new PrecisionPoint();

    @objid ("fd048c38-f7e3-475f-a906-c68309dbb520")
    private Connection connection;

    @objid ("92e37720-3cf9-4f47-8cc1-a826b76e8cdf")
    private static final Rectangle tmpRect = new Rectangle();

    @objid ("a547495b-d320-4803-a346-7d132cc6d317")
    private static final Point tmpPrev = new PrecisionPoint();

    @objid ("a16d1e34-5e88-49f8-aadf-caecd9ff4873")
    private static final Point tmpNormalVector = new PrecisionPoint();

    @objid ("b627e69a-a98a-4ded-8401-7a989795628d")
    @Override
    public void relocate(IFigure target) {
        Connection connection = getConnection();
        PointList connPoints = connection.getPoints();
        
        if (getIndex() < 1 || getIndex() >= connPoints.size() - 1) {
            target.setVisible(false);
            return;
        }
        
        target.setVisible(true);
        
        connPoints.getPoint(tmpPrev, getIndex()-1);
        connPoints.getPoint(tmpRef, getIndex());
        connPoints.getPoint(tmpNext, getIndex()+1);
        
        connection.translateToAbsolute(tmpTranslator);
        target.translateToRelative(tmpTranslator);
        
        // tmpV1 is the vector from tmpRef to tmpPrev
        tmpV1.setLocation(0, 0);
        addVector (tmpV1, tmpRef, tmpPrev);
        normalizeVector(tmpV1);
        
        // tmpV2 is the vector from tmpRef to tmpNext
        tmpV2.setLocation(0, 0);
        addVector (tmpV2, tmpRef, tmpNext);
        normalizeVector(tmpV2);
        
        // tmpNormalVector is the vector from the bend point to the pin circle
        // Add tmpV1 + tmpV2 and invert direction
        tmpNormalVector.setLocation(tmpV1).translate(tmpV2).negate();
        if (tmpNormalVector.equals(0, 0)) // the 3 points are aligned
            tmpNormalVector.setLocation(1, -1);
        normalizeVector(tmpNormalVector);
        
        // Move the pin header a little away from the bend point,
        this.pinHeader.setLocation(tmpRef).translate(tmpNormalVector).translate(tmpNormalVector);
        
        tmpV1.setLocation(tmpNormalVector).scale(PIN_LEN);
        this.circleBase.setLocation(this.pinHeader).translate(tmpV1);
        
        tmpV1.setLocation(tmpNormalVector).scale(PIN_RADIUS);
        this.circleCenter.setLocation(this.circleBase).translate(tmpV1);
        
        tmpRect.setLocation(this.circleCenter);
        tmpRect.setSize(1, 1);
        tmpRect.expand(PIN_RADIUS+2, PIN_RADIUS+2);
        tmpRect.union(this.pinHeader);
        tmpRect.union(tmpRef);
        tmpRect.expand(3, 3);
        
        target.setBounds(tmpRect);
        
    }

    @objid ("fe8ade16-06ec-462e-9a1f-3f7a581d3d22")
    private int getIndex() {
        return this.index;
    }

    @objid ("a25fd12a-3652-410c-871e-028f9209947d")
    private Connection getConnection() {
        return this.connection;
    }

    /**
     * Add the vector from 'from' to 'to' to the 'result' vector.
     * @param result the result vector
     * @param from start of vector
     * @param to end of vector
     */
    @objid ("2fb72c7b-aff3-41a9-aada-6d508502c9e0")
    private static Point addVector(Point result, Point from, Point to) {
        result.translate(to).translate(-from.preciseX(), -from.preciseY());
        return result;
    }

    /**
     * Draws the handle with fill color and outline color dependent on the primary selection status of the owner editpart.
     * @param g The graphics used to paint the figure.
     * @param isPrimary draw as primary or secondary selection
     */
    @objid ("59a55521-384f-44b8-9cda-53f4c7c28dbe")
    public void paintFigure(Graphics g, boolean isPrimary) {
        tmpRect.setLocation(this.circleCenter);
        tmpRect.setSize(0, 0);
        tmpRect.expand(PIN_RADIUS, PIN_RADIUS);
        
        g.setAntialias(1);
        //g.setAlpha(220);
        
        Color lineColor = UIColor.BLACK; //getFillColor();
        Color fillColor = UIColor.WHITE; //getBorderColor();
        
        
        // Draw pin
        g.setForegroundColor(lineColor );
        g.setLineCap(SWT.CAP_ROUND);
        g.setLineWidth(2);
        g.drawLine(this.pinHeader, this.circleBase);
        
        // Draw circle
        //g.setAlpha(220);
        g.setForegroundColor(lineColor );
        g.drawOval(tmpRect);
        
        g.setForegroundColor(fillColor );
        tmpRect.shrink(1, 1);
        g.drawOval(tmpRect);
        g.fillOval(tmpRect);
        
        
        if (isPrimary) {
            tmpRect.shrink(1, 1);
            //g.setAlpha(220);
            g.setBackgroundColor(lineColor);
            g.fillOval(tmpRect);
        }
        
    }

    @objid ("0e55cca2-3773-4bc6-8125-121cb18a6b59")
    private static Point normalizeVector(Point v) {
        double vecLen = Math.sqrt(v.preciseX() * v.preciseX() + v.preciseY() * v.preciseY());
        v.scale(1.0 / vecLen);
        return v;
    }

    /**
     * @param owner the connection to attach to
     * @param index the bendpoint index. Must be 1 <= index < owner.getPoints().size()-1
     */
    @objid ("2143e89b-8aba-4096-bfb5-6ae533db0334")
    public  RotatedPinFigureHelper(Connection owner, int index) {
        this.connection = owner;
        this.index = index;
        
    }

    /**
     * Rotated pin figure to be put on a connection.
     * <p>
     * Add it to the connection with {@link #getLocator()} as routing constraint.
     * @author cma
     * @since 5.0.2
     */
    @objid ("cd4bab4a-431c-4115-a2ff-710b803f4a5f")
    public static class RotatedPinFigure extends Figure {
        @objid ("fe78f07c-da51-4af8-b6c2-53ea28c4b189")
        private final RotatedPinFigureHelper helper;

        @objid ("31c3ef96-f1f7-425d-9f80-0e43849c19d3")
        public  RotatedPinFigure(Connection owner, int index) {
            super();
            this.helper = new RotatedPinFigureHelper(owner, index);
            
        }

        @objid ("0b739fc4-9490-4d06-b0fa-c349efb20ea0")
        public Locator getLocator() {
            return this.helper;
        }

        @objid ("134de2af-ac2d-4c5d-ac0e-db5d3ca72187")
        @Override
        protected void paintFigure(Graphics graphics) {
            this.helper.paintFigure(graphics, true);
        }

    }

}

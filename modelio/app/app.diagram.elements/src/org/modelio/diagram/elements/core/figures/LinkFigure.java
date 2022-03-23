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
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Figure class to display GmLinks.
 * <p>
 * It inherits from {@link PolylineConnection} and implements {@link IPenOptionsSupport}. This class may be used as is or redefined.
 * 
 * @author cmarin
 */
@objid ("7fa7269a-1dec-11e2-8cad-001ec947c8cc")
public class LinkFigure extends PolylineConnection implements IPenOptionsSupport {
    /**
     * When creating a link figure, temporary use this as default path.
     */
    @objid ("81dd60e0-a1f4-46ce-a0c5-7a29e3ab1609")
    public static final int[] DEFAULT_POINTS = { 0, 0, 0, 0 };

    @objid ("7fa7269e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getLineColor() {
        return this.getForegroundColor();
    }

    @objid ("7fa726a3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getTextColor() {
        return null;
    }

    @objid ("7fa726a8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Font getTextFont() {
        return null;
    }

    @objid ("7fa726ad-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        this.setForegroundColor(lineColor);
    }

    @objid ("7fa726b1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        // Nothing to do
    }

    @objid ("7fa726b5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        // Nothing to do
    }

    @objid ("7fa726b9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RotatableDecoration getSourceDecoration() {
        // Redefined to be public
        return super.getSourceDecoration();
    }

    @objid ("7fa726c0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RotatableDecoration getTargetDecoration() {
        // Redefined to be public
        return super.getTargetDecoration();
    }

    @objid ("7fa988c3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void outlineShape(Graphics g) {
        ZoomDrawer.setLineWidth(g, this.getLineWidth());
        super.outlineShape(g);
        
    }

    @objid ("7fa988c9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LinePattern getLinePattern() {
        return LinePattern.fromSWTConstant(this.getLineStyle());
    }

    @objid ("7fa988ce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        this.setLineStyle(linePattern.toSWTConstant());
    }

    /**
     * Creates an instance of LinkFigure. The tolerance (used in {@link #containsPoint(int, int)}) is set to 3 (default from {@link Polyline} is 2).
     */
    @objid ("7fa988d2-1dec-11e2-8cad-001ec947c8cc")
    public  LinkFigure() {
        super();
        setTolerance(3);
        
        // Use a specific path as default points
        removeAllPoints();
        for (int i = 0; i < DEFAULT_POINTS.length - 1; i = i + 2) {
            addPoint(new Point(DEFAULT_POINTS[i], DEFAULT_POINTS[i + 1]));
        }
        
    }

}

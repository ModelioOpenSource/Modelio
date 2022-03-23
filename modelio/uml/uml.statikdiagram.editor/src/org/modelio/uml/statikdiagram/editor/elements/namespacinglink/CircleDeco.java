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
package org.modelio.uml.statikdiagram.editor.elements.namespacinglink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;
import org.modelio.diagram.elements.core.figures.GradientFigure;

@objid ("35a6709a-55b7-11e2-877f-002564c97630")
public class CircleDeco extends GradientFigure implements RotatableDecoration {
    @objid ("35a6709d-55b7-11e2-877f-002564c97630")
    private static final int CIRCLE_OFFSET = 4;

    @objid ("d82d59e1-51da-4562-880b-44e002cde100")
    private Point location = new Point();

    @objid ("cf186e95-113e-43b2-9203-ebcfccd31e73")
    private Transform transform = new Transform();

    @objid ("69781b2c-5227-430a-8bff-8c5ab5093cfa")
    private static final PrecisionPoint tmp = new PrecisionPoint();

    @objid ("35a670a3-55b7-11e2-877f-002564c97630")
    public  CircleDeco() {
        super();
        setSize(CIRCLE_OFFSET * 2, CIRCLE_OFFSET * 2);
        
    }

    @objid ("35a670a5-55b7-11e2-877f-002564c97630")
    @Override
    public void setReferencePoint(final Point ref) {
        Point pt = Point.SINGLETON;
        pt.setLocation(ref);
        pt.negate().translate(this.location);
        this.transform.setRotation(Math.atan2(pt.y, pt.x));
        
    }

    @objid ("35a670aa-55b7-11e2-877f-002564c97630")
    @Override
    public void setLocation(final Point p) {
        this.location.setLocation(p);
        this.transform.setTranslation(p.x, p.y);
        
    }

    @objid ("35a670af-55b7-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(final Graphics graphics) {
        tmp.setLocation(-CIRCLE_OFFSET, 0);
        tmp.setLocation(this.transform.getTransformed(tmp));
        graphics.setForegroundColor(this.getLineColor());
        graphics.setBackgroundColor(this.getFillColor());
        graphics.setClip(new Rectangle(tmp.x() - (CIRCLE_OFFSET + 1),
                                       tmp.y() - (CIRCLE_OFFSET + 1),
                                       (CIRCLE_OFFSET + 1) * 2,
                                       (CIRCLE_OFFSET + 1) * 2));
        graphics.fillArc(tmp.x() - CIRCLE_OFFSET,
                         tmp.y() - CIRCLE_OFFSET,
                         (CIRCLE_OFFSET * 2) + 1,
                         (CIRCLE_OFFSET * 2) + 1,
                         0,
                         360);
        //super.paintFigure(graphics);
        
    }

    @objid ("35a670b4-55b7-11e2-877f-002564c97630")
    @Override
    protected void paintBorder(final Graphics graphics) {
        tmp.setLocation(-CIRCLE_OFFSET, 0);
        tmp.setLocation(this.transform.getTransformed(tmp));
        graphics.setForegroundColor(this.getLineColor());
        graphics.setBackgroundColor(this.getFillColor());
        graphics.setClip(new Rectangle(tmp.x() - (CIRCLE_OFFSET + 1),
                                       tmp.y() - (CIRCLE_OFFSET + 1),
                                       (CIRCLE_OFFSET + 1) * 2,
                                       (CIRCLE_OFFSET + 1) * 2));
        graphics.drawArc(tmp.x() - CIRCLE_OFFSET,
                         tmp.y() - CIRCLE_OFFSET,
                         CIRCLE_OFFSET * 2,
                         CIRCLE_OFFSET * 2,
                         0,
                         360);
        graphics.drawLine(tmp.x() - CIRCLE_OFFSET, tmp.y(), tmp.x() + CIRCLE_OFFSET, tmp.y());
        graphics.drawLine(tmp.x(), tmp.y() - CIRCLE_OFFSET, tmp.x(), tmp.y() + CIRCLE_OFFSET);
        //super.paintBorder(graphics);
        
    }

    @objid ("35a670b9-55b7-11e2-877f-002564c97630")
    @Override
    public Rectangle getBounds() {
        tmp.setLocation(-10, 0);
        tmp.setLocation(this.transform.getTransformed(tmp));
        return new Rectangle(tmp.x() - (CIRCLE_OFFSET + 1),
                                                             tmp.y() - (CIRCLE_OFFSET + 1),
                                                             (CIRCLE_OFFSET + 1) * 2,
                                                             (CIRCLE_OFFSET + 1) * 2);
        
    }

}

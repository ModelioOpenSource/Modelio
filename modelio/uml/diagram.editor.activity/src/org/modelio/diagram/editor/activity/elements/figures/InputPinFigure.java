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

package org.modelio.diagram.editor.activity.elements.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.figures.IOrientableShaper.Orientation;
import org.modelio.diagram.elements.core.figures.IShaper;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;

@objid ("2a6da078-55b6-11e2-877f-002564c97630")
public class InputPinFigure extends ShapedFigure {
    @objid ("2a6da07c-55b6-11e2-877f-002564c97630")
    protected Orientation orientation = Orientation.Undefined;

    @objid ("2a6da07b-55b6-11e2-877f-002564c97630")
    private ShapedBorder shapedBorder;

    @objid ("2a6da07f-55b6-11e2-877f-002564c97630")
    public InputPinFigure() {
        super();
        setShaper(new InputPinFigureShaper(this));
        this.setOpaque(true);
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor,
                this.penOptions.lineWidth,
                this.shaper);
        
        setBorder(this.shapedBorder);
    }

    @objid ("2a6f26fa-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        this.shapedBorder.setColor(lineColor);
        super.setLineColor(lineColor);
    }

    @objid ("2a6f26fe-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        this.shapedBorder.setWidth(lineWidth);
        super.setLineWidth(lineWidth);
    }

    @objid ("2a6f2702-55b6-11e2-877f-002564c97630")
    public void setOrientation(Border b) {
        switch (b) {
        case North:
            this.orientation = Orientation.NorthSouth;
            break;
        case South:
            this.orientation = Orientation.SouthNorth;
            break;
        case East:
            this.orientation = Orientation.EastWest;
            break;
        case West:
            this.orientation = Orientation.WestEast;
            break;
        default:
            this.orientation = Orientation.Undefined;
            break;
        }
    }

    @objid ("2a6f2707-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics g) {
        super.paintFigure(g);
        
        // Rectangle rect = this.getBounds();
        // int x = rect.x;
        // int y = rect.y;
        // int w = rect.width;
        // int h = rect.height;
        //
        // switch (this.orientation) {
        // case NorthSouth: {
        // Point p1 = new Point(x + w / 2, y + 2);
        // Point p2 = new Point(x + w / 2, y + h - 4);
        // g.drawLine(p1, p2);
        // g.drawLine(p2, p2.getCopy().translate(w / 3, -h / 3));
        // g.drawLine(p2, p2.getCopy().translate(-w / 3, -h / 3));
        // }
        // break;
        // case SouthNorth: {
        // Point p1 = new Point(x + w / 2, y + 2);
        // Point p2 = new Point(x + w / 2, y + h - 4);
        // g.drawLine(p1, p2);
        // g.drawLine(p1, p1.getCopy().translate(w / 3, h / 3));
        // g.drawLine(p1, p1.getCopy().translate(-w / 3, h / 3));
        //
        // }
        // break;
        // case EastWest: {
        // Point p1 = new Point(x + 2, y + h / 2);
        // Point p2 = new Point(x + w - 4, y + h / 2);
        // g.drawLine(p1, p2);
        // g.drawLine(p1, p1.getCopy().translate(w / 3, -h / 3));
        // g.drawLine(p1, p1.getCopy().translate(w / 3, h / 3));
        //
        // }
        // break;
        // case WestEast: {
        // Point p1 = new Point(x + 2, y + h / 2);
        // Point p2 = new Point(x + w - 4, y + h / 2);
        // g.drawLine(p1, p2);
        // g.drawLine(p2, p2.getCopy().translate(-w / 3, -h / 3));
        // g.drawLine(p2, p2.getCopy().translate(-w / 3, h / 3));
        //
        // }
        // break;
        // default:
        // break;
        //
        // }
    }

    @objid ("6b6d75e9-66d7-4115-a066-12787523898e")
    public Orientation getOrientation() {
        return this.orientation;
    }

    @objid ("379c4819-1d93-48ba-b7b8-7fa12a460ee0")
    @Override
    protected Rectangle getPaintRectangle() {
        return super.getPaintRectangle();
    }

    @objid ("1c653e0b-2808-42e0-b92a-008238af4826")
    public static class InputPinFigureShaper implements IShaper {
        @objid ("41b67bca-3442-4493-a13b-30c9ed4d55bf")
        private InputPinFigure inputPinFigure;

        @objid ("a7cc9ad7-4728-49cb-b952-5ad3b8ed7d06")
        public InputPinFigureShaper(InputPinFigure inputPinFigure) {
            this.inputPinFigure = inputPinFigure;
        }

        @objid ("b361803a-eff9-4868-8a99-1d9bec74637d")
        @Override
        public Path getShapePath(Rectangle rect) {
            int x = rect.x;
            int y = rect.y;
            int w = rect.width;
            int h = rect.height;
            
            Path path = new Path(Display.getCurrent());
            
            path.addRectangle(x, y, w, h);
            
            switch (this.inputPinFigure.getOrientation()) {
            case NorthSouth: {
                Point p1 = new Point(x + w / 2, y + 2);
                Point p2 = new Point(x + w / 2, y + h - 2);
                Point p3 = p2.getCopy().translate(w / 3, -h / 3);
                Point p4 = p2.getCopy().translate(-w / 3, -h / 3);
            
                path.moveTo(p1.x, p1.y);
                path.lineTo(p2.x, p2.y);
                path.lineTo(p3.x, p3.y);
                path.moveTo(p2.x, p2.y);
                path.lineTo(p4.x, p4.y);
            }
                break;
            case SouthNorth: {
                Point p2 = new Point(x + w / 2, y + 2);
                Point p1 = new Point(x + w / 2, y + h - 2);
                Point p3 = p2.getCopy().translate(w / 3, h / 3);
                Point p4 = p2.getCopy().translate(-w / 3, h / 3);
            
                path.moveTo(p1.x, p1.y);
                path.lineTo(p2.x, p2.y);
                path.lineTo(p3.x, p3.y);
                path.moveTo(p2.x, p2.y);
                path.lineTo(p4.x, p4.y);
            }
            
                break;
            case EastWest: {
                Point p2 = new Point(x + 2, y + h / 2);
                Point p1 = new Point(x + w - 2, y + h / 2);
                Point p3 = p2.getCopy().translate(w / 3, -h / 3);
                Point p4 = p2.getCopy().translate(w / 3, h / 3);
            
                path.moveTo(p1.x, p1.y);
                path.lineTo(p2.x, p2.y);
                path.lineTo(p3.x, p3.y);
                path.moveTo(p2.x, p2.y);
                path.lineTo(p4.x, p4.y);
            }
            
                break;
            case WestEast: {
                {
                    Point p1 = new Point(x + 2, y + h / 2);
                    Point p2 = new Point(x + w - 2, y + h / 2);
                    Point p3 = p2.getCopy().translate(-w / 3, -h / 3);
                    Point p4 = p2.getCopy().translate(-w / 3, h / 3);
            
                    path.moveTo(p1.x, p1.y);
                    path.lineTo(p2.x, p2.y);
                    path.lineTo(p3.x, p3.y);
                    path.moveTo(p2.x, p2.y);
                    path.lineTo(p4.x, p4.y);
                }
            
            }
                break;
            default:
                break;
            
            }
            return path;
        }

        @objid ("1f83786c-d030-44a3-9dd4-77f49e28b7c2")
        @Override
        public Insets getShapeInsets(Rectangle rect) {
            return NO_INSETS;
        }

    }

}

/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.core.figures.IShaper;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;

/**
 * Shaped figure drawing the shape of a package.
 */
@objid ("362b3109-55b7-11e2-877f-002564c97630")
public class PackageSimpleFigure extends ShapedFigure {
    @objid ("362b310d-55b7-11e2-877f-002564c97630")
    private static final IShaper shaper = new PackageShaper();

    @objid ("362b3113-55b7-11e2-877f-002564c97630")
    private static final int MARGIN = 2;

    @objid ("a4f96dcb-55c2-11e2-9337-002564c97630")
    private ShapedBorder shapedBorder;

    /**
     * C'tor.
     */
    @objid ("362b3115-55b7-11e2-877f-002564c97630")
    public PackageSimpleFigure() {
        super(shaper);
        
        this.shapedBorder = new ShapedBorder(getLineColor(), getLineWidth(), shaper);
        this.shapedBorder.setStyle(getLinePattern().toSWTConstant());
        
        setBorder(new CompoundBorder(this.shapedBorder, new MarginBorder(PackageSimpleFigure.MARGIN)));
    }

    @objid ("9ef49249-63eb-42e0-a0d0-b2575ebfc301")
    @Override
    public void setLineColor(Color lineColor) {
        this.shapedBorder.setColor(lineColor);
        super.setLineColor(lineColor);
    }

    @objid ("0ff7d9bf-d6f4-478a-abff-81230f0d90b3")
    @Override
    public void setLineWidth(int lineWidth) {
        this.shapedBorder.setWidth(lineWidth);
        super.setLineWidth(lineWidth);
    }

    @objid ("e5d8d77a-01c6-4fe1-ab25-704c1efae613")
    @Override
    protected Rectangle getPaintRectangle() {
        return getBounds().getCopy();
    }

    @objid ("362b3120-55b7-11e2-877f-002564c97630")
    private static final class PackageShaper implements IShaper {
        @objid ("362b3124-55b7-11e2-877f-002564c97630")
        public PackageShaper() {
        }

        @objid ("362b3126-55b7-11e2-877f-002564c97630")
        @Override
        public Path getShapePath(final Rectangle rect) {
            int x = rect.x;
            int y = rect.y;
            int w = rect.width;
            int h = rect.height;
            int d = 20;
            if (d > h / 5) {
                d = h / 5;
            }
            
            Path path = new Path(Display.getCurrent());
            
            path.moveTo(x, y);
            path.lineTo(x + (w / 3), y);
            path.lineTo(x + (w / 3), y + d);
            path.lineTo(x, y + d);
            path.lineTo(x + w, y + d);
            path.lineTo(x + w, y + h);
            path.lineTo(x, y + h);
            path.lineTo(x, y);
            return path;
        }

        @objid ("362b312d-55b7-11e2-877f-002564c97630")
        @Override
        public Insets getShapeInsets(final Rectangle rect) {
            int d = 20;
            if (d > rect.height() / 5) {
                d = rect.height() / 5;
            }
            return new Insets(d, 0, 0, 0);
        }

    }

}

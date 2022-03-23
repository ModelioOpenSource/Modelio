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
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This class implements a right-aligned ToolBarLayout, ie children are stacked on the right side of the toolbar.
 * <p>
 * NOTE: A ToolbarRLayout is always horizontal.
 */
@objid ("e2e5457c-7f4a-4237-b8fa-0e760a27a32b")
public class ToolbarRLayout extends ToolbarLayout {
    @objid ("886962e1-4752-473e-8ef9-5b370b998c79")
    @Override
    public void layout(IFigure parent) {
        super.layout(parent);
        final int Xl = parent.getBounds().x;
        final int Xr = parent.getBounds().right();
        
        for (Object obj : parent.getChildren()) {
            final IFigure child = (IFigure) obj;
            final Rectangle aBounds = child.getBounds().getCopy();
            child.translate(Xr - (aBounds.x - Xl) - aBounds.x - aBounds.width, 0);
        }
        
    }

    /**
     * C'tor.
     */
    @objid ("3086b4c1-fb72-41ec-ad42-228c32fd6e63")
    public  ToolbarRLayout() {
        super(true); // force horizontal
    }

}

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
package org.modelio.uml.statediagram.editor.elements.shallowhistory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.statediagram.editor.elements.common.state.AbstractStateEditPart;

/**
 * EditPart for an {@link GmShallowHistoryPrimaryNode} Node.
 * 
 * @author sbe
 */
@objid ("f5764003-55b6-11e2-877f-002564c97630")
public class ShallowHistoryEditPart extends AbstractStateEditPart {
    @objid ("f5764007-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        EllipseFigure fig = new EllipseFigure();
        fig.setLabel("H");
        
        // set style independent properties
        fig.setPreferredSize(20, 20);
        fig.setMinimumSize(new Dimension(20, 20));
        fig.setOpaque(true);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("f576400f-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmShallowHistoryPrimaryNode shallowHistoryModel = (GmShallowHistoryPrimaryNode) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), shallowHistoryModel.getLayoutData());
        
    }

    @objid ("f5764012-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("f5764017-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof EllipseFigure) {
            if (switchRepresentationMode()) {
                return;
            }
        }
        
        super.refreshFromStyle(aFigure, style);
        
        for (Object c : aFigure.getChildren()) {
            super.refreshFromStyle((IFigure) c, style);
        }
        
    }

}

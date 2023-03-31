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
package org.modelio.uml.statediagram.editor.elements.entry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.link.anchors.fixed2.DefaultFixedAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFixedNodeAnchorProvider;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.statediagram.editor.elements.common.state.AbstractStateEditPart;

/**
 * EditPart for an Entry Node.
 * 
 * @author fpoyer
 */
@objid ("f50cf6ee-55b6-11e2-877f-002564c97630")
public class EntryEditPart extends AbstractStateEditPart {
    @objid ("2bb05c13-bf05-430b-a6c3-fe878dfceb3d")
    Label labelFigure;

    @objid ("f50e7d7d-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        EllipseFigure fig = new EllipseFigure();
        
        // set style independent properties
        fig.setPreferredSize(20, 20);
        fig.setMinimumSize(new Dimension(20, 20));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("f50e7d85-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmEntryPrimaryNode entryModel = (GmEntryPrimaryNode) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), entryModel.getLayoutData());
        
    }

    @objid ("f50e7d88-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("f50e7d8d-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof EllipseFigure) {
            if (switchRepresentationMode()) {
                return;
            }
        }
        
        super.refreshFromStyle(aFigure, style);
        
    }

    /**
     * Create the {@link IFixedNodeAnchorProvider} for this edit part.
     * @return the created anchor provider.
     */
    @objid ("3e1fd320-1005-4c40-94c7-fa83f738511e")
    protected IFixedNodeAnchorProvider createAnchorProvider() {
        return DefaultFixedAnchorProvider.ellipseFor(this);
    }

}

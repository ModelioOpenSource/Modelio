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
package org.modelio.uml.statediagram.editor.elements.terminal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.link.anchors.fixed2.DefaultFixedAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFixedNodeAnchorProvider;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.statediagram.editor.elements.common.state.AbstractStateEditPart;

/**
 * EditPart for an Terminal Node.
 * 
 * @author fpoyer
 */
@objid ("f5a71407-55b6-11e2-877f-002564c97630")
public class TerminalEditPart extends AbstractStateEditPart {
    @objid ("da473432-b11e-4186-9d1e-dcde71537b67")
    Label labelFigure;

    @objid ("f5a7140c-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        TerminateFigure fig = new TerminateFigure();
        
        // set style independent properties
        fig.setPreferredSize(20, 20);
        fig.setMinimumSize(new Dimension(20, 20));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("f5a71414-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmTerminalPrimaryNode terminalModel = (GmTerminalPrimaryNode) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), terminalModel.getLayoutData());
        
    }

    @objid ("f5a71417-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("f5a7141c-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure.getClass() == TerminateFigure.class) {
            if (switchRepresentationMode()) {
                return;
            }
        }
        
        super.refreshFromStyle(aFigure, style);
        
    }

    @objid ("cfa9ca81-0650-4070-b814-a5372b8be599")
    @Override
    protected IFixedNodeAnchorProvider createAnchorProvider() {
        return DefaultFixedAnchorProvider.defaultFor(this);
    }

}

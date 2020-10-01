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

package org.modelio.uml.sequencediagram.editor.elements.lifeline.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Editpart for the container of lifeline header.
 * 
 * @author fpoyer
 */
@objid ("d946acab-55b6-11e2-877f-002564c97630")
public class LifelineHeaderContainerEditPart extends AbstractNodeEditPart {
    @objid ("d946acaf-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("d946acb4-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure fig = new Figure();
        // Set style independent properties.
        fig.setOpaque(false);
        fig.setLayoutManager(new BorderLayout());
        // Set style dependenty properties.
        refreshFromStyle(fig, ((GmAbstractObject) getModel()).getDisplayedStyle());
        return fig;
    }

    @objid ("d946acb9-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure headerFigure, final IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(headerFigure, style);
        }
    }

    @objid ("d946acc2-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        getContentPane().add(child, BorderLayout.CENTER, index);
    }

}

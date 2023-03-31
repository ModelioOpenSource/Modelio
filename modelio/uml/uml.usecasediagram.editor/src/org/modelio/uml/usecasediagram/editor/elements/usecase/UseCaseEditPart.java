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
package org.modelio.uml.usecasediagram.editor.elements.usecase;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.link.anchors.INodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed2.DefaultFixedAnchorProvider;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.statikdiagram.editor.elements.policies.SmartGeneralizationEditPolicy;

@objid ("5e6a3b50-55b7-11e2-877f-002564c97630")
public class UseCaseEditPart extends AbstractNodeEditPart {
    @objid ("5e6a3b54-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // UseCaseFigure
        final UseCaseFigure classFigure = new UseCaseFigure();
        
        // Set style independent properties
        classFigure.setOpaque(true);
        
        // Set default size
        MinimumSizeLayout.apply(classFigure, 200, 75);
        
        // Set style dependent properties
        refreshFromStyle(classFigure, getModelStyle());
        return classFigure;
    }

    @objid ("5e6a3b59-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
        
    }

    @objid ("09970b1c-5f47-4ed0-b661-90ee07e5d57c")
    @Override
    protected INodeAnchorProvider createAnchorProvider() {
        return DefaultFixedAnchorProvider.ellipseFor(this);
    }

    @objid ("5e6bc1bf-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new SmartGeneralizationEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DeferringCreateNodePolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("5e6bc1c2-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        
    }

    @objid ("5e6bc1c5-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        final IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        if (index == 0) {
            getFigure().add(child, BorderLayout.TOP, index);
        } else {
            getFigure().add(child, BorderLayout.CENTER, index);
        }
        
    }

    @objid ("5e6bc1ca-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

}

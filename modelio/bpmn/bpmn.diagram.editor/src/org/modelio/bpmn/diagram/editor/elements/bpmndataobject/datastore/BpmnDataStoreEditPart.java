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

package org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datastore;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.graphics.Color;
import org.modelio.bpmn.diagram.editor.editor.BpmnSharedImages;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.BpmnDataFigure;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.BpmnItemAwareElementElementDropEditPolicy;
import org.modelio.bpmn.diagram.editor.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.ColorizableImageFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * Edit part for {@link GmBpmnDataStorePrimaryNode}.
 */
@objid ("60ccb19a-55b6-11e2-877f-002564c97630")
public class BpmnDataStoreEditPart extends AbstractNodeEditPart {
    @objid ("60ccb19e-55b6-11e2-877f-002564c97630")
    @Override
    public void activate() {
        super.activate();
    }

    @objid ("60ccb1a1-55b6-11e2-877f-002564c97630")
    @Override
    public void deactivate() {
        super.deactivate();
    }

    /**
     * @see AbstractNodeEditPart#propertyChange(java.beans.PropertyChangeEvent)
     */
    @objid ("60ccb1a4-55b6-11e2-877f-002564c97630")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        } else {
            super.propertyChange(evt);
        }
    }

    /**
     * @see AbstractNodeEditPart#createEditPolicies()
     */
    @objid ("60ccb1a9-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy(ModelElementDropRequest.TYPE, new BpmnItemAwareElementElementDropEditPolicy());
    }

    /**
     * Creates the Figure to be used as this part's visuals
     * @see AbstractNodeEditPart#createFigure()
     */
    @objid ("60ccb1ad-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        BpmnDataFigure figure1 = new BpmnDataFigure();
        
        // set style independent properties
        MinimumSizeLayout.apply(figure1, 40, 55);
        figure1.setOpaque(true);
        
        figure1.setCenterIcon(DiagramEditorBpmn.getImageRegistry().getImage(BpmnSharedImages.STORE));
        
        // set style dependent properties
        refreshFromStyle(figure1, getModelStyle());
        
        // return the figure
        return figure1;
    }

    /**
     * Get the note figure.
     * 
     * @return The note figure.
     */
    @objid ("60ccb1b3-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnDataFigure getFigure() {
        return (BpmnDataFigure) super.getFigure();
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("60ccb1b8-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final BpmnDataFigure fig = getFigure();
        final GmBpmnDataStorePrimaryNode gm = (GmBpmnDataStorePrimaryNode) getModel();
        fig.getParent().setConstraint(fig, gm.getLayoutData());
        fig.setTopIcons(gm.getReferenceIcone());
    }

    @objid ("60ccb1bc-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        if (index == 0) {
            getFigure().add(child, BorderLayout.CENTER, index);
        }
    }

    @objid ("60ccb1c1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("60ccb1c5-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        
            if (aFigure instanceof ColorizableImageFigure) {
                ColorizableImageFigure cFigure = (ColorizableImageFigure) aFigure;
                final GmModel gmModel = getModel();
                Color color = style.getColor(gmModel.getStyleKey(MetaKey.FILLCOLOR));
                cFigure.setColor(color);
            }
        }
    }

}

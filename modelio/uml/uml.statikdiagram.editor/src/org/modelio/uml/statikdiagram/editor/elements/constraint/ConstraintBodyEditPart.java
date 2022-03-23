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
package org.modelio.uml.statikdiagram.editor.elements.constraint;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkCommand;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * EditPart for the GmConstraintBody.
 * 
 * @author fpoyer
 */
@objid ("81165088-1dec-11e2-8cad-001ec947c8cc")
public class ConstraintBodyEditPart extends AbstractNodeEditPart {
    @objid ("8116508a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LINK_SOURCE)) {
            // This property change event may be used to signal that some links are missing.
            Object newValue = evt.getNewValue();
            if (newValue instanceof ModelElement) {
                createMissingLinkForElement((ModelElement) newValue);
            }
        }
        // In any case apply the super routine.
        super.propertyChange(evt);
        
    }

    @objid ("8116508f-1dec-11e2-8cad-001ec947c8cc")
    private void createMissingLinkForElement(final ModelElement constrainedElement) {
        IGmLinkable sourceModel = getModel();
        GmConstraintLink link = new GmConstraintLink(sourceModel.getDiagram(),
                new MRef(sourceModel.getRelatedElement()),
                constrainedElement,
                new MRef(constrainedElement));
        
        sourceModel.addStartingLink(link);
        CreateBendedConnectionRequest request = new CreateBendedConnectionRequest();
        request.setType(RequestConstants.REQ_CONNECTION_END);
        request.setSourceEditPart(this);
        request.setLocation(new Point(0, 0));
        ModelioLinkCreationContext context = new ModelioLinkCreationContext(sourceModel.getRelatedElement());
        request.setFactory(context);
        DefaultCreateLinkCommand startCommand = new DefaultCreateLinkCommand(context);
        startCommand.setSource(sourceModel);
        request.setStartCommand(startCommand);
        
        // Search on the edit part composition
        Collection<GmModel> constrainedElementModels = sourceModel.getDiagram().getAllGMRelatedTo(new MRef(constrainedElement));
        for (GmModel constrainedElementModel : constrainedElementModels) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) getViewer()
                    .getEditPartRegistry()
                    .get(constrainedElementModel);
            if (editPart != null) {
                editPart = editPart.getParent();
                // See if this edit part accepts the reconnection request
                EditPart targetEditPart = editPart.getTargetEditPart(request);
                if (targetEditPart != null) {
                    // found a valid target: add the link to it!
                    IGmLinkable targetModel = (IGmLinkable) targetEditPart.getModel();
                    targetModel.addEndingLink(link);
                    return;
                }
            }
        }
        
        // No target found, look for parent edit parts...
        for (GmModel constrainedElementModel : constrainedElementModels) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) getViewer()
                    .getEditPartRegistry()
                    .get(constrainedElementModel);
            if (editPart != null) {
                // See if this edit part accepts the reconnection request
                EditPart targetEditPart = editPart.getTargetEditPart(request);
                if (targetEditPart != null) {
                    // found a valid target: add the link to it!
                    IGmLinkable targetModel = (IGmLinkable) targetEditPart.getModel();
                    targetModel.addEndingLink(link);
                    return;
                }
            }
        }
        
    }

    @objid ("81165093-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        GradientFigure fig = new GradientFigure();
        fig.setOpaque(false);
        fig.setLayoutManager(new BorderLayout());
        
        MinimumSizeLayout.apply(fig, 100, 15);
        
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("8116509a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        getContentPane().add(child, BorderLayout.CENTER, index);
        
    }

    @objid ("811650a3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        
    }

}

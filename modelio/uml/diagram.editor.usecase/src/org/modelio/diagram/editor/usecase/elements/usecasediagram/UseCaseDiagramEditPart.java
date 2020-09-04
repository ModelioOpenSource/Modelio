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

package org.modelio.diagram.editor.usecase.elements.usecasediagram;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.editor.usecase.elements.system.GmSystem;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCase;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.CreateLinkIntermediateEditPolicy;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * EditPart for UseCase diagram background.
 */
@objid ("5e873928-55b7-11e2-877f-002564c97630")
public class UseCaseDiagramEditPart extends AbstractDiagramEditPart {
    @objid ("5e87392c-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure diagramFigure = new UseCaseDiagramFigure();
        IStyle style = ((GmAbstractObject) this.getModel()).getDisplayedStyle();
        
        // Set style independent properties
        
        // Set style dependent properties
        refreshFromStyle(diagramFigure, style);
        return diagramFigure;
    }

    @objid ("5e873932-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Policy to add nodes on the diagram
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new UseCaseDiagramLayoutEditPolicy());
        
        // Policy to create notes
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_END,
                new LinkedNodeFinishCreationEditPolicy());
        
        // Policy to add bend points to connections being created
        installEditPolicy(CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT,
                new CreateLinkIntermediateEditPolicy());
    }

    @objid ("5e88bf99-55b7-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Style")) {
            refreshUseCaseOwnership();
        }
        super.propertyChange(evt);
    }

    @objid ("5e88bf9e-55b7-11e2-877f-002564c97630")
    private void refreshUseCaseOwnership() {
        GmUseCaseDiagram diagram = (GmUseCaseDiagram) getModel();
        GmSystem model = diagram.getSystem();
        boolean isSystemVisible = model.getDisplayedStyle().getProperty(GmUseCaseDiagramStyleKeys.SHOW_SYSTEM);
        if (!isSystemVisible) {
            for (GmNodeModel child : model.getBody().getChildren()) {
                GraphicalEditPart ep = (GraphicalEditPart) getViewer().getEditPartRegistry().get(child);
                if (ep != null) {
                    Rectangle ucBounds = ep.getFigure().getBounds().getCopy();
                    ep.getFigure().translateToAbsolute(ucBounds);
                    getFigure().translateToRelative(ucBounds);
                    child.setLayoutData(ucBounds);
                }
            }
            for (GmNodeModel child : model.getBody().getChildren()) {
                model.getBody().removeChild(child);
                diagram.addChild(child);
            }
        } else {
            Map<GmUseCase, Rectangle> layoutData = new HashMap<>();
            for (GmNodeModel child : diagram.getChildren()) {
                if (child instanceof GmUseCase) {
                    GmUseCase useCaseNode = (GmUseCase) child;
                    MObject theUseCase = useCaseNode.getRepresentedElement();
                    if (theUseCase != null && isInSystem(model, theUseCase)) {
                        GraphicalEditPart uCEp = (GraphicalEditPart) getViewer().getEditPartRegistry()
                                .get(useCaseNode);
                        Rectangle ucBounds = null;
                        if (uCEp != null) {
                            ucBounds = uCEp.getFigure().getBounds().getCopy();
                            if (model.getLayoutData() == null) {
                                Rectangle systemBounds = ucBounds.getCopy();
                                systemBounds.setSize(-1, -1);
                                model.setLayoutData(systemBounds);
                            }
                            uCEp.getFigure().translateToAbsolute(ucBounds);
                            layoutData.put(useCaseNode, ucBounds);
                        }
                        diagram.removeChild(child);
                        model.getBody().addChild(child);
                    }
                }
            }
            getFigure().validate();
            for (GmNodeModel child : model.getBody().getChildren()) {
                GmUseCase useCaseGm = (GmUseCase) child;
                GraphicalEditPart useCaseEp = (GraphicalEditPart) getViewer().getEditPartRegistry()
                        .get(useCaseGm);
        
                GraphicalEditPart systemEp = (GraphicalEditPart) getViewer().getEditPartRegistry()
                        .get(model.getBody());
                Rectangle ucAbsBounds = layoutData.get(useCaseGm);
                if (systemEp != null && ucAbsBounds != null) {
                    Rectangle ucCurrentBounds = useCaseEp.getFigure().getBounds().getCopy();
                    useCaseEp.getFigure().translateToAbsolute(ucCurrentBounds);
                    ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
                    req.setEditParts(useCaseEp);
                    req.setMoveDelta(ucAbsBounds.getLocation().getTranslated(ucCurrentBounds.getLocation()
                            .getNegated()));
                    req.setSizeDelta(ucAbsBounds.getSize().getShrinked(ucCurrentBounds.getSize()));
                    Command command = useCaseEp.getCommand(req);
                    if (command != null && command.canExecute()) {
                        command.execute();
                        getFigure().validate();
                    }
                }
        
            }
        }
    }

    @objid ("5e88bfa0-55b7-11e2-877f-002564c97630")
    private boolean isInSystem(final GmSystem model, final MObject theUseCase) {
        return theUseCase.getCompositionOwner().equals(model.getRepresentedElement());
    }

}

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
package org.modelio.uml.statikdiagram.editor.elements.bpmnsharedefinition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for an BpmnSharedDefinitions Node.
 */
@objid ("2d6b9bcb-7401-43b0-ac03-eb27176b8a67")
public class BpmnSharedDefinitionsEditPart extends AbstractNodeEditPart {
    @objid ("a0659dcb-9899-4d59-9840-ed82733bcd97")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("54b603b6-f1a8-463a-ade4-cd6b509aaf95")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        // Only two possible children: the header and the behavior label!
        // See Gm constructor for detail
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        GmAbstractObject gmAbstractObject = (GmAbstractObject) childEditPart.getModel();
        if (index == 0 && gmAbstractObject.getLayoutData() == null) {
        
            gmAbstractObject.setLayoutData(BorderLayout.TOP);
        } else if (index == 1 && gmAbstractObject.getLayoutData() == null) {
            gmAbstractObject.setLayoutData(BorderLayout.CENTER);
        } else if (index >= 2) {
            throw new IllegalArgumentException("CallBehaviorEditPart#addChildVisual: unknown index " + index);
        }
        getFigure().add(child, gmAbstractObject.getLayoutData(), index);
        
    }

    @objid ("ad5b9ea5-5e46-4e3b-9489-cc5bef085b8b")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("73b7eaa3-0e50-430b-a89f-0832d9ff0ed9")
    @Override
    protected IFigure createFigure() {
        // create the figure
        RoundedBoxFigure fig = new RoundedBoxFigure();
        fig.setLayoutManager(new BorderLayout());
        
        // set style independent properties
        fig.setSize(100, 50);
        fig.setRadius(5);
        MinimumSizeLayout.apply(fig, 100, 50);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("6a22cd1d-0feb-4663-9413-f7586b4e59fb")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        if (aFigure instanceof RoundedBoxFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        }
        
    }

    @objid ("e3883082-cc2c-4241-afca-6bdaa2ebd618")
    @Override
    protected void refreshVisuals() {
        GmBpmnSharedDefinitionsPrimaryNode callBehaviorModel = (GmBpmnSharedDefinitionsPrimaryNode) getModel();
        getFigure().getParent().setConstraint(getFigure(), callBehaviorModel.getLayoutData());
        
    }

}

/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessStructuredStyleKeys;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * EditPart for an SubProcess Node.
 */
@objid ("61c25c3a-55b6-11e2-877f-002564c97630")
public class BpmnSubProcessEditPart extends AbstractNodeEditPart {
    @objid ("61c25c3e-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        RoundedBoxFigure fig = new RoundedBoxFigure();
        fig.setLayoutManager(new BorderLayout());
        
        // set style independent properties
        MinimumSizeLayout.apply(fig, 100, 50);
        fig.setRadius(5);
        
        // Required for CallActivity reprsentation
        fig.setLineWidth(1);
        
        fig.setLinePattern(LinePattern.LINE_SOLID);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("61c25c43-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnSubProcessLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DeferringCreateNodePolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("61c25c46-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmBpmnSubProcessPrimaryNode model = (GmBpmnSubProcessPrimaryNode) getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        
        if (model.getRelatedElement().isTriggeredByEvent()) {
            ((RoundedBoxFigure) getFigure()).setLinePattern(LinePattern.LINE_DOT);
        } else {
            ((RoundedBoxFigure) getFigure()).setLinePattern(LinePattern.LINE_SOLID);
        }
    }

    @objid ("61c25c49-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        if (((GmNodeModel) childEditPart.getModel()).getRoleInComposition().equals(GmBpmnSubProcessPrimaryNode.ROLE_HEADER)) {
            getFigure().add(child, BorderLayout.TOP, index);
        }
        if (((GmNodeModel) childEditPart.getModel()).getRoleInComposition().equals(GmBpmnSubProcessPrimaryNode.ROLE_BODY)) {
            getFigure().add(child, BorderLayout.CENTER, index);
        }
        if (((GmNodeModel) childEditPart.getModel()).getRoleInComposition().equals(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER)) {
            getFigure().add(child, BorderLayout.BOTTOM, index);
        }
    }

    @objid ("61c25c4e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("61c3e2da-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof RoundedBoxFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        }
        
        GmBpmnSubProcessPrimaryNode model = (GmBpmnSubProcessPrimaryNode) getModel();
        Boolean showcontent = getModelStyle().getProperty(GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT);
        GmBpmnNodeFooter gmBpmnNodeFooter = (GmBpmnNodeFooter) model.getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER);
        if (gmBpmnNodeFooter != null) {
            if (showcontent) {
                gmBpmnNodeFooter.setEmptySubProcess(false);
                gmBpmnNodeFooter.setNonEmptySubProcess(true);
            } else {
                gmBpmnNodeFooter.setEmptySubProcess(true);
                gmBpmnNodeFooter.setNonEmptySubProcess(false);
            }
        }
    }

    @objid ("61c3e2e1-55b6-11e2-877f-002564c97630")
    @Override
    protected void reorderChild(final EditPart child, final int index) {
        removeChildVisual(child);
        List<EditPart> achildren = getChildren();
        achildren.remove(child);
        achildren.add(index, child);
        addChildVisual(child, index);
    }

}

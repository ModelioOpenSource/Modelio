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

package org.modelio.diagram.editor.bpmn.elements.bpmnnodefooter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultElementDirectEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Edit part of classifier header.
 */
@objid ("61717d20-55b6-11e2-877f-002564c97630")
public class BpmnNodeFooterEditPart extends AbstractNodeEditPart {
    @objid ("617303bc-55b6-11e2-877f-002564c97630")
    public BpmnNodeFooterEditPart() {
        // Nothing to do.
    }

    @objid ("617303be-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("617303c3-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DefaultElementDirectEditPolicy());
    }

    @objid ("617303c6-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        GmBpmnNodeFooter gm = (GmBpmnNodeFooter) this.getModel();
        
        // Create the class header figure
        Figure newFigure = new Figure();
        ToolbarLayout manager = new ToolbarLayout(gm.isHorizontal());
        manager.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
        manager.setStretchMinorAxis(false);
        newFigure.setLayoutManager(manager);
        
        BpmnNodeFooterFigure theFigure = new BpmnNodeFooterFigure();
        ToolbarLayout layout = new ToolbarLayout(!gm.isHorizontal());
        layout.setSpacing(5);
        layout.setObserveVisibility(false);
        theFigure.setLayoutManager(layout);
        newFigure.add(theFigure);
        refreshFromStyle(theFigure, getModelStyle());
        return newFigure;
    }

    @objid ("617303cb-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        GmBpmnNodeFooter gm = (GmBpmnNodeFooter) this.getModel();
        BpmnNodeFooterFigure ffigure = (BpmnNodeFooterFigure) getFigure().getChildren().get(0);
        
        if (gm.isLoop()) {
            ffigure.setLoopVisible(true);
        } else {
            ffigure.setLoopVisible(false);
        }
        
        if (gm.isParallel()) {
            ffigure.setParallelVisible(true);
        } else {
            ffigure.setParallelVisible(false);
        }
        
        if (gm.isSequential()) {
            ffigure.setSequentialVisible(true);
        } else {
            ffigure.setSequentialVisible(false);
        }
        
        if (gm.isAdHoc()) {
            ffigure.setAdHocVisible(true);
        } else {
            ffigure.setAdHocVisible(false);
        }
        
        if (gm.isCompensation()) {
            ffigure.setCompensationVisible(true);
        } else {
            ffigure.setCompensationVisible(false);
        }
        
        if (gm.isEmptySubProcess()) {
            ffigure.setEmptySubProcessVisible(true);
        } else {
            ffigure.setEmptySubProcessVisible(false);
        }
    }

    @objid ("617303ce-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        GmBpmnNodeFooter gm = (GmBpmnNodeFooter) this.getModel();
        BpmnNodeFooterFigure ffigure = null;
        if (aFigure instanceof BpmnNodeFooterFigure) {
            ffigure = (BpmnNodeFooterFigure) aFigure;
        } else {
            ffigure = (BpmnNodeFooterFigure) getFigure().getChildren().get(0);
        }
        
        if (gm.isEmptySubProcess()) {
            ffigure.setEmptySubProcessVisible(true);
        } else {
            ffigure.setEmptySubProcessVisible(false);
        }
        
        if (gm.isNonEmptySubProcess()) {
            ffigure.setNonEmptySubProcessVisible(true);
        } else {
            ffigure.setNonEmptySubProcessVisible(false);
        }
        super.refreshFromStyle(aFigure, style);
    }

}

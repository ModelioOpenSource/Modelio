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
package org.modelio.uml.sequencediagram.editor.elements.interactionoperand;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.AutoSizeEditPolicy2;
import org.modelio.diagram.elements.common.portcontainer.LayoutMainNodeConnectionsEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.PortContainerLayout;
import org.modelio.diagram.elements.core.policies.LayoutConnectionsConstrainedLayoutEditPolicyDecorator;
import org.modelio.diagram.elements.core.policies.LayoutNodeConnectionsEditPolicy;

/**
 * EditPart for InteractionOperand. Handles specificity of gates.
 * 
 * @author fpoyer
 */
@objid ("d906966c-55b6-11e2-877f-002564c97630")
public class InteractionOperandEditPart extends PortContainerEditPart {
    @objid ("d9069670-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // Override the layout to provide a version that doesn't shrink primary node if preferred bounds are not given...
        IFigure fig = super.createFigure();
        fig.setLayoutManager(new InteractionOperandPortLayout());
        return fig;
    }

    @objid ("482c00d5-95d8-4783-b75b-03cc37a99234")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        removeEditPolicy(LayoutMainNodeConnectionsEditPolicy.ROLE);
        
    }

    @objid ("565765ef-4b44-4e4f-a7ec-83ba2774d05f")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(String requestType) {
        return new AutoSizeEditPolicy2() {
            @Override
            public void activate() {
                super.activate();
        
                EditPart host = getHost();
                host.removeEditPolicy(LayoutNodeConnectionsEditPolicy.ROLE);
            }
        };
        
    }

    @objid ("cc726d27-d96b-4512-8cae-a40a3f02449c")
    @Override
    protected EditPolicy createLayoutPolicyDecorator(EditPolicy layoutPolicy) {
        return new LayoutConnectionsConstrainedLayoutEditPolicyDecorator((PortContainerEditPolicy) layoutPolicy);
    }

    /**
     * Port container layout that doesn't shrink primary node if preferred bounds are not given.
     */
    @objid ("dbd427cf-94f1-4d51-b97c-f6a56c7eb2b8")
    protected static final class InteractionOperandPortLayout extends PortContainerLayout {
        @objid ("ab63ab5b-0777-499a-a60d-31cd088bb9b9")
        @Override
        public void layout(IFigure parent) {
            Point offset = getOrigin(parent);
            
            for (Object childObj : parent.getChildren()) {
                IFigure child = (IFigure) childObj;
            
                Object childConstraint;
                if (child.equals(getMainNodeFigure())) {
                    // Main node _ALWAYS_ uses all available space.
                    childConstraint = parent.getBounds().getTranslated(offset.getNegated());
                } else {
                    childConstraint = getConstraint(child);
                }
            
                if (childConstraint != null) {
                    Rectangle bounds;
                    if (childConstraint instanceof Rectangle) {
                        bounds = (Rectangle) childConstraint;
                    } else {
                        bounds = getCorrectedRectangle(parent, child, (PortConstraint) childConstraint);
                    }
            
                    if (bounds.width == -1 || bounds.height == -1) {
                        Dimension childPreferredSize = child.getPreferredSize(bounds.width, bounds.height);
                        bounds = bounds.getCopy();
                        if (bounds.width == -1) {
                            bounds.width = childPreferredSize.width;
                        }
                        if (bounds.height == -1) {
                            bounds.height = childPreferredSize.height;
                        }
                    }
                    bounds = bounds.getTranslated(offset);
                    child.setBounds(bounds);
                } else {
                    // no constraint for this child yet, do not move it and resize it to its preferred size (this is only a temporary state).
                    child.setSize(child.getPreferredSize());
                }
            }
            
        }

    }

}

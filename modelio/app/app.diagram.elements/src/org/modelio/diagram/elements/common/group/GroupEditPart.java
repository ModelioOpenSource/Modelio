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
package org.modelio.diagram.elements.common.group;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.AutoFitToContentEditPolicy;
import org.modelio.diagram.elements.core.policies.HoverFeedbackEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for {@link GmGroup} models.
 * <p>
 * This class contains the behavior that is common to all GmGroups.<br>
 * It may be redefined to add behavior specific to a subclass of GmGroup.
 * <p>
 * Creates a <code>{@link GroupFigure}</code> as this part's <i>visuals</i>.
 * 
 * @see GmGroup
 * @author cmarin
 */
@objid ("7e56fafb-1dec-11e2-8cad-001ec947c8cc")
public class GroupEditPart extends AbstractNodeEditPart {
    @objid ("7e56fafd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IFigure getContentPane() {
        GroupFigure f = (GroupFigure) getFigure();
        if (f == null) {
            return null;
        }
        return f.getContenPane();
    }

    /**
     * A {@link GroupEditPart} is not selectable by default.
     * <p>
     * Sub classes may decide differently.
     */
    @objid ("7e56fb04-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("7e56fb09-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Call inherited
        super.propertyChange(evt);
        
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_CHILDREN)) {
            // Update the incomplete indicator.
            final GmGroup gmGroup = (GmGroup) getModel();
            final GroupFigure aFigure = (GroupFigure) getFigure();
            aFigure.showIncompleteIndicator(gmGroup.hasHiddenFeatures());
        }
        
    }

    @objid ("7e595d2d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DefaultGroupLayoutEditPolicy());
        
        // Allow group to self resize vertically, and horizontally if it is a link extension.
        boolean isInLink = getParent().getModel() instanceof IGmLink;
        installEditPolicy(AutoFitToContentEditPolicy.ROLE, new AutoFitToContentEditPolicy(isInLink, true));
        
        // Draw rectangle on hover
        installEditPolicy("hover", new HoverFeedbackEditPolicy());
        
    }

    @objid ("7e595d30-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        final GmGroup gmGroup = (GmGroup) getModel();
        // Create GroupFigure
        GroupFigure groupFigure = new GroupFigure(gmGroup.stretchLastChild());
        
        // Set style independent properties
        groupFigure.setBackgroundColor(null);
        groupFigure.setOpaque(false);
        
        // Set style dependent properties
        refreshFromStyle(groupFigure, getModelStyle());
        return groupFigure;
    }

    @objid ("7e595d37-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        updateVisibility((GroupFigure) aFigure);
        
    }

    /**
     * Show or hide the group figure depending on {@link GmNodeModel#isVisible()} result.
     * @return <i>true</i> if the group is visible, <i>false</i> in the other case.
     */
    @objid ("7e595d3e-1dec-11e2-8cad-001ec947c8cc")
    private boolean updateVisibility(GroupFigure aFigure) {
        final boolean oldVisible = (aFigure.isVisible());
        final GmGroup gmGroup = (GmGroup) getModel();
        final boolean newVisible = gmGroup.isVisible();
        
        if (oldVisible == newVisible) {
            return oldVisible;
        }
        
        if (newVisible) {
            aFigure.setVisible(true);
            aFigure.showIncompleteIndicator(gmGroup.hasHiddenFeatures());
        } else {
            aFigure.setVisible(false);
            aFigure.showIncompleteIndicator(false);
        }
        return newVisible;
    }

}

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
package org.modelio.uml.statikdiagram.editor.elements.assocqualifier;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.modelio.diagram.elements.common.group.GroupEditPart;
import org.modelio.diagram.elements.common.group.GroupFigure;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.borders.ZoomableLineBorder;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;

/**
 * EditPart for {@link GmQualifierGroup}.
 * <p>
 * All behavior is currently inherited from {@link GroupEditPart}. This class may be deleted if no more behavior is to be added.
 * 
 * @author cmarin
 */
@objid ("33fb31cd-55b7-11e2-877f-002564c97630")
public class QualifierGroupEditPart extends GroupEditPart {
    @objid ("33fb31d1-55b7-11e2-877f-002564c97630")
    private RepresentationMode originalMode;

    @objid ("33fb31d4-55b7-11e2-877f-002564c97630")
    private StyleUpdater styleUpdater = new StyleUpdater();

    @objid ("33fb31d5-55b7-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        // Needed because of potential call from getModel().setVisible(false)
        if (!isActive()) {
            return;
        }
        
        final String propName = evt.getPropertyName();
        if (IGmObject.PROPERTY_CHILDREN.equals(propName)) {
            // Qualifiers were added/removed, show/hide the group depending on its remaining content
            final GmQualifierGroup gmModel = (GmQualifierGroup) getModel();
            final boolean newVis = !gmModel.getChildren().isEmpty();
            if (newVis != gmModel.isVisible()) {
                gmModel.setVisible(newVis); // May suicide the edit part
                if (!newVis) {
                    return; // The edit part is dead here.
                }
            }
        }
        
        super.propertyChange(evt);
        
    }

    @objid ("33fb31da-55b7-11e2-877f-002564c97630")
    @Override
    public void activate() {
        GmQualifierGroup gmModel = (GmQualifierGroup) getModel();
        gmModel.updateStyle(gmModel.getParentLink());
        gmModel.getParentLink().addPropertyChangeListener(this.styleUpdater);
        
        super.activate();
        
        refreshFromStyle(getFigure(), getModelStyle());
        
    }

    @objid ("33fb31dd-55b7-11e2-877f-002564c97630")
    @Override
    public void deactivate() {
        super.deactivate();
        
        final GmQualifierGroup gmModel = (GmQualifierGroup) getModel();
        final GmLink parentLink = gmModel.getParentLink();
        if (parentLink != null) {
            parentLink.removePropertyChangeListener(this.styleUpdater);
        }
        
    }

    @objid ("33fb31e0-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final GmQualifierGroup gmGroup = (GmQualifierGroup) getModel();
        
        // Get the current representation mode
        this.originalMode = gmGroup.getRepresentationMode();
        
        // Create GroupFigure
        GroupFigure groupFigure = new GroupFigure(gmGroup.stretchLastChild());
        
        // Set style independent properties
        // groupFigure.setBackgroundColor(null);
        groupFigure.setOpaque(true);
        
        // Set style dependent properties
        refreshFromStyle(groupFigure, getModelStyle());
        return groupFigure;
    }

    @objid ("33fcb839-55b7-11e2-877f-002564c97630")
    private void updateFigureBorder(final GradientFigure classFig) {
        final Border inner = new ZoomableLineBorder(classFig.getLineColor(), classFig.getLineWidth());
        classFig.setBorder(inner);
        
    }

    @objid ("33fcb83d-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        if (aFigure instanceof GradientFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                updateFigureBorder((GradientFigure) aFigure);
            }
        }
        
    }

    /**
     * Listens the GmAssociation source/target change to link the style to the new extremity.
     */
    @objid ("33fcb846-55b7-11e2-877f-002564c97630")
    private class StyleUpdater implements PropertyChangeListener {
        @objid ("33fcb848-55b7-11e2-877f-002564c97630")
        public  StyleUpdater() {
            
        }

        @objid ("33fcb84a-55b7-11e2-877f-002564c97630")
        @Override
        public void propertyChange(final PropertyChangeEvent evt) {
            // Needed because of potential call from getModel().setVisible(false)
            if (!isActive()) {
                return;
            }
            
            final String propName = evt.getPropertyName();
            if (GmLink.PROP_SOURCE_GM.equals(propName) || GmLink.PROP_TARGET_GM.equals(propName)) {
                // The owning association has moved, update style from new extremities.
                GmQualifierGroup gmModel = (GmQualifierGroup) getModel();
                gmModel.updateStyle(gmModel.getParentLink());
            }
            
        }

    }

}

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

package org.modelio.diagram.editor.statik.elements.packaze;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.statik.elements.namespacinglink.GmCompositionLink;
import org.modelio.diagram.editor.statik.elements.namespacinglink.redraw.RedrawCompositionLinkEditPolicy;
import org.modelio.diagram.elements.common.simple.NonSelectableSimpleEditPart;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;

/**
 * Overloading of the {@link NonSelectableSimpleEditPart} to handle satellite children transfer back into body when leaving simple mode.
 * 
 * @author fpoyer
 */
@objid ("3629aa91-55b7-11e2-877f-002564c97630")
public class PackageSimpleEditPart extends NonSelectableSimpleEditPart {
    @objid ("3629aa95-55b7-11e2-877f-002564c97630")
     static final int DEFAULT_HEIGHT = 50;

    @objid ("3629aa97-55b7-11e2-877f-002564c97630")
     static final int DEFAULT_WIDTH = 100;

    @objid ("3629aaa2-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final PackageSimpleFigure aFigure = new PackageSimpleFigure();
        aFigure.setLayoutManager(new BorderLayout());
        aFigure.setOpaque(true);
        
        // set style independent properties
        final Dimension d = new Dimension(PackageSimpleEditPart.DEFAULT_WIDTH, PackageSimpleEditPart.DEFAULT_HEIGHT);
        aFigure.setPreferredSize(d);
        aFigure.setMinimumSize(d);
        
        // set style dependent properties
        refreshFromStyle(aFigure, getModelStyle());
        
        // return the figure
        return aFigure;
    }

    @objid ("362b30fa-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Add specific policy to handle requests to redraw composition links.
        installEditPolicy("RedrawCompositionLinkEditPolicy", new RedrawCompositionLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeOwnedElementCreationEditPolicy());
    }

    @objid ("362b30fd-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final GmPackagePrimaryNode model = (GmPackagePrimaryNode) getModel();
        final PackageSimpleFigure aFigure = (PackageSimpleFigure) getFigure();
        
        aFigure.getParent().setConstraint(aFigure, model.getLayoutData());
    }

    /**
     * If new representation mode is STRUCTURED, put back satellite content into the body before the switch.
     */
    @objid ("4f384b56-7799-4874-930a-51a458ddcbff")
    @Override
    protected void beforeSwitchRepresentationMode() {
        super.beforeSwitchRepresentationMode();
        
        GmPackagePrimaryNode model = (GmPackagePrimaryNode) getModel();
        
        if (model.getRepresentationMode() == RepresentationMode.STRUCTURED) {
            // New representation mode is STRUCTURED, put back body content into body BEFORE the switch.
        
            // Disable listener to avoid recursion
            model.removePropertyChangeListener(this);
        
            // Start by cleaning all children that might have been created by the auto-unmask behaviors.
            final GmCompositeNode body = model.getBody();
            for (GmNodeModel mbodyChild : body.getChildren()) {
                mbodyChild.delete();
            }
        
            // Delete composition links.
            for (IGmLink link : new ArrayList<>(model.getStartingLinks())) {
                if (link instanceof GmCompositionLink) {
                    link.delete();
                }
            }
        
            // Put back body content into body .
            final GmCompositeNode parentNode = model.getParentNode();
            for (GmNodeModel child : parentNode.getChildren(GmPackage.BODY_CONTENT_AS_SATELLITE)) {
                parentNode.removeChild(child);
                child.setRoleInComposition("");
                body.addChild(child);
            }
        
            // Restore listener
            model.addPropertyChangeListener(this);
        
        }
    }

    @objid ("664e5fcd-7a66-41f5-bd4e-735ce67b9c64")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // On any property change event, refresh all!
        refresh();
        refreshFromStyle(getFigure(), getModelStyle());
    }

}

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

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.statik.elements.namespacinglink.GmCompositionLink;
import org.modelio.diagram.editor.statik.elements.namespacinglink.redraw.RedrawCompositionLinkEditPolicy;
import org.modelio.diagram.elements.common.image.NonSelectableImageEditPart;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;

/**
 * Overloading of the {@link NonSelectableImageEditPart} to handle body children transfer as satellites when switching
 * to simple mode.
 * 
 * @author fpoyer
 */
@objid ("3629aa68-55b7-11e2-877f-002564c97630")
public class PackageImageEditPart extends NonSelectableImageEditPart {
    /**
     * If new representation mode is STRUCTURED, put back satellite content into the body before the switch.
     */
    @objid ("3629aa6c-55b7-11e2-877f-002564c97630")
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

    @objid ("3629aa75-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Add specific policy to handle requests to redraw composition links.
        installEditPolicy("RedrawCompositionLinkEditPolicy", new RedrawCompositionLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeOwnedElementCreationEditPolicy());
    }

}

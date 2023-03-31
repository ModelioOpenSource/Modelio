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
package org.modelio.uml.statediagram.editor.elements.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.modelio.diagram.elements.common.image.NonSelectableImageEditPart;
import org.modelio.diagram.elements.core.link.anchors.fixed2.DefaultFixedAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.policies.AnchorsFeedbackEditPolicy;

/**
 * {@link NonSelectableImageEditPart} with fixed anchors.
 * 
 * @since 5.1
 */
@objid ("aa531675-80dc-479d-9854-4a8da7c40721")
public class StateNonSelectableImageEditPart extends NonSelectableImageEditPart {
    @objid ("c29ac4e1-88e6-4a7a-bd8c-2ecfdca6c04d")
    private final boolean useEllipseAnchorProvider;

    /**
     * C'tor
     * @param useEllipseAnchorProvider whether this edit part should use an ellipse or a default anchor provider.
     */
    @objid ("842e5497-61a1-4376-9654-f7683125b371")
    public  StateNonSelectableImageEditPart(boolean useEllipseAnchorProvider) {
        this.useEllipseAnchorProvider = useEllipseAnchorProvider;
    }

    /**
     * Create the {@link IFixedNodeAnchorProvider} for this edit part.
     * @return the created anchor provider.
     */
    @objid ("7d418295-5f57-4dd1-8c2b-34b223c06292")
    protected IFixedNodeAnchorProvider createAnchorProvider() {
        return this.useEllipseAnchorProvider ? DefaultFixedAnchorProvider.ellipseFor(this) : DefaultFixedAnchorProvider.defaultFor(this);
    }

    @objid ("d45bad4f-0bcc-4d67-869b-8dde97c0c41d")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(AnchorsFeedbackEditPolicy.class, new AnchorsFeedbackEditPolicy(getNodeAnchorProvider()));
        
    }

}

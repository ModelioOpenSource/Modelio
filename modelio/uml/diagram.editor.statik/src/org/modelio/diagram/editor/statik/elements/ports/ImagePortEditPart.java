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

package org.modelio.diagram.editor.statik.elements.ports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.modelio.diagram.editor.statik.elements.naryconnector.AcceptNConnectorEditPolicy;
import org.modelio.diagram.elements.common.image.NonSelectableImageEditPart;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidConstants;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidStartCreationEditPolicy;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;

/**
 * EditPart for {@link GmPortPrimaryNode} in image mode.
 * 
 * @author cmarin
 */
@objid ("3649b598-55b7-11e2-877f-002564c97630")
public class ImagePortEditPart extends NonSelectableImageEditPart {
    @objid ("3649b59c-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(LinkToVoidConstants.REQ_LINKTOVOID_START, new LinkToVoidStartCreationEditPolicy());
        
        installEditPolicy("constraint", new ConstraintLinkEditPolicy(false));
        installEditPolicy("nary-connector", new AcceptNConnectorEditPolicy(true));
    }

    @objid ("3649b59f-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("3649b5a6-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("3649b5ad-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connectionPart) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("364b3c1c-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        return new ChopboxAnchor(getFigure());
    }

}

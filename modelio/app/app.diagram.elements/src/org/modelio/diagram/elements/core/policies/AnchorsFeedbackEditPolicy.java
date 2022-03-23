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
package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;

/**
 * Edit policy that shows a feedback when hovering the host figure while creating or reconnecting a connection.
 */
@objid ("f70f18c3-799c-487d-9a11-1151b352f5d9")
public class AnchorsFeedbackEditPolicy extends GraphicalEditPolicy {
    @objid ("f5830548-f761-4a5a-bb5b-5e13802ff029")
    private final IFixedConnectionAnchorFactory anchorFactory;

    @objid ("48659857-c0ad-468e-8eef-ae924c4bb65d")
    private DisplayAnchorFeedbackHelper anchorFbHelper;

    @objid ("7dec3524-3e27-4dc2-9810-a0f4cf5dee0f")
    public  AnchorsFeedbackEditPolicy(IFixedConnectionAnchorFactory anchorFactory) {
        super();
        this.anchorFactory = anchorFactory;
        
    }

    @objid ("ffda857b-9ec5-4764-9627-05ff880fe5fb")
    @Override
    public EditPart getTargetEditPart(Request request) {
        return null;
    }

    @objid ("a1a91b5a-8b7f-4bd5-961b-0ceb09e1b8a5")
    @Override
    public void showTargetFeedback(Request request) {
        if (isHandled(request)) {
            if (this.anchorFbHelper==null) {
                if (this.anchorFactory != null) {
                    this.anchorFbHelper = new DisplayAnchorFeedbackHelper(getHost(), getHostFigure(), this.anchorFactory, getFeedbackLayer());
                }
            }
            if (this.anchorFbHelper!=null) {
                this.anchorFbHelper.showTargetFeedback(request);
            }
        }
        
    }

    @objid ("7b957730-740c-4c81-9c6e-ad80706d3aa7")
    @Override
    public void eraseTargetFeedback(Request request) {
        removeAllFeedbacks();
    }

    @objid ("f1719f60-48ff-4007-84de-343a3b81e51f")
    private boolean isHandled(Request request) {
        if (! (request.getType() instanceof String))
            return false;
        
        switch ((String) request.getType()) {
        case RequestConstants.REQ_CONNECTION_START:
        case RequestConstants.REQ_CONNECTION_END:
        case RequestConstants.REQ_RECONNECT_SOURCE:
        case RequestConstants.REQ_RECONNECT_TARGET:
        case RequestConstants.REQ_SELECTION:
        case RequestConstants.REQ_SELECTION_HOVER:
            return true;
        default:
            return false;
        }
        
    }

    @objid ("701d03fa-6e1b-4fd2-b7a7-351ba1746af8")
    @Override
    public void deactivate() {
        removeAllFeedbacks();
        
        super.deactivate();
        
    }

    @objid ("c6674385-e8f0-462a-86aa-ebff9baaef64")
    protected void removeAllFeedbacks() {
        if (this.anchorFbHelper!=null) {
            this.anchorFbHelper.removeAllFeedbacks();
            this.anchorFbHelper = null;
        }
        
    }

}

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
package org.modelio.bpmn.diagram.editor.elements.common.editpart;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.modelio.diagram.elements.common.image.NonSelectableImageEditPart;
import org.modelio.diagram.elements.core.link.anchors.fixed.AbstractFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed.VariableFixedAnchorProvider;
import org.modelio.diagram.elements.core.policies.AnchorsFeedbackEditPolicy;

@objid ("af22b979-3755-412c-b36b-588009125020")
public class BpmnNonSelectableImageEditPart extends NonSelectableImageEditPart {
    @objid ("ab5dc11e-6678-4ddd-bf1a-ba53d34b93a2")
    private AbstractFixedNodeAnchorProvider nodeAnchorProvider;

    @objid ("66ba8606-eb07-41d5-981b-a53109e28674")
    @Override
    protected AbstractFixedNodeAnchorProvider getNodeAnchorProvider() {
        if (this.nodeAnchorProvider == null) {
            this.nodeAnchorProvider = createAnchorProvider(this.figure);
        }
        return this.nodeAnchorProvider;
    }

    /**
     * Create the {@link AbstractFixedNodeAnchorProvider} for this edit part.
     * @param figure the edit part figure.
     * @return the created anchor provider.
     */
    @objid ("b002d47b-bd48-44b4-8523-4e2451bc688d")
    protected AbstractFixedNodeAnchorProvider createAnchorProvider(IFigure figure) {
        return new VariableFixedAnchorProvider();
    }

    @objid ("bb38aae9-dc0b-48fa-81b6-afaa3620e6a5")
    @Override
    protected void setFigure(IFigure figure) {
        super.setFigure(figure);
        getNodeAnchorProvider().onFigureMoved(figure);
        
    }

    /**
     * Adapt to {@link AccessibleAnchorProvider} or {@link IFixedConnectionAnchorFactory}.
     */
    @objid ("7b807984-5ccb-4242-abc8-4d6aacbcc0bd")
    @Override
    public Object getAdapter(Class adapter) {
        if (AccessibleAnchorProvider.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider.getAccessibleAnchorProvider(getFigure());
        } else if (IFixedConnectionAnchorFactory.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider;
        }
        return super.getAdapter(adapter);
    }

    @objid ("32cf61bc-f5f3-46d0-9ec2-70499467f5e3")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(AnchorsFeedbackEditPolicy.class, new AnchorsFeedbackEditPolicy(this.nodeAnchorProvider));
        
    }

}

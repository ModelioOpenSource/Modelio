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
import org.modelio.diagram.elements.core.link.anchors.fixed.AbstractFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed.VariableFixedAnchorProvider;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AnchorsFeedbackEditPolicy;

/**
 * Base {@link AbstractNodeEditPart} for a BPMN Node.
 */
@objid ("3ad04988-df6b-4f8e-9f26-ae66ff72a185")
public class AbstractBpmnNodeEditPart extends AbstractNodeEditPart {
    @objid ("c4a19e1f-08e1-4739-a6b5-1c08e8dcfbbc")
    private AbstractFixedNodeAnchorProvider nodeAnchorProvider;

    @objid ("32ab754b-dbcd-41a2-95fe-89fd310b975a")
    @Override
    protected AbstractFixedNodeAnchorProvider getNodeAnchorProvider() {
        if (this.nodeAnchorProvider == null) {
            this.nodeAnchorProvider = createAnchorProvider(this.figure);
        }
        return this.nodeAnchorProvider;
    }

    @objid ("423f3039-e840-4b1d-82ae-098d5f9d1466")
    @Override
    protected void setFigure(IFigure figure) {
        super.setFigure(figure);
        getNodeAnchorProvider().onFigureMoved(figure);
        
    }

    /**
     * Create the {@link AbstractFixedNodeAnchorProvider} for this edit part.
     * @param figure the edit part figure.
     * @return the created anchor provider.
     */
    @objid ("e482d180-72a9-42d5-a8c5-ae8a908709c4")
    protected AbstractFixedNodeAnchorProvider createAnchorProvider(IFigure figure) {
        return new VariableFixedAnchorProvider();
    }

    /**
     * Adapt to {@link AccessibleAnchorProvider} or {@link IFixedConnectionAnchorFactory}.
     */
    @objid ("af2b2c82-d69b-4ecc-a223-0043b0784dc1")
    @Override
    public Object getAdapter(Class adapter) {
        if (AccessibleAnchorProvider.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider.getAccessibleAnchorProvider(getFigure());
        } else if (IFixedConnectionAnchorFactory.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider;
        }
        return super.getAdapter(adapter);
    }

    @objid ("520c5efa-501f-4a44-a5e0-eb9eced56ff9")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(AnchorsFeedbackEditPolicy.class, new AnchorsFeedbackEditPolicy(this.nodeAnchorProvider));
        
    }

}

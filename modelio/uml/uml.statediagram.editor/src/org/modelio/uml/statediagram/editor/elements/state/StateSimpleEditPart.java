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
package org.modelio.uml.statediagram.editor.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.modelio.diagram.elements.common.simple.SimpleEditPart;
import org.modelio.diagram.elements.core.link.anchors.fixed.AbstractFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed.VariableFixedAnchorProvider;
import org.modelio.diagram.elements.core.policies.AnchorsFeedbackEditPolicy;

/**
 * {@link SimpleEditPart} with fixed anchors.
 * 
 * @since 5.1
 */
@objid ("c832e2b7-2880-40e8-af27-ea8090c37bcc")
public class StateSimpleEditPart extends SimpleEditPart {
    @objid ("3c6893ba-710b-474b-9e47-cd4781cc9ef0")
    private AbstractFixedNodeAnchorProvider nodeAnchorProvider;

    @objid ("1578756c-5839-481d-a835-fd38e3454356")
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
    @objid ("de1f5e76-6a58-49e8-a86e-4bf259fb0b1c")
    protected AbstractFixedNodeAnchorProvider createAnchorProvider(IFigure figure) {
        return new VariableFixedAnchorProvider();
    }

    @objid ("a5698c50-8d33-4561-b1c4-cf6e1a5307b2")
    @Override
    protected void setFigure(IFigure figure) {
        super.setFigure(figure);
        getNodeAnchorProvider().onFigureMoved(figure);
        
    }

    /**
     * Adapt to {@link AccessibleAnchorProvider} or {@link IFixedConnectionAnchorFactory}.
     */
    @objid ("1cde3781-5df5-41d4-aba6-a21e57111277")
    @Override
    public Object getAdapter(Class adapter) {
        if (AccessibleAnchorProvider.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider.getAccessibleAnchorProvider(getFigure());
        } else if (IFixedConnectionAnchorFactory.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider;
        }
        return super.getAdapter(adapter);
    }

    @objid ("996efc63-d80e-4395-93d8-855b38be2962")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(AnchorsFeedbackEditPolicy.class, new AnchorsFeedbackEditPolicy(this.nodeAnchorProvider));
        
    }

}

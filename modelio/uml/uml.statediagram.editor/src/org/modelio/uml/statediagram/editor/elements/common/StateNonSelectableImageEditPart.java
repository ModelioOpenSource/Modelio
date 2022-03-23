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
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.modelio.diagram.elements.common.image.NonSelectableImageEditPart;
import org.modelio.diagram.elements.core.link.anchors.fixed.AbstractFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.EllipseFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed.VariableFixedAnchorProvider;
import org.modelio.diagram.elements.core.policies.AnchorsFeedbackEditPolicy;

/**
 * {@link NonSelectableImageEditPart} with fixed anchors.
 * 
 * @since 5.1
 */
@objid ("8cfa7291-e161-49f4-ba88-9a28713253f4")
public class StateNonSelectableImageEditPart extends NonSelectableImageEditPart {
    @objid ("e92ef838-d703-4472-9f04-4e9814cbca7d")
    private final boolean useEllipseAnchorProvider;

    @objid ("c1fbd3a1-f857-437f-82fc-0d43537164fe")
    private AbstractFixedNodeAnchorProvider nodeAnchorProvider;

    /**
     * C'tor
     * @param useEllipseAnchorProvider whether this edit part should use an {@link EllipseFixedNodeAnchorProvider} or a {@link VariableFixedAnchorProvider}.
     */
    @objid ("d6b6b084-145a-4313-bb94-1eb18875fe49")
    public  StateNonSelectableImageEditPart(boolean useEllipseAnchorProvider) {
        this.useEllipseAnchorProvider = useEllipseAnchorProvider;
    }

    @objid ("f3d075f0-29c3-4572-89e5-83670d0a8fdf")
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
    @objid ("94bd7fe7-d850-4a09-8260-29e702bf83ed")
    protected AbstractFixedNodeAnchorProvider createAnchorProvider(IFigure figure) {
        return this.useEllipseAnchorProvider ? new EllipseFixedNodeAnchorProvider() : new VariableFixedAnchorProvider();
    }

    @objid ("7ba47cc3-08dc-4d35-9eb8-96a050f3c750")
    @Override
    protected void setFigure(IFigure figure) {
        super.setFigure(figure);
        getNodeAnchorProvider().onFigureMoved(figure);
        
    }

    /**
     * Adapt to {@link AccessibleAnchorProvider} or {@link IFixedConnectionAnchorFactory}.
     */
    @objid ("9bdda293-792f-4cdd-932f-0a3146513ff2")
    @Override
    public Object getAdapter(Class adapter) {
        if (AccessibleAnchorProvider.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider.getAccessibleAnchorProvider(getFigure());
        } else if (IFixedConnectionAnchorFactory.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider;
        }
        return super.getAdapter(adapter);
    }

    @objid ("715202b8-5957-4fe6-a04b-8c32eabc3f75")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(AnchorsFeedbackEditPolicy.class, new AnchorsFeedbackEditPolicy(this.nodeAnchorProvider));
        
    }

}

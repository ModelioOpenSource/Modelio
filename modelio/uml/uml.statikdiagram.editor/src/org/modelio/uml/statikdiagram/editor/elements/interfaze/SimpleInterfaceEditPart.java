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

package org.modelio.uml.statikdiagram.editor.elements.interfaze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.policies.SmallNodeNonResizeableEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.GmCompositionLink;
import org.modelio.uml.statikdiagram.editor.elements.namespacinglink.redraw.RedrawCompositionLinkEditPolicy;
import org.modelio.uml.statikdiagram.editor.elements.naryassoc.AcceptNAssocEditPolicy;
import org.modelio.uml.statikdiagram.editor.elements.packaze.SimpleModeOwnedElementCreationEditPolicy;
import org.modelio.uml.statikdiagram.editor.elements.policies.SmartGeneralizationEditPolicy;

/**
 * {@link GmInterfacePrimaryNode} edit part for {@link RepresentationMode#SIMPLE simple} mode.
 * <p>
 * Creates an empty non resizeable {@link EllipseFigure} .
 */
@objid ("3584def0-55b7-11e2-877f-002564c97630")
public class SimpleInterfaceEditPart extends AbstractNodeEditPart {
    @objid ("3584def4-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("3584def9-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Allow drag & drop
        installEditPolicy(ModelElementDropRequest.TYPE, new DefaultElementDropEditPolicy());
        
        // Not any more =>       // Switch to structured mode when creating elements inside
        //        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeDeferringCreateNodePolicy());
        
        // Allow links
        installEditPolicy(EditPolicy.NODE_ROLE, new SmartGeneralizationEditPolicy());
        
        // Allow notes
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        
        installEditPolicy("Constraint creation", new ConstraintLinkEditPolicy(false));
        installEditPolicy("N-ary assoc", new AcceptNAssocEditPolicy(true));
        
        // Add specific policy to handle requests to redraw composition links.
        installEditPolicy("RedrawCompositionLinkEditPolicy", new RedrawCompositionLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeOwnedElementCreationEditPolicy());
    }

    @objid ("3584defc-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final EllipseFigure classFigure = new EllipseFigure();
        
        // Set style independent properties
        final Dimension d = new Dimension(26, 26);
        classFigure.setOpaque(true);
        classFigure.setPreferredSize(d);
        classFigure.setMinimumSize(d);
        classFigure.setMaximumSize(d);
        
        // Set style dependent properties
        refreshFromStyle(classFigure, getModelStyle());
        return classFigure;
    }

    @objid ("3584df01-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        GmCompositeNode model = (GmCompositeNode) getModel();
        if (model.getRepresentationMode() == RepresentationMode.STRUCTURED) {
            // new representation mode is STRUCTURED, put back body content into body BEFORE the switch.
            for (GmNodeModel child : model.getParentNode().getChildren("body content as satellite")) {
                model.getParentNode().removeChild(child);
                child.setRoleInComposition("");
                model.getCompositeFor(child.getRepresentedElement().getClass()).addChild(child);
            }
            // Delete composition links.
            List<IGmLink> linksToDelete = new ArrayList<>();
            for (IGmLink link : model.getStartingLinks()) {
                if (link instanceof GmCompositionLink) {
                    linksToDelete.add(link);
                }
            }
            for (IGmLink link : linksToDelete) {
                link.delete();
            }
        }
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
    }

    @objid ("3584df08-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final IFigure fig = getFigure();
        final GmNodeModel gm = getModel();
        
        fig.getParent().setConstraint(fig, gm.getLayoutData());
    }

    /**
     * The interface circle is not resizeable.
     */
    @objid ("3584df0b-55b7-11e2-877f-002564c97630")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(String requestType) {
        return new SmallNodeNonResizeableEditPolicy();
    }

    @objid ("3584df12-55b7-11e2-877f-002564c97630")
    @Override
    protected List<Object> getModelChildren() {
        return Collections.emptyList();
    }

}

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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.editor.statik.elements.namespacinglink.GmCompositionLink;
import org.modelio.diagram.editor.statik.elements.namespacinglink.redraw.RedrawCompositionLinkEditPolicy;
import org.modelio.diagram.elements.common.freezone.FreeZoneEditPart;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.AutoExpandLayoutEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.elements.umlcommon.packaze.PackageFigure;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Edit part for {@link GmPackagePrimaryNode}.
 * <p>
 * Creates and manipulate a {@link PackageFigure}.
 * 
 * @author pvlaemyn
 */
@objid ("362823cc-55b7-11e2-877f-002564c97630")
public class PackageEditPart extends AbstractNodeEditPart {
    @objid ("362823d0-55b7-11e2-877f-002564c97630")
    private static final int MARGIN = 20;

    @objid ("362823d2-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart child, int index) {
        IFigure childFigure = ((GraphicalEditPart) child).getFigure();
        
        if (child instanceof FreeZoneEditPart) {
            getPackageFigure().setContentsFigure(childFigure);
        } else if (child instanceof ModelElementHeaderEditPart) {
            getPackageFigure().setHeaderFigure(childFigure);
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Cannot add ");
            msg.append(child.toString());
            msg.append(" child edit part to PackageEditPart");
            throw new IllegalArgumentException(msg.toString());
        }
    }

    @objid ("362823d7-55b7-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(EditPart child) {
        if (child instanceof FreeZoneEditPart) {
            getPackageFigure().setContentsFigure(null);
        } else if (child instanceof ModelElementHeaderEditPart) {
            getPackageFigure().setHeaderFigure(null);
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Cannot remove ");
            msg.append(child.toString());
            msg.append(" child edit part from PackageEditPart");
            throw new IllegalArgumentException(msg.toString());
        }
    }

    @objid ("362823db-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
        // Add specific policy to handle requests to redraw composition links.
        installEditPolicy("RedrawCompositionLinkEditPolicy", new RedrawCompositionLinkEditPolicy());
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AutoExpandLayoutEditPolicy());
    }

    @objid ("362823de-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final PackageFigure figure1 = new PackageFigure();
        
        // set style independent properties
        MinimumSizeLayout.apply(figure1, 180, 140);
        
        // set style dependent properties
        refreshFromStyle(figure1, getModelStyle());
        
        // return the figure
        return figure1;
    }

    /**
     * Refresh the figure from the given style.
     * <p>
     * Often called in {@link #createFigure()} and after a style change.
     * 
     * @param aFigure The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("362823e3-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
    }

    @objid ("362823eb-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final IFigure fig = getFigure();
        final GmAbstractObject model = getModel();
        
        fig.getParent().setConstraint(fig, model.getLayoutData());
    }

    /**
     * Get the casted package figure.
     * 
     * @return the package figure.
     */
    @objid ("362823ee-55b7-11e2-877f-002564c97630")
    private PackageFigure getPackageFigure() {
        return ((PackageFigure) getFigure());
    }

    @objid ("362823f5-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Reparent all content of body to port container as satellites and add composition link.
     */
    @objid ("ce6059cc-4571-446f-8487-c8a9f3ed61f4")
    @Override
    protected void beforeSwitchRepresentationMode() {
        super.beforeSwitchRepresentationMode();
        
        GmPackagePrimaryNode model = (GmPackagePrimaryNode) getModel();
        
        // Disable listener to avoid recursion
        model.removePropertyChangeListener(this);
        
        // Reparent all content of body to port container as satellites and add composition link
        final GmCompositeNode body = model.getBody();
        final GmCompositeNode parentNode = model.getParentNode();
        for (GmNodeModel child : body.getChildren()) {
        
            // Avoid content appearing over the new figure.
            Rectangle constraint = (Rectangle) child.getLayoutData();
            if (constraint.x() < PackageSimpleEditPart.DEFAULT_WIDTH + PackageEditPart.MARGIN &&
                    constraint.y() < PackageSimpleEditPart.DEFAULT_HEIGHT + PackageEditPart.MARGIN) {
                constraint.setY(PackageSimpleEditPart.DEFAULT_HEIGHT + PackageEditPart.MARGIN);
            }
        
            child.setRoleInComposition(GmPackage.BODY_CONTENT_AS_SATELLITE);
            body.removeChild(child);
            parentNode.addChild(child);
        
            GmCompositionLink link = new GmCompositionLink(model.getDiagram(), model.getRepresentedRef());
            model.addStartingLink(link);
            child.addEndingLink(link);
        }
        
        model.addPropertyChangeListener(this);
    }

}

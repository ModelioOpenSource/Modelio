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
package org.modelio.diagram.elements.umlcommon.diagramview;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.borders.ShadowBorder;
import org.modelio.diagram.elements.core.helpers.palapi.PaletteActionProvider;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.umlcommon.diagramholder.GmDiagramHolderLink;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for {@link GmDiagramView}.
 * 
 * @author cmarin
 */
@objid ("81439d36-1dec-11e2-8cad-001ec947c8cc")
public class DiagramViewEditPart extends AbstractNodeEditPart {
    @objid ("81439d45-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        IFigure childFig = ((GraphicalEditPart) childEditPart).getFigure();
        GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        
        switch (childModel.getRoleInComposition()) {
        case GmDiagramView.ROLE_HEADER:
            getContentPane().add(childFig, BorderLayout.TOP);
            break;
        case GmDiagramView.ROLE_BODY:
            getContentPane().add(childFig, BorderLayout.CENTER);
            break;
        default:
            throw new IllegalArgumentException(String.format("Unexpected '%s' child at index %d.", childEditPart, index));
        }
        
        updateFigureBorder(getFigure());
        
    }

    @objid ("81439d4e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Modified createLinkPolicy to forbid re-routing the link to another element while allowing moving its anchor
        installEditPolicy(EditPolicy.NODE_ROLE, new DiagramViewLinkCreateEditPolicy());
        
        // Policy to create notes
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_END, new LinkedNodeFinishCreationEditPolicy());
        
        // Remove the default DIRECT_EDIT policy: we don't want the diagram
        // background to delegate direct edit requests.
        removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
        
        // Do not show the smart link creation handle on related diagrams.
        installEditPolicy(UserChoiceCreateLinkEditPolicy.class, new UserChoiceCreateLinkEditPolicy(new PaletteActionProvider(this, PaletteActionProvider.IS_LINK_TOOL), false));
        
    }

    @objid ("81439d51-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        // Create the figure
        final GradientFigure fig = new GradientFigure();
        
        // Set style independent properties
        fig.setOpaque(true);
        
        fig.setLayoutManager(new BorderLayout());
        
        MinimumSizeLayout.apply(fig, 150, 100);
        fig.setPreferredSize(200, 200);
        
        // Set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("81439d58-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        if (aFigure instanceof GradientFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                updateFigureBorder((GradientFigure) aFigure);
            }
        }
        
    }

    @objid ("81439d61-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final IFigure fig = getFigure();
        final GmNodeModel gm = getModel();
        
        final Object layoutData = gm.getLayoutData();
        if (layoutData != null) {
            fig.getParent().setConstraint(fig, layoutData);
        }
        
    }

    @objid ("81439d64-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void removeChildVisual(final EditPart childEditPart) {
        super.removeChildVisual(childEditPart);
        updateFigureBorder(getFigure());
        
    }

    @objid ("8145ff94-1dec-11e2-8cad-001ec947c8cc")
    private void updateFigureBorder(final GradientFigure aFigure) {
        final Border border = new CompoundBorder(new ShadowBorder(aFigure.getLineColor(), aFigure.getLineWidth()),
                new LineBorder(aFigure.getLineColor(), aFigure.getLineWidth()));
        aFigure.setBorder(border);
        
    }

    @objid ("e2c4f3b4-dc7c-405f-9545-7585126d14bc")
    @Override
    public boolean isSelectable() {
        return true;
    }

    @objid ("7d77b924-bd77-4119-a3a3-50d9aeab3006")
    @Override
    public GradientFigure getFigure() {
        return (GradientFigure) super.getFigure();
    }

    /**
     * Modified createLinkPolicy to forbid re-routing the link to another element while allowing moving its anchor (applies only to {@link GmDiagramHolderLink})
     */
    @objid ("f37b9d38-5521-4022-a938-bb42f65a8cb4")
    private final class DiagramViewLinkCreateEditPolicy extends DefaultCreateLinkEditPolicy {
        @objid ("757d8e75-cf2a-4fe7-82a2-ee31207087a9")
        @Override
        protected Command getReconnectTargetCommand(ReconnectRequest req) {
            if (req.getConnectionEditPart().getModel() instanceof GmDiagramHolderLink) {
                final NodeEditPart proposedTarget = (NodeEditPart) req.getTarget();
                final EditPart currentLinkTarget = req.getConnectionEditPart().getTarget();
                // Must remain on the same element edit part
                if (proposedTarget != currentLinkTarget) {
                    return null;
                }
            }
            return super.getReconnectTargetCommand(req);
        }

    }

}

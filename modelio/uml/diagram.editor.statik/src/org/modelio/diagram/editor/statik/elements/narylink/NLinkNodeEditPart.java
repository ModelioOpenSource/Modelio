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

package org.modelio.diagram.editor.statik.elements.narylink;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskLinkCommand;
import org.modelio.diagram.elements.core.figures.DiamondShaper;
import org.modelio.diagram.elements.core.figures.IShaper;
import org.modelio.diagram.elements.core.figures.ShapedBorderedFigure;
import org.modelio.diagram.elements.core.helpers.palapi.PaletteActionProvider;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.SmallNodeNonResizeableEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * EditPart for the {@link GmNLinkNode}.
 * 
 * @author cmarin
 */
@objid ("35efaecb-55b7-11e2-877f-002564c97630")
public class NLinkNodeEditPart extends AbstractNodeEditPart {
    @objid ("213e4999-1914-4ba8-80ed-eb709ce8a807")
    private static final IShaper DIAMOND_SHAPE = new DiamondShaper();

    @objid ("57f27ee2-1fad-41aa-90ec-bb6a1b87fdc5")
    private static final Dimension DIAMOND_SIZE = new Dimension(15, 15);

    @objid ("35efaed5-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final ShapedBorderedFigure fig = new ShapedBorderedFigure(NLinkNodeEditPart.DIAMOND_SHAPE);
        fig.setMinimumSize(NLinkNodeEditPart.DIAMOND_SIZE);
        fig.setPreferredSize(NLinkNodeEditPart.DIAMOND_SIZE);
        fig.setOpaque(true);
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("35efaeda-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final GmAbstractObject model = this.getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
    }

    @objid ("35f13539-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * The diamond is not resizeable.
     */
    @objid ("35f1353e-55b7-11e2-877f-002564c97630")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(final String requestType) {
        return new SmallNodeNonResizeableEditPolicy();
    }

    @objid ("35f13546-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        if (aFigure instanceof ShapedBorderedFigure) {
            final GmNodeModel gmModel = getModel();
            final RepresentationMode askedMode = gmModel.getRepresentationMode();
        
            if (askedMode != RepresentationMode.IMAGE || !switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
            }
        }
    }

    @objid ("35f1354f-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Do not show the smart link creation handle in the diamond which is too small to display them properly.
        installEditPolicy(UserChoiceCreateLinkEditPolicy.class, new UserChoiceCreateLinkEditPolicy(new PaletteActionProvider(this, PaletteActionProvider.IS_LINK_TOOL), false));
        
        // Allow links
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
    }

    @objid ("35f13552-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("35f13559-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("35f13560-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connectionPart) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("35f13567-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("35f1356e-55b7-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(GmNLinkPrimaryNode.PROP_REFRESH_BRANCHES)) {
            // Refresh the branches, look for missing branches
            GmNodeModel gmModel = getModel();
            List<NaryLinkEnd> newbranches = getMissingBranches(gmModel);
        
            Point dropLocation = getFigure().getBounds().getBottomRight();
            getFigure().translateToAbsolute(dropLocation);
            for (NaryLinkEnd b : newbranches) {
                createMissingLinkForElement(b, dropLocation);
                dropLocation.translate(5, 30);
            }
        } else if (evt.getPropertyName().equals(IGmObject.PROPERTY_LINK_SOURCE)) {
            // Suicide if one branch was masked
            GmNodeModel gmModel = getModel();
            if (evt.getNewValue() == null && !getMissingBranches(gmModel).isEmpty()) {
                deactivate();
                // The main node is the port container node
                gmModel.getParentNode().delete();
                return;
            }
        }
        
        // In any case apply the super routine.
        super.propertyChange(evt);
    }

    /**
     * Get the n-ary link branches that are not unmasked.
     * @param gmModel
     * @param element
     * @return
     */
    @objid ("35f2bbd9-55b7-11e2-877f-002564c97630")
    private List<NaryLinkEnd> getMissingBranches(final GmNodeModel gmModel) {
        NaryLink element = (NaryLink) gmModel.getRelatedElement();
        if (element == null || !element.isValid()) {
            return Collections.emptyList();
        } else {
            List<NaryLinkEnd> newbranches = new ArrayList<>(element.getNaryLinkEnd());
            List<IGmLink> gmBranches = gmModel.getStartingLinks();
            for (IGmLink l : gmBranches) {
                newbranches.remove(l.getRelatedElement());
            }
            return newbranches;
        }
    }

    @objid ("35f2bbe5-55b7-11e2-877f-002564c97630")
    private void createMissingLinkForElement(final NaryLinkEnd b, final Point dropLocation) {
        final GmNodeModel gmModel = getModel();
        GmLink link = new GmNLinkEndLink(gmModel.getDiagram(), b, new MRef(b));
        
        UnmaskLinkCommand cmd = new UnmaskLinkCommand(link,
                (AbstractDiagramEditPart) getRoot().getContents(),
                dropLocation);
        cmd.execute();
    }

}

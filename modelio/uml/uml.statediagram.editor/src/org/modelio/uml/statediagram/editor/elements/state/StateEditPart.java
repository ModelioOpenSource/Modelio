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
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.anchors.fixed.AbstractFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed.VariableFixedAnchorProvider;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AnchorsFeedbackEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.uml.statediagram.editor.plugin.DiagramEditorState;

/**
 * EditPart for a State Node.
 * 
 * @author fpoyer
 */
@objid ("f58a1626-55b6-11e2-877f-002564c97630")
public class StateEditPart extends AbstractNodeEditPart {
    @objid ("f58a162b-55b6-11e2-877f-002564c97630")
    private static Image compositeIcon;

    @objid ("df472a9d-30bd-44f5-b528-815535aab0a3")
    private Label label;

    @objid ("2954b833-f177-4a64-83f6-0753cff846f7")
    private AbstractFixedNodeAnchorProvider nodeAnchorProvider;

    @objid ("f58a162c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("f58a1631-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Link creation & modification edit policy
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        
        // Allow creation of regions
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new StateCreateNodeEditPolicy());
        // Allow creation of notes.
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy(AnchorsFeedbackEditPolicy.class, new AnchorsFeedbackEditPolicy(this.nodeAnchorProvider));
        
    }

    @objid ("f58a1634-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final RoundedBoxFigure fig = new RoundedBoxFigure();
        fig.setLayoutManager(new BorderLayout());
        final GradientFigure container = new GradientFigure();
        container.setFillColor(null);
        
        final ToolbarLayoutWithGrab layoutWithGrab = new ToolbarLayoutWithGrab();
        layoutWithGrab.setHorizontal(false);
        layoutWithGrab.setStretchMinorAxis(true);
        
        container.setLayoutManager(layoutWithGrab);
        
        fig.add(container, BorderLayout.CENTER);
        
        // set style independent properties
        MinimumSizeLayout.apply(fig, 100, 50);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("f58a1639-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
        
    }

    @objid ("f58a1640-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmStatePrimaryNode stateModel = (GmStatePrimaryNode) getModel();
        getFigure().getParent().setConstraint(getFigure(), stateModel.getLayoutData());
        // If inner states are not shown and this state is composite, show the icon.
        State state = (State) stateModel.getRelatedElement();
        if (!getModelStyle().getBoolean(GmStateStructuredStyleKeys.SHOWINNER) &&
                state.getOwnedRegion().size() > 0) {
            getFigure().add(this.label, BorderLayout.BOTTOM);
        } else {
            if (getFigure().getChildren().contains(this.label)) {
                getFigure().remove(this.label);
            }
        }
        
    }

    @objid ("f58a1643-55b6-11e2-877f-002564c97630")
    public  StateEditPart() {
        super();
        this.label = new Label();
        this.label.setIcon(StateEditPart.compositeIcon);
        this.label.setBorder(new MarginBorder(0, 0, 3, 3));
        this.label.setLabelAlignment(PositionConstants.RIGHT);
        
    }

    @objid ("f58a1645-55b6-11e2-877f-002564c97630")
    @Override
    public IFigure getContentPane() {
        // See #createFigure : first child is the container
        return (IFigure) getFigure().getChildren().get(0);
    }

    @objid ("06d00094-2c8e-4df1-94a0-e2f7aef0e03e")
    @Override
    protected AbstractFixedNodeAnchorProvider getNodeAnchorProvider() {
        if (this.nodeAnchorProvider == null) {
            this.nodeAnchorProvider = createAnchorProvider(this.figure);
        }
        return this.nodeAnchorProvider;
    }

    @objid ("94876a4b-e864-46b1-a0fc-987726a5ce3e")
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
    @objid ("826fa20e-3bd3-4dd3-ad30-a214a807e800")
    protected AbstractFixedNodeAnchorProvider createAnchorProvider(IFigure figure) {
        return new VariableFixedAnchorProvider();
    }

    /**
     * Adapt to {@link AccessibleAnchorProvider} or {@link IFixedConnectionAnchorFactory}.
     */
    @objid ("4dfe4bc7-2c5b-4d9d-bc99-14436392bce4")
    @Override
    public Object getAdapter(Class adapter) {
        if (AccessibleAnchorProvider.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider.getAccessibleAnchorProvider(getFigure());
        } else if (IFixedConnectionAnchorFactory.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider;
        }
        return super.getAdapter(adapter);
    }
static {
            // Statically initialize the icon.
            StateEditPart.compositeIcon = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramEditorState.PLUGIN_ID,
                    "icons/composite.png").createImage();
        }
    
}

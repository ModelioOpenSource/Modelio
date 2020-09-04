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

package org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToHelper;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.GmBpmnLane;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.hibridcontainer.GmBodyHybridContainer;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnLaneSetContainerLayout;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnLaneSetDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.SnapEditPartAdapter;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.commands.ExpandToContentCommand;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Base class for edit part of {@link GmBpmnLaneSetContainer}.
 */
@objid ("613a8e9a-55b6-11e2-877f-002564c97630")
public class BpmnLaneSetContainerEditPart extends AbstractNodeEditPart {
    /**
     * Layout orientation cache, initialized when the figure is created.
     */
    @objid ("a05ab72d-1159-4a7b-9326-d121a372bc94")
    private boolean isHorizontalLayoutCache;

    @objid ("613a8e9e-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure fig = new GradientFigure();
        fig.setOpaque(true);
        
        this.isHorizontalLayoutCache = isHorizontalLayout();
        
        BpmnLaneSetContainerLayout layoutManager = new BpmnLaneSetContainerLayout();
        layoutManager.setStretchMinorAxis(true);
        layoutManager.setHorizontal(this.isHorizontalLayoutCache);
        fig.setLayoutManager(layoutManager);
        fig.setSize(700, 200);
        
        MinimumSizeLayout.apply(fig, 40, 20);
        
        // Define properties specific to style
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("613a8ea3-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new BpmnLaneSetContainerLayoutEditPolicy());
        
        // Remove the default DIRECT_EDIT policy: we don't want the container to
        // delegate direct edit requests.
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
        
        // Override the default DROP policy to add one that can only understand Partitions
        installEditPolicy(ModelElementDropRequest.TYPE, new BpmnLaneSetDropEditPolicy());
    }

    @objid ("613a8ea6-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof GradientFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        } else {
            super.refreshFromStyle(aFigure, style);
        }
    }

    @objid ("613a8ead-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        // Update figure constraint in parent from model
        final IFigure fig = getFigure();
        final GmBpmnLaneSetContainer partitionContainerModel = (GmBpmnLaneSetContainer) getModel();
        if (!GmBodyHybridContainer.SUB_LANE.equals(partitionContainerModel.getRoleInComposition())) {
            fig.getParent().setConstraint(fig, partitionContainerModel.getLayoutData());
        } else {
            fig.getParent().setConstraint(fig, BorderLayout.CENTER);
        }
    }

    @objid ("613a8eb0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        // Is selectable if it is not a sub partition
        GmBpmnLaneSetContainer model = (GmBpmnLaneSetContainer) getModel();
        if (model.getRepresentedElement().getParentLane() == null) {
            return true;
        }
        return false;
    }

    @objid ("613c153c-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        // If child doesn't have a layout data, compute one for it.
        GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        
        if (childModel.getLayoutData() == null) {
            if (GmBodyHybridContainer.SUB_LANE.equals(childModel.getRoleInComposition())) {
                childModel.setLayoutData(Integer.valueOf(-1));
            }
        }
        
        super.addChildVisual(childEditPart, index);
    }

    /**
     * In order to provide snap to grid facility the AbstractEditPart must be adaptable to a SnapToHelper. Here we adapt to a SnapToGrid only if the style defines SNAPTOGRID = true
     */
    @objid ("cf9b934d-f0d4-418f-be13-0567b6135809")
    @Override
    public Object getAdapter(final Class type) {
        if (type == SnapToHelper.class) {
            return new SnapEditPartAdapter(this).getSnapToHelper();
        }
        return super.getAdapter(type);
    }

    /**
     * According to the lane orientation in the diagram, make sure the lane set container's layout has the proper orientation.
     * <p>
     * In a vertical lane, sub-lanes should be stacked horizontally.
     * </p>
     * <p>
     * In an horizontal lane, sub-lanes should be stacked vertically.
     * </p>
     * 
     * @return <code>true</code> if the lane set container's layout should be horizontal.
     */
    @objid ("5116d403-0abd-40c6-9da2-7b19a28ea7b1")
    private boolean isHorizontalLayout() {
        boolean isHorizontalLaneOrientation = getModel().getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
        return !isHorizontalLaneOrientation;
    }

    /**
     * @return <code>true</code> when an orientation change occurs, to make the edit part create its figure again.
     */
    @objid ("01a2ae9d-188d-4703-b34a-8f5063055874")
    @Override
    protected boolean needsRepresentationModeSwitch() {
        if (this.isHorizontalLayoutCache != isHorizontalLayout()) {
            // update cache
            this.isHorizontalLayoutCache = isHorizontalLayout();
        
            // return true to make the edit part create its figure again
            return true;
        } else {
            return super.needsRepresentationModeSwitch();
        }
    }

    @objid ("7e48ecc3-8f08-4509-bdd1-3340f385b1a9")
    @Override
    protected void autoSizeNode(EditPart newEditPart) {
        // Orientation is changing, do not apply FitToMinSize, 
        // instead apply a  ExpandToContentCommand to child lanes recursively
        for (GraphicalEditPart child : (List<GraphicalEditPart>) newEditPart.getChildren()) {
            Object childModel = child.getModel();
            if (childModel instanceof GmBpmnLaneSetContainer || childModel instanceof GmBpmnLane || childModel instanceof GmBodyHybridContainer) {
                // Recurse on the child container first 
                autoSizeNode(child);
            }
        }
        
        ((GraphicalEditPart) newEditPart).getFigure().getUpdateManager().performValidation();
        ExpandToContentCommand cmd = new ExpandToContentCommand((GraphicalEditPart) newEditPart);
        cmd.execute();
    }

    @objid ("01217fe0-7e32-4f54-96a2-dc7ad686a64a")
    @Override
    protected void beforeSwitchRepresentationMode() {
        // The content must be transposed before the representation is remade because auto-size edit policies
        // combined to freezone layout constraining content inside bounds completely break the layout.
        for (GmBpmnLane gmLane : ((GmBpmnLaneSetContainer) getModel()).getLanes()) {
            transposeLayoutData(gmLane.getContentsArea());
        }
    }

    @objid ("46f7548d-748a-45b3-a26a-f6263caa6b90")
    private void transposeLayoutData(GmNodeModel node) {
        Transposer t = new Transposer();
        t.enable();
        if (node instanceof GmFreeZone) {
            for (GmNodeModel child : ((GmCompositeNode) node).getChildren()) {
                if (child.getLayoutData() instanceof Rectangle) {
                    // Transpose the center of bounds and apply the translation to initial bounds.
                    Rectangle before = (Rectangle) child.getLayoutData();
                    Point beforeCenter = before.getCenter();
                    Point afterCenter = t.t(beforeCenter);
                    
                    Rectangle after = before.getTranslated(afterCenter.x - beforeCenter.x, afterCenter.y - beforeCenter.y);
        
                    child.setLayoutData(after);
        
                    transposeLayoutData(child); 
                }
            }
        } else if (node instanceof GmPortContainer) {
            // ignore . Note: GmPortContainer inherit from GmCompositeNode so this test must be before GmCompositeNode test
        } else if (node instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode) node).getChildren()) {
                transposeLayoutData(child); 
            }
        
        }
    }

}

/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeEndReconnectEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;

/**
 * EditPart for a {@link GmBpmnMessage} Node in simple mode.
 */
@objid ("dc66b9c3-d9ba-41d8-a53d-52ad5ba7a6eb")
public class BpmnMessageSimpleEditPart extends AbstractNodeEditPart {
    @objid ("36bdfaac-82a5-4db0-ad61-cc403c53d5f7")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final BpmnMessageFigure fig = new BpmnMessageFigure();
        
        // set style independent properties
        MinimumSizeLayout.apply(fig, 40, 55);
        fig.setMinimumSize(new Dimension(40, 55));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("3a59bdfb-ab42-43e0-a55c-2bb809252815")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
    }

    @objid ("a5bb0ee2-deaa-463f-b5a5-b092698931d3")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy());
        installEditPolicy("linkedNode", new LinkedNodeEndReconnectEditPolicy());
        installEditPolicy(ModelElementDropRequest.class, new BpmnMessageElementDropEditPolicy());
        
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
    }

    @objid ("217713ac-06e7-4ca3-a11b-714a1e835f8c")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof BpmnMessageFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        }
    }

    @objid ("58d28b13-b7ca-448d-b7a9-8665ebd31a25")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Custom figure for {@link BpmnMessage} in simple mode.
     */
    @objid ("70b47b58-7922-42d8-baac-a5f1c3a6b05a")
    private static class BpmnMessageFigure extends GradientFigure {
        @objid ("b27a75c5-bb07-4e2a-886a-4a542ce7c4fa")
        private static final Dimension IDEAL_DIMENSIONS = new Dimension(40, 30);

        /**
         * Default c'tor building an opaque figure using a {@link ToolbarLayoutWithGrab}.
         */
        @objid ("e0f1c0a1-51f1-4efe-af17-8a685df868f8")
        public BpmnMessageFigure() {
            // Add layout
            final ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
            layout.setHorizontal(false);
            layout.setStretchMinorAxis(true);
            setLayoutManager(layout);
            setOpaque(true);
        }

        @objid ("bf928e46-7d37-4328-87a1-eaecfd71f3bc")
        @Override
        public Insets getInsets() {
            return IFigure.NO_INSETS;
        }

        @objid ("372f6fb1-404d-47e3-935b-2b7c392790fb")
        @Override
        protected void paintFigure(Graphics graphics) {
            Rectangle messageBounds = getBounds().getCopy();
            int initialHeight = messageBounds.height;
            
            // Compute the message bounds to preserve the ideal dimensions
            double wRatio = ((double) messageBounds.width) / (double) BpmnMessageFigure.IDEAL_DIMENSIONS.width;
            double hRatio = ((double) messageBounds.height) / (double) BpmnMessageFigure.IDEAL_DIMENSIONS.height;
            if (wRatio < hRatio) {
                messageBounds.setSize(messageBounds.width, (int) (BpmnMessageFigure.IDEAL_DIMENSIONS.height * wRatio));
            } else {
                messageBounds.setSize(messageBounds.width, (int) (BpmnMessageFigure.IDEAL_DIMENSIONS.height * hRatio));
            }
            
            // If the message is smaller than the figure itself, center it
            if (messageBounds.height < initialHeight) {
                messageBounds.translate(0, (initialHeight - messageBounds.height) / 2);
            }
            
            // Remove 1 pixel from each side to compensate rounding errors
            messageBounds.shrink(1, 1);
            
            // Draw the message's background
            if (isOpaque() && this.brushOptions.fillColor != null) {
                graphics.setAlpha(this.brushOptions.alpha);
            
                if (this.brushOptions.useGradient) {
                    Color gradientColor = computeGradientColor(this.brushOptions.fillColor);
                    graphics.setBackgroundColor(gradientColor);
                    graphics.fillGradient(messageBounds, false);
                    gradientColor.dispose();
                } else {
                    graphics.setBackgroundColor(this.brushOptions.fillColor);
                    graphics.fillRectangle(messageBounds);
                }
                graphics.restoreState();
            }
            
            // Draw the message itself
            graphics.setForegroundColor(this.penOptions.lineColor);
            graphics.setLineWidth(this.penOptions.lineWidth);
            graphics.drawRectangle(messageBounds);
            graphics.drawLine(messageBounds.x, messageBounds.y, messageBounds.x + messageBounds.width / 2, messageBounds.y + messageBounds.height / 2);
            graphics.drawLine(messageBounds.x + messageBounds.width / 2, messageBounds.y + messageBounds.height / 2, messageBounds.x + messageBounds.width - 1, messageBounds.y);
            
            graphics.restoreState();
        }

    }

}

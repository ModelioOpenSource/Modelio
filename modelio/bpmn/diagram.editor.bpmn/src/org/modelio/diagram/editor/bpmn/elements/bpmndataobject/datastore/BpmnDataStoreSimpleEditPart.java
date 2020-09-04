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

package org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datastore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.BpmnItemAwareElementElementDropEditPolicy;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.IShaper;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;

/**
 * EditPart for a {@link GmBpmnDataStore} Node in simple mode.
 */
@objid ("995d17b1-8073-4b33-8f34-dc3f47849143")
public class BpmnDataStoreSimpleEditPart extends AbstractNodeEditPart {
    @objid ("8f0044ed-c9b7-4767-88fd-90737fda051b")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final BpmnDataStoreFigure fig = new BpmnDataStoreFigure();
        
        // set style independent properties
        MinimumSizeLayout.apply(fig, 40, 55);
        fig.setMinimumSize(new Dimension(40, 55));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("5927c346-f410-4b33-ab2c-a59c409e3f89")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
    }

    @objid ("08514022-89ab-432b-af4f-53ecfe605fb5")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy(ModelElementDropRequest.TYPE, new BpmnItemAwareElementElementDropEditPolicy());
    }

    @objid ("177f8de5-df01-4f4b-a9d0-b0ac6c80da92")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof BpmnDataStoreFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        }
    }

    @objid ("df5ddcb3-d3f1-4d6c-b420-f67892e2bbcb")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Custom figure for {@link BpmnDataStore} in simple mode.
     */
    @objid ("703e0cda-2a42-46e5-8ecc-2338fc82be97")
    public static class BpmnDataStoreFigure extends ShapedFigure {
        @objid ("a5d4eb9c-c70f-4871-bfcb-233897cf092f")
        private ShapedBorder shapedBorder;

        /**
         * Default c'tor building an opaque figure using a {@link ToolbarLayoutWithGrab}.
         */
        @objid ("0182247e-86e0-4bd3-8ebf-172bcc494e00")
        public BpmnDataStoreFigure() {
            super(new BpmnDataStoreShaper());
            final ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
            layout.setHorizontal(false);
            layout.setStretchMinorAxis(true);
            this.setLayoutManager(layout);
            
            this.setOpaque(true);
            this.shapedBorder = new ShapedBorder(this.penOptions.lineColor, this.penOptions.lineWidth, this.shaper);
            setBorder(new CompoundBorder(this.shapedBorder, new MarginBorder(1)));
        }

        @objid ("ff65f4fe-36b5-403e-9a11-4a7cf0c956ae")
        @Override
        protected void paintFigure(Graphics graphics) {
            super.paintFigure(graphics);
            
            Rectangle shapeBounds = getBounds();
            
            graphics.pushState();
            graphics.setForegroundColor(this.penOptions.lineColor);
            graphics.setLineWidth(this.penOptions.lineWidth);
            
            int offset = BpmnDataStoreShaper.computeOffset(shapeBounds);
            graphics.drawArc(shapeBounds.x, shapeBounds.y, shapeBounds.width, offset * 2, 0, -180);
            graphics.drawArc(shapeBounds.x, shapeBounds.y + (shapeBounds.height / 4), shapeBounds.width, offset * 2, 0, -180);
            
            graphics.popState();
        }

        @objid ("5241f273-fad8-4ebe-a6b1-2530d87be046")
        @Override
        public void setLineColor(Color lineColor) {
            if (lineColor != this.penOptions.lineColor) {
                super.setLineColor(lineColor);
                this.shapedBorder.setColor(lineColor);
            }
        }

        @objid ("04380aef-bb0e-4980-aea2-019d4415cf63")
        @Override
        public void setLineWidth(int lineWidth) {
            if (lineWidth != this.penOptions.lineWidth) {
                super.setLineWidth(lineWidth);
                this.shapedBorder.setWidth(lineWidth);
            }
        }

        @objid ("4dd73ae2-a981-4dee-bbd2-e740d35d5961")
        private static class BpmnDataStoreShaper implements IShaper {
            @objid ("f823d257-45ce-492d-a557-39530855e574")
            public BpmnDataStoreShaper() {
                super();
            }

            @objid ("eb620b94-28f9-4878-a10b-30647aa343dd")
            @Override
            public Path getShapePath(Rectangle shapeBounds) {
                Path path = new Path(Display.getCurrent());
                
                int offset = computeOffset(shapeBounds);
                path.addArc(shapeBounds.x, shapeBounds.y, shapeBounds.width, offset * 2, 0, 180);
                path.moveTo(shapeBounds.x + shapeBounds.width, shapeBounds.y + offset);
                path.lineTo(shapeBounds.x + shapeBounds.width, shapeBounds.y + shapeBounds.height - offset);
                path.addArc(shapeBounds.x, shapeBounds.y + shapeBounds.height - offset * 2, shapeBounds.width, offset * 2, 0, -180);
                path.lineTo(shapeBounds.x, shapeBounds.y + offset);
                return path;
            }

            @objid ("8ba57ec8-faf2-4e88-8491-d9e619565fe7")
            private static int computeOffset(Rectangle shapeBounds) {
                return shapeBounds.height / 10;
            }

            @objid ("9cdc00c7-0f27-454f-9413-04599c674d6a")
            @Override
            public Insets getShapeInsets(Rectangle rect) {
                return IFigure.NO_INSETS;
            }

        }

    }

}

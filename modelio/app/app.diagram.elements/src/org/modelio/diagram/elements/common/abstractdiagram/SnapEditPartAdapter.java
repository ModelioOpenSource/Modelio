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

package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.rulers.RulerProvider;

/**
 * Provider of {@link SnapToHelper} for {@link GraphicalEditPart edit parts}.
 * <p>
 * To be used in implementation of {@link GraphicalEditPart#getAdapter(Class)} when c is {@link SnapToHelper}.
 * 
 * @author cmarin
 * @since 3.5.1
 * @credits Archi
 */
@objid ("6a135d52-1135-476d-a280-f478c7a5ac6f")
public class SnapEditPartAdapter {
    @objid ("f2a71aa3-d994-49b3-89f8-c6b4b5f039fd")
    private GraphicalEditPart editPart;

    @objid ("37a06147-5a66-47d1-84ee-7c6f21fdb434")
    private Collection<Data> otherContainers = Collections.emptyList();

    @objid ("736740c9-05dd-4e70-8b7e-e56194033fc0")
    public SnapEditPartAdapter(GraphicalEditPart editPart) {
        this.editPart = editPart;
    }

    /**
     * Generates the SnapToHelper.
     * 
     * @return a SnapToHelper
     */
    @objid ("e4012c1e-c134-468a-a879-f6e8e9cd104e")
    public SnapToHelper getSnapToHelper() {
        List<SnapToHelper> snapStrategies = new ArrayList<>();
        
        // Snap to Ruler Guides
        Boolean val = (Boolean)this.editPart.getViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);
        if(val != null && val.booleanValue()) {
            snapStrategies.add(new SnapToGuides(this.editPart));
        }
        
        // Snap to Geometry
        val = (Boolean)this.editPart.getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
        if(val != null && val.booleanValue()) {
            snapStrategies.add(new ModelioSnapToGeometry(this.editPart, 0));
            
            for (Data cont : this.otherContainers) {
                snapStrategies.add(new ModelioSnapToGeometry(cont.container, cont.margin));
            }
        }
        
        // Snap to Grid
        val = (Boolean)this.editPart.getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
        if(val != null && val.booleanValue()) {
            snapStrategies.add(new SnapToGrid(this.editPart));
        }
        
        if(snapStrategies.isEmpty()) {
            return null;
        }
        
        if(snapStrategies.size() == 1) {
            return snapStrategies.get(0);
        }
        
        SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
        
        for(int i = 0; i < snapStrategies.size(); i++) {
            ss[i] = snapStrategies.get(i);
        }
        return new CompoundSnapToHelper(ss);
    }

    /**
     * Add another container for geometry snapping.
     * <p>
     * All the container children will be considered, with their bounds expanded by the given margin.
     * If the margin is positive, the dragged parts insets will be also added to the margin
     * so that the dragged parts may draw a frame around the snapped to graphic.
     * 
     * @param container the container whose children geometry must be considered
     * @param margin the margin that should expand children bounds with.
     * @return this instance for calls chaining.
     */
    @objid ("420b2c18-c21c-40d6-afb3-9829da92cbd7")
    public SnapEditPartAdapter addContainer(GraphicalEditPart container, int margin) {
        if (this.otherContainers == Collections.EMPTY_LIST) {
            this.otherContainers = new ArrayList<>(3);
        }
        this.otherContainers.add(new Data(margin, container));
        return this;
    }

    /**
     * SnapToGeometry overridden to increase threshold.
     * @author cmarin
     * @since 3.5.1
     */
    @objid ("fb1908a6-9b22-4241-9203-9453cc20e45c")
    private static class ModelioSnapToGeometry extends SnapToGeometry {
        @objid ("4f2236b1-a0a7-4cf2-baff-9f2908c87c7e")
        private int margin;

        @objid ("ab011976-0f5e-4eb2-8691-d433eed8baeb")
        private Insets movedInset;

        @objid ("f84bbf9e-da7c-4e8f-8522-624948e9054a")
        public ModelioSnapToGeometry(GraphicalEditPart container, int margin) {
            super(container);
            this.margin = margin;
        }

        @objid ("3c26fda9-edc4-42b5-8900-f5294f9a0b78")
        @Override
        protected double getThreshold() {
            return 10;
        }

        @objid ("34a2d08d-4a5d-45c8-ac1e-11c04555413a")
        @Override
        public int snapRectangle(Request request, int snapOrientation, PrecisionRectangle baseRect, PrecisionRectangle result) {
            if (this.margin > 0 && this.movedInset == null && request instanceof GroupRequest) {
                // Cache the maximum insets of the dragged element(s)
                this.movedInset = new Insets();
                List<GraphicalEditPart> dragged = ((GroupRequest) request).getEditParts();
                for (GraphicalEditPart ep : dragged) {
                    Insets inset = ep.getFigure().getInsets();
                    this.movedInset.top = Math.max(this.movedInset.top, inset.top);
                    this.movedInset.bottom = Math.max(this.movedInset.bottom, inset.bottom);
                    this.movedInset.left = Math.max(this.movedInset.left, inset.left);
                    this.movedInset.right = Math.max(this.movedInset.right, inset.right);
                }
            }
            return super.snapRectangle(request, snapOrientation, baseRect, result);
        }

        @objid ("4af2b3ba-490a-4155-8980-c898f7121451")
        @Override
        protected Rectangle getFigureBounds(GraphicalEditPart part) {
            Rectangle ret = super.getFigureBounds(part);
            if (this.margin != 0) {
                if (this.margin > 0 && this.movedInset != null) {
                    // Add the maximum insets of the dragged element(s)
                    return ret.getExpanded(
                            this.margin + Math.max(this.movedInset.left, this.movedInset.right),
                            this.margin + Math.max(this.movedInset.top, this.movedInset.bottom));
                } else if (this.margin < 0) {
                    // what about //adding the edit part insets to the margin ?
                    // return ret.getShrinked(part.getFigure().getInsets()).expand(this.margin, this.margin);
                    return ret.getExpanded(this.margin, this.margin);
                } else {
                    return ret.getExpanded(this.margin, this.margin);
                }
            }
            return ret;
        }

    }

    @objid ("ff959c86-bfb6-4324-8252-cffb2cb04cbd")
    private static final class Data {
        @objid ("545e5df5-e2df-43e8-95c5-9134c3296725")
         final int margin;

        @objid ("47b6962f-d605-4538-9d07-f44b25909c72")
         final GraphicalEditPart container;

        @objid ("7df67877-6459-453e-afd2-8b65c7fa1e12")
        public Data(int margin, GraphicalEditPart container) {
            this.margin = margin;
            this.container = container;
        }

    }

}

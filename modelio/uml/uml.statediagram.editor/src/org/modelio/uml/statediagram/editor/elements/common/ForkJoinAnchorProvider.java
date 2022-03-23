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

import java.util.Objects;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.link.anchors.fixed.VariableFixedAnchorProvider;
import org.modelio.uml.statediagram.editor.elements.ForkJoinOrientation;

/**
 * Anchor provider for <b>Fork</b> and <b>Join</b> nodes, displaying anchors:
 * <ul>
 * <li>closer than the default {@link VariableFixedAnchorProvider}.</li>
 * <li>only vertically or horizontally, according to the node's orientation.</li>
 * </ul>
 */
@objid ("83431d78-522e-49d9-9210-1de1b7b42273")
public class ForkJoinAnchorProvider extends VariableFixedAnchorProvider {
    @objid ("9a28566b-21f2-42a5-80a7-0bdf609a72e3")
    private static final int DIST_BETWEEN_ANCHORS = 15;

    @objid ("0b6d6a60-944b-45cd-a433-1065380e88e9")
    private Supplier<ForkJoinOrientation> orientation;

    @objid ("90a51d4e-f7d7-44bb-828f-74ae1f5e6aa4")
    private ForkJoinMarginDependentComputedState state;

    /**
     * C'tor setting a default minimum distance of {@value #DIST_BETWEEN_ANCHORS} between anchors.
     */
    @objid ("fe2e9e4e-5df3-4630-b69c-077fbed96e7b")
    public  ForkJoinAnchorProvider(Supplier<ForkJoinOrientation> orientation) {
        super(DIST_BETWEEN_ANCHORS);
        this.orientation = orientation;
        
    }

    @objid ("58674d56-613d-4f41-afd9-02fd7a5a129f")
    @Override
    public void onFigureMoved(IFigure figure) {
        super.onFigureMoved(figure);
    }

    @objid ("356c8889-f5b3-4804-aeea-79a213735c97")
    @Override
    protected ComputedState createComputedState(IFigure aNodeFigure) {
        if (this.state == null) {
            this.state = new ForkJoinMarginDependentComputedState(this.margin, this.orientation);
        }
        return this.state;
    }

    /**
     * Refresh the provided anchors after the node orientation's changed.
     * @param aNodeFigure the figure providing anchors.
     */
    @objid ("81b59c88-201d-4286-8479-5b2fd92e4ef0")
    public void refresh(IFigure aNodeFigure) {
        if (this.state != null) {
            this.state.update(aNodeFigure);
        }
        
    }

    @objid ("3d30f142-09ba-444a-bf90-c16646a67e18")
    protected static class ForkJoinMarginDependentComputedState extends MarginDependentComputedState {
        @objid ("b404f579-a334-4f97-a3ac-e4ee0b1228c5")
        private final Supplier<ForkJoinOrientation> orientationSupplier;

        @objid ("2448bc05-8750-4116-8a10-3e38cc47d679")
        public  ForkJoinMarginDependentComputedState(int margin, Supplier<ForkJoinOrientation> orientationSupplier) {
            super(margin);
            this.orientationSupplier = orientationSupplier;
            
        }

        @objid ("6476979e-63a4-4eeb-b1bb-5c24858a1d56")
        public void reset() {
            super.invalidate();
            this.previousFigSize = new Dimension();
            
        }

        @objid ("43c56c62-390b-4d88-96b3-35a46b73921b")
        @Override
        protected boolean update(IFigure aNodeFig) {
            Dimension figSize = aNodeFig.getSize();
            
            boolean sizeChanged = !Objects.equals(this.previousFigSize, figSize);
            
            if (sizeChanged) {
                // compute how many time the label fit on horizontal and vertical faces
                int nHorizontal = Math.max(1, figSize.width / this.margin - 0);
                int nVertical = Math.max(1, figSize.height / this.margin - 0);
            
                if (this.orientationSupplier.get() == ForkJoinOrientation.HORIZONTAL) {
                    nVertical = 0;
            
                    // ensure count is odd
                    if (nHorizontal % 2 == 0) {
                        nHorizontal--;
                    }
                } else {
                    nHorizontal = 0;
            
                    // ensure count is odd
                    if (nVertical % 2 == 0) {
                        nVertical--;
                    }
                }
            
                this.anchorsCount.setSize(nHorizontal, nVertical);
                this.previousFigSize = figSize;
            }
            
            // Call inherited behavior
            boolean ret = super.update(aNodeFig);
            return ret && !figSize.isEmpty();
        }

    }

}

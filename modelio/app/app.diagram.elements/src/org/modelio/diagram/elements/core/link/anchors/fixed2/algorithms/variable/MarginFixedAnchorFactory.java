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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.variable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.ConfigurableFixedAnchorFactory;

/**
 * Default Factory of all possible FixedAnchors for a rectangular figure.
 */
@objid ("ea0285c1-ba16-4249-8b2f-4e616b131b30")
public class MarginFixedAnchorFactory extends ConfigurableFixedAnchorFactory {
    @objid ("be094803-2c26-45e4-8845-cd94805310e2")
    private static final int DIST_BETWEEN_ANCHORS = 30;

    /**
     * Distance between each anchor.
     */
    @objid ("fdd40877-cf81-4407-a2f8-f066e360c766")
    protected final int margin;

    /**
     * C'tor setting a default minimum distance of {@value #DIST_BETWEEN_ANCHORS} between anchors.
     * @param algoId the algorithm id
     * @param locator the anchor locator
     */
    @objid ("306e9eef-d031-4cd5-85d3-efb21ede6b3a")
    public  MarginFixedAnchorFactory(String algoId, IFixedAnchorLocator locator) {
        this(algoId, DIST_BETWEEN_ANCHORS, locator);
    }

    /**
     * C'tor setting a default minimum distance of {@value #DIST_BETWEEN_ANCHORS} between anchors
     * and no locator for the moment.
     * @see #setLocator(IFixedAnchorLocator)
     * @see #setWrappedLocator(IFixedAnchorLocator)
     * @param algoId the algorithm id
     */
    @objid ("44a61a55-148a-43d0-82c3-4fa4abb246fc")
    public  MarginFixedAnchorFactory(String algoId) {
        this(algoId, DIST_BETWEEN_ANCHORS, null);
        
        setLocator(new VariableFixedAnchorLocator(getAlgorithmId(),  this::getAnchorCount));
        
    }

    /**
     * C'tor.
     * @param algoId the algorithm id
     * @param margin the minimum distance to keep between anchors.
     * @param locator the anchor locator
     */
    @objid ("3ac1c8e8-d2d4-4a02-87f1-f218aa9ea3ed")
    public  MarginFixedAnchorFactory(String algoId, int margin, IFixedAnchorLocator locator) {
        super(algoId, locator);
        this.margin = margin;
        
    }

    /**
     * Wrap the locator in a {@link VariableWrappedAnchorLocator} set sets the result as the {@link #getLocator()}.
     * @param locator the locator to wrap then set
     * @return this instance
     */
    @objid ("91c7a87d-3243-440d-ad14-34f065a40086")
    public MarginFixedAnchorFactory setWrappedLocator(IFixedAnchorLocator locator) {
        setLocator(new VariableWrappedAnchorLocator(locator, this::fillAnchorCount));
        return this;
    }

    @objid ("7b91ee04-174b-4bf4-9543-e5864b6d19c4")
    @Override
    public void fillAnchorCount(Dimension out) {
        Dimension figSize = getNodeFigure().getSize();
        
        // compute how many time the 'margin' fit on horizontal and vertical faces
        int nHorizontal = Math.max(1, figSize.width / this.margin - 0);
        int nVertical = Math.max(1, figSize.height / this.margin - 0);
        
        // ensure count is odd
        if (nHorizontal % 2 == 0) {
            nHorizontal++;
        }
        if (nVertical % 2 == 0) {
            nVertical++;
        }
        out.setSize(nHorizontal, nVertical);
        
    }

}

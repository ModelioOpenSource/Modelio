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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsAbstractFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsFactory;

@objid ("3354604c-4b05-4913-80f0-5448189d991e")
public class ConfigurableFixedAnchorFactory extends AbstractFixedAnchorFactory implements IFigureAnchorsAbstractFactory {
    /**
     * This instance is protected from external writes.
     */
    @objid ("10849fa9-1611-4d3b-85da-e58bb0e4bd07")
    private final Dimension anchorCount = new Dimension();

    @objid ("e07c11e3-9b77-4a53-966a-999a84782c89")
    private GraphicalEditPart editPart;

    @objid ("77915820-2792-44ed-a43d-fcbbe22f6083")
    private IFixedAnchorLocator locator;

    @objid ("3ca08de2-1db2-49b6-9215-131b1d3d1bb7")
    private IFigure figure;

    @objid ("8409ef98-4ec7-4a0c-9f92-231f668404be")
    public  ConfigurableFixedAnchorFactory(String algorithmId, IFixedAnchorLocator locator) {
        super(algorithmId);
        this.locator = locator;
        
    }

    /**
     * Updates {@link #getNodeEdtPart()} and returns this instance.
     * <p>
     * {@inheritDoc}
     */
    @objid ("bd212a07-8d15-4c1c-8030-f2a61cec2160")
    @Override
    public IFigureAnchorsFactory getAnchorFactoryFor(GraphicalEditPart anEditPart, IFigure aFigure) {
        this.editPart = anEditPart;
        this.figure = aFigure;
        return this;
    }

    @objid ("d4647d98-1e9a-471e-80ec-8f5ee16582ca")
    @Override
    protected IFixedAnchorLocator getLocator() {
        return this.locator;
    }

    @objid ("542dcd36-d85f-45bd-a032-6ac538b61180")
    @Override
    protected GraphicalEditPart getNodeEdtPart() {
        return this.editPart;
    }

    @objid ("ac224884-7a7e-413c-99fa-f705c2636ccd")
    @Override
    protected IFigure getNodeFigure() {
        return this.figure;
    }

    @objid ("17e45717-e684-43d1-bcda-d6092a2c60e9")
    @Override
    public void fillAnchorCount(Dimension out) {
        out.setSize(this.anchorCount);
    }

    @objid ("817cb4c9-f1af-4ba5-8bdc-cffefc63fd0b")
    public ConfigurableFixedAnchorFactory setAnchorCount(Dimension anchorCount) {
        this.anchorCount.setSize(anchorCount);
        return this;
    }

    @objid ("aa6a531a-1536-4a39-a6f5-32ff2ceecaea")
    public ConfigurableFixedAnchorFactory setAnchorCount(int northSouthFacesCount, int eastWestFacesCount) {
        this.anchorCount.setSize(northSouthFacesCount, eastWestFacesCount);
        return this;
    }

    @objid ("09520311-112d-4d83-a62b-a3cecbab6d3a")
    public ConfigurableFixedAnchorFactory setLocator(IFixedAnchorLocator locator) {
        this.locator = locator;
        return this;
    }

}

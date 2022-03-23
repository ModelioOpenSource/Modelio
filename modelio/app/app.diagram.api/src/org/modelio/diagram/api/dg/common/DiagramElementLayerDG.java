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
package org.modelio.diagram.api.dg.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.dg.IDiagramElementsLayer;
import org.modelio.api.modelio.diagram.dg.IDiagramLayer;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramAbstractNode;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Represents the diagram elements layer.
 */
@objid ("239f893a-bdca-4ee7-ab53-5a867a3fd8fb")
public class DiagramElementLayerDG extends DiagramAbstractNode implements IDiagramElementsLayer {
    @objid ("9c7132aa-b496-49bd-a440-e34e74049493")
    private GmAbstractDiagram model;

    /**
     * C'tor
     * @param diagramHandle the diagram handle
     * @param model the graphic model
     */
    @objid ("0eda5c41-e23f-4fb5-bba3-40ef4eb6db56")
    public  DiagramElementLayerDG(DiagramHandle diagramHandle, GmAbstractDiagram model) {
        super(diagramHandle);
        this.model = model;
        
    }

    @objid ("6d5a99f9-57ad-4bbe-8770-2e53f8244150")
    @Override
    public List<IDiagramNode> getNodes() {
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, this.model.getVisibleChildren());
    }

    @objid ("a6e99267-eb65-40bb-9399-2850ce66a10f")
    @Override
    public List<IDiagramLink> getLinks() {
        return getElementLinks();
    }

    @objid ("d685381f-e1d1-4c74-9b38-3006f4e655a8")
    @Override
    public IDiagramGraphic getParent() {
        return this.diagramHandle.getDiagramNode();
    }

    @objid ("abe567c0-444b-4a8b-a40f-c425838b58cf")
    @Override
    public int getRepresentationMode() {
        return 0;
    }

    @objid ("40424f0e-6125-49a0-a875-d082eb777438")
    @Override
    public void setRepresentationMode(int value) {
        // ignore
    }

    @objid ("435fb22c-5ac3-4c18-9191-b92ce68b197b")
    @Override
    public MObject getElement() {
        return this.model.getRelatedElement();
    }

    @objid ("6ed16c06-e534-48e3-b1a3-8dea677b4d27")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("a0360915-dbd7-4b85-815f-5cd65eb0be8b")
    @Override
    public String getName() {
        return IDiagramElementsLayer.MAIN;
    }

    @objid ("123c3dc2-89d4-41bb-bd32-ed935b0bf2ee")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

    @objid ("5c343825-2b7c-4dd1-9093-433fcd2b29b1")
    @Override
    public List<IDiagramNode> getElementNodes() {
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, this.model.getVisibleChildren());
    }

    @objid ("8ae3bb50-7cd5-4e36-843b-da0a0ccb4987")
    @Override
    public List<IDiagramLink> getElementLinks() {
        List<IDiagramLink> links = new ArrayList<>();
        
        for (GmModel gm : this.model.getDiagram().getAllModels()) {
            if (gm instanceof IGmLink) {
                IDiagramGraphic diagramLink = DGFactory.getInstance().getDiagramGraphic(this.diagramHandle, gm);
                if (diagramLink instanceof IDiagramLink) {
                    links.add((IDiagramLink) diagramLink);
                }
            }
        }
        return links;
    }

    @objid ("5e32a017-3224-42c7-a6c0-4ddebf368d71")
    @Override
    public IGmObject getModel() {
        return this.model;
    }

    /**
     * Layers don't belong to a layer.
     */
    @objid ("bbe09b02-cf1a-49c1-a95b-1e378f9d80d3")
    @Override
    public IDiagramLayer getLayer() {
        return null;
    }

    @objid ("961d27df-0e79-4b3f-bac6-d3c2f2721add")
    @Override
    public void moveToLayer(IDiagramLayer newLayer) throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Layers cannot be moved.");
    }

    @objid ("95db2944-a033-4dfb-ada3-7b6ddb78d5a6")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        switch (role) {
        case INNER:
            return getNodes();
        default:
            return Collections.emptyList();
        }
        
    }

}

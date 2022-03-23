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
package org.modelio.diagram.diagramauto.diagram.creator;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;

@objid ("c1728ca4-73fd-43c7-8c22-ae85bb52c6bd")
public class ClassArchitectureDiagramTemplate extends ClassStructureDiagramTemplate {
    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("bca0d640-874f-4546-bf24-8122867469b3")
    public  ClassArchitectureDiagramTemplate() {
        super();
    }

    @objid ("d165fa36-848c-4380-8975-33013fd0d7eb")
    @Override
    public String getOldType() {
        return "architecture_autodiagram";
    }

    @objid ("fe4a48c0-8e49-463d-b010-2086b5dc5c86")
    @Override
    protected void generateContent(final IDiagramHandle dh, final Classifier main) {
        super.generateContent(dh, main);
        // Unmask incoming associations
        for (AssociationEnd a : main.getTargetingEnd()) {
            if (a.getAggregation() == AggregationKind.KINDISASSOCIATION) {
                // Unmask right node
                Classifier owner = a.getSource();
                List<IDiagramGraphic> nodes = dh.unmask(owner, 0, 0);
                if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                    IDiagramNode node = (IDiagramNode) nodes.get(0);
                    // Add intern/extern style
                    initStyle(main, owner, node);
                    this._leftDgs.add(node);
                }
                // Unmask outgoing link
                List<IDiagramGraphic> links = dh.unmask(a.getAssociation(), 0, 0);
                if ((links != null) && (links.size() > 0)) {
                    IDiagramLink link = (IDiagramLink) links.get(0);
        
                    if (link.getFrom().equals(link.getTo())) {
                        this._reflexiveLinksDgs.add(link);
                    }
                }
            }
        }
        
        // unmask generalizations
        for (Generalization g : main.getSpecialization()) {
            // Unmask parent node
            NameSpace child = g.getSubType();
            List<IDiagramGraphic> nodes = dh.unmask(child, 0, 0);
            if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                IDiagramNode node = (IDiagramNode) nodes.get(0);
        
                // Add intern/extern style
                initStyle(main, child, node);
        
                this._bottomDgs.add(node);
        
                // Unmask link
                List<IDiagramGraphic> links = dh.unmask(g, 0, 0);
                if ((links != null) && (links.size() > 0)) {
                    IDiagramLink link = (IDiagramLink) links.get(0);
        
                    if (link.getFrom().equals(link.getTo())) {
                        this._reflexiveLinksDgs.add(link);
                    }
                }
            }
        }
        
        // unmask realizations
        if (main instanceof Interface) {
            for (InterfaceRealization ir : ((Interface) main).getImplementedLink()) {
                // Unmask parent node
                NameSpace child = ir.getImplementer();
                List<IDiagramGraphic> nodes = dh.unmask(child, 0, 0);
                if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                    IDiagramNode node = (IDiagramNode) nodes.get(0);
                    // Add intern/extern style
                    initStyle(main, child, node);
                    this._bottomDgs.add(node);
                }
        
                // Unmask link
                List<IDiagramGraphic> links = dh.unmask(ir, 0, 0);
                if ((links != null) && (links.size() > 0)) {
                    IDiagramLink link = (IDiagramLink) links.get(0);
        
                    if (link.getFrom().equals(link.getTo())) {
                        this._reflexiveLinksDgs.add(link);
                    }
                }
            }
        }
        
    }

}

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

package org.modelio.bpmnxml.importer.service.processor.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.importer.service.processor.IBPMNImportProcessor;
import org.modelio.bpmnxml.nodes.IProduction;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.nodes.ProductionFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("ba6d843a-2030-4b57-9f30-ca65319a4cef")
public class UpdateElementProcessorUpdate implements IBPMNImportProcessor {
    @objid ("ba720d9e-122c-4dc3-b575-de697605d2a8")
    private ICoreSession session;

    @objid ("8dc875e5-c8be-49e6-b96a-abe9bd1b48fa")
    private IDiagramService diagramService;

    @objid ("219e61ee-6329-43ad-bd5e-4b89cf2841ad")
    private ProductionFactory factory;

    @objid ("df6da58a-921f-4f59-a34b-926c96b33ece")
    private Map<String, Object> elementsMap;

    @objid ("01ae6b4a-ae11-456f-bbea-a8f2d143ae26")
    public List<Object> getCreatedElements() {
        return new ArrayList<>(this.elementsMap.values());
    }

    @objid ("125799c3-9309-4855-be83-3d7e1b104247")
    public UpdateElementProcessorUpdate(ICoreSession session, IDiagramService diagramService, Map<String, Object> elementsMape) {
        this.session = session;
        this.diagramService = diagramService;
        this.elementsMap = elementsMape;
        this.factory = new ProductionFactory();
    }

    /**
     * Update Modelio element property from a JaxbElement.
     */
    @objid ("11345a8e-22eb-4c69-9ec2-c6baf978d9a5")
    @Override
    public MObject process(MObject context, Object jaxbElement) {
        IProduction node = this.factory.getImportProductionNode(jaxbElement);
        if (node != null) {
            node.setElements(this.elementsMap);
        
            MObject modelioElement = (MObject) this.elementsMap.get(IDUtils.getJaxbId(context, jaxbElement));
        
            if (node instanceof IProductionNode) {
                modelioElement = ((IProductionNode) node).updateUMLElement(context, modelioElement, jaxbElement);
            }
            return modelioElement;
        }
        return null;
    }

}

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

package org.modelio.bpmnxml.exporter.service.processor.export;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.exporter.service.processor.IBPMNProcessor;
import org.modelio.bpmnxml.nodes.IFinaliseNode;
import org.modelio.bpmnxml.nodes.ProductionFactory;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e394820b-154e-411f-a973-1f9371eae5ef")
public class FinalizeProcessorExport implements IBPMNProcessor {
    @objid ("66c77ed0-44fd-4d6a-a0c4-0ef907dfc942")
    private IDiagramService diagramService;

    @objid ("d0a96f11-1ecc-490e-8522-75b2d901bef6")
    private ProductionFactory factory;

    @objid ("fec9a12e-db63-4543-9baa-19c323cd3cd6")
    private Map<String, Object> elementsMap;

    @objid ("0a21d72e-20be-466d-9f2f-4cc241cb41b6")
    private ICoreSession session;

    @objid ("e8286d9c-7b03-45ae-912e-b02c821aa869")
    private Map<String, Object> configuration;

    @objid ("75f5d878-4d44-4844-91a9-cb25e1290871")
    public FinalizeProcessorExport(IDiagramService diagramService, Map<String, Object> elementsMap, Map<String, Object> configuration) {
        this.diagramService = diagramService;
        this.elementsMap = elementsMap;
        this.factory = new ProductionFactory();
        this.configuration = configuration;
    }

    @objid ("6f9bb4a8-b9d6-48dc-bd2e-e6e48e3214a2")
    @Override
    public Object process(Object context, Object modelioElement) {
        IFinaliseNode node = this.factory.getExportFinalizationNode(modelioElement);
        
        if (node != null) {
            node.setElements(this.elementsMap);
            node.setConfiguration(this.configuration);
            Object jaxbElement = this.elementsMap.get(((MObject) modelioElement).getUuid());
            node.finalizeJaxbElement((MObject)modelioElement, jaxbElement, this.diagramService);
            return jaxbElement;
        }
        return null;
    }

}

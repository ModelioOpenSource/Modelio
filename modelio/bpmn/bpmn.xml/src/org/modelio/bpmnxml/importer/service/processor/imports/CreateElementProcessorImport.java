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

package org.modelio.bpmnxml.importer.service.processor.imports;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.importer.service.processor.IBPMNImportProcessor;
import org.modelio.bpmnxml.nodes.IProduction;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.nodes.ProductionFactory;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("2373017f-6270-49dd-9e80-919b0f498c0a")
public class CreateElementProcessorImport implements IBPMNImportProcessor {
    @objid ("796ac16f-cd9a-4f08-b96c-cc1caafe4a21")
    private boolean keepId;

    @objid ("b77289bc-79b3-46b1-8a8c-c8b75487f1fb")
    private ICoreSession session;

    @objid ("fc77eb97-e9fe-43b8-be5b-8cab32b13db1")
    private IDiagramService diagramService;

    @objid ("7f3dc575-1d7d-497c-b956-d7911b8ad964")
    private ProductionFactory factory;

    @objid ("8bd3f75a-e100-4edb-a58b-cf0c0ba89f36")
    private Map<String, Object> elementsMap;

    @objid ("b5961bbf-9997-4bdd-a7e2-c8a281970a63")
    private AbstractDiagram diagram;

    @objid ("89ca82ef-450f-4c51-9527-1c1afdee7ac6")
    public CreateElementProcessorImport(ICoreSession session, IDiagramService diagramService, Map<String, Object> elementsMap, boolean keepId) {
        this.session = session;
        this.diagramService = diagramService;
        this.elementsMap = elementsMap;
        this.factory = new ProductionFactory();
        this.keepId = keepId;
    }

    @objid ("634d7476-0d3f-4fca-b952-a18d76b53d49")
    @Override
    public MObject process(MObject context, Object jaxbElement) {
        IProduction node = this.factory.getImportProductionNode(jaxbElement);
        if (node instanceof IProductionNode) {
            node.setElements(this.elementsMap);
            BpmnImportFactory elementFactory = new BpmnImportFactory((CoreSession) this.session);
            MObject modelioElement = ((IProductionNode) node).createUMLElement(context, jaxbElement, elementFactory, this.keepId);
            this.elementsMap.put(IDUtils.getJaxbId(context, jaxbElement), modelioElement);
            return modelioElement;
        }
        return null;
    }

}

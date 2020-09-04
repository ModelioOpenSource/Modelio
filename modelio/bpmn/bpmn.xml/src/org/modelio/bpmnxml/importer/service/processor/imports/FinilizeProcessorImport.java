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
import org.modelio.bpmnxml.nodes.IFinaliseNode;
import org.modelio.bpmnxml.nodes.ProductionFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d2335375-8360-4483-8462-f9d9fd109d4e")
public class FinilizeProcessorImport implements IBPMNImportProcessor {
    @objid ("97314952-708b-4820-8bbc-896308922b3e")
    private IDiagramService diagramService;

    @objid ("9f66e0db-70c8-4a9c-9875-83755f17f33c")
    private ProductionFactory factory;

    @objid ("f4f4e8e2-fa85-4dde-a881-65150d7dd5ec")
    private Map<String, Object> elementsMap;

    @objid ("6fb0979b-7754-4411-b02d-abfc9c72e842")
    private ICoreSession session;

    @objid ("4555f969-4d06-4d46-84dd-ff81641c276a")
    public FinilizeProcessorImport(ICoreSession session, IDiagramService diagramService, Map<String, Object> elementsMap) {
        this.diagramService = diagramService;
        this.elementsMap = elementsMap;
        this.session = session;
        this.factory = new ProductionFactory();
    }

    @objid ("0af18cd6-b6ba-4e18-904d-f3d3c2b32556")
    @Override
    public MObject process(MObject context, Object jaxbElement) {
        IFinaliseNode node = this.factory.getImportFinalizationNode(jaxbElement);
        
        if(node != null){
            node.setElements(this.elementsMap);
            Object modelioElement = this.elementsMap.get(IDUtils.getJaxbId(context, jaxbElement));
            node.finalizeElement((MObject)modelioElement, jaxbElement, this.session, this.diagramService);
        }
        return  (MObject)this.elementsMap.get(IDUtils.getJaxbId(context,jaxbElement));
    }

}

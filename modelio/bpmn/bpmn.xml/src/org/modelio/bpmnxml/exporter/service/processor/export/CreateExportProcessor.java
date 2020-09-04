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

package org.modelio.bpmnxml.exporter.service.processor.export;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.exporter.service.processor.IBPMNProcessor;
import org.modelio.bpmnxml.nodes.IProduction;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.nodes.ProductionFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e1f3bbbf-57eb-46e6-8521-0ae1b260e2f4")
public class CreateExportProcessor implements IBPMNProcessor {
    @objid ("37a3d9f3-2424-4e32-98e6-2a4ca09dd166")
    private ProductionFactory factory;

    @objid ("bd1ab771-7e87-4ad4-a9ad-ec1352249a9a")
    private Map<String, Object> elementsMap;

    @objid ("97f7fac3-492d-49bb-9c8d-f20fc405fbfb")
    private IDiagramService diagramService;

    @objid ("b333e68f-7d02-43b2-9c3e-fd521742f713")
    public CreateExportProcessor(IDiagramService diagramService, Map<String, Object> elementsMap) {
        this.factory = new ProductionFactory();
        this.elementsMap = elementsMap;
        this.diagramService = diagramService;
    }

    @objid ("a2c5bfff-3dc3-4c48-a410-b5286b2d2a3f")
    @Override
    public Object process(Object context, Object modelioElement) {
        IProduction node = this.factory.getExportProductionNode(modelioElement);
        if(node != null){
            node.setElements(this.elementsMap);
        
            if(node instanceof IProductionNode){
                Object jaxbElement =  ((IProductionNode)node).createJaxbElement(context,(MObject) modelioElement);
                this.elementsMap.put(((MObject) modelioElement).getUuid(),jaxbElement);
                return jaxbElement;
            }
        
        }
        return null;
    }

}

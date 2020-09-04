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

@objid ("40b46aee-0318-4255-b6da-a38dc0052f95")
public class UpdateExportProcessor implements IBPMNProcessor {
    @objid ("48d9c30d-2d00-4c39-b064-53f6a98febaf")
    private ProductionFactory factory;

    @objid ("88d4c017-b63d-46d6-bdee-8011618e4595")
    private Map<String, Object> elementsMap;

    @objid ("94ba4cab-f1c8-4dbe-ad98-9a9bf7411a7f")
    private IDiagramService diagramService;

    @objid ("b3821243-fdb0-46c5-ad7e-efc43c84598b")
    public UpdateExportProcessor(IDiagramService diagramService, Map<String, Object> elementsMap) {
        this.factory = new ProductionFactory();
        this.elementsMap = elementsMap;
        this.diagramService = diagramService;
    }

    @objid ("c68837d5-a9b6-4038-bf1e-0d7ca6b18e01")
    @Override
    public Object process(Object context, Object modelioElement) {
        IProduction node = this.factory.getExportProductionNode(modelioElement);
        if(node != null){
            node.setElements(this.elementsMap);
        
        
            if(node instanceof IProductionNode){
                Object jaxbElement = this.elementsMap.get(((MObject) modelioElement).getUuid());
                return  ((IProductionNode)node).updateJaxbElement(context,jaxbElement,(MObject) modelioElement);     
            }
        }
        return null;
    }

}

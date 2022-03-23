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
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8750f83b-abef-44ef-b307-e9a6dfccb531")
public class UpdateElementProcessorImport implements IBPMNImportProcessor {
    @objid ("00afda13-a77c-4fa1-bd7b-37a858e993b9")
    private ICoreSession session;

    @objid ("f7f7df99-4d90-4135-aea6-178eac6ee880")
    private IDiagramService diagramService;

    @objid ("e88df387-1af0-41f8-a4bc-d9d31ace1a19")
    private ProductionFactory factory;

    @objid ("0cc1f518-0741-444d-8508-5cd993732805")
    private Map<String, Object> elementsMap;

    @objid ("b64fe30f-02aa-4102-983a-a86cfb388bff")
    private AbstractDiagram diagram;

    @objid ("256042e5-9b7b-4861-8e7d-b54eb0351bde")
    public  UpdateElementProcessorImport(ICoreSession session, IDiagramService diagramService, Map<String, Object> elementsMap) {
        this.session = session;
        this.diagramService = diagramService;
        this.elementsMap = elementsMap;
        this.factory = new ProductionFactory();
        
    }

    @objid ("de862a4a-3608-4cc6-9586-5564290748e4")
    @Override
    public MObject process(MObject context, Object jaxbElement) {
        IProduction node = this.factory.getImportProductionNode(jaxbElement);
        if(node != null){
            node.setElements(this.elementsMap);
        
            MObject modelioElement = (MObject)this.elementsMap.get(IDUtils.getJaxbId(context,jaxbElement));
        
            if(modelioElement != null){
                if (node instanceof IProductionNode) {
                    try{
                        modelioElement = ((IProductionNode) node).updateUMLElement(context,modelioElement, jaxbElement);
                    }catch(Exception e){
                        e.printStackTrace();
                    }     
                }
            }
            return modelioElement;
        }
        return null;
    }

}

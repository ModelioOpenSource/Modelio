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
package org.modelio.bpmnxml.exporter.service;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.exporter.service.processor.export.CreateExportProcessor;
import org.modelio.bpmnxml.exporter.service.processor.export.FinalizeProcessorExport;
import org.modelio.bpmnxml.exporter.service.processor.export.UpdateExportProcessor;
import org.modelio.bpmnxml.exporter.service.walker.BPMNWalker;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("421e5041-a3ed-405d-9368-de8ec37f0076")
public class BPMNExportService {
    @objid ("b39a1667-f51b-4c9a-80fc-d75d65768fc2")
    private IDiagramService diagramService;

    @objid ("4c414e4b-195e-4214-b9a3-539f60f7fc8b")
    private IProgressMonitor progress;

    @objid ("2a8e5e95-79fb-4dd2-9cae-3d8b2ce67416")
    private ICoreSession session;

    @objid ("ba9e1948-f01c-4ae9-a6ad-31aaa9adacfb")
    public  BPMNExportService(IDiagramService diagramService, ICoreSession session) {
        this.diagramService = diagramService;
        this.session  = session;
        
    }

    @objid ("581cc75f-698e-4fc1-910e-72a2b9f5d839")
    public void exportBPMN(Path xpdlFile, ModelElement context, Map<String, Object> configuration) throws JAXBException {
        Map<String, Object> elementsMap = new HashMap<>();
        
        ObjectFactory jaxFactory = new ObjectFactory();
        TDefinitions jaxDefinition = new TDefinitions();
        jaxDefinition.setTargetNamespace("http://www.omg.org/bpmn20");
        
        elementsMap.put("TDefinitions", jaxDefinition);
        
        // Create JaxbElements
        CreateExportProcessor createProcessor = new CreateExportProcessor(this.diagramService,elementsMap);
        BPMNWalker createWalker = new BPMNWalker(context,createProcessor,this.diagramService);
        createWalker.walk(jaxDefinition,this.progress);
        
        // Update properties and resolve depencencys of jaxbElements
        UpdateExportProcessor updateProcessor = new UpdateExportProcessor(this.diagramService,elementsMap);
        BPMNWalker updateWalker = new BPMNWalker(context,updateProcessor,this.diagramService);
        updateWalker.walk(jaxDefinition,this.progress);
        
        
        // Show Element in diagram
        FinalizeProcessorExport showProcessor = new FinalizeProcessorExport(this.diagramService, elementsMap,configuration);
        BPMNWalker showWalker = new BPMNWalker(context, showProcessor,this.diagramService);
        showWalker.walk(jaxDefinition, this.progress);
        
         
        if(xpdlFile.toFile().exists()){
            xpdlFile.toFile().delete();
        }
        
        writeXPDLFile(xpdlFile, jaxFactory.createDefinitions(jaxDefinition));
        
    }

    @objid ("395084fa-0eb5-46f0-bb7e-bed4775f8579")
    public void setProgressMonitor(IProgressMonitor progress) {
        this.progress = progress;
    }

    @objid ("4225649f-aeb8-41b7-af27-670b02f0098a")
    private void writeXPDLFile(Path xpdlFile, JAXBElement<? extends TDefinitions> packageType) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("org.modelio.bpmnxml.model");
        Marshaller  marchaller = jc.createMarshaller();
        
        
        marchaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marchaller.marshal(packageType, xpdlFile.toFile());
        
    }

}

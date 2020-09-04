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

package org.modelio.bpmnxml.importer.service;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.importer.service.processor.imports.CreateElementProcessorImport;
import org.modelio.bpmnxml.importer.service.processor.imports.FinilizeProcessorImport;
import org.modelio.bpmnxml.importer.service.processor.imports.UpdateElementProcessorImport;
import org.modelio.bpmnxml.importer.service.processor.update.CreateElementProcessorUpdate;
import org.modelio.bpmnxml.importer.service.processor.update.FinilizeProcessorUpdate;
import org.modelio.bpmnxml.importer.service.processor.update.UpdateElementProcessorUpdate;
import org.modelio.bpmnxml.importer.service.walker.JaxbWalker;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("157a5f40-d6fd-40de-81e6-7aa093a3ca6a")
public class BPMNImportService {
    @objid ("77e76602-274f-496d-af55-4d7bbb9988b1")
    private IDiagramService diagramService;

    @objid ("3f9178c5-6d91-4e74-a1b8-453a301bc894")
    private ICoreSession session;

    @objid ("4d08c359-8737-4d88-b9bc-2008b5b684c3")
    private IProgressMonitor progress;

    @objid ("02d23fcc-3b92-4ff8-9db7-db4fcb51ec61")
    public BPMNImportService(ICoreSession session, IDiagramService diagramService) {
        this.session = session;
        this.diagramService = diagramService;
    }

    @objid ("34fb19db-2b04-442e-8f26-d81561e53f1b")
    public void setProgressMonitor(IProgressMonitor progress) {
        this.progress = progress;
    }

    @objid ("b6f472c6-4b42-4853-82cb-0948be509964")
    public void importBPMN(Path xpdlFile, Package context, boolean keepId) throws FileNotFoundException, JAXBException {
        if (!xpdlFile.toFile().exists()) {
            throw new FileNotFoundException();
        }
        
        TDefinitions jaxbRoot = readXPDLFile(xpdlFile);
        try (ITransaction tr = this.session.getTransactionSupport().createTransaction("XPDLImportService")) {
        
            Map<String, Object> elementsMap = new HashMap<>();
        
            // Create Modelio Element
            CreateElementProcessorImport createProcessor = new CreateElementProcessorImport(this.session, this.diagramService, elementsMap, keepId);
            JaxbWalker createWalker = new JaxbWalker(jaxbRoot, createProcessor);
            createWalker.walk(context, this.progress);
        
            // Update Modelio Element properties
            UpdateElementProcessorImport updateProcessor = new UpdateElementProcessorImport(this.session, this.diagramService, elementsMap);
            JaxbWalker updateWalker = new JaxbWalker(jaxbRoot, updateProcessor);
            updateWalker.walk(context, this.progress);
        
            // Show Element in diagram
            FinilizeProcessorImport showProcessor = new FinilizeProcessorImport(this.session, this.diagramService, elementsMap);
            JaxbWalker showWalker = new JaxbWalker(jaxbRoot, showProcessor);
            showWalker.walk(context, this.progress);
        
            tr.commit();
        }
    }

    @objid ("9bee80a7-fac9-42f0-90ac-1f1dbe99e61b")
    public void updateBPMN(Path xpdlFile, BpmnProcess context, boolean keepId) throws FileNotFoundException, JAXBException {
        if (!xpdlFile.toFile().exists()) {
            throw new FileNotFoundException();
        }
        
        TDefinitions jaxbRoot = readXPDLFile(xpdlFile);
        
        try (ITransaction tr = this.session.getTransactionSupport().createTransaction("XPDLImportService")) {
        
            Map<String, Object> elementsMap = new HashMap<>();
        
            // Create on find Modelio Element
            CreateElementProcessorUpdate createProcessor = new CreateElementProcessorUpdate(this.session, this.diagramService, elementsMap, keepId);
            JaxbWalker walker = new JaxbWalker(jaxbRoot, createProcessor);
            walker.walk(context, this.progress);
        
            // Update Modelio Element properties
            UpdateElementProcessorUpdate updateProcessor = new UpdateElementProcessorUpdate(this.session, this.diagramService, elementsMap);
            JaxbWalker updateWalker = new JaxbWalker(jaxbRoot, updateProcessor);
            updateWalker.walk(context, this.progress);
        
            // Show Element in diagram
            FinilizeProcessorUpdate showProcessor = new FinilizeProcessorUpdate(this.session, this.diagramService, elementsMap);
            JaxbWalker showWalker = new JaxbWalker(jaxbRoot, showProcessor);
            showWalker.walk(context, this.progress);
        
            // Delete old elements
            // for (MObject element : collectElementToDelete(context, new
            // ArrayList<>(elementsMap.values()))) {
            // element.delete();
            // }
        
            tr.commit();
        }
    }

    @objid ("f0ef88c3-8076-4967-96c6-51504660392c")
    public void updateBPMN(Path xpdlFile, BpmnCollaboration context, boolean keepId) throws FileNotFoundException, JAXBException {
        if (!xpdlFile.toFile().exists()) {
            throw new FileNotFoundException();
        }
        
        TDefinitions jaxbRoot = readXPDLFile(xpdlFile);
        
        try (ITransaction tr = this.session.getTransactionSupport().createTransaction("XPDLImportService")) {
        
            Map<String, Object> elementsMap = new HashMap<>();
        
            // Create on find Modelio Element
            CreateElementProcessorUpdate createProcessor = new CreateElementProcessorUpdate(this.session, this.diagramService, elementsMap, keepId);
            JaxbWalker walker = new JaxbWalker(jaxbRoot, createProcessor);
            walker.walk(context, this.progress);
        
            // Update Modelio Element properties
            UpdateElementProcessorUpdate updateProcessor = new UpdateElementProcessorUpdate(this.session, this.diagramService, elementsMap);
            JaxbWalker updateWalker = new JaxbWalker(jaxbRoot, updateProcessor);
            updateWalker.walk(context, this.progress);
        
            // Show Element in diagram
            FinilizeProcessorUpdate showProcessor = new FinilizeProcessorUpdate(this.session, this.diagramService, elementsMap);
            JaxbWalker showWalker = new JaxbWalker(jaxbRoot, showProcessor);
            showWalker.walk(context, this.progress);
        
            // Delete old elements
            // for (MObject element : collectElementToDelete(context, new
            // ArrayList<>(elementsMap.values()))) {
            // element.delete();
            // }
        
            tr.commit();
        }
    }

    @objid ("dfe4d7e7-d538-407d-b81b-91ad8521f7f5")
    private List<MObject> collectElementToDelete(MObject element, List<Object> createdElements) {
        List<MObject> toDelete = new ArrayList<>();
        if (!createdElements.contains(element)) {
            if (element instanceof BpmnBaseElement) {
                toDelete.add(element);
            }
        } else {
            for (MObject sub : element.getCompositionChildren()) {
                toDelete.addAll(collectElementToDelete(sub, createdElements));
            }
        }
        return toDelete;
    }

    @objid ("0a24fb62-2b92-4f1e-96ec-022cd9bb7007")
    private TDefinitions readXPDLFile(Path xpdlFile) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("org.modelio.bpmnxml.model");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Source source = new StreamSource(xpdlFile.toFile());
        JAXBElement<TDefinitions> root = unmarshaller.unmarshal(source, TDefinitions.class);
        return root.getValue();
    }

    @objid ("661f400c-1861-44cb-a733-d0b2c364de08")
    public void importBPMN(Path filePath, IProjectFragment context, boolean keepId) throws FileNotFoundException, JAXBException {
        Package packageContext = null;
        
        for (MObject root : context.getRoots()) {
            if (root instanceof Project && ((Project) root).getModel().size() > 0) {
                packageContext = ((Project) root).getModel().get(0);
            }
        }
        
        if (packageContext == null) {
            try (ITransaction tr = this.session.getTransactionSupport().createTransaction("XPDLImportService")) {
                Project project = MTools.get(this.session).getModelFactory(IStandardModelFactory.class).createProject(context.getRepository());
                packageContext = project.getModel().get(0);
                project.setName(filePath.toFile().getName().replace(".bpmn", ""));
                packageContext.setName(filePath.toFile().getName().replace(".bpmn", ""));
                tr.commit();
            }
        }
        
        importBPMN(filePath, packageContext, keepId);
    }

}

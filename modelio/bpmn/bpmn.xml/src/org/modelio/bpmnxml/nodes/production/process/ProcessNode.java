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

package org.modelio.bpmnxml.nodes.production.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TProcessType;
import org.modelio.bpmnxml.model.TRootElement;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcessType;
import org.modelio.metamodel.bpmn.processCollaboration.OptionalBoolean;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e069173f-e4ae-4b04-b26b-2264f9ecd75a")
public class ProcessNode implements IProductionNode<BpmnProcess,TProcess> {
    @objid ("a2ebceca-aba0-47bc-b338-078d744ae973")
    private Map<String, Object> elementsMap;

    @objid ("72bead03-34a3-4217-95b0-4c9d279240b4")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("06f86dfc-aa5b-4fc2-9924-b44963e32f14")
    @Override
    public BpmnProcess findUMLElement(MObject context, TProcess jaxbElement) {
        if (context instanceof BpmnProcess && (context.getName().equals(jaxbElement.getName()) || (jaxbElement.getName() == null && context.getName().equals(jaxbElement.getId())))) {
            return (BpmnProcess) context;
        
        }
        return null;
    }

    @objid ("9bfc708c-66b9-4a04-b2c0-b693437b9b22")
    @Override
    public BpmnProcess createUMLElement(MObject context, TProcess jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnProcess.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnProcess.class, context);
        }
    }

    @objid ("7010c7f5-33a8-476b-8a6b-05f62e3b78a7")
    @Override
    public BpmnProcess updateUMLElement(MObject context, BpmnProcess modelioElement, TProcess jaxbElement) {
        // set owner
        if (context instanceof NameSpace) {
            ((NameSpace) context).getOwnedBehavior().add(modelioElement);
        } else if (context instanceof Operation) {
            ((Operation) context).getOwnedBehavior().add(modelioElement);
        }
        
        // set properties
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        } else if (!modelioElement.getParticipant().isEmpty()) {
            modelioElement.setName(modelioElement.getParticipant().get(0).getName());
        } else if (getFirstBpmnCaller(modelioElement) != null) {
            modelioElement.setName(getFirstBpmnCaller(modelioElement).getName());
        } else {
            modelioElement.setName("Process");
        }
        
        if (jaxbElement.getProcessType() != null) {
            if (jaxbElement.getProcessType() == TProcessType.NONE) {
                modelioElement.setProcessType(BpmnProcessType.NONEPROCESS);
            } else if (jaxbElement.getProcessType() == TProcessType.PRIVATE) {
                modelioElement.setProcessType(BpmnProcessType.PRIVATEPROCESS);
            } else if (jaxbElement.getProcessType() == TProcessType.PUBLIC) {
                modelioElement.setProcessType(BpmnProcessType.PUBLICPROCESS);
            }
        }
        
        modelioElement.setIsClosed(jaxbElement.isIsClosed());
        
        if (jaxbElement.isIsExecutable() != null) {
            if (jaxbElement.isIsExecutable()) {
                modelioElement.setIsExecutable(OptionalBoolean.OTRUE);
            } else {
                modelioElement.setIsExecutable(OptionalBoolean.OFALSE);
            }
        } else {
            modelioElement.setIsExecutable(OptionalBoolean.OUNDEFINED);
        }
        return modelioElement;
    }

    @objid ("a3df07c8-bdd4-4b1a-9201-c11e76a51fc9")
    @Override
    public TProcess createJaxbElement(Object context, BpmnProcess modelioElement) {
        // Create JaxbElement
        TProcess process = new TProcess();
        
        // Add to context
        TDefinitions jaxDefinition = (TDefinitions) context;
        List<JAXBElement<? extends TRootElement>> jaxContent = jaxDefinition.getRootElement();
        if (jaxContent == null) {
            jaxContent = new ArrayList<>();
        }
        ObjectFactory factory = new ObjectFactory();
        jaxContent.add(factory.createProcess(process));
        
        process.setId(IDUtils.formatJaxbID(modelioElement));
        return process;
    }

    @objid ("7f4477ad-5079-4403-852b-5417ca2d89f5")
    @Override
    public TProcess updateJaxbElement(Object context, TProcess process, BpmnProcess modelioElement) {
        process.setName(modelioElement.getName());
        
        if (modelioElement.getProcessType() == BpmnProcessType.PRIVATEPROCESS) {
            process.setProcessType(TProcessType.PRIVATE);
        } else if (modelioElement.getProcessType() == BpmnProcessType.PUBLICPROCESS) {
            process.setProcessType(TProcessType.PUBLIC);
        } else {
            process.setProcessType(TProcessType.NONE);
        }
        
        process.setIsClosed(modelioElement.isIsClosed());
        
        if (modelioElement.getIsExecutable() != null) {
            if (modelioElement.getIsExecutable() == OptionalBoolean.OTRUE) {
                process.setIsExecutable(true);
            } else if (modelioElement.getIsExecutable() == OptionalBoolean.OFALSE) {
                process.setIsExecutable(false);
            }
        }
        return process;
    }

    @objid ("81006d6e-bdeb-46f1-9b27-f7c6e177fe86")
    @Override
    public BpmnProcess findUMLElementById(TProcess jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnProcess.class, jaxbElement.getId());
    }

    @objid ("afaf2f3e-a6bd-40cd-839e-fab29a918b82")
    private BpmnCallActivity getFirstBpmnCaller(BpmnProcess modelioElement) {
        for (MethodologicalLink dep : modelioElement.getImpactedDependency(MethodologicalLink.class)) {
            if (dep.isStereotyped(Called.MdaTypes.STEREOTYPE_ELT)) {
                ModelElement source = dep.getImpacted();
                if (source instanceof BpmnCallActivity) {
                    return (BpmnCallActivity) source;
                }
            }
        }
        return null;
    }

}

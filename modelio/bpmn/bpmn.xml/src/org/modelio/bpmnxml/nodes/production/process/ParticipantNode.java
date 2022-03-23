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
package org.modelio.bpmnxml.nodes.production.process;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.namespace.QName;
import org.modelio.bpmnxml.model.TCollaboration;
import org.modelio.bpmnxml.model.TParticipant;
import org.modelio.bpmnxml.model.TParticipantMultiplicity;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8008ace9-f7b2-4570-b70f-4ca88a115f2e")
public class ParticipantNode implements IProductionNode<BpmnParticipant, TParticipant> {
    @objid ("bd2a909d-0f1a-46d4-858c-9a7d5bd91a09")
    private Map<String, Object> elementsMap;

    @objid ("a0314558-af7e-4dcb-89ce-a534c3284fc3")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("c96cf949-8533-4518-9d76-f92912ab0f10")
    @Override
    public BpmnParticipant findUMLElement(MObject context, TParticipant jaxbElement) {
        if (context instanceof BpmnCollaboration) {
            for (BpmnParticipant participant : ((BpmnCollaboration) context).getParticipants()) {
                if (participant.getName().equals(jaxbElement.getName())) {
                    return participant;
                }
            }
        }
        return null;
    }

    @objid ("9a1c6e8e-45d3-4eee-8fa1-4f9533037dce")
    @Override
    public BpmnParticipant createUMLElement(MObject context, TParticipant jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnParticipant.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnParticipant.class, context);
        }
        
    }

    @objid ("667eb8d3-95e6-426a-8098-062e3995f395")
    @Override
    public BpmnParticipant updateUMLElement(MObject context, BpmnParticipant modelioElement, TParticipant jaxbElement) {
        // set owner
        
        modelioElement.setContainer((BpmnCollaboration) context);
        
        // set properties
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        }
        
        if (jaxbElement.getProcessRef() != null) {
            Object ref = this.elementsMap.get(jaxbElement.getProcessRef().getLocalPart());
            // Fine process in current project
        
            if (ref == null) {
                ref = CoreSession.getSession(modelioElement).getModel().findById(BpmnProcess.class, IDUtils.formatModelioId(jaxbElement.getProcessRef().getLocalPart()));
            }
        
            if (ref instanceof BpmnProcess) {
                modelioElement.setProcess((BpmnProcess) ref);
            }
        }
        
        if (jaxbElement.getParticipantMultiplicity() != null) {
            modelioElement.setMultiplicityMin(jaxbElement.getParticipantMultiplicity().getMinimum());
            modelioElement.setMultiplicityMax(jaxbElement.getParticipantMultiplicity().getMaximum());
        }
        return modelioElement;
    }

    @objid ("98256adc-9faa-418f-8e45-1e48ad644a9e")
    @Override
    public TParticipant createJaxbElement(Object context, BpmnParticipant modelioElement) {
        // Create JaxbElement
        TParticipant participant = new TParticipant();
        
        TCollaboration jaxCollab = (TCollaboration) context;
        jaxCollab.getParticipant().add(participant);
        
        if (modelioElement.getMultiplicityMin() != 0 || modelioElement.getMultiplicityMax() != 0) {
            TParticipantMultiplicity jaxMult = new TParticipantMultiplicity();
            jaxMult.setMaximum(modelioElement.getMultiplicityMax());
            jaxMult.setMinimum(modelioElement.getMultiplicityMin());
            participant.setParticipantMultiplicity(jaxMult);
        }
        
        participant.setId(IDUtils.formatJaxbID(modelioElement));
        return participant;
    }

    @objid ("f644a1c1-9126-403c-b19b-56c0dbcb3266")
    @Override
    public TParticipant updateJaxbElement(Object context, TParticipant participant, BpmnParticipant modelioElement) {
        participant.setName(modelioElement.getName());
        if (modelioElement.getProcess() != null) {
            participant.setProcessRef(new QName(IDUtils.formatJaxbID(modelioElement.getProcess())));
        }
        return participant;
    }

    @objid ("ba668c37-7fd2-41ce-bff2-7390deb6a07f")
    @Override
    public BpmnParticipant findUMLElementById(TParticipant jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnParticipant.class, jaxbElement.getId());
    }

}

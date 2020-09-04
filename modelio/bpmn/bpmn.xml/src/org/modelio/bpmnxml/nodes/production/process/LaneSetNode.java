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

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.TLane;
import org.modelio.bpmnxml.model.TLaneSet;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("11128e24-b2c6-4f84-a8ff-9c439001e133")
public class LaneSetNode implements IProductionNode<BpmnLaneSet,TLaneSet> {
    @objid ("bccb7395-a61c-4c21-be59-15ac79844684")
    private Map<String, Object> elementsMap;

    @objid ("a5546a2d-2628-4f88-8e63-c62d2eb4f9b0")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("2be5c93b-ae85-4c68-920f-e1c4a96d44c8")
    @Override
    public BpmnLaneSet findUMLElement(MObject context, TLaneSet jaxbElement) {
        if (context instanceof BpmnProcess) {
            return ((BpmnProcess) context).getLaneSet();
        } else if (context instanceof BpmnSubProcess) {
             return ((BpmnSubProcess) context).getLaneSet();     
        } else if (context instanceof BpmnLane) {
            BpmnLaneSet modelioLanSet = ((BpmnLane) context).getLaneSet();
            if (modelioLanSet != null)
                return modelioLanSet;
        }
        return null;
    }

    @objid ("d1e398e3-cc9f-4665-99e7-01ba7ce00c26")
    @Override
    public BpmnLaneSet createUMLElement(MObject context, TLaneSet jaxbElement, BpmnImportFactory factory, boolean keepId) {
        BpmnLaneSet modelioElement = null;
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            modelioElement = factory.createWithId(BpmnLaneSet.class, context,jaxbElement.getId());
        } else {
            modelioElement = factory.create(BpmnLaneSet.class, context);
        }
        
        // set owner
        if (context instanceof BpmnProcess) {
            modelioElement.setProcess((BpmnProcess) context);
        } else if (context instanceof BpmnSubProcess) {
            modelioElement.setSubProcess((BpmnSubProcess) context);
        } else if (context instanceof BpmnLane) {
            modelioElement.setParentLane((BpmnLane) context);
        }
        
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        return modelioElement;
    }

    @objid ("5b52041b-43ef-4b1b-884c-e70b7a1d6cbe")
    @Override
    public BpmnLaneSet updateUMLElement(MObject context, BpmnLaneSet modelioElement, TLaneSet jaxbElement) {
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        return modelioElement;
    }

    @objid ("101cca33-9505-464f-bcef-988f191be85d")
    @Override
    public TLaneSet createJaxbElement(Object context, BpmnLaneSet modelioElement) {
        // Create JaxbElement
        TLaneSet jaxElement = new TLaneSet();
        
        // Add to context
        if (context instanceof TProcess) {
            TProcess jaxProcess = (TProcess) context;
            jaxProcess.getLaneSet().add(jaxElement);
        } else if (context instanceof TSubProcess) {
            TSubProcess jaxProcess = (TSubProcess) context;
            jaxProcess.getLaneSet().add(jaxElement);
        } else if (context instanceof TLane) {
            TLane jaxLan = (TLane) context;
            jaxLan.setChildLaneSet(jaxElement);
        }
        
        jaxElement.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxElement;
    }

    @objid ("0715ee0e-d2db-4955-a4ef-383bfc79ee2f")
    @Override
    public TLaneSet updateJaxbElement(Object context, TLaneSet jaxbElement, BpmnLaneSet modelioElement) {
        if (!"".equals(modelioElement.getName())) {
            jaxbElement.setName(modelioElement.getName());
        }
        return jaxbElement;
    }

    @objid ("e11eb4e5-eaec-4830-a3d1-3ce6a3fd3a39")
    @Override
    public BpmnLaneSet findUMLElementById(TLaneSet jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnLaneSet.class, jaxbElement.getId());
    }

}

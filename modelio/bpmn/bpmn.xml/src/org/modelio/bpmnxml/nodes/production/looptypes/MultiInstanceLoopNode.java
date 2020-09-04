/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.bpmnxml.nodes.production.looptypes;

import java.io.Serializable;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TActivity;
import org.modelio.bpmnxml.model.TExpression;
import org.modelio.bpmnxml.model.TMultiInstanceLoopCharacteristics;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d27d2639-2f15-4758-99e1-575ecb9d3236")
public class MultiInstanceLoopNode implements IProductionNode<BpmnMultiInstanceLoopCharacteristics,TMultiInstanceLoopCharacteristics> {
    @objid ("85872c0a-bad1-4124-9db9-f9288f7670ff")
    private Map<String, Object> elementsMap;

    @objid ("6550699c-3f65-4e41-b42f-b715b69a0c4a")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("24724595-070f-4e73-9b33-596204c3da5d")
    @Override
    public BpmnMultiInstanceLoopCharacteristics findUMLElement(MObject context, TMultiInstanceLoopCharacteristics jaxbElement) {
        if (context instanceof BpmnActivity) {
            if (((BpmnActivity) context).getLoopCharacteristics() != null && ((BpmnActivity) context).getLoopCharacteristics() instanceof BpmnMultiInstanceLoopCharacteristics) {
                return (BpmnMultiInstanceLoopCharacteristics) ((BpmnActivity) context).getLoopCharacteristics();
            }
        }
        return null;
    }

    @objid ("8b4d73de-a2be-4d6a-80bf-7f84bcc6d731")
    @Override
    public BpmnMultiInstanceLoopCharacteristics createUMLElement(MObject context, TMultiInstanceLoopCharacteristics jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnMultiInstanceLoopCharacteristics.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnMultiInstanceLoopCharacteristics.class, context);
        }
    }

    @objid ("ce9ba144-7c65-4360-a2ed-aae794fc538b")
    @Override
    public BpmnMultiInstanceLoopCharacteristics updateUMLElement(MObject context, BpmnMultiInstanceLoopCharacteristics modelioElement, TMultiInstanceLoopCharacteristics jaxbElement) {
        if (context instanceof BpmnActivity) {
            modelioElement.setOwnerActivity((BpmnActivity)context);
        }
        
        if (jaxbElement.getCompletionCondition() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getCompletionCondition().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setCompletionCondition(condition);
        }
        
        if (jaxbElement.getLoopCardinality() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getLoopCardinality().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setLoopCardinality(condition);
        }
        
        modelioElement.setIsSequencial(jaxbElement.isIsSequential());
        
        // TODO : Link with BpmnEventDefinition
        return modelioElement;
    }

    @objid ("8b9375af-ddf8-4f5b-b271-9c5b931db288")
    @Override
    public TMultiInstanceLoopCharacteristics createJaxbElement(Object context, BpmnMultiInstanceLoopCharacteristics modelioElement) {
        TActivity jaxActivity = (TActivity) context;
        
        // Create JaxbElement
        TMultiInstanceLoopCharacteristics jaxLoop = new TMultiInstanceLoopCharacteristics();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        jaxActivity.setLoopCharacteristics(factory.createMultiInstanceLoopCharacteristics(jaxLoop));
        
        jaxLoop.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxLoop;
    }

    @objid ("96a17c9e-384f-4135-856c-950602bddf63")
    @Override
    public TMultiInstanceLoopCharacteristics updateJaxbElement(Object context, TMultiInstanceLoopCharacteristics jaxLoop, BpmnMultiInstanceLoopCharacteristics modelioElement) {
        jaxLoop.setIsSequential(modelioElement.isIsSequencial());
        
        if (!"".equals(modelioElement.getCompletionCondition())) {
            TExpression exp = new TExpression();
            exp.getContent().add(modelioElement.getCompletionCondition());
            jaxLoop.setCompletionCondition(exp);
        }
        
        if (!"".equals(modelioElement.getLoopCardinality())) {
            TExpression exp = new TExpression();
            exp.getContent().add(modelioElement.getLoopCardinality());
            jaxLoop.setLoopCardinality(exp);
        }
        return jaxLoop;
    }

    @objid ("9297f7e4-d39e-4206-85ce-c6317e126523")
    @Override
    public BpmnMultiInstanceLoopCharacteristics findUMLElementById(TMultiInstanceLoopCharacteristics jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnMultiInstanceLoopCharacteristics.class, jaxbElement.getId());
    }

}

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

package org.modelio.bpmnxml.nodes.production.looptypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TActivity;
import org.modelio.bpmnxml.model.TExpression;
import org.modelio.bpmnxml.model.TStandardLoopCharacteristics;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("2ea8ae25-30db-4c46-9ec5-d1b27e54d212")
public class StandardLoopNode implements IProductionNode<BpmnStandardLoopCharacteristics,TStandardLoopCharacteristics> {
    @objid ("26708517-24c0-4a46-a40c-12cc7fb0da9b")
    private Map<String, Object> elementsMap;

    @objid ("fedb0a9d-f290-4616-a408-f58f1c94ffaa")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("bb24d6c3-986d-49a5-b573-eda10d0de97a")
    @Override
    public BpmnStandardLoopCharacteristics findUMLElement(MObject context, TStandardLoopCharacteristics jaxbElement) {
        if (context instanceof BpmnActivity) {
            if (((BpmnActivity) context).getLoopCharacteristics() != null && ((BpmnActivity) context).getLoopCharacteristics() instanceof BpmnStandardLoopCharacteristics) {
                return (BpmnStandardLoopCharacteristics) ((BpmnActivity) context).getLoopCharacteristics();
            }
        }
        return null;
    }

    @objid ("c908f326-a691-4295-ab40-48bfcfe345d1")
    @Override
    public BpmnStandardLoopCharacteristics createUMLElement(MObject context, TStandardLoopCharacteristics jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnStandardLoopCharacteristics.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnStandardLoopCharacteristics.class, context);
        }
    }

    @objid ("7fc306da-8e01-48c5-8bda-40b715812c27")
    @Override
    public BpmnStandardLoopCharacteristics updateUMLElement(MObject context, BpmnStandardLoopCharacteristics modelioElement, TStandardLoopCharacteristics jaxbElement) {
        if (context instanceof BpmnActivity) {
            modelioElement.setOwnerActivity((BpmnActivity)context);
        }
        
        if (jaxbElement.getLoopCondition() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getLoopCondition().getContent()) {
                condition = condition + val.toString();
                if (!val.equals(jaxbElement.getLoopCondition().getContent().size() - 1)) {
                    condition = condition + ";";
                }
            }
            modelioElement.setLoopCondition(condition);
        }
        
        if (jaxbElement.getLoopMaximum() != null)
            modelioElement.setLoopMaximum(jaxbElement.getLoopMaximum().toString());
        modelioElement.setTestBefore(jaxbElement.isTestBefore());
        return modelioElement;
    }

    @objid ("f5bd3e33-1086-405a-a7cb-9aa023f40e23")
    @Override
    public TStandardLoopCharacteristics createJaxbElement(Object context, BpmnStandardLoopCharacteristics modelioElement) {
        TActivity jaxActivity = (TActivity) context;
        
        // Create JaxbElement
        TStandardLoopCharacteristics jaxLoop = new TStandardLoopCharacteristics();
        
        // // Add to context
        ObjectFactory factory = new ObjectFactory();
        jaxActivity.setLoopCharacteristics(factory.createStandardLoopCharacteristics(jaxLoop));
        //
        // // Edit Properties
        jaxLoop.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxLoop;
    }

    @objid ("b8f00cab-33d7-40a1-a485-dadc11d7730b")
    @Override
    public TStandardLoopCharacteristics updateJaxbElement(Object context, TStandardLoopCharacteristics jaxLoop, BpmnStandardLoopCharacteristics modelioElement) {
        jaxLoop.setTestBefore(modelioElement.isTestBefore());
        
        if (!"".equals(modelioElement.getLoopCondition())) {
            if (modelioElement.getLoopCondition().contains(";")) {
                String[] vals = modelioElement.getLoopCondition().split(";");
                for (String val : vals) {
                    TExpression exp = new TExpression();
                    exp.getContent().add(val);
                    jaxLoop.setLoopCondition(exp);
                }
            } else {
                TExpression exp = new TExpression();
                exp.getContent().add(modelioElement.getLoopCondition());
                jaxLoop.setLoopCondition(exp);
            }
        
        }
        
        try {
            jaxLoop.setLoopMaximum(new BigInteger(modelioElement.getLoopMaximum()));
        } catch (NumberFormatException e) {
        
        }
        return jaxLoop;
    }

    @objid ("ae6918b4-ae10-4b42-9676-55a93cede08a")
    @Override
    public BpmnStandardLoopCharacteristics findUMLElementById(TStandardLoopCharacteristics jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnStandardLoopCharacteristics.class, jaxbElement.getId());
    }

}

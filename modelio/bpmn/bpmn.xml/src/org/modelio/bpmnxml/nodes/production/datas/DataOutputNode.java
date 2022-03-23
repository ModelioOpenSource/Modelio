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
package org.modelio.bpmnxml.nodes.production.datas;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.TActivity;
import org.modelio.bpmnxml.model.TDataOutput;
import org.modelio.bpmnxml.model.TInputOutputSpecification;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("fc361464-3075-4667-b77f-9ac54e310d3c")
public class DataOutputNode implements IProductionNode<BpmnDataOutput, TDataOutput> {
    @objid ("92676958-b921-4147-8caf-7a4cdbbccd95")
    private Map<String, Object> elementsMap;

    @objid ("9fc1d21e-d156-45eb-a03e-67fd771e754a")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("45de4ce2-2f51-449c-8a85-4eb827a0594c")
    @Override
    public BpmnDataOutput findUMLElement(MObject context, TDataOutput jaxbElement) {
        if(context instanceof BpmnActivity){
            for(BpmnDataOutput dataOutput :((BpmnActivity) context).getOutputSpecification()){
                if(dataOutput.getName().equals(jaxbElement.getName())){
                    return dataOutput;
                }
            }
        }
        return null;
    }

    @objid ("b2aa2b5b-d0fb-421c-bc0c-7985a8cc40fe")
    @Override
    public BpmnDataOutput createUMLElement(MObject context, TDataOutput jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnDataOutput.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnDataOutput.class,context);
        }
        
    }

    @objid ("9c4b7a0f-7bf5-472f-bda6-ed3c97b3707e")
    @Override
    public BpmnDataOutput updateUMLElement(MObject context, BpmnDataOutput modelioElement, TDataOutput jaxbElement) {
        // Set owner
        if(context instanceof BpmnActivity){
            ((BpmnActivity) context).getOutputSpecification().add(modelioElement);
        }else if(context instanceof BpmnCatchEvent){
            ((BpmnCatchEvent) context).setDataOutput(modelioElement);
        }
        
        // Set properties
        if(jaxbElement.getName()!=null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        modelioElement.setIsCollection(jaxbElement.isIsCollection());
        return modelioElement;
    }

    @objid ("beb3048b-78f8-446c-a0b7-a36c4489448d")
    @Override
    public TDataOutput createJaxbElement(Object context, BpmnDataOutput modelioElement) {
        // Create JaxbElement
        // Add to context
        if(context instanceof TActivity){
            TDataOutput jaxTask = new TDataOutput();
            TInputOutputSpecification jaxContent = ((TActivity)context).getIoSpecification();
            jaxContent.getDataOutput().add(jaxTask);
            jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
            return jaxTask;
        }
        return null;
    }

    @objid ("e0b7d6f4-04d4-4ea1-b332-8129f774779a")
    @Override
    public TDataOutput updateJaxbElement(Object context, TDataOutput jaxTask, BpmnDataOutput modelioElement) {
        jaxTask.setName(modelioElement.getName());
        jaxTask.setIsCollection(modelioElement.isIsCollection());
        return jaxTask;
    }

    @objid ("3d1f3d62-db18-4cd4-bc53-e773aba11c3b")
    @Override
    public BpmnDataOutput findUMLElementById(TDataOutput jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnDataOutput.class, jaxbElement.getId());
    }

}

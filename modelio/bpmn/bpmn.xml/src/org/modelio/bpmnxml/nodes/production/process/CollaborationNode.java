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
import org.modelio.bpmnxml.model.TCollaboration;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.bpmnxml.model.TParticipant;
import org.modelio.bpmnxml.model.TRootElement;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("063470ec-7cab-426d-adec-68d9f710ac42")
public class CollaborationNode implements IProductionNode<BpmnCollaboration,TCollaboration> {
    @objid ("e0cc8ad9-7bc7-4a8c-b1df-ea5bcdff4687")
    private Map<String, Object> elementsMap;

    @objid ("1f1a53b0-4415-434f-a4a0-0814d21442ec")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("bdd42364-4ac1-4681-9517-e9c618a69dbf")
    @Override
    public BpmnCollaboration findUMLElement(MObject context, TCollaboration jaxbElement) {
        if (context instanceof BpmnCollaboration) {
            return (BpmnCollaboration) context;
        }
        return null;
    }

    @objid ("8d51a4e5-423d-4dc9-88a7-a14de8eb0cf9")
    @Override
    public BpmnCollaboration createUMLElement(MObject context, TCollaboration jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnCollaboration.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnCollaboration.class, context);
        }
    }

    @objid ("ef59f647-3523-4719-b215-213318cd0be3")
    @Override
    public BpmnCollaboration updateUMLElement(MObject context, BpmnCollaboration modelioElement, TCollaboration jaxbElement) {
        // set owner
        if (context instanceof NameSpace) {
            ((NameSpace) context).getOwnedBehavior().add(modelioElement);
        }else if(context instanceof Operation){
            ((Operation) context).getOwnedBehavior().add(modelioElement);
        }else if(context instanceof BpmnProcess){
            ((BpmnProcess) context).setDefinitionalCollaboration(modelioElement);
        }
        // set properties
               if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        }else{
            String partname = "";
            for(TParticipant part : jaxbElement.getParticipant()){
                partname = partname + StringConvertor.imports(part.getName());
                if(!part.equals(jaxbElement.getParticipant().get(jaxbElement.getParticipant().size()-1))){
                    partname = partname + " - ";
                }
            }
            modelioElement.setName(partname);
        }
        
        modelioElement.setIsClosed(jaxbElement.isIsClosed());
        return modelioElement;
    }

    @objid ("b5a6281d-24a0-4de8-b4d6-e2dd007c5797")
    @Override
    public TCollaboration createJaxbElement(Object context, BpmnCollaboration modelioElement) {
        // Create JaxbElement
        TCollaboration process = new TCollaboration();
        
        // Add to context
        TDefinitions jaxDefinition = (TDefinitions) context;
        List<JAXBElement<? extends TRootElement>> jaxContent = jaxDefinition.getRootElement();
        if (jaxContent == null) {
            jaxContent = new ArrayList<>();
        }
        ObjectFactory factory = new ObjectFactory();
        jaxContent.add(factory.createCollaboration(process));
        
        process.setId(IDUtils.formatJaxbID(modelioElement));
        return process;
    }

    @objid ("8b36f068-05a8-44f0-814d-04b1daaa1410")
    @Override
    public TCollaboration updateJaxbElement(Object context, TCollaboration collaboration, BpmnCollaboration modelioElement) {
        collaboration.setName(modelioElement.getName());
        
        collaboration.setIsClosed(modelioElement.isIsClosed());
        return collaboration;
    }

    @objid ("d6a85b7d-fb12-4ef1-a70b-ef45838adf84")
    @Override
    public BpmnCollaboration findUMLElementById(TCollaboration jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnCollaboration.class, jaxbElement.getId());
    }

}

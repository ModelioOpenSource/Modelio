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
import org.modelio.bpmnxml.model.TTextAnnotation;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("eb903429-b9f2-466c-a7b9-25268243ed7f")
public class AnnotationNode implements IProductionNode<Note,TTextAnnotation> {
    @objid ("aa41fc5f-5d9a-4828-9e7a-cc6239ffb2f8")
    private Map<String, Object> elementsMap;

    @objid ("f3ed753a-e70f-44a4-8b10-7a69a28c14b1")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("5f843b0b-d0a6-46dc-8684-f33cc970b98b")
    @Override
    public Note findUMLElement(MObject context, TTextAnnotation jaxbElement) {
        return null;
    }

    @objid ("ffcd45e9-83f7-40b7-8f21-de10e79c4cc3")
    @Override
    public Note createUMLElement(MObject context, TTextAnnotation jaxbElement, BpmnImportFactory factory, boolean keepId) {
        String text = "";
        if (jaxbElement.getText() != null) {
            text = jaxbElement.getText().getContent().toString();
        }
        
        Note modelioElement = null;
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            modelioElement = factory.createWithId(Note.class, context, jaxbElement.getId());
        } else {
            modelioElement = factory.create(Note.class, context);
        }
        
        for (NoteType type : new MModelServices(CoreSession.getSession(context)).findNoteTypes("ModelerModule", ModelElement.MQNAME, "description", context.getMClass())) {
            modelioElement.setModel(type);
            break;
        }
        
        modelioElement.setContent(text);
        return modelioElement;
    }

    @objid ("ab25e12d-b853-48d4-adc1-75036fbde9ac")
    @Override
    public Note updateUMLElement(MObject context, Note modelioElement, TTextAnnotation jaxbElement) {
        // NA
        return modelioElement;
    }

    @objid ("3d0dba34-75f4-47da-aa00-56a2957d2a06")
    @Override
    public TTextAnnotation createJaxbElement(Object context, Note modelioElement) {
        // // Create JaxbElement
        // TTextAnnotation jaxAnnotation = new TTextAnnotation();
        //
        // // Add to context
        // ObjectFactory factory = new ObjectFactory();
        // if (context instanceof TProcess) {
        // context.get
        // List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess)
        // context).getFlowElement();
        // jaxContent.add(factory.createTextAnnotation(jaxAnnotation));
        // } else if (context instanceof TSubProcess) {
        // List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess)
        // context).getFlowElement();
        // jaxContent.add(factory.createTextAnnotation(jaxAnnotation));
        // }
        //
        // jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return null;
    }

    @objid ("d5a7b9d6-ce10-42ff-b672-f04b0def5944")
    @Override
    public TTextAnnotation updateJaxbElement(Object context, TTextAnnotation jaxTask, Note modelioElement) {
        // jaxTask.setName(modelioElement.getName());
        return jaxTask;
    }

    @objid ("b3b10034-ec8a-4f99-a230-c2521cd3cb16")
    @Override
    public Note findUMLElementById(TTextAnnotation jaxbElement, ICoreSession session) {
        return session.getModel().findById(Note.class, jaxbElement.getId());
    }

}

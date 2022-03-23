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
import org.modelio.bpmnxml.model.TAssociation;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("3aa46027-bfd2-4091-a249-7422d3419fca")
public class AssociationNode implements IProductionNode<Note, TAssociation> {
    @objid ("41f20879-8c8c-44e4-802d-35da4c5163c2")
    private Map<String, Object> elementsMap;

    @objid ("fc2d2a35-e563-4804-b13c-e1dd0678987c")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("6732c7f3-935f-4d3d-a59b-df94ed1a333a")
    @Override
    public Note findUMLElement(MObject context, TAssociation jaxbElement) {
        return null;
    }

    @objid ("812d3086-ecdd-46a0-bf13-add0eafaeda9")
    @Override
    public Note createUMLElement(MObject context, TAssociation jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(Note.class, context, jaxbElement.getId());
        } else {
            return factory.create(Note.class, context);
        }
        
    }

    @objid ("d5a8f3c3-f177-4ac0-a90a-5bc07b3e3bd7")
    @Override
    public Note updateUMLElement(MObject context, Note modelioElement, TAssociation jaxbElement) {
        ModelElement source = (ModelElement) this.elementsMap.get(jaxbElement.getSourceRef().getLocalPart());
        MObject target = (MObject) this.elementsMap.get(jaxbElement.getTargetRef().getLocalPart());
        if (!(target instanceof Note)) {
            target = (MObject) this.elementsMap.get(jaxbElement.getSourceRef().getLocalPart());
            source = (ModelElement) this.elementsMap.get(jaxbElement.getTargetRef().getLocalPart());
        }
        if (source != null && target instanceof Note) {
            ((Note) target).setSubject(source);
            for (NoteType type : new MModelServices(CoreSession.getSession(context)).findNoteTypes("ModelerModule", ModelElement.MQNAME, "description", context.getMClass())) {
                ((Note) target).setModel(type);
                break;
            }
        }
        
        modelioElement.delete();
        this.elementsMap.remove(jaxbElement.getId());
        return null;
    }

    @objid ("5c24600e-57f7-413c-885d-decaef8e20ef")
    @Override
    public TAssociation createJaxbElement(Object context, Note modelioElement) {
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

    @objid ("2afa7ff1-1fad-43ce-9e9d-fa2b96a94c4a")
    @Override
    public TAssociation updateJaxbElement(Object context, TAssociation jaxTask, Note modelioElement) {
        // jaxTask.setName(modelioElement.getName());
        return jaxTask;
    }

    @objid ("3073b83b-fc19-4221-a5e0-aac9b4f33ea9")
    @Override
    public Note findUMLElementById(TAssociation jaxbElement, ICoreSession session) {
        return session.getModel().findById(Note.class, jaxbElement.getId());
    }

}

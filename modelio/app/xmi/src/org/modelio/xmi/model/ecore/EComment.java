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

package org.modelio.xmi.model.ecore;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("2c12769b-4ec8-446b-b19b-03672ef9aafe")
public class EComment extends EElement {
    @objid ("b41467a5-743c-45b5-8909-d4ac3d31830e")
    private org.eclipse.uml2.uml.Comment ecoreElement = null;

    @objid ("28210844-514d-4119-a67e-f12e969ac539")
    @Override
    public ArrayList<Note> createObjingElt() {
        return new ArrayList<>();
    }

    @objid ("d1632c07-be4d-4d08-bb18-bec150822ccb")
    private Note createNote() {
        IMModelServices mmService = ReverseProperties.getInstance().getMModelServices();
        
        Note result = mmService.getModelFactory().getFactory(IStandardModelFactory.class).createNote();
        
        List<NoteType> noteTypes = mmService.findNoteTypes("ModelerModule", ModelElement.MQNAME, "comment", result.getMClass().getMetamodel().getMClass(ModelElement.class));
        if (!noteTypes.isEmpty()) {
            result.setModel(noteTypes.get(0));
        }
        return result;
    }

    @objid ("214027c4-7a8f-43d6-be90-8696a1c94004")
    public EComment(org.eclipse.uml2.uml.Comment element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("7fbf2ca9-cd75-43c8-b5ba-2d4bf0ccb103")
    @Override
    public void attach(List<Object> objingElts) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        List<org.eclipse.uml2.uml.Element> annotatedElementList = this.ecoreElement.getAnnotatedElements();
        if (annotatedElementList == null || annotatedElementList.size() == 0) {
            annotatedElementList = new ArrayList<>();
            annotatedElementList.add(this.ecoreElement.getOwner());
        }
        
        for (Object annotatedElement : annotatedElementList) {
            org.eclipse.uml2.uml.Element ecoreAnnotatedElt = (org.eclipse.uml2.uml.Element) annotatedElement;
            Object objingAnnotatedElt = revProp.getMappedElement(ecoreAnnotatedElt);
        
            if ((objingAnnotatedElt != null) && (objingAnnotatedElt instanceof Element)) {
                if (ecoreAnnotatedElt instanceof org.eclipse.uml2.uml.AssociationClass) {
                    if (ObjingEAnnotation.isOwnedByAssociation(this.ecoreElement)) {
                        createNote(((Class) objingAnnotatedElt).getLinkToAssociation().getAssociationPart(), objingElts);
                    } else if (ObjingEAnnotation.isOwnedByAssociationClass(this.ecoreElement)) {
                        createNote(((Class) objingAnnotatedElt).getLinkToAssociation(), objingElts);
                    } else {
                        createNote(objingAnnotatedElt, objingElts);
                    }
                } else {
                    createNote(objingAnnotatedElt, objingElts);
                }
            } else if (objingAnnotatedElt instanceof ArrayList) {
                for (Object objingAnnotatedElt2 : (ArrayList<? extends Object>) objingAnnotatedElt) {
                    if (objingAnnotatedElt2 instanceof ModelElement) {
                        createNote(objingAnnotatedElt2, objingElts);
                    }
                }
            }
        }
    }

    @objid ("3916ec60-6288-497d-a890-23390b7ed96e")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        if ((objingElt != null) && (objingElt instanceof Note)) {
            setNote((Note) objingElt);
        }
    }

    @objid ("ad241920-7c0e-4196-b7e1-ca476b99b15c")
    public void createNote(Object objingAnnotatedElt, List<Object> objingElt) {
        if ((objingAnnotatedElt != null)
                && (objingAnnotatedElt instanceof ModelElement)
                && ((ModelElement) objingAnnotatedElt).getStatus().isModifiable()) {
        
            Note objingNote = createNote();
            objingNote.setSubject((ModelElement) objingAnnotatedElt);
            ((ModelElement) objingAnnotatedElt).getDescriptor().add(objingNote);
            objingElt.add(objingNote);
        }
    }

    @objid ("ec20e832-599f-41d3-aaa8-1275ac89ca0d")
    private void setBody(Note objingElt) {
        String body = this.ecoreElement.getBody();
        if (body != null) {
            objingElt.setContent(body);
        }
    }

    @objid ("f31a49d3-e814-4a9c-b3da-c19289b22616")
    private void setModel(Note objingElt) {
        String noteTypeName = ObjingEAnnotation.getNoteTypeName(this.ecoreElement);
        
        if (!noteTypeName.equals("")) {
            List<NoteType> objingNoteType = ReverseProperties.getInstance().getMModelServices().findNoteTypes(".*", ".*", noteTypeName, objingElt.getSubject().getMClass());
        
            if ((objingNoteType != null) && (objingNoteType.size() > 0)) {
                objingElt.setModel(objingNoteType.get(0));
            }
        }
    }

    @objid ("d585e1b7-bf53-449b-ab8e-e9876f4ed316")
    private void setNote(Note note) {
        setBody(note);
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()) {
            setModel(note);
        }
    }

}

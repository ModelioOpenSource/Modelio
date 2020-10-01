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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.SysMLProfileUtils;

/**
 * This class handles the export of Note elements
 * @author ebrosse
 */
@objid ("095ecaf0-91db-40f8-9f35-5174e7a62172")
public class ONote extends OElement implements IOElement {
    @objid ("f09b84c7-a880-476a-b417-93a2e84a1d06")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        ModelElement subject = getObjingElement().getSubject();
        org.eclipse.uml2.uml.Element annotatedElement =GenerationProperties.getInstance()
                .getMappedElement(subject);
        
        if (getObjingElement().getModel().getName().equals("TimedObservation") && (annotatedElement instanceof org.eclipse.uml2.uml.NamedElement)){
            return UMLFactory.eINSTANCE.createTimeObservation();
        
        }else{
            org.eclipse.uml2.uml.Comment ecoreComment = UMLFactory.eINSTANCE.createComment();
            if ((getObjingElement().getModel() != null )
                    && (((getObjingElement().getModel().getOwnerStereotype() != null ) && (SysMLProfileUtils.isSysML(getObjingElement().getModel().getOwnerStereotype().getOwner()))
                            || ((getObjingElement().getModel().getOwnerReference() != null ) && (SysMLProfileUtils.isSysML(getObjingElement().getModel().getOwnerReference().getOwnerProfile()))))))
                GenerationProperties.getInstance().addSysMLExported(getObjingElement());
        
            return ecoreComment;
        
        }
    }

    /**
     * @param element : the exported Note
     */
    @objid ("9dea1ffc-5d4c-4c5f-ad14-780aab9db96f")
    public ONote(final Note element) {
        super(element);
    }

    @objid ("4be1d339-b2ea-4ae4-a495-7e75c5d46235")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        ModelElement subjectModelElement = getObjingElement().getSubject();
        
        if (subjectModelElement != null) {
            // Gets or creates the ecore element that owns the Note:
            org.eclipse.uml2.uml.Element annotatedElement = genProp.getMappedElement(subjectModelElement);
        
            if (annotatedElement != null) {
        
                if (ecoreElt instanceof  org.eclipse.uml2.uml.Comment){
                    annotatedElement.getOwnedComments().add( (org.eclipse.uml2.uml.Comment)ecoreElt);
                }else{
                    org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(AbstractObjingModelNavigation.getNearestPackage(subjectModelElement));
                    if (ecoreOwner instanceof Package){
                        org.eclipse.uml2.uml.TimeObservation timeObs = (org.eclipse.uml2.uml.TimeObservation) ecoreElt;
                        ((org.eclipse.uml2.uml.Package) ecoreOwner).getPackagedElements().add(timeObs);
                        timeObs.setEvent((org.eclipse.uml2.uml.NamedElement) annotatedElement );
        
                    }else{
                        ecoreElt.destroy();
                    }
                }
            }
        }
    }

    @objid ("7a40312c-7075-4cd4-a011-3fa5c2712165")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt instanceof  org.eclipse.uml2.uml.Comment){           
            setContents( (org.eclipse.uml2.uml.Comment) ecoreElt);
            if (GenerationProperties.getInstance().isRoundtripEnabled()) {
                setOwnerAnnotation( (org.eclipse.uml2.uml.Comment) ecoreElt);
                setTypeEAnnotation( (org.eclipse.uml2.uml.Comment) ecoreElt);
            }
        }else{
            setName((org.eclipse.uml2.uml.TimeObservation) ecoreElt);
        }
    }

    @objid ("a520f5e8-d478-4e6e-bb2a-89d09887c8d3")
    private void setContents(org.eclipse.uml2.uml.Comment ecoreElt) {
        ecoreElt.setBody(getObjingElement().getContent());
    }

    @objid ("bee7381f-3331-4772-9506-c09d811c5b68")
    private void setTypeEAnnotation(org.eclipse.uml2.uml.Comment ecoreElt) {
        NoteType noteType = getObjingElement().getModel();
        ObjingEAnnotation.setNoteTypeName(ecoreElt, noteType.getName());
    }

    @objid ("83494a5d-8a85-4d13-963f-541b134c407d")
    private void setOwnerAnnotation(final org.eclipse.uml2.uml.Comment ecoreElt) {
        if (ecoreElt.getOwner() instanceof org.eclipse.uml2.uml.AssociationClass){
            if (getObjingElement().getSubject() instanceof Association)
                ObjingEAnnotation.setIsOwnedByAssociation(ecoreElt);
            else if (getObjingElement().getSubject() instanceof ClassAssociation){
                ObjingEAnnotation.setIsOwnedByAssociationClass(ecoreElt);
            }
        }
    }

    @objid ("dfcf25f2-8f34-4c04-a5cc-ffbee21cf8bf")
    private void setName(final org.eclipse.uml2.uml.TimeObservation ecoreElt) {
        String name = getObjingElement().getName();
        
        if ((name != null) &&  (!name.equals(""))){
            ecoreElt.setName(name);
        }
    }

    @objid ("3ddbf38a-2ad6-4fa9-bc89-628736d04afb")
    @Override
    public Note getObjingElement() {
        return (Note) super.getObjingElement();
    }

}

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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of Modelio ModelElement
 * @author ebrosse
 */
@objid ("8b5aac1b-e8e9-41d9-8954-ba925521e129")
public class OModelElement extends OElement implements IOElement {
    @objid ("4d7442eb-1eba-45b3-85cb-af3f0d1ce3ef")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    /**
     * Constructor
     * @param element : the exported Modelio ModelElement
     */
    @objid ("b412f535-b026-4cb7-a420-0ea3c6446564")
    public  OModelElement(final ModelElement element) {
        super(element);
    }

    @objid ("ce17feaf-dab8-460d-98dc-b0693b294340")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        
    }

    @objid ("cb440f44-9107-4044-992b-799778083876")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt instanceof org.eclipse.uml2.uml.NamedElement){
            setName((org.eclipse.uml2.uml.NamedElement) ecoreElt);
            setVisibility((org.eclipse.uml2.uml.NamedElement) ecoreElt);
        } else if (GenerationProperties.getInstance().isRoundtripEnabled()){
            this.setNameEAnnotation(ecoreElt);
            this.setVisibilityEAnnotation(ecoreElt);
        }
        
    }

    @objid ("49fd183c-d1cb-49fd-9911-60eaad388e6d")
    private void setName(final org.eclipse.uml2.uml.NamedElement ecoreElt) {
        String name = getObjingElement().getName();
        if (AbstractObjingModelNavigation.isNotNullOrEmpty(name))
            ecoreElt.setName(name);
        
    }

    @objid ("88726831-e30d-45d0-b2df-fc1502cedb1d")
    private void setVisibilityEAnnotation(final org.eclipse.uml2.uml.Element ecoreElt) {
        Element objElement = getObjingElement();
        if (objElement instanceof NameSpace)
            ObjingEAnnotation.setVisibility(ecoreElt, ((NameSpace)objElement ).getVisibility().ordinal());
        
        else if (objElement instanceof Feature)
            ObjingEAnnotation.setVisibility(ecoreElt, ((Feature)objElement ).getVisibility().ordinal());
        
    }

    @objid ("a6f50084-445e-4497-932f-391b70ed0f0e")
    private void setVisibility(final org.eclipse.uml2.uml.NamedElement ecoreElt) {
        VisibilityMode visibility = null;
        Element objingElement = getObjingElement();
        if (objingElement instanceof NameSpace){
            visibility = ((NameSpace) objingElement).getVisibility();
        }
        
        if (objingElement instanceof Feature){
            visibility = ((Feature) objingElement).getVisibility();
        }
        
        if (visibility != null){
            switch (visibility) {
            case PACKAGEVISIBILITY:
                ecoreElt.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PACKAGE_LITERAL);
                break;
            case PRIVATE:
                ecoreElt.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PRIVATE_LITERAL);
                break;
            case PROTECTED:
                ecoreElt.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PROTECTED_LITERAL);
                break;
            case PUBLIC:
                ecoreElt.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
                break;
            case VISIBILITYUNDEFINED:
                ObjingEAnnotation.setIsUndefined(ecoreElt);
                // No undefined field exist in UML2-Ecore for visibility:
                // a visibility not setted corresponds to a PUBLIC visibility.
                break;
            }
        }
        
    }

    @objid ("49431f9c-2461-4406-b8ff-95f3ba9e0dbd")
    private void setNameEAnnotation(final org.eclipse.uml2.uml.Element ecoreElt) {
        String name = getObjingElement().getName();
        if (AbstractObjingModelNavigation.isNotNullOrEmpty(name))
            ObjingEAnnotation.setName(ecoreElt, name);
        
    }

    @objid ("734f7fe0-7243-459a-8270-dcd8f19552e2")
    @Override
    public ModelElement getObjingElement() {
        return (ModelElement) super.getObjingElement();
    }

}

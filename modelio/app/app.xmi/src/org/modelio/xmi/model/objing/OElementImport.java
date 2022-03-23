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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("41f10e59-7437-40f9-9d86-4728b40c14fd")
public class OElementImport extends OElement implements IOElement {
    @objid ("e10edbe9-51b5-4232-8758-9d3b4bf299a6")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createElementImport();
    }

    @objid ("90b9243a-c22b-4bd2-aece-b4d5f1816100")
    public  OElementImport(ElementImport element) {
        super(element);
    }

    @objid ("f2a57055-37e3-499c-8143-f1cbb2be25cb")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        org.eclipse.uml2.uml.ElementImport ecoreEltImport = (org.eclipse.uml2.uml.ElementImport) ecoreElt;
        
        ModelElement objingImportingElt = AbstractObjingModelNavigation
                .getImportingElement(getObjingElement());
        NameSpace objingImportedElt = getObjingElement().getImportedElement();
        
        if (objingImportingElt != null && objingImportedElt != null) {
        
            // Gets or creates the ecore importing element (the "owner"):
            org.eclipse.uml2.uml.Element ecoreImportingElt = genProp
                    .getMappedElement(objingImportingElt);
        
            // Gets or creates the ecore imported element (the element to
            // import):
            org.eclipse.uml2.uml.Element ecoreImportedElt = genProp
                    .getMappedElement(objingImportedElt);
        
            if (ecoreImportingElt != null && ecoreImportedElt != null) {
                ((org.eclipse.uml2.uml.Namespace) ecoreImportingElt).getElementImports().add(
                        (org.eclipse.uml2.uml.ElementImport)ecoreElt);
        
                // In UML2, an element import is defined as a directed
                // relationship between an
                // importing namespace and a packageable element.
                if (ecoreImportedElt instanceof org.eclipse.uml2.uml.PackageableElement) {
                    ecoreEltImport
                            .setImportingNamespace((org.eclipse.uml2.uml.Namespace) ecoreImportingElt);
                    ecoreEltImport
                            .setImportedElement((org.eclipse.uml2.uml.PackageableElement) ecoreImportedElt);
                } else if (ecoreImportedElt instanceof org.eclipse.uml2.uml.NamedElement) {
                    ObjingEAnnotation.setImportedElement(ecoreImportingElt,
                            ((org.eclipse.uml2.uml.NamedElement) ecoreImportedElt)
                                    .getQualifiedName());
                }
            }
        }
        
    }

    @objid ("fad8e0fc-e320-468f-9fb4-e9a3c3832f84")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        setName((org.eclipse.uml2.uml.ElementImport) ecoreElt);
        setVisibility((org.eclipse.uml2.uml.ElementImport) ecoreElt);
        // setImportedElement((org.eclipse.uml2.uml.ElementImport) ecoreElt); -> DONE IN
        // linkEcoreElt() OPERATION
        
    }

    @objid ("a9cacd30-1d7d-47b7-85f3-1130d6f46bbd")
    private void setName(org.eclipse.uml2.uml.ElementImport ecoreElt) {
        // Specifies the name that should be added to the namespace of the
        // importing Package in lieu
        // of the name of the imported PackagableElement. The aliased name must
        // not clash with any
        // other member name in the importing Package. By default, no alias is
        // used.
        String name = getObjingElement().getName();
        // If the name is "", then we don't export the name:
        if (!"".equals(name))
            ecoreElt.setAlias(name);
        
    }

    @objid ("2aeb0323-a398-4ce3-aa1c-2032dbfe8a97")
    private void setVisibility(org.eclipse.uml2.uml.ElementImport ecoreElt) {
        // Note that in theory, the visibility of an org.eclipse.uml2.uml.ElementImport is either
        // public or private.
        switch (getObjingElement().getVisibility()) {
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
            // It is the Default Values
            break;
        case VISIBILITYUNDEFINED:
            ObjingEAnnotation.setIsUndefined(ecoreElt);
            // No undefined field exist in UML2-Ecore for visibility:
            // a visibility not setted corresponds to a PUBLIC visibility.
            break;
        }
        
    }

    @objid ("e8f09ca3-f2d2-4c2d-ba15-dba3fa940568")
    @Override
    public ElementImport getObjingElement() {
        return (ElementImport) super.getObjingElement();
    }

}

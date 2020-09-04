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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("6ddc18a4-ec8b-4d31-b713-c62b11757021")
public class OComponent extends ONameSpace {
    @objid ("a25f99b7-8502-4fdd-a5b4-9e0bc4eeb028")
    private boolean isIsClassAssociation = true;

    @objid ("3419fdca-1959-4fcf-ac8c-9d1fe6c41b29")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        org.eclipse.uml2.uml.Element element = null;
        if (!this.isIsClassAssociation)
            element = createEcoreComponent();
        else
            // The Ecore org.eclipse.uml2.uml.AssociationClass can be created when parsing the
            // related Modelio org.eclipse.uml2.uml.Association,
            // ClassAssociation, or current Component.
            element = getOrCreateEcoreAssociationClass();
        return element;
    }

    @objid ("cc5b19ae-c015-43d2-b991-de912e5f2ca1")
    private org.eclipse.uml2.uml.Element createEcoreComponent() {
        return UMLFactory.eINSTANCE.createComponent();
    }

    @objid ("fad32a46-6dae-49db-91ee-3e9d15ffe754")
    private org.eclipse.uml2.uml.Element getOrCreateEcoreAssociationClass() {
        return GenerationProperties.getInstance()
                                                 .getMappedElement(getObjingElement());
    }

    @objid ("7689f272-1341-4ada-b9ef-1bd0186f6e08")
    public OComponent(Component element) {
        super(element);
        if (AbstractObjingModelNavigation.isIsClassAssociation(element))
            this.isIsClassAssociation = true;
        else
            this.isIsClassAssociation = false;
    }

    @objid ("3e6e447b-4287-4a10-ac28-f1f7517e6938")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!this.isIsClassAssociation)
            linkEcoreComponent((org.eclipse.uml2.uml.Component) ecoreElt);
    }

    @objid ("96278f15-3a6a-4214-97b9-13008a562cf1")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!this.isIsClassAssociation)
            setComponentProperties((org.eclipse.uml2.uml.Component) ecoreElt);
    }

    @objid ("24c044c8-9d99-4ec0-803a-6fd840f9a1a0")
    private void setPrimitiveEAnnotation(org.eclipse.uml2.uml.Component ecoreElt) {
        ObjingEAnnotation.setIsPrimitive(ecoreElt, getObjingElement().isIsElementary());
    }

    @objid ("17d992af-512a-4557-9b6a-44f97ae133de")
    private void setLeaf(org.eclipse.uml2.uml.Component ecoreElt) {
        ecoreElt.setIsLeaf(getObjingElement().isIsLeaf());
    }

    @objid ("c054f16b-aa84-44e3-a673-22ff142bac91")
    private void setMainEAnnotation(org.eclipse.uml2.uml.Component ecoreElt) {
        ObjingEAnnotation.setIsMain(ecoreElt, getObjingElement().isIsMain());
    }

    @objid ("f0ce5ee3-5fd6-476d-a966-2dcb8931d355")
    private void setActive(org.eclipse.uml2.uml.Component ecoreElt) {
        ecoreElt.setIsActive(getObjingElement().isIsActive());
    }

    @objid ("5393c9a2-3bd5-4e56-9f4f-ce31f51acf4f")
    private void linkEcoreComponent(org.eclipse.uml2.uml.Component ecoreElt) {
        ModelTree objingOwner = getObjingElement().getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner =  GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add(ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
                org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add(ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
                org.eclipse.uml2.uml.Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                ownerIsClass.getNestedClassifiers().add(ecoreElt);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
    }

    @objid ("4730da56-16df-425b-8811-84f4ce85f6dd")
    private void setComponentProperties(org.eclipse.uml2.uml.Component ecoreElt) {
        super.setProperties(ecoreElt);
        
        setLeaf(ecoreElt);
        setActive(ecoreElt);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()) {
            setPrimitiveEAnnotation(ecoreElt);
            setMainEAnnotation(ecoreElt);
        }
    }

    @objid ("cbb92038-3ce0-466d-a13e-ea50e536a05c")
    @Override
    public Component getObjingElement() {
        return (Component) super.getObjingElement();
    }

}

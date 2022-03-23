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
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("d6fea16f-f743-40e0-a244-206893547c5d")
public class OActor extends ONameSpace {
    @objid ("5aac9e9f-d1d1-4d08-b65d-89210ede1b07")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createActor();
    }

    @objid ("1e4ed0f0-90a3-47e5-8648-fa798cb3267f")
    public  OActor(Actor element) {
        super(element);
    }

    @objid ("1be5f538-77de-469b-baf4-5bced5656c46")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        ModelTree objingOwner = getObjingElement().getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
                org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
                org.eclipse.uml2.uml.Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                ownerIsClass.getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof  org.eclipse.uml2.uml.ClassifierTemplateParameter) {
                 org.eclipse.uml2.uml.ClassifierTemplateParameter ownerIsTemplateParameter =  (org.eclipse.uml2.uml.ClassifierTemplateParameter) ecoreOwner;
                ownerIsTemplateParameter.setParameteredElement((org.eclipse.uml2.uml.Actor)ecoreElt);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
        
    }

    @objid ("a1b61304-be1a-4275-9b86-22baec68f3a7")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("0dd9c773-6fd6-41f1-91d6-bc20461b16dd")
    @Override
    public Actor getObjingElement() {
        return (Actor) super.getObjingElement();
    }

}

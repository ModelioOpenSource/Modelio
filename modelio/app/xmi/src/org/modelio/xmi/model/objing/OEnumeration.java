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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;

@objid ("bfa780aa-9d83-4aca-8349-d58615c6bb5f")
public class OEnumeration extends ONameSpace {
    @objid ("2a0a31ad-1128-41e6-ba7c-94fecde8c9c1")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return  UMLFactory.eINSTANCE.createEnumeration();
    }

    @objid ("7c804373-49c0-4c26-83be-5b2be0d5389f")
    public OEnumeration(Enumeration element) {
        super(element);
    }

    @objid ("70060394-59e3-406d-b356-4b5fd56a0094")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        ModelTree objingOwner =  getObjingElement().getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
        
        // In UML2,  org.eclipse.uml2.uml.Signals can't own org.eclipse.uml2.uml.Enumerations.
        if ((ecoreOwner != null) && (ecoreOwner instanceof  org.eclipse.uml2.uml.Signal)){
        
            AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(
                    objingOwner,  getObjingElement(),ecoreElt);
        
            ecoreOwner = genProp.getMappedElement(objingOwner.getOwner());
        }
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                ((org.eclipse.uml2.uml.Package) ecoreOwner).getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
                ((org.eclipse.uml2.uml.Component) ecoreOwner).getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
                ( (org.eclipse.uml2.uml.Class) ecoreOwner).getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                ((org.eclipse.uml2.uml.Interface) ecoreOwner).getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateParameter) {
                org.eclipse.uml2.uml.TemplateParameter ownerIsTemplateParameter = (org.eclipse.uml2.uml.TemplateParameter) ecoreOwner;
                ( (org.eclipse.uml2.uml.Classifier)ecoreElt).setOwningTemplateParameter(ownerIsTemplateParameter);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
    }

    @objid ("8db3fced-f513-4adc-809e-3e113961230b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("529205fe-511c-4e33-80a3-4039932f4208")
    @Override
    public Enumeration getObjingElement() {
        return (Enumeration) super.getObjingElement();
    }

}

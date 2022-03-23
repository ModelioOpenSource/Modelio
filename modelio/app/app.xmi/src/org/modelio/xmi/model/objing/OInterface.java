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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("6fb9f9a4-4910-4c63-b681-ab1d0a42e4e2")
public class OInterface extends ONameSpace {
    @objid ("10173497-7eba-41e5-a6ee-bfd7a7d21519")
    private Interface objingElement;

    @objid ("52282691-7500-4339-96b2-b8fb35222548")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createInterface();
    }

    @objid ("f7a98697-0a6f-4c53-aa48-f9e811a99169")
    public  OInterface(Interface element) {
        super(element);
        this.objingElement = element;
        
    }

    @objid ("cc5d9103-f2d7-4358-9195-3dda125ed941")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
                
        ModelTree objingOwner = this.objingElement.getOwner();
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
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                org.eclipse.uml2.uml.Interface ownerIsItf = (org.eclipse.uml2.uml.Interface) ecoreOwner;
                ownerIsItf.getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateParameter) {
                org.eclipse.uml2.uml.TemplateParameter ownerIsTemplateParameter = (org.eclipse.uml2.uml.TemplateParameter) ecoreOwner;
                ( (org.eclipse.uml2.uml.Classifier)ecoreElt).setOwningTemplateParameter(ownerIsTemplateParameter);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
            // In case of a org.eclipse.uml2.uml.TemplateParameter, we don't handle export of
            // org.eclipse.uml2.uml.Interfaces: the Modelio
            // metamodel supports it but it is not possible for an user to
            // create one through the
            // MMI (man-machine interface).
        }
        
    }

    @objid ("da112940-e8e5-4b93-a77a-52db11ce7021")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        setLeaf((org.eclipse.uml2.uml.Interface) ecoreElt);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()) {
            setPrimitiveEAnnotation((org.eclipse.uml2.uml.Interface) ecoreElt);
        }
        
    }

    @objid ("06417056-54e7-4afc-aea2-16ed12ac07f3")
    private void setPrimitiveEAnnotation(org.eclipse.uml2.uml.Interface ecoreElt) {
        ObjingEAnnotation.setIsPrimitive(ecoreElt, this.objingElement.isIsElementary());
    }

    @objid ("eb9435fe-de31-43ac-86d6-f8f9782b5b1e")
    private void setLeaf(org.eclipse.uml2.uml.Interface ecoreElt) {
        ecoreElt.setIsLeaf(this.objingElement.isIsLeaf());
    }

}

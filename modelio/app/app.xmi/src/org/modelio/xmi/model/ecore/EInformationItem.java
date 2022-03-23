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
package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("d043ecf6-dd6b-4b26-ab49-1b28c1b7f1ac")
public class EInformationItem extends ENamedElement {
    @objid ("f4a86ac2-c283-475b-ad1a-1f0138079700")
    private org.eclipse.uml2.uml.InformationItem ecoreElement = null;

    @objid ("be582ef0-a03b-42e9-93bd-2e78999b1033")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInformationItem();
    }

    @objid ("b1d90dc5-6170-4024-9813-2ab4ad11709f")
    public  EInformationItem(org.eclipse.uml2.uml.InformationItem element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("6ccbba8e-2914-440f-9d87-e0a6f0e8c310")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        Object objOwner = ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
        if (objOwner instanceof Profile){
             ((InformationItem) objingElt).setOwner(ReverseProperties.getInstance().getExternalPackage());
        }else if (objOwner instanceof ModelTree){
            ((InformationItem) objingElt).setOwner((ModelTree) objOwner);
        } else if (objingElt instanceof ModelElement){
            EcoreModelNavigation.unsupportedOwnedWithIj((Element)objOwner, (ModelElement) objingElt);
            ((InformationItem) objingElt).setOwner(ReverseProperties.getInstance().getExternalPackage());
        }else
             ((InformationItem) objingElt).setOwner(ReverseProperties.getInstance().getExternalPackage());
        
    }

    @objid ("12bc6c35-4fa0-411f-b620-6525dc2fb5c0")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setRepresented((InformationItem) objingElt);
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            setAbstract((InformationItem) objingElt);
            setLeaf((InformationItem) objingElt);
            setRoot((InformationItem) objingElt);
            setEAVisibility((InformationItem) objingElt);
        }
        
    }

    @objid ("69fdae02-79f8-474e-900d-316bb9bd3b20")
    private void setEAVisibility(InformationItem objingElt) {
        int literal = ObjingEAnnotation.getVisibility(this.ecoreElement);
        objingElt.setVisibility(VisibilityMode.values()[literal]);
        
    }

    @objid ("469e33e1-af65-4e69-b4b9-caa6f0929e66")
    private void setRoot(InformationItem objingElt) {
        objingElt.setIsRoot(ObjingEAnnotation.isRoot(this.ecoreElement));
    }

    @objid ("635c8dcd-b0d5-4a80-a10c-4855c9139aba")
    private void setLeaf(InformationItem objingElt) {
        objingElt.setIsLeaf(ObjingEAnnotation.isLeaf(this.ecoreElement));
    }

    @objid ("d40082dd-4546-44f9-9d12-588813f9fa30")
    private void setAbstract(InformationItem objingElt) {
        objingElt.setIsAbstract(ObjingEAnnotation.isIsAbstract(this.ecoreElement));
    }

    @objid ("2f4795d5-d17b-4626-aa43-c0e7b952fc96")
    private void setRepresented(InformationItem objingElt) {
        for(Object represented : this.ecoreElement.getRepresenteds()){
            Object objRepresented = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)represented);
            if (objRepresented instanceof Classifier)
                objingElt.getRepresented().add((Classifier) objRepresented);
        }
        
    }

}

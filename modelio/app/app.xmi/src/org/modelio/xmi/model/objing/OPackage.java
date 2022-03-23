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
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("681974c0-25e8-457d-8af7-f664bdf59bfe")
public class OPackage extends ONameSpace {
    @objid ("1f5f70b7-b437-4da2-a51e-6c0efe50e1d4")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPackage();
    }

    @objid ("4442262e-29ad-4963-b9aa-63f2506f7379")
    public  OPackage(org.modelio.metamodel.uml.statik.Package element) {
        super(element);
    }

    @objid ("d32efabc-f7c9-474a-8368-46da59029de7")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = ((org.modelio.metamodel.uml.statik.Package)getObjingElement()).getCompositionOwner();
        
        if (objingOwner != null) {// else : case of the root package
            org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
            if (ecoreOwner != null) {
                if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                    org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
                    ownerIsPkg.getPackagedElements().add((PackageableElement)ecoreElt);
                } else if (ecoreOwner instanceof Component) {
                    Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                    ownerIsCmpnt.getPackagedElements().add((PackageableElement)ecoreElt);
                }
                else {
                    ecoreElt.destroy();
                    throw new NotFoundException("Owner Class ("
                            + ecoreOwner.getClass().getSimpleName()
                            + ") Not Found");
                }
            }
        }
        
    }

    @objid ("fa0646a6-2478-4b46-94a4-be0e9eb36fe2")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        //Modelio Properties
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            this.setLeafEAnnotation((org.eclipse.uml2.uml.Package) ecoreElt); 
            this.setRootEAnnotation((org.eclipse.uml2.uml.Package) ecoreElt);
            this.setInstantiableEAnnotation((org.eclipse.uml2.uml.Package) ecoreElt);
        }
        
    }

    @objid ("54ce1f42-73ff-41c3-8a10-708626f22ae3")
    private void setInstantiableEAnnotation(org.eclipse.uml2.uml.Package ecoreElt) {
        ObjingEAnnotation.setIsInstantiable(ecoreElt, ((org.modelio.metamodel.uml.statik.Package)getObjingElement()).isIsInstantiable());
    }

    @objid ("46211b2a-ddee-42ae-8db1-10f963c1aee4")
    private void setLeafEAnnotation(org.eclipse.uml2.uml.Package ecoreElt) {
        ObjingEAnnotation.setIsLeaf(ecoreElt, ((org.modelio.metamodel.uml.statik.Package)getObjingElement()).isIsLeaf());
    }

    @objid ("bd363dca-7ce5-4d84-b50f-5e94957af4e9")
    private void setRootEAnnotation(org.eclipse.uml2.uml.Package ecoreElt) {
        ObjingEAnnotation.setIsRoot(ecoreElt, ((org.modelio.metamodel.uml.statik.Package)getObjingElement()).isIsRoot());
    }

}

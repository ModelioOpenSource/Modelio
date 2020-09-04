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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcorePrimitiveTypeMapper;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("6d173106-3cea-4371-8c5c-ea9d451a8193")
public class EPrimitiveType extends ENamedElement {
    @objid ("ea3f2f96-cad2-45f2-9c7a-685db851423d")
    private org.eclipse.uml2.uml.PrimitiveType ecoreElement = null;

    @objid ("c99212e7-ec16-4070-93de-dd74ab4625c0")
    @Override
    public Element createObjingElt() {
        if (EcorePrimitiveTypeMapper.isPredefinedType(this.ecoreElement)) {
            return EcorePrimitiveTypeMapper.getPredefinedType(this.ecoreElement);
        }
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createDataType();
    }

    @objid ("c0fae08b-dcd5-4656-bfea-2f9903c1190e")
    public EPrimitiveType(org.eclipse.uml2.uml.PrimitiveType element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("afa84d8e-4259-45d2-8f56-659653530dff")
    @Override
    public void attach(Element objingElt) {
        if (!EcorePrimitiveTypeMapper.isPredefinedType(this.ecoreElement)){
            ReverseProperties revProp = ReverseProperties.getInstance();
        
            // we get the owner
            org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
            Object objingOwner =  revProp.getMappedElement(ecoreOwner);
        
            if ((objingOwner != null) 
                    &&  (objingOwner instanceof ModelTree) 
                    && ! (objingOwner instanceof Profile)){
                ((GeneralClass)objingElt).setOwner((ModelTree)objingOwner);
            }else
                ((GeneralClass)objingElt).setOwner(ReverseProperties.getInstance().getExternalPackage());      
        
        }
    }

    @objid ("c1dbea71-c4f0-4407-ba1e-5016f0dd1f85")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (!EcorePrimitiveTypeMapper.isPredefinedType(this.ecoreElement)){
            if (objingElt instanceof GeneralClass){
                setVisibility((GeneralClass)objingElt);
            }
        
            if (objingElt instanceof DataType) {
                setAbstract((DataType) objingElt);
                setLeaf((DataType) objingElt);
        
                if (ReverseProperties.getInstance().isRoundtripEnabled()) {
                    setPrimitiveEAnnotation((DataType) objingElt);
                    setRootEAnnotation((DataType) objingElt);
                }
        
            }
        }
    }

    @objid ("23a7ef13-5c2b-4bff-9298-1a57ee0a8a69")
    private void setAbstract(DataType objingElt) {
        objingElt.setIsAbstract(this.ecoreElement.isAbstract());
    }

    @objid ("435fd694-9f35-4258-93e5-88bf96a395be")
    private void setLeaf(DataType objingElt) {
        objingElt.setIsLeaf(this.ecoreElement.isLeaf());
    }

    @objid ("d36a155b-2581-4fef-9d0d-cdbb21e03ba2")
    private void setPrimitiveEAnnotation(DataType objingElt) {
        objingElt.setIsElementary(ObjingEAnnotation.isPrimitive(this.ecoreElement));
    }

    @objid ("4357309f-bd6a-4d07-ac11-160fd4ac960b")
    private void setRootEAnnotation(DataType objingElt) {
        objingElt.setIsRoot(ObjingEAnnotation.isRoot(this.ecoreElement));
    }

    @objid ("fe0ab26f-5c98-4f45-82e1-e71081157f5e")
    public void setVisibility(GeneralClass objingElt) {
        switch (this.ecoreElement.getVisibility().getValue()) {
        case org.eclipse.uml2.uml.VisibilityKind.PUBLIC:
            objingElt.setVisibility(VisibilityMode.PUBLIC);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PRIVATE:
            objingElt.setVisibility(VisibilityMode.PRIVATE);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PACKAGE:
            objingElt.setVisibility(VisibilityMode.PACKAGEVISIBILITY);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PROTECTED:
            objingElt.setVisibility(VisibilityMode.PROTECTED);
            break;
        default:
            objingElt.setVisibility(VisibilityMode.PUBLIC);
        }
        
        if (ObjingEAnnotation.isUndefined(this.ecoreElement))
            objingElt
            .setVisibility(VisibilityMode.VISIBILITYUNDEFINED);
    }

}

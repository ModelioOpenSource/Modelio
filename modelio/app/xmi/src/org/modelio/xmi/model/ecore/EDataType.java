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
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.EcorePrimitiveTypeMapper;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the import of  Ecore org.eclipse.uml2.uml.DataType
 * @author ebrosse
 */
@objid ("6ce204df-d714-4384-a48b-20c59dcd591c")
public class EDataType extends ENamedElement {
    @objid ("d1d75979-db80-4d98-8088-59b3e164b72d")
    private org.eclipse.uml2.uml.DataType ecoreElement = null;

    @objid ("f9c62d64-20b0-4b6c-8850-d520edacffba")
    @Override
    public Element createObjingElt() {
        if (EcorePrimitiveTypeMapper.isPredefinedType(this.ecoreElement)) {
            return EcorePrimitiveTypeMapper.getPredefinedType(this.ecoreElement);
              }
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createDataType();
    }

    /**
     * Constructor with the imported Ecore org.eclipse.uml2.uml.DataType
     * @param element : the imported Ecore org.eclipse.uml2.uml.DataType
     */
    @objid ("ac60c550-1ef5-410a-80b1-82ef516c6ee9")
    public EDataType(org.eclipse.uml2.uml.DataType element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("d7f642bb-a25d-4847-8ffc-3541743844d6")
    @Override
    public void attach(Element objingElt) {
        if (!((this.ecoreElement instanceof org.eclipse.uml2.uml.PrimitiveType)
                || (this.ecoreElement instanceof org.eclipse.uml2.uml.Enumeration))) {
        
            ModelTree objingDTImport = (ModelTree) objingElt;
        
            ModelTree objingOwner = EcoreModelNavigation.getNearestDataTypeOwner(this.ecoreElement);
        
            if (objingOwner != null){
                if (objingOwner instanceof Profile){
                    objingDTImport.setOwner(ReverseProperties.getInstance().getExternalPackage());
                }else {
                    objingDTImport.setOwner(objingOwner);
                }
            }
        }
    }

    @objid ("5b5546ff-ec80-4e5f-afec-16dc57c3cdd3")
    @Override
    public void setProperties(Element objingElt) {
        if (!(EcorePrimitiveTypeMapper.isPredefinedType(this.ecoreElement))) {
            super.setProperties(objingElt);
            if (!(this.ecoreElement instanceof org.eclipse.uml2.uml.PrimitiveType)) {
                setVisibility((GeneralClass) objingElt);
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
    }

    @objid ("9bee6d47-2b95-4425-926b-62c8b4fa4c52")
    private void setVisibility(GeneralClass objingElt) {
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

    @objid ("cc117593-d7f9-4ffb-bfe5-0d35018ce5f1")
    private void setAbstract(DataType objingElt) {
        objingElt.setIsAbstract(this.ecoreElement.isAbstract());
    }

    @objid ("60f31d3f-92a2-40e3-96e0-969467bc421a")
    private void setLeaf(DataType objingElt) {
        objingElt.setIsLeaf(this.ecoreElement.isLeaf());
    }

    @objid ("88bd7646-bb06-4594-8414-990ff6c80714")
    private void setPrimitiveEAnnotation(DataType objingElt) {
        objingElt.setIsElementary(ObjingEAnnotation.isPrimitive(this.ecoreElement));
    }

    @objid ("8ba01d3b-5ad3-479e-bc43-17e9b7bada33")
    private void setRootEAnnotation(DataType objingElt) {
        objingElt.setIsRoot(ObjingEAnnotation.isRoot(this.ecoreElement));
    }

}

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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ModelioPrimitiveTypeMapper;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of Modelio IDatatype
 * @author ebrosse
 */
@objid ("e3c65e84-ec06-4d0a-898e-a951ad2f3ad2")
public class ODataType extends ONameSpace {
    @objid ("ed76a7e4-2f6a-41d8-b0cf-7f9eb38dd705")
    private DataType objingElement;

    @objid ("2298138d-00c8-4367-b859-465cb22df2ac")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        MObject objingOwner = this.objingElement.getCompositionOwner();
        
        if ( objingOwner instanceof Signal) {
            return null;
        }
        
        if (ModelioPrimitiveTypeMapper.isPredefinedType(this.objingElement)){
            return  ModelioPrimitiveTypeMapper.getEcoreType(this.objingElement);
        }else if (this.objingElement.isIsElementary())
            return UMLFactory.eINSTANCE.createPrimitiveType();
        else
            return UMLFactory.eINSTANCE.createDataType();
    }

    /**
     * Constructor with an the exported DataType
     * 
     * @param element : the exported DataType
     */
    @objid ("74af972b-798c-429f-8449-242386243434")
    public ODataType(DataType element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("809e828a-b8dd-4629-a358-501b40444a7a")
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
            } else if (ecoreOwner instanceof  org.eclipse.uml2.uml.Signal) {
                // In UML2,  org.eclipse.uml2.uml.Signals can't own org.eclipse.uml2.uml.DataTypes.
                /*
                 *  org.eclipse.uml2.uml.Signal ownerIsSignal =  (org.eclipse.uml2.uml.Signal)ecoreOwner; EAnnotation
                 * objingEA =
                 * ObjingEAnnotation.getOrCreateObjingEAnnotation(ownerIsSignal);
                 * if (objingEA != null) objingEA.getContents().add(ecoreElt);
                 */
                AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(
                        objingOwner, this.objingElement, ecoreElt);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
    }

    @objid ("de7ef657-9769-47cb-a0f8-6ca975163441")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        // -> isPrimitive => export as a org.eclipse.uml2.uml.PrimitiveType element
        // -> else => export as a org.eclipse.uml2.uml.DataType element.
        //UML Properties
        setLeaf((org.eclipse.uml2.uml.DataType) ecoreElt);
        
        //Modelio Properties
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            setRootEAnnotation( ecoreElt);
            setPrimitiveEAnnotation(ecoreElt, this.objingElement.isIsElementary());       
        }
    }

    @objid ("4df192dc-fb7b-4077-8b74-b6f2dea6428f")
    private void setLeaf(org.eclipse.uml2.uml.DataType ecoreElt) {
        ecoreElt.setIsLeaf(this.objingElement.isIsLeaf());
    }

    @objid ("161a4dfa-8501-4c6f-91f4-e3a5b06b87c0")
    private void setRootEAnnotation(org.eclipse.uml2.uml.Element ecoreElt) {
        ObjingEAnnotation.setIsRoot(ecoreElt, this.objingElement.isIsRoot());
    }

    @objid ("b41adf5a-d394-477b-a263-41c745237d1c")
    private void setPrimitiveEAnnotation(org.eclipse.uml2.uml.Element ecoreElt, boolean isPrimitive) {
        ObjingEAnnotation.setIsPrimitive(ecoreElt, isPrimitive);
    }

}

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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("4e6d9e6f-ea63-4453-8e07-11c5ab3fd962")
public class OCollaboration extends ONameSpace {
    @objid ("fc03ef08-7bb0-4f27-a484-2a054a6621b3")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createCollaboration();
    }

    @objid ("75ac470c-1106-4ea9-8b36-d5d9fb43d48d")
    public OCollaboration(Collaboration param) {
        super(param);
    }

    @objid ("2b6d518f-0c98-4656-b123-88333dcbc9a3")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = getObjingElement().getCompositionOwner();
        
        // Possible owner of an objing  org.eclipse.uml2.uml.Collaboration:
        // org.eclipse.uml2.uml. Behavior ;  org.eclipse.uml2.uml.Operation ; ModelTree ; org.eclipse.uml2.uml.TemplateParameter
        
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (objingOwner instanceof Behavior) {
                setOwnerBehavior( (org.eclipse.uml2.uml.Collaboration) ecoreElt, ecoreOwner);
            } else if (objingOwner instanceof Operation) {
                setOwnerOperation( (org.eclipse.uml2.uml.Collaboration) ecoreElt,
                        (Operation) objingOwner);
            } else if (objingOwner instanceof ModelTree) {
                setOwnerModelTree( (org.eclipse.uml2.uml.Collaboration) ecoreElt,
                        (ModelTree) objingOwner, ecoreOwner);
            } else {
                AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(
                        objingOwner, getObjingElement(), ecoreElt);
                String errorMsg = "Owner Class ("
                            + ecoreOwner.getClass().getSimpleName()
                            + ") Not Found";
        
                throw new NotFoundException(errorMsg);
            }
        } else{
        
            AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(
                    objingOwner, getObjingElement(),ecoreElt);
            String errorMsg = "Owner Class Not Found";
            throw new NotFoundException(errorMsg);
        }
    }

    @objid ("43b49293-5abb-4942-b5ab-8d344b6571c4")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        //UML Properties
        setLeaf( (org.eclipse.uml2.uml.Collaboration) ecoreElt);
        
        //Modelio Properties
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            setConcurrentEAnnotation( (org.eclipse.uml2.uml.Collaboration) ecoreElt);
            setRootEAnnotation( (org.eclipse.uml2.uml.Collaboration)ecoreElt);
        }
    }

    @objid ("502cb179-7623-4efc-b4f9-8a5914784434")
    private void setConcurrentEAnnotation(org.eclipse.uml2.uml.Collaboration ecoreElt) {
        ObjingEAnnotation.setIsConcurrent(ecoreElt, getObjingElement().isIsConcurrent());
    }

    @objid ("9b34b711-2ec7-4e15-9d0f-310cdcd193e3")
    private void setRootEAnnotation(org.eclipse.uml2.uml.Collaboration ecoreElt) {
        ObjingEAnnotation.setIsRoot(ecoreElt, getObjingElement().isIsRoot());
    }

    @objid ("61ab8699-a1ce-418f-83e3-ac2fe0fa06c1")
    private void setLeaf(org.eclipse.uml2.uml.Collaboration ecoreElt) {
        ecoreElt.setIsLeaf(getObjingElement().isIsLeaf());
    }

    @objid ("278ddee8-3b9a-42c9-a73b-c8a972ded7da")
    private void setOwnerBehavior(org.eclipse.uml2.uml.Collaboration ecoreCollab, org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof org.eclipse.uml2.uml. Behavior) {
            org.eclipse.uml2.uml. Behavior ownerIsBehavior = (org.eclipse.uml2.uml.Behavior) ecoreOwner;
            ownerIsBehavior.getNestedClassifiers().add(ecoreCollab);
        }
    }

    @objid ("8b85ccd5-4524-4f6b-a998-666a86e557e7")
    private void setOwnerOperation(org.eclipse.uml2.uml.Collaboration ecoreCollab, Operation objingOperation) {
        //  org.eclipse.uml2.uml.Operations can't own  org.eclipse.uml2.uml.Collaborations in UML2 => attach the
        // collaboration to
        // the owner of the operation
        MObject ownerOp = objingOperation.getCompositionOwner();
        
        org.eclipse.uml2.uml.Element ecoreOwnerOp = GenerationProperties.getInstance().getMappedElement(ownerOp);
        
        if (ecoreOwnerOp != null) {
            if (ownerOp instanceof Classifier) {
                setOwnerModelTree(ecoreCollab, (Classifier) ownerOp,
                        ecoreOwnerOp);
            }
        } else{
            ecoreCollab.destroy();
            throw new NotFoundException("Owner Class not found.");
        }
    }

    @objid ("012a1f88-c5f1-49b6-b402-5067cb2e3125")
    private void setOwnerModelTree(org.eclipse.uml2.uml.Collaboration ecoreCollab, ModelTree objingMdlTree, org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
            ownerIsPkg.getPackagedElements().add(ecoreCollab);
        } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
            org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
            ownerIsCmpnt.getPackagedElements().add(ecoreCollab);
        } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
            org.eclipse.uml2.uml.Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
            ownerIsClass.getNestedClassifiers().add(ecoreCollab);
        } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
            org.eclipse.uml2.uml.Interface ownerIsItf = (org.eclipse.uml2.uml.Interface) ecoreOwner;
            ownerIsItf.getNestedClassifiers().add(ecoreCollab);
        } else {
            AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(objingMdlTree,
                    getObjingElement(),ecoreCollab);
        }
    }

    @objid ("c7758aa3-c457-4288-b79d-d555ef2747ab")
    @Override
    public Collaboration getObjingElement() {
        return (Collaboration) super.getObjingElement();
    }

}

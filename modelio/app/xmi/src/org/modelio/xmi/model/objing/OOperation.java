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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.XMIProperties;

@objid ("f4b8a21b-124a-4f0d-aaa9-c96fa4d384c5")
public class OOperation extends OFeature {
    @objid ("d2002cc7-3fcf-447f-abb3-2695b982b0fe")
    private Operation objingElement;

    @objid ("902d7e02-49ec-4a50-accc-b61f0dea55fa")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        MObject objingOwner = this.objingElement.getCompositionOwner();
        if (((ModelElement) getObjingElement()).isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2RECEPTION)){
            if (objingOwner instanceof Actor
                    || objingOwner instanceof UseCase
                    || objingOwner instanceof Signal) {
        
                AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(
                        objingOwner, this.objingElement);
                return null;
        
            }
            return UMLFactory.eINSTANCE.createReception();
        }else if (((ModelElement) getObjingElement()).isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2REDEFINABLETEMPLATESIGNATURE)){
            return UMLFactory.eINSTANCE.createRedefinableTemplateSignature();
        }else if (((ModelElement) getObjingElement()).isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2TEMPLATESIGNATURE)){
            return UMLFactory.eINSTANCE.createTemplateSignature();
        }else{
        
            if (objingOwner instanceof Actor
                    || objingOwner instanceof UseCase
                    || objingOwner instanceof Signal) {
        
                AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(
                        objingOwner, this.objingElement);
                return null;
        
            }
            return UMLFactory.eINSTANCE.createOperation();
        }
    }

    @objid ("d72b6f62-e293-4da1-a9fd-2f66b7e06721")
    public OOperation(Operation element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("78642492-e39e-40ba-b293-04924640ed44")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt.getOwner() == null){
            if (ecoreElt instanceof  org.eclipse.uml2.uml.Operation){
                attachOperation( (org.eclipse.uml2.uml.Operation) ecoreElt);
            } else if (ecoreElt instanceof org.eclipse.uml2.uml.Reception){
                attachReception( (org.eclipse.uml2.uml.Reception) ecoreElt);
            } else if (ecoreElt instanceof org.eclipse.uml2.uml.TemplateSignature){
                attachTemplateSignature((org.eclipse.uml2.uml.TemplateSignature) ecoreElt);
            }
        }
    }

    @objid ("b41dcffa-a5d5-4386-9958-c7869f7e35d6")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (ecoreElt instanceof org.eclipse.uml2.uml. BehavioralFeature){
            setAbstract((org.eclipse.uml2.uml.BehavioralFeature) ecoreElt);
            setFinal((org.eclipse.uml2.uml.BehavioralFeature) ecoreElt);
            setIsClass((org.eclipse.uml2.uml.BehavioralFeature) ecoreElt);
            setPassingMode((org.eclipse.uml2.uml.BehavioralFeature) ecoreElt);
            setConcurrency((org.eclipse.uml2.uml.BehavioralFeature) ecoreElt);        
            setMethod((org.eclipse.uml2.uml.BehavioralFeature) ecoreElt);
        }
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.Reception){
            setSignal( (org.eclipse.uml2.uml.Reception) ecoreElt);
        }
        if (ecoreElt instanceof  org.eclipse.uml2.uml.Operation){
            setRedefined( (org.eclipse.uml2.uml.Operation) ecoreElt);
        }
    }

    @objid ("71cee9c8-de1f-497c-940c-a0eb78f830fd")
    private void setAbstract(org.eclipse.uml2.uml.BehavioralFeature ecoreElt) {
        ecoreElt.setIsAbstract(this.objingElement.isIsAbstract());
    }

    @objid ("bee7f6e9-a037-497b-8641-6ea0c0924068")
    private void setFinal(org.eclipse.uml2.uml.BehavioralFeature ecoreElt) {
        ecoreElt.setIsLeaf(this.objingElement.isFinal());
    }

    @objid ("2136e415-ae63-4a24-9803-6c2179949e5b")
    private void setIsClass(org.eclipse.uml2.uml.BehavioralFeature ecoreElt) {
        ecoreElt.setIsStatic(this.objingElement.isIsClass());
    }

    @objid ("9acfa321-eb40-4969-9325-159decfe5393")
    private void setPassingMode(org.eclipse.uml2.uml.BehavioralFeature ecoreElt) {
        boolean isQuery = false;
        switch (this.objingElement.getPassing()) {
        case METHODIN:
            isQuery = true;
            break;
        case METHODOUT:
            isQuery = false;
            break;
        }
        
        if (ecoreElt instanceof  org.eclipse.uml2.uml.Operation)
            ( (org.eclipse.uml2.uml.Operation)ecoreElt).setIsQuery(isQuery);
    }

    @objid ("b8a36dac-82ea-4389-9d4f-6607ee6e1777")
    private void setConcurrency(org.eclipse.uml2.uml.BehavioralFeature ecoreElt) {
        // Since concurrency is represented as a boolean in Ijing, the mapping
        // is true for the
        // CONCURRENT value, and false else (SEQUENTIAL by default):
        if (this.objingElement.isConcurrency())
            ecoreElt.setConcurrency(org.eclipse.uml2.uml.CallConcurrencyKind.CONCURRENT_LITERAL);
        else
            ecoreElt.setConcurrency(org.eclipse.uml2.uml.CallConcurrencyKind.SEQUENTIAL_LITERAL);
    }

    @objid ("7a3ec367-89fc-4c51-adcc-2b19223fe02f")
    private void setMethod(org.eclipse.uml2.uml.BehavioralFeature ecoreElt) {
        for (Dependency depend : this.objingElement.getDependsOnDependency()){
            if (depend.isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2METHODREFERENCE)){
                org.eclipse.uml2.uml.Element behavior = GenerationProperties.getInstance().getMappedElement(depend.getDependsOn());
                if ((behavior != null) && (behavior instanceof org.eclipse.uml2.uml. Behavior)){
                    ecoreElt.getMethods().add((org.eclipse.uml2.uml.Behavior) behavior);
                }
            }
        }
    }

    @objid ("92b8e080-75ed-4469-962b-bd5755dccf4f")
    private void attachReception(org.eclipse.uml2.uml.Reception ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        ModelTree objingOwner = this.objingElement.getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            //  org.eclipse.uml2.uml.Operations defined on org.eclipse.uml2.uml.Actor, org.eclipse.uml2.uml.UseCase and  org.eclipse.uml2.uml.Signal are not exported
            // in
            // UML2. 
            if (objingOwner instanceof Actor
                    || objingOwner instanceof UseCase
                    || objingOwner instanceof Signal) {
                AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(
                        objingOwner, this.objingElement, ecoreElt);
            } else {
                if (ecoreOwner instanceof Component) {
                    Component ownerIsComponent = (org.eclipse.uml2.uml.Component) ecoreOwner;
                    ownerIsComponent.getOwnedReceptions().add(ecoreElt);
                } else if (ecoreOwner instanceof Node) {
                    Node ownerIsNode = (org.eclipse.uml2.uml.Node) ecoreOwner;
                    ownerIsNode.getOwnedReceptions().add(ecoreElt);
                } else
                    // An org.eclipse.uml2.uml.AssociationClass is a Class.
                    if (ecoreOwner instanceof Class) {
                        Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                        ownerIsClass.getOwnedReceptions().add(ecoreElt);
                    } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                        org.eclipse.uml2.uml.Interface ownerIsInterface = (org.eclipse.uml2.uml.Interface) ecoreOwner;
                        ownerIsInterface.getOwnedReceptions().add(ecoreElt);
                    } else {
                        ecoreElt.destroy();
                        //                    XMILogs xmilogs = XMILogs.getInstance();
                        //                    xmilogs.writelnInLog(org.eclipse.uml2.uml.Messages.getString("logFile.warning.unsupportedOwnerExport", ))
                        throw new NotFoundException("Owner Class ("
                                + ecoreOwner.getClass().getSimpleName()
                                + ") Not Found");
                    }
            }
        }
    }

    @objid ("662cca46-ca10-47d9-a094-4539902becc5")
    private void attachOperation(org.eclipse.uml2.uml.Operation ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        ModelTree objingOwner = this.objingElement.getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            //  org.eclipse.uml2.uml.Operations defined on org.eclipse.uml2.uml.Actor, org.eclipse.uml2.uml.UseCase and  org.eclipse.uml2.uml.Signal are not exported
            // in
            // UML2. They are
            // added into an EAnnotation:
            if (objingOwner instanceof Actor
                    || objingOwner instanceof UseCase
                    || objingOwner instanceof Signal) {
             
                AbstractObjingModelNavigation.infoOfUnsupportedOwnedWithEMF(
                        objingOwner, this.objingElement, ecoreElt);
        
            } else {
                if (ecoreOwner instanceof Component) {
                    Component ownerIsComponent = (org.eclipse.uml2.uml.Component) ecoreOwner;
                    ownerIsComponent.getOwnedOperations().add(ecoreElt);
                } else if (ecoreOwner instanceof Node) {
                    Node ownerIsNode = (org.eclipse.uml2.uml.Node) ecoreOwner;
                    ownerIsNode.getOwnedOperations().add(ecoreElt);
                } else
                    // An org.eclipse.uml2.uml.AssociationClass is a Class.
                    if (ecoreOwner instanceof Class) {
                        Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                        ownerIsClass.getOwnedOperations().add(ecoreElt);
                    } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                        org.eclipse.uml2.uml.Interface ownerIsInterface = (org.eclipse.uml2.uml.Interface) ecoreOwner;
                        ownerIsInterface.getOwnedOperations().add(ecoreElt);
                    } else if (ecoreOwner instanceof org.eclipse.uml2.uml.DataType) {
                        org.eclipse.uml2.uml.DataType ownerIsDataType = (org.eclipse.uml2.uml.DataType) ecoreOwner;
                        ownerIsDataType.getOwnedOperations().add(ecoreElt);
                    } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Artifact) {
                        org.eclipse.uml2.uml.Artifact ownerIsArtifact = (org.eclipse.uml2.uml.Artifact) ecoreOwner;
                        ownerIsArtifact.getOwnedOperations().add(ecoreElt);
                    } else {
                        ecoreElt.destroy();
                        //                    XMILogs xmilogs = XMILogs.getInstance();
                        //                    xmilogs.writelnInLog(org.eclipse.uml2.uml.Messages.getString("logFile.warning.unsupportedOwnerExport", ))
                        throw new NotFoundException("Owner Class ("
                                + ecoreOwner.getClass().getSimpleName()
                                + ") Not Found");
                    }
            }
        }
    }

    @objid ("61c90c0c-4015-49cf-87e9-624b78bdf1b9")
    private void setSignal(org.eclipse.uml2.uml.Reception ecoreElt) {
        List<Signal> lists = ((Operation) getObjingElement()).getSRepresentation();
        if (lists.size()>0){
            org.eclipse.uml2.uml.Element ecoreSignal = GenerationProperties.getInstance().getMappedElement(lists.get(0));
            if (ecoreSignal instanceof  org.eclipse.uml2.uml.Signal)
                ecoreElt.setSignal( (org.eclipse.uml2.uml.Signal) ecoreSignal);
        }
    }

    @objid ("ad2867a4-3aad-483f-9d42-451068e9cf9a")
    private void setRedefined(org.eclipse.uml2.uml.Operation ecoreElt) {
        for (Operation redefines : this.objingElement.getRedefinition()){
            org.eclipse.uml2.uml.Element ecoreOperation = GenerationProperties.getInstance().getMappedElement(redefines);
            if (ecoreOperation instanceof  org.eclipse.uml2.uml.Operation){
                ecoreElt.getRedefinedOperations().add( (org.eclipse.uml2.uml.Operation)ecoreOperation);
            }
        }
    }

    @objid ("5c78c6ea-fab2-4477-a4dd-4af33c3709c1")
    private void attachTemplateSignature(org.eclipse.uml2.uml.TemplateSignature ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        ModelTree objingOwner = this.objingElement.getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
        if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateableElement)
            ecoreElt.setTemplate((org.eclipse.uml2.uml.TemplateableElement)ecoreOwner);
        else 
            ecoreElt.destroy();
    }

}

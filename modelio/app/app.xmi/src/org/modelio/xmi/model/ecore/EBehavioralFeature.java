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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2MethodReference;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("51edacaf-5331-4648-9bdd-964adb89f2d2")
public class EBehavioralFeature extends EFeature {
    @objid ("0f2111ec-231e-4aa8-8679-9310c5ddf2ac")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("ca7dcf1e-3b9b-4578-8530-af0eb3183640")
    public  EBehavioralFeature(org.eclipse.uml2.uml.BehavioralFeature element) {
        super(element);
    }

    @objid ("eec9c388-f284-4c8f-a07c-fd38837f6e6b")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (objingElt instanceof Operation){            
            setMethod((Operation) objingElt);
            setAbstract((Operation) objingElt);
            setFinal((Operation) objingElt);
            setIsClass((Operation) objingElt);
            setConcurrency((Operation) objingElt);
            setRaisedException((Operation) objingElt);
        }
        
    }

    @objid ("4dbe1760-f743-42f9-88f8-3a4b04140b15")
    private void setRaisedException(Operation objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        // Test if the number of raised exceptions in the Ecore model is the
        // same in the Modelio model:
        EList<?> ecoreRaisedExceptions = ((org.eclipse.uml2.uml.BehavioralFeature)getEcoreElement()).getRaisedExceptions();
        List<RaisedException> objingRaisedExceptions = objingElt
                .getThrown();
        int objingExcepNumber = objingRaisedExceptions.size();
        
        // Warning : no update in case of same number of thrown exceptions.
        if (ecoreRaisedExceptions.size() != objingExcepNumber) {
            boolean isNewExeptionLink;
            for (Object raisedException : ecoreRaisedExceptions) {
                isNewExeptionLink = false;
                org.eclipse.uml2.uml.Type ecoreThrownType =  (org.eclipse.uml2.uml.Type) raisedException;
                Element objingThrownType = (Element) revProp
                        .getMappedElement(ecoreThrownType);
        
                if (objingThrownType instanceof Classifier) {
                    if (isNewExeptionLink
                            || !isExceptionAlreadyThrown(objingElt,
                                    objingThrownType)) {
                        RaisedException objingExceptionLink = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createRaisedException();
        
                        objingExceptionLink.setThrower(objingElt);
                        objingExceptionLink
                        .setThrownType((Classifier) objingThrownType);
                    }
                }
            }
        }
        
    }

    @objid ("7ce41ee6-22fc-4b9f-8288-0e77a8ddb5a3")
    private boolean isExceptionAlreadyThrown(Operation objingElt, Element objingThrownTypeFromMap) {
        for (RaisedException objingExcep : objingElt.getThrown()) {
            Classifier objingThrownType = objingExcep.getThrownType();
            if (objingThrownTypeFromMap.equals(objingThrownType))
                return true;
        }
        return false;
    }

    @objid ("b875b378-f22a-48f4-813a-c1726dab8279")
    private void setAbstract(Operation objingElt) {
        objingElt.setIsAbstract(((org.eclipse.uml2.uml.BehavioralFeature)getEcoreElement()).isAbstract());
    }

    @objid ("af2bbefc-45d0-4610-8466-33b6cb3d1c10")
    private void setFinal(Operation objingElt) {
        objingElt.setFinal(((org.eclipse.uml2.uml.BehavioralFeature)getEcoreElement()).isLeaf());
    }

    @objid ("29e01225-b282-4ffc-967c-e1596e0992fa")
    private void setIsClass(Operation objingElt) {
        objingElt.setIsClass(((org.eclipse.uml2.uml.BehavioralFeature)getEcoreElement()).isStatic());
    }

    @objid ("6844329a-a489-4100-8cf0-5e48a06119ad")
    private void setConcurrency(Operation objingElt) {
        // Since concurrency is represented as a boolean in Modelio, the mapping
        // is true for the
        // CONCURRENT value, and false else (SEQUENTIAL by default):
        org.eclipse.uml2.uml. BehavioralFeature ecoreElement = (org.eclipse.uml2.uml.BehavioralFeature) getEcoreElement();
        switch (ecoreElement.getConcurrency().getValue()) {
        case org.eclipse.uml2.uml.CallConcurrencyKind.CONCURRENT:
            objingElt.setConcurrency(true);
            break;
        default:
            objingElt.setConcurrency(false);
        }
        
    }

    @objid ("3292b3f6-f0e1-45c7-b450-b93f12b40c01")
    private void setMethod(Operation objingElt) {
        org.eclipse.uml2.uml. BehavioralFeature ecoreElement = (org.eclipse.uml2.uml.BehavioralFeature) getEcoreElement();
        
        for (org.eclipse.uml2.uml.Behavior behavior : ecoreElement.getMethods()){
            
            ReverseProperties revProp = ReverseProperties.getInstance();
            
            if (behavior.getOwner() instanceof  org.eclipse.uml2.uml.Transition){
                
                Object temp = revProp.getMappedElement(behavior.getOwner());
                if (temp instanceof Transition){
                    ((Transition) temp).setProcessed(objingElt);
                }
            }else{
                
                Object obBehavior =  revProp.getMappedElement(behavior);                
                if ((obBehavior != null) && (obBehavior instanceof ModelElement)) {                    
                    Dependency dependency = UML2MethodReference.create().getElement();                    
                    dependency.setDependsOn((ModelElement)obBehavior);
                    dependency.setImpacted(objingElt);
                }
            }
        }
        
    }

}

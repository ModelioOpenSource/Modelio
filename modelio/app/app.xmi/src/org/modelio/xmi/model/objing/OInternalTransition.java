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
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This classe manages the export of InternalTransition
 * @author ebrosse
 */
@objid ("8a8f2b8c-c363-4f84-b418-d76134d7679c")
public class OInternalTransition extends OTransition {
    @objid ("62ebbc0f-52ec-452c-93fd-46a6fb8b42dd")
    private org.eclipse.uml2.uml.OpaqueBehavior ecoreOpaqueBehavior = null;

    @objid ("c92e497a-3025-42cc-ad6a-8961d599b30a")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        setEffect();
        setProcessed();
        setBehavior();
        setAdditionalValues();
        return null;
    }

    /**
     * Constructor
     * 
     * @param param : the exported Modelio InternalTransition
     */
    @objid ("41f3b24d-7c7c-4413-a036-950a3bd04db4")
    public OInternalTransition(final InternalTransition param) {
        super(param);
    }

    @objid ("dcc1a3c5-282c-4bc9-920a-16462ed28162")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt != null){
            super.setProperties(ecoreElt);
        }
    }

    @objid ("88e89445-3ad9-4be4-a38f-790ffb31fecc")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt != null)
            super.setProperties(ecoreElt);
    }

    @objid ("6fdf4950-ba0c-4828-833b-c5223ac39024")
    private void setBehavior() {
        InternalTransition objingElement = (InternalTransition) getObjingElement();
        
        String received = objingElement.getReceivedEvents();
        
        org.eclipse.uml2.uml.State ecoreState = (org.eclipse.uml2.uml.State) GenerationProperties.getInstance().getMappedElement(objingElement.getSComposed());
        
        if (received != null) {
            if  (received.equals("Entry")){
                ecoreState.setEntry(this.ecoreOpaqueBehavior);               
            }else if  (received.equals("Exit")){
                ecoreState.setExit(this.ecoreOpaqueBehavior);
            }else {
                ecoreState.setDoActivity(this.ecoreOpaqueBehavior);
            }
        }else{
            this.ecoreOpaqueBehavior.destroy();
        }
    }

    @objid ("3a7d26d8-1237-4082-8aed-01f7b2ee6695")
    private void setAdditionalValues() {
        InternalTransition internalTransition = (InternalTransition) getObjingElement();
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        if (this.ecoreOpaqueBehavior != null){
            if (genProp.isRoundtripEnabled()){
                if (internalTransition.getEffect() != null)
                    ObjingEAnnotation.setEffect(this.ecoreOpaqueBehavior, internalTransition.getEffect());
        
                if ((internalTransition.getPostCondition() != null) && (genProp.isRoundtripEnabled()))
                    ObjingEAnnotation.setPostCondition(this.ecoreOpaqueBehavior, internalTransition.getPostCondition());
            }
        }else{
            String message = Xmi.I18N.getMessage("logFile.warning.export.internalTransitionHaveNotBehavior", 
                    internalTransition.getReceivedEvents(), internalTransition.getSComposed().getName());
            genProp.addWarning(message, internalTransition);
        }
    }

    @objid ("cc7890e6-aaa2-4cbf-aa73-fe19d34fca80")
    private void setEffect() {
        InternalTransition transition = ((InternalTransition) this.getObjingElement());
        String effect = transition.getEffect();
        if ((effect != null) && (!effect.equals(""))){
            // String Case
            this.ecoreOpaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
            this.ecoreOpaqueBehavior.getBodies().add(effect);
        }else{
        
            //Behavior Case
        
            Behavior behavior = transition.getBehaviorEffect();
            if (behavior != null){
                org.eclipse.uml2.uml.Element ecoreBehavior = GenerationProperties.getInstance().getMappedElement(behavior);
        
                if ((ecoreBehavior != null) && (ecoreBehavior instanceof org.eclipse.uml2.uml. Behavior)){
        
                    this.ecoreOpaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
                    this.ecoreOpaqueBehavior.getRedefinedBehaviors().add((org.eclipse.uml2.uml.Behavior)ecoreBehavior);
                }
        
            }
        }
    }

    @objid ("d30f7a3f-e59e-4f6d-8944-90986c8284ac")
    private void setProcessed() {
        InternalTransition transition = ((InternalTransition) this.getObjingElement());
        Operation objingOperation = transition.getProcessed();
        
        if (objingOperation != null) {
            Object effect = GenerationProperties.getInstance().getMappedElement(objingOperation);
            if (effect instanceof org.eclipse.uml2.uml. BehavioralFeature){                
                this.ecoreOpaqueBehavior = UMLFactory.eINSTANCE.createOpaqueBehavior();                
                this.ecoreOpaqueBehavior.setSpecification((org.eclipse.uml2.uml.BehavioralFeature) effect);
            }
        }
    }

}

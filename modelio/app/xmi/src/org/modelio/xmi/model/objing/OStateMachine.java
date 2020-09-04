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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of StateMachine
 * @author ebrosse
 */
@objid ("813c71a3-c449-45cf-84fa-6a4b17617398")
public class OStateMachine extends OModelElement {
    @objid ("cc15c9dd-cded-4bec-8537-9def666ff10e")
     boolean isProtocol = false;

    @objid ("62baa6b4-a0a7-4fec-be98-7973931d8717")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("96edbc28-a02b-4f7a-afa2-306e74ce0f0e")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (this.isProtocol)
            return UMLFactory.eINSTANCE.createProtocolStateMachine();
        return UMLFactory.eINSTANCE.createStateMachine();
    }

    /**
     * Constructor
     * @param element : the exported Modelio StateMachine
     */
    @objid ("541932f3-9cb0-4aa3-bf9d-d8d821bab04b")
    public OStateMachine(final StateMachine element) {
        super(element);
        if (AbstractObjingModelNavigation.isProtocolStateMachine(element))
            this.isProtocol = true;
        else
            this.isProtocol = false;
    }

    @objid ("3fa12d98-3502-4457-96b2-0319425f528c")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        ModelElement objingOwner = getObjingElement().getOwner();
        if (objingOwner == null) {
            Operation op = getObjingElement().getOwnerOperation();
            objingOwner = op.getOwner();
            String message =  Xmi.I18N.getMessage("logFile.warning.moving.stateMachine",
                                                  getObjingElement().getName(), op.getName(), objingOwner
                                                  .getName());
            GenerationProperties.getInstance().addWarning(message, getObjingElement());
        }
        org.eclipse.uml2.uml.Element ecoreOwner = this.genProp.getMappedElement(objingOwner);
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof Package) {
                Package ownerIsPkg = (Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add((PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof Component) {
                Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add((PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof Class) {
                Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                ownerIsClass.getOwnedBehaviors().add((org.eclipse.uml2.uml.Behavior)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                org.eclipse.uml2.uml.Interface ownerIsItf = (org.eclipse.uml2.uml.Interface) ecoreOwner;
                ownerIsItf.getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml. BehavioredClassifier) {
                org.eclipse.uml2.uml. BehavioredClassifier ownerIsBehavioredClassifier = (org.eclipse.uml2.uml.BehavioredClassifier) ecoreOwner;
                ownerIsBehavioredClassifier.getOwnedBehaviors().add((org.eclipse.uml2.uml.Behavior)ecoreElt);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
    }

    @objid ("95205158-b040-4f9b-8ee6-3162198f8936")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        if (this.genProp.isRoundtripEnabled())
            setReentrant((org.eclipse.uml2.uml.StateMachine) ecoreElt);
    }

    @objid ("414f7d1a-c63c-446d-b1a4-83dce2e5802e")
    private void setReentrant(org.eclipse.uml2.uml.StateMachine ecoreElt) {
        ObjingEAnnotation.setIsReentrant(ecoreElt, getObjingElement().isIsReentrant());
    }

    @objid ("b11e5a4e-6cc6-4a89-b2c8-9f2e3f13a386")
    @Override
    public StateMachine getObjingElement() {
        return (StateMachine) super.getObjingElement();
    }

}

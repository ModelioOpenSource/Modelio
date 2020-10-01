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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * Export Constraint
 * @author ebrosse
 */
@objid ("6d880c0f-a06c-43dc-91ba-e196e796e663")
public class OConstraint extends OModelElement {
    @objid ("14724b00-156d-46ea-ad8d-a79ca2f7bf4d")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, "DurationObservation_Constraint")){
            return UMLFactory.eINSTANCE.createDurationObservation();
        }
        return UMLFactory.eINSTANCE.createConstraint();
    }

    @objid ("ee156bcb-35da-4c73-a301-a7418f297e00")
    public OConstraint(Constraint element) {
        super(element);
    }

    @objid ("569ce9b9-f093-494b-a234-88b1177de4c0")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.Constraint){
            //Constraint case
            // Exceptionally sets the Properties of the org.eclipse.uml2.uml.Constraint, as we need to
            // know the Name and Body of the
            // current constraint.
            setProperties(ecoreElt);
        
            // Gets the ecore constrained elements:
            List <org.eclipse.uml2.uml.Element> ecoreConstrainedElts = new ArrayList <>();
            boolean isMappingPossible = true;
            List<UmlModelElement> objingConstrainedElts = getObjingElement()
                    .getConstrainedElement();
            for (UmlModelElement objingConstrainedElt : objingConstrainedElts) {
                org.eclipse.uml2.uml.Element ecoreConstrainedElt = genProp
                        .getMappedElement(objingConstrainedElt);
                if (ecoreConstrainedElt == null)
                    return;// The constraint will be linked later: all the
                // constrained elements must have been
                // created.
        
                if (!(ecoreConstrainedElt instanceof org.eclipse.uml2.uml.Namespace))
                    isMappingPossible = false;
                ecoreConstrainedElts.add(ecoreConstrainedElt);
            }
        
            if (isMappingPossible) {
        
                for (org.eclipse.uml2.uml.Element ecoreConstrainedElt : ecoreConstrainedElts) {
                    org.eclipse.uml2.uml.Namespace eConstrainedElt = (org.eclipse.uml2.uml.Namespace) ecoreConstrainedElt;
                    if (!eConstrainedElt.getOwnedRules().contains(ecoreElt))
                        eConstrainedElt.getOwnedRules().add((org.eclipse.uml2.uml.Constraint)ecoreElt);
                }
                ((org.eclipse.uml2.uml.Constraint) ecoreElt).getConstrainedElements().addAll(
                                                                        ecoreConstrainedElts);
                setContext((org.eclipse.uml2.uml.Constraint) ecoreElt);
        
            } else {
                for (org.eclipse.uml2.uml.Element ecoreConstrainedElt : ecoreConstrainedElts) {
                    EAnnotation objingEA = ObjingEAnnotation
                            .getOrCreateObjingEAnnotation(ecoreConstrainedElt);
                    if ((objingEA != null)
                            && (!ObjingEAnnotation.existsConstraintInEA(objingEA,
                                                                        (org.eclipse.uml2.uml.Constraint) ecoreElt))) {
                        objingEA.getContents().add(ecoreElt);
                    }
                }
                ((org.eclipse.uml2.uml.Constraint) ecoreElt).getConstrainedElements().addAll(
                                                                        ecoreConstrainedElts);
        
            }
        }else{
            if (getObjingElement().getConstrainedElement().size() == 2 ){
                //Duration Observation
                UmlModelElement constrainedElt = getObjingElement().getConstrainedElement().get(0);
                org.eclipse.uml2.uml.Element ecoreOwner = genProp
                        .getMappedElement(AbstractObjingModelNavigation.getNearestPackage(constrainedElt));
                if (ecoreOwner instanceof Package){
                    org.eclipse.uml2.uml.DurationObservation durObs = (org.eclipse.uml2.uml.DurationObservation) ecoreElt;
                    ((org.eclipse.uml2.uml.Package) ecoreOwner).getPackagedElements().add(durObs);
                    durObs.getEvents().add((org.eclipse.uml2.uml.NamedElement) genProp
                                           .getMappedElement(getObjingElement().getConstrainedElement().get(0)) );
                    durObs.getEvents().add((org.eclipse.uml2.uml.NamedElement) genProp
                                           .getMappedElement(getObjingElement().getConstrainedElement().get(1)) );
                    durObs.getFirstEvents().add(true);
                    durObs.getFirstEvents().add(false);
                }else{
                    ecoreElt.destroy();
                }
            }
        }
    }

    @objid ("c83a2cbc-a9e1-4828-a770-0c84577e2348")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt instanceof org.eclipse.uml2.uml.Constraint){
            super.setProperties(ecoreElt);
            setSpecification((org.eclipse.uml2.uml.Constraint) ecoreElt);
            setContext((org.eclipse.uml2.uml.Constraint) ecoreElt);
        }else{
            setName((org.eclipse.uml2.uml.DurationObservation) ecoreElt);
        }
    }

    @objid ("a7c3049c-a0b4-4954-880e-c24e97710756")
    private void setSpecification(org.eclipse.uml2.uml.Constraint ecoreElt) {
        org.eclipse.uml2.uml.LiteralString valueSpecification = UMLFactory.eINSTANCE
                .createLiteralString();
        valueSpecification.setValue(getObjingElement().getBody());
        ecoreElt.setSpecification(valueSpecification);
    }

    @objid ("e5fcfe58-4550-407d-b2b0-fe5a2bc71214")
    private void setContext(org.eclipse.uml2.uml.Constraint constraint) {
        org.eclipse.uml2.uml.Model ecoreModel = GenerationProperties.getInstance().getEcoreModel();
        constraint.setContext(ecoreModel);
    }

    @objid ("ca1828ba-3ad2-4ca1-b15e-317b60c7357b")
    private void setName(final org.eclipse.uml2.uml.DurationObservation ecoreElt) {
        String name = getObjingElement().getName();
        
        if ((name != null) &&  (!name.equals(""))){
            ecoreElt.setName(name);
        }
    }

    @objid ("9f6f768c-e405-4179-86a0-aa85c63e9f32")
    @Override
    public Constraint getObjingElement() {
        return (Constraint) super.getObjingElement();
    }

}

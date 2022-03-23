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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("35ec879d-0286-4736-9b8a-98715696988f")
public class OComponentRealization extends OModelElement {
    @objid ("707fac9f-2aab-47e1-9dd4-0a40565f2e11")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE
                                        .createComponentRealization();
        
    }

    @objid ("19f86469-22e0-4cba-8358-94250fa18a32")
    public  OComponentRealization(ComponentRealization element) {
        super(element);
    }

    @objid ("3180bd21-a665-4c1b-aa7e-7744ad55127e")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        Component objingComp = getObjingElement().getAbstraction();
        Classifier objingClass = getObjingElement().getRealizingClassifier();
        
        if ((objingComp != null) && (objingClass != null)) {
            // Gets or creates the ecore implementer org.eclipse.uml2.uml.Namespace:
            org.eclipse.uml2.uml.Element ecoreComp =  genProp
                    .getMappedElement(objingComp);
        
            // Gets or creates the ecore implemented org.eclipse.uml2.uml.Interface:
            org.eclipse.uml2.uml.Element ecoreClass = genProp
                    .getMappedElement(objingClass);
        
            if ((ecoreComp instanceof org.eclipse.uml2.uml.Component)
                    && (ecoreClass instanceof org.eclipse.uml2.uml.Classifier)) {
                org.eclipse.uml2.uml.ComponentRealization ecoreItfReal = (org.eclipse.uml2.uml.ComponentRealization) ecoreElt;
                ecoreItfReal.setAbstraction((org.eclipse.uml2.uml.Component) ecoreComp);
                ecoreItfReal.getRealizingClassifiers().add((org.eclipse.uml2.uml.Classifier) ecoreClass);
            }
        }
        
    }

    @objid ("dccd06d5-719d-4da6-a04d-384454f49ee3")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("4b118c32-67f2-49c1-90f8-3c7b8a9dbc14")
    @Override
    public ComponentRealization getObjingElement() {
        return (ComponentRealization) super.getObjingElement();
    }

}

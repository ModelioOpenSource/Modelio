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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("93749a61-9cef-44d7-b6af-f2ed9835795b")
public class ORaisedException extends OElement implements IOElement {
    @objid ("ab5c64b6-d71d-41f2-8a73-af69141cb13a")
    private RaisedException objingElement;

    @objid ("6e149459-7f8a-4f73-ab5b-2db78eedbf8b")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        // RaisedException is a property directly defined on  org.eclipse.uml2.uml.Operation in UML2.
        // No ecore element is
        // created. The exception is added to the related  org.eclipse.uml2.uml.Operation:
        GenerationProperties genProp = GenerationProperties.getInstance();
                
        Operation objingThrower = this.objingElement.getThrower();
        Classifier objingThrownType = this.objingElement.getThrownType();
         org.eclipse.uml2.uml.Operation ecoreThrower = null;
                
        if (objingThrower != null && objingThrownType != null) {
            // Gets or creates the ecore thrower (the  org.eclipse.uml2.uml.Operation):
            ecoreThrower =  (org.eclipse.uml2.uml.Operation) genProp.getMappedElement(objingThrower);
                
            // Gets or creates the ecore thrown type (the org.eclipse.uml2.uml.Type):
            org.eclipse.uml2.uml.Type ecoreThrownType =  (org.eclipse.uml2.uml.Type) genProp
                    .getMappedElement(objingThrownType);
                
            if (ecoreThrower != null && ecoreThrownType != null) {
                ecoreThrower.getRaisedExceptions().add(ecoreThrownType);
            }
        }
        // Returns the operation: if we return null, if Notes are defined on the
        // RaisedException element, the
        // visitor will produce an infinite loop. In this case, as we return the
        // operation, the Note will be
        // added on the operation (as no model element exists for
        // "RaisedException" in UML2).
        return ecoreThrower;
    }

    @objid ("7db31c13-fab9-4acb-925f-9ed4e37ceb60")
    public  ORaisedException(RaisedException element) {
        super(element);
        this.objingElement = element;
        
    }

    @objid ("7502076a-afd5-484e-8398-adfd3e095e26")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // Nothing to do: RaisedException is a property directly defined on
        //  org.eclipse.uml2.uml.Operation in UML2.
        
    }

    @objid ("b5964ba5-2fda-4501-a2b0-7661a2c6fc3b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // Nothing to do: RaisedException is a property directly defined on
        //  org.eclipse.uml2.uml.Operation in UML2.
        
    }

}

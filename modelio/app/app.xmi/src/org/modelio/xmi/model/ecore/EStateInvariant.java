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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("352e1571-5cc9-4063-8fe8-59dbe83bb299")
public class EStateInvariant extends EInteractionFragment {
    @objid ("f06592e3-8599-48ce-b572-403da12e188f")
    @Override
    public Element createObjingElt() {
        return  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createStateInvariant();
    }

    @objid ("d244bfc4-f348-40aa-a653-bd7271d8e884")
    public  EStateInvariant(org.eclipse.uml2.uml.StateInvariant element) {
        super(element);
    }

    @objid ("2ee5f1ac-e405-4cde-9ac8-aa761389da26")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setBody((StateInvariant) objingElt);
        
    }

    @objid ("cc326fdd-a871-4a0f-967b-dd030f0a6e30")
    private void setBody(StateInvariant invariant) {
        // In UML2, the body of the org.eclipse.uml2.uml.StateInvariant (SI) is stored in a
        // constraint.
        // In Modelio, in import, we won't create this constraint.
        // => If the org.eclipse.uml2.uml.Constraint in objing has already been created, we delete
        // it, and, in
        // the HashMap, we associate the ecore constraint (key) with the objing
        // SI (value).
        
        
        org.eclipse.uml2.uml.Constraint ecoreConstraint = ((org.eclipse.uml2.uml.StateInvariant) getEcoreElement()).getInvariant();
        if(ecoreConstraint != null){
        
             org.eclipse.uml2.uml.ValueSpecification valueSpecif = ecoreConstraint.getSpecification();
            if (valueSpecif != null) {
                String body = valueSpecif.stringValue();
                if (body != null)
                    invariant.setBody(body);
            }
        }
        
    }

    @objid ("8200640d-19bd-4aca-bc24-552ba87ec5fc")
    @Override
    public void attach(Element objingElt) {
        super.attach(objingElt);
    }

}

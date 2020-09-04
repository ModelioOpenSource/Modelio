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
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JunctionPseudoState;

@objid ("be88e164-1334-4a05-96f3-e540c20b84fe")
public class OJunctionPseudoState extends OAbstractPseudoState {
    @objid ("a02632ed-fcb7-4f84-bb38-ef261e8c059f")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPseudostate();
    }

    @objid ("af2f58d3-0e67-46e5-897e-fd5e1d773fc3")
    public OJunctionPseudoState(JunctionPseudoState param) {
        super(param);
    }

    @objid ("945b5f56-6ff9-49ed-add4-07dc51954bb7")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("ef9a23a7-f3ae-4228-9db9-f2733ee7c25d")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setKind( (org.eclipse.uml2.uml.Pseudostate)ecoreElt);
    }

    @objid ("9fb4ed91-9b55-4a5e-9bc6-874dd79e93b6")
    private void setKind(org.eclipse.uml2.uml.Pseudostate pseudostate) {
        pseudostate.setKind (org.eclipse.uml2.uml.PseudostateKind.JUNCTION_LITERAL);
    }

}

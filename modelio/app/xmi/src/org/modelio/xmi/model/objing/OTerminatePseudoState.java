/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.uml.behavior.stateMachineModel.TerminatePseudoState;

@objid ("d6c9b314-5c70-48a8-ba60-146270082e87")
public class OTerminatePseudoState extends OAbstractPseudoState {
    @objid ("d368fcff-9a66-4013-ac11-7b89be1a13d7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPseudostate();
    }

    @objid ("eac418f0-1ed5-468a-9999-dbba4615489c")
    public OTerminatePseudoState(TerminatePseudoState param) {
        super(param);
    }

    @objid ("bf8ac545-1570-46b7-86c5-91d8f0a294e8")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("836512bd-9bb2-4310-8988-8d81e4d56035")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
                setKind( (org.eclipse.uml2.uml.Pseudostate)ecoreElt);
    }

    @objid ("e849755e-c6bc-472a-887d-6d8336cbc17c")
    private void setKind(org.eclipse.uml2.uml.Pseudostate pseudostate) {
        pseudostate.setKind (org.eclipse.uml2.uml.PseudostateKind.TERMINATE_LITERAL);
    }

}

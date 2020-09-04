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
import org.modelio.metamodel.uml.behavior.stateMachineModel.ChoicePseudoState;

@objid ("2d51a243-1bbd-47d6-a398-c7d7797a13ee")
public class OChoicePseudoState extends OAbstractPseudoState {
    @objid ("31c4ef87-343d-4e51-84a7-8f58d8bcb41b")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPseudostate();
    }

    @objid ("b7a200ab-8596-43b7-bb2a-54f8971f62e1")
    public OChoicePseudoState(ChoicePseudoState param) {
        super(param);
    }

    @objid ("bf6d660e-901e-4e42-872d-e51b78b7fc34")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("e89a102e-f926-417f-8024-dcc8815b7f70")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setKind( (org.eclipse.uml2.uml.Pseudostate)ecoreElt);
    }

    @objid ("71403799-9a65-4dfc-a2c1-e5def594e727")
    private void setKind(org.eclipse.uml2.uml.Pseudostate pseudostate) {
        pseudostate.setKind (org.eclipse.uml2.uml.PseudostateKind.CHOICE_LITERAL);
    }

}

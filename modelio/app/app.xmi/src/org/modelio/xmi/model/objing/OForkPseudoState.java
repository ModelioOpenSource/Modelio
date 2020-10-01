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
import org.modelio.metamodel.uml.behavior.stateMachineModel.ForkPseudoState;

@objid ("28c73dfb-eb99-4a7b-a89c-0707e4e1ba41")
public class OForkPseudoState extends OAbstractPseudoState {
    @objid ("66bb081f-d585-4a20-8748-c5a96c618c72")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPseudostate();
    }

    @objid ("fb618e67-1942-4ce2-964b-e5726bd0ce6d")
    public OForkPseudoState(ForkPseudoState param) {
        super(param);
    }

    @objid ("81b0308b-5191-4703-9b4b-7ad9321d6bd0")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("c431742d-4d6b-457c-a901-dd123df376d7")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setKind( (org.eclipse.uml2.uml.Pseudostate)ecoreElt);
    }

    @objid ("89c87d49-44d4-4cf6-829e-783ccb6cd0c8")
    private void setKind(org.eclipse.uml2.uml.Pseudostate pseudostate) {
        pseudostate.setKind (org.eclipse.uml2.uml.PseudostateKind.FORK_LITERAL);
    }

}

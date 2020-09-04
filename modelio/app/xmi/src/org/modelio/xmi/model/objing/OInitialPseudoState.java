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
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;

@objid ("458d7e4a-c144-4cdf-b5f0-83a34a3df67e")
public class OInitialPseudoState extends OAbstractPseudoState {
    @objid ("f1373e0e-0b06-4377-965e-d0821cfa3a88")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPseudostate();
    }

    @objid ("25840323-780c-40fb-95dd-2bbcc67322c7")
    public OInitialPseudoState(InitialPseudoState param) {
        super(param);
    }

    @objid ("98c641fa-de38-43ad-a95c-9b88bbc7fa45")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("b77ea504-4c41-4638-a3ff-93338922a24c")
    private void setKind(org.eclipse.uml2.uml.Pseudostate pseudostate) {
        pseudostate.setKind (org.eclipse.uml2.uml.PseudostateKind.INITIAL_LITERAL);
    }

    @objid ("6ea35c4b-2dd2-4a84-8952-36b793afe00c")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setKind( (org.eclipse.uml2.uml.Pseudostate)ecoreElt);
    }

}

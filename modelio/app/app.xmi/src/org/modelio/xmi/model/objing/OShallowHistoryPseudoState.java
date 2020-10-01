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
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;

@objid ("ca39db1b-d5bd-45c7-bc37-a75f7c1681e2")
public class OShallowHistoryPseudoState extends OAbstractPseudoState {
    @objid ("3f14ab77-0433-47b6-bc7d-f90f41673075")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPseudostate();
    }

    @objid ("bdd3e2d5-503a-4351-9a0b-bebe164eae69")
    public OShallowHistoryPseudoState(ShallowHistoryPseudoState param) {
        super(param);
    }

    @objid ("302bda80-0608-4a9f-ad6b-5295c56d680c")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("4462e387-9e9f-4286-ad48-65c2e6974297")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
                setKind( (org.eclipse.uml2.uml.Pseudostate)ecoreElt);
    }

    @objid ("bb7de353-1dcd-4a62-8b91-e9ca01de01f2")
    private void setKind(org.eclipse.uml2.uml.Pseudostate pseudostate) {
        pseudostate.setKind (org.eclipse.uml2.uml.PseudostateKind.SHALLOW_HISTORY_LITERAL);
    }

}

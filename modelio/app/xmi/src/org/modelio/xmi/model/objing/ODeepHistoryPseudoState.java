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
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;

@objid ("1e9b80d7-3585-4211-903a-53b72e9d3567")
public class ODeepHistoryPseudoState extends OAbstractPseudoState {
    @objid ("5005db30-790f-44c0-85d5-627b08541fd7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPseudostate();
    }

    @objid ("5dd72bc6-c434-448b-8a6f-59f182d90283")
    public ODeepHistoryPseudoState(DeepHistoryPseudoState param) {
        super(param);
    }

    @objid ("d5143adf-bfce-4235-9671-d92adea72ca5")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("7c796ef2-e66b-4ef5-bbcd-544bbd151524")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setKind( (org.eclipse.uml2.uml.Pseudostate)ecoreElt);
    }

    @objid ("01a2e544-65a8-4d56-83a4-73224ea15972")
    private void setKind(org.eclipse.uml2.uml.Pseudostate pseudostate) {
        pseudostate.setKind (org.eclipse.uml2.uml.PseudostateKind.DEEP_HISTORY_LITERAL);
    }

}

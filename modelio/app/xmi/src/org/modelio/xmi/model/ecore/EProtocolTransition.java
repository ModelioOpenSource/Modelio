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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("4932eaa7-ea5a-4c43-a5e6-a34c3a3dae6b")
public class EProtocolTransition extends ETransition {
    @objid ("ac5ea5a5-9e98-4197-aeb5-9ee1c8a080f9")
    private boolean isInternalTransition = false;

    @objid ("7a1c82c5-e5ea-4820-9eb7-e89af3129541")
    private org.eclipse.uml2.uml.ProtocolTransition ecoreElement = null;

    @objid ("35122a87-7ba3-4229-8888-aff118d4da0e")
    @Override
    public Element createObjingElt() {
        if (this.isInternalTransition)
            return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInternalTransition();
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createTransition();
    }

    @objid ("5fed398d-4ddf-4256-8a78-306e5be46cb1")
    public EProtocolTransition(org.eclipse.uml2.uml.ProtocolTransition element) {
        super(element);
        this.ecoreElement = element;
        this.isInternalTransition = EcoreModelNavigation
        .isInternalTransition(element);
    }

    @objid ("cfd0c66c-1d13-4ee6-868d-70e561509540")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setProcessed((Transition) objingElt);
    }

    @objid ("d7579e1e-fe82-4d54-a98d-c4f7f7e1e055")
    private void setProcessed(Transition transition) {
        EList<?> referreds = this.ecoreElement.getReferreds();
        if (referreds.size() > 0) {
             org.eclipse.uml2.uml.Operation ecoreOp =  (org.eclipse.uml2.uml.Operation) referreds.get(0);
            Object objingOp = ReverseProperties.getInstance().getMappedElement(ecoreOp);
            if (objingOp instanceof Operation) {
                transition.setProcessed((Operation) objingOp);
            }
        }
    }

}

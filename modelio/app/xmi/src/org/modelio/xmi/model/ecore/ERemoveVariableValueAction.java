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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2RemoveVariableValueAction;

@objid ("1e9b0044-1ffa-4ae6-8ad5-f353e0380458")
public class ERemoveVariableValueAction extends EActivityNode {
    @objid ("297fc270-7052-4d1b-bc66-6bdfbdeb89da")
    @Override
    public Element createObjingElt() {
        return UML2RemoveVariableValueAction.create().getElement();
    }

    @objid ("bd4146b6-3877-4ae0-b866-bd6c0029c05e")
    public ERemoveVariableValueAction(org.eclipse.uml2.uml.RemoveVariableValueAction element) {
        super(element);
    }

}

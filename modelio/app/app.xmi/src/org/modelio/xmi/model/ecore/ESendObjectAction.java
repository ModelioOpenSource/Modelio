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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2SendObjectAction;

@objid ("45f1dfc9-c45b-419e-808c-afbdb2671c8e")
public class ESendObjectAction extends EActivityNode {
    @objid ("72e8ea0f-183a-4f02-8d04-f4bb852b269a")
    @Override
    public Element createObjingElt() {
        return UML2SendObjectAction.create().getElement();
    }

    @objid ("73ac79e2-2819-4720-948b-435d74acff0d")
    public  ESendObjectAction(org.eclipse.uml2.uml.SendObjectAction element) {
        super(element);
    }

}

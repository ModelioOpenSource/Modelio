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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2StartObjectBehaviorAction;

@objid ("47705f04-3d8e-4352-aadd-824ddde8a6d3")
public class EStartObjectBehaviorAction extends EActivityNode {
    @objid ("7b7307c0-5e21-45a1-9c03-1a6e2c2d098f")
    @Override
    public Element createObjingElt() {
        return UML2StartObjectBehaviorAction.create().getElement();
    }

    @objid ("dcca950a-7bda-48e1-965b-a7c4c647b042")
    public  EStartObjectBehaviorAction(org.eclipse.uml2.uml.StartObjectBehaviorAction element) {
        super(element);
    }

}

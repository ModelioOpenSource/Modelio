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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadSelfAction;

@objid ("969a1246-f194-4152-b59b-4f06a39c5fae")
public class EReadSelfAction extends EActivityNode {
    @objid ("4e585e47-5e41-4e82-9969-806df765e489")
    @Override
    public Element createObjingElt() {
        return UML2ReadSelfAction.create().getElement();
    }

    @objid ("81fc2d5f-c8ac-4f90-af3d-d00b9ce6f50a")
    public  EReadSelfAction(org.eclipse.uml2.uml.ReadSelfAction element) {
        super(element);
    }

}

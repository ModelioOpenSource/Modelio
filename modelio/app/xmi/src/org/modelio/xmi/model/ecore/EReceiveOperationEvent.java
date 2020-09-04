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

@objid ("6f5122ba-f533-42da-8ae9-9d01955ab2d9")
public class EReceiveOperationEvent extends ENamedElement {
    @objid ("ed28cfb6-e6d1-47f1-8568-cd1c6241b51f")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("98ce92cb-c5c3-4f78-ad87-0c8d39739407")
    public EReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent element) {
        super(element);
    }

}

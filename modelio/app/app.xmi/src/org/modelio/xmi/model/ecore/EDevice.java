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
import org.modelio.module.modelermodule.api.xmi.standard.node.UML2Device;

@objid ("03366215-ea9b-462c-a7ec-a89046809bef")
public class EDevice extends ENode {
    @objid ("eb9f06d9-12b3-485e-919a-0ca9164ef114")
    @Override
    public Element createObjingElt() {
        return UML2Device.create().getElement();
    }

    @objid ("224f9f85-0e96-4871-9f98-b38a9034ee90")
    public  EDevice(org.eclipse.uml2.uml.Device element) {
        super(element);
    }

}

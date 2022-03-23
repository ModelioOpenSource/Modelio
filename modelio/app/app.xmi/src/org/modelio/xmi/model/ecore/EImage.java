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

@objid ("00ec6b3d-33b6-4a99-ad7b-38c8731f1637")
public class EImage extends EElement {
    @objid ("75429dd2-1853-4a3d-8f9d-bf4d432e6866")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("441589e7-69f3-4876-b783-dc3a0cfeebb2")
    public  EImage(org.eclipse.uml2.uml.Image element) {
        super(element);
    }

}

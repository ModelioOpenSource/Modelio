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

@objid ("dc0f6b4b-ffc2-482a-a354-dff2b0a2dbf0")
public class ELiteralString extends ENamedElement {
    @objid ("23634682-cf32-467e-8f26-5bfa96f2401e")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("1495dbe6-f7a7-4c16-aa74-becb950a3a61")
    public  ELiteralString(org.eclipse.uml2.uml.LiteralString element) {
        super(element);
    }

}

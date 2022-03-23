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

@objid ("765a5953-0e9a-4b47-b317-c01b51f25f96")
public class ELiteralUnlimitedNatural extends ENamedElement {
    @objid ("ea6052a4-f8a1-451e-a607-18344eb4c8c5")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("b8fec22c-1624-4a3c-8b7c-20af5baf5b5f")
    public  ELiteralUnlimitedNatural(org.eclipse.uml2.uml.LiteralUnlimitedNatural element) {
        super(element);
    }

}

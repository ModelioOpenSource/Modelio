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

@objid ("5c93f6ee-e839-4cc2-8c9f-d62bb45f8494")
public class ELiteralNull extends ENamedElement {
    @objid ("3525974a-f347-47f1-b6d7-e67b97c72c02")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("86592c49-752b-439c-acf6-169da8f02f5e")
    public  ELiteralNull(org.eclipse.uml2.uml.LiteralNull element) {
        super(element);
    }

}

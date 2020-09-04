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

@objid ("81b99576-20c6-4fa2-b220-d8b7d1846663")
public class ELinkEndDestructionData extends EElement {
    @objid ("b4fdf022-fd40-42b0-ad03-d15607b2a014")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("396d5c9d-a567-4e36-bc02-9e0af81ba084")
    public ELinkEndDestructionData(org.eclipse.uml2.uml.LinkEndDestructionData element) {
        super(element);
    }

}

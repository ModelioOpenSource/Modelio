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

@objid ("4c40c22c-b529-4567-8754-162477e59189")
public class EGeneralOrdering extends ENamedElement {
    @objid ("e83fbfb2-6712-4bf4-ac88-83c85e7da817")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("459b5623-4e3c-4e10-9468-e756b6ff3d0a")
    public EGeneralOrdering(org.eclipse.uml2.uml.GeneralOrdering element) {
        super(element);
    }

}

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

@objid ("b9ee5d4e-4ba4-42ba-9a71-3cb240066cb1")
public class EQualifierValue extends EElement {
    @objid ("5012ec3f-ad1d-4657-806d-ebf05801e26f")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("c28bb672-57a8-4e09-bb62-e378f8329ed5")
    public EQualifierValue(org.eclipse.uml2.uml.QualifierValue element) {
        super(element);
    }

}

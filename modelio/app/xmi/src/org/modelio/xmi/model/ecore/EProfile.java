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
import org.eclipse.uml2.uml.Profile;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.util.ProfileUtils;

@objid ("6848c7be-39c7-4e47-8d27-bee0ca0d6ba1")
public class EProfile extends ENamedElement {
    @objid ("32b275b9-fb86-4c32-b5f8-18ba4ffbdb7c")
    @Override
    public Element createObjingElt() {
        return ProfileUtils.createObjProfile((Profile) getEcoreElement());
    }

    @objid ("f0384560-15e6-4c5f-b1fb-5b1ba51b276e")
    public EProfile(Profile element) {
        super(element);
    }

}

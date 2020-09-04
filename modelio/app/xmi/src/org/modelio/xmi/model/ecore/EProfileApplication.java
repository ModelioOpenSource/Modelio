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

@objid ("cba3ddec-250c-4c34-bb14-94bd12f37b0f")
public class EProfileApplication extends EElement {
    @objid ("3b270d9c-86e6-4f84-be85-7394c69b6d73")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("54fcfe92-7932-4155-bfc5-7ef03747463c")
    public EProfileApplication(org.eclipse.uml2.uml.ProfileApplication element) {
        super(element);
    }

}

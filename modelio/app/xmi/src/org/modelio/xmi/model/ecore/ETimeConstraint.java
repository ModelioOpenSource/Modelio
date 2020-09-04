/* 
 * Copyright 2013-2018 Modeliosoft
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

@objid ("fd7d1cb5-5b58-4841-83eb-d44dae691546")
public class ETimeConstraint extends ENamedElement {
    @objid ("c3c31f82-5e86-4a7f-8ca8-ed123e8872e3")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("b8d4a559-6527-43a3-b091-209588644301")
    public ETimeConstraint(org.eclipse.uml2.uml.TimeConstraint element) {
        super(element);
    }

}

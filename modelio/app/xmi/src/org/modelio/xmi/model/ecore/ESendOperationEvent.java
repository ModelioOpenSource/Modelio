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

@objid ("5ead38eb-0821-4992-bb8a-5d1bb8dd96c5")
public class ESendOperationEvent extends ENamedElement {
    @objid ("5b48f3d8-0cb5-46f7-8b72-d9b95a7120bb")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("e969a213-a4b5-48be-9037-25e3198aa21d")
    public ESendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent element) {
        super(element);
    }

}

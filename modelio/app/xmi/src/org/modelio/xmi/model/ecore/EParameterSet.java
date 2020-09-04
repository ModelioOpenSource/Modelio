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

@objid ("acdf7900-5519-4b57-88d4-0720c3d2aa12")
public class EParameterSet extends ENamedElement {
    @objid ("81a6ebff-bd2f-485f-9f99-735963fc8e33")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("e69bb904-86ba-477f-b80a-0a2b04e639b6")
    public EParameterSet(org.eclipse.uml2.uml.ParameterSet element) {
        super(element);
    }

}

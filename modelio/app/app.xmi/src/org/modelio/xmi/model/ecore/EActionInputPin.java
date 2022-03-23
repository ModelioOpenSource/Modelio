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
import org.modelio.module.modelermodule.api.xmi.standard.inputpin.UML2ActionInputPin;

@objid ("d7d9e743-f6ec-441a-a288-f3cb80f4e356")
public class EActionInputPin extends EInputPin {
    @objid ("80ee03aa-efc3-411f-93e9-52506c52dd8b")
    @Override
    public Element createObjingElt() {
        return UML2ActionInputPin.create().getElement();
    }

    @objid ("4bc54fdc-e73f-49f0-b5a2-595df4252480")
    public  EActionInputPin(org.eclipse.uml2.uml.ActionInputPin element) {
        super(element);
    }

}

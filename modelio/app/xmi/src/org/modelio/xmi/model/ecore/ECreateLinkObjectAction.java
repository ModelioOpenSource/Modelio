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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkObjectAction;

@objid ("6bb6f9d2-4f88-4d23-a1ef-5614949cb2e9")
public class ECreateLinkObjectAction extends EActivityNode {
    @objid ("a3032e39-4f40-453e-bc36-9404d62dbc15")
    @Override
    public Element createObjingElt() {
        return UML2CreateLinkObjectAction.create().getElement();
    }

    @objid ("03cccb1f-e11e-4546-b3a2-b4bf4fbf8336")
    public ECreateLinkObjectAction(org.eclipse.uml2.uml.CreateLinkObjectAction element) {
        super(element);
    }

}

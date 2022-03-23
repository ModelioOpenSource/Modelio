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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReplyAction;

@objid ("0c992afd-ef44-499f-8198-9b919aa48bcb")
public class EReplyAction extends EActivityNode {
    @objid ("5b7a10ce-be9c-4840-bb57-a7f3ebd0a0bd")
    @Override
    public Element createObjingElt() {
        return UML2ReplyAction.create().getElement();
    }

    @objid ("e333994d-379f-4145-ad9f-859533806f19")
    public  EReplyAction(org.eclipse.uml2.uml.ReplyAction element) {
        super(element);
    }

}

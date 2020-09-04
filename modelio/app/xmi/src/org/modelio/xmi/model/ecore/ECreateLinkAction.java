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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2CreateLinkAction;

@objid ("21c137cc-3eda-4255-8637-3227e9524b74")
public class ECreateLinkAction extends EActivityNode {
    @objid ("50d8f1fa-b7b3-43e2-ae22-784176be84a8")
    @Override
    public Element createObjingElt() {
        return UML2CreateLinkAction.create().getElement();
    }

    @objid ("b795bd28-dd1c-489a-aaf8-77f989f63624")
    public ECreateLinkAction(org.eclipse.uml2.uml.CreateLinkAction element) {
        super(element);
    }

}

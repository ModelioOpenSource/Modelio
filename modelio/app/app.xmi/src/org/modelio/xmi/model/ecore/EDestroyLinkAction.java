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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2DestroyLinkAction;

@objid ("f14bde51-d130-425c-a04f-6c3489b249d3")
public class EDestroyLinkAction extends EActivityNode {
    @objid ("c464ccbf-3601-4d74-9119-e1b8ab7a0143")
    @Override
    public Element createObjingElt() {
        return UML2DestroyLinkAction.create().getElement();
    }

    @objid ("ce646271-847e-4326-acd9-60540199f4af")
    public EDestroyLinkAction(org.eclipse.uml2.uml.DestroyLinkAction element) {
        super(element);
    }

}

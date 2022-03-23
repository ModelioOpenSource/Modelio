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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndAction;

@objid ("2c32feb0-a23e-4353-afad-57673c2cabda")
public class EReadLinkObjectEndAction extends EActivityNode {
    @objid ("973ec6da-3818-47a2-afcd-eb248f3298b7")
    @Override
    public Element createObjingElt() {
        return UML2ReadLinkObjectEndAction.create().getElement();
    }

    @objid ("ae076631-d97e-4505-81df-60bf0a015b6d")
    public  EReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction element) {
        super(element);
    }

}

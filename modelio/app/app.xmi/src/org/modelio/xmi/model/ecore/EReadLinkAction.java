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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkAction;

@objid ("0d527267-c2af-44cc-a842-3c87374a7b66")
public class EReadLinkAction extends EActivityNode {
    @objid ("b00be9c9-8d3b-4ef4-b0af-952bd8277468")
    @Override
    public Element createObjingElt() {
        return UML2ReadLinkAction.create().getElement();
    }

    @objid ("3e35910f-11cd-487c-a881-a1d4105136b2")
    public  EReadLinkAction(org.eclipse.uml2.uml.ReadLinkAction element) {
        super(element);
    }

}

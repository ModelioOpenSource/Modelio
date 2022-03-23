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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadVariableAction;

@objid ("bb4cb6e4-e42a-4b67-9e30-c0135632856d")
public class EReadVariableAction extends EActivityNode {
    @objid ("21437213-1d5f-4938-979f-0ed7409643d5")
    @Override
    public Element createObjingElt() {
        return UML2ReadVariableAction.create().getElement();
    }

    @objid ("a206df6e-7969-426c-8146-18663508b993")
    public  EReadVariableAction(org.eclipse.uml2.uml.ReadVariableAction element) {
        super(element);
    }

}

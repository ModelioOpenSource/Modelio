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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2AddVariableValueAction;

@objid ("af72410b-0db5-4e0b-8f78-9d36f87d6e6c")
public class EAddVariableValueAction extends EActivityNode {
    @objid ("17b10b7a-b3d2-426f-8983-4484536d43e1")
    @Override
    public Element createObjingElt() {
        return UML2AddVariableValueAction.create().getElement();
    }

    @objid ("789cc6f8-fdc2-49cd-aed9-9a3843981d8e")
    public EAddVariableValueAction(org.eclipse.uml2.uml.AddVariableValueAction element) {
        super(element);
    }

}

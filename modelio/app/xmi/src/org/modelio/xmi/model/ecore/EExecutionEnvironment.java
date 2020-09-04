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
import org.modelio.module.modelermodule.api.xmi.standard.node.UML2ExecutionEnvironment;

@objid ("5d6b6e99-e4d0-4289-a0b7-4c05cc54872b")
public class EExecutionEnvironment extends ENode {
    @objid ("42203a0f-9cd1-4c7b-8e57-43730f823695")
    @Override
    public Element createObjingElt() {
        return UML2ExecutionEnvironment.create().getElement();
    }

    @objid ("5cf4351b-ef8a-4aec-a884-01e1cd616533")
    public EExecutionEnvironment(org.eclipse.uml2.uml.ExecutionEnvironment element) {
        super(element);
    }

}

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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReduceAction;

@objid ("74b0c82d-80a4-43ae-af77-00fce8dc720f")
public class EReduceAction extends EActivityNode {
    @objid ("ad00c51d-0278-4a6b-8030-2a7202ae4b83")
    @Override
    public Element createObjingElt() {
        return UML2ReduceAction.create().getElement();
    }

    @objid ("2459d98f-f099-4a28-b0e9-b2c5d89b3410")
    public EReduceAction(org.eclipse.uml2.uml.ReduceAction element) {
        super(element);
    }

}

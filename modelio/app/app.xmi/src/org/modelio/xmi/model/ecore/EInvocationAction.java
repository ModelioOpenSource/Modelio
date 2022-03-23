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

@objid ("913d8457-7341-41d4-9838-7c959d618241")
public class EInvocationAction extends EElement {
    @objid ("90db848a-b49e-4161-97eb-fc8d5cce8139")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("7b99eac7-252d-44f1-8f59-1d883b317232")
    public  EInvocationAction(org.eclipse.uml2.uml.Behavior element) {
        super(element);
    }

}

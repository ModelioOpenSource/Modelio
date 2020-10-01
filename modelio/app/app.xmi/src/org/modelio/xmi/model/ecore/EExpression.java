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

@objid ("061b4997-0c94-4e37-af14-3101beadcb55")
public class EExpression extends ENamedElement {
    @objid ("a9a8bc51-24dd-4083-ac36-9e6980e19abe")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("ea2d9c71-c0ec-44b2-908e-e2500eae7ac0")
    public EExpression(org.eclipse.uml2.uml.Expression element) {
        super(element);
    }

}

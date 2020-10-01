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
import org.modelio.xmi.util.ProfileUtils;

@objid ("d85d800e-aa2f-4393-858d-303165d6b6a0")
public class EStereotype extends EElement {
    @objid ("2c9807a5-08df-44db-b1a2-c92e98b519be")
    @Override
    public Element createObjingElt() {
        return  null;
    }

    @objid ("0fff9e13-ee5b-4079-9315-3a6279ce3cb2")
    public EStereotype(org.eclipse.uml2.uml.Stereotype element) {
        super(element);
        ProfileUtils.visitStereotype((org.eclipse.uml2.uml.Stereotype) getEcoreElement());
    }

}

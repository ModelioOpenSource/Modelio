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
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2ProtocolConformance;

@objid ("5b3b64cc-f9ad-4680-9bd2-4d4f9b234224")
public class EProtocolConformance extends EElement {
    @objid ("e5946d3a-8772-4e2f-b7fb-a3a2451793bc")
    @Override
    public Element createObjingElt() {
        return UML2ProtocolConformance.create().getElement();
    }

    @objid ("f4d78669-5c4c-4504-bf76-35c6b9399a3d")
    public EProtocolConformance(org.eclipse.uml2.uml.ProtocolConformance element) {
        super(element);
    }

}

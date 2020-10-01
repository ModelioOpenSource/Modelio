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
import org.modelio.module.modelermodule.api.xmi.standard.templateparameter.UML2ConnectableElementTemplateParameter;

@objid ("1c09ea4a-fa77-4a91-9f92-ef3100b6eec8")
public class EConnectableElementTemplateParameter extends EElement {
    @objid ("f6ba83b1-85e2-470d-aa82-1a58afbddb43")
    @Override
    public Element createObjingElt() {
        return UML2ConnectableElementTemplateParameter.create().getElement();
    }

    @objid ("ea5fc7b4-ab44-4196-8e29-057d5fc401d4")
    public EConnectableElementTemplateParameter(org.eclipse.uml2.uml.ConnectableElementTemplateParameter element) {
        super(element);
    }

}

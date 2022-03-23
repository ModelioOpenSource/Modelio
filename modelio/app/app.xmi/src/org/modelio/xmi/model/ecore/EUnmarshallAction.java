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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2UnmarshallAction;

@objid ("afb8af0e-04f9-420c-82c1-790eb185e9e0")
public class EUnmarshallAction extends EActivityNode {
    @objid ("f8566e9f-32b4-4d3b-98fb-b7fbdd460d34")
    @Override
    public Element createObjingElt() {
        return UML2UnmarshallAction.create().getElement();
    }

    @objid ("58e6860d-e096-4e14-84d1-4886578048bd")
    public  EUnmarshallAction(org.eclipse.uml2.uml.UnmarshallAction element) {
        super(element);
    }

}

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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("348a1066-9a43-42e9-9d79-b452dc1151af")
public class EOpaqueAction extends EActivityNode {
    @objid ("b41b5c09-9a72-474c-8970-89bb12d09d16")
    private org.eclipse.uml2.uml.OpaqueAction ecoreElement = null;

    @objid ("7a767e37-bdae-40ed-98e4-5c083ca20165")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
    }

    @objid ("f4244578-27bb-4633-87cf-8ed91762aef6")
    public EOpaqueAction(org.eclipse.uml2.uml.OpaqueAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("9a9293a9-6d18-445e-9126-cc5488c4c6d5")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setBody((OpaqueAction) objingElt);
    }

    @objid ("fc8be9ed-27e3-4145-bb8d-a3af89e7ff53")
    private void setBody(OpaqueAction action) {
        String bodies = "";
        for (Object body : this.ecoreElement.getBodies()) {
            bodies = bodies.concat((String) body);
        }
        action.setBody(bodies);
    }

}

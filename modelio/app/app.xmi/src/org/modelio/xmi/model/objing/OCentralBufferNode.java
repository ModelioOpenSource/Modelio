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
package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;

@objid ("5f188ad8-8330-4db8-b81f-20e77168ec16")
public class OCentralBufferNode extends OObjectNode {
    @objid ("77e2bd83-a00b-4bdb-8a68-8f9474031d3e")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createCentralBufferNode();
    }

    @objid ("e807f5f0-9e93-44bf-9fe7-6e0d1033721f")
    public  OCentralBufferNode(CentralBufferNode element) {
        super(element);
    }

}

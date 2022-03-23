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

@objid ("df8f2324-43b5-4ff3-aa51-d6c2c9b50cd9")
public class ESendSignalEvent extends ENamedElement {
    @objid ("a0c05b64-7b06-4037-ae4e-7496ce64124b")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("e21dd530-e73d-49eb-a756-38db2a8ffdb5")
    public  ESendSignalEvent(org.eclipse.uml2.uml.SendSignalEvent element) {
        super(element);
    }

}

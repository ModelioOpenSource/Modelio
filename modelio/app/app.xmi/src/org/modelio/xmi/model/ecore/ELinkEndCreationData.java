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

@objid ("8e78f14c-0a3a-40ab-bfbe-fe5b05a56c63")
public class ELinkEndCreationData extends EElement {
    @objid ("da92d173-c501-4586-9e98-5eaef5e163b4")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("a7415e27-46ac-44f4-b7d6-2865cf5a9493")
    public  ELinkEndCreationData(org.eclipse.uml2.uml.LinkEndCreationData element) {
        super(element);
    }

}

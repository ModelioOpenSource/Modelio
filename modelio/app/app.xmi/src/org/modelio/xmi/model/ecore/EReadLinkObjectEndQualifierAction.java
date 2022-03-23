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
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ReadLinkObjectEndQualifierAction;

@objid ("ecc9bde1-42ad-46db-8779-b65b38668b7e")
public class EReadLinkObjectEndQualifierAction extends EActivityNode {
    @objid ("dbf39f58-8599-4b82-ab04-8b11d4c9de6e")
    @Override
    public Element createObjingElt() {
        return UML2ReadLinkObjectEndQualifierAction.create().getElement();
    }

    @objid ("4aa283fb-4508-4958-9da3-8063c201969c")
    public  EReadLinkObjectEndQualifierAction(org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction element) {
        super(element);
    }

}

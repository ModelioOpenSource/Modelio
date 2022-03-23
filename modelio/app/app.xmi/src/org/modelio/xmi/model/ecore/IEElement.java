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

@objid ("ebde9764-3f9f-407a-b316-ca876a96fd89")
public interface IEElement {
    @objid ("8653d529-b1b3-45e4-a88a-002183e6b661")
    Object createObjingElt();

    @objid ("dcc752ea-576a-422b-8979-c21043b3ac3f")
    org.eclipse.uml2.uml.Element getEcoreElement();

    @objid ("089d96dc-6915-4687-92e6-73aa3b29a040")
    void attach(final Element objingElt);

    @objid ("0360ae9b-76d6-4cf5-a515-ea88b341aeeb")
    void setProperties(final Element objingElt);

    @objid ("0b48a653-8cfe-4d0e-9331-4784f17d48b3")
    void setStereotypes();
//    @objid ("47434085-dda3-443b-bede-510a3498c9a3")
    //    void attach(final List<Object> objingElts);
    
}

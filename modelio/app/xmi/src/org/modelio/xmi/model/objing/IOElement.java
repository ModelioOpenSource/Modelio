/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("55731985-a548-4195-aac6-358ce20a891e")
public interface IOElement {
    @objid ("09d2f0d4-96be-4311-9ddf-b567d7e58453")
    String getObjingID();

    @objid ("c068ead5-ba41-4b01-8c4e-7f884843abc1")
    org.eclipse.uml2.uml.Element createEcoreElt();

    @objid ("ecd60d42-96df-469d-8c5d-e1d2f5a67663")
    Element getObjingElement();

    @objid ("bbd73fba-985b-4078-8bad-9d429654664b")
    void attach(org.eclipse.uml2.uml.Element ecoreElt);

    @objid ("703e1d59-3750-4d77-bed9-e5a2384c9f3c")
    void setProperties(org.eclipse.uml2.uml.Element ecoreElt);

}

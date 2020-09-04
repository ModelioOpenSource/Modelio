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
import org.modelio.metamodel.uml.behavior.interactionModel.OccurrenceSpecification;

@objid ("74aca169-6f0f-406d-af5b-a12ee64450be")
public class OOccurrenceSpecification extends OInteractionFragment {
    @objid ("400a03d5-6687-492c-97f9-c005342d4184")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("3d323a97-7223-43d5-9e2a-f5abea969a1b")
    public OOccurrenceSpecification(OccurrenceSpecification param) {
        super(param);
    }

    @objid ("55d514b8-9eea-46ba-80ba-46186aabae2a")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("d49eb71e-7fc8-4121-8cf6-00a8fa0bd77f")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}

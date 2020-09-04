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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("acb1d0bc-17d7-4646-837b-5b138439be36")
public class ELiteralBoolean extends ENamedElement {
    @objid ("cddbb2bd-820e-4ab4-b24d-e816d59659ec")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("60445db8-7cb9-42ed-abcc-1126f9c72181")
    public ELiteralBoolean(org.eclipse.uml2.uml.LiteralBoolean element) {
        super(element);
    }

}

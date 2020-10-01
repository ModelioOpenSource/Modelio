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

@objid ("2cc1192d-542d-49a9-9601-a67eedfad3d8")
public class ETimeExpression extends ENamedElement {
    @objid ("788cd19e-310a-410c-975c-488826be8756")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("1914c4f0-073b-4ca8-9514-cccde3a55374")
    public ETimeExpression(org.eclipse.uml2.uml.TimeExpression element) {
        super(element);
    }

}

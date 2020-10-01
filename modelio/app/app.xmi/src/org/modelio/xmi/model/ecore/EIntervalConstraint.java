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

@objid ("2f8fb650-83c3-4a99-a44a-72ec06b3f927")
public class EIntervalConstraint extends ENamedElement {
    @objid ("81930ad3-a844-476c-bba2-00dc86ea6807")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("220cc290-9ce0-40a8-b1d2-bcdc36d98a7c")
    public EIntervalConstraint(org.eclipse.uml2.uml.IntervalConstraint element) {
        super(element);
    }

}

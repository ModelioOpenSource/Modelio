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

@objid ("a14b08b9-b0ab-4820-95c3-70724cf19931")
public class EStructuredClassifier extends EElement {
    @objid ("a7355b7a-d721-4dbd-9be7-a561d83c3210")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("d48332b4-25b8-4217-9d67-497accdfcd3b")
    public EStructuredClassifier(org.eclipse.uml2.uml.StructuredClassifier element) {
        super(element);
    }

}

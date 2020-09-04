/* 
 * Copyright 2013-2019 Modeliosoft
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

@objid ("7f61d46c-a7b5-496d-9a00-eba0a7a19501")
public class EGeneralizationSet extends ENamedElement {
    @objid ("0b40ce7a-f4f1-4712-9e62-34cd6ada4186")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("42037cf0-4dbf-4e7a-afc5-e01b5eaaea5b")
    public EGeneralizationSet(org.eclipse.uml2.uml.GeneralizationSet element) {
        super(element);
    }

}

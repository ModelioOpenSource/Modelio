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

@objid ("131f1fcb-18fc-4971-8522-c66ff4f3c634")
public class EExtensionEnd extends EFeature {
    @objid ("d5989297-cb52-4722-a592-bc0eb199e4d7")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("121ee62b-a285-4fd0-8f02-d2fa2e676ac6")
    public EExtensionEnd(org.eclipse.uml2.uml.ExtensionEnd element) {
        super(element);
    }

}

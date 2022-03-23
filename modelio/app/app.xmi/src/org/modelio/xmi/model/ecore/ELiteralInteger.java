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

@objid ("d4f0f57e-df8c-454c-b7d4-152b39364410")
public class ELiteralInteger extends ENamedElement {
    @objid ("114a688b-6eac-4f6b-847e-2b4da3c022d6")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("a867e190-9d5f-427c-bfa4-e7596e39c7bf")
    public  ELiteralInteger(org.eclipse.uml2.uml.LiteralInteger element) {
        super(element);
    }

}

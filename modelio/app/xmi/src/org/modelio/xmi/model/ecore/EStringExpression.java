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
import org.eclipse.uml2.uml.StringExpression;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("e5b3bb34-aa0a-4566-9a7b-f788d362fe92")
public class EStringExpression extends ENamedElement {
    @objid ("7f4a5ced-4173-434c-a900-f77f01acdcda")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("2bbdfa44-564c-4d78-863b-9a71db897008")
    public EStringExpression(StringExpression element) {
        super(element);
    }

}

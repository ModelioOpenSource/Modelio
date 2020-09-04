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

package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.xmi.plugin.Xmi;

@objid ("adcd9cda-1a5c-4947-bdb3-87adb6d8916c")
public class MessageUtils {
    @objid ("e9727fec-bc6c-4471-86fe-127b64e20507")
    public static String getEltTypeName(final ModelElement element) {
        return getEltTypeName(element.getName(), element.getMClass().getName());
    }

    @objid ("16d1b0eb-f784-4986-83fe-09a44d31b9e1")
    private static String getEltTypeName(final String name, final String type) {
        if ((name != null ) && (!name.equals(""))) {
            return Xmi.I18N.getMessage("logFile.facility.name",           
                    name,
                    type);
        }else {
            return type;
        }
    }

    @objid ("0c3783c2-9fa1-453d-baa5-ffe538436c43")
    public static String getEltTypeName(final Element element) {
        if (element instanceof NamedElement) {
            return getEltTypeName(((NamedElement)element).getName(), element.getClass().getSimpleName());
        }else {
            return element.getClass().getSimpleName();
        }
    }

}

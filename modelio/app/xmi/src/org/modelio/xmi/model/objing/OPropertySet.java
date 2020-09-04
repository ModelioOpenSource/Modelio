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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("693e920b-8e43-4ec5-8056-ca1ae88f172a")
public class OPropertySet extends OElement implements IOElement {
    @objid ("b6ad536f-e45f-439a-80f6-d955cdb771cb")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        // TODO Auto-generated method stub
        String metaClassName = this.getClass().getSimpleName();
        // String packagingStr = "org.modelio.xmi.model.objing";
        metaClassName = metaClassName.substring(1);
                
        String message = Xmi.I18N.getMessage("logFile.warning.elementNotMapped",
                        metaClassName);
        GenerationProperties.getInstance().addWarning(message, getObjingElement());
        return null;
    }

    @objid ("63ca13e1-52b9-453e-ac5e-378b99abe348")
    public OPropertySet(PropertyTableDefinition param) {
        super(param);
    }

    @objid ("1c236c81-64f8-476a-8f66-28fb58f2da04")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("8abb9e30-1fb0-44d7-8663-ad8bd744d2f4")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

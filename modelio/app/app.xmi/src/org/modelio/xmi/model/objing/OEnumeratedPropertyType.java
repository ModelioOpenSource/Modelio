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

package org.modelio.xmi.model.objing;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("c8a17078-ba74-4b36-9e3d-65602b4366c8")
public class OEnumeratedPropertyType extends OElement implements IOElement {
    @objid ("6ab420b4-f81d-44ec-9906-d447e1068ea7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        // TODO Auto-generated method stub
        String metaClassName = this.getClass().getSimpleName();
        // String packagingStr = "org.modelio.xmi.model.objing";
        metaClassName = metaClassName.substring(1);
                
        String message = Xmi.I18N.getMessage("logFile.warning.elementNotMapped",
                        metaClassName);
        GenerationProperties.getInstance().addInfo(message, getObjingElement());
        return null;
    }

    @objid ("1f9b6cfa-bff4-43cc-b8d7-2e8217fdbb8c")
    public OEnumeratedPropertyType(EnumeratedPropertyType param) {
        super(param);
    }

    @objid ("78c89a58-53f6-450e-b166-b649b4f6ac2c")
    @Override
    public List<String> getEcoreClassName() {
        return new ArrayList<>();
    }

    @objid ("77543dbe-11cc-4ed8-8ba0-3f4c828fc708")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("6a6787c8-89e1-422c-8dc5-e133d1de57e4")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("74e8ac01-d541-4978-bcb9-15cf038ede52")
public class OPropertyValue extends OElement implements IOElement {
    @objid ("1bd10faf-1295-47e6-adba-be7388126d36")
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

    @objid ("01085c7c-d307-423b-b5dd-776090f031c1")
    public  OPropertyValue(PropertyTableDefinition param) {
        super(param);
    }

    @objid ("830163ca-4502-4618-bd49-7782b04e01a1")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("db126578-d451-426f-86ba-9b9498a484a9")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

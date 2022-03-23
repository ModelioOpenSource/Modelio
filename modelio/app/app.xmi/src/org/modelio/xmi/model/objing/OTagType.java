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
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("7a125b0c-5ce6-4f39-8f29-8c67b23ee221")
public class OTagType extends OElement implements IOElement {
    @objid ("9870f88f-8499-4a7f-9ae7-36f615f40248")
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

    @objid ("f033b5d8-878e-446c-8ff8-6999cfcfa614")
    public  OTagType(TagType param) {
        super(param);
    }

    @objid ("f6f6cc20-86ab-446e-af62-f7fef3fd5c8a")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("6e64282e-04cf-46ca-b052-5b40f5b2a336")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

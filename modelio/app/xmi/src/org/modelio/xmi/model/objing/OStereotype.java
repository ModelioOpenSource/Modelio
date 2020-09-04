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
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("65b1419d-a3f0-46b4-bded-2ac17693be92")
public class OStereotype extends OElement implements IOElement {
    @objid ("c2d905f2-1dde-4104-9a7b-9c210531fea1")
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

    @objid ("3bd9d597-4bd5-4fe4-a507-52d0cab50e3c")
    public OStereotype(Stereotype param) {
        super(param);
    }

    @objid ("3386c2ec-a7d8-4f36-8b2d-dc054f7a24d2")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("38ee1cc8-4f0d-44ba-a1c8-ab5a4c642307")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

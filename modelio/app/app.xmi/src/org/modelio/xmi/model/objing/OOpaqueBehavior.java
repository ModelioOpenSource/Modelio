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
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("fba0c236-d1af-4553-adf5-801c7da66bab")
public class OOpaqueBehavior extends OElement implements IOElement {
    @objid ("b10f9cab-053b-426b-91fa-4606777d1df9")
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

    @objid ("79d63fc5-93cf-4c59-84a9-c52cc7d47bb4")
    public  OOpaqueBehavior(OpaqueBehavior param) {
        super(param);
    }

    @objid ("c0858730-0f26-46d0-acd1-6f8485f7fb8f")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("b73a1338-2431-4ae5-8586-d90ddbc186dd")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

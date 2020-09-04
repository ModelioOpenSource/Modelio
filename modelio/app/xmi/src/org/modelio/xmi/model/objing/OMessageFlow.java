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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("af70077e-c1c2-4f90-88aa-13ff43c8861a")
public class OMessageFlow extends OElement implements IOElement {
    @objid ("3d33cffd-d3dd-4d6c-81d3-36ac9a40bcbc")
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

    @objid ("871332e8-2a70-430e-99cb-50ca16e3e5e6")
    public OMessageFlow(MessageFlow param) {
        super(param);
    }

    @objid ("f503ec39-9659-4b43-a2c5-13c23738019d")
    @Override
    public List<String> getEcoreClassName() {
        return new ArrayList<>();
    }

    @objid ("80388f52-b171-4427-9c9f-e311204fe13a")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("665ff17f-b42e-4a80-ac2f-ca5076e77990")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

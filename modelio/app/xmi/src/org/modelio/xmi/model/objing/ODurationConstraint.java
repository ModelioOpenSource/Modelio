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

package org.modelio.xmi.model.objing;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.DurationConstraint;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("5114e9c3-b73c-482f-9f57-3312b88bd196")
public class ODurationConstraint extends OElement implements IOElement {
    @objid ("1473698b-48b0-4bf5-9b3b-2f7415245f07")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        // TODO Auto-generated method stub
        String metaClassName = this.getClass().getSimpleName();
        // String packagingStr = "org.modelio.xmi.model.objing";
        metaClassName = metaClassName.substring(1);
        
        String message = Xmi.I18N.getMessage("logFile.warning.elementNotMapped", metaClassName);
        GenerationProperties.getInstance().addInfo(message, getObjingElement());
        return null;
    }

    @objid ("df6f9067-ecee-4de0-bf45-c0685a9e2930")
    public ODurationConstraint(DurationConstraint param) {
        super(param);
    }

    @objid ("73bdab9b-807b-4991-b99c-b9f808868a76")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("fc149c5d-a8e9-463d-9681-f749cd499211")
    @Override
    public List<String> getEcoreClassName() {
        return new ArrayList<>();
    }

    @objid ("3f7d45e9-65ff-45d1-9b0a-5a4c81f301e8")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

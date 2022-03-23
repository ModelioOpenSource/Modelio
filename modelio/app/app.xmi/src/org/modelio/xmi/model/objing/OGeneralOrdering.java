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
import org.modelio.metamodel.uml.behavior.interactionModel.GeneralOrdering;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;

@objid ("3ed165f2-6612-49f1-acca-9bf843fda33e")
public class OGeneralOrdering extends OElement implements IOElement {
    @objid ("6ac6a45c-dcf7-4e8a-8189-0693448b1716")
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

    @objid ("a90b870a-18c1-46b4-8791-3f621a940fdb")
    public  OGeneralOrdering(GeneralOrdering param) {
        super(param);
    }

    @objid ("6c6455b2-eaef-444c-bdc4-8b46b46558dd")
    @Override
    public List<String> getEcoreClassName() {
        return new ArrayList<>();
    }

    @objid ("dfbd6da2-386e-45df-9cb7-d3693c1e2e69")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("ac452d2f-681c-46e0-9113-91492e3d3a5b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

}

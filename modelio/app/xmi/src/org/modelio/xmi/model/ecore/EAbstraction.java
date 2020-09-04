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

package org.modelio.xmi.model.ecore;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Element;

@objid ("ae9e83c2-86e5-46ea-81c7-cdfbc62759c9")
public class EAbstraction extends EDependency {
    @objid ("008860eb-4962-491c-9a57-9d89acaf91c8")
    @Override
    public List<Element> createObjingElt() {
        return super.createObjingElt();
    }

    @objid ("7b1d772c-f2ac-4739-afe4-c91c20fabc39")
    public EAbstraction(org.eclipse.uml2.uml.Abstraction element) {
        super(element);
    }

    @objid ("22274d67-89dd-4449-b327-0b05970ec8be")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        if (objingElt instanceof Abstraction) {
            setMapping((Abstraction) objingElt);
        }
    }

    @objid ("8a3e5ba3-c827-47c8-9d79-3960070d1fce")
    private void setMapping(Abstraction objingElt) {
        org.eclipse.uml2.uml.OpaqueExpression opaqueExpr = ((org.eclipse.uml2.uml.Abstraction) getEcoreElement()).getMapping();
        
        if (opaqueExpr != null){
            StringBuffer objingMapping = new StringBuffer();
            EList<String> bodies = opaqueExpr.getBodies();
            int i = 0;
            int size = bodies.size();
            for (String body : bodies) {
                i++;
                objingMapping.append(body);
                if (size > 1 && i < size)
                    objingMapping.append("|");
            }
        
            objingElt.setMapping(objingMapping.toString());
        }
    }

}

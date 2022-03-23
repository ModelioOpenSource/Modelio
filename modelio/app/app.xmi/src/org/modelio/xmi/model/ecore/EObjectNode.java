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
package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Type;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("0769d8f2-f5a3-4a07-a709-ae3847873857")
public class EObjectNode extends ENamedElement {
    @objid ("047f8fea-a9e1-4a13-8b00-67d361fa41d2")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("814dfba5-ac34-413c-b4a0-9ba515708056")
    public  EObjectNode(ObjectNode element) {
        super(element);
    }

    @objid ("df5cbd23-9c7a-4e8f-ab51-1171cae7cdc1")
    @Override
    public void setProperties(final Element objingElement) {
        super.setProperties(objingElement);
        setType((org.modelio.metamodel.uml.behavior.activityModel.ObjectNode) objingElement);
        
    }

    @objid ("cf62e085-03a0-4bfa-af74-edcb7fa6b6ab")
    private void setType(final org.modelio.metamodel.uml.behavior.activityModel.ObjectNode objingElt) {
        Type type = ((org.eclipse.uml2.uml.ObjectNode) this.getEcoreElement()).getType();
        
        if (type != null){
            Object objType = ReverseProperties.getInstance().getMappedElement(type);
            
            if (objType instanceof GeneralClass){
                objingElt.setType((GeneralClass) objType);
            }
        }
        
    }

}

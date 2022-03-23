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
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("c2d56eef-ef47-4c4a-99f8-135751fd8aee")
public class EInstanceValue extends ENamedElement {
    @objid ("96a95fa2-df17-408f-8d52-b3bf6f9a8a19")
    private InstanceValue ecoreElement = null;

    @objid ("a4157056-d4f1-443b-b435-04a23d07cd74")
    @Override
    public Element createObjingElt() {
        InstanceSpecification instance = this.ecoreElement.getInstance();
        
        if (instance != null) {
            Object temp = ReverseProperties.getInstance().getMappedElement(instance);
            if (temp instanceof Instance){
                Instance objInstance = (Instance) temp;
                if (objInstance instanceof BindableInstance){
                    
                    if ((((BindableInstance) objInstance).getInternalOwner() == null) 
                            && (((BindableInstance) objInstance).getOwner() == null)){
                        
                        ((BindableInstance) objInstance).setCluster(
                                (Instance) ReverseProperties.getInstance().getMappedElement(this.ecoreElement.getInstance()));
                    }
                }
            }
        }
        return null;
    }

    @objid ("ddefe9b5-3431-4c99-a207-f8163749c937")
    public  EInstanceValue(InstanceValue element) {
        super(element);
        this.ecoreElement = element;
        
    }

}

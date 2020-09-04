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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataObjectData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00037546-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataObjectImpl extends BpmnItemAwareElementImpl implements BpmnDataObject {
    @objid ("af7157e1-22f1-4736-9331-9d2fb7719d63")
    @Override
    public boolean isIsCollection() {
        return (Boolean) getAttVal(((BpmnDataObjectSmClass)getClassOf()).getIsCollectionAtt());
    }

    @objid ("5eaf8e3f-9ac8-42cd-8721-45a310d8c6e0")
    @Override
    public void setIsCollection(boolean value) {
        setAttVal(((BpmnDataObjectSmClass)getClassOf()).getIsCollectionAtt(), value);
    }

    @objid ("c90b00dd-810f-4e5f-87dd-7c081377c7ea")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("f16c15a4-4d6c-43f8-89ac-535fab640475")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("8f372f25-4a1a-47c5-8195-e105a97a896c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnDataObject(this);
    }

}

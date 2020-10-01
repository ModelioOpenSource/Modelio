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
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataStoreData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00041136-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataStoreImpl extends BpmnItemAwareElementImpl implements BpmnDataStore {
    @objid ("ead9fd6a-2726-4488-b437-c02ca3372014")
    @Override
    public int getCapacity() {
        return (Integer) getAttVal(((BpmnDataStoreSmClass)getClassOf()).getCapacityAtt());
    }

    @objid ("0e4e8dea-b718-4aaf-8c07-d2201bfcd1f0")
    @Override
    public void setCapacity(int value) {
        setAttVal(((BpmnDataStoreSmClass)getClassOf()).getCapacityAtt(), value);
    }

    @objid ("a11c8bcc-e2b0-4f6f-860f-6f4f91f5c373")
    @Override
    public boolean isIsUnlimited() {
        return (Boolean) getAttVal(((BpmnDataStoreSmClass)getClassOf()).getIsUnlimitedAtt());
    }

    @objid ("6921af46-8247-4c9e-9c0e-42c1fab6dce6")
    @Override
    public void setIsUnlimited(boolean value) {
        setAttVal(((BpmnDataStoreSmClass)getClassOf()).getIsUnlimitedAtt(), value);
    }

    @objid ("b69b2dd5-fc2b-4b7a-b520-250c50b25ea9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("ddd63076-fb35-4499-8c61-83ce0576bcac")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("720a46fd-4c8f-4c25-a401-cc5cf6f11bc4")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnDataStore(this);
    }

}

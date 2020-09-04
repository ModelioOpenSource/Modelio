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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnTransaction;
import org.modelio.metamodel.bpmn.activities.TransactionMethod;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTransactionData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0085963e-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnTransactionImpl extends BpmnSubProcessImpl implements BpmnTransaction {
    @objid ("c0e4b075-23b1-4826-ae68-69620131fc79")
    @Override
    public TransactionMethod getMethod() {
        return (TransactionMethod) getAttVal(((BpmnTransactionSmClass)getClassOf()).getMethodAtt());
    }

    @objid ("baf01738-9dca-4eb7-b21a-28b13dcbbfd0")
    @Override
    public void setMethod(TransactionMethod value) {
        setAttVal(((BpmnTransactionSmClass)getClassOf()).getMethodAtt(), value);
    }

    @objid ("cb2de9d4-fb5b-4168-b2c3-aca9bb1e9811")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("00412621-746e-4a00-974e-d3c892f1053a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("7a8ae670-c5e8-4083-a6af-72294d9bf549")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnTransaction(this);
    }

}

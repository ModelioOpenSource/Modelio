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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageEndData;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0049f7c8-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class MessageEndImpl extends OccurrenceSpecificationImpl implements MessageEnd {
    @objid ("27fcad95-ecfb-4d50-942c-2f30aa1db989")
    @Override
    public Message getReceivedMessage() {
        Object obj = getDepVal(((MessageEndSmClass)getClassOf()).getReceivedMessageDep());
        return (obj instanceof Message)? (Message)obj : null;
    }

    @objid ("425d34b4-4323-4461-897c-e2cc605f9d12")
    @Override
    public void setReceivedMessage(Message value) {
        appendDepVal(((MessageEndSmClass)getClassOf()).getReceivedMessageDep(), (SmObjectImpl)value);
    }

    @objid ("1540d54a-c0cd-4180-bfe9-3d514a9142e7")
    @Override
    public Message getSentMessage() {
        Object obj = getDepVal(((MessageEndSmClass)getClassOf()).getSentMessageDep());
        return (obj instanceof Message)? (Message)obj : null;
    }

    @objid ("90591bf7-6049-4662-ae8f-1517d4bb4c42")
    @Override
    public void setSentMessage(Message value) {
        appendDepVal(((MessageEndSmClass)getClassOf()).getSentMessageDep(), (SmObjectImpl)value);
    }

    @objid ("84114c77-cd2d-4c7f-a371-5b4d382d1fe8")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3118aaec-2043-425c-861f-dbd10488b7f3")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("cd445776-23d6-4eb3-94be-51fae74db17e")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitMessageEnd(this);
    }

}

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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.SendSignalActionData;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("003d773c-c4bf-1fd8-97fe-001ec947cd2a")
public class SendSignalActionImpl extends ActivityActionImpl implements SendSignalAction {
    @objid ("e02f424d-8d0f-4928-96ba-5755f142f3fd")
    @Override
    public Signal getSent() {
        Object obj = getDepVal(((SendSignalActionSmClass)getClassOf()).getSentDep());
        return (obj instanceof Signal)? (Signal)obj : null;
    }

    @objid ("fd93d0d2-426d-4eed-9b3b-aeea20a88f60")
    @Override
    public void setSent(Signal value) {
        appendDepVal(((SendSignalActionSmClass)getClassOf()).getSentDep(), (SmObjectImpl)value);
    }

    @objid ("9c22e60c-b1c1-47cd-a57c-d22caf5a2b1f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3ab1b1bf-d7fc-4a67-9c68-c8cff46cba65")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("050bba70-c486-42a9-b17e-65852da5ef5d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitSendSignalAction(this);
    }

}

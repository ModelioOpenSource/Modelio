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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.impl.bpmn.events.BpmnTimerEventDefinitionData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00969aa6-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnTimerEventDefinitionImpl extends BpmnEventDefinitionImpl implements BpmnTimerEventDefinition {
    @objid ("fb971430-0d81-4dd3-af74-810c9653f4fe")
    @Override
    public String getTimeCycle() {
        return (String) getAttVal(((BpmnTimerEventDefinitionSmClass)getClassOf()).getTimeCycleAtt());
    }

    @objid ("f79a4f07-a816-425d-91de-9a0f2113c726")
    @Override
    public void setTimeCycle(String value) {
        setAttVal(((BpmnTimerEventDefinitionSmClass)getClassOf()).getTimeCycleAtt(), value);
    }

    @objid ("b4363966-96b6-4f54-8b9f-7bc7b95f11e4")
    @Override
    public String getTimeDate() {
        return (String) getAttVal(((BpmnTimerEventDefinitionSmClass)getClassOf()).getTimeDateAtt());
    }

    @objid ("31ba62ec-54dc-4604-bbc0-e8ff146e207f")
    @Override
    public void setTimeDate(String value) {
        setAttVal(((BpmnTimerEventDefinitionSmClass)getClassOf()).getTimeDateAtt(), value);
    }

    @objid ("9f103ae2-9386-4860-bd75-31571fec7d17")
    @Override
    public String getTimeDuration() {
        return (String) getAttVal(((BpmnTimerEventDefinitionSmClass)getClassOf()).getTimeDurationAtt());
    }

    @objid ("7da74d96-168f-4d51-bb4e-2c7c2175f26f")
    @Override
    public void setTimeDuration(String value) {
        setAttVal(((BpmnTimerEventDefinitionSmClass)getClassOf()).getTimeDurationAtt(), value);
    }

    @objid ("d702a85b-2b6c-4499-9681-1897bea4ee5b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("bc887141-ea83-4824-869d-98d19c6e7e81")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("465b8294-7b6f-4432-b583-dd89e9bd941c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnTimerEventDefinition(this);
    }

}

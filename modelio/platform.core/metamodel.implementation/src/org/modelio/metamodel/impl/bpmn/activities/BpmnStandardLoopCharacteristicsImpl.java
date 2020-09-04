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
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.impl.bpmn.activities.BpmnStandardLoopCharacteristicsData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0083ce8a-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnStandardLoopCharacteristicsImpl extends BpmnLoopCharacteristicsImpl implements BpmnStandardLoopCharacteristics {
    @objid ("d9d8fee7-4c99-4086-b76f-954b312f2b03")
    @Override
    public boolean isTestBefore() {
        return (Boolean) getAttVal(((BpmnStandardLoopCharacteristicsSmClass)getClassOf()).getTestBeforeAtt());
    }

    @objid ("fdaa9c3d-45f1-465f-8212-e42123d71480")
    @Override
    public void setTestBefore(boolean value) {
        setAttVal(((BpmnStandardLoopCharacteristicsSmClass)getClassOf()).getTestBeforeAtt(), value);
    }

    @objid ("4de65194-d436-422a-89bb-93215c9b06f3")
    @Override
    public String getLoopCondition() {
        return (String) getAttVal(((BpmnStandardLoopCharacteristicsSmClass)getClassOf()).getLoopConditionAtt());
    }

    @objid ("a768177a-f3e3-4120-a696-d4d925384832")
    @Override
    public void setLoopCondition(String value) {
        setAttVal(((BpmnStandardLoopCharacteristicsSmClass)getClassOf()).getLoopConditionAtt(), value);
    }

    @objid ("ceff7906-c7d9-4cd2-881d-44f762799a91")
    @Override
    public String getLoopMaximum() {
        return (String) getAttVal(((BpmnStandardLoopCharacteristicsSmClass)getClassOf()).getLoopMaximumAtt());
    }

    @objid ("6c42f6d7-c3c6-4f04-b5e6-098831b5e861")
    @Override
    public void setLoopMaximum(String value) {
        setAttVal(((BpmnStandardLoopCharacteristicsSmClass)getClassOf()).getLoopMaximumAtt(), value);
    }

    @objid ("550e9ac4-b7d4-4ab5-8365-53058038b053")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("b0c8805d-a98b-455b-89e8-e8bf14561c53")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("23a57a6f-3a91-4e0a-82ee-7089db812889")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnStandardLoopCharacteristics(this);
    }

}

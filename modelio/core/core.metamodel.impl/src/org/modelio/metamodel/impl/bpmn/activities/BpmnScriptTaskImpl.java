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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnScriptTask;
import org.modelio.metamodel.impl.bpmn.activities.BpmnScriptTaskData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00822bd4-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnScriptTaskImpl extends BpmnTaskImpl implements BpmnScriptTask {
    @objid ("ade02b0f-9eb7-46ba-9886-a9d88e8a98ad")
    @Override
    public String getScriptLanguage() {
        return (String) getAttVal(((BpmnScriptTaskSmClass)getClassOf()).getScriptLanguageAtt());
    }

    @objid ("78a2949b-0fa1-4e08-994d-58f3a3366cc2")
    @Override
    public void setScriptLanguage(String value) {
        setAttVal(((BpmnScriptTaskSmClass)getClassOf()).getScriptLanguageAtt(), value);
    }

    @objid ("4429f723-072a-477e-9a2d-2e37b5b9524c")
    @Override
    public String getScript() {
        return (String) getAttVal(((BpmnScriptTaskSmClass)getClassOf()).getScriptAtt());
    }

    @objid ("8f147080-09d8-46da-a8b7-ff7ee1ddaaf0")
    @Override
    public void setScript(String value) {
        setAttVal(((BpmnScriptTaskSmClass)getClassOf()).getScriptAtt(), value);
    }

    @objid ("fa336ce3-6a01-41bb-88a1-237d2cb79233")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("152a4102-8d9f-4832-95c2-6fc8c3deed6f")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("ddebe212-01a5-4488-bcca-3250cabfefa1")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnScriptTask(this);
    }

}

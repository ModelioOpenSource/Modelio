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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectFlowData;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlowEffectKind;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0038c502-c4bf-1fd8-97fe-001ec947cd2a")
public class ObjectFlowImpl extends ActivityEdgeImpl implements ObjectFlow {
    @objid ("c2335b14-e85f-4d3c-8e01-dae04fd3d26b")
    @Override
    public String getTransformationBehavior() {
        return (String) getAttVal(((ObjectFlowSmClass)getClassOf()).getTransformationBehaviorAtt());
    }

    @objid ("6fc1e019-2f59-455d-a903-df3781eb1f9b")
    @Override
    public void setTransformationBehavior(String value) {
        setAttVal(((ObjectFlowSmClass)getClassOf()).getTransformationBehaviorAtt(), value);
    }

    @objid ("cf14dc5e-6d11-4219-bfe2-faa3bfa766f0")
    @Override
    public String getSelectionBehavior() {
        return (String) getAttVal(((ObjectFlowSmClass)getClassOf()).getSelectionBehaviorAtt());
    }

    @objid ("0aa7fdbd-15f2-4cdd-b617-2e9500d67fda")
    @Override
    public void setSelectionBehavior(String value) {
        setAttVal(((ObjectFlowSmClass)getClassOf()).getSelectionBehaviorAtt(), value);
    }

    @objid ("bd716bdf-46e7-4eaa-a13c-bc670caf326b")
    @Override
    public boolean isIsMultiCast() {
        return (Boolean) getAttVal(((ObjectFlowSmClass)getClassOf()).getIsMultiCastAtt());
    }

    @objid ("ae24a9f2-eb43-40ea-a87a-400db63ae1d5")
    @Override
    public void setIsMultiCast(boolean value) {
        setAttVal(((ObjectFlowSmClass)getClassOf()).getIsMultiCastAtt(), value);
    }

    @objid ("a81afc61-5025-4e21-b395-3b16bcaef8fe")
    @Override
    public boolean isIsMultiReceive() {
        return (Boolean) getAttVal(((ObjectFlowSmClass)getClassOf()).getIsMultiReceiveAtt());
    }

    @objid ("a48eac93-1b8a-460e-bf40-e4d24d764000")
    @Override
    public void setIsMultiReceive(boolean value) {
        setAttVal(((ObjectFlowSmClass)getClassOf()).getIsMultiReceiveAtt(), value);
    }

    @objid ("e766f12c-c106-4ea1-bc0f-27cd57c0d5d6")
    @Override
    public ObjectFlowEffectKind getEffect() {
        return (ObjectFlowEffectKind) getAttVal(((ObjectFlowSmClass)getClassOf()).getEffectAtt());
    }

    @objid ("fde9a986-5e14-4adb-b5c2-dbc99a05ba04")
    @Override
    public void setEffect(ObjectFlowEffectKind value) {
        setAttVal(((ObjectFlowSmClass)getClassOf()).getEffectAtt(), value);
    }

    @objid ("335d9b63-57e8-411c-883f-036db1c747d5")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("a07b4458-b530-4ce6-8975-a79eb0ea0cd6")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("693180da-4629-4edc-afcf-72b359ab7c64")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitObjectFlow(this);
    }

}

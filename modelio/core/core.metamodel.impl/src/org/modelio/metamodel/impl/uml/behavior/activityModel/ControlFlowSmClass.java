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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("bcf8c7a8-f5f2-4aa2-8d40-afaac0e29e40")
public class ControlFlowSmClass extends ActivityEdgeSmClass {
    @objid ("1c8acb54-e203-44bb-bee6-9685f6940808")
    public  ControlFlowSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("54286227-4d79-4611-aee2-b3b931687b10")
    @Override
    public String getName() {
        return "ControlFlow";
        
    }

    @objid ("77d9346a-5eeb-4dae-9f5f-c9d5a64062ce")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("4f7f1796-4ea5-4ed2-9476-0a9b77dcac66")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ControlFlow.class;
        
    }

    @objid ("d1d85574-9cb1-442c-9df0-0746edb744f9")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("330534c1-14e1-436f-b609-6bac6df05d98")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("b6865597-7c22-4ad0-940e-5d5ceb742f8f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityEdge.MQNAME);
        this.registerFactory(new ControlFlowObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("b768d67b-dca4-4262-9c1d-62a84924e1be")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("63f8d176-7c1a-43d6-b93d-bfb1902e73d0")
    private static class ControlFlowObjectFactory implements ISmObjectFactory {
        @objid ("6fbda756-b209-4d50-a94b-1aceb876ea06")
        private ControlFlowSmClass smClass;

        @objid ("2a5e61f9-e1c2-4636-a32d-dc07a542e288")
        public  ControlFlowObjectFactory(ControlFlowSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("1e244895-37dd-485e-8cb7-938447a5ddb6")
        @Override
        public ISmObjectData createData() {
            return new ControlFlowData(this.smClass);
        }

        @objid ("69ded808-4609-4a6c-a001-7faa33a10ef4")
        @Override
        public SmObjectImpl createImpl() {
            return new ControlFlowImpl();
        }

    }

}

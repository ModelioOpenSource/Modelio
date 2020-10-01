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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("3cdd22d4-0911-4788-b3a1-dd23e0f8fe44")
public class ControlNodeSmClass extends ActivityNodeSmClass {
    @objid ("eb9f679b-f1ce-4097-b1b7-32ffc37baacd")
    public ControlNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("2aff1b2a-e3ed-4070-96d1-53debb3f412c")
    @Override
    public String getName() {
        return "ControlNode";
    }

    @objid ("3f403fbd-8972-4aa7-8863-1731b3c73a29")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d1aa4eb8-b494-498a-a641-42cda193978e")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ControlNode.class;
    }

    @objid ("cef56f7d-d127-4792-ba71-499b6c8f44fa")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("3db2c610-e0e8-4a51-b08d-ce0bfa200ac3")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("d121520f-9fd1-4ffd-8b9a-89c3f08c8895")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ActivityNode.MQNAME);
        this.registerFactory(new ControlNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("b723b072-98d6-4e30-af94-e7eb61a2fff9")
    private static class ControlNodeObjectFactory implements ISmObjectFactory {
        @objid ("ec839f26-6b6c-4a98-8857-3b42cb5cf839")
        private ControlNodeSmClass smClass;

        @objid ("1b440c65-5bf1-40ab-b883-a72c74178bb7")
        public ControlNodeObjectFactory(ControlNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8246e260-195d-4e41-8966-994384c16166")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("72d721d9-c68e-419e-901e-1e3a7f1045ee")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

}

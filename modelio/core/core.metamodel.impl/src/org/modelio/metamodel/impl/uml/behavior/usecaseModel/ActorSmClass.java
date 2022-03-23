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

package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("75ac7365-5024-4e1f-a996-4d7ea2b787c3")
public class ActorSmClass extends GeneralClassSmClass {
    @objid ("e80dcf60-c526-49d5-ae91-3d63cfc21dfc")
    public  ActorSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("11c7c88c-bec9-453d-8fc4-54b57511ba70")
    @Override
    public String getName() {
        return "Actor";
        
    }

    @objid ("dd604fe1-7fde-42f6-8374-6f1fba6f09c9")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6bff36cf-2197-46ff-9bac-598b78e87582")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Actor.class;
        
    }

    @objid ("12ed3a73-08b5-4636-a489-e5e0863f78ba")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("71038c6d-80a5-4f17-8c78-f36f63563bbd")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("11d388cb-e58c-4717-b608-13037260b690")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(GeneralClass.MQNAME);
        this.registerFactory(new ActorObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("78e4c876-bf88-47ec-8145-c5d5111b3b6b")
    private static class ActorObjectFactory implements ISmObjectFactory {
        @objid ("71e70015-af80-4fac-9479-40da38bcd311")
        private ActorSmClass smClass;

        @objid ("fd5fcf80-6cc3-4c28-95c8-7fed96d1221b")
        public  ActorObjectFactory(ActorSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("47d09870-ffcd-4abc-a537-f6840ad99fe2")
        @Override
        public ISmObjectData createData() {
            return new ActorData(this.smClass);
        }

        @objid ("3245d22f-93b3-4e98-8abe-dbb44c918d2e")
        @Override
        public SmObjectImpl createImpl() {
            return new ActorImpl();
        }

    }

}

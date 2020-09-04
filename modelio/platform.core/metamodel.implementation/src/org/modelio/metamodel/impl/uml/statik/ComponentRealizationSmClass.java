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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.impl.uml.statik.ComponentRealizationData;
import org.modelio.metamodel.impl.uml.statik.ComponentSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("e45e84a7-0dba-4cfb-b997-5a197cb308e9")
public class ComponentRealizationSmClass extends UmlModelElementSmClass {
    @objid ("9c1dbfa5-8a1a-4057-9ff8-85deaaf44035")
    private SmDependency realizingClassifierDep;

    @objid ("ba8ff15d-d8d2-43c6-bbfa-ebc738ff3340")
    private SmDependency abstractionDep;

    @objid ("4e72ba3f-5d54-4453-8f3a-25f2cfc7a3e1")
    public ComponentRealizationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c5c47260-9249-49d4-96db-bfd336c3d55c")
    @Override
    public String getName() {
        return "ComponentRealization";
    }

    @objid ("61667ee1-5f6a-47b4-9d21-dff88fa66c9c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("48208c38-4ce0-49aa-9967-8340ed0b4098")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ComponentRealization.class;
    }

    @objid ("493f0f04-54bd-4851-9037-cf6542926cd5")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("99d93851-c2ce-4387-bf6f-2396957fd1da")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("42752c53-6701-4935-9980-30ba8175a4e4")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new ComponentRealizationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.realizingClassifierDep = new RealizingClassifierSmDependency();
        this.realizingClassifierDep.init("RealizingClassifier", this, metamodel.getMClass(Classifier.MQNAME), 1, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.realizingClassifierDep);
        
        this.abstractionDep = new AbstractionSmDependency();
        this.abstractionDep.init("Abstraction", this, metamodel.getMClass(Component.MQNAME), 1, 1 );
        registerDependency(this.abstractionDep);
    }

    @objid ("d7a20ee6-f717-4800-b769-cc0cf3ebff6d")
    public SmDependency getRealizingClassifierDep() {
        if (this.realizingClassifierDep == null) {
        	this.realizingClassifierDep = this.getDependencyDef("RealizingClassifier");
        }
        return this.realizingClassifierDep;
    }

    @objid ("a2abe052-5c37-4226-9aad-1ff68ecc6121")
    public SmDependency getAbstractionDep() {
        if (this.abstractionDep == null) {
        	this.abstractionDep = this.getDependencyDef("Abstraction");
        }
        return this.abstractionDep;
    }

    @objid ("d9e6efc9-9b51-4ffc-9322-827ff520bcc0")
    private static class ComponentRealizationObjectFactory implements ISmObjectFactory {
        @objid ("887bdf70-6587-4391-bbdb-fbaff2add026")
        private ComponentRealizationSmClass smClass;

        @objid ("d842ed54-a93d-453f-9980-6ae1eb5ac818")
        public ComponentRealizationObjectFactory(ComponentRealizationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("5052786d-a0bf-49ea-9a98-4ae1ba5e30a4")
        @Override
        public ISmObjectData createData() {
            return new ComponentRealizationData(this.smClass);
        }

        @objid ("477801b4-f0ad-4cbf-aa00-dead60475a7a")
        @Override
        public SmObjectImpl createImpl() {
            return new ComponentRealizationImpl();
        }

    }

    @objid ("b45da4d2-7aa5-49ef-a3c9-249dc0474f2f")
    public static class RealizingClassifierSmDependency extends SmSingleDependency {
        @objid ("2b55fc14-8b86-4f83-bb29-5ccb8db873f1")
        private SmDependency symetricDep;

        @objid ("ec0d126f-8f67-40b5-bd89-71bb54359b52")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ComponentRealizationData) data).mRealizingClassifier;
        }

        @objid ("c49956d4-1b6b-4efc-aa4c-a588c8900aec")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ComponentRealizationData) data).mRealizingClassifier = value;
        }

        @objid ("d0c08fb2-e9b8-4c57-b34f-a24788e379b1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getRealizedComponentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("9e6156fb-025e-4cb1-85b2-8b04c8ac8c60")
    public static class AbstractionSmDependency extends SmSingleDependency {
        @objid ("d49bfc3b-4baa-4800-9cbb-4fafe91b3ad5")
        private SmDependency symetricDep;

        @objid ("f454d104-22e4-4376-9768-7838a3d616af")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ComponentRealizationData) data).mAbstraction;
        }

        @objid ("9ad9475d-e3e2-4433-9255-41f970ae2770")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ComponentRealizationData) data).mAbstraction = value;
        }

        @objid ("c3a880d6-b0f7-4def-b0a1-88bde10af2a8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ComponentSmClass)this.getTarget()).getRealizationDep();
            }
            return this.symetricDep;
        }

    }

}

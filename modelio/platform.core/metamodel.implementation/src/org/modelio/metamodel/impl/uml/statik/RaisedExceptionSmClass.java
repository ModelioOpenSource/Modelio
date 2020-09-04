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
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.impl.uml.statik.RaisedExceptionData;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.RaisedException;
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

@objid ("b960d404-5093-4b35-8dd7-972452bceb08")
public class RaisedExceptionSmClass extends UmlModelElementSmClass {
    @objid ("453047cc-3781-4ba1-8459-63e6b5033f03")
    private SmDependency thrownTypeDep;

    @objid ("bacb11e0-0db1-4e92-a868-e176b9570c3d")
    private SmDependency throwerDep;

    @objid ("8564bfc0-d68c-48b2-97e7-65970df45d71")
    public RaisedExceptionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("c43b92f0-dcb9-4ee6-9941-58111110f34e")
    @Override
    public String getName() {
        return "RaisedException";
    }

    @objid ("c4ebd542-90df-4433-b34a-5db07aeea5f3")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("eb491dd3-ca66-4860-b84e-26acb5a21788")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return RaisedException.class;
    }

    @objid ("80b37443-de1b-4242-8651-768f059deba9")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("ae0cea48-759b-4050-ac00-7e361afa9053")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("762e6cc0-f9aa-4265-bb54-eaf56c6a170a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new RaisedExceptionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.thrownTypeDep = new ThrownTypeSmDependency();
        this.thrownTypeDep.init("ThrownType", this, metamodel.getMClass(Classifier.MQNAME), 1, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.thrownTypeDep);
        
        this.throwerDep = new ThrowerSmDependency();
        this.throwerDep.init("Thrower", this, metamodel.getMClass(Operation.MQNAME), 1, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.throwerDep);
    }

    @objid ("ad7afa48-d6ed-4b80-8f52-4f86e512677e")
    public SmDependency getThrownTypeDep() {
        if (this.thrownTypeDep == null) {
        	this.thrownTypeDep = this.getDependencyDef("ThrownType");
        }
        return this.thrownTypeDep;
    }

    @objid ("e208cf75-4d60-4a6e-aa5d-26c4281c99ae")
    public SmDependency getThrowerDep() {
        if (this.throwerDep == null) {
        	this.throwerDep = this.getDependencyDef("Thrower");
        }
        return this.throwerDep;
    }

    @objid ("d2f05395-cb05-4c19-acf0-0595938ea571")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("a29b4053-1a60-414c-8a97-d3201ced8c80")
    private static class RaisedExceptionObjectFactory implements ISmObjectFactory {
        @objid ("257a4eb7-9e7e-4f11-81d5-cd8ac9ffe46e")
        private RaisedExceptionSmClass smClass;

        @objid ("37f7f0db-5b27-4a1a-98f3-67eed1e11430")
        public RaisedExceptionObjectFactory(RaisedExceptionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c30076b2-bebe-4618-a1d2-cf03dee7e4df")
        @Override
        public ISmObjectData createData() {
            return new RaisedExceptionData(this.smClass);
        }

        @objid ("153b26fc-b09f-4394-8dde-c0a1919d8473")
        @Override
        public SmObjectImpl createImpl() {
            return new RaisedExceptionImpl();
        }

    }

    @objid ("2ea0cdbb-972a-4dab-81e9-97d06903bd26")
    public static class ThrownTypeSmDependency extends SmSingleDependency {
        @objid ("18afc847-a558-426d-b54f-e4a7f70f060b")
        private SmDependency symetricDep;

        @objid ("6519a8bf-ea8f-499e-a2b0-6bafd930d06d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((RaisedExceptionData) data).mThrownType;
        }

        @objid ("faf720d3-8d4e-4af9-9efe-a1d93e895aa4")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((RaisedExceptionData) data).mThrownType = value;
        }

        @objid ("6ae61fd2-a448-4fef-858c-d7eed280428c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getThrowingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("d215c73f-da99-4bcf-a6e7-44f99511556c")
    public static class ThrowerSmDependency extends SmSingleDependency {
        @objid ("e248c9ad-b5d5-435d-a7d1-b5eeda142880")
        private SmDependency symetricDep;

        @objid ("61d38791-c73f-4ffe-b6ba-15632729701d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((RaisedExceptionData) data).mThrower;
        }

        @objid ("67c404e6-f33d-4fc5-a0e6-2ddd32af1d8b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((RaisedExceptionData) data).mThrower = value;
        }

        @objid ("ab7eeec2-7db9-47c8-8c6d-bbb2f434f44b")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getThrownDep();
            }
            return this.symetricDep;
        }

    }

}

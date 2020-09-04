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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.SubstitutionData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
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

@objid ("2023cbd9-0f99-421d-982c-f6ccede2a596")
public class SubstitutionSmClass extends UmlModelElementSmClass {
    @objid ("16833c88-25a3-4853-b009-d032f843f529")
    private SmDependency contractDep;

    @objid ("9ad9a423-3ec3-4ce8-906a-d1ba9102f8d8")
    private SmDependency substitutingClassifierDep;

    @objid ("31c5a9ec-67a4-4086-8927-407aaab5d56b")
    public SubstitutionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("4d8e386a-05a2-4e4c-a130-7ebc249b4799")
    @Override
    public String getName() {
        return "Substitution";
    }

    @objid ("4d0e53fa-b599-447c-b1c4-8a40f1af71fb")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e0f98a35-a4d8-4a22-9169-dc3630dfa314")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Substitution.class;
    }

    @objid ("5a87f95f-8512-4af7-8202-54bb26b7a4b9")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6cb87f6a-c7c3-4fbf-9146-880efb4d383d")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("068121eb-f964-4047-8245-d7abc2a9169a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new SubstitutionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.contractDep = new ContractSmDependency();
        this.contractDep.init("Contract", this, metamodel.getMClass(Classifier.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.contractDep);
        
        this.substitutingClassifierDep = new SubstitutingClassifierSmDependency();
        this.substitutingClassifierDep.init("SubstitutingClassifier", this, metamodel.getMClass(Classifier.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.substitutingClassifierDep);
    }

    @objid ("375fbd1b-d22d-430f-8da0-46495c48e694")
    public SmDependency getContractDep() {
        if (this.contractDep == null) {
        	this.contractDep = this.getDependencyDef("Contract");
        }
        return this.contractDep;
    }

    @objid ("6660d61d-b57a-48d0-9efc-f3fa0ae7c49a")
    public SmDependency getSubstitutingClassifierDep() {
        if (this.substitutingClassifierDep == null) {
        	this.substitutingClassifierDep = this.getDependencyDef("SubstitutingClassifier");
        }
        return this.substitutingClassifierDep;
    }

    @objid ("c6b3bf11-9eed-4fe5-8d85-d295e8191c68")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("936be856-0eee-482c-bf5f-0fd72c031c62")
    private static class SubstitutionObjectFactory implements ISmObjectFactory {
        @objid ("e2d20b9b-57d3-4c0a-8005-672c9a96de2f")
        private SubstitutionSmClass smClass;

        @objid ("6a50b367-06eb-43d3-bbaf-2e3e84f52e56")
        public SubstitutionObjectFactory(SubstitutionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("fe719d7a-e74e-40ee-9378-e8fdb4819764")
        @Override
        public ISmObjectData createData() {
            return new SubstitutionData(this.smClass);
        }

        @objid ("467138fc-6bec-465a-b393-b97ad6c77ff1")
        @Override
        public SmObjectImpl createImpl() {
            return new SubstitutionImpl();
        }

    }

    @objid ("8cad2aa0-2fce-4fb2-812f-28aa246331d3")
    public static class ContractSmDependency extends SmSingleDependency {
        @objid ("45a0c9f7-d44a-4116-b6f5-f6a5d507a9af")
        private SmDependency symetricDep;

        @objid ("d477ca9d-ee07-4250-92b0-14777521ecbe")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((SubstitutionData) data).mContract;
        }

        @objid ("3dfcbf3e-dec0-4b6b-8588-66c45ac6d211")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((SubstitutionData) data).mContract = value;
        }

        @objid ("a42e5c10-4bdb-4bbb-a7c6-d990b93d6d97")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getSubstitutingSubstitutionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("82d164de-0ed8-4c9c-9690-141bc7e27655")
    public static class SubstitutingClassifierSmDependency extends SmSingleDependency {
        @objid ("fcb6e0c2-46c9-41b1-94f5-47779787f6ab")
        private SmDependency symetricDep;

        @objid ("e7ea4e79-cff2-467a-b3ed-8b7b22ec3e82")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((SubstitutionData) data).mSubstitutingClassifier;
        }

        @objid ("9ea3b385-9d61-499d-8b8b-993193e1334a")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((SubstitutionData) data).mSubstitutingClassifier = value;
        }

        @objid ("e486501a-95c6-4085-9c24-c7d386bbd94a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getSubstituedDep();
            }
            return this.symetricDep;
        }

    }

}

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
package org.modelio.metamodel.impl.uml.informationFlow;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.informationFlow.InformationItemData;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1314c071-1b9e-4f70-9275-ed8a40148625")
public class InformationItemSmClass extends ClassifierSmClass {
    @objid ("3c78c672-8f24-4e13-b156-922e52cb23b4")
    private SmDependency representedDep;

    @objid ("f087acc2-939a-4336-bae2-5c948276e293")
    public InformationItemSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7f88fcc9-613c-4cd9-b2e3-f019d6fef9f0")
    @Override
    public String getName() {
        return "InformationItem";
    }

    @objid ("7042e941-3a72-4dfc-84db-912c5322a509")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("36b775ca-afbb-4048-a59e-0683becff5bb")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return InformationItem.class;
    }

    @objid ("4ee6a268-21af-4aeb-ad7d-3886d3189e44")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("d68f7216-74b6-4f09-a562-fccaaedbffbc")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("f174c3d4-ea92-41a7-adfd-8e3b1906faa3")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Classifier.MQNAME);
        this.registerFactory(new InformationItemObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.representedDep = new RepresentedSmDependency();
        this.representedDep.init("Represented", this, metamodel.getMClass(Classifier.MQNAME), 0, -1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedDep);
    }

    @objid ("3f34ecfe-f38e-478d-8d48-bf70ce620148")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("0a65d0c1-0403-4c20-9530-8b89e08ebdd4")
    private static class InformationItemObjectFactory implements ISmObjectFactory {
        @objid ("7d124901-38ec-439f-ab66-65e4c7312485")
        private InformationItemSmClass smClass;

        @objid ("416f69f1-dbcb-415d-9878-b3f32636087f")
        public InformationItemObjectFactory(InformationItemSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9a0f8199-baba-4bed-9bed-928823ddf779")
        @Override
        public ISmObjectData createData() {
            return new InformationItemData(this.smClass);
        }

        @objid ("cd108708-9425-4088-80e3-e3ddea66f2c1")
        @Override
        public SmObjectImpl createImpl() {
            return new InformationItemImpl();
        }

    }

    @objid ("f75d045a-9977-46f7-bc70-5b43d2b35976")
    public static class RepresentedSmDependency extends SmMultipleDependency {
        @objid ("45856fc9-66ab-4e2d-923e-5011964ad6ce")
        private SmDependency symetricDep;

        @objid ("db4599b8-c9f4-4270-a79c-313bb2a98bac")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((InformationItemData)data).mRepresented != null)? ((InformationItemData)data).mRepresented:SmMultipleDependency.EMPTY;
        }

        @objid ("c78ef2ff-d40e-42a6-b048-3e20b84bc3a6")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((InformationItemData) data).mRepresented = values;
        }

        @objid ("5b79f020-7da2-4aa3-8c4e-ee7bd534202d")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ClassifierSmClass)this.getTarget()).getRepresentationDep();
            }
            return this.symetricDep;
        }

    }

}

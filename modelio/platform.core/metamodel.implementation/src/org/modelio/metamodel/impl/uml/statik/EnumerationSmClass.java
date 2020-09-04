/* 
 * Copyright 2013-2018 Modeliosoft
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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.EnumerationData;
import org.modelio.metamodel.impl.uml.statik.EnumerationLiteralSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.GeneralClass;
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

@objid ("49bcd7f8-1b5e-4962-82fc-c54ac6966058")
public class EnumerationSmClass extends GeneralClassSmClass {
    @objid ("08cc6954-b04a-48b2-bedf-63149a650554")
    private SmDependency valueDep;

    @objid ("e046e931-d82d-4cd9-ac65-90d2e95337de")
    public EnumerationSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b6cd85d8-e310-4e44-9212-2060783570e0")
    @Override
    public String getName() {
        return "Enumeration";
    }

    @objid ("1460dc89-1468-4e7f-883a-b5059b9f959d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("38c382a1-4838-4be3-a43c-a7996df173c6")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Enumeration.class;
    }

    @objid ("e8e41722-0e5f-4952-8039-6153e48341c8")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("ce2421ac-5fdb-4fcc-8739-4ee69e75f4c8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("dfce23ed-a7bc-4285-a56f-872cae981df6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(GeneralClass.MQNAME);
        this.registerFactory(new EnumerationObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.valueDep = new ValueSmDependency();
        this.valueDep.init("Value", this, metamodel.getMClass(EnumerationLiteral.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.valueDep);
    }

    @objid ("6dd9b59d-3c1b-46eb-976a-c13029303cf9")
    public SmDependency getValueDep() {
        if (this.valueDep == null) {
        	this.valueDep = this.getDependencyDef("Value");
        }
        return this.valueDep;
    }

    @objid ("23dfb1da-9abd-48db-9481-a9dc0db3df98")
    private static class EnumerationObjectFactory implements ISmObjectFactory {
        @objid ("2a06bd58-454a-49ca-a6d4-20066787b413")
        private EnumerationSmClass smClass;

        @objid ("acc97915-f5ac-4001-a485-0407e4600f44")
        public EnumerationObjectFactory(EnumerationSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4a08db95-5b6e-4e09-ad16-39ab29bd7c39")
        @Override
        public ISmObjectData createData() {
            return new EnumerationData(this.smClass);
        }

        @objid ("9a5946fe-a931-4db7-8c9d-41b2b707d2ac")
        @Override
        public SmObjectImpl createImpl() {
            return new EnumerationImpl();
        }

    }

    @objid ("ec134b37-d414-4471-9fc0-690c7b03b561")
    public static class ValueSmDependency extends SmMultipleDependency {
        @objid ("3a3bf987-aae8-4ddd-858b-819a07e3cf30")
        private SmDependency symetricDep;

        @objid ("692b8277-9a39-4de0-916b-24fbf9c18244")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((EnumerationData)data).mValue != null)? ((EnumerationData)data).mValue:SmMultipleDependency.EMPTY;
        }

        @objid ("653f2805-4904-4c4c-a7e9-7962dd526a2a")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((EnumerationData) data).mValue = values;
        }

        @objid ("e3d27941-f5d9-414b-a24c-f6d083e549b9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EnumerationLiteralSmClass)this.getTarget()).getValuatedDep();
            }
            return this.symetricDep;
        }

    }

}

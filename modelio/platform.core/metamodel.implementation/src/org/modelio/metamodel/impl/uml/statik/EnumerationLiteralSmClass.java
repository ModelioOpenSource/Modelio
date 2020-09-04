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
import org.modelio.metamodel.impl.uml.statik.EnumerationLiteralData;
import org.modelio.metamodel.impl.uml.statik.EnumerationSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
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

@objid ("d14877d6-45e4-49f7-92c5-6db955704cf5")
public class EnumerationLiteralSmClass extends UmlModelElementSmClass {
    @objid ("63891e2f-e2ca-4289-946a-685778a4cf63")
    private SmDependency valuatedDep;

    @objid ("3643d3e9-d86f-40c6-ae41-7819f95ee7c5")
    public EnumerationLiteralSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7dae32cd-ba20-488b-a101-c12eef3c63ca")
    @Override
    public String getName() {
        return "EnumerationLiteral";
    }

    @objid ("1c4fdac7-9c59-4198-a272-88f94e707547")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("57a9ac55-785b-4bc7-b719-76e3d3ee36bd")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return EnumerationLiteral.class;
    }

    @objid ("cf91af7e-73e1-4d8d-9250-9353c9363dda")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("9548607c-3e65-44ca-a625-f576b8841678")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("1ece4a03-ac4e-4261-9187-ac7a9183ab89")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new EnumerationLiteralObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.valuatedDep = new ValuatedSmDependency();
        this.valuatedDep.init("Valuated", this, metamodel.getMClass(Enumeration.MQNAME), 1, 1 );
        registerDependency(this.valuatedDep);
    }

    @objid ("a4a9bfbc-c6a5-4e7c-a3cd-31a57359d5ef")
    public SmDependency getValuatedDep() {
        if (this.valuatedDep == null) {
        	this.valuatedDep = this.getDependencyDef("Valuated");
        }
        return this.valuatedDep;
    }

    @objid ("76402487-664a-4d9e-ac9f-90efe26e0dfb")
    private static class EnumerationLiteralObjectFactory implements ISmObjectFactory {
        @objid ("b62de694-d923-4287-8988-4a5f848a1311")
        private EnumerationLiteralSmClass smClass;

        @objid ("f68a3871-eacb-4ae3-9a33-52a607456dea")
        public EnumerationLiteralObjectFactory(EnumerationLiteralSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("43bac855-c6af-417f-a3d5-93a2f2acb837")
        @Override
        public ISmObjectData createData() {
            return new EnumerationLiteralData(this.smClass);
        }

        @objid ("e0034035-4c5c-455d-89ab-45f487bb60cf")
        @Override
        public SmObjectImpl createImpl() {
            return new EnumerationLiteralImpl();
        }

    }

    @objid ("243e7d39-af71-4b90-a709-2534be80e17b")
    public static class ValuatedSmDependency extends SmSingleDependency {
        @objid ("e839f3ee-e59a-41d1-a33e-f1d5171b9e29")
        private SmDependency symetricDep;

        @objid ("dc40ed85-edbd-45ec-90c6-7010eceb9c54")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((EnumerationLiteralData) data).mValuated;
        }

        @objid ("320790b1-7238-4f74-a8f4-5f33c1d631d8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((EnumerationLiteralData) data).mValuated = value;
        }

        @objid ("6a00ff3e-4487-4915-9a1d-b143753032b9")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((EnumerationSmClass)this.getTarget()).getValueDep();
            }
            return this.symetricDep;
        }

    }

}

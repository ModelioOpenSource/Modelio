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

package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("c774246a-dc30-43bf-bb73-ffd464368bca")
public class NaryConnectorSmClass extends NaryLinkSmClass {
    @objid ("f2f69e69-7e14-44ed-80c3-4e2d7f6f0c67")
    private SmDependency representationDep;

    @objid ("1723c2a1-494b-4e2b-a2ca-578b01b5e41f")
    private SmDependency representedFeatureDep;

    @objid ("5a7a4b35-2ca2-4a80-b266-b357f9b3e7f9")
    public  NaryConnectorSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("85e89d48-481e-42ca-9ffb-12752ea9f204")
    @Override
    public String getName() {
        return "NaryConnector";
        
    }

    @objid ("1dd2c5b3-6bb6-4a67-86cd-80b58d707237")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("675f1143-966e-4066-8fcc-2012c143f9a8")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return NaryConnector.class;
        
    }

    @objid ("d45bebd7-c88a-4df2-ba5f-fac3830bd5d0")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("5944ebbc-d7cc-414d-bf59-2953bbdd9ee6")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("1be1b41d-5ba1-4e4b-a6b6-8b3de00defd8")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(NaryLink.MQNAME);
        this.registerFactory(new NaryConnectorObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.representationDep = new RepresentationSmDependency();
        this.representationDep.init("Representation", this, metamodel.getMClass(Binding.MQNAME), 0, -1 );
        registerDependency(this.representationDep);
        
        this.representedFeatureDep = new RepresentedFeatureSmDependency();
        this.representedFeatureDep.init("RepresentedFeature", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedFeatureDep);
        
        
    }

    @objid ("d1981fab-1061-4fbd-9c0f-d865126bf56e")
    public SmDependency getRepresentationDep() {
        if (this.representationDep == null) {
        	this.representationDep = this.getDependencyDef("Representation");
        }
        return this.representationDep;
    }

    @objid ("db162a3e-7758-41c4-9a09-59331d8b95c9")
    public SmDependency getRepresentedFeatureDep() {
        if (this.representedFeatureDep == null) {
        	this.representedFeatureDep = this.getDependencyDef("RepresentedFeature");
        }
        return this.representedFeatureDep;
    }

    @objid ("279b029b-6dea-4464-8974-75d2d7b0f5fa")
    private static class NaryConnectorObjectFactory implements ISmObjectFactory {
        @objid ("d61debac-93ba-40b0-807b-772f36cccdf1")
        private NaryConnectorSmClass smClass;

        @objid ("60db4cd3-1592-4297-97b5-4a75eae1ddfb")
        public  NaryConnectorObjectFactory(NaryConnectorSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2f3a1e1c-3f23-4e17-bb4e-8561fb4377db")
        @Override
        public ISmObjectData createData() {
            return new NaryConnectorData(this.smClass);
        }

        @objid ("5e626be9-ffa5-4929-a956-ce63ee22aea1")
        @Override
        public SmObjectImpl createImpl() {
            return new NaryConnectorImpl();
        }

    }

    @objid ("47c210d6-d671-41c8-a036-5cf535433629")
    public static class RepresentationSmDependency extends SmMultipleDependency {
        @objid ("e262435e-70da-4329-8632-f0437b24f5f0")
        private SmDependency symetricDep;

        @objid ("63f3d656-1e76-48fb-abcd-2bdc7195b4ff")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((NaryConnectorData)data).mRepresentation != null)? ((NaryConnectorData)data).mRepresentation:SmMultipleDependency.EMPTY;
        }

        @objid ("8000806e-c8ce-41c4-b549-f113673e3154")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((NaryConnectorData) data).mRepresentation = values;
            
        }

        @objid ("c2557b9c-9e71-4734-89d1-93da0954dcd7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindingSmClass)this.getTarget()).getConnectorRoleDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("5ea9c401-e886-451e-8990-b6da0dac5611")
    public static class RepresentedFeatureSmDependency extends SmSingleDependency {
        @objid ("60b111be-3017-4576-a4e9-940fe37b516d")
        private SmDependency symetricDep;

        @objid ("87073815-e922-4db7-9a54-600f02969106")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((NaryConnectorData) data).mRepresentedFeature;
        }

        @objid ("ca5e68df-e8c5-4974-afa5-663d5fb943a1")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((NaryConnectorData) data).mRepresentedFeature = value;
        }

        @objid ("83e5a0dc-a3d3-4fcd-b45e-0bf7914c8620")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getRepresentingConnectorDep();
            }
            return this.symetricDep;
            
        }

    }

}

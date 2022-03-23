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
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
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

@objid ("ce238240-a882-429d-80cd-99a484941658")
public class ExpansionNodeSmClass extends ObjectNodeSmClass {
    @objid ("059ca294-1861-4c1e-b0be-a5c83c4d2646")
    private SmDependency regionAsOutputDep;

    @objid ("37e31c91-f3fe-436b-b6f5-29c94b7b3dc5")
    private SmDependency regionAsInputDep;

    @objid ("a2ff501a-e4cd-487c-b6a0-132e187a5f29")
    public  ExpansionNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("07e54633-8766-496b-aa37-f4af131dd06d")
    @Override
    public String getName() {
        return "ExpansionNode";
        
    }

    @objid ("ebe33434-7828-419c-a242-9b6bfb0587ba")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("9ec2e4ec-6457-4452-8d5d-0306dc43d816")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ExpansionNode.class;
        
    }

    @objid ("944fa5c0-7aab-4168-8cf7-6cafd21e4107")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("46dc6095-3743-418f-ab35-42a3e48f9946")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("91572832-3db1-4c31-8f0d-7ba1e304e014")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ObjectNode.MQNAME);
        this.registerFactory(new ExpansionNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.regionAsOutputDep = new RegionAsOutputSmDependency();
        this.regionAsOutputDep.init("RegionAsOutput", this, metamodel.getMClass(ExpansionRegion.MQNAME), 0, 1 );
        registerDependency(this.regionAsOutputDep);
        
        this.regionAsInputDep = new RegionAsInputSmDependency();
        this.regionAsInputDep.init("RegionAsInput", this, metamodel.getMClass(ExpansionRegion.MQNAME), 0, 1 );
        registerDependency(this.regionAsInputDep);
        
        
    }

    @objid ("02ef3967-00e2-412a-ac39-5885bdddbe16")
    public SmDependency getRegionAsOutputDep() {
        if (this.regionAsOutputDep == null) {
        	this.regionAsOutputDep = this.getDependencyDef("RegionAsOutput");
        }
        return this.regionAsOutputDep;
    }

    @objid ("e637ac5a-2ae6-4994-9a5d-ef5bf2a7a32d")
    public SmDependency getRegionAsInputDep() {
        if (this.regionAsInputDep == null) {
        	this.regionAsInputDep = this.getDependencyDef("RegionAsInput");
        }
        return this.regionAsInputDep;
    }

    @objid ("d8485453-317c-4e8b-afb8-aacbf602cc68")
    private static class ExpansionNodeObjectFactory implements ISmObjectFactory {
        @objid ("c116ef35-a5de-44a5-80b0-884591b04424")
        private ExpansionNodeSmClass smClass;

        @objid ("88a1a22a-7233-44a5-ae24-d26835ecb77d")
        public  ExpansionNodeObjectFactory(ExpansionNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f5655a00-1265-4c6a-bc11-822686ae2227")
        @Override
        public ISmObjectData createData() {
            return new ExpansionNodeData(this.smClass);
        }

        @objid ("62f574ca-93eb-46e8-ab05-2854953b424e")
        @Override
        public SmObjectImpl createImpl() {
            return new ExpansionNodeImpl();
        }

    }

    @objid ("b628fe1b-7f4e-4964-95a2-9e94e41fd395")
    public static class RegionAsOutputSmDependency extends SmSingleDependency {
        @objid ("6d540977-d465-4396-b90c-19a95c4e6e22")
        private SmDependency symetricDep;

        @objid ("26ae39b2-c121-46ce-ac4a-21c05fb10e0f")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExpansionNodeData) data).mRegionAsOutput;
        }

        @objid ("af54507a-5cb3-44e1-8eb2-98cbf8a25eca")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExpansionNodeData) data).mRegionAsOutput = value;
        }

        @objid ("978a91b9-89a5-44c2-b2d0-9024abee970c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExpansionRegionSmClass)this.getTarget()).getOutputElementDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("ed1b8992-4d0b-4992-92a0-2322b7290def")
    public static class RegionAsInputSmDependency extends SmSingleDependency {
        @objid ("07ae1f01-95d1-48a0-b811-634bfd5c2036")
        private SmDependency symetricDep;

        @objid ("e4f0efc3-f171-480c-b46c-57b2259720a7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ExpansionNodeData) data).mRegionAsInput;
        }

        @objid ("8afd1754-ca0f-49e6-b98f-103385b5c785")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ExpansionNodeData) data).mRegionAsInput = value;
        }

        @objid ("4330bf22-1361-4e88-867b-b8e58e6d1bbe")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ExpansionRegionSmClass)this.getTarget()).getInputElementDep();
            }
            return this.symetricDep;
            
        }

    }

}

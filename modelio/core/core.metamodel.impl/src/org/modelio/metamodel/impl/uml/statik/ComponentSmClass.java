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
import org.modelio.metamodel.uml.statik.Class;
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
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("be561684-0faa-4b90-a406-2ee10bd255a4")
public class ComponentSmClass extends ClassSmClass {
    @objid ("da573146-1730-448f-b9d3-51bf59b63dd2")
    private SmDependency realizationDep;

    @objid ("f54a2083-318b-47da-90e0-e1adb5fe647a")
    public  ComponentSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("824bc59a-c857-4623-8355-8e1d8843c154")
    @Override
    public String getName() {
        return "Component";
        
    }

    @objid ("0976e137-0aa2-4223-95ed-325e01b80ccc")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("185ba293-1a9d-4a6a-bb0f-68bfec206bbf")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Component.class;
        
    }

    @objid ("144fed02-7256-409d-8cf3-b082059a8d1b")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("afed991e-45fc-4e4a-902f-ee08d9781610")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("9d6d0637-e4c7-4627-8b27-e1c18bedcc5a")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Class.MQNAME);
        this.registerFactory(new ComponentObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.realizationDep = new RealizationSmDependency();
        this.realizationDep.init("Realization", this, metamodel.getMClass(ComponentRealization.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.realizationDep);
        
        
    }

    @objid ("48de55f7-b18f-434c-90a6-ed9e635682b7")
    public SmDependency getRealizationDep() {
        if (this.realizationDep == null) {
        	this.realizationDep = this.getDependencyDef("Realization");
        }
        return this.realizationDep;
    }

    @objid ("1e6bcde6-6801-4d29-a18c-8c4b87018862")
    private static class ComponentObjectFactory implements ISmObjectFactory {
        @objid ("cf73f4fa-4db8-477b-90c7-fa4485446215")
        private ComponentSmClass smClass;

        @objid ("dea14be0-e443-458a-b29f-9728a54050fd")
        public  ComponentObjectFactory(ComponentSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("0c6e331e-365b-4222-b8f2-27341c1d3856")
        @Override
        public ISmObjectData createData() {
            return new ComponentData(this.smClass);
        }

        @objid ("cfc58d00-0814-4838-9b90-e48e0156ce28")
        @Override
        public SmObjectImpl createImpl() {
            return new ComponentImpl();
        }

    }

    @objid ("0a4c68d9-139d-4814-9c65-022298ad3cb6")
    public static class RealizationSmDependency extends SmMultipleDependency {
        @objid ("6e912c2c-5c51-4cb6-8374-8abd4b82df38")
        private SmDependency symetricDep;

        @objid ("1fe84fbf-b4c8-4dc3-a973-de08b434151c")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ComponentData)data).mRealization != null)? ((ComponentData)data).mRealization:SmMultipleDependency.EMPTY;
        }

        @objid ("563e7835-d629-4d4c-8d6c-ade9214db74d")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ComponentData) data).mRealization = values;
            
        }

        @objid ("e6e4b063-bef5-44f7-a541-449bd6e66364")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ComponentRealizationSmClass)this.getTarget()).getAbstractionDep();
            }
            return this.symetricDep;
            
        }

    }

}

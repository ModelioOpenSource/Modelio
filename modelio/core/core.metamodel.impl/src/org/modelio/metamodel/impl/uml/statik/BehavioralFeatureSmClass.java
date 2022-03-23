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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.BehavioralFeature;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("91104596-96f5-4349-af9b-a9f073e341b1")
public class BehavioralFeatureSmClass extends FeatureSmClass {
    @objid ("832b6bbd-4e3b-4128-b0a5-4a339b8f9d56")
    public  BehavioralFeatureSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("47bb0336-300a-491b-8a83-9263e022a11e")
    @Override
    public String getName() {
        return "BehavioralFeature";
        
    }

    @objid ("7aadb651-3127-4c76-8e7f-00e0cd699401")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("3783d2c0-944d-46c9-a8f1-bb54aa18f3d2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BehavioralFeature.class;
        
    }

    @objid ("2272a0f8-fdab-43a2-aea4-70fdf60fe412")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("76d0cf88-3353-46d8-8e3d-3f9511ddc6a7")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("979db336-7c31-4db8-a5dc-fb93cce05e3b")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Feature.MQNAME);
        this.registerFactory(new BehavioralFeatureObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("a50b7489-6d70-4547-a4a7-7c314167f60d")
    private static class BehavioralFeatureObjectFactory implements ISmObjectFactory {
        @objid ("ab863dc9-f683-4966-9f09-120ceb074894")
        private BehavioralFeatureSmClass smClass;

        @objid ("b2e16873-18aa-4d02-84a1-173659d86972")
        public  BehavioralFeatureObjectFactory(BehavioralFeatureSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("25a64dd9-f27d-42ee-ae89-8aca782a19bc")
        @Override
        public ISmObjectData createData() {
            return new BehavioralFeatureData(this.smClass);
        }

        @objid ("18443a0d-7e4d-4579-8658-7c01cc315978")
        @Override
        public SmObjectImpl createImpl() {
            return new BehavioralFeatureImpl();
        }

    }

}

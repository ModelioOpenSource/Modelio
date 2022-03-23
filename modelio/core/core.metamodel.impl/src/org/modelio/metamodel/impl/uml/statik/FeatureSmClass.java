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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("13761dd9-9bc8-4c3f-a387-24f38b54c85e")
public class FeatureSmClass extends UmlModelElementSmClass {
    @objid ("276b2e1b-7ce0-40fb-aaa0-b5ab42e76606")
    private SmAttribute visibilityAtt;

    @objid ("f6447f67-c48f-4b63-9050-80fe84923e2a")
    private SmAttribute isClassAtt;

    @objid ("a2ee31a6-dfcc-42a9-a432-cbb26f9aa60a")
    private SmAttribute isAbstractAtt;

    @objid ("f542718d-e7bb-4c22-8794-1b823de4fa91")
    public  FeatureSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("b728a591-bf89-4bca-82d7-f8dce2eff791")
    @Override
    public String getName() {
        return "Feature";
        
    }

    @objid ("6ecbdd03-d673-4ee7-9af3-db0e1f93db71")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("7bd6a92a-4d5a-41c8-948d-a235973c4873")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Feature.class;
        
    }

    @objid ("a7eac96d-7125-4653-a948-72b6ed6062e0")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("d3d08b57-4455-45ea-a197-07e0565387d5")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("b7f1ccb2-b2d8-48e1-885b-9e5d8a15e29d")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new FeatureObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.visibilityAtt = new VisibilitySmAttribute();
        this.visibilityAtt.init("Visibility", this, VisibilityMode.class );
        registerAttribute(this.visibilityAtt);
        
        this.isClassAtt = new IsClassSmAttribute();
        this.isClassAtt.init("IsClass", this, Boolean.class );
        registerAttribute(this.isClassAtt);
        
        this.isAbstractAtt = new IsAbstractSmAttribute();
        this.isAbstractAtt.init("IsAbstract", this, Boolean.class );
        registerAttribute(this.isAbstractAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("8860fb8e-5887-41d5-ad11-3dfaadd832d8")
    public SmAttribute getVisibilityAtt() {
        if (this.visibilityAtt == null) {
        	this.visibilityAtt = this.getAttributeDef("Visibility");
        }
        return this.visibilityAtt;
    }

    @objid ("5eddf621-e6e7-429b-85fb-6140cd0bc12f")
    public SmAttribute getIsClassAtt() {
        if (this.isClassAtt == null) {
        	this.isClassAtt = this.getAttributeDef("IsClass");
        }
        return this.isClassAtt;
    }

    @objid ("11df34c2-93bd-496e-af18-bfd69190d99b")
    public SmAttribute getIsAbstractAtt() {
        if (this.isAbstractAtt == null) {
        	this.isAbstractAtt = this.getAttributeDef("IsAbstract");
        }
        return this.isAbstractAtt;
    }

    @objid ("b13141e1-fccc-40b7-82c4-9dedd2b9e651")
    private static class FeatureObjectFactory implements ISmObjectFactory {
        @objid ("73a0e821-9f3a-4f61-a23e-83d070599050")
        private FeatureSmClass smClass;

        @objid ("a767a010-8977-4861-b2bb-f1e046aa414d")
        public  FeatureObjectFactory(FeatureSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c5e3a790-1f92-4609-b188-b1984bc4d3da")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("93b95857-bcb4-4bbc-9d99-2b6cc3368b3c")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("460bdf9f-498e-4579-b162-13a69df27b38")
    public static class VisibilitySmAttribute extends SmAttribute {
        @objid ("e4706388-977c-4c91-a2bd-5c48e4dc5779")
        public Object getValue(ISmObjectData data) {
            return ((FeatureData) data).mVisibility;
        }

        @objid ("36209c02-8f3d-41d8-82e0-368da49421c6")
        public void setValue(ISmObjectData data, Object value) {
            ((FeatureData) data).mVisibility = value;
        }

    }

    @objid ("2b9d64a2-d96d-41ae-8445-51ba590746bb")
    public static class IsClassSmAttribute extends SmAttribute {
        @objid ("c97ae267-f2ae-4d1f-ac1e-f1d0617331fd")
        public Object getValue(ISmObjectData data) {
            return ((FeatureData) data).mIsClass;
        }

        @objid ("be4075e1-01c4-4973-8d99-5ba913747e0d")
        public void setValue(ISmObjectData data, Object value) {
            ((FeatureData) data).mIsClass = value;
        }

    }

    @objid ("2f8a2ec0-bab3-43cb-a646-6702d5ab16f3")
    public static class IsAbstractSmAttribute extends SmAttribute {
        @objid ("0318b460-5cdb-47b7-8d21-d7aef6038d01")
        public Object getValue(ISmObjectData data) {
            return ((FeatureData) data).mIsAbstract;
        }

        @objid ("3c54ef6d-db08-492d-9a3c-d05891208a8b")
        public void setValue(ISmObjectData data, Object value) {
            ((FeatureData) data).mIsAbstract = value;
        }

    }

}

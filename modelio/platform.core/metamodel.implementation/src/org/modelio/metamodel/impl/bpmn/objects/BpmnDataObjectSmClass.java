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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemAwareElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("94f227aa-d368-490d-bc78-674fbc48e2a9")
public class BpmnDataObjectSmClass extends BpmnItemAwareElementSmClass {
    @objid ("cebd2560-a688-45e0-a20c-95f441ca8812")
    private SmAttribute isCollectionAtt;

    @objid ("ca6a1a56-057a-4bcc-93ae-09af0f5d3c43")
    public BpmnDataObjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("8107ea6c-9e4c-4baf-a115-ddb78d8bf5c0")
    @Override
    public String getName() {
        return "BpmnDataObject";
    }

    @objid ("e013900f-af02-4781-a478-cfed068741c5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0c6968b6-9247-4e17-ba28-935def9052f4")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnDataObject.class;
    }

    @objid ("8f2f3b9b-5b55-4a24-a454-0d11ec80ebf4")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("f18f135e-81e0-4c30-ba26-471a123c3cbc")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("b37fae58-36a4-4713-9d21-ad149a661ebf")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnItemAwareElement.MQNAME);
        this.registerFactory(new BpmnDataObjectObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isCollectionAtt = new IsCollectionSmAttribute();
        this.isCollectionAtt.init("IsCollection", this, Boolean.class );
        registerAttribute(this.isCollectionAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("2384d156-d9ce-4619-8f40-849726129910")
    public SmAttribute getIsCollectionAtt() {
        if (this.isCollectionAtt == null) {
        	this.isCollectionAtt = this.getAttributeDef("IsCollection");
        }
        return this.isCollectionAtt;
    }

    @objid ("63d20614-79e2-4d86-889f-6c059e148fee")
    private static class BpmnDataObjectObjectFactory implements ISmObjectFactory {
        @objid ("c510a4ca-32e6-490f-91e5-937bd843f273")
        private BpmnDataObjectSmClass smClass;

        @objid ("62cb576e-1a4f-4b3b-a9d0-79d76e120aaf")
        public BpmnDataObjectObjectFactory(BpmnDataObjectSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8b3cca88-7204-404d-830b-de871e9455d8")
        @Override
        public ISmObjectData createData() {
            return new BpmnDataObjectData(this.smClass);
        }

        @objid ("a67e47ed-66ce-4321-816b-e4ca021092a5")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnDataObjectImpl();
        }

    }

    @objid ("c701cb4f-9f0a-4864-87c4-b5b29e77b188")
    public static class IsCollectionSmAttribute extends SmAttribute {
        @objid ("aaf82f90-cbf2-470c-affb-e8a0efd0a715")
        public Object getValue(ISmObjectData data) {
            return ((BpmnDataObjectData) data).mIsCollection;
        }

        @objid ("197c0dbb-b0e9-4e0c-b2c0-6d9ef54adb01")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnDataObjectData) data).mIsCollection = value;
        }

    }

}

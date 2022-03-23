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
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("205ae75c-6818-4c12-8295-cb1fb852bc5d")
public class LoopNodeSmClass extends StructuredActivityNodeSmClass {
    @objid ("77d92dac-cff4-4a15-b606-d5dbd27396ce")
    private SmAttribute isTestedFirstAtt;

    @objid ("e4799eac-f769-4881-8917-581a7793a32f")
    private SmAttribute setupAtt;

    @objid ("3fc89cc9-9aa1-48ef-b5cd-5bcca975a4f8")
    private SmAttribute testAtt;

    @objid ("e209ece6-7daa-468e-97b4-2fbaf4391334")
    public  LoopNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("616d6816-326f-4a36-bb94-fd2850dc6b5c")
    @Override
    public String getName() {
        return "LoopNode";
        
    }

    @objid ("06273b77-34f3-4523-b35c-ec641b887940")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("771bf1b7-e8fa-4ddc-b678-542ebddb076b")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return LoopNode.class;
        
    }

    @objid ("53171e4b-135d-4d19-8bb9-472f886f4ac3")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("7ffed83d-8eea-4206-82e3-ce5e42459b44")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("0df093fa-ecca-43c6-b230-28758e929230")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(StructuredActivityNode.MQNAME);
        this.registerFactory(new LoopNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isTestedFirstAtt = new IsTestedFirstSmAttribute();
        this.isTestedFirstAtt.init("IsTestedFirst", this, Boolean.class );
        registerAttribute(this.isTestedFirstAtt);
        
        this.setupAtt = new SetupSmAttribute();
        this.setupAtt.init("Setup", this, String.class );
        registerAttribute(this.setupAtt);
        
        this.testAtt = new TestSmAttribute();
        this.testAtt.init("Test", this, String.class );
        registerAttribute(this.testAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("c24300b8-6d12-4561-b3ca-b1ff9e7914af")
    public SmAttribute getIsTestedFirstAtt() {
        if (this.isTestedFirstAtt == null) {
        	this.isTestedFirstAtt = this.getAttributeDef("IsTestedFirst");
        }
        return this.isTestedFirstAtt;
    }

    @objid ("f4f66b2f-eff1-4bb2-a07a-641b3cafa2d1")
    public SmAttribute getSetupAtt() {
        if (this.setupAtt == null) {
        	this.setupAtt = this.getAttributeDef("Setup");
        }
        return this.setupAtt;
    }

    @objid ("be239938-49a9-40ab-af6e-b6005a9e6bae")
    public SmAttribute getTestAtt() {
        if (this.testAtt == null) {
        	this.testAtt = this.getAttributeDef("Test");
        }
        return this.testAtt;
    }

    @objid ("c1deb14a-a81d-4cab-ac88-af00c6f18538")
    private static class LoopNodeObjectFactory implements ISmObjectFactory {
        @objid ("039606d5-0cc6-4b07-928d-8f9a6a96a5c5")
        private LoopNodeSmClass smClass;

        @objid ("cfb9f485-d338-42e3-8b24-ef53fe6bbeee")
        public  LoopNodeObjectFactory(LoopNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("eb4b02dc-d4ba-4ff5-871e-e7a0083a05c6")
        @Override
        public ISmObjectData createData() {
            return new LoopNodeData(this.smClass);
        }

        @objid ("d7915abe-54d7-44b3-93d9-802c47542fad")
        @Override
        public SmObjectImpl createImpl() {
            return new LoopNodeImpl();
        }

    }

    @objid ("d0bcfdb7-4db6-4f37-9745-fe95e3e9196d")
    public static class IsTestedFirstSmAttribute extends SmAttribute {
        @objid ("2c83c9a0-8912-4a37-b9e2-f1d9576e3736")
        public Object getValue(ISmObjectData data) {
            return ((LoopNodeData) data).mIsTestedFirst;
        }

        @objid ("8daae5df-61b3-49cb-8db3-f5d8636fe9c3")
        public void setValue(ISmObjectData data, Object value) {
            ((LoopNodeData) data).mIsTestedFirst = value;
        }

    }

    @objid ("413a3bbf-da89-43a1-9828-7e7ca1870863")
    public static class SetupSmAttribute extends SmAttribute {
        @objid ("55ec99dc-9224-40f5-8e7b-d8299b4ec7ea")
        public Object getValue(ISmObjectData data) {
            return ((LoopNodeData) data).mSetup;
        }

        @objid ("5aca3bcd-2442-4764-bd38-a544334cb8d0")
        public void setValue(ISmObjectData data, Object value) {
            ((LoopNodeData) data).mSetup = value;
        }

    }

    @objid ("52b73810-03f5-4351-b8da-79f353d40cc2")
    public static class TestSmAttribute extends SmAttribute {
        @objid ("c2638ab2-7b99-45ee-a1e6-9559beec9672")
        public Object getValue(ISmObjectData data) {
            return ((LoopNodeData) data).mTest;
        }

        @objid ("27412aac-af26-41ec-b87d-36f2d13002d3")
        public void setValue(ISmObjectData data, Object value) {
            ((LoopNodeData) data).mTest = value;
        }

    }

}

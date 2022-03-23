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

package org.modelio.metamodel.impl.bpmn.activities;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnScriptTask;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("498e9253-4950-4494-b75b-60929dff9589")
public class BpmnScriptTaskSmClass extends BpmnTaskSmClass {
    @objid ("d716e203-1e80-4a72-b51a-d0ec5da38019")
    private SmAttribute scriptLanguageAtt;

    @objid ("ea28cedd-7d0b-4e3d-8c46-de1388950485")
    private SmAttribute scriptAtt;

    @objid ("b8adac30-3e23-45f8-9709-45879676f2db")
    public  BpmnScriptTaskSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5f3836ff-3756-4945-b10d-fbbb31cf3c63")
    @Override
    public String getName() {
        return "BpmnScriptTask";
        
    }

    @objid ("2e7f50eb-f1cb-4fcd-af3e-ab19ddbc92eb")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("0c03bfb0-8ea9-42f2-92df-a89984fd9982")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnScriptTask.class;
        
    }

    @objid ("afc1d55f-da06-4f37-a655-b17328dc7285")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("817d72d2-79a2-424b-a99e-0b7b6d828147")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("831d347b-a8bc-49a6-8ebc-e3923354e379")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnTask.MQNAME);
        this.registerFactory(new BpmnScriptTaskObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.scriptLanguageAtt = new ScriptLanguageSmAttribute();
        this.scriptLanguageAtt.init("ScriptLanguage", this, String.class );
        registerAttribute(this.scriptLanguageAtt);
        
        this.scriptAtt = new ScriptSmAttribute();
        this.scriptAtt.init("Script", this, String.class );
        registerAttribute(this.scriptAtt);
        
        
        // Initialize and register the SmDependency
        
    }

    @objid ("b10ee5e4-d348-4420-b1e6-53765519220f")
    public SmAttribute getScriptLanguageAtt() {
        if (this.scriptLanguageAtt == null) {
        	this.scriptLanguageAtt = this.getAttributeDef("ScriptLanguage");
        }
        return this.scriptLanguageAtt;
    }

    @objid ("a5d65402-57f9-4dd5-92fe-6e044d75b84e")
    public SmAttribute getScriptAtt() {
        if (this.scriptAtt == null) {
        	this.scriptAtt = this.getAttributeDef("Script");
        }
        return this.scriptAtt;
    }

    @objid ("a9e12faf-bba4-4161-a92f-7336efc0fbed")
    private static class BpmnScriptTaskObjectFactory implements ISmObjectFactory {
        @objid ("99cdc914-3be1-4aef-9183-ec4433e3e047")
        private BpmnScriptTaskSmClass smClass;

        @objid ("c513509e-1295-4b30-ad53-b733a256cac5")
        public  BpmnScriptTaskObjectFactory(BpmnScriptTaskSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("4132309a-d126-468e-b01b-7f40b8936a18")
        @Override
        public ISmObjectData createData() {
            return new BpmnScriptTaskData(this.smClass);
        }

        @objid ("74c0209e-ec07-410a-80e7-22107474d176")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnScriptTaskImpl();
        }

    }

    @objid ("37461fd6-6dcd-44a7-96c4-e344098c8f5e")
    public static class ScriptLanguageSmAttribute extends SmAttribute {
        @objid ("fe57b153-b0dc-4994-883f-122cd26ba53d")
        public Object getValue(ISmObjectData data) {
            return ((BpmnScriptTaskData) data).mScriptLanguage;
        }

        @objid ("6bdaa2bf-d03a-485a-868f-5a46b5850aa3")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnScriptTaskData) data).mScriptLanguage = value;
        }

    }

    @objid ("4adcfbd3-d236-45db-9b08-f2f0bd755f15")
    public static class ScriptSmAttribute extends SmAttribute {
        @objid ("82de9daf-26f0-4a12-a6b6-7c3381e8afbe")
        public Object getValue(ISmObjectData data) {
            return ((BpmnScriptTaskData) data).mScript;
        }

        @objid ("0f84a25d-9937-4806-8660-7b0c41d1dd2f")
        public void setValue(ISmObjectData data, Object value) {
            ((BpmnScriptTaskData) data).mScript = value;
        }

    }

}

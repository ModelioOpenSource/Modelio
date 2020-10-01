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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.impl.diagrams.BehaviorDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("61ba724d-091b-4b0f-91b3-0545ad0201cc")
public class ActivityDiagramSmClass extends BehaviorDiagramSmClass {
    @objid ("f8169676-96d8-4726-86f3-8745957c9eff")
    private SmAttribute isVerticalAtt;

    @objid ("5c7cc3a7-a5af-4bcc-b25c-bd2e1445fb76")
    public ActivityDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7c44e118-ef26-4069-a78b-7dcfb3625234")
    @Override
    public String getName() {
        return "ActivityDiagram";
    }

    @objid ("d60836e4-1af6-494c-b937-27770aeb44d4")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("01c07777-f61b-458f-8f1b-fd82fe712d7f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ActivityDiagram.class;
    }

    @objid ("c0a6544e-4cb6-48d0-a75d-3545d314f6b9")
    @Override
    public boolean isCmsNode() {
        return true;
    }

    @objid ("6fb3e243-dcfb-46f0-8f52-271356cdcf5f")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("ca490925-09be-4daa-9532-b064f6f89002")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BehaviorDiagram.MQNAME);
        this.registerFactory(new ActivityDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isVerticalAtt = new IsVerticalSmAttribute();
        this.isVerticalAtt.init("IsVertical", this, Boolean.class );
        registerAttribute(this.isVerticalAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("97a719c4-c426-4363-a091-34b07c07c7ff")
    public SmAttribute getIsVerticalAtt() {
        if (this.isVerticalAtt == null) {
        	this.isVerticalAtt = this.getAttributeDef("IsVertical");
        }
        return this.isVerticalAtt;
    }

    @objid ("5db99d36-7099-4938-b022-e5259eda69e4")
    private static class ActivityDiagramObjectFactory implements ISmObjectFactory {
        @objid ("f7a64e11-6c1d-436f-8c51-1fd547dd48f2")
        private ActivityDiagramSmClass smClass;

        @objid ("c780937a-dfd7-48c6-aedc-9212f088bb42")
        public ActivityDiagramObjectFactory(ActivityDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("f56f5f18-51b3-46bc-aa83-1471297bf441")
        @Override
        public ISmObjectData createData() {
            return new ActivityDiagramData(this.smClass);
        }

        @objid ("3c884efc-a0fe-4c9e-a737-c757758da653")
        @Override
        public SmObjectImpl createImpl() {
            return new ActivityDiagramImpl();
        }

    }

    @objid ("68da4ff7-5b70-42e1-b6fe-c54fd787a986")
    public static class IsVerticalSmAttribute extends SmAttribute {
        @objid ("65d35ae1-7fca-4366-9c2a-7c9fc506e0df")
        public Object getValue(ISmObjectData data) {
            return ((ActivityDiagramData) data).mIsVertical;
        }

        @objid ("c281bd60-fef1-4dc6-bf20-a687b7baff68")
        public void setValue(ISmObjectData data, Object value) {
            ((ActivityDiagramData) data).mIsVertical = value;
        }

    }

}

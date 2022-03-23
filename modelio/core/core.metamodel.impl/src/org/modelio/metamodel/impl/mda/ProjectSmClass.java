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

package org.modelio.metamodel.impl.mda;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectSmClass;
import org.modelio.metamodel.impl.uml.statik.PackageSmClass;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("89372670-8d71-4434-8d2f-26ea95be7263")
public class ProjectSmClass extends AbstractProjectSmClass {
    @objid ("cd656d1e-e42d-4383-993b-fe9e704cae11")
    private SmAttribute projectContextAtt;

    @objid ("7ecad069-1fc8-4654-88bc-aea055bcc2fa")
    private SmAttribute projectDescrAtt;

    @objid ("c8e53bec-0a80-4a41-89f7-b00cefd2ea0d")
    private SmDependency modelDep;

    @objid ("2a6d8ece-5330-4cc5-a41e-a28f52240814")
    public  ProjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("46d8d08d-5306-4ac7-9cb4-d9ad7dba3b5f")
    @Override
    public String getName() {
        return "Project";
        
    }

    @objid ("9115168c-18a5-42ff-bba2-2c4d4103a824")
    @Override
    public Version getVersion() {
        return new Version("1.1.1");
    }

    @objid ("f075883a-f37e-43f0-a89c-42ea178edb51")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Project.class;
        
    }

    @objid ("219d913a-4f83-47f1-bdbd-4f18d9d1ad2f")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("00554c17-a6ab-4bb8-95a5-70735035cd95")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("431b72a2-70a4-4ee2-996e-b56594c71689")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractProject.MQNAME);
        this.registerFactory(new ProjectObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.projectContextAtt = new ProjectContextSmAttribute();
        this.projectContextAtt.init("ProjectContext", this, String.class );
        registerAttribute(this.projectContextAtt);
        
        this.projectDescrAtt = new ProjectDescrSmAttribute();
        this.projectDescrAtt.init("ProjectDescr", this, String.class );
        registerAttribute(this.projectDescrAtt);
        
        
        // Initialize and register the SmDependency
        this.modelDep = new ModelSmDependency();
        this.modelDep.init("Model", this, metamodel.getMClass(Package.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.modelDep);
        
        
    }

    @objid ("24ec0304-827e-49f0-8424-bc13e2f2105e")
    public SmAttribute getProjectContextAtt() {
        if (this.projectContextAtt == null) {
        	this.projectContextAtt = this.getAttributeDef("ProjectContext");
        }
        return this.projectContextAtt;
    }

    @objid ("79e7f9bc-93b5-497a-9b90-d5de333f857a")
    public SmAttribute getProjectDescrAtt() {
        if (this.projectDescrAtt == null) {
        	this.projectDescrAtt = this.getAttributeDef("ProjectDescr");
        }
        return this.projectDescrAtt;
    }

    @objid ("f8a9a728-e642-4758-ac5e-b57960345cb6")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("Model");
        }
        return this.modelDep;
    }

    @objid ("6b4c498e-c38f-4006-aeac-433540563dc8")
    @Override
    public boolean areOrphansAllowed() {
        return true;
        
    }

    @objid ("8184752d-2599-450a-9246-e93681627a23")
    private static class ProjectObjectFactory implements ISmObjectFactory {
        @objid ("76b450c0-9f75-4de3-b316-6088f8b36f01")
        private ProjectSmClass smClass;

        @objid ("dde8c122-7c6a-4602-b321-444292d87e2b")
        public  ProjectObjectFactory(ProjectSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("135e86e6-7f01-430f-9614-4935c1616c8c")
        @Override
        public ISmObjectData createData() {
            return new ProjectData(this.smClass);
        }

        @objid ("d51607dd-0f78-4c50-99e7-d4a33c48a648")
        @Override
        public SmObjectImpl createImpl() {
            return new ProjectImpl();
        }

    }

    @objid ("2efdf20b-cca3-46fe-aa1c-2646ca9c10ff")
    public static class ProjectContextSmAttribute extends SmAttribute {
        @objid ("538dfd35-8ae4-4665-bf94-c974154cb79b")
        public Object getValue(ISmObjectData data) {
            return ((ProjectData) data).mProjectContext;
        }

        @objid ("1615a9ec-4896-4750-b936-bec6ae221b03")
        public void setValue(ISmObjectData data, Object value) {
            ((ProjectData) data).mProjectContext = value;
        }

    }

    @objid ("c71b1c26-1dd3-402e-bac4-acbbc0aa9196")
    public static class ProjectDescrSmAttribute extends SmAttribute {
        @objid ("70743c10-4c0d-4b1e-8537-d73937539bf1")
        public Object getValue(ISmObjectData data) {
            return ((ProjectData) data).mProjectDescr;
        }

        @objid ("ad143d21-860d-4f95-bf00-35dcfb24bb83")
        public void setValue(ISmObjectData data, Object value) {
            ((ProjectData) data).mProjectDescr = value;
        }

    }

    @objid ("be0afe4a-c8bb-4d94-b258-cc355d2809ba")
    public static class ModelSmDependency extends SmMultipleDependency {
        @objid ("f1eedabf-5960-423f-ad52-7965504094e5")
        private SmDependency symetricDep;

        @objid ("ada72a9d-2b0b-4041-9dad-7dba5c0734f6")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ProjectData)data).mModel != null)? ((ProjectData)data).mModel:SmMultipleDependency.EMPTY;
        }

        @objid ("a38e3693-b976-4069-93e5-322c00dce07c")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ProjectData) data).mModel = values;
            
        }

        @objid ("afa59242-a77e-42c2-875c-2bcaa691ab30")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PackageSmClass)this.getTarget()).getRepresentedDep();
            }
            return this.symetricDep;
            
        }

    }

}

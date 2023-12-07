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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impl.diagrams.DiagramSetSmClass;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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

@objid ("8419f137-d5a6-4b2f-915a-84d538d77eae")
public class AbstractProjectSmClass extends ModelElementSmClass {
    @objid ("c32cd5a1-6525-4219-bea9-1640318cd0f4")
    private SmDependency diagramRootDep;

    @objid ("7df52c08-edce-4a3d-bf25-91f07c036a46")
    public  AbstractProjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("80d217ab-e837-41c8-991b-92a6e6bfcf02")
    @Override
    public String getName() {
        return "AbstractProject";
        
    }

    @objid ("60ef3b67-e0fe-45e0-a97b-ec606cfb1f4e")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("76e588cb-f6b9-4b38-a27f-da05799aa549")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractProject.class;
        
    }

    @objid ("cdac9bf8-9eae-4af5-af0a-f0b0fbcf0938")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("1a491f12-f0e4-4b0d-9941-f80a82cf87d1")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("4caed0da-3d73-4bdc-b744-28323e53e8fb")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new AbstractProjectObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.diagramRootDep = new DiagramRootSmDependency();
        this.diagramRootDep.init("DiagramRoot", this, metamodel.getMClass(DiagramSet.MQNAME), 1, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.diagramRootDep);
        
        
    }

    @objid ("aaeea318-c60f-4d1b-a8d5-b57465bc4e2f")
    public SmDependency getDiagramRootDep() {
        if (this.diagramRootDep == null) {
        	this.diagramRootDep = this.getDependencyDef("DiagramRoot");
        }
        return this.diagramRootDep;
    }

    @objid ("ac9933f6-c21c-438d-ba9f-a4d0f8687299")
    private static class AbstractProjectObjectFactory implements ISmObjectFactory {
        @objid ("027a8b29-aceb-4938-bca4-112180c1f9b5")
        private AbstractProjectSmClass smClass;

        @objid ("3b5aad63-6447-423f-99aa-3f639a140d8a")
        public  AbstractProjectObjectFactory(AbstractProjectSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("7830103f-e71d-4b45-bb82-bce1fb673a83")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("b7e22262-2691-434c-a488-ebed6e25347c")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("a9fe42c3-fa1a-4c91-8bb2-0fc33c0ebbae")
    public static class DiagramRootSmDependency extends SmSingleDependency {
        @objid ("916173ba-5869-416c-8d9a-46fb2b558d8a")
        private SmDependency symetricDep;

        @objid ("8a87d78a-5d11-4eae-be8f-7e89a09e6b85")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractProjectData) data).mDiagramRoot;
        }

        @objid ("7d3b58ec-b0f8-4a17-a799-25056b52d403")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractProjectData) data).mDiagramRoot = value;
        }

        @objid ("075c13f9-e798-4a2c-ab00-9ab532547355")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

}

/* 
 * Copyright 2013-2018 Modeliosoft
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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impl.diagrams.DiagramSetSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
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
    @objid ("8b671613-33ba-4cd5-913d-490752d340c1")
    private SmDependency diagramRootDep;

    @objid ("2921b674-b478-420c-ba23-35e4dd369974")
    public AbstractProjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("5a741ae9-80c7-469a-a002-c729b454ec86")
    @Override
    public String getName() {
        return "AbstractProject";
    }

    @objid ("1e1b4718-ab01-47cc-a3c3-b61b00343556")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("411e13d0-46c5-4ecf-afd1-2e16e9413ce9")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractProject.class;
    }

    @objid ("27252520-d033-4ed3-9bf9-d965fd3e9b05")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("0f19a325-d779-4ded-8425-36d4bb3615a9")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("1b7dc9c8-bc6b-4d5c-b15f-647dd85f4163")
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

    @objid ("a281ac9d-4a3c-474a-9e85-14b3628b8930")
    public SmDependency getDiagramRootDep() {
        if (this.diagramRootDep == null) {
        	this.diagramRootDep = this.getDependencyDef("DiagramRoot");
        }
        return this.diagramRootDep;
    }

    @objid ("ac9933f6-c21c-438d-ba9f-a4d0f8687299")
    private static class AbstractProjectObjectFactory implements ISmObjectFactory {
        @objid ("3d95bfb4-4f4f-4047-945c-a5c14e8957be")
        private AbstractProjectSmClass smClass;

        @objid ("d27b1c72-6bf7-4586-b7cc-743c148b696a")
        public AbstractProjectObjectFactory(AbstractProjectSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("b4305532-4eba-4fa6-a04c-2646b705b1e9")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("dcb3382a-7f1a-49bb-a62f-696f1fd84662")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("a9fe42c3-fa1a-4c91-8bb2-0fc33c0ebbae")
    public static class DiagramRootSmDependency extends SmSingleDependency {
        @objid ("7bfd8dbf-3de7-4212-a767-ddd209a0f164")
        private SmDependency symetricDep;

        @objid ("685965e1-4f67-40b3-acc6-347b5a49c8a4")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractProjectData) data).mDiagramRoot;
        }

        @objid ("c633512c-3d13-4944-92d2-f789e8c24956")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractProjectData) data).mDiagramRoot = value;
        }

        @objid ("fadf1eef-d8ef-4b34-be7f-4d44973abc95")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}

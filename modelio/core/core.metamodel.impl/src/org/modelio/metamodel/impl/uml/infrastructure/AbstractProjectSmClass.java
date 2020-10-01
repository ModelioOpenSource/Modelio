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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("d342e353-9b43-445a-af19-a38e2a45b024")
    private SmDependency diagramRootDep;

    @objid ("a0f88ae2-b60a-4f24-b659-29b4681ac428")
    public AbstractProjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("568ccd30-b902-44f3-b38b-2fc5a1e75150")
    @Override
    public String getName() {
        return "AbstractProject";
    }

    @objid ("9f6d49fa-e503-41e0-8ef2-ff6b40881d31")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("a4329762-7c24-4e5e-ac04-ec3f4ae861b4")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractProject.class;
    }

    @objid ("890e0f5d-bdaa-4ead-a7e3-38c89b6830d9")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("8184a3fa-da9b-49a8-97d7-20cba0466b19")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("3d6adf62-5c45-497e-98e4-c4b8b2e4fa66")
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

    @objid ("e5ce6da7-3422-46c3-986a-b142e002455e")
    public SmDependency getDiagramRootDep() {
        if (this.diagramRootDep == null) {
        	this.diagramRootDep = this.getDependencyDef("DiagramRoot");
        }
        return this.diagramRootDep;
    }

    @objid ("ac9933f6-c21c-438d-ba9f-a4d0f8687299")
    private static class AbstractProjectObjectFactory implements ISmObjectFactory {
        @objid ("2f9954be-99ff-4f77-8f4e-84587848d7ad")
        private AbstractProjectSmClass smClass;

        @objid ("6a3635ad-20cf-4ae7-a2da-17b3dd7c095f")
        public AbstractProjectObjectFactory(AbstractProjectSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("58cbbdac-00e5-4b0c-9c58-b5cd46bcb4a8")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("6aae4abd-540c-4303-85f4-6c8de4870d1d")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("a9fe42c3-fa1a-4c91-8bb2-0fc33c0ebbae")
    public static class DiagramRootSmDependency extends SmSingleDependency {
        @objid ("b64d06cd-90bf-41e2-abef-dcaae27f113c")
        private SmDependency symetricDep;

        @objid ("cb0219f6-24e1-4c22-b2ab-29f1a8167ec9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractProjectData) data).mDiagramRoot;
        }

        @objid ("66d6d202-c1e3-4df3-abad-72ceb8c632f7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractProjectData) data).mDiagramRoot = value;
        }

        @objid ("1678a1e7-973e-43a4-875f-96eabb005c76")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
        }

    }

}

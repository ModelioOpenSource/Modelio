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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramData;
import org.modelio.metamodel.impl.diagrams.DiagramSetSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("aa6381c6-52b5-44e0-a8e3-06e0f2ea2012")
public class AbstractDiagramSmClass extends ModelElementSmClass {
    @objid ("f6021522-1435-4910-8d13-bf414799410d")
    private SmAttribute uiDataVersionAtt;

    @objid ("419aafb8-008c-4f07-bb67-249f37ebe4f5")
    private SmAttribute uiDataAtt;

    @objid ("825079a5-12e2-49e6-9948-e4db09606d7c")
    private SmDependency representedDep;

    @objid ("355870ea-9af3-4513-b61e-a1265d2d39ce")
    private SmDependency referencingSetDep;

    @objid ("17430061-943c-4c55-9a7b-35a447b00c08")
    private SmDependency originDep;

    @objid ("13e6bff1-3947-48d4-b169-06a0aa8ae951")
    public AbstractDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("735c7ff5-e929-47f1-83f9-bf4b67d94514")
    @Override
    public String getName() {
        return "AbstractDiagram";
    }

    @objid ("530be63e-73bc-4c62-8084-97e81e31d104")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("6171fa0f-8e9e-4835-ad81-f5608498e88e")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractDiagram.class;
    }

    @objid ("ec2ed2e8-3bfe-4175-8bc6-251386ea5b30")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("752ab1d9-c2d3-4e63-bcfc-eec326fd25c0")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("6570ed18-fe30-4a3e-ba40-395420723604")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ModelElement.MQNAME);
        this.registerFactory(new AbstractDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.uiDataVersionAtt = new UiDataVersionSmAttribute();
        this.uiDataVersionAtt.init("UiDataVersion", this, Integer.class );
        registerAttribute(this.uiDataVersionAtt);
        
        this.uiDataAtt = new UiDataSmAttribute();
        this.uiDataAtt.init("UiData", this, String.class );
        registerAttribute(this.uiDataAtt);
        
        
        // Initialize and register the SmDependency
        this.representedDep = new RepresentedSmDependency();
        this.representedDep.init("Represented", this, metamodel.getMClass(Element.MQNAME), 0, -1 , SmDirective.SMCDPARTOF, SmDirective.SMCD_KEEP_DELETED_ON_READONLY);
        registerDependency(this.representedDep);
        
        this.referencingSetDep = new ReferencingSetSmDependency();
        this.referencingSetDep.init("ReferencingSet", this, metamodel.getMClass(DiagramSet.MQNAME), 0, -1 );
        registerDependency(this.referencingSetDep);
        
        this.originDep = new OriginSmDependency();
        this.originDep.init("Origin", this, metamodel.getMClass(ModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.originDep);
    }

    @objid ("9c31f7da-1634-4f90-97d1-0c16bfded23c")
    public SmAttribute getUiDataVersionAtt() {
        if (this.uiDataVersionAtt == null) {
        	this.uiDataVersionAtt = this.getAttributeDef("UiDataVersion");
        }
        return this.uiDataVersionAtt;
    }

    @objid ("6c25cb62-f11d-494f-82d8-d4e89499e386")
    public SmAttribute getUiDataAtt() {
        if (this.uiDataAtt == null) {
        	this.uiDataAtt = this.getAttributeDef("UiData");
        }
        return this.uiDataAtt;
    }

    @objid ("e787abfb-05df-4f76-8e42-13fb3bec2f4b")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("962d81d2-0b84-4e27-95a9-a54a39e999c3")
    public SmDependency getReferencingSetDep() {
        if (this.referencingSetDep == null) {
        	this.referencingSetDep = this.getDependencyDef("ReferencingSet");
        }
        return this.referencingSetDep;
    }

    @objid ("43d80aeb-5798-4d5a-8b88-6382741bde17")
    public SmDependency getOriginDep() {
        if (this.originDep == null) {
        	this.originDep = this.getDependencyDef("Origin");
        }
        return this.originDep;
    }

    @objid ("9db695ff-5be1-400d-a200-13c7083e6ab7")
    private static class AbstractDiagramObjectFactory implements ISmObjectFactory {
        @objid ("ebafc261-24cf-484b-913b-52403d97a759")
        private AbstractDiagramSmClass smClass;

        @objid ("97808a3a-0a4b-4ea8-a06d-d6e3e87b9862")
        public AbstractDiagramObjectFactory(AbstractDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("253d4b91-7148-435a-9e76-39675e3d438c")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("05443b07-5e20-4238-83ec-c0b292e2ac8a")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("e7934e10-7c02-42ac-bc42-ad84ac2b0fab")
    public static class UiDataVersionSmAttribute extends SmAttribute {
        @objid ("80d076c9-e260-40b3-88c0-9cd510857a06")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mUiDataVersion;
        }

        @objid ("bdc8ccb5-7218-45e5-a5e6-b189ce26d368")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mUiDataVersion = value;
        }

    }

    @objid ("e79509d1-26cd-4a66-bc3a-0641a97b3077")
    public static class UiDataSmAttribute extends SmAttribute {
        @objid ("12bb9254-aa57-4afa-a136-70c894df3694")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mUiData;
        }

        @objid ("24e59f36-51f5-4fee-b2bb-049a4f5b8a69")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mUiData = value;
        }

    }

    @objid ("a80bbeeb-a279-4a7a-89f8-c3ed37a9af1a")
    public static class OriginSmDependency extends SmSingleDependency {
        @objid ("bd3d32a0-64c7-49e2-b81f-178d1537cb38")
        private SmDependency symetricDep;

        @objid ("1d15d189-3aa1-49e9-ad7f-fd802500d129")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mOrigin;
        }

        @objid ("c12bb8ee-b9b8-4088-a611-56a17e13ca91")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractDiagramData) data).mOrigin = value;
        }

        @objid ("66659325-5075-49ac-ac5c-ac70ea83aadb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ModelElementSmClass)this.getTarget()).getProductDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("02661953-e41d-4139-9f36-c16ad1445f4d")
    public static class RepresentedSmDependency extends SmMultipleDependency {
        @objid ("47cc6fb2-172e-4f00-9a2d-324cdcf3c2f2")
        private SmDependency symetricDep;

        @objid ("bba9e043-7cf0-42e0-904a-3de8a9997b88")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AbstractDiagramData)data).mRepresented != null)? ((AbstractDiagramData)data).mRepresented:SmMultipleDependency.EMPTY;
        }

        @objid ("f4dba46e-80b2-41ee-8a16-a759be25f5dc")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AbstractDiagramData) data).mRepresented = values;
        }

        @objid ("0981a375-f0b9-403b-9875-b75c0ff5903e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ElementSmClass)this.getTarget()).getDiagramElementDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("d4afe4ed-e04a-4aae-9d08-d174cc645537")
    public static class ReferencingSetSmDependency extends SmMultipleDependency {
        @objid ("8c7427e7-ed0f-4ae4-8f83-45c92b96c4c6")
        private SmDependency symetricDep;

        @objid ("7f78cd35-178c-462c-a87f-e4bef104ff5d")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AbstractDiagramData)data).mReferencingSet != null)? ((AbstractDiagramData)data).mReferencingSet:SmMultipleDependency.EMPTY;
        }

        @objid ("7031544d-94ad-401e-ac2d-0bd7b294fea5")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AbstractDiagramData) data).mReferencingSet = values;
        }

        @objid ("f4e2dd39-e011-4cb5-96df-7fe51b499a6e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getReferencedDiagramDep();
            }
            return this.symetricDep;
        }

    }

}

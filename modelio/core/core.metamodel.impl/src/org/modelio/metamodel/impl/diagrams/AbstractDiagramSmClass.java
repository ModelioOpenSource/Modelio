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

package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
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
    @objid ("12f838b1-e1a9-4de7-9cb6-3338c81cb381")
    private SmAttribute uiDataVersionAtt;

    @objid ("fcf18a8b-1c5b-41f5-b6ea-7b5f0f5c67bf")
    private SmAttribute uiDataAtt;

    @objid ("3c74653f-e4dc-4384-a668-04781ce201da")
    private SmAttribute previewDataAtt;

    @objid ("22fdbd73-d70b-4c3e-815c-cc99fe487e13")
    private SmAttribute jsStructureAtt;

    @objid ("22edc3d1-16af-4b50-95d9-9ce1c138841e")
    private SmDependency representedDep;

    @objid ("e6bd4b29-5edf-48a8-8425-9de3f6c25634")
    private SmDependency referencingSetDep;

    @objid ("98566bc4-f3dd-45fb-9981-dcef7f0b289e")
    private SmDependency originDep;

    @objid ("373122ed-9c47-477b-92f1-bd962934f316")
    public  AbstractDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("7c88b994-f13e-46a6-980c-a0b38db2644d")
    @Override
    public String getName() {
        return "AbstractDiagram";
        
    }

    @objid ("65fd28d0-3ae5-4447-b82b-333c1f408661")
    @Override
    public Version getVersion() {
        return new Version("0.0.9055");
    }

    @objid ("79c7da6b-d3f4-441e-a6be-51d70b634408")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractDiagram.class;
        
    }

    @objid ("8abeeb2d-e6f0-4d96-ab85-1d6b9b995b7f")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("327189ea-69cc-4493-9b52-f37ebf4f82b0")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("9b6b869c-17d3-415e-af10-9ca669f9ff97")
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
        
        this.previewDataAtt = new PreviewDataSmAttribute();
        this.previewDataAtt.init("PreviewData", this, String.class );
        registerAttribute(this.previewDataAtt);
        
        this.jsStructureAtt = new JsStructureSmAttribute();
        this.jsStructureAtt.init("JsStructure", this, String.class );
        registerAttribute(this.jsStructureAtt);
        
        
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

    @objid ("7d61c8b5-1dff-4d93-8b0e-83ac04483a4a")
    public SmAttribute getUiDataVersionAtt() {
        if (this.uiDataVersionAtt == null) {
        	this.uiDataVersionAtt = this.getAttributeDef("UiDataVersion");
        }
        return this.uiDataVersionAtt;
    }

    @objid ("390abdc6-286a-4a51-a143-974ac2e935b9")
    public SmAttribute getUiDataAtt() {
        if (this.uiDataAtt == null) {
        	this.uiDataAtt = this.getAttributeDef("UiData");
        }
        return this.uiDataAtt;
    }

    @objid ("1eb253ba-efdf-414a-9e9d-c6b1c198aee6")
    public SmAttribute getPreviewDataAtt() {
        if (this.previewDataAtt == null) {
        	this.previewDataAtt = this.getAttributeDef("PreviewData");
        }
        return this.previewDataAtt;
    }

    @objid ("eff8f88e-5d0e-459c-a187-ab35dd1664e3")
    public SmAttribute getJsStructureAtt() {
        if (this.jsStructureAtt == null) {
        	this.jsStructureAtt = this.getAttributeDef("JsStructure");
        }
        return this.jsStructureAtt;
    }

    @objid ("1f79628c-4e85-47cb-a817-c07e333d463b")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("b0608b0d-d4fa-4b05-8f19-6461f6ef44a2")
    public SmDependency getReferencingSetDep() {
        if (this.referencingSetDep == null) {
        	this.referencingSetDep = this.getDependencyDef("ReferencingSet");
        }
        return this.referencingSetDep;
    }

    @objid ("b087ea69-20a3-43fc-a2e8-bc40dbefe5b3")
    public SmDependency getOriginDep() {
        if (this.originDep == null) {
        	this.originDep = this.getDependencyDef("Origin");
        }
        return this.originDep;
    }

    @objid ("9db695ff-5be1-400d-a200-13c7083e6ab7")
    private static class AbstractDiagramObjectFactory implements ISmObjectFactory {
        @objid ("c7f45a44-1df9-40f5-b8d6-c86405f45b96")
        private AbstractDiagramSmClass smClass;

        @objid ("da7867d3-5ede-4cf4-9e12-cdcd1ce90ef3")
        public  AbstractDiagramObjectFactory(AbstractDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("69cc8e82-420a-4efb-988e-a9f720045a8f")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("6dd422fd-aeae-4e8b-8745-5d0858fa4058")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("e7934e10-7c02-42ac-bc42-ad84ac2b0fab")
    public static class UiDataVersionSmAttribute extends SmAttribute {
        @objid ("e36f29ba-d45c-4f40-a5b9-f8a5f9369d27")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mUiDataVersion;
        }

        @objid ("31c6039a-0c6b-48e0-9dc9-9d84804cfa0b")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mUiDataVersion = value;
        }

    }

    @objid ("e79509d1-26cd-4a66-bc3a-0641a97b3077")
    public static class UiDataSmAttribute extends SmAttribute {
        @objid ("7693c66b-57ab-4d0b-a2e0-f1c4c0963f62")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mUiData;
        }

        @objid ("535682a3-434d-44d2-bf42-02a89f60e168")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mUiData = value;
        }

    }

    @objid ("a80bbeeb-a279-4a7a-89f8-c3ed37a9af1a")
    public static class OriginSmDependency extends SmSingleDependency {
        @objid ("2944ced5-eb76-4457-9175-15e8431f368e")
        private SmDependency symetricDep;

        @objid ("dd76b7f4-0c90-4914-a08d-768e295ecdf7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mOrigin;
        }

        @objid ("a5461b9c-34d2-4353-a63f-bb3883d87709")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractDiagramData) data).mOrigin = value;
        }

        @objid ("dcf2ed45-8554-4c46-baa9-12342a135c2f")
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
        @objid ("5ec739d9-36e6-46f2-8018-bb97abc9f584")
        private SmDependency symetricDep;

        @objid ("69d5093a-0b87-4192-91ee-db3ec053b856")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AbstractDiagramData)data).mRepresented != null)? ((AbstractDiagramData)data).mRepresented:SmMultipleDependency.EMPTY;
        }

        @objid ("8eb6ccc9-a518-41e7-9833-b281bedeb48e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AbstractDiagramData) data).mRepresented = values;
            
        }

        @objid ("fef6d31b-58e0-40d6-a81e-47b50cd11061")
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
        @objid ("ab851165-9351-4254-bd3e-485cd1d0628e")
        private SmDependency symetricDep;

        @objid ("eccdc090-e45c-4f5f-9ed0-fe36673395eb")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AbstractDiagramData)data).mReferencingSet != null)? ((AbstractDiagramData)data).mReferencingSet:SmMultipleDependency.EMPTY;
        }

        @objid ("9e25cb90-9f30-42da-927b-d5c03e050cd9")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AbstractDiagramData) data).mReferencingSet = values;
            
        }

        @objid ("d46a7f5d-f951-4df5-8a6d-02c37f1e97fb")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((DiagramSetSmClass)this.getTarget()).getReferencedDiagramDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("99f06712-5122-4204-83bd-490da28432c9")
    public static class PreviewDataSmAttribute extends SmAttribute {
        @objid ("7703493a-5063-49f2-8a82-1e2a411b9646")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mPreviewData;
        }

        @objid ("f3d09f7a-f87b-4898-ad53-f59134fce6ec")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mPreviewData = value;
        }

    }

    @objid ("9cd199dd-8ca5-4580-8fab-56572e5156ed")
    public static class JsStructureSmAttribute extends SmAttribute {
        @objid ("1b0ae690-e463-4fe9-86dc-2026c9e2ec91")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mJsStructure;
        }

        @objid ("3f672e18-0073-45df-afd1-ac0803a14007")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mJsStructure = value;
        }

    }

}

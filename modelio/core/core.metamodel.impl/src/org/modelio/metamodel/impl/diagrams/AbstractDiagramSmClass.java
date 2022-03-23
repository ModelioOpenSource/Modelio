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
    @objid ("39bc9092-68b1-47f9-88af-970883e9ab3a")
    private SmAttribute uiDataVersionAtt;

    @objid ("9fef8943-8f5a-4bd4-93a7-c9c9bca33509")
    private SmAttribute uiDataAtt;

    @objid ("3ea348ca-2c8b-4065-bc89-bfb480754e7a")
    private SmAttribute previewDataAtt;

    @objid ("39727f04-4f5d-4642-beb0-29ce1b28ba0e")
    private SmDependency representedDep;

    @objid ("b0887d63-3b8c-4814-97ac-dcbe460d9d5e")
    private SmDependency referencingSetDep;

    @objid ("48da6578-5a55-4760-9775-1ad9102e1ff4")
    private SmDependency originDep;

    @objid ("ec350b15-f1e9-443e-8a29-f006017c08a3")
    public  AbstractDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1d39ab3c-4833-4d09-ad58-cc4229340b23")
    @Override
    public String getName() {
        return "AbstractDiagram";
        
    }

    @objid ("03f5a386-138c-42fd-8c5a-f30a483225e5")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("20a1ecdc-4a89-40cb-92d9-ba7a2b46cc53")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AbstractDiagram.class;
        
    }

    @objid ("46e25967-ada5-426d-8184-7e6009684a76")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("66774ebe-ba84-4924-8d93-92dd74ec3dd1")
    @Override
    public boolean isAbstract() {
        return true;
        
    }

    @objid ("de532a06-c6ac-4b08-a616-70c299d4d3c6")
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

    @objid ("0a2bdfc8-b5de-4b6f-9085-9e554c40604c")
    public SmAttribute getUiDataVersionAtt() {
        if (this.uiDataVersionAtt == null) {
        	this.uiDataVersionAtt = this.getAttributeDef("UiDataVersion");
        }
        return this.uiDataVersionAtt;
    }

    @objid ("dd053184-6f31-49a8-b6eb-01eed1a3fff0")
    public SmAttribute getUiDataAtt() {
        if (this.uiDataAtt == null) {
        	this.uiDataAtt = this.getAttributeDef("UiData");
        }
        return this.uiDataAtt;
    }

    @objid ("fd7b0c79-32b3-4c35-98b9-23fddf389edf")
    public SmAttribute getPreviewDataAtt() {
        if (this.previewDataAtt == null) {
        	this.previewDataAtt = this.getAttributeDef("PreviewData");
        }
        return this.previewDataAtt;
    }

    @objid ("235de8da-c9fc-48df-b6b0-6587ea520f9d")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("9d81ab5b-b52a-472f-b63a-094ee2784aeb")
    public SmDependency getReferencingSetDep() {
        if (this.referencingSetDep == null) {
        	this.referencingSetDep = this.getDependencyDef("ReferencingSet");
        }
        return this.referencingSetDep;
    }

    @objid ("9c7161a5-cdfc-4921-b74d-77766d65e6f6")
    public SmDependency getOriginDep() {
        if (this.originDep == null) {
        	this.originDep = this.getDependencyDef("Origin");
        }
        return this.originDep;
    }

    @objid ("9db695ff-5be1-400d-a200-13c7083e6ab7")
    private static class AbstractDiagramObjectFactory implements ISmObjectFactory {
        @objid ("6efd9bc5-e4d4-439a-9871-5980cbee3b53")
        private AbstractDiagramSmClass smClass;

        @objid ("d1a98209-e837-4a09-90fc-56723ce5b8f9")
        public  AbstractDiagramObjectFactory(AbstractDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("8d44c9a5-6ddd-4452-8018-0ffe21c91691")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("09e49922-3832-4b6a-96cb-c26e3169ed72")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("e7934e10-7c02-42ac-bc42-ad84ac2b0fab")
    public static class UiDataVersionSmAttribute extends SmAttribute {
        @objid ("759ac299-3029-42cf-a94f-15c0a7089c78")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mUiDataVersion;
        }

        @objid ("31067bff-8b1b-4cca-af5d-6401b8b085a7")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mUiDataVersion = value;
        }

    }

    @objid ("e79509d1-26cd-4a66-bc3a-0641a97b3077")
    public static class UiDataSmAttribute extends SmAttribute {
        @objid ("589ecd4b-e3cc-4776-8675-faa63843383c")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mUiData;
        }

        @objid ("15cf4696-f336-45f9-8ce9-085c4f26531c")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mUiData = value;
        }

    }

    @objid ("a80bbeeb-a279-4a7a-89f8-c3ed37a9af1a")
    public static class OriginSmDependency extends SmSingleDependency {
        @objid ("5d9fd8b7-6c36-48d9-86c9-64c0bed27434")
        private SmDependency symetricDep;

        @objid ("568caecb-c9d3-4e14-a41b-6dbe91987953")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mOrigin;
        }

        @objid ("2804c26f-59a5-458f-b6f0-859941c35dfc")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AbstractDiagramData) data).mOrigin = value;
        }

        @objid ("79f7d625-e22f-4fd0-b16a-a248056f460b")
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
        @objid ("fdaa7a66-f16d-4daa-8725-2c4445dec579")
        private SmDependency symetricDep;

        @objid ("70d24eec-b0bd-4e85-99a0-07a41ad8c603")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AbstractDiagramData)data).mRepresented != null)? ((AbstractDiagramData)data).mRepresented:SmMultipleDependency.EMPTY;
        }

        @objid ("f3f5359b-56ca-431f-b512-a302b1fcbedd")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AbstractDiagramData) data).mRepresented = values;
            
        }

        @objid ("07528c4b-be8e-4372-99bb-834c288c51ae")
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
        @objid ("49c7b8bd-34e1-4d8e-8f35-818aa7414beb")
        private SmDependency symetricDep;

        @objid ("7984d2de-1779-4237-8677-3969116d64bb")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((AbstractDiagramData)data).mReferencingSet != null)? ((AbstractDiagramData)data).mReferencingSet:SmMultipleDependency.EMPTY;
        }

        @objid ("65b80d01-5b38-4af7-a69a-6e8658405f3b")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((AbstractDiagramData) data).mReferencingSet = values;
            
        }

        @objid ("e7a515d8-cb9d-4a79-965c-005203ebcc11")
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
        @objid ("1d9a3052-7512-466e-a42b-3040bab4ba25")
        public Object getValue(ISmObjectData data) {
            return ((AbstractDiagramData) data).mPreviewData;
        }

        @objid ("1f8aae7a-5235-4175-8181-471cee4dd47b")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractDiagramData) data).mPreviewData = value;
        }

    }

}

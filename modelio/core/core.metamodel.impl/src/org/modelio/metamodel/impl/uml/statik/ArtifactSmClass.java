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

package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.Node;
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

@objid ("1cf1c572-23cc-4aa8-b86c-f4409fc31cba")
public class ArtifactSmClass extends ClassifierSmClass {
    @objid ("df286364-4eaa-4af5-812d-accd1ebbdb6a")
    private SmAttribute fileNameAtt;

    @objid ("f685b86e-5d9b-4bd3-b97d-db150314ebc7")
    private SmDependency utilizedDep;

    @objid ("8b5b7738-0661-4526-9354-986685ce6da6")
    private SmDependency deploymentLocationDep;

    @objid ("fc4c22f2-ff1e-41c8-a5a2-6734f54d728d")
    public  ArtifactSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("0b6b0773-5e0d-4d0a-ba4c-36710686ab95")
    @Override
    public String getName() {
        return "Artifact";
        
    }

    @objid ("8e2a866c-bd24-459b-a4b2-027df2f8b94f")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d99666f7-27ac-4abd-96e0-85f6204cbb3f")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Artifact.class;
        
    }

    @objid ("48aa96cb-ac10-4a68-bbe8-15103226270b")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("1ab10964-70c8-4866-909e-6de38117b18f")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("264ce81f-719f-47c3-b507-16e60c9b94f3")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Classifier.MQNAME);
        this.registerFactory(new ArtifactObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.fileNameAtt = new FileNameSmAttribute();
        this.fileNameAtt.init("FileName", this, String.class );
        registerAttribute(this.fileNameAtt);
        
        
        // Initialize and register the SmDependency
        this.utilizedDep = new UtilizedSmDependency();
        this.utilizedDep.init("Utilized", this, metamodel.getMClass(Manifestation.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.utilizedDep);
        
        this.deploymentLocationDep = new DeploymentLocationSmDependency();
        this.deploymentLocationDep.init("DeploymentLocation", this, metamodel.getMClass(Node.MQNAME), 0, -1 );
        registerDependency(this.deploymentLocationDep);
        
        
    }

    @objid ("ab8a03cb-fe3b-4d47-8d89-8d9df26d0885")
    public SmAttribute getFileNameAtt() {
        if (this.fileNameAtt == null) {
        	this.fileNameAtt = this.getAttributeDef("FileName");
        }
        return this.fileNameAtt;
    }

    @objid ("543a7eec-29b0-4c42-afbc-2c57769b9175")
    public SmDependency getUtilizedDep() {
        if (this.utilizedDep == null) {
        	this.utilizedDep = this.getDependencyDef("Utilized");
        }
        return this.utilizedDep;
    }

    @objid ("ca8987d4-eb83-4e56-aee2-d2748a1114f4")
    public SmDependency getDeploymentLocationDep() {
        if (this.deploymentLocationDep == null) {
        	this.deploymentLocationDep = this.getDependencyDef("DeploymentLocation");
        }
        return this.deploymentLocationDep;
    }

    @objid ("0d1b5320-12c1-4d07-af17-653f95fa2fcb")
    private static class ArtifactObjectFactory implements ISmObjectFactory {
        @objid ("f910b672-ec7c-41a8-8d3c-802a3c842251")
        private ArtifactSmClass smClass;

        @objid ("b7aac80b-d762-43ad-a646-50ad944786b2")
        public  ArtifactObjectFactory(ArtifactSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("27d823e6-305e-48cd-942d-d9e9b7d542f0")
        @Override
        public ISmObjectData createData() {
            return new ArtifactData(this.smClass);
        }

        @objid ("7de16b2a-63b9-4c5f-bd9e-ba8b738c7c98")
        @Override
        public SmObjectImpl createImpl() {
            return new ArtifactImpl();
        }

    }

    @objid ("7c2dfdbc-5877-4c03-8170-7fbb981ca50b")
    public static class FileNameSmAttribute extends SmAttribute {
        @objid ("72d79af5-007c-4b0e-9bce-901a7168cc3c")
        public Object getValue(ISmObjectData data) {
            return ((ArtifactData) data).mFileName;
        }

        @objid ("ca97250c-24b7-488d-9495-a47cc7123479")
        public void setValue(ISmObjectData data, Object value) {
            ((ArtifactData) data).mFileName = value;
        }

    }

    @objid ("09817731-f7c3-4681-a606-9acfceade331")
    public static class UtilizedSmDependency extends SmMultipleDependency {
        @objid ("e2041aa1-6ba6-4cc9-b7f0-aa856e700f4b")
        private SmDependency symetricDep;

        @objid ("3c215e64-15fb-423a-979a-06ec1136d59f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ArtifactData)data).mUtilized != null)? ((ArtifactData)data).mUtilized:SmMultipleDependency.EMPTY;
        }

        @objid ("dabe7534-8f56-4c62-9f30-1d6c2a6cae97")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ArtifactData) data).mUtilized = values;
            
        }

        @objid ("f004287c-3e6b-4d08-81fb-6c3a633d6188")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ManifestationSmClass)this.getTarget()).getOwnerDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c52ee089-d7f5-4362-8ff0-6bed938a7e0d")
    public static class DeploymentLocationSmDependency extends SmMultipleDependency {
        @objid ("65684309-be1d-4949-a1e0-be27dc1e9657")
        private SmDependency symetricDep;

        @objid ("eb9ab7e1-a093-4106-bd26-4e25904a8854")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ArtifactData)data).mDeploymentLocation != null)? ((ArtifactData)data).mDeploymentLocation:SmMultipleDependency.EMPTY;
        }

        @objid ("59d9944f-b555-4085-9439-da5dfa578c11")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ArtifactData) data).mDeploymentLocation = values;
            
        }

        @objid ("f256e143-2bf2-4fc8-8580-b95095228682")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NodeSmClass)this.getTarget()).getResidentDep();
            }
            return this.symetricDep;
            
        }

    }

}

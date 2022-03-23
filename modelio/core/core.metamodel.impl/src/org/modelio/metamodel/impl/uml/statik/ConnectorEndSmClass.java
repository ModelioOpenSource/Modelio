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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("9be2b5a2-c153-473d-878e-f3214b6f8407")
public class ConnectorEndSmClass extends LinkEndSmClass {
    @objid ("a4870e1e-d1f2-4cfb-9ed3-274e35c0f742")
    private SmDependency representationDep;

    @objid ("38f6d068-e553-4f5d-b5ef-4d4d91e04979")
    private SmDependency representedFeatureDep;

    @objid ("60d79536-d3d0-4107-b8f1-e3cf4f694b89")
    public  ConnectorEndSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("d502412a-7d5f-4d1a-bc5d-25a5c278d4f8")
    @Override
    public String getName() {
        return "ConnectorEnd";
        
    }

    @objid ("1bd94f08-d7d1-44b5-ac93-c78e91f3d29e")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("8f503c89-323e-4732-ab7b-9bea686d8450")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ConnectorEnd.class;
        
    }

    @objid ("315861da-c9c0-4fa7-93da-d4ce1ed95dc1")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("0c9eebfc-2749-44a2-a929-e945323f9100")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("7cb8d2c4-6172-43bf-a81e-04aefcf3c2bb")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(LinkEnd.MQNAME);
        this.registerFactory(new ConnectorEndObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.representationDep = new RepresentationSmDependency();
        this.representationDep.init("Representation", this, metamodel.getMClass(Binding.MQNAME), 0, -1 );
        registerDependency(this.representationDep);
        
        this.representedFeatureDep = new RepresentedFeatureSmDependency();
        this.representedFeatureDep.init("RepresentedFeature", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedFeatureDep);
        
        
    }

    @objid ("9c897b94-7edb-4b96-ace1-617046d3b70e")
    public SmDependency getRepresentationDep() {
        if (this.representationDep == null) {
        	this.representationDep = this.getDependencyDef("Representation");
        }
        return this.representationDep;
    }

    @objid ("fd1ad25b-8409-4b5f-9d93-910cd043a98d")
    public SmDependency getRepresentedFeatureDep() {
        if (this.representedFeatureDep == null) {
        	this.representedFeatureDep = this.getDependencyDef("RepresentedFeature");
        }
        return this.representedFeatureDep;
    }

    @objid ("e52bbb10-d3b6-4ef7-8696-6c336ec1fe36")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("5908bc43-89a0-4325-8a8e-75366d61fcba")
    private static class ConnectorEndObjectFactory implements ISmObjectFactory {
        @objid ("19c542e7-c4a2-48d4-ba52-d23684ef3ad4")
        private ConnectorEndSmClass smClass;

        @objid ("8a3e758d-2114-4b11-9642-d0472c57c1f8")
        public  ConnectorEndObjectFactory(ConnectorEndSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d1ac712a-8423-424e-92f5-7c105501949b")
        @Override
        public ISmObjectData createData() {
            return new ConnectorEndData(this.smClass);
        }

        @objid ("6a604b0c-7f14-4ad9-858a-57f0c4ede8ff")
        @Override
        public SmObjectImpl createImpl() {
            return new ConnectorEndImpl();
        }

    }

    @objid ("27f4f1ae-514f-40be-883e-af22c0685f12")
    public static class RepresentationSmDependency extends SmMultipleDependency {
        @objid ("b887a46f-c5d2-4841-bea0-549f92b865a4")
        private SmDependency symetricDep;

        @objid ("c348b24e-3d4f-4b35-9399-e4dcf5084d9f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((ConnectorEndData)data).mRepresentation != null)? ((ConnectorEndData)data).mRepresentation:SmMultipleDependency.EMPTY;
        }

        @objid ("93add3d2-b409-4746-b1ef-86b0bbe89d38")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((ConnectorEndData) data).mRepresentation = values;
            
        }

        @objid ("93b3bc71-fa58-44a2-8c46-4f74002d6f90")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BindingSmClass)this.getTarget()).getConnectorEndRoleDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("55b2a698-e612-457a-a827-bfc7cf2972eb")
    public static class RepresentedFeatureSmDependency extends SmSingleDependency {
        @objid ("820bcf10-ae24-47b1-bfc7-4b69c58155b0")
        private SmDependency symetricDep;

        @objid ("4564709f-f4c8-493e-96d7-593e51257430")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((ConnectorEndData) data).mRepresentedFeature;
        }

        @objid ("0317ac68-ef69-4470-903a-98aeeae5ac10")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((ConnectorEndData) data).mRepresentedFeature = value;
        }

        @objid ("4ab9c8a4-8b92-46e6-97e5-a1602ad9c843")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getRepresentingEndDep();
            }
            return this.symetricDep;
            
        }

    }

}

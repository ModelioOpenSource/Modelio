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

package org.modelio.metamodel.impl.bpmn.bpmnService;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnService.BpmnEndPoint;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnParticipantSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("cda66a80-ee68-4580-a0eb-53130d952aaa")
public class BpmnEndPointSmClass extends BpmnSharedElementSmClass {
    @objid ("5dbdd8fc-526c-407b-ada1-c51d5416c25e")
    private SmDependency participantRefsDep;

    @objid ("aaa93156-97c1-426d-9db9-5db7daff1fac")
    public  BpmnEndPointSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("914c6e9b-60e6-4670-acd0-27efbd8fc800")
    @Override
    public String getName() {
        return "BpmnEndPoint";
        
    }

    @objid ("aa4080f8-be36-4462-a8c2-ca3df2151b0d")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("d202d931-8592-432e-9f0d-769b96157d67")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BpmnEndPoint.class;
        
    }

    @objid ("11ebece1-eb0f-4baf-a616-e152beecab28")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("fa7a2a3d-ee3b-4989-92d5-fd17d2075863")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("09e96bad-522b-4b90-bba2-99a1f0065c50")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BpmnSharedElement.MQNAME);
        this.registerFactory(new BpmnEndPointObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.participantRefsDep = new ParticipantRefsSmDependency();
        this.participantRefsDep.init("ParticipantRefs", this, metamodel.getMClass(BpmnParticipant.MQNAME), 0, -1 );
        registerDependency(this.participantRefsDep);
        
        
    }

    @objid ("ee96e4f2-98f5-485b-ae0b-ffd208c02e9f")
    public SmDependency getParticipantRefsDep() {
        if (this.participantRefsDep == null) {
        	this.participantRefsDep = this.getDependencyDef("ParticipantRefs");
        }
        return this.participantRefsDep;
    }

    @objid ("8be21d69-8110-467a-ac71-3b08dba110cb")
    private static class BpmnEndPointObjectFactory implements ISmObjectFactory {
        @objid ("285ec09f-a258-4941-b5e6-dacc05f90b24")
        private BpmnEndPointSmClass smClass;

        @objid ("bb42a1f8-2469-45ac-8a1b-8effcec5dcf7")
        public  BpmnEndPointObjectFactory(BpmnEndPointSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("995536ec-02fe-46f9-9ebc-194fbd5e907f")
        @Override
        public ISmObjectData createData() {
            return new BpmnEndPointData(this.smClass);
        }

        @objid ("1764b933-3f8a-48ea-8560-68c83eb5927c")
        @Override
        public SmObjectImpl createImpl() {
            return new BpmnEndPointImpl();
        }

    }

    @objid ("6ca99cb5-deea-40a1-b498-23fac4c28177")
    public static class ParticipantRefsSmDependency extends SmMultipleDependency {
        @objid ("6284dd90-27c8-4bed-afdb-09d431803361")
        private SmDependency symetricDep;

        @objid ("f1df8947-af3e-4cfa-bf12-f052aa300e07")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((BpmnEndPointData)data).mParticipantRefs != null)? ((BpmnEndPointData)data).mParticipantRefs:SmMultipleDependency.EMPTY;
        }

        @objid ("5e3fcc15-f32f-4e1d-8e3a-2e97b99f0a63")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((BpmnEndPointData) data).mParticipantRefs = values;
            
        }

        @objid ("53f64879-811e-431f-a29f-ecb6a1432d6a")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((BpmnParticipantSmClass)this.getTarget()).getEndPointRefsDep();
            }
            return this.symetricDep;
            
        }

    }

}

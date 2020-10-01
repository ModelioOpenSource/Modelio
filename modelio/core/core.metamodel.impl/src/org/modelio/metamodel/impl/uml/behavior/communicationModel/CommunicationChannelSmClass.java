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
package org.modelio.metamodel.impl.uml.behavior.communicationModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationChannelData;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationMessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationNodeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.LinkSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryLinkSmClass;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.NaryLink;
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

@objid ("74a6b528-ebe4-421d-bf3f-31899335ac89")
public class CommunicationChannelSmClass extends UmlModelElementSmClass {
    @objid ("c6e28066-5a91-4de1-98a4-93205e5a8785")
    private SmDependency startToEndMessageDep;

    @objid ("b00f1efc-68a6-43fd-9c57-7a68ba8273bc")
    private SmDependency channelDep;

    @objid ("f42f7576-6251-4105-873b-2cf35c13ba32")
    private SmDependency startDep;

    @objid ("7d088e91-5f01-436f-8c93-04027d3b49b7")
    private SmDependency naryChannelDep;

    @objid ("34705095-ca0e-4fa9-b1a6-3fbbbca9efa6")
    private SmDependency endToStartMessageDep;

    @objid ("d50999c8-7042-4a37-a964-235210812835")
    private SmDependency endDep;

    @objid ("c7089942-3f74-4525-b1c7-3e10b5d23f8f")
    public CommunicationChannelSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("129845d8-1cb7-4583-b18c-e1eceac46a02")
    @Override
    public String getName() {
        return "CommunicationChannel";
    }

    @objid ("ffa3b545-abd9-4fdd-90eb-a09cb57070b1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("26245897-b74e-419c-b3ed-a5c230261aa2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return CommunicationChannel.class;
    }

    @objid ("545cc839-9baf-454a-b01d-ed44ce0b83ff")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("8b42baf2-560a-4ad3-898a-700026e68ac8")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("836388a8-6e14-40db-be7a-776e249ee12f")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new CommunicationChannelObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.startToEndMessageDep = new StartToEndMessageSmDependency();
        this.startToEndMessageDep.init("StartToEndMessage", this, metamodel.getMClass(CommunicationMessage.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.startToEndMessageDep);
        
        this.channelDep = new ChannelSmDependency();
        this.channelDep.init("Channel", this, metamodel.getMClass(Link.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.channelDep);
        
        this.startDep = new StartSmDependency();
        this.startDep.init("Start", this, metamodel.getMClass(CommunicationNode.MQNAME), 0, 1 , SmDirective.SMCDLINKSOURCE);
        registerDependency(this.startDep);
        
        this.naryChannelDep = new NaryChannelSmDependency();
        this.naryChannelDep.init("NaryChannel", this, metamodel.getMClass(NaryLink.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.naryChannelDep);
        
        this.endToStartMessageDep = new EndToStartMessageSmDependency();
        this.endToStartMessageDep.init("EndToStartMessage", this, metamodel.getMClass(CommunicationMessage.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.endToStartMessageDep);
        
        this.endDep = new EndSmDependency();
        this.endDep.init("End", this, metamodel.getMClass(CommunicationNode.MQNAME), 0, 1 , SmDirective.SMCDLINKTARGET, SmDirective.SMCDPARTOF);
        registerDependency(this.endDep);
    }

    @objid ("91b4ba32-fc8d-42a7-8eb9-e98ab3da64a4")
    public SmDependency getStartToEndMessageDep() {
        if (this.startToEndMessageDep == null) {
        	this.startToEndMessageDep = this.getDependencyDef("StartToEndMessage");
        }
        return this.startToEndMessageDep;
    }

    @objid ("a726760a-5afe-41bf-8f98-605536c554c5")
    public SmDependency getChannelDep() {
        if (this.channelDep == null) {
        	this.channelDep = this.getDependencyDef("Channel");
        }
        return this.channelDep;
    }

    @objid ("a4790f91-7666-4a17-89e2-89d7c20772ec")
    public SmDependency getStartDep() {
        if (this.startDep == null) {
        	this.startDep = this.getDependencyDef("Start");
        }
        return this.startDep;
    }

    @objid ("5caa681b-0916-42cc-863d-9fb13a8f2e06")
    public SmDependency getNaryChannelDep() {
        if (this.naryChannelDep == null) {
        	this.naryChannelDep = this.getDependencyDef("NaryChannel");
        }
        return this.naryChannelDep;
    }

    @objid ("b5555d13-fc8e-444f-a3ba-a650633b7393")
    public SmDependency getEndToStartMessageDep() {
        if (this.endToStartMessageDep == null) {
        	this.endToStartMessageDep = this.getDependencyDef("EndToStartMessage");
        }
        return this.endToStartMessageDep;
    }

    @objid ("f3394b6c-33ab-46e9-8cd9-963a7e091bfb")
    public SmDependency getEndDep() {
        if (this.endDep == null) {
        	this.endDep = this.getDependencyDef("End");
        }
        return this.endDep;
    }

    @objid ("eadc8de9-d7f5-469b-a829-30c06b175585")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("4297b476-7a04-4d34-8e78-e76630688d26")
    private static class CommunicationChannelObjectFactory implements ISmObjectFactory {
        @objid ("467c6b68-6374-416e-bccb-68919b5364fb")
        private CommunicationChannelSmClass smClass;

        @objid ("af8648b2-09b3-4bfe-8f4c-193ca361dc36")
        public CommunicationChannelObjectFactory(CommunicationChannelSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c33a308e-6815-4e2f-9654-9bf60956ab82")
        @Override
        public ISmObjectData createData() {
            return new CommunicationChannelData(this.smClass);
        }

        @objid ("341e2764-96ca-4ddc-8977-191a7b858208")
        @Override
        public SmObjectImpl createImpl() {
            return new CommunicationChannelImpl();
        }

    }

    @objid ("a9112456-f700-49de-8fa4-99ca4656bbac")
    public static class StartToEndMessageSmDependency extends SmMultipleDependency {
        @objid ("436f8a7b-71de-4e8a-b953-4a5fe349a72e")
        private SmDependency symetricDep;

        @objid ("72783149-43de-4414-9247-b83540d24b6f")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CommunicationChannelData)data).mStartToEndMessage != null)? ((CommunicationChannelData)data).mStartToEndMessage:SmMultipleDependency.EMPTY;
        }

        @objid ("56d20d2a-9691-422e-9c88-887f9a374be2")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CommunicationChannelData) data).mStartToEndMessage = values;
        }

        @objid ("e299060c-07c8-4c0d-9f15-98a85fd8897e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationMessageSmClass)this.getTarget()).getChannelDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("0fb26925-0971-4da1-8a5e-7f4d2c100b71")
    public static class ChannelSmDependency extends SmSingleDependency {
        @objid ("0cd13eb5-b8a1-49b1-be9f-8f80ff944737")
        private SmDependency symetricDep;

        @objid ("0a18b75e-8657-4d8d-9f66-2a6524b326f1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationChannelData) data).mChannel;
        }

        @objid ("55e9d9a0-a41b-4336-9176-4762c47db2ee")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationChannelData) data).mChannel = value;
        }

        @objid ("7f247f97-bf65-46d7-8f32-3582bfc550e4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkSmClass)this.getTarget()).getSentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("33fc050c-5994-4c27-8fed-923f999de375")
    public static class StartSmDependency extends SmSingleDependency {
        @objid ("2af151b2-138a-49b9-b9f2-72f83e17229c")
        private SmDependency symetricDep;

        @objid ("4f3d4605-6edd-4f95-8c05-391dffef2b58")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationChannelData) data).mStart;
        }

        @objid ("bb27d635-1f70-46cf-bbae-63413e3196b7")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationChannelData) data).mStart = value;
        }

        @objid ("9b561763-0afd-4d44-a5ea-26a4b6a924f0")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationNodeSmClass)this.getTarget()).getStartedDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("5fdacd0c-1b9f-472a-9469-37fc97ae4f79")
    public static class NaryChannelSmDependency extends SmSingleDependency {
        @objid ("b89d3aac-6790-4b34-8253-189f3a7c8dfa")
        private SmDependency symetricDep;

        @objid ("2b608954-83f1-4485-a854-9985d3e7442d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationChannelData) data).mNaryChannel;
        }

        @objid ("6ce3e787-2c7e-43d0-9bd2-86cc10cf8d31")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationChannelData) data).mNaryChannel = value;
        }

        @objid ("c36ec733-5eed-4bb4-906f-802542771def")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NaryLinkSmClass)this.getTarget()).getSentDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("80567e7b-d94f-41ec-93b7-00d2ae37d7fa")
    public static class EndToStartMessageSmDependency extends SmMultipleDependency {
        @objid ("9339a803-e303-49ec-8ae8-ac7b8f934c0a")
        private SmDependency symetricDep;

        @objid ("f9746a37-bc46-4d7a-962a-da93f34ef1c9")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((CommunicationChannelData)data).mEndToStartMessage != null)? ((CommunicationChannelData)data).mEndToStartMessage:SmMultipleDependency.EMPTY;
        }

        @objid ("31b72279-9878-4ea3-9cf8-91a94dd13bf7")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((CommunicationChannelData) data).mEndToStartMessage = values;
        }

        @objid ("acb84df7-19c1-4adf-ae2d-a4de11ba26fe")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationMessageSmClass)this.getTarget()).getInvertedChannelDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2c122289-dcd3-41b3-9786-1d475b2dd299")
    public static class EndSmDependency extends SmSingleDependency {
        @objid ("8805e7b8-eb97-49d3-90f9-af5a45266ff1")
        private SmDependency symetricDep;

        @objid ("b772e2f1-3ae0-446e-a591-cddc99072138")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((CommunicationChannelData) data).mEnd;
        }

        @objid ("ca6d7f86-fbdd-40ce-97d7-bd645cccf583")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((CommunicationChannelData) data).mEnd = value;
        }

        @objid ("e96fd328-f2d9-4fec-bff2-0e936bc8eaae")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationNodeSmClass)this.getTarget()).getEndedDep();
            }
            return this.symetricDep;
        }

    }

}

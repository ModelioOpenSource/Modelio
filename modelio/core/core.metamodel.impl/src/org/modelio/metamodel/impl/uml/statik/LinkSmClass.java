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
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationChannelSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Link;
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

@objid ("d2454b12-e12b-41af-b5aa-cc2f011ab2ab")
public class LinkSmClass extends UmlModelElementSmClass {
    @objid ("e842ee1f-42d4-4a36-b365-293eec1a8290")
    private SmDependency modelDep;

    @objid ("9ba49cd8-01d4-45d8-ac73-ec6c28d6de0e")
    private SmDependency linkEndDep;

    @objid ("38d83125-888b-4553-a277-8f07407d9b74")
    private SmDependency sentDep;

    @objid ("2d03b41e-42a9-4647-91d6-5cb6f170226d")
    public  LinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("9817c5c5-f1a1-45f3-a32c-63a60ecd8532")
    @Override
    public String getName() {
        return "Link";
        
    }

    @objid ("776ce48e-ccbc-46e2-9dc5-ab8509794d74")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("35668fb4-bbc7-4262-aec1-883ec475f364")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Link.class;
        
    }

    @objid ("47875c7c-7618-446b-9471-f6bcfc715c9e")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("26a937c1-72ac-4abf-a18c-81cf29bce594")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("34119191-94d3-4231-8f17-c71f556c29f0")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new LinkObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.modelDep = new ModelSmDependency();
        this.modelDep.init("Model", this, metamodel.getMClass(Association.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.modelDep);
        
        this.linkEndDep = new LinkEndSmDependency();
        this.linkEndDep.init("LinkEnd", this, metamodel.getMClass(LinkEnd.MQNAME), 2, 2 );
        registerDependency(this.linkEndDep);
        
        this.sentDep = new SentSmDependency();
        this.sentDep.init("Sent", this, metamodel.getMClass(CommunicationChannel.MQNAME), 0, 1 );
        registerDependency(this.sentDep);
        
        
    }

    @objid ("09a82bb4-4f56-4971-91a6-8719c0e56bb6")
    public SmDependency getModelDep() {
        if (this.modelDep == null) {
        	this.modelDep = this.getDependencyDef("Model");
        }
        return this.modelDep;
    }

    @objid ("6df78675-af2d-4e79-854d-08929065888e")
    public SmDependency getLinkEndDep() {
        if (this.linkEndDep == null) {
        	this.linkEndDep = this.getDependencyDef("LinkEnd");
        }
        return this.linkEndDep;
    }

    @objid ("acc18df2-00df-434c-a8a4-e44c44436043")
    public SmDependency getSentDep() {
        if (this.sentDep == null) {
        	this.sentDep = this.getDependencyDef("Sent");
        }
        return this.sentDep;
    }

    @objid ("feff8428-fd7b-43a8-b01a-71e394898a18")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("1833ca8a-f3f5-4b15-8f0b-2734bb6afd57")
    private static class LinkObjectFactory implements ISmObjectFactory {
        @objid ("bc4e2ed6-9fd6-4f50-ab5e-2bbb8ad9e1c9")
        private LinkSmClass smClass;

        @objid ("40ac2aeb-4469-42cc-97f9-60785d5132c1")
        public  LinkObjectFactory(LinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("e70afab1-3c3e-4c97-a32a-9e831abc7570")
        @Override
        public ISmObjectData createData() {
            return new LinkData(this.smClass);
        }

        @objid ("0aa0acad-51f0-4eb3-9b96-d8d3236df3a4")
        @Override
        public SmObjectImpl createImpl() {
            return new LinkImpl();
        }

    }

    @objid ("ec54e2b2-6109-4168-91a0-5eefd848bcd5")
    public static class ModelSmDependency extends SmSingleDependency {
        @objid ("356df993-b77f-4c7d-90e8-cfb6d3229a91")
        private SmDependency symetricDep;

        @objid ("e8046dce-5cc5-471a-a473-75d0d9268232")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkData) data).mModel;
        }

        @objid ("83a1ad5c-8536-401a-ad30-0d312c8ca798")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkData) data).mModel = value;
        }

        @objid ("7f8131c9-fe7c-45c7-96c6-09b4117db26c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AssociationSmClass)this.getTarget()).getOccurenceDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("5403eb98-43c7-459d-9dbe-56dcf6991d37")
    public static class LinkEndSmDependency extends SmMultipleDependency {
        @objid ("f9769621-6e62-4b90-b470-53f7ac517738")
        private SmDependency symetricDep;

        @objid ("738ea748-e42d-415e-994d-26867e193226")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((LinkData)data).mLinkEnd != null)? ((LinkData)data).mLinkEnd:SmMultipleDependency.EMPTY;
        }

        @objid ("7fda4724-e99e-4783-8982-2e3f06b36c2e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((LinkData) data).mLinkEnd = values;
            
        }

        @objid ("986681e1-95ee-4a8c-86d1-6d3ce95f8ed4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((LinkEndSmClass)this.getTarget()).getLinkDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("c74a8f11-fb0e-48ed-a6d6-af99d808d2a5")
    public static class SentSmDependency extends SmSingleDependency {
        @objid ("3c3dae30-e823-4a38-8af1-7217e9d16b59")
        private SmDependency symetricDep;

        @objid ("d1729932-81c2-4ced-a686-955e0f7c15b7")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LinkData) data).mSent;
        }

        @objid ("af6a1b5e-c53f-4ba4-b5f2-5589f69e3d85")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LinkData) data).mSent = value;
        }

        @objid ("8c54c0ac-e1c3-4612-9a87-ba88104a512c")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((CommunicationChannelSmClass)this.getTarget()).getChannelDep();
            }
            return this.symetricDep;
            
        }

    }

}

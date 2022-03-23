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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("97a1dde2-c320-448c-8141-4c39d53500a2")
public class AttributeLinkSmClass extends UmlModelElementSmClass {
    @objid ("fa1af406-ac99-430d-87b0-2ea1aabd36ff")
    private SmAttribute valueAtt;

    @objid ("5f74bdf6-a8c3-4ada-bc5f-6918d160f678")
    private SmDependency attributedDep;

    @objid ("ea58297d-3520-4b55-8226-f1e67a9c9a58")
    private SmDependency baseDep;

    @objid ("e57f6c21-ee1f-4e43-b54c-db669d6a9707")
    public  AttributeLinkSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cad7f661-df7d-40bf-ac25-1928a4012c11")
    @Override
    public String getName() {
        return "AttributeLink";
        
    }

    @objid ("84528547-9b2a-4d0c-bbb6-21ccdda09351")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("f61dc32c-23df-4f86-bb48-cb4b84e93602")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return AttributeLink.class;
        
    }

    @objid ("9234a473-4f3a-46a7-85b4-ba636b0835eb")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("e1f87012-6906-46d5-9d3d-891e79de7f2d")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("ae0a9a2c-38e1-4bd3-a44f-505ba2b75b51")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new AttributeLinkObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.valueAtt = new ValueSmAttribute();
        this.valueAtt.init("Value", this, String.class );
        registerAttribute(this.valueAtt);
        
        
        // Initialize and register the SmDependency
        this.attributedDep = new AttributedSmDependency();
        this.attributedDep.init("Attributed", this, metamodel.getMClass(Instance.MQNAME), 1, 1 );
        registerDependency(this.attributedDep);
        
        this.baseDep = new BaseSmDependency();
        this.baseDep.init("Base", this, metamodel.getMClass(Attribute.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.baseDep);
        
        
    }

    @objid ("71981701-4a3c-4584-988d-2367aff2d315")
    public SmAttribute getValueAtt() {
        if (this.valueAtt == null) {
        	this.valueAtt = this.getAttributeDef("Value");
        }
        return this.valueAtt;
    }

    @objid ("16ac67f1-fcd6-46ef-a975-7d615b4e9df9")
    public SmDependency getAttributedDep() {
        if (this.attributedDep == null) {
        	this.attributedDep = this.getDependencyDef("Attributed");
        }
        return this.attributedDep;
    }

    @objid ("5a146dcb-7bc3-45c1-9bf8-c3f181a36404")
    public SmDependency getBaseDep() {
        if (this.baseDep == null) {
        	this.baseDep = this.getDependencyDef("Base");
        }
        return this.baseDep;
    }

    @objid ("b4604b57-819a-43b5-bb8f-7712129ab8ec")
    private static class AttributeLinkObjectFactory implements ISmObjectFactory {
        @objid ("20d04ed7-abe7-4351-a6a2-1d16980e9247")
        private AttributeLinkSmClass smClass;

        @objid ("a8a927b6-b7d2-45f4-b770-b6484dc70001")
        public  AttributeLinkObjectFactory(AttributeLinkSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ff673938-76c2-43dc-b59e-ca26309c82f0")
        @Override
        public ISmObjectData createData() {
            return new AttributeLinkData(this.smClass);
        }

        @objid ("17358880-aefc-46c5-9ee3-75a6ffabfd35")
        @Override
        public SmObjectImpl createImpl() {
            return new AttributeLinkImpl();
        }

    }

    @objid ("1a3ad329-ab47-4649-88be-c3175441bfe8")
    public static class ValueSmAttribute extends SmAttribute {
        @objid ("2c202073-2ec4-4847-ab65-b6a09084a4ea")
        public Object getValue(ISmObjectData data) {
            return ((AttributeLinkData) data).mValue;
        }

        @objid ("f239809f-204c-48e7-b15f-46c7e5eb8d79")
        public void setValue(ISmObjectData data, Object value) {
            ((AttributeLinkData) data).mValue = value;
        }

    }

    @objid ("7ef7c526-f3fd-44c0-a249-311a5a35f630")
    public static class AttributedSmDependency extends SmSingleDependency {
        @objid ("5a9736b3-b10a-4608-89e1-9c8a2ed8bcb3")
        private SmDependency symetricDep;

        @objid ("c05b1657-aa04-4351-ad84-3c5c54bf8ef1")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AttributeLinkData) data).mAttributed;
        }

        @objid ("fdfd291f-c2c0-4389-b794-ebf091de27e0")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AttributeLinkData) data).mAttributed = value;
        }

        @objid ("37986e57-48eb-4e70-90c9-b506b45709e1")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getSlotDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("8f44c6e0-81c9-4fdc-bc66-f181f6d626fd")
    public static class BaseSmDependency extends SmSingleDependency {
        @objid ("6784a49b-0701-4a50-a4d2-8fb9ba9cbe98")
        private SmDependency symetricDep;

        @objid ("8ca27993-e8cc-4b6c-b40b-382e854baeed")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((AttributeLinkData) data).mBase;
        }

        @objid ("132dacaf-eb6a-40a3-a13d-6e5af589915f")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((AttributeLinkData) data).mBase = value;
        }

        @objid ("b283910f-0dfd-4724-933b-7fe8e2b721e7")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((AttributeSmClass)this.getTarget()).getOccurenceDep();
            }
            return this.symetricDep;
            
        }

    }

}

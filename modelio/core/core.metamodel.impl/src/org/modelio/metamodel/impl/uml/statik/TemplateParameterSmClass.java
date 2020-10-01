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
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterData;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSubstitutionSmClass;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
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

@objid ("b5f86fdd-6b97-467b-a13c-48b4ec111c67")
public class TemplateParameterSmClass extends GeneralClassSmClass {
    @objid ("754d8e06-d4c9-4128-b713-e4493452e351")
    private SmAttribute defaultValueAtt;

    @objid ("a0ca25d2-106d-47a5-8f71-96cd0923873a")
    private SmAttribute isValueParameterAtt;

    @objid ("7dcb2e72-4ef7-448b-8edd-9f400ddb5dc9")
    private SmDependency parameterSubstitutionDep;

    @objid ("4e5fd508-f028-4878-9c7d-75d0339f9429")
    private SmDependency typeDep;

    @objid ("1c1dbbad-fdcc-4dc8-abc7-6ae6aca8e194")
    private SmDependency parameterizedDep;

    @objid ("6173852a-af93-444e-a491-7eb9964c5d66")
    private SmDependency ownedParameterElementDep;

    @objid ("5f62fdcb-26a9-4f7a-9e47-cf035eb4b93e")
    private SmDependency defaultTypeDep;

    @objid ("ff41a476-9068-4132-922f-7894ec1d8d65")
    private SmDependency parameterizedOperationDep;

    @objid ("8df66720-3c28-4cbc-a692-e7a7068e0618")
    public TemplateParameterSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("39ebb1fe-d0f6-4668-9e49-d10388e8dbf5")
    @Override
    public String getName() {
        return "TemplateParameter";
    }

    @objid ("6d3aa257-9547-43b1-913b-513ca2d4b911")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("2f13e89b-842d-48bd-b04d-3cbf1eeb08bd")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return TemplateParameter.class;
    }

    @objid ("125756ed-565c-4409-9569-a2ea08a482e1")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("305ba3d8-bfbb-41a4-94a2-0d676584f8f3")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("dc0b9497-e22c-442b-b51c-ab7c6ce72bd3")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(GeneralClass.MQNAME);
        this.registerFactory(new TemplateParameterObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.defaultValueAtt = new DefaultValueSmAttribute();
        this.defaultValueAtt.init("DefaultValue", this, String.class );
        registerAttribute(this.defaultValueAtt);
        
        this.isValueParameterAtt = new IsValueParameterSmAttribute();
        this.isValueParameterAtt.init("IsValueParameter", this, Boolean.class );
        registerAttribute(this.isValueParameterAtt);
        
        
        // Initialize and register the SmDependency
        this.parameterSubstitutionDep = new ParameterSubstitutionSmDependency();
        this.parameterSubstitutionDep.init("ParameterSubstitution", this, metamodel.getMClass(TemplateParameterSubstitution.MQNAME), 0, -1 , SmDirective.SMCDTODELETE);
        registerDependency(this.parameterSubstitutionDep);
        
        this.typeDep = new TypeSmDependency();
        this.typeDep.init("Type", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.typeDep);
        
        this.parameterizedDep = new ParameterizedSmDependency();
        this.parameterizedDep.init("Parameterized", this, metamodel.getMClass(NameSpace.MQNAME), 0, 1 );
        registerDependency(this.parameterizedDep);
        
        this.ownedParameterElementDep = new OwnedParameterElementSmDependency();
        this.ownedParameterElementDep.init("OwnedParameterElement", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.ownedParameterElementDep);
        
        this.defaultTypeDep = new DefaultTypeSmDependency();
        this.defaultTypeDep.init("DefaultType", this, metamodel.getMClass(UmlModelElement.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.defaultTypeDep);
        
        this.parameterizedOperationDep = new ParameterizedOperationSmDependency();
        this.parameterizedOperationDep.init("ParameterizedOperation", this, metamodel.getMClass(Operation.MQNAME), 0, 1 );
        registerDependency(this.parameterizedOperationDep);
    }

    @objid ("4cb6d446-7561-4fdc-bc96-ae68894b0d41")
    public SmAttribute getDefaultValueAtt() {
        if (this.defaultValueAtt == null) {
        	this.defaultValueAtt = this.getAttributeDef("DefaultValue");
        }
        return this.defaultValueAtt;
    }

    @objid ("de185911-81e0-4a4e-aa56-297620332910")
    public SmAttribute getIsValueParameterAtt() {
        if (this.isValueParameterAtt == null) {
        	this.isValueParameterAtt = this.getAttributeDef("IsValueParameter");
        }
        return this.isValueParameterAtt;
    }

    @objid ("807621e6-d197-4c6f-8099-a55d19658454")
    public SmDependency getParameterSubstitutionDep() {
        if (this.parameterSubstitutionDep == null) {
        	this.parameterSubstitutionDep = this.getDependencyDef("ParameterSubstitution");
        }
        return this.parameterSubstitutionDep;
    }

    @objid ("6c796713-10db-4381-94b9-d3559b6d871d")
    public SmDependency getTypeDep() {
        if (this.typeDep == null) {
        	this.typeDep = this.getDependencyDef("Type");
        }
        return this.typeDep;
    }

    @objid ("c864dd36-793e-4f0e-9902-529d7fdf4dc9")
    public SmDependency getParameterizedDep() {
        if (this.parameterizedDep == null) {
        	this.parameterizedDep = this.getDependencyDef("Parameterized");
        }
        return this.parameterizedDep;
    }

    @objid ("8876999f-b25d-48e4-bee4-6049833643fb")
    public SmDependency getOwnedParameterElementDep() {
        if (this.ownedParameterElementDep == null) {
        	this.ownedParameterElementDep = this.getDependencyDef("OwnedParameterElement");
        }
        return this.ownedParameterElementDep;
    }

    @objid ("553bd42a-fedb-47f9-9280-113d55135345")
    public SmDependency getDefaultTypeDep() {
        if (this.defaultTypeDep == null) {
        	this.defaultTypeDep = this.getDependencyDef("DefaultType");
        }
        return this.defaultTypeDep;
    }

    @objid ("9091c9f0-e04e-4215-bfca-8eea50dc3f85")
    public SmDependency getParameterizedOperationDep() {
        if (this.parameterizedOperationDep == null) {
        	this.parameterizedOperationDep = this.getDependencyDef("ParameterizedOperation");
        }
        return this.parameterizedOperationDep;
    }

    @objid ("9aac9ab9-4c7e-47d6-b443-016594c9ab66")
    private static class TemplateParameterObjectFactory implements ISmObjectFactory {
        @objid ("c637b939-a530-4ac9-8aef-715c7c88a6f9")
        private TemplateParameterSmClass smClass;

        @objid ("38110e60-4470-45e8-a402-130ec12d1485")
        public TemplateParameterObjectFactory(TemplateParameterSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("2564b17d-4aed-402f-b237-db76ffe6526d")
        @Override
        public ISmObjectData createData() {
            return new TemplateParameterData(this.smClass);
        }

        @objid ("ccdd0d88-113e-4b42-bbae-9edcec5e0f2c")
        @Override
        public SmObjectImpl createImpl() {
            return new TemplateParameterImpl();
        }

    }

    @objid ("725d5a8b-a47e-4c22-b403-30471751d33d")
    public static class DefaultValueSmAttribute extends SmAttribute {
        @objid ("3a130f17-666d-424f-8693-dc4504cd93f3")
        public Object getValue(ISmObjectData data) {
            return ((TemplateParameterData) data).mDefaultValue;
        }

        @objid ("06b399aa-634c-4ffe-91c7-7fa71dfe13ef")
        public void setValue(ISmObjectData data, Object value) {
            ((TemplateParameterData) data).mDefaultValue = value;
        }

    }

    @objid ("f9b98ade-1dae-402b-abf3-d3644ab09be4")
    public static class IsValueParameterSmAttribute extends SmAttribute {
        @objid ("cf770b04-d6cd-45e7-bf04-62e9f0d39f02")
        public Object getValue(ISmObjectData data) {
            return ((TemplateParameterData) data).mIsValueParameter;
        }

        @objid ("763aab25-efaf-4ca8-84da-145ada5efe75")
        public void setValue(ISmObjectData data, Object value) {
            ((TemplateParameterData) data).mIsValueParameter = value;
        }

    }

    @objid ("e4d60b13-f683-4af7-9640-6bd0703e5275")
    public static class ParameterSubstitutionSmDependency extends SmMultipleDependency {
        @objid ("ccb74c9b-45ff-48e7-989c-bb19d464ee7e")
        private SmDependency symetricDep;

        @objid ("9416bf07-4dd0-40a0-86d8-1546773fad3b")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((TemplateParameterData)data).mParameterSubstitution != null)? ((TemplateParameterData)data).mParameterSubstitution:SmMultipleDependency.EMPTY;
        }

        @objid ("54992f45-b71b-421a-b558-0e98b0f83ea9")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((TemplateParameterData) data).mParameterSubstitution = values;
        }

        @objid ("2bd39525-d2cc-47e4-92f0-5c9a7cda409e")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((TemplateParameterSubstitutionSmClass)this.getTarget()).getFormalParameterDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("1bf3d748-0190-4308-9cba-60661417dc08")
    public static class TypeSmDependency extends SmSingleDependency {
        @objid ("03718314-e1c0-41c5-a0e8-556e94f766b6")
        private SmDependency symetricDep;

        @objid ("f6ebad6f-5373-4954-9c39-e5cd1da0d7af")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateParameterData) data).mType;
        }

        @objid ("2b34a330-aaa5-4ae7-88d1-6c012788ae3d")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateParameterData) data).mType = value;
        }

        @objid ("732c22f1-71d3-47c7-956a-f8a6433ae7d4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getTypingParameterDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("2704724d-fa1d-44a4-8535-3e172edc2877")
    public static class ParameterizedSmDependency extends SmSingleDependency {
        @objid ("65188336-654b-42f8-af4a-933ccff4896a")
        private SmDependency symetricDep;

        @objid ("b0f2e6f6-278c-4bf8-be54-2e35d0399b2d")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateParameterData) data).mParameterized;
        }

        @objid ("9c1fe00c-d552-4c48-a45c-4ff325e9dcac")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateParameterData) data).mParameterized = value;
        }

        @objid ("28b462f3-98eb-4bf1-be3a-34649d9241cc")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((NameSpaceSmClass)this.getTarget()).getTemplateDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8c566423-4278-4817-9b76-82afdc102c7f")
    public static class OwnedParameterElementSmDependency extends SmSingleDependency {
        @objid ("5574fb94-4c7b-4f53-955e-1032fed37808")
        private SmDependency symetricDep;

        @objid ("c3c8c1fb-3b5f-4d53-8eff-16dd6acc63e9")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateParameterData) data).mOwnedParameterElement;
        }

        @objid ("7ddff2cd-bf5f-4c9e-a08a-19353bab6ed8")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateParameterData) data).mOwnedParameterElement = value;
        }

        @objid ("9a30691c-2376-4b82-ab28-c6ce93c45ada")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getOwnerTemplateParameterDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("3bc93f4e-44aa-4fc7-988e-e19aa32b153a")
    public static class DefaultTypeSmDependency extends SmSingleDependency {
        @objid ("a9015fbb-7dad-4d40-81f4-259edc9378c0")
        private SmDependency symetricDep;

        @objid ("f2b42661-2374-45ca-af52-32e8d3eaa7af")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateParameterData) data).mDefaultType;
        }

        @objid ("0bd9f301-1e0a-475c-a658-4a3384f86cbe")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateParameterData) data).mDefaultType = value;
        }

        @objid ("7503c97d-d7e3-499a-89dc-e261017d2f36")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((UmlModelElementSmClass)this.getTarget()).getDefaultParameteringDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("8386e796-2d60-4bb0-b4e2-d10f0bb0fe68")
    public static class ParameterizedOperationSmDependency extends SmSingleDependency {
        @objid ("b3e03af0-d730-4886-bff2-7e953bd853b5")
        private SmDependency symetricDep;

        @objid ("f07b0cac-4469-4ab7-af15-4634ab8fc2bd")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((TemplateParameterData) data).mParameterizedOperation;
        }

        @objid ("6b183aa4-13d1-4734-aaf0-9502ffa30b92")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((TemplateParameterData) data).mParameterizedOperation = value;
        }

        @objid ("cf46e022-e572-415f-a4d9-add77e2928f4")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((OperationSmClass)this.getTarget()).getTemplateDep();
            }
            return this.symetricDep;
        }

    }

}

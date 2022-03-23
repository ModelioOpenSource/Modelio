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

package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.statik.InstanceSmClass;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("21ee1855-80e2-44cf-9070-e41222bd7696")
public class LifelineSmClass extends UmlModelElementSmClass {
    @objid ("00824765-9c6f-4935-a71a-c854c6ff7a9f")
    private SmAttribute selectorAtt;

    @objid ("45c8db46-4434-4381-af8d-94ccb2472de0")
    private SmDependency coveredByDep;

    @objid ("cab001a8-1d09-4b68-91e9-dbce7d38e13f")
    private SmDependency decomposedAsDep;

    @objid ("1b09c2d8-c745-4b73-8bda-0a0ffeb26ca9")
    private SmDependency ownerDep;

    @objid ("227cf917-3d4c-4bb2-bca5-0bf0741e66a7")
    private SmDependency representedDep;

    @objid ("a375dd8e-778c-4c37-9c22-4cbae8dc80f8")
    public  LifelineSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("733d6092-258f-4304-bdd5-02069d6a77dc")
    @Override
    public String getName() {
        return "Lifeline";
        
    }

    @objid ("cdaec220-e51f-41d8-8764-4a37b79103d8")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("33f94fc4-b540-4ef9-8bdc-054f69dd3b20")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Lifeline.class;
        
    }

    @objid ("691c9bfd-0e2d-459c-9c67-81878d1cd827")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("6a36bd02-1dce-4ff2-bc97-b2d3c243dae6")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("2f9700f4-0068-480a-a6a4-cf196293e456")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new LifelineObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.selectorAtt = new SelectorSmAttribute();
        this.selectorAtt.init("Selector", this, String.class );
        registerAttribute(this.selectorAtt);
        
        
        // Initialize and register the SmDependency
        this.coveredByDep = new CoveredBySmDependency();
        this.coveredByDep.init("CoveredBy", this, metamodel.getMClass(InteractionFragment.MQNAME), 0, -1 );
        registerDependency(this.coveredByDep);
        
        this.decomposedAsDep = new DecomposedAsSmDependency();
        this.decomposedAsDep.init("DecomposedAs", this, metamodel.getMClass(PartDecomposition.MQNAME), 0, 1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.decomposedAsDep);
        
        this.ownerDep = new OwnerSmDependency();
        this.ownerDep.init("Owner", this, metamodel.getMClass(Interaction.MQNAME), 0, 1 );
        registerDependency(this.ownerDep);
        
        this.representedDep = new RepresentedSmDependency();
        this.representedDep.init("Represented", this, metamodel.getMClass(Instance.MQNAME), 0, 1 , SmDirective.SMCDPARTOF);
        registerDependency(this.representedDep);
        
        
    }

    @objid ("f8d70ff8-3d3b-4ad7-b306-5e5ee4d49ad9")
    public SmAttribute getSelectorAtt() {
        if (this.selectorAtt == null) {
        	this.selectorAtt = this.getAttributeDef("Selector");
        }
        return this.selectorAtt;
    }

    @objid ("4198993c-d6e8-4ac2-a599-95bab96201b1")
    public SmDependency getCoveredByDep() {
        if (this.coveredByDep == null) {
        	this.coveredByDep = this.getDependencyDef("CoveredBy");
        }
        return this.coveredByDep;
    }

    @objid ("874c0f8f-9064-4950-95c7-582a59b28273")
    public SmDependency getDecomposedAsDep() {
        if (this.decomposedAsDep == null) {
        	this.decomposedAsDep = this.getDependencyDef("DecomposedAs");
        }
        return this.decomposedAsDep;
    }

    @objid ("c315c3cc-5ab0-460a-ab71-a96cfb7c231b")
    public SmDependency getOwnerDep() {
        if (this.ownerDep == null) {
        	this.ownerDep = this.getDependencyDef("Owner");
        }
        return this.ownerDep;
    }

    @objid ("803bd1b4-8812-44b9-9dc4-04d56efbd2ff")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("5847d495-0182-45e5-a2b9-c469242b1b96")
    private static class LifelineObjectFactory implements ISmObjectFactory {
        @objid ("fa72fb56-7215-4eca-9a79-7cd7c79b9dbe")
        private LifelineSmClass smClass;

        @objid ("b3beb3a8-6b26-40c7-ba43-6b52ee43e29e")
        public  LifelineObjectFactory(LifelineSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ef18f01b-f3c7-42a8-ac46-ac3a21d5da28")
        @Override
        public ISmObjectData createData() {
            return new LifelineData(this.smClass);
        }

        @objid ("d34415ec-961f-4426-89da-cbf185deb916")
        @Override
        public SmObjectImpl createImpl() {
            return new LifelineImpl();
        }

    }

    @objid ("a6d61419-08a9-4393-b021-a7257d0cb5bd")
    public static class SelectorSmAttribute extends SmAttribute {
        @objid ("8cb0ace0-b77b-4800-84fd-bfb79ede9dbc")
        public Object getValue(ISmObjectData data) {
            return ((LifelineData) data).mSelector;
        }

        @objid ("f808c7e1-a120-403a-b948-d5ad3aa1a2ca")
        public void setValue(ISmObjectData data, Object value) {
            ((LifelineData) data).mSelector = value;
        }

    }

    @objid ("f8c4616d-c0da-47f8-a81e-3932461da420")
    public static class CoveredBySmDependency extends SmMultipleDependency {
        @objid ("071a1f45-4016-4f58-a2bd-ef99c17cf641")
        private SmDependency symetricDep;

        @objid ("c6b5342f-12f1-4aac-b54a-45f84d7386f1")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((LifelineData)data).mCoveredBy != null)? ((LifelineData)data).mCoveredBy:SmMultipleDependency.EMPTY;
        }

        @objid ("b8795fa1-9882-43e1-80a7-6424b09cd301")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((LifelineData) data).mCoveredBy = values;
            
        }

        @objid ("b0b6c875-5ac0-4221-b730-9709eac217e6")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionFragmentSmClass)this.getTarget()).getCoveredDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("bf0e6bbd-8786-4d3d-86a4-24924239eac3")
    public static class DecomposedAsSmDependency extends SmSingleDependency {
        @objid ("a7caa3e4-0268-4b91-b1a7-d28cf8078880")
        private SmDependency symetricDep;

        @objid ("526ab7e9-5e82-4ce4-83b9-43dfa8a0c186")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LifelineData) data).mDecomposedAs;
        }

        @objid ("c1192243-5db9-4255-9a34-8921d875ab91")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LifelineData) data).mDecomposedAs = value;
        }

        @objid ("201a300e-cc5c-493f-bc76-2c1ed265f406")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((PartDecompositionSmClass)this.getTarget()).getDecomposedDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("9b0a748c-0559-45fc-b4dc-d8688addc020")
    public static class OwnerSmDependency extends SmSingleDependency {
        @objid ("c1b59b93-f74d-4d54-a3a7-6f3821f10e0c")
        private SmDependency symetricDep;

        @objid ("44736723-c334-4bff-9b9d-d868373f3e64")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LifelineData) data).mOwner;
        }

        @objid ("1b76cee6-37d2-4cad-96ee-531222a0b178")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LifelineData) data).mOwner = value;
        }

        @objid ("3273feb4-097a-4ec7-b8d9-8bc1e9d75409")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InteractionSmClass)this.getTarget()).getOwnedLineDep();
            }
            return this.symetricDep;
            
        }

    }

    @objid ("184334fc-86a6-4cb0-895e-28f7dbb393c9")
    public static class RepresentedSmDependency extends SmSingleDependency {
        @objid ("4fefa7af-b89a-4f3c-8661-29b19d965eb4")
        private SmDependency symetricDep;

        @objid ("cd24b20b-f47d-4f6a-9930-8376a5489e31")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((LifelineData) data).mRepresented;
        }

        @objid ("bc6827f6-34d1-4556-821e-e9a127577583")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((LifelineData) data).mRepresented = value;
        }

        @objid ("aee62136-94ab-4a72-ad4e-3cc306abffa8")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((InstanceSmClass)this.getTarget()).getRepresentedLifeLineDep();
            }
            return this.symetricDep;
            
        }

    }

}

/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.RegionData;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateMachineSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateVertexSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
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

@objid ("a16e3d3b-8dda-4d6c-be75-819b122d2f99")
public class RegionSmClass extends UmlModelElementSmClass {
    @objid ("e296fa6c-d5dc-45d0-8b30-b0945742e44e")
    private SmDependency parentDep;

    @objid ("0027ba9e-87af-4df7-86c0-11212087a66b")
    private SmDependency representedDep;

    @objid ("3ed2c56c-1dcd-400e-b7e9-6f807fcd282e")
    private SmDependency subDep;

    @objid ("98925097-cce4-476c-a2fc-786aa8f220e7")
    public RegionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("980d31e0-0777-45d0-996a-5c7f98c4b572")
    @Override
    public String getName() {
        return "Region";
    }

    @objid ("e4df9036-22fd-4a6a-9f34-e3b0c4448051")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("cc50b397-bd31-4203-bb76-a1de064414df")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Region.class;
    }

    @objid ("d60f01e8-fb80-438c-aba7-d8b82491eaca")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("afb39839-b589-4145-af41-f9ab8bd9d53e")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("4e721374-1aa6-493a-a086-706e2b7a77ad")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(UmlModelElement.MQNAME);
        this.registerFactory(new RegionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        this.parentDep = new ParentSmDependency();
        this.parentDep.init("Parent", this, metamodel.getMClass(State.MQNAME), 0, 1 );
        registerDependency(this.parentDep);
        
        this.representedDep = new RepresentedSmDependency();
        this.representedDep.init("Represented", this, metamodel.getMClass(StateMachine.MQNAME), 0, 1 );
        registerDependency(this.representedDep);
        
        this.subDep = new SubSmDependency();
        this.subDep.init("Sub", this, metamodel.getMClass(StateVertex.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.subDep);
    }

    @objid ("1a55f249-d131-4604-95ad-96dda4343bca")
    public SmDependency getParentDep() {
        if (this.parentDep == null) {
        	this.parentDep = this.getDependencyDef("Parent");
        }
        return this.parentDep;
    }

    @objid ("29e3abae-d409-4851-bb46-789ae3d2a8a0")
    public SmDependency getRepresentedDep() {
        if (this.representedDep == null) {
        	this.representedDep = this.getDependencyDef("Represented");
        }
        return this.representedDep;
    }

    @objid ("73026e79-7058-481e-a53b-e0bb5b984ebd")
    public SmDependency getSubDep() {
        if (this.subDep == null) {
        	this.subDep = this.getDependencyDef("Sub");
        }
        return this.subDep;
    }

    @objid ("2e21f7ac-30e9-4716-ab21-db670bffc831")
    private static class RegionObjectFactory implements ISmObjectFactory {
        @objid ("320e478f-72f8-4502-9433-146b86e7625f")
        private RegionSmClass smClass;

        @objid ("e3d93175-ce51-4085-a979-b77850813d0f")
        public RegionObjectFactory(RegionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("c93f495e-c60a-4b3c-a25f-da85d077b514")
        @Override
        public ISmObjectData createData() {
            return new RegionData(this.smClass);
        }

        @objid ("98461170-1579-4862-a24a-f59491323163")
        @Override
        public SmObjectImpl createImpl() {
            return new RegionImpl();
        }

    }

    @objid ("13ba5fdd-175a-4474-8e5d-4c54addd0c42")
    public static class ParentSmDependency extends SmSingleDependency {
        @objid ("3b4f899e-c577-43e8-be43-9357a2450c32")
        private SmDependency symetricDep;

        @objid ("88bc750c-7b6a-4a10-8094-3686110eb3a2")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((RegionData) data).mParent;
        }

        @objid ("876d1a18-6abc-478a-b680-1ea23b1c001b")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((RegionData) data).mParent = value;
        }

        @objid ("9d4f17f9-ccde-414c-8bae-9ba846b24970")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateSmClass)this.getTarget()).getOwnedRegionDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("c2e349ec-e394-4f5c-bb80-4f07b3535102")
    public static class RepresentedSmDependency extends SmSingleDependency {
        @objid ("3dd70a6c-2b06-4bec-9efd-ac5da2f5e8a9")
        private SmDependency symetricDep;

        @objid ("d617f6e9-191b-42ee-80e1-03e969abb0a3")
        @Override
        public SmObjectImpl getValue(ISmObjectData data) {
            return ((RegionData) data).mRepresented;
        }

        @objid ("19ac5aa0-57a7-480a-84b3-4badb790a152")
        @Override
        public void setValue(ISmObjectData data, SmObjectImpl value) {
            ((RegionData) data).mRepresented = value;
        }

        @objid ("b874e3fb-4d96-49b1-8b8a-114deb68fabf")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateMachineSmClass)this.getTarget()).getTopDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("27ad0f58-5236-4ffe-8749-8bd4e98b0317")
    public static class SubSmDependency extends SmMultipleDependency {
        @objid ("def7d321-f442-48dd-bc36-b43e9981ff3d")
        private SmDependency symetricDep;

        @objid ("56dede5e-e0ca-4fdf-a037-76a39580ca39")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((RegionData)data).mSub != null)? ((RegionData)data).mSub:SmMultipleDependency.EMPTY;
        }

        @objid ("c70f98cc-3b38-4b13-8123-cedf9f142a4e")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((RegionData) data).mSub = values;
        }

        @objid ("9f125abc-0df0-4d78-8887-2d1efcc8494f")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((StateVertexSmClass)this.getTarget()).getParentDep();
            }
            return this.symetricDep;
        }

    }

}

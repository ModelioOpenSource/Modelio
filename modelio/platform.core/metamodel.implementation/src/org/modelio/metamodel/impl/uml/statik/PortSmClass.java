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
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.BindableInstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.PortData;
import org.modelio.metamodel.impl.uml.statik.ProvidedInterfaceSmClass;
import org.modelio.metamodel.impl.uml.statik.RequiredInterfaceSmClass;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
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

@objid ("61204c97-45b7-4c84-92f4-7c2cd83be544")
public class PortSmClass extends BindableInstanceSmClass {
    @objid ("90d7fd6f-3f41-4bd4-aab0-05e67fb1ece5")
    private SmAttribute isBehaviorAtt;

    @objid ("034b09ab-35c6-4677-bd8e-d4621d03ba98")
    private SmAttribute isServiceAtt;

    @objid ("6dbff6d8-c406-4b4d-9790-4602641228a8")
    private SmAttribute isConjugatedAtt;

    @objid ("314acf66-5797-4911-920a-7526f90801ef")
    private SmAttribute directionAtt;

    @objid ("d338bbf4-d3a9-42c9-bb27-6e66aaa0f9af")
    private SmDependency providedDep;

    @objid ("88aeb4dd-e8a7-4b8a-b4e5-745d60c72a65")
    private SmDependency requiredDep;

    @objid ("f5bdb6f2-6d2d-40ed-ae13-4099a7ded8ba")
    public PortSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("a7d05494-4dfb-45e4-904f-331271ede2bf")
    @Override
    public String getName() {
        return "Port";
    }

    @objid ("5fcaae36-277d-4454-9f6a-c47c6f9dce57")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("e45c0655-ebb0-4435-b094-ef8c4dcb58b2")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Port.class;
    }

    @objid ("8178febb-b044-4ba8-85bc-e9ba7125cb5f")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("6cce3233-c089-40ab-8331-5c7056cf7583")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("39b5c2f7-6c34-497a-9b23-1430df89e340")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(BindableInstance.MQNAME);
        this.registerFactory(new PortObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isBehaviorAtt = new IsBehaviorSmAttribute();
        this.isBehaviorAtt.init("IsBehavior", this, Boolean.class );
        registerAttribute(this.isBehaviorAtt);
        
        this.isServiceAtt = new IsServiceSmAttribute();
        this.isServiceAtt.init("IsService", this, Boolean.class );
        registerAttribute(this.isServiceAtt);
        
        this.isConjugatedAtt = new IsConjugatedSmAttribute();
        this.isConjugatedAtt.init("IsConjugated", this, Boolean.class );
        registerAttribute(this.isConjugatedAtt);
        
        this.directionAtt = new DirectionSmAttribute();
        this.directionAtt.init("Direction", this, PortOrientation.class );
        registerAttribute(this.directionAtt);
        
        
        // Initialize and register the SmDependency
        this.providedDep = new ProvidedSmDependency();
        this.providedDep.init("Provided", this, metamodel.getMClass(ProvidedInterface.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.providedDep);
        
        this.requiredDep = new RequiredSmDependency();
        this.requiredDep.init("Required", this, metamodel.getMClass(RequiredInterface.MQNAME), 0, -1 , SmDirective.SMCDCOMPONENT);
        registerDependency(this.requiredDep);
    }

    @objid ("aeda62af-a144-4b93-8a44-27577c7fad81")
    public SmAttribute getIsBehaviorAtt() {
        if (this.isBehaviorAtt == null) {
        	this.isBehaviorAtt = this.getAttributeDef("IsBehavior");
        }
        return this.isBehaviorAtt;
    }

    @objid ("51469157-af4d-4ce1-a33e-e194d84b08b1")
    public SmAttribute getIsServiceAtt() {
        if (this.isServiceAtt == null) {
        	this.isServiceAtt = this.getAttributeDef("IsService");
        }
        return this.isServiceAtt;
    }

    @objid ("a049cce1-c6e5-4235-8ca1-35b9b3c25e34")
    public SmAttribute getIsConjugatedAtt() {
        if (this.isConjugatedAtt == null) {
        	this.isConjugatedAtt = this.getAttributeDef("IsConjugated");
        }
        return this.isConjugatedAtt;
    }

    @objid ("bfa23258-9b71-4cd6-84b2-f373239883fa")
    public SmAttribute getDirectionAtt() {
        if (this.directionAtt == null) {
        	this.directionAtt = this.getAttributeDef("Direction");
        }
        return this.directionAtt;
    }

    @objid ("296f1a2a-b6c7-4fdd-8af9-3b369df6a51f")
    public SmDependency getProvidedDep() {
        if (this.providedDep == null) {
        	this.providedDep = this.getDependencyDef("Provided");
        }
        return this.providedDep;
    }

    @objid ("5edbb398-263a-4a24-a5e8-7880f638b853")
    public SmDependency getRequiredDep() {
        if (this.requiredDep == null) {
        	this.requiredDep = this.getDependencyDef("Required");
        }
        return this.requiredDep;
    }

    @objid ("bec8422c-2282-4449-a915-85e752750eec")
    private static class PortObjectFactory implements ISmObjectFactory {
        @objid ("569881fb-3ffc-4a8e-ab17-c7544756902b")
        private PortSmClass smClass;

        @objid ("cfb69f4a-6bb8-42e1-a27b-44e6e8821a72")
        public PortObjectFactory(PortSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("80a1ac3c-b08c-40b9-b1a8-e1852bd455c1")
        @Override
        public ISmObjectData createData() {
            return new PortData(this.smClass);
        }

        @objid ("8b15f3af-2636-4acf-93e5-68cd1179bb26")
        @Override
        public SmObjectImpl createImpl() {
            return new PortImpl();
        }

    }

    @objid ("b9e5af97-ff1d-49c2-bfef-69611e246c4a")
    public static class IsBehaviorSmAttribute extends SmAttribute {
        @objid ("43e30688-0516-48fa-8551-c17cbe5d43a9")
        public Object getValue(ISmObjectData data) {
            return ((PortData) data).mIsBehavior;
        }

        @objid ("43fd026b-d57b-47ff-83a4-db3075ec6eb7")
        public void setValue(ISmObjectData data, Object value) {
            ((PortData) data).mIsBehavior = value;
        }

    }

    @objid ("bce3df54-ac27-4a84-bffc-be44199e0bb7")
    public static class IsServiceSmAttribute extends SmAttribute {
        @objid ("07584f84-9dc4-4018-89cb-d73aea735189")
        public Object getValue(ISmObjectData data) {
            return ((PortData) data).mIsService;
        }

        @objid ("bf7e3761-4b45-42c7-ac72-2d63a0eb69e5")
        public void setValue(ISmObjectData data, Object value) {
            ((PortData) data).mIsService = value;
        }

    }

    @objid ("2a3fc268-a0fa-4fe7-af57-5464ca4f9786")
    public static class IsConjugatedSmAttribute extends SmAttribute {
        @objid ("f8c8c906-ba6b-427d-b522-39460da125cf")
        public Object getValue(ISmObjectData data) {
            return ((PortData) data).mIsConjugated;
        }

        @objid ("8af2666b-3492-46cc-97ef-1bd2b93f8dab")
        public void setValue(ISmObjectData data, Object value) {
            ((PortData) data).mIsConjugated = value;
        }

    }

    @objid ("21bc0ae8-296c-4d70-9b0f-aef602a047ed")
    public static class DirectionSmAttribute extends SmAttribute {
        @objid ("9b7894bc-7f3c-4c8f-9635-b1913783def8")
        public Object getValue(ISmObjectData data) {
            return ((PortData) data).mDirection;
        }

        @objid ("3c54876e-3d6a-4429-8930-b79a37f94aa2")
        public void setValue(ISmObjectData data, Object value) {
            ((PortData) data).mDirection = value;
        }

    }

    @objid ("46f7750c-0a13-45be-8466-b0977ada8060")
    public static class ProvidedSmDependency extends SmMultipleDependency {
        @objid ("eb8d9010-86b3-45ab-99bb-c5dacff788a0")
        private SmDependency symetricDep;

        @objid ("51174306-62c0-4fc2-924e-cb4f12e61af3")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PortData)data).mProvided != null)? ((PortData)data).mProvided:SmMultipleDependency.EMPTY;
        }

        @objid ("e68a9ac3-5ab0-4a8d-95ce-050d87ef17a3")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PortData) data).mProvided = values;
        }

        @objid ("9d2a1520-0e31-4c91-a7cb-3434ebec8f26")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((ProvidedInterfaceSmClass)this.getTarget()).getProvidingDep();
            }
            return this.symetricDep;
        }

    }

    @objid ("b6e30311-16d7-49e0-9d53-0ce5e854e341")
    public static class RequiredSmDependency extends SmMultipleDependency {
        @objid ("0b0f6295-5ebe-4d8c-967a-c01b4cdeb90b")
        private SmDependency symetricDep;

        @objid ("fe916ba4-afbe-40d5-a63e-90970aaa9d34")
        @Override
        public List<SmObjectImpl> getValueList(ISmObjectData data) {
            return (((PortData)data).mRequired != null)? ((PortData)data).mRequired:SmMultipleDependency.EMPTY;
        }

        @objid ("dea1f91c-a043-452d-a816-fa58dbcd4389")
        @Override
        protected void initValueList(ISmObjectData data, List<SmObjectImpl> values) {
            ((PortData) data).mRequired = values;
        }

        @objid ("082637d0-e91d-4918-a6b0-9a310829f270")
        @Override
        public SmDependency getSymetric() {
            if (this.symetricDep == null) {
            	this.symetricDep = ((RequiredInterfaceSmClass)this.getTarget()).getRequiringDep();
            }
            return this.symetricDep;
        }

    }

}

/* 
 * Copyright 2013-2018 Modeliosoft
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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ControlNodeSmClass;
import org.modelio.metamodel.uml.behavior.activityModel.ControlNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("1ebe4134-991e-4e1c-8bb0-d1db7d1d2e79")
public class ForkJoinNodeSmClass extends ControlNodeSmClass {
    @objid ("9150a54b-f7b9-4b0b-8a87-1824b9299724")
    private SmAttribute isCombineDuplicateAtt;

    @objid ("6a4adffc-498d-4afc-9729-28f53ca14ae6")
    private SmAttribute joinSpecAtt;

    @objid ("9727a927-37a6-46d8-8d8c-e9d32f0e74f3")
    public ForkJoinNodeSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("e6d5377e-c54f-4cc0-a9ed-b0617c587da7")
    @Override
    public String getName() {
        return "ForkJoinNode";
    }

    @objid ("bce3844d-74f5-4c6b-aaf3-033d7045a0b6")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("a3175b21-6673-456a-b91d-ab99f9d1c39c")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ForkJoinNode.class;
    }

    @objid ("6e382204-c82b-4779-b32c-42658c8d8ee5")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("4916e72a-a275-4402-92a5-66b9c5aa6b2a")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("56ecf80e-fb8e-45ba-ba33-5c5d339e865c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(ControlNode.MQNAME);
        this.registerFactory(new ForkJoinNodeObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.isCombineDuplicateAtt = new IsCombineDuplicateSmAttribute();
        this.isCombineDuplicateAtt.init("IsCombineDuplicate", this, Boolean.class );
        registerAttribute(this.isCombineDuplicateAtt);
        
        this.joinSpecAtt = new JoinSpecSmAttribute();
        this.joinSpecAtt.init("JoinSpec", this, String.class );
        registerAttribute(this.joinSpecAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("fe8405f9-6e5c-4555-9814-1ca0bd78b83e")
    public SmAttribute getIsCombineDuplicateAtt() {
        if (this.isCombineDuplicateAtt == null) {
        	this.isCombineDuplicateAtt = this.getAttributeDef("IsCombineDuplicate");
        }
        return this.isCombineDuplicateAtt;
    }

    @objid ("848cb4b3-abe9-4200-a5c1-477f96a56fab")
    public SmAttribute getJoinSpecAtt() {
        if (this.joinSpecAtt == null) {
        	this.joinSpecAtt = this.getAttributeDef("JoinSpec");
        }
        return this.joinSpecAtt;
    }

    @objid ("8a67bf02-5901-4ea1-8437-a05394f707cd")
    private static class ForkJoinNodeObjectFactory implements ISmObjectFactory {
        @objid ("4a7465e0-6dfe-48bd-95c6-774d3633804a")
        private ForkJoinNodeSmClass smClass;

        @objid ("4cfd7906-70be-4723-9275-7734be4b1f12")
        public ForkJoinNodeObjectFactory(ForkJoinNodeSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("9a59da67-51d5-4fed-ad87-55b0ee5b8bfc")
        @Override
        public ISmObjectData createData() {
            return new ForkJoinNodeData(this.smClass);
        }

        @objid ("c039b966-16b1-47aa-9285-8bfcceb34786")
        @Override
        public SmObjectImpl createImpl() {
            return new ForkJoinNodeImpl();
        }

    }

    @objid ("d49fbc43-b29a-46ff-ba01-70a4a5463023")
    public static class IsCombineDuplicateSmAttribute extends SmAttribute {
        @objid ("75db9ab8-34e6-42d5-9ac0-4f99ba373652")
        public Object getValue(ISmObjectData data) {
            return ((ForkJoinNodeData) data).mIsCombineDuplicate;
        }

        @objid ("561423f7-2159-4625-9c21-095e3df05e46")
        public void setValue(ISmObjectData data, Object value) {
            ((ForkJoinNodeData) data).mIsCombineDuplicate = value;
        }

    }

    @objid ("55312577-6cee-43a6-ae49-98a8e88fef7a")
    public static class JoinSpecSmAttribute extends SmAttribute {
        @objid ("e522abf1-1435-4984-b3c1-b0e962bac009")
        public Object getValue(ISmObjectData data) {
            return ((ForkJoinNodeData) data).mJoinSpec;
        }

        @objid ("312bf5c5-8350-4d06-984e-78c2365a5d31")
        public void setValue(ISmObjectData data, Object value) {
            ((ForkJoinNodeData) data).mJoinSpec = value;
        }

    }

}

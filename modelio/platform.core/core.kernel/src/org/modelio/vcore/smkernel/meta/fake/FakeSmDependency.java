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

package org.modelio.vcore.smkernel.meta.fake;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.descriptor.MDependencyDescriptor;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Fake SmMultipleDependency implementation.
 */
@objid ("e02af274-72f8-42e9-bdb0-852449195734")
public class FakeSmDependency extends SmMultipleDependency {
    @objid ("de99be1f-5208-4b22-9b37-9c4e0c2372cd")
    private boolean copied;

    @objid ("3d23f36c-6f47-4756-82d4-9dc7a2ac89dd")
    private SmDependency symetric;

    /**
     * @param srcClass the source class
     * @param dep_name the name
     */
    @objid ("c2281b9a-37a9-420f-8bc0-a9f7604d28cd")
    public FakeSmDependency(SmClass srcClass, String dep_name) {
        init(dep_name, srcClass,
                (SmClass)srcClass.getMetamodel().getMClass(MObject.class),
                0, -1,
                SmDirective.SMCDPARTOF);
    }

    /**
     * Get or create a fake dependency from a dependency that existed on the metaclass before it was discarded.
     * <p>
     * @param srcClass the source fake metaclass
     * @param orig a dependency that existed on the metaclass before it becomes fake.
     */
    @objid ("553a7b47-211e-4567-b4cc-f58497c107ea")
    public FakeSmDependency(FakeSmClass srcClass, SmDependency orig) {
        init(orig.getName(), getOwner(), orig.getType(), orig.getMinCardinality(), orig.getMaxCardinality());
        setSymetric(orig.getSymetric());
        setFlag(SmDirective.SMCDCOMPONENT, orig.isComposition());
        setFlag(SmDirective.SMCDPARTOF, orig.isPartOf());
        setFlag(SmDirective.SMCDSHAREDCOMPONENT, orig.isSharedComposition());
        setFlag(SmDirective.SMCDTODELETE, orig.isToDelete());
        setFlag(SmDirective.SMCDDYNAMIC, orig.isDynamic());
        setFlag(SmDirective.SMCDTRANSIENT, orig.isTransient());
    }

    @objid ("9bfb1765-8785-4b74-a2a7-580f97838ad6")
    public FakeSmDependency(FakeSmClass srcClass, MDependencyDescriptor desc) {
        SmClass targetMClass = (SmClass)srcClass.getMetamodel().getMClass(desc.getTarget().getQualifiedName());
        init(desc.getName(),
                srcClass,
                targetMClass,
                desc.getMin(),
                desc.getMax());
        
        
        switch (desc.getAggregation()) {
        case Composition:
            setFlag(SmDirective.SMCDCOMPONENT, true);
            break;
        case None:
            setFlag(SmDirective.SMCDPARTOF, desc.isNavigate());
            setFlag(SmDirective.SMCDTODELETE, desc.isCascadeDelete());
            break;
        case SharedAggregation:
            setFlag(SmDirective.SMCDSHAREDCOMPONENT, true);
            break;
        }
        
        setFlag(SmDirective.SMCD_KEEP_DELETED_ON_READONLY, desc.isWeakReference());
        
        String oppName = desc.getOppositeName() ;
        if (oppName != null && !oppName.isEmpty()) {
            SmDependency oppDep = targetMClass.findDependencyDef(oppName);
            if (oppDep != null) {
                setSymetric(oppDep);
            }
        }
    }

    @objid ("207809bc-7a98-4dbc-8a22-31438e14d536")
    @Override
    public SmDependency getSymetric() {
        return this.symetric;
    }

    @objid ("1df65533-8ccb-485e-bea5-c4dfbd9d06d4")
    @Override
    public Object getValue(ISmObjectData object) {
        List<SmObjectImpl> l = getValueList(object);
        if (isMultiple()) {
            return l;
        } else if (l.isEmpty()) {
            return null;
        } else if (l.size() == 1) {
            return l.get(0);
        } else {
            // Should not happen
            return l;
        }
    }

    @objid ("458fb881-449b-496d-90a5-bc8f17b377e6")
    @SuppressWarnings("unchecked")
    @Override
    public List<SmObjectImpl> getValueList(ISmObjectData obj) {
        List<SmObjectImpl> ret = (List<SmObjectImpl>) cast(obj).content.get(this);
        if (ret != null) {
            return ret;
        } else {
            return SmMultipleDependency.EMPTY;
        }
    }

    @objid ("e98da180-fc26-4d50-8b3c-e1973edd10aa")
    public void setSymetric(SmDependency symetric) {
        this.symetric = symetric;
    }

    @objid ("3f94096b-71db-4a3a-8553-b3743e105888")
    @Override
    protected void initValueList(ISmObjectData data, List<SmObjectImpl> list) {
        cast(data).content.put(this, list);
    }

    @objid ("1d5b93c4-6f4e-456c-adf0-74b6b3b518ca")
    private static FakeSmObjectData cast(ISmObjectData obj) {
        return (FakeSmObjectData) obj;
    }

    @objid ("d503717d-f15b-4f50-bf36-016d7dcb6a9a")
    private void setFlag(SmDirective flag, boolean val) {
        if (val) {
            this.smFlags.add(flag);
        } else {
            this.smFlags.remove(flag);
        }
    }

}

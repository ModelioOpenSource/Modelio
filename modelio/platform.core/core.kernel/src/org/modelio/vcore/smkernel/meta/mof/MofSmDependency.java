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

package org.modelio.vcore.smkernel.meta.mof;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.IllegalModelManipulationException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmDependencyTypeChecker;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Fake SmMultipleDependency implementation.
 */
@objid ("4cc4a437-d55e-4d4f-8f81-2a1bbb6cdc72")
public class MofSmDependency extends SmMultipleDependency {
    @mdl.prop
    @objid ("fdc9dfd0-de79-432a-94c9-cbd0cf663027")
    private boolean temporary;

    @mdl.propgetter
    public boolean isTemporary() {
        // Automatically generated method. Please do not modify this code.
        return this.temporary;
    }

    @mdl.propsetter
    public void setTemporary(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.temporary = value;
    }

    @objid ("b69a613c-3e93-45d6-8654-6739d19d974b")
    private SmDependency symetric;

    /**
     * Create a {noPartOf} 0..* dependency.
     * @param srcClass the source metaclass
     * @param dep_name the name
     * @param targetClass the target metaclass
     */
    @objid ("b4e57658-33cf-4a80-a416-b28f999c6e6c")
    public MofSmDependency(SmClass srcClass, String dep_name, SmClass targetClass) {
        init(dep_name, srcClass,
                targetClass,
                0, -1);
        
        setChecker(MOFDepTypeChecker.instance );
    }

    /**
     * Get or create a MOF dependency from another dependency .
     * <p>
     * The original and this dependency will share the same opposite.
     * @param srcClass the source fake metaclass
     * @param orig a dependency that existed on the metaclass before it becomes fake.
     */
    @objid ("128e391e-7ec2-4f60-8cfa-d59a602346b3")
    public MofSmDependency(MofSmClass srcClass, SmDependency orig) {
        init(orig.getName(), srcClass, orig.getType(), orig.getMinCardinality(), orig.getMaxCardinality());
        initSmFlags(orig.getDirectives());
        setSymetric(orig.getSymetric());
        setChecker(MOFDepTypeChecker.instance);
    }

    @objid ("9563006f-7398-4350-b31e-46e30f913ef9")
    @Override
    public SmDependency getSymetric() {
        return this.symetric;
    }

    @objid ("d2af41bc-4efc-48e5-8f7f-c4ff23315b68")
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

    @objid ("4ae2d030-099c-47e4-8011-75ac248c7c0b")
    @SuppressWarnings("unchecked")
    @Override
    public List<SmObjectImpl> getValueList(ISmObjectData obj) {
        List<SmObjectImpl> ret = (List<SmObjectImpl>) cast(obj).content.get(getContentKey());
        if (ret != null) {
            return ret;
        } else {
            return SmMultipleDependency.EMPTY;
        }
    }

    /**
     * Set the dependency opposite
     * @param symetric the dependency opposite
     */
    @objid ("186024c7-4bdc-490c-a469-c8a061585ea9")
    public void setSymetric(SmDependency symetric) {
        this.symetric = symetric;
        reinitialize();
    }

    @objid ("8d9fa869-1a2d-4eae-b4fe-14fa2281f3dd")
    @Override
    protected void initValueList(ISmObjectData data, List<SmObjectImpl> list) {
        cast(data).content.put(getContentKey(), list);
    }

    @objid ("7bfc859f-240c-48f6-bb2c-9b2056825376")
    private static MofSmObjectData cast(ISmObjectData obj) {
        return (MofSmObjectData) obj;
    }

    /**
     * Put or remove a flag.
     * @param flag the flag to modify
     * @param val true to add the flag, false to remove it.
     */
    @objid ("f7955c38-cabc-4c65-9c24-7add3090a6e0")
    public void setFlag(SmDirective flag, boolean val) {
        if (val) {
            this.smFlags.add(flag);
        } else {
            this.smFlags.remove(flag);
        }
        reinitialize();
    }

    /**
     * Get the key  used to look for the dependency content in the SmObject.
     * @return the lookup key
     */
    @objid ("4ee0d1bb-6901-49d1-bde6-909a4c1fdd8b")
    protected Object getContentKey() {
        // use the name to ease transmutations of objects.
        return getName();
    }

    /**
     * Set the target metaclass.
     * <p>
     * Warn : The target should be set at construction time,
     * call only if you know what you are doing !
     * @param target the target metaclass.
     */
    @objid ("b0b488dc-c41c-4784-91e9-8a5fbff33d83")
    public void setTarget(MofSmClass target) {
        super.setTarget(target);
    }

    /**
     * Add flags to the dependency
     * @param flags the flags to add.
     */
    @objid ("b4512f99-4239-4846-b031-c93a207df611")
    public void addFlags(Collection<SmDirective> flags) {
        this.smFlags.addAll(flags);
        reinitialize();
    }

    /**
     * Set the cardinality.
     * @param minCardinality the minimum cardinality
     * @param maxCardinality the maximum cardinality, -1 for infinite.
     */
    @objid ("77845d6f-2d44-41c2-94ff-f31fe77dd2be")
    public void setCardinality(int minCardinality, int maxCardinality) {
        setMin(minCardinality);
        setMax(maxCardinality);
    }

    @objid ("ede56f3c-d95a-459f-9716-2332e134aa5f")
    protected void reinitialize() {
        postInit();
        
        SmDependency opp = getSymetric();
        if (opp instanceof MofSmDependency) {
            ((MofSmDependency) opp).postInit();
        }
    }

    /**
     * Rename the dependency.
     * <p>
     * To be used when 2 dependencies have the same name on a metaclass hierarchy during metamodel migration.
     * In this case rename the newest dependency.
     * <p>
     * WARNING: This method should be used only with non persisted dependencies, in the other case anything
     * may happen when saving the project.
     * @param newName the new dependency name.
     */
    @objid ("1338c5e2-3e43-4800-a801-9abcabf4604c")
    public void rename(String newName) {
        // Allow rename only {noPartOf} opposite dependencies
        assert(! isComposition() && ! isSharedComposition() && !isPartOf()) : String.format("Trying to rename %s to '%s'", this, newName);
        setName(newName);
    }

    /**
     * {@link SmDependencyTypeChecker} that checks nothing.
     */
    @objid ("22a8af5d-22c1-4fc2-aeb5-7b93a18af2f7")
    private static class MOFDepTypeChecker implements SmDependencyTypeChecker {
        @objid ("53b8e77a-4cb9-441d-935b-b856c18a94d2")
        public static SmDependencyTypeChecker instance = new MOFDepTypeChecker();

        @objid ("e1392382-b46d-476f-8b70-3d56dc7adbf3")
        @Override
        public void assertType(SmObjectImpl smObjectImpl, Object value) throws IllegalModelManipulationException {
            // accept all
        }

        @objid ("4511033e-de9d-48f6-9299-db5bb3a38739")
        @Override
        public boolean checkType(SmObjectImpl obj, Object value) {
            // accept all
            return true;
        }

    }

}

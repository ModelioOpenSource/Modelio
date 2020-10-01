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

package org.modelio.vcore.smkernel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;

/**
 * This interface defines the methods that {@link SmObjectImpl} must implement in order to deal with {@link SmAttribute} and
 * {@link SmDependency}. It actually defines the generic API to a metaclass features.
 * 
 * @author phv
 */
@objid ("002c1ece-a6df-1f4f-9c13-001ec947cd2a")
public interface ISmMeta {
    /**
     * Add 'value' to the 'dep' dependency at position 'index'. For SmSingleDependency index is ignored
     * @param dep
     * @param value
     * @param index
     * 
     * @return <i>true</i> if the model object was modified.
     */
    @objid ("006258a4-b2b0-1f4f-9c13-001ec947cd2a")
    boolean appendDepVal(final SmDependency dep, final SmObjectImpl value, final int index);

    /**
     * Append 'value' to the 'dep' dependency. For SmSingleDependency this method is equivalent to a 'set'
     * @param dep
     * @param value
     * 
     * @return <i>true</i> if the model object was modified.
     */
    @objid ("006247c4-b2b0-1f4f-9c13-001ec947cd2a")
    boolean appendDepVal(final SmDependency dep, final SmObjectImpl value);

    /**
     * Set the 'dep' value at position 'index', replacing the current value at this position.
     * 
     * @param dep the dependency
     * @param index the index to modify
     * @param value the new value
     * @return the old value at the given index
     * @throws java.lang.IllegalArgumentException if 'dep' is not a multiple dependency
     */
    @objid ("00627a6e-b2b0-1f4f-9c13-001ec947cd2a")
    SmObjectImpl setDepVal(final SmDependency dep, final int index, final SmObjectImpl value) throws IllegalArgumentException;

    /**
     * Remove 'value' from 'dep'. For SmSingleDependency, the method is equivalent to a 'set' to null
     * @param dep
     * @param value
     * 
     * @return <i>true</i> if the dependency was modified (ie something was removed)
     */
    @objid ("00629030-b2b0-1f4f-9c13-001ec947cd2a")
    boolean eraseDepVal(final SmDependency dep, final SmObjectImpl value);

    /**
     * Return the current value of the 'dep' dependency.
     * <p>
     * Note that the returned object is a List<SmObjectImpl> for
     * {@link SmMultipleDependency multiple dependencies} and
     * a SmObjectImpl for ?..1 dependencies.
     * 
     * @param dep the dependency
     * @return the dependency content
     */
    @objid ("00626e3e-b2b0-1f4f-9c13-001ec947cd2a")
    Object getDepVal(final SmDependency dep);

    /**
     * Return the current value of the 'dep' dependency as a list. Note that the returned object is a List<SmObjectImpl> for
     * {@link SmMultipleDependency multiple dependencies}
     * 
     * @param dep the dependency
     * @return the dependency content
     */
    @objid ("f2ef2219-bfa2-11e1-b511-001ec947ccaf")
    List<SmObjectImpl> getDepValList(final SmDependency dep);

    /**
     * Return the current value of the 'att' SmAttribute.
     * 
     * @param att the SmAttribute.
     * @return the attribute value
     */
    @objid ("0062a17e-b2b0-1f4f-9c13-001ec947cd2a")
    Object getAttVal(final SmAttribute att);

    /**
     * Set the value of the 'att' SmAttribute to 'value'
     * @param att
     * @param value
     */
    @objid ("0062ae62-b2b0-1f4f-9c13-001ec947cd2a")
    void setAttVal(final SmAttribute att, final Object value);

    /**
     * @return the composition owner
     */
    @objid ("00069d66-45ea-1ffc-8433-001ec947cd2a")
    SmObjectImpl getCompositionOwner();

    /**
     * @return the composition children
     */
    @objid ("000684fc-45ea-1ffc-8433-001ec947cd2a")
    List<SmObjectImpl> getCompositionChildren();

    /**
     * @return the composition relationship with the dependency from this object to its owner.
     */
    @objid ("0006a70c-45ea-1ffc-8433-001ec947cd2a")
    SmDepVal getCompositionRelation();

}

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

package org.modelio.vcore.smkernel.mapi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface defines the services available to programmers outside 'vcore' for handling SmDependency instances.
 * <p>
 * The MDependency services are obviously a subset of those provided by SmDependency. This is because external programmers are not
 * expected to modify the metamodel and therefore only limited 'get-like' accessors are provided.
 * 
 * @author phv
 */
@objid ("006f28d6-25a9-1ffc-8433-001ec947cd2a")
public interface MDependency {
    /**
     * @return the dependency name.
     */
    @objid ("00530520-2efa-1ffc-8433-001ec947cd2a")
    String getName();

    /**
     * Get the dependency target (aka its type).
     * 
     * @return the dependency target metamodel class.
     */
    @objid ("005315ec-2efa-1ffc-8433-001ec947cd2a")
    MClass getTarget();

    /**
     * Get the origin of this dependency.
     * 
     * @return the dependency origin metamodel class.
     */
    @objid ("00531da8-2efa-1ffc-8433-001ec947cd2a")
    MClass getSource();

    /**
     * Tells whether this dependency is a strong composition.
     * @see #isSharedComposition()
     * 
     * @return <code>true</code> for a composition dependency.
     */
    @objid ("00532596-2efa-1ffc-8433-001ec947cd2a")
    boolean isComposition();

    /**
     * Get the opposite dependency.
     * 
     * @return the opposite of this dependency.
     */
    @objid ("00532dca-2efa-1ffc-8433-001ec947cd2a")
    MDependency getSymetric();

    /**
     * Get the minimum cardinality of this dependency
     * <p>
     * Usually returns 0 or 1.
     * 
     * @return the minimum cardinality
     */
    @objid ("0053364e-2efa-1ffc-8433-001ec947cd2a")
    int getMinCardinality();

    /**
     * Get the maximum cardinality of this dependency.
     * <p>
     * -1 means no limit, usually return 1 or -1.
     * 
     * @return the maximum cardinality
     */
    @objid ("00533f36-2efa-1ffc-8433-001ec947cd2a")
    int getMaxCardinality();

    /**
     * Tells whether this dependency is a shared composition.
     * <p>
     * Target objects of this dependency may be 'owned' by more than one object.
     * <p>
     * <b>Note:</b> Be warned that a model object composition graph including shared compositions may
     * contain cycles. Please mind them when navigating it to avoid infinite loops and {@link StackOverflowError}.
     * 
     * @return <code>true</code> for a composition dependency.
     */
    @objid ("9ac80a00-7577-4334-8564-8ecd5d145372")
    boolean isSharedComposition();

}

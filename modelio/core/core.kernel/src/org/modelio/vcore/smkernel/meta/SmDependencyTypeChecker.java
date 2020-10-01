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

package org.modelio.vcore.smkernel.meta;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.IllegalModelManipulationException;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * A SmDependencyTypeChecker implements type checking for SmDependency. The two control methods carry out the same tests, however,
 * the <i>assertValue()</i> directly throws an Exception when the control fails while the <i>checkValue()</i> returns a boolean
 * indicating the success or the failure of the control.
 * 
 * <p>
 * SmDependencyTypeChecker must only deal with type checking or metamodel expertise, it should not, for example, take care of null
 * values, access rights and so on. As a consequence, nothing about the passed objects but their classof should be used to perform
 * the controls.
 * 
 * <p>
 * Implementation notes:<br>
 * The <i>register()</i> method is in charge of registering the checker on the proper SmDependency. Calling the <i>register</i>
 * method in the responsability of the caller of the SmDependencyTypeChecker constructor, it should not be called automatically by
 * the SmDependencyTypeChecker constructor itself.
 * 
 * @author phv
 */
@objid ("006c1eb6-e6c5-1fa3-98b4-001ec947cd2a")
public interface SmDependencyTypeChecker {
    /**
     * This method carries out the exact same controls as the <i>checkValue()</i> but it throws an exception instead of returning a
     * boolean.
     * @param smObjectImpl
     * @param value
     * 
     * @throws org.modelio.vcore.smkernel.IllegalModelManipulationException if the value is invalid.
     */
    @objid ("007c365c-ea69-1fa3-98b4-001ec947cd2a")
    void assertType(final SmObjectImpl smObjectImpl, final Object value) throws IllegalModelManipulationException;

    /**
     * Checks that the classof of <i>value</i> is compatible with the SmDependency this checker has been registered to.= for the
     * given <i>obj</i>. For a registration on a SmDependency named DEP, the checker will control that the following statement is
     * acceptable:
     * <ul>
     * <li>obj.setDEP(value) - <i> for a simple dependency or attribute</i></li>
     * <li>obj.getDEP(value).add(value) - <i> for a multiple dependency or attribute</i></li>
     * </ul>
     * 
     * @param obj the source object
     * @param value the target value
     * @return true if <i>value</i> is acceptable, false otherwise.
     */
    @objid ("001e7288-9103-1fa7-95d2-001ec947cd2a")
    boolean checkType(final SmObjectImpl obj, final Object value);

}

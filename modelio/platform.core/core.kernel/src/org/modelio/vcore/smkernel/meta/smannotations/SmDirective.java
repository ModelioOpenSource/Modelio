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

package org.modelio.vcore.smkernel.meta.smannotations;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Flags for SmClass, SmAttribute and SmDependency.
 */
@objid ("000924be-f99c-1f1f-85a5-001ec947cd2a")
public enum SmDirective {
    /**
     * Tells that a SmClass should be treated as a file when segmenting a repository content.
     */
    CMSNODE,
    /**
     * No right access check should be done on this SmAttribute or SmDependency or SmClass.
     */
    NOREADONLY,
    /**
     * Directives from Modelio 2.1 for Element:
     * @deprecated : what's this?
     */
    @Deprecated
    OBSOLETE,
    /**
     * Tells the target objects must be deleted with the source object.
     */
    SMCDTODELETE,
    /**
     * Tells the SmDependency is a composition.
     */
    SMCDCOMPONENT,
    /**
     * Hint to the storage to tell him it should avoid to store the content directly.
     */
    SMCDDYNAMIC,
    /**
     * Tells the SmDependency maximum multiplicity is more than 1.
     */
    SMCDMULTIPLE,
    /**
     * Tells the SmDependency must be taken into account when copy/pasting or exporting the source object.
     */
    SMCDPARTOF,
    /**
     * Tells the storage to forget the SmAttribute or the SmDependency.
     */
    SMCDTRANSIENT,
    /**
     * Tells the SmDependency is a shared composition.
     */
    SMCDSHAREDCOMPONENT,
    /**
     * Tells the SmDependency is ordered.
     */
    SMCDORDERED,
    /**
     * Tells the storage that the SmDependency or SmAttribute should be indexed for using it as key in lookups.
     */
    FPINDEXED,
    /**
     * Keep deleted elements in the SmDependency if read only when deleting the target.
     */
    SMCD_KEEP_DELETED_ON_READONLY,
    /**
     * Tells the SmDependency is link metaclass dependency pointing to the source.
     */
    SMCDLINKSOURCE,
    /**
     * Tells the SmDependency is a link metaclass dependency pointing toward the target.
     */
    SMCDLINKTARGET;
}

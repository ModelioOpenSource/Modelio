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
    @objid ("005738e8-ec4b-1f33-b94f-001ec947cd2a")
    CMSNODE,
    /**
     * No right access check should be done on this SmAttribute or SmDependency or SmClass.
     */
    @objid ("00093936-f99c-1f1f-85a5-001ec947cd2a")
    NOREADONLY,
    /**
     * Directives from Modelio 2.1 for Element:
     * @deprecated : what's this?
     */
    @objid ("0009399a-f99c-1f1f-85a5-001ec947cd2a")
    @Deprecated
    OBSOLETE,
    /**
     * Tells the target objects must be deleted with the source object.
     */
    @objid ("00093a6c-f99c-1f1f-85a5-001ec947cd2a")
    SMCDTODELETE,
    /**
     * Tells the SmDependency is a composition.
     */
    @objid ("00093ad0-f99c-1f1f-85a5-001ec947cd2a")
    SMCDCOMPONENT,
    /**
     * Hint to the storage to tell him it should avoid to store the content directly.
     */
    @objid ("00093b48-f99c-1f1f-85a5-001ec947cd2a")
    SMCDDYNAMIC,
    /**
     * Tells the SmDependency maximum multiplicity is more than 1.
     */
    @objid ("00093bde-f99c-1f1f-85a5-001ec947cd2a")
    SMCDMULTIPLE,
    /**
     * Tells the SmDependency must be taken into account when copy/pasting or exporting the source object.
     */
    @objid ("00093c4c-f99c-1f1f-85a5-001ec947cd2a")
    SMCDPARTOF,
    /**
     * Tells the storage to forget the SmAttribute or the SmDependency.
     */
    @objid ("00093d82-f99c-1f1f-85a5-001ec947cd2a")
    SMCDTRANSIENT,
    /**
     * Tells the SmDependency is a shared composition.
     */
    @objid ("d637ee50-5989-11e1-be4a-001ec947ccaf")
    SMCDSHAREDCOMPONENT,
    /**
     * Tells the SmDependency is ordered.
     */
    @objid ("74f17e91-5c76-11e1-b6d1-001ec947ccaf")
    SMCDORDERED,
    /**
     * Tells the storage that the SmDependency or SmAttribute should be indexed for using it as key in lookups.
     */
    @objid ("1bad695d-3c8e-406e-b6c4-1705d215cdc5")
    FPINDEXED,
    /**
     * Keep deleted elements in the SmDependency if read only when deleting the target.
     */
    @objid ("d98c80f4-15f9-4737-9629-809319a3863e")
    SMCD_KEEP_DELETED_ON_READONLY,
    /**
     * Tells the SmDependency is link metaclass dependency pointing to the source.
     */
    @objid ("67211dc8-53a6-46e4-b11d-da694232d315")
    SMCDLINKSOURCE,
    /**
     * Tells the SmDependency is a link metaclass dependency pointing toward the target.
     */
    @objid ("559f2fad-e10f-4ffc-8bce-9041d15e2bbd")
    SMCDLINKTARGET;

}

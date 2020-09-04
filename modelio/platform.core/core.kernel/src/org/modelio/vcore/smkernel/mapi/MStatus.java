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

package org.modelio.vcore.smkernel.mapi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represents the status of an element.
 */
@objid ("422654a2-d281-11e1-b069-001ec947ccaf")
public interface MStatus {
    /**
     * Tells whether related object is made visible in the GUI.
     * <p>
     * The current value depends on the currently logged user rights on the object.
     * 
     * @return true if the element is visible in GUI, else false.
     */
    @objid ("aaf2a991-d287-11e1-b069-001ec947ccaf")
    boolean isUserVisible();

    /**
     * Tells whether related object object is browsable (visiting its dependencies).
     * <p>
     * The current value depends on the currently logged user rights on the object.
     * 
     * @return <code>true</code> object is browsable, else <code>false</code>.
     */
    @objid ("aaf2a993-d287-11e1-b069-001ec947ccaf")
    boolean isUserBrowse();

    /**
     * Tells whether related object is writable.
     * <p>
     * The current value depends on the currently logged user rights on the object.
     * 
     * @return <code>true</code> if the element is writable, else <code>false</code>.
     */
    @objid ("aaf2a995-d287-11e1-b069-001ec947ccaf")
    boolean isUserWrite();

//
// Domain flags
// ------------
    /**
     * Tells whether related object is made visible in the GUI.
     * <p>
     * The current value depends on the current state of the application (licences).
     * 
     * @return <code>true</code> if the element is visible in GUI, else <code>false</code>.
     */
    @objid ("aaf50be4-d287-11e1-b069-001ec947ccaf")
    boolean isDomainVisible();

    /**
     * Tells whether related object is browsable (visiting its dependencies).
     * <p>
     * The current value depends on the current state of the application (licences).
     * 
     * @return <code>true</code> if the element is browsable, else <code>false</code>.
     */
    @objid ("aaf50be6-d287-11e1-b069-001ec947ccaf")
    boolean isDomainBrowse();

    /**
     * Tells whether related object is writable.
     * <p>
     * The current value depends on the current state of the application (licences).
     * 
     * @return <code>true</code> if the element is writable, else <code>false</code>.
     */
    @objid ("aaf50be8-d287-11e1-b069-001ec947ccaf")
    boolean isDomainWrite();

    /**
     * Tells whether related object .
     * <p>
     * This value is set for an object and is intrinsic to this object.
     * 
     * @return <code>true</code> if the element is visible in GUI, else <code>false</code>.
     */
    @objid ("aaf50bea-d287-11e1-b069-001ec947ccaf")
    boolean isObjectVisible();

    /**
     * Tells whether related object is browsable (visiting its dependencies).
     * <p>
     * This value is set for an object and is intrinsic to this object.
     * 
     * @return <code>true</code> if the element is browsable, else <code>false</code>.
     */
    @objid ("aaf50bec-d287-11e1-b069-001ec947ccaf")
    boolean isObjectBrowse();

    /**
     * Tells whether related object is writable.
     * <p>
     * This value is set for an object and is intrinsic to this object.
     * 
     * @return <code>true</code> if the element is writable, else <code>false</code>.
     */
    @objid ("aaf50bee-d287-11e1-b069-001ec947ccaf")
    boolean isObjectWrite();

    /**
     * Tells whether the related element is made visible in the GUI.
     * <p>
     * This metho
     * 
     * @return true if the element is visible in the explorer and other views, else false.
     */
    @objid ("aaf50bf0-d287-11e1-b069-001ec947ccaf")
    boolean isVisible();

    /**
     * Tells whether related object is browsable (visiting its dependencies).
     * <p>
     * The current value is the intersection of the currently logged user rights on the object,
     * the current state of the application (licences) and the intrinsic rights set on the object.
     * 
     * @return <code>true</code> if the element is visible in GUI, else <code>false</code>.
     */
    @objid ("aaf50bf2-d287-11e1-b069-001ec947ccaf")
    boolean isBrowse();

    /**
     * Tells whether related object is writable.
     * <p>
     * The current value is the intersection of the currently logged user rights on the object,
     * the current state of the application (licences) and the intrinsic rights set on the object.
     * <p>
     * Note: an object that is writable is not necessarly {@link #isModifiable() modifiable}.
     * @see MStatus#isModifiable()
     * 
     * @return <code>true</code> if the element is visible in GUI, else <code>false</code>.
     */
    @objid ("aaf50bf4-d287-11e1-b069-001ec947ccaf")
    boolean isWrite();

    /**
     * When a CMS is used (or a similar tool), tells whether the related object
     * needs an update from its CMS repository.
     * 
     * @return <code>true</code> if the object needs an update, else <code>false</code>.
     */
    @objid ("aaf50bf6-d287-11e1-b069-001ec947ccaf")
    boolean isCmsSync();

    /**
     * When a CMS is used (or a similar tool), tells whether the related object
     * has been locally modified (by reference to its CMS repository).
     * 
     * @return <code>true</code> if the object has been locally modified, else <code>false</code>.
     */
    @objid ("aaf50bf8-d287-11e1-b069-001ec947ccaf")
    boolean isCmsModified();

    /**
     * When a CMS is used (or a similar tool), tells whether the related object
     * is planned for adding to the CMS repository (most often at next commit).
     * 
     * @return <code>true</code> if the object is planned for adding, else <code>false</code>.
     */
    @objid ("aaf50bfa-d287-11e1-b069-001ec947ccaf")
    boolean isCmsToAdd();

    /**
     * When a CMS is used (or a similar tool), tells whether the related object
     * is planned for removing from the CMS repository (most often at next commit).
     * 
     * @return <code>true</code> if the object is planned for removing, else <code>false</code>.
     */
    @objid ("462ad777-e2ab-4742-abd1-ee44973ed770")
    boolean isCmsToDelete();

    /**
     * When a CMS is used (or a similar tool), tells whether the related object
     * is read only at the CMS level.
     * 
     * @return <code>true</code> if the object is read only at the CMS level, else <code>false</code>.
     */
    @objid ("aaf50bfc-d287-11e1-b069-001ec947ccaf")
    boolean isCmsReadOnly();

    /**
     * Tells whether the related object is under CMS management.
     * 
     * @return <code>true</code> if the object is under CMS management, else <code>false</code>.
     */
    @objid ("aaf50bfe-d287-11e1-b069-001ec947ccaf")
    boolean isCmsManaged();

    /**
     * Tells whether the related object is in CMS conflict.
     * 
     * @return <code>true</code> if the object is in CMS conflict, else <code>false</code>.
     */
    @objid ("16a8c411-67ca-4f0d-95fb-ef97b76a90eb")
    boolean isCmsConflict();

    /**
     * Tells whether the related object belongs to a Model Component (RAMC),
     * meaning that its definition is partial.
     * 
     * @return <code>true</code> if the object is part of a model component, else <code>false</code>.
     */
    @objid ("aaf50c00-d287-11e1-b069-001ec947ccaf")
    boolean isRamc();

    /**
     * Tells whether the related object is a shell object.
     * <p>
     * Shell objects are objects that are not currently reachable for some reasons,
     * and detailed contents is not available.
     * 
     * @return <code>true</code> if the object is a shell object, else <code>false</code>.
     */
    @objid ("aaf50c02-d287-11e1-b069-001ec947ccaf")
    boolean isShell();

    /**
     * Tells whether the related object is deleted.
     * 
     * @return <code>true</code> if the object is deleted, else <code>false</code>.
     */
    @objid ("aaf50c04-d287-11e1-b069-001ec947ccaf")
    boolean isDeleted();

    /**
     * Tells whether related object is modifiable.
     * <p>
     * An object is modifiable if all the following conditions are met:<ul>
     * <li> it is {@link #isWrite() writable}.
     * <li> it is not a {@link #isRamc() model component}.
     * <li> it is not {@link #isCmsReadOnly() locked by the CMS}.
     * <li> it is not a {@link #isShell() shell object}.
     * <li> it is not {@link #isDeleted() deleted}.
     * 
     * @return <code>true</code> if the element is visible in GUI, else <code>false</code>.
     */
    @objid ("aaf50c06-d287-11e1-b069-001ec947ccaf")
    boolean isModifiable();

    /**
     * Tells whether a lock must be taken before modifying the related object.
     * <p>
     * This usually depends on the versioned fragment the related object belongs to.
     * 
     * @return <code>true</code> if locking is needed, else <code>false</code>.
     */
    @objid ("7d33f1f6-0265-43e8-b697-eb4930b115f3")
    boolean isLockingNeeded();

    /**
     * Tells whether has been modified since last model save.
     * <p>
     * May answer true for a whole set of object (eg: a CMS node) if only one of them is modified.
     * 
     * @return <code>true</code> if the object needs to be saved.
     */
    @objid ("f34b675a-5bb7-4804-a63e-868a027dd998")
    boolean isDirty();

}

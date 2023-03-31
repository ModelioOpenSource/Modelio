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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Runtime status of a {@link SmObjectImpl}.
 */
@objid ("30ce786f-e5d8-4d37-ba7e-fdfa9a6c6dfe")
public interface IRStatus {
    /**
     * The object is made visible in the GUI. The current value depends on the currently logged user rights on the object.
     */
    @objid ("7a5ebfe0-dbdd-4ae8-b123-396a14bb16eb")
    public static final long USERVISIBLE = 1L << 0;

    /**
     * The object is browsable (visiting its dependencies). The current value depends on the currently logged user rights on the
     * object.
     */
    @objid ("94dd2b70-8e32-4399-887b-3aa07965af64")
    public static final long USERBROWSE = 1L << 1;

    /**
     * The object is writable. The current value depends on the currently logged user rights on the object.
     */
    @objid ("43264ca0-38ff-4dfc-b493-a584ffc869c4")
    public static final long USERWRITE = 1L << 2;

    /**
     * The object is made visible in the GUI. The current value depends on the current state of the application (licences)
     */
    @objid ("9c5e8764-5622-4eca-91bd-b39b868a6cc7")
    public static final long DOMAINVISIBLE = 1L << 3;

    /**
     * The object is browsable (visiting its dependencies). The current value depends on the current state of the application
     * (licences)
     */
    @objid ("ab913224-e9cb-4b78-a396-bb43ecd63ebb")
    public static final long DOMAINBROWSE = 1L << 4;

    /**
     * The object is browsable (visiting its dependencies). The current value depends on the current state of the application
     * (licences)
     */
    @objid ("e3c6ce38-3807-4081-a41c-f1ec33d56d1b")
    public static final long DOMAINWRITE = 1L << 5;

    /**
     * The object is deleted.
     */
    @objid ("e453a7be-e37a-4cd4-9664-0f4f1c85e393")
    public static final long DELETED = 1L << 6;

    /**
     * The object is being processed for deletion by the deletion algorithm.
     */
    @objid ("cbc4026d-1d6d-4e74-adfb-ba11fa1ad5e1")
    public static final long BEINGDELETED = 1L << 7;

    /**
     * The object is a shell object (its detailed contents are not currently reachable, whatever the reasons)
     */
    @objid ("714a4a6f-3f5b-406a-bd70-c75ebb23b617")
    public static final long SHELL = 1L << 8;

    /**
     * The object belongs to a ModelComponent, meaning that its definition is partial.
     */
    @objid ("e9b42ac0-c8f4-42e3-bcb6-e276d3f3a414")
    public static final long RAMC = 1L << 9;

    /**
     * When a CMS is used (or a similar tool), indicates that the local object is not up to date with its CMS repository
     */
    @objid ("3856b010-820c-418e-95c6-c91626968140")
    public static final long CMSSYNC = 1L << 10;

    /*
     * //
     * // Audit status
     * //
     */
    /**
     * Audit state first bit
     */
    @objid ("da3c7818-de3c-47c6-9be8-c9688a129663")
    public static final long AUDIT1 = 1L << 11;

    /**
     * Audit state 2nd bit
     */
    @objid ("a7e227a1-7d13-4932-bf10-6326563cd363")
    public static final long AUDIT2 = 1L << 12;

    /**
     * The object is being loaded
     */
    @objid ("a9855bf9-d9c5-40f0-85d1-98aabf55df88")
    public static final long LOADING = 1L << 13;

    /**
     * The object has been restored from swap
     */
    @objid ("e2732622-b9ce-4086-86bd-40dbd805f967")
    public static final long RESTORED_FROM_SWAP = 1L << 14;

    /**
     * The object has been locally modified (by reference to its CMS repository)
     */
    @objid ("a43899da-94c3-48ea-a267-62de04a58f2f")
    public static final long CMSMODIFIED = 1L << 35;

    /**
     * The object is planned for adding to the CMS repository (most often at next commit)
     */
    @objid ("59989a8d-b4fa-43e5-b4ec-e85c3b57194d")
    public static final long CMSTOADD = 1L << 36;

    /**
     * The object must be locked before being modified.
     */
    @objid ("03392d89-7506-4c2b-8291-04196052a1cb")
    public static final long CMSTODELETE = 1L << 37;

    /**
     * The CMS forbids modifying the object.
     * <p>
     * Many version managers forbid writing an object until the user has acquired a lock.
     */
    @objid ("b7997a01-5322-46f4-80b0-929a3c323827")
    public static final long CMSREADONLY = 1L << 38;

    /**
     * The object is under CMS management.
     */
    @objid ("e2fabeff-81f2-4f84-bb7b-eb73439a2f9a")
    public static final long CMSMANAGED = 1L << 39;

    /**
     * The object is in conflict with the CMS repository:
     * it has been modified both locally and remotely.
     */
    @objid ("02c0d16f-bdbf-4fd6-bbf8-83e21a31df2e")
    public static final long CMSCONFLICT = 1L << 40;

    /**
     * The object must be locked before being modified.
     */
    @objid ("29146a85-d0a7-48db-8b59-8dd359e12aea")
    public static final long CMSNEEDSLOCK = 1L << 41;

    /*
     * //
     * // Repository flags.
     * //
     * // These flags may be used or not by the repository implementation.
     * //
     */
    /**
     * The object has been loaded.
     * This flag may be used or not by the repository implementation.
     */
    @objid ("ff394cd1-0e02-4f01-93ff-a899de3dd90e")
    public static final long REPO_LOADED = 1L << 42;

    /**
     * All references to the object have been loaded.
     * This flag may be used or not by the repository implementation.
     */
    @objid ("99ad7b94-b671-4a44-b19f-586ae20d8cfc")
    public static final long REPO_USERS_LOADED = 1L << 43;

    /**
     * The reference from the composition owner has been loaded.
     * This flag may be used or not by the repository implementation.
     */
    @objid ("b3f0e8bf-6cfb-4ee3-9684-2e7bc7a52829")
    public static final long REPO_OWNER_LOADED = 1L << 44;

    /**
     * This flag may be used or not by the repository implementation.
     */
    @objid ("5c03d0ce-6ddc-4a5f-9fd9-a557bd3ff834")
    public static final long REPO_DIRTY = 1L << 45;

    /**
     * Flags that represent the CMS status
     */
    @objid ("4ab0c5da-3739-46ad-a8cb-9c16b2789728")
    public static final long MASK_CMS = CMSMODIFIED | CMSTOADD| CMSTODELETE | CMSREADONLY| CMSMANAGED| CMSSYNC| CMSCONFLICT| CMSNEEDSLOCK;

    /**
     * Flags for user access
     */
    @objid ("3633921a-d27b-11e1-b069-001ec947ccaf")
    public static final long MASK_USER = USERVISIBLE | USERBROWSE | USERWRITE;

    /**
     * Mask for domain access
     */
    @objid ("36339220-d27b-11e1-b069-001ec947ccaf")
    public static final long MASK_DOMAIN = DOMAINBROWSE | DOMAINVISIBLE | DOMAINWRITE;

    /**
     * Flags used by the audit.
     */
    @objid ("0084fcf6-f2f3-100f-85b1-001ec947cd2a")
    public static final long MASK_AUDIT = AUDIT1| AUDIT2;

    /**
     * All runtime flags used to compute the access rights
     */
    @objid ("3633922c-d27b-11e1-b069-001ec947ccaf")
    public static final long MASK_RACCESS = MASK_USER | MASK_DOMAIN | CMSREADONLY | RAMC;

    /**
     * Required runtime flags for an object to be modifiable.
     * <p>
     * All of the must be set to <code>true</code> for an object to be modifiable.
     * 
     * @see #RMASK_MODIFIABLE_FORBIDDEN
     */
    @objid ("caeef124-d58f-11e1-b069-001ec947ccaf")
    public static final long RMASK_MODIFIABLE_REQUIRED = IRStatus.USERWRITE | IRStatus.DOMAINWRITE;

    /**
     * Forbidden runtime flags for an object to be modifiable.
     * <p>
     * None of them must be set to <code>true</code> for an object to be modifiable.
     */
    @objid ("caeef12a-d58f-11e1-b069-001ec947ccaf")
    public static final long RMASK_MODIFIABLE_FORBIDDEN = IRStatus.RAMC | IRStatus.SHELL | IRStatus.DELETED | IRStatus.CMSREADONLY;

    /**
     * Mask telling the object is deleted or being deleted.
     */
    @objid ("0eb2ce7b-d4cd-11e1-b069-001ec947ccaf")
    public static final long MASK_DELETE = DELETED | BEINGDELETED;

    /**
     * Flags usable by the repository implementation
     */
    @objid ("d147b0b9-a93a-4336-9626-af1b0fce858c")
    public static final long MASK_REPO = REPO_LOADED | REPO_USERS_LOADED | REPO_OWNER_LOADED | REPO_DIRTY;
}


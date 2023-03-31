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

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Data implementation of MObject.
 */
@objid ("008a4350-dbe7-1f1f-85a5-001ec947cd2a")
public abstract class SmObjectData implements ISmObjectData {
    /**
     * Status flags.
     * <p>
     * In order to support atomic flag changes, all access must be done through {@link #StatusAccessor} setters.
     */
    @objid ("00275434-117d-1f35-b94f-001ec947cd2a")
    private volatile long status;

    @objid ("0022240a-fd1a-1f27-a7da-001ec947cd2a")
    private static final long serialVersionUID = 4272487596063422376L;

    /**
     * Identifier for an object in memory.
     */
    @objid ("0014fd8e-35bd-10bf-bd58-001ec947cd2a")
    private volatile long liveId;

    @objid ("2d463f39-40e6-4016-89ec-fbc9d36c8e36")
    private int lastAccess;

    @objid ("fd60e315-eef1-4280-9311-c44a0f7a5c28")
    private String uuid;

    @objid ("2c9b5b2f-5261-4e55-a843-094c84fdd65f")
    private volatile transient IMetaOf metaOf;

    @objid ("466990f4-0073-4364-aea7-a71fe83b6ef9")
    private volatile transient IRepositoryObject repositoryObject = DummyRepositoryObject.getInstance(this);

    @objid ("bcd89f29-ba93-44d0-9ce5-371dbfb9fc1e")
    protected final SmObjectSmClass classof;

    /**
     * Atomic field updater to use to access {@link #status}
     */
    @objid ("ebd8b330-6356-4a2f-970e-a39c4e51218d")
    private static AtomicLongFieldUpdater<SmObjectData> StatusAccessor = AtomicLongFieldUpdater.newUpdater(SmObjectData.class, "status");

    /**
     * To be called before using the object.
     * @param uuid the identifier
     * @param liveId identifier for the object in memory.
     */
    @objid ("0030a278-702c-1f21-85a5-001ec947cd2a")
    @SuppressWarnings("hiding")
    @Override
    public final void init(final String uuid, final long liveId) {
        this.liveId = liveId;
        this.uuid = uuid;
        
    }

    @objid ("0030c6d6-702c-1f21-85a5-001ec947cd2a")
    @Override
    public String getUuid() {
        return this.uuid;
    }

    @objid ("0030f0ac-702c-1f21-85a5-001ec947cd2a")
    @Override
    public SmObjectSmClass getClassOf() {
        return this.classof;
    }

    @objid ("0031d648-702c-1f21-85a5-001ec947cd2a")
    @Override
    public final IMetaOf getMetaOf() {
        return this.metaOf;
    }

    @objid ("0032015e-702c-1f21-85a5-001ec947cd2a")
    @Override
    public final void setMetaOf(final IMetaOf newMetaObject) {
        this.metaOf = newMetaObject;
    }

    @objid ("00322396-702c-1f21-85a5-001ec947cd2a")
    @Override
    public final long getStatus() {
        return StatusAccessor.get(this);
    }

    @objid ("0085ae62-eb1b-1f22-8c06-001ec947cd2a")
    @Override
    public final IRepositoryObject getRepositoryObject() {
        return this.repositoryObject;
    }

    @objid ("00871392-eb1b-1f22-8c06-001ec947cd2a")
    @Override
    public final void setRepositoryObject(final IRepositoryObject repositoryObject) {
        assert (!(repositoryObject instanceof DummyRepositoryObject));
        
        if (repositoryObject == null) {
            throw new NullPointerException("Repository object should never be null");
        }
        
        this.repositoryObject = repositoryObject;
        
    }

    @objid ("00249ea6-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public final long getLiveId() {
        return this.liveId;
    }

    @objid ("36c65e0c-c618-4417-957d-5a80f74e8eb8")
    @Override
    public final void setLastAccess(int accessTime) {
        this.lastAccess = accessTime;
    }

    @objid ("52eaab5b-d7c8-45d0-84e2-8b725724deef")
    @Override
    public final int getLastAccess() {
        return this.lastAccess;
    }

    @objid ("43b1b017-d85b-4914-96e9-371ad2dfd402")
    @Override
    public final void setRFlags(long flags, StatusState state) {
        // check flags only contains runtime flags
        checkInvalidFlags("setRFlags(...)", flags, SmStatus.RFLAGS);
        
        // check no CMS flag is defined on non CMS node
        if (((flags & SmStatus.MASK_CMS) != 0) && state != StatusState.UNDEFINED && !getClassOf().isCmsNode()) {
            throw new IllegalArgumentException(String.format("setRFlags(%s, %s) : CMS flags cannot be defined on non CMS {%s} %s.",
                    SmStatus.flagsToString(flags), state,
                    this.uuid,  getClassOf().getQualifiedName()));
        }
        
        long newStatus;
        long oldStatus;
        do {
            oldStatus = StatusAccessor.get(this);
            newStatus = SmStatus.setFlags(oldStatus, flags, state);
        } while (! StatusAccessor.compareAndSet(this, oldStatus, newStatus));
        
    }

    /**
     * Set the given persistent flags state.
     * @param flags a combination of flags. Use the constants defined in {@link IRStatus}.
     * @param state the flags state
     */
    @objid ("e2c0b986-38a1-403d-876f-aed92894081a")
    @Override
    public final void setPFlags(long flags, StatusState state) {
        // check flags only contains runtime flags
        checkInvalidFlags("setPFlags(...)", flags, SmStatus.PFLAGS);
        
        // check no CMS flag is defined on non CMS node
        if (((flags & SmStatus.MASK_CMS) != 0) && state != StatusState.UNDEFINED && !getClassOf().isCmsNode()) {
            throw new IllegalArgumentException(String.format("setPFlags(%s, %s) : CMS flags cannot be defined on non CMS {%s} %s.",
                    SmStatus.flagsToString(flags), state,
                    this.uuid,  getClassOf().getQualifiedName()));
        }
        
        long newStatus;
        long oldStatus;
        do {
            oldStatus = StatusAccessor.get(this);
            newStatus = SmStatus.setFlags(oldStatus, flags, state);
        } while (! StatusAccessor.compareAndSet(this, oldStatus, newStatus));
        
    }

    @objid ("b2afcd9d-eede-4915-bc19-1b396c51f985")
    private void checkInvalidFlags(String name, long flags, long allowedFlags) throws IllegalArgumentException {
        if ((flags & ~allowedFlags) == 0)
            return;
        
        throw new IllegalArgumentException(String.format("%s: %s flag(s) not allowed on {%s} %s.",
                name,
                SmStatus.flagsToString(flags & ~allowedFlags),
                this.uuid,
                getClassOf().getQualifiedName()));
        
    }

    @objid ("de75df49-41f6-4cc4-9a1f-1d21679a80a8")
    private void checkInvalidFlags(String name, long trueFlags, long falseFlags, long undefFlags, long allowedFlags) {
        if (((trueFlags| falseFlags| undefFlags) & ~allowedFlags) == 0)
            return;
        
        throw new IllegalArgumentException(String.format("%s({%s}, {%s}, {%s}): %s flag(s) not allowed on {%s} %s.",
                name,
                SmStatus.flagsToString(trueFlags),
                SmStatus.flagsToString(falseFlags),
                SmStatus.flagsToString(undefFlags),
                SmStatus.flagsToString((trueFlags| falseFlags| undefFlags) & ~allowedFlags),
                this.uuid,
                getClassOf().getQualifiedName()));
        
    }

    /**
     * Set all persistent flags state at once.
     * <p>
     * Package private to avoid mess.
     * @param pFlags the new persistent flags
     */
    @objid ("5b6b368b-c462-400c-afa7-958504a3939e")
    final void replacePersistentFlags(long pFlags) {
        long newStatus;
        long oldStatus;
        do {
            oldStatus = StatusAccessor.get(this);
            newStatus = SmStatus.setPersistentPart(oldStatus, pFlags);
        } while (! StatusAccessor.compareAndSet(this, oldStatus, newStatus));
        
    }

    /**
     * Set all persistent flags state at once.
     * <p>
     * Package private to avoid mess.
     * @param newStatus the new runtime and persistent persistent flags
     */
    @objid ("5eb4b655-c87f-4cac-8c3b-fd35dbf829f7")
    final void replaceAllFlags(long newStatus) {
        StatusAccessor.set(this, newStatus);
    }

    @objid ("d093ac00-8056-477e-8fe4-1ce4b6b46d9c")
    @Override
    public final void setRFlags(long trueFlags, long falseFlags, long undefFlags) {
        // check flags only contains runtime flags
        checkInvalidFlags("setRFlags", trueFlags, falseFlags, undefFlags, SmStatus.RFLAGS);
        
        // check no CMS flag is defined on non CMS node
        if ((((trueFlags| falseFlags) & SmStatus.MASK_CMS) != 0) && !getClassOf().isCmsNode()) {
            String msg = String.format("setRFlags(%s, %s, %s) : CMS flags cannot be defined on non CMS {%s} %s.",
                    SmStatus.flagsToString(trueFlags), SmStatus.flagsToString(falseFlags), SmStatus.flagsToString(undefFlags),
                    this.uuid,  getClassOf().getQualifiedName());
            throw new IllegalArgumentException(msg);
        }
        
        // debug
        //System.err.println("Set "+this.getUuid()+" "+this.getClassOf().getName()+" status from {"+SmStatus.toString(this.status)+ "} to {"+SmStatus.toString(newStatus)+"}");
        
        long newStatus;
        long oldStatus;
        do {
            oldStatus = StatusAccessor.get(this);
            newStatus = SmStatus.setFlags(oldStatus, trueFlags, falseFlags, undefFlags);
        } while (! StatusAccessor.compareAndSet(this, oldStatus, newStatus));
        
    }

    @objid ("3cd4ebe6-cea5-4c52-aa19-571f869b9ff3")
    @Override
    public final void setPFlags(long trueFlags, long falseFlags, long undefFlags) {
        // check flags only contains persistent flags
        checkInvalidFlags("setRFlags", trueFlags, falseFlags, undefFlags, SmStatus.PFLAGS);
        
        // check no CMS flag is defined on non CMS node
        if ((((trueFlags| falseFlags) & SmStatus.MASK_CMS) != 0) && !getClassOf().isCmsNode()) {
            throw new IllegalArgumentException(String.format("setPFlags(%s, %s, %s) : CMS flags cannot be defined on non CMS {%s} %s.",
                    SmStatus.flagsToString(trueFlags), SmStatus.flagsToString(falseFlags), SmStatus.flagsToString(undefFlags),
                    this.uuid,  getClassOf().getQualifiedName()));
        }
        
        long newStatus;
        long oldStatus;
        do {
            oldStatus = StatusAccessor.get(this);
            newStatus = SmStatus.setFlags(oldStatus, trueFlags, falseFlags, undefFlags);
        } while (! StatusAccessor.compareAndSet(this, oldStatus, newStatus));
        
    }

    @objid ("0623aa1b-a60d-4292-acb6-9b166adfd2cb")
    @Override
    public final StatusState hasAllStatus(long flags) {
        return SmStatus.areAllSet(StatusAccessor.get(this), flags);
    }

    @objid ("3bf5de1f-b836-42b3-b552-0b62e9b58ddc")
    @Override
    public final StatusState hasAnyStatus(long flags) {
        return SmStatus.isAnySet(StatusAccessor.get(this), flags);
    }

    /**
     * Mandatory constructor
     * @param classof the object metaclass
     */
    @objid ("a9cc60f6-dbc2-43c6-94ab-069a4d0a3af4")
    public  SmObjectData(SmObjectSmClass classof) {
        this.classof = classof;
    }

}

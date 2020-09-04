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

package org.modelio.vcore.session.impl.load;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.AccessDeniedException;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.IKernelServiceProvider;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.KernelRegistry.NoSuchKernelException;
import org.modelio.vcore.smkernel.KernelRegistry;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Storage handler for unloaded elements.
 * <p>
 * Used mainly to ease debugging of orphans/detached/duplicate objects.
 * <p>
 * Is able to resurrect objects if another instance has been loaded in memory.
 * 
 * @author cmarin
 */
@objid ("e44ff08e-9c51-478a-98f3-2a43a2e62c1c")
public class UnloadedRepositoryHandle implements IRepositoryObject {
    /**
     * Constant to help debugging unloading.
     * <p>
     * Do not set to true in production, it makes everything crash.
     */
    @objid ("da227395-5ba9-4d86-b9b3-f9b187f96a1f")
    private static final boolean CRASH_ON_READ = false;

    @objid ("a1e20989-8b30-4cdd-8549-064abfe17290")
    private final IRepository repository;

    @objid ("8cc4a47c-fab4-4964-8577-a06a513ac10e")
    public UnloadedRepositoryHandle(IRepository repository) {
        this.repository = repository;
    }

    @objid ("497d0590-e67f-4667-a774-8081d005fd45")
    @Override
    public String toString() {
        return String.format("Unloaded handler for objects on %s", this.repository);
    }

    @objid ("a3440705-8be5-4ac1-a2c8-e60d99addf3d")
    @Override
    public byte getRepositoryId() {
        return this.repository.getRepositoryId();
    }

    @objid ("f111f4f2-fd42-456a-86db-3c5c8347af28")
    @Override
    public boolean isAttLoaded(SmObjectImpl obj, SmAttribute att) {
        if (tryRestore(obj)) {
            return obj.getRepositoryObject().isAttLoaded(obj, att);
        } else if (CRASH_ON_READ) {
            if (!isPassThroughAtt(att)) {
                throw createException(obj);
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @objid ("9c4b6d5f-1ca1-4556-bd8d-55446c5b1ad0")
    private static boolean isPassThroughAtt(SmAttribute att) {
        if (att == null) {
            return true;
        } else {
            String attName = att.getName();
            return attName.equalsIgnoreCase("name") || attName.equalsIgnoreCase("status");
        }
    }

    @objid ("292eaff8-ea31-4ef8-b36a-ca233af48c01")
    @Override
    public void loadAtt(SmObjectImpl obj, SmAttribute att) {
        if (tryRestore(obj)) {
            obj.getRepositoryObject().loadAtt(obj, att);
        } else if (CRASH_ON_READ) {
            if (!isPassThroughAtt(att)) {
                throw createException(obj);
            }
        }
    }

    @objid ("d0d04edd-967b-4c60-a462-6b0e03885b8f")
    @Override
    public void depValErased(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        if (tryRestore(obj)) {
            obj.getRepositoryObject().depValErased(obj, dep, val);
        } else if (dep.isPartOf() || dep.isComposition() || dep.isSharedComposition()) {
            // forbid modification of normal dependencies (allow opposite ones)
            throw createException(obj);
        }
    }

    @objid ("ccc9ba64-9a94-4de2-98cf-6171c69d6e40")
    protected UnloadedException createException(SmObjectImpl obj) {
        return new UnloadedException(obj, this.repository);
    }

    /**
     * Try to reload another live instance of the object data with another repository handle.
     * @param obj the object to reload.
     * @return true if the object was successfully  else false.
     */
    @objid ("8c48b9fe-4234-41c9-a612-151af34f065e")
    private boolean tryRestore(SmObjectImpl obj) {
        try {
            IKernelServiceProvider svc = KernelRegistry.getService0(obj.getLiveId());
            if (svc != null) {
                //ISmObjectData oldData = obj.getData();
                ISmObjectData newdata = svc.loadData(obj);
                if (newdata.getRepositoryObject() != this) {
                    return true;
                }
            } 
        } catch (NoSuchKernelException | DeadObjectException e) {
            // ignore
        }
        return false;
    }

    @objid ("cd2642bd-9b35-48f0-ace5-d185de62c39a")
    @Override
    public void attModified(SmObjectImpl obj, SmAttribute att) {
        if (tryRestore(obj)) {
            obj.getRepositoryObject().attModified(obj, att);
        } else {
            throw createException(obj);
        }
    }

    @objid ("23354f5c-9bcb-45cf-b861-1073195b53db")
    @Override
    public void attach(SmObjectImpl obj) {
        // should not be called
        throw new UnsupportedOperationException();
    }

    @objid ("5c2548eb-8c81-4e71-a1cc-9ffb5073fac6")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        // should not be called
        throw new UnsupportedOperationException();
    }

    @objid ("5cc40b01-2f59-4d80-9185-2ad31aaede09")
    @Override
    public void depValAppended(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        if (tryRestore(obj)) {
            obj.getRepositoryObject().depValAppended(obj, dep, val);
        } else {
            throw createException(obj);
        }
    }

    @objid ("2db6c98e-7197-4a7d-b355-d12536e8a5ef")
    @Override
    public void depValMoved(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        if (tryRestore(obj)) {
            obj.getRepositoryObject().depValMoved(obj, dep, val);
        } else {
            throw createException(obj);
        }
    }

    @objid ("5a0ee0ee-18f3-476b-a92d-a1f84c10dece")
    @Override
    public void detach(SmObjectImpl obj) {
        if (tryRestore(obj)) {
            obj.getRepositoryObject().detach(obj);
        } else {
            // Gladly accept a such request
        }
    }

    @objid ("a6f5a876-fe53-4a1f-9bf2-b070c2cc1479")
    @Override
    public Resource getEmfResource() {
        // should not be called
        throw new UnsupportedOperationException();
    }

    @objid ("fd40e079-14b7-495a-9f23-b7b63e14f906")
    @Override
    public boolean isDepLoaded(SmObjectImpl obj, SmDependency dep) {
        if (tryRestore(obj)) {
            return obj.getRepositoryObject().isDepLoaded(obj, dep);
        }
        return false;
    }

    @objid ("a16abb11-4ef8-4e9c-909f-abb9ad095fca")
    @Override
    public boolean isDirty(SmObjectImpl obj) {
        if (tryRestore(obj)) {
            obj.getRepositoryObject().isDirty(obj);
        }
        return false;
    }

    @objid ("868ebdf5-a515-4fda-b749-e2441bf8f032")
    @Override
    public boolean isPersistent(SmDependency dep) {
        // should not be called
        throw new UnsupportedOperationException();
    }

    @objid ("9f2db59f-c60e-429c-b9af-c8330d90f66f")
    @Override
    public void loadDep(SmObjectImpl obj, SmDependency dep) {
        if (tryRestore(obj)) {
            obj.getRepositoryObject().loadDep(obj, dep);
        }
    }

    @objid ("4851dcf6-a02e-4efa-a664-cc741cb3e507")
    @Override
    public void unload(SmObjectImpl obj) {
        // already done
        // should not be called
    }

    @objid ("26ee9ed0-b03b-4375-9b12-7a2f54de5693")
    @Override
    public void setToReload(SmObjectImpl obj) {
        // ignore
        // should not be called
    }

    @objid ("3e41e2ef-7830-407a-b6a6-57a633a01319")
    public static final class UnloadedException extends AccessDeniedException {
        @objid ("8e56ce29-a65e-4447-a879-07143b11934a")
        private static final long serialVersionUID = 1L;

        @objid ("e85c60d5-14e2-4548-8e4c-3a292c305c17")
        public UnloadedException(MObject related, IRepository repository) {
            super(related+" has been unloaded from "+repository+".", related);
        }

    }

}

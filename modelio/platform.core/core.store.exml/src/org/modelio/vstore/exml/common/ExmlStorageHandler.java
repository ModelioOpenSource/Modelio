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

package org.modelio.vstore.exml.common;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.StatusState;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.exml.common.index.ICmsNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.utils.ExmlUtils;

/**
 * Repository object for objects stored in a ExmlBase.
 * <p>
 * There is one ExmlStorageHandler per CMS node object.
 * It is shared by each object in the CMS node.
 */
@objid ("fd26ba13-5986-11e1-991a-001ec947ccaf")
public class ExmlStorageHandler implements IRepositoryObject {
    @objid ("fd21f4cb-5986-11e1-991a-001ec947ccaf")
    private boolean loaded;

    @objid ("fd21f4d0-5986-11e1-991a-001ec947ccaf")
    private boolean dirty;

    @objid ("fd21f4e0-5986-11e1-991a-001ec947ccaf")
    private boolean parentLoaded;

    @objid ("61cc023a-49bf-4b0d-909f-335456e57c8c")
    private final IExmlBase base;

    @objid ("4c1854a7-9489-4ce9-a95d-817b8f7e7260")
    private SmObjectImpl cmsNode;

    @objid ("66924664-2d4f-4293-ae3e-35dbead5bc18")
    private final ObjId cmsNodeId;

    @objid ("fd245807-5986-11e1-991a-001ec947ccaf")
    protected static boolean isInverseCompositionDep(SmDependency dep) {
        return (dep.getSymetric() != null && dep.getSymetric().isComponent());
    }

    /**
     * Initialize the handler.
     * 
     * @param base the EXML repository.
     * @param cmsNode the root CMS node
     * @param isNodeLoaded <code>true</code> if the node is already loaded else <code>false</code>.
     */
    @objid ("fd245855-5986-11e1-991a-001ec947ccaf")
    public ExmlStorageHandler(IExmlBase base, SmObjectImpl cmsNode, final boolean isNodeLoaded) {
        this.cmsNode = cmsNode;
        this.base = base;
        this.loaded = isNodeLoaded;
        this.cmsNodeId = new ObjId(cmsNode);
    }

    @objid ("fd24575c-5986-11e1-991a-001ec947ccaf")
    @Override
    public void attModified(SmObjectImpl obj, SmAttribute att) {
        this.dirty = true;
    }

    @objid ("fd245802-5986-11e1-991a-001ec947ccaf")
    @Override
    public void attach(final SmObjectImpl obj) {
        if ( this != obj.getRepositoryObject()) {
            this.base.addObject(obj);
        
            if (!obj.getClassOf().isCmsNode()) {
                this.dirty = true;
                obj.setRepositoryObject(this);
            }
        }
    }

    @objid ("fd24575a-5986-11e1-991a-001ec947ccaf")
    @Override
    public void depValAppended(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        if (isPersistent(dep)) {
            this.dirty = true;
        }
        
        if ((ExmlUtils.isComposition(obj, dep, val))
                && !val.getClassOf().isCmsNode() && val.getRepositoryObject() != this) {
            // A non CMS node moved into this CMS node, fix its storage handler
            propagateHandler(val);
        }
    }

    @objid ("fd245756-5986-11e1-991a-001ec947ccaf")
    @Override
    public void depValErased(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        if (isPersistent(dep)) {
            this.dirty = true;
        }
        
        if ((ExmlUtils.isComposition(obj, dep, val))
                && !val.getClassOf().isCmsNode() && val.getRepositoryObject() == this) {
            // A non CMS node moved out this CMS node.
            // For most case 'val' becomes orphan.
            // In corner cases 'val' may become owned by another object,
            // In this last case fix its storage handler.
            SmObjectImpl newOwner = val.getCompositionOwner();
            if (newOwner != null ) {
                IRepositoryObject newHandle = newOwner.getRepositoryObject();
                if(newHandle  != this && newHandle instanceof ExmlStorageHandler) {
                    ((ExmlStorageHandler) newHandle).propagateHandler(val);
                }
            }
        }
    }

    @objid ("fd245759-5986-11e1-991a-001ec947ccaf")
    @Override
    public void depValMoved(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        if (isPersistent(dep)) {
            this.dirty = true;
        }
    }

    @objid ("fd24572b-5986-11e1-991a-001ec947ccaf")
    @Override
    public void detach(SmObjectImpl obj) {
        try {
            this.base.removeObject(obj);
            this.dirty = true;
        } catch (IOException e) {
            this.base.getErrorSupport().fireError(e);
        }
    }

    /**
     * Get the root CMS node of this handler.
     * <p>
     * May return <i>null</i> if the node was deleted then unloaded.
     * 
     * @return the root CMS node of this handler or <i>null</i>.
     */
    @objid ("fd245752-5986-11e1-991a-001ec947ccaf")
    public final SmObjectImpl getCmsNode() {
        if (this.cmsNode == null) {
            this.cmsNode = this.base.findById(this.cmsNodeId.classof, this.cmsNodeId.id);
            if (this.cmsNode != null) {
                this.cmsNode.setRepositoryObject(this);
            } else {
                String msg = String.format("ExmlStorageHandler.getCmsNode(): %s not found in %s. The node was probably deleted then unloaded", this.cmsNodeId, this.base);
                Log.trace(new Throwable(msg));
            }
        }
        return this.cmsNode;
    }

    @objid ("4b1b9db4-c065-11e1-b511-001ec947ccaf")
    @Override
    public final Resource getEmfResource() {
        return this.base.getEmfResource();
    }

    @objid ("fd2457fc-5986-11e1-991a-001ec947ccaf")
    @Override
    public final byte getRepositoryId() {
        return this.base.getRepositoryId();
    }

    @objid ("fd245825-5986-11e1-991a-001ec947ccaf")
    @Override
    public final boolean isAttLoaded(SmObjectImpl obj, SmAttribute att) {
        if (att != null && att.isNameAtt()) {
            return true;
        }
        return isLoaded();
    }

    @objid ("fd245753-5986-11e1-991a-001ec947ccaf")
    @Override
    public final boolean isDepLoaded(SmObjectImpl obj, SmDependency dep) {
        if (isPersistent(dep)) {
            if (obj.equals(getCmsNode()) && isInverseCompositionDep(dep)) {
                return this.parentLoaded;
            } else {
                return this.loaded;
            }
        } else if (areUsersLoaded(obj)) {
            return true;
        } else if (isInverseDepStored(dep)) {
            // dynamic dependencies are always reloaded
        
            return false;
        } else {
            // Inverse dependency not stored either, this case shouldn't occur
            return true;
        }
    }

    /**
     * @return <code>true</code> if the node needs to be saved.
     */
    @objid ("fd24574b-5986-11e1-991a-001ec947ccaf")
    public final boolean isDirty() {
        return this.dirty;
    }

    /**
     * Tells whether the CMS node is loaded.
     * 
     * @return <code>true</code> if the node is loaded else <code>false</code>.
     */
    @objid ("3c9891b4-2f3f-11e2-8359-001ec947ccaf")
    public final boolean isLoaded() {
        return this.loaded;
    }

    @objid ("fd21f72a-5986-11e1-991a-001ec947ccaf")
    @Override
    public final boolean isPersistent(SmDependency dep) {
        // Inverse of composition SmDependencies are considered as stored.
        return (dep.isPartOf() || dep.isComponent() || dep.isSharedComposition() || isInverseCompositionDep(dep));
    }

    @objid ("3c9891b9-2f3f-11e2-8359-001ec947ccaf")
    @Override
    public final void loadAtt(SmObjectImpl obj, SmAttribute att) {
        load();
    }

    @objid ("fd24581f-5986-11e1-991a-001ec947ccaf")
    @Override
    public final void loadDep(SmObjectImpl obj, SmDependency dep) {
        try (IModelLoader modelLoader = this.base.getModelLoaderProvider().beginLoadSession()) {
            if (isPersistent(dep)) {
                SmObjectImpl lcmsNode = getCmsNode(modelLoader);
                if (obj.equals(lcmsNode) && isInverseCompositionDep(dep)) {
                    // It is the dependency from the CMS node to the parent CMS node
                    if (!this.parentLoaded ) {
                        final ObjId  parentId = getParentCmsNode(obj);
                        if (parentId != null) {
                            this.base.loadCmsNode(parentId, modelLoader, false);
                        }
        
                        this.parentLoaded = true;
                    }
                } else {
                    // any "navigable" dependency : load the node
                    load ();
        
                }
            } else if (isInverseDepStored(dep)) {
                this.base.loadDynamicDep(obj, dep);
            }
        } catch (DuplicateObjectException e) {
            this.base.getErrorSupport().fireError(e);
        } catch (IllegalReferenceException e) {
            this.base.setIndexesDamaged(e);
            this.base.getErrorSupport().fireError(e);
        } catch (IndexException e) {
            this.base.getErrorSupport().fireError(e);
        }
    }

    /**
     * Set the node as dirty or not.
     * 
     * @param value the new dirty state.
     */
    @objid ("fd24572c-5986-11e1-991a-001ec947ccaf")
    public final void setDirty(final boolean value) {
        this.dirty = value;
    }

    /**
     * Set the node as loaded or not.
     * 
     * @param value the new load state.
     */
    @objid ("fd245812-5986-11e1-991a-001ec947ccaf")
    public final void setLoaded(final boolean value) {
        this.loaded =  value;
    }

    @objid ("f4dc263e-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public final void unload(SmObjectImpl obj) {
        if (this.cmsNode == obj) {
            this.cmsNode = null;
        }
        
        this.base.unloadObject(obj);
    }

    @objid ("fd245806-5986-11e1-991a-001ec947ccaf")
    private boolean isInverseDepStored(SmDependency dep) {
        SmDependency sym = dep.getSymetric();
        if (sym == null) {
            return false;
        }
        return isPersistent(sym);
    }

    @objid ("fd245816-5986-11e1-991a-001ec947ccaf")
    private void load() {
        if (! this.loaded) {
            try (IModelLoader modelLoader = this.base.getModelLoaderProvider().beginLoadSession()) {
                this.base.loadCmsNode(this.cmsNodeId, modelLoader, false);
            } catch (DuplicateObjectException e) {
                this.base.getErrorSupport().fireError(new IOException("Failed loading "+this+": "+e.getLocalizedMessage(), e));
            } catch (RuntimeException e) {
                this.base.getErrorSupport().fireError(new IOException("Failed loading "+this+": "+e.toString(), e));
            }
        }
    }

    /**
     * Set the repository object of the given model object to this handler.
     * Propagates to all composition children in the same CMS node
     * 
     * @param obj a non CMS node model object
     */
    @objid ("fd245805-5986-11e1-991a-001ec947ccaf")
    private void propagateHandler(SmObjectImpl obj) {
        assert (! obj.getClassOf().isCmsNode());
        obj.setRepositoryObject(this);
        
        for (SmObjectImpl child : ExmlUtils.getLoadedCmsNodeContent(obj))
        {
            child.setRepositoryObject(this);
        }
    }

    @objid ("785dabaa-485e-11e2-91c9-001ec947ccaf")
    @Override
    public final boolean isDirty(SmObjectImpl obj) {
        return this.dirty;
    }

    @objid ("4d0a5074-a683-411a-a0d2-26438cf33469")
    @Override
    public String toString() {
        if (this.cmsNode != null) {
            return "ExmlStorageHandler {node="+this.cmsNode+", base="+this.base.toString()+"}";
        } else {
            return "ExmlStorageHandler {nodeid="+this.cmsNodeId+", base="+this.base.toString()+"}";
        }
    }

    /**
     * Look for the parent CMS node in the index.
     * 
     * @param obj the model object whose parent is wanted.
     * @return the model object parent CMS node or <code>null</code>.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of error in the indexes.
     */
    @objid ("a83af727-8650-44af-9572-9a71799225e8")
    private ObjId getParentCmsNode(SmObjectImpl obj) throws IndexException {
        ObjId objId = new ObjId(obj);
        ICmsNodeIndex cmsNodeIndex = this.base.getCmsNodeIndex();
        try {
            ObjId parent = cmsNodeIndex.getParentNodeOf(objId);
            /*
            if (parent==null && ! obj.getClassOf().getName().equals("Project")) {
                throw new IOException(objId+" has no parent CMS node");
            }*/
            return parent;
        } catch (IndexException | RuntimeException e) {
            // Set indexes as damaged
            this.base.setIndexesDamaged(e);
        
            // This will rebuild the indexes
            cmsNodeIndex = this.base.getCmsNodeIndex();
        
            // Try again
            try {
                ObjId parent = cmsNodeIndex.getParentNodeOf(objId);
                return parent;
            }  catch (IndexException | RuntimeException e2) {
                // rebuilding indexes failed
                e2.addSuppressed(e);
                throw e2;
            }
        }
    }

    @objid ("b8d57a7a-9c78-4ac7-aac7-4acaf82a2891")
    protected IExmlBase getBase() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.base;
    }

    @objid ("f26eb894-49b6-4711-ac7e-d7a11cc3cf87")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        if ( this != obj.getRepositoryObject()) {
            this.base.addCreatedObject(obj);
        
            if (!obj.getClassOf().isCmsNode()) {
                this.dirty = true;
                obj.setRepositoryObject(this);
            }
        }
    }

    @objid ("f0a06ea8-060f-4e02-9cee-2e9e378ee9ae")
    @Override
    public void setToReload(SmObjectImpl obj) {
        // Set the whole CMS node as to be reloaded
        this.loaded = false;
    }

    @objid ("a1f03963-0759-4ce9-8ea4-0a76aa868ecd")
    protected boolean areUsersLoaded(SmObjectImpl obj) {
        return obj.getData().hasAllStatus(IRStatus.REPO_USERS_LOADED)==StatusState.TRUE;
    }

    @objid ("21216608-3114-4f33-a26f-727acb1ec877")
    private SmObjectImpl getCmsNode(IModelLoader loader) throws DuplicateObjectException, IllegalReferenceException, IndexException {
        if (this.cmsNode == null) {
            this.cmsNode = this.base.findByObjId(this.cmsNodeId, loader);
            if (this.cmsNode != null) {
                this.cmsNode.setRepositoryObject(this);
            } else {
                Log.trace("ExmlStorageHandler.getCmsNode(IModelLoader) : %s not found in %s.", this.cmsNodeId, this.base);
            }
        
        }
        return this.cmsNode;
    }

    /**
     * @return The identifier of the represented CMS node.
     */
    @objid ("1b686a21-1bf7-419d-b0f8-eb816fe7fc45")
    public ObjId getCmsNodeId() {
        return this.cmsNodeId;
    }

}

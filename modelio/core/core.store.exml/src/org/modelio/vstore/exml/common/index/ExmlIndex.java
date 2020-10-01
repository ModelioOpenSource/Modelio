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

package org.modelio.vstore.exml.common.index;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.session.api.repository.StorageErrorSupport;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.index.builder.IndexBuilder;
import org.modelio.vstore.exml.common.index.builder.InvalidExmlException;
import org.modelio.vstore.exml.common.index.jdbm.JdbmIndex;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.plugin.VStoreExml;
import org.modelio.vstore.exml.resource.IExmlResourceProvider.ExmlResource;
import org.modelio.vstore.exml.resource.IExmlResourceProvider;
import org.xml.sax.InputSource;

/**
 * EXML repository index.
 * <p>
 * If the index is located on a remote file system, the index
 * is copied to a temporary directory by the EXML resource provider and will be deleted when
 * closing the repository.
 */
@objid ("04741aa9-d015-11e1-bf59-001ec947ccaf")
public class ExmlIndex {
    @objid ("bbe48531-7e64-11e1-bee3-001ec947ccaf")
    private IIndexDb db;

    /**
     * Used by {@link #commitSometimes()} to know how often a JDBM commit must be done.
     */
    @objid ("43dbc27d-3879-11e2-920a-001ec947ccaf")
    private static final int COMMIT_FREQ = 100;

    /**
     * Used by {@link #commitSometimes()} and reset by {@link #commitDb()}.
     */
    @objid ("43dbc27b-3879-11e2-920a-001ec947ccaf")
    private int commitCounter;

    @objid ("fd1f92a0-5986-11e1-991a-001ec947ccaf")
    private ICmsNodeIndex cmsNodeIndex;

    @objid ("fd1f92a1-5986-11e1-991a-001ec947ccaf")
    private IUserNodeIndex userNodeIndex;

    @objid ("cf3179a9-03e4-11e2-b5bf-001ec947ccaf")
    private final IExmlResourceProvider resProvider;

    @objid ("69a9c72a-ddc0-4b47-856b-c589a80a781f")
    private IndexBuilder builder;

    @objid ("c9f929a1-0295-4948-b618-f800ff04c7cf")
    private final StorageErrorSupport errSupport;

    /**
     * Instantiate the indexes and open them.
     * 
     * @param resProvider the EXML resources provider.
     * @param errSupport error reporter
     */
    @objid ("f778d0bd-d023-11e1-bf59-001ec947ccaf")
    public ExmlIndex(IExmlResourceProvider resProvider, StorageErrorSupport errSupport) {
        this.resProvider = resProvider;
        this.errSupport = errSupport;
    }

    /**
     * Build the indexes from scratch.
     * <p>
     * The index must be empty.
     * 
     * @param aMonitor a progress monitor
     * @throws java.io.IOException in case of failure reading EXML files.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of error writing the index.
     */
    @objid ("d5c90dd1-6231-11e1-b31a-001ec947ccaf")
    public void buildIndexes(IModelioProgress aMonitor) throws IOException, IndexException {
        SubProgress monitor = SubProgress.convert(aMonitor, "Building indexes", 320);
        boolean problemsFound = false;
        
        Collection<ExmlResource> allResources = this.resProvider.getAllResources(monitor.newChild(100));
        int nbResources = allResources.size();
        monitor.setWorkRemaining(nbResources);
        
        int i = 0;
        for (ExmlResource f : allResources) {
            try (InputStream is = f.bufferedRead()) {
                InputSource src = new InputSource(is);
                src.setPublicId(f.getPublicLocation());
                this.builder.run(src);
        
                commitSometimes();
                i++;
                if (i % 10 == 0) {
                    monitor.subTask( VStoreExml.I18N.getMessage("AbstractExmlRepository.mon.buildingIndexes.i",this.resProvider.getName(), i, nbResources));
                }
        
                monitor.worked(1);
            } catch (InvalidExmlException e) {
                problemsFound = true;
                this.errSupport.fireWarning(e);
            }
        }
        
        // Update index stamp if no problem found.
        // in the other case the indexes will be rebuilt on next repository opening.
        if (!problemsFound) {
            this.db.setStoredVersion();
        
            commitDb();
        
            compress(monitor.newChild(20));
        }
        
        commitDb();
        
        
        monitor.done();
    }

    /**
     * Close the indexes.
     * 
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of failure.
     */
    @objid ("f778d0d2-d023-11e1-bf59-001ec947ccaf")
    public void close() throws IndexException {
        if (this.db != null) {
            this.db.close();
            this.db = null;
            this.cmsNodeIndex = null;
            this.userNodeIndex = null;
            this.builder = null;
        }
    }

    /**
     * Check the indexes are up to date.
     * 
     * @throws java.io.IOException in case of stamp reading error
     * @throws org.modelio.vstore.exml.common.index.IndexOutdatedException if the indexes are out of date.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of read error.
     */
    @objid ("6905f779-4b8b-11e2-91c9-001ec947ccaf")
    public void checkUptodate() throws IOException, IndexOutdatedException, IndexException {
        // Check index format
        this.db.checkIndexFormat();
        
        // Check index is here
        if (this.cmsNodeIndex.isEmpty()) {
            throw new IndexOutdatedException(VStoreExml.I18N.getMessage("ExmlIndex.indexMissing", this.resProvider.getName()));
        }
        
        // Check index stamp
        String stamp = this.resProvider.getStamp();
        if( !(getStoredStamp().equals(stamp))) {
            throw new IndexOutdatedException(VStoreExml.I18N.getMessage("ExmlIndex.indexNotSynchro",
                    this.resProvider.getName(),
                    getStoredStamp(),
                    stamp));
        }
    }

    /**
     * Commit pending changes now, and reset internal counter.
     * 
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of index writing failure.
     */
    @objid ("43dbc287-3879-11e2-920a-001ec947ccaf")
    public void commitDb() throws IndexException {
        setStamp();
        
        this.db.commit();
        this.commitCounter = 0;
    }

    /**
     * Close and delete completely the indexes.
     * 
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of failure.
     */
    @objid ("cf3179b2-03e4-11e2-b5bf-001ec947ccaf")
    public void deleteIndexes() throws IndexException {
        try {
            if (this.db != null) {
                close();
            }
            FileUtils.delete(this.resProvider.getIndexAccessPath());
        } catch (IOException e) {
            throw convert(e);
        }
    }

    /**
     * Get a <i>try-with-resource</i> shield that closes the index on close() unless success() has been called.
     * <p>
     * To be used to close the index when an operation on the resource fails with an exception.
     * 
     * @return a shield that closes the index on close() unless success() has been called.
     */
    @objid ("39c250ba-1b58-4ff9-91a7-ab79ce48ce2b")
    public CloseOnFail getCloseOnFail() {
        return new CloseOnFail(this);
    }

    /**
     * @return the CMS node composition index.
     */
    @objid ("f778d0c6-d023-11e1-bf59-001ec947ccaf")
    public ICmsNodeIndex getCmsNodeIndex() {
        return this.cmsNodeIndex;
    }

    /**
     * @return the user/used index
     */
    @objid ("f778d0ca-d023-11e1-bf59-001ec947ccaf")
    public IUserNodeIndex getUserNodeIndex() {
        return this.userNodeIndex;
    }

    /**
     * Open the indexes and rebuild them if necessary.
     * 
     * @param aMonitor a progress monitor to report index building and opening.
     * @param metamodel the metamodel
     * @throws org.modelio.vstore.exml.common.index.IndexException if unable to open and unable to recreate indexes.
     */
    @objid ("f778d0ce-d023-11e1-bf59-001ec947ccaf")
    public void open(IModelioProgress aMonitor, SmMetamodel metamodel) throws IndexException {
        if (this.db != null) {
            throw new IllegalStateException("Indexes are already open.");
        }
        
        try {
            this.resProvider.buildIndexes(aMonitor);
        
            this.db = new JdbmIndex();
            // Uncomment to enable mapdb 2.0
            /*
            File mapDbDir = new File(this.resProvider.getIndexAccessPath(), "mapdb");
            if (mapDbDir.isDirectory() || this.resProvider.isWriteable()) {
                this.db = new MapDbIndex();
            }*/
        
        
            try (CloseOnFail shield = new CloseOnFail(this)) {
                this.db.open(aMonitor, this.resProvider, metamodel);
                this.cmsNodeIndex = this.db.getCmsNodeIndex();
                this.userNodeIndex = this.db.getUserNodeIndex();
                this.builder = new IndexBuilder(metamodel, getCmsNodeIndex(), getUserNodeIndex());
        
                shield.success();
            }
        } catch (IOException e) {
            throw convert(e);
        }
    }

    /**
     * Remove an object from all indexes.
     * <p>
     * If the object is a CMS node all its content is removed too.
     * 
     * @param id the identifier to remove
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure.
     */
    @objid ("f778d0c2-d023-11e1-bf59-001ec947ccaf")
    public void removeFromIndexes(final ObjId id) throws IndexException {
        for (ObjId childId : getCmsNodeIndex().getCmsNodeContent(id)) {
            getUserNodeIndex().remove(childId);
        }
        
        //clean parent index
        getCmsNodeIndex().removeObj(id);
        
        //clean uses index
        getUserNodeIndex().remove(id);
        
        commitSometimes();
    }

    /**
     * Update the indexes for the given CMS node.
     * 
     * @param cmsNodeId a CMS node identifier
     * @throws org.modelio.vstore.exml.common.index.IndexException if the indexes cannot be modified.
     */
    @objid ("fbb237ba-1e83-11e2-90db-001ec947ccaf")
    public void updateIndexes(ObjId cmsNodeId) throws IndexException {
        try {
            //Clean the indexes from all references of the given CMS node and all its content.
            removeFromIndexes(cmsNodeId);
        
            // Update indexes
            ExmlResource resource = this.resProvider.getResource(cmsNodeId);
            try (InputStream is = resource.bufferedRead()) {
                if (is != null) {
                    InputSource src = new InputSource(is);
                    src.setPublicId(resource.getPublicLocation());
                    this.builder.run(src);
                }
            } catch (InvalidExmlException e) {
                this.errSupport.fireWarning(e);
            }
        
            commitSometimes();
        } catch (IOException e) {
            throw convert(e);
        }
    }

    /**
     * Commit JDBM pending changes once a time to free memory while keeping
     * hard disk load charge low.
     * @throws java.io.IOException in case of JDBM failure.
     */
    @objid ("43dbc280-3879-11e2-920a-001ec947ccaf")
    private void commitSometimes() throws IndexException {
        this.commitCounter++;
        if (this.commitCounter >= COMMIT_FREQ) {
            this.db.commit();
            this.commitCounter = 0;
        }
    }

    @objid ("b878a549-e62a-472d-a363-745026388a29")
    private void setStamp() throws IndexException {
        try {
            String stamp = this.resProvider.getStamp();
            this.db.setStamp(stamp);
        } catch (IOException e) {
            throw convert(e);
        }
    }

    /**
     * Read the index stamp.
     * 
     * @return the index stamp.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure
     */
    @objid ("964bbe53-b4c1-4b2e-94f5-c478909b5928")
    private String getStoredStamp() throws IndexException {
        return this.db.getStoredStamp();
    }

    /**
     * Defragments the index, so it consumes less space. This commits any uncommitted data.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done()
     * on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of failure
     */
    @objid ("3fa2dfbe-6770-41c1-be4c-6955841eefb9")
    public void compress(IModelioProgress monitor) throws IndexException {
        this.db.compress(monitor);
    }

    @objid ("2b5174b6-f063-4c84-aec0-36779029efc0")
    private static IndexException convert(IOException e) {
        return new IndexException(FileUtils.getLocalizedMessage(e), e);
    }

    /**
     * Get a <i>try-with-resource</i> shield that closes the index on close() unless success() has been called.
     * <p>
     * To be used to close the index when an operation on the resource fails with an exception.
     */
    @objid ("27a80efa-23f8-44e1-ad8b-2a94f7b4cbea")
    public static class CloseOnFail implements AutoCloseable {
        @objid ("067d6eca-77c6-41f4-ae23-067a221aab57")
        private ExmlIndex resource;

        /**
         * Initialize the shield
         * 
         * @param resource the resource to close on failure
         */
        @objid ("0007ff9f-57f7-4450-9960-cf7200662e10")
        public CloseOnFail(ExmlIndex resource) {
            this.resource = resource;
        }

        /**
         * Abort the closure of the resource.
         * <p>
         * To be called after the operation has succeeded.
         */
        @objid ("a3c5af98-9d29-4a69-9f96-c605029eff5d")
        public void success() {
            this.resource = null;
        }

        @objid ("d26d6514-f234-4b43-a867-89de4219f9c2")
        @Override
        public void close() throws IndexException {
            if (this.resource != null) {
                this.resource.close();
                this.resource = null;
            }
        }

    }

}

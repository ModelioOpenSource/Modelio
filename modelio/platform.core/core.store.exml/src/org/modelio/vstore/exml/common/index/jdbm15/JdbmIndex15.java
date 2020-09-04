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

package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOError;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.index.ICmsNodeIndex;
import org.modelio.vstore.exml.common.index.IIndexDb;
import org.modelio.vstore.exml.common.index.IUserNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.index.IndexOutdatedException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.plugin.VStoreExml;
import org.modelio.vstore.exml.resource.IExmlResourceProvider;

/**
 * JDBM indexes with format version <= 15.
 * 
 * @author cma
 * @since 3.6.1
 * @deprecated indexes for Modelio < 3.6.1
 */
@objid ("6d249c99-e805-4638-b1d3-26b6180a772d")
@Deprecated
public class JdbmIndex15 implements IIndexDb {
    @objid ("a0ddca08-0d5a-4558-a656-1619fbf288c5")
    private RecordManager db;

    @objid ("7053f77d-222d-493c-99bf-7a8c0923048a")
    private static final String VERSION_OBJ_NAME = "version_of_index";

    @objid ("e42b4b69-c2b0-4f0e-a47b-e1c88ccca943")
    private static final String STAMP_OBJ_NAME = "stamp_of_index";

    /**
     * Indexes format version.
     * Increment to force indexes regeneration.
     * <p>
     * <h3>History:</h3>
     * <ul>
     * <li> 3 : bug fix ?
     * <li> 4, 5, 6 : 20/12/2012 : add indexes by metaclass
     * <li> 7: 03/01/2013 : add name to object index
     * <li> 8: 18/01/2013, 28/01/2013: indexes were not correctly updated on svn update
     * and child lost their parent when the parent index was recomputed.
     * <li> 9: 06/12/2013 : found  & fixed bugs in indexes refresh on Modelio 3.0.1.
     * Force index rebuild to avoid shell objects in the model.
     * <li> 10: 20/03/2015 - Modelio 3.4 Raiju with multiple metamodels : {@link ObjId#hashCode()} does not
     * return same result than Modelio 3.3
     * <li> 11: 08/06/2015 - Modelio 3.4 Raiju : REFOBJ tags don't provide the CMS node anymore.
     * Consequence : the users index record uses from a CMS node to individual objects instead of CMS nodes.
     * <li> 12: 11/06/2015 - Modelio 3.4 Raiju : ObjId hash code must not depend on metamodel fragment version,
     * use only the metaclass name..
     * <li> 13: 30/10/2015 - Modelio 3.4.1 Raiju : merged user index and foreign index. Fixed bugs in index building
     * on COMPID and REFOBJ tags that were visible on bidirectional Associations and Links.
     * <li> 14 : 07/01/2016 - Modelio 3.5: UUIDs are now strings
     * <li> 15 : 22/08/2016 - Modelio 3.6: use qualified MClass names.
     * </ul>
     */
    @objid ("e39ea504-b07f-4e81-82cd-21f82a428ae6")
    private static final int INDEX_FORMAT_VERSION = 15;

    @objid ("9b30b384-0be2-4c89-99c0-f274ea0e183c")
    private CmsNodeIndex15 cmsNodeIndex;

    @objid ("2b21a6ef-d828-4740-ad95-5eb21cec7442")
    private UserNodeIndex userNodeIndex;

    @objid ("3cc38109-59fb-42b8-81cc-5e88ed5ddac5")
    private IExmlResourceProvider resProvider;

    /**
     * Commit pending changes now, and reset internal counter.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of JDBM failure.
     */
    @objid ("c63b68ab-50ec-4144-9cf0-5cae745e4e16")
    @Override
    public void commit() throws IndexException {
        try {
            this.db.commit();
        
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("fd72b626-9e2d-4aca-8204-c4ac76876d54")
    @Override
    public void open(IModelioProgress aMonitor, IExmlResourceProvider resProvider, SmMetamodel metamodel) throws IndexException {
        if (this.db != null) {
            throw new IllegalStateException("Indexes are already open.");
        }
        
        try {
            this.resProvider = resProvider;
            this.db = RecordManagerFactory.createRecordManager(resProvider.getIndexAccessPath() + "/index");
        
            this.cmsNodeIndex = new CmsNodeIndex15(this.db, metamodel);
            this.userNodeIndex = new UserNodeIndex(this.db, metamodel);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("46339e92-ef39-4485-9b90-2111be90a1d3")
    @Override
    public void close() throws IndexException {
        if (this.db != null) {
            try {
                this.db.close();
                this.db = null;
            } catch (InternalError e) {
                throw JdbmIndexException.from(e);
            } catch (IOError e) {
                throw JdbmIndexException.from(e);
            } catch (IOException e) {
                throw JdbmIndexException.from(e);
            } 
        }
    }

    /**
     * Read the index format version.
     * @return the index format version
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure
     */
    @objid ("5a0c7ae7-20a8-4b4f-91e6-f5289e98ca3c")
    @Override
    public int getStoredVersion() throws IndexException {
        try {
            long id = this.db.getNamedObject(VERSION_OBJ_NAME);
            if (id == 0) {
                return 0;
            } else {
                return (Integer) this.db.fetch(id);
            }
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("ff21b462-bcbe-48c8-914a-db1f5fa0b39d")
    @Override
    public void setStamp(String stamp) throws IndexException {
        try {
            long id = this.db.getNamedObject(STAMP_OBJ_NAME);
            if (id != 0) {
                this.db.update(id, stamp);
            } else {
                this.db.setNamedObject(STAMP_OBJ_NAME, this.db.insert(stamp));
            }
        
            this.db.commit();
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("a9900d23-fb59-47ce-b3f1-2b4eba38252f")
    @Override
    public String getStoredStamp() throws IndexException {
        try {
            long id = this.db.getNamedObject(STAMP_OBJ_NAME);
            if (id == 0) {
                return "";
            } else {
                return (String) this.db.fetch(id);
            }
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    /**
     * Save the index format version.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure.
     */
    @objid ("391713d6-b50f-4a72-987a-f3189751880a")
    @Override
    public void setStoredVersion() throws IndexException {
        try {
            long id = this.db.getNamedObject(VERSION_OBJ_NAME);
            if (id != 0) {
                this.db.update(id, INDEX_FORMAT_VERSION);
            } else {
                this.db.setNamedObject(VERSION_OBJ_NAME, this.db.insert(INDEX_FORMAT_VERSION));
            }
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    /**
     * Defragments the index, so it consumes less space. This commits any uncommitted data.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done()
     * on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of failure
     */
    @objid ("82e66cfe-4a48-4530-a6fb-cc50af7fc15d")
    @Override
    public void compress(IModelioProgress monitor) throws IndexException {
        try {
            this.db.defrag();
        
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("cb06334b-59fd-4df5-bcf9-a9999b55780d")
    @Override
    public ICmsNodeIndex getCmsNodeIndex() {
        return this.cmsNodeIndex;
    }

    @objid ("426fb90f-96d0-4e64-8ec2-ee67093b2476")
    @Override
    public IUserNodeIndex getUserNodeIndex() {
        return this.userNodeIndex;
    }

    @objid ("b21f92ab-2832-47e4-a9ec-0156f838928a")
    @Override
    public void checkIndexFormat() throws IndexException, IndexOutdatedException {
        int storedVersion = getStoredVersion();
        if (storedVersion == 0) {
            throw new IndexOutdatedException(VStoreExml.getMessage("ExmlIndex.indexMissing", this.resProvider.getName()));
        
        } else if ( INDEX_FORMAT_VERSION > storedVersion) {
            throw new IndexOutdatedException(VStoreExml.getMessage("ExmlIndex.indexFormatOld",
                    this.resProvider.getName(),
                    storedVersion,
                    INDEX_FORMAT_VERSION));
        } else if ( INDEX_FORMAT_VERSION < storedVersion) {
            throw new IndexOutdatedException(VStoreExml.getMessage("ExmlIndex.indexFormatNew",
                    this.resProvider.getName(),
                    storedVersion,
                    INDEX_FORMAT_VERSION));
        }
    }

}

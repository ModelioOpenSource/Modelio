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

package org.modelio.vstore.exml.common.index.jdbm;

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

@objid ("2dd4738f-86b5-4b6d-9e69-c395aa9f9a5f")
public class JdbmIndex implements IIndexDb {
    @objid ("4ef193a6-cf3a-4867-93f7-d8c5c1b9153b")
    private RecordManager db;

    @objid ("78450c5d-e493-47cf-93b8-ca4a6cf25643")
    private static final String VERSION_OBJ_NAME = "version_of_index";

    @objid ("7d134fdb-836c-4f11-ae58-fdea6b625710")
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
     * <li> 16 : 01/02/2017 - Modelio 3.6.1: Reworked used nodes indexes to include dependency name,
     * added a symbol table to share metaclass names and dependency names to save space.
     * </ul>
     */
    @objid ("7dc0cd99-1877-11e2-9dfc-001ec947ccaf")
    private static final int INDEX_FORMAT_VERSION = 16;

    @objid ("15db0306-18af-4014-91d4-80c848b40bca")
    private CmsNodeIndex cmsNodeIndex;

    @objid ("6b640d4e-20ba-43e7-93f0-9bc23114e5ad")
    private UserNodeIndex userNodeIndex;

    @objid ("06b11858-950a-455b-9378-6ee28120cfa7")
    private IExmlResourceProvider resProvider;

    /**
     * Commit pending changes now, and reset internal counter.
     * 
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of JDBM failure.
     */
    @objid ("ea23dd5d-a9ba-4aae-96e0-51b10def6e74")
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

    @objid ("ffb1f476-6aae-4279-a6a5-d4652ff92c04")
    @Override
    public void open(IModelioProgress aMonitor, IExmlResourceProvider resProvider, SmMetamodel metamodel) throws IndexException {
        if (this.db != null) {
            throw new IllegalStateException("Indexes are already open.");
        }
        
        try {
            this.resProvider = resProvider;
            this.db = RecordManagerFactory.createRecordManager(resProvider.getIndexAccessPath() + "/index");
            ObjIdSerializer objIdSerializer = new ObjIdSerializer(metamodel);
            SymbolTable<ObjId> objIdTable = new SymbolTable<>(this.db, "table_objid", objIdSerializer);
            SymbolTable<String> symbolTable = new SymbolTable<>(this.db, "symbol", UTFSerializer.INSTANCE);
        
            this.cmsNodeIndex = new org.modelio.vstore.exml.common.index.jdbm.CmsNodeIndex(this.db, objIdTable);
            this.userNodeIndex = new org.modelio.vstore.exml.common.index.jdbm.UserNodeIndex(this.db, symbolTable, objIdTable);
        } catch (InternalError e) {
            throw JdbmIndexException.from(e);
        } catch (IOError e) {
            throw JdbmIndexException.from(e);
        } catch (IOException e) {
            throw JdbmIndexException.from(e);
        }
    }

    @objid ("4cad4d71-2c3c-4e29-8442-15a9acfa124d")
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
     * 
     * @return the index format version
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure
     */
    @objid ("367bd1fd-6a8b-4a07-90c3-ba79a5517541")
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

    @objid ("b53f76c7-1502-47e3-a97f-de585fb23903")
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

    @objid ("c8da44c2-fcef-4836-892d-cf46b60dbab1")
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
     * 
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure.
     */
    @objid ("e19e13cc-f742-449e-a654-27f2b1d27616")
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
     * 
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done()
     * on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of failure
     */
    @objid ("bfd527e3-0871-4bfb-9f39-7ff936d05d91")
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

    @objid ("6fb3045d-f237-48fb-9e03-58a39ef79b67")
    @Override
    public ICmsNodeIndex getCmsNodeIndex() {
        return this.cmsNodeIndex;
    }

    @objid ("b66facc7-0083-466e-854d-f0c3d5ab09ee")
    @Override
    public IUserNodeIndex getUserNodeIndex() {
        return this.userNodeIndex;
    }

    @objid ("7dc0cd93-1877-11e2-9dfc-001ec947ccaf")
    @Override
    public void checkIndexFormat() throws IndexOutdatedException, IndexException {
        int storedVersion = getStoredVersion();
        if (storedVersion == 0) {
            throw new IndexOutdatedException(VStoreExml.I18N.getMessage("ExmlIndex.indexMissing", this.resProvider.getName()));
        
        } else if ( INDEX_FORMAT_VERSION > storedVersion) {
            throw new IndexOutdatedException(VStoreExml.I18N.getMessage("ExmlIndex.indexFormatOld",
                    this.resProvider.getName(),
                    storedVersion,
                    INDEX_FORMAT_VERSION));
        } else if ( INDEX_FORMAT_VERSION < storedVersion) {
            throw new IndexOutdatedException(VStoreExml.I18N.getMessage("ExmlIndex.indexFormatNew",
                    this.resProvider.getName(),
                    storedVersion,
                    INDEX_FORMAT_VERSION));
        }
    }

}

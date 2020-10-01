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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.resource.IExmlResourceProvider;

/**
 * Interface that an index implementation must implement.
 * @author cma
 */
@objid ("139d53b5-82b4-44df-865a-dd357fe8ef21")
public interface IIndexDb {
    /**
     * Close all connections and release resources.
     * 
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure
     */
    @objid ("8ffd24ac-aae7-4f71-8e73-c265f5a68885")
    void close() throws IndexException;

    /**
     * Commit pending changes now, and reset internal counter.
     * 
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of JDBM failure.
     */
    @objid ("74f81083-ce49-4b84-ada9-2e905c544d8b")
    void commit() throws IndexException;

    /**
     * Read the index format version.
     * 
     * @return the index format version
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure
     */
    @objid ("75ecb7fa-b721-470e-ba64-c5ca658ccf22")
    int getStoredVersion() throws IndexException;

    /**
     * Write the index  stamp.
     * 
     * @param stamp the index  stamp.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure
     */
    @objid ("8e1b1735-8711-4ebd-b22d-0052c3354d7a")
    void setStamp(String stamp) throws IndexException;

    /**
     * Read the index stamp.
     * 
     * @return the index stamp.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure
     */
    @objid ("f0bac8d8-d1a0-4637-9bf1-68642eac1e25")
    String getStoredStamp() throws IndexException;

    /**
     * Save the index format version.
     * 
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O failure.
     */
    @objid ("1e34a4c3-93fd-4e01-9350-e968747940ed")
    void setStoredVersion() throws IndexException;

    /**
     * Defragments the index, so it consumes less space. This commits any uncommitted data.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done()
     * on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of failure
     */
    @objid ("8df7be19-49d3-4a5e-b108-927741c1ae93")
    void compress(IModelioProgress monitor) throws IndexException;

    @objid ("e84ae47c-aff3-45aa-87fa-a99498e7b34b")
    void open(IModelioProgress aMonitor, IExmlResourceProvider resProvider, SmMetamodel metamodel) throws IndexException;

    /**
     * @return the CMS nodes and objects index
     */
    @objid ("e2f168ed-9ff2-4aea-9040-871afbf342c6")
    ICmsNodeIndex getCmsNodeIndex();

    /**
     * @return the inter CMS nodes dependencies index
     */
    @objid ("d30e6aba-b356-418c-b1e6-9932d28f9ff9")
    IUserNodeIndex getUserNodeIndex();

    @objid ("e538ee9b-2a35-41bb-87e3-6b60aafcf424")
    void checkIndexFormat() throws IndexOutdatedException, IndexException;

}

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
package org.modelio.vstore.exml.resource;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the repository files organization.
 * <p>
 * All paths to be passed or returned are relative to the repository root path.
 */
@objid ("4d16f7c3-1c4e-11e2-8eb9-001ec947ccaf")
public interface IExmlRepositoryGeometry {
    /**
     * The administration directory name.
     */
    @objid ("d82eb560-1ceb-11e2-8eb9-001ec947ccaf")
    public static final String ADMIN_DIRNAME = "admin";

    /**
     * The blobs directory name.
     */
    @objid ("a86b59e2-c0d0-48b7-a17c-8d19f68699d5")
    public static final String BLOBS_DIRNAME = "blobs";

    /**
     * File extension for blob files.
     */
    @objid ("3d279d3c-14e4-45f7-afd8-c4231443859e")
    public static final String EXT_BLOB = ".blob";

    /**
     * File extension for EXMl files.
     */
    @objid ("986ddcd8-12de-11e2-816a-001ec947ccaf")
    public static final String EXT_EXML = ".exml";

    /**
     * File extension for EXML files storing references to non versioned nodes.
     */
    @objid ("986ddcd2-12de-11e2-816a-001ec947ccaf")
    public static final String EXT_LOCAL_EXML = ".local.exml";

    /**
     * Path of the versions property file relative to the repository directory.
     */
    @objid ("2a5b046d-d7d8-455a-8f78-e734872c8a72")
    public static final String FORMAT_VERSION_PATH = ADMIN_DIRNAME + "/format_version.dat";

    /**
     * Index directory name.
     */
    @objid ("9232dfb0-e7e1-4a29-9447-983eed6831d6")
    public static final String INDEX_DIRNAME = ".index";

    /**
     * Path of the versions property file relative to the repository directory.
     */
    @objid ("8c591bea-eec3-4d8b-9805-7191284f216b")
    public static final String MM_DESCRIPTOR_PATH = ADMIN_DIRNAME + "/metamodel_descriptor.xml";

    /**
     * EXML files storage directory name.
     */
    @objid ("d82eb55a-1ceb-11e2-8eb9-001ec947ccaf")
    public static final String MODEL_DIRNAME = "model";

    /**
     * Get the blob key stored in the given file.
     * @param relativePath a blob file path relative to the repository root.
     * @return the blob key.
     */
    @objid ("0118f654-b100-4826-8cf9-1f31a06d46c3")
    String getBlobKey(String relativePath);

    /**
     * Get the path where the given blob should be stored.
     * @param blobKey a Blob key
     * @return the path where the blob is stored.
     */
    @objid ("f7c479d2-dbe9-4e58-bfd1-8d32847eac28")
    String getBlobPath(String blobKey);

    /**
     * Get the directories that should exist on an empty repository.
     * <p>
     * The returned paths are relative to the repository root.
     * @param metamodel the metamodel
     * @return the list of directories.
     */
    @objid ("fe9b781b-fb6e-4361-acc2-99d37859e1e0")
    Collection<String> getInitialDirectories(MMetamodel metamodel);

    /**
     * Get the non versioned part EXML file path of an element reference relative to the repository path.
     * @param ref an element reference.
     * @return the EXML file path relative to the repository path.
     */
    @objid ("6cf3ca4f-3933-41d8-bdd1-5d1f1733a21c")
    String getLocalFileRelativePath(final MRef ref);

    /**
     * Get the path where the metamodel descriptor XMl file is stored.
     * @return the metamodel descriptor path
     * @since 3.6
     */
    @objid ("a257a196-a7eb-4a2f-aaa9-4991d72c829c")
    String getMetamodelDescriptorPath();

    /**
     * Get the 'model' directory containing a sub directory per metaclass.
     * @return the model directory path relative to the repository root.
     */
    @objid ("3bc1e08d-fa36-457b-8a48-e3ad0f2cc2bb")
    String getModelPath();

    /**
     * Get the element reference representing the given EXML file.
     * @param relativePath an EXML file path relative to the repository root.
     * @return the represented element reference.
     */
    @objid ("d717c49c-1ceb-11e2-8eb9-001ec947ccaf")
    MRef getObRef(String relativePath);

    /**
     * Get the EXML file path of an element reference relative to the repository path.
     * @param ref an element reference.
     * @return the EXML file path relative to the repository path.
     */
    @objid ("9a81ce78-b1c3-44dc-b302-befcccc6001e")
    String getRelativePath(MRef ref);

    /**
     * @param relativePath a file path relative to the repository root.
     * @return <code>true</code> if the file is a blob file.
     */
    @objid ("d5f9eacc-2293-4840-920f-d31c4cdd5309")
    boolean isBlobPath(String relativePath);

    /**
     * Tells whether a file is an EXML file that can be versioned.
     * <p>
     * The answer is based on the file extension.
     * Returns <i>false</i> if it is a {@link IExmlRepositoryGeometry#EXT_LOCAL_EXML ".local.exml"} file.
     * @param relativePath a file path relative to the repository root.
     * @return <i>true</i> if it is an EXML file, else <i>false</i>.
     */
    @objid ("d717c499-1ceb-11e2-8eb9-001ec947ccaf")
    boolean isModelPath(String relativePath);
}


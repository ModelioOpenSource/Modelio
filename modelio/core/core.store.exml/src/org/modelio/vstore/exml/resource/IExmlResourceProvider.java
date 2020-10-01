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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vstore.exml.common.RepositoryVersions;
import org.modelio.vstore.exml.common.model.ObjId;

/**
 * Resource provider for an EXML repository.
 * <p>
 * Allows to define different EXML repository sources as local files,
 * distant URL, ...
 */
@objid ("8b22cfd7-0326-11e2-b5bf-001ec947ccaf")
public interface IExmlResourceProvider {
    /**
     * Build the indexes in a local directory.
     * 
     * @param monitor a progress monitor.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("cf2f1764-03e4-11e2-b5bf-001ec947ccaf")
    void buildIndexes(IModelioProgress monitor) throws IOException;

    /**
     * Close the resource provider.
     * 
     * @throws java.io.IOException in case of failure.
     */
    @objid ("cf2f176b-03e4-11e2-b5bf-001ec947ccaf")
    void close() throws IOException;

    /**
     * Commit all writes that were done since last commit().
     * 
     * @throws java.io.IOException in case of failure.
     */
    @objid ("cf2f176d-03e4-11e2-b5bf-001ec947ccaf")
    void commit() throws IOException;

    /**
     * Create the repository.
     * 
     * @param mMetamodel the initial metamodel
     * @throws java.io.IOException in case of failure
     */
    @objid ("cf2f1775-03e4-11e2-b5bf-001ec947ccaf")
    void createRepository(MMetamodel mMetamodel) throws IOException;

    /**
     * Delete a blob
     * 
     * @param blob a blob identifier
     * @throws java.io.IOException in case of failure
     */
    @objid ("19e4043a-65a4-49aa-9f69-00cf1b8b2ca2")
    void deleteBlob(String blob) throws IOException;

    /**
     * Tells whether the repository exists.
     * 
     * @return whether the repository exists.
     * @throws java.io.IOException in case of I/O failure.
     */
    @objid ("f97b2aee-f14f-481d-b518-6bf0fbe2af91")
    boolean exists() throws IOException;

    /**
     * Get all the repository content.
     * 
     * @param aMonitor a progress monitor.
     * @return all CMS nodes.
     * @throws java.io.IOException in case of failure
     */
    @objid ("cf2f176f-03e4-11e2-b5bf-001ec947ccaf")
    Collection<ExmlResource> getAllResources(IModelioProgress aMonitor) throws IOException;

    /**
     * Get the geometry of the repository.
     * <p>
     * {@link #open()} or {@link #createRepository(MMetamodel)} must have been called once before this method.
     * 
     * @return the repository geometry.
     */
    @objid ("89ce59ba-a607-4291-93ce-0fce053f3d89")
    IExmlRepositoryGeometry getGeometry();

    /**
     * Get the path where the EXML indexes will be accessible after having called {@link #buildIndexes(IModelioProgress)}.
     * 
     * @return the EXML indexes path.
     */
    @objid ("e710f189-e8e0-489c-9b6b-cdd574ae1a08")
    File getIndexAccessPath();

    /**
     * Get access to the non CMS managed content for a CMS node.
     * <p>
     * Used by EXML repositories in versioned mode.
     * 
     * @param cmsNodeId the reference of a CMS node.
     * @return the content
     * @throws java.io.IOException in case of failure
     */
    @objid ("978b5e97-12de-11e2-816a-001ec947ccaf")
    ExmlResource getLocalResource(ObjId cmsNodeId) throws IOException;

    /**
     * Get access to the metamodel descriptor.
     * 
     * @return the metamodel descriptor resource.
     * @throws java.io.IOException in case of failure
     * @since 3.6
     */
    @objid ("dfa6784f-15c9-4faa-a18f-0062df6c7a63")
    ExmlResource getMetamodelDescriptorResource() throws IOException;

    /**
     * Get a short displayable name for this repository.
     * <p>
     * This name should be short enough to be displayed and should
     * reflect the repository content, for example the root element name.
     * An implementation may compute this name or be initialized with it.
     * 
     * @return the repository name.
     */
    @objid ("a23e9ba0-1c4a-4354-abdf-b75630a8b4c2")
    String getName();

    /**
     * Get an access to the resource pointed by the given relative path.
     * <p>
     * Returns a resource even if it does not exist.
     * 
     * @param relativePath a path relative to the repository root.
     * @return the resource access, never null.
     * @throws java.io.IOException on I/O error determining the resource
     */
    @objid ("2a38b7e0-b4b3-4da5-9cc4-08ce3c144bfe")
    ExmlResource getRelativePathResource(String relativePath) throws IOException;

    /**
     * Get the resource where a {@link RepositoryVersions} can be read/written.
     * 
     * @return the repository format versions resource.
     * @throws java.io.IOException in case of failure
     */
    @objid ("e8992ad0-fcab-4e29-8e5a-5a60c06df29f")
    ExmlResource getRepositoryVersionResource() throws IOException;

    /**
     * Get access to the EXML content for a CMS node.
     * 
     * @param id the reference of a CMS node.
     * @return the content
     * @throws java.io.IOException in case of failure
     */
    @objid ("cf2f1760-03e4-11e2-b5bf-001ec947ccaf")
    ExmlResource getResource(ObjId id) throws IOException;

    /**
     * Get a stamp that is updated on each {@link #commit()}.
     * <p>
     * To be used to ensure indexes and other local resources are synchronized
     * with the remote storage location.
     * <p>
     * Returns empty string if there is no stamp.
     * 
     * @return the repository stamp or empty string.
     * @throws java.io.IOException in case of I/O error preventing from reading the stamp
     */
    @objid ("b19917ea-0859-4803-ace8-b32cd49e97f3")
    String getStamp() throws IOException;

    /**
     * Get the repository location as an URI
     * 
     * @return the repository location
     */
    @objid ("cf2f1777-03e4-11e2-b5bf-001ec947ccaf")
    URI getURI();

    /**
     * Tells whether the repository can be browsed as a file system directory.
     * <p>
     * In this case indexes can be rebuilt. In the other case it is impossible to rebuild indexes
     * and {@link #getAllResources(IModelioProgress)} must not be called.
     * 
     * @return <code>true</code> if the repository can be browsed, <code>false</code> in the other case.
     */
    @objid ("644389dd-3565-4176-b4d7-2916677600cf")
    boolean isBrowsable();

    /**
     * Tells whether the repository is accessible in read/write mode
     * or only in read only mode.
     * 
     * @return <code>true</code> if the repository is read/write, <code>false</code> if it is read only.
     */
    @objid ("5a44d1ec-0724-11e2-9eb7-001ec947ccaf")
    boolean isWriteable();

    /**
     * Open the provider so that it is ready .
     * <p>
     * The implementation will probably need to read the format version
     * stored in {@link #getRepositoryVersionResource()} with {@link #readRepositoryVersion()}.
     * 
     * @throws java.io.IOException in case of non recoverable failure.
     */
    @objid ("5ef60438-4f0d-4441-b359-f5673f9cbb41")
    void open() throws IOException;

    /**
     * Read the content of a blob.
     * <p>
     * Returns <code>null</code> if there is no blob with such key.
     * 
     * @param key a blob key
     * @return the blob content or <code>null</code>.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("7b8b05e8-a0dd-445e-854a-59675996c3e9")
    InputStream readBlob(String key) throws IOException;

    /**
     * Read the informations of a blob.
     * <p>
     * Returns <code>null</code> if there is no blob with such key.
     * 
     * @param key a blob key
     * @return the blob content or <code>null</code>.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("8aa80024-ca4d-43f8-87ec-e8e16e9e518a")
    IBlobInfo readBlobInfo(String key) throws IOException;

    /**
     * Get the repository formats versions.
     * <p>
     * May return <i>null</i> if it is an old repository with no format version file.
     * <p>
     * The implementation will probably need to read the format version
     * stored in {@link #getRepositoryVersionResource()}.
     * 
     * @return the repository versions, null if none stored yet.
     * @throws java.io.IOException in case of error getting the versions
     */
    @objid ("ddb2f7a8-bcef-4bfc-8134-ac31941498af")
    RepositoryVersions readRepositoryVersion() throws IOException;

    /**
     * Ensure all needed directories exist.
     * <p>
     * Create missing directories.
     * Do not delete directories not needed anymore.
     * 
     * @param mMetamodel the metamodel to match.
     * @throws java.io.IOException in case of failure
     */
    @objid ("8e06a65d-5fed-4ac8-87f8-c78b44fd96a2")
    void updateRepositoryStructure(MMetamodel mMetamodel) throws IOException;

    /**
     * Write to a blob.
     * 
     * @param info the blob info
     * @return a stream to write the blob content to.
     * @throws java.io.IOException in case of copy failure.
     */
    @objid ("c77d483e-f2bd-4c2c-91e2-25ec9b385b8f")
    OutputStream writeBlob(IBlobInfo info) throws IOException;

    /**
     * Recompute the stamp.
     * <p>
     * Should be called by {@link #commit()}.
     * May be called by external code that directly modifies the repository files
     * such as version managers.
     * 
     * @throws java.io.IOException in case of failure.
     */
    @objid ("79a4c697-eff7-401b-8e26-74d2a0dbccef")
    void writeStamp() throws IOException;

    /**
     * Represents an EXML node resource.
     */
    @objid ("cf2f175a-03e4-11e2-b5bf-001ec947ccaf")
    interface ExmlResource {
        /**
         * Open a buffered input stream on the resource.
         * <p>
         * Returns <code>null</code> if the resource does not exist.
         * 
         * @return the buffered input stream or <code>null</code> if the resource does not exist.
         * @throws java.io.IOException in case of failure.
         */
        @objid ("3a3a8df7-a7ea-480c-80f1-1a70c14ed59f")
        default InputStream bufferedRead() throws IOException {
            InputStream is = read();
            return (is == null) ? null : new BufferedInputStream(is);
        }

        /**
         * Open a buffered output stream on the resource.
         * 
         * @return the buffered output stream .
         * @throws java.io.IOException in case of failure.
         */
        @objid ("a90ac57a-26f8-4897-ba15-51bab833b6cf")
        default OutputStream bufferedWrite() throws IOException {
            OutputStream os = write();
            return (os == null) ? null : new BufferedOutputStream(os);
        }

        /**
         * Delete the resource.
         * 
         * @throws java.io.IOException in case of failure.
         */
        @objid ("978dc0e1-12de-11e2-816a-001ec947ccaf")
        void delete() throws IOException;

        /**
         * Get the resource URI.
         * <p>
         * Will be used for error reporting.
         * 
         * @return the resource URI.
         */
        @objid ("92debe6e-2cd2-11e2-81f1-001ec947ccaf")
        String getPublicLocation();

        /**
         * Open an input stream on the resource.
         * <p>
         * Returns <code>null</code> if the resource does not exist.
         * 
         * @return the input stream or <code>null</code> if the resource does not exist.
         * @throws java.io.IOException in case of failure.
         */
        @objid ("cf2f175c-03e4-11e2-b5bf-001ec947ccaf")
        InputStream read() throws IOException;

        /**
         * Open an output stream on the resource.
         * 
         * @return an output stream.
         * @throws java.io.IOException in case of failure.
         */
        @objid ("cf2f175e-03e4-11e2-b5bf-001ec947ccaf")
        OutputStream write() throws IOException;

    }

}

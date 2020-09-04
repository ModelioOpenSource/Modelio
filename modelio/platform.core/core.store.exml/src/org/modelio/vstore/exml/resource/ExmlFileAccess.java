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

package org.modelio.vstore.exml.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vstore.exml.common.model.ExmlTags;

/**
 * Helper to map {@link File} paths to {@link MRef} for File based repositories.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("14bcb3b4-eca1-4fad-b19f-8f1690c55f30")
public class ExmlFileAccess {
    @objid ("18be0943-ed06-4883-b61d-91ac63c468e8")
    private final File cachedModelPath;

    @objid ("6fa39d12-2ce1-48e1-8153-86d632916f76")
    private final IExmlRepositoryGeometry geometry;

    @objid ("2e450d97-56ac-40cf-a0b3-d7beb1ec4e43")
    private final File repositoryRoot;

    @objid ("f21cc796-1ef1-4a7a-af0d-2dbe3be3c253")
    private final XMLInputFactory xmlInputFactory;

    /**
     * @param repositoryRoot the repository root path
     * @param geometry the repository geometry.
     */
    @objid ("980fb6d6-8f92-4840-840d-6a71ca4f8ae3")
    public ExmlFileAccess(File repositoryRoot, IExmlRepositoryGeometry geometry) {
        this.repositoryRoot = Objects.requireNonNull(repositoryRoot);
        this.geometry = Objects.requireNonNull(geometry);
        this.cachedModelPath = new File(this.repositoryRoot, this.geometry.getModelPath());
        this.xmlInputFactory = XMLInputFactory.newInstance();
    }

    /**
     * Create a copy of this geometry with the given directory as repository root.
     * 
     * @param newRoot the repository root path
     * @return a copy of this geometry.
     */
    @objid ("954db597-1413-4879-9a36-83df00865aef")
    public ExmlFileAccess copyIn(File newRoot) {
        return new ExmlFileAccess(newRoot, this.geometry);
    }

    /**
     * Get the file where the given blob should be stored.
     * 
     * @param blobKey a Blob key
     * @return the file where the blob is stored.
     */
    @objid ("8103d012-162c-41c8-807c-fc8f53eb3711")
    public File getBlobFile(String blobKey) {
        return new File(this.repositoryRoot, this.geometry.getBlobPath(blobKey));
    }

    /**
     * Get the blob key stored in the given file.
     * 
     * @param file a blob file
     * @return the blob key.
     */
    @objid ("7d64905e-f956-4c74-bc9b-7d905e68ecfc")
    public String getBlobKey(File file) {
        return this.geometry.getBlobKey(toRelativePath(file));
    }

    /**
     * Get the root directory where blobs are stored.
     * 
     * @return the blobs directory.
     */
    @objid ("b94567dd-c6cf-4fcf-8cac-07f13e242455")
    public File getBlobsDirectory() {
        return new File(this.repositoryRoot, IExmlRepositoryGeometry.BLOBS_DIRNAME);
    }

    /**
     * This method returns the externalized file for the element. Can be null for CMS tools which are not able to
     * produce such a file.
     * 
     * @param element an element.
     * @return the element file.
     */
    @objid ("67ccae28-258c-4abb-b8e4-8f6a4ae703e0")
    public File getExmlFile(final MObject element) {
        return getExmlFile(MRef.withoutName(element));
    }

    /**
     * This method returns the externalized file for the element.
     * 
     * @param ref the element reference.
     * @return the element file.
     */
    @objid ("e55ad399-4923-4cf2-a885-c79123d34534")
    public File getExmlFile(final MRef ref) {
        return new File(this.repositoryRoot, this.geometry.getRelativePath(ref));
    }

    /**
     * @return the repository geometry.
     */
    @objid ("1a9625f7-c119-48b4-884d-be4df13b21b1")
    public IExmlRepositoryGeometry getGeometry() {
        return this.geometry;
    }

    /**
     * Get the directories that should exist on an empty repository.
     * 
     * @param metamodel the metamodel
     * @return the list of directories.
     */
    @objid ("9f31718b-4758-4ecf-bb70-144648a9548e")
    public Collection<File> getInitialDirectories(MMetamodel metamodel) {
        return this.geometry.getInitialDirectories(metamodel)
                                .stream()
                                .map(p -> new File(this.repositoryRoot,p))
                                .collect(Collectors.toList());
    }

    /**
     * This method returns the externalized file for the local part of an element.
     * 
     * @param ref the element reference.
     * @return the element local part file.
     */
    @objid ("9379bcd3-cb9a-49fd-84c7-daad05192676")
    public File getLocalExmlFile(MRef ref) {
        return new File(this.repositoryRoot, this.geometry.getLocalFileRelativePath(ref));
    }

    /**
     * @return the metamodel descriptor file path.
     */
    @objid ("a006dce2-a8ea-481e-a76d-6b38466c9aa6")
    public File getMetamodelDescriptorFile() {
        return new File(this.repositoryRoot, IExmlRepositoryGeometry.MM_DESCRIPTOR_PATH);
    }

    /**
     * Get the 'model' directory containing a sub directory per metaclass.
     * 
     * @return the model directory.
     */
    @objid ("c31bcf87-f23a-44de-acad-a5ff0d09d86b")
    public File getModelDirectory() {
        return this.cachedModelPath;
    }

    /**
     * Get the element reference representing the given file.
     * 
     * @param exmlFile an EXML file
     * @return the represented element reference.
     */
    @objid ("5e6084ce-93df-498a-a288-243776ee199a")
    public MRef getObRef(final File exmlFile) {
        try {
            return readRefFromFile(exmlFile);
        } catch (IOException e) {
            // Guess with errors in the metaclass
            return this.geometry.getObRef(exmlFile.getPath());
        }
    }

    /**
     * @param file a file
     * @return <code>true</code> if the file is a blob file.
     */
    @objid ("a35fb18c-4e2a-42c5-9b3e-bc0f8b7cd519")
    public boolean isBlobFile(File file) {
        return !file.isDirectory() 
                                && this.geometry.isBlobPath(toRelativePath(file));
    }

    /**
     * Tells whether a file is an EXML file that can be versioned.
     * <p>
     * The answer is based on the file extension.
     * Returns <i>false</i> if it is a {@link IExmlRepositoryGeometry#EXT_LOCAL_EXML ".local.exml"} file.
     * 
     * @param file a file
     * @return <i>true</i> if it is an EXML file, else <i>false</i>.
     */
    @objid ("e47353a9-65aa-4ee7-854f-0e1632c06ca2")
    public boolean isModelFile(File file) {
        return !file.isDirectory() 
                                && this.geometry.isModelPath(toRelativePath(file));
    }

    /**
     * Read the element reference from the file.
     * 
     * @param exmlFile the file path
     * @return the element reference
     * @throws java.io.IOException on failure
     */
    @objid ("169e7b58-7215-46a9-8fae-817a402e13b3")
    public MRef readRefFromFile(final File exmlFile) throws IOException {
        try (FileInputStream is = new FileInputStream(exmlFile)){
        
            XMLStreamReader reader = this.xmlInputFactory.createXMLStreamReader(exmlFile.toString(), is);
        
            try {
                while (! (reader.getEventType() == XMLStreamReader.START_ELEMENT 
                        && reader.getLocalName().equals(ExmlTags.TAG_ID))) {
                    reader.next();
                }
                String mc = reader.getAttributeValue(null, ExmlTags.ATT_ID_MC);
                String name = reader.getAttributeValue(null, ExmlTags.ATT_ID_NAME);
                String uuid = reader.getAttributeValue(null, ExmlTags.ATT_ID_UID);
        
                return new MRef(mc, uuid, name);
            } finally {
                try {reader.close();} catch (XMLStreamException e) {Log.warning(e);}
            }
        } catch (XMLStreamException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("9c72fa65-c1b2-492d-8c4b-001695605695")
    private static String getRelativePath(File file, File folder) {
        String filePath = file.getAbsolutePath();
        String folderPath = folder.getAbsolutePath();
        if (filePath.startsWith(folderPath) && folderPath.length() < filePath.length()) {
            return filePath.substring(folderPath.length() + 1);
        } else {
            return null;
        }
    }

    @objid ("6a406bf8-4040-491f-be16-32ea708daf7c")
    private String toRelativePath(File file) {
        return getRelativePath(file, this.repositoryRoot);
    }

}

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

package org.modelio.gproject.data.ramc;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.IFragmentInfos;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Model component information interface.
 */
@objid ("0ff8a947-3918-4cab-85ee-9f968e71d6e2")
public interface IModelComponentInfos extends IFragmentInfos {
    /**
     * The manifest version.
     * <p>
     * History:<ul>
     * <li>2.0 : Modelio 3.5 -
     * <li>2.0.01 : added {@link #getModelioVersion()}
     * <li>2.0.02 : added {@link #getProvider()}
     * </ul>
     */
    @objid ("737a392c-2e25-4bff-882e-ea1b2f516a1a")
    public static final Version MANIFEST_VERSION = new Version(2,0,2);

    /**
     * @return the modules that contributed to the packaging.
     */
    @objid ("c5d6beaa-0300-48ac-9471-0278292097a7")
    List<VersionedItem<?>> getContributingModules();

    /**
     * @return the description.
     */
    @objid ("ce6e13f7-cf57-4725-a842-026328f892a9")
    @Override
    String getDescription();

    /**
     * @return the exported files.
     */
    @objid ("cd373789-5c4f-4a9b-bcb7-ce32681a0e28")
    List<ExportedFile> getExportedFiles();

    /**
     * Get the version of Modelio used to package this RAMC.
     * 
     * @return the Modelio version.
     */
    @objid ("5b2a2002-09a6-4655-9848-f9385ae1f64c")
    @Override
    Version getModelioVersion();

    /**
     * @return this model component name.
     */
    @objid ("90859aa0-b1c7-4ee7-97c0-0d0e229bd90e")
    @Override
    String getName();

    /**
     * @return the required metamodel fragments.
     */
    @objid ("44ef4df7-01d9-49b2-aed3-2aa948b271fb")
    List<VersionedItem<?>> getRequiredMetamodelFragments();

    /**
     * @return the required model components.
     */
    @objid ("5ed23cdf-3cf9-4bcd-9f69-c78137076cee")
    List<VersionedItem<?>> getRequiredModelComponents();

    /**
     * @return the identifiers of the root model elements.
     */
    @objid ("89cfa488-8e74-45f0-9e46-45af21b590ee")
    List<ModelRef> getRoots();

    /**
     * @return this model component version.
     */
    @objid ("550b4736-d101-40b4-8468-c75e0dbf1202")
    @Override
    Version getVersion();

    /**
     * @return this model component provider.
     */
    @objid ("47948167-87c6-4378-a6e6-2ab7918477cf")
    String getProvider();

    /**
     * File exported by the RAMC
     */
    @objid ("4fbf6c11-c9b2-42b7-a1d2-3d04a0617b6b")
    static class ExportedFile {
        /**
         * The file name in the RAMC archive
         * @since 3.6
         */
        @objid ("ad9bd5ad-c084-4997-8f6c-b5ca0d9fa273")
        private final String nameInArchive;

        @objid ("5c4a8b1e-b11b-47b7-97a8-07593f7c72dd")
        private final Path path;

        @objid ("76513372-5060-4333-b0a7-0774baf6c9d2")
        private final FileTime date;

        /**
         * @param nameInArchive the file name in the archive.
         * @param path the deployment target path, relative to the project directory.
         * @param date the date/time to set as the file modification time.
         */
        @objid ("1eb8fd9b-c40b-4494-997d-550c4e8687ce")
        public ExportedFile(final String nameInArchive, final Path path, final FileTime date) {
            this.nameInArchive = nameInArchive;
            this.path = path;
            this.date = date;
        }

        /**
         * Get the destination path.
         * <p>
         * This path is a relative path from the project directory.
         * 
         * @return the destination path.
         */
        @objid ("f298608a-5f1d-4692-aea2-934bdcb40caf")
        public Path getPath() {
            return this.path;
        }

        /**
         * @return the file modification time.
         */
        @objid ("d9b8ea0b-586f-4e34-b078-cd7d081d0ffb")
        public FileTime getDate() {
            return this.date;
        }

        /**
         * @return the file name in the RAMC archive
         * @since 3.6
         */
        @objid ("1cd9d27b-8f74-44d5-8be2-78af56ee4d5d")
        public String getNameInArchive() {
            return this.nameInArchive;
        }

    }

}

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

package org.modelio.gproject.ramc.core.packaging;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This interface represents a contribution to a Model Component (RAMC), usually provided by a module.
 * <p>
 * Each contribution is interrogated when packaging the Model Component, allowing to add specific contents to it.
 * </p>
 * <p>
 * MDA annotations (NoteType, TagType, Stereotype) are static contributions, permitting to ignore parts of a
 * model that are not required in a Model Component.
 * </p>
 */
@objid ("4296eaae-a37b-428e-a5ec-2a23e85b63f1")
public interface IModelComponentContributor {
    /**
     * @return a bunch of {@link MObject} to be added to the Model Component from this contribution.
     */
    @objid ("36983300-24f6-4734-b644-c6cdd12b9502")
    Set<MObject> getElements();

    /**
     * @return a bunch of {@link NoteType} to be added to the Model Component from this contribution.
     */
    @objid ("d2a62245-94ac-42f7-a4b9-2815e89ca86e")
    Set<NoteType> getNoteTypes();

    /**
     * @return a bunch of {@link TagType} to be added to the Model Component from this contribution.
     */
    @objid ("241a9fbe-ec1f-4a64-ac9f-1f82ff4e5200")
    Set<TagType> getTagTypes();

    /**
     * @return a bunch of {@link Stereotype} for {@link Dependency} links to be added to the Model Component from this contribution.
     */
    @objid ("e18c47fe-c354-4428-8345-56fd71e5c1c0")
    Set<Stereotype> getDependencyStereotypes();

    /**
     * @return the files to be added to the Model Component from this contribution.
     */
    @objid ("d0329c46-7491-43ff-a949-8c628520f859")
    Set<ExportedFileEntry> getFiles();

    /**
     * This class is the representation of a physical file to be embedded in a Model Component (RAMC).
     * <p>
     * Note that <i>fileToExport</i> is the place to find the file at packaging, whereas the
     * <i>exportPath</i> is the relative path to copy the file into when deploying the Model Component.
     * </p>
     * Since 3.8, the <i>fileToExport</i> may be a path relative to the project directory.
     */
    @objid ("8b102bf7-bb62-4faa-a047-e44f0d0c134a")
    static class ExportedFileEntry implements Comparable<ExportedFileEntry> {
        @objid ("10ea84ab-79cd-44b5-a86c-75a0505cb935")
        private Path fileToExport;

        @objid ("31729165-c3aa-4d0a-9f1d-86642d9bb51f")
        private String exportPath;

        /**
         * @return the path to export to, relative to the project path.
         */
        @objid ("a3d987a5-638f-4ea6-b99c-08206697bada")
        public String getExportPath() {
            return this.exportPath;
        }

        @objid ("210b2cc9-c434-4139-acc6-e4c59cad155e")
        @Override
        public int compareTo(ExportedFileEntry e) {
            int ret = this.fileToExport.compareTo(e.fileToExport);
            return (ret != 0) ? ret : this.exportPath.compareTo(e.exportPath);
        }

        /**
         * Set the export path.
         * <p>
         * The export path must be relative to the project path.
         * 
         * @param exportPath the path to deploy the file into. This path must a relative path.
         */
        @objid ("54f5401f-9e9e-4734-a1bb-8c66d78f87d2")
        public void setExportPath(String exportPath) {
            this.exportPath = exportPath;
        }

        @objid ("6f5bfff6-1f67-4fa9-949e-8ddf64c2216d")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.exportPath == null) ? 0 : this.exportPath.hashCode());
            result = prime * result + ((this.fileToExport == null) ? 0 : this.fileToExport.hashCode());
            return result;
        }

        @objid ("d7c23751-a90f-4a14-a686-bf076803744d")
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ExportedFileEntry other = (ExportedFileEntry) obj;
            if (this.exportPath == null) {
                if (other.exportPath != null) {
                    return false;
                }
            } else if (!this.exportPath.equals(other.exportPath)) {
                return false;
            }
            if (this.fileToExport == null) {
                if (other.fileToExport != null) {
                    return false;
                }
            } else if (!this.fileToExport.equals(other.fileToExport)) {
                return false;
            }
            return true;
        }

        /**
         * Adds a file to the set of files path that must be added to a model component.
         * <p>
         * The export path must be relative to the project path.
         * If the provided path does not match a file in the project, it will be ignored.
         * </p>
         * 
         * @param fileToExport the file to package in the model component.
         * @param exportPath the path to deploy the file into. This path must a relative path.
         * @since 3.6
         */
        @objid ("deac7d4e-775c-460f-97cb-fe94b7240989")
        public ExportedFileEntry(Path fileToExport, String exportPath) {
            this.fileToExport = Objects.requireNonNull(fileToExport);
            this.exportPath = (exportPath != null) ? exportPath : "";
        }

        /**
         * @return the path where the file to export currently reside.
         * @since 3.6
         */
        @objid ("1af261a2-82c4-44ed-b212-311f0788544f")
        public Path getFileToExport() {
            return this.fileToExport;
        }

        /**
         * Set the file to export.
         * 
         * @param fileToExport the file to export.
         */
        @objid ("e30d3d15-41aa-4a9c-9623-25b4da1ddd37")
        public void setFileToExport(Path fileToExport) {
            this.fileToExport = fileToExport;
        }

    }

}

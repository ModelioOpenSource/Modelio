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

package org.modelio.gproject.ramc.core.packaging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor.ExportedFileEntry;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.SubProgress;

@objid ("ae8240c1-608b-44f5-a471-920e2e982dfe")
class FilesExporter {
    @objid ("3b012391-28d9-4607-9374-b067b5f08b91")
    private final Path exportPath;

    @objid ("29fe3305-f5fe-4c05-9bec-ce70611fa34b")
    private final Path projectPath;

    @objid ("e18a5475-3b21-4cab-9233-81cffb04addb")
    public FilesExporter(Path exportPath, Path projectPath) {
        this.exportPath = exportPath;
        this.projectPath = projectPath;
    }

    /**
     * Copy exported files in the export area and record them in the {@link Metadatas}.
     * @param filesToExport the files to export
     * @param metadatas the metadatas to update
     * @param subMonitor a progress monitor
     * @throws java.io.IOException on I/O failure
     */
    @objid ("8f1ef777-c37c-4d27-911d-f9b4b801b080")
    public void run(List<ExportedFileEntry> filesToExport, Metadatas metadatas, SubProgress subMonitor) throws IOException {
        subMonitor.subTask(CoreProject.I18N.getString("RamcPackager.ExportFiles"));
        
        int idx = 0;
        for (ExportedFileEntry fileEntry : filesToExport) {
            String metaName = "File" + idx;
            if (copyFile(metadatas, fileEntry, metaName)) {
                idx++;
            }
        }
    }

    @objid ("a14ef114-16d2-4cbe-afa8-cf133ec7ce1b")
    private boolean copyFile(Metadatas metadatas, ExportedFileEntry fileEntry, String metaName) throws IOException {
        Path file = this.projectPath.resolve(fileEntry.getFileToExport());
        
        if (Files.isRegularFile(file)) {
            FileTime date = Files.getLastModifiedTime(file);
            Files.copy(file, this.exportPath.resolve(metaName));
            metadatas.addExportedFileDef(metaName, fileEntry.getExportPath(), date);
            return true;
        } else {
            Log.warning("%s - Invalid file path: %s", getClass().getSimpleName(), file.normalize());
            return false;
        }
    }

}

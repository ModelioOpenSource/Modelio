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
package org.modelio.gproject.migration;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.vbasic.files.FileUtils;

/**
 * Put flags on fragments persisted by .flag files in the runtime directory.
 * @author cmarin
 * @since 3.4
 */
@objid ("84f4f27b-e64b-4772-86c4-cc05995b92e2")
public class FileFlags {
    @objid ("ad823a3b-50fd-4d06-89af-734758b8f9b6")
    private IGModelFragment fragment;

    @objid ("91c3da8e-93ac-4dbf-8ddf-17fd680f7c6d")
    public  FileFlags(IGModelFragment fragment) {
        super();
        this.fragment = fragment;
        
    }

    @objid ("444666e1-25a8-41c1-b079-247db5c9bf34")
    public void removeMigrationFlag(String flag) throws IOException {
        Path f = this.fragment.getRuntimeDirectory().resolve(flag+".flag");
        Files.deleteIfExists(f);
        
    }

    @objid ("4ed3ff2a-60d5-4f43-8dd3-8703c940318d")
    public boolean isMigrationFlag(String flag) {
        Path f = this.fragment.getRuntimeDirectory().resolve(flag+".flag");
        return Files.isRegularFile(f);
    }

    @objid ("99f34dd6-4ed5-4680-8134-a798c378c864")
    public String getMigrationFlag(String flag) throws FileSystemException, IOException {
        Path f = this.fragment.getRuntimeDirectory().resolve(flag+".flag");
        if (Files.isRegularFile(f)) {
            return FileUtils.readWhole(f, StandardCharsets.UTF_8.name());
        } else {
            return null;
        }
        
    }

    @objid ("694de16e-5289-47fa-9dc0-a3dbb1fba4e3")
    public void putMigrationFlag(String flag, String content) throws IOException {
        Path f = this.fragment.getRuntimeDirectory().resolve(flag+".flag");
        try (BufferedWriter w = Files.newBufferedWriter(f, StandardCharsets.UTF_8)) {
            if (content != null) {
                w.write(content);
            }
        }
        
    }

}

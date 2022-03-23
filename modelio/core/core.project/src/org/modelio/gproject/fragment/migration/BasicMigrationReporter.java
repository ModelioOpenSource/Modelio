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
package org.modelio.gproject.fragment.migration;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;

/**
 * Basic IMigrationReporter implementation that writes log to a file and store result in a string.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("d91e1877-ed87-46e8-b81d-9ac739a3034f")
public class BasicMigrationReporter implements IMigrationReporter, Closeable {
    @objid ("cc236137-fbf8-4d08-a7ef-e35a65f01ec6")
    private PrintWriter logger;

    @objid ("e90d6c9e-281f-47fc-a099-8339a887a0f0")
    private PrintWriter resultWriter;

    @objid ("e4e4a021-1e54-4764-bca7-b8ebb16453c1")
    private StringWriter result;

    @objid ("45a8bfbe-7f8c-42fa-8970-7b5f77df923f")
    private BufferedWriter fileWriter;

    /**
     * @param reportFile the log file path
     * @throws IOException on failure opening the log file.
     */
    @objid ("39d1ba8d-7e4f-4b85-824b-6dcad22de91e")
    public  BasicMigrationReporter(Path reportFile) throws IOException {
        this.result = new StringWriter();
        this.resultWriter = new PrintWriter(this.result);
        
        Files.createDirectories(reportFile.getParent());
        this.fileWriter = Files.newBufferedWriter(reportFile);
        this.logger = new PrintWriter(this.fileWriter);
        
        this.resultWriter.println(CoreProject.I18N.getMessage("BasicMigrationReporter.logFileWritten", reportFile));
        
    }

    @objid ("c9ea383a-4e55-4ed9-ac91-c6b3d63e3499")
    @Override
    public PrintWriter getLogger() {
        return this.logger;
    }

    @objid ("54bdfabb-1bc7-4824-9861-8f38f01657a4")
    @Override
    public PrintWriter getResultReporter() {
        return this.resultWriter;
    }

    /**
     * @return the content stored to {@link #getResultReporter()}
     */
    @objid ("c7dac7ef-bb3a-48e1-95ba-138d37b8ecae")
    public String getResult() {
        return this.result.toString();
    }

    @objid ("b62cec73-13d5-4ee0-a392-922c6bd5aaeb")
    @Override
    public void close() throws IOException {
        if (this.resultWriter != null) {
            this.resultWriter.close();
            this.resultWriter = null;
        }
        
        if (this.fileWriter != null) {
            this.fileWriter.close();
            this.fileWriter = null;
        }
        
    }

}

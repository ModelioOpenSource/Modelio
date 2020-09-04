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

package org.modelio.editors.log;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.api.modelio.editor.EditorType;
import org.modelio.editors.service.EditionManager;
import org.modelio.utils.log.writers.PluginLogger;

/**
 * <p>Handler for the &quot;Show Log&quot; command. Opens a read only text editor on the modelio.log file in {user.home}/.modelio/3.2.</p>
 */
@objid ("a29e2c00-4307-4bb9-86f2-523e5d0eb9c1")
public class ShowLogHandler {
    @objid ("2592655e-1e15-48c0-bdf1-b74f4b841e7b")
    @Execute
    public void execute() {
        File logFile = new File(PluginLogger.getLogFile());
        EditionManager.services().openEditor(null, logFile, EditorType.TXTEditor, true, EditionManager.DEFAULT_CHARSET_NAME, "Modelio log", null);
    }

}

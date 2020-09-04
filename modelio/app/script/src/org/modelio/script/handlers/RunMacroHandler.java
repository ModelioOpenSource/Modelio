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

package org.modelio.script.handlers;

import java.nio.file.Paths;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.modelio.script.engine.core.engine.IScriptRunner;
import org.modelio.script.view.ScriptView;
import org.modelio.script.view.ScriptViewSelectionGetter;

/**
 * Internal use handler, used to launch toolbar/popup macros defined in the catalog.
 */
@objid ("51959ddb-a046-46a4-b928-6213b57ac1d8")
public class RunMacroHandler {
    @objid ("acef53a2-d141-4445-8508-47201e04d986")
    public static final String RUNMACRO_FILE = "org.modelio.script.command.runmacro.file";

    @objid ("27b79cac-7ef2-4a9f-baa4-7bf8fd551a0d")
    public static final String RUNMACRO = "org.modelio.script.command.runmacro";

    @objid ("98ec7b4f-cfc5-4e35-9daa-05fc96520fbb")
    @Execute
    public void execute(EPartService partService, MWindow window, @Optional @Named(RUNMACRO_FILE) final String file) {
        MPart part = partService.findPart(ScriptView.PARTID);
        //If the part is not found try to browse the shared elements to find the script part
        if (part == null && window != null) {
            for (MUIElement x : window.getSharedElements()) {
                if (x.getElementId().equals(ScriptView.PARTID)) {
                    part  = (MPart) x;
                    break;
                }
            }
        }
        if (part !=null) {
            if (part.getContext() == null) {
                // Create script view if it is not created yet in order to run the script
                if (!partService.isPartVisible(part)) {
                    partService.showPart(part, PartState.CREATE);
                }
            }
            else {
                //Force the activation of the script view
                partService.showPart(part, PartState.ACTIVATE);
            }
        
            // Activate the part to give it focus
            partService.activate(part);
        
            ScriptView scriptView = (ScriptView) part.getObject();
        
            final IScriptRunner scriptRunner = scriptView.getScriptRunner();
        
            scriptView.getOutputWriter();
            ScriptViewSelectionGetter selectionGetter = scriptView.getSelectionGetter();
            scriptRunner.runFile(Paths.get(file), selectionGetter.getSelection(), selectionGetter.getSelectedElements());
        }
    }

    @objid ("69b79019-f73d-45ac-8d85-fed5b5cbd2ad")
    @CanExecute
    public boolean isEnable() {
        return true;
    }

}

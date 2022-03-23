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
package org.modelio.script.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import javax.script.ScriptException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.modelio.platform.script.engine.core.engine.IScriptRunner;
import org.modelio.script.plugin.Script;
import org.modelio.script.view.ScriptView;
import org.modelio.script.view.ScriptViewSelectionGetter;

@objid ("004aa7d6-6505-105c-84ef-001ec947cd2a")
public class EvalScriptHandler {
    @objid ("004ac720-6505-105c-84ef-001ec947cd2a")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
        final ScriptView scriptView = (ScriptView) part.getObject();
        
        Boolean debugMode = scriptView.getOptions().getDebugMode();
        
        final String s = scriptView.getInputViewContent(debugMode);
        
        final IScriptRunner scriptRunner = scriptView.getScriptRunner();
        
        if (scriptRunner != null) {
            ScriptViewSelectionGetter selectionGetter = scriptView.getSelectionGetter();
            try {
                scriptRunner.runScript(s, selectionGetter.getSelection(), selectionGetter.getSelectedElements());
            } catch (ScriptException e) {
                Script.LOG.debug(e);
            }
        }
        
    }

    @objid ("004b03b6-6505-105c-84ef-001ec947cd2a")
    @CanExecute
    public boolean canExecute() {
        return true;
    }

}

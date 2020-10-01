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

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectToolItem;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.script.view.ScriptView;

@objid ("008b0b3c-663d-105c-84ef-001ec947cd2a")
public class DebugModeHandler {
    @objid ("008b3670-663d-105c-84ef-001ec947cd2a")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part, EModelService s) {
        MUIElement button = s.find("org.modelio.script.toolbar.item.debug", part.getToolbar());
        
        if (button != null) {
            ScriptView scriptView = (ScriptView) part.getObject();
            scriptView.getOptions().setDebugMode(((MDirectToolItem) button).isSelected());
        }
    }

}

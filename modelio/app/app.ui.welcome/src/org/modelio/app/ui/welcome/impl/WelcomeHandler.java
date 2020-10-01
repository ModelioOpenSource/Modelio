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

package org.modelio.app.ui.welcome.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.modelio.platform.rcp.uiservice.IModelioUiService;

/**
 * Handler opening the welcome part.
 */
@objid ("377404ed-33e4-4678-ab58-7f3eecf6bc7d")
public class WelcomeHandler {
    @objid ("4dc3dd4b-1163-471f-9d39-ba868013d567")
    @Execute
    public void execute(final IModelioUiService pm, final EPartService partService) {
        pm.showWelcome(true);
        
        final MPart part = partService.findPart(WelcomeView.PARTID);
        if (part == null) {
            partService.showPart(WelcomeView.PARTID, PartState.ACTIVATE);
        } else {
            partService.showPart(part, PartState.ACTIVATE);
        }
    }

}

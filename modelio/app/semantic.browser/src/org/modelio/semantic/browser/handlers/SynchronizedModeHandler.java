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

package org.modelio.semantic.browser.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledToolItem;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.semantic.browser.SemanticBrowserView;

@objid ("a2018945-6820-49c6-a11b-ae4fec5378a8")
public class SynchronizedModeHandler {
    @objid ("7c09b938-311b-428a-b013-0be9f8b8b44e")
    @Execute
    public void execute(MPart part, MHandledToolItem item) {
        SemanticBrowserView view = (SemanticBrowserView)part.getObject();
        view.getSemanticBrowser().setSynchronizedSelectionMode(item.isSelected());
    }

    @objid ("50450bb2-a340-40ef-a1f0-bc36e248ab0b")
    @CanExecute
    public boolean canExecute(IProjectService projectService) {
        return projectService.getOpenedProject() != null;
    }

}

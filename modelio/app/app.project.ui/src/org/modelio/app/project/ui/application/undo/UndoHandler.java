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

package org.modelio.app.project.ui.application.undo;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Undo last transaction handler.
 */
@objid ("d26fbb25-3259-11e2-ad6b-002564c97630")
public class UndoHandler {
    @objid ("d3ffa225-3259-11e2-ad6b-002564c97630")
    @Execute
    public void execute(final IProjectService projectService, StatusReporter statusReporter) {
        AppProjectUi.LOG.info("Undo transaction");
        try {
            ICoreSession session = projectService.getSession();
            session.getTransactionSupport().undo();
        } catch (RuntimeException e) {
            AppProjectUi.LOG.error(e);
            
            statusReporter.show(StatusReporter.ERROR, AppProjectUi.I18N.getMessage("UndoHandler.Failed"), e);
        }
    }

    @objid ("d40464e8-3259-11e2-ad6b-002564c97630")
    @CanExecute
    public boolean canExecute(final IProjectService projectService) {
        ICoreSession session = projectService.getSession();
        return session != null && session.getTransactionSupport().hasUndo();
    }

}

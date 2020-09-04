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

package org.modelio.app.ui.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuItem;
import org.eclipse.ui.help.IWorkbenchHelpSystem;

/**
 * @author phv
 */
@objid ("a4c20b23-76bf-4876-ba88-7c6f20e0646b")
public class HelpHandler {
    @objid ("d68514f5-196c-4653-8bf5-a6376a6d566a")
    @Execute
    public void execute(IWorkbenchHelpSystem helpService, MMenuItem item, @Optional @Named("org.modelio.app.ui.command.help.topicid") String topic) {
        if (topic == null) {
            helpService.displayHelp();
        } else {
            helpService.displayHelpResource(topic);
        }
    }

    @objid ("69ec4baf-272d-41c2-b9f6-351803fff691")
    @CanExecute
    boolean canExecute() {
        return true;
    }

}

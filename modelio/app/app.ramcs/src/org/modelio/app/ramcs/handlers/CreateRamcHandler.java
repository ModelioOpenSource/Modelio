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

package org.modelio.app.ramcs.handlers;

import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.ramcs.creation.RamcCreator;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Package;

/**
 * Command handler that creates a model component (RAMC).
 */
@objid ("89e51034-745b-41e7-8da7-417748b8e9d5")
public class CreateRamcHandler {
    @objid ("bb168517-d91d-4666-b96a-37e4a57b6a50")
    @Inject
     ECommandService commandService;

    @objid ("86e5b425-309e-41cf-bc67-42a1af768b98")
    @Inject
     EHandlerService handlerService;

    @objid ("440fe48b-b04a-48e4-9120-8d8d608b90d7")
    @Inject
     IModelioNavigationService navigationService;

    @objid ("64bbbb08-d645-43dd-be50-2298aabe1c7b")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (selection.size() == 1 && selection.getFirstElement() instanceof Package) {
            Package rootPackage = (Package) selection.getFirstElement();
            Artifact artifact = RamcCreator.create(rootPackage);
        
            if (artifact != null) {
                this.navigationService.fireNavigate(artifact);
                final ParameterizedCommand cmd = CreateRamcHandler.this.commandService.createCommand("app.ramcs.command.packageramc", null);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    // Ignore exception
                }
                Display.getCurrent().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        CreateRamcHandler.this.handlerService.executeHandler(cmd);
                    }
        
                });
        
            }
        }
    }

    @objid ("86b64d81-b2d1-413f-bcfe-fadd767c5d6c")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (selection != null && selection.size() == 1 && selection.getFirstElement() instanceof Package) {
            return ((Package) selection.getFirstElement()).isModifiable();
        } else {
            return false;
        }
    }

}

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
package org.modelio.platform.module.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("19384840-120f-11e2-8b3b-001ec947c8cc")
public class ExecuteModuleActionHandler {
    @objid ("19384841-120f-11e2-8b3b-001ec947c8cc")
    private IModuleAction actionToExecute;

    @objid ("737ea389-120f-11e2-8b3b-001ec947c8cc")
    private IRTModule module;

    @objid ("19384844-120f-11e2-8b3b-001ec947c8cc")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final ISelection selection) {
        List<MObject> selectedElements = ExecuteModuleActionHandler.convertSelection(selection);
        this.actionToExecute.getHandler().actionPerformed(selectedElements, this.module.getIModule());
        
    }

    @objid ("19384845-120f-11e2-8b3b-001ec947c8cc")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final ISelection selection) {
        List<MObject> selectedElements = ExecuteModuleActionHandler.convertSelection(selection);
        return this.actionToExecute.isActiveFor(selectedElements.toArray(new MObject[selectedElements.size()]), false);
    }

    @objid ("19384846-120f-11e2-8b3b-001ec947c8cc")
    private static List<MObject> convertSelection(final ISelection selection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            for (Object o : structuredSelection.toList()) {
                if (o instanceof MObject) {
                    selectedElements.add((MObject) o);
                } else if (o instanceof IAdaptable) {
                    MObject adaptedElement = ((IAdaptable) o).getAdapter(MObject.class);
                    if (adaptedElement != null) {
                        selectedElements.add(adaptedElement);
                    }
                } else {
                    return Collections.emptyList();
                }
            }
        }
        return selectedElements;
    }

    @objid ("737ea38a-120f-11e2-8b3b-001ec947c8cc")
    public  ExecuteModuleActionHandler(IRTModule module, IModuleAction actionToExecute) {
        this.module = module;
        this.actionToExecute = actionToExecute;
        
    }

    @objid ("4b84ffab-13bb-11e2-825e-001ec947c8cc")
    @IsVisible
    public boolean isvisible(@Named(IServiceConstants.ACTIVE_SELECTION) final ISelection selection) {
        List<MObject> selectedElements = ExecuteModuleActionHandler.convertSelection(selection);
        return this.actionToExecute.accept(selectedElements.toArray(new MObject[selectedElements.size()]));
    }

}

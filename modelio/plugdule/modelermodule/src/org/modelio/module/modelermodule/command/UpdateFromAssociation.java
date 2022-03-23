/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.module.modelermodule.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.LinkUpdater;
import org.modelio.module.modelermodule.gui.ShellHelper;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Update a link from its base association.
 */
@objid ("9fa0a578-8380-48aa-852b-3860dfb9d308")
public class UpdateFromAssociation extends DefaultModuleCommandHandler {
    @objid ("c3c7933f-c5b0-46d2-8dc5-dbc780282584")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
        try (ITransaction transaction = session.createTransaction("UpdateFromAssociation")) {
            LinkUpdater lu = new LinkUpdater();
        
            for (MObject elt : selectedElements) {
                if (elt instanceof Link) {
                    lu.updateLinkFromAssociation((Link) elt);
                } else if (elt instanceof LinkEnd) {
                    lu.updateLinkFromAssociation(((LinkEnd) elt).getLink());
                }
            }
        
            transaction.commit();
        } catch (ModelerModuleException e) {
            MessageDialog.openError (ShellHelper.findActiveShell(),
                    I18nMessageService.getString ("Ui.Error.Title"),
                    e.getMessage());
        }
        
    }

    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("10cca098-4d3b-4b59-9415-8ad6af4d2bf9")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        
        for (MObject elt : selectedElements) {
            if (elt instanceof Link) {
                if (((Link) elt).getModel() == null) {
                    continue;
                }
            } else if (elt instanceof LinkEnd) {
                if (((LinkEnd) elt).getLink().getModel() != null) {
                    continue;
                }
            }
            return false;
        }
        return !selectedElements.isEmpty();
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("ac58ac0a-e790-44a0-8c31-56e46aceb6eb")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}

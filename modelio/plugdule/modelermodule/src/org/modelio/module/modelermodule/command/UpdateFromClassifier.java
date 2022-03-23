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
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.InstanceUpdater;
import org.modelio.module.modelermodule.gui.ShellHelper;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Update a part contents from its base classifier.
 * Allows creation of a new classifier if no base exists, or referencing an existing classifier.
 */
@objid ("1e64bbe3-3d9d-4fdb-9842-78964ef80fc7")
public class UpdateFromClassifier extends DefaultModuleCommandHandler {
    @objid ("0321dd25-76da-4004-a692-c11b09db6275")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("UpdateFromClassifier")) {
            InstanceUpdater p = new InstanceUpdater();
            for (MObject selectedElement : selectedElements) {
            p.updatePartFromInstanciedClassifier(session, (Instance) selectedElement);
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
    @objid ("4725cae5-8ade-4f71-9e10-7601a8563923")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        
        for (MObject elt : selectedElements) {
            if (elt instanceof Port || !(elt instanceof Instance)) {
                return false;
            }
        }
        return !selectedElements.isEmpty();
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("361bc03a-c661-4484-8c97-5d9c3bcf96a0")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}

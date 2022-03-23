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
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.InstanceUpdater;
import org.modelio.module.modelermodule.gui.ShellHelper;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create a classifier from a lifeline.
 * Includes creation of:
 * - an instance represented by the lifeline, if necessary.
 * - ports from the instance ports.
 * - attributes from attribute links.
 * - operations from incoming messages.
 */
@objid ("4f1956ab-cc77-4c3a-b459-83cf531311d8")
public class CreateClassifierByLifeline extends DefaultModuleCommandHandler {
    @objid ("73c6c051-67a4-4eb0-a6f1-201faecefaa8")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("CreateClassifierByLifeline")) {
            Lifeline ll = (Lifeline) selectedElements.get(0);
        
            InstanceUpdater p = new InstanceUpdater();
            Instance inst = ll.getRepresented();
        
            if (inst != null) {
                p.createClassifier(session, inst);
            } else {
                p.createInstanceAndClassifier(session, ll);
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
    @objid ("94c26695-7686-4ae4-aa8a-c068350cf5c4")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        return selectedElements.size() == 1;
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("5bec75f2-ada6-4a7d-8b50-d2cdcab18e2e")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}

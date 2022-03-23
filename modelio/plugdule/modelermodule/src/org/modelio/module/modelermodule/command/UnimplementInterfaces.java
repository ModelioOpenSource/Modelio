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
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.engine.InterfaceImplementer;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Delete Operations in Classifiers from those defined in their implemented Interfaces.
 */
@objid ("f7178c8d-7ee3-4b7e-9d5d-d6cd38150d3c")
public class UnimplementInterfaces extends DefaultModuleCommandHandler {
    @objid ("df36338f-64e6-4bd8-b5da-c3c0c31d92d2")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        InterfaceImplementer interfaceManager = new InterfaceImplementer();
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("Unimplement Interfaces in class")) {
            boolean hasDoneWork = false;
            for (MObject theElement : selectedElements) {
                Classifier theClassifier = (Classifier) theElement;
        
                boolean newResult = interfaceManager.unImplementInterfaces(theClassifier);
                hasDoneWork = hasDoneWork || newResult;
            }
        
            if (hasDoneWork) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
        
    }

    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("82dfe460-3aa8-49b3-b597-8621681541ba")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        
        // The method is only active if the class implements at least one interface
        for (MObject selectedElement : selectedElements) {
            Classifier current = (Classifier) selectedElement;
            if (current.getRealized().isEmpty()) {
                return false;
            }
        }
        return !selectedElements.isEmpty();
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivated the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("809a79af-70da-4968-8b90-1d78e93af464")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}

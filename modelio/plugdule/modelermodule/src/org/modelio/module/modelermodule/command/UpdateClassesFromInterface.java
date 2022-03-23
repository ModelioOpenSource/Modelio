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
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.module.modelermodule.engine.InterfaceImplementer;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * For all classifiers implementing this interface, synchronize all operation signatures.
 * Missing operations are created.
 */
@objid ("ce0d3086-0745-40bf-a9ed-fad3e056fea3")
public class UpdateClassesFromInterface extends DefaultModuleCommandHandler {
    @objid ("ff96e0e8-aabc-4df3-ac22-3b197a351c93")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        InterfaceImplementer interfaceManager = new InterfaceImplementer();
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("Update classes from interface")) {
            boolean hasDoneWork = false;
            for (MObject theElement : selectedElements) {
                Interface theInterface = (Interface) theElement;
        
                boolean newResult = interfaceManager.updateImplementingClassifiers(session, theInterface);
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
    @objid ("9abeff98-eb61-4d05-bc49-750c7a02540f")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        for (MObject theElement : selectedElements) {
            Interface theInterface = (Interface) theElement;
            if (theInterface.getImplementedLink().size() == 0) {
                return false;
            }
        }
        return (selectedElements.size() != 0);
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("37e7326e-99ac-45df-814e-2365441a09ee")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}

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
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create an attribute from an attribute link. If the class doesn't exists, it is also created.
 */
@objid ("f39aa9df-f759-416c-8c50-8288f1b8c192")
public class OpenDocument extends DefaultModuleCommandHandler {
    @objid ("14d78961-098a-413d-aa5c-271856d87850")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        for (MObject selectedElement : selectedElements) {
            ModelerModuleModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor((Artifact) selectedElement);
        }
    }

    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("ac3ec2de-327c-4064-b59b-55d65baf3dc1")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        if (selectedElements.isEmpty()) {
            return false;
        }
        
        for (MObject selectedElement : selectedElements) {
            Artifact artifact = (Artifact) selectedElement;
            if (!artifact.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.DIRECTORY) &&
                    !artifact.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.FILE) &&
                    !artifact.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.MAIL) &&
                    !artifact.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.URL)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("d71db0bb-b73a-4071-a746-462d3e672f9a")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}

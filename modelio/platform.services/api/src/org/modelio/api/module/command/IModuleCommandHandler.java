/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.api.module.command;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.IModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Base interface of all the module contextual commands.
 * <p><p>
 * The module contextual commands are displayed in the contextual menu and
 * in the specific toolbar of each module property page.
 * <p><p>
 * The developer may inherit the {@link DefaultModuleCommandHandler} class which
 * contains a default standard contextual command implementation.
 */
@objid ("bdae235d-851c-439c-b3f3-c2e9221eeadc")
public interface IModuleCommandHandler {
    /**
     * Method invoked when the command is launched.
     * <p>
     * <p>
     * 
     * This command is launched by Modelio when the user clicks on the contextual menu command. The selected elements that
     * corresponds to the current selection are given as parameter.
     * <p>
     * <p>
     * 
     * The module that is passed as parameter corresponds to the owner of the command.
     * 
     * @param selectedElements Current selection
     * @param module Owner of the command
     */
    @objid ("01e40268-0000-081e-0000-000000000000")
    void actionPerformed(List<MObject> selectedElements, IModule module);

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * <p>
     * <p>
     * 
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched. If the
     * module developer wants to refine the command activation depending on specific markers that can be defined on the selection
     * (tagged value, specific properties...), it can be implemented in the <code>accept</code> method.
     * <p>
     * <p>
     * 
     * This method is invoked only if all the default filters are passed (if the kind of metaclass is valid)
     * 
     * @param selectedElements Current selection
     * @param module Owner of the command
     * @return true if the command is valid for all the elements, false otherwise.
     */
    @objid ("01e40268-0000-0824-0000-000000000000")
    boolean accept(List<MObject> selectedElements, IModule module);

    /**
     * This method precizes if a command has to be desactivated.
     * <p>
     * <p>
     * 
     * If the command has to be displayed (which means that the <code>accept</code> method has returned a positive value, it is
     * sometimes needed to deactivate the command depending on specific constraints that are specific to the module.
     * <p>
     * <p>
     * 
     * This method is invoked only if all the default filters are passed (depending on the read only status of the tool and the
     * objects)
     * 
     * @param selectedElements Current selection
     * @param module Owner of the command
     * @return false if the command has to be grayed, false otherwise.
     */
    @objid ("01e40268-0000-082b-0000-000000000000")
    boolean isActiveFor(List<MObject> selectedElements, IModule module);

    /**
     * This method is called right after the constructor to initialize scopes and parameters for the handler.
     */
    @objid ("48c890c0-ec0a-4714-ac31-e614033ca209")
    void initialize(List<ElementScope> scopes, Map<String, String> hParameters);

    @objid ("ea28d435-5480-40ce-9698-16cc2e053065")
    Map<String, String> getParameters();

    @objid ("8e36ad2d-b24a-42c6-ae75-679adb818adf")
    List<ElementScope> getScopes();

}

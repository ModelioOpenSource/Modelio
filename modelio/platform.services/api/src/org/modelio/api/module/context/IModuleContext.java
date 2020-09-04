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

package org.modelio.api.module.context;

import javax.script.ScriptEngine;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.IModelioContext;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.context.i18n.I18nSupport;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.module.context.project.IProjectStructure;
import org.modelio.metamodel.mda.ModuleComponent;

/**
 * Module context is the access point to Modelio services for a module. It is
 * built and initialized by Modelio for each module. It is passed to module at
 * construction time and cannot be modified.
 * 
 * @since 3.5
 */
@objid ("1c5b0293-ca0a-46bd-8f4f-50f28749bcb0")
public interface IModuleContext {
    /**
     * Get the "user" configuration associated to this module, meant to be accessed by the module itself.
     * Module configuration provide access to the module parameters and resource paths.
     * @see IModuleUserConfiguration
     * 
     * @return the module configuration.
     */
    @objid ("f08e7922-c0f9-4f8c-96d6-420b53378970")
    IModuleUserConfiguration getConfiguration();

    /**
     * Get the Jython scripting engine configured for having access to all the
     * module classes and the public classes of all required modules.
     * The following variables are already bound:
     * <ul>
     * <li>SESSION : the MDA modeling session</li>
     * <li>{@link IModule} MODULE : this module</li>
     * <li>{@link ClassLoader} CLASSLOADER : the class loader of the module</li>
     * </ul>
     * @see <a href="http://www.jython.org" > The Jython project homepage</a>
     * 
     * @return The Jython scripting engine.
     */
    @objid ("96ff4204-96a1-4883-b9bd-811be4787358")
    ScriptEngine getJythonEngine();

    /**
     * Returns the {@link ModuleComponent} model associated with this module.
     * 
     * @return the {@link ModuleComponent} model associated with this module.
     */
    @objid ("dc74eb7b-23c8-4090-82fa-6ce0618827f9")
    ModuleComponent getModel();

    /**
     * Get the current modelinSession, ie the modeling session bound to the
     * project currently opened in Modelio
     * 
     * @return the {@link IModelingSession} session bound to the project
     * currently opened in Modelio
     */
    @objid ("ba8a59ca-6b5e-4791-8273-6df1db5dd011")
    IModelingSession getModelingSession();

    /**
     * Get information about the Modelio application itself
     * 
     * @return a {@link IModelioContext} instance.
     */
    @objid ("16fec824-6ed1-4a5c-bf91-5d45100a0153")
    IModelioContext getModelioContext();

    /**
     * Get the application level services provided by Modelio for module
     * development.
     * 
     * @return a {@link IModelioServices} instance.
     */
    @objid ("bfdbf529-9fd8-4556-91bd-22446f7adf9a")
    IModelioServices getModelioServices();

    /**
     * Get the structure of the currently opened project.
     * 
     * @return a {@link IProjectStructure} instance.
     */
    @objid ("17e19385-059e-4556-8467-d36780bb2065")
    IProjectStructure getProjectStructure();

    /**
     * Get the i18n service for a module.
     * 
     * @return the i18n service.
     */
    @objid ("5371a976-1110-43b6-83b4-b33efbb5fb3a")
    I18nSupport getI18nSupport();

    /**
     * Get the log service for a module.
     * 
     * @return the log service.
     */
    @objid ("2d09dd05-9681-4d53-b9c2-a0cf84c27ba3")
    ILogService getLogService();

    /**
     * Get the "api" configuration associated to this module, meant to be accessed by other modules and scripts.
     * Module configuration provide access to the module parameters and resource
     * paths
     * @see IModuleAPIConfiguration
     * 
     * @return the module configuration.
     */
    @objid ("715f2571-2f43-401c-845a-583f5b5eea2b")
    IModuleAPIConfiguration getPeerConfiguration();

    /**
     * Initialize the module instance this context was built for.
     * 
     * @param iModule a module.
     */
    @objid ("eb320d46-f17f-4786-9356-23b819aa233a")
    void setModule(IModule iModule);

}

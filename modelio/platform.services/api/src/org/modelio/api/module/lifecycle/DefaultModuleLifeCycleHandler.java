/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.api.module.lifecycle;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.vbasic.version.Version;

/**
 * Default implementation of the {@link IModuleLifeCycleHandler} interface.
 * <p>
 * <p>
 * This default implementation may be inherited by the module developers in
 * order to simplify the code writing of the mdac session.
 * 
 * @since 3.5
 */
@objid ("99a637ee-2eed-42d0-a26f-3698072278ac")
public class DefaultModuleLifeCycleHandler implements IModuleLifeCycleHandler {
    @objid ("4bcd308a-833f-4fd8-b88d-118479db450c")
    protected IModule module;

    /**
     * Accepts the installation by default.
     * @param modelioPath the path of modelio application
     * @param installPath the path where the module archive contents have been expanded.
     * @return true if the module accepts to be installed false otherwise.
     * @throws org.modelio.api.module.lifecycle.ModuleException when the installation failed.
     */
    @objid ("7c488de4-5402-4775-b1e0-1f241bb80dc8")
    public static boolean install(String modelioPath, String installPath) throws ModuleException {
        return true;
    }

    @objid ("277c89af-89c6-40e9-bd4a-8fec3926be6c")
    @Override
    public boolean select() throws ModuleException {
        return true;
    }

    @objid ("ce03951e-7ce4-484f-857e-335f7b68938e")
    @Override
    public boolean start() throws ModuleException {
        return true;
    }

    @objid ("f2db1035-91ac-49d2-93d4-cf3005208b6a")
    @Override
    public void stop() throws ModuleException {
        // Empty
    }

    @objid ("36cc4534-3b54-43e5-9fda-baaaf713f55f")
    @Override
    public void unselect() throws ModuleException {
        // Empty
    }

    @objid ("10fb592c-8c34-4d57-824e-7774e528c850")
    @Override
    public void upgrade(Version oldVersion, Map<String, String> oldParameters) throws ModuleException {
        // Empty
    }

    /**
     * C'tor initializing the module.
     * @param module the module being constructed.
     */
    @objid ("53dce8bc-1100-4612-b48b-7fa77a21e7a4")
    public DefaultModuleLifeCycleHandler(IModule module) {
        this.module = module;
    }

}

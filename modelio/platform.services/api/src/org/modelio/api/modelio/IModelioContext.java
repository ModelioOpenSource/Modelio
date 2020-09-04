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

package org.modelio.api.modelio;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.context.project.IProjectStructure;
import org.modelio.vbasic.version.Version;

/**
 * This interface gives access to various information about Modelio installation.
 */
@objid ("4d4df2f8-eb90-11dd-8ce6-0014222a9f79")
public interface IModelioContext {
    /**
     * Get the language defined for resources. The returned value is the value that Locale.getDefault().getLanguage()
     * returns if this value is supported by Modelio. Otherwise 'us' is returned.
     * 
     * @return a String containing the language used for Modelio resources.
     */
    @objid ("2f09bbda-ebb7-11dd-aa31-0014222a9f79")
    String getLanguage();

    /**
     * Get the project space path.<br>
     * The project space is the directory where the .ofp file is stored, but also a basis for the generation root
     * directories and the module deployment paths. If no project is currently opened, returns null;
     * @see IModuleUserConfiguration#getProjectSpacePath() : You also can use
     * <code>getConfiguration().getProjectSpacePath()</code> on the module.
     * 
     * @return the project space path or <code>null</code> if no project is currently opened
     * @deprecated use {@link IProjectStructure#getPath()} instead.
     */
    @objid ("785eaef1-5cc6-11de-bee5-001ec947ccaf")
    @Deprecated
    File getProjectSpacePath();

    /**
     * Get the version of the current Modelio
     * 
     * @return an object of the Version class that represent the version of the current Modelio.
     */
    @objid ("2f0e8088-ebb7-11dd-aa31-0014222a9f79")
    Version getVersion();

    /**
     * Get the current workspace path of Modelio.
     * 
     * @return the current workspace path of Modelio.
     */
    @objid ("9b1d0290-757c-11e0-8651-001ec947cd2a")
    File getWorkspacePath();

    /**
     * Get the current installation directory of Modelio.
     * 
     * @return the current installation directory of Modelio.
     */
    @objid ("6c3c5a2f-cd3b-4242-8c68-99e433983f44")
    File getInstallPath();

}

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
package org.modelio.api.module;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.vbasic.version.Version;

/**
 * Public interface of the mdac services.
 * <p><p>
 * When a mdac is built using the <i>MDA Modeler</i> tool, a public interface
 * is generated and accessible for the other mdac developments.
 * <p><p>
 * The main class that allows developpers to get specific mdac services has to
 * implement the current interface.
 * <p><p>
 * Each mda component brings a specific interface that inherit from this one and gives
 * all the desired mdac services.
 * <p><p>
 * This interface should never directly been implement by a developper.
 */
@objid ("01f40414-0000-32e8-0000-000000000000")
public interface IPeerModule {
    /**
     * Used to return the mdac name.
     * <p><p>
     * The mdac name corresponds to the name of the MDA Component, as defined in the
     * <i>MDA Modeler</i> tool.
     * @return The mda component name
     */
    @objid ("01f40414-0000-32ed-0000-000000000000")
    String getName();

    /**
     * Used to return the mdac description.
     * @return The mda component description
     */
    @objid ("01f40414-0000-32f1-0000-000000000000")
    String getDescription();

    /**
     * Used to return the mdac version.
     * @return The mda component version
     */
    @objid ("01f40414-0000-32f5-0000-000000000000")
    Version getVersion();

    /**
     * Return the module API configuration.
     * <p><p>
     * The mdac configuration permits to access certain information
     * such as parameters, resource path, etc.
     * @return The module configuration
     */
    @objid ("01f40414-0000-32f9-0000-000000000000")
    IModuleAPIConfiguration getConfiguration();

}

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

package org.modelio.api.module.context.configuration;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface describe the API of module configuration for API use.
 * <p>
 * Module configuration provide access to module parameter and resource paths.
 * <p>
 * Available only on IPeerModule
 */
@objid ("01f40414-0000-3903-0000-000000000000")
public interface IModuleAPIConfiguration {
    /**
     * Get the value of the parameter identified by the given <code>key</code>.
     * <p>
     * Returns null if the key does not exist
     * 
     * @param key Key of the parameter
     * @return The value of the <code>key</code> parameter (or null)
     */
    @objid ("01f40414-0000-3904-0000-000000000000")
    String getParameterValue(String key);

    /**
     * Get all parameter values.
     * 
     * @return A map representing the parameter name as key and the parameter value as value.
     */
    @objid ("01f40414-0000-390b-0000-000000000000")
    Map<String, String> getParameters();

    /**
     * Set the value of a parameter.
     * 
     * @param key Key of the parameter
     * @param value Value to define on the parameter
     * @return <code>true</code> if the parameter is only locally defined.
     */
    @objid ("01f40414-0000-3912-0000-000000000000")
    boolean setParameterValue(String key, String value);

    /**
     * Update the mdac parameters with the given ones.
     * 
     * @param parameters The new module parameter values.
     */
    @objid ("01f40414-0000-42d5-0000-000000000000")
    void updateFrom(Map<String, String> parameters);

    /**
     * Get the path where the files packaged with the module were deployed. It allows the module to access its resource
     * files.
     * 
     * @return the module resources path.
     */
    @objid ("d07290ac-cce7-11dd-8617-001ec947ccaf")
    Path getModuleResourcesPath();

    /**
     * Returns the documentation path.
     * 
     * @return the documentation path.
     */
    @objid ("079ed4d4-ae25-11e1-893a-002564c97630")
    List<Path> getDocpath();

    /**
     * Get the lock status of the parameter identified by the given <code>key</code>.<p>
     * A parameter is locked when defined by a Modelio server.
     * 
     * @param key Key of the parameter
     * @return Whether or not the given parameter is locked.
     */
    @objid ("aabdc660-8a5d-43ea-b1df-8f3c6c341946")
    boolean isLocked(String key);

}

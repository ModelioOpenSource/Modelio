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

package org.modelio.api.module.context.configuration;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface describe the API of module configuration to use for UI.
 * It allows to set a new config parameter validator.
 * <p>
 * Available only on IModule.
 */
@objid ("cce5ce5e-f121-11e1-9565-002564c97630")
public interface IModuleUserConfiguration {
    @objid ("2ce0147b-f122-11e1-9565-002564c97630")
    void setParameterValidator(String paramName, IConfigParamValidator validator);

    /**
     * Get the value of the parameter identified by the given <code>key</code>.<p>
     * Returns null if the key does not exist
     * @param key Key of the parameter
     * @return The value of the <code>key</code> parameter (or null)
     */
    @objid ("e7981cbb-03e3-11e2-8e1f-001ec947c8cc")
    String getParameterValue(String key);

    /**
     * Get all parameter values.
     * @return A map representing the parameter name as key and the parameter value as value.
     */
    @objid ("e7981cbc-03e3-11e2-8e1f-001ec947c8cc")
    Map<String, String> getParameters();

    /**
     * Set the value of a parameter.
     * @param key Key of the parameter
     * @param value Value to define on the parameter
     * @return <code>true</code> if the parameter is only locally defined.
     */
    @objid ("e7981cbd-03e3-11e2-8e1f-001ec947c8cc")
    boolean setParameterValue(String key, String value);

    /**
     * Update the mdac parameters with the given ones.
     * @param parameters The new module parameter values.
     */
    @objid ("e7981cbe-03e3-11e2-8e1f-001ec947c8cc")
    void updateFrom(Map<String, String> parameters);

    /**
     * Get the path where the files packaged with the module were deployed.
     * It allows the module to access its resource files.
     * @return the module resources path.
     */
    @objid ("e7981cc1-03e3-11e2-8e1f-001ec947c8cc")
    Path getModuleResourcesPath();

    /**
     * Returns the documentation path.
     * @return the documentation path.
     */
    @objid ("e7981cc2-03e3-11e2-8e1f-001ec947c8cc")
    List<Path> getDocpath();

    @objid ("e7981cc3-03e3-11e2-8e1f-001ec947c8cc")
    IConfigParamValidator getParameterValidator(String paramName);

    /**
     * Get the lock status of the parameter identified by the given <code>key</code>.<p>
     * A parameter is locked when defined by a Modelio server.
     * @param key Key of the parameter
     * @return Whether or not the given parameter is locked.
     */
    @objid ("4294bf47-6e25-4947-aa58-1407bb92dc83")
    boolean isLocked(String key);

    /**
     * Returns the style path.
     * @return the style path.
     */
    @objid ("5b1183a7-896e-4f8c-a22a-102913888a2a")
    Map<String, Path> getStylePath();

}

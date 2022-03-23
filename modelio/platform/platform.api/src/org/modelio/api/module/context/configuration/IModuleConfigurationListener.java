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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * IModuleConfigurationListener listener called when a module parameter value has changed.
 * @since 4.1
 */
@objid ("9b3f3aed-be06-40f9-95c6-c760c8fcb2f2")
public interface IModuleConfigurationListener {
    /**
     * A IModuleConfigurationListener is called when a module parameter value has changed.
     * @param pName The name of the parameter whose value changed.
     * @param oldValue The previous value of the parameter value. Can be null.
     * @param newValue The new value of the parameter value. Can be null.
     * @since 4.1
     */
    @objid ("94f6cf51-eff9-4678-ab10-210daaa5f489")
    void parameterChanged(String pName, String oldValue, String newValue);

}

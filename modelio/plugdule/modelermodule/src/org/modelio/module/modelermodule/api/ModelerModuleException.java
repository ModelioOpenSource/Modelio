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

package org.modelio.module.modelermodule.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Default exception type used by ModelerModule.
 */
@objid ("1d3e2042-afd6-4cff-a9df-1c8703dfbb2c")
public class ModelerModuleException extends Exception {
    @objid ("77f4c91d-accc-4879-82ee-a06c51aad886")
    private static final long serialVersionUID = 982636473481714157L;

    /**
     * Default constructor.
     * 
     * @param message the error message.
     */
    @objid ("2cd32773-20c0-41aa-a51c-777bb4113692")
    public ModelerModuleException(final String message) {
        super(message);
    }

}

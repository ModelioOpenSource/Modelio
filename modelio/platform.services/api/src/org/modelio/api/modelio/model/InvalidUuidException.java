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

package org.modelio.api.modelio.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Thrown when an invalid uuid is used. The reason for declaring a uuid invalid depends on its usage.
 */
@objid ("5376f3f2-7592-11e0-8651-001ec947cd2a")
public class InvalidUuidException extends RuntimeException {
    @objid ("5376f3f6-7592-11e0-8651-001ec947cd2a")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidPathException with no detail message
     */
    @objid ("5376f3f3-7592-11e0-8651-001ec947cd2a")
    public InvalidUuidException() {
        super();
    }

    /**
     * Constructs an InvalidPathException with the specified detail message.
     * 
     * @param message the detail message.
     */
    @objid ("5376f3f4-7592-11e0-8651-001ec947cd2a")
    public InvalidUuidException(String message) {
        super(message);
    }

}

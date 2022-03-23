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
package org.modelio.api.modelio.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Thrown when an invalid UMLPath is used.<p>
 * The UMLPath is considered as invalid if it references a namespace that does not
 * exist.
 */
@objid ("00d00158-0001-631c-0000-000000000000")
public class InvalidPathException extends RuntimeException {
    @objid ("00d00158-0001-631e-0000-000000000000")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidPathException with no detail message
     */
    @objid ("00d00158-0001-6322-0000-000000000000")
    public  InvalidPathException() {
        super();
    }

    /**
     * Constructs an InvalidPathException with the specified detail message.
     * @param message the detail message.
     */
    @objid ("00d00158-0001-6324-0000-000000000000")
    public  InvalidPathException(String message) {
        super(message);
    }

}

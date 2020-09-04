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

package org.modelio.api.modelio.diagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Thrown when the destination point of a {@link ILinkPath} is invalid.
 */
@objid ("01e402b4-0000-21ab-0000-000000000000")
public class InvalidDestinationPointException extends Exception {
    @objid ("01e402b4-0000-21b3-0000-000000000000")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param   message   the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    @objid ("01e402b4-0000-21b0-0000-000000000000")
    public InvalidDestinationPointException(String message) {
        super(message);
    }

}

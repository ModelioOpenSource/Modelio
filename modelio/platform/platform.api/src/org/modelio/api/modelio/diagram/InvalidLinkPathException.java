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
package org.modelio.api.modelio.diagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Base class for exceptions telling a {@link ILinkPath} is invalid.
 * 
 * @since 5.1.0, now derives from RuntimeException instead of Exception
 */
@objid ("aa3117fb-c616-4455-914e-728501287f76")
public class InvalidLinkPathException extends RuntimeException {
    @objid ("b9630c03-bc52-4113-a8b1-d9852bd777c2")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param   message   the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    @objid ("424a231b-1850-4f6c-9f4b-5658aecf84c2")
    public  InvalidLinkPathException(String message) {
        super(message);
    }

}

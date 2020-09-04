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
 * Thrown when a transaction should be commited but could not because of the audit system.
 * <p>
 * <p>
 * When a transaction is commited, Modelio audits the transaction to check that
 * it is considered as valid. If not, an <code>InvalidTransactionException</code> exception
 * is thrown.
 */
@objid ("00d00158-0001-6328-0000-000000000000")
@Deprecated
public class InvalidTransactionException extends RuntimeException {
    @objid ("00d00158-0001-632a-0000-000000000000")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new runtime exception with <code>null</code> as its
     * detail message.
     */
    @objid ("00d00158-0001-632e-0000-000000000000")
    public InvalidTransactionException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * 
     * @param message the detail message.
     */
    @objid ("00d00158-0001-6330-0000-000000000000")
    public InvalidTransactionException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.
     * 
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    @objid ("00d00158-0001-6333-0000-000000000000")
    public InvalidTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified cause.
     * 
     * @param cause the cause of the exception.
     */
    @objid ("00d00158-0001-6337-0000-000000000000")
    public InvalidTransactionException(Throwable cause) {
        super(cause);
    }

}

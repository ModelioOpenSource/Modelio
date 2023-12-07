/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.vcore.session.api.repository;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Exception that tells the reopsitory is closed.
 * @author cmarin
 * @since 5.5 / 30/08/2023
 */
@objid ("88c4d8ec-f177-4d15-be6c-bd04e98c2c43")
public class RepositoryClosedException extends IllegalStateException {
    @objid ("26527f7e-fb59-431b-8b9b-d607134bb633")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     * 
     * <p>Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     * @param  message the detail message (which is saved for later retrieval
     * by the {@link Throwable#getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     * {@link Throwable#getCause()} method).  (A {@code null} value
     * is permitted, and indicates that the cause is nonexistent or
     * unknown.)
     */
    @objid ("a957a1d8-2281-48e0-8f64-643d448cd859")
    public  RepositoryClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an IllegalStateException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     * @param s the String that contains a detailed message
     */
    @objid ("be4b54aa-79c3-4b31-8185-8517fdf26970")
    public  RepositoryClosedException(String s) {
        super(s);
    }

}

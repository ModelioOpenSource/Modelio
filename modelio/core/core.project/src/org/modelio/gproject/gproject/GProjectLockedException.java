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
package org.modelio.gproject.gproject;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.ILockInfo;

/**
 * Indicates the project is already open in another (or same) Modelio instance.
 */
@objid ("3dc2e5fc-0551-4e16-9788-07abd236d647")
public class GProjectLockedException extends IOException {
    @objid ("f346b01b-a38d-487d-b7c1-399577e48549")
    private static final long serialVersionUID = 2L;

    @objid ("217e98b1-4943-4f0a-9b1b-8cf5b48a5d6c")
    private ILockInfo info;

    /**
     * Constructs an {@code GProjectLockedException} with the specified detail message
     * and cause.
     * 
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     * @param message The detail message (which is saved for later retrieval
     * by the {@link #getMessage()} method)
     * @param cause The cause (which is saved for later retrieval by the
     * {@link #getCause()} method).  (A null value is permitted,
     * and indicates that the cause is nonexistent or unknown.)
     */
    @objid ("2b277550-6b2b-435e-b4aa-dab4caa6cdb0")
    public  GProjectLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code GProjectLockedException} with the specified detail message
     * and loack informations.
     * @param message The detail message (which is saved for later retrieval
     * by the {@link #getMessage()} method)
     * @param info lock informations
     */
    @objid ("ea5d686d-f998-47e7-8989-663170d21f62")
    public  GProjectLockedException(String message, ILockInfo info) {
        super(message);
        this.info = info;
        
    }

    /**
     * Get informations on the project lock, if available.
     * @return the lock informations or <i>null</i>.
     */
    @objid ("6088adc5-6137-420e-b769-b3c50b1c848a")
    public ILockInfo getLockInfos() {
        return this.info;
    }

}

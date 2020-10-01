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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Exception thrown when opening a project fails because of some authentication issue.
 */
@objid ("50f3b145-72b9-4853-8201-5e33b2c9023b")
public class GProjectAuthenticationException extends Exception {
    @objid ("91d1327d-1550-4dc7-8949-7e1be607f3be")
    private static final long serialVersionUID = 1L;

    @objid ("694617dd-eee3-44b6-a67a-a37104769d1c")
    private IAuthData authData;

    /**
     * Constructs a new exception with no detail.
     */
    @objid ("418d1432-2d39-496f-aec8-c6258f26daf0")
    public GProjectAuthenticationException() {
        super();
    }

    /**
     * Constructs a new exception.
     * 
     * @param message a message
     * @param cause a cause
     */
    @objid ("23aea02f-9289-4080-b50e-0822be1863d0")
    public GProjectAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception.
     * 
     * @param message a message
     */
    @objid ("9712fdf8-2304-40d2-8143-8fce23bcb103")
    public GProjectAuthenticationException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception.
     * 
     * @param cause a cause
     */
    @objid ("6119baf5-3bcc-4068-b012-cd7005b134a6")
    public GProjectAuthenticationException(Throwable cause) {
        super(cause);
    }

    /**
     * Get authentication data used to try connection.
     * 
     * @return the failed authentication data.
     */
    @objid ("9340167f-8ff0-4d1c-8d56-03df2bb7e250")
    public IAuthData getAuthData() {
        return this.authData;
    }

    /**
     * Set authentication data used to try connection.
     * 
     * @param authData the invalid authentication data.
     * @return <i>this</i> as convenience.
     */
    @objid ("69f64948-b406-4290-b469-b078175f4198")
    public GProjectAuthenticationException setAuthData(IAuthData authData) {
        this.authData = authData;
        return this;
    }

}

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
package com.sun.star.comp.beans;

import java.net.MalformedURLException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.lang.XComponent;
import com.sun.star.uno.XComponentContext;

/**
 * This abstract class represents a connection to the office application.
 */
@objid ("6e464541-7004-4568-8064-b879821325b8")
public interface OfficeConnection extends XComponent {
    /**
     * Sets a connection URL.
     * @param url This is UNO URL which describes the type of a connection.
     * @throws MalformedURLException if the URL is invalid
     */
    @objid ("2c87f254-f95b-417b-b61d-5078ac5ad4eb")
    void setUnoUrl(final String url) throws MalformedURLException;

    /**
     * Retrieves the UNO component context.
     * Establishes a connection if necessary and initializes the
     * UNO service manager if it has not already been initialized.
     * @return The office UNO component context.
     */
    @objid ("30e722e8-cdde-4fb0-b7f6-220b5ece55ab")
    XComponentContext getComponentContext();

}

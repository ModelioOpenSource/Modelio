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
package com.sun.star.lib.loader;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * WinRegKeyException is a checked exception.
 */
@objid ("943b8222-f835-4c38-be80-1769bda8690c")
final class WinRegKeyException extends java.lang.Exception {
    @objid ("394c3a10-07fe-4c1f-b210-e256e27739dd")
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a <code>WinRegKeyException</code>.
     */
    @objid ("3dddc885-dc0d-4102-85e7-8b07a832416a")
    public  WinRegKeyException() {
        super();
    }

    /**
     * Constructs a <code>WinRegKeyException</code> with the specified
     * detail message.
     * @param  message   the detail message
     */
    @objid ("83006bd9-bd9c-4971-bbea-a06fa31d9d42")
    public  WinRegKeyException(final String message) {
        super( message );
    }

}

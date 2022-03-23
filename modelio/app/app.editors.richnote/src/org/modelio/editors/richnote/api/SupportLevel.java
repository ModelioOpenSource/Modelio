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
package org.modelio.editors.richnote.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Support level of a given format.
 */
@objid ("cc551c6e-2cce-409c-a6d4-c1cb9c643b07")
public enum SupportLevel {
    /**
     * The format is not supported.
     */
    @objid ("00aad22e-7ec5-4308-8c36-9ea2ee2be9c0")
    None,
    /**
     * The format is supported natively.
     */
    @objid ("2447d8d6-2fb2-4a59-81d9-ede7448ee5a2")
    Primary,
    /**
     * The format is supported as an alternate one. Another editor might support it better.
     */
    @objid ("bdc5e56d-598e-42a0-9a06-7864295a1c44")
    Alternate;

}

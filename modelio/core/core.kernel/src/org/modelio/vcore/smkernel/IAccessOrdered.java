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
package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Interface for objects recording their last access time.
 * <p>
 * Used by the swap system to choose objects to drop.
 * @author cmarin
 * @see AccessOrderer
 */
@objid ("e0278158-af4f-4d75-a703-1f861c286ca4")
public interface IAccessOrdered {
    /**
     * Set the last access time.
     * @param accessTime the last access time.
     */
    @objid ("b1f82ea0-3a48-41a8-81e9-756ffc52e9e0")
    void setLastAccess(int accessTime);

    /**
     * @return the last access time.
     */
    @objid ("aa4fe13e-0783-4852-ae2c-65a1277c8ee9")
    int getLastAccess();
}


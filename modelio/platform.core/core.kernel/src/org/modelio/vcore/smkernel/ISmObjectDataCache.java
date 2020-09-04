/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Cache of in memory {@link ISmObjectData}.
 */
@objid ("18a08aa9-856d-11e1-bb11-001ec947ccaf")
public interface ISmObjectDataCache {
    /**
     * Get the cached data for the given UUID.
     * 
     * @param uid the identifier.
     * @return the cached data or <code>null</code>.
     */
    @objid ("0212830d-8585-11e1-bb11-001ec947ccaf")
    ISmObjectData getCachedData(final String uid);

    /**
     * Put the given data to cache.
     * 
     * @param data the data to cache.
     */
    @objid ("02128311-8585-11e1-bb11-001ec947ccaf")
    void putDataToCache(final ISmObjectData data);

}

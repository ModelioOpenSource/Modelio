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

package org.modelio.vcore.session.impl.cache;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.handles.ICacheHandle;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * <code>StdMetaObject</code> delegate to maintain the cache.
 */
@objid ("006f8d4e-0d1e-1f20-85a5-001ec947cd2a")
public class CacheHandle implements ICacheHandle {
    @objid ("006c83ba-0d1e-1f20-85a5-001ec947cd2a")
    private CacheManager cacheManager;

    /**
     * Initialize the cache handler
     * 
     * @param cacheManager the cache to maintain.
     */
    @objid ("006e898a-0d1e-1f20-85a5-001ec947cd2a")
    public CacheHandle(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @objid ("006c7f14-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void createObject(SmObjectImpl obj) {
        try {
            this.cacheManager.addToCache (obj);
        } catch (DuplicateObjectException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @objid ("006c8108-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void deleteObject(SmObjectImpl obj) {
        this.cacheManager.addToDeleted(obj);
    }

    @objid ("006c84b4-0d1e-1f20-85a5-001ec947cd2a")
    @Override
    public void objUndeleted(SmObjectImpl obj) {
        this.cacheManager.removeFromDeleted(obj);
    }

}

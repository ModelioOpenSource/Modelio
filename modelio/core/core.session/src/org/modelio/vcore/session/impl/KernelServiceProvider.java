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
package org.modelio.vcore.session.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.cache.MemoryManager;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.IKernelServiceProvider;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectDataCache;
import org.modelio.vcore.smkernel.ISwap;
import org.modelio.vcore.smkernel.KernelRegistry;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * CoreSession kernel service provider implementation.
 */
@objid ("002cdb7a-eb1c-1f22-8c06-001ec947cd2a")
final class KernelServiceProvider implements IKernelServiceProvider {
    @objid ("002cedea-eb1c-1f22-8c06-001ec947cd2a")
    private short kid;

    @objid ("002cf4e8-eb1c-1f22-8c06-001ec947cd2a")
    private final ISwap swap;

    @objid ("19f0ff54-cb37-11e1-87f1-001ec947ccaf")
    private final ISmObjectDataCache dataCache;

    @objid ("c46dac13-97d7-4821-8411-cbe220bd928a")
    private CoreSession session;

    @objid ("002cface-eb1c-1f22-8c06-001ec947cd2a")
     KernelServiceProvider(CoreSession session, ISwap swap, ISmObjectDataCache cacheManager) {
        KernelRegistry.registerService(this);
        this.swap = swap;
        this.dataCache = cacheManager;
        this.session = session;
        
    }

    @objid ("002d0e1a-eb1c-1f22-8c06-001ec947cd2a")
    public void dispose() {
        KernelRegistry.removeService(this.kid);
        this.swap.close();
        
    }

    @objid ("002d1cb6-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public ISwap getSwap() {
        return this.swap;
    }

    @objid ("002d43ee-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void setId(final short newId) {
        this.kid = newId;
    }

    @objid ("002d61bc-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public short getId() {
        return this.kid;
    }

    @objid ("00374812-1199-1f35-b94f-001ec947cd2a")
    @Override
    public String getName() {
        return "CoreSession "+this.kid;
    }

    @objid ("024231cb-8585-11e1-bb11-001ec947ccaf")
    private ISmObjectDataCache getDataCache() {
        return this.dataCache;
    }

    /**
     * Get the underlying CoreSession.
     * @return the CoreSession.
     */
    @objid ("6c4d3cac-176e-11e2-ac36-001ec947ccaf")
    CoreSession getSession() {
        return this.session;
    }

    @objid ("3ba767f5-4853-11e2-91c9-001ec947ccaf")
    @Override
    public ISmObjectData loadData(SmObjectImpl oobj) throws DeadObjectException {
        final String uuid = oobj.getUuid();
        final ISmObjectData cachedData = getDataCache().getCachedData(uuid);
        
        ISmObjectData data = cachedData;
        
        // Restore from swap
        if (data == null) {
            synchronized (MemoryManager.get()) {
                data = getSwap().restore(uuid);
            }
        }
        
        // Maybe the data is not loaded
        if (data == null) {
            IRepository repository = this.session.getRepository(oobj);
            if (repository != null) {
                data = repository.loadObjectData(oobj);
            }
        }
        
        
        if (data == null) {
            throw new DeadObjectException(oobj);
        }
        
        if (cachedData == null) {
            getDataCache().putDataToCache(data);
        }
        
        oobj.initData(data);
        oobj.init(uuid, data.getLiveId());
        return data;
    }

    @objid ("91750174-1f9b-4c76-829f-ea6bb3992352")
    @Override
    public SmMetamodel getMetamodel() {
        return this.session.getMetamodel();
    }

}

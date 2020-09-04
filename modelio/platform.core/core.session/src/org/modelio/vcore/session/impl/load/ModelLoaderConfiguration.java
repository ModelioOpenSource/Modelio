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

package org.modelio.vcore.session.impl.load;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.session.impl.cache.CacheManager;
import org.modelio.vcore.smkernel.IMetaOf;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Data object used to initialize a {@link ModelLoader}.
 */
@objid ("cedfe5d4-4941-43ef-af8b-86844ad81d6a")
public final class ModelLoaderConfiguration {
    @objid ("da8878eb-fb91-4520-a60e-7bcbe7a546ce")
    private final short kid;

    @objid ("1cb05412-1b01-49e5-af6f-e74d69270dbb")
    private final byte rid;

    @objid ("c0a7a0b5-1372-4ec2-a08b-ddb4187c7da7")
    private final CoreSession session;

    @objid ("97389eb9-e3af-4aa6-9fea-23fc60442567")
    private final IRepository shellRepository;

    @objid ("575b6e1e-7624-4b61-b6e4-be99a2404e0a")
    private final CacheManager cacheManager;

    @objid ("9441ac81-2644-4642-9fb5-28dabc8a2686")
    private final IAccessManager accessManager;

    @objid ("6bc6ac18-5bdc-4a0e-9ef6-61b116066765")
    private final RefreshEventService refreshEventService;

    @objid ("b08bf1b3-1cee-403d-8f4c-d0ed1c8e6526")
    private final SmMetamodel metamodel;

    @objid ("e6da6fc1-87b1-4433-836b-a57a53dcf7a1")
    private final IRepositoryObject unloadedRepositoryHandle;

    /**
     * @param unloadedRepositoryHandle
     * 
     * @param session the core session
     * @param kid the kernel id
     * @param rid the repository id
     * @param shellRepository the unresolved references repository
     * @param cacheManager the cache manager
     * @param accessManager the access manager
     * @param refreshEventService the model refresh event service
     */
    @objid ("1c44100c-b515-42ac-aa4a-bb6f2728a65b")
    public ModelLoaderConfiguration(CoreSession session, short kid, byte rid, IRepository shellRepository, CacheManager cacheManager, IAccessManager accessManager, RefreshEventService refreshEventService, IRepositoryObject unloadedRepositoryHandle) {
        this.session = session;
        this.accessManager = accessManager;
        this.cacheManager = cacheManager;
        this.kid =  kid;
        this.rid = rid;
        this.shellRepository = shellRepository;
        this.refreshEventService = refreshEventService;
        this.unloadedRepositoryHandle = unloadedRepositoryHandle;
        this.metamodel = session.getMetamodel();
        
        assert (this.refreshEventService != null);
    }

    /**
     * @return the core session
     */
    @objid ("700f8e7c-706d-42e2-851a-46da1e29236d")
    public ICoreSession getSession() {
        return this.session;
    }

    /**
     * @return the kernel id
     */
    @objid ("e2a0581d-c63c-4838-8b6b-35b735c82a98")
    public short getKid() {
        return this.kid;
    }

    /**
     * @return the repository id
     */
    @objid ("c403a5b2-5010-4bd3-a7ec-6ad64db66a72")
    public byte getRid() {
        return this.rid;
    }

    /**
     * @return the unresolved references repository
     */
    @objid ("caf7819d-69ce-4ffc-92f4-c25e49650a9b")
    public IRepository getShellRepository() {
        return this.shellRepository;
    }

    /**
     * @return the cache manager
     */
    @objid ("a7b394ab-75b8-4d98-9a40-55be9c6508fd")
    public CacheManager getCacheManager() {
        return this.cacheManager;
    }

    /**
     * @return the access manager
     */
    @objid ("8bc57b52-4ac9-49e4-93f7-9e3ef3667f07")
    public IAccessManager getAccessManager() {
        return this.accessManager;
    }

    /**
     * @return the refresh event service.
     */
    @objid ("790cd109-0f8a-40ce-bc7b-50362e05edeb")
    public RefreshEventService getRefreshEventService() {
        return this.refreshEventService;
    }

    /**
     * @return the metamodel to use.
     */
    @objid ("bb66cf63-4e09-4b0d-bd21-ec36eedbc05a")
    public SmMetamodel getMetamodel() {
        return this.metamodel;
    }

    /**
     * @return the session metaobject.
     */
    @objid ("5ec3b973-90f0-4402-8a7b-bb20add8e2ba")
    public IMetaOf getMetaObject() {
        return this.session.getMetaObject();
    }

    /**
     * @return the unloaded objects repository handle.
     */
    @objid ("b9fe55ea-557b-4c74-bcc6-754e16362016")
    public IRepositoryObject getUnloadedRepositoryHandle() {
        return this.unloadedRepositoryHandle;
    }

}

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

package org.modelio.vcore.session.impl.load;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.repository.IRepositoryChangeEvent;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.IModelLoaderProvider;
import org.modelio.vcore.session.impl.storage.IModelRefresher;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implementation of {@link IModelLoaderProvider}.
 * <p>
 * Handles a concurrent pool of {@link IModelLoader} and
 * provides the first free one in {@link #beginLoadSession()}.
 */
@objid ("a55c7527-1a03-11e2-8eb9-001ec947ccaf")
public class ModelLoaderProvider implements IModelLoaderProvider {
    @objid ("b42ab8d9-a2ba-4f87-bd56-95d820c702d6")
    private final Queue<IModelLoader> loaderPool;

    @objid ("ec10b3aa-88ee-47f4-a299-96858cfffa9e")
    private final ModelLoaderConfiguration loaderConfig;

    @objid ("34a3103c-6fc0-434e-b100-a7fed28526aa")
    private final Queue<IModelLoader> refreshPool;

    /**
     * Initialize the model loader provider.
     * 
     * @param loaderConfig The model loaders configuration.
     */
    @objid ("7dc20091-1c43-11e2-8eb9-001ec947ccaf")
    public ModelLoaderProvider(ModelLoaderConfiguration loaderConfig) {
        this.loaderPool = new ConcurrentLinkedQueue<>();
        this.refreshPool = new ConcurrentLinkedQueue<>();
        this.loaderConfig = loaderConfig;
    }

    @objid ("f69ee879-3948-11e2-920a-001ec947ccaf")
    @Override
    public short getKid() {
        return this.loaderConfig.getKid();
    }

    @objid ("1fc5fb7a-3a2d-11e2-bf6c-001ec947ccaf")
    @Override
    public IModelLoader beginLoadSession() {
        IModelLoader ret = this.loaderPool.poll();
        if (ret == null) {
            ret = new ModelLoader(this.loaderConfig, this.loaderPool);
        }
        
        ret.begin();
        return ret;
    }

    @objid ("b02c4de4-aa36-4dc4-8fda-60fbc92acecb")
    @Override
    public IModelRefresher beginRefreshSession() {
        IModelRefresher ret = (IModelRefresher) this.refreshPool.poll();
        if (ret == null) {
            ret = new ModelRefresher(this.loaderConfig, this.refreshPool);
        }
        
        ret.begin();
        return ret;
    }

    @objid ("591da55f-6323-4bd6-ad16-d447a8ee0ed1")
    @Override
    public void fireRepositoryChange(IRepositoryChangeEvent event) {
        this.loaderConfig.getSession().getRepositorySupport().fireRepositoryChange(event);
    }

    @objid ("4e669a55-78b0-437c-a8fa-8b83668a4c70")
    @Override
    public SmMetamodel getMetamodel() {
        return this.loaderConfig.getMetamodel();
    }

    @objid ("feb580e8-ceef-46c8-8ecf-29ed7290958f")
    @Override
    public ScheduledExecutorService getSchedulerService() {
        return this.loaderConfig.getSession().getSchedulerService();
    }

}

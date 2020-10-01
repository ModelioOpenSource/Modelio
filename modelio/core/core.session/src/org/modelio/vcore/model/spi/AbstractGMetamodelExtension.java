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

package org.modelio.vcore.model.spi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IRepositoryContentInitializer;
import org.modelio.vcore.model.api.IRepositoryRootGetter;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.services.MetamodelExtensionPoint;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * Base implementation of {@link IGMetamodelExtension}.
 * <p>
 * All metamodel extensions should subclass this class and implement all abstract methods.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("3766af5f-5cc4-4544-ad26-8573888e987a")
public abstract class AbstractGMetamodelExtension implements IGMetamodelExtension {
    @objid ("b39da582-0981-47a8-b393-1c4c0c0a0181")
    private ISmMetamodelFragment mmFragment;

    @objid ("e5798298-4d16-4b7e-93f8-5b1faf03bd64")
    protected abstract IElementNamer getNamer(ICoreSession session);

    @objid ("dc2adf7c-c2f3-4b18-9eb1-6fb0936aaa8f")
    protected abstract IElementConfigurator getConfigurator(ICoreSession session);

    @objid ("4d65750c-e570-462e-bbda-bdd0155deb90")
    protected abstract IRepositoryContentInitializer getPopulator(ICoreSession session);

    @objid ("d7b20ae1-47e0-470f-8517-8d35db946e8a")
    protected abstract IRepositoryRootGetter getRootGetter(ICoreSession session);

    @objid ("c659e9f9-b1a2-4610-89c2-9bcfd7a816db")
    protected abstract IModelFactoryProvider getModelFactoryProvider(ICoreSession session);

    @objid ("9fd10aab-f946-464e-929f-8e379fd41acd")
    @Override
    public void register(ICoreSession session) {
        MTools tools = MTools.get(session);
        
        registerProvider(tools.getConfigurator().getMetamodelExtensionPoint(), getConfigurator(session));
        registerProvider(tools.getModelFactory().getMetamodelExtensionPoint(), getModelFactoryProvider(session));
        registerProvider(tools.getNamer().getMetamodelExtensionPoint(), getNamer(session));
        registerProvider(tools.getPopulator().getMetamodelExtensionPoint(), getPopulator(session));
        registerProvider(tools.getRootGetter().getMetamodelExtensionPoint(), getRootGetter(session));
    }

    @objid ("1eb280ca-367e-4cbf-9f46-067c16ad2b64")
    protected <T> void registerProvider(MetamodelExtensionPoint<T> registry, T service) {
        if (service != null) {
            registry.registerExtension(service, getMmFragment().getClass());
        }
    }

    @objid ("7d90d164-6d74-4253-8927-c927bced9a95")
    @Override
    public ISmMetamodelFragment getMmFragment() {
        // Automatically generated method. Please do not modify this code.
        return this.mmFragment;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns null.
     */
    @objid ("f977e63d-2bf8-491b-9f27-0a54583d1039")
    @Override
    public <T> T createExtension(Class<T> service, ICoreSession session) {
        return null;
    }

    @objid ("166080c0-d3f8-4045-a605-16b0011b15b5")
    protected AbstractGMetamodelExtension(ISmMetamodelFragment mmFragment) {
        this.mmFragment = mmFragment;
    }

}

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
package org.modelio.metamodel.impl.mmextensions.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.InfrastructureMetamodelFragment;
import org.modelio.metamodel.impl.mmextensions.infrastructure.blob.InfrastructureMmBlobProvider;
import org.modelio.metamodel.impl.mmextensions.infrastructure.configurator.InfrastructureElementConfigurator;
import org.modelio.metamodel.impl.mmextensions.infrastructure.factory.InfrastructureModelFactoryImpl;
import org.modelio.metamodel.impl.mmextensions.infrastructure.migration.InfrastructureMmMigrationProvider;
import org.modelio.metamodel.impl.mmextensions.infrastructure.namer.InfrastructureNamer;
import org.modelio.metamodel.impl.mmextensions.infrastructure.populator.InfrastructurePopulator;
import org.modelio.metamodel.impl.mmextensions.infrastructure.root.InfrastructureRootGetter;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IRepositoryContentInitializer;
import org.modelio.vcore.model.api.IRepositoryRootGetter;
import org.modelio.vcore.model.spi.AbstractGMetamodelExtension;
import org.modelio.vcore.model.spi.IModelFactoryProvider;
import org.modelio.vcore.model.spi.mm.IMigrationProvider;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Infrastructure Modelio metamodel extension.
 * <p>
 * This should be added to any session.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("25e95fef-2c5a-4029-b370-77776f7b20f6")
public class InfrastructureMetamodelExtension extends AbstractGMetamodelExtension {
    /**
     * Initialize the metamodel extension.
     */
    @objid ("1d90a5da-c3c3-4849-bcc7-2249dd529dbc")
    public  InfrastructureMetamodelExtension() {
        super(InfrastructureMetamodelFragment.getInstance());
    }

    @objid ("ca57e272-650b-4cb5-bded-a6268909eb61")
    @Override
    protected IElementNamer getNamer(ICoreSession session) {
        return new InfrastructureNamer();
    }

    @objid ("aa269a33-cc06-4131-90d8-c7e2b876e17b")
    @Override
    protected IElementConfigurator getConfigurator(ICoreSession session) {
        return new InfrastructureElementConfigurator();
    }

    @objid ("10963985-a5f9-4eb1-9252-44d1ef48618e")
    @Override
    protected IRepositoryContentInitializer getPopulator(ICoreSession session) {
        return new InfrastructurePopulator();
    }

    @objid ("bcb80895-d966-4f5a-9f68-e233120783fe")
    @Override
    protected IRepositoryRootGetter getRootGetter(ICoreSession session) {
        return new InfrastructureRootGetter(session.getMetamodel());
    }

    @objid ("b35bf51e-4f3b-4eb7-b3d2-7ef4899abb43")
    @Override
    protected IModelFactoryProvider getModelFactoryProvider(ICoreSession session) {
        return (s) -> new InfrastructureModelFactoryImpl(s);
    }

    @objid ("8cc37a8b-32f4-4b49-9651-4fd87dcb08ee")
    @Override
    public void register(ICoreSession session) {
        super.register(session);
        
        session.getBlobSupport().addBlobProvider(new InfrastructureMmBlobProvider());
        
    }

    @objid ("48f1942e-ae29-44e7-bafa-01b5be2c23db")
    @SuppressWarnings("unchecked")
    @Override
    public <T> T createExtension(Class<T> service, ICoreSession session) {
        if (service == IMigrationProvider.class) {
            return (T) new InfrastructureMmMigrationProvider();
        } else {
            return super.createExtension(service, session);
        }
        
    }

}

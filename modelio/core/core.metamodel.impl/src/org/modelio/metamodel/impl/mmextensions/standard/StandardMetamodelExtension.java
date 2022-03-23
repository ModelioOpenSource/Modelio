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
package org.modelio.metamodel.impl.mmextensions.standard;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.StandardMetamodelFragment;
import org.modelio.metamodel.impl.mmextensions.standard.configurator.StandardElementConfigurator;
import org.modelio.metamodel.impl.mmextensions.standard.factory.StandardModelFactoryImpl;
import org.modelio.metamodel.impl.mmextensions.standard.migration.StandardMmMigrationProvider;
import org.modelio.metamodel.impl.mmextensions.standard.namer.StandardNamer;
import org.modelio.metamodel.impl.mmextensions.standard.populator.StandardPopulator;
import org.modelio.metamodel.impl.mmextensions.standard.root.StandardRootGetter;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IRepositoryContentInitializer;
import org.modelio.vcore.model.api.IRepositoryRootGetter;
import org.modelio.vcore.model.spi.AbstractGMetamodelExtension;
import org.modelio.vcore.model.spi.IModelFactoryProvider;
import org.modelio.vcore.model.spi.mm.IMigrationProvider;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Standard Modelio metamodel extension.
 * <p>
 * This should be added to any session.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("6fcbf849-2dc8-447e-8183-d428930edf0f")
public class StandardMetamodelExtension extends AbstractGMetamodelExtension {
    /**
     * Initialize the metamodel extension.
     */
    @objid ("91cb87a7-55f0-4249-8249-1e0c50e14370")
    public  StandardMetamodelExtension() {
        super(StandardMetamodelFragment.getInstance());
    }

    @objid ("f808d258-5a89-4169-8d7d-c1dd0c56a31c")
    @Override
    protected IElementConfigurator getConfigurator(ICoreSession session) {
        return new StandardElementConfigurator();
    }

    @objid ("0feb2d36-daf7-4fde-93e2-fefa0e1e49ff")
    @Override
    protected IModelFactoryProvider getModelFactoryProvider(ICoreSession session) {
        return (s) -> new StandardModelFactoryImpl(s);
    }

    @objid ("969a6e19-fa83-417e-b359-98b2416d7311")
    @Override
    protected IElementNamer getNamer(ICoreSession session) {
        return new StandardNamer();
    }

    @objid ("a648512f-cde9-4bdf-b893-485017629864")
    @Override
    protected IRepositoryContentInitializer getPopulator(ICoreSession session) {
        return new StandardPopulator();
    }

    @objid ("c4db5f2b-02bd-4ae5-a423-8b55ad0429d3")
    @Override
    protected IRepositoryRootGetter getRootGetter(ICoreSession session) {
        return new StandardRootGetter(session.getMetamodel());
    }

    @objid ("2291cb95-2b04-4395-b1a5-ae39341165a7")
    @SuppressWarnings("unchecked")
    @Override
    public <T> T createExtension(Class<T> service, ICoreSession session) {
        if (service == IMigrationProvider.class) {
            return (T) new StandardMmMigrationProvider();
        } else {
            return super.createExtension(service, session);
        }
        
    }

}

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
package org.modelio.api.impl.module;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.platform.mda.infra.service.ModuleContextFactory;

/**
 * The mda.infra module loader uses this ModuleContextFactoryImpl factory to
 * create IModuleContext instances for modules.
 * 
 * @author phv
 */
@objid ("41e555e8-06f3-4e8c-984b-db5a3c227bd9")
public class ModuleContextFactoryImpl extends ModuleContextFactory {
    @objid ("2d32f904-7ffb-4594-83a4-be41815a5735")
    private IEclipseContext eclipseContext;

    @objid ("49792120-54cc-43f5-b649-cf08b9d41556")
    @Override
    public IModuleContext createModuleContext(ModuleComponent moduleElement, IModuleUserConfiguration moduleUserConfiguration, IModuleAPIConfiguration moduleApiConfiguration) {
        return new ModuleContext(moduleElement, moduleUserConfiguration, moduleApiConfiguration, this.eclipseContext);
    }

    @objid ("493ce802-7325-4c82-8f57-3c0a209b8ade")
    public  ModuleContextFactoryImpl(IEclipseContext eclipseContext) {
        this.eclipseContext = eclipseContext;
    }

    @objid ("d0f1f0be-7e2b-4d50-9414-0029a1ce64be")
    public void register() {
        assert(ModuleContextFactory.instance == null);
        ModuleContextFactory.instance = this;
        
    }

}

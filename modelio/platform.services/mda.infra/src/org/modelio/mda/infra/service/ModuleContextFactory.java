/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.mda.infra.service;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.mda.ModuleComponent;

@objid ("9a9112e9-7d01-4c42-b3b4-8340aac65c2a")
public abstract class ModuleContextFactory {
    @objid ("77848e62-faf4-42c1-831c-9fbf51141226")
    protected static ModuleContextFactory instance;

    @objid ("c858884f-7805-463e-800b-fa7b2faff80d")
    public static ModuleContextFactory getInstance() {
        return instance;
    }

    @objid ("52898115-228a-4336-8a63-20904e3c822d")
    public abstract IModuleContext createModuleContext(ModuleComponent moduleElement, IModuleUserConfiguration moduleUserConfiguration, IModuleAPIConfiguration moduleApiConfiguration);

}

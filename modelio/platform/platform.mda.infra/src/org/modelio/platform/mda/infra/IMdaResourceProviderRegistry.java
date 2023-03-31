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
package org.modelio.platform.mda.infra;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.ModuleComponent;

/**
 * Registertry of  {@link IMdaResourceProvider} instances used by  {@link MdaResourceService} delegation mechanism.
 * 
 * @since 5.2
 */
@objid ("5016e7d2-a683-4e74-bb4a-12a154c90a80")
public interface IMdaResourceProviderRegistry {
    @objid ("eca1dce8-6f14-4655-bc4a-f76cdc174dbb")
    void register(ModuleComponent module, IMdaResourceProvider provider);

    @objid ("bf1a381f-cec7-43df-b29b-799373cd7a26")
    void unregister(ModuleComponent module);

    @objid ("4ee6f1f0-d50d-4f1f-9579-b4d32eb7b628")
    IMdaResourceProvider getProvider(ModuleComponent module);
}


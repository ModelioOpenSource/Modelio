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

package org.modelio.platform.mda.infra.service.contributions;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui;
import org.modelio.platform.mda.infra.service.IRTModule;

/**
 * Interface for plugins that want to contribute to module dynamic model loading.
 * @author cmarin
 * @since 3.4
 */
@objid ("aa371b28-3de0-4173-8abc-14899580fddb")
public interface IDynamicModelLoader {
    /**
     * Load the dynamic model part.
     * 
     * @param module the loading module
     * @param model the dynamic model JAXB model
     * @throws java.io.IOException to abort loading.
     */
    @objid ("c44a1466-dbd6-4619-8c21-004ad8d41731")
    void loadDynamicModel(IRTModule module, Jxbv2Gui model) throws IOException;

    /**
     * Unload the loaded dynamic model.
     * 
     * @param module the unloaded module.
     */
    @objid ("89ff9c7a-ccd1-4d92-91e7-07ba8e553669")
    void unloadDynamicModel(IRTModule module);

}

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
package org.modelio.platform.mda.infra.service;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.platform.mda.infra.IMdaResourceProvider;

/**
 * Access to services provided by deployed modules.
 */
@objid ("6f8ea657-4d53-473c-9049-d13dd320c297")
public interface IModuleService {
    /**
     * The MDA expert tool can answer questions about dependencies between stereotyped metaclass or elements.
     * @return the MDA expert.
     */
    @objid ("d28f6b20-c3c7-4ff4-9cd1-c13c26b869a6")
    IMdaExpert getMdaExpert();

    /**
     * Get the started modules.
     * @return The started modules.
     */
    @objid ("e7106362-faf7-4de6-8f2c-a192145b7b58")
    List<IRTModule> getStartedModules();

    /**
     * The MDA resource provider can provide I18n'ed labels and descriptions along with icons and images from MDA elements.
     * @return the MDA resources provider.
     */
    @objid ("62129c91-5ccd-4e05-ba93-7b9a9907a60a")
    IMdaResourceProvider getMdaResourceProvider();
}


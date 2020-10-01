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

package org.modelio.platform.mda.infra.service.plugins;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("bafb8784-6ba6-487d-a8cf-d5b1a50d119f")
public class PluginModuleConstants {
    /**
     * The plugin module extension point ID.
     */
    @objid ("36df8961-9d8d-4411-93de-f8cfaa9b776c")
    public static final String EXTENSION_ID = "org.modelio.platform.mda.infra.plugin_module";

    @objid ("0c7ea40a-5800-4c5d-8631-35818181487b")
    public static final String MODULE_EL = "module";

    @objid ("519eb1cd-71ec-4c6a-9029-2f83818a4e71")
    public static final String MODULE_NAME = "name";

    @objid ("0a7e48c2-dc95-49c3-903c-b73ad8c2a747")
    public static final String MODULE_VERSION = "version";

    @objid ("0bb8e6f1-e961-4eea-89e9-9cce27af8bbe")
    public static final String MODULE_MANDATORY = "mandatory";

}

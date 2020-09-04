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

package org.modelio.gproject.data.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.i18n.MessageBundle;

/**
 * Represents the <b>gproject.data</b> plugin.
 * <p>
 * Cannot respect the <a href="http://forge.minotaure.softeam.com/projects/modelio-phoenix/wiki/Plugin_singleton">
 * typical plugin singleton pattern</a> because core plugins must not use OSGI.
 */
@objid ("22f348a0-23f2-4c38-aab3-a3b3e35da805")
public class GProjectData {
    /**
     * The plugin ID.
     */
    @objid ("6ace0455-b510-49c1-a262-9f2f2095b93c")
    public static final String PLUGIN_ID = "org.modelio.gproject.data";

    /**
     * The resource bundle.
     */
    @objid ("62360ba4-d728-42d3-9675-2987396e2c38")
    public static final MessageBundle I18N = new MessageBundle(ResourceBundle.getBundle("gproject_data"));

}

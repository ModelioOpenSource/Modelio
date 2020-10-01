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

package org.modelio.gproject.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.i18n.MessageBundle;

/**
 * Represents the <b>core.project</b> plugin.
 * <p>
 * Cannot respect the <a href="http://forge.minotaure.softeam.com/projects/modelio-phoenix/wiki/Plugin_singleton">
 * typical plugin singleton pattern</a> because core plugins must not use osgi.
 */
@objid ("d1ccad8b-c9dd-11e1-96e9-001ec947ccaf")
public class CoreProject {
    /**
     * The plugin ID.
     */
    @objid ("d4817b07-cb72-11e1-87f1-001ec947ccaf")
    public static final String PLUGIN_ID = "org.modelio.core.project";

    /**
     * The resource bundle.
     */
    @objid ("d3a8861c-cb72-11e1-87f1-001ec947ccaf")
    public static final MessageBundle I18N = new MessageBundle(ResourceBundle.getBundle("coreproject"));

}

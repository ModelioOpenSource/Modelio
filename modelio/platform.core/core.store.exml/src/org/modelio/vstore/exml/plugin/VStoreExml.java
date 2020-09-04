/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vstore.exml.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.i18n.MessageBundle;

/**
 * Represents the <b>core.store.exml</b> plugin.
 * <p>
 * Cannot respect the <a href="http://forge.minotaure.softeam.com/projects/modelio-phoenix/wiki/Plugin_singleton">
 * typical plugin singleton pattern</a> because core plugins must not use OSGI.
 */
@objid ("b6608515-463d-4e21-ad27-cdcf69674050")
public class VStoreExml {
    /**
     * Plugin ID.
     */
    @objid ("c83b7df8-f707-47a0-9d93-b687b0b5779e")
    public static final String PLUGIN_ID = "org.modelio.core.store.exml";

    /**
     * Translated messages bundle.
     */
    @objid ("dfb81334-92ea-4bdb-8ae2-8170faa76b14")
    public static final MessageBundle I18N = new MessageBundle(ResourceBundle.getBundle("vstore_exml"));

}

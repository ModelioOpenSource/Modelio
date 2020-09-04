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

package org.modelio.vcore.session.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.i18n.MessageBundle;

/**
 * Represents the <b>core.session</b> plugin.
 * <p>
 * Cannot respect the <a href="http://forge.minotaure.softeam.com/projects/modelio-phoenix/wiki/Plugin_singleton">
 * typical plugin singleton pattern</a> because core plugins must not use OSGI.
 */
@objid ("ab43279b-6602-4f6b-9cd0-d3fa2b553644")
public class VCoreSession {
    /**
     * The plugin ID.
     */
    @objid ("eb554403-2612-4c95-b5d8-468f3157cf78")
    public static final String PLUGIN_ID = "org.modelio.core.session";

    /**
     * The resource bundle.
     */
    @objid ("395e5243-8ab6-4e38-8d16-8b2f7481506e")
    public static final MessageBundle I18N = new MessageBundle(ResourceBundle.getBundle("coresession"));

}

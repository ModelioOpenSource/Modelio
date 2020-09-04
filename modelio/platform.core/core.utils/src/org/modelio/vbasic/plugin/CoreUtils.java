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

package org.modelio.vbasic.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * core.utils plugin class.
 */
@objid ("dbe34ae6-cc11-11e1-a564-001ec947ccaf")
public class CoreUtils {
    /**
     * Plugin ID.
     */
    @objid ("d45c25f2-cc12-11e1-a564-001ec947ccaf")
    public static final String PLUGIN_ID = "org.modelio.core.utils";

    /**
     * Translated messages bundle.
     */
    @objid ("d454feea-cc12-11e1-a564-001ec947ccaf")
    public static final ResourceBundle I18N = ResourceBundle.getBundle("coreutils");

}

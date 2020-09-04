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

package org.modelio.app.project.conf.dialog.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Control;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.data.project.GProperties;

/**
 * Helper class to handle fields enablement following
 * the scope (local/shared) of fragment and project elements and properties.
 * @author cmarin
 */
@objid ("68838577-944c-4ece-ad21-41782b49d74f")
public class ScopeHelper {
    @objid ("b369ecfa-d2d2-4dec-a175-8c788dbb8161")
    private ScopeHelper() {
        // nothing
    }

    /**
     * Return LOCAL if the control is enabled, SHARED if
     * the control is disabled.
     * 
     * @param c a SWT control
     * @return the matching definition scope.
     */
    @objid ("8bbdfcfc-5431-4d04-86f7-a26fe10283fe")
    public static DefinitionScope getScope(Control c) {
        if (c.isEnabled())
            return DefinitionScope.LOCAL;
        else
            return DefinitionScope.SHARED;
    }

    /**
     * Returns <code>true</code> if the property is not defined or defined locally,
     * <code>false</code> if the property value is shared.
     * 
     * @param props the property container.
     * @param propName a property name.
     * @return <code>true</code> for local scope, <code>false</code> for shared.
     */
    @objid ("5eaccc97-0c87-47d6-8f88-96686ec382a6")
    public static boolean isLocalProperty(GProperties props, String propName) {
        Entry prop = props.getProperty(propName);
        return prop==null ? true : prop.getScope() == DefinitionScope.LOCAL;
    }

    /**
     * Get a localized label for the given scope.
     * 
     * @param scope a definition scope.
     * @return a label for the definition scope.
     */
    @objid ("d59f35ee-e3d0-4e6e-8068-11c0ec23a282")
    public static String getText(DefinitionScope scope) {
        switch (scope) {
        case LOCAL:
            return AppProjectConf.I18N.getString("DefinitionScope.local");
        case SHARED:
            return AppProjectConf.I18N.getString("DefinitionScope.shared");
        default:
            return scope.toString();
        }
    }

}

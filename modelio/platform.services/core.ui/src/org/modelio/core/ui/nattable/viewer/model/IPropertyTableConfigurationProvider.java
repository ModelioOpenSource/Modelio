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

package org.modelio.core.ui.nattable.viewer.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.IConfiguration;

/**
 * Eclipse executable extension point that extends PropertyNatTableViewer with supplementary {@link IConfiguration}.
 * @author cma
 * @since Valkyrie 3.8
 */
@objid ("6ecde4a2-7d1e-4a81-9c27-859c511df339")
public interface IPropertyTableConfigurationProvider {
    /**
     * The extension point identifier.
     */
    @objid ("4db4949b-f6f5-4404-ae79-751ddc5cac19")
    public static final String EXTENSION_POINT = "org.modelio.core.ui.nattable.viewer.model.IPropertyTableConfigurationProvider";

    /**
     * Get a {@link IConfiguration} with the given datas.
     * @param context the table project context
     * @param dataModel the table data model
     * @return the configuration
     */
    @objid ("10d02cb9-5fc4-4b0a-821f-3afa09b49084")
    IConfiguration getConfiguration(INatTableViewerContext context, PropertyTableDataModel dataModel);

}

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

package org.modelio.diagram.elements.core.link;

import java.util.HashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionRouter;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Connection router registry.
 * <p>
 * All supported connection routers are listed here
 * 
 * @author cmarin
 */
@objid ("7fe52397-1dec-11e2-8cad-001ec947c8cc")
public class ConnectionRouterRegistry extends HashMap<ConnectionRouterId,ConnectionRouter> {
    @objid ("7fe52399-1dec-11e2-8cad-001ec947c8cc")
    private static final long serialVersionUID = 1L;

    /**
     * Identifier used to store the router registry in the root edit part properties.
     */
    @objid ("91b8228a-1e83-11e2-8cad-001ec947c8cc")
    public static final String ID = "ConnectionRouterRegistry";

    @objid ("7fe5239e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionRouter get(Object key) {
        final ConnectionRouter ret = super.get(key);
        if (ret != null)
            return ret;
        
        throw new IllegalStateException(key + " router is not registered");
    }

}

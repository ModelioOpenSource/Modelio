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
package org.modelio.diagram.elements.core.link.ortho.migration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.migration.OrthogonalPathFixer5_0_2;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * diagram orthogonal links migration helper
 * @author cmarin
 */
@objid ("617fa230-ccdc-4375-b291-f3cb8d7517b6")
public class OrthoLinkDiagramMigrationHelper {
    /**
     * Migrate orthogonal links to 5.1 orthogonal links router
     * <p>
     * to be called in diagram deserialization method.
     * @param diagram the diagram to migrate.
     */
    @objid ("22abe806-ad32-427f-b402-ee9d972559e0")
    public static void migrate(IGmDiagram diagram) {
        // Handle orthogonal router changes as a post load action, because it needs the figure to exist
        for (IGmLinkObject link : diagram.getAllLinks()) {
            IGmPath path = link.getPath();
            if (path != null && path.getRouterKind() == ConnectionRouterId.ORTHOGONAL && link instanceof GmLink) {
                diagram.addPostLoadAction(new OrthogonalPathFixer5_0_2((GmLink) link));
            }
        }
        
    }

}

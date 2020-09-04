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

package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Defines standard locations for link extensions.
 * <p>
 * These constants can be used as key for the
 * {@link GmLink#addExtension(String, org.modelio.diagram.elements.core.node.GmNodeModel)
 * GmLink.addExtension(String key, node)} method.
 * <p>
 * All extension locations defined below are implemented in
 * {@link LinkEditPart#addChildVisual(org.eclipse.gef.EditPart, int)}.
 */
@objid ("7ff10f7e-1dec-11e2-8cad-001ec947c8cc")
public interface ExtensionLocation {
    /**
     * Upper or left location on the source side of the link
     */
    @objid ("91bf4996-1e83-11e2-8cad-001ec947c8cc")
    public static final String SourceNW = "sourceNW";

    /**
     * Lower or right location on the source side of the link.
     */
    @objid ("91bf499c-1e83-11e2-8cad-001ec947c8cc")
    public static final String SourceSE = "SourceSE";

    /**
     * Upper or left location on the target side of the link.
     */
    @objid ("91c1abec-1e83-11e2-8cad-001ec947c8cc")
    public static final String TargetNW = "TargetNW";

    /**
     * Lower or right location for source extension.
     */
    @objid ("91c1abf2-1e83-11e2-8cad-001ec947c8cc")
    public static final String TargetSE = "TargetSE";

    /**
     * Lower or right location on the middle of the link.
     */
    @objid ("91c1abf8-1e83-11e2-8cad-001ec947c8cc")
    public static final String MiddleSE = "MiddleSE";

    /**
     * Upper or left location on the middle of the link.
     */
    @objid ("91c1abfe-1e83-11e2-8cad-001ec947c8cc")
    public static final String MiddleNW = "MiddleNW";

    /**
     * On the link at 1/3 distance from the source
     */
    @objid ("91c1ac04-1e83-11e2-8cad-001ec947c8cc")
    public static final String OnSourceThird = "OnSourceThird";

    /**
     * On the link at 1/3 distance from the target
     */
    @objid ("91c1ac0a-1e83-11e2-8cad-001ec947c8cc")
    public static final String OnTargetThird = "OnTargetThird";

    /**
     * location on the middle of the link.
     */
    @objid ("91c1ac10-1e83-11e2-8cad-001ec947c8cc")
    public static final String OnMiddle = "OnMiddle";

}

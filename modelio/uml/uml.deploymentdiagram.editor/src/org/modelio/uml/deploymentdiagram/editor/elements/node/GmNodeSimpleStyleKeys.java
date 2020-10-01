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

package org.modelio.uml.deploymentdiagram.editor.elements.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.deploymentdiagram.editor.style.DeploymentAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmNode when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("9740838f-55b6-11e2-877f-002564c97630")
public class GmNodeSimpleStyleKeys extends DeploymentAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("1cd60cab-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("NODE_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("1cd60cae-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("NODE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("1cd60cb1-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("NODE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color.
     */
    @objid ("1cd60cb4-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("NODE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("1cd60cb7-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("NODE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("1cd60cba-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("NODE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("1cd60cbd-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("NODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("1cd60cc0-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("NODE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("1cd60cc3-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("NODE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("1cd60cc6-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("NODE_SHOWTAGS", MetaKey.SHOWTAGS);

}

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

package org.modelio.diagram.editor.deployment.elements.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.deployment.style.DeploymentAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmNode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("973bef94-55b6-11e2-877f-002564c97630")
public class GmNodeImageStyleKeys extends DeploymentAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("1cd178ce-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("NODE_REPMODE", MetaKey.REPMODE);

    /**
     * Text font.
     */
    @objid ("1cd178d1-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("NODE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("1cd178d4-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("NODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("1cd178d7-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("NODE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("1cd178da-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("NODE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("1cd178dd-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("NODE_SHOWTAGS", MetaKey.SHOWTAGS);

}

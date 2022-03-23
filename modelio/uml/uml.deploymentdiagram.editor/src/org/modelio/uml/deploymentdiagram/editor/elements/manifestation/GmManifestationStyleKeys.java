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
package org.modelio.uml.deploymentdiagram.editor.elements.manifestation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.deploymentdiagram.editor.style.DeploymentAbstractStyleKeyProvider;

/**
 * Style key provider for {@link GmManifestation}.
 */
@objid ("97314153-55b6-11e2-877f-002564c97630")
public class GmManifestationStyleKeys extends DeploymentAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("1cf4912b-55c2-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("MANIFESTATION_ROUTINGMODE",
                MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("1cf4912e-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("MANIFESTATION_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("1cf49131-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("MANIFESTATION_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line corners radius
     */
    @objid ("1cf49134-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("MANIFESTATION_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("1cf49137-55c2-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("MANIFESTATION_DRAWLINEBRIDGES",
                MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("1cf4913a-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("MANIFESTATION_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("1cf4913d-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("MANIFESTATION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("1cf49140-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("MANIFESTATION_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("1cf49143-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("MANIFESTATION_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("1cf49146-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("MANIFESTATION_LINEPATTERN",
                MetaKey.LINEPATTERN);

}

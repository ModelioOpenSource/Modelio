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

package org.modelio.diagram.elements.common.genericlink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmGenericLink}.
 */
@objid ("eae4af5f-061b-4ee0-a404-16acdba18ba7")
public class GmGenericLinkStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("c7189307-ce7a-4847-acd0-7797feb2c1df")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("GENERICLINK_ROUTINGMODE", MetaKey.CONNECTIONROUTER);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("ce2b71d1-ffdd-49da-81fb-0c0cedfbe858")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("GENERICLINK_DRAWLINEBRIDGES", MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("2e917c6c-02e5-4e06-963d-1ac69fee2d4e")
    public static final StyleKey FONT = createStyleKey("GENERICLINK_FONT", MetaKey.FONT);

    /**
     * Line color
     */
    @objid ("e3018125-db56-46a4-814c-e348d96c78de")
    public static final StyleKey LINECOLOR = createStyleKey("GENERICLINK_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("cee19247-d896-47bf-bfb1-d857ca6cb8ed")
    public static final StyleKey LINEWIDTH = createStyleKey("GENERICLINK_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line pattern
     */
    @objid ("d6c5a47e-aecc-4e6d-bf7e-c61249d0f3eb")
    public static final StyleKey LINEPATTERN = createStyleKey("GENERICLINK_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Line corners radius
     */
    @objid ("658cc5fd-6b69-41d5-8aed-6866e5079012")
    public static final StyleKey LINERADIUS = createStyleKey("GENERICLINK_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Display name
     */
    @objid ("222ae642-5dc8-4d3a-a627-2a1c8acf862c")
    public static final StyleKey SHOWLABEL = createStyleKey("GENERICLINK_SHOWLABEL", MetaKey.SHOWLABEL);

    /**
     * Stereotype display mode.
     */
    @objid ("769f6776-de1d-4401-82a3-0ee9b03aa8c4")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("GENERICLINK_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("61a1361e-cd9e-44ed-a305-4ef448d6f0f6")
    public static final StyleKey SHOWTAGS = createStyleKey("GENERICLINK_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Text color.
     */
    @objid ("61646665-aaf3-4804-98c3-7661e1dd86d0")
    public static final StyleKey TEXTCOLOR = createStyleKey("GENERICLINK_TEXTCOLOR", MetaKey.TEXTCOLOR);

}

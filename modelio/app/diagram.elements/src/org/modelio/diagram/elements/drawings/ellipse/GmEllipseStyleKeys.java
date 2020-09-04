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

package org.modelio.diagram.elements.drawings.ellipse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.elements.drawings.core.VAlign;
import org.modelio.diagram.elements.drawings.rectangle.GmRectangleDrawing;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmRectangleDrawing}.
 */
@objid ("d2cc5186-f412-49f4-abeb-053bb0eab0a0")
public class GmEllipseStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Fill color
     */
    @objid ("96de2426-fbbe-4f9c-8c9e-59df4095da43")
    public static final StyleKey FILLCOLOR = createStyleKey("ELLIPSE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode
     */
    @objid ("93c29ef4-f2f3-424c-b3f0-68c0c2bc42ef")
    public static final StyleKey FILLMODE = createStyleKey("ELLIPSE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color
     */
    @objid ("e6081248-206b-4b9b-b8f0-ae01771fdec1")
    public static final StyleKey LINECOLOR = createStyleKey("ELLIPSE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("12b35867-65fb-4c9e-8643-5480f237325c")
    public static final StyleKey LINEWIDTH = createStyleKey("ELLIPSE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line style
     */
    @objid ("f9faf11a-e400-43cc-bff4-02e745f1e31e")
    public static final StyleKey LINEPATTERN = createStyleKey("ELLIPSE_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Fill mode
     */
    @objid ("5d46965a-c84f-4abc-b09c-875c9ab80dd9")
    public static final StyleKey FILLALPHA = createStyleKey("ELLIPSE_FILLALPHA", MetaKey.FILLALPHA);

    /**
     * Ellipse label style.
     */
    @objid ("32ebaa31-6636-4fd4-8b55-2a306296f19c")
    public static final class Label extends ElementsAbstractStyleKeyProvider {
        /**
         * Text font
         */
        @objid ("5e30e429-2ff6-4c43-8196-33d9c24b6936")
        public static final StyleKey FONT = createStyleKey("ELLIPSE_FONT", MetaKey.FONT);

        /**
         * Text color
         */
        @objid ("44f1e55f-8125-41c1-ba4b-b2ac47fa292f")
        public static final StyleKey TEXTCOLOR = createStyleKey("ELLIPSE_TEXTCOLOR", MetaKey.TEXTCOLOR);

        /**
         * Label vertical alignment
         */
        @objid ("e9ce6899-a53d-4003-9f66-132f06143bdf")
        public static final StyleKey VALIGN = createStyleKey("ELLIPSE_VALIGN", VAlign.class);

        /**
         * Label horizontal alignment
         */
        @objid ("e70a7b93-8e0e-40b3-b2f6-8520562666c3")
        public static final StyleKey HALIGN = createStyleKey("ELLIPSE_HALIGN", HAlign.class);

        /**
         * Label text horizontal alignment.
         */
        @objid ("80ba9b08-17b3-4225-ad65-cf7d75e66dcb")
        public static final StyleKey TEXTALIGN = createStyleKey("ELLIPSE_TEXTALIGN", HAlign.class);

    }

}

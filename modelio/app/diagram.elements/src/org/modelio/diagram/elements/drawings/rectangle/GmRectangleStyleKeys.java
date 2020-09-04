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

package org.modelio.diagram.elements.drawings.rectangle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.elements.drawings.core.VAlign;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmRectangleDrawing}.
 */
@objid ("97bec59b-8f16-409d-ace4-d135daa3ec08")
public class GmRectangleStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Fill color
     */
    @objid ("978457df-97be-49fc-b334-40cf6ef84d29")
    public static final StyleKey FILLCOLOR = createStyleKey("RECTANGLE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode
     */
    @objid ("3e7ea59e-fbbd-47dc-afa7-eb4cc539b366")
    public static final StyleKey FILLMODE = createStyleKey("RECTANGLE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color
     */
    @objid ("57dcf266-394f-488d-b705-b6e3396d7f0a")
    public static final StyleKey LINECOLOR = createStyleKey("RECTANGLE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("03604435-a7d5-4e5e-a4bb-470a62a8e469")
    public static final StyleKey LINEWIDTH = createStyleKey("RECTANGLE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line style
     */
    @objid ("2260fadc-c49c-46f3-b963-d319d71ee64d")
    public static final StyleKey LINEPATTERN = createStyleKey("RECTANGLE_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Fill mode
     */
    @objid ("593c6693-ad86-4c49-ba05-ba8b16fa8aec")
    public static final StyleKey FILLALPHA = createStyleKey("RECTANGLE_FILLALPHA", MetaKey.FILLALPHA);

    /**
     * Rounded rectangle radius
     */
    @objid ("9fe39d5c-2d8d-43e3-a256-cdf8ea086906")
    public static final StyleKey LINERADIUS = createStyleKey("RECTANGLE_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Label style keys
     */
    @objid ("dd66715a-b11d-4f23-89f3-a083e7027fa4")
    public static final class Label extends ElementsAbstractStyleKeyProvider {
        /**
         * Text font
         */
        @objid ("28349603-ff18-4cf8-ab58-ebd145bb5857")
        public static final StyleKey FONT = createStyleKey("RECTANGLE_FONT", MetaKey.FONT);

        /**
         * Text color
         */
        @objid ("ab242206-10b5-4962-aa7d-7f420519535a")
        public static final StyleKey TEXTCOLOR = createStyleKey("RECTANGLE_TEXTCOLOR", MetaKey.TEXTCOLOR);

        /**
         * Text horizontal alignment
         */
        @objid ("8677c641-ccd1-42fd-9c22-2fb20b383d6c")
        public static final StyleKey HALIGN = createStyleKey("RECTANGLE_HALIGN", HAlign.class);

        /**
         * Text vertical alignment
         */
        @objid ("cb8ff87b-a00d-4a9c-a200-d70d8d2fb34f")
        public static final StyleKey VALIGN = createStyleKey("RECTANGLE_VALIGN", VAlign.class);

        /**
         * Label text horizontal alignment.
         */
        @objid ("05cde5e9-4ee7-4a11-ba7f-a50aaf61dcde")
        public static final StyleKey TEXTALIGN = createStyleKey("RECTANGLE_TEXTALIGN", HAlign.class);

    }

}

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

package org.modelio.diagram.elements.drawings.text;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmTextDrawing}.
 */
@objid ("01fb52fb-0982-4daf-8bf0-2d4220abf827")
public class GmTextStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * Text font
     */
    @objid ("734cb2b4-8481-4ae4-bde1-86363f2d088a")
    public static final StyleKey FONT = createStyleKey("DRAWTEXT_FONT", MetaKey.FONT);

    /**
     * Text alignment.
     */
    @objid ("9cbb3547-a1cc-487e-9fc8-5f17ba17aaec")
    public static final StyleKey ALIGNMENT = createStyleKey("DRAWTEXT_ALIGNMENT", HAlign.class);

    /**
     * Text color
     */
    @objid ("33fe9e6b-8f91-4e3a-a818-c34aefb28f6a")
    public static final StyleKey TEXTCOLOR = createStyleKey("DRAWTEXT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("69c03e4e-2d03-45d4-b407-35c8fe28d988")
    private static final class Unused {
        /**
         * Fill color
         */
        @objid ("ea946f36-fe5f-4eb2-afdf-03f0e11acafd")
        public static final StyleKey FILLCOLOR = createStyleKey("DRAWTEXT_FILLCOLOR", MetaKey.FILLCOLOR);

        /**
         * Fill mode
         */
        @objid ("5367a62c-9390-44d5-918a-5e9c18bf524d")
        public static final StyleKey FILLMODE = createStyleKey("DRAWTEXT_FILLMODE", MetaKey.FILLMODE);

        /**
         * Line color
         */
        @objid ("3ee6cf53-a546-4297-8203-d1aad3301dbd")
        public static final StyleKey LINECOLOR = createStyleKey("DRAWTEXT_LINECOLOR", MetaKey.LINECOLOR);

        /**
         * Line width
         */
        @objid ("e3af295c-dc6a-4308-95b4-318507462ede")
        public static final StyleKey LINEWIDTH = createStyleKey("DRAWTEXT_LINEWIDTH", MetaKey.LINEWIDTH);

        /**
         * Line style
         */
        @objid ("79a22e78-adb5-4780-9417-5c05de62b4a6")
        public static final StyleKey LINEPATTERN = createStyleKey("DRAWTEXT_LINEPATTERN", MetaKey.LINEPATTERN);

        /**
         * Fill mode
         */
        @objid ("22f5f082-5e17-49f5-a941-52864246fb45")
        public static final StyleKey FILLALPHA = createStyleKey("DRAWTEXT_FILLALPHA", MetaKey.FILLALPHA);

        /**
         * Rounded rectangle radius
         */
        @objid ("fea2b624-cc75-4bd1-8ec5-9f13894a5a8f")
        public static final StyleKey LINERADIUS = createStyleKey("DRAWTEXT_LINERADIUS", MetaKey.LINERADIUS);

    }

}

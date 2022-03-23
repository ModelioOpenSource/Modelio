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
package org.modelio.diagram.elements.drawings.line;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmLineDrawing}.
 */
@objid ("6d6316c2-309c-44ad-bf0b-4466e46d091a")
public class GmLineStyleKeys extends ElementsAbstractStyleKeyProvider {
    @objid ("74e94e4b-c3da-4158-b89f-a8d389331858")
    static final StyleKey CONNECTIONROUTER = createStyleKey("DRAWLINE_CONNECTIONROUTER",
                                                                MetaKey.CONNECTIONROUTER);

    @objid ("81e3a096-0b74-4af7-aa99-48964e0060d7")
    static final StyleKey LINECOLOR = createStyleKey("DRAWLINE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("0ed73cb8-bcb7-4ca3-aaf4-424310424c75")
    static final StyleKey LINEWIDTH = createStyleKey("DRAWLINE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("0102676f-9eb4-4425-b96d-65adb81dd349")
    static final StyleKey LINERADIUS = createStyleKey("DRAWLINE_LINERADIUS", MetaKey.LINERADIUS);

    @objid ("5a1425a8-4be3-488e-b45b-dfbc8a3fdba5")
    static final StyleKey DRAWLINEBRIDGES = createStyleKey("DRAWLINE_DRAWLINEBRIDGES", MetaKey.DRAWLINEBRIDGES);

    @objid ("857bf848-188b-4aed-b820-13f6a1b1d030")
    static final StyleKey LINEPATTERN = createStyleKey("DRAWLINE_LINEPATTERN", MetaKey.LINEPATTERN);

    /**
     * Label style keys
     * <p>
     * Not (yet?) used.
     */
    @objid ("f45bdb30-5f7b-4cb2-af40-97e233116f94")
    @Deprecated
    private static final class Label {
        /**
         * Text font
         */
        @objid ("3e7a3e45-d548-43b0-979a-54363f329283")
        public static final StyleKey FONT = createStyleKey("DRAWLINE_FONT", MetaKey.FONT);

        /**
         * Text color
         */
        @objid ("1047d9ea-b6c3-455c-9790-1cf677e02da8")
        public static final StyleKey TEXTCOLOR = createStyleKey("DRAWLINE_TEXTCOLOR", MetaKey.TEXTCOLOR);

        /**
         * Label text horizontal alignment.
         */
        @objid ("ee5c59fe-edd1-494d-9a14-5b5f4f9e9f4e")
        public static final StyleKey TEXTALIGN = createStyleKey("DRAWLINE_TEXTALIGN", HAlign.class);

    }

    /**
     * Source side decoration
     * 
     * static final StyleKey LINEWIDTH = createStyleKey("DRAWLINEDECO_SOURCE_LINEWIDTH", MetaKey.LINEWIDTH);
     */
    @objid ("5def1fd9-debc-47ee-9d74-b55383a1e0f0")
    @SuppressWarnings("hiding")
    public static final class SourceDeco extends ElementsAbstractStyleKeyProvider {
        @objid ("73c05337-f122-4c60-8d96-15d9f64a24ac")
        static final StyleKey KIND = createStyleKey("DRAWLINEDECO_SOURCE_KIND", LineDecoration.class);

        @objid ("ee37e82a-5074-4702-9aa5-651a36524de2")
        static final StyleKey LINECOLOR = createStyleKey("DRAWLINEDECO_SOURCE_LINECOLOR", MetaKey.LINECOLOR);

        @objid ("d9dff7a3-d980-4203-b734-564804ac4a91")
        static final StyleKey FILLCOLOR = createStyleKey("DRAWLINEDECO_SOURCE_FILLCOLOR", MetaKey.FILLCOLOR);

        @objid ("9aad167d-539a-43f0-bb0b-6ecb82b2688a")
        static final StyleKey FILLMODE = createStyleKey("DRAWLINEDECO_SOURCE_FILLMODE", MetaKey.FILLMODE);

        @objid ("af164216-923d-44d9-8d48-b1a1375aecba")
        static final StyleKey LINEPATTERN = createStyleKey("DRAWLINEDECO_SOURCE_LINEPATTERN", MetaKey.LINEPATTERN);

        @objid ("7900261d-3d21-47b2-aa42-380a71761333")
        static final StyleKey SCALEX = createStyleKey("DRAWLINEDECO_SOURCE_SCALEX", Integer.class);

        @objid ("368a1669-b8b6-4a10-94d9-60b0d2ef1250")
        static final StyleKey SCALEY = createStyleKey("DRAWLINEDECO_SOURCE_SCALEY", Integer.class);

    }

    /**
     * Target side decoration
     * 
     * static final StyleKey LINEWIDTH = createStyleKey("DRAWLINEDECO_TARGET_LINEWIDTH", MetaKey.LINEWIDTH);
     */
    @objid ("f081c49a-fa2d-4e79-8c39-1896f28ba14d")
    @SuppressWarnings("hiding")
    public static final class TargetDeco extends ElementsAbstractStyleKeyProvider {
        @objid ("a4d81fa3-9543-4839-b3eb-185e182da488")
        static final StyleKey KIND = createStyleKey("DRAWLINEDECO_TARGET_KIND", LineDecoration.class);

        @objid ("615e3482-dcc4-449c-8bc6-8d62a1b4aa64")
        static final StyleKey LINECOLOR = createStyleKey("DRAWLINEDECO_TARGET_LINECOLOR", MetaKey.LINECOLOR);

        @objid ("1d00b03c-a226-4197-8e3f-1e9d5016d13d")
        static final StyleKey FILLCOLOR = createStyleKey("DRAWLINEDECO_TARGET_FILLCOLOR", MetaKey.FILLCOLOR);

        @objid ("38ecf65f-0658-4e30-85f8-5880e07dcf36")
        static final StyleKey FILLMODE = createStyleKey("DRAWLINEDECO_TARGET_FILLMODE", MetaKey.FILLMODE);

        @objid ("6f02ab81-5e8c-49a2-829b-8778ea03a90e")
        static final StyleKey LINEPATTERN = createStyleKey("DRAWLINEDECO_TARGET_LINEPATTERN", MetaKey.LINEPATTERN);

        @objid ("9f155e53-d44b-4a64-9eac-773bfdd027db")
        static final StyleKey SCALEX = createStyleKey("DRAWLINEDECO_TARGET_SCALEX", Integer.class);

        @objid ("3ed03f97-1f2f-4d51-ad98-6643b79e81f8")
        static final StyleKey SCALEY = createStyleKey("DRAWLINEDECO_TARGET_SCALEY", Integer.class);

    }

}

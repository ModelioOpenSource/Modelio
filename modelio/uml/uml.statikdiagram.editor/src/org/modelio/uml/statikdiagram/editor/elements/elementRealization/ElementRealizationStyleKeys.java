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
package org.modelio.uml.statikdiagram.editor.elements.elementRealization;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style key provider for {@link GmElementRealization}.
 */
@objid ("22b13070-f4e1-4847-a989-3e54d45a0980")
public class ElementRealizationStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("e3a7cbf9-3ef3-4032-aab1-b9a14e352d93")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("ELEMENTREALIZATION_CONNECTIONROUTER",
                                                                       MetaKey.CONNECTIONROUTER);

    /**
     * Fill color.
     */
    @objid ("13c7c565-264f-4ec4-a04e-be4000b428dd")
    public static final StyleKey FILLCOLOR = createStyleKey("ELEMENTREALIZATION_FILLCOLOR",
                                                                MetaKey.FILLCOLOR);

    /**
     * Line color.
     */
    @objid ("64ded3f5-9caf-494e-9f8d-c309432117a7")
    public static final StyleKey LINECOLOR = createStyleKey("ELEMENTREALIZATION_LINECOLOR",
                                                                MetaKey.LINECOLOR);

    /**
     * Line pattern.
     */
    @objid ("8523d468-ef3e-436f-9bd1-f60d4739fe7e")
    public static final StyleKey LINEPATTERN = createStyleKey("ELEMENTREALIZATION_LINEPATTERN",
                                                                  MetaKey.LINEPATTERN);

    /**
     * Line corners radius
     */
    @objid ("4be9d5a5-9a82-4b64-a479-39a2c985a34a")
    public static final StyleKey LINERADIUS = createStyleKey("ELEMENTREALIZATION_LINERADIUS",
                                                                 MetaKey.LINERADIUS);

    /**
     * Line width.
     */
    @objid ("6c9055d2-b59c-48e5-97cf-814759ef39f3")
    public static final StyleKey LINEWIDTH = createStyleKey("ELEMENTREALIZATION_LINEWIDTH",
                                                                MetaKey.LINEWIDTH);

    /**
     * Draw bridge where vertical segments cross horizontal ones.
     */
    @objid ("f578c376-410c-45e4-b959-c089b42f2bca")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("ELEMENTREALIZATION_DRAWLINEBRIDGES",
                                                                      MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("3044864d-03f1-44ab-8fce-1a42fb60f2c0")
    public static final StyleKey FONT = createStyleKey("ELEMENTREALIZATION_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("733d25e3-1e9e-4f83-afe3-86fc0199f6de")
    public static final StyleKey TEXTCOLOR = createStyleKey("ELEMENTREALIZATION_TEXTCOLOR",
                                                                MetaKey.TEXTCOLOR);

    /**
     * Display label: {@link Boolean}.
     */
    @objid ("4f41880e-b9d1-48a2-b28d-3506b0da5ebe")
    final StyleKey SHOWLABEL = createStyleKey("ELEMENTREALIZATION_SHOWLABEL", MetaKey.SHOWLABEL);

    /**
     * Stereotype mode: none, icon, text, text+icon
     */
    @objid ("1caec5ed-5e41-467c-ae9b-2be8ccca6057")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ELEMENTREALIZATION_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tags: {@link Boolean}.
     */
    @objid ("bf089149-ffb4-42b7-b76d-f6bae2920513")
    public static final StyleKey SHOWTAGS = createStyleKey("ELEMENTREALIZATION_SHOWTAGS", MetaKey.SHOWTAGS);

}

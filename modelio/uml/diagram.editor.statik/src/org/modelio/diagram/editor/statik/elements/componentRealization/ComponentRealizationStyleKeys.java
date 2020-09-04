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

package org.modelio.diagram.editor.statik.elements.componentRealization;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmComponentRealization}.
 */
@objid ("74aa00b9-cbd9-4c66-aad7-2de6ea305868")
public class ComponentRealizationStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Connection routing mode.
     */
    @objid ("931a61e6-0399-407c-918c-f361be5fbaa2")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("COMPONENTREALIZATION_CONNECTIONROUTER",
                                                                   MetaKey.CONNECTIONROUTER);

    /**
     * Fill color.
     */
    @objid ("b48b5d17-356d-4ef4-a283-973c44b3bd33")
    public static final StyleKey FILLCOLOR = createStyleKey("COMPONENTREALIZATION_FILLCOLOR",
                                                            MetaKey.FILLCOLOR);

    /**
     * Line color.
     */
    @objid ("4c113766-81ca-418c-bb9e-c2ea3a5feee4")
    public static final StyleKey LINECOLOR = createStyleKey("COMPONENTREALIZATION_LINECOLOR",
                                                            MetaKey.LINECOLOR);

    /**
     * Line pattern.
     */
    @objid ("f6ead77d-7135-4393-b9c3-c9e5d87217e9")
    public static final StyleKey LINEPATTERN = createStyleKey("COMPONENTREALIZATION_LINEPATTERN",
                                                              MetaKey.LINEPATTERN);

    /**
     * Line corners radius
     */
    @objid ("37d8dc28-b5c6-4014-b2c4-bc4e80c71986")
    public static final StyleKey LINERADIUS = createStyleKey("COMPONENTREALIZATION_LINERADIUS",
                                                             MetaKey.LINERADIUS);

    /**
     * Line width.
     */
    @objid ("d96277ae-8cfd-4ca2-83f7-a0142af6d001")
    public static final StyleKey LINEWIDTH = createStyleKey("COMPONENTREALIZATION_LINEWIDTH",
                                                            MetaKey.LINEWIDTH);

    /**
     * Draw bridge where vertical segments cross horizontal ones: {@link Boolean}.
     */
    @objid ("d40a17e9-676d-443a-9a42-6e67d1a00430")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("COMPONENTREALIZATION_DRAWLINEBRIDGES",
                                                                  MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("e7bf91d9-a1a7-4d68-b357-62fb4aedb995")
    public static final StyleKey FONT = createStyleKey("COMPONENTREALIZATION_FONT", MetaKey.FONT);

    /**
     * Display label: {@link Boolean}.
     */
    @objid ("241cfa9d-fcc9-41f0-b319-b96bebd56869")
     final StyleKey SHOWLABEL = createStyleKey("COMPONENTREALIZATION_SHOWLABEL", MetaKey.SHOWLABEL);

    /**
     * Show tags: {@link Boolean}.
     */
    @objid ("7ec330c4-577c-48a6-b2aa-1534eaec510a")
    public static final StyleKey SHOWTAGS = createStyleKey("COMPONENTREALIZATION_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Stereotype mode: none, icon, text, text+icon
     */
    @objid ("971c9348-e57f-446d-9691-88edf29f7406")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("COMPONENTREALIZATION_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Text color.
     */
    @objid ("ab89b251-aaee-4b50-af6b-bb900f61726a")
    public static final StyleKey TEXTCOLOR = createStyleKey("COMPONENTREALIZATION_TEXTCOLOR",
                                                            MetaKey.TEXTCOLOR);

}

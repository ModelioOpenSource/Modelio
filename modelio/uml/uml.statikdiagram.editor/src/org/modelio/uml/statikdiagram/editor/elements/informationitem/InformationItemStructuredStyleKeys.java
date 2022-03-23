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
package org.modelio.uml.statikdiagram.editor.elements.informationitem;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * {@link GmInformationItem} style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("351d1c73-55b7-11e2-877f-002564c97630")
public final class InformationItemStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation model.
     */
    @objid ("a67f2387-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("INFOITEM_REPRES_MODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a67f238a-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("INFOITEM_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a67f238d-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("INFOITEM_FILLMODE", MetaKey.FILLMODE);

    /**
     * Lines color.
     */
    @objid ("a67f2390-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("INFOITEM_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a67f2393-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("INFOITEM_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a67f2396-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("INFOITEM_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a680aa0b-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("INFOITEM_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a680aa0e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("INFOITEM_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a680aa11-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("INFOITEM_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a680aa14-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("INFOITEM_SHOWTAGS", MetaKey.SHOWTAGS);

}

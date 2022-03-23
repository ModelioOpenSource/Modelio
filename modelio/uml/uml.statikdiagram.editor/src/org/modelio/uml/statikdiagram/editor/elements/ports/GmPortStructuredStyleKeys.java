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
package org.modelio.uml.statikdiagram.editor.elements.ports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style keys for {@link GmPort}.
 * 
 * @author cmarin
 */
@objid ("36482f13-55b7-11e2-877f-002564c97630")
public class GmPortStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a757d02c-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("PORT_REPMODE", MetaKey.REPMODE);

    @objid ("a757d02e-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("PORT_FILL_COLOR", MetaKey.FILLCOLOR);

    @objid ("a757d030-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("PORT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a757d032-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("PORT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a75956ca-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("PORT_FONT", MetaKey.FONT);

    @objid ("a75956cc-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("PORT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a75956ce-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("PORT_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("a75956d0-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("PORT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("a75956d2-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("PORT_SHOWLABEL", MetaKey.SHOWLABEL);

}

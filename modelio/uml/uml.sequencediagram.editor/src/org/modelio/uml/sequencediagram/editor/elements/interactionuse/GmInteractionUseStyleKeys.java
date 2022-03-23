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
package org.modelio.uml.sequencediagram.editor.elements.interactionuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.sequencediagram.editor.style.SequenceAbstractStyleKeyProvider;

/**
 * Style keys for InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d91f0080-55b6-11e2-877f-002564c97630")
public class GmInteractionUseStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("4f990b6b-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("INTERACTIONUSE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("4f990b6d-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("INTERACTIONUSE_FILLMODE", MetaKey.FILLMODE);

    @objid ("4f990b6f-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("INTERACTIONUSE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("4f990b71-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("INTERACTIONUSE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("4f990b73-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("INTERACTIONUSE_FONT", MetaKey.FONT);

    @objid ("4f990b75-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("INTERACTIONUSE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("4f990b77-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("INTERACTIONUSE_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("4f990b79-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("INTERACTIONUSE_SHOWTAGS", MetaKey.SHOWTAGS);

}

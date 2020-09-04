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

package org.modelio.diagram.editor.sequence.elements.interactionuse.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.style.SequenceAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style keys for InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d91a6c76-55b6-11e2-877f-002564c97630")
public class GmGateOnInteractionUseSimpleStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("503635cd-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("GATE_REPMODE", MetaKey.REPMODE);

    @objid ("5037bc6a-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("GATE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("5037bc6c-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("GATE_FILLMODE", MetaKey.FILLMODE);

    @objid ("5037bc6e-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("GATE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("5037bc70-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("GATE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("5037bc72-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("GATE_FONT", MetaKey.FONT);

    @objid ("5037bc74-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("GATE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("5037bc76-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("GATE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("5037bc78-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("GATE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("5037bc7a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("GATE_SHOWLABEL", MetaKey.SHOWLABEL);

}

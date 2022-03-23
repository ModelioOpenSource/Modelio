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
package org.modelio.uml.sequencediagram.editor.elements.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.sequencediagram.editor.style.SequenceAbstractStyleKeyProvider;

/**
 * Style keys for InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d8fa616c-55b6-11e2-877f-002564c97630")
public class GmGateImageStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("501dcbcb-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("GATE_REPMODE", MetaKey.REPMODE);

    @objid ("501dcbcd-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("GATE_FONT", MetaKey.FONT);

    @objid ("501dcbcf-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("GATE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("501dcbd1-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("GATE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("501dcbd3-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("GATE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("501dcbd5-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("GATE_SHOWLABEL", MetaKey.SHOWLABEL);

}

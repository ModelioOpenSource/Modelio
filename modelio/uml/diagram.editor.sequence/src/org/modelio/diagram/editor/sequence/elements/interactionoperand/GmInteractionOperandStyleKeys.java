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

package org.modelio.diagram.editor.sequence.elements.interactionoperand;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.style.SequenceAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style keys for InteractionOperand.
 * 
 * @author fpoyer
 */
@objid ("d9069657-55b6-11e2-877f-002564c97630")
public class GmInteractionOperandStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("4fb482ab-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("INTERACTIONOPERAND_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("4fb482ad-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("INTERACTIONOPERAND_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("4fb482af-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("INTERACTIONOPERAND_FONT", MetaKey.FONT);

    @objid ("4fb482b1-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("INTERACTIONOPERAND_TEXTCOLOR", MetaKey.TEXTCOLOR);

}

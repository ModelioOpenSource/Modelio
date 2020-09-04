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

package org.modelio.diagram.editor.sequence.elements.combinedfragment;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.style.SequenceAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style keys for InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d8c806a4-55b6-11e2-877f-002564c97630")
public class GmCombinedFragmentStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("4ff3124a-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("COMBINEDFRAGMENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("4ff3124c-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("COMBINEDFRAGMENT_FILLMODE", MetaKey.FILLMODE);

    @objid ("4ff3124e-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("COMBINEDFRAGMENT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("4ff31250-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("COMBINEDFRAGMENT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("4ff31252-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("COMBINEDFRAGMENT_FONT", MetaKey.FONT);

    @objid ("4ff31254-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("COMBINEDFRAGMENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("4ff31256-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("COMBINEDFRAGMENT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("4ff31258-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("COMBINEDFRAGMENT_SHOWTAGS", MetaKey.SHOWTAGS);

}

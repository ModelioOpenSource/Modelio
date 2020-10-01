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
@objid ("a8fd44ed-aa6c-4a43-8972-df25d2803a68")
public class GmGateUserImageStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("167d771b-ed72-4920-aba2-877820c2f4e1")
     static final StyleKey REPMODE = createStyleKey("GATE_REPMODE", MetaKey.REPMODE);

    @objid ("6e907ba7-c292-4090-9834-db657f684e11")
     static final StyleKey FONT = createStyleKey("GATE_FONT", MetaKey.FONT);

    @objid ("3705a43c-9864-4be4-b877-367721f96b30")
     static final StyleKey TEXTCOLOR = createStyleKey("GATE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("980d1736-89a5-423e-9e1b-b5dca2c94db6")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("GATE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("1ca756da-bb1a-4977-96a8-f444523a126d")
     static final StyleKey SHOWTAGS = createStyleKey("GATE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("91d31b09-93be-48da-a23c-9d67ab21e4aa")
     static final StyleKey SHOWLABEL = createStyleKey("GATE_SHOWLABEL", MetaKey.SHOWLABEL);

}

/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.lifeline;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.style.SequenceAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * StyleKey provider for the GmLifeline class.
 */
@objid ("e989021b-e1d9-4603-9c1c-e03c06b2336c")
public class GmLifelineUserImageStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("789b2956-eaf4-4473-8830-26528137c2f0")
     static final StyleKey REPMODE = createStyleKey("LIFELINE_REPMODE", MetaKey.REPMODE);

    @objid ("504c470d-2635-4721-9c62-dd646fa2c251")
     static final StyleKey FONT = createStyleKey("LIFELINE_FONT", MetaKey.FONT);

    @objid ("0fa9e09d-8879-40f8-b2f9-8f43559adcd4")
     static final StyleKey TEXTCOLOR = createStyleKey("LIFELINE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("9c1a0a87-0591-43a2-b3b5-1ae1fed47955")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("LIFELINE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("014511a6-b5d0-4290-a9f2-d56c0bb90fd9")
     static final StyleKey SHOWTAGS = createStyleKey("LIFELINE_SHOWTAGS", MetaKey.SHOWTAGS);

}

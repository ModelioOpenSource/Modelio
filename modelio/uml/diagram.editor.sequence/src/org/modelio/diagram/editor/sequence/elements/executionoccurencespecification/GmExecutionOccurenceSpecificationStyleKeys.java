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

package org.modelio.diagram.editor.sequence.elements.executionoccurencespecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.style.SequenceAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style keys provider for the GmExecutionOccurrenceSpecification class.
 * 
 * @author fpoyer
 */
@objid ("d8deea25-55b6-11e2-877f-002564c97630")
public class GmExecutionOccurenceSpecificationStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("5006e869-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("EXECUTIONOCCURENCESPECIFICATION_FILLCOLOR",
            MetaKey.FILLCOLOR);

    @objid ("5006e86b-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("EXECUTIONOCCURENCESPECIFICATION_FILLMODE",
            MetaKey.FILLMODE);

}

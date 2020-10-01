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

package org.modelio.uml.sequencediagram.editor.elements.executionspecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.sequencediagram.editor.style.SequenceAbstractStyleKeyProvider;

/**
 * StyleKey provider for the GmExecution class.
 */
@objid ("d8ee2c6e-55b6-11e2-877f-002564c97630")
public class GmExecutionSpecificationStructuredStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("501196c9-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("EXECUTIONSPECIFICATION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("501196cb-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("EXECUTIONSPECIFICATION_FILLMODE", MetaKey.FILLMODE);

    @objid ("501196cd-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("EXECUTIONSPECIFICATION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("501196cf-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("EXECUTIONSPECIFICATION_LINEWIDTH", MetaKey.LINEWIDTH);

}

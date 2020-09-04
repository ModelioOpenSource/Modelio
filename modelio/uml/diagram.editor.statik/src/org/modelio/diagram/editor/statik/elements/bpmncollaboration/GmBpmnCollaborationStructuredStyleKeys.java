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

package org.modelio.diagram.editor.statik.elements.bpmncollaboration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnCollaboration when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("57806edd-d28c-4e74-839d-0f4d1a13ce03")
public class GmBpmnCollaborationStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("ca5ac254-a13c-415b-8349-bc2c6994b86b")
     static final StyleKey REPMODE = createStyleKey("BPMNCOLLABORATION_REPMODE", MetaKey.REPMODE);

    @objid ("a8166efc-b45b-4021-86b2-cd7be355014c")
     static final StyleKey FILLCOLOR = createStyleKey("BPMNCOLLABORATION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("8414d8c2-03cb-4a3a-b79d-b49ab69dcc55")
     static final StyleKey FILLMODE = createStyleKey("BPMNCOLLABORATION_FILLMODE", MetaKey.FILLMODE);

    @objid ("c9ae1805-ffa9-4112-a00d-3c840d95a068")
     static final StyleKey LINECOLOR = createStyleKey("BPMNCOLLABORATION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d4e28fa8-0cf4-45e1-97b7-9efe421d20f8")
     static final StyleKey LINEWIDTH = createStyleKey("BPMNCOLLABORATION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("53cff955-e6f2-4966-b9d5-b210ed419f4f")
     static final StyleKey FONT = createStyleKey("BPMNCOLLABORATION_FONT", MetaKey.FONT);

    @objid ("c874a63e-49a2-4caa-8660-17180705dac6")
     static final StyleKey TEXTCOLOR = createStyleKey("BPMNCOLLABORATION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("2fd65f19-94ed-4209-afad-70a0acde7634")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNCOLLABORATION_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("7bdfc09e-406e-4cd2-a6c7-6778441d4f22")
     static final StyleKey SHOWTAGS = createStyleKey("BPMNCOLLABORATION_SHOWTAGS", MetaKey.SHOWTAGS);

}

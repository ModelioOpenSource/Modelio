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

package org.modelio.diagram.editor.statik.elements.bpmnbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link BpmnBehavior} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("4d676fe3-2ab6-4230-80a5-c3818cee6fb4")
public class GmBpmnBehaviorUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("08c1cf62-415d-4c55-a6cf-527f4646651e")
     static final StyleKey REPMODE = GmBpmnBehaviorStructuredStyleKeys.REPMODE;

    @objid ("5c1d4678-94e3-4c2f-ae1b-f29ce45cce3a")
     static final StyleKey FONT = GmBpmnBehaviorStructuredStyleKeys.FONT;

    @objid ("4649890a-dcd9-4fa6-880d-871a3ed66262")
     static final StyleKey TEXTCOLOR = GmBpmnBehaviorStructuredStyleKeys.TEXTCOLOR;

    @objid ("47c799f6-41aa-42fc-9fa8-ec28655be4aa")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnBehaviorStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("630d8e46-f13b-4684-9f12-2412407db808")
     static final StyleKey SHOWTAGS = GmBpmnBehaviorStructuredStyleKeys.SHOWTAGS;

}

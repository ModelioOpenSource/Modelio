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

package org.modelio.diagram.editor.bpmn.elements.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnBusinessRuleTask when its representation mode is RepresentationMode.IMAGE
 */
@objid ("1a9772d9-d2df-4c75-bfc5-9f857349ef54")
public class GmBpmnTaskUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("97111a9e-0bf0-4682-8610-b88e868b7974")
    public static final StyleKey REPMODE = createStyleKey("TASK_REPMODE", MetaKey.REPMODE);

    @objid ("d53e1b23-41f3-46b2-9a0b-ffd89c806018")
    public static final StyleKey FONT = createStyleKey("TASK_FONT", MetaKey.FONT);

    @objid ("f3589ed0-f1fd-4e09-859f-6f33b4224151")
    public static final StyleKey TEXTCOLOR = createStyleKey("TASK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a785fd38-12fb-4a21-b230-b64a82167afa")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TASK_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("a6e9fd63-df44-4fb0-af6a-fdae4d987570")
    public static final StyleKey SHOWTAGS = createStyleKey("TASK_SHOWTAGS", MetaKey.SHOWTAGS);

}

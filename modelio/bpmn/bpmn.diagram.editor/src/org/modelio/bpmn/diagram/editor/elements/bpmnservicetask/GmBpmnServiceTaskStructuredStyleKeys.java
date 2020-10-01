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

package org.modelio.bpmn.diagram.editor.elements.bpmnservicetask;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("8400fe97-90e0-46ce-a025-6b2674948275")
public class GmBpmnServiceTaskStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("c4489438-c9cb-4c62-800c-63c65c43ad52")
    public static final StyleKey REPMODE = createStyleKey("TASK_REPMODE", MetaKey.REPMODE);

    @objid ("8889af7d-87fb-42e9-8d0c-89e959e91dae")
    public static final StyleKey FILLCOLOR = createStyleKey("TASK_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("e570d24a-9cde-481d-ab27-22cbfa9e7488")
    public static final StyleKey FILLMODE = createStyleKey("TASK_FILLMODE", MetaKey.FILLMODE);

    @objid ("b8e504fa-fbe3-472a-a4a9-ae5ddf3364f5")
    public static final StyleKey LINECOLOR = createStyleKey("TASK_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("c1779278-88aa-4738-a586-3a378f11a22e")
    public static final StyleKey LINEWIDTH = createStyleKey("TASK_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("53f9877a-4664-467f-a454-bd3a7e80af19")
    public static final StyleKey FONT = createStyleKey("TASK_FONT", MetaKey.FONT);

    @objid ("8a7361e9-253e-4c10-9c43-b06c382c6746")
    public static final StyleKey TEXTCOLOR = createStyleKey("TASK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("c762df46-451c-4af3-be7d-1c2c999209d4")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TASK_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("139f0382-6596-45ae-b73d-c5e60a011c7a")
    public static final StyleKey SHOWTAGS = createStyleKey("TASK_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("ed35156c-e424-44cb-810a-0a184e0ba38e")
    public static final StyleKey SHOWREPRESENTED = createStyleKey("TASK_SHOWREPRESENTED", Boolean.class);

}

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

package org.modelio.diagram.editor.bpmn.elements.bpmnsendtask;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("6a5ad0f1-6131-4bfa-9690-9d0b46045fed")
public class GmBpmnSendTaskStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("a5d5aa68-ad6b-46c1-8535-b666e5910e87")
    public static final StyleKey REPMODE = createStyleKey("TASK_REPMODE", MetaKey.REPMODE);

    @objid ("d0e910e8-e2aa-4018-a995-57f62b7f0701")
    public static final StyleKey FILLCOLOR = createStyleKey("TASK_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("9bc96a7e-88b2-4a3d-b27c-f2875740d0b0")
    public static final StyleKey FILLMODE = createStyleKey("TASK_FILLMODE", MetaKey.FILLMODE);

    @objid ("80e3e0e8-24d6-419c-86a9-b4e2a19edd7f")
    public static final StyleKey LINECOLOR = createStyleKey("TASK_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("4ed7f32a-228e-4635-8d2d-da04c58cd3b2")
    public static final StyleKey LINEWIDTH = createStyleKey("TASK_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("0d72a68b-d902-4ead-af91-f24be894669d")
    public static final StyleKey FONT = createStyleKey("TASK_FONT", MetaKey.FONT);

    @objid ("37c27ac8-226e-4e48-9950-2538a82dccaa")
    public static final StyleKey TEXTCOLOR = createStyleKey("TASK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("4cbd7eaf-6f9b-4ddc-96cd-dc71be4aa527")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TASK_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("14dbf73f-2f27-4849-96c9-73edab21588b")
    public static final StyleKey SHOWTAGS = createStyleKey("TASK_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("b74faaab-4e6c-4064-af7d-fd9295631312")
    public static final StyleKey SHOWREPRESENTED = createStyleKey("TASK_SHOWREPRESENTED", Boolean.class);

}

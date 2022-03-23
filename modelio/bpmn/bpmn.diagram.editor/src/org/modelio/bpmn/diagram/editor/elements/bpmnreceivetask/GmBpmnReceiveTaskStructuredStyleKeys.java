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
package org.modelio.bpmn.diagram.editor.elements.bpmnreceivetask;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.common.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("f38d8242-ad2e-4e9a-967c-484629856878")
public class GmBpmnReceiveTaskStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("1cd6b9c7-7755-4970-91e2-a664b672ad2d")
    public static final StyleKey REPMODE = createStyleKey("TASK_REPMODE", MetaKey.REPMODE);

    @objid ("6861ad4f-64f5-4607-8a69-d608c6780aa0")
    public static final StyleKey FILLCOLOR = createStyleKey("TASK_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("0c1828f9-719d-42c5-bc07-3ef4ec25d2c0")
    public static final StyleKey FILLMODE = createStyleKey("TASK_FILLMODE", MetaKey.FILLMODE);

    @objid ("d773d442-d7c7-4dba-810c-094214b2f8e7")
    public static final StyleKey LINECOLOR = createStyleKey("TASK_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("fe0f63e4-3012-41f9-8e38-4e73a1bad4bf")
    public static final StyleKey LINEWIDTH = createStyleKey("TASK_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("04896bcf-2111-41ad-9dce-19502960527e")
    public static final StyleKey FONT = createStyleKey("TASK_FONT", MetaKey.FONT);

    @objid ("c9d0711d-a686-4b6c-b76c-5950b0695f54")
    public static final StyleKey TEXTCOLOR = createStyleKey("TASK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("87e29237-628d-4fe9-9402-102f990fb0c8")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TASK_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("249433d2-6af2-46f7-94cf-0cc0e967862b")
    public static final StyleKey SHOWTAGS = createStyleKey("TASK_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("235c958e-a4e8-4576-b7d6-b47b860fe99c")
    public static final StyleKey SHOWREPRESENTED = createStyleKey("TASK_SHOWREPRESENTED", Boolean.class);

}

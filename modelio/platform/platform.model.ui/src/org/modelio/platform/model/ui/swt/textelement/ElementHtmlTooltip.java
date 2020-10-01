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

package org.modelio.platform.model.ui.swt.textelement;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.mda.infra.ModuleI18NService;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("37efae21-81ac-4881-b112-24a8e8dfb216")
class ElementHtmlTooltip {
    @objid ("edb419a5-7ec6-4df4-8849-2e5cd14e72ee")
    public static String getHtml(MObject element) {
        final Color background = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND);
        final String bgColor = "#" + Integer.toHexString(background.getRed()) + Integer.toHexString(background.getGreen())
                + Integer.toHexString(background.getBlue());
        
        final Color foreground = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND);
        final String fgColor = "#" + Integer.toHexString(foreground.getRed()) + Integer.toHexString(foreground.getGreen())
                + Integer.toHexString(foreground.getBlue());
        
        final StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        builder.append("<header>");
        builder.append("<meta charset=\"utf-8\" />");
        builder.append("<style>");
        builder.append("body, table { right: 0; text-align: left; valign: text-top; background-color: ");
        builder.append(bgColor);
        builder.append("; color:");
        builder.append(fgColor);
        final FontData fontData = Display.getCurrent().getSystemFont().getFontData()[0];
        builder.append("; font-family: " + fontData.getName() + "; font-size: " + fontData.height + "pt;}");
        builder.append("tr, td {vertical-align: text-top;} ");
        builder.append("h1 {font-size: " + (fontData.height + 1) + "pt;} ");
        builder.append("</style>");
        builder.append("</header>");
        
        builder.append("<body>");
        if (element != null) {
            builder.append("<h1>" + getCompositionPath(element) + "</h1>");
        
            builder.append("<table border=0>");
        
            if (element instanceof NameSpace) {
                builder.append("<tr><td><strong>Visibility:</strong></td>");
                builder.append("<td>" + ((NameSpace)element).getVisibility().toString() + "</td></tr>");
            }
        
            builder.append("<tr><td><strong>Metaclass:</strong></td>");
            builder.append("<td>" + element.getMClass().getName() + "</td></tr>");
        
            if (element instanceof ModelElement) {
                if ((((ModelElement)element).getExtension()).size() != 0) {
                    builder.append("<tr><td><strong>Stereotype:</strong></td><td>");
                    for (Stereotype stereotype : ((ModelElement)element).getExtension()) {
                        builder.append(ModuleI18NService.getLabel(stereotype) + "<br/>");
                    }
                    builder.append("</td></tr>");
                }
            }
        
            builder.append("<tr><td><strong>Identifier:</strong></td>");
            builder.append("<td>" + element.getUuid().toString() + "</td></tr>");
        
            if (element instanceof ModelElement) {
                String desc = ((ModelElement) element).getNoteContent("ModelerModule", "description");
                if (desc != null) {
                    String descHtml = desc.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("\\n" ,"<br/>");
        
                    builder.append("<tr><td><strong>Description:</strong></td>");
                    builder.append("<td><em>");
                    builder.append(descHtml);
                    builder.append("</em></td></tr>");
                }
            }
            builder.append("</table>");
        }
        builder.append("</body></html>");
        return builder.toString();
    }

    @objid ("970cee64-218a-4952-82de-8424f66039c8")
    private static String getCompositionPath(MObject o) {
        final String name = o.getName();
        
        final MObject owner = o.getCompositionOwner();
        if (owner != null) {
            return getCompositionPath(owner) + "." + name;
        }
        return name;
    }

}

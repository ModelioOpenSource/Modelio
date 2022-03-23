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
package org.modelio.platform.model.ui;

import java.io.StringReader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import org.modelio.platform.model.ui.plugin.CoreUi;

@objid ("8046caaa-315d-41c5-aad6-63ae6c8023ca")
public class MimeServices {
    @objid ("59581b8b-31f1-4eb7-afb2-74bd9a5e1278")
    private static final HTMLEditorKit HTML_KIT = new HTMLEditorKit();

    @objid ("a9e8316d-7881-4c86-917e-5e3b6b4c3f5a")
    public static String convert(String s, MimeType from, MimeType to) {
        if (from == MimeType.HTML && to == MimeType.PLAIN) {
            return html2text(s);
        } else {
            // Any other case do nothing
            return s;
        }
        
    }

    @objid ("c89eb0a8-6850-4d5c-b332-29ded6f34b5f")
    public static String html2text(String html) {
        Document doc = HTML_KIT.createDefaultDocument();
        doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        try {
            HTML_KIT.read(new StringReader(html), doc, 0);
            return doc.getText(0, doc.getLength()).trim();
        } catch (Exception e) {
            CoreUi.LOG.debug("'" + html + "' convertion failed.", e);
            return html;
        }
        
    }

    @objid ("c7fac915-20f8-4485-a2d8-667373fe12ef")
    public enum MimeType {
        @objid ("9ea2543b-fe31-424d-8ceb-3a148d98d187")
        PLAIN,
        @objid ("07005868-6fa5-4047-bbb0-c42709072b73")
        HTML;

        @objid ("5e72df72-b33a-43b5-9a26-5bd094c6fb6d")
        public String toEncodingString() {
            switch (this) {
            case HTML:
                return "text/html";
            case PLAIN:
            default:
                return "text/plain";
            }
            
        }

    }

}

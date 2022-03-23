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
package org.modelio.diagram.elements.core.figures.html.flyingsaucer;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.modelio.diagram.elements.core.figures.html.flyingsaucer.impl.GefFsRenderer;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.css.sheet.StylesheetInfo;
import org.xhtmlrenderer.simple.extend.XhtmlNamespaceHandler;

/**
 * HTML text figure using Flying Saucer library and {@link Tidy}.
 * 
 * @author cmarin
 * @see <a href = "https://code.google.com/p/flying-saucer"> Flying Saucer library </a>
 */
@objid ("7da26c3c-775f-41b1-9350-ea17c020d1af")
public class FsHtmlFigure extends GefFsRenderer {
    @objid ("a4b97752-8f24-4641-91ed-ffce538de060")
    private String htmlText;

    /**
     * C'tor
     * @param swtComposite a SWT composite to allow HTML to add SWT controls, not yet used.
     */
    @objid ("dbd77727-6f8b-4202-95a2-a6a29c80d8dd")
    public  FsHtmlFigure(Composite swtComposite) {
        super(swtComposite);
        setOpaque(false);
        
    }

    /**
     * Set the HTML content.
     * @param htmlText the HTML text
     */
    @objid ("649ea6d6-3583-4546-803c-875c8994d3b1")
    public void setHtmlText(String htmlText) {
        if (! Objects.equals(htmlText, this.htmlText)) {
            setDocument(getHtmlDocument(htmlText), null, new NamespaceHandlerExtension());
            
            this.htmlText = htmlText;
        }
        
    }

    /**
     * Redefined to reset the default stylesheets that depend on the Figure font.
     */
    @objid ("26467a6e-4c61-4475-83d2-dff894705a13")
    @Override
    public void setFont(Font f) {
        if (! Objects.equals(f, getFont())) {
            super.setFont(f);
            
            setDocument(getHtmlDocument(this.htmlText), null, new NamespaceHandlerExtension());
        }
        
    }

    /**
     * Tidy and parse the HTML text.
     * @param aHtmlText an HTML text
     * @return the parsed DOM tree or null.
     */
    @objid ("cb1c9155-0a57-4934-91cd-94e4e3bd6992")
    private Document getHtmlDocument(String aHtmlText) {
        if (aHtmlText == null)
            return null;
        
        Tidy t = new Tidy();
        t.setForceOutput(true);
        t.setXHTML(true);
        t.setMakeBare(true);
        t.setMakeClean(true);
        t.setTidyMark(false);
        t.setIndentContent(false);
        t.setJoinStyles(true);
        t.setJoinClasses(true);
        t.setWord2000(true);
        t.setShowWarnings(false);
        t.setQuiet(true);
        
        StringWriter err = new StringWriter();
        StringWriter out = new StringWriter();
        t.setErrout(new PrintWriter(err));
        
        Document doc = t.parseDOM(new StringReader(aHtmlText), out);
        
        if (err.getBuffer().length() > 0)
            DiagramElements.LOG.debug("tidy errors:"+err.toString());
        
        //DiagramElements.LOG.debug("tidy = "+out.toString());
        return doc;
    }

    /**
     * Prepend our own stylesheet to define the default font from the Figure defined font.
     */
    @objid ("e6e7f2c8-14b9-4795-b934-016bc3b06743")
    private final class NamespaceHandlerExtension extends XhtmlNamespaceHandler {
        @objid ("dfdafd97-d0db-44e3-bdf8-0622f1a112be")
        public  NamespaceHandlerExtension() {
            super();
        }

        @objid ("a9a5a2ce-5a70-4ffd-9a8c-acdd0f9a1a3c")
        @Override
        public StylesheetInfo[] getStylesheets(Document doc) {
            List<StylesheetInfo> result = new ArrayList<>();
            
            // Add a default Note style on beginning so that it is the fall-back style.
            result.add(createNoteStyle());
            
            // Add inherited implementation stylesheets
            result.addAll(Arrays.asList(super.getStylesheets(doc)));
            return result.toArray(new StylesheetInfo[result.size()]);
        }

        /**
         * Creates a CSS style sheet from the {@link FsHtmlFigure#getFont() Figure font}.
         * @return the note style sheet.
         */
        @objid ("7e6875d9-2186-4ff2-8f01-599371cea938")
        private StylesheetInfo createNoteStyle() {
            // to try: get the HTML editor style sheet.
            //DiagramElements.getContext().getBundle("edition.html").getEntry("rte/ckeditor/contents.css");
            //FileLocator.find(bundle, path, override)
            //FileLocator.resolve(new URL("platform://edition.html/rte/ckeditor/contents.css"));
            StylesheetInfo info = new StylesheetInfo();
            info.setUri(getNamespace());
            info.setOrigin(StylesheetInfo.USER_AGENT);
            info.setMedia("all");
            info.setType("text/css");
            
            FontData figFont = getFont().getFontData()[0];
            
            Object boldStr = (figFont.getStyle() & SWT.BOLD) == 0 ? "" : "font-weight: bold; ";
            Object italicStr = (figFont.getStyle() & SWT.ITALIC) == 0 ? "" : "font-style: italic; ";
            
            String styleText = String.format("* { font-family: %s; %s %s} body, p, tr, td, li { font-size: %dpt; %s %s}",
                    figFont.getName(),
                    boldStr,
                    italicStr,
                    figFont.getHeight(),
                    boldStr,
                    italicStr);
            
            info.setContent(styleText);
            return info;
        }

    }

}

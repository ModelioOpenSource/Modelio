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

package org.modelio.core.ui.swt;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.ui.CoreColorRegistry;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.vbasic.files.FileUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class interprets a html text to build and set the text and style ranges of a StyledText.
 * 
 * <p>
 * <h2>Usage</h2>
 * </p>
 * <code>
 * StyledText myStyledText  = ...<br/>
 * String     myHtmlText = "&lt;html&gt; &lt;p&gt;my paragraph with &lt;b&gt;a few bold chars&lt;/b>&lt;/p&gt; &lt;/html&gt;"; <br/>
 * </code>
 * 
 * StyledTextHelper.setStyledText(myHtmlText, myStyledText);
 * <p>
 * Only a few HTML tags are supported:
 * <ul>
 * <li>&lt;html&gt; to begin the formatted text.</li>
 * <li>&lt;p&gt; to insert a paragraph.</li>
 * <li>&lt;br&gt; to insert a new line.</li>
 * <li>&lt;b&gt; to switch to bold characters.</li>
 * <li>&lt;i&gt; to switch to italic characters.</li>
 * <li>&lt;u&gt; to switch to underline characters.</li>
 * <li>&lt;s&gt; to switch to strike out characters.</li>
 * <li>&lt;div&gt; to define a zone.</li>
 * <li>&lt;span&gt; to define a zone.</li>
 * </ul>
 * </p>
 * The &lt;p&gt; &lt;span&gt; and &lt;div&gt; tags support some limited CSS styling:<br/>
 * <ul>
 * <li><b>color: #123456;</b> - Set the text color. Only the #XXXXXX hexadecimal syntax is supported for the color</li>
 * <li><b>background-color: #123456;</b> - Set the paragraph background color. Only the #XXXXXX hexadecimal syntax is supported for the color</li>
 * <li><b>border: 1px solid blue;</b> - Set a border around the paragraph. Parameters defining the border are not suported. The rendered border will always be: solid, one pixel and same color as text color.</li>
 * <li><b>font-size:125%;</b> - Change the font size. Font size must be defined as a percentage (no other syntax supported)</li>
 * </ul>
 * 
 * <h2>CSS Style example:</h2>
 * 
 * The string:
 * <code>"&lt;html&gt; &lt;p style="font-size:150%;color:#FF0000;"&gt; red big text &lt;/p&gt; &lt;/html&gt;"</code>
 * will be rendered as:
 * 
 * <p style="font-size:150%;color:#FF0000;">
 * red big text
 * </p>
 */
@objid ("a6e3b7d7-1d87-4b0f-beb0-c67b7bbb868f")
public class StyledTextHelper {
    /**
     * Interprets the potentially HTML text and set the text and style ranges of a StyledText.
     * <p>
     * The text must begin with "&lt;html&gt;" to be interpreted as HTML.
     * 
     * @param htmlText the HTML text.
     * @param widget the StyledText to fill.
     */
    @objid ("33672e7a-0af5-4700-914a-6b53cea9b1a1")
    public static void setStyledText(String htmlText, StyledText widget) {
        if (!htmlText.startsWith("<html>")) {
            widget.setText(htmlText);
            return;
        }
        
        try {
            // XML parsing
            StringReader reader = new StringReader(htmlText);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            HtmlTextContentHandler handler = new HtmlTextContentHandler();
            parser.parse(new InputSource(reader), handler);
        
            // Set the styled text contents
            widget.setText(handler.getText());
            widget.setStyleRanges(handler.getStyleRanges());
        
        } catch (ParserConfigurationException | SAXException | RuntimeException e) {
            CoreUi.LOG.warning(e);
            widget.setText("<!--"+e.getClass().getSimpleName()+":"+e.getMessage()+"-->\n"+htmlText);
        } catch (IOException e) {
            CoreUi.LOG.warning(e);
            widget.setText("<!--"+FileUtils.getLocalizedMessage(e)+"-->\n"+htmlText);
        }
    }

    /**
     * This main method can be used to test the StyledTextHelper class.
     * 
     * @param args unused
     */
    @objid ("e3b38947-0556-42ad-ad9b-d72930210be6")
    public static final void main(String[] args) {
        String htmlText = "<html>normal <div style=\"font-size:150%;color:#FF0000;background-color:#00F0F0;border: 1px solid #FF0000;\">paragraph <b>style</b></div><b>bold</b> <i>italic</i> <br/><u>underline</u> <s>strike</s> normal</html>";
        
        // create the widget's shell
        Shell shell = new Shell();
        shell.setLayout(new FillLayout());
        shell.setSize(300, 200);
        Display display = shell.getDisplay();
        // create the styled text widget
        StyledText styledText = new StyledText(shell, SWT.BORDER);
        shell.open();
        
        StyledTextHelper.setStyledText(htmlText, styledText);
        
        while (!shell.isDisposed())
            if (!display.readAndDispatch())
                display.sleep();
    }

    /**
     * Sax parser handler.
     */
    @objid ("5341c060-d3e1-4031-815a-e0b6783571d9")
    private static class HtmlTextContentHandler extends DefaultHandler {
        @objid ("801f848a-9f73-45fc-99c4-61ca66765281")
        private static final List<String> supportedTags = Arrays.asList("html", "p", "b", "i", "u", "s", "br", "div", "span");

        @objid ("820fc518-4a85-4752-a0cf-2b97eb350813")
        private List<StyleRange> styleRanges;

        @objid ("74f5053a-542b-47d3-a7af-c192d04795c3")
        private StringBuilder textBuilder;

        @objid ("7cdc6cd4-cb4f-4171-874b-b47c0c9e0a59")
        private Stack<Format> formatStack;

        @objid ("52a4e9fd-31e8-4d7a-8e4e-f5a9a9088768")
        private StringBuilder lastTextChunk;

        /**
         * This method returns a string representing the current state of the handler. Use to debug the class.
         */
        @objid ("f49f4959-064a-435d-8fbb-72e2ca9e0799")
        private String dumpState() {
            return String.format("text='%s', format='%s'", this.lastTextChunk.toString(), getFormat() != null ? getFormat().toString() : "null");
        }

        @objid ("2d88853d-7901-46eb-85d1-0c67332ab67b")
        public StyleRange[] getStyleRanges() {
            return this.styleRanges.toArray(new StyleRange[this.styleRanges.size()]);
        }

        @objid ("c852d502-f5ce-44ea-b59f-ff53ddee2980")
        public String getText() {
            return this.textBuilder.toString();
        }

        @objid ("db739d8d-7807-45d4-b3e0-2de49b400026")
        HtmlTextContentHandler() {
            this.formatStack = new Stack<>();
            this.formatStack.push(new Format());
            this.lastTextChunk = new StringBuilder();
            
            this.styleRanges = new ArrayList<>();
            this.textBuilder = new StringBuilder();
        }

        /**
         * Processing "characters" Sax events.
         */
        @objid ("11eed365-a5f4-4037-b1e4-fec299643cde")
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            this.lastTextChunk.append(new String(ch, start, length));
        }

        /**
         * Processing "element start" Sax events.
         */
        @objid ("bec57553-39a6-472f-968f-30cab6da9fc7")
        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            if (!isSupportedTag(qName)) {
                // Add it as text for debugging purpose
                this.lastTextChunk.append(String.format("<%s>", qName));
                return;
            }
            
            Format currentFormat = getFormat();
            
            produce(this.lastTextChunk.toString(), currentFormat);
            this.lastTextChunk = new StringBuilder();
            
            currentFormat = pushFormat();
            
            switch (qName) {
            case "p":
                this.lastTextChunk.append("\n\n");
                for (int i = 0; i < atts.getLength(); i++) {
                    if ("style".equals(atts.getQName(i))) {
                        String[] rules = atts.getValue(i).split(";");
                        currentFormat.applyCSS(rules);
                    }
                }
                break;
            case "div":
                this.lastTextChunk.append("\n");
                for (int i = 0; i < atts.getLength(); i++) {
                    if ("style".equals(atts.getQName(i))) {
                        String[] rules = atts.getValue(i).split(";");
                        currentFormat.applyCSS(rules);
                    }
                }
                break;
            case "span":
                for (int i = 0; i < atts.getLength(); i++) {
                    if ("style".equals(atts.getQName(i))) {
                        String[] rules = atts.getValue(i).split(";");
                        currentFormat.applyCSS(rules);
                    }
                }
                break;
            case "i":
                currentFormat.italic = true;
                break;
            case "b":
                currentFormat.bold = true;
                break;
            case "u":
                currentFormat.underline = true;
                currentFormat.underlineStyle = SWT.UNDERLINE_SINGLE;
                break;
            case "s":
                currentFormat.strikeout = true;
                break;
            case "br":
                this.lastTextChunk.append("\n");
                break;
            case "html":
                pushFormat();
                break;
            default:
            
            }
        }

        @objid ("81efc2a6-450c-4d2c-9960-4ba8033dccb3")
        private boolean isSupportedTag(String qName) {
            return HtmlTextContentHandler.supportedTags.contains(qName);
        }

        @objid ("463eb61a-149e-4e76-b917-9016e24af17d")
        private void produce(String s, Format format) {
            if (s == null || s.isEmpty())
                return;
            
            StyleRange r = createStyleRange(format, currentIndex(), s.length());
            this.textBuilder.append(s);
            this.styleRanges.add(r);
        }

        /**
         * Processing "document end" Sax events.
         */
        @objid ("959a690f-9a12-43f1-9818-a43f29b6d53b")
        @Override
        public void endDocument() throws SAXException {
            produce(this.lastTextChunk.toString(), getFormat());
            this.lastTextChunk = new StringBuilder();
            super.endDocument();
        }

        /**
         * Processing "element end" Sax events.
         */
        @objid ("bf0ddce5-060d-4477-af66-dee6c0c38a8b")
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            // If the tag found is not a supported one then return
            if (!HtmlTextContentHandler.supportedTags.contains(qName)) {
                this.lastTextChunk.append(String.format("</%s>", qName));
                return;
            }
            
            Format currentFormat = null;
            
            switch (qName) {
            case "p":
                this.lastTextChunk.append("\n\n");
                currentFormat = popFormat();
                break;
            case "div":
                this.lastTextChunk.append("\n");
                currentFormat = popFormat();
                break;
            case "span":
                currentFormat = popFormat();
                break;
            case "i":
            case "b":
            case "u":
            case "s":
                currentFormat = popFormat();
                break;
            case "br":
                currentFormat = popFormat();
                break;
            case "html":
                currentFormat = popFormat();
                break;
            default:
                break;
            
            }
            
            produce(this.lastTextChunk.toString(), currentFormat);
            this.lastTextChunk = new StringBuilder();
        }

        /**
         * Build a StyleRange for the given Format definition
         * @return
         */
        @objid ("bbd9af6f-aca6-4e52-ae18-85a978c9fb19")
        private StyleRange createStyleRange(Format format, int start, int length) {
            StyleRange range = new StyleRange();
            range.start = start; // currentIndex() + 1;
            range.length = length; // this.lastTextChunk.length();
            
            if (format.foreground != null) {
                range.foreground = format.foreground;
            }
            
            if (format.background != null) {
                range.background = format.background;
            }
            
            if (format.bold)
                range.fontStyle |= SWT.BOLD;
            
            if (format.italic)
                range.fontStyle |= SWT.ITALIC;
            
            if (format.borderStyle != SWT.NONE) {
                range.borderColor = range.foreground;
                range.borderStyle = SWT.BORDER_SOLID;
            }
            
            range.underline = format.underline;
            range.underlineColor = range.foreground;
            range.underlineStyle = SWT.UNDERLINE_SINGLE;
            
            range.strikeout = format.strikeout;
            range.strikeoutColor = range.foreground;
            
            if (format.fontSize != -1) {
                Font baseFont = Display.getCurrent().getSystemFont();
                Font font = CoreFontRegistry.getModifiedFont(baseFont, (format.bold ? SWT.BOLD : SWT.NONE) | (format.italic ? SWT.ITALIC : SWT.NONE), format.fontSize / 100.0f);
                range.font = font;
            }
            return range;
        }

        @objid ("8efef1eb-66b0-4d8f-a830-dd06ee4eccc4")
        private Format getFormat() {
            return this.formatStack.isEmpty() ? null : this.formatStack.peek();
        }

        @objid ("fe21981d-3c47-41d7-bb3f-d82c77d8797e")
        private Format popFormat() {
            return this.formatStack.isEmpty() ? null : this.formatStack.pop();
        }

        @objid ("2073cd08-4ddc-4ecb-a3bf-23c84b7276ce")
        private Format pushFormat() {
            if (this.formatStack.isEmpty()) {
                this.formatStack.push(new Format());
            } else {
                this.formatStack.push(this.formatStack.peek().clone());
            }
            return getFormat();
        }

        @objid ("c97b9ec6-38a7-4a72-957e-020f8832a7e8")
        private int currentIndex() {
            return this.textBuilder.length();
        }

    }

    /**
     * A class that hold the formatting attributes of a text chunk.
     */
    @objid ("34231317-7f70-47a1-9699-15ab2f513d8a")
    private static class Format {
        @objid ("ddff2b35-c45a-4d0c-94dc-755f7390ea65")
        public boolean bold;

        @objid ("10ce81cc-de8a-4f6d-b0d9-a5cf6fe8eeb1")
        public boolean italic;

        @objid ("1537b7f7-e793-47e3-b4b5-710d94fe1d1b")
        private int fontSize;

        @objid ("758a60c5-bcba-4fc3-bd90-ac35fc29ccc7")
        private boolean underline;

        @objid ("affa5b76-766d-4453-8572-2a6f77c6c692")
        private int underlineStyle;

        @objid ("4cd6ec37-524b-4d71-9543-4c5bc1fe1ab2")
        private boolean strikeout;

        @objid ("a11a4c49-f5d1-4d40-ab48-a28576c2cc52")
        private int borderStyle;

        @objid ("1d367972-8310-4e6e-bdd2-0de3a9cf4c9b")
        private Color foreground;

        @objid ("cdc186f3-41fd-4392-990e-4caed149740d")
        private Color background;

        @objid ("59b3eaf3-62bf-46c9-9b57-a0e09d7ed8c4")
        private Color underlineColor;

        @objid ("343a5a2f-82e4-4e8a-a26a-6043cb152975")
        private Color strikeoutColor;

        @objid ("fff49d2a-c2fb-44db-a5e4-943e6b2e8c3f")
        private Color borderColor;

        @objid ("c8d47bd5-5462-4486-ab63-5670c5fcdda0")
        @Override
        public Format clone() {
            return new Format(this);
        }

        @objid ("c824fb35-9512-4abd-9880-bd225bc8a497")
        private Format(Format f) {
            this.fontSize = f.fontSize;
            this.foreground = f.foreground;
            this.background = f.background;
            this.underline = f.underline;
            this.underlineColor = f.underlineColor;
            this.underlineStyle = f.underlineStyle;
            this.strikeout = f.strikeout;
            this.strikeoutColor = f.strikeoutColor;
            this.borderStyle = f.borderStyle;
            this.borderColor = f.borderColor;
            this.bold = f.bold;
            this.italic = f.italic;
        }

        @objid ("9cea3b24-aa1f-4094-930a-b47c3a31a100")
        public Format() {
            this.fontSize = -1;
            this.foreground = null;
            this.background = null;
            this.underline = false;
            this.underlineColor = null;
            this.underlineStyle = SWT.NONE;
            this.strikeout = false;
            this.strikeoutColor = null;
            this.borderStyle = SWT.NONE;
            this.borderColor = null;
            this.bold = false;
            this.italic = false;
        }

        @objid ("0baa6e86-7f1c-41d4-8d23-1b3a9ea523d7")
        @Override
        public String toString() {
            return String.format("%s%s%s%s",
                    this.bold ? "b" : "-",
                    this.italic ? "i" : "-",
                    this.underline ? "u" : "-",
                    this.strikeout ? "s" : "-");
        }

        @objid ("5046d6a0-cbdd-4bc8-8010-2104b783f2b6")
        private void applyCSS(String[] rules) {
            for (String rule : rules) {
                String[] pair = rule.split(":");
                if (pair.length == 2) {
                    switch (pair[0]) {
                    case "color":
                        Color fgColor = getColor(pair[1]);
                        if (fgColor != null) {
                            this.foreground = fgColor;
                        }
                        break;
                    case "background-color":
                        Color bgColor = getColor(pair[1]);
                        if (bgColor != null) {
                            this.background = bgColor;
                        }
                        break;
                    case "border":
                        this.borderStyle = SWT.BORDER_SOLID;
                        this.borderColor = getColor("#000000");
                        break;
            
                    case "font-size": // font-size:30%;
                        // Only percentage are supported
                        if (pair[1].matches("[0-9]+%")) {
                            fontSize = Integer.valueOf(pair[1].substring(0, pair[1].length() - 1));
                        }
            
                        break;
                    default:
                        ; // debug: System.err.printf("ignored CSS %s:%s\n", pair[0], pair[1]);
                    }
                }
            }
        }

        /**
         * Convert a HTML color value into a color.
         * 
         * Only the hexadecimal #XXXXXX syntax is supported.
         * 
         * @param s doc
         * @return the color 2
         */
        @objid ("fc101912-de63-425d-89b2-9c8b76cd1b77")
        private Color getColor(String s) {
            // #RRGGBB
            if (s.startsWith("#") & s.length() == 7) {
                int red = Integer.parseInt(s.substring(1, 3), 16);
                int green = Integer.parseInt(s.substring(3, 5), 16);
                int blue = Integer.parseInt(s.substring(5, 7), 16);
            
                RGB rgb = new RGB(red, green, blue);
                return CoreColorRegistry.getColor(rgb);
            } else {
                return null;
            }
        }

    }

}

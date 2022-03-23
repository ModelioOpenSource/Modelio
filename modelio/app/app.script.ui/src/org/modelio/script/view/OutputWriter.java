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
package org.modelio.script.view;

import java.io.IOException;
import java.io.Writer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

/**
 * Writer that sends the input to the given {@linkplain StyledText}.
 * <p>
 * A text style can be specified to change the colour of the text for example.<br/>
 * {@linkplain #flush()} can also execute a given {@linkplain Runnable}.
 */
@objid ("007da6d6-663d-105c-84ef-001ec947cd2a")
class OutputWriter extends Writer {
    @objid ("007dbe8c-663d-105c-84ef-001ec947cd2a")
    private Runnable onFlush;

    @objid ("007dd098-663d-105c-84ef-001ec947cd2a")
    private boolean isToFlush = false;

    @objid ("007db734-663d-105c-84ef-001ec947cd2a")
    protected TextAppender appender;

    /**
     * Creates an OutputWriter.
     * <p>
     * The input will be sent in green colour to the styled text.
     * @param outputView the styled text to send the input to.
     */
    @objid ("007dd6d8-663d-105c-84ef-001ec947cd2a")
    public  OutputWriter(StyledText outputView) {
        TextStyle style = new TextStyle(null, Display.getDefault().getSystemColor(SWT.COLOR_GREEN), null);
        this.appender = new TextAppender(outputView, style);
        
    }

    /**
     * Creates an OutputWriter.
     * @param outputView the styled text to send the input to.
     * @param style style of the text that will be sent to the styled text.
     */
    @objid ("007e00ae-663d-105c-84ef-001ec947cd2a")
    public  OutputWriter(StyledText outputView, TextStyle style) {
        this.appender = new TextAppender(outputView, style);
    }

    @objid ("007e3600-663d-105c-84ef-001ec947cd2a")
    @Override
    public void close() throws IOException {
        flush();
        this.appender = null;
        this.onFlush = null;
        
    }

    /**
     * Run the given runnable if one has been specified at construction.<br>
     * Do nothing in the other case.
     */
    @objid ("007e4bea-663d-105c-84ef-001ec947cd2a")
    @Override
    public void flush() throws IOException {
        if (this.isToFlush && this.onFlush != null) {
            this.onFlush.run();
            this.isToFlush = false;
        }
        
    }

    @objid ("007e6b3e-663d-105c-84ef-001ec947cd2a")
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        final String string = String.valueOf(cbuf, off, len);
        this.appender.execute(string);
        this.isToFlush = true;
        
    }

    /**
     * Creates an OutputWriter.
     * @param outputView the styled text to send the input to.
     * @param textStyle style of the text that will be sent to the styled text.
     * @param onFlush will be run each time flush() is called.
     */
    @objid ("007e9e74-663d-105c-84ef-001ec947cd2a")
    public  OutputWriter(OutputView outputView, TextStyle textStyle, Runnable onFlush) {
        this(outputView, textStyle);
        this.onFlush = onFlush;
        
    }

    /**
     * Runnable that appends text to a {@link StyledText} in the GUI thread.
     */
    @objid ("008d009a-663d-105c-84ef-001ec947cd2a")
    class TextAppender implements Runnable {
        @objid ("008d1bc0-663d-105c-84ef-001ec947cd2a")
        private String stringtoAppend = null;

        @objid ("00525d96-f1bd-106a-bf4f-001ec947cd2a")
        private final StyledText outputView;

        @objid ("00526e12-f1bd-106a-bf4f-001ec947cd2a")
        private final TextStyle style;

        @objid ("008d651c-663d-105c-84ef-001ec947cd2a")
        public  TextAppender(StyledText outputView, TextStyle style) {
            this.outputView = outputView;
            this.style = style;
            
        }

        @objid ("008d90fa-663d-105c-84ef-001ec947cd2a")
        public void execute(String aString) {
            this.stringtoAppend = aString;
            this.outputView.getDisplay().syncExec(this);
            
        }

        @objid ("008dadb0-663d-105c-84ef-001ec947cd2a")
        @Override
        public void run() {
            final StyleRange range = new StyleRange(this.style);
            
            range.start = this.outputView.getCharCount();
            range.length = this.stringtoAppend.length();
            
            // if (range.start < 0)
            // range.start = 0;
            
            this.outputView.append(this.stringtoAppend);
            this.outputView.setStyleRange(range);
            this.outputView.setSelection(this.outputView.getCharCount());
            
        }

    }

}

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

package org.modelio.diagram.elements.umlcommon.note;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.ScrollPaneLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.core.ui.MimeServices.MimeType;
import org.modelio.core.ui.MimeServices;
import org.modelio.diagram.elements.core.figures.IShaper;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.figures.html.flyingsaucer.FsHtmlFigure;

/**
 * A note figure is is made of: <br>
 * <ul>
 * <li>a title (a label)</li>
 * <li>a content area (multiline text)</li>
 * </ul>
 */
@objid ("818d863e-1dec-11e2-8cad-001ec947c8cc")
public class NoteFigure extends ShapedFigure {
    @objid ("687af5c9-1e83-11e2-8cad-001ec947c8cc")
    private Label title;

    @objid ("687af5ca-1e83-11e2-8cad-001ec947c8cc")
    private Figure contentFigure;

    @objid ("687af5cb-1e83-11e2-8cad-001ec947c8cc")
    private TextFlow contentsText;

    @objid ("687af5cc-1e83-11e2-8cad-001ec947c8cc")
    private ScrollPane scrollPane;

    @objid ("47ded996-327e-4f1d-95ea-1f1cc555724c")
    private FsHtmlFigure fsHtmlFigure;

    @objid ("510ae984-1387-4cfd-b5ea-1fabb598f12b")
    private static final NoteShaper NOTE_SHAPER = new NoteShaper();

    /**
     * Creates a note figure.
     * @param aCompo a SWT composite
     */
    @objid ("818d8652-1dec-11e2-8cad-001ec947c8cc")
    public NoteFigure() {
        super(NoteFigure.NOTE_SHAPER);
        
        // this.swtComposite = aCompo;
        // The note figure is a container layouted as a vertical toolbar
        // Children are transparent without borders
        ToolbarLayout layout = new NoteLayout();
        layout.setStretchMinorAxis(true);
        setLayoutManager(layout);
        
        setBorder(new ShapedBorder(getLineColor(), getLineWidth(), NoteFigure.NOTE_SHAPER));
        
        // First child: the title area
        this.title = new Label();
        TLBRBorder tlbrBorder = new TLBRBorder(getLineColor(), 1, false, false, true, false);
        tlbrBorder.setStyle(Graphics.LINE_DOT);
        this.title.setBorder(new CompoundBorder(tlbrBorder, new MarginBorder(2)));
        this.add(this.title);
        
        // The note text figure list is placed in a TRANSPARENT scroll pane
        this.scrollPane = new TransparentScrollPane();
        this.scrollPane.getViewport().setContentsTracksWidth(true);
        this.scrollPane.getViewport().setContentsTracksHeight(true);
        this.scrollPane.setLayoutManager(new ScrollPaneLayout());
        this.scrollPane.setVerticalScrollBarVisibility(ScrollPane.AUTOMATIC);
        this.scrollPane.setHorizontalScrollBarVisibility(ScrollPane.AUTOMATIC);
        this.scrollPane.setBorder(new MarginBorder(2, 2, 2, 2));
        
        FlowPage page = createPlainTextFigure("");
        this.contentFigure = page;
        
        this.scrollPane.setContents(this.contentFigure);
        
        this.add(this.scrollPane);
    }

    /**
     * Get the note content figure.
     * 
     * @return The figure where the note content is displayed.
     */
    @objid ("818d8655-1dec-11e2-8cad-001ec947c8cc")
    public Figure getContentFigure() {
        return this.contentFigure;
    }

    /**
     * Set note content and mode.
     * 
     * @param content the content, as HTML or plain text
     * @param htmlMode true to activate the HTML mode
     */
    @objid ("818d865c-1dec-11e2-8cad-001ec947c8cc")
    public void setContents(String content, boolean htmlMode) {
        if (htmlMode) {
            // new content is HTML
            if (this.contentsText != null) {
                // previous mode is plain text, switch to HTML
                Figure childFig = createHtmlFigure(content);
        
                this.scrollPane.setContents(childFig);
                this.contentFigure = childFig;
                this.contentFigure.setFont(getTextFont());
                this.contentFigure.setForegroundColor(getTextColor());
        
                this.fsHtmlFigure.setHtmlText(content);
                this.contentsText = null;
            } else {
                // Mode is already OK, just change text
                this.fsHtmlFigure.setHtmlText(content);
            }
        } else {
            // new content is plain text
            if (this.fsHtmlFigure != null) {
                // Previous mode is HTML, switch to plain text
                // Create PageFlow for plain text mode
                FlowPage page = createPlainTextFigure(content);
        
                this.contentFigure = page;
                this.contentFigure.setFont(getTextFont());
                this.contentFigure.setForegroundColor(getTextColor());
        
                this.scrollPane.setContents(this.contentFigure);
        
                // Reset HTML figure pointers
                this.fsHtmlFigure = null;
            } else {
                this.contentsText.setText(content);
            }
        }
    }

    @objid ("818d8660-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        this.title.setForegroundColor(textColor);
        this.contentFigure.setForegroundColor(textColor);
        super.setTextColor(textColor);
    }

    @objid ("818d8664-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        this.title.setFont(textFont);
        this.title.setMinimumSize(this.title.getPreferredSize(-1, -1));
        this.contentFigure.setFont(textFont);
        super.setTextFont(textFont);
    }

    /**
     * Set the displayed note type.
     * 
     * @param type the note type.
     */
    @objid ("818d8668-1dec-11e2-8cad-001ec947c8cc")
    public void setType(String type) {
        this.title.setText(type);
        this.title.setMinimumSize(this.title.getPreferredSize(-1, -1));
    }

    /**
     * Convert a HTML text to plain text.
     * <p>
     * In the case where the conversion fails, the HTMl text is returned.
     * 
     * @param htmlText HTML text
     * @return plain text.
     */
    @objid ("90e1bbd3-3681-4d62-aeb3-b5a93a9ab5f6")
    private static String toPlainText(String htmlText) {
        return MimeServices.convert(htmlText, MimeType.HTML, MimeType.PLAIN);
    }

    /**
     * Creates HTMl representation .
     * 
     * @param htmlText the HTML text
     * @return the created figure
     */
    @objid ("a72dd6fd-6e67-4578-8843-0c69559d67e7")
    private Figure createHtmlFigure(final String htmlText) {
        this.fsHtmlFigure = new FsHtmlFigure(null);
        return this.fsHtmlFigure;
    }

    /**
     * Creates the plain text mode figure: a FlowPage + a TextFlow
     * 
     * @param content the plain text
     * @return the plain text mode figure
     */
    @objid ("2e900df0-d162-4d89-b8a6-72dfb402880d")
    private FlowPage createPlainTextFigure(String content) {
        FlowPage page = new FlowPage();
        this.contentsText = new TextFlow();
        this.contentsText.setText(content);
        page.add(this.contentsText);
        page.setBorder(new MarginBorder(2));
        page.setOpaque(false);
        page.setHorizontalAligment(PositionConstants.LEFT);
        return page;
    }

    /**
     * Note scroll pane layout.
     * <p>
     * <li>Its minimum size is its preferred size.
     * <li>Tries to have a 2/1 width/height ratio.
     */
    @objid ("818d8672-1dec-11e2-8cad-001ec947c8cc")
    private static final class NoteLayout extends ToolbarLayoutWithGrab {
        @objid ("818fe875-1dec-11e2-8cad-001ec947c8cc")
        NoteLayout() {
            super(false);
        }

        /**
         * Called by {@link #layout(IFigure)}.
         * <p>
         * Should not resize automatically the note but super.calculatePreferredSize() returns the ideal size and it is not wanted.
         */
        @objid ("818fe877-1dec-11e2-8cad-001ec947c8cc")
        @Override
        protected Dimension calculatePreferredSize(final IFigure container, final int wHint, final int hHint) {
            // Called by layout().
            // Should not resize automatically the note.
            // super.calculatePreferredSize() returns the ideal size and it is not wanted
            Dimension ret = container.getSize();
            if (ret.width == 0) {
                ret.width = -1;
            }
            return calculateMinSize(container, Math.max(wHint, ret.width), Math.max(ret.height, hHint));
        }

        /**
         * Called by 'fit to content' command.
         * <p>
         * But super.calculateMinimumSize() does not take the content into account.
         */
        @objid ("818fe887-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Dimension calculateMinimumSize(final IFigure container, final int wHint, final int hHint) {
            // Called by 'fit to content' command.
            // But super.calculateMinimumSize() does not take the content into account.
            return calculateIdealSize(container, wHint, hHint);
        }

        /**
         * Calculate the minimum size a note should be.
         * 
         * @param container the note figure
         * @param wHint the width hint (the desired width of the container)
         * @param hHint the height hint (the desired height of the container)
         * @return the minimum size a note should be.
         */
        @objid ("818fe897-1dec-11e2-8cad-001ec947c8cc")
        private Dimension calculateMinSize(final IFigure container, final int wHint, final int hHint) {
            @SuppressWarnings ("synthetic-access")
            Dimension ret = ((NoteFigure) container).title.getPreferredSize(wHint, -1).getCopy();
            ret.width += NoteShaper.FOLDSIZE * 2;
            
            ret.union(super.calculateMinimumSize(container, wHint, hHint));
            
            if (ret.width < 120) {
                ret.width = 120;
            }
            
            if (ret.height < 100) {
                ret.height = 100;
            }
            
            if (ret.width / ret.height > 4) {
                ret = super.calculateMinimumSize(container, ret.height * 4, hHint);
            }
            return ret;
        }

        /**
         * Compute the ideal size of the note.
         * 
         * @param container the note figure
         * @param wHint the width hint (the desired width of the container)
         * @param hHint the height hint (the desired height of the container)
         * @return the ideal size.
         */
        @objid ("818fe8a6-1dec-11e2-8cad-001ec947c8cc")
        private Dimension calculateIdealSize(final IFigure container, final int wHint, final int hHint) {
            Dimension ret = super.calculatePreferredSize(container, wHint, hHint);
            
            if (ret.height < 60) {
                ret.height = 60;
            }
            
            if (ret.width < 40) {
                ret.width = 40;
            }
            
            if (ret.width / ret.height > 4) {
                ret = super.calculatePreferredSize(container, ret.height * 4, hHint);
            }
            return ret;
        }

    }

    /**
     * Transparent {@link ScrollPane}.
     * <p>
     * Exists because {@link ScrollPane#isOpaque()} always returns <code>true</code>.
     */
    @objid ("81924ad9-1dec-11e2-8cad-001ec947c8cc")
    public static final class TransparentScrollPane extends ScrollPane {
        @objid ("81924ade-1dec-11e2-8cad-001ec947c8cc")
        public TransparentScrollPane() {
            super();
        }

        @objid ("81924ae0-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public boolean isOpaque() {
            // Override because ScrollPane.isOpaque()
            // always return true.
            return false;
        }

    }

    @objid ("320f478c-872e-44f3-affe-65d73965adcc")
    public static class NoteShaper implements IShaper {
        @objid ("44036049-f9db-44e9-92f7-0bdf6f313953")
        private static final int FOLDSIZE = 12;

        @objid ("9329aee9-5033-49d0-b193-2f4fd59e8bda")
        @Override
        public Path getShapePath(Rectangle rect) {
            Path path = new Path(Display.getCurrent());
            
            Point[] points = computeShape(rect);
            
            path.moveTo(points[0].x, points[0].y);
            path.lineTo(points[1].x, points[1].y);
            path.lineTo(points[3].x, points[3].y);
            path.lineTo(points[4].x, points[4].y);
            path.lineTo(points[5].x, points[5].y);
            path.lineTo(points[0].x, points[0].y);
            
            path.moveTo(points[1].x, points[1].y);
            path.lineTo(points[2].x, points[2].y);
            path.lineTo(points[3].x, points[3].y);
            return path;
        }

        /**
         * Compute the shape of the note figure in an array of Points.
         * 
         * <pre>
         * 0--------1
         * |        |\
         * |        | \
         * |        2-3
         * |          |
         * |          |
         * 5----------4
         * </pre>
         * @param p an array of 6 points. If null a new array with 6 points will be allocated.
         * 
         * @param r the bounding box rectangle
         * @return the passed array or a new one
         */
        @objid ("7e78fa9d-3872-4c69-8392-f67dde7e8ad3")
        private Point[] computeShape(Rectangle r) {
            Point[] ret = new Point[6];
            for (int i = 0; i < 6; i++) {
                ret[i] = new Point();
            }
            
            int right = r.right();
            int bottom = r.bottom();
            
            ret[0].setLocation(r.x, r.y);
            ret[1].setLocation(right - NoteShaper.FOLDSIZE, r.y);
            ret[2].setLocation(right - NoteShaper.FOLDSIZE, r.y + NoteShaper.FOLDSIZE);
            ret[3].setLocation(right, r.y + NoteShaper.FOLDSIZE);
            ret[4].setLocation(right, bottom);
            ret[5].setLocation(r.x, bottom);
            return ret;
        }

        @objid ("c8e793c4-e116-4431-895f-3b051bf33d3f")
        @Override
        public Insets getShapeInsets(Rectangle rect) {
            return IFigure.NO_INSETS;
        }

    }

}

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
package org.modelio.diagram.elements.umlcommon.externdocument;

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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.core.figures.IShaper;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;

/**
 * A document figure is is made of: <br>
 * <ul>
 * <li>a type (a label)</li>
 * <li>a document name (a label)</li>
 * <li>a content area (multiline text)</li>
 * </ul>
 */
@objid ("814f890d-1dec-11e2-8cad-001ec947c8cc")
public class ExternDocumentFigure extends ShapedFigure {
    @objid ("685e5991-1e83-11e2-8cad-001ec947c8cc")
    private Label name;

    @objid ("685e5992-1e83-11e2-8cad-001ec947c8cc")
    private Label type;

    @objid ("685e5993-1e83-11e2-8cad-001ec947c8cc")
    private FlowPage contents;

    @objid ("685e5994-1e83-11e2-8cad-001ec947c8cc")
    private TextFlow contentsText;

    @objid ("685e5995-1e83-11e2-8cad-001ec947c8cc")
    private ScrollPane scrollPane;

    @objid ("e0bcea09-0a7d-4a83-bde7-c88ad631b367")
    private static final DocumentShaper DOCUMENT_SHAPER = new DocumentShaper();

    /**
     * Creates a document figure.
     */
    @objid ("8151eb53-1dec-11e2-8cad-001ec947c8cc")
    public  ExternDocumentFigure() {
        super(ExternDocumentFigure.DOCUMENT_SHAPER);
        
        // The document figure is a container layouted as a vertical toolbar
        // Children are transparent without borders
        ToolbarLayout layout = new DocumentLayout();
        layout.setStretchMinorAxis(true);
        setLayoutManager(layout);
        
        setBorder(new ShapedBorder(getLineColor(), getLineWidth(), ExternDocumentFigure.DOCUMENT_SHAPER));
        
        // Type area
        this.type = new Label();
        TLBRBorder typeBorder = new TLBRBorder(getLineColor(), 1, false, false, true, false);
        typeBorder.setStyle(Graphics.LINE_DASH);
        this.type.setBorder(new CompoundBorder(typeBorder, new MarginBorder(2)));
        this.type.setLabelAlignment(PositionConstants.LEFT);
        this.add(this.type);
        
        // Name area
        this.name = new Label();
        TLBRBorder nameBorder = new TLBRBorder(getLineColor(), 1, false, false, true, false);
        nameBorder.setStyle(Graphics.LINE_DOT);
        this.name.setBorder(new CompoundBorder(nameBorder, new MarginBorder(2)));
        this.add(this.name);
        
        // The document text figure list is placed in a TRANSPARENT scroll pane
        this.scrollPane = new TransparentScrollPane();
        this.scrollPane.getViewport().setContentsTracksWidth(true);
        this.scrollPane.getViewport().setContentsTracksHeight(true);
        this.scrollPane.setLayoutManager(new ScrollPaneLayout());
        this.scrollPane.setVerticalScrollBarVisibility(ScrollPane.AUTOMATIC);
        this.scrollPane.setHorizontalScrollBarVisibility(ScrollPane.AUTOMATIC);
        this.scrollPane.setBorder(new MarginBorder(2, 2, 2, 2));
        
        // In the scroll: the document text, a FlowPage + a TextFlow
        this.contents = new FlowPage();
        this.contentsText = new TextFlow();
        this.contents.add(this.contentsText);
        this.contents.setBorder(new MarginBorder(2));
        this.contents.setOpaque(false);
        this.contents.setHorizontalAligment(PositionConstants.LEFT);
        
        this.scrollPane.setContents(this.contents);
        
        this.add(this.scrollPane);
        
    }

    /**
     * Get the external document content figure.
     * @return The figure where the document content is displayed.
     */
    @objid ("8151eb56-1dec-11e2-8cad-001ec947c8cc")
    public Figure getContentFigure() {
        return this.contents;
    }

    /**
     * Get the external document name figure.
     * @return The figure where the document name is displayed.
     */
    @objid ("8151eb5d-1dec-11e2-8cad-001ec947c8cc")
    public Figure getNameFigure() {
        return this.name;
    }

    /**
     * Get the external document type figure.
     * @return The figure where the document type is displayed.
     */
    @objid ("8151eb64-1dec-11e2-8cad-001ec947c8cc")
    public Figure getTypeFigure() {
        return this.type;
    }

    @objid ("8151eb6b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(final Color textColor) {
        this.name.setForegroundColor(textColor);
        this.contents.setForegroundColor(textColor);
        this.type.setForegroundColor(textColor);
        super.setTextColor(textColor);
        
    }

    @objid ("8151eb70-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(final Font textFont) {
        this.name.setFont(textFont);
        this.name.setMinimumSize(this.name.getPreferredSize(-1, -1));
        
        this.contents.setFont(textFont);
        this.contents.setMinimumSize(this.contents.getPreferredSize(-1, -1));
        
        this.type.setFont(textFont);
        Dimension preferredSize = this.type.getPreferredSize(-1, -1);
        this.type.setMinimumSize(preferredSize);
        this.type.setPreferredSize(preferredSize);
        this.type.setMaximumSize(preferredSize);
        super.setTextFont(textFont);
        
    }

    /**
     * set the displayed document name.
     * @param name the document name.
     */
    @objid ("8151eb7c-1dec-11e2-8cad-001ec947c8cc")
    public void setName(final String name) {
        this.name.setText(name);
        this.name.setMinimumSize(this.name.getPreferredSize(-1, -1));
        
    }

    /**
     * set the displayed document mimeType.
     * @param mimeType the document mimeType.
     */
    @objid ("8151eb81-1dec-11e2-8cad-001ec947c8cc")
    public void setType(final Image mimeType) {
        this.type.setIcon(mimeType);
        this.type.setMinimumSize(this.type.getPreferredSize(-1, -1));
        
    }

    /**
     * Set the document text.
     * @param contents the document text.
     */
    @objid ("8151eb86-1dec-11e2-8cad-001ec947c8cc")
    public void setContents(final String contents) {
        this.contentsText.setText(contents);
        this.contentsText.setMinimumSize(this.contentsText.getPreferredSize(-1, -1));
        
    }

    /**
     * Set the displayed document type.
     * @param type the document type.
     */
    @objid ("8151eb8b-1dec-11e2-8cad-001ec947c8cc")
    public void setType(final String type) {
        this.type.setText(type);
        this.type.setMinimumSize(this.type.getPreferredSize(-1, -1));
        
    }

    /**
     * Document scroll pane layout.
     * <p>
     * <li>Its minimum size is its preferred size.
     * <li>Tries to have a 2/1 width/height ratio.
     */
    @objid ("8151eb90-1dec-11e2-8cad-001ec947c8cc")
    private static final class DocumentLayout extends ToolbarLayoutWithGrab {
        @objid ("8151eb93-1dec-11e2-8cad-001ec947c8cc")
         DocumentLayout() {
            super(false);
        }

        /**
         * Called by {@link #layout(IFigure)}.
         * <p>
         * Should not resize automatically the document but super.calculatePreferredSize() returns the ideal size and it is not wanted.
         */
        @objid ("8151eb95-1dec-11e2-8cad-001ec947c8cc")
        @Override
        protected Dimension calculatePreferredSize(final IFigure container, final int wHint, final int hHint) {
            // Called by layout().
            // Should not resize automatically the document.
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
        @objid ("8151eba5-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Dimension calculateMinimumSize(final IFigure container, final int wHint, final int hHint) {
            // Called by 'fit to content' command.
            // But super.calculateMinimumSize() does not take the content into account.
            return calculateIdealSize(container, wHint, hHint);
        }

        /**
         * Calculate the minimum size a document should be.
         * @param container the document figure
         * @param wHint the width hint (the desired width of the container)
         * @param hHint the height hint (the desired height of the container)
         * @return the minimum size a document should be.
         */
        @objid ("81544db4-1dec-11e2-8cad-001ec947c8cc")
        private Dimension calculateMinSize(final IFigure container, final int wHint, final int hHint) {
            Dimension ret = ((ExternDocumentFigure) container).name.getPreferredSize(wHint, -1).getCopy();
            ret.width += DocumentShaper.FOLDSIZE * 2;
            
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
         * Compute the ideal size of the document.
         * @param container the document figure
         * @param wHint the width hint (the desired width of the container)
         * @param hHint the height hint (the desired height of the container)
         * @return the ideal size.
         */
        @objid ("81544dc3-1dec-11e2-8cad-001ec947c8cc")
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
    @objid ("81544dd2-1dec-11e2-8cad-001ec947c8cc")
    public static final class TransparentScrollPane extends ScrollPane {
        @objid ("81544dd7-1dec-11e2-8cad-001ec947c8cc")
        public  TransparentScrollPane() {
            super();
        }

        @objid ("81544dd9-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public boolean isOpaque() {
            // Override because ScrollPane.isOpaque()
            // always return true.
            return false;
        }

    }

    @objid ("62ff146b-cd6b-4f8f-a858-85011bd426d5")
    private static class DocumentShaper implements IShaper {
        @objid ("9233f970-4d30-4dfe-9e74-8c49ca5f29f5")
        private static final int FOLDSIZE = 12;

        @objid ("12c1c9ba-1308-45b9-ba81-1203a061b4b6")
        @Override
        public Path createShapePath(Rectangle rect) {
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
         * Compute the shape of the document figure in an array of Points.
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
         * @param r the bounding box rectangle
         * @return the passed array or a new one
         */
        @objid ("43f26fe1-6bee-4d06-88c0-3b68650b6696")
        private Point[] computeShape(Rectangle r) {
            Point[] ret = new Point[6];
            for (int i = 0; i < 6; i++) {
                ret[i] = new Point();
            }
            
            int right = r.right();
            int bottom = r.bottom();
            
            ret[0].setLocation(r.x, r.y);
            ret[1].setLocation(right - DocumentShaper.FOLDSIZE, r.y);
            ret[2].setLocation(right - DocumentShaper.FOLDSIZE, r.y + DocumentShaper.FOLDSIZE);
            ret[3].setLocation(right, r.y + DocumentShaper.FOLDSIZE);
            ret[4].setLocation(right, bottom);
            ret[5].setLocation(r.x, bottom);
            return ret;
        }

        @objid ("5271d4ce-e637-4568-bb91-4e37e07f793b")
        @Override
        public Insets getShapeInsets(Rectangle rect) {
            return IFigure.NO_INSETS;
        }

    }

}

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
package org.modelio.diagram.elements.drawings.note;

import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.ScrollPaneLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.core.figures.IShaper;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.html.flyingsaucer.FsHtmlFigure;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * A note drawing figure is is made of: <br>
 * <ul>
 * <li>a content area (multiline text)</li>
 * </ul>
 */
@objid ("f6de7d06-9a0a-4673-b3de-461685be01dc")
public class NoteDrawingFigure extends ShapedFigure {
    @objid ("5ffdbc99-d03d-49f8-b55b-4531c196754a")
    private ScrollPane scrollPane;

    @objid ("3349305e-e7b1-428e-862d-6b1d25cc8683")
    private FsHtmlFigure fsHtmlFigure;

    @objid ("c93703f2-0e49-42ad-8df8-76782a65ebee")
    private final UMLNoteShaper umlNoteShaper = new UMLNoteShaper();

    @objid ("cdf1ccf5-ffc9-4d3b-9a4b-8051ddb44439")
    public void setAnchoringBorderPosition(Direction direction) {
        setGradientDirection(direction);
    }

    /**
     * Creates a note figure.
     */
    @objid ("2159bb63-85b7-4574-88f7-95255e00b9b7")
    public  NoteDrawingFigure() {
        super();
        
        ToolbarLayout layout = new NoteLayout();
        layout.setStretchMinorAxis(true);
        setLayoutManager(layout);
        
        // The note text figure list is placed in a TRANSPARENT scroll pane
        this.scrollPane = new TransparentScrollPane();
        this.scrollPane.getViewport().setContentsTracksWidth(true);
        this.scrollPane.getViewport().setContentsTracksHeight(true);
        this.scrollPane.setLayoutManager(new ScrollPaneLayout());
        this.scrollPane.setVerticalScrollBarVisibility(ScrollPane.AUTOMATIC);
        this.scrollPane.setHorizontalScrollBarVisibility(ScrollPane.AUTOMATIC);
        this.scrollPane.setBorder(new MarginBorder(1));
        this.fsHtmlFigure = new FsHtmlFigure(null);
        this.fsHtmlFigure.setFont(getTextFont());
        this.scrollPane.setContents(this.fsHtmlFigure);
        this.add(this.scrollPane);
        setShaper(this.umlNoteShaper);
        setBorder(new ShapedBorder(getLineColor(), getLineWidth(), this.umlNoteShaper));
        
    }

    @objid ("2229ab25-c88b-4eff-8440-3fcba9e6b19a")
    @Override
    public void setLineColor(Color lineColor) {
        super.setLineColor(lineColor);
        ((ShapedBorder) getBorder()).setColor(lineColor);
        
    }

    @objid ("e620214e-8741-4043-b55b-d5e2eca38865")
    @Override
    public void setLineWidth(int lineWidth) {
        super.setLineWidth(lineWidth);
        ((ShapedBorder) getBorder()).setWidth(lineWidth);
        
    }

    @objid ("1d3cd741-f465-4de5-a0e6-fe98b1f66eda")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        if (this.penOptions.linePattern != linePattern) {
            this.penOptions.linePattern = linePattern;
            ((LineBorder) getBorder()).setStyle(linePattern.toSWTConstant());
            this.repaint();
        }
        
    }

    /**
     * Get the note content figure.
     * @return The figure where the note content is displayed.
     */
    @objid ("1b914c36-f302-49f9-a282-d1e2343a3f9e")
    public Figure getContentFigure() {
        return this.fsHtmlFigure;
    }

    @objid ("1d63acb7-8913-4f06-a262-0041e2228330")
    public String getContent() {
        return this.fsHtmlFigure.getHtmlText();
    }

    /**
     * Set note content and mode.
     * @param htmlMode true to activate the HTML mode
     * @param content the content, as HTML or plain text
     */
    @objid ("976a830b-c62e-4385-a2b0-55ae918b3947")
    public void setContents(String content) {
        // Mode is already OK, just change text
        this.fsHtmlFigure.setHtmlText(content);
        
    }

    /**
     * Note scroll pane layout.
     * <p>
     * <li>Its minimum size is its preferred size.
     * <li>Tries to have a 2/1 width/height ratio.
     */
    @objid ("16f320d9-3a71-4f9e-8d69-de7b6019a7da")
    private static final class NoteLayout extends ToolbarLayoutWithGrab {
        @objid ("8ce4b0cf-a0a1-4ee1-9847-275ec157b271")
         NoteLayout() {
            super(false);
        }

        /**
         * Called by {@link #layout(IFigure)}.
         * <p>
         * Should not resize automatically the note but super.calculatePreferredSize() returns the ideal size and it is not wanted.
         */
        @objid ("0d84ed83-20e8-4cb8-ad8d-1597f2819ffe")
        @Override
        protected Dimension calculatePreferredSize(final IFigure container, final int wHint, final int hHint) {
            // Called by layout().
            // Should not resize automatically the note.
            // super.calculatePreferredSize() returns the ideal size and it is not wanted
            Dimension ret = container.getSize();
            if (ret.width == 0) {
                ret.width = -1;
            }
            return calculateMinSize((NoteDrawingFigure) container, Math.max(wHint, ret.width), Math.max(ret.height, hHint));
        }

        /**
         * Called by 'fit to content' command.
         * <p>
         * But super.calculateMinimumSize() does not take the content into account.
         */
        @objid ("046b97d0-f712-483d-85b1-1d4688594ae1")
        @Override
        public Dimension calculateMinimumSize(final IFigure container, final int wHint, final int hHint) {
            // Called by 'fit to content' command.
            // But super.calculateMinimumSize() does not take the content into account.
            return calculateIdealSize((NoteDrawingFigure) container, wHint, hHint);
        }

        /**
         * Calculate the minimum size a note should be.
         * @param container the note figure
         * @param wHint the width hint (the desired width of the container)
         * @param hHint the height hint (the desired height of the container)
         * @return the minimum size a note should be.
         */
        @objid ("ca37c364-96e6-45ab-80fc-fca6a402b339")
        private Dimension calculateMinSize(final NoteDrawingFigure container, final int wHint, final int hHint) {
            @SuppressWarnings ("synthetic-access")
            
            Dimension ret = new Dimension(0, 0);
            ret.union(super.calculateMinimumSize(container, wHint, hHint));
            
            if (ret.width < 200) {
                ret.width = 200;
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
         * @param container the note figure
         * @param wHint the width hint (the desired width of the container)
         * @param hHint the height hint (the desired height of the container)
         * @return the ideal size.
         */
        @objid ("41d08228-0fc8-4444-ae31-62e3dba0d859")
        private Dimension calculateIdealSize(final NoteDrawingFigure container, final int wHint, final int hHint) {
            Dimension ret = super.calculatePreferredSize(container, wHint, hHint);
            
            // Try to estimate the dimention of rendered htmt note
            String content = container.getContent();
            if(content.contains("<p>")) {
                int maxString = 0;
                int extimateLignes = 0;
                List<String>  paragraphers = splitByParagrapher(content);
            
                for(String par : paragraphers) {
                    maxString = Math.max(maxString, par.replaceAll("<.*>", "").length());
                }
            
                extimateLignes = paragraphers.size();
            
            
                ret.height = extimateLignes * 30 + 30;
                ret.width = maxString * 5;
            
                if (ret.width / ret.height > 3) {
                    ret.width  = ret.height * 3;
                    ret.height = ret.height * 2 ;
            
                }
            
            }else {
                ret.height = 100;
                ret.width = 200;
            }
            return ret;
        }

        @objid ("8b01543b-3508-4c57-bb49-70de5d6d8e5f")
        private List<String> splitByParagrapher(String content) {
            return  Arrays.asList(content.split("</p>|<li>"));
        }

    }

    /**
     * Transparent {@link ScrollPane}.
     * <p>
     * Exists because {@link ScrollPane#isOpaque()} always returns <code>true</code>.
     */
    @objid ("6c3a4bbe-b688-4447-9a4d-34cc5b33895b")
    public static final class TransparentScrollPane extends ScrollPane {
        @objid ("b01a7293-afe7-40dc-a583-452ca0354d3a")
        public  TransparentScrollPane() {
            super();
        }

        @objid ("65e3d9df-7e70-4e98-8fbd-ff56e8593a8c")
        @Override
        public boolean isOpaque() {
            // Override because ScrollPane.isOpaque()
            // always return true.
            return false;
        }

    }

    /**
     * Shaper for a UML note. Draws a rectangle with a folded top right corner.
     */
    @objid ("63fbe5fc-e7f0-48e1-a102-917c8e84d526")
    public static class UMLNoteShaper implements IShaper {
        @objid ("4ab959fe-4d08-485d-aae8-9bacffa97e4b")
        private static final int FOLDSIZE = 15;

        @objid ("4856734d-e2a5-47d5-9aa3-0b3a57083b4f")
        @Override
        public Path createShapePath(Rectangle rect) {
            Path path = new Path(Display.getCurrent());
            
            Point[] points = computeShape(rect);
            
            path.moveTo(points[0].x, points[0].y);
            path.lineTo(points[1].x, points[1].y);
            
            path.lineTo(points[2].x, points[2].y);
            path.lineTo(points[3].x, points[3].y);
            return path;
        }

        /**
         * Compute the shape of the note figure in an array of Points.
         * 
         * <pre>
         * 1--0
         * |
         * |
         * |
         * |
         * |
         * 2--3
         * </pre>
         * @param r the bounding box rectangle
         * @return the passed array or a new one
         */
        @objid ("50d2eb82-adf1-4c27-b964-170d54515d1f")
        private Point[] computeShape(Rectangle r) {
            Point[] ret = new Point[6];
            int bottom = r.bottom();
            ret[0] = new Point(r.x + 20, r.y);
            ret[1] = new Point(r.x, r.y);
            ret[2] = new Point(r.x, bottom);
            ret[3] = new Point(r.x + 20, bottom);
            return ret;
        }

        @objid ("1e1dbcdc-8dc6-4e36-9a7b-3d2e86027fd2")
        @Override
        public Insets getShapeInsets(Rectangle rect) {
            return IFigure.NO_INSETS;
        }

    }

}

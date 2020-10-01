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

package org.modelio.propertyview.vtabfolder;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolderRenderer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

/**
 * Instances of this class provide all of the measuring and drawing functionality required by <code>VTabFolder</code>. This class can be subclassed in order to customize the look of a VTabFolder.
 */
@objid ("1591f5d7-5a5e-427d-9d1f-7423a5b3bbdf")
public class VTabFolderRenderer {
    @objid ("ad046173-375f-443e-9d3b-e6fe6ff0eef1")
    private static final int HIGHLIGHT_HEADER_THICKNESS = 6;

    @objid ("6a98ebdb-9f89-407d-96f9-327b766fdf65")
    private static final int BEVEL = 2;

    @objid ("634f6bfa-b682-4249-ab22-92073fcda1aa")
    private static final int BUTTON_SIZE = 16;

    @objid ("736f2b36-762f-460f-a4fd-c6fe3344be4b")
    private static final int BUTTON_TRIM = 1;

    @objid ("da54f59b-b67e-429a-a804-a78f2e9f412d")
    private static final int BUTTON_BORDER = SWT.COLOR_WIDGET_DARK_SHADOW;

    @objid ("8327e2dc-304e-46e9-a894-503e33caa0d9")
    private static final int BUTTON_FILL = SWT.COLOR_LIST_BACKGROUND;

    @objid ("e174bc4e-58d3-4b91-830a-03cb03128900")
    private static final int BORDER1_COLOR = SWT.COLOR_WIDGET_NORMAL_SHADOW;

    @objid ("05acdcda-86a3-4be5-80a3-f1dc35dedd49")
    private static final int ITEM_TOP_MARGIN = 4;

    @objid ("7eee42a4-d02a-4553-9deb-0cd4e0d7dd55")
    private static final int ITEM_BOTTOM_MARGIN = 4;

    @objid ("2bdd41ad-c674-4bbe-87b5-89b2ca9924ad")
    private static final int ITEM_LEFT_MARGIN = 4;

    @objid ("4f058677-36eb-4b03-bdcd-ecc115d8080e")
    private static final int ITEM_RIGHT_MARGIN = 4;

    @objid ("18e5fee1-ab97-4a7a-a752-1dd997eaec96")
    private static final int INTERNAL_SPACING = 4;

    @objid ("6da6fee1-50ef-4d73-a3f9-61745db4b572")
    private static final int FLAGS = SWT.DRAW_TRANSPARENT | SWT.DRAW_MNEMONIC;

    @objid ("cdf0b542-7827-4281-8cc4-823c148b15e1")
    private static final String ELLIPSIS = "..."; // $NON-NLS-1$

// Part constants
    /**
     * Part constant indicating the body of the tab folder. The body is the underlying container for all of the tab folder and all other parts are drawn on top of it. (value is -1).
     * 
     * @see #computeSize(int, int, GC, int, int)
     * @see #computeTrim(int, int, int, int, int, int)
     * @see #draw(int, int, Rectangle, GC)
     */
    @objid ("74d81c09-2744-46d4-a448-b4e70f8f7232")
    public static final int PART_BODY = -1;

    /**
     * Part constant indicating the tab header of the folder (value is -2). The header is drawn on top of the body and provides an area for the tabs and other tab folder buttons to be rendered.
     * 
     * @see #computeSize(int, int, GC, int, int)
     * @see #computeTrim(int, int, int, int, int, int)
     * @see #draw(int, int, Rectangle, GC)
     */
    @objid ("fba0ea48-7394-42ff-93e0-d9ef5ac47713")
    public static final int PART_HEADER = -2;

    /**
     * Part constant indicating the border of the tab folder. (value is -3). The border is drawn around the body and is part of the body trim.
     * 
     * @see #computeSize(int, int, GC, int, int)
     * @see #computeTrim(int, int, int, int, int, int)
     * @see #draw(int, int, Rectangle, GC)
     */
    @objid ("69554861-be7e-469d-b39c-629d7e4138c3")
    public static final int PART_BORDER = -3;

    /**
     * Part constant indicating the background of the tab folder. (value is -4).
     * 
     * @see #computeSize(int, int, GC, int, int)
     * @see #computeTrim(int, int, int, int, int, int)
     * @see #draw(int, int, Rectangle, GC)
     */
    @objid ("c47985a1-42e1-44b1-bf43-e47e12d16545")
    public static final int PART_BACKGROUND = -4;

    /**
     * Part constant indicating the maximize button of the tab folder. (value is -5).
     * 
     * @see #computeSize(int, int, GC, int, int)
     * @see #computeTrim(int, int, int, int, int, int)
     * @see #draw(int, int, Rectangle, GC)
     */
    @objid ("c99e4820-4839-4f6d-b68d-d1210360df2f")
    public static final int PART_MAX_BUTTON = -5;

    /**
     * Part constant indicating the minimize button of the tab folder. (value is -6).
     * 
     * @see #computeSize(int, int, GC, int, int)
     * @see #computeTrim(int, int, int, int, int, int)
     * @see #draw(int, int, Rectangle, GC)
     */
    @objid ("7e06f9f7-095c-4f50-99a2-ff8805a22d01")
    public static final int PART_MIN_BUTTON = -6;

    /**
     * Part constant indicating the chevron button of the tab folder. (value is -7).
     * 
     * @see #computeSize(int, int, GC, int, int)
     * @see #computeTrim(int, int, int, int, int, int)
     * @see #draw(int, int, Rectangle, GC)
     */
    @objid ("3d6845e1-8ac7-4450-957f-1e69a8dec02f")
    public static final int PART_CHEVRON_BUTTON = -7;

    /**
     * Part constant indicating the close button of a tab item. (value is -8).
     * 
     * @see #computeSize(int, int, GC, int, int)
     * @see #computeTrim(int, int, int, int, int, int)
     * @see #draw(int, int, Rectangle, GC)
     */
    @objid ("0605620f-0b14-45d4-9504-f638b95b549e")
    public static final int PART_CLOSE_BUTTON = -8;

    @objid ("01d35b3c-e0ac-47f3-9826-dce06d4b6e8d")
    public static final int MINIMUM_SIZE = 1 << 24; // TODO: Should this be a state?

    @objid ("7bacabfb-5c59-4154-9672-e650e2c08b22")
    protected VTabFolder parent;

    @objid ("c8708ad5-d344-4511-bba5-118c3ce629ff")
    private Color fillColor;

    @objid ("323bd786-dc28-4228-8874-294694206922")
    private static final RGB CLOSE_FILL = new RGB(252, 160, 160);

    /**
     * Constructs a new instance of this class given its parent.
     * @see Widget#getStyle
     * 
     * @param parent CTabFolder
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_INVALID_ARGUMENT - if the parent is disposed</li>
     * </ul>
     */
    @objid ("ff346574-632b-4060-b4ae-a72917458756")
    protected VTabFolderRenderer(VTabFolder parent) {
        if (parent == null)
            return;
        if (parent.isDisposed())
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        this.parent = parent;
    }

    /**
     * Returns the preferred size of a part.
     * <p>
     * The <em>preferred size</em> of a part is the size that it would best be displayed at. The width hint and height hint arguments allow the caller to ask a control questions such as "Given a particular width, how high does the part need to be to show
     * all of the contents?" To indicate that the caller does not wish to constrain a particular dimension, the constant <code>SWT.DEFAULT</code> is passed for the hint.
     * </p>
     * <p>
     * The <code>part</code> value indicated what component the preferred size is to be calculated for. Valid values are any of the part constants:
     * <ul>
     * <li>PART_BODY</li>
     * <li>PART_HEADER</li>
     * <li>PART_BORDER</li>
     * <li>PART_BACKGROUND</li>
     * <li>PART_MAX_BUTTON</li>
     * <li>PART_MIN_BUTTON</li>
     * <li>PART_CHEVRON_BUTTON</li>
     * <li>PART_CLOSE_BUTTON</li>
     * <li>A positive integer which is the index of an item in the CTabFolder.</li>
     * </ul>
     * </p>
     * <p>
     * The <code>state</code> parameter may be one of the following:
     * <ul>
     * <li>SWT.NONE</li>
     * <li>SWT.SELECTED - whether the part is selected</li>
     * </ul>
     * </p>
     * 
     * @param part a part constant
     * @param state current state
     * @param gc the gc to use for measuring
     * @param wHint the width hint (can be <code>SWT.DEFAULT</code>)
     * @param hHint the height hint (can be <code>SWT.DEFAULT</code>)
     * @return the preferred size of the part
     * 
     * @since 3.6
     */
    @objid ("581181f6-cb91-4839-992c-ab260dcd4953")
    protected Point computeSize(int part, int state, GC gc, int wHint, int hHint) {
        int width = 0, height = 0;
        switch (part) {
        case PART_HEADER:
            if (this.parent.getFixedTabWidth() != SWT.DEFAULT) {
                width = this.parent.getFixedTabWidth() == 0 ? 0 : this.parent.getFixedTabWidth() + 1; // +1
                // for
                // line
                // drawn
                // across
                // top
                // of
                // tab
            } else {
                VTabItem[] items = this.parent.getItems();
                if (items.length == 0) {
                    width = gc.textExtent("Default", FLAGS).x + ITEM_TOP_MARGIN + ITEM_BOTTOM_MARGIN; //$NON-NLS-1$
                } else {
                    for (int i = 0; i < items.length; i++) {
                        width = Math.max(width, computeSize(i, SWT.NONE, gc, wHint, hHint).x);
                    }
                }
                gc.dispose();
            }
            break;
        case PART_MAX_BUTTON:
        case PART_MIN_BUTTON:
        case PART_CLOSE_BUTTON:
            width = height = BUTTON_SIZE;
            break;
        case PART_CHEVRON_BUTTON:
            width = 3 * BUTTON_SIZE / 2;
            height = BUTTON_SIZE;
            break;
        default:
            if (0 <= part && part < this.parent.getItemCount()) {
        
                VTabItem item = this.parent.getItems()[part];
                if (item.isDisposed())
                    return new Point(0, 0);
                Image image = item.getImage();
                if (image != null && !image.isDisposed()) {
                    Rectangle bounds = image.getBounds();
                    if ((state & SWT.SELECTED) != 0 || this.parent.showUnselectedImage) {
                        width += bounds.width;
                    }
                    height = bounds.height;
                }
                String text = null;
                if ((state & MINIMUM_SIZE) != 0) {
                    int minChars = this.parent.getMinChars();
                    text = minChars == 0 ? null : item.getText();
                    if (text != null && text.length() > minChars) {
                        if (useEllipses()) {
                            int end = minChars < ELLIPSIS.length() + 1 ? minChars : minChars - ELLIPSIS.length();
                            text = text.substring(0, end);
                            if (minChars > ELLIPSIS.length() + 1)
                                text += ELLIPSIS;
                        } else {
                            int end = minChars;
                            text = text.substring(0, end);
                        }
                    }
                } else {
                    text = item.getText();
                }
                if (text != null) {
                    if (width > 0)
                        width += INTERNAL_SPACING;
                    if (item.font == null) {
                        Point size = gc.textExtent(text, FLAGS);
                        width += size.x;
                        height = Math.max(height, size.y);
                    } else {
                        Font gcFont = gc.getFont();
                        gc.setFont(item.font);
                        Point size = gc.textExtent(text, FLAGS);
                        width += size.x;
                        height = Math.max(height, size.y);
                        gc.setFont(gcFont);
                    }
                }
        
            }
            break;
        }
        Rectangle trim = computeTrim(part, state, 0, 0, width, height);
        width = trim.width;
        height = trim.height;
        return new Point(width, height);
    }

    /**
     * Given a desired <em>client area</em> for the part (as described by the arguments), returns the bounding rectangle which would be required to produce that client area.
     * <p>
     * In other words, it returns a rectangle such that, if the part's bounds were set to that rectangle, the area of the part which is capable of displaying data (that is, not covered by the "trimmings") would be the rectangle described by the arguments
     * (relative to the receiver's parent).
     * </p>
     * @see CTabFolderRenderer#computeSize(int, int, GC, int, int) valid part and state values
     * 
     * @since 3.6
     * 
     * @param part one of the part constants
     * @param state the state of the part
     * @param x the desired x coordinate of the client area
     * @param y the desired y coordinate of the client area
     * @param width the desired width of the client area
     * @param height the desired height of the client area
     * @return the required bounds to produce the given client area
     */
    @objid ("6ccccacb-b262-443c-a4d0-6abac942213b")
    protected Rectangle computeTrim(int part, int state, int x, int y, int width, int height) {
        int borderTop = this.parent.isBorderVisible() ? 1 : 0;
        int borderBottom = borderTop;
        
        int borderLeft = this.parent.isOnRight() ? borderTop : 0;
        int borderRight = this.parent.isOnRight() ? 0 : borderTop;
        
        int tabWidth = this.parent.getTabWidth();
        
        switch (part) {
        case PART_BODY:
            int style = this.parent.getStyle();
            int highlight_header = getHighlightHeader(style);
            int highlight_margin = (style & SWT.FLAT) != 0 ? 0 : 2;
            if (this.parent.getFixedTabWidth() == 0 && (style & SWT.FLAT) != 0 && (style & SWT.BORDER) == 0) {
                highlight_header = 0;
            }
            int marginWidth = this.parent.marginWidth;
            int marginHeight = this.parent.marginHeight;
            y = y - marginHeight - highlight_margin - borderTop;
            height = height + borderTop + borderBottom + 2 * marginHeight + 2 * highlight_margin;
        
            x = this.parent.isOnRight() ? x - marginWidth - highlight_margin - borderLeft
                    : x - marginWidth - highlight_header - tabWidth - borderLeft;
            width = width + borderLeft + borderRight + 2 * marginWidth + tabWidth + highlight_header;
            // + highlight_margin;
        
            break;
        case PART_HEADER:
            y -= ITEM_TOP_MARGIN + ITEM_BOTTOM_MARGIN;
            break;
        case PART_MAX_BUTTON:
        case PART_MIN_BUTTON:
        case PART_CLOSE_BUTTON:
        case PART_CHEVRON_BUTTON:
            x -= BUTTON_TRIM;
            y -= BUTTON_TRIM;
            width += BUTTON_TRIM * 2;
            height += BUTTON_TRIM * 2;
            break;
        case PART_BORDER:
            x = x - borderLeft;
            width = width + borderLeft + borderRight;
            if (!this.parent.isSimple())
                width += 2; // TOP_RIGHT_CORNER needs more space
            y = y - borderTop;
            height = height + borderTop + borderBottom;
            break;
        default:
            if (0 <= part && part < this.parent.getItemCount()) {
        
                x = x - ITEM_LEFT_MARGIN;
                width = width + ITEM_LEFT_MARGIN + ITEM_RIGHT_MARGIN;
        
                y = y - ITEM_TOP_MARGIN;
                height = height + ITEM_TOP_MARGIN + ITEM_BOTTOM_MARGIN;
            }
            break;
        }
        return new Rectangle(x, y, width, height);
    }

    /**
     * Dispose of any operating system resources associated with the renderer. Called by the CTabFolder parent upon receiving the dispose event or when changing the renderer.
     * @since 3.6
     */
    @objid ("4d248531-2efe-47cd-a28d-9ac60cf0869a")
    protected void dispose() {
        if (this.fillColor != null) {
            this.fillColor.dispose();
            this.fillColor = null;
        }
    }

    /**
     * Draw a specified <code>part</code> of the CTabFolder using the provided <code>bounds</code> and <code>GC</code>.
     * <p>
     * The valid CTabFolder <code>part</code> constants are:
     * <ul>
     * <li>PART_BODY - the entire body of the CTabFolder</li>
     * <li>PART_HEADER - the upper tab area of the CTabFolder</li>
     * <li>PART_BORDER - the border of the CTabFolder</li>
     * <li>PART_BACKGROUND - the background of the CTabFolder</li>
     * <li>PART_MAX_BUTTON</li>
     * <li>PART_MIN_BUTTON</li>
     * <li>PART_CHEVRON_BUTTON</li>
     * <li>PART_CLOSE_BUTTON</li>
     * <li>A positive integer which is the index of an item in the CTabFolder.</li>
     * </ul>
     * </p>
     * <p>
     * The <code>state</code> parameter may be a combination of:
     * <ul>
     * <li>SWT.BACKGROUND - whether the background should be drawn</li>
     * <li>SWT.FOREGROUND - whether the foreground should be drawn</li>
     * <li>SWT.SELECTED - whether the part is selected</li>
     * <li>SWT.HOT - whether the part is hot (i.e. mouse is over the part)</li>
     * </ul>
     * </p>
     * 
     * @param part part to draw
     * @param state state of the part
     * @param bounds the bounds of the part
     * @param gc the gc to draw the part on
     * 
     * @since 3.6
     */
    @objid ("b2db1aed-aa69-4c09-9825-d597e0e3f0c8")
    protected void draw(int part, int state, Rectangle bounds, GC gc) {
        switch (part) {
        case PART_BACKGROUND:
            this.drawBackground(gc, bounds, state);
            break;
        case PART_BODY:
            drawBody(gc, bounds);
            break;
        case PART_HEADER:
            drawTabArea(gc);
            break;
        // case PART_MAX_BUTTON:
        // drawMaximize(gc, bounds, state);
        // break;
        // case PART_MIN_BUTTON:
        // drawMinimize(gc, bounds, state);
        // break;
        case PART_CHEVRON_BUTTON:
            drawChevron(gc, bounds, state);
            break;
        default:
            if (0 <= part && part < this.parent.getItemCount()) {
                if (bounds.width == 0 || bounds.height == 0)
                    return;
                if ((state & SWT.SELECTED) != 0) {
                    drawSelected(part, gc, bounds, state);
                } else {
                    drawUnselected(part, gc, bounds, state);
                }
            }
            break;
        }
    }

    @objid ("6798866f-6259-4cdb-9312-deb081cd5b99")
    private void drawBackground(GC gc, Rectangle bounds, int state) {
        boolean selected = (state & SWT.SELECTED) != 0;
        Color defaultBackground = selected ? this.parent.selectionBackground : this.parent.getBackground();
        Image image = selected ? this.parent.selectionBgImage : null;
        
        drawBackground(gc, null, bounds.x, bounds.y, bounds.width, bounds.height, defaultBackground, image);
    }

    @objid ("24fdef38-d8d0-4d09-af6d-613109a18003")
    private void drawBackground(GC gc, int[] shape, boolean selected) {
        Color defaultBackground = selected ? this.parent.selectionBackground : this.parent.getBackground();
        Image image = selected ? this.parent.selectionBgImage : null;
        
        Point size = this.parent.getSize();
        int width = this.parent.getTabWidth() + ((this.parent.getStyle() & SWT.FLAT) != 0 ? 1 : 3);
        int height = size.y;
        int x = 0;
        int y = 0;
        
        int borderTop = this.parent.isBorderVisible() ? 1 : 0;
        int borderLeft = this.parent.isOnRight() ? borderTop : 0;
        int borderRight = this.parent.isOnRight() ? 0 : borderTop;
        
        if (borderTop > 0) {
            y += 1;
            height -= 2;
        }
        x = this.parent.isOnRight() ? size.x - borderRight - width : borderLeft;
        drawBackground(gc, shape, x, y, width, height, defaultBackground, image);
    }

    @objid ("2523a4d8-2fd6-4f89-99de-2b89f843bc69")
    private void drawBackground(GC gc, int[] shape, int x, int y, int width, int height, Color defaultBackground, Image image) {
        Region clipping = null, region = null;
        if (shape != null) {
            clipping = new Region();
            gc.getClipping(clipping);
            region = new Region();
            region.add(shape);
            region.intersect(clipping);
            gc.setClipping(region);
        }
        if (image != null) {
            // draw the background image in shape
            gc.setBackground(defaultBackground);
            gc.fillRectangle(x, y, width, height);
            Rectangle imageRect = image.getBounds();
            gc.drawImage(image, imageRect.x, imageRect.y, imageRect.width, imageRect.height, x, y, width, height);
        } else {
            // draw a solid background using default background in shape
            if ((this.parent.getStyle() & SWT.NO_BACKGROUND) != 0
                    || !defaultBackground.equals(this.parent.getBackground())) {
                gc.setBackground(defaultBackground);
                gc.fillRectangle(x, y, width, height);
            }
        }
        if (shape != null) {
            gc.setClipping(clipping);
            if (clipping != null) {
                clipping.dispose();
            }
            if (region != null) {
                region.dispose();
            }
        }
    }

/*
     * Draw the border of the tab
     * 
     * @param gc
     * 
     * @param shape
     */
    @objid ("c1e3de8e-f412-4f56-9b6f-6d3dfdb7132e")
    private void drawBorder(GC gc, int[] shape) {
        gc.setForeground(this.parent.getDisplay().getSystemColor(BORDER1_COLOR));
        gc.drawPolyline(shape);
    }

    @objid ("0de36eed-7892-48b0-bd5a-8f0f60cfcd2c")
    private void drawBody(GC gc, Rectangle bounds) {
        Point size = new Point(bounds.width, bounds.height);
        int selectedIndex = this.parent.getSelectedIndex();
        int tabWidth = this.parent.getTabWidth();
        
        int borderTop = this.parent.isBorderVisible() ? 1 : 0;
        int borderBottom = borderTop;
        
        int borderLeft = this.parent.isOnRight() ? borderTop : 0;
        int borderRight = this.parent.isOnRight() ? 0 : borderTop;
        
        int style = this.parent.getStyle();
        int highlight_header = getHighlightHeader(style);
        int highlight_margin = (style & SWT.FLAT) != 0 ? 0 : 2;
        
        // fill in body
        
        int width = size.x - borderLeft - borderRight - tabWidth - highlight_header - highlight_margin;
        int height = size.y - borderTop - borderBottom - 2 * highlight_margin;
        // Draw highlight margin
        
        if (highlight_margin > 0) {
            int[] shape = null;
            if (this.parent.isOnRight()) {
                int x1 = borderLeft;
                int y1 = borderTop;
                int x2 = size.y - borderBottom - tabWidth - highlight_header;
        
                int y2 = size.y - borderBottom;
                shape = new int[] { x1, y1, x2, y1, x2, y2, x2 - highlight_margin, y2, x2 - highlight_margin,
                        y1 + highlight_margin, x1 + highlight_margin, y1 + highlight_margin, x1 + highlight_margin, y2,
                        x1, y2 };
            } else {
                int x1 = borderLeft + tabWidth + highlight_header;
                int y1 = borderTop;
                int x2 = size.x - borderRight;
                int y2 = size.y - borderBottom;
                shape = new int[] { x1, y1, x2, y1, x2, y2, x1, y2, x1, y1 };
            }
        
            gc.setBackground(selectedIndex == -1 ? this.parent.getBackground() : this.parent.selectionBackground);
            gc.fillPolygon(shape);
        
        }
        // Draw client area
        if ((this.parent.getStyle() & SWT.NO_BACKGROUND) != 0) {
            gc.setBackground(this.parent.getBackground());
            int marginWidth = this.parent.marginWidth;
            int marginHeight = this.parent.marginHeight;
        
            int xClient;
            int yClient = borderTop + marginHeight + highlight_margin;
        
            if (this.parent.isOnRight()) {
                xClient = borderLeft + highlight_margin + marginWidth;
            } else {
                xClient = borderLeft + tabWidth + highlight_header + marginWidth;
            }
            gc.fillRectangle(xClient - marginWidth, yClient - marginHeight, width, height);
        }
        
        // draw 1 pixel border around outside
        if (borderTop > 0) {
            gc.setForeground(this.parent.getDisplay().getSystemColor(BORDER1_COLOR));
            int x1 = this.parent.isOnRight() ? borderLeft - 1 : borderLeft + tabWidth;
            int y1 = borderTop - 1;
        
            int x2 = this.parent.isOnRight() ? size.x - tabWidth - borderRight - 1 : size.x - borderRight;
            int y2 = size.y - borderBottom;
        
            gc.drawLine(x1, y1, x2, y1); // top
            gc.drawLine(x1, y2, x2, y2); // bottom
        
            if (this.parent.isOnRight()) {
                gc.drawLine(x2, y1, x2, y2); // right
            } else {
                gc.drawLine(x1, y1, x1, y2); // left
            }
        }
    }

    @objid ("0a1e3f78-084d-45cf-8b24-90bdf346b89e")
    private void drawChevron(GC gc, Rectangle chevronRect, int chevronImageState) {
        if (chevronRect.width == 0 || chevronRect.height == 0)
            return;
        int selectedIndex = this.parent.getSelectedIndex();
        // draw chevron (10x7)
        Display display = this.parent.getDisplay();
        Point dpi = display.getDPI();
        int fontHeight = 72 * 10 / dpi.y;
        FontData fd = this.parent.getFont().getFontData()[0];
        fd.setHeight(fontHeight);
        Font f = new Font(display, fd);
        int fHeight = f.getFontData()[0].getHeight() * dpi.y / 72;
        int indent = Math.max(2, (chevronRect.height - fHeight - 4) / 2);
        int x = chevronRect.x + 2;
        int y = chevronRect.y + indent;
        int count;
        int itemCount = this.parent.getItemCount();
        if (this.parent.isSingle()) {
            count = selectedIndex == -1 ? itemCount : itemCount - 1;
        } else {
            int showCount = 0;
            while (showCount < this.parent.priority.length
                    && this.parent.getItems()[this.parent.priority[showCount]].isShowing()) {
                showCount++;
            }
            count = itemCount - showCount;
        }
        String chevronString = count > 99 ? "99+" : String.valueOf(count); //$NON-NLS-1$
        switch (chevronImageState & (SWT.HOT | SWT.SELECTED)) {
        case SWT.NONE: {
            Color chevronBorder = this.parent.isSingle() ? this.parent.getSelectionForeground()
                    : this.parent.getForeground();
            gc.setForeground(chevronBorder);
            gc.setFont(f);
            gc.drawLine(x, y, x + 2, y + 2);
            gc.drawLine(x + 2, y + 2, x, y + 4);
            gc.drawLine(x + 1, y, x + 3, y + 2);
            gc.drawLine(x + 3, y + 2, x + 1, y + 4);
            gc.drawLine(x + 4, y, x + 6, y + 2);
            gc.drawLine(x + 6, y + 2, x + 5, y + 4);
            gc.drawLine(x + 5, y, x + 7, y + 2);
            gc.drawLine(x + 7, y + 2, x + 4, y + 4);
            gc.drawString(chevronString, x + 7, y + 3, true);
            break;
        }
        case SWT.HOT: {
            gc.setForeground(display.getSystemColor(BUTTON_BORDER));
            gc.setBackground(display.getSystemColor(BUTTON_FILL));
            gc.setFont(f);
            gc.fillRoundRectangle(chevronRect.x, chevronRect.y, chevronRect.width, chevronRect.height, 6, 6);
            gc.drawRoundRectangle(chevronRect.x, chevronRect.y, chevronRect.width - 1, chevronRect.height - 1, 6, 6);
            gc.drawLine(x, y, x + 2, y + 2);
            gc.drawLine(x + 2, y + 2, x, y + 4);
            gc.drawLine(x + 1, y, x + 3, y + 2);
            gc.drawLine(x + 3, y + 2, x + 1, y + 4);
            gc.drawLine(x + 4, y, x + 6, y + 2);
            gc.drawLine(x + 6, y + 2, x + 5, y + 4);
            gc.drawLine(x + 5, y, x + 7, y + 2);
            gc.drawLine(x + 7, y + 2, x + 4, y + 4);
            gc.drawString(chevronString, x + 7, y + 3, true);
            break;
        }
        case SWT.SELECTED: {
            gc.setForeground(display.getSystemColor(BUTTON_BORDER));
            gc.setBackground(display.getSystemColor(BUTTON_FILL));
            gc.setFont(f);
            gc.fillRoundRectangle(chevronRect.x, chevronRect.y, chevronRect.width, chevronRect.height, 6, 6);
            gc.drawRoundRectangle(chevronRect.x, chevronRect.y, chevronRect.width - 1, chevronRect.height - 1, 6, 6);
            gc.drawLine(x + 1, y + 1, x + 3, y + 3);
            gc.drawLine(x + 3, y + 3, x + 1, y + 5);
            gc.drawLine(x + 2, y + 1, x + 4, y + 3);
            gc.drawLine(x + 4, y + 3, x + 2, y + 5);
            gc.drawLine(x + 5, y + 1, x + 7, y + 3);
            gc.drawLine(x + 7, y + 3, x + 6, y + 5);
            gc.drawLine(x + 6, y + 1, x + 8, y + 3);
            gc.drawLine(x + 8, y + 3, x + 5, y + 5);
            gc.drawString(chevronString, x + 8, y + 4, true);
            break;
        }
        }
        f.dispose();
    }

    /**
     * Draw the highlight shadow of a tab
     * @param gc
     * @param bounds
     * @param state
     * @param bottomEdge
     */
    @objid ("63674b61-f94c-4770-8091-a83d1b45849d")
    private void drawHighlight(GC gc, Rectangle bounds, int state, int bottomEdge) {
        int x = bounds.x;
        int y = bounds.y;
        int w = bounds.width;
        int h = bounds.height;
        
        gc.setForeground(this.parent.getDisplay().getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
        
        // draw top horizontal line
        gc.drawLine(x + BEVEL, y + 1, x + w, y + 1);
        
        if (this.parent.isOnRight()) {
            // draw right vertical line highlight
            gc.drawLine(x + w - 1, y + BEVEL, x + w - 1, y + h - BEVEL);
        } else {
            // draw left vertical line highlight
            gc.drawLine(x + 1, y + BEVEL, x + 1, y + h - BEVEL);
        }
    }

/*
     * Draw the unselected border for the receiver on the left.
     * 
     * @param gc
     */
    @objid ("63477b91-a47a-43d3-ac77-aae3b548bdaa")
    private void drawTopUnselectedBorder(GC gc, Rectangle bounds) {
        int x = bounds.x;
        int y = bounds.y;
        
        int w = bounds.width;
        
        int[] shape = null;
        if (this.parent.isOnRight()) {
            shape = new int[4];
            shape[0] = x;
            shape[1] = y;
            shape[2] = x + w - 1;
            shape[3] = y;
        } else {
            shape = new int[4];
            shape[0] = x;
            shape[1] = y;
            shape[2] = x + w - 1;
            shape[3] = y;
        }
        drawBorder(gc, shape);
    }

/*
     * Draw the unselected border for the receiver on the right.
     * 
     * @param gc
     */
    @objid ("b9f8dac0-c433-42a1-9b6b-d494786a1c69")
    private void drawBottomUnselectedBorder(GC gc, Rectangle bounds) {
        int x = bounds.x;
        int y = bounds.y;
        int w = bounds.width;
        int h = bounds.height;
        
        int[] shape = null;
        
        if (this.parent.isOnRight()) {
            shape = new int[4];
            shape[0] = x;
            shape[1] = y + h;
            shape[2] = x + w - 1;
            shape[3] = y + h;
        } else {
            shape = new int[4];
            shape[0] = x;
            shape[1] = y + h;
            shape[2] = x + w - 1;
            shape[3] = y + h;
        
        }
        
        drawBorder(gc, shape);
    }

//
// private void drawMaximize(GC gc, Rectangle maxRect, int maxImageState) {
// if (maxRect.width == 0 || maxRect.height == 0)
// return;
// Display display = this.parent.getDisplay();
// // 5x4 or 7x9
// int x = maxRect.x + (maxRect.width - 10) / 2;
// int y = maxRect.y + 3;
//
// gc.setForeground(display.getSystemColor(BUTTON_BORDER));
// gc.setBackground(display.getSystemColor(BUTTON_FILL));
//
// switch (maxImageState & (SWT.HOT | SWT.SELECTED)) {
// case SWT.NONE: {
//
// gc.fillRectangle(x, y + 3, 5, 4);
// gc.fillRectangle(x + 2, y, 5, 4);
// gc.drawRectangle(x, y + 3, 5, 4);
// gc.drawRectangle(x + 2, y, 5, 4);
// gc.drawLine(x + 3, y + 1, x + 6, y + 1);
// gc.drawLine(x + 1, y + 4, x + 4, y + 4);
//
// break;
// }
// case SWT.HOT: {
// gc.fillRoundRectangle(maxRect.x, maxRect.y, maxRect.width, maxRect.height, 6, 6);
// gc.drawRoundRectangle(maxRect.x, maxRect.y, maxRect.width - 1, maxRect.height - 1, 6, 6);
//
// gc.fillRectangle(x, y + 3, 5, 4);
// gc.fillRectangle(x + 2, y, 5, 4);
// gc.drawRectangle(x, y + 3, 5, 4);
// gc.drawRectangle(x + 2, y, 5, 4);
// gc.drawLine(x + 3, y + 1, x + 6, y + 1);
// gc.drawLine(x + 1, y + 4, x + 4, y + 4);
//
// break;
// }
// case SWT.SELECTED: {
// gc.fillRoundRectangle(maxRect.x, maxRect.y, maxRect.width, maxRect.height, 6, 6);
// gc.drawRoundRectangle(maxRect.x, maxRect.y, maxRect.width - 1, maxRect.height - 1, 6, 6);
//
// gc.fillRectangle(x + 1, y + 4, 5, 4);
// gc.fillRectangle(x + 3, y + 1, 5, 4);
// gc.drawRectangle(x + 1, y + 4, 5, 4);
// gc.drawRectangle(x + 3, y + 1, 5, 4);
// gc.drawLine(x + 4, y + 2, x + 7, y + 2);
// gc.drawLine(x + 2, y + 5, x + 5, y + 5);
//
// break;
// }
// }
// }
//
// private void drawMinimize(GC gc, Rectangle minRect, int minImageState) {
// if (minRect.width == 0 || minRect.height == 0)
// return;
// Display display = this.parent.getDisplay();
// // 5x4 or 9x3
// int x = minRect.x + (minRect.width - 10) / 2;
// int y = minRect.y + 3;
//
// gc.setForeground(display.getSystemColor(BUTTON_BORDER));
// gc.setBackground(display.getSystemColor(BUTTON_FILL));
//
// switch (minImageState & (SWT.HOT | SWT.SELECTED)) {
// case SWT.NONE: {
//
// gc.fillRectangle(x, y, 9, 3);
// gc.drawRectangle(x, y, 9, 3);
//
// break;
// }
// case SWT.HOT: {
// gc.fillRoundRectangle(minRect.x, minRect.y, minRect.width, minRect.height, 6, 6);
// gc.drawRoundRectangle(minRect.x, minRect.y, minRect.width - 1, minRect.height - 1, 6, 6);
//
// gc.fillRectangle(x, y, 9, 3);
// gc.drawRectangle(x, y, 9, 3);
//
// break;
// }
// case SWT.SELECTED: {
// gc.fillRoundRectangle(minRect.x, minRect.y, minRect.width, minRect.height, 6, 6);
// gc.drawRoundRectangle(minRect.x, minRect.y, minRect.width - 1, minRect.height - 1, 6, 6);
//
// gc.fillRectangle(x + 1, y + 1, 9, 3);
// gc.drawRectangle(x + 1, y + 1, 9, 3);
//
// break;
// }
// }
// }
    /**
     * Draw a seleted tab
     * @param itemIndex
     * @param gc
     * @param bounds
     * @param state
     */
    @objid ("1d0dd2a9-b09e-4871-adbe-f57d6f1b180d")
    private void drawSelected(int itemIndex, GC gc, Rectangle bounds, int state) {
        VTabItem item = this.parent.getItems()[itemIndex];
        int x = bounds.x;
        int y = bounds.y;
        int height = bounds.height;
        int width = bounds.width;
        
        int borderTop = this.parent.isBorderVisible() ? 1 : 0;
        int borderBottom = borderTop;
        int borderLeft = this.parent.isOnRight() ? borderTop : 0;
        int borderRight = this.parent.isOnRight() ? 0 : borderTop;
        
        Point size = this.parent.getSize();
        
        int bottomEdge = Math.min(y + height, this.parent.getBottomItemEdge(gc));
        
        // Draw selection border across all tabs
        if ((state & SWT.BACKGROUND) != 0) {
            int highlight_header = getHighlightHeader(this.parent.getStyle());
            int xx = this.parent.isOnRight() ? size.x - borderRight - this.parent.getTabWidth() - highlight_header
                    : borderLeft + this.parent.getTabWidth() + 1;
            int yy = borderTop;
            int ww = highlight_header - 1;
            int hh = size.y - borderTop - borderBottom;
        
            gc.setBackground(this.parent.selectionBackground);
            gc.fillRectangle(xx, yy, ww, hh);
        
            if (this.parent.isSingle()) {
                if (!item.isShowing())
                    return;
            } else {
                // if selected tab scrolled out of view or partially out of view
                // just draw bottom line
                if (!item.isShowing()) {
                    int x1 = Math.max(0, borderLeft - 1);
                    int y1 = (this.parent.isOnRight()) ? y - 1 : y + height;
                    int x2 = size.x - borderRight;
                    gc.setForeground(this.parent.getDisplay().getSystemColor(BORDER1_COLOR));
                    gc.drawLine(x1, y1, x2, y1);
                    return;
                }
        
                // draw selected tab background and outline
                int[] shape = null;
                if (this.parent.isOnRight()) {
                    shape = new int[14];
                    // A
                    shape[0] = x + width - BEVEL;
                    shape[1] = y;
        
                    // B
                    shape[2] = x;
                    shape[3] = y;
        
                    // C
                    shape[4] = x;
                    shape[5] = y + height;
        
                    // D
                    shape[6] = x + width - BEVEL;
                    shape[7] = y + height;
        
                    // E
                    shape[8] = x + width;
                    shape[9] = y + height - BEVEL;
        
                    // F
                    shape[10] = x + width;
                    shape[11] = y + BEVEL;
        
                    // A
                    shape[12] = x + width - BEVEL;
                    shape[13] = y;
        
                } else {
        
                    shape = new int[14];
        
                    // A
                    shape[0] = x + BEVEL;
                    shape[1] = y;
        
                    // B
                    shape[2] = x + width;
                    shape[3] = y;
        
                    // C
                    shape[4] = x + width;
                    shape[5] = y + height;
        
                    // D
                    shape[6] = x + BEVEL;
                    shape[7] = y + height;
        
                    // E
                    shape[8] = x;
                    shape[9] = y + height - BEVEL;
        
                    // F
                    shape[10] = x;
                    shape[11] = y + BEVEL;
        
                    // A
                    shape[12] = x + BEVEL;
                    shape[13] = y;
        
                }
        
                Rectangle clipping = gc.getClipping();
                Rectangle clipBounds = item.getBounds();
                clipBounds.height += 1;
                if (this.parent.isOnRight())
                    clipBounds.y -= 1;
                boolean tabInPaint = clipping.intersects(clipBounds);
        
                if (tabInPaint) {
                    // fill in tab background
        
                    Color defaultBackground = this.parent.selectionBackground;
        
                    Image image = this.parent.selectionBgImage;
        
                    xx = x;
                    yy = this.parent.isOnRight() ? y - 1 : y + 1;
                    ww = width;
                    hh = height;
        
                    drawBackground(gc, shape, xx, yy, ww, hh, defaultBackground, image);
        
                }
        
                // Highlight MUST be drawn before the outline so that outline
                // can cover it in the right spots (start of swoop)
                // otherwise the curve looks jagged
                drawHighlight(gc, bounds, state, bottomEdge);
        
                // draw outline
                // shape[0] = Math.max(0, borderLeft - 1);
                // if (borderTop == 0 && itemIndex == this.parent.firstIndex) {
                // shape[0] = this.parent.onRight ? x + width - 1 : x;
                // shape[5] = shape[3] = shape[1];
                // }
                // shape[shape.length - 2] = size.x - borderRight + 1;
                // for (int i = 0; i < shape.length / 2; i++) {
                // if (shape[2 * i + 1] == y + height + 1)
                // shape[2 * i + 1] -= 1;
                // }
                Color borderColor = this.parent.getDisplay().getSystemColor(BORDER1_COLOR);
        
                gc.setForeground(borderColor);
        
                if (this.parent.isOnRight()) {
                    shape = new int[12];
        
                    // A
                    shape[0] = x - 1;
                    shape[1] = y;
        
                    // B
                    shape[2] = x + width - BEVEL - 1;
                    shape[3] = y;
        
                    // C
                    shape[4] = x + width - 1;
                    shape[5] = y + BEVEL;
        
                    // D
                    shape[6] = x + width - 1;
                    shape[7] = y + height - BEVEL;
        
                    // E
                    shape[8] = x + width - BEVEL - 1;
                    shape[9] = y + height;
        
                    // F
                    shape[10] = x - 1;
                    shape[11] = y + height;
        
                } else {
                    shape = new int[12];
        
                    // A
                    shape[0] = x + width;
                    shape[1] = y;
        
                    // B
                    shape[2] = x + BEVEL;
                    shape[3] = y;
        
                    // C
                    shape[4] = x;
                    shape[5] = y + BEVEL;
        
                    // D
                    shape[6] = x;
                    shape[7] = y + height - BEVEL;
        
                    // E
                    shape[8] = x + BEVEL;
                    shape[9] = y + height;
        
                    // F
                    shape[10] = x + width;
                    shape[11] = y + height;
                }
        
                gc.drawPolyline(shape);
        
                if (!tabInPaint)
                    return;
            }
        }
        
        if ((state & SWT.FOREGROUND) != 0) {
            // draw Image
            Rectangle trim = computeTrim(itemIndex, SWT.NONE, 0, 0, 0, 0);
            int xDraw = x - trim.x;
        
            Image image = item.getImage();
            if (image != null && !image.isDisposed() && this.parent.showUnselectedImage) {
                Rectangle imageBounds = image.getBounds();
                // only draw image if it won't overlap with close button
                int maxImageWidth = x + width - xDraw - (trim.width + trim.x);
        
                if (imageBounds.width < maxImageWidth) {
                    int imageX = xDraw;
                    int imageHeight = imageBounds.height;
                    int imageY = y + (height - imageHeight) / 2;
                    imageY += this.parent.isOnRight() ? -1 : 1;
                    int imageWidth = imageBounds.width * imageHeight / imageBounds.height;
                    gc.drawImage(image, imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height, imageX,
                            imageY, imageWidth, imageHeight);
                    xDraw += imageWidth + INTERNAL_SPACING;
                }
            }
        
            // draw Text
            int textWidth = x + width - xDraw; // rightEdge - xDraw -
            // (trim.width +
            // trim.x);
            if (!this.parent.isSingle() && item.closeRect.width > 0)
                textWidth -= item.closeRect.width + INTERNAL_SPACING;
            if (textWidth > 0) {
                Font gcFont = gc.getFont();
                gc.setFont(item.font == null ? this.parent.getFont() : item.font);
        
                if (item.shortenedText == null || item.shortenedTextWidth != textWidth) {
                    item.shortenedText = shortenText(gc, item.getText(), textWidth);
                    item.shortenedTextWidth = textWidth;
                }
                Point extent = gc.textExtent(item.shortenedText, FLAGS);
                int textY = y + (height - 1 - extent.y) / 2;
                textY += 1;
        
                gc.setForeground(this.parent.selectionForeground);
                gc.drawText(item.shortenedText, xDraw, textY, FLAGS);
                gc.setFont(gcFont);
        
                // draw a Focus rectangle
                if (this.parent.isFocusControl()) {
                    Display display = this.parent.getDisplay();
                    if (this.parent.isSimple() || this.parent.isSingle()) {
                        gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
                        gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
                        gc.drawFocus(xDraw - 1, textY - 1, extent.x + 2, extent.y + 2);
                    } else {
                        gc.setForeground(display.getSystemColor(BUTTON_BORDER));
                        gc.drawLine(xDraw, textY + extent.y + 1, xDraw + extent.x + 1, textY + extent.y + 1);
                    }
                }
            }
        
        }
    }

    @objid ("968a3934-bd9a-4773-a060-52bdfccbb6bf")
    private void drawTabArea(GC gc) {
        Point size = this.parent.getSize();
        int[] shape = null;
        Color borderColor = this.parent.getDisplay().getSystemColor(BORDER1_COLOR);
        int tabWidth = this.parent.getTabWidth();
        int style = this.parent.getStyle();
        
        int borderTop = this.parent.isBorderVisible() ? 1 : 0;
        int borderBottom = borderTop;
        
        int borderLeft = this.parent.isOnRight() ? borderTop : 0;
        int borderRight = this.parent.isOnRight() ? 0 : borderTop;
        
        int selectedIndex = this.parent.getSelectedIndex();
        int highlight_header = getHighlightHeader(style);
        if (tabWidth == 0) {
            if ((style & SWT.FLAT) != 0 && (style & SWT.BORDER) == 0)
                return;
            int y1 = borderTop - 1;
            int y2 = size.y - borderBottom;
            int x1 = this.parent.isOnRight() ? size.x - borderRight - highlight_header - 1
                    : borderLeft + highlight_header;
            int x2 = this.parent.isOnRight() ? size.x - borderRight : borderLeft;
            if (borderLeft > 0 && this.parent.isOnRight())
                y2 -= 1;
        
            shape = new int[] { x1, y1, x1, y2, x2, y2, x2, y1 };
        
            gc.setBackground(selectedIndex == -1 ? this.parent.getBackground() : this.parent.selectionBackground);
            gc.fillPolygon(shape);
        
            // draw 1 pixel border
            if (borderLeft > 0) {
                gc.setForeground(borderColor);
                gc.drawPolyline(shape);
            }
            return;
        }
        
        int y = Math.max(0, borderTop - 1);
        int x = this.parent.isOnRight() ? size.x - borderRight - tabWidth : borderLeft;
        int height = size.y - borderTop - borderBottom + 1;
        int width = tabWidth - 1;
        
        // Draw Tab Header
        if (this.parent.isOnRight()) {
        
            shape = new int[10];
        
            shape[0] = x + size.x;
            shape[1] = y;
            shape[2] = x + size.x - width;
            shape[3] = y;
            shape[4] = x + size.x - width;
            shape[5] = y + height;
            shape[6] = x + size.x;
            shape[7] = y + height;
            shape[8] = x + size.x;
            shape[9] = y;
        
        } else {
        
            shape = new int[10];
        
            shape[0] = x;
            shape[1] = y;
            shape[2] = x + width;
            shape[3] = y;
            shape[4] = x + width;
            shape[5] = y + height;
            shape[6] = x;
            shape[7] = y + height;
            shape[8] = x;
            shape[9] = y;
        }
        // Fill in background
        boolean single = this.parent.isSingle();
        boolean bkSelected = single && selectedIndex != -1;
        drawBackground(gc, shape, bkSelected);
        // Fill in parent background for non-rectangular shape
        Region r = new Region();
        r.add(new Rectangle(x, y, width + 1, height + 1));
        r.subtract(shape);
        gc.setBackground(this.parent.getParent().getBackground());
        fillRegion(gc, r);
        r.dispose();
        
        // Draw selected tab
        if (selectedIndex == -1) {
            // if no selected tab - draw line across bottom of all tabs
            int y1 = borderTop;
            int x1 = (this.parent.isOnRight()) ? size.x - borderRight - tabWidth - 1 : borderLeft + tabWidth;
            int y2 = size.y - borderBottom;
            gc.setForeground(borderColor);
            gc.drawLine(x1, y1, x1, y2);
        }
        
        // Draw border line
        if (borderTop > 0) {
            gc.setForeground(borderColor);
            gc.drawPolyline(shape);
        }
    }

    @objid ("bab1f600-7206-438b-b898-f6f1e348ddb5")
    private int getHighlightHeader(int style) {
        return (style & SWT.FLAT) != 0 ? 1 : HIGHLIGHT_HEADER_THICKNESS;
    }

    @objid ("2de5bfda-3b22-4a35-b647-96717a521796")
    private void drawUnselected(int index, GC gc, Rectangle bounds, int state) {
        VTabItem item = this.parent.getItems()[index];
        int x = bounds.x;
        int y = bounds.y;
        int height = bounds.height;
        int width = bounds.width;
        
        // Do not draw partial items
        if (!item.showing)
            return;
        
        Rectangle clipping = gc.getClipping();
        if (!clipping.intersects(bounds))
            return;
        
        if ((state & SWT.BACKGROUND) != 0) {
            if (index >= 0 && index < this.parent.getSelectedIndex())
                drawTopUnselectedBorder(gc, bounds);
            // If it is the last one then draw a line
            if (index > this.parent.getSelectedIndex())
                drawBottomUnselectedBorder(gc, bounds);
        }
        
        if ((state & SWT.FOREGROUND) != 0) {
            // draw Image
            Rectangle trim = computeTrim(index, SWT.NONE, 0, 0, 0, 0);
            int xDraw = x - trim.x;
            Image image = item.getImage();
            if (image != null && !image.isDisposed() && this.parent.showUnselectedImage) {
                Rectangle imageBounds = image.getBounds();
                // only draw image if it won't overlap with close button
                int maxImageWidth = x + width - xDraw - (trim.width + trim.x);
        
                if (imageBounds.width < maxImageWidth) {
                    int imageX = xDraw;
                    int imageHeight = imageBounds.height;
                    int imageY = y + (height - imageHeight) / 2;
                    imageY += this.parent.isOnRight() ? -1 : 1;
                    int imageWidth = imageBounds.width * imageHeight / imageBounds.height;
                    gc.drawImage(image, imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height, imageX,
                            imageY, imageWidth, imageHeight);
                    xDraw += imageWidth + INTERNAL_SPACING;
                }
            }
            // draw Text
            int textWidth = x + width - xDraw - (trim.width + trim.x);
        
            if (textWidth > 0) {
                Font gcFont = gc.getFont();
                gc.setFont(item.font == null ? this.parent.getFont() : item.font);
                if (item.shortenedText == null || item.shortenedTextWidth != textWidth) {
                    item.shortenedText = shortenText(gc, item.getText(), textWidth);
                    item.shortenedTextWidth = textWidth;
                }
                Point extent = gc.textExtent(item.shortenedText, FLAGS);
                int textY = y + (height - extent.y) / 2;
                textY += this.parent.isOnRight() ? -1 : 1;
                gc.setForeground(this.parent.getForeground());
                gc.drawText(item.shortenedText, xDraw, textY, FLAGS);
                gc.setFont(gcFont);
            }
            // draw close
        
        }
    }

    @objid ("96bd8e7e-9868-4ca5-aa3a-3ab8857ca2e1")
    private void fillRegion(GC gc, Region region) {
        // NOTE: region passed in to this function will be modified
        Region clipping = new Region();
        gc.getClipping(clipping);
        region.intersect(clipping);
        gc.setClipping(region);
        gc.fillRectangle(region.getBounds());
        gc.setClipping(clipping);
        clipping.dispose();
    }

    @objid ("eccd6b52-20da-4f10-acee-cabf74894380")
    private String shortenText(GC gc, String text, int width) {
        return useEllipses() ? shortenText(gc, text, width, ELLIPSIS) : shortenText(gc, text, width, ""); //$NON-NLS-1$
    }

    @objid ("15891384-281f-4743-bd58-96fb996ec983")
    private String shortenText(GC gc, String text, int width, String ellipses) {
        if (gc.textExtent(text, FLAGS).x <= width)
            return text;
        int ellipseWidth = gc.textExtent(ellipses, FLAGS).x;
        int length = text.length();
        TextLayout layout = new TextLayout(this.parent.getDisplay());
        layout.setText(text);
        int end = layout.getPreviousOffset(length, SWT.MOVEMENT_CLUSTER);
        while (end > 0) {
            text = text.substring(0, end);
            int l = gc.textExtent(text, FLAGS).x;
            if (l + ellipseWidth <= width) {
                break;
            }
            end = layout.getPreviousOffset(end, SWT.MOVEMENT_CLUSTER);
        }
        layout.dispose();
        return end == 0 ? text.substring(0, 1) : text + ellipses;
    }

/*
     * Return whether to use ellipses or just truncate labels
     */
    @objid ("648887a9-0baf-48fd-9953-822041c8972d")
    private boolean useEllipses() {
        return this.parent.isSimple();
    }

}

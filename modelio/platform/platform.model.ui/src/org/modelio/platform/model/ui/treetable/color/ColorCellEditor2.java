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

package org.modelio.platform.model.ui.treetable.color;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

/**
 * This class is a clone of SWT/JFace original org.eclipse.jface.viewers.ColorCellEditor modified to center the color
 * chooser dialog when it pops. The original class is marked @noextend therefore we choose to use a copy instead of
 * inheriting and specialising
 * 
 * @author pvlaemyn
 */
@objid ("6b1eba10-1eba-11e2-9382-bc305ba4815c")
public class ColorCellEditor2 extends DialogCellEditor {
    /**
     * The default extent in pixels.
     */
    @objid ("6b21a040-1eba-11e2-9382-bc305ba4815c")
    private static final int DEFAULT_EXTENT = 16;

    /**
     * Gap between between image and text in pixels.
     */
    @objid ("6b28a520-1eba-11e2-9382-bc305ba4815c")
    private static final int GAP = 6;

    /**
     * The composite widget containing the color and RGB label widgets
     */
    @objid ("6b28cc30-1eba-11e2-9382-bc305ba4815c")
    private Composite composite;

    /**
     * The label widget showing the current color.
     */
    @objid ("6b296870-1eba-11e2-9382-bc305ba4815c")
    private Label colorLabel;

    /**
     * The label widget showing the RGB values.
     */
    @objid ("6b296872-1eba-11e2-9382-bc305ba4815c")
    private Label rgbLabel;

    /**
     * The image.
     */
    @objid ("6b298f81-1eba-11e2-9382-bc305ba4815c")
    private Image image;

    @objid ("6b298f83-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Control createContents(Composite cell) {
        Color bg = cell.getBackground();
        this.composite = new Composite(cell, getStyle());
        this.composite.setBackground(bg);
        this.composite.setLayout(new ColorCellLayout());
        this.colorLabel = new Label(this.composite, SWT.LEFT);
        this.colorLabel.setBackground(bg);
        this.rgbLabel = new Label(this.composite, SWT.LEFT);
        this.rgbLabel.setBackground(bg);
        this.rgbLabel.setFont(cell.getFont());
        return this.composite;
    }

    @objid ("6b2c0080-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        final Display display = cellEditorWindow.getDisplay();
        final Shell centerShell = new Shell(cellEditorWindow.getShell(), SWT.NO_TRIM);
        centerShell.setLocation(display.getCursorLocation());
        
        ColorDialog dialog = new ColorDialog(centerShell, SWT.NONE);
        
        Object value = getValue();
        if (value != null) {
            dialog.setRGB((RGB) value);
        }
        dialog.open();
        return dialog.getRGB();
    }

    @objid ("6b2c2790-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected void updateContents(Object value) {
        RGB rgb = (RGB) value;
        // XXX: We don't have a value the first time this method is called".
        if (rgb == null) {
            rgb = new RGB(0, 0, 0);
        }
        // XXX: Workaround for 1FMQ0P3: SWT:ALL - TableItem.setImage doesn't work if using the identical image."
        if (this.image != null) {
            this.image.dispose();
        }
        
        ImageData id = createColorImage(this.colorLabel.getParent().getParent(), rgb);
        ImageData mask = id.getTransparencyMask();
        this.image = new Image(this.colorLabel.getDisplay(), id, mask);
        this.colorLabel.setImage(this.image);
        
        this.rgbLabel.setText("(" + rgb.red + "," + rgb.green + "," + rgb.blue + ")");//$NON-NLS-4$//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
    }

    /**
     * Creates a new color cell editor parented under the given control. The cell editor value is black (
     * <code>RGB(0,0,0)</code>) initially, and has no validator.
     * 
     * @param parent the parent control
     */
    @objid ("6b2c2793-1eba-11e2-9382-bc305ba4815c")
    public ColorCellEditor2(final Composite parent) {
        this(parent, SWT.NONE);
    }

    /**
     * Creates a new color cell editor parented under the given control. The cell editor value is black (
     * <code>RGB(0,0,0)</code>) initially, and has no validator.
     * 
     * @param parent the parent control
     * @param style the style bits
     * @since 2.1
     */
    @objid ("6b2c75b1-1eba-11e2-9382-bc305ba4815c")
    public ColorCellEditor2(final Composite parent, final int style) {
        super(parent, style);
        doSetValue(new RGB(0, 0, 0));
    }

    /**
     * Creates and returns the color image data for the given control and RGB value. The image's size is either the
     * control's item extent or the cell editor's default extent, which is 16 pixels square.
     * 
     * @param w the control
     * @param color the color
     */
    @objid ("6b2cc3d1-1eba-11e2-9382-bc305ba4815c")
    private ImageData createColorImage(final Control w, final RGB color) {
        GC gc = new GC(w);
        FontMetrics fm = gc.getFontMetrics();
        int size = fm.getAscent();
        gc.dispose();
        
        int indent = 6;
        int extent = ColorCellEditor2.DEFAULT_EXTENT;
        if (w instanceof Table) {
            extent = ((Table) w).getItemHeight() - 1;
        } else if (w instanceof Tree) {
            extent = ((Tree) w).getItemHeight() - 1;
        } else if (w instanceof TableTree) {
            extent = ((TableTree) w).getItemHeight() - 1;
        }
        
        if (size > extent) {
            size = extent;
        }
        
        int width = indent + size;
        int height = extent;
        
        int xoffset = indent;
        int yoffset = (height - size) / 2;
        
        RGB black = new RGB(0, 0, 0);
        PaletteData dataPalette = new PaletteData(new RGB[] { black, black, color });
        ImageData data = new ImageData(width, height, 4, dataPalette);
        data.transparentPixel = 0;
        
        int end = size - 1;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (x == 0 || y == 0 || x == end || y == end) {
                    data.setPixel(x + xoffset, y + yoffset, 1);
                } else {
                    data.setPixel(x + xoffset, y + yoffset, 2);
                }
            }
        }
        return data;
    }

    @objid ("6b2d11f2-1eba-11e2-9382-bc305ba4815c")
    @Override
    public void dispose() {
        if (this.image != null) {
            this.image.dispose();
            this.image = null;
        }
        super.dispose();
    }

    /**
     * Internal class for laying out this cell editor.
     */
    @objid ("6b2d3900-1eba-11e2-9382-bc305ba4815c")
    private class ColorCellLayout extends Layout {
        @objid ("6b2d3902-1eba-11e2-9382-bc305ba4815c")
        @Override
        public Point computeSize(final Composite editor, final int wHint, final int hHint, final boolean force) {
            if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
                return new Point(wHint, hHint);
            }
            Point colorSize = ColorCellEditor2.this.colorLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            Point rgbSize = ColorCellEditor2.this.rgbLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            return new Point(colorSize.x + ColorCellEditor2.GAP + rgbSize.x, Math.max(colorSize.y, rgbSize.y));
        }

        @objid ("6b2dd540-1eba-11e2-9382-bc305ba4815c")
        @Override
        public void layout(final Composite editor, final boolean force) {
            Rectangle bounds = editor.getClientArea();
            Point colorSize = ColorCellEditor2.this.colorLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            Point rgbSize = ColorCellEditor2.this.rgbLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            int ty = (bounds.height - rgbSize.y) / 2;
            if (ty < 0) {
                ty = 0;
            }
            ColorCellEditor2.this.colorLabel.setBounds(-1, 0, colorSize.x, colorSize.y);
            ColorCellEditor2.this.rgbLabel.setBounds(colorSize.x + ColorCellEditor2.GAP - 1, ty, bounds.width - colorSize.x - ColorCellEditor2.GAP, bounds.height);
        }

    }

}

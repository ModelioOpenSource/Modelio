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

package org.modelio.diagram.elements.core.figures.html.flyingsaucer.impl;

import java.awt.BasicStroke;
import java.awt.RenderingHints.Key;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.xhtmlrenderer.css.parser.FSColor;
import org.xhtmlrenderer.css.parser.FSRGBColor;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.OutputDevice;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.render.AbstractOutputDevice;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.render.BorderPainter;
import org.xhtmlrenderer.render.FSFont;
import org.xhtmlrenderer.render.InlineText;
import org.xhtmlrenderer.render.RenderingContext;
import org.xhtmlrenderer.swt.FormControlReplacementElement;
import org.xhtmlrenderer.swt.ImageReplacedElement;
import org.xhtmlrenderer.swt.SWTFSFont;
import org.xhtmlrenderer.swt.SWTFSImage;

/**
 * Implementation of {@link OutputDevice} for SWT.
 * 
 * @author Vianney le Clément
 */
@objid ("243884c6-3dbf-4e5b-b877-98bf81b02311")
class GefFsOutputDevice extends AbstractOutputDevice {
    @objid ("467789bb-d7ec-49f5-afa0-da380a1bd39e")
    private final Graphics _gc;

    @objid ("0e8a1ac2-1402-46eb-b177-122085098e95")
    private Path _clippingPath = null;

    @objid ("64f799e4-6c5a-4d6c-85ac-c99659899b95")
    private Area _clippingArea = null;

    @objid ("fd0b2510-2d83-4908-a9ae-d7a19fa024ab")
    private Color _color = null;

// private Transform _transform = null;
    @objid ("0190f551-08a3-4a14-ab9f-a13b2de76644")
    private Stroke _stroke = null;

    @objid ("f6d5d8eb-0d4f-4154-bc63-88941cda79a5")
    private Device device;

    @objid ("115732b7-7472-4ba9-83db-06b237c644fa")
    private FSRGBColor _awt_color = null;

    /**
     * @param graphics a draw2d context
     * @param device a SWT Device
     */
    @objid ("9981f317-8feb-48d6-a835-7d05718d5d1a")
    public GefFsOutputDevice(Graphics graphics, Device device) {
        this._gc = graphics;
        this.device = device;
    }

    /**
     * @return the Graphical Context associated with this OutputDevice
     */
    @objid ("fd7ce588-f77b-4d8f-8eb7-7fbaef5c4a24")
    public Graphics getGC() {
        return this._gc;
    }

    /**
     * Clean used resources.
     */
    @objid ("254aa5a1-35b9-4300-9387-eb93f6c1a7db")
    public void clean() {
        if (this._clippingPath != null) {
            // this._gc.setClip((Rectangle)null);
            this._clippingPath.dispose();
            this._clippingPath = null;
            this._clippingArea = null;
        }
        if (this._color != null) {
            this._color.dispose();
            this._color = null;
        }
        /*
         * if (this._transform != null) { this._gc.setTransform(null); this._transform.dispose(); }
         */
    }

    @objid ("6cc380f9-a869-4fe6-8a17-bfeca7d44546")
    @Override
    public void clip(java.awt.Shape s) {
        if (s == null) {
            return;
        }
        if (this._clippingArea == null) {
            setClip(s);
        } else {
            Area a = new Area(this._clippingArea);
            a.intersect(new Area(s));
            setClip(a);
        }
    }

    @objid ("e18558de-bf46-4ffb-abe7-a0cf3ae8fe31")
    @Override
    public void setClip(java.awt.Shape s) {
        Path path = convertToPath(s);
        if (path == null) {
            this._gc.setClip((Rectangle) null);
        } else {
            this._gc.setClip(path);
        }
        if (this._clippingPath != null) {
            this._clippingPath.dispose();
        }
        this._clippingPath = path;
        this._clippingArea = (s == null ? null : new Area(s));
    }

    @objid ("108790e9-8df5-474f-a32a-b88647a4e375")
    @Override
    public java.awt.Shape getClip() {
        return this._clippingArea;
    }

    @objid ("bc2989a9-15e3-4f62-b568-d13ef47d5645")
    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        this._gc.drawLine(x1, y1, x2, y2);
    }

    @objid ("a2de1f5b-4372-4ba4-bcd7-6a9639f12fef")
    @Override
    public void drawBorderLine(java.awt.Rectangle bounds, int side, int lineWidth, boolean solid) {
        int x = bounds.x;
        int y = bounds.y;
        int w = bounds.width;
        int h = bounds.height;
        
        int adj = solid ? 1 : 0;
        
        if (side == BorderPainter.TOP) {
            drawLine(x, y + (lineWidth / 2), x + w - adj, y + (lineWidth / 2));
        } else if (side == BorderPainter.LEFT) {
            drawLine(x + (lineWidth / 2), y, x + (lineWidth / 2), y + h - adj);
        } else if (side == BorderPainter.RIGHT) {
            int offset = (lineWidth / 2);
            if (lineWidth % 2 != 0) {
                offset += 1;
            }
            drawLine(x + w - offset, y, x + w - offset, y + h - adj);
        } else if (side == BorderPainter.BOTTOM) {
            int offset = (lineWidth / 2);
            if (lineWidth % 2 != 0) {
                offset += 1;
            }
            drawLine(x, y + h - offset, x + w - adj, y + h - offset);
        }
    }

    @objid ("376c36bc-08c8-4fbd-9c0d-20c0dea5ce60")
    @Override
    public void drawImage(FSImage image, int x, int y) {
        Image img = ((SWTFSImage) image).getImage();
        if (img == null) {
            int width = image.getWidth();
            int height = image.getHeight();
            Color oldBG = this._gc.getBackgroundColor();
            Color oldFG = this._gc.getForegroundColor();
            this._gc.setBackgroundColor(getDevice().getSystemColor(SWT.COLOR_WHITE));
            this._gc.setForegroundColor(getDevice().getSystemColor(SWT.COLOR_BLACK));
            this._gc.fillRectangle(x, y, width, height);
            this._gc.drawRectangle(x, y, width, height);
            this._gc.drawLine(x, y, x + width - 1, y + height - 1);
            this._gc.drawLine(x, y + height - 1, x + width - 1, y);
            this._gc.setBackgroundColor(oldBG);
            this._gc.setForegroundColor(oldFG);
        } else {
            org.eclipse.swt.graphics.Rectangle bounds = img.getBounds();
            this._gc.drawImage(img, 0, 0, bounds.width, bounds.height, x, y, image
                    .getWidth(), image.getHeight());
        }
    }

    @objid ("609618bd-14d5-41b8-951d-6280cfc6795a")
    private Device getDevice() {
        return this.device;
    }

    @objid ("871528ac-9d40-4174-9025-50fea09e3006")
    @Override
    public void drawOval(int x, int y, int width, int height) {
        this._gc.drawOval(x, y, width, height);
    }

    @objid ("bb85459c-5400-4479-a4c8-c76aa4cdb798")
    @Override
    public void drawRect(int x, int y, int width, int height) {
        this._gc.drawRectangle(x, y, width, height);
    }

    @objid ("d9fc98b9-b058-40d4-a2e4-760c74291eae")
    @Override
    public void fill(java.awt.Shape s) {
        Path p = convertToPath(s);
        this._gc.fillPath(p);
        p.dispose();
    }

    @objid ("19e1ed78-f548-4736-a726-217091878790")
    @Override
    public void fillOval(int x, int y, int width, int height) {
        this._gc.fillOval(x, y, width, height);
    }

    @objid ("1291478b-b6b6-41f8-8c00-26301160dfcf")
    @Override
    public void fillRect(int x, int y, int width, int height) {
        this._gc.fillRectangle(x, y, width, height);
    }

    @objid ("8da7d308-6cd4-43a2-b15a-9dd274c46c36")
    @Override
    public void paintReplacedElement(RenderingContext c, BlockBox box) {
        ReplacedElement replaced = box.getReplacedElement();
        java.awt.Point location = replaced.getLocation();
        if (replaced instanceof ImageReplacedElement) {
            drawImage(((ImageReplacedElement) replaced).getImage(), location.x,
                    location.y);
        } else if (replaced instanceof FormControlReplacementElement) {
            org.xhtmlrenderer.simple.xhtml.swt.SWTFormControl swtControl = ((FormControlReplacementElement) replaced).getControl();
            swtControl.getSWTControl().setVisible(true);
        }
    }

    @objid ("d2f64f96-a458-484a-a1c2-cdaac9c72cbb")
    private void setFSRGBColor(FSRGBColor color) {
        if (color.equals(this._awt_color)) {
            return;
        }
        
        Color col = new Color(getDevice(), color.getRed(),
                color.getGreen(), color.getBlue());
        this._gc.setForegroundColor(col);
        this._gc.setBackgroundColor(col);
        // this._gc.setAlpha(color.getAlpha());
        if (this._color != null) {
            this._color.dispose();
        }
        this._color = col;
        this._awt_color = color;
    }

    @objid ("cd170c01-bd47-4dcf-bce3-ec0def031fdd")
    @Override
    public void setFont(FSFont font) {
        this._gc.setFont(((SWTFSFont) font).getSWTFont());
    }

    @objid ("fb1efeb6-9e07-40d8-9ad6-72e8372df9f7")
    @Override
    public void setColor(FSColor color) {
        if (color instanceof FSRGBColor) {
            FSRGBColor rgb = (FSRGBColor) color;
            setFSRGBColor(rgb); // new java.awt.Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue()));
        } else {
            throw new IllegalArgumentException("unsupported color class " + color.getClass().getName());
        }
    }

    @objid ("d1e9da89-7d5a-4cbe-a666-8dc48aed2c03")
    @Override
    public Stroke getStroke() {
        return this._stroke;
    }

    @objid ("3d00f50f-38e0-4052-8389-ddeaf8e5a190")
    @Override
    public void setStroke(Stroke s) {
        this._stroke = s;
        
        /*
         * Code borrowed from SwingWT
         */
        if (s == null) {
            this._gc.setLineWidth(1);
            this._gc.setLineCap(SWT.CAP_SQUARE);
            this._gc.setLineJoin(SWT.JOIN_MITER);
            this._gc.setLineDash((int[]) null);
            return;
        }
        
        if (!(s instanceof BasicStroke)) {
            return;
        }
        
        BasicStroke bs = (BasicStroke) s;
        
        // Setup the line width
        this._gc.setLineWidth((int) bs.getLineWidth());
        
        // Setup the line cap
        int gcCap = SWT.CAP_SQUARE;
        switch (bs.getEndCap()) {
        case BasicStroke.CAP_BUTT:
            gcCap = SWT.CAP_FLAT;
            break;
        case BasicStroke.CAP_ROUND:
            gcCap = SWT.CAP_ROUND;
            break;
        case BasicStroke.CAP_SQUARE:
            gcCap = SWT.CAP_SQUARE;
            break;
        default:
            break;
        }
        this._gc.setLineCap(gcCap);
        
        // Setup the line Join
        int gcJoin = SWT.JOIN_MITER;
        switch (bs.getLineJoin()) {
        case BasicStroke.JOIN_BEVEL:
            gcJoin = SWT.JOIN_BEVEL;
            break;
        case BasicStroke.JOIN_MITER:
            gcJoin = SWT.JOIN_MITER;
            break;
        case BasicStroke.JOIN_ROUND:
            gcJoin = SWT.JOIN_ROUND;
            break;
        default:
            break;
        }
        this._gc.setLineJoin(gcJoin);
        
        float d[] = bs.getDashArray();
        int[] dashes = new int[d.length];
        for (int i = 0; i < d.length; i++) {
            dashes[i] = (int) d[i];
        }
        this._gc.setLineDash(dashes);
    }

    @objid ("9e23bdba-1ef3-4e8f-ae12-e4cb2361ecf6")
    @Override
    public void translate(double tx, double ty) {
        this._gc.translate((float) tx, (float) ty);
        
        if (this._clippingArea != null) {
            AffineTransform t = new AffineTransform();
            t.translate(-tx, -ty);
            this._clippingArea.transform(t);
        }
    }

    @objid ("fe677246-53ad-4722-9a4c-d9e5482d5105")
    @Override
    public Object getRenderingHint(Key key) {
        if (RenderingHints.KEY_ANTIALIASING.equals(key)) {
            switch (this._gc.getAntialias()) {
            default:
            case SWT.DEFAULT:
                return RenderingHints.VALUE_ANTIALIAS_DEFAULT;
            case SWT.OFF:
                return RenderingHints.VALUE_ANTIALIAS_OFF;
            case SWT.ON:
                return RenderingHints.VALUE_ANTIALIAS_ON;
            }
        }
        return null;
    }

    @objid ("33ffd955-3a83-4582-896a-aaaac9ffc121")
    @Override
    public void setRenderingHint(Key key, Object value) {
        if (RenderingHints.KEY_ANTIALIASING.equals(key)) {
            int antialias = SWT.DEFAULT;
            if (RenderingHints.VALUE_ANTIALIAS_OFF.equals(value)) {
                antialias = SWT.OFF;
            } else if (RenderingHints.VALUE_ANTIALIAS_ON.equals(value)) {
                antialias = SWT.ON;
            }
            this._gc.setAntialias(antialias);
        }
    }

    /**
     * Convert an AWT Shape to an SWT Path.
     * 
     * @param shape an AWT Shape
     * @return the SWT Path or <code>null</code> if <code>shape == null</code>
     */
    @objid ("0104944c-fae7-4d33-b3bb-0ad70abbb08c")
    private Path convertToPath(java.awt.Shape shape) {
        if (shape == null) {
            return null;
        }
        Path path = new Path(Display.getCurrent());
        PathIterator iter = shape.getPathIterator(null);
        float[] coords = new float[6];
        while (!iter.isDone()) {
            int op = iter.currentSegment(coords);
            switch (op) {
            case PathIterator.SEG_MOVETO:
                path.moveTo(coords[0], coords[1]);
                break;
            case PathIterator.SEG_LINETO:
                path.lineTo(coords[0], coords[1]);
                break;
            case PathIterator.SEG_QUADTO:
                path.quadTo(coords[0], coords[1], coords[2], coords[3]);
                break;
            case PathIterator.SEG_CUBICTO:
                path.cubicTo(coords[0], coords[1], coords[2], coords[3],
                        coords[4], coords[5]);
                break;
            case PathIterator.SEG_CLOSE:
                path.close();
                break;
            default:
                break;
            }
            iter.next();
        }
        return path;
    }

    @objid ("96845c1a-0f3b-4523-8e7c-84c0dd04c9ff")
    @Override
    public void drawSelection(RenderingContext c, InlineText inlineText) {
        // TODO support selection drawing
    }

    @objid ("f9bb3300-8b3a-4ab2-8129-cb2d965573d5")
    @Override
    public boolean isSupportsSelection() {
        // TODO support selection drawing
        return false;
    }

    @objid ("bca7e247-052c-47c5-b34f-188395df6bf8")
    @Override
    public boolean isSupportsCMYKColors() {
        return false; // To change body of implemented methods use File | Settings | File Templates.
    }

}

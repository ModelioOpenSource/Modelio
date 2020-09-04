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

package org.modelio.diagram.elements.core.figures.rotated;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.TextLayout;

/**
 * A Graphics object able to rotate all operations based on the current rotation angle.
 * <p>
 * Works around {@link org.eclipse.draw2d.SWTGraphics} unable to keep rotations across {@link #pushState()}.
 * 
 * @since Modelio 3.4
 */
@objid ("cf4ba714-4055-417b-89e7-5ee998f6e3f5")
class RotatedGraphics extends Graphics {
    @objid ("617744de-71eb-41ae-9c5d-3254b6841249")
    private int angle;

    @objid ("7fc788a4-f7a9-4a5b-b285-f2159ce73f07")
    private double cos;

    @objid ("710f0b8c-36c9-4d36-aa04-a839312d8f07")
    private double sin;

    @objid ("dab548dc-62f8-4cb9-a5aa-19a7fa5bc322")
    private Graphics g;

    @objid ("31c97933-43cd-42c2-9cc9-fee16039be12")
    public RotatedGraphics(Graphics g, int angle) {
        this.g = g;
        this.angle = angle;
        
        double rad = Math.toRadians(angle);
        this.cos = Math.cos(rad);
        this.sin = Math.sin(rad);
    }

    @objid ("7eb9a650-eb66-4ccd-84d2-199288b4873c")
    @Override
    public void clipPath(Path path) {
        PathData d = path.getPathData();
        
        for (int i=0; i<d.points.length; i+=2) {
            d.points[i] = ftx(d.points[i], d.points[i+1]);
            d.points[i+1] = fty(d.points[i], d.points[i+1]);
        }
        
        this.g.clipPath(new Path(path.getDevice(), d));
    }

    @objid ("c1119808-08ec-46f4-8d9a-cb9e6e23564b")
    @Override
    public void clipRect(Rectangle r) {
        // TODO to be done, currently clipping is ignored.
        // this may have some side drawing effects.
    }

    @objid ("ec330f1c-47f9-48d5-9a9d-f44d569bc5d7")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("c02159d7-d260-4d39-b829-c59d4db0e2d8")
    @Override
    public void drawArc(int x, int y, int w, int h, int offset, int length) {
        this.g.drawArc(tx(x, y), ty(x,y), w, h, offset+this.angle, length+this.angle);
    }

    @objid ("2b4df84a-0c32-4316-8d41-dc2dbfd67deb")
    @Override
    public void drawFocus(int x, int y, int w, int h) {
        int[] points = trRectangle(x, y, w, h);
        this.g.drawPolygon(points);
    }

    @objid ("48563677-462e-4b92-a51d-7313b3847c41")
    @Override
    public void drawImage(Image srcImage, int x, int y) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawImage(srcImage, x, y);
        this.g.popState();
    }

    @objid ("34e544f0-9b03-4aad-9287-b174f9de9638")
    @Override
    public void drawImage(Image srcImage, int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawImage(srcImage, x1, y1, w1, h1, x2, y2, w2, h2);
        this.g.popState();
    }

    @objid ("2416c9d4-5c5e-4b27-9130-958330827eca")
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        this.g.drawLine(tx(x1,y1), ty(x1,y1), tx(x2,y2), ty(x2,y2));
    }

    @objid ("4f68463f-9c1b-41e1-9f51-5e7ffd70dff5")
    @Override
    public void drawOval(int x, int y, int w, int h) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawOval(x, y, w, h);
        this.g.popState();
    }

    @objid ("a6735397-e769-4ed2-bd8c-069448d53d0d")
    @Override
    public void drawPath(Path path) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawPath(path);
        this.g.popState();
    }

    @objid ("6c6e6875-7565-4538-943e-bb39ae3bb472")
    @Override
    public void drawPoint(int x, int y) {
        this.g.drawPoint(tx(x, y), ty(x, y));
    }

    @objid ("03dee267-bb37-4b86-ad74-dae44514cc02")
    @Override
    public void drawPolygon(PointList points) {
        drawPolygon(points.toIntArray());
    }

    @objid ("541de21c-2f70-46a4-b9c8-85f261ea5508")
    @Override
    public void drawPolygon(int[] points) {
        int[] np = trPoints(points);
        
        this.g.drawPolygon(np);
    }

    @objid ("0e0552a7-81e0-4587-a762-053db317c551")
    @Override
    public void drawPolyline(PointList points) {
        drawPolyline(points.toIntArray());
    }

    @objid ("936edb8c-2fb5-4e1a-ab83-57b748f459ea")
    @Override
    public void drawPolyline(int[] points) {
        int[] np = trPoints(points);
        this.g.drawPolyline(np);
    }

    @objid ("8a5a908c-cbeb-4d86-a240-e665ce24479d")
    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        int[] points = trRectangle(x, y, width, height);
        this.g.drawPolygon(points);
    }

    @objid ("f2d3f665-9244-4dbd-822f-a02544ddf5a9")
    @Override
    public void drawRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawRoundRectangle(r, arcWidth, arcHeight);
        this.g.popState();
    }

    @objid ("f2a28b75-dc6f-460f-8bda-789846c4e246")
    @Override
    public void drawString(String s, int x, int y) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawString(s, x, y);
        this.g.popState();
    }

    @objid ("7d71e918-d817-412b-89bf-0ea91821d255")
    @Override
    public void drawText(String s, int x, int y) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawText(s, x, y);
        this.g.popState();
    }

    @objid ("883e12a2-20b0-471d-8791-4901033edcc1")
    @Override
    public void drawText(String s, int x, int y, int style) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawText(s, x, y, style);
        this.g.popState();
    }

    @objid ("9696bcc4-3a0d-4a9c-a3e6-c4adce335beb")
    @Override
    public void drawTextLayout(TextLayout layout, int x, int y, int selectionStart, int selectionEnd, Color selectionForeground, Color selectionBackground) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.drawTextLayout(layout, x, y, selectionStart, selectionEnd,
                selectionForeground, selectionBackground);
        this.g.popState();
    }

    @objid ("714e58db-a3bd-4a6f-8380-4323f8c3b9fd")
    @Override
    public void fillArc(int x, int y, int w, int h, int offset, int length) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.fillArc(x, y, w, h, offset, length);
        this.g.popState();
    }

    @objid ("bec6f33f-1989-41d0-90d8-f194c809ac9c")
    @Override
    public void fillGradient(int x, int y, int w, int h, boolean vertical) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.fillGradient(x, y, w, h, vertical);
        this.g.popState();
    }

    @objid ("88b7922b-e202-43de-9e00-7df9aa167b30")
    @Override
    public void fillOval(int x, int y, int w, int h) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.fillOval(x, y, w, h);
        this.g.popState();
    }

    @objid ("751337d2-50a8-4c58-816b-aeac6979edfb")
    @Override
    public void fillPath(Path path) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.fillPath(path);
        this.g.popState();
    }

    @objid ("21195808-f076-4627-9f50-de28c02dc091")
    @Override
    public void fillPolygon(PointList points) {
        fillPolygon(points.toIntArray());
    }

    @objid ("a97f408c-59a3-447b-837e-459887862178")
    @Override
    public void fillPolygon(int[] points) {
        this.g.fillPolygon(trPoints(points));
    }

    @objid ("8d826dbe-e181-4ad2-b0f5-81625540eeb9")
    @Override
    public void fillRectangle(int x, int y, int width, int height) {
        this.g.fillPolygon(trRectangle(x, y, width, height));
    }

    @objid ("809bd158-4b12-41ad-ac4f-d236164c2eef")
    @Override
    public void fillRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.fillRoundRectangle(r, arcWidth, arcHeight);
        this.g.popState();
    }

    @objid ("94b714c1-906a-4167-95b3-90aea9bfa567")
    @Override
    public void fillString(String s, int x, int y) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.fillString(s, x, y);
        this.g.popState();
    }

    @objid ("c29b1f05-210a-4521-af5a-c491220c8531")
    @Override
    public void fillText(String s, int x, int y) {
        this.g.pushState();
        this.g.rotate(this.angle);
        this.g.fillText(s, x, y);
        this.g.popState();
    }

    @objid ("d7742f85-ad87-4f48-a692-b377969b484e")
    @Override
    public double getAbsoluteScale() {
        return this.g.getAbsoluteScale();
    }

    @objid ("1b084089-d614-4b02-a085-307a2b292f98")
    @Override
    public boolean getAdvanced() {
        return this.g.getAdvanced();
    }

    @objid ("c39b3616-faf1-4786-bfad-e583b335820f")
    @Override
    public int getAlpha() {
        return this.g.getAlpha();
    }

    @objid ("6eefa9fa-c4cd-4344-aa47-72a64eebbf03")
    @Override
    public int getAntialias() {
        return this.g.getAntialias();
    }

    @objid ("5533f3b2-b5d6-41d0-a571-b823b619e867")
    @Override
    public Color getBackgroundColor() {
        return this.g.getBackgroundColor();
    }

    @objid ("fc7e8381-3d3e-4eae-8265-1cfdf3c8dcb2")
    @Override
    public Rectangle getClip(Rectangle rect) {
        this.g.getClip(rect);
        
        this.cos = - this.cos;
        this.sin = - this.sin;
        int[] rp = trRectangle(rect.x(), rect.y(), rect.width(), rect.height());
        this.cos = - this.cos;
        this.sin = - this.sin;
        
        Rectangle r2 = new PointList(rp).getBounds();
        rect.setBounds(r2);
        return rect;
    }

    @objid ("dbd39a02-79a9-469a-a819-67020d078a99")
    @Override
    public int getFillRule() {
        return this.g.getFillRule();
    }

    @objid ("d6359081-0d36-4b78-9572-df52530c4c2a")
    @Override
    public Font getFont() {
        return this.g.getFont();
    }

    @objid ("e6f2a436-84d7-40e8-9bb2-9aa1e32b1115")
    @Override
    public FontMetrics getFontMetrics() {
        return this.g.getFontMetrics();
    }

    @objid ("3a157203-2ae5-4105-9365-d2422d2734c6")
    @Override
    public Color getForegroundColor() {
        return this.g.getForegroundColor();
    }

    @objid ("c07a63a4-4f45-4309-a53d-c4376fc36c75")
    @Override
    public int getInterpolation() {
        return this.g.getInterpolation();
    }

    @objid ("efbd1b9c-e14b-405c-9eb9-03c354f7901f")
    @Override
    public LineAttributes getLineAttributes() {
        return this.g.getLineAttributes();
    }

    @objid ("ce5551fa-7e1e-4b52-86cf-be84a2566782")
    @Override
    public int getLineCap() {
        return this.g.getLineCap();
    }

    @objid ("c05cb78c-4025-458e-a726-389f9ffafdcb")
    @Override
    public int getLineJoin() {
        return this.g.getLineJoin();
    }

    @objid ("bae47b90-b2b6-47dd-96e2-daa7e33e0931")
    @Override
    public float getLineMiterLimit() {
        return this.g.getLineMiterLimit();
    }

    @objid ("32a188fb-3b3e-452c-b913-8fd0866bd5ba")
    @Override
    public int getLineStyle() {
        return this.g.getLineStyle();
    }

    @objid ("fcd93a64-affc-4bd2-accd-d3e0fceeb0b1")
    @Override
    public int getLineWidth() {
        return this.g.getLineWidth();
    }

    @objid ("e277e2a1-88e6-4f48-9c8e-02734dcd858a")
    @Override
    public float getLineWidthFloat() {
        return this.g.getLineWidthFloat();
    }

    @objid ("f26858f6-39cf-482c-9208-60e5a0869da1")
    @Override
    public int getTextAntialias() {
        return this.g.getTextAntialias();
    }

    @objid ("f3bfdf19-013a-4d1b-b8c5-1676833afca5")
    @Override
    public boolean getXORMode() {
        return this.g.getXORMode();
    }

    @objid ("1cc32ce6-a86d-4618-af16-13437727c464")
    @Override
    public void popState() {
        this.g.popState();
    }

    @objid ("0f9fd3f7-9534-4400-b2c3-0729b7b866e1")
    @Override
    public void pushState() {
        this.g.pushState();
    }

    @objid ("3dce3a25-daac-4936-a278-e2378ca3ec2a")
    @Override
    public void restoreState() {
        this.g.restoreState();
    }

    @objid ("5d935b52-d74e-4c66-9870-97acad01f88e")
    @Override
    public void rotate(float degrees) {
        this.g.rotate(degrees);
    }

    @objid ("abdbb99d-3fd6-44b4-be62-2ed9d56e4b3b")
    @Override
    public void scale(double amount) {
        this.g.scale(amount);
    }

    @objid ("64feb3e6-4ca6-4663-805c-a644fc7a05fb")
    @Override
    public void scale(float horizontal, float vertical) {
        this.g.scale(horizontal, vertical);
    }

    @objid ("f3689153-1bd9-476c-9394-292fe94d2b97")
    @Override
    public void setAdvanced(boolean advanced) {
        this.g.setAdvanced(advanced);
    }

    @objid ("d4cee9b7-96b5-4e90-9328-a99dd2b354aa")
    @Override
    public void setAlpha(int alpha) {
        this.g.setAlpha(alpha);
    }

    @objid ("3558e0de-4b76-4c66-b693-6a00ff4dd794")
    @Override
    public void setAntialias(int value) {
        this.g.setAntialias(value);
    }

    @objid ("e8fc333d-5db2-44b6-bf29-c8d6488c907a")
    @Override
    public void setBackgroundColor(Color rgb) {
        this.g.setBackgroundColor(rgb);
    }

    @objid ("bf3c77ae-dd62-4659-950b-2d56d22b0056")
    @Override
    public void setBackgroundPattern(Pattern pattern) {
        this.g.setBackgroundPattern(pattern);
    }

    @objid ("dde6b372-da2f-4292-8ed5-7bd80f8fb015")
    @Override
    public void setClip(Rectangle r) {
        int[] rp = trRectangle(r.x(), r.y(), r.width(), r.height());
        
        Rectangle r2 = new PointList(rp).getBounds();
        this.g.setClip(r2);
    }

    @objid ("94020590-34dc-48f2-9a61-d9663d8749b1")
    @Override
    public void setClip(Path path) {
        PathData d = path.getPathData();
        
        for (int i=0; i<d.points.length; i+=2) {
            d.points[i] = ftx(d.points[i], d.points[i+1]);
            d.points[i+1] = fty(d.points[i], d.points[i+1]);
        }
        
        this.g.setClip(new Path(path.getDevice(), d));
    }

    @objid ("a86eeb31-8386-4ccf-b69e-291ca9c919f7")
    @Override
    public void setFillRule(int rule) {
        this.g.setFillRule(rule);
    }

    @objid ("ebd40d44-b346-48cd-b3c2-4bdad2b2fe13")
    @Override
    public void setFont(Font f) {
        this.g.setFont(f);
    }

    @objid ("278c686e-ab2b-4116-9ba1-8ede8774bed8")
    @Override
    public void setForegroundColor(Color rgb) {
        this.g.setForegroundColor(rgb);
    }

    @objid ("2005dfed-b362-48ca-973d-926326ac737f")
    @Override
    public void setForegroundPattern(Pattern pattern) {
        this.g.setForegroundPattern(pattern);
    }

    @objid ("6297a977-3e2b-4d68-8094-9e35d30a2068")
    @Override
    public void setInterpolation(int interpolation) {
        this.g.setInterpolation(interpolation);
    }

    @objid ("5106c0e9-81c0-4111-b81c-9aeb93ceb650")
    @Override
    public void setLineAttributes(LineAttributes attributes) {
        this.g.setLineAttributes(attributes);
    }

    @objid ("62ae163f-dbb0-44f3-8f27-01c6c53528dd")
    @Override
    public void setLineCap(int cap) {
        this.g.setLineCap(cap);
    }

    @objid ("24329b17-5440-457f-aa6d-a519240281ad")
    @Override
    public void setLineDash(float[] value) {
        this.g.setLineDash(value);
    }

    @objid ("b6d1631a-585a-4f57-bf10-7b6af9fe716d")
    @Override
    public void setLineDash(int[] dash) {
        this.g.setLineDash(dash);
    }

    @objid ("bafc8eb0-c944-43eb-97ce-80a88f789ccc")
    @Override
    public void setLineDashOffset(float value) {
        this.g.setLineDashOffset(value);
    }

    @objid ("9e4fcaad-af86-48fa-bd4f-5d910e170171")
    @Override
    public void setLineJoin(int join) {
        this.g.setLineJoin(join);
    }

    @objid ("07067017-29d5-497c-857e-aed8c0b807ff")
    @Override
    public void setLineMiterLimit(float miterLimit) {
        this.g.setLineMiterLimit(miterLimit);
    }

    @objid ("0ca514e3-9262-4fd0-b237-a78aa77bc3da")
    @Override
    public void setLineStyle(int style) {
        this.g.setLineStyle(style);
    }

    @objid ("4a3fa047-c899-471d-a891-77bfa0d4ad17")
    @Override
    public void setLineWidth(int width) {
        this.g.setLineWidth(width);
    }

    @objid ("0eb00cf5-1a94-44e2-9062-fb69617134b4")
    @Override
    public void setLineWidthFloat(float width) {
        this.g.setLineWidthFloat(width);
    }

    @objid ("2e3fd068-25e4-4874-9984-d7a5b6b42d6b")
    @Override
    public void setTextAntialias(int value) {
        this.g.setTextAntialias(value);
    }

    @objid ("c9651c7e-ac03-4f1b-89dd-6e4749eb0715")
    @Override
    public void setXORMode(boolean b) {
        this.g.setXORMode(b);
    }

    @objid ("2ce16159-6517-454b-98ed-074e11526099")
    @Override
    public void shear(float horz, float vert) {
        this.g.shear(horz, vert);
    }

    @objid ("117dc225-d643-4f8e-a130-2b5814905ee6")
    @Override
    public void translate(int dx, int dy) {
        this.g.translate(tx(dx, dy), ty(dx, dy));
    }

    @objid ("04f4ada3-8c46-4bca-a4fe-2de12b119764")
    @Override
    public void translate(float dx, float dy) {
        this.g.translate(ftx(dx, dy), fty(dx,dy));
    }

    @objid ("5aa48840-0f58-458b-9ba4-ef38910d3db6")
    private float ftx(float x, float y) {
        return (float) (x * this.cos - y * this.sin);
    }

    @objid ("516c3dab-4c54-4feb-b9e1-5f9e4244adc0")
    private float fty(float x, float y) {
        return (float) (x * this.sin + y * this.cos);
    }

    @objid ("1b7a0a5f-854a-468d-ba6a-679e5b8c3a1e")
    private int[] trPoints(int[] points) {
        int[] np = new int[points.length];
        
        for (int i=0; i< np.length; i+=2) {
            np[i] = tx(points[i], points[i+1]);
            np[i+1] = ty(points[i], points[i+1]);
        }
        return np;
    }

    @objid ("43da1135-1955-4f04-9a74-68fdb6b0c929")
    private int[] trRectangle(int x, int y, int w, int h) {
        int x1 = tx(x, y);
        int y1 = ty(x, y);
        int x2 = tx(x+w, y);
        int y2 = ty(x+w, y);
        int x3 = tx(x+w, y+h);
        int y3 = ty(x+w, y+h);
        int x4 = tx(x, y+h);
        int y4 = ty(x, y+h);
        
        int[] points = new int[]{x1,y1,x2,y2,x3,y3, x4, y4};
        return points;
    }

    @objid ("a31c581d-5489-476a-abe4-bc97d16dcd70")
    private int tx(int x, int y) {
        return (int) Math.round(x * this.cos - y * this.sin);
    }

    @objid ("6c5d007a-7028-41a2-92c8-5406d63f83b7")
    private int ty(int x, int y) {
        return (int) Math.round(x * this.sin + y * this.cos);
    }

}

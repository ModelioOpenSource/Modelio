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

package org.modelio.diagram.elements.core.figures.labelum;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.core.figures.ChainedLayout;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * This is a rotatable wrapped label with an icon .
 * <p>
 * The word wrap strategy is set using a {@link ILabelumTextLayouter}.
 * <p>
 * <h3>Implementation:</h3>
 * <li>It is copied from the {@link org.eclipse.draw2d.Label} implementation.
 * <li>It can be viewed as a rectangle containing a rotated rectangle that
 * take the maximum place.
 * 
 * 
 * @since Modelio 3.4
 */
@objid ("40f780d8-45b2-488a-bbd5-92127d2ccf12")
public class LabelumFigure extends Figure implements IPenOptionsSupport, PositionConstants {
    @objid ("1cfbc230-e4fa-481b-9258-4027b49dc747")
    private static boolean DEBUG = false;

    @objid ("5b107b27-90b4-4282-870c-28338d37cc52")
    private static String ELLIPSIS = "..."; // $NON-NLS-1$

    @objid ("39c8428f-4885-429f-9b88-10e4212c604a")
    private int iconAlignment = PositionConstants.CENTER;

    @objid ("a9b8c818-8468-42e7-b107-1f6a76576fe4")
    private int iconTextGap = 3;

    @objid ("89a28b84-6ae6-4b29-9d52-28175933ea5d")
    private int labelAlignment = PositionConstants.CENTER;

    /**
     * rotation angle in degrees.
     * <p>
     * Is guaranteed to be between 0 and 359.
     */
    @objid ("45a72aca-91e9-4a70-9e68-223bc6f5f163")
    private int orientation;

    /**
     * The amount of the Label's current text will fit in the Label,
     * including an elipsis "..." if truncation is required.
     */
    @objid ("1bdd77d0-6436-4084-89b0-4b95b3ffcff4")
    private String subStringText;

    @objid ("ab21b6a3-1d50-4903-9b92-4b221ec8568a")
    private String text = ""; // $NON-NLS-1$

    @objid ("ed0c38c0-9d92-48f5-b7a3-dcbd8bf95561")
    private int textAlignment = PositionConstants.CENTER;

    @objid ("2cedf9be-346b-4685-87bd-5d428b57f6df")
    private int textPlacement = PositionConstants.EAST;

    @objid ("068a8d0b-91b3-4f5f-b1b8-2c647e95c8ce")
    private Image icon;

    /**
     * Icon location in the inner rectangle coordinates
     */
    @objid ("9a42e983-ce39-48e7-9d4e-7ecdc02195aa")
    private Point iconLocation;

    @objid ("86a60302-2be2-49f8-9fde-07091dc0ebf5")
    private Dimension iconSize = new Dimension(0, 0);

    /**
     * The inner rotated rectangle coordinates.
     */
    @objid ("77ae41b5-78e7-45af-93e3-7378ab0dc58d")
    private PrecisionRectangle innerRectangle;

    @objid ("271e5f87-8ba4-44f5-a13f-c0ff674d6fc7")
    private Dimension subStringTextSize;

    /**
     * Shared SWT {@link TextLayout} used for layouting text and drawing it.
     */
    @objid ("102e250b-52de-422e-9192-1acf31b590c5")
    private static TextLayout textDrawer = null;

    @objid ("4c35a14e-430e-4797-8168-9ad82dc9ace8")
    private ILabelumTextLayouter textLayouter = NativeTextLayouter.INSTANCE;

    /**
     * Text location in the inner rectangle coordinates
     */
    @objid ("05b23814-78c6-42e1-b9bb-676906e56616")
    private Point textLocation;

    @objid ("3df57a7e-2122-459e-ae86-d0b9c0d7702e")
    private Dimension textSize;

    @objid ("e1573335-3767-4516-a49e-9dbee1e68e2f")
    private TextStyle textStyle = new TextStyle();

    /**
     * Creates an empty label figure oriented horizontally.
     */
    @objid ("a0ae666b-bb4e-4dce-9576-f88f13eb9274")
    public LabelumFigure() {
        init();
    }

    /**
     * Construct a Label with passed String as its text.
     * 
     * @param s the label text
     */
    @objid ("164a393c-325f-4d6b-8dfb-78a10d45b2c2")
    public LabelumFigure(String s) {
        setText(s);
        init();
    }

    /**
     * Construct a Label with passed Image as its icon.
     * 
     * @param i the label image
     */
    @objid ("3e15a74e-f886-47b9-84c9-5a7a3a519e95")
    public LabelumFigure(Image i) {
        setIcon(i);
        init();
    }

    /**
     * Construct a Label with passed String as text and passed Image as its
     * icon.
     * 
     * @param s the label text
     * @param i the label image
     */
    @objid ("b05b5606-5a3b-4771-a32e-86395249f624")
    public LabelumFigure(String s, Image i) {
        setText(s);
        setIcon(i);
        init();
    }

    @objid ("668de6f4-cdd7-47a6-aa00-4f277cb70288")
    @Override
    public Font getFont() {
        // the font is stored in the text style.
        if (this.textStyle.font != null) {
            return this.textStyle.font;
        }
        
        if (getParent() != null) {
            return getParent().getFont();
        }
        return null;
    }

    /**
     * Returns the Label's icon.
     * 
     * @return the label icon
     * @since 2.0
     */
    @objid ("574e6a9e-ab60-4bc3-ad66-0c1e1706c7bf")
    public Image getIcon() {
        return this.icon;
    }

    /**
     * Returns the current alignment of the Label's icon. The default is
     * {@link PositionConstants#CENTER}.
     * 
     * @return the icon alignment
     * @since 2.0
     */
    @objid ("293aa39f-6342-4f44-a119-462745fa0b6d")
    public int getIconAlignment() {
        return this.iconAlignment;
    }

    /**
     * Returns the bounds of the Label's icon.
     * 
     * @return the icon's bounds
     * @since 2.0
     */
    @objid ("dcca7546-bb07-4a8b-8cf4-03cd88d3bc21")
    public Rectangle getIconBounds() {
        Rectangle b = getBounds();
        return new Rectangle(b.getLocation().translate(getIconLocation()),
                        getIconSize());
    }

    /**
     * Returns the gap in pixels between the Label's icon and its text.
     * 
     * @return the gap
     * @since 2.0
     */
    @objid ("31a9e349-6492-4035-a5c3-2c355b072ccc")
    public int getIconTextGap() {
        return this.iconTextGap;
    }

    /**
     * Returns the alignment of the entire label (icon and text). The default
     * label alignment is {@link PositionConstants#CENTER}.
     * 
     * @return the label alignment
     * @since 3.5
     */
    @objid ("c99cffac-e358-4dad-91a6-9482220db044")
    public int getLabelAlignment() {
        return this.labelAlignment;
    }

    @objid ("fce1c79b-2e5e-44dd-a900-cd76b0eda560")
    @Override
    public LayoutManager getLayoutManager() {
        LayoutManager manager = super.getLayoutManager();
        assert (manager != null && (ChainedLayout.getRootLayout(manager) instanceof LM));
        return manager;
    }

    @objid ("62625e7c-e615-4178-836f-4288034e6394")
    @Override
    public Color getLineColor() {
        return this.textStyle.underlineColor;
    }

    @objid ("b5eca609-4db2-4808-bf7f-a59ed7ec5c02")
    @Override
    public LinePattern getLinePattern() {
        return LinePattern.LINE_SOLID;
    }

    @objid ("e58fa4b9-300d-467c-8e5b-8b19529ccede")
    @Override
    public int getLineWidth() {
        return 0;
    }

    /**
     * Get the orientation angle in counter-clockwise degrees.
     * 
     * @return the orientation angle in degrees. 0 means horizontal.
     */
    @objid ("306ba641-2e59-44b0-bfa7-70ddd808deed")
    public int getOrientation() {
        return this.orientation;
    }

    /**
     * Calculates the amount of the Label's current text will fit in the Label,
     * including an elipsis "..." if truncation is required.
     * 
     * @return the substring
     */
    @objid ("1d1f59f1-6eda-44d4-b9e3-6f47b5b2664e")
    public String getSubStringText() {
        if (this.subStringText == null) {
            // Compute subStringText and subStringTextSize
            final String origText = getText();
        
            // Format text to dimensions
            PrecisionRectangle ir = getInnerRectangle();
            Dimension availTextSize = calculateAvailableTextSize(new PrecisionDimension(ir.preciseWidth(), ir.preciseHeight()));
            this.subStringText = this.textLayouter.formatText(this, origText, availTextSize);
        
            // Configure text drawer to ask him text size
            // Store text size
            TextLayout td = getTextDrawer(this.subStringText, availTextSize.width());
            org.eclipse.swt.graphics.Rectangle ttb = td.getBounds();
            this.subStringTextSize = new Dimension(ttb.width, ttb.height);
        
        }
        return this.subStringText;
    }

    /**
     * Returns the bounds of the label's text. Note that the bounds are
     * calculated using the label's displayed text, truncated if needed.
     * 
     * @return the bounds of this label's complete text
     */
    @objid ("c6100c66-f495-443e-a52e-e4a99273dc3d")
    public Rectangle getSubStringBounds() {
        Rectangle tb = getBounds().getCopy();
        tb.translate(getTextLocation());
        tb.setSize(getSubStringTextSize());
        return tb;
    }

    /**
     * Returns the text of the label. Note that this is the complete text of the
     * label, regardless of whether it is currently being truncated. Call
     * {@link #getSubStringText()} to return the label's current text contents
     * with truncation considered.
     * 
     * @return the complete text of this label
     * @since 2.0
     */
    @objid ("7a02c1e3-38c3-47d2-a41f-60f9359c1ee1")
    public String getText() {
        return this.text;
    }

    /**
     * Returns the current alignment of the Label's text. The default text
     * alignment is {@link PositionConstants#CENTER}.
     * 
     * @return the text alignment
     */
    @objid ("f5341eec-bb0a-4309-b083-a18404ca1e9a")
    public int getTextAlignment() {
        return this.textAlignment;
    }

    /**
     * Returns the bounds of the label's text. Note that the bounds are
     * calculated using the label's complete text regardless of whether the
     * label's text is currently truncated.
     * 
     * @return the bounds of this label's complete text
     */
    @objid ("6ee9b558-e6ce-4a15-a64b-4c4e91c5f1c0")
    public Rectangle getTextBounds() {
        Rectangle tb = getBounds().getCopy();
        tb.translate(getTextLocation());
        tb.setSize(getTextSize());
        return tb;
    }

    @objid ("c77ab596-24ce-4c81-b3da-84625f3100a6")
    @Override
    public Color getTextColor() {
        return this.textStyle.foreground;
    }

    /**
     * Provides a TextLayout that can be used by the {@link LabelumFigure} and {@link ILabelumTextLayouter} for layout and drawing.
     * <p>
     * The returned {@link TextLayout} is ready to draw the given text with the labelum style.
     * <p>
     * This TextLayout must not be disposed by clients. The provided TextLayout's orientation will be LTR.
     * 
     * @param textToDraw the text to draw
     * @param width the available width to draw text. -1 means no limit. Justification and text centering need a positive value.
     * @return a SWT TextLayout that can be used for Bidi
     */
    @objid ("8a1f726f-139e-4622-baae-1015e800be2e")
    public TextLayout getTextDrawer(final String textToDraw, int width) {
        if (LabelumFigure.textDrawer == null) {
            LabelumFigure.textDrawer = new TextLayout(Display.getDefault());
        }
        
        TextLayout tl = LabelumFigure.textDrawer;
        try {
            if (isMirrored()) {
                tl.setOrientation(SWT.RIGHT_TO_LEFT);
            } else {
                tl.setOrientation(SWT.LEFT_TO_RIGHT);
            }
        
        } catch (@SuppressWarnings ("unused") org.eclipse.swt.SWTException e) {
            // the diagram is probably already disposed...
            return tl;
        }
        
        // Abort if the TextLayout is already configured with same parameters
        if (tl.getWidth() == width
                && Objects.equals(tl.getFont(), getTextFont())
                && textToDraw.equals(tl.getText())
                && (textToDraw.isEmpty() || this.textStyle.equals(tl.getStyle(0)))) {
            return tl;
        }
        
        // 0 is illegal,
        // -1 = disable wrap for drawing
        tl.setWidth(width > 0 ? width : -1);
        
        switch (this.labelAlignment) {
        case LEFT:
        case ALWAYS_LEFT:
        default:
            tl.setAlignment(SWT.LEFT);
            tl.setJustify(false);
            break;
        case RIGHT:
        case ALWAYS_RIGHT:
            tl.setAlignment(SWT.RIGHT);
            tl.setJustify(false);
            break;
        case CENTER:
            tl.setAlignment(SWT.CENTER);
            tl.setJustify(false);
            break;
        case (PositionConstants.LEFT + PositionConstants.RIGHT):
            tl.setAlignment(SWT.LEFT);
            tl.setJustify(true);
            break;
        }
        
        tl.setFont(getTextFont());
        tl.setText(textToDraw);
        tl.setStyle(this.textStyle, 0, textToDraw.length());
        return tl;
    }

    @objid ("2fb272c6-f16d-4e74-b43d-5b225ac9272c")
    @Override
    public Font getTextFont() {
        return getFont();
    }

    /**
     * Get the line breaking strategy for this labelum.
     * 
     * @return the labelum text layouter.
     */
    @objid ("ae5b7ff2-34bd-4827-926d-90023b8ee234")
    public ILabelumTextLayouter getTextLayouter() {
        return this.textLayouter;
    }

    /**
     * Returns the current placement of the label's text relative to its icon.
     * The default text placement is {@link PositionConstants#EAST}.
     * 
     * @return the text placement
     */
    @objid ("c90b6480-5b2c-4aa0-b6df-4ae27b4ff556")
    public int getTextPlacement() {
        return this.textPlacement;
    }

    /**
     * Get the <code>LabelumFigure</code> text style.
     * <p>
     * Provides direct access to the text style.
     * Many text style fields can be set using the setter methods
     * on the <code>LabelumFigure</code>:
     * <ul>
     * <li>{@link #setTextFont(Font)} : {@link TextStyle#font}
     * <li>{@link #setTextColor(Color)} : {@link TextStyle#foreground}
     * <li>{@link #setLineColor(Color)} : {@link TextStyle#underlineColor}
     * <li>{@link #setUnderline(boolean)} : {@link TextStyle#underline}
     * <li>{@link #setStrikeThrough(boolean)}: {@link TextStyle#strikeout}
     * </ul>
     * You may need to call {@link #repaint()} after changing the text style fields.
     * 
     * @return the text style
     */
    @objid ("95c2b855-2a3e-4f18-9411-cb25e8023058")
    public TextStyle getTextStyle() {
        return this.textStyle;
    }

    /**
     * Gets the <code>TextUtilities</code> instance to be used in measurement
     * calculations.
     * 
     * @return a <code>TextUtilities</code> instance
     * @since 3.4
     */
    @objid ("c1bbffa4-be7d-42b7-8cd0-cdad191c551a")
    public TextUtilities getTextUtilities() {
        return TextUtilities.INSTANCE;
    }

    @objid ("5a0aa123-4923-499c-a372-6b1a2e93179b")
    @Override
    public void invalidate() {
        super.invalidate();
        
        this.innerRectangle = null;
        this.prefSize = null;
        this.minSize = null;
        clearLocations();
        this.textSize = null;
        this.subStringTextSize = null;
        this.subStringText = null;
    }

    @objid ("78182752-cbe5-4ff3-a478-59b9627dcbe5")
    @Override
    public void setFont(Font f) {
        if (!Objects.equals(f, this.textStyle.font)) {
            this.textStyle.font = f;
            revalidate();
            repaint();
        }
    }

    /**
     * Sets the label's icon to the passed image.
     * 
     * @param image the new label image
     * @since 2.0
     */
    @objid ("f879528f-25e7-4bc6-ab5e-bb4bf23f37bd")
    public void setIcon(Image image) {
        if (this.icon == image) {
            return;
        }
        this.icon = image;
        // Call repaint, in case the image dimensions are the same.
        repaint();
        if (this.icon == null) {
            setIconDimension(new Dimension());
        } else {
            setIconDimension(new Dimension(image));
        }
    }

    /**
     * This method sets the alignment of the icon within the bounds of the
     * label. If the label is larger than the icon, then the icon will be
     * aligned according to this alignment. Valid values are:
     * <UL>
     * <LI><EM>{@link PositionConstants#CENTER}</EM>
     * <LI>{@link PositionConstants#TOP}
     * <LI>{@link PositionConstants#BOTTOM}
     * <LI>{@link PositionConstants#LEFT}
     * <LI>{@link PositionConstants#RIGHT}
     * </UL>
     * 
     * @param align the icon alignment
     */
    @objid ("3a94dcf0-1285-4c58-8d76-f5102d3cc654")
    public void setIconAlignment(int align) {
        if (this.iconAlignment == align) {
            return;
        }
        this.iconAlignment = align;
        clearLocations();
        repaint();
    }

    /**
     * Sets the gap in pixels between the label's icon and text to the passed
     * value. The default is 4.
     * 
     * @param gap the gap
     */
    @objid ("f89fda84-f8bc-4521-b735-0fdbca033afb")
    public void setIconTextGap(int gap) {
        if (this.iconTextGap == gap) {
            return;
        }
        this.iconTextGap = gap;
        repaint();
        revalidate();
    }

    /**
     * Sets the alignment of the label (icon and text) within the figure. If
     * this figure's bounds are larger than the size needed to display the
     * label, the label will be aligned accordingly. Valid values are:
     * <UL>
     * <LI><EM>{@link PositionConstants#CENTER}</EM>
     * <LI>{@link PositionConstants#TOP}
     * <LI>{@link PositionConstants#BOTTOM}
     * <LI>{@link PositionConstants#LEFT}
     * <LI>{@link PositionConstants#RIGHT}
     * <LI>{@link PositionConstants#LEFT} + {@link PositionConstants#RIGHT} = Takes the whole place and justify text.
     * </UL>
     * 
     * @param align label alignment
     */
    @objid ("5d0c5514-e59f-4ecc-a8b2-de45a1af3567")
    public void setLabelAlignment(int align) {
        if (this.labelAlignment == align) {
            return;
        }
        
        this.labelAlignment = align;
        
        clearLocations();
        repaint();
    }

    @objid ("0757f5ef-bbaa-457e-9ccc-3ffaa2a6af61")
    @Override
    public void setLayoutManager(LayoutManager manager) {
        if (manager == null || !(ChainedLayout.getRootLayout(manager) instanceof LM)) {
            throw new IllegalArgumentException(String.format("%s must be a LabelumFigure.LM ", manager));
        }
        
        super.setLayoutManager(manager);
    }

    @objid ("defbd690-f72b-4e9d-8503-f248aa16e702")
    @Override
    public void setLineColor(Color lineColor) {
        this.textStyle.underlineColor = lineColor;
        this.textStyle.strikeoutColor = lineColor;
        repaint();
    }

    @objid ("05ff3226-ce96-4ea6-b5d3-87a5415791e9")
    @Override
    public void setLinePattern(LinePattern lineStyle) {
        // ignore
        
        // We might use this to set underline style:
        // TextStyle doc:
        // This value should be one of SWT.UNDERLINE_SINGLE, SWT.UNDERLINE_DOUBLE, SWT.UNDERLINE_ERROR,
        // SWT.UNDERLINE_SQUIGGLE, or SWT.UNDERLINE_LINK.
    }

    @objid ("096178b1-928a-4d3a-9f27-618748880135")
    @Override
    public void setLineWidth(int lineWidth) {
        // ignore
    }

    /**
     * Rotates the label by the given counter-clockwise angle.
     * <p>
     * The angle is specified in degrees and for the identity transform 0 degrees is
     * at the 3 o'clock position.
     * A positive value indicates a counter-clockwise rotation while a negative value
     * indicates a clockwise rotation.
     * 
     * @param orientation the orientation in degrees.
     */
    @objid ("4303b9fe-46cd-4c62-a493-44442a4ad832")
    public void setOrientationAngle(int orientation) {
        this.orientation = orientation % 360;
        if (this.orientation < 0) {
            this.orientation = 360 + this.orientation;
        }
        
        revalidate();
    }

    /**
     * Set the label orientation using one of the {@link PositionConstants} constants.
     * <p>
     * {@link PositionConstants#EAST} is the default horizontal direction,
     * {@link PositionConstants#NORTH} is the default vertical direction.
     * 
     * @param orientation the label orientation
     */
    @objid ("ca3ffe99-bc79-4032-bf55-c2c3ccd935c6")
    public void setOrientationDirection(int orientation) {
        switch (orientation) {
        case PositionConstants.NORTH:
            setOrientationAngle(90);
            break;
        case PositionConstants.NORTH_EAST:
            setOrientationAngle(45);
            break;
        case PositionConstants.NORTH_WEST:
            setOrientationAngle(90 + 45);
            break;
        case PositionConstants.EAST:
            setOrientationAngle(0);
            break;
        case PositionConstants.SOUTH:
            setOrientationAngle(-90);
            break;
        case PositionConstants.SOUTH_EAST:
            setOrientationAngle(-45);
            break;
        case PositionConstants.SOUTH_WEST:
            setOrientationAngle(-90 - 45);
            break;
        case PositionConstants.WEST:
            setOrientationAngle(180);
            break;
        default:
            throw new IllegalArgumentException(String.valueOf(orientation));
        }
    }

    /**
     * Set whether the main label is underlined.
     * 
     * @param strikeThrough true to strike the label
     */
    @objid ("1bdf9bd4-4feb-42bf-95d9-9cfafdd72a95")
    public void setStrikeThrough(final boolean strikeThrough) {
        if (this.textStyle.strikeout != strikeThrough) {
            this.textStyle.strikeout = strikeThrough;
            repaint();
        }
    }

    /**
     * Sets the label's text.
     * 
     * @param s the new label text
     * @since 2.0
     */
    @objid ("2c5bbb91-fb69-48d1-90c4-01d2933437b3")
    public void setText(String s) {
        // "text" will never be null.
        String ls = s;
        if (ls == null) {
            ls = "";//$NON-NLS-1$
        }
        if (this.text.equals(ls)) {
            return;
        }
        this.text = ls;
        revalidate();
        repaint();
    }

    @objid ("a777d90e-6739-4b28-91bf-08380a5a1b75")
    @Override
    public void setTextColor(Color textColor) {
        if (!Objects.equals(this.textStyle.foreground, textColor)) {
            this.textStyle.foreground = textColor;
            repaint();
        }
    }

    @objid ("a6441a57-9bdb-46e3-a89c-6e758b6338cd")
    @Override
    public void setTextFont(Font textFont) {
        setFont(textFont);
    }

    /**
     * Set the text layouter used to define where text lines are broken.
     * 
     * @param labellumLayouter the text layouter.
     */
    @objid ("690f07b0-b415-48d0-8734-24b8e88cc1c8")
    public void setTextLayouter(ILabelumTextLayouter labellumLayouter) {
        if (!Objects.equals(this.textLayouter, labellumLayouter)) {
            this.textLayouter = labellumLayouter;
            revalidate();
            repaint();
        }
    }

    /**
     * Sets the minor alignment of the text <b>relative to the icon</b> within the label.
     * <p>
     * If you want to set the text horizontal alignment, use {@link #setLabelAlignment(int)}.
     * <p>
     * The text alignment must be orthogonal to the text placement. For example, if
     * the placement is EAST, then the text can be aligned using TOP, CENTER, or
     * BOTTOM. Valid values are:
     * <UL>
     * <LI><EM>{@link PositionConstants#CENTER}</EM>
     * <LI>{@link PositionConstants#TOP}
     * <LI>{@link PositionConstants#BOTTOM}
     * <LI>{@link PositionConstants#LEFT}
     * <LI>{@link PositionConstants#RIGHT}
     * </UL>
     * @see #setLabelAlignment(int)
     * 
     * @param align the text alignment
     */
    @objid ("2edd573d-169b-485f-875c-2e78e92d8393")
    public void setTextMinorAlignment(int align) {
        if (this.textAlignment == align) {
            return;
        }
        this.textAlignment = align;
        clearLocations();
        repaint();
    }

    /**
     * Sets the placement of the text relative to the icon within the label.
     * Valid values are:
     * <UL>
     * <LI><EM>{@link PositionConstants#EAST}</EM>
     * <LI>{@link PositionConstants#NORTH}
     * <LI>{@link PositionConstants#SOUTH}
     * <LI>{@link PositionConstants#WEST}
     * </UL>
     * 
     * @param where the text placement
     * @since 2.0
     */
    @objid ("6adf0349-d3c2-4187-9765-159e455eb067")
    public void setTextPlacement(int where) {
        if (this.textPlacement == where) {
            return;
        }
        this.textPlacement = where;
        revalidate();
        repaint();
    }

    /**
     * Set whether the main label is underlined.
     * 
     * @param underline true to underline the main label
     */
    @objid ("d8dd1dde-bd96-413d-b765-5c7bda7466f3")
    public void setUnderline(final boolean underline) {
        if (this.textStyle.underline != underline) {
            this.textStyle.underline = underline;
            repaint();
        }
    }

    /**
     * Calculates the size available for text using the passed Dimension as the size
     * of the whole Label's figure.
     * 
     * @param figureSize the precalculated size of the label's figure.
     * @return the available text size
     */
    @objid ("7841cd54-148f-4ab0-8cbd-16469aa39db9")
    protected Dimension calculateAvailableTextSize(Dimension figureSize) {
        int gap = getIconTextGap();
        if (getIcon() == null || getText().isEmpty()) {
            gap = 0;
        }
        
        Dimension d = new Dimension(0, 0);
        final int txtPlacement = getTextPlacement();
        if (txtPlacement == PositionConstants.WEST || txtPlacement == PositionConstants.EAST) {
            d.width = figureSize.width() - getIconSize().width() - gap;
            d.height = Math.max(getIconSize().height(), figureSize.height());
        } else {
            d.width = Math.max(getIconSize().width(), figureSize.width());
            d.height = figureSize.height() - getIconSize().height() - gap;
        }
        
        if (figureSize.width() == -1) {
            d.width = -1;
        }
        if (figureSize.height() == -1) {
            d.height = -1;
        }
        return d;
    }

    /**
     * Calculates the size of the Label using the passed Dimension as the size
     * of the Label's text.
     * 
     * @param txtSize the precalculated size of the label's text
     * @return the label's size in a new Dimension.
     * @since 2.0
     */
    @objid ("e63d988e-0ac1-4337-a69c-d5ad22f2c1b7")
    protected Dimension calculateLabelSize(Dimension txtSize) {
        int gap = getIconTextGap();
        if (getIcon() == null || getText().equals("")) {
            gap = 0;
        }
        Dimension d = new Dimension(0, 0);
        if (this.textPlacement == PositionConstants.WEST || this.textPlacement == PositionConstants.EAST) {
            d.width = getIconSize().width + gap + txtSize.width;
            d.height = Math.max(getIconSize().height, txtSize.height);
        } else {
            d.width = Math.max(getIconSize().width, txtSize.width);
            d.height = getIconSize().height + gap + txtSize.height;
        }
        return d;
    }

    /**
     * Called by the layout manager.
     * 
     * @param w rectangle width
     * @param h rectangle height
     * @return The minimum size
     */
    @objid ("580f994e-d260-49f4-b935-e8ed2733cda0")
    protected Dimension calculateMinimumSize(int w, int h) {
        Dimension availTextSize;
        if (w == -1 && h == -1) {
            availTextSize = new Dimension(-1, -1); // unlimited size
        } else {
            availTextSize = calculateAvailableTextSize(calculateMaxRotatedRectangleSize(w, h));
        }
        
        // reformat text into availTextSize, setup the text layouter and compute its size
        String tt = getTextLayouter().formatText(LabelumFigure.this, getText(), availTextSize);
        TextLayout td = getTextDrawer(tt, -1 /* availTextSize.width() */);
        org.eclipse.swt.graphics.Rectangle ttBounds = td.getBounds();
        Dimension ttDim = new Dimension(ttBounds.width, ttBounds.height);
        
        // Compute ellipsis size
        Dimension truncatedTextSize = getTextUtilities().getTextExtents(getTruncationString(), getFont());
        
        // Compute needed label bounds for text or ellipsis
        Dimension lMinSize = calculateRotatedRectangleBounds(calculateLabelSize(truncatedTextSize.union(ttDim)), new Dimension());
        
        // Add insets
        Insets insets = getInsets();
        lMinSize.expand(insets.getWidth(), insets.getHeight());
        return lMinSize;
    }

    /**
     * Called by the layout manager.
     * 
     * @param wHint The width hint
     * @param hHint The height hint
     * @return The preferred size
     */
    @objid ("122efad8-e5e9-4bfc-94df-92c23f6b129c")
    protected Dimension calculatePreferredSize(int wHint, int hHint) {
        Dimension lprefSize = calculateRotatedRectangleBounds(calculateLabelSize(getTextSize()), new Dimension());
        Insets insets = getInsets();
        lprefSize.expand(insets.getWidth(), insets.getHeight());
        
        if ((wHint >= 0 && wHint < lprefSize.width) || (hHint >= 0 && hHint < lprefSize.height)) {
            // Default preferred size is too big for given hints,
            // calculate minimum size for them.
        
            Dimension result = getMinimumSize(wHint, hHint).getCopy();
            result.width = Math.max(result.width, wHint);
            result.height = Math.max(result.height, hHint);
        
            return result;
        } else if ((wHint >= 0 && wHint > lprefSize.width && this.labelAlignment == PositionConstants.LEFT + PositionConstants.RIGHT)) {
            // Justified text and remaining place, take the whole space and justify
            lprefSize.width = wHint;
        }
        return lprefSize;
    }

    /**
     * Calculates and returns the size of the Label's text. Note that this
     * Dimension is calculated using the Label's full text, regardless of
     * whether or not its text is currently truncated. If text size considering
     * current truncation is desired, use {@link #getSubStringTextSize()}.
     * 
     * @return the size of the label's text, ignoring truncation
     * @since 2.0
     */
    @objid ("466335a6-1da5-489d-9045-1937a7459fac")
    protected Dimension calculateTextSize() {
        TextLayout td = getTextDrawer(getText(), -1);
        org.eclipse.swt.graphics.Rectangle ttb = td.getBounds();
        Dimension textExtents = new Dimension(ttb.width, ttb.height);
        return textExtents;
    }

    /**
     * Returns the location of the Label's icon relative to the Label.
     * 
     * @return the icon's location
     * @since 2.0
     */
    @objid ("969f1279-9a1c-47a0-8465-11e334e753d3")
    protected Point getIconLocation() {
        if (this.iconLocation == null) {
            calculateLocations();
        }
        return this.iconLocation;
    }

    /**
     * Gets the icon size
     * 
     * @return the icon size
     * @since 3.4
     */
    @objid ("35265b6b-1888-40ae-8d91-d0fa18032ff8")
    protected Dimension getIconSize() {
        return this.iconSize;
    }

    @objid ("ecb5f083-0df4-4853-83c9-f636a0dd575e")
    protected PrecisionRectangle getInnerRectangle() {
        if (this.innerRectangle == null) {
            PrecisionRectangle r = new PrecisionRectangle();
            Dimension size = getSize();
            r.setSize(calculateMaxRotatedRectangleSize(size.width, size.height));
        
            this.innerRectangle = r;
            double innerW = this.innerRectangle.preciseWidth();
            double innerH = this.innerRectangle.preciseHeight();
            double sinr = Math.sin(Math.toRadians(this.orientation));
            double cosr = Math.cos(Math.toRadians(this.orientation));
        
            // Translate the rotated inner rectangle so that it fits inside the figure bounds.
            if (this.orientation <= 90) {
                // final double dy = size.preciseHeight() / 2;
                final double dy = this.innerRectangle.width() * sinr;
                this.innerRectangle.translate(0.0, dy);
            } else if (this.orientation > 90 && this.orientation <= 180) {
                // double dx = - size.width() / 2.0;
                double dx = -innerW * cosr;
                double dy = innerW * sinr - innerH * cosr;
                this.innerRectangle.translate(dx, dy /* getSize().height */);
            } else if (this.orientation > 180 && this.orientation <= 270) {
                double dx = -innerW * cosr - innerH * sinr;
                double dy = -innerH * cosr;
                this.innerRectangle.translate(dx, dy);
            } else {
                double dx = -innerH * sinr;
                final double dy = 0;
                this.innerRectangle.translate(dx, dy);
            }
        }
        return this.innerRectangle;
    }

    /**
     * Returns the size of the Label's current text. If the text is currently
     * truncated, the truncated text with its ellipsis is used to calculate the
     * size.
     * 
     * @return the size of this label's text, taking into account truncation
     * @since 2.0
     */
    @objid ("502de5a1-8453-4726-a57e-3297869075f9")
    protected Dimension getSubStringTextSize() {
        if (this.subStringTextSize == null) {
            getSubStringText(); // compute text and text size
        }
        return this.subStringTextSize;
    }

    /**
     * Returns the location of the label's text relative to the label.
     * <p>
     * The point is in the inner rectangle coordinates.
     * 
     * @return the text location
     */
    @objid ("7d176674-2c1e-4d1b-9abb-ac5ce235d395")
    protected Point getTextLocation() {
        if (this.textLocation != null) {
            return this.textLocation;
        }
        calculateLocations();
        return this.textLocation;
    }

    /**
     * Returns the size of the label's complete text. Note that the text used to
     * make this calculation is the label's full text, regardless of whether the
     * label's text is currently being truncated and is displaying an ellipsis.
     * If the size considering current truncation is desired, call
     * {@link #getSubStringTextSize()}.
     * 
     * @return the size of this label's complete text
     * @since 2.0
     */
    @objid ("ec435493-2f50-413c-90fb-6b9730202db9")
    protected Dimension getTextSize() {
        if (this.textSize == null) {
            this.textSize = calculateTextSize();
        }
        return this.textSize;
    }

    /**
     * Gets the string that will be appended to the text when the label is
     * truncated. By default, this returns an ellipsis.
     * 
     * @return the string to append to the text when truncated
     * @since 3.4
     */
    @objid ("dbce7409-6926-4659-90ff-eb6082a2113e")
    protected String getTruncationString() {
        return LabelumFigure.ELLIPSIS;
    }

    @objid ("50a44741-d2c6-4f37-b6a7-b1c28ef6720d")
    @Override
    protected void paintFigure(final Graphics graphics) {
        if (isOpaque()) {
            super.paintFigure(graphics);
        }
        
        Rectangle lbounds = getBounds();
        graphics.translate(lbounds.x, lbounds.y);
        
        final Rectangle innerRect = getInnerRectangle();
        
        if (this.orientation != 0) {
            if (LabelumFigure.DEBUG) {
                graphics.setAlpha(40);
                graphics.drawRectangle(1, 0, lbounds.width() - 1, lbounds.height() - 1);
                graphics.setAlpha(255);
            }
        
            graphics.pushState();
            graphics.translate((float) innerRect.preciseX(), (float) innerRect.preciseY());
            graphics.rotate(-this.orientation);
            if (LabelumFigure.DEBUG) {
                graphics.setAlpha(40);
                graphics.drawRectangle(0, 0, innerRect.width(), innerRect.height());
                graphics.setAlpha(255);
            }
        }
        
        if (this.icon != null) {
            graphics.drawImage(this.icon, getIconLocation());
        }
        
        final Point lTextLocation = getTextLocation();
        
        if (LabelumFigure.DEBUG) {
            graphics.setAlpha(140);
            graphics.drawRectangle(lTextLocation.x(), lTextLocation.y(), getSubStringTextSize().width(), getSubStringTextSize().height());
            graphics.setAlpha(255);
        }
        
        translateGraphics(graphics, lTextLocation.preciseX(), lTextLocation.preciseY());
        
        if (LabelumFigure.DEBUG) {
            graphics.setAlpha(100);
            graphics.drawRectangle(0, 0, getSubStringTextSize().width(), getSubStringTextSize().height());
            graphics.setAlpha(255);
        }
        
        String draw = getSubStringText();
        TextLayout tl = getTextDrawer(draw, calculateAvailableTextSize(innerRect.getSize()).width());
        
        if (!isEnabled()) {
            translateGraphics(graphics, 1, 1);
            graphics.setForegroundColor(ColorConstants.buttonLightest);
        
            graphics.drawTextLayout(tl, 0, 0);
        
            translateGraphics(graphics, -1, -1);
            graphics.setForegroundColor(ColorConstants.buttonDarker);
        }
        
        graphics.drawTextLayout(tl, 0, 0);
        
        if (this.orientation != 0) {
            graphics.popState();
        }
    }

    @objid ("8b8d30c7-07f6-414e-8f71-295e243709a1")
    private void alignOnHeight(Point loc, Dimension size, int alignment) {
        Rectangle ref = getInnerRectangle();
        Insets insets = getInsets();
        switch (alignment) {
        case TOP:
            loc.y = insets.top;
            break;
        case BOTTOM:
            loc.y = ref.height - size.height - insets.bottom;
            break;
        default:
            loc.y = (ref.height - size.height) / 2;
        }
    }

    @objid ("ca2ea81c-a6de-4e96-a466-365ba6b92239")
    private void alignOnWidth(Point loc, Dimension size, int alignment) {
        Rectangle ref = getInnerRectangle();
        Insets insets = getInsets();
        switch (alignment) {
        case LEFT:
            loc.x = insets.left;
            break;
        case RIGHT:
            loc.x = ref.width - size.width - insets.right;
            break;
        default:
            loc.x = (ref.width - size.width) / 2;
        }
    }

    @objid ("81783808-7395-4cd9-83d7-4339b1389bac")
    private void calculateAlignment() {
        switch (this.textPlacement) {
        case EAST:
        case WEST:
            alignOnHeight(this.textLocation, getSubStringTextSize(), this.textAlignment);
            alignOnHeight(this.iconLocation, getIconSize(), this.iconAlignment);
            break;
        case NORTH:
        case SOUTH:
        default:
            alignOnWidth(this.textLocation, getSubStringTextSize(), this.textAlignment);
            alignOnWidth(this.iconLocation, getIconSize(), this.iconAlignment);
            break;
        }
    }

    @objid ("f84f0355-5030-44b9-8880-97f266c03555")
    private void calculateLocations() {
        this.textLocation = new PrecisionPoint();
        this.iconLocation = new PrecisionPoint();
        
        calculatePlacement();
        calculateAlignment();
        
        // Dimension offset = getSize().getShrinked(getPreferredSize());
        // offset.width += getTextSize().width - getSubStringTextSize().width;
        
        Dimension offset = preciseSize(getInnerRectangle()).getShrinked(getSubStringTextSize());
        switch (this.labelAlignment) {
        case CENTER:
            offset.scale(0.5);
            break;
        case LEFT:
        case PositionConstants.LEFT + PositionConstants.RIGHT:
            offset.scale(0.0);
            break;
        case RIGHT:
            offset.scale(1.0);
            break;
        case TOP:
            offset.setHeight(0);
            offset.scale(0.5);
            break;
        case BOTTOM:
            // offset.height = offset.height * 2;
            offset.scale(0.5, 1);
            break;
        default:
            offset.scale(0.5f);
            break;
        }
        
        switch (this.textPlacement) {
        case EAST:
        case WEST:
            offset.setHeight(0);
            break;
        case NORTH:
        case SOUTH:
        default:
            offset.setWidth(0);
            break;
        }
        
        this.textLocation.translate(offset);
        this.iconLocation.translate(offset);
    }

    /**
     * Calculate the max size of a rectangle inside the given rectangle size rotated by the labelum angle .
     * <p>
     * 
     * @param w rectangle width
     * @param h rectangle height
     * @return the inner rotated rectangle dimension
     */
    @objid ("539b914e-677c-4607-a4bc-a0ffcdb9d534")
    private PrecisionDimension calculateMaxRotatedRectangleSize(int w, int h) {
        if (this.orientation == 0 || this.orientation == 180) {
            // Horizontal
            return new PrecisionDimension(w, h);
        } else if (this.orientation == 90 || this.orientation == 270) {
            // Vertical
            return new PrecisionDimension(h, w);
        } else {
            return guessLargestRotatedTextSize(this.orientation, w, h);
        }
    }

    @objid ("417eb5e1-9985-45b3-9001-843f23406561")
    private void calculatePlacement() {
        int gap = getIconTextGap();
        if (this.icon == null || this.text.equals("")) {
            gap = 0;
        }
        Insets insets = getInsets();
        
        switch (this.textPlacement) {
        case EAST:
            this.iconLocation.x = insets.left;
            this.textLocation.x = getIconSize().width + gap + insets.left;
            break;
        case WEST:
            this.textLocation.x = insets.left;
            this.iconLocation.x = getSubStringTextSize().width + gap + insets.left;
            break;
        case NORTH:
            this.textLocation.y = insets.top;
            this.iconLocation.y = getTextSize().height + gap + insets.top;
            break;
        default:
        case SOUTH:
            this.textLocation.y = getIconSize().height + gap + insets.top;
            this.iconLocation.y = insets.top;
        }
    }

    /**
     * Compute the size of the a rectangle after rotation.
     * 
     * @param d the initial size.
     * @param target the dimension in which the bounds size of the rotated rectangle will be computed.
     * @return the bounds of the rotated dimension : target for convenience.
     */
    @objid ("e2866891-29fa-4e7c-8dd1-14c8b8591a36")
    private Dimension calculateRotatedRectangleBounds(Dimension d, Dimension target) {
        if (this.orientation == 0 || this.orientation == 180) {
            target.setSize(d);
        } else if (this.orientation == 90 || this.orientation == -90) {
            target.setSize(d);
            target.transpose();
        } else {
            // http://stackoverflow.com/questions/3231176/how-to-get-size-of-a-rotated-rectangle/3234405#3234405
            double rad = Math.toRadians(this.orientation);
            double cos = Math.cos(rad);
            double sin = Math.sin(rad);
        
            double a = Math.abs(d.width * cos) + Math.abs(d.height * sin);
            double b = Math.abs(d.width * sin) + Math.abs(d.height * cos);
        
            target.setSize((int) Math.ceil(a), (int) Math.ceil(b));
        }
        return target;
    }

    @objid ("8aeb58cc-5a38-411a-a897-5bc0d41668ed")
    private void clearLocations() {
        this.iconLocation = this.textLocation = null;
    }

    /**
     * Guess the ideal text width for the given dimensions.
     * 
     * @param angle the rotation angle in degrees
     * @param origWidth the constrained width.
     * @param origHeight the constrained height
     * @return the best text size.
     */
    @objid ("ebabf959-b6ff-4d75-86ab-71f1c433233d")
    private PrecisionDimension guessLargestRotatedTextSize(double angle, int origWidth, int origHeight) {
        org.eclipse.swt.graphics.Rectangle swtbounds = getTextDrawer(getText(), -1).getBounds();
        PrecisionDimension ret = new PrecisionDimension(swtbounds.width, swtbounds.height);
        
        int limitWidth = origWidth;
        boolean vertical = false;
        
        int quarter = (int) ((angle + 45) / 90);
        if (quarter == 1 || quarter == 3) {
            limitWidth = origHeight;
            vertical = true;
        }
        
        Dimension cbounds = calculateRotatedRectangleBounds(ret, new Dimension());
        if (limitWidth == -1 || cbounds.width() < limitWidth) {
            // inside constraints
            return ret;
        }
        
        int wrapCur = swtbounds.width;
        int wrapHigh = wrapCur;
        int wrapLow = 0;
        Dimension wrapDim = new Dimension(-1, -1);
        
        while (wrapHigh - wrapLow > 2) {
            wrapCur = (wrapHigh + wrapLow) / 2;
            wrapDim.setWidth(wrapCur);
        
            String formatedText = getTextLayouter().formatText(this, getText(), wrapDim);
            swtbounds = getTextDrawer(formatedText, wrapDim.width()).getBounds();
            ret.setSize(swtbounds.width, swtbounds.height);
            cbounds = calculateRotatedRectangleBounds(ret, cbounds);
        
            if (cbounds.width() > limitWidth) {
                // outside constraints
                wrapHigh = wrapCur;
            } else if (cbounds.width() < limitWidth) {
                // inside constraints
                wrapLow = wrapCur;
            } else {
                // On the constraint, probably the better size
                return ret;
            }
        }
        
        // Crop the other side to other limits
        if (!vertical) {
            // the text is horizontal
            if (origHeight != -1 && cbounds.height() > origHeight) {
                double sin = Math.sin(Math.toRadians(angle));
                double cos = Math.cos(Math.toRadians(angle));
                ret.height = (int) ((origHeight - (ret.width * sin)) / cos);
            }
        } else {
            // the text is vertical
            if (origWidth != -1 && cbounds.width() > origWidth) {
                double sin = Math.sin(Math.toRadians(angle));
                double cos = Math.cos(Math.toRadians(angle));
                ret.width = (int) ((origWidth - (ret.height * sin)) / cos);
            }
        }
        return ret;
    }

    @objid ("fb40ebec-42f5-4b5f-93a3-649c9c4f3b33")
    private void init() {
        setLayoutManager(new LM());
    }

    @objid ("e8ddc4e8-d858-4ef7-b495-d9ffb1126c8e")
    private static PrecisionDimension preciseSize(PrecisionRectangle r) {
        return new PrecisionDimension(r.preciseWidth(), r.preciseHeight());
    }

    /**
     * Sets the label's icon size to the passed Dimension.
     * 
     * @param d the new icon size
     */
    @objid ("7044e1cd-44ef-452b-b678-0d8ba96219ce")
    private void setIconDimension(Dimension d) {
        if (d.equals(getIconSize())) {
            return;
        }
        this.iconSize = d;
        revalidate();
    }

    /**
     * Wrapper aroung {@link Graphics#translate(float, float)} that ignores
     * thrown {@link NullPointerException} because of GEF bug.
     * @see Graphics#translate(float, float)
     * 
     * @param graphics the Graphics to translate
     * @param x the horizontal offset
     * @param y the vertical offset
     */
    @objid ("27a1fcd1-7403-4ed0-9696-b60837161bcf")
    private void translateGraphics(final Graphics graphics, double x, double y) {
        try {
            graphics.translate((float) x, (float) y);
        } catch (NullPointerException e) {
            DiagramElements.LOG.debug(e.toString());
            // Ignore draw2d bug because there is no clip:
            // java.lang.NullPointerException
            // at org.eclipse.draw2d.SWTGraphics.checkSharedClipping(SWTGraphics.java:338)
            // at org.eclipse.draw2d.SWTGraphics.translate(SWTGraphics.java:1349)
            // at org.eclipse.draw2d.ScaledGraphics.translate(ScaledGraphics.java:884)
            // at org.modelio.diagram.elements.core.figures.labelum.LabelumFigure.paintFigure(LabelumFigure.java:101)
        }
    }

    /**
     * Custom {@link AbstractHintLayout} layout manager.
     */
    @objid ("698f65f6-4d23-425b-b6db-72d33617b8e5")
    protected static class LM extends AbstractHintLayout {
        @objid ("a7d3d08f-ea77-4afd-9b31-8534f4f61b37")
        @Override
        public void layout(IFigure container) {
            // nothing to do
        }

        @objid ("4cc68e4a-aaf3-44c4-aa9b-01d51447d557")
        @Override
        protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
            return ((LabelumFigure) container).calculatePreferredSize(wHint, hHint);
        }

        @objid ("6b2f4257-f90c-46a6-a5ee-634cf6504daa")
        @Override
        protected Dimension calculateMinimumSize(IFigure container, int wHint, int hHint) {
            return ((LabelumFigure) container).calculateMinimumSize(wHint, hHint);
        }

    }

}

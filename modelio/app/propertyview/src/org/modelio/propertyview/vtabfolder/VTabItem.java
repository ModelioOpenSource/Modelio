/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Widget;

/**
 * Instances of this class represent a selectable user interface object that
 * represent a page in a notebook widget.
 * 
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>SWT.CLOSE</dd>
 * <dt><b>Events:</b></dt>
 * <dd>(none)</dd>
 * </dl>
 * <p>
 * IMPORTANT: This class is <em>not</em> intended to be subclassed.
 * </p>
 * 
 * @see <a href="http://www.eclipse.org/swt/snippets/#VTabFolder">VTabFolder,
 * VTabItem snippets</a>
 * @see <a href="http://www.eclipse.org/swt/">Sample code and further
 * information</a>
 * @noextend This class is not intended to be subclassed by clients.
 */
@objid ("731f17a7-f467-4341-bc87-2cd84ca4414e")
public class VTabItem extends Item {
    @objid ("a0e71e32-e7bb-42f0-9887-5edc1eb44cb9")
     int x = 0;

    @objid ("e06402a2-1344-4439-b3d3-6e940495fc69")
     int y = 0;

    @objid ("04077f4e-2cc6-44a0-b01b-baf585a333d0")
     int width = 0;

    @objid ("edf347c9-fed0-487a-992a-3dc4e29f01f7")
     int height = 0;

    @objid ("b5449e33-ae9f-4dc8-81a1-4f8bae3837df")
     String toolTipText;

    @objid ("03c5e87d-12c1-423c-a232-a3541617271a")
     String shortenedText;

    @objid ("25b371ab-8524-4555-a6c1-0d7c252dc886")
     int shortenedTextWidth;

    @objid ("dc9f951c-ea35-4101-8140-b298913a4389")
     int closeImageState = SWT.BACKGROUND;

    @objid ("e6aab95d-8a64-4eec-b381-86ca310b9699")
     int state = SWT.NONE;

    @objid ("e291af26-c029-473a-9e90-e03cf5ca67d7")
     boolean showClose = false;

    @objid ("1d1f5229-e2a3-40ea-87da-063f9d7a0585")
     boolean showing = false;

    @objid ("a114f2e6-f044-47a4-a100-ffe7a5592426")
     VTabFolder parent;

    @objid ("79615129-f717-45c2-a694-aa867d1b8c20")
     Control control; // the tab page

// Appearance
    @objid ("193cbcac-489e-48c9-a594-61942bf9ef13")
     Font font;

    @objid ("600186fe-54b9-4580-b32a-5eaf26749b62")
     Rectangle closeRect = new Rectangle(0, 0, 0, 0);

    /**
     * Constructs a new instance of this class given its parent (which must be a
     * <code>VTabFolder</code>) and a style value describing its behavior and
     * appearance. The item is added to the end of the items maintained by its
     * parent.
     * <p>
     * The style value is either one of the style constants defined in class
     * <code>SWT</code> which is applicable to instances of this class, or must
     * be built by <em>bitwise OR</em>'ing together (that is, using the
     * <code>int</code> "|" operator) two or more of those <code>SWT</code>
     * style constants. The class description lists the style constants that are
     * applicable to the class. Style bits are also inherited from superclasses.
     * </p>
     * @see SWT
     * @see Widget#getStyle()
     * @param parent a VTabFolder which will be the parent of the new instance
     * (cannot be null)
     * @param style the style of control to construct
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the parent</li>
     * </ul>
     */
    @objid ("7c8fea7e-6909-48de-ad0d-f16de3563884")
    public VTabItem(VTabFolder parent, int style) {
        this(parent, style, parent.getItemCount());
    }

    /**
     * Constructs a new instance of this class given its parent (which must be a
     * <code>VTabFolder</code>), a style value describing its behavior and
     * appearance, and the index at which to place it in the items maintained by
     * its parent.
     * <p>
     * The style value is either one of the style constants defined in class
     * <code>SWT</code> which is applicable to instances of this class, or must
     * be built by <em>bitwise OR</em>'ing together (that is, using the
     * <code>int</code> "|" operator) two or more of those <code>SWT</code>
     * style constants. The class description lists the style constants that are
     * applicable to the class. Style bits are also inherited from superclasses.
     * </p>
     * @see SWT
     * @see Widget#getStyle()
     * @param parent a VTabFolder which will be the parent of the new instance
     * (cannot be null)
     * @param style the style of control to construct
     * @param index the zero-relative index to store the receiver in its parent
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
     * <li>ERROR_INVALID_RANGE - if the index is not between 0
     * and the number of elements in the parent (inclusive)</li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the parent</li>
     * </ul>
     */
    @objid ("38180ce1-2a67-415b-a975-a3e0466bc551")
    public VTabItem(VTabFolder parent, int style, int index) {
        super(parent, style);
        this.showClose = (style & SWT.CLOSE) != 0;
        parent.createItem(this, index);
    }

    @objid ("2d7ceaa8-33e6-49b8-90de-d9961c7f6973")
    @Override
    public void dispose() {
        if (isDisposed())
            return;
        // if (!isValidThread ()) error (SWT.ERROR_THREAD_INVALID_ACCESS);
        this.parent.destroyItem(this);
        super.dispose();
        this.parent = null;
        this.control = null;
        this.toolTipText = null;
        this.shortenedText = null;
        this.font = null;
    }

    /**
     * Returns a rectangle describing the receiver's size and location relative
     * to its parent.
     * @return the receiver's bounding column rectangle
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("066488e6-8c59-43e7-8517-b70cbef23424")
    public Rectangle getBounds() {
        // checkWidget();
        this.parent.runUpdate();
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    /**
     * Gets the control that is displayed in the content area of the tab item.
     * @return the control
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("4c68e261-b6a1-4435-b295-73dca1fe627f")
    public Control getControl() {
        checkWidget();
        return this.control;
    }

    /**
     * Returns the font that the receiver will use to paint textual information.
     * @return the receiver's font
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     * 
     * @since 3.0
     */
    @objid ("606491dc-bec9-4cc5-a1d2-4b19f16f0022")
    public Font getFont() {
        checkWidget();
        if (this.font != null)
            return this.font;
        return this.parent.getFont();
    }

    /**
     * Returns the receiver's parent, which must be a <code>VTabFolder</code>.
     * @return the receiver's parent
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("b2e411db-0ae2-40ab-b6dc-cf6686cdd71b")
    public VTabFolder getParent() {
        // checkWidget();
        return this.parent;
    }

    /**
     * Returns <code>true</code> to indicate that the receiver's close button
     * should be shown. Otherwise return <code>false</code>. The initial value
     * is defined by the style (SWT.CLOSE) that was used to create the receiver.
     * @return <code>true</code> if the close button should be shown
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     * 
     * @since 3.4
     */
    @objid ("b66f80c2-6adb-408c-bb4b-67b2f745da02")
    public boolean getShowClose() {
        checkWidget();
        return this.showClose;
    }

    /**
     * Returns the receiver's tool tip text, or null if it has not been set.
     * @return the receiver's tool tip text
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("4bcd3dee-fb07-4ac8-973f-d629a7ee5db4")
    public String getToolTipText() {
        checkWidget();
        if (this.toolTipText == null && this.shortenedText != null) {
            String text = getText();
            if (!this.shortenedText.equals(text))
                return text;
        }
        return this.toolTipText;
    }

    /**
     * Returns <code>true</code> if the item will be rendered in the visible
     * area of the VTabFolder. Returns false otherwise.
     * @return <code>true</code> if the item will be rendered in the visible
     * area of the VTabFolder. Returns false otherwise.
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     * 
     * @since 3.0
     */
    @objid ("5cb18b97-8e56-4e4b-b225-5b636ab64280")
    public boolean isShowing() {
        checkWidget();
        return this.showing;
    }

    /**
     * Sets the control that is used to fill the client area of the tab folder
     * when the user selects the tab item.
     * @param control the new control (or null)
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_INVALID_ARGUMENT - if the control has been
     * disposed</li>
     * <li>ERROR_INVALID_PARENT - if the control is not in the
     * same widget tree</li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("cc8816c8-4952-4594-b518-825585a9e424")
    public void setControl(Control control) {
        checkWidget();
        if (control != null) {
            if (control.isDisposed())
                SWT.error(SWT.ERROR_INVALID_ARGUMENT);
            if (control.getParent() != this.parent)
                SWT.error(SWT.ERROR_INVALID_PARENT);
        }
        if (this.control != null && !this.control.isDisposed()) {
            this.control.setVisible(false);
        }
        this.control = control;
        if (this.control != null) {
            int index = this.parent.indexOf(this);
            if (index == this.parent.getSelectionIndex()) {
                this.control.setBounds(this.parent.getClientArea());
                this.control.setVisible(true);
            } else {
                int selectedIndex = this.parent.getSelectionIndex();
                Control selectedControl = null;
                if (selectedIndex != -1) {
                    selectedControl = this.parent.getItem(selectedIndex).control;
                }
                if (this.control != selectedControl) {
                    this.control.setVisible(false);
                }
            }
        }
    }

    @objid ("554f3a5c-e8ce-40d3-a20f-23fbe822ae99")
    boolean setFocus() {
        return this.control != null && !this.control.isDisposed() && this.control.setFocus();
    }

    /**
     * Sets the font that the receiver will use to paint textual information for
     * this item to the font specified by the argument, or to the default font
     * for that kind of control if the argument is null.
     * @param font the new font (or null)
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_INVALID_ARGUMENT - if the argument has been
     * disposed</li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     * 
     * @since 3.0
     */
    @objid ("1ab8e94c-5724-4e98-93fd-5cbc99c5036d")
    public void setFont(Font font) {
        checkWidget();
        if (font != null && font.isDisposed()) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
        if (font == null && this.font == null)
            return;
        if (font != null && font.equals(this.font))
            return;
        this.font = font;
        this.parent.updateFolder(VTabFolder.UPDATE_TAB_HEIGHT | VTabFolder.REDRAW_TABS);
    }

    @objid ("bd45eb2b-d2a7-4da1-8732-c91f155c2e80")
    @Override
    public void setImage(Image image) {
        checkWidget();
        if (image != null && image.isDisposed()) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
        Image oldImage = getImage();
        if (image == null && oldImage == null)
            return;
        if (image != null && image.equals(oldImage))
            return;
        super.setImage(image);
        this.parent.updateFolder(VTabFolder.UPDATE_TAB_HEIGHT | VTabFolder.REDRAW_TABS);
    }

    /**
     * Sets to <code>true</code> to indicate that the receiver's close button
     * should be shown. If the parent (VTabFolder) was created with SWT.CLOSE
     * style, changing this value has no effect.
     * @param close the new state of the close button
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     * 
     * @since 3.4
     */
    @objid ("02e4d5a8-7038-469e-a2c3-1bd37e93739a")
    public void setShowClose(boolean close) {
        checkWidget();
        if (this.showClose == close)
            return;
        this.showClose = close;
        this.parent.updateFolder(VTabFolder.REDRAW_TABS);
    }

    @objid ("500e29cc-08da-4722-8cf8-57c1271b9e0f")
    @Override
    public void setText(String string) {
        checkWidget();
        if (string == null) {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
            return;
        }
        if (string.equals(getText())) {
            return;
        }
        super.setText(string);
        this.shortenedText = null;
        this.shortenedTextWidth = 0;
        this.parent.updateFolder(VTabFolder.UPDATE_TAB_HEIGHT | VTabFolder.REDRAW_TABS);
    }

    /**
     * Sets the receiver's tool tip text to the argument, which may be null
     * indicating that the default tool tip for the control will be shown. For a
     * control that has a default tool tip, such as the Tree control on Windows,
     * setting the tool tip text to an empty string replaces the default,
     * causing no tool tip text to be shown.
     * <p>
     * The mnemonic indicator (character '&amp;') is not displayed in a tool
     * tip. To display a single '&amp;' in the tool tip, the character '&amp;'
     * can be escaped by doubling it in the string.
     * </p>
     * @param string the new tool tip text (or null)
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("45a871f9-5b26-4fa7-a800-345b456ee73a")
    public void setToolTipText(String string) {
        checkWidget();
        this.toolTipText = string;
    }

}

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
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TypedListener;

/**
 * Instances of this class implement the notebook user interface metaphor. It
 * allows the user to select a notebook page from set of pages.
 * <p>
 * The item children that may be added to instances of this class must be of
 * type <code>VTabItem</code>. <code>Control</code> children are created and
 * then set into a tab item using <code>VTabItem#setControl</code>.
 * </p>
 * <p>
 * Note that although this class is a subclass of <code>Composite</code>, it
 * does not make sense to set a layout on it.
 * </p>
 * <p>
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>CLOSE, LEFT, RIGHT, FLAT, BORDER, SINGLE, MULTI</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * <dd>"VTabFolder2"</dd>
 * </dl>
 * <p>
 * Note: Only one of the styles LEFT and RIGHT may be specified.
 * </p>
 * <p>
 * IMPORTANT: This class is <em>not</em> intended to be subclassed.
 * </p>
 * 
 * 
 * @noextend This class is not intended to be subclassed by clients.
 */
@objid ("732a7d30-0917-4d4c-8413-8dd5ebe42fee")
public class VTabFolder extends Composite {
    /**
     * marginWidth specifies the number of pixels of horizontal margin that will
     * be placed along the left and right edges of the form.
     * 
     * The default value is 0.
     */
    @objid ("78657261-fb63-4098-a124-e497e13197f1")
    public int marginWidth = 0;

    /**
     * marginHeight specifies the number of pixels of vertical margin that will
     * be placed along the top and bottom edges of the form.
     * 
     * The default value is 0.
     */
    @objid ("7d5104d8-7788-46f6-873f-1c4b3e066a50")
    public int marginHeight = 0;

/* sizing, positioning */
    @objid ("ab7b3cc7-2979-4fc9-8f9d-56d805366946")
    private boolean onRight = false;

    @objid ("e19f87c3-f8cb-4b8e-be81-3cb6e674b23e")
    private boolean single = false;

    @objid ("e9546916-be79-4a7b-a178-81f2826d0b79")
    private boolean simple = true;

    @objid ("2a5a6f74-8a3f-4d67-8bac-695859136407")
    private int fixedTabWidth = SWT.DEFAULT;

    @objid ("ad4c00d8-db5f-4375-93e8-fa6006047c38")
    private int tabWidth;

    @objid ("01358a42-95c6-4d51-a0c2-83b9074dc451")
    private int minChars = 20;

    @objid ("c36f7f14-69b0-49c7-bf03-d35716c0ca94")
    private boolean borderVisible = false;

    /**
     * index of the left most visible tab.
     */
    @objid ("c7b3b908-ee34-4b24-9943-5c44a12aa024")
    private int firstIndex = -1;

    @objid ("9f4bf1b7-be87-4518-a71e-58b2485d3dcc")
    private int selectedIndex = -1;

    /**
     * Indices of the elements in the {@link #items} array, used to manage tab
     * visibility and candidates to be hidden/shown next.
     * <p>
     * If there is not enough place for all tabs, tabs starting from the end of
     * the {@link #priority} array will be hidden first (independently from the
     * {@link #mru} flag!) => the right most elements have the highest priority
     * to be hidden.
     * <p>
     * If there is more place to show previously hidden tabs, tabs starting from
     * the beginning of the {@link #priority} array will be made visible first
     * (independently from the {@link #mru} flag!) => the left most elements
     * have the highest priority to be shown.
     * <p>
     * The update strategy of the {@link #priority} array however depends on the
     * {@link #mru} flag.
     * <p>
     * If {@link #mru} flag is set, the first index is always the index of the
     * currently selected tab, next one is the tab selected before current
     * etc...
     * <p>
     * Example: [4,2,5,1,3,0], just representing the last selection order.
     * <p>
     * If {@link #mru} flag is not set, the first index is always the index of
     * the left most visible tab ({@link #firstIndex} field), next indices are
     * incremented by one up to <code>priority.length-1</code>, and the rest
     * filled with indices starting with <code>firstIndex-1</code> and
     * decremented by one until 0 index is reached.
     * <p>
     * The tabs between first index and the index of the currently selected tab
     * are always visible.
     * <p>
     * Example: 6 tabs, 2 and 3 are indices of currently shown tabs:
     * [2,3,4,5,1,0]. The array consists of two blocks: sorted ascending from
     * first visible (2) to last available (5), and the rest sorted descending
     * (1,0). 4 and 5 are the hidden tabs on the right side, 0 and 1 are the
     * hidden tabs on the left side from the visible tabs 2 and 3.
     * 
     * @see #updateItems(int)
     * @see #setItemLocation(GC)
     */
    @objid ("53be4b70-d793-44c3-916c-b1915e9a130b")
     int[] priority = new int[0];

    @objid ("29d3a6ed-c440-4354-81be-1fe2c9fd47c2")
     boolean mru = false;

    @objid ("bc2743a2-e357-4510-a18b-d89ae49cf13e")
     boolean ignoreTraverse;

    @objid ("06d16813-1ab9-4953-ae22-20c8c1e38f0d")
     boolean useDefaultRenderer;

/* Unselected item appearance */
    @objid ("4ddbb8eb-6699-41d5-99c6-f3ff6d6b4656")
     boolean showUnselectedImage = true;

    @objid ("36e3bc0d-b5ed-4366-a93b-a949f3ebff51")
     boolean hoverTb;

    @objid ("2bc7d548-fdc0-48a6-977e-f6c206a5e208")
     boolean hovering;

    @objid ("0950b8a3-faa0-4edd-b7e8-0ff86f6bc7e2")
     boolean hoverTimerRunning;

    @objid ("8b4c903e-643d-4129-838e-2833f6cb4e40")
     boolean showChevron = false;

    @objid ("28a6010f-ef9d-45ad-be7d-e16a4b87ef16")
     int chevronCount;

    @objid ("cbcbd899-9f96-4b31-96a0-7af3689a57ef")
     boolean chevronVisible = true;

    @objid ("9e15a4d5-a57b-406a-8df9-74b832967e80")
     int topRightAlignment = SWT.RIGHT;

    @objid ("94f37d26-d418-444f-9f40-01aa4bd1a049")
     boolean ignoreResize;

    @objid ("2a34c148-7694-4815-8e4e-1d12062db7da")
     int[] controlAlignments;

    @objid ("27a68539-2618-44c0-98e9-a4dea5272797")
     int updateFlags;

    @objid ("beaa04cc-735c-4c03-88c3-0a308acdad8f")
     static final int REDRAW = 1 << 1;

    @objid ("ac258e66-e92d-400f-9420-7687d7171523")
     static final int REDRAW_TABS = 1 << 2;

    @objid ("8a080990-16a4-404e-bdc9-370718f3887e")
     static final int UPDATE_TAB_HEIGHT = 1 << 3;

// when disposing VTabFolder, don't try to layout the items or
// change the selection as each child is destroyed.
    @objid ("e382807b-6027-4c06-b9d4-495bf0bd7d9d")
     boolean inDispose = false;

// internal constants
    @objid ("454651ea-0ca7-454c-9c62-4cec3108d2b9")
     static final int DEFAULT_WIDTH = 64;

    @objid ("e178ae29-66c7-41f1-9d9d-c71babb1d153")
     static final int DEFAULT_HEIGHT = 64;

    @objid ("e668fab6-7347-4c2a-bcc6-61ddbf980b90")
     static final int SELECTION_FOREGROUND = SWT.COLOR_LIST_FOREGROUND;

    @objid ("f0576278-6ee4-4cb8-8e42-286ebaa9c016")
     static final int SELECTION_BACKGROUND = SWT.COLOR_LIST_BACKGROUND;

    @objid ("60f6c320-2503-4e1c-84d2-5427a6d758a6")
     static final int FOREGROUND = SWT.COLOR_WIDGET_FOREGROUND;

    @objid ("a8dceaed-c6ca-4889-964d-9b613d4b7b52")
     static final int BACKGROUND = SWT.COLOR_WIDGET_BACKGROUND;

// TODO: add setter for spacing?
    @objid ("02f45d02-8303-441f-b0e3-6f1a1f897d22")
     static final int SPACING = 3;

    @objid ("44929478-c559-4909-93d5-da6b083d6ce7")
     static final boolean IS_GTK;

/* item management */
    @objid ("ac2194b1-5015-4ea6-8b25-216c26368220")
    private VTabFolderRenderer renderer;

    @objid ("c5d57a6a-b976-4935-8a3b-681dd57a85c4")
    private VTabItem[] items = new VTabItem[0];

    @objid ("f764b2ae-ffab-4833-b085-31f6e8090d2f")
     Listener listener;

/* External Listener management */
    @objid ("adafb746-554f-416e-bafe-86229ceff2ee")
     VTabFolder2Listener[] folderListeners = new VTabFolder2Listener[0];

/* Selected item appearance */
    @objid ("aa5eaee8-a8e2-402b-975d-73c4b5f342ed")
     Image selectionBgImage;

    @objid ("d9021783-3bd4-4bb6-a072-9ea22f731dc6")
     Color selectionForeground;

    @objid ("0261c77e-6476-4088-a058-af9bc99b2fb8")
     Color selectionBackground;

    @objid ("26e03545-f034-42b6-9939-47dd5b693e07")
     Rectangle hoverRect = new Rectangle(0, 0, 0, 0);

    @objid ("9169584e-1ee5-457d-8a55-45d11aff0a0d")
     Menu showMenu;

    @objid ("5345a29e-e834-46d1-be57-b5fb92a602b9")
     ToolBar chevronTb;

    @objid ("2ff81925-7566-48c9-8647-26c93f0e55db")
     ToolItem chevronItem;

    @objid ("1bdb0237-f68f-4915-97a2-5180685281a7")
     Image chevronImage;

    @objid ("723f735c-567f-490c-bb72-0a38ddde374d")
     Control topRight;

    @objid ("a9e2f793-2a9b-4397-a177-c5c2201b5769")
     Control[] controls;

    @objid ("d695f566-c95e-4784-a322-b85f1ebddf45")
     Rectangle[] controlRects;

    @objid ("2c047133-cf46-4653-a1c1-80315c0abef8")
     Image[] controlBkImages;

    @objid ("63a9b64f-f5ee-4d2e-82ed-e58f47418d02")
     Runnable updateRun;

// keep track of size changes in order to redraw only affected area
// on Resize
    @objid ("169db865-4de1-48a3-9f33-fc3a01409c93")
     Point oldSize;

    @objid ("c49761c9-d0b6-44ac-abbb-32a763a293a2")
     Font oldFont;

    /**
     * Constructs a new instance of this class given its parent and a style
     * value describing its behavior and appearance.
     * <p>
     * The style value is either one of the style constants defined in class
     * <code>SWT</code> which is applicable to instances of this class, or must
     * be built by <em>bitwise OR</em>'ing together (that is, using the
     * <code>int</code> "|" operator) two or more of those <code>SWT</code>
     * style constants. The class description lists the style constants that are
     * applicable to the class. Style bits are also inherited from superclasses.
     * </p>
     * @see SWT#LEFT
     * @see SWT#RIGHT
     * @see SWT#FLAT
     * @see SWT#BORDER
     * @see SWT#SINGLE
     * @see SWT#MULTI
     * @see #getStyle()
     * @param parent a widget which will be the parent of the new instance (cannot
     * be null)
     * @param style the style of widget to construct
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
    @objid ("da3f83c9-67c5-4763-8a6d-b2a5b5592a43")
    public VTabFolder(Composite parent, int style) {
        super(parent, checkStyle(parent, style));
        init(style);
    }

    @objid ("f8a8f0ab-ffaf-4442-be4b-26ceff7ce497")
    void init(int style) {
        super.setLayout(new VTabFolderLayout());
        int style2 = super.getStyle();
        this.oldFont = getFont();
        this.setOnRight((style2 & SWT.RIGHT) != 0);
        
        // showMin = (style2 & SWT.MIN) != 0; - conflicts with SWT.TOP
        // showMax = (style2 & SWT.MAX) != 0; - conflicts with SWT.BOTTOM
        this.setSingle((style2 & SWT.SINGLE) != 0);
        this.setBorderVisible((style & SWT.BORDER) != 0);
        // set up default colors
        Display display = getDisplay();
        this.selectionForeground = display.getSystemColor(SELECTION_FOREGROUND);
        this.selectionBackground = display.getSystemColor(SELECTION_BACKGROUND);
        this.renderer = new VTabFolderRenderer(this);
        this.useDefaultRenderer = true;
        this.controls = new Control[0];
        this.controlAlignments = new int[0];
        this.controlRects = new Rectangle[0];
        this.controlBkImages = new Image[0];
        updateTabWidth(false);
        
        // Add all listeners
        this.listener = new Listener() {
            @Override
            public void handleEvent(Event event) {
                switch (event.type) {
                case SWT.Dispose:
                    onDispose(event);
                    break;
                case SWT.DragDetect:
                    onDragDetect(event);
                    break;
                case SWT.FocusIn:
                    onFocus(event);
                    break;
                case SWT.FocusOut:
                    onFocus(event);
                    break;
                case SWT.KeyDown:
                    onKeyDown(event);
                    break;
                case SWT.MenuDetect:
                    onMenuDetect(event);
                    break;
                case SWT.MouseDoubleClick:
                    onMouseDoubleClick(event);
                    break;
                case SWT.MouseDown:
                    onMouse(event);
                    break;
                case SWT.MouseEnter:
                    onMouse(event);
                    break;
                case SWT.MouseExit:
                    onMouse(event);
                    break;
                case SWT.MouseHover:
                    onMouse(event);
                    break;
                case SWT.MouseMove:
                    onMouse(event);
                    break;
                case SWT.MouseUp:
                    onMouse(event);
                    break;
                case SWT.Paint:
                    onPaint(event);
                    break;
                case SWT.Resize:
                    onResize(event);
                    break;
                case SWT.Traverse:
                    onTraverse(event);
                    break;
                case SWT.Selection:
                    onSelection(event);
                    break;
                }
            }
        };
        
        int[] folderEvents = new int[] { SWT.Dispose, SWT.DragDetect, SWT.FocusIn, SWT.FocusOut, SWT.KeyDown,
                SWT.MenuDetect, SWT.MouseDoubleClick, SWT.MouseDown, SWT.MouseEnter, SWT.MouseExit, SWT.MouseHover,
                SWT.MouseMove, SWT.MouseUp, SWT.Paint, SWT.Resize, SWT.Traverse, };
        for (int i = 0; i < folderEvents.length; i++) {
            addListener(folderEvents[i], this.listener);
        }
        
        initAccessible();
    }

    @objid ("ae0d7be3-b961-4a41-a23c-9680e49bc6a6")
    static int checkStyle(Composite parent, int style) {
        int mask = SWT.CLOSE | SWT.LEFT | SWT.RIGHT | SWT.FLAT | SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT | SWT.SINGLE
                | SWT.MULTI;
        style = style & mask;
        // LEFt and RIGHT are mutually exclusive.
        // LEFT is the default
        if ((style & SWT.LEFT) != 0)
            style = style & ~SWT.RIGHT;
        // SINGLE and MULTI are mutually exclusive.
        // MULTI is the default
        if ((style & SWT.MULTI) != 0)
            style = style & ~SWT.SINGLE;
        // reduce the flash by not redrawing the entire area on a Resize event
        style |= SWT.NO_REDRAW_RESIZE;
        
        // TEMPORARY CODE
        /*
         * In Right To Left orientation on Windows, all GC calls that use a
         * brush are drawing offset by one pixel. This results in some parts of
         * the VTabFolder not drawing correctly. To alleviate some of the
         * appearance problems, allow the OS to draw the background. This does
         * not draw correctly but the result is less obviously wrong.
         */
        if ((style & SWT.RIGHT_TO_LEFT) != 0)
            return style;
        if ((parent.getStyle() & SWT.MIRRORED) != 0 && (style & SWT.LEFT_TO_RIGHT) == 0)
            return style;
        return style | SWT.DOUBLE_BUFFERED;
    }

    /**
     * Adds the listener to the collection of listeners who will be notified
     * when a tab item is closed, minimized, maximized, restored, or to show the
     * list of items that are not currently visible.
     * @see VTabFolder2Listener
     * @see #removeVTabFolder2Listener(VTabFolder2Listener)
     * 
     * @since 3.0
     * @param listener the listener which should be notified
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("7b7352ed-1be6-40eb-adf8-b1e1f696efaf")
    public void addVTabFolder2Listener(VTabFolder2Listener listener) {
        checkWidget();
        if (listener == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        // add to array
        VTabFolder2Listener[] newListeners = new VTabFolder2Listener[this.folderListeners.length + 1];
        System.arraycopy(this.folderListeners, 0, newListeners, 0, this.folderListeners.length);
        this.folderListeners = newListeners;
        this.folderListeners[this.folderListeners.length - 1] = listener;
    }

    /**
     * Adds the listener to the collection of listeners who will be notified
     * when the user changes the receiver's selection, by sending it one of the
     * messages defined in the <code>SelectionListener</code> interface.
     * <p>
     * <code>widgetSelected</code> is called when the user changes the selected
     * tab. <code>widgetDefaultSelected</code> is not called.
     * </p>
     * @see SelectionListener
     * @see #removeSelectionListener
     * @see SelectionEvent
     * @param listener the listener which should be notified when the user changes
     * the receiver's selection
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("2b031802-2798-4db8-a22a-5547f54846ce")
    public void addSelectionListener(SelectionListener listener) {
        checkWidget();
        if (listener == null) {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        }
        TypedListener typedListener = new TypedListener(listener);
        addListener(SWT.Selection, typedListener);
        addListener(SWT.DefaultSelection, typedListener);
    }

    @objid ("4739242d-bc52-404f-aefe-d1096f8815ac")
    Rectangle[] computeControlBounds(Point size, boolean[][] position) {
        if (this.controls == null || this.controls.length == 0)
            return new Rectangle[0];
        Rectangle[] rects = new Rectangle[this.controls.length];
        for (int i = 0; i < rects.length; i++) {
            rects[i] = new Rectangle(0, 0, 0, 0);
        }
        Rectangle trim = this.renderer.computeTrim(VTabFolderRenderer.PART_BORDER, SWT.NONE, 0, 0, 0, 0);
        int borderRight = trim.width + trim.x;
        int borderLeft = -trim.x;
        int borderBottom = trim.height + trim.y;
        int borderTop = -trim.y;
        
        Point[] tabControlSizes = new Point[this.controls.length];
        
        boolean[] overflow = new boolean[this.controls.length];
        // Left Control
        int topHeight = 0;
        int y = borderTop + SPACING;
        int bottomHeight = 0;
        int allHeight = 0;
        for (int i = 0; i < this.controls.length; i++) {
            Point ctrlSize = tabControlSizes[i] = !this.controls[i].isDisposed() && this.controls[i].getVisible()
                    ? this.controls[i].computeSize(SWT.DEFAULT, SWT.DEFAULT) : new Point(0, 0);
                    int alignment = this.controlAlignments[i];
                    if ((alignment & SWT.LEAD) != 0) {
                        rects[i].width = getControlWidth(ctrlSize);
                        rects[i].height = ctrlSize.y;
                        rects[i].x = getControlX(size, rects, borderLeft, borderRight, i);
                        rects[i].y = y; // getControlY(size, rects, borderBottom,
                        // borderTop, i);
                        y += ctrlSize.y;
                        topHeight += ctrlSize.y;
                    } else {
                        if ((alignment & (SWT.FILL | SWT.WRAP)) == 0) {
                            bottomHeight += ctrlSize.y;
                        }
                        allHeight += ctrlSize.y;
                    }
        }
        if (topHeight > 0)
            topHeight += SPACING * 2;
        
        int allItemsHeight = 0;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i].showing)
                allItemsHeight += this.items[i].height;
        }
        
        int maxHeight = size.y - borderTop - topHeight - borderBottom;
        int availableHeight = Math.max(0, maxHeight - allItemsHeight - bottomHeight);
        if (bottomHeight > 0)
            availableHeight -= SPACING * 2;
        y = size.y - borderBottom - SPACING;
        if (allItemsHeight + allHeight <= maxHeight) {
            // Everything fits
            for (int i = 0; i < this.controls.length; i++) {
                int alignment = this.controlAlignments[i];
                if ((alignment & SWT.TRAIL) != 0) {
                    Point ctrlSize = tabControlSizes[i];
                    y -= ctrlSize.y;
                    rects[i].width = ctrlSize.x;
                    rects[i].height = getControlWidth(ctrlSize);
                    rects[i].y = getControlX(size, rects, borderLeft, borderBottom, i);
                    rects[i].y = y;
                    if ((alignment & (SWT.FILL | SWT.WRAP)) != 0)
                        availableHeight -= ctrlSize.y;
                }
            }
        } else {
            for (int i = 0; i < this.controls.length; i++) {
                int alignment = this.controlAlignments[i];
                Point ctrlSize = tabControlSizes[i];
                if ((alignment & SWT.TRAIL) != 0) {
                    if ((alignment & (SWT.FILL | SWT.WRAP)) == 0) {
                        y += ctrlSize.y;
                        rects[i].width = getControlWidth(ctrlSize);
                        rects[i].height = ctrlSize.y;
                        rects[i].x = getControlX(size, rects, borderLeft, borderRight, i);
                        rects[i].y = y;
                    } else if (((alignment & (SWT.WRAP)) != 0 && ctrlSize.x < availableHeight)) {
                        y += ctrlSize.y;
                        rects[i].width = getControlWidth(ctrlSize);
                        rects[i].height = ctrlSize.y;
                        rects[i].x = getControlX(size, rects, borderLeft, borderRight, i);
                        rects[i].y = y;
                    } else if ((alignment & (SWT.FILL)) != 0 && (alignment & (SWT.WRAP)) == 0) {
                        rects[i].width = getControlWidth(ctrlSize);
                        rects[i].height = 0;
                        rects[i].x = getControlX(size, rects, borderBottom, borderTop, i);
                        rects[i].y = y;
                    } else {
                        if ((alignment & (SWT.WRAP)) != 0) {
                            overflow[i] = true;
                        }
                    }
                }
            }
        }
        
        // Any space, distribute amongst FILL
        if (availableHeight > 0) {
            int fillCount = 0;
            for (int i = 0; i < this.controls.length; i++) {
                int alignment = this.controlAlignments[i];
                if ((alignment & SWT.TRAIL) != 0 && (alignment & SWT.FILL) != 0 && !overflow[i]) {
                    fillCount++;
                }
            }
            if (fillCount != 0) {
                int extraSpace = availableHeight / fillCount;
                int addedSpace = 0;
                for (int i = 0; i < this.controls.length; i++) {
                    int alignment = this.controlAlignments[i];
                    if ((alignment & SWT.TRAIL) != 0) {
                        if ((alignment & SWT.FILL) != 0 && !overflow[i]) {
                            rects[i].width += extraSpace;
                            addedSpace += extraSpace;
                        }
                        if (!overflow[i]) {
                            rects[i].x -= addedSpace;
                        }
                    }
                }
            }
        }
        
        // Go through overflow laying out all wrapped controls
        Rectangle bodyTrim = this.renderer.computeTrim(VTabFolderRenderer.PART_BODY, SWT.NONE, 0, 0, 0, 0);
        
        int bodyBottom = bodyTrim.height + bodyTrim.y;
        int bodyTop = -bodyTrim.y;
        int bodyHeight = size.y - bodyTop - bodyBottom;
        
        int x = this.isOnRight() ? this.getSize().x - getTabWidth() + 2 * bodyTrim.x : -bodyTrim.x;
        y = size.y - bodyBottom;
        
        availableHeight = bodyHeight;
        
        for (int i = 0; i < this.controls.length; i++) {
            Point ctrlSize = tabControlSizes[i];
            if (overflow[i]) {
                if (availableHeight > ctrlSize.y) {
                    y -= ctrlSize.y;
                    rects[i].height = ctrlSize.y;
                    rects[i].x = this.isOnRight() ? x - ctrlSize.x : x;
                    rects[i].height = ctrlSize.y;
                    rects[i].y = y;
                    availableHeight -= ctrlSize.y;
                    maxHeight = Math.max(maxHeight, ctrlSize.y);
                } else {
                    y = size.y - bodyBottom;
                    y += maxHeight;
                    maxHeight = 0;
                    availableHeight = bodyHeight;
                    if (availableHeight > ctrlSize.y) {
                        // Relayout this control in the next line
                        i--;
                    } else {
                        ctrlSize = this.controls[i].isDisposed() ? new Point(0, 0)
                                : this.controls[i].computeSize(SWT.DEFAULT, bodyHeight);
                        rects[i].width = ctrlSize.x;
                        rects[i].x = this.isOnRight() ? x - ctrlSize.x : x;
                        rects[i].height = bodyHeight;
                        rects[i].x = size.y - ctrlSize.y - bodyBottom;
                        y += ctrlSize.y;
                    }
                }
            }
        
        }
        
        if (this.showChevron) {
            int i = 0, lastIndex = -1;
            while (i < this.priority.length && this.items[this.priority[i]].showing) {
                lastIndex = Math.max(lastIndex, this.priority[i++]);
            }
            if (lastIndex == -1)
                lastIndex = this.getSelectedIndex();
            if (lastIndex != -1) {
                VTabItem lastItem = this.items[lastIndex];
                int w = lastItem.x + lastItem.width + SPACING;
        
                rects[this.controls.length - 1].x = w;
            }
        }
        
        if (position != null)
            position[0] = overflow;
        return rects;
    }

    @objid ("f6f4253d-e58c-4c17-834c-188ccba7be5c")
    int getControlWidth(Point ctrlSize) {
        return this.getFixedTabWidth() == SWT.DEFAULT ? Math.max(this.tabWidth - 1, ctrlSize.x) : ctrlSize.x;
    }

/*
     * This class was not intended to be subclassed but this restriction cannot
     * be enforced without breaking backward compatibility.
     */
// protected void checkSubclass () {
// String name = getClass ().getName ();
// int index = name.lastIndexOf ('.');
// if (!name.substring (0, index + 1).equals ("org.eclipse.swt.custom.")) {
// SWT.error (SWT.ERROR_INVALID_SUBCLASS);
// }
// }
    @objid ("6864c5fc-7361-42e9-bb32-b5eb5c53bf5a")
    @Override
    public Rectangle computeTrim(int x, int y, int width, int height) {
        checkWidget();
        Rectangle trim = this.renderer.computeTrim(VTabFolderRenderer.PART_BODY, SWT.NONE, x, y, width, height);
        Point size = new Point(width, height);
        int wrapHeight = getWrappedHeight(size);
        if (this.isOnRight()) {
            trim.height += wrapHeight;
        } else {
            trim.y -= wrapHeight;
            trim.height += wrapHeight;
        }
        return trim;
    }

    @objid ("70ccc059-90fc-4bd9-8ef8-f4db225e5084")
    Image createButtonImage(Display display, int button) {
        GC tempGC = new GC(this);
        Point size = this.renderer.computeSize(button, SWT.NONE, tempGC, SWT.DEFAULT, SWT.DEFAULT);
        tempGC.dispose();
        Rectangle trim = this.renderer.computeTrim(button, SWT.NONE, 0, 0, 0, 0);
        Image image = new Image(display, size.x - trim.width, size.y - trim.height);
        GC gc = new GC(image);
        RGB transparent;
        if (button == VTabFolderRenderer.PART_CHEVRON_BUTTON) {
            transparent = new RGB(0xFF, 0xFF, 0xFF);
        } else {
            transparent = new RGB(0xFD, 0, 0);
        }
        Color transColor = new Color(display, transparent);
        gc.setBackground(transColor);
        gc.fillRectangle(image.getBounds());
        this.renderer.draw(button, SWT.NONE, new Rectangle(trim.x, trim.y, size.x, size.y), gc);
        gc.dispose();
        transColor.dispose();
        ImageData imageData = image.getImageData();
        imageData.transparentPixel = imageData.palette.getPixel(transparent);
        image.dispose();
        image = new Image(display, imageData);
        return image;
    }

    @objid ("d060ebfe-c523-43d0-8649-a548c99eca9f")
    void createItem(VTabItem item, int index) {
        if (0 > index || index > getItemCount())
            SWT.error(SWT.ERROR_INVALID_RANGE);
        item.parent = this;
        VTabItem[] newItems = new VTabItem[this.items.length + 1];
        System.arraycopy(this.items, 0, newItems, 0, index);
        newItems[index] = item;
        System.arraycopy(this.items, index, newItems, index + 1, this.items.length - index);
        this.items = newItems;
        if (this.getSelectedIndex() >= index)
            this.setSelectedIndex(this.getSelectedIndex() + 1);
        int[] newPriority = new int[this.priority.length + 1];
        int next = 0, priorityIndex = this.priority.length;
        for (int i = 0; i < this.priority.length; i++) {
            if (!this.mru && this.priority[i] == index) {
                priorityIndex = next++;
            }
            newPriority[next++] = this.priority[i] >= index ? this.priority[i] + 1 : this.priority[i];
        }
        newPriority[priorityIndex] = index;
        this.priority = newPriority;
        
        if (this.items.length == 1) {
            updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
        } else {
            updateFolder(REDRAW_TABS);
        }
    }

    @objid ("e66d74ab-ddd1-4143-a094-fcf3b9661ec3")
    void destroyItem(VTabItem item) {
        if (this.inDispose)
            return;
        int index = indexOf(item);
        if (index == -1)
            return;
        
        if (this.items.length == 1) {
            this.items = new VTabItem[0];
            this.priority = new int[0];
            this.setFirstIndex(-1);
            this.setSelectedIndex(-1);
        
            Control control = item.control;
            if (control != null && !control.isDisposed()) {
                control.setVisible(false);
            }
            setToolTipText(null);
            GC gc = new GC(this);
            setButtonBounds(gc);
            gc.dispose();
            redraw();
            return;
        }
        
        VTabItem[] newItems = new VTabItem[this.items.length - 1];
        System.arraycopy(this.items, 0, newItems, 0, index);
        System.arraycopy(this.items, index + 1, newItems, index, this.items.length - index - 1);
        this.items = newItems;
        
        int[] newPriority = new int[this.priority.length - 1];
        int next = 0;
        for (int i = 0; i < this.priority.length; i++) {
            if (this.priority[i] == index)
                continue;
            newPriority[next++] = this.priority[i] > index ? this.priority[i] - 1 : this.priority[i];
        }
        this.priority = newPriority;
        
        // move the selection if this item is selected
        if (this.getSelectedIndex() == index) {
            Control control = item.getControl();
            this.setSelectedIndex(-1);
            int nextSelection = this.mru ? this.priority[0] : Math.max(0, index - 1);
            setSelection(nextSelection, true);
            if (control != null && !control.isDisposed()) {
                control.setVisible(false);
            }
        } else if (this.getSelectedIndex() > index) {
            this.setSelectedIndex(this.getSelectedIndex() - 1);
        }
        
        updateFolder(UPDATE_TAB_HEIGHT | REDRAW_TABS);
    }

    /**
     * Returns <code>true</code> if the receiver's border is visible.
     * @return the receiver's border visibility state
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
    @objid ("cbb36b34-90b6-499d-aee6-10eb6e53c4a9")
    public boolean getBorderVisible() {
        checkWidget();
        return this.isBorderVisible();
    }

    @objid ("48f326a7-2d25-4946-b883-e51c32f2aacb")
    ToolBar getChevron() {
        if (this.chevronTb == null) {
            this.chevronTb = new ToolBar(this, SWT.FLAT);
            initAccessibleChevronTb();
            addTabControl(this.chevronTb, SWT.TRAIL, -1, false);
        }
        if (this.chevronItem == null) {
            this.chevronItem = new ToolItem(this.chevronTb, SWT.PUSH);
            this.chevronItem.setToolTipText(SWT.getMessage("SWT_ShowList"));
            this.chevronItem.addListener(SWT.Selection, this.listener);
        }
        return this.chevronTb;
    }

/* public */
    /**
     * Returns <code>true</code> if the chevron button is visible when
     * necessary.
     * @return the visibility of the chevron button
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has
     * been disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called
     * from the thread that created the receiver</li>
     * </ul>
     */
    @objid ("e2eb46e3-4945-40ed-9bc9-0cb1fdd51a59")
    boolean getChevronVisible() {
        checkWidget();
        return this.chevronVisible;
    }

    @objid ("08b68ecd-8e33-4903-8dc7-4250234e9a97")
    @Override
    public Rectangle getClientArea() {
        checkWidget();
        // TODO: HACK - find a better way to get padding
        Rectangle trim = this.renderer.computeTrim(VTabFolderRenderer.PART_BODY, SWT.FILL, 0, 0, 0, 0);
        Point size = getSize();
        int wrapHeight = getWrappedHeight(size);
        if (this.isOnRight()) {
            trim.height += wrapHeight;
        } else {
            trim.y -= wrapHeight;
            trim.height += wrapHeight;
        }
        
        int width = size.x - trim.width;
        int height = size.y - trim.height;
        return new Rectangle(-trim.x, -trim.y, width, height);
    }

    /**
     * Return the tab that is located at the specified index.
     * @param index the index of the tab item
     * @return the item at the specified index
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_INVALID_RANGE - if the index is out of range
     * </li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("19f95a1c-4bc9-430a-9b49-4db22a24631e")
    public VTabItem getItem(int index) {
        // checkWidget();
        if (index < 0 || index >= this.items.length)
            SWT.error(SWT.ERROR_INVALID_RANGE);
        return this.items[index];
    }

    /**
     * Gets the item at a point in the widget.
     * @param pt the point in coordinates relative to the VTabFolder
     * @return the item at a point or null
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("66f0a1d1-5f00-4d33-9c00-4bb8a853d9e3")
    public VTabItem getItem(Point pt) {
        // checkWidget();
        if (this.items.length == 0)
            return null;
        runUpdate();
        Point size = getSize();
        Rectangle trim = this.renderer.computeTrim(VTabFolderRenderer.PART_BORDER, SWT.NONE, 0, 0, 0, 0);
        if (size.x <= trim.width)
            return null;
        for (int i = 0; i < this.priority.length; i++) {
            VTabItem item = this.items[this.priority[i]];
            Rectangle rect = item.getBounds();
            if (rect.contains(pt))
                return item;
        }
        return null;
    }

    /**
     * Return the number of tabs in the folder.
     * @return the number of tabs in the folder
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("c012c452-ade6-48f3-9011-fb8394bed078")
    public int getItemCount() {
        // checkWidget();
        return this.items.length;
    }

    /**
     * Return the tab items.
     * @return the tab items
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("a6a17227-793d-4dbd-9599-12fda3367471")
    public VTabItem[] getItems() {
        // checkWidget();
        VTabItem[] tabItems = new VTabItem[this.items.length];
        System.arraycopy(this.items, 0, tabItems, 0, this.items.length);
        return tabItems;
    }

    @objid ("1f591bfe-a2c8-4408-bc6b-8781442ca6a1")
    int getTopItemEdge(GC gc, int part) {
        Rectangle trim = this.renderer.computeTrim(part, SWT.NONE, 0, 0, 0, 0);
        int y = -trim.y;
        int height = 0;
        for (int i = 0; i < this.controls.length; i++) {
            if ((this.controlAlignments[i] & SWT.LEAD) != 0 && !this.controls[i].isDisposed()
                    && this.controls[i].getVisible()) {
                height += this.controls[i].computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
            }
        }
        if (height != 0)
            height += SPACING * 2;
        y += height;
        return Math.max(0, y);
    }

/*
     * Return the lowercase of the first non-'&' character following an '&'
     * character in the given string. If there are no '&' characters in the
     * given string, return '\0'.
     */
    @objid ("199b2ab9-1b74-4a73-90d5-2ae3c6e65a6b")
    char _findMnemonic(String string) {
        if (string == null)
            return '\0';
        int index = 0;
        int length = string.length();
        do {
            while (index < length && string.charAt(index) != '&')
                index++;
            if (++index >= length)
                return '\0';
            if (string.charAt(index) != '&')
                return Character.toLowerCase(string.charAt(index));
            index++;
        } while (index < length);
        return '\0';
    }

    @objid ("d59830a3-07f3-49a0-aa12-923b4c6c139f")
    String stripMnemonic(String string) {
        int index = 0;
        int length = string.length();
        do {
            while ((index < length) && (string.charAt(index) != '&'))
                index++;
            if (++index >= length)
                return string;
            if (string.charAt(index) != '&') {
                return string.substring(0, index - 1) + string.substring(index, length);
            }
            index++;
        } while (index < length);
        return string;
    }

    /**
     * Returns the number of characters that will appear in a fully compressed
     * tab.
     * @return number of characters that will appear in a fully compressed tab
     * 
     * @since 3.0
     */
    @objid ("ed99d373-8f0b-4ad1-bca5-4abb7c612c69")
    public int getMinimumCharacters() {
        checkWidget();
        return this.getMinChars();
    }

    /**
     * Returns <code>true</code> if the receiver displays most recently used
     * tabs and <code>false</code> otherwise.
     * <p>
     * When there is not enough horizontal space to show all the tabs, by
     * default, tabs are shown sequentially from left to right in order of their
     * index. When the MRU visibility is turned on, the tabs that are visible
     * will be the tabs most recently selected. Tabs will still maintain their
     * left to right order based on index but only the most recently selected
     * tabs are visible.
     * <p>
     * For example, consider a VTabFolder that contains "Tab 1", "Tab 2",
     * "Tab 3" and "Tab 4" (in order by index). The user selects "Tab 1" and
     * then "Tab 3". If the VTabFolder is now compressed so that only two tabs
     * are visible, by default, "Tab 2" and "Tab 3" will be shown ("Tab 3" since
     * it is currently selected and "Tab 2" because it is the previous item in
     * index order). If MRU visibility is enabled, the two visible tabs will be
     * "Tab 1" and "Tab 3" (in that order from left to right).
     * </p>
     * @return the receiver's header's visibility state
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     * 
     * @since 3.1
     */
    @objid ("4f7479d5-35ca-472d-9204-f18ac78f4348")
    public boolean getMRUVisible() {
        checkWidget();
        return this.mru;
    }

    /**
     * Returns the receiver's renderer.
     * @see #setRenderer(VTabFolderRenderer)
     * @see VTabFolderRenderer
     * 
     * @since 3.6
     * @return the receiver's renderer
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("ac55fd24-c473-4240-8d9c-0fc0baa3a4a9")
    public VTabFolderRenderer getRenderer() {
        checkWidget();
        return this.renderer;
    }

    @objid ("7cfc3ea0-c19b-4a97-aab0-7327560aa57c")
    int getBottomItemEdge(GC gc) {
        Rectangle trim = this.renderer.computeTrim(VTabFolderRenderer.PART_BORDER, SWT.NONE, 0, 0, 0, 0);
        int y = getSize().y + (trim.width + trim.x);
        int height = 0;
        for (int i = 0; i < this.controls.length; i++) {
            int align = this.controlAlignments[i];
            if ((align & SWT.WRAP) == 0 && (align & SWT.LEAD) == 0 && !this.controls[i].isDisposed()
                    && this.controls[i].getVisible()) {
                Point rightSize = this.controls[i].computeSize(SWT.DEFAULT, SWT.DEFAULT);
                height += rightSize.y;
            }
        }
        if (height != 0)
            height += SPACING * 2;
        y -= height;
        return Math.max(0, y);
    }

    /**
     * Return the selected tab item, or null if there is no selection.
     * @return the selected tab item, or null if none has been selected
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("6ccebc65-25c7-4c3f-ab03-07b92aec9a56")
    public VTabItem getSelection() {
        // checkWidget();
        if (this.getSelectedIndex() == -1)
            return null;
        return this.items[this.getSelectedIndex()];
    }

    /**
     * Returns the receiver's selection background color.
     * @return the selection background color of the receiver
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
    @objid ("2d487f2d-7ca3-4cce-a92a-603a468d5ccb")
    public Color getSelectionBackground() {
        checkWidget();
        return this.selectionBackground;
    }

    /**
     * Returns the receiver's selection foreground color.
     * @return the selection foreground color of the receiver
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
    @objid ("c7dd54fa-7535-44b0-a13d-9c25d24cfc89")
    public Color getSelectionForeground() {
        checkWidget();
        return this.selectionForeground;
    }

    /**
     * Return the index of the selected tab item, or -1 if there is no
     * selection.
     * @return the index of the selected tab item or -1
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("b7e20204-3b3e-42dd-ac0c-e11942f2ae62")
    public int getSelectionIndex() {
        // checkWidget();
        return this.getSelectedIndex();
    }

    /**
     * Returns <code>true</code> if the VTabFolder is rendered with a simple,
     * traditional shape.
     * @return <code>true</code> if the VTabFolder is rendered with a simple
     * shape
     * 
     * @since 3.0
     */
    @objid ("ec9a88dc-23dd-4f3e-977b-37dabb8efbc5")
    public boolean getSimple() {
        checkWidget();
        return this.isSimple();
    }

    /**
     * Returns <code>true</code> if the VTabFolder only displays the selected
     * tab and <code>false</code> if the VTabFolder displays multiple tabs.
     * @return <code>true</code> if the VTabFolder only displays the selected
     * tab and <code>false</code> if the VTabFolder displays multiple
     * tabs
     * 
     * @since 3.0
     */
    @objid ("3811a69c-4cf2-4188-b173-f60f36376eb8")
    public boolean getSingle() {
        checkWidget();
        return this.isSingle();
    }

    @objid ("0b6c02e9-a00f-4ef1-9bc3-226099012e2f")
    @Override
    public int getStyle() {
        int style = super.getStyle();
        style &= ~(SWT.TOP | SWT.BOTTOM);
        style |= this.isOnRight() ? SWT.BOTTOM : SWT.TOP;
        style &= ~(SWT.SINGLE | SWT.MULTI);
        style |= this.isSingle() ? SWT.SINGLE : SWT.MULTI;
        if (this.isBorderVisible())
            style |= SWT.BORDER;
        style &= ~SWT.CLOSE;
        return style;
    }

    /**
     * Returns the height of the tab
     * @return the height of the tab
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("54494ed8-9ae7-4b81-b8ca-a330d185ca6c")
    public int getTabWidth() {
        checkWidget();
        if (this.getFixedTabWidth() != SWT.DEFAULT)
            return this.getFixedTabWidth();
        return this.tabWidth - 1; // -1 for line drawn across top of tab
        // //TODO:
        // replace w/ computeTrim of tab area?
    }

    /**
     * Returns the position of the tab. Possible values are SWT.LEFT or
     * SWT.RIGHT.
     * @return the position of the tab
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("decc7362-dce6-452a-864f-8daea07088d6")
    public int getTabPosition() {
        checkWidget();
        return this.isOnRight() ? SWT.RIGHT : SWT.LEFT;
    }

    /**
     * Returns the control in the top right corner of the tab folder. Typically
     * this is a close button or a composite with a menu and close button.
     * @return the control in the top right corner of the tab folder or null
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     * 
     * @since 2.1
     */
    @objid ("d8c1c0f0-82cd-4b81-bbb0-c170ec8eafea")
    public Control getTopRight() {
        checkWidget();
        return this.topRight;
    }

    /**
     * Returns the alignment of the top right control.
     * @return the alignment of the top right control which is either
     * <code>SWT.RIGHT</code> or <code>SWT.FILL</code>
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     * 
     * @since 3.6
     */
    @objid ("11dbd87a-a313-4f79-bbd4-6ee78c7ef011")
    public int getTopRightAlignment() {
        checkWidget();
        return this.topRightAlignment;
    }

    /**
     * Returns <code>true</code> if an image appears in unselected tabs.
     * @return <code>true</code> if an image appears in unselected tabs
     * 
     * @since 3.0
     */
    @objid ("b26c9f0c-c7d1-4f71-9a43-caffb7a6df90")
    public boolean getUnselectedImageVisible() {
        checkWidget();
        return this.showUnselectedImage;
    }

    /**
     * Return the index of the specified tab or -1 if the tab is not in the
     * receiver.
     * @param item the tab item for which the index is required
     * @return the index of the specified tab item or -1
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("aac72c06-ce57-409f-a68d-1bb14d06587e")
    public int indexOf(VTabItem item) {
        checkWidget();
        if (item == null) {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        }
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == item)
                return i;
        }
        return -1;
    }

    @objid ("1a7aeb96-375d-411e-ab95-2eb05c8378b5")
    void initAccessible() {
        final Accessible accessible = getAccessible();
        accessible.addAccessibleListener(new AccessibleAdapter() {
            @Override
            public void getName(AccessibleEvent e) {
                VTabItem item = null;
                int childID = e.childID;
                if (childID == ACC.CHILDID_SELF) {
                    if (VTabFolder.this.getSelectedIndex() != -1) {
                        item = VTabFolder.this.items[VTabFolder.this.getSelectedIndex()];
                    }
                } else if (childID >= 0 && childID < VTabFolder.this.items.length) {
                    item = VTabFolder.this.items[childID];
                }
                e.result = item == null ? null : stripMnemonic(item.getText());
            }
        
            @Override
            public void getHelp(AccessibleEvent e) {
                String help = null;
                int childID = e.childID;
                if (childID == ACC.CHILDID_SELF) {
                    help = getToolTipText();
                } else if (childID >= 0 && childID < VTabFolder.this.items.length) {
                    help = VTabFolder.this.items[childID].getToolTipText();
                }
                e.result = help;
            }
        
            @Override
            public void getKeyboardShortcut(AccessibleEvent e) {
                String shortcut = null;
                int childID = e.childID;
                if (childID >= 0 && childID < VTabFolder.this.items.length) {
                    String text = VTabFolder.this.items[childID].getText();
                    if (text != null) {
                        char mnemonic = _findMnemonic(text);
                        if (mnemonic != '\0') {
                            shortcut = SWT.getMessage("SWT_Page_Mnemonic", new Object[] { new Character(mnemonic) }); //$NON-NLS-1$
                        }
                    }
                }
                if (childID == ACC.CHILDID_SELF) {
                    shortcut = SWT.getMessage("SWT_SwitchPage_Shortcut"); //$NON-NLS-1$
                }
                e.result = shortcut;
            }
        });
        
        accessible.addAccessibleControlListener(new AccessibleControlAdapter() {
            @Override
            public void getChildAtPoint(AccessibleControlEvent e) {
                Point testPoint = toControl(e.x, e.y);
                int childID = ACC.CHILDID_NONE;
                for (int i = 0; i < VTabFolder.this.items.length; i++) {
                    if (VTabFolder.this.items[i].getBounds().contains(testPoint)) {
                        childID = i;
                        break;
                    }
                }
                if (childID == ACC.CHILDID_NONE) {
                    Rectangle location = getBounds();
                    location.x = location.y = 0;
                    location.height = location.height - getClientArea().height;
                    if (location.contains(testPoint)) {
                        childID = ACC.CHILDID_SELF;
                    }
                }
                e.childID = childID;
            }
        
            @Override
            public void getLocation(AccessibleControlEvent e) {
                Rectangle location = null;
                Point pt = null;
                int childID = e.childID;
                if (childID == ACC.CHILDID_SELF) {
                    location = getBounds();
                    pt = getParent().toDisplay(location.x, location.y);
                } else {
                    if (childID >= 0 && childID < VTabFolder.this.items.length
                            && VTabFolder.this.items[childID].showing) {
                        location = VTabFolder.this.items[childID].getBounds();
                    }
                    if (location != null) {
                        pt = toDisplay(location.x, location.y);
                    }
                }
                if (location != null && pt != null) {
                    e.x = pt.x;
                    e.y = pt.y;
                    e.width = location.width;
                    e.height = location.height;
                }
            }
        
            @Override
            public void getChildCount(AccessibleControlEvent e) {
                e.detail = VTabFolder.this.items.length;
            }
        
            @Override
            public void getDefaultAction(AccessibleControlEvent e) {
                String action = null;
                int childID = e.childID;
                if (childID >= 0 && childID < VTabFolder.this.items.length) {
                    action = SWT.getMessage("SWT_Switch"); //$NON-NLS-1$
                }
                e.result = action;
            }
        
            @Override
            public void getFocus(AccessibleControlEvent e) {
                int childID = ACC.CHILDID_NONE;
                if (isFocusControl()) {
                    if (VTabFolder.this.getSelectedIndex() == -1) {
                        childID = ACC.CHILDID_SELF;
                    } else {
                        childID = VTabFolder.this.getSelectedIndex();
                    }
                }
                e.childID = childID;
            }
        
            @Override
            public void getRole(AccessibleControlEvent e) {
                int role = 0;
                int childID = e.childID;
                if (childID == ACC.CHILDID_SELF) {
                    role = ACC.ROLE_TABFOLDER;
                } else if (childID >= 0 && childID < VTabFolder.this.items.length) {
                    role = ACC.ROLE_TABITEM;
                }
                e.detail = role;
            }
        
            @Override
            public void getSelection(AccessibleControlEvent e) {
                e.childID = (VTabFolder.this.getSelectedIndex() == -1) ? ACC.CHILDID_NONE : VTabFolder.this.getSelectedIndex();
            }
        
            @Override
            public void getState(AccessibleControlEvent e) {
                int state = 0;
                int childID = e.childID;
                if (childID == ACC.CHILDID_SELF) {
                    state = ACC.STATE_NORMAL;
                } else if (childID >= 0 && childID < VTabFolder.this.items.length) {
                    state = ACC.STATE_SELECTABLE;
                    if (isFocusControl()) {
                        state |= ACC.STATE_FOCUSABLE;
                    }
                    if (VTabFolder.this.getSelectedIndex() == childID) {
                        state |= ACC.STATE_SELECTED;
                        if (isFocusControl()) {
                            state |= ACC.STATE_FOCUSED;
                        }
                    }
                }
                e.detail = state;
            }
        
            @Override
            public void getChildren(AccessibleControlEvent e) {
                int childIdCount = VTabFolder.this.items.length;
                Object[] children = new Object[childIdCount];
                for (int i = 0; i < childIdCount; i++) {
                    children[i] = new Integer(i);
                }
                e.children = children;
            }
        });
        
        addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (isFocusControl()) {
                    if (VTabFolder.this.getSelectedIndex() == -1) {
                        accessible.setFocus(ACC.CHILDID_SELF);
                    } else {
                        accessible.setFocus(VTabFolder.this.getSelectedIndex());
                    }
                }
            }
        });
        
        addListener(SWT.FocusIn, new Listener() {
            @Override
            public void handleEvent(Event event) {
                if (VTabFolder.this.getSelectedIndex() == -1) {
                    accessible.setFocus(ACC.CHILDID_SELF);
                } else {
                    accessible.setFocus(VTabFolder.this.getSelectedIndex());
                }
            }
        });
    }

    @objid ("553d24fe-8ce1-47a0-ba9f-9c2a53d3050c")
    void initAccessibleChevronTb() {
        this.chevronTb.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            @Override
            public void getName(AccessibleEvent e) {
                if (e.childID != ACC.CHILDID_SELF) {
                    if (VTabFolder.this.chevronItem != null
                            && e.childID == VTabFolder.this.chevronTb.indexOf(VTabFolder.this.chevronItem)) {
                        e.result = VTabFolder.this.chevronItem.getToolTipText();
                    }
                }
            }
        });
    }

    @objid ("642e3dca-b7b5-46ed-aad0-9167c44acfab")
    void onKeyDown(Event event) {
        runUpdate();
        switch (event.keyCode) {
        case SWT.ARROW_LEFT:
        case SWT.ARROW_RIGHT:
            int count = this.items.length;
            if (count == 0)
                return;
            if (this.getSelectedIndex() == -1)
                return;
            int leadKey = (getStyle() & SWT.RIGHT_TO_LEFT) != 0 ? SWT.ARROW_RIGHT : SWT.ARROW_LEFT;
            int offset = event.keyCode == leadKey ? -1 : 1;
            int index;
            if (!this.mru) {
                index = this.getSelectedIndex() + offset;
            } else {
                int[] visible = new int[this.items.length];
                int idx = 0;
                int current = -1;
                for (int i = 0; i < this.items.length; i++) {
                    if (this.items[i].showing) {
                        if (i == this.getSelectedIndex())
                            current = idx;
                        visible[idx++] = i;
                    }
                }
                if (current + offset >= 0 && current + offset < idx) {
                    index = visible[current + offset];
                } else {
                    if (this.showChevron) {
                        Rectangle chevronRect = this.chevronItem.getBounds();
                        chevronRect = event.display.map(this.chevronTb, this, chevronRect);
                        VTabFolderEvent e = new VTabFolderEvent(this);
                        e.widget = this;
                        e.time = event.time;
                        e.x = chevronRect.x;
                        e.y = chevronRect.y;
                        e.width = chevronRect.width;
                        e.height = chevronRect.height;
                        e.doit = true;
                        for (int i = 0; i < this.folderListeners.length; i++) {
                            this.folderListeners[i].showList(e);
                        }
                        if (e.doit && !isDisposed()) {
                            showList(chevronRect);
                        }
                    }
                    return;
                }
            }
            if (index < 0 || index >= count)
                return;
            setSelection(index, true);
            forceFocus();
        }
    }

    @objid ("1c6c51b6-470a-46ee-8d69-553f6ffe9796")
    void onDispose(Event event) {
        removeListener(SWT.Dispose, this.listener);
        notifyListeners(SWT.Dispose, event);
        event.type = SWT.None;
        /*
         * Usually when an item is disposed, destroyItem will change the size of
         * the items array, reset the bounds of all the tabs and manage the
         * widget associated with the tab. Since the whole folder is being
         * disposed, this is not necessary. For speed the inDispose flag is used
         * to skip over this part of the item dispose.
         */
        this.inDispose = true;
        
        if (this.showMenu != null && !this.showMenu.isDisposed()) {
            this.showMenu.dispose();
            this.showMenu = null;
        }
        int length = this.items.length;
        for (int i = 0; i < length; i++) {
            if (this.items[i] != null) {
                this.items[i].dispose();
            }
        }
        this.selectionBgImage = null;
        this.selectionBackground = null;
        this.selectionForeground = null;
        
        if (this.controlBkImages != null) {
            for (int i = 0; i < this.controlBkImages.length; i++) {
                if (this.controlBkImages[i] != null) {
                    this.controlBkImages[i].dispose();
                    this.controlBkImages[i] = null;
                }
            }
            this.controlBkImages = null;
        }
        this.controls = null;
        this.controlAlignments = null;
        this.controlRects = null;
        
        
        if (this.chevronImage != null)
            this.chevronImage.dispose();
        this.chevronImage = null;
        
        if (this.renderer != null)
            this.renderer.dispose();
        this.renderer = null;
        
        
        this.chevronItem = null;
        this.chevronTb = null;
        
        if (this.folderListeners.length != 0)
            this.folderListeners = new VTabFolder2Listener[0];
    }

    @objid ("77e6a705-2a35-4d6e-86db-6eb925a57e77")
    void onDragDetect(Event event) {
        boolean consume = false;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i].closeRect.contains(event.x, event.y)) {
                consume = true;
                break;
            }
        }
        if (consume) {
            event.type = SWT.None;
        }
    }

    @objid ("114a7739-76fe-4314-98ef-b6001c446429")
    void onFocus(Event event) {
        checkWidget();
        if (this.getSelectedIndex() >= 0) {
            redraw();
        } else {
            setSelection(0, true);
        }
    }

    @objid ("6376febe-3a1b-4dfb-983d-0ed7eeb601d3")
    boolean onMnemonic(Event event, boolean doit) {
        char key = event.character;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                char mnemonic = _findMnemonic(this.items[i].getText());
                if (mnemonic != '\0') {
                    if (Character.toLowerCase(key) == mnemonic) {
                        if (doit) {
                            setSelection(i, true);
                            forceFocus();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @objid ("10dfe25c-8bfc-4874-8ac3-bc48d278d412")
    void onMenuDetect(Event event) {
        if (event.detail == SWT.MENU_KEYBOARD) {
            if (this.getSelectedIndex() != -1) {
                VTabItem item = this.items[this.getSelectedIndex()];
                Rectangle rect = getDisplay().map(this, null, item.getBounds());
                if (!rect.contains(event.x, event.y)) {
                    /*
                     * If the mouse is not in the currently-selected tab, then
                     * pop up the menu near the top-right corner of the current
                     * tab.
                     */
                    Rectangle itemTrim = this.renderer.computeTrim(this.getSelectedIndex(), SWT.NONE, 0, 0, 0, 0);
                    Rectangle closeTrim = this.renderer.computeTrim(VTabFolderRenderer.PART_CLOSE_BUTTON, SWT.NONE, 0,
                            0, 0, 0);
                    event.x = rect.x + rect.width - item.closeRect.width + itemTrim.x - closeTrim.width;
                    event.y = rect.y - itemTrim.y - closeTrim.y;
                }
            }
        }
    }

    @objid ("e410eedf-3e0a-407e-aab1-0cd7f148d5bc")
    void onMouseDoubleClick(Event event) {
        if (event.button != 1 || (event.stateMask & SWT.BUTTON2) != 0 || (event.stateMask & SWT.BUTTON3) != 0)
            return;
        Event e = new Event();
        e.item = getItem(new Point(event.x, event.y));
        if (e.item != null) {
            notifyListeners(SWT.DefaultSelection, e);
        }
    }

    @objid ("3daaf80b-9134-4e59-b8c9-380a5ec33531")
    void onMouse(Event event) {
        if (isDisposed()) {
            return;
        }
        int x = event.x, y = event.y;
        switch (event.type) {
        case SWT.MouseEnter: {
            setToolTipText(null);
            break;
        }
        case SWT.MouseExit: {
        
            for (int i = 0; i < this.items.length; i++) {
                VTabItem item = this.items[i];
                if (i != this.getSelectedIndex() && item.closeImageState != SWT.BACKGROUND) {
                    item.closeImageState = SWT.BACKGROUND;
                    redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height, false);
                }
                if ((item.state & SWT.HOT) != 0) {
                    item.state &= ~SWT.HOT;
                    redraw(item.x, item.y, item.width, item.height, false);
                }
                if (i == this.getSelectedIndex() && item.closeImageState != SWT.NONE) {
                    item.closeImageState = SWT.NONE;
                    redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height, false);
                }
            }
            break;
        }
        case SWT.MouseHover:
        case SWT.MouseDown: {
        
            if (this.hoverTb && this.hoverRect.contains(x, y) && !this.hovering) {
                this.hovering = true;
                updateItems();
                this.hoverTimerRunning = true;
                event.display.timerExec(2000, new Runnable() {
                    @Override
                    public void run() {
                        if (isDisposed())
                            return;
                        if (VTabFolder.this.hovering) {
                            Display display = getDisplay();
                            Control c = display.getCursorControl();
                            boolean reschedule = false;
                            if (c != null) {
                                for (int i = 0; i < VTabFolder.this.controls.length; i++) {
                                    Control temp = c;
                                    do {
                                        if (temp.equals(VTabFolder.this.controls[i])) {
                                            reschedule = true;
                                        } else {
                                            temp = temp.getParent();
                                            if (temp == null || temp.equals(VTabFolder.this))
                                                break;
                                        }
                                    } while (!reschedule);
                                    if (reschedule)
                                        break;
                                }
                            }
                            if (reschedule && VTabFolder.this.hoverTimerRunning) {
                                display.timerExec(2000, this);
                            } else {
                                VTabFolder.this.hovering = false;
                                updateItems();
                            }
                        }
                    }
                });
                return;
            }
            if (event.button != 1)
                return;
            VTabItem item = null;
            if (this.isSingle()) {
                if (this.getSelectedIndex() != -1) {
                    Rectangle bounds = this.items[this.getSelectedIndex()].getBounds();
                    if (bounds.contains(x, y)) {
                        item = this.items[this.getSelectedIndex()];
                    }
                }
            } else {
                for (int i = 0; i < this.items.length; i++) {
                    Rectangle bounds = this.items[i].getBounds();
                    if (bounds.contains(x, y)) {
                        item = this.items[i];
                    }
                }
            }
            if (item != null) {
                if (item.closeRect.contains(x, y)) {
                    item.closeImageState = SWT.SELECTED;
                    redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height, false);
                    update();
                    return;
                }
                int index = indexOf(item);
                if (item.showing) {
                    int oldSelectedIndex = this.getSelectedIndex();
                    setSelection(index, true);
                    if (oldSelectedIndex == this.getSelectedIndex()) {
                        /*
                         * If the click is on the selected tabitem, then set
                         * focus to the tabfolder
                         */
                        forceFocus();
                    }
                }
                return;
            }
            break;
        }
        case SWT.MouseMove: {
            _setToolTipText(event.x, event.y);
            boolean close = false;
            for (int i = 0; i < this.items.length; i++) {
                VTabItem item = this.items[i];
                close = false;
                if (item.getBounds().contains(x, y)) {
                    close = true;
                    if (item.closeRect.contains(x, y)) {
                        if (item.closeImageState != SWT.SELECTED && item.closeImageState != SWT.HOT) {
                            item.closeImageState = SWT.HOT;
                            redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height,
                                    false);
                        }
                    } else {
                        if (item.closeImageState != SWT.NONE) {
                            item.closeImageState = SWT.NONE;
                            redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height,
                                    false);
                        }
                    }
                    if ((item.state & SWT.HOT) == 0) {
                        item.state |= SWT.HOT;
                        redraw(item.x, item.y, item.width, item.height, false);
                    }
                }
                if (i != this.getSelectedIndex() && item.closeImageState != SWT.BACKGROUND && !close) {
                    item.closeImageState = SWT.BACKGROUND;
                    redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height, false);
                }
                if ((item.state & SWT.HOT) != 0 && !close) {
                    item.state &= ~SWT.HOT;
                    redraw(item.x, item.y, item.width, item.height, false);
                }
                if (i == this.getSelectedIndex() && item.closeImageState != SWT.NONE && !close) {
                    item.closeImageState = SWT.NONE;
                    redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height, false);
                }
            }
            break;
        }
        case SWT.MouseUp: {
            if (event.button != 1)
                return;
            VTabItem item = null;
            if (this.isSingle()) {
                if (this.getSelectedIndex() != -1) {
                    Rectangle bounds = this.items[this.getSelectedIndex()].getBounds();
                    if (bounds.contains(x, y)) {
                        item = this.items[this.getSelectedIndex()];
                    }
                }
            } else {
                for (int i = 0; i < this.items.length; i++) {
                    Rectangle bounds = this.items[i].getBounds();
                    if (bounds.contains(x, y)) {
                        item = this.items[i];
                    }
                }
            }
            if (item != null) {
                if (item.closeRect.contains(x, y)) {
                    boolean selected = item.closeImageState == SWT.SELECTED;
                    item.closeImageState = SWT.HOT;
                    redraw(item.closeRect.x, item.closeRect.y, item.closeRect.width, item.closeRect.height, false);
                    if (!selected)
                        return;
                    VTabFolderEvent e = new VTabFolderEvent(this);
                    e.widget = this;
                    e.time = event.time;
                    e.item = item;
                    e.doit = true;
                    for (int j = 0; j < this.folderListeners.length; j++) {
                        VTabFolder2Listener listener = this.folderListeners[j];
                        listener.close(e);
                    }
        
                    if (e.doit)
                        item.dispose();
                    if (!isDisposed() && item.isDisposed()) {
                        Display display = getDisplay();
                        Point pt = display.getCursorLocation();
                        pt = display.map(null, this, pt.x, pt.y);
                        VTabItem nextItem = getItem(pt);
                        if (nextItem != null) {
                            if (nextItem.closeRect.contains(pt)) {
                                if (nextItem.closeImageState != SWT.SELECTED && nextItem.closeImageState != SWT.HOT) {
                                    nextItem.closeImageState = SWT.HOT;
                                    redraw(nextItem.closeRect.x, nextItem.closeRect.y, nextItem.closeRect.width,
                                            nextItem.closeRect.height, false);
                                }
                            } else {
                                if (nextItem.closeImageState != SWT.NONE) {
                                    nextItem.closeImageState = SWT.NONE;
                                    redraw(nextItem.closeRect.x, nextItem.closeRect.y, nextItem.closeRect.width,
                                            nextItem.closeRect.height, false);
                                }
                            }
                        }
                    }
                    return;
                }
            }
        }
        }
    }

    @objid ("2d8912eb-a3a7-4d93-a798-c0de1ace0779")
    void onPageTraversal(Event event) {
        int count = this.items.length;
        if (count == 0)
            return;
        int index = this.getSelectedIndex();
        if (index == -1) {
            index = 0;
        } else {
            int offset = (event.detail == SWT.TRAVERSE_PAGE_NEXT) ? 1 : -1;
            if (!this.mru) {
                index = (this.getSelectedIndex() + offset + count) % count;
            } else {
                int[] visible = new int[this.items.length];
                int idx = 0;
                int current = -1;
                for (int i = 0; i < this.items.length; i++) {
                    if (this.items[i].showing) {
                        if (i == this.getSelectedIndex())
                            current = idx;
                        visible[idx++] = i;
                    }
                }
                if (current + offset >= 0 && current + offset < idx) {
                    index = visible[current + offset];
                } else {
                    if (this.showChevron) {
                        Rectangle chevronRect = this.chevronItem.getBounds();
                        chevronRect = event.display.map(this.chevronTb, this, chevronRect);
                        VTabFolderEvent e = new VTabFolderEvent(this);
                        e.widget = this;
                        e.time = event.time;
                        e.x = chevronRect.x;
                        e.y = chevronRect.y;
                        e.width = chevronRect.width;
                        e.height = chevronRect.height;
                        e.doit = true;
                        for (int i = 0; i < this.folderListeners.length; i++) {
                            this.folderListeners[i].showList(e);
                        }
                        if (e.doit && !isDisposed()) {
                            showList(chevronRect);
                        }
                    }
                }
            }
        }
        setSelection(index, true);
    }

    @objid ("4da9cf9e-4aa0-4684-a412-b2421434192c")
    void onPaint(Event event) {
        if (this.inDispose)
            return;
        Font font = getFont();
        if (this.oldFont == null || !this.oldFont.equals(font)) {
            // handle case where default font changes
            this.oldFont = font;
            if (!updateTabWidth(false)) {
                updateItems();
                redraw();
                return;
            }
        }
        
        GC gc = event.gc;
        Font gcFont = gc.getFont();
        Color gcBackground = gc.getBackground();
        Color gcForeground = gc.getForeground();
        
        // Useful for debugging paint problems
        //        {
        //            Point size = getSize();
        //            gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_GREEN));
        //            gc.fillRectangle(-10, -10, size.x + 20, size.y + 20);
        //        }
        
        Point size = getSize();
        Rectangle bodyRect = new Rectangle(0, 0, size.x, size.y);
        this.renderer.draw(VTabFolderRenderer.PART_BODY, SWT.BACKGROUND | SWT.FOREGROUND, bodyRect, gc);
        
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
        
        this.renderer.draw(VTabFolderRenderer.PART_HEADER, SWT.BACKGROUND | SWT.FOREGROUND, bodyRect, gc);
        
        gc.setFont(gcFont);
        gc.setBackground(gcBackground);
        
        if (!this.isSingle()) {
            for (int i = 0; i < this.items.length; i++) {
                Rectangle itemBounds = this.items[i].getBounds();
                if (i != this.getSelectedIndex() && event.getBounds().intersects(itemBounds)) {
                    this.renderer.draw(i, SWT.BACKGROUND | SWT.FOREGROUND | this.items[i].state, itemBounds, gc);
                }
            }
        }
        
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
        
        if (this.getSelectedIndex() != -1) {
            this.renderer.draw(this.getSelectedIndex(),
                    this.items[this.getSelectedIndex()].state | SWT.BACKGROUND | SWT.FOREGROUND,
                    this.items[this.getSelectedIndex()].getBounds(), gc);
        }
        
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
        
        if (this.hoverTb) {
            // Rectangle trim =
            // this.renderer.computeTrim(VTabFolderRenderer.PART_BORDER,
            // SWT.NONE, 0, 0, 0, 0);
            // int x = getSize().x - (trim.width + trim.x);
            // this.hoverRect = new Rectangle(x - 16 - SPACING, 2, 16,
            // getTabHeight() - 2);
            // gc.setForeground(gc.getDevice().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
            // x = this.hoverRect.x;
            // int y = this.hoverRect.y;
            // gc.setBackground(gc.getDevice().getSystemColor(SWT.COLOR_WHITE));
            // gc.fillRectangle(x + this.hoverRect.width - 6, y, 5, 5);
            // gc.drawRectangle(x + this.hoverRect.width - 6, y, 5, 5);
            // gc.drawLine(x + this.hoverRect.width - 6, y + 2, x +
            // this.hoverRect.width - 6 + 5, y + 2);
            // gc.fillRectangle(x, y, 5, 2);
            // gc.drawRectangle(x, y, 5, 2);
        }
        gc.setFont(gcFont);
        gc.setForeground(gcForeground);
        gc.setBackground(gcBackground);
    }

    @objid ("defb65e4-e84c-4e3d-93c1-a89adc7b23c3")
    void onResize(Event event) {
        if (this.inDispose)
            return;
        if (this.ignoreResize)
            return;
        if (updateItems()) {
            redrawTabs();
        }
        Point size = getSize();
        if (this.oldSize == null) {
            redraw();
        } else {
            if (this.isOnRight() && size.y != this.oldSize.y) {
                redraw();
            } else {
                int x1 = Math.min(size.x, this.oldSize.x);
                Rectangle trim = this.renderer.computeTrim(VTabFolderRenderer.PART_BODY, SWT.NONE, 0, 0, 0, 0);
                if (size.x != this.oldSize.x)
                    x1 -= trim.width + trim.x - this.marginWidth + 2;
                if (!this.isSimple())
                    x1 -= 5; // rounded top right corner
                    int y1 = Math.min(size.y, this.oldSize.y);
                    if (size.y != this.oldSize.y)
                        y1 -= trim.height + trim.y - this.marginHeight;
                    int x2 = Math.max(size.x, this.oldSize.x);
                    int y2 = Math.max(size.y, this.oldSize.y);
                    redraw(0, y1, x2, y2 - y1, false);
                    redraw(x1, 0, x2 - x1, y2, false);
                    if (this.hoverTb) {
                        redraw(this.hoverRect.x, this.hoverRect.y, this.hoverRect.width, this.hoverRect.height, false);
                    }
            }
        }
        this.oldSize = size;
    }

    @objid ("d2262cca-bb6d-46c1-9647-125707b66970")
    void onSelection(Event event) {
        if (this.hovering) {
            this.hovering = false;
            updateItems();
        }
        if (event.widget == this.chevronItem) {
            Rectangle chevronRect = this.chevronItem.getBounds();
            chevronRect = event.display.map(this.chevronTb, this, chevronRect);
            VTabFolderEvent e = new VTabFolderEvent(this);
            e.widget = this;
            e.time = event.time;
            e.x = chevronRect.x;
            e.y = chevronRect.y;
            e.width = chevronRect.width;
            e.height = chevronRect.height;
            e.doit = true;
            for (int i = 0; i < this.folderListeners.length; i++) {
                this.folderListeners[i].showList(e);
            }
            if (e.doit && !isDisposed()) {
                showList(chevronRect);
            }
        }
    }

    @objid ("fdf9ed58-617d-463b-b316-888ea2c1cca1")
    void onTraverse(Event event) {
        if (this.ignoreTraverse)
            return;
        runUpdate();
        switch (event.detail) {
        case SWT.TRAVERSE_ESCAPE:
        case SWT.TRAVERSE_RETURN:
        case SWT.TRAVERSE_TAB_NEXT:
        case SWT.TRAVERSE_TAB_PREVIOUS:
            Control focusControl = getDisplay().getFocusControl();
            if (focusControl == this)
                event.doit = true;
            break;
        case SWT.TRAVERSE_MNEMONIC:
            event.doit = onMnemonic(event, false);
            break;
        case SWT.TRAVERSE_PAGE_NEXT:
        case SWT.TRAVERSE_PAGE_PREVIOUS:
            event.doit = this.items.length > 0;
            break;
        }
        this.ignoreTraverse = true;
        notifyListeners(SWT.Traverse, event);
        this.ignoreTraverse = false;
        event.type = SWT.None;
        if (isDisposed())
            return;
        if (!event.doit)
            return;
        switch (event.detail) {
        case SWT.TRAVERSE_MNEMONIC:
            onMnemonic(event, true);
            event.detail = SWT.TRAVERSE_NONE;
            break;
        case SWT.TRAVERSE_PAGE_NEXT:
        case SWT.TRAVERSE_PAGE_PREVIOUS:
            onPageTraversal(event);
            event.detail = SWT.TRAVERSE_NONE;
            break;
        }
    }

    @objid ("36719e10-9aba-49b6-a468-5e3fcd715702")
    void redrawTabs() {
        Point size = getSize();
        Rectangle trim = this.renderer.computeTrim(VTabFolderRenderer.PART_BODY, SWT.NONE, 0, 0, 0, 0);
        if (this.isOnRight()) {
            int h = trim.height + trim.y - this.marginHeight;
            redraw(0, size.y - h - 1, size.x, h + 1, false);
        } else {
            redraw(0, 0, size.x, -trim.y - this.marginHeight + 1, false);
        }
    }

    /**
     * Removes the listener.
     * @see #addVTabFolder2Listener(VTabFolder2Listener)
     * 
     * @since 3.0
     * @param listener the listener which should no longer be notified
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("c98153d8-3aec-4c0e-8984-84fa8bb5228e")
    public void removeVTabFolder2Listener(VTabFolder2Listener listener) {
        checkWidget();
        if (listener == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        if (this.folderListeners.length == 0)
            return;
        int index = -1;
        for (int i = 0; i < this.folderListeners.length; i++) {
            if (listener == this.folderListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1)
            return;
        if (this.folderListeners.length == 1) {
            this.folderListeners = new VTabFolder2Listener[0];
            return;
        }
        VTabFolder2Listener[] newTabListeners = new VTabFolder2Listener[this.folderListeners.length - 1];
        System.arraycopy(this.folderListeners, 0, newTabListeners, 0, index);
        System.arraycopy(this.folderListeners, index + 1, newTabListeners, index,
                this.folderListeners.length - index - 1);
        this.folderListeners = newTabListeners;
    }

    /**
     * Removes the listener from the collection of listeners who will be
     * notified when the user changes the receiver's selection.
     * @see SelectionListener
     * @see #addSelectionListener
     * @param listener the listener which should no longer be notified
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("096edb46-b45d-48e0-9cbf-684153d2bea0")
    public void removeSelectionListener(SelectionListener listener) {
        checkWidget();
        if (listener == null) {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        }
        removeListener(SWT.Selection, listener);
        removeListener(SWT.DefaultSelection, listener);
    }

    @objid ("fdb3b019-743a-4cf5-b378-e6545ea9ce1e")
    @Override
    public void reskin(int flags) {
        super.reskin(flags);
        for (int i = 0; i < this.items.length; i++) {
            this.items[i].reskin(flags);
        }
    }

    @objid ("57dbb6cc-2054-4f37-ac02-b589c65a5d03")
    @Override
    public void setBackground(Color color) {
        super.setBackground(color);
        
        updateBkImages();
        redraw();
    }

    @objid ("4f37db9b-e35f-4e90-9e92-08005dced355")
    @Override
    public void setBackgroundImage(Image image) {
        super.setBackgroundImage(image);
        redraw();
    }

    /**
     * Toggle the visibility of the border
     * @param show true if the border should be displayed
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("d5572f05-8bd4-497e-8062-4fff52ae0686")
    public void setBorderVisible(boolean show) {
        checkWidget();
        if (this.borderVisible == show)
            return;
        this.borderVisible = show;
        updateFolder(REDRAW);
    }

    @objid ("17d190b1-44d7-4d36-9bcc-7a103e5b2145")
    void setButtonBounds(GC gc) {
        Point size = getSize();
        // max button
        Display display = getDisplay();
        
        if (this.showChevron) {
            int itemCount = this.items.length;
            int count;
            if (this.isSingle()) {
                count = this.getSelectedIndex() == -1 ? itemCount : itemCount - 1;
            } else {
                int showCount = 0;
                while (showCount < this.priority.length && this.items[this.priority[showCount]].showing) {
                    showCount++;
                }
                count = itemCount - showCount;
            }
            if (count != this.chevronCount) {
                this.chevronCount = count;
                if (this.chevronImage != null)
                    this.chevronImage.dispose();
                this.chevronImage = createButtonImage(display, VTabFolderRenderer.PART_CHEVRON_BUTTON);
                this.chevronItem.setImage(this.chevronImage);
            }
        }
        
        boolean[][] overflow = new boolean[1][0];
        Rectangle[] rects = computeControlBounds(size, overflow);
        if (this.getFixedTabWidth() != SWT.DEFAULT) {
            int width = this.getFixedTabWidth();
            if (!this.hovering) {
                this.hoverTb = false;
                Rectangle tabBounds = this.getBounds();
                for (int i = 0; i < rects.length; i++) {
                    if (!(overflow[0][i])) {
                        if (rects[i].width > width) {
                            this.hoverTb = true;
                            break;
                        }
                    }
                }
                if (this.hoverTb) {
                    for (int i = 0; i < rects.length; i++) {
                        if (!(overflow[0][i])) {
                            if (rects[i].width > width) {
                                rects[i].x = tabBounds.width + 20;
                            }
                        }
                    }
                }
            }
        }
        int headerHeight = 0;
        for (int i = 0; i < rects.length; i++) {
            if (!overflow[0][i])
                headerHeight = Math.max(rects[i].height, headerHeight);
        }
        boolean changed = false;
        this.ignoreResize = true;
        for (int i = 0; i < this.controls.length; i++) {
            if (!this.controls[i].isDisposed()) {
                if (overflow[0][i]) {
                    this.controls[i].setBounds(rects[i]);
                } else {
                    this.controls[i].moveAbove(null);
                    this.controls[i].setBounds(rects[i].x, rects[i].y, rects[i].width, headerHeight);
                }
            }
            if (!changed && !rects[i].equals(this.controlRects[i]))
                changed = true;
        }
        this.ignoreResize = false;
        this.controlRects = rects;
        if (changed || this.hovering)
            updateBkImages();
    }

    @objid ("c3a877e2-fdb8-410f-9592-ea6d5182ee0d")
    @Override
    public boolean setFocus() {
        checkWidget();
        
        /*
         * Feature in SWT. When a new tab item is selected and the previous tab
         * item had focus, removing focus from the previous tab item causes
         * fixFocus() to give focus to the first child, which is usually one of
         * the toolbars. This is unexpected. The fix is to try to set focus on
         * the first tab item if fixFocus() is called.
         */
        Control focusControl = getDisplay().getFocusControl();
        boolean fixFocus = isAncestor(focusControl);
        if (fixFocus) {
            VTabItem item = getSelection();
            if (item != null) {
                if (item.setFocus())
                    return true;
            }
        }
        return super.setFocus();
    }

/* Copy of isFocusAncestor from Control. */
    @objid ("21e3cb93-c337-4b45-bb98-b449f1a15b28")
    boolean isAncestor(Control control) {
        while (control != null && control != this && !(control instanceof Shell)) {
            control = control.getParent();
        }
        return control == this;
    }

    @objid ("b375ebe3-8805-42e5-b6e4-1581a70ddaa2")
    @Override
    public void setFont(Font font) {
        checkWidget();
        if (font != null && font.equals(getFont()))
            return;
        super.setFont(font);
        this.oldFont = getFont();
        updateFolder(REDRAW);
    }

    @objid ("198d8742-9e5e-4ffa-b475-57bcdd6de37e")
    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        redraw();
    }

    /**
     * Display an insert marker before or after the specified tab item.
     * 
     * A value of null will clear the mark.
     * @param item the item with which the mark is associated or null
     * @param after true if the mark should be displayed after the specified item
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("274be944-222f-40f9-b334-b9876aa2cb86")
    public void setInsertMark(VTabItem item, boolean after) {
        checkWidget();
    }

    /**
     * Display an insert marker before or after the specified tab item.
     * 
     * A value of -1 will clear the mark.
     * @param index the index of the item with which the mark is associated or -1
     * @param after true if the mark should be displayed after the specified item
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_INVALID_ARGUMENT when the index is invalid</li>
     * </ul>
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("386a1586-35ae-4b5c-a3f5-a2df9d9dd8f8")
    public void setInsertMark(int index, boolean after) {
        checkWidget();
        if (index < -1 || index >= getItemCount()) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
    }

    @objid ("08a9c9bf-bbd4-4fb1-98b3-5fec5d338e2d")
    boolean setItemLocation(GC gc) {
        boolean changed = false;
        if (this.items.length == 0)
            return false;
        Rectangle trim = this.renderer.computeTrim(VTabFolderRenderer.PART_BORDER, SWT.NONE, 0, 0, 0, 0);
        int borderRight = trim.width + trim.x;
        int borderLeft = -trim.x;
        Point size = getSize();
        int x = this.isOnRight() ? Math.max(borderRight, size.x - borderRight - this.tabWidth) : borderLeft;
        
        
        int topItemEdge = getTopItemEdge(gc, VTabFolderRenderer.PART_BORDER);
        if (this.isSingle()) {
            int defaultX = getDisplay().getBounds().width + 10; // off screen
            for (int i = 0; i < this.items.length; i++) {
                VTabItem item = this.items[i];
                if (i == this.getSelectedIndex()) {
                    this.setFirstIndex(this.getSelectedIndex());
                    int oldX = item.x, oldY = item.y;
                    item.x = x;
                    item.y = topItemEdge;
                    item.showing = true;
        
                    if (item.x != oldX || item.y != oldY)
                        changed = true;
                } else {
                    item.x = defaultX;
                    item.showing = false;
                }
            }
        } else {
            int bottomItemEdge = getBottomItemEdge(gc);
            int maxHeight = bottomItemEdge - topItemEdge;
            int height = 0;
            for (int i = 0; i < this.priority.length; i++) {
                VTabItem item = this.items[this.priority[i]];
                height += item.height;
                item.showing = i == 0 ? true : item.height > 0 && height <= maxHeight;
            }
            int y = getTopItemEdge(gc, VTabFolderRenderer.PART_HEADER);
            int defaultY = getDisplay().getBounds().height + 10; // off screen
            this.setFirstIndex(this.items.length - 1);
            for (int i = 0; i < this.items.length; i++) {
                VTabItem item = this.items[i];
                if (!item.showing) {
                    if (item.y != defaultY)
                        changed = true;
                    item.y = defaultY;
                } else {
                    this.setFirstIndex(Math.min(this.getFirstIndex(), i));
                    if (item.y != y || item.x != x)
                        changed = true;
                    item.x = x;
                    item.y = y;
                    int state = SWT.NONE;
                    if (i == this.getSelectedIndex())
                        state |= SWT.SELECTED;
                    Rectangle edgeTrim = this.renderer.computeTrim(i, state, 0, 0, 0, 0);
        
                    y = y + item.height;
        
        
                }
            }
        }
        return changed;
    }

/* public */
    /**
     * Reorder the items of the receiver.
     * @param indices an array containing the new indices for all items
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the indices array is
     * null</li>
     * <li>ERROR_INVALID_ARGUMENT - if the indices array
     * is not the same length as the number of items, if
     * there are duplicate indices or an index is out of
     * range.</li>
     * </ul>
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has
     * been disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called
     * from the thread that created the receiver</li>
     * </ul>
     */
    @objid ("88178ff4-a4f8-4c7b-830b-6dbf1c61e01d")
    void setItemOrder(int[] indices) {
        checkWidget();
        if (indices == null) {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
            return;
        } else if (indices.length != this.items.length) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
            return;
        }
        int newSelectedIndex = -1;
        boolean[] seen = new boolean[this.items.length];
        VTabItem[] temp = new VTabItem[this.items.length];
        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            if (!(0 <= index && index < this.items.length))
                SWT.error(SWT.ERROR_INVALID_ARGUMENT);
            if (seen[index])
                SWT.error(SWT.ERROR_INVALID_ARGUMENT);
            seen[index] = true;
            if (index == this.getSelectedIndex())
                newSelectedIndex = i;
            temp[i] = this.items[index];
        }
        this.items = temp;
        this.setSelectedIndex(newSelectedIndex);
        updateFolder(REDRAW);
    }

    @objid ("1c02bfe2-1eea-4d08-8cda-c0ceaa126900")
    boolean setItemSize(GC gc) {
        boolean changed = false;
        if (isDisposed())
            return changed;
        Point size = getSize();
        if (size.x <= 0 || size.y <= 0)
            return changed;
        ToolBar chevron = getChevron();
        if (chevron != null) {
            chevron.setVisible(false);
        }
        this.showChevron = false;
        if (this.isSingle()) {
            this.showChevron = this.chevronVisible && this.items.length > 1;
            if (this.showChevron && chevron != null) {
                chevron.setVisible(true);
            }
            if (this.getSelectedIndex() != -1) {
                VTabItem tab = this.items[this.getSelectedIndex()];
                int height = this.renderer.computeSize(this.getSelectedIndex(), SWT.SELECTED, gc, SWT.DEFAULT,
                        SWT.DEFAULT).y;
                height = Math.min(height, getBottomItemEdge(gc) - getTopItemEdge(gc, VTabFolderRenderer.PART_BORDER));
                if (tab.width != this.tabWidth || tab.height != height) {
                    changed = true;
                    tab.shortenedText = null;
                    tab.shortenedTextWidth = 0;
                    tab.height = height;
                    tab.width = this.tabWidth;
                    tab.closeRect.width = tab.closeRect.height = 0;
        
                }
            }
            return changed;
        }
        
        if (this.items.length == 0)
            return changed;
        int[] widths;
        int tabAreaHeight = Math.max(0, getBottomItemEdge(gc) - getTopItemEdge(gc, VTabFolderRenderer.PART_BORDER));
        // First, try the minimum tab size at full compression.
        int minHeight = 0;
        int[] minHeights = new int[this.items.length];
        for (int i = 0; i < this.priority.length; i++) {
            int index = this.priority[i];
            int state = VTabFolderRenderer.MINIMUM_SIZE;
            if (index == this.getSelectedIndex())
                state |= SWT.SELECTED;
            minHeights[index] = this.renderer.computeSize(index, state, gc, SWT.DEFAULT, SWT.DEFAULT).y;
            minHeight += minHeights[index];
            if (minHeight > tabAreaHeight)
                break;
        }
        if (minHeight > tabAreaHeight) {
            // full compression required and a chevron
            this.showChevron = this.chevronVisible && this.items.length > 1;
            if (this.showChevron && chevron != null) {
                tabAreaHeight -= chevron.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
                chevron.setVisible(true);
            }
            widths = minHeights;
            int index = this.getSelectedIndex() != -1 ? this.getSelectedIndex() : 0;
            if (tabAreaHeight < widths[index]) {
                widths[index] = Math.max(0, tabAreaHeight);
            }
        } else {
            int maxHeight = 0;
            int[] maxHeights = new int[this.items.length];
            for (int i = 0; i < this.items.length; i++) {
                int state = 0;
                if (i == this.getSelectedIndex())
                    state |= SWT.SELECTED;
                maxHeights[i] = this.renderer.computeSize(i, state, gc, SWT.DEFAULT, SWT.DEFAULT).y;
                maxHeight += maxHeights[i];
            }
            if (maxHeight <= tabAreaHeight) {
                // no compression required
                widths = maxHeights;
            } else {
                // determine compression for each item
                int extra = (tabAreaHeight - minHeight) / this.items.length;
                while (true) {
                    int large = 0, totalWidth = 0;
                    for (int i = 0; i < this.items.length; i++) {
                        if (maxHeights[i] > minHeights[i] + extra) {
                            totalWidth += minHeights[i] + extra;
                            large++;
                        } else {
                            totalWidth += maxHeights[i];
                        }
                    }
                    if (totalWidth >= tabAreaHeight) {
                        extra--;
                        break;
                    }
                    if (large == 0 || tabAreaHeight - totalWidth < large)
                        break;
                    extra++;
                }
                widths = new int[this.items.length];
                for (int i = 0; i < this.items.length; i++) {
                    widths[i] = Math.min(maxHeights[i], minHeights[i] + extra);
                }
            }
        }
        
        for (int i = 0; i < this.items.length; i++) {
            VTabItem tab = this.items[i];
            int width = widths[i];
            if (tab.width != this.tabWidth || tab.height != width) {
                changed = true;
                tab.shortenedText = null;
                tab.shortenedTextWidth = 0;
                tab.width = this.tabWidth;
                tab.height = width;
                tab.closeRect.width = tab.closeRect.height = 0;
        
            }
        }
        return changed;
    }

    /**
     * Sets the layout which is associated with the receiver to be the argument
     * which may be null.
     * <p>
     * Note: No Layout can be set on this Control because it already manages the
     * size and position of its children.
     * </p>
     * @param layout the receiver's new layout or null
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("643d9656-a0f5-462a-a92a-b4885ea942c1")
    @Override
    public void setLayout(Layout layout) {
        checkWidget();
        return;
    }

    /**
     * Sets the minimum number of characters that will be displayed in a fully
     * compressed tab.
     * @param count the minimum number of characters that will be displayed in a
     * fully compressed tab
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * <li>ERROR_INVALID_RANGE - if the count is less than zero
     * </li>
     * </ul>
     * 
     * @since 3.0
     */
    @objid ("a11fd44a-618c-4590-91a9-958cf20a8a40")
    public void setMinimumCharacters(int count) {
        checkWidget();
        if (count < 0)
            SWT.error(SWT.ERROR_INVALID_RANGE);
        if (this.getMinChars() == count)
            return;
        this.setMinChars(count);
        updateFolder(REDRAW_TABS);
    }

    /**
     * When there is not enough horizontal space to show all the tabs, by
     * default, tabs are shown sequentially from left to right in order of their
     * index. When the MRU visibility is turned on, the tabs that are visible
     * will be the tabs most recently selected. Tabs will still maintain their
     * left to right order based on index but only the most recently selected
     * tabs are visible.
     * <p>
     * For example, consider a VTabFolder that contains "Tab 1", "Tab 2",
     * "Tab 3" and "Tab 4" (in order by index). The user selects "Tab 1" and
     * then "Tab 3". If the VTabFolder is now compressed so that only two tabs
     * are visible, by default, "Tab 2" and "Tab 3" will be shown ("Tab 3" since
     * it is currently selected and "Tab 2" because it is the previous item in
     * index order). If MRU visibility is enabled, the two visible tabs will be
     * "Tab 1" and "Tab 3" (in that order from left to right).
     * </p>
     * @param show the new visibility state
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     * 
     * @since 3.1
     */
    @objid ("be055935-17dd-4eb6-914e-73dbca9f9f4c")
    public void setMRUVisible(boolean show) {
        checkWidget();
        if (this.mru == show)
            return;
        this.mru = show;
        if (!this.mru) {
            if (this.getFirstIndex() == -1)
                return;
            int idx = this.getFirstIndex();
            int next = 0;
            for (int i = this.getFirstIndex(); i < this.items.length; i++) {
                this.priority[next++] = i;
            }
            for (int i = 0; i < idx; i++) {
                this.priority[next++] = i;
            }
            updateFolder(REDRAW_TABS);
        }
    }

    /**
     * Sets the renderer which is associated with the receiver to be the
     * argument which may be null. In the case of null, the default renderer is
     * used.
     * @see VTabFolderRenderer
     * 
     * @since 3.6
     * @param renderer a new renderer
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("7536fdbd-2eaf-4b47-812c-37b9467d4f7b")
    public void setRenderer(VTabFolderRenderer renderer) {
        checkWidget();
        if (this.renderer == renderer || (this.useDefaultRenderer && renderer == null))
            return;
        if (this.renderer != null)
            this.renderer.dispose();
        this.useDefaultRenderer = renderer == null;
        if (this.useDefaultRenderer)
            renderer = new VTabFolderRenderer(this);
        this.renderer = renderer;
        updateFolder(REDRAW);
    }

    /**
     * Set the selection to the tab at the specified item.
     * @param item the tab item to be selected
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the item is null</li>
     * </ul>
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("8d592d58-ac76-4095-9786-4be7e9e181a8")
    public void setSelection(VTabItem item) {
        checkWidget();
        if (item == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        int index = indexOf(item);
        setSelection(index);
    }

    /**
     * Set the selection to the tab at the specified index.
     * @param index the index of the tab item to be selected
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("45e7a703-38d7-4557-a6d8-8e751dcaffcb")
    public void setSelection(int index) {
        checkWidget();
        if (index < 0 || index >= this.items.length)
            return;
        VTabItem selection = this.items[index];
        if (this.getSelectedIndex() == index) {
            showItem(selection);
            return;
        }
        
        int oldIndex = this.getSelectedIndex();
        this.setSelectedIndex(index);
        if (oldIndex != -1) {
            this.items[oldIndex].closeImageState = SWT.BACKGROUND;
            this.items[oldIndex].state &= ~SWT.SELECTED;
        }
        selection.closeImageState = SWT.NONE;
        selection.showing = false;
        selection.state |= SWT.SELECTED;
        
        Control newControl = selection.control;
        Control oldControl = null;
        if (oldIndex != -1) {
            oldControl = this.items[oldIndex].control;
        }
        
        if (newControl != oldControl) {
            if (newControl != null && !newControl.isDisposed()) {
                newControl.setBounds(getClientArea());
                newControl.setVisible(true);
            }
            if (oldControl != null && !oldControl.isDisposed()) {
                oldControl.setVisible(false);
            }
        }
        showItem(selection);
        redraw();
    }

    @objid ("bfa559f0-be96-438f-ad11-f8a439e0ebd2")
    void setSelection(int index, boolean notify) {
        int oldSelectedIndex = this.getSelectedIndex();
        setSelection(index);
        if (notify && this.getSelectedIndex() != oldSelectedIndex && this.getSelectedIndex() != -1) {
            Event event = new Event();
            event.item = getItem(this.getSelectedIndex());
            notifyListeners(SWT.Selection, event);
        }
    }

    /**
     * Sets the receiver's selection background color to the color specified by
     * the argument, or to the default system color for the control if the
     * argument is null.
     * @param color the new color (or null)
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
    @objid ("42553089-01bc-44c2-94a1-9c942254875b")
    public void setSelectionBackground(Color color) {
        if (this.inDispose)
            return;
        checkWidget();
        
        if (this.selectionBackground == color)
            return;
        if (color == null)
            color = getDisplay().getSystemColor(SELECTION_BACKGROUND);
        this.selectionBackground = color;
        
        if (this.getSelectedIndex() > -1)
            redraw();
    }

    /**
     * Set the image to be drawn in the background of the selected tab. Image is
     * stretched or compressed to cover entire selection tab area.
     * @param image the image to be drawn in the background
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("ac2b2d8e-d931-4fef-9e3e-cd38f80b4c92")
    public void setSelectionBackground(Image image) {
        checkWidget();
        
        if (image == this.selectionBgImage)
            return;
        this.selectionBgImage = image;
        
        // strategy
        if (this.getSelectedIndex() > -1)
            redraw();
    }

    /**
     * Set the foreground color of the selected tab.
     * @param color the color of the text displayed in the selected tab
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("cd5b9731-ed34-44d8-a064-3694a11601c5")
    public void setSelectionForeground(Color color) {
        checkWidget();
        if (this.selectionForeground == color)
            return;
        if (color == null)
            color = getDisplay().getSystemColor(SELECTION_FOREGROUND);
        this.selectionForeground = color;
        if (this.getSelectedIndex() > -1)
            redraw();
    }

    /**
     * Sets the shape that the VTabFolder will use to render itself.
     * @param simple <code>true</code> if the VTabFolder should render itself in a
     * simple, traditional style
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
    @objid ("74da1209-bc1d-422e-9987-7e08337ac521")
    public void setSimple(boolean simple) {
        checkWidget();
        if (this.simple != simple) {
            this.simple = simple;
            updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
        }
    }

    /**
     * Sets the number of tabs that the VTabFolder should display
     * @param single <code>true</code> if only the selected tab should be displayed
     * otherwise, multiple tabs will be shown.
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
    @objid ("d9bd84e8-00d7-40eb-bb85-bf0afd22a55a")
    public void setSingle(boolean single) {
        checkWidget();
        if (this.single != single) {
            this.single = single;
            if (!single) {
                for (int i = 0; i < this.items.length; i++) {
                    if (i != this.getSelectedIndex() && this.items[i].closeImageState == SWT.NONE) {
                        this.items[i].closeImageState = SWT.BACKGROUND;
                    }
                }
            }
            updateFolder(REDRAW);
        }
    }

    @objid ("a92d7b07-ee22-4706-98ce-0f5ede3b77a0")
    int getControlX(Point size, Rectangle[] rects, int borderLeft, int borderRight, int i) {
        int center = this.getFixedTabWidth() != SWT.DEFAULT ? 0 : (this.tabWidth - rects[i].width) / 2;
        return this.isOnRight() ? size.x - borderRight - this.tabWidth + center : 1 + borderLeft + center;
    }

    /**
     * Specify a fixed height for the tab items. If no height is specified, the
     * default height is the height of the text or the image, whichever is
     * greater. Specifying a height of -1 will revert to the default height.
     * @param height
     * the pixel value of the height or -1
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * <li>ERROR_INVALID_ARGUMENT - if called with a height of
     * less than 0</li>
     * </ul>
     */
    @objid ("d5805ca6-4396-468b-88ad-88cdc5b71648")
    public void setTabWidth(int width) {
        checkWidget();
        if (width < -1) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
        this.setFixedTabWidth(width);
        updateFolder(UPDATE_TAB_HEIGHT);
    }

    /**
     * Specify whether the tabs should appear along the top of the folder or
     * along the bottom of the folder.
     * @param position <code>SWT.TOP</code> for tabs along the top or
     * <code>SWT.BOTTOM</code> for tabs along the bottom
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * <li>ERROR_INVALID_ARGUMENT - if the position value is not
     * either SWT.TOP or SWT.BOTTOM</li>
     * </ul>
     * 
     * @since 3.0
     */
    @objid ("edbb35ea-85ad-459d-b748-5305a7870415")
    public void setTabPosition(int position) {
        checkWidget();
        if (position != SWT.TOP && position != SWT.BOTTOM) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
        if (this.isOnRight() != (position == SWT.BOTTOM)) {
            this.setOnRight(position == SWT.BOTTOM);
            updateFolder(REDRAW);
        }
    }

    /**
     * Set the control that appears in the top right corner of the tab folder.
     * Typically this is a close button or a composite with a Menu and close
     * button. The topRight control is optional. Setting the top right control
     * to null will remove it from the tab folder.
     * @param control the control to be displayed in the top right corner or null
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * <li>ERROR_INVALID_ARGUMENT - if the control is disposed,
     * or not a child of this VTabFolder</li>
     * </ul>
     * 
     * @since 2.1
     */
    @objid ("e5ee04b5-ca67-4d4a-a6da-cc6b2255ee7f")
    public void setTopRight(Control control) {
        setTopRight(control, SWT.RIGHT);
    }

    /**
     * Set the control that appears in the top right corner of the tab folder.
     * Typically this is a close button or a composite with a Menu and close
     * button. The topRight control is optional. Setting the top right control
     * to null will remove it from the tab folder.
     * <p>
     * The alignment parameter sets the layout of the control in the tab area.
     * <code>SWT.RIGHT</code> will cause the control to be positioned on the far
     * right of the folder and it will have its default size.
     * <code>SWT.FILL</code> will size the control to fill all the available
     * space to the right of the last tab. If there is no available space, the
     * control will not be visible. <code>SWT.RIGHT | SWT.WRAP</code> will allow
     * the control to wrap below the tabs if there is not enough available space
     * to the right of the last tab.
     * </p>
     * @param control the control to be displayed in the top right corner or null
     * @param alignment <code>SWT.RIGHT</code> or <code>SWT.FILL</code> or
     * <code>SWT.RIGHT | SWT.WRAP</code>
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * <li>ERROR_INVALID_ARGUMENT - if the control is disposed,
     * or not a child of this VTabFolder</li>
     * </ul>
     * 
     * @since 3.0
     */
    @objid ("d4f997a4-d732-4c0a-9a13-c5f7897b6970")
    public void setTopRight(Control control, int alignment) {
        checkWidget();
        if (alignment != SWT.RIGHT && alignment != SWT.FILL && alignment != (SWT.RIGHT | SWT.WRAP)) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
        if (control != null && (control.isDisposed() || control.getParent() != this)) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
        if (this.topRight == control && this.topRightAlignment == alignment)
            return;
        if (this.topRight != null && !this.topRight.isDisposed())
            removeTabControl(this.topRight, false);
        this.topRight = control;
        this.topRightAlignment = alignment;
        alignment &= ~SWT.RIGHT;
        if (control != null)
            addTabControl(control, SWT.TRAIL | alignment, -1, false);
        updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
    }

    /**
     * Specify whether the image appears on unselected tabs.
     * @param visible <code>true</code> makes the image appear
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
    @objid ("3ec4a988-a7db-4c0a-a1bd-9f04da03cd68")
    public void setUnselectedImageVisible(boolean visible) {
        checkWidget();
        if (this.showUnselectedImage == visible)
            return;
        // display image on unselected items
        this.showUnselectedImage = visible;
        updateFolder(REDRAW);
    }

    /**
     * Shows the item. If the item is already showing in the receiver, this
     * method simply returns. Otherwise, the items are scrolled until the item
     * is visible.
     * @see VTabFolder#showSelection()
     * 
     * @since 2.0
     * @param item the item to be shown
     * 
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_NULL_ARGUMENT - if the item is null</li>
     * <li>ERROR_INVALID_ARGUMENT - if the item has been disposed
     * </li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     */
    @objid ("10a5cac8-f5dd-40ff-bd58-c7d484dc3980")
    public void showItem(VTabItem item) {
        checkWidget();
        if (item == null) {
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
            return;
        } else if (item.isDisposed()) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
            return;
        }
        int index = indexOf(item);
        if (index == -1)
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        int idx = -1;
        for (int i = 0; i < this.priority.length; i++) {
            if (this.priority[i] == index) {
                idx = i;
                break;
            }
        }
        if (this.mru) {
            // move to front of mru order
            int[] newPriority = new int[this.priority.length];
            System.arraycopy(this.priority, 0, newPriority, 1, idx);
            System.arraycopy(this.priority, idx + 1, newPriority, idx + 1, this.priority.length - idx - 1);
            newPriority[0] = index;
            this.priority = newPriority;
        }
        if (item.showing)
            return;
        updateFolder(REDRAW_TABS);
    }

    @objid ("1df6f717-383b-4167-bf5f-3aeee3759aa5")
    void showList(Rectangle rect) {
        if (this.items.length == 0 || !this.showChevron)
            return;
        if (this.showMenu == null || this.showMenu.isDisposed()) {
            this.showMenu = new Menu(getShell(), getStyle() & (SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT));
        } else {
            MenuItem[] items = this.showMenu.getItems();
            for (int i = 0; i < items.length; i++) {
                items[i].dispose();
            }
        }
        final String id = "VTabFolder_showList_Index"; //$NON-NLS-1$
        for (int i = 0; i < this.items.length; i++) {
            VTabItem tab = this.items[i];
            if (tab.showing)
                continue;
            MenuItem item = new MenuItem(this.showMenu, SWT.NONE);
            item.setText(tab.getText());
            item.setImage(tab.getImage());
            item.setData(id, tab);
            item.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    MenuItem menuItem = (MenuItem) e.widget;
                    int index = indexOf((VTabItem) menuItem.getData(id));
                    VTabFolder.this.setSelection(index, true);
                }
            });
        }
        int x = rect.x;
        int y = rect.y + rect.height;
        Point location = getDisplay().map(this, null, x, y);
        this.showMenu.setLocation(location.x, location.y);
        this.showMenu.setVisible(true);
    }

    /**
     * Shows the selection. If the selection is already showing in the receiver,
     * this method simply returns. Otherwise, the items are scrolled until the
     * selection is visible.
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has been
     * disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
     * thread that created the receiver</li>
     * </ul>
     * @see VTabFolder#showItem(VTabItem)
     * 
     * @since 2.0
     */
    @objid ("17614f58-49c9-4ada-a919-44790a15b6ea")
    public void showSelection() {
        checkWidget();
        if (this.getSelectedIndex() != -1) {
            showItem(getSelection());
        }
    }

    @objid ("99baef77-eac7-4737-9ee0-c8fde73c0c41")
    void _setToolTipText(int x, int y) {
        String oldTip = getToolTipText();
        String newTip = _getToolTip(x, y);
        if (newTip == null || !newTip.equals(oldTip)) {
            setToolTipText(newTip);
        }
    }

    @objid ("e0c6f6e3-2c6e-4732-8464-84171c4ba52f")
    boolean updateItems() {
        return updateItems(this.getSelectedIndex());
    }

    @objid ("8cff62d7-56bb-4d55-af72-35ab0e866a2b")
    boolean updateItems(int showIndex) {
        GC gc = new GC(this);
        if (!this.single && !this.mru && showIndex != -1) {
            // make sure selected item will be showing
            int firstIndex = showIndex;
            if (this.priority[0] < showIndex) {
                int maxHeight = getBottomItemEdge(gc) - getTopItemEdge(gc, VTabFolderRenderer.PART_BORDER);
                int height = 0;
                int[] heights = new int[this.items.length];
                for (int i = this.priority[0]; i <= showIndex; i++) {
                    int state = VTabFolderRenderer.MINIMUM_SIZE;
                    if (i == this.selectedIndex)
                        state |= SWT.SELECTED;
                    heights[i] = this.renderer.computeSize(i, state, gc, SWT.DEFAULT, SWT.DEFAULT).y;
                    height += heights[i];
                    if (height > maxHeight)
                        break;
                }
                if (height > maxHeight) {
                    height = 0;
                    for (int i = showIndex; i >= 0; i--) {
                        int state = VTabFolderRenderer.MINIMUM_SIZE;
                        if (i == this.selectedIndex)
                            state |= SWT.SELECTED;
                        if (heights[i] == 0)
                            heights[i] = this.renderer.computeSize(i, state, gc, SWT.DEFAULT, SWT.DEFAULT).y;
                        height += heights[i];
                        if (height > maxHeight)
                            break;
                        firstIndex = i;
                    }
                } else {
                    firstIndex = this.priority[0];
                    for (int i = showIndex + 1; i < this.items.length; i++) {
                        int state = VTabFolderRenderer.MINIMUM_SIZE;
                        if (i == this.selectedIndex)
                            state |= SWT.SELECTED;
                        heights[i] = this.renderer.computeSize(i, state, gc, SWT.DEFAULT, SWT.DEFAULT).y;
                        height += heights[i];
                        if (height >= maxHeight)
                            break;
                    }
                    if (height < maxHeight) {
                        for (int i = this.priority[0] - 1; i >= 0; i--) {
                            int state = VTabFolderRenderer.MINIMUM_SIZE;
                            if (i == this.selectedIndex)
                                state |= SWT.SELECTED;
                            if (heights[i] == 0)
                                heights[i] = this.renderer.computeSize(i, state, gc, SWT.DEFAULT, SWT.DEFAULT).x;
                            height += heights[i];
                            if (height > maxHeight)
                                break;
                            firstIndex = i;
                        }
                    }
                }
        
            }
            if (firstIndex != this.priority[0]) {
                int index = 0;
                // enumerate tabs from first visible to the last existing one
                // (sorted ascending)
                for (int i = firstIndex; i < this.items.length; i++) {
                    this.priority[index++] = i;
                }
                // enumerate hidden tabs on the left hand from first visible one
                // in the inverse order (sorted descending) so that the
                // originally
                // first opened tab is always at the end of the list
                for (int i = firstIndex - 1; i >= 0; i--) {
                    this.priority[index++] = i;
                }
            }
        }
        
        boolean oldShowChevron = this.showChevron;
        boolean changed = setItemSize(gc);
        changed |= setItemLocation(gc);
        setButtonBounds(gc);
        changed |= this.showChevron != oldShowChevron;
        if (changed && getToolTipText() != null) {
            Point pt = getDisplay().getCursorLocation();
            pt = toControl(pt);
            _setToolTipText(pt.x, pt.y);
        }
        gc.dispose();
        return changed;
    }

    @objid ("77fcf856-ffff-4c04-90d5-7f1e56544862")
    boolean updateTabWidth(boolean force) {
        int oldwidth = this.tabWidth;
        GC gc = new GC(this);
        this.tabWidth = this.renderer.computeSize(VTabFolderRenderer.PART_HEADER, SWT.NONE, gc, SWT.DEFAULT,
                SWT.DEFAULT).x;
        gc.dispose();
        if (this.getFixedTabWidth() == SWT.DEFAULT && this.controls != null && this.controls.length > 0) {
            for (int i = 0; i < this.controls.length; i++) {
                if ((this.controlAlignments[i] & SWT.WRAP) == 0 && !this.controls[i].isDisposed()
                        && (this.controls[i].getVisible() || this.controls[i] == this.chevronTb)) {
                    int topWidth = this.controls[i].computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
                    topWidth += this.renderer.computeTrim(VTabFolderRenderer.PART_HEADER, SWT.NONE, 0, 0, 0, 0).width
                            + 1;
                    this.tabWidth = Math.max(topWidth, this.tabWidth);
                }
            }
        }
        if (!force && this.tabWidth == oldwidth)
            return false;
        this.oldSize = null;
        return true;
    }

    @objid ("51571871-a36a-4080-a848-290a9a7f449d")
    void updateFolder(int flags) {
        this.updateFlags |= flags;
        if (this.updateRun != null)
            return;
        this.updateRun = new Runnable() {
            @Override
            public void run() {
                VTabFolder.this.updateRun = null;
                if (isDisposed())
                    return;
                runUpdate();
            }
        };
        getDisplay().asyncExec(this.updateRun);
    }

    @objid ("8ede5e75-6557-427b-b7a9-8531b4f6954a")
    void runUpdate() {
        if (this.updateFlags == 0)
            return;
        int flags = this.updateFlags;
        this.updateFlags = 0;
        Rectangle rectBefore = getClientArea();
        boolean updated = updateTabWidth(false);
        updated |= updateItems(this.getSelectedIndex());
        if (IS_GTK && updated && getParent() != null) {
            getParent().layout(true, false);
        }
        if ((flags & REDRAW) != 0) {
            redraw();
        } else if ((flags & REDRAW_TABS) != 0) {
            redrawTabs();
        }
        Rectangle rectAfter = getClientArea();
        if (!rectBefore.equals(rectAfter)) {
            notifyListeners(SWT.Resize, new Event());
            layout();
        }
    }

    @objid ("8715cd3d-7501-4cd6-a22d-adecbf96b083")
    void updateBkImages() {
        if (this.controls != null && this.controls.length > 0) {
            // for (int i = 0; i < this.controls.length; i++) {
            // Control control = this.controls[i];
            // if (!control.isDisposed()) {
            // if (this.hovering) {
            // if (control instanceof Composite)
            // ((Composite) control).setBackgroundMode(SWT.INHERIT_NONE);
            // control.setBackgroundImage(null);
            // control.setBackground(getBackground());
            // } else {
            // if (control instanceof Composite)
            // ((Composite) control).setBackgroundMode(SWT.INHERIT_DEFAULT);
            // Rectangle bounds = control.getBounds();
            // int tabHeight = getTabHeight();
            // int height = this.getSize().y;
            // boolean wrapped = this.onRight ? bounds.y + bounds.height <
            // height - tabHeight
            // : bounds.y > tabHeight;
            // if (wrapped || this.gradientColors == null) {
            // control.setBackgroundImage(null);
            // control.setBackground(getBackground());
            // } else {
            // bounds.width = 10;
            // if (!this.onRight) {
            // bounds.y = -bounds.y;
            // bounds.height -= 2 * bounds.y - 1;
            // } else {
            // bounds.height += height - (bounds.y + bounds.height);
            // bounds.y = -1;
            // }
            // bounds.x = 0;
            // if (this.controlBkImages[i] != null)
            // this.controlBkImages[i].dispose();
            // this.controlBkImages[i] = new Image(control.getDisplay(),
            // bounds);
            // GC gc = new GC(this.controlBkImages[i]);
            // this.renderer.drawBackground(gc, bounds, 0);
            // gc.dispose();
            // control.setBackground(null);
            // control.setBackgroundImage(this.controlBkImages[i]);
            // }
            // }
            // }
            // }
        
        }
    }

    @objid ("e28b9d1b-0bdb-4fba-821f-9bdbe794ac75")
    String _getToolTip(int x, int y) {
        VTabItem item = getItem(new Point(x, y));
        if (item == null)
            return null;
        if (!item.showing)
            return null;
        return item.getToolTipText();
    }

/* public */
    /**
     * Set a control that can appear to the left or to the right of the
     * folder tabs. This method can also be used instead of
     * #setTopRight(Control). To remove a tab control,
     * see#removeTabControl(Control);
     * <p>
     * The flags parameter sets the layout of the control in the tab
     * area. <code>SWT.LEAD</code> will cause the control to be
     * positioned on the left of the tabs. <code>SWT.TRAIL</code> will
     * cause the control to be positioned on the far right of the folder
     * and it will have its default size. <code>SWT.TRAIL</code> can be
     * combined with <code>SWT.FILL</code>to fill all the available
     * space to the right of the last tab. <code>SWT.WRAP</code> can
     * also be added to <code>SWT.TRAIL</code> only to cause a control
     * to wrap if there is not enough space to display it in its
     * entirety.
     * </p>
     * @param control the control to be displayed in the top right corner or
     * null
     * @param flags valid combinations are:
     * <ul>
     * <li>SWT.LEAD
     * <li>SWT.TRAIL (| SWT.FILL | SWT.WRAP)
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has
     * been disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called
     * from the thread that created the receiver</li>
     * <li>ERROR_INVALID_ARGUMENT - if the control is not
     * a child of this VTabFolder</li>
     * </ul>
     */
    @objid ("1f0fc626-bbc4-43d5-bdd7-8f8a88271b21")
    void addTabControl(Control control, int flags) {
        checkWidget();
        addTabControl(control, flags, -1, true);
    }

    @objid ("772592f5-79b5-4b0a-8531-da2bc6a50655")
    void addTabControl(Control control, int flags, int index, boolean update) {
        switch (flags) {
        case SWT.TRAIL:
        case SWT.TRAIL | SWT.WRAP:
        case SWT.TRAIL | SWT.FILL:
        case SWT.TRAIL | SWT.FILL | SWT.WRAP:
        case SWT.LEAD:
            break;
        default:
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
            break;
        }
        if (control != null && control.getParent() != this) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
            return;
        }
        // check for duplicates
        for (int i = 0; i < this.controls.length; i++) {
            if (this.controls[i] == control) {
                SWT.error(SWT.ERROR_INVALID_ARGUMENT);
                return;
            }
        }
        int length = this.controls.length;
        
        if (control != null) {
            control.addListener(SWT.Resize, this.listener);
        }
        
        // Grow all 4 arrays
        Control[] newControls = new Control[length + 1];
        System.arraycopy(this.controls, 0, newControls, 0, length);
        this.controls = newControls;
        int[] newAlignment = new int[length + 1];
        System.arraycopy(this.controlAlignments, 0, newAlignment, 0, length);
        this.controlAlignments = newAlignment;
        Rectangle[] newRect = new Rectangle[length + 1];
        System.arraycopy(this.controlRects, 0, newRect, 0, length);
        this.controlRects = newRect;
        Image[] newImage = new Image[length + 1];
        System.arraycopy(this.controlBkImages, 0, newImage, 0, length);
        this.controlBkImages = newImage;
        if (index == -1) {
            index = length;
            if (this.chevronTb != null && control != this.chevronTb)
                index--;
        }
        System.arraycopy(this.controls, index, this.controls, index + 1, length - index);
        System.arraycopy(this.controlAlignments, index, this.controlAlignments, index + 1, length - index);
        System.arraycopy(this.controlRects, index, this.controlRects, index + 1, length - index);
        System.arraycopy(this.controlBkImages, index, this.controlBkImages, index + 1, length - index);
        this.controls[index] = control;
        this.controlAlignments[index] = flags;
        this.controlRects[index] = new Rectangle(0, 0, 0, 0);
        if (update) {
            updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
        }
    }

/* public */
    /**
     * Removes the control from the list of tab controls.
     * @param control the control to be removed
     * 
     * @exception SWTException
     * <ul>
     * <li>ERROR_WIDGET_DISPOSED - if the receiver has
     * been disposed</li>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called
     * from the thread that created the receiver</li>
     * <li>ERROR_INVALID_ARGUMENT - if the control is not
     * a child of this VTabFolder</li>
     * </ul>
     */
    @objid ("3302a4e2-4455-4c1a-bd82-8230c3b880b6")
    void removeTabControl(Control control) {
        checkWidget();
        removeTabControl(control, true);
    }

    @objid ("4faa08c3-77cf-4dbc-a19c-066904116fde")
    void removeTabControl(Control control, boolean update) {
        if (control != null && control.getParent() != this) {
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);
        }
        int index = -1;
        for (int i = 0; i < this.controls.length; i++) {
            if (this.controls[i] == control) {
                index = i;
                break;
            }
        }
        if (index == -1)
            return;
        
        if (control != null && !control.isDisposed()) {
            control.removeListener(SWT.Resize, this.listener);
            control.setBackground(null);
            control.setBackgroundImage(null);
            if (control instanceof Composite)
                ((Composite) control).setBackgroundMode(SWT.INHERIT_NONE);
        }
        
        if (this.controlBkImages[index] != null && !this.controlBkImages[index].isDisposed())
            this.controlBkImages[index].dispose();
        if (this.controls.length == 1) {
            this.controls = new Control[0];
            this.controlAlignments = new int[0];
            this.controlRects = new Rectangle[0];
            this.controlBkImages = new Image[0];
        } else {
            Control[] newControls = new Control[this.controls.length - 1];
            System.arraycopy(this.controls, 0, newControls, 0, index);
            System.arraycopy(this.controls, index + 1, newControls, index, this.controls.length - index - 1);
            this.controls = newControls;
        
            int[] newAlignments = new int[this.controls.length];
            System.arraycopy(this.controlAlignments, 0, newAlignments, 0, index);
            System.arraycopy(this.controlAlignments, index + 1, newAlignments, index, this.controls.length - index);
            this.controlAlignments = newAlignments;
        
            Rectangle[] newRects = new Rectangle[this.controls.length];
            System.arraycopy(this.controlRects, 0, newRects, 0, index);
            System.arraycopy(this.controlRects, index + 1, newRects, index, this.controls.length - index);
            this.controlRects = newRects;
        
            Image[] newBkImages = new Image[this.controls.length];
            System.arraycopy(this.controlBkImages, 0, newBkImages, 0, index);
            System.arraycopy(this.controlBkImages, index + 1, newBkImages, index, this.controls.length - index);
            this.controlBkImages = newBkImages;
        }
        if (update) {
            updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
        }
    }

    @objid ("45d90f5d-c633-49cc-91df-70e74bffd22c")
    int getWrappedHeight(Point size) {
        boolean[][] positions = new boolean[1][];
        Rectangle[] rects = computeControlBounds(size, positions);
        int minY = Integer.MAX_VALUE, maxY = 0, wrapHeight = 0;
        for (int i = 0; i < rects.length; i++) {
            if (positions[0][i]) {
                minY = Math.min(minY, rects[i].y);
                maxY = Math.max(maxY, rects[i].y + rects[i].height);
                wrapHeight = maxY - minY;
            }
        }
        return wrapHeight;
    }

/* public */
    /**
     * Sets whether a chevron is shown when there are more items to be
     * displayed.
     * @exception IllegalArgumentException
     * <ul>
     * <li>ERROR_INVALID_RANGE - if the index is out of range
     * </li>
     * </ul>
     * @exception SWTException
     * <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS when called from the wrong
     * thread</li>
     * <li>ERROR_WIDGET_DISPOSED when the widget has been
     * disposed</li>
     * </ul>
     */
    @objid ("134bacca-f4b2-4093-8c2c-daf6cc5d25ab")
    void setChevronVisible(boolean visible) {
        checkWidget();
        if (this.chevronVisible == visible)
            return;
        this.chevronVisible = visible;
        updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
    }

    @objid ("b2c98743-fdce-48e6-b143-8f8ffb95649a")
    public boolean isSimple() {
        return this.simple;
    }

    @objid ("0fb39a9b-46b9-4a4b-9a58-fe50bdf81f8a")
    public boolean isOnRight() {
        return this.onRight;
    }

    @objid ("4b57176f-4ca6-4da9-9eac-c6a2004274c8")
    public void setOnRight(boolean onRight) {
        this.onRight = onRight;
    }

    @objid ("96c12d2d-bb2e-479d-9c80-a27603d8cd96")
    public int getFixedTabWidth() {
        return this.fixedTabWidth;
    }

    @objid ("52261651-ef54-409c-b520-0d9d555528ad")
    public void setFixedTabWidth(int fixedTabWidth) {
        this.fixedTabWidth = fixedTabWidth;
    }

    @objid ("63f0c511-37c9-4031-a6a2-5bdd62a91bc9")
    public int getMinChars() {
        return this.minChars;
    }

    @objid ("792c6102-7ae5-4d2c-b930-c68a435aca7f")
    public void setMinChars(int minChars) {
        this.minChars = minChars;
    }

    @objid ("36aa41f2-0392-469f-b740-8e6573991123")
    public boolean isBorderVisible() {
        return this.borderVisible;
    }

    @objid ("33edeb85-0d69-42dd-a726-8ba277d3c53f")
    public boolean isSingle() {
        return this.single;
    }

    @objid ("0b3c7fbc-935c-4915-95bb-f3e706a7d035")
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @objid ("344f746d-de1a-41e5-9465-95fcea9f776d")
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @objid ("c09437dd-a1fc-4788-aa94-a3087131518c")
    public int getFirstIndex() {
        return this.firstIndex;
    }

    @objid ("af9325c3-6ea4-42b9-8444-c60a922d8b1d")
    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }


static {
        String platform = SWT.getPlatform();
        IS_GTK = "gtk".equals(platform);
    }
}

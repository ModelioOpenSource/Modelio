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

package org.modelio.core.ui.swt.contribitem;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionManagerOverrides;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.LegacyActionTools;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.util.Policy;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;

/**
 * This is a copy of {@link org.eclipse.jface.action.ActionContributionItem} with the following modifications:
 * <ul>
 * <li>Eclipse strange things removed</li>
 * <li>No need to provide an action, all setters are on SwtContributionItem</li>
 * <li>Fixed no tooltip on menu items.</li>
 * </ul>
 * 
 * A contribution item which runs an action set with {@link #setAction(Runnable)}.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * @since Modelio 3.7
 * @author cma
 */
@objid ("a559f7f7-aee6-450c-a09d-8a727311b351")
public class SwtContributionItem extends ContributionItem {
    /**
     * Mode bit: Show text on tool items or buttons, even if an image is
     * present. If this mode bit is not set, text is only shown on tool items if
     * there is no image present.
     */
    @objid ("9eb2c6da-b17f-4d76-8e8f-625de9b47bfd")
    public static final int MODE_FORCE_TEXT = 1;

    /**
     * Whether color icons should be used in toolbars.
     * <code>true</code> if color icons should be used in toolbars,
     * <code>false</code> otherwise
     */
    @objid ("384018e8-e9e5-4c8b-840c-7f52d0855517")
    private static boolean USE_COLOR_ICONS = true;

    @objid ("a7afa125-3187-4708-95da-2a17538cbf36")
    private int accelerator;

    /**
     * For checkbox and radio buttons.
     */
    @objid ("d5dcf59f-4df6-4929-b14f-123ebaae28f6")
    private boolean checked;

    /**
     * a string inserted in the middle of text that has been shortened
     */
    @objid ("c5853e8b-ef2d-42e1-8754-1b0d9f769034")
    private static final String ellipsis = "..."; // $NON-NLS-1$

    @objid ("8c404366-d013-493d-a5d7-74c44019dd74")
    private boolean enabled = true;

    @objid ("5c3aaac0-72a0-49be-93ef-e043ea4b5b1b")
    private boolean menuCreatorCalled;

    /**
     * The presentation mode, which is the bitwise-or of the
     * <code>MODE_*</code> constants.
     * @see #MODE_FORCE_TEXT
     */
    @objid ("344cfcc8-2e70-4ac3-82d9-3cf5096328c0")
    private int mode;

    @objid ("626dd338-55d0-4cb1-8cc6-30c6fb552d5a")
    private Style style;

    @objid ("4195f15a-258e-4b01-bbd6-bf6f90036344")
    private String text;

    @objid ("7b2c01de-c92f-495f-b08b-7121a720d25a")
    private String tooltipText;

    /**
     * The action.
     */
    @objid ("8bfea282-1f0e-49d8-828b-bb0d1347314f")
    private Runnable action;

    /**
     * Listener for SWT button widget events.
     */
    @objid ("c799ad59-b1d9-44f2-b68a-d69536369b01")
    private Listener buttonListener;

    @objid ("124e86e5-237b-4fc1-a37b-5b7792676c3d")
    private ImageDescriptor disabledImageDescriptor;

    @objid ("bd529461-f2ee-4b7f-8490-5f00933fb95b")
    private HelpListener helpListener;

    /**
     * This is the easiest way to hold the menu until we can swap it in to the
     * proxy.
     */
    @objid ("a6275da3-e48a-4c10-9046-8c5021e8c53a")
    private Menu holdMenu;

    @objid ("c74bd7b4-390d-4bad-b17a-141965b90f86")
    private ImageDescriptor hoverImageDescriptor;

    @objid ("375b3bb1-58f9-4cd2-8c31-d668afd64897")
    private ImageDescriptor imageDescriptor;

    /**
     * Remembers all images in use by this contribution item
     */
    @objid ("256ef0b6-c043-495a-bb05-8362f696ae8a")
    private LocalResourceManager imageManager;

    @objid ("61080993-25ed-4c03-b217-9af45f84ba30")
    private IMenuCreator menuCreator;

    @objid ("fc279058-4bc7-4b80-8f3c-a645a753c5c1")
    private Listener menuCreatorListener;

    /**
     * Listener for SWT menu item widget events.
     */
    @objid ("c5588b4a-fae5-49c4-99e3-6dfe6bf265d7")
    private Listener menuItemListener;

    /**
     * Listener for SWT tool item widget events.
     */
    @objid ("238e25bd-2acc-449c-a93f-5e27811c91e7")
    private Listener toolItemListener;

    /**
     * The widget created for this item; <code>null</code> before creation and
     * after disposal.
     */
    @objid ("cde0f268-8861-4415-8611-f2671e4d8b84")
    private Widget widget;

    /**
     * Creates a new contribution item from the given action. The id of the
     * action is used as the id of the item.
     * @param style the action
     */
    @objid ("9c223825-f21c-4778-afdd-1622ec494241")
    public SwtContributionItem(Style style) {
        super();
        this.style = style;
    }

    /**
     * Creates a new contribution item from the {@link Style#AS_PUSH_BUTTON} action.
     */
    @objid ("918a1ae8-fa83-4ef1-8eee-534f07bc9523")
    public SwtContributionItem() {
        this(Style.AS_PUSH_BUTTON);
    }

    @objid ("2860fcf8-a9b6-4f0b-9ce0-bb8981063d0b")
    @Override
    public void dispose() {
        if (this.widget != null) {
            this.widget.dispose();
            this.widget = null;
        }
        this.holdMenu = null;
    }

    /**
     * Compares this action contribution item with another object. Two action
     * contribution items are equal if they refer to the identical Action.
     */
    @objid ("27fde173-868b-440a-b099-85b3f9e534f4")
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SwtContributionItem)) {
            return false;
        }
        return this.action.equals(((SwtContributionItem) o).action);
    }

    /**
     * The <code>ActionContributionItem</code> implementation of this
     * <code>IContributionItem</code> method creates an SWT
     * <code>Button</code> for the action using the action's style. If the
     * action's checked property has been set, the button is created and primed
     * to the value of the checked property.
     */
    @objid ("a046b4b7-51ce-4d29-a47c-a3b27aa3310e")
    @Override
    public void fill(Composite parent) {
        if (this.widget == null && parent != null) {
            int flags = SWT.PUSH;
            if (this.action != null) {
                if (getStyle() == Style.AS_CHECK_BOX) {
                    flags = SWT.TOGGLE;
                }
                if (getStyle() == Style.AS_RADIO_BUTTON) {
                    flags = SWT.RADIO;
                }
            }
        
            Button b = new Button(parent, flags);
            b.setData(this);
            b.addListener(SWT.Dispose, getButtonListener());
            // Don't hook a dispose listener on the parent
            b.addListener(SWT.Selection, getButtonListener());
            if (getHelpListener() != null) {
                b.addHelpListener(getHelpListener());
            }
            this.widget = b;
        
            update(null);
        }
    }

    /**
     * The <code>ActionContributionItem</code> implementation of this
     * <code>IContributionItem</code> method creates an SWT
     * <code>MenuItem</code> for the action using the action's style. If the
     * action's checked property has been set, a button is created and primed to
     * the value of the checked property. If the action's menu creator property
     * has been set, a cascading submenu is created.
     */
    @objid ("e9e962de-5f27-4fbf-a865-37412c3112b2")
    @Override
    public void fill(Menu parent, int index) {
        if (this.widget == null && parent != null && isVisible()) {
            int flags = SWT.PUSH;
            if (this.action != null) {
                Style myStyle = getStyle();
                if (myStyle == Style.AS_CHECK_BOX) {
                    flags = SWT.CHECK;
                } else if (myStyle == Style.AS_RADIO_BUTTON) {
                    flags = SWT.RADIO;
                } else if (myStyle == Style.AS_DROP_DOWN_MENU) {
                    flags = SWT.CASCADE;
                }
            }
        
            MenuItem mi = null;
            if (index >= 0) {
                mi = new MenuItem(parent, flags, index);
            } else {
                mi = new MenuItem(parent, flags);
            }
            this.widget = mi;
        
            mi.setData(this);
            mi.addListener(SWT.Dispose, getMenuItemListener());
            mi.addListener(SWT.Selection, getMenuItemListener());
            if (getHelpListener() != null) {
                mi.addHelpListener(getHelpListener());
            }
        
            if (flags == SWT.CASCADE) {
                // just create a proxy for now, if the user shows it then
                // fill it in
                Menu subMenu = new Menu(parent);
                subMenu.addListener(SWT.Show, getMenuCreatorListener());
                subMenu.addListener(SWT.Hide, getMenuCreatorListener());
                mi.setMenu(subMenu);
            }
        
            update(null);
        }
    }

    /**
     * The <code>ActionContributionItem</code> implementation of this ,
     * <code>IContributionItem</code> method creates an SWT
     * <code>ToolItem</code> for the action using the action's style. If the
     * action's checked property has been set, a button is created and primed to
     * the value of the checked property. If the action's menu creator property
     * has been set, a drop-down tool item is created.
     */
    @objid ("07e2d3f1-0661-47ab-b0e0-8c5964678bb4")
    @Override
    public void fill(ToolBar parent, int index) {
        if (this.widget == null && parent != null) {
            int flags = SWT.PUSH;
            if (this.action != null) {
                Style myStyle = getStyle();
                if (myStyle == Style.AS_CHECK_BOX) {
                    flags = SWT.CHECK;
                } else if (myStyle == Style.AS_RADIO_BUTTON) {
                    flags = SWT.RADIO;
                } else if (myStyle == Style.AS_DROP_DOWN_MENU) {
                    flags = SWT.DROP_DOWN;
                }
            }
        
            ToolItem ti = null;
            if (index >= 0) {
                ti = new ToolItem(parent, flags, index);
            } else {
                ti = new ToolItem(parent, flags);
            }
            ti.setData(this);
            ti.addListener(SWT.Selection, getToolItemListener());
            ti.addListener(SWT.Dispose, getToolItemListener());
        
            this.widget = ti;
        
            update(null);
        
        }
    }

    @objid ("2050489d-6a81-4ab5-be73-adcea121b2cc")
    public int getAccelerator() {
        return this.accelerator;
    }

    /**
     * Returns the action associated with this contribution item.
     * @return the action
     */
    @objid ("61ab63dc-52d3-4c87-b554-5373efc42db5")
    public Runnable getAction() {
        return this.action;
    }

    /**
     * Returns the presentation mode, which is the bitwise-or of the
     * <code>MODE_*</code> constants. The default mode setting is 0, meaning
     * that for menu items, both text and image are shown (if present), but for
     * tool items, the text is shown only if there is no image.
     * @return the presentation mode settings
     */
    @objid ("433ba94b-bce9-4e11-bc7f-342415b56772")
    public int getMode() {
        return this.mode;
    }

    @objid ("913589f3-d520-4353-b6cf-f19916703d93")
    public Style getStyle() {
        return this.style;
    }

    /**
     * Gets the receiver's text.
     * <p>
     * The string may include the mnemonic character.
     * Mnemonics are indicated by an '&' that causes the next character to be the mnemonic.
     * When the user presses a key sequence that matches the mnemonic, a selection event occurs.
     * On most platforms, the mnemonic appears underlined but may be emphasised in a platform specific manner.
     * The mnemonic indicator character '&' can be escaped by doubling it in the string, causing a single '&' to be displayed.
     * @return the item text.
     */
    @objid ("eb588b1b-7820-4705-abc0-cbcb366dd8d2")
    public String getText() {
        return this.text;
    }

    @objid ("da51bc89-8976-4550-b20a-6a4aeb8dab4c")
    public String getToolTipText() {
        return this.tooltipText;
    }

    /**
     * Returns whether color icons should be used in toolbars.
     * @return <code>true</code> if color icons should be used in toolbars,
     * <code>false</code> otherwise
     */
    @objid ("a5bf6551-2a28-4e37-a251-d310d18fb206")
    public static boolean getUseColorIconsInToolbars() {
        return SwtContributionItem.USE_COLOR_ICONS;
    }

    /**
     * Return the widget associated with this contribution item. It should not
     * be cached, as it can be disposed and re-created by its containing
     * ContributionManager, which controls all of the widgets lifecycle methods.
     * <p>
     * This can be used to set layout data on the widget if appropriate. The
     * actual type of the widget can be any valid control for this
     * ContributionItem's current ContributionManager.
     * </p>
     * @return the widget, or <code>null</code> depending on the lifecycle.
     */
    @objid ("57948fff-83a1-4567-b4ac-25a1e86c4dfd")
    public Widget getWidget() {
        return this.widget;
    }

    @objid ("50e97b55-e481-4c07-b076-ceba0f9d646a")
    @Override
    public int hashCode() {
        return this.action.hashCode();
    }

    @objid ("86ad3f51-465d-4936-bee9-b6a573a9a7b0")
    public boolean isChecked() {
        return this.checked;
    }

    /**
     * The action item implementation of this <code>IContributionItem</code>
     * method returns <code>true</code> for menu items and <code>false</code>
     * for everything else.
     */
    @objid ("c81c6eb9-106d-4cc8-a758-88bbaa8cf1af")
    @Override
    public boolean isDynamic() {
        if (this.widget instanceof MenuItem) {
            // Optimization. Only recreate the item is the check or radio style
            // has changed.
            boolean itemIsCheck = (this.widget.getStyle() & SWT.CHECK) != 0;
            boolean actionIsCheck = getAction() != null
                    && getStyle() == Style.AS_CHECK_BOX;
            boolean itemIsRadio = (this.widget.getStyle() & SWT.RADIO) != 0;
            boolean actionIsRadio = getAction() != null
                    && getStyle() == Style.AS_RADIO_BUTTON;
            return (itemIsCheck != actionIsCheck)
                    || (itemIsRadio != actionIsRadio);
        }
        return false;
    }

    @objid ("3b8a6062-9ed7-450f-a8a9-237028bc2aa3")
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @objid ("82ce155c-7171-4e8d-9acb-d2bc52a77764")
    public void setAccelerator(int accelerator) {
        this.accelerator = accelerator;
    }

    @objid ("8b380736-48fc-4b1b-9cf8-6593013678d0")
    public void setAction(Runnable action) {
        this.action = action;
    }

    @objid ("8739fb1d-d877-4173-84cf-edfb61a94b38")
    public void setButtonListener(Listener buttonListener) {
        this.buttonListener = buttonListener;
    }

    @objid ("7e299c92-3a25-484f-9e25-2bee637d720f")
    public void setChecked(boolean selection) {
        this.checked = selection;
    }

    @objid ("e736d355-5f66-4184-aa77-4a5b9cd1758c")
    public void setDisabledImageDescriptor(ImageDescriptor disabledImageDescriptor) {
        this.disabledImageDescriptor = disabledImageDescriptor;
    }

    @objid ("e11839fc-a96e-43ea-9332-59c87ba1a622")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @objid ("10249316-bb30-4ad2-b27e-d138cc350bbe")
    public SwtContributionItem setHelpListener(HelpListener helpListener) {
        this.helpListener = helpListener;
        return this;
    }

    @objid ("93502104-8341-47be-9f5e-2832c62420ea")
    public void setHoverImageDescriptor(ImageDescriptor hoverImageDescriptor) {
        this.hoverImageDescriptor = hoverImageDescriptor;
    }

    @objid ("b08da8c9-08a1-46e2-98b9-f3ba7acb8119")
    public void setImageDescriptor(ImageDescriptor imageDescriptor) {
        this.imageDescriptor = imageDescriptor;
    }

    @objid ("87725a46-684e-4151-93b7-5a746f6409e4")
    public void setMenuCreator(IMenuCreator menuCreator) {
        this.menuCreator = menuCreator;
    }

    @objid ("457cbe11-292f-430a-8c11-3c7992d4b224")
    public void setMenuCreatorListener(Listener menuCreatorListener) {
        this.menuCreatorListener = menuCreatorListener;
    }

    @objid ("c22a26a1-b139-4a12-aaf0-8cbdaa5b0ef4")
    public void setMenuItemListener(Listener menuItemListener) {
        this.menuItemListener = menuItemListener;
    }

    /**
     * Sets the presentation mode, which is the bitwise-or of the
     * <code>MODE_*</code> constants.
     * @see #MODE_FORCE_TEXT
     * @param mode the presentation mode settings
     */
    @objid ("b8159e7d-1714-46c6-88e4-2f920aa870e1")
    public void setMode(int mode) {
        this.mode = mode;
        update();
    }

    /**
     * Sets the receiver's text.
     * <p>
     * The string may include the mnemonic character.
     * Mnemonics are indicated by an '&' that causes the next character to be the mnemonic.
     * When the user presses a key sequence that matches the mnemonic, a selection event occurs.
     * On most platforms, the mnemonic appears underlined but may be emphasised in a platform specific manner.
     * The mnemonic indicator character '&' can be escaped by doubling it in the string, causing a single '&' to be displayed.
     * @param text the item text.
     */
    @objid ("ab5c6720-2cc9-4edf-88d4-1458c5541105")
    public void setText(String text) {
        this.text = text;
    }

    @objid ("b33396af-966e-459d-b494-88fdd3aa853b")
    public void setToolItemListener(Listener toolItemListener) {
        this.toolItemListener = toolItemListener;
    }

    /**
     * Set the tooltip text.
     * <p>
     * Automatically escape mnemonics.
     * @see MenuItem#setToolTipText(String)
     * @see ToolItem#setToolTipText(String)
     * @see Button#setToolTipText(String)
     * @see #setTooltipText(String, boolean)
     * @param tooltipText the tooltip text.
     */
    @objid ("77b70109-7fe0-4877-a571-4687d7801c4c")
    public void setTooltipText(String tooltipText) {
        setTooltipText(tooltipText, true);
    }

    /**
     * Set the tooltip text.
     * <p>
     * Escape mnemonics if escapeMnemonics is true .
     * @see MenuItem#setToolTipText(String)
     * @see ToolItem#setToolTipText(String)
     * @see Button#setToolTipText(String)
     * @param tooltipText the tooltip text.
     * @param escapeMnemonics whether to escape '&' mnemonic character.
     */
    @objid ("96446ea8-bcb2-4eb8-baee-387f2411f5e8")
    public void setTooltipText(String tooltipText, boolean escapeMnemonics) {
        if (escapeMnemonics) {
            this.tooltipText = LegacyActionTools.escapeMnemonics(tooltipText);
        } else {
            this.tooltipText = tooltipText;
        }
    }

    /**
     * Sets whether color icons should be used in toolbars.
     * @param useColorIcons <code>true</code> if color icons should be used in toolbars,
     * <code>false</code> otherwise
     */
    @objid ("81eedb06-df2a-454d-ad31-8789226f6ec4")
    public static void setUseColorIconsInToolbars(boolean useColorIcons) {
        SwtContributionItem.USE_COLOR_ICONS = useColorIcons;
    }

    /**
     * The action item implementation of this <code>IContributionItem</code>
     * method calls <code>update(null)</code>.
     */
    @objid ("e48217ef-8a7d-44fe-a5af-dc78f6ffeb85")
    @Override
    public final void update() {
        update(null);
    }

    /**
     * Synchronizes the UI with the given property.
     * @param propertyName the name of the property, or <code>null</code> meaning all
     * applicable properties
     */
    @objid ("da930f05-945b-45cd-9eae-7e650725dd52")
    @Override
    public void update(String propertyName) {
        if (this.widget != null) {
        
            if (this.widget instanceof ToolItem) {
                ToolItem ti = (ToolItem) this.widget;
                updateToolItem(ti);
                return;
            }
        
            if (this.widget instanceof MenuItem) {
                MenuItem mi = (MenuItem) this.widget;
        
                updateMenuItem(mi);
        
                return;
            }
        
            if (this.widget instanceof Button) {
                Button button = (Button) this.widget;
        
                updateButton(button);
                return;
            }
        }
    }

    /**
     * Returns <code>true</code> if this item is allowed to enable,
     * <code>false</code> otherwise.
     * @return if this item is allowed to be enabled
     */
    @objid ("91e06e50-9dc5-4779-a39b-e8c273e2dbb6")
    protected boolean isEnabledAllowed() {
        if (getParent() == null) {
            return true;
        }
        Boolean value = getParent().getOverrides().getEnabled(this);
        return (value == null) ? true : value.booleanValue();
    }

    /**
     * Shorten the given text <code>t</code> so that its length doesn't exceed
     * the width of the given ToolItem.The default implementation replaces
     * characters in the center of the original string with an ellipsis ("...").
     * Override if you need a different strategy.
     * @param textValue the text to shorten
     * @param item the tool item the text belongs to
     * @return the shortened string
     */
    @objid ("84aa792f-2fd6-4754-9e60-f0b1c4ab37b0")
    protected String shortenText(String textValue, ToolItem item) {
        if (textValue == null) {
            return null;
        }
        
        GC gc = new GC(item.getParent());
        try {
            Image image = item.getImage();
            int maxWidth = image != null ? image.getBounds().width * 4 : 32;
        
            if (gc.textExtent(textValue).x < maxWidth) {
                return textValue;
            }
        
            for (int i = textValue.length(); i > 0; i--) {
                String test = textValue.substring(0, i);
                test = test + SwtContributionItem.ellipsis;
                if (gc.textExtent(test).x < maxWidth) {
                    return test;
                }
        
            }
        } finally {
            gc.dispose();
        }
        // If for some reason we fall through abort
        return textValue;
    }

    @objid ("55a6733f-fc2f-49bf-a5aa-a56fc2ed29c5")
    protected void updateButton(Button button) {
        updateImages(false);
        
        String myText = getText();
        boolean showText = myText != null && ((getMode() & SwtContributionItem.MODE_FORCE_TEXT) != 0 || !hasImages());
        String textToSet = showText ? myText : ""; //$NON-NLS-1$
        button.setText(textToSet);
        button.setToolTipText(getToolTipText());
        
        boolean shouldBeEnabled = isEnabled()
                && isEnabledAllowed();
        
        if (button.getEnabled() != shouldBeEnabled) {
            button.setEnabled(shouldBeEnabled);
        }
        
        boolean bv = isChecked();
        if (button.getSelection() != bv) {
            button.setSelection(bv);
        }
    }

    @objid ("b206b84b-1f9b-47aa-8d11-66ed2b3537b3")
    protected void updateMenuItem(MenuItem mi) {
        int myAccelerator = 0;
        String myText = null;
        IContributionManagerOverrides overrides = null;
        String acceleratorText = null;
        
        if (getParent() != null) {
            overrides = getParent().getOverrides();
            if (overrides != null) {
                myText = overrides.getText(this);
            }
        }
        
        if (myText == null) {
            myText = getText();
        }
        if (myAccelerator == 0) {
            myAccelerator = getAccelerator();
        }
        mi.setAccelerator(myAccelerator);
        
        if (myText != null) {
            // use extracted accelerator text in case accelerator
            // cannot be fully represented in one int (e.g.
            // multi-stroke keys)
            acceleratorText = LegacyActionTools.extractAcceleratorText(myText);
            if (acceleratorText == null && myAccelerator != 0) {
                acceleratorText = Action.convertAccelerator(myAccelerator);
            }
        }
        
        if (myText == null) {
            myText = ""; //$NON-NLS-1$
        } else {
            myText = Action.removeAcceleratorText(myText);
        }
        
        if (acceleratorText == null) {
            mi.setText(myText);
        } else {
            mi.setText(myText + '\t' + acceleratorText);
        }
        
        String toolTip = getToolTipText();
        if (toolTip != null && Util.isWindows()) {
            // Infamous bug under windows, escaped mnemonic indicators are not properly taken into account
            toolTip = toolTip.replace("&&", "&");
        }
        mi.setToolTipText(toolTip);
        
        updateImages(false);
        
        boolean shouldBeEnabled = isEnabled()
                && isEnabledAllowed();
        
        if (mi.getEnabled() != shouldBeEnabled) {
            mi.setEnabled(shouldBeEnabled);
        }
        
        boolean bv = isChecked();
        
        if (mi.getSelection() != bv) {
            mi.setSelection(bv);
        }
    }

    @objid ("c222c2b8-7b38-479b-9c79-a99235bc4428")
    protected void updateToolItem(ToolItem ti) {
        String myText = getText();
        // the set text is shown only if there is no image or if forced
        // by MODE_FORCE_TEXT
        boolean showText = myText != null
                && ((getMode() & SwtContributionItem.MODE_FORCE_TEXT) != 0 || !hasImages());
        
        // only do the trimming if the text will be used
        if (showText && myText != null) {
            myText = LegacyActionTools.removeAcceleratorText(myText);
        }
        
        String textToSet = showText ? shortenText(myText, ti) : ""; //$NON-NLS-1$
        ti.setText(textToSet);
        
        // only substitute a missing image if it has no text
        updateImages(!showText);
        
        String toolTip = getToolTipText();
        if (toolTip == null || toolTip.isEmpty()) {
            toolTip = myText;
        }
        
        if (toolTip != null && Util.isWindows()) {
            // Infamous bug under windows, escaped mnemonic indicators are not properly taken into account
            toolTip = toolTip.replace("&&", "&&&&&&");
        }
        
        // if the text is showing, then only set the tooltip if
        // different
        if (!showText || toolTip != null && !toolTip.equals(myText)) {
            ti.setToolTipText(toolTip);
        } else {
            ti.setToolTipText(null);
        }
        
        boolean shouldBeEnabled = isEnabled()
                && isEnabledAllowed();
        
        if (ti.getEnabled() != shouldBeEnabled) {
            ti.setEnabled(shouldBeEnabled);
        }
        
        boolean bv = isChecked();
        
        if (ti.getSelection() != bv) {
            ti.setSelection(bv);
        }
    }

    /**
     * Create MenuItems in the proxy menu that can execute the real menu items
     * if selected. Create proxy menus for any real item submenus.
     * @param realMenu the real menu to copy from
     * @param proxy the proxy menu to populate
     */
    @objid ("65b6c8fe-c4eb-45cc-a245-c33c667041ca")
    private void copyMenu(Menu realMenu, Menu proxy) {
        if (realMenu.isDisposed() || proxy.isDisposed()) {
            return;
        }
        
        // we notify the real menu so it can populate itself if it was
        // listening for SWT.Show
        realMenu.notifyListeners(SWT.Show, null);
        
        final Listener passThrough = event -> {
            if (!event.widget.isDisposed()) {
                Widget realItem = (Widget) event.widget.getData();
                if (!realItem.isDisposed()) {
                    int widgetStyle = event.widget.getStyle();
                    if (event.type == SWT.Selection
                            && ((widgetStyle & (SWT.TOGGLE | SWT.CHECK | SWT.RADIO)) != 0)
                            && realItem instanceof MenuItem) {
                        ((MenuItem) realItem)
                                .setSelection(((MenuItem) event.widget)
                                        .getSelection());
                    }
                    event.widget = realItem;
                    realItem.notifyListeners(event.type, event);
                }
            }
        };
        
        MenuItem[] items = realMenu.getItems();
        for (int i = 0; i < items.length; i++) {
            final MenuItem realItem = items[i];
            final MenuItem proxyItem = new MenuItem(proxy, realItem.getStyle());
            proxyItem.setData(realItem);
            proxyItem.setAccelerator(realItem.getAccelerator());
            proxyItem.setEnabled(realItem.getEnabled());
            proxyItem.setImage(realItem.getImage());
            proxyItem.setSelection(realItem.getSelection());
            proxyItem.setText(realItem.getText());
        
            // pass through any events
            proxyItem.addListener(SWT.Selection, passThrough);
            proxyItem.addListener(SWT.Arm, passThrough);
            proxyItem.addListener(SWT.Help, passThrough);
        
            final Menu itemMenu = realItem.getMenu();
            if (itemMenu != null) {
                // create a proxy for any sub menu items
                final Menu subMenu = new Menu(proxy);
                subMenu.setData(itemMenu);
                proxyItem.setMenu(subMenu);
                subMenu.addListener(SWT.Show, new Listener() {
                    @Override
                    public void handleEvent(Event event) {
                        event.widget.removeListener(SWT.Show, this);
                        if (event.type == SWT.Show) {
                            copyMenu(itemMenu, subMenu);
                        }
                    }
                });
                subMenu.addListener(SWT.Help, passThrough);
                subMenu.addListener(SWT.Hide, passThrough);
            }
        }
    }

    /**
     * Dispose any images allocated for this contribution item
     */
    @objid ("516fa5de-9e25-49f1-95d0-22af7da575de")
    private void disposeOldImages() {
        if (this.imageManager != null) {
            this.imageManager.dispose();
            this.imageManager = null;
        }
    }

    /**
     * Returns the listener for SWT button widget events.
     * @return a listener for button events
     */
    @objid ("b9bc0099-5120-4624-bf4a-c26a67b93958")
    private Listener getButtonListener() {
        if (this.buttonListener == null) {
            this.buttonListener = event -> {
                switch (event.type) {
                case SWT.Dispose:
                    handleWidgetDispose(event);
                    break;
                case SWT.Selection:
                    Widget ew = event.widget;
                    if (ew != null) {
                        handleWidgetSelection(event, ((Button) ew)
                                .getSelection());
                    }
                    break;
                }
            };
        }
        return this.buttonListener;
    }

    @objid ("979296ef-43cd-4b95-9bb0-2f1aaa2aa502")
    private ImageDescriptor getDisabledImageDescriptor() {
        return this.disabledImageDescriptor;
    }

    @objid ("3b792fb1-9da3-4aa1-821a-4fddcf499f93")
    private HelpListener getHelpListener() {
        return this.helpListener;
    }

    @objid ("adaba9f8-48c5-47d8-a87f-575bc4e04118")
    private ImageDescriptor getHoverImageDescriptor() {
        return this.hoverImageDescriptor;
    }

    @objid ("68d66ea5-e9c0-410c-a735-b9f0cb2948f1")
    private ImageDescriptor getImageDescriptor() {
        return this.imageDescriptor;
    }

    @objid ("11e94100-0819-412e-b170-f46fda296ba2")
    private IMenuCreator getMenuCreator() {
        return this.menuCreator;
    }

    /**
     * Handle show and hide on the proxy menu for IAction.AS_DROP_DOWN_MENU
     * actions.
     * @return the appropriate listener
     */
    @objid ("6cf14fc0-78e8-45e6-a9ae-1db9d0fa0dab")
    private Listener getMenuCreatorListener() {
        if (this.menuCreatorListener == null) {
            this.menuCreatorListener = event -> {
                switch (event.type) {
                case SWT.Show:
                    handleShowProxy((Menu) event.widget);
                    break;
                case SWT.Hide:
                    handleHideProxy((Menu) event.widget);
                    break;
                }
            };
        }
        return this.menuCreatorListener;
    }

    /**
     * Returns the listener for SWT menu item widget events.
     * @return a listener for menu item events
     */
    @objid ("934938fa-5122-4345-ac59-e5f466509450")
    private Listener getMenuItemListener() {
        if (this.menuItemListener == null) {
            this.menuItemListener = event -> {
                switch (event.type) {
                case SWT.Dispose:
                    handleWidgetDispose(event);
                    break;
                case SWT.Selection:
                    Widget ew = event.widget;
                    if (ew != null) {
                        handleWidgetSelection(event, ((MenuItem) ew)
                                .getSelection());
                    }
                    break;
                }
            };
        }
        return this.menuItemListener;
    }

    /**
     * Returns the listener for SWT tool item widget events.
     * @return a listener for tool item events
     */
    @objid ("157b6c2d-6664-4496-a084-326af40da730")
    private Listener getToolItemListener() {
        if (this.toolItemListener == null) {
            this.toolItemListener = event -> {
                switch (event.type) {
                case SWT.Dispose:
                    handleWidgetDispose(event);
                    break;
                case SWT.Selection:
                    Widget ew = event.widget;
                    if (ew != null) {
                        handleWidgetSelection(
                                event,
                                ((ToolItem) ew).getSelection());
                    }
                    break;
                }
            };
        }
        return this.toolItemListener;
    }

    /**
     * The proxy menu is being hidden, so we need to make it go away.
     * @param proxy the proxy menu
     */
    @objid ("d931473f-9b60-4ad8-8d2a-30e4d084d5d1")
    private void handleHideProxy(final Menu proxy) {
        proxy.removeListener(SWT.Hide, getMenuCreatorListener());
        proxy.getDisplay().asyncExec(() -> {
            if (!proxy.isDisposed()) {
                MenuItem parentItem = proxy.getParentItem();
                proxy.dispose();
                parentItem.setMenu(this.holdMenu);
            }
            if (this.holdMenu != null && !this.holdMenu.isDisposed()) {
                this.holdMenu.notifyListeners(SWT.Hide, null);
            }
            this.holdMenu = null;
        });
    }

    /**
     * The proxy menu is being shown, we better get the real menu.
     * @param proxy the proxy menu
     */
    @objid ("d1d2ee64-2c77-4de7-bd63-c49fb0116dd4")
    private void handleShowProxy(Menu proxy) {
        proxy.removeListener(SWT.Show, getMenuCreatorListener());
        IMenuCreator mc = getMenuCreator();
        this.menuCreatorCalled = true;
        if (mc == null) {
            return;
        }
        this.holdMenu = mc.getMenu(proxy.getParentMenu());
        if (this.holdMenu == null) {
            return;
        }
        copyMenu(this.holdMenu, proxy);
    }

    /**
     * Handles a widget dispose event for the widget corresponding to this item.
     */
    @objid ("c2aa5e90-b6ad-40cd-81de-c12f8ed46286")
    private void handleWidgetDispose(Event e) {
        // Check if our widget is the one being disposed.
        if (e.widget == this.widget) {
            // Dispose of the menu creator.
            if (getStyle() == Style.AS_DROP_DOWN_MENU
                    && this.menuCreatorCalled) {
                IMenuCreator mc = getMenuCreator();
                if (mc != null) {
                    mc.dispose();
                }
            }
        
            // Clear the widget field.
            this.widget = null;
        
            disposeOldImages();
        }
    }

    /**
     * Handles a widget selection event.
     */
    @objid ("375c7eb6-5ca3-4a9e-acce-8f8dab026d59")
    private void handleWidgetSelection(Event e, boolean selection) {
        Widget item = e.widget;
        if (item != null) {
            int itemStyle = item.getStyle();
        
            if ((itemStyle & (SWT.TOGGLE | SWT.CHECK)) != 0) {
                if (getStyle() == Style.AS_CHECK_BOX) {
                    setChecked(selection);
                }
            } else if ((itemStyle & SWT.RADIO) != 0) {
                if (getStyle() == Style.AS_RADIO_BUTTON) {
                    setChecked(selection);
                }
            } else if ((itemStyle & SWT.DROP_DOWN) != 0) {
                if (e.detail == 4) { // on drop-down button
                    if (getStyle() == Style.AS_DROP_DOWN_MENU) {
                        IMenuCreator mc = getMenuCreator();
                        this.menuCreatorCalled = true;
                        ToolItem ti = (ToolItem) item;
                        // we create the menu as a sub-menu of "dummy" so that
                        // we can use
                        // it in a cascading menu too.
                        // If created on a SWT control we would get an SWT error...
                        // Menu dummy= new Menu(ti.getParent());
                        // Menu m= mc.getMenu(dummy);
                        // dummy.dispose();
                        if (mc != null) {
                            Menu m = mc.getMenu(ti.getParent());
                            if (m != null) {
                                // position the menu below the drop down item
                                Point point = ti.getParent().toDisplay(
                                        new Point(e.x, e.y));
                                m.setLocation(point.x, point.y);
                                m.setVisible(true);
                                return; // we don't fire the action
                            }
                        }
                    }
                }
            }
        
            // Ensure action is enabled first.
            if (isEnabled()) {
                boolean trace = Policy.TRACE_ACTIONS;
        
                long ms = 0L;
                if (trace) {
                    ms = System.currentTimeMillis();
                    System.out.println("Running action: " + getText()); //$NON-NLS-1$
                }
        
                this.action.run();
        
                if (trace) {
                    System.out.println((System.currentTimeMillis() - ms)
                            + " ms to run action: " + getText()); //$NON-NLS-1$
                }
            }
        }
    }

    /**
     * Returns whether the given action has any images.
     * @param actionToCheck
     * the action
     * @return <code>true</code> if the action has any images,
     * <code>false</code> if not
     */
    @objid ("6e85fbc8-358e-409b-90c7-dc2ea5554763")
    private boolean hasImages() {
        return getImageDescriptor() != null
                                || getHoverImageDescriptor() != null
                                || getDisabledImageDescriptor() != null;
    }

    /**
     * Updates the images for this action.
     * @param forceImage <code>true</code> if some form of image is compulsory, and
     * <code>false</code> if it is acceptable for this item to have
     * no image
     * @return <code>true</code> if there are images for this action,
     * <code>false</code> if not
     */
    @objid ("524ed4dc-595c-4c0a-b9df-14f8b3d62a8f")
    private boolean updateImages(boolean forceImage) {
        ResourceManager parentResourceManager = JFaceResources.getResources();
        
        if (this.widget instanceof ToolItem) {
            if (SwtContributionItem.USE_COLOR_ICONS) {
                ImageDescriptor image = getHoverImageDescriptor();
                if (image == null) {
                    image = getImageDescriptor();
                }
                ImageDescriptor disabledImage = getDisabledImageDescriptor();
        
                // Make sure there is a valid image.
                if (image == null && forceImage) {
                    image = ImageDescriptor.getMissingImageDescriptor();
                }
        
                LocalResourceManager localManager = new LocalResourceManager(
                        parentResourceManager);
        
                // performance: more efficient in SWT to set disabled and hot
                // image before regular image
                ((ToolItem) this.widget)
                        .setDisabledImage(disabledImage == null ? null
                                : localManager
                                        .createImageWithDefault(disabledImage));
                ((ToolItem) this.widget).setImage(image == null ? null
                        : localManager.createImageWithDefault(image));
        
                disposeOldImages();
                this.imageManager = localManager;
        
                return image != null;
            }
            ImageDescriptor image = getImageDescriptor();
            ImageDescriptor hoverImage = getHoverImageDescriptor();
            ImageDescriptor disabledImage = getDisabledImageDescriptor();
        
            // If there is no regular image, but there is a hover image,
            // convert the hover image to gray and use it as the regular image.
            if (image == null && hoverImage != null) {
                image = ImageDescriptor.createWithFlags(getHoverImageDescriptor(), SWT.IMAGE_GRAY);
            } else {
                // If there is no hover image, use the regular image as the
                // hover image,
                // and convert the regular image to gray
                if (hoverImage == null && image != null) {
                    hoverImage = image;
                    image = ImageDescriptor.createWithFlags(getImageDescriptor(), SWT.IMAGE_GRAY);
                }
            }
        
            // Make sure there is a valid image.
            if (hoverImage == null && image == null && forceImage) {
                image = ImageDescriptor.getMissingImageDescriptor();
            }
        
            // Create a local resource manager to remember the images we've
            // allocated for this tool item
            LocalResourceManager localManager = new LocalResourceManager(
                    parentResourceManager);
        
            // performance: more efficient in SWT to set disabled and hot image
            // before regular image
            ((ToolItem) this.widget).setDisabledImage(disabledImage == null ? null
                    : localManager.createImageWithDefault(disabledImage));
            ((ToolItem) this.widget).setHotImage(hoverImage == null ? null
                    : localManager.createImageWithDefault(hoverImage));
            ((ToolItem) this.widget).setImage(image == null ? null : localManager
                    .createImageWithDefault(image));
        
            // Now that we're no longer referencing the old images, clear them
            // out.
            disposeOldImages();
            this.imageManager = localManager;
        
            return image != null;
        } else if (this.widget instanceof Item || this.widget instanceof Button) {
        
            // Use hover image if there is one, otherwise use regular image.
            ImageDescriptor image = getHoverImageDescriptor();
            if (image == null) {
                image = getImageDescriptor();
            }
            // Make sure there is a valid image.
            if (image == null && forceImage) {
                image = ImageDescriptor.getMissingImageDescriptor();
            }
        
            // Create a local resource manager to remember the images we've
            // allocated for this widget
            LocalResourceManager localManager = new LocalResourceManager(
                    parentResourceManager);
        
            if (this.widget instanceof Item) {
                ((Item) this.widget).setImage(image == null ? null : localManager
                        .createImageWithDefault(image));
            } else if (this.widget instanceof Button) {
                ((Button) this.widget).setImage(image == null ? null : localManager
                        .createImageWithDefault(image));
            }
        
            // Now that we're no longer referencing the old images, clear them
            // out.
            disposeOldImages();
            this.imageManager = localManager;
        
            return image != null;
        }
        return false;
    }

    @objid ("2018552c-88f0-4985-b1a0-3e4603df9aee")
    @Override
    public void setVisible(boolean visible) {
        if (visible != isVisible()) {
            super.setVisible(visible);
        
            if (getParent() != null) {
                getParent().markDirty();
            }
        }
    }

    @objid ("489ace3b-c1c6-4ac2-9e5e-c9975e15a0dd")
    public enum Style {
        /**
         * Action style constant (value <code>2</code>) indicating action is
         * a check box (or a toggle button).
         * <p>
         * <strong>Note:</strong> The action is also run when a check box gets
         * deselected. Use {@link #isChecked} to determine the selection state.
         * </p>
         */
        AS_CHECK_BOX,
        /**
         * Action style constant (value <code>8</code>) indicating action is
         * a radio button.
         * <p>
         * <strong>Note:</strong> When a radio button gets selected, the action for
         * the unselected radio button will also be run. Use {@link #isChecked} to
         * determine the selection state.
         * </p>
         */
        AS_RADIO_BUTTON,
        /**
         * Action style constant (value <code>4</code>) indicating action is
         * a drop down menu.
         */
        AS_DROP_DOWN_MENU,
        /**
         * Action style constant (value <code>1</code>) indicating action is
         * a simple push button.
         */
        AS_PUSH_BUTTON;
    }

}

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
package org.modelio.platform.ui.dialog;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAttributeAdapter;
import org.eclipse.swt.accessibility.AccessibleAttributeEvent;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.platform.rcp.system.ModelioHelpSystem;
import org.modelio.platform.ui.plugin.UI;

/**
 * Dialog template that has :
 * <ul>
 * <li>a header with :
 * <ul>
 * <li>a left image, modifiable with {@link #setTitleLeftImage(Image)}
 * <li>a bold title, set with {@link #setTitle(String)}
 * <li>a logo image displayed on the right side, set with {@link #setLogoImage(Image)}
 * <li>a message, set with {@link #setMessage(String)}.
 * </ul>
 * <li>a user defined content area defined with {@link #createContentArea(Composite)}.
 * <li>a button area with user defined buttons defined with {@link #addButtonsInButtonBar(Composite)}.
 * <li>An optional message area that display on top of the header area, showing a warning or error small icon and a message.<br>
 * See {@link #setWarningMessage(String)} and {@link #setErrorMessage(String)}.
 * </ul>
 * <p>
 * Subclasses typical implementation:
 * 
 * <pre>
 * public class ExampleDialog extends ModelioDialog {
 * protected ExampleDialog(Shell parentShell) {
 * super(parentShell);
 * setBlockOnOpen(false);
 * }
 * 
 * &#64;Override
 * public Control createContentArea(Composite parent) {
 * Composite composite = new Composite(parent, SWT.NONE);
 * composite.setLayoutData(new GridData(GridData.FILL_BOTH));
 * 
 * composite.setLayout(new FillLayout());
 * // add controls to composite as necessary
 * Label label = new Label(composite, SWT.NONE);
 * label.setText("Dialog content is here");
 * 
 * return composite;
 * }
 * 
 * &#64;Override
 * protected void addButtonsInButtonBar(Composite parent) {
 * addDefaultButtons(parent);
 * }
 * 
 * &#64;Override
 * public void init() {
 * setTitle("ExampleDialog");
 * setMessage("A very simple dialog");
 * }
 * 
 * &#64;Override
 * protected Point getInitialSize() {
 * return new Point(150, 150);
 * }
 * 
 * &#64;Override
 * protected void configureShell(Shell newShell) {
 * super.configureShell(newShell);
 * setTitle("Shell title");
 * }
 * }
 * </pre>
 * </p>
 */
@objid ("0049a19c-3b99-1fcf-9f44-001ec947cd2a")
public abstract class ModelioDialog extends TrayDialog {
    /**
     * Image registry key for banner image (value
     * <code>"dialog_title_banner_image"</code>).
     */
    @objid ("004de4e6-4a05-1fe0-bf4c-001ec947cd2a")
    public static final String DLG_IMG_TITLE_BANNER = "dialog_title_banner_image"; // $NON-NLS-1$
    

    /**
     * Minimum dialog height (in dialog units)
     */
    @objid ("0046819c-4a05-1fe0-bf4c-001ec947cd2a")
    private static final int MIN_DIALOG_HEIGHT = 250;

    /**
     * Minimum dialog width (in dialog units)
     */
    @objid ("004693c6-4a05-1fe0-bf4c-001ec947cd2a")
    private static final int MIN_DIALOG_WIDTH = 350;

    @objid ("004e58ae-4a05-1fe0-bf4c-001ec947cd2a")
    private String errorMessage;

    @objid ("004ea548-4a05-1fe0-bf4c-001ec947cd2a")
    private String message = ""; // $NON-NLS-1$
    

    @objid ("00473c90-4a05-1fe0-bf4c-001ec947cd2a")
    private int messageLabelHeight;

    @objid ("00474564-4a05-1fe0-bf4c-001ec947cd2a")
    private boolean showingError = false;

    @objid ("004783f8-4a05-1fe0-bf4c-001ec947cd2a")
    private boolean leftImageLargest = false;

    /**
     * Image registry key for error message image.
     */
    @objid ("c727c9af-0685-4800-8096-e1bdce5b24fc")
    public static final String DLG_IMG_TITLE_ERROR = DLG_IMG_MESSAGE_ERROR;

    /**
     * Space between an image and a label
     */
    @objid ("2bd1a0d3-8897-4008-954a-f70483134fc8")
    private static final int H_GAP_IMAGE = 5;

    @objid ("cbf0541e-d307-4eb1-9ca4-bfb33794602f")
    private int xTrim;

    @objid ("4e6bf40d-6f9d-4ab0-a75b-257706931eab")
    private int yTrim;

    @objid ("2cbd3e0e-eaf2-47fb-a31f-520af39a057e")
    private boolean rightImageLargest = false;

    @objid ("5290f926-0d47-480f-b763-3b8cd969c317")
    private Label leftFillerLabel;

    @objid ("fa3d4890-f5cc-412f-a1a2-c5618a7ab847")
    private Image messageImage;

    @objid ("1a7c6969-386d-49cd-8f7e-f00a287b966e")
    private Label messageImageLabel;

    @objid ("61770565-f3d6-4888-8f0e-16c0e41110c3")
    private Text messageLabel;

    @objid ("d030a09f-d670-41db-9871-645891e28768")
    Color titleAreaColor;

    @objid ("419bfe7c-f022-46ea-bcdc-ebb9a0f8e188")
    private RGB titleAreaRGB;

    @objid ("051ae957-997b-4c14-a783-de5c09aa5e2c")
    private Label titleLabel;

    @objid ("2d1204df-a4b5-42a9-bfa4-26d53466d913")
    private Image titleRightImage;

    @objid ("bcb21365-637b-4f1c-80c6-cc3d2b81e210")
    private Label titleRightImageLabel;

    @objid ("bd11aa5b-3a55-4172-9719-312709ec0127")
    private Composite workArea;

    @objid ("a13dbf3f-9dff-4909-81c3-c2adee13390c")
    protected static final Image LEFT_IMAGE = UI.getImageDescriptor("images/headerleft110x50.png").createImage();

    @objid ("461a4ed2-fb57-4f77-a0e4-9c9b9b84b8e7")
    private Label bottomFillerLabel;

    @objid ("67cd6f95-6cc5-44ed-8d81-1834983467ce")
    private Image titleLeftImage;

    @objid ("1caecf42-eec5-4e3b-a172-b6e6dacabdac")
    private Label titleLeftImageLabel;

    @objid ("004b855c-4a05-1fe0-bf4c-001ec947cd2a")
    @Override
    public void setBlockOnOpen(final boolean shouldBlock) {
        if (!shouldBlock) {
            // Hack the shell style so that the dialog is not modal.
            int style = getShellStyle();
            style &= ~(SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL);
            setShellStyle(style);
        
        }
        super.setBlockOnOpen(shouldBlock);
        
    }

    /**
     * Instantiate a new modelio title dialog.
     * <p>
     * For non modal dialogs, implementers should call <code>setBlockOnOpen(false)</code>
     * </p>
     * @param parentShell the parent SWT shell
     */
    @objid ("00481d40-4a05-1fe0-bf4c-001ec947cd2a")
    protected  ModelioDialog(final Shell parentShell) {
        super(parentShell);
        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER | SWT.APPLICATION_MODAL | getDefaultOrientation());
        setBlockOnOpen(true);
        
    }

    /**
     * Adds buttons to this dialog's button bar.
     * <p>
     * For default ok and cancel buttons, please call the {@link #addDefaultButtons(Composite)} methods.
     * </p>
     * <p>
     * Subclasses may override this method as in the following example:
     * </p>
     * 
     * <pre>
     * createButton(parent, IDialogConstants.YES_ID, IDialogConstants.YES_LABEL, false);
     * createButton(parent, IDialogConstants.NO_ID, IDialogConstants.NO_LABEL, false);
     * createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
     * </pre>
     * <p>
     * Note: The common button order is: <b>{other buttons}</b>, <b>OK</b>, <b>Cancel</b>. On some platforms, {@link #initializeBounds()} will move the default button to the right.
     * </p>
     * @see #addDefaultButtons(Composite)
     * @param parent the button bar composite
     */
    @objid ("dda6960a-3144-4e0a-929f-13c7dfbb7f38")
    protected abstract void addButtonsInButtonBar(Composite parent);

    /**
     * This method adds standard ok and cancel buttons using the <code>createButton</code> framework method. These standard buttons will be accessible from <code>getCancelButton</code>, and <code>getOKButton</code>.
     * <p>
     * Note: The common button order is: <b>{other buttons}</b>, <b>OK</b>, <b>Cancel</b>. On some platforms, {@link #initializeBounds()} will move the default button to the right.
     * </p>
     * @param parent the button bar composite
     */
    @objid ("00484342-4a05-1fe0-bf4c-001ec947cd2a")
    protected void addDefaultButtons(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        
    }

    @objid ("0048619c-4a05-1fe0-bf4c-001ec947cd2a")
    @Override
    protected final void createButtonsForButtonBar(final Composite parent) {
        addButtonsInButtonBar(parent);
    }

    /**
     * Creates and returns the contents of the dialog area (between the title bar and the button bar).
     * <p>
     * The returned control's layout data must be an instance of <code>GridData</code>. This method must not modify the parent's layout.
     * </p>
     * <p>
     * Subclasses must override this method as in the following example:
     * </p>
     * 
     * <pre>
     * Composite composite = new Composite(parent, SWT.NONE);
     * composite.setLayoutData(new GridData(GridData.FILL_BOTH));
     * // add controls to composite as necessary
     * return composite;
     * </pre>
     * @param parent the parent composite to contain the dialog content area
     * @return the dialog content area control
     */
    @objid ("b97df5be-e664-4b0f-8602-e16eace9f475")
    protected abstract Control createContentArea(Composite parent);

    @objid ("00488a28-4a05-1fe0-bf4c-001ec947cd2a")
    @Override
    protected Control createContents(final Composite parent) {
        // create the overall composite
        final Composite contents = new Composite(parent, SWT.NONE);
        contents.setLayoutData(new GridData(GridData.FILL_BOTH));
        // initialize the dialog units
        initializeDialogUnits(contents);
        final FormLayout layout = new FormLayout();
        contents.setLayout(layout);
        // Now create a work area for the rest of the dialog
        this.workArea = new Composite(contents, SWT.NONE);
        final GridLayout childLayout = new GridLayout();
        childLayout.marginHeight = 0;
        childLayout.marginWidth = 0;
        childLayout.verticalSpacing = 0;
        this.workArea.setLayout(childLayout);
        final Control top = createTitleArea(contents);
        resetWorkAreaAttachments(top);
        this.workArea.setFont(JFaceResources.getDialogFont());
        // initialize the dialog units
        initializeDialogUnits(this.workArea);
        // create the dialog area and button bar
        this.dialogArea = createDialogArea(this.workArea);
        this.buttonBar = createButtonBar(this.workArea);
        
        init();
        
        // computing trim for later
        final Rectangle rect = this.messageLabel.computeTrim(0, 0, 100, 100);
        this.xTrim = rect.width - 100;
        this.yTrim = rect.height - 100;
        
        // need to react to new size of title area
        getShell().addListener(SWT.Resize, event -> layoutForNewMessage(true));
        return contents;
    }

    /**
     * Creates and returns the contents of the upper part of this dialog (above
     * the button bar).
     * <p>
     * The <code>Dialog</code> implementation of this framework method creates
     * and returns a new <code>Composite</code> with no margins and spacing.
     * Subclasses should override.
     * </p>
     * @param parent The parent composite to contain the dialog area
     * @return the dialog area control
     */
    @objid ("0048cc54-4a05-1fe0-bf4c-001ec947cd2a")
    @Override
    protected Control createDialogArea(final Composite parent) {
        // create the top level composite for the dialog area
        final Composite composite = new Composite(parent, SWT.NONE);
        final GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = 0;
        layout.horizontalSpacing = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parent.getFont());
        // Build the separator line
        final Label titleBarSeparator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
        titleBarSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        initHelpButton(getShell());
        
        createContentArea(composite);
        return composite;
    }

    @objid ("004b659a-4a05-1fe0-bf4c-001ec947cd2a")
    protected String getHelpId() {
        return null;
    }

    /**
     * The <code>ModelioDialog</code> implementation of this
     * <code>Window</code> methods returns an initial size which is at least
     * some reasonable minimum.
     * @return the initial size of the dialog
     */
    @objid ("00490dcc-4a05-1fe0-bf4c-001ec947cd2a")
    @Override
    protected Point getInitialSize() {
        final Point shellSize = super.getInitialSize();
        return new Point(Math.max(convertHorizontalDLUsToPixels(ModelioDialog.MIN_DIALOG_WIDTH), shellSize.x),
                Math.max(convertVerticalDLUsToPixels(ModelioDialog.MIN_DIALOG_HEIGHT), shellSize.y));
        
    }

    /**
     * Called at the end of the dialog's content instantiation.
     * <p>
     * Implementers should complete the dialog's initialization right here, for example setting a title, a message or a logo.
     * </p>
     */
    @objid ("8707e86b-ea1a-4bac-ade9-c291f890490f")
    protected abstract void init();

    /**
     * Display the given error message. The currently displayed message is saved
     * and will be redisplayed when the error message is set to
     * <code>null</code>.
     * @param newErrorMessage the newErrorMessage to display or <code>null</code>
     */
    @objid ("00493ef0-4a05-1fe0-bf4c-001ec947cd2a")
    protected void setErrorMessage(final String newErrorMessage) {
        // Any change?
        if (this.errorMessage == null ? newErrorMessage == null : this.errorMessage.equals(newErrorMessage)) {
            return;
        }
        this.errorMessage = newErrorMessage;
        
        // Clear or set error message.
        if (this.errorMessage == null) {
            if (this.showingError) {
                // we were previously showing an error
                this.showingError = false;
            }
            // show the message
            // avoid calling setMessage in case it is overridden to call
            // setErrorMessage,
            // which would result in a recursive infinite loop
            if (this.message == null) {
                // setMessage does this conversion....
                this.message = ""; //$NON-NLS-1$
            }
            updateMessage(this.message);
            this.messageImageLabel.setImage(this.messageImage);
            setImageLabelVisible(this.messageImage != null);
        } else {
            // Add in a space for layout purposes but do not
            // change the instance variable
            final String displayedErrorMessage = " " + this.errorMessage; //$NON-NLS-1$
            updateMessage(displayedErrorMessage);
            if (!this.showingError) {
                // we were not previously showing an error
                this.showingError = true;
                this.messageImageLabel.setImage(JFaceResources
                        .getImage(DLG_IMG_TITLE_ERROR));
                setImageLabelVisible(true);
            }
        }
        layoutForNewMessage(false);
        
    }

    /**
     * Set the logo image that displays on the right side of the header.
     * @param newLogoImage The new logo image.
     */
    @objid ("00495c46-4a05-1fe0-bf4c-001ec947cd2a")
    protected void setLogoImage(final Image newLogoImage) {
        this.titleRightImage = newLogoImage;
        if (this.titleRightImageLabel != null && !this.titleRightImageLabel.isDisposed()) {
            this.titleRightImageLabel.setImage(newLogoImage);
            determineTitleImageLargest();
            Control top;
            if (this.rightImageLargest) {
                top = this.titleRightImageLabel;
            } else if (this.leftImageLargest) {
                top = this.titleLeftImageLabel;
            } else {
                top = this.messageLabel;
            }
            resetWorkAreaAttachments(top);
        
            if (this.dialogArea != null) {
                this.workArea.getParent().layout(true);
            }
        }
        
    }

    /**
     * Set the message text. If the message line currently displays an error,
     * the message is saved and will be redisplayed when the error message is
     * set to <code>null</code>.
     * <p>
     * Shortcut for <code>setMessage(newMessage, IMessageProvider.NONE)</code>
     * </p>
     * This method should be called after the dialog has been opened as it
     * updates the message label immediately.
     * @param newMessage the message, or <code>null</code> to clear the message
     */
    @objid ("004985c2-4a05-1fe0-bf4c-001ec947cd2a")
    protected void setMessage(final String newMessage) {
        setMessage(newMessage, IMessageProvider.NONE);
    }

    /**
     * Sets the message for this dialog with an indication of what type of
     * message it is.
     * <p>
     * The valid message types are one of <code>NONE</code>,
     * <code>INFORMATION</code>,<code>WARNING</code>, or
     * <code>ERROR</code>.
     * </p>
     * <p>
     * Note that for backward compatibility, a message of type
     * <code>ERROR</code> is different than an error message (set using
     * <code>setErrorMessage</code>). An error message overrides the current
     * message until the error message is cleared. This method replaces the
     * current message and does not affect the error message.
     * </p>
     * @param newMessage the message, or <code>null</code> to clear the message
     * @param newType the message type
     * @since 2.0
     */
    @objid ("004abae6-4a05-1fe0-bf4c-001ec947cd2a")
    protected void setMessage(final String newMessage, final int newType) {
        Image newImage = null;
        if (newMessage != null) {
            switch (newType) {
            case IMessageProvider.NONE:
                break;
            case IMessageProvider.INFORMATION:
                newImage = JFaceResources.getImage(DLG_IMG_MESSAGE_INFO);
                break;
            case IMessageProvider.WARNING:
                newImage = JFaceResources.getImage(DLG_IMG_MESSAGE_WARNING);
                break;
            case IMessageProvider.ERROR:
                newImage = JFaceResources.getImage(DLG_IMG_MESSAGE_ERROR);
                break;
            default:
                break;
            }
        }
        showMessage(newMessage, newImage);
        
    }

    /**
     * Sets the title to be shown in the title area of this dialog.
     * @param newTitle the title show
     */
    @objid ("0049a3a4-4a05-1fe0-bf4c-001ec947cd2a")
    protected void setTitle(final String newTitle) {
        if (this.titleLabel == null) {
            return;
        }
        String title = newTitle;
        if (title == null) {
            title = "";//$NON-NLS-1$
        }
        this.titleLabel.setText(title);
        
    }

    /**
     * Sets the title image to be shown in the title area of this dialog.
     * <p>
     * The image is aligned on the left of the header.
     * </p>
     * @param newTitleImage the title image to be shown
     */
    @objid ("0049c1b8-4a05-1fe0-bf4c-001ec947cd2a")
    protected void setTitleLeftImage(final Image newTitleImage) {
        this.titleLeftImage = newTitleImage;
        if (this.titleLeftImageLabel != null) {
            this.titleLeftImageLabel.setImage(newTitleImage);
            determineTitleImageLargest();
            Control top;
            if (this.rightImageLargest) {
                top = this.titleRightImageLabel;
            } else if (this.leftImageLargest) {
                top = this.titleLeftImageLabel;
            } else {
                top = this.messageLabel;
            }
            resetWorkAreaAttachments(top);
        }
        
    }

    @objid ("0049ec10-4a05-1fe0-bf4c-001ec947cd2a")
    protected void setWarningMessage(final String newWarningMessage) {
        setMessage(newWarningMessage, IMessageProvider.WARNING);
    }

    /**
     * Creates the dialog's title area.
     * @param parent the SWT parent for the title area widgets
     * @return Control with the highest x axis value.
     */
    @objid ("004a0aba-4a05-1fe0-bf4c-001ec947cd2a")
    private Control createTitleArea(final Composite parent) {
        // add a dispose listener
        parent.addDisposeListener(e -> {
            if (this.titleAreaColor != null) {
                this.titleAreaColor.dispose();
            }
        });
        // Determine the background color of the title bar
        final Display display = parent.getDisplay();
        Color background;
        Color foreground;
        if (this.titleAreaRGB != null) {
            this.titleAreaColor = new Color(display, this.titleAreaRGB);
            background = this.titleAreaColor;
            foreground = null;
        } else {
            background = JFaceColors.getBannerBackground(display);
            foreground = JFaceColors.getBannerForeground(display);
        }
        
        parent.setBackground(background);
        
        final int verticalSpacing = 0; //convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        final int horizontalSpacing = 0; //convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        
        // Dialog image @ left
        this.titleLeftImageLabel = new Label(parent, SWT.CENTER);
        this.titleLeftImageLabel.setBackground(background);
        if (this.titleLeftImage == null) {
            this.titleLeftImageLabel.setImage(LEFT_IMAGE);
        } else {
            this.titleLeftImageLabel.setImage(this.titleLeftImage);
        }
        
        final FormData imageData = new FormData();
        imageData.top = new FormAttachment(0, 0);
        imageData.left = new FormAttachment(0, 0); // horizontalSpacing
        this.titleLeftImageLabel.setLayoutData(imageData);
        
        // Dialog image @ right
        this.titleRightImageLabel = new Label(parent, SWT.CENTER);
        this.titleRightImageLabel.setBackground(background);
        if (this.titleRightImage == null) {
            this.titleRightImageLabel.setImage(null);
        } else {
            this.titleRightImageLabel.setImage(this.titleRightImage);
        }
        
        final FormData imageData2 = new FormData();
        imageData2.top = new FormAttachment(0, 0);
        imageData2.right = new FormAttachment(100, 0); // horizontalSpacing
        this.titleRightImageLabel.setLayoutData(imageData2);
        
        // Title label @ top, left
        this.titleLabel = new Label(parent, SWT.LEFT);
        JFaceColors.setColors(this.titleLabel, foreground, background);
        this.titleLabel.setFont(JFaceResources.getBannerFont());
        this.titleLabel.setText(" ");//$NON-NLS-1$
        
        final FormData titleData = new FormData();
        titleData.top = new FormAttachment(0, verticalSpacing);
        titleData.right = new FormAttachment(this.titleRightImageLabel);
        titleData.left = new FormAttachment(this.titleLeftImageLabel, 0);
        this.titleLabel.setLayoutData(titleData);
        
        // Message image @ bottom, left
        this.messageImageLabel = new Label(parent, SWT.CENTER);
        this.messageImageLabel.setBackground(background);
        
        // Message label @ bottom, center
        this.messageLabel = new Text(parent, SWT.WRAP | SWT.READ_ONLY);
        JFaceColors.setColors(this.messageLabel, foreground, background);
        this.messageLabel.setText(" \n "); // two lines//$NON-NLS-1$
        this.messageLabel.setFont(JFaceResources.getDialogFont());
        // Bug 248410 -  This snippet will only work with Windows screen readers.
        this.messageLabel.getAccessible().addAccessibleAttributeListener(
                new AccessibleAttributeAdapter() {
                    @Override
                    public void getAttributes(final AccessibleAttributeEvent e) {
                        e.attributes = new String[] { "container-live", //$NON-NLS-1$
                                "polite", "live", "polite",   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
                                "container-live-role", "status", }; //$NON-NLS-1$ //$NON-NLS-2$
                    }
                });
        this.messageLabelHeight = this.messageLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
        
        // Filler labels
        this.leftFillerLabel = new Label(parent, SWT.CENTER);
        this.leftFillerLabel.setBackground(background);
        this.bottomFillerLabel = new Label(parent, SWT.CENTER);
        this.bottomFillerLabel.setBackground(background);
        setLayoutsForNormalMessage(verticalSpacing, horizontalSpacing);
        determineTitleImageLargest();
        if (this.rightImageLargest) {
            return this.titleRightImageLabel;
        } else if (this.leftImageLargest) {
            return this.titleLeftImageLabel;
        } else {
            return this.messageLabel;
        }
        
    }

    /**
     * Determine if the title image is larger than the title message and message
     * area. This is used for layout decisions.
     */
    @objid ("004a43ea-4a05-1fe0-bf4c-001ec947cd2a")
    private void determineTitleImageLargest() {
        final int leftTitleY = this.titleLeftImageLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
        final int rightTitleY = this.titleRightImageLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
        
        final int verticalSpacing = 0;
        int labelY = this.titleLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
        labelY += verticalSpacing;
        labelY += this.messageLabelHeight;
        labelY += verticalSpacing;
        if (leftTitleY > labelY && leftTitleY >= rightTitleY) {
            this.leftImageLargest = true;
            this.rightImageLargest = false;
        } else if (rightTitleY > labelY && rightTitleY > leftTitleY) {
            this.leftImageLargest = false;
            this.rightImageLargest = true;
        } else {
            this.leftImageLargest = false;
            this.rightImageLargest = false;
        }
        
    }

    @objid ("004b418c-4a05-1fe0-bf4c-001ec947cd2a")
    private void initHelpButton(final Control control) {
        final String helpId = getHelpId();
        
        if (helpId != null && !helpId.isEmpty()) {
            setHelpAvailable(true);
            control.addHelpListener(new HelpListener() {
                @Override
                public void helpRequested(final HelpEvent e) {
                    final String hId = getHelpId();
                    if (hId != null && !hId.isEmpty()) {
                        ModelioHelpSystem.getInstance().displayHelpResource(hId);
                    }
                }
            });
        }
        
    }

    /**
     * Re-layout the labels for the new message.
     * @param forceLayout <code>true</code> to force a layout of the shell
     */
    @objid ("004a5506-4a05-1fe0-bf4c-001ec947cd2a")
    private void layoutForNewMessage(final boolean forceLayout) {
        final int verticalSpacing = 0; //convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        final int horizontalSpacing = 0; //convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        
        // If there are no images then layout as normal
        if (this.errorMessage == null && this.messageImage == null) {
            setImageLabelVisible(false);
            setLayoutsForNormalMessage(verticalSpacing, horizontalSpacing);
        } else {
            this.messageImageLabel.setVisible(true);
            this.bottomFillerLabel.setVisible(true);
            this.leftFillerLabel.setVisible(true);
            /**
             * Note that we do not use horizontalSpacing here as when the
             * background of the messages changes there will be gaps between the
             * icon label and the message that are the background color of the
             * shell. We add a leading space elsewhere to compendate for this.
             */
            FormData data = new FormData();
            data.left = new FormAttachment(this.titleLeftImageLabel, H_GAP_IMAGE);
            data.top = new FormAttachment(this.titleLabel, verticalSpacing);
            this.messageImageLabel.setLayoutData(data);
        
            data = new FormData();
            data.top = new FormAttachment(this.messageImageLabel, 0);
            data.left = new FormAttachment(this.titleLeftImageLabel, 0);
            data.bottom = new FormAttachment(this.messageLabel, 0, SWT.BOTTOM);
            data.right = new FormAttachment(this.messageImageLabel, 0, SWT.RIGHT);
            this.bottomFillerLabel.setLayoutData(data);
        
            data = new FormData();
            data.top = new FormAttachment(this.messageImageLabel, 0, SWT.TOP);
            data.left = new FormAttachment(this.titleLeftImageLabel, 0);
            data.bottom = new FormAttachment(this.messageImageLabel, 0, SWT.BOTTOM);
            data.right = new FormAttachment(this.messageImageLabel, 0);
            this.leftFillerLabel.setLayoutData(data);
        
            final FormData messageLabelData = new FormData();
            messageLabelData.top = new FormAttachment(this.titleLabel, verticalSpacing);
            messageLabelData.right = new FormAttachment(this.titleRightImageLabel);
            messageLabelData.left = new FormAttachment(this.messageImageLabel, 0);
            messageLabelData.height = this.messageLabelHeight;
            if (this.leftImageLargest) {
                messageLabelData.bottom = new FormAttachment(this.titleLeftImageLabel, 0, SWT.BOTTOM);
            } else if (this.rightImageLargest) {
                messageLabelData.bottom = new FormAttachment(this.titleRightImageLabel, 0, SWT.BOTTOM);
            }
            this.messageLabel.setLayoutData(messageLabelData);
        }
        
        if (forceLayout) {
            getShell().layout();
        } else {
            // Do not layout before the dialog area has been created
            // to avoid incomplete calculations.
            if (this.dialogArea != null) {
                this.workArea.getParent().layout(true);
            }
        }
        
        final int messageLabelUnclippedHeight = this.messageLabel.computeSize(this.messageLabel.getSize().x - this.xTrim, SWT.DEFAULT, true).y;
        final boolean messageLabelClipped = messageLabelUnclippedHeight > this.messageLabel.getSize().y - this.yTrim;
        if (this.messageLabel.getData() instanceof ToolTip) {
            final ToolTip toolTip = (ToolTip) this.messageLabel.getData();
            toolTip.hide();
            toolTip.deactivate();
            this.messageLabel.setData(null);
        }
        if (messageLabelClipped) {
            final ToolTip tooltip = new ToolTip(this.messageLabel, ToolTip.NO_RECREATE, false) {
        
                @Override
                protected Composite createToolTipContentArea(final Event event, final Composite parent) {
                    final Composite result = new Composite(parent, SWT.NONE);
                    result.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                    result.setLayout(new GridLayout());
                    final Text text = new Text(result, SWT.WRAP);
                    text.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                    text.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
                    text.setText(ModelioDialog.this.messageLabel.getText());
                    final GridData gridData = new GridData();
                    gridData.widthHint = ModelioDialog.this.messageLabel.getSize().x;
                    text.setLayoutData(gridData);
                    Dialog.applyDialogFont(result);
                    return result;
                }
                @Override
                public Point getLocation(final Point tipSize, final Event event) {
                    return ModelioDialog.this.messageLabel.getShell().toDisplay(ModelioDialog.this.messageLabel.getLocation());
                }
            };
            this.messageLabel.setData(tooltip);
            tooltip.setPopupDelay(0);
            tooltip.activate();
        }
        
    }

    /**
     * Reset the attachment of the workArea to now attach to top as the top
     * control.
     */
    @objid ("004a65dc-4a05-1fe0-bf4c-001ec947cd2a")
    private void resetWorkAreaAttachments(final Control top) {
        final FormData childData = new FormData();
        childData.top = new FormAttachment(top);
        childData.right = new FormAttachment(100, 0);
        childData.left = new FormAttachment(0, 0);
        childData.bottom = new FormAttachment(100, 0);
        this.workArea.setLayoutData(childData);
        
    }

    /**
     * Make the label used for displaying error images visible depending on
     * boolean.
     * @param visible If <code>true</code> make the image visible, if not then
     * make it not visible.
     */
    @objid ("004a881e-4a05-1fe0-bf4c-001ec947cd2a")
    private void setImageLabelVisible(final boolean visible) {
        this.messageImageLabel.setVisible(visible);
        this.bottomFillerLabel.setVisible(visible);
        this.leftFillerLabel.setVisible(visible);
        
    }

    /**
     * Set the layout values for the messageLabel, messageImageLabel and
     * fillerLabel for the case where there is a normal message.
     * @param verticalSpacing int The spacing between widgets on the vertical axis.
     * @param horizontalSpacing int The spacing between widgets on the horizontal axis.
     */
    @objid ("004a9e9e-4a05-1fe0-bf4c-001ec947cd2a")
    private void setLayoutsForNormalMessage(final int verticalSpacing, final int horizontalSpacing) {
        final FormData messageImageData = new FormData();
        messageImageData.top = new FormAttachment(this.titleLabel, verticalSpacing);
        messageImageData.left = new FormAttachment(this.titleLeftImageLabel, H_GAP_IMAGE);
        this.messageImageLabel.setLayoutData(messageImageData);
        
        final FormData messageLabelData = new FormData();
        messageLabelData.top = new FormAttachment(this.titleLabel, verticalSpacing);
        messageLabelData.right = new FormAttachment(this.titleRightImageLabel);
        messageLabelData.left = new FormAttachment(this.messageImageLabel,
                horizontalSpacing);
        messageLabelData.height = this.messageLabelHeight;
        if (this.leftImageLargest) {
            messageLabelData.bottom = new FormAttachment(this.titleLeftImageLabel, 0, SWT.BOTTOM);
        } else if (this.rightImageLargest) {
            messageLabelData.bottom = new FormAttachment(this.titleRightImageLabel, 0, SWT.BOTTOM);
        }
        this.messageLabel.setLayoutData(messageLabelData);
        
        final FormData fillerData = new FormData();
        fillerData.left = new FormAttachment(this.titleLeftImageLabel, horizontalSpacing);
        fillerData.top = new FormAttachment(this.messageImageLabel, 0);
        fillerData.bottom = new FormAttachment(this.messageLabel, 0, SWT.BOTTOM);
        this.bottomFillerLabel.setLayoutData(fillerData);
        
        final FormData data = new FormData();
        data.top = new FormAttachment(this.messageImageLabel, 0, SWT.TOP);
        data.left = new FormAttachment(this.titleLeftImageLabel, 0);
        data.bottom = new FormAttachment(this.messageImageLabel, 0, SWT.BOTTOM);
        data.right = new FormAttachment(this.messageImageLabel, 0);
        this.leftFillerLabel.setLayoutData(data);
        
    }

    /**
     * Show the new message and image.
     */
    @objid ("004b00aa-4a05-1fe0-bf4c-001ec947cd2a")
    private void showMessage(final String initialMessage, final Image newImage) {
        final String newMessage = initialMessage != null ? initialMessage : ""; //$NON-NLS-1$
        
        // Any change?
        if (this.message.equals(newMessage) && this.messageImage == newImage) {
            return;
        }
        this.message = newMessage;
        
        // Message string to be shown - if there is an image then add in
        // a space to the message for layout purposes
        final String shownMessage = newImage == null ? this.message : " " + this.message; //$NON-NLS-1$
        this.messageImage = newImage;
        if (!this.showingError) {
            // we are not showing an error
            updateMessage(shownMessage);
            this.messageImageLabel.setImage(this.messageImage);
            setImageLabelVisible(this.messageImage != null);
            layoutForNewMessage(false);
        }
        
    }

    /**
     * Update the contents of the messageLabel.
     * @param newMessage the message to use
     */
    @objid ("004b297c-4a05-1fe0-bf4c-001ec947cd2a")
    private void updateMessage(final String newMessage) {
        final String oldMessage = this.messageLabel.getText();
        this.messageLabel.setText(newMessage);
        // Bug 248410 -  This snippet will only work with Windows screen readers.
        this.messageLabel.getAccessible().sendEvent(ACC.EVENT_ATTRIBUTE_CHANGED,
                null);
        this.messageLabel.getAccessible().sendEvent(
                ACC.EVENT_TEXT_CHANGED,
                new Object[] { Integer.valueOf(ACC.TEXT_DELETE), Integer.valueOf(0),
                        Integer.valueOf(oldMessage.length()), oldMessage });
        this.messageLabel.getAccessible().sendEvent(
                ACC.EVENT_TEXT_CHANGED,
                new Object[] { Integer.valueOf(ACC.TEXT_INSERT), Integer.valueOf(0),
                        Integer.valueOf(newMessage.length()), newMessage });
        
    }

    /**
     * Sets the title bar color for this dialog.
     * @param color the title bar color
     */
    @objid ("fdcadf01-4072-4f52-93e5-40fb01b80db2")
    public void setTitleAreaColor(final RGB color) {
        this.titleAreaRGB = color;
    }

    /**
     * Returns the current error message being shown in the dialog, or
     * <code>null</code> if there is no error message being shown.
     * @see #setErrorMessage(String)
     * @see #setMessage(String)
     * 
     * @since 3.6
     * @return the error message, which may be <code>null</code>.
     */
    @objid ("b0572974-e5fa-4c33-8edd-b4e3686153ff")
    protected String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Returns the current message text for this dialog.  This message is
     * displayed in the message line of the dialog when the error message
     * is <code>null</code>.  If there is a non-null error message, this
     * message is not shown, but is stored so that it can be shown in
     * the message line whenever {@link #setErrorMessage(String)} is called with
     * a <code>null</code> parameter.
     * @see #setMessage(String)
     * @see #setErrorMessage(String)
     * 
     * @since 3.6
     * @return the message text, which is never <code>null</code>.
     */
    @objid ("0c2e195c-ec53-4177-9319-d1dccb352ac1")
    protected String getMessage() {
        return this.message;
    }

    /**
     * Returns the title image label.
     * @return the title image label
     */
    @objid ("39137d96-b99b-44ee-b4d2-d5b19ea892bd")
    protected Label getRightTitleImageLabel() {
        return this.titleRightImageLabel;
    }

}

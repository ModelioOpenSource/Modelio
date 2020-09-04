/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.ControlAnimator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ImageAndMessageArea;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.Policy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.core.rcp.system.ModelioHelpSystem;
import org.modelio.ui.plugin.UI;

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
@objid ("7c12d54a-704b-11dd-933a-001ec947cd2a")
public abstract class ModelioDialog extends TrayDialog {
    @objid ("7c1537cd-704b-11dd-933a-001ec947cd2a")
    public static final String DLG_IMG_TITLE_BANNER = "dialog_title_banner_image";

    @objid ("7c12d512-704b-11dd-933a-001ec947cd2a")
    private static final int MIN_DIALOG_HEIGHT = 250;

    @objid ("7c1537b3-704b-11dd-933a-001ec947cd2a")
    private static final int MIN_DIALOG_WIDTH = 350;

    @objid ("7c1537af-704b-11dd-933a-001ec947cd2a")
    private String errorMessage;

    @objid ("7c15377b-704b-11dd-933a-001ec947cd2a")
    private String message;

    @objid ("7c153774-704b-11dd-933a-001ec947cd2a")
    private int messageLabelHeight;

    @objid ("7c153775-704b-11dd-933a-001ec947cd2a")
    private boolean showingError;

    @objid ("7c153776-704b-11dd-933a-001ec947cd2a")
    private boolean showingWarning;

    @objid ("7c153777-704b-11dd-933a-001ec947cd2a")
    private boolean titleImageLargest;

    @objid ("7c15376e-704b-11dd-933a-001ec947cd2a")
    private String warningMessage;

    @objid ("bc28cf6f-120f-11e2-b5c6-002564c97630")
    private ControlAnimator animator;

    @objid ("bc28cf71-120f-11e2-b5c6-002564c97630")
    private Label leftFillerLabel;

    @objid ("bc28cf73-120f-11e2-b5c6-002564c97630")
    private ImageAndMessageArea messageArea;

    @objid ("bc28cf74-120f-11e2-b5c6-002564c97630")
    private Image messageImage;

    @objid ("bc28cf75-120f-11e2-b5c6-002564c97630")
    private Label messageImageLabel;

    @objid ("bc28cf76-120f-11e2-b5c6-002564c97630")
    private Text messageLabel;

    @objid ("bc28cf77-120f-11e2-b5c6-002564c97630")
    private Composite titleArea;

    @objid ("bc28cf6e-120f-11e2-b5c6-002564c97630")
     Color titleAreaColor;

    @objid ("bc28cf78-120f-11e2-b5c6-002564c97630")
    private RGB titleAreaRGB;

    @objid ("bc28cf79-120f-11e2-b5c6-002564c97630")
    private Label titleLabel;

    @objid ("bc28cf7a-120f-11e2-b5c6-002564c97630")
    private Image titleLeftImage;

    @objid ("bc28cf7b-120f-11e2-b5c6-002564c97630")
    private Label titleLeftImageLabel;

    @objid ("bc28cf7c-120f-11e2-b5c6-002564c97630")
    private Image titleRightImage;

    @objid ("bc2b30aa-120f-11e2-b5c6-002564c97630")
    private Label titleRightImageLabel;

    @objid ("bc2b30ac-120f-11e2-b5c6-002564c97630")
    private Composite workArea;

    @objid ("40aa19cc-08ea-11de-a5ef-001ec947ccaf")
    @Override
    public void setBlockOnOpen(boolean shouldBlock) {
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
    @objid ("bc2b30ad-120f-11e2-b5c6-002564c97630")
    protected ModelioDialog(Shell parentShell) {
        super(parentShell);
        this.message = "";
        this.showingError = false;
        this.showingWarning = false;
        this.titleImageLargest = true;
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
    @objid ("bbf20fad-120f-11e2-b5c6-002564c97630")
    protected abstract void addButtonsInButtonBar(Composite parent);

    /**
     * This method adds standard ok and cancel buttons using the <code>createButton</code> framework method. These standard buttons will be accessible from <code>getCancelButton</code>, and <code>getOKButton</code>.
     * <p>
     * Note: The common button order is: <b>{other buttons}</b>, <b>OK</b>, <b>Cancel</b>. On some platforms, {@link #initializeBounds()} will move the default button to the right.
     * </p>
     * @param parent the button bar composite
     */
    @objid ("bc2b30b0-120f-11e2-b5c6-002564c97630")
    protected void addDefaultButtons(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    @objid ("bc2b30b3-120f-11e2-b5c6-002564c97630")
    @Override
    protected final void createButtonsForButtonBar(Composite parent) {
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
    @objid ("bbf20faa-120f-11e2-b5c6-002564c97630")
    protected abstract Control createContentArea(Composite parent);

    @objid ("bc2b30b7-120f-11e2-b5c6-002564c97630")
    @Override
    protected Control createContents(Composite parent) {
        Composite contents = new Composite(parent, 0);
        contents.setLayoutData(new GridData(1808));
        initializeDialogUnits(contents);
        FormLayout layout = new FormLayout();
        contents.setLayout(layout);
        this.workArea = new Composite(contents, SWT.NONE);
        GridLayout childLayout = new GridLayout();
        childLayout.marginHeight = 0;
        childLayout.marginWidth = 0;
        childLayout.verticalSpacing = 0;
        this.workArea.setLayout(childLayout);
        Control top = createTitleArea(contents);
        resetWorkAreaAttachments(top);
        this.workArea.setFont(JFaceResources.getDialogFont());
        initializeDialogUnits(this.workArea);
        this.dialogArea = createDialogArea(this.workArea);
        this.buttonBar = createButtonBar(this.workArea);
        
        ImageDescriptor imageDescriptor = UI.getImageDescriptor("images/headerleft110x50.png");
        if (imageDescriptor != null) {
            Image image = imageDescriptor.createImage();
        
            setTitleLeftImage(image);
        }
        
        init();
        return contents;
    }

    @objid ("bc2b30bd-120f-11e2-b5c6-002564c97630")
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = new Composite(parent, 0);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = 0;
        layout.horizontalSpacing = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(1808));
        composite.setFont(parent.getFont());
        Label titleBarSeparator = new Label(composite, 258);
        
        initHelpButton(composite);
        
        createContentArea(composite);
        
        titleBarSeparator.setLayoutData(new GridData(768));
        return composite;
    }

    @objid ("a50829be-08d1-11de-a1e5-0014222a9f79")
    protected String getHelpId() {
        return null;
    }

    @objid ("bc2b30c3-120f-11e2-b5c6-002564c97630")
    @Override
    protected Point getInitialSize() {
        Point shellSize = super.getInitialSize();
        return new Point(Math.max(convertHorizontalDLUsToPixels(ModelioDialog.MIN_DIALOG_WIDTH), shellSize.x), Math.max(
                        convertVerticalDLUsToPixels(ModelioDialog.MIN_DIALOG_HEIGHT), shellSize.y));
    }

    /**
     * Called at the end of the dialog's content instantiation.
     * <p>
     * Implementers should complete the dialog's initialization right here, for example setting a title, a message or a logo.
     * </p>
     */
    @objid ("7c12d530-704b-11dd-933a-001ec947cd2a")
    protected abstract void init();

    /**
     * Set an error message that will display in the message area on top of the header of the dialog, with an error small icon.<br>
     * Removes the message if null is passed .
     * @param newErrorMessage The error message or null to remove the error message.
     */
    @objid ("7c15376f-704b-11dd-933a-001ec947cd2a")
    protected void setErrorMessage(String newErrorMessage) {
        if ((this.errorMessage != null) ? this.errorMessage.equals(newErrorMessage) : newErrorMessage == null) {
            return;
        }
        this.errorMessage = newErrorMessage;
        if (this.errorMessage == null) {
            if (this.messageArea != null && !this.showingWarning) {
                setMessageAreaVisible(false);
            }
            if (this.showingError) {
                this.showingError = false;
            }
            if (this.message == null) {
                this.message = "";
            }
            updateMessage(this.message);
            this.messageImageLabel.setImage(this.messageImage);
            setImageLabelVisible(this.messageImage != null);
            if (this.showingWarning) {
                setWarningMessage(this.warningMessage);
            }
        } else {
            if (!this.showingError) {
                this.showingError = true;
            }
            if (this.showingWarning) {
                setWarningMessage(null);
            }
            if (this.messageArea == null) {
                this.messageArea = new ImageAndMessageArea(this.titleArea, 64);
                this.messageArea.setBackground(this.messageLabel.getBackground());
                this.animator = Policy.getAnimatorFactory().createAnimator(this.messageArea);
            }
            this.messageArea.setToolTipText(this.errorMessage);
            this.messageArea.setText(this.errorMessage);
            this.messageArea.setImage(JFaceResources.getImage("dialog_message_error_image"));
            setMessageAreaVisible(true);
        }
        int verticalSpacing = convertVerticalDLUsToPixels(1);
        int horizontalSpacing = convertHorizontalDLUsToPixels(4);
        setLayoutsForNormalMessage(verticalSpacing, horizontalSpacing);
    }

    /**
     * Set the logo image that displays on the right side of the header.
     * @param newLogoImage The new logo image.
     */
    @objid ("bc2b30c8-120f-11e2-b5c6-002564c97630")
    protected void setLogoImage(Image newLogoImage) {
        this.titleRightImage = newLogoImage;
        if (this.titleRightImageLabel != null && !this.titleRightImageLabel.isDisposed()) {
            this.titleRightImageLabel.setImage(newLogoImage);
            this.titleRightImageLabel.setVisible(newLogoImage != null);
            if (newLogoImage != null) {
                resetWorkAreaAttachments(this.titleArea);
            }
        }
    }

    /**
     * Set a message that will display in the header of the dialog.
     * @param newMessage The message .
     */
    @objid ("7c153773-704b-11dd-933a-001ec947cd2a")
    protected void setMessage(String newMessage) {
        setMessage(newMessage, 0);
    }

    @objid ("7c153772-704b-11dd-933a-001ec947cd2a")
    protected void setMessage(String newMessage, int newType) {
        Image newImage = null;
        if (newMessage != null) {
            switch (newType) {
            case 1: // '\001'
                newImage = JFaceResources.getImage("dialog_messasge_info_image");
                break;
        
            case 2: // '\002'
                newImage = JFaceResources.getImage("dialog_messasge_warning_image");
                break;
        
            case 3: // '\003'
                newImage = JFaceResources.getImage("dialog_message_error_image");
                break;
            default:
                break;
            }
        }
        if (newType == 2) {
            setWarningMessage(newMessage);
        } else {
            setWarningMessage(null);
            showMessage(newMessage, newImage);
        }
    }

    /**
     * Set the title that displays in bold in the dialog header. The title is different from the dialog tray title.
     * @param newTitle The header title.
     */
    @objid ("7c12d52a-704b-11dd-933a-001ec947cd2a")
    protected void setTitle(String newTitle) {
        if (this.titleLabel == null) {
            return;
        }
        String title = newTitle;
        if (title == null) {
            title = "";
        }
        this.titleLabel.setText(title);
    }

    /**
     * Set the header image that will display in the header. The image is aligned on the left of the header.
     * @param newTitleImage the title image.
     */
    @objid ("bc2b30cc-120f-11e2-b5c6-002564c97630")
    protected void setTitleLeftImage(Image newTitleImage) {
        this.titleLeftImage = newTitleImage;
        if (this.titleLeftImageLabel != null && !this.titleLeftImageLabel.isDisposed()) {
            this.titleLeftImageLabel.setImage(newTitleImage);
            this.titleLeftImageLabel.setVisible(newTitleImage != null);
            if (newTitleImage != null) {
                resetWorkAreaAttachments(this.titleArea);
            }
        }
    }

    /**
     * Set a warning message that will display in the message area on top of the dialog, with a warning small icon.<br>
     * Removes the message if null is passed .
     * @param newMessage The warning message or null to remove the warning message.
     */
    @objid ("7c12d579-704b-11dd-933a-001ec947cd2a")
    protected void setWarningMessage(String newMessage) {
        // @SuppressWarnings("deprecation") added to
        if (this.warningMessage != null ? this.warningMessage.equals(newMessage) : newMessage == null) {
            return;
        }
        this.warningMessage = newMessage;
        if (this.warningMessage == null) {
            if (this.messageArea != null && !this.showingError) {
                setMessageAreaVisible(false);
            }
            if (this.showingWarning) {
                this.showingWarning = false;
            }
        } else {
            if (!this.showingWarning) {
                this.showingWarning = true;
            }
            this.warningMessage = newMessage;
            if (this.messageArea == null) {
                this.messageArea = new ImageAndMessageArea(this.titleArea, 64);
                this.messageArea.setBackground(this.messageLabel.getBackground());
                this.animator = Policy.getAnimatorFactory().createAnimator(this.messageArea);
            }
            this.messageArea.setToolTipText(this.warningMessage);
            this.messageArea.setText(this.warningMessage);
            this.messageArea.setImage(JFaceResources.getImage("dialog_messasge_warning_image"));
            setMessageAreaVisible(true);
        }
        int verticalSpacing = convertVerticalDLUsToPixels(1);
        int horizontalSpacing = convertHorizontalDLUsToPixels(4);
        setLayoutsForNormalMessage(verticalSpacing, horizontalSpacing);
    }

    @objid ("bc2b30d0-120f-11e2-b5c6-002564c97630")
    private Control createTitleArea(Composite parent) {
        this.titleArea = new Composite(parent, SWT.NONE);
        initializeDialogUnits(this.titleArea);
        FormData titleAreaData = new FormData();
        titleAreaData.top = new FormAttachment(0, 0);
        titleAreaData.left = new FormAttachment(0, 0);
        titleAreaData.right = new FormAttachment(100, 0);
        this.titleArea.setLayoutData(titleAreaData);
        FormLayout layout = new FormLayout();
        this.titleArea.setLayout(layout);
        this.titleArea.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                if (ModelioDialog.this.titleAreaColor != null) {
                    ModelioDialog.this.titleAreaColor.dispose();
                }
            }
        });
        
        org.eclipse.swt.widgets.Display display = this.titleArea.getDisplay();
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
        int verticalSpacing = convertVerticalDLUsToPixels(1);
        int horizontalSpacing = convertHorizontalDLUsToPixels(4);
        this.titleArea.setBackground(background);
        
        this.titleRightImageLabel = new Label(this.titleArea, SWT.NONE);
        this.titleRightImageLabel.setBackground(background);
        if (this.titleRightImage != null && !this.titleRightImage.isDisposed()) {
            this.titleRightImageLabel.setImage(this.titleRightImage);
        }
        
        this.titleLeftImageLabel = new Label(this.titleArea, SWT.NONE);
        this.titleLeftImageLabel.setBackground(background);
        if (this.titleLeftImage == null || this.titleLeftImage.isDisposed()) {
            this.titleLeftImageLabel.setImage(JFaceResources.getImage(ModelioDialog.DLG_IMG_TITLE_BANNER));
        } else {
            this.titleLeftImageLabel.setImage(this.titleLeftImage);
        }
        
        FormData rightImageData = new FormData();
        rightImageData.top = new FormAttachment(0, 0);
        rightImageData.right = new FormAttachment(100, 0);
        this.titleRightImageLabel.setLayoutData(rightImageData);
        
        FormData leftImageData = new FormData();
        leftImageData.top = new FormAttachment(0, 0);
        leftImageData.left = new FormAttachment(0, 0);
        this.titleLeftImageLabel.setLayoutData(leftImageData);
        
        this.titleLabel = new Label(this.titleArea, SWT.NONE);
        JFaceColors.setColors(this.titleLabel, foreground, background);
        this.titleLabel.setFont(JFaceResources.getBannerFont());
        this.titleLabel.setText(" ");
        FormData titleData = new FormData();
        titleData.left = new FormAttachment(this.titleLeftImageLabel, 5);
        titleData.top = new FormAttachment(0, verticalSpacing);
        titleData.right = new FormAttachment(this.titleRightImageLabel, -5);
        this.titleLabel.setLayoutData(titleData);
        this.messageImageLabel = new Label(this.titleArea, SWT.NONE);
        this.messageImageLabel.setBackground(background);
        this.messageLabel = new Text(this.titleArea, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
        // this.messageLabel.setEnabled(false);
        this.messageLabel.setEditable(false);
        JFaceColors.setColors(this.messageLabel, foreground, background);
        this.messageLabel.setFont(JFaceResources.getDialogFont());
        this.messageLabelHeight = this.messageLabel.computeSize(-1, -1).y;
        this.leftFillerLabel = new Label(this.titleArea, SWT.NONE);
        this.leftFillerLabel.setBackground(background);
        setLayoutsForNormalMessage(verticalSpacing, horizontalSpacing);
        determineTitleImageLargest();
        return this.titleArea;
    }

    @objid ("7c12d529-704b-11dd-933a-001ec947cd2a")
    private void determineTitleImageLargest() {
        int titleY = this.titleLeftImageLabel.computeSize(-1, -1).y;
        int verticalSpacing = convertVerticalDLUsToPixels(1);
        int labelY = this.titleLabel.computeSize(-1, -1).y;
        labelY += verticalSpacing;
        labelY += this.messageLabelHeight;
        labelY += verticalSpacing;
        this.titleImageLargest = titleY > labelY;
    }

    @objid ("bc2b30dc-120f-11e2-b5c6-002564c97630")
    private void initHelpButton(Control control) {
        String helpId = getHelpId();
        
        if (helpId != null && !helpId.isEmpty()) {
            setHelpAvailable(true);
            control.addHelpListener(new HelpListener() {
                @Override
                public void helpRequested(HelpEvent e) {
                    String hId = getHelpId();
                    if (hId != null && !hId.isEmpty()) {
                        ModelioHelpSystem.getInstance().displayHelpResource(hId);
                    }
                }
            });
        }
    }

    @objid ("7c12d522-704b-11dd-933a-001ec947cd2a")
    private void layoutForNewMessage() {
        int verticalSpacing = convertVerticalDLUsToPixels(1);
        int horizontalSpacing = convertHorizontalDLUsToPixels(4);
        setLayoutsForNormalMessage(verticalSpacing, horizontalSpacing);
        if (this.dialogArea != null) {
            this.titleArea.layout(true);
        }
    }

    @objid ("bc2b30d5-120f-11e2-b5c6-002564c97630")
    private void resetWorkAreaAttachments(Control top) {
        FormData childData = new FormData();
        childData.top = new FormAttachment(top);
        childData.right = new FormAttachment(100, 0);
        childData.left = new FormAttachment(0, 0);
        childData.bottom = new FormAttachment(100, 0);
        this.workArea.setLayoutData(childData);
    }

    @objid ("7c12d520-704b-11dd-933a-001ec947cd2a")
    private void setImageLabelVisible(boolean visible) {
        this.messageImageLabel.setVisible(visible);
        this.leftFillerLabel.setVisible(visible);
    }

    @objid ("7c12d521-704b-11dd-933a-001ec947cd2a")
    private void setLayoutsForNormalMessage(int verticalSpacing, int horizontalSpacing) {
        FormData messageLabelData = new FormData();
        messageLabelData.top = new FormAttachment(this.titleLabel, verticalSpacing);
        messageLabelData.right = new FormAttachment(this.titleRightImageLabel);
        messageLabelData.left = new FormAttachment(this.messageImageLabel, horizontalSpacing);
        // messageLabelData.height = _messageLabelHeight;
        
        if (!this.titleImageLargest) {
            messageLabelData.bottom = new FormAttachment(this.titleRightImageLabel, 0, SWT.BOTTOM);
        }
        
        this.messageLabel.setLayoutData(messageLabelData);
        
        FormData imageLabelData = new FormData();
        imageLabelData.top = new FormAttachment(this.titleLabel, verticalSpacing);
        imageLabelData.left = new FormAttachment(this.titleLeftImageLabel);
        this.messageImageLabel.setLayoutData(imageLabelData);
        
        FormData data = new FormData();
        data.top = new FormAttachment(this.titleLabel, 0, SWT.TOP);
        data.left = new FormAttachment(this.titleLeftImageLabel);
        data.bottom = new FormAttachment(this.messageLabel, 0, SWT.BOTTOM);
        this.leftFillerLabel.setLayoutData(data);
    }

    @objid ("7c12d51a-704b-11dd-933a-001ec947cd2a")
    private void setMessageAreaVisible(boolean visible) {
        this.messageArea.moveAbove(null);
        int bottom = this.titleArea.getBounds().y + this.titleArea.getBounds().height;
        Rectangle msgLabelBounds = this.messageLabel.getBounds();
        if (!this.messageArea.isVisible() && this.messageArea.getBounds().y != bottom) {
            this.messageArea.setBounds(this.messageImageLabel != null ? this.messageImageLabel.getBounds().x : msgLabelBounds.x, bottom,
                    this.messageImageLabel != null ? msgLabelBounds.width + this.messageImageLabel.getBounds().width : msgLabelBounds.width, this.messageArea.computeSize(-1, -1).y);
        }
        this.animator.setVisible(visible);
        setMessageLayoutData();
    }

    @objid ("7c15377c-704b-11dd-933a-001ec947cd2a")
    private void setMessageLayoutData() {
        if (this.messageArea == null) {
            return;
        } else {
            FormData messageAreaData = new FormData();
            messageAreaData.right = new FormAttachment(this.titleRightImageLabel);
            messageAreaData.left = new FormAttachment(this.leftFillerLabel);
            messageAreaData.bottom = new FormAttachment(100, 0);
            this.messageArea.setLayoutData(messageAreaData);
            return;
        }
    }

    @objid ("bc2b30d8-120f-11e2-b5c6-002564c97630")
    private void showMessage(String newMessage, Image newImage) {
        if (this.message.equals(newMessage) && this.messageImage == newImage) {
            return;
        }
        this.message = newMessage;
        if (this.message == null) {
            this.message = "";
        }
        String shownMessage = this.message;
        this.messageImage = newImage;
        if (!this.showingError) {
            updateMessage(shownMessage);
            this.messageImageLabel.setImage(this.messageImage);
            setImageLabelVisible(this.messageImage != null);
            layoutForNewMessage();
        }
    }

    @objid ("7c153779-704b-11dd-933a-001ec947cd2a")
    private void updateMessage(String newMessage) {
        this.messageLabel.setText(newMessage);
    }

}

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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.modelio.platform.rcp.system.ModelioHelpSystem;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIImages;

/**
 * Dialog template that has :
 * <li>a user defined content area defined with {@link #createContentArea(Composite)}.
 * <li>a button area with user defined buttons defined with {@link #addButtonsInButtonBar(Composite)}.
 * <li>An optional message area that display on top of the header area, showing a warning or error small icon and a message.<br>
 * See {@link #setWarningMessage(String)} and {@link #setErrorMessage(String)}.
 * </ul>
 * <p>
 * Subclasses typical implementation:
 * 
 * <pre>
 * public class ExampleDialog extends ModelioDialog2 {
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
 * setImage(anImage);
 * }
 * 
 * &#64;Override
 * protected Point getInitialSize() {
 * return new Point(150, 150);
 * }
 * 
 * }
 * </pre>
 * </p>
 */
@objid ("95df0775-6253-4021-bb65-4fda41763860")
public abstract class ModelioDialog2 extends TrayDialog {
    /**
     * Minimum dialog height (in dialog units)
     */
    @objid ("bc7afcda-4fd3-4aae-a5d6-8ece4913c208")
    private static final int MIN_DIALOG_HEIGHT = 250;

    /**
     * Minimum dialog width (in dialog units)
     */
    @objid ("a56e60e4-e8d7-47b5-83b7-8dbf34a65f60")
    private static final int MIN_DIALOG_WIDTH = 350;

    /**
     * Image registry key for error message image.
     */
    @objid ("016eddf5-0a14-44cf-a13b-5f2c5a97d080")
    public static final String DLG_IMG_TITLE_ERROR = DLG_IMG_MESSAGE_ERROR;

    @objid ("14cc89eb-52e8-4e8f-958d-5b9010da8f23")
    private Composite workArea;

    @objid ("476208a1-8cbc-4640-8045-71d460a5fbdd")
    private ResourceManager resourceManager;

    @objid ("92d2272f-9586-4f77-b9d2-b1e6d2e56826")
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

    @objid ("2dd96dcb-ca3f-4842-b3ad-29398c5458d0")
    public void setModal(final boolean shouldBlock) {
        if (!shouldBlock) {
            int style = getShellStyle();
            style &= ~(SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL);
            setShellStyle(style);
        }
        
    }

    /**
     * Instantiate a new modelio dialog.
     * <p>
     * For non modal dialogs, implementers should call <code>setBlockOnOpen(false)</code>
     * </p>
     * @param parentShell the parent SWT shell
     */
    @objid ("4d290b77-48b1-44d2-ab5e-1a68bdb1a148")
    protected  ModelioDialog2(final Shell parentShell) {
        super(parentShell);
        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER | SWT.APPLICATION_MODAL | getDefaultOrientation());
        setBlockOnOpen(true);
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources());
        
    }

    /**
     * Return a local resource manager whose lifecycle is bound to the dialog life cycle (disposed when the dialog is disposed)
     * @return a resource manager local for this dialog.
     */
    @objid ("07d860c4-9ff3-4f9d-a6dc-474088e9c10d")
    public ResourceManager getResourceManager() {
        return this.resourceManager;
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
    @objid ("90ab2b55-209c-4d95-9a43-750a4f6e6b70")
    protected abstract void addButtonsInButtonBar(Composite parent);

    /**
     * This method adds standard ok and cancel buttons using the <code>createButton</code> framework method. These standard buttons will be accessible from <code>getCancelButton</code>, and <code>getOKButton</code>.
     * <p>
     * Note: The common button order is: <b>{other buttons}</b>, <b>OK</b>, <b>Cancel</b>. On some platforms, {@link #initializeBounds()} will move the default button to the right.
     * </p>
     * @param parent the button bar composite
     */
    @objid ("f39a90e6-950f-4695-9fde-dca3adee3468")
    protected void addDefaultButtons(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        
    }

    @objid ("f16a9b7f-b100-4b02-80d6-a1741e4e6699")
    @Override
    protected final void createButtonsForButtonBar(final Composite parent) {
        addButtonsInButtonBar(parent);
    }

    /**
     * Creates and returns the contents of the dialog area (between the title bar and the button bar).
     * <p>
     * The returned control's layout data must be an instance of <code>GridData</code>.<br/>
     * This method must not modify the parent's layout.
     * </p>
     * <p>
     * Subclasses should override this method as in the following example:
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
    @objid ("c643f113-fa06-4556-b1fe-cab787306d59")
    protected abstract Control createContentArea(Composite parent);

    @objid ("6e49df56-ce8a-4336-86a2-fb5b443864a7")
    @Override
    protected Control createContents(final Composite parent) {
        getShell().addDisposeListener((e) -> this.resourceManager.dispose());
        
        // create the top level composite for the dialog
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 10;
        layout.verticalSpacing = 0;
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        // applyDialogFont(composite);
        // initialize the dialog units
        initializeDialogUnits(composite);
        composite.setBackground(UIColor.WHITE);
        
        // create the dialog area and button bar
        this.dialogArea = createDialogArea(composite);
        this.buttonBar = createButtonBar(composite);
        
        setImage(UIImages.MODELIO);
        init();
        return composite;
    }

    @objid ("8c446e35-28f1-4817-9d65-bfd38b27c11f")
    @Override
    protected Control createButtonBar(Composite parent) {
        Composite buttonBar = (Composite) super.createButtonBar(parent);
        buttonBar.setBackground(parent.getBackground());
        for (Control c : buttonBar.getChildren()) {
            c.setBackground(parent.getBackground());
        }
        return buttonBar;
    }

    /**
     * Creates and returns the contents of the upper part of this dialog (above the button bar).
     * <p>
     * The <code>Dialog</code> implementation of this framework method creates and returns a new <code>Composite</code> with no margins and spacing. Subclasses should override.
     * </p>
     * @param parent The parent composite to contain the dialog area
     * @return the dialog area control
     */
    @objid ("ae9e97d1-d6ae-4bcd-9f16-e36791e8e67c")
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
        composite.setBackground(UIColor.WHITE);
        
        // Build the separator line
        final Label titleBarSeparator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
        titleBarSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        initHelpButton(getShell());
        
        Control content = createContentArea(composite);
        
        // Ensure returned control has valid layout data
        if (content != null && ! (content.getLayoutData() instanceof GridData)) {
            throw new IllegalStateException(String.format("%s.createContentArea() returned a %s with no valid GridData layout data", getClass().getName(), content.getClass().getSimpleName()));
        }
        return composite;
    }

    @objid ("eb2321e8-c549-4b9c-94a8-26e65a13bae0")
    protected String getHelpId() {
        return null;
    }

    /**
     * The <code>ModelioDialog</code> implementation of this <code>Window</code> methods returns an initial size which is at least some reasonable minimum.
     * @return the initial size of the dialog
     */
    @objid ("d574c44e-4167-4550-9211-5c252b55ec1a")
    @Override
    protected Point getInitialSize() {
        final Point shellSize = super.getInitialSize();
        return new Point(Math.max(convertHorizontalDLUsToPixels(ModelioDialog2.MIN_DIALOG_WIDTH), shellSize.x),
                Math.max(convertVerticalDLUsToPixels(ModelioDialog2.MIN_DIALOG_HEIGHT), shellSize.y));
        
    }

    /**
     * Called at the end of the dialog's content instantiation.
     * <p>
     * Implementers should complete the dialog's initialization right here, for example setting a title, a message or a logo.
     * </p>
     */
    @objid ("02f06a93-90c0-4a0f-9822-c294efee2ef2")
    protected abstract void init();

    /**
     * Sets the dialog shell title.
     * @param title the title show
     */
    @objid ("94288b27-6867-4eb4-bcd2-faabe267883d")
    protected void setTitle(final String title) {
        if (getShell() != null)
            getShell().setText(title != null ? title : "");
        
    }

    /**
     * Sets the dialog shell icon.
     * @param title the title show
     */
    @objid ("2ffcd314-1c85-42f9-af38-a9ff33114635")
    protected void setImage(final Image image) {
        if (getShell() != null)
            getShell().setImage(image != null ? image : null);
        
    }

    @objid ("aeff832f-940b-42e5-bd4e-da734903ff65")
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

}

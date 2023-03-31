/* 
 * Copyright 2013-2020 Modeliosoft
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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.dialogs.ControlEnableState;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.IPageChangeProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer2;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.modelio.platform.ui.plugin.UI;

/**
 * Version of eclipse's WizardDialog using ModelioDialog instead of TitleAreaDialog.
 * 
 * @see WizardDialog
 */
@objid ("c1fc24d5-910f-11e0-9de7-002564c97630")
public class ModelioWizardDialog extends ModelioDialog implements IWizardContainer2, IPageChangeProvider {
    /**
     * Image registry key for error message image (value <code>"dialog_title_error_image"</code>).
     */
    @objid ("d6549436-910f-11e0-9de7-002564c97630")
    public static final String WIZ_IMG_ERROR = "dialog_title_error_image"; // $NON-NLS-1$
    

    /**
     * The number of long running operation executed from the dialog.
     */
    @objid ("d6550968-910f-11e0-9de7-002564c97630")
    private long activeRunningOperations = 0;

    /**
     * The time in milliseconds where the last job finished. 'Enter' key presses are ignored for the next {@value #RESTORE_ENTER_DELAY} milliseconds.
     * <p>
     * The value <code>-1</code> indicates that the traverse listener needs to be installed.
     * </p>
     * 
     * @since 3.6
     */
    @objid ("d655096a-910f-11e0-9de7-002564c97630")
    long timeWhenLastJobFinished = -1;

    /**
     * Tells whether a subclass provided the progress monitor part
     */
    @objid ("d6553075-910f-11e0-9de7-002564c97630")
    private boolean useCustomProgressMonitorPart = true;

    @objid ("d6553079-910f-11e0-9de7-002564c97630")
    private int pageMessageType = IMessageProvider.NONE;

    @objid ("d655307a-910f-11e0-9de7-002564c97630")
    private String pageDescription;

    @objid ("d655a5a7-910f-11e0-9de7-002564c97630")
    private boolean isMovingToPreviousPage = false;

    @objid ("d655ccb6-910f-11e0-9de7-002564c97630")
    private int pageWidth = SWT.DEFAULT;

    @objid ("d655ccb7-910f-11e0-9de7-002564c97630")
    private int pageHeight = SWT.DEFAULT;

    @objid ("d655ccb8-910f-11e0-9de7-002564c97630")
    private static final String FOCUS_CONTROL = "focusControl"; // $NON-NLS-1$
    

    /**
     * A delay in milliseconds that reduces the risk that the user accidentally triggers a button by pressing the 'Enter' key immediately after a job has finished.
     * 
     * @since 3.6
     */
    @objid ("d655f3c7-910f-11e0-9de7-002564c97630")
    private static final int RESTORE_ENTER_DELAY = 500;

    @objid ("d655f3ca-910f-11e0-9de7-002564c97630")
    boolean lockedUI = false;

    @objid ("d655ccb5-910f-11e0-9de7-002564c97630")
    private PageContainerFillLayout pageContainerLayout = new PageContainerFillLayout(
                5, 5, 300, 225);

    /**
     * The wizard the dialog is currently showing.
     */
    @objid ("bbf20fb3-120f-11e2-b5c6-002564c97630")
    private IWizard wizard;

    /**
     * Wizards to dispose
     */
    @objid ("bbf20fb5-120f-11e2-b5c6-002564c97630")
    private List<IWizard> createdWizards = new ArrayList<>();

    /**
     * Current nested wizards
     */
    @objid ("bbf20fb9-120f-11e2-b5c6-002564c97630")
    private List<IWizard> nestedWizards = new ArrayList<>();

    /**
     * The currently displayed page.
     */
    @objid ("bbf20fbd-120f-11e2-b5c6-002564c97630")
    IWizardPage currentPage = null;

    /**
     * The progress monitor
     */
    @objid ("bc1362eb-120f-11e2-b5c6-002564c97630")
    private ProgressMonitorPart progressMonitorPart;

    @objid ("bc1362ed-120f-11e2-b5c6-002564c97630")
    private Cursor waitCursor;

    @objid ("bc1362ee-120f-11e2-b5c6-002564c97630")
    private Cursor arrowCursor;

    @objid ("bc1362ef-120f-11e2-b5c6-002564c97630")
    private MessageDialog windowClosingDialog;

    /**
     * Navigation buttons
     */
    @objid ("bc1362f0-120f-11e2-b5c6-002564c97630")
    private Button backButton;

    @objid ("bc1362f2-120f-11e2-b5c6-002564c97630")
    private Button nextButton;

    @objid ("bc1362f3-120f-11e2-b5c6-002564c97630")
    private Button finishButton;

    @objid ("bc1362f4-120f-11e2-b5c6-002564c97630")
    private Button cancelButton;

    @objid ("bc1362f5-120f-11e2-b5c6-002564c97630")
    private Button helpButton;

    @objid ("bc1362f6-120f-11e2-b5c6-002564c97630")
    private SelectionAdapter cancelListener;

    @objid ("bc15c44a-120f-11e2-b5c6-002564c97630")
    private Composite pageContainer;

    @objid ("bc1825ad-120f-11e2-b5c6-002564c97630")
    private ListenerList<IPageChangedListener> pageChangedListeners = new ListenerList<>();

    @objid ("bc1825ae-120f-11e2-b5c6-002564c97630")
    private ListenerList<IPageChangingListener> pageChangingListeners = new ListenerList<>();

    /**
     * About to start a long running operation triggered through the wizard. Shows the progress monitor and disables the wizard's buttons and controls.
     * @param enableCancelButton <code>true</code> if the Cancel button should be enabled, and <code>false</code> if it should be disabled
     * @return the saved UI state
     */
    @objid ("d6577a69-910f-11e0-9de7-002564c97630")
    private Object aboutToStart(final boolean enableCancelButton) {
        Map<String, Object> savedState = null;
        if (getShell() != null) {
            // Save focus control
            Control focusControl = getShell().getDisplay().getFocusControl();
            if (focusControl != null && focusControl.getShell() != getShell()) {
                focusControl = null;
            }
            boolean needsProgressMonitor = this.wizard.needsProgressMonitor();
        
            // Set the busy cursor to all shells.
            Display d = getShell().getDisplay();
            this.waitCursor = new Cursor(d, SWT.CURSOR_WAIT);
            setDisplayCursor(this.waitCursor);
        
            if (this.useCustomProgressMonitorPart) {
                this.cancelButton.removeSelectionListener(this.cancelListener);
                // Set the arrow cursor to the cancel component.
                this.arrowCursor = new Cursor(d, SWT.CURSOR_ARROW);
                this.cancelButton.setCursor(this.arrowCursor);
            }
        
            // Deactivate shell
            savedState = saveUIState(this.useCustomProgressMonitorPart && needsProgressMonitor && enableCancelButton);
            if (focusControl != null) {
                savedState.put(ModelioWizardDialog.FOCUS_CONTROL, focusControl);
            }
            // Activate cancel behavior.
            if (needsProgressMonitor) {
                if (enableCancelButton || this.useCustomProgressMonitorPart) {
                    this.progressMonitorPart.attachToCancelComponent(this.cancelButton);
                }
                this.progressMonitorPart.setVisible(true);
            }
        
            // Install traverse listener once in order to implement 'Enter' and 'Space' key blocking
            if (this.timeWhenLastJobFinished == -1) {
                this.timeWhenLastJobFinished = 0;
                getShell().addTraverseListener(new TraverseListener() {
                    @Override
                    public void keyTraversed(TraverseEvent e) {
                        if (e.detail == SWT.TRAVERSE_RETURN || (e.detail == SWT.TRAVERSE_MNEMONIC && e.keyCode == 32)) {
                            // We want to ignore the keystroke when we detect that it has been received within the
                            // delay period after the last operation has finished. This prevents the user from accidentally
                            // hitting "Enter" or "Space", intending to cancel an operation, but having it processed exactly
                            // when the operation finished, thus traversing the wizard. If there is another operation still
                            // running, the UI is locked anyway so we are not in this code. This listener should fire only
                            // after the UI state is restored (which by definition means all jobs are done.
                            // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=287887
                            if (ModelioWizardDialog.this.timeWhenLastJobFinished != 0 && System.currentTimeMillis() - ModelioWizardDialog.this.timeWhenLastJobFinished < ModelioWizardDialog.RESTORE_ENTER_DELAY) {
                                e.doit = false;
                                return;
                            }
                            ModelioWizardDialog.this.timeWhenLastJobFinished = 0;
                        }
                    }
                });
            }
        }
        return savedState;
    }

    /**
     * The Back button has been pressed.
     */
    @objid ("d657a17a-910f-11e0-9de7-002564c97630")
    protected void backPressed() {
        IWizardPage page = this.currentPage.getPreviousPage();
        if (page == null) {
            // should never happen since we have already visited the page
            return;
        }
        
        // set flag to indicate that we are moving back
        this.isMovingToPreviousPage = true;
        // show the page
        showPage(page);
        
    }

    @objid ("d657c887-910f-11e0-9de7-002564c97630")
    @Override
    protected void buttonPressed(final int buttonId) {
        switch (buttonId) {
        case IDialogConstants.HELP_ID: {
            helpPressed();
            break;
        }
        case IDialogConstants.BACK_ID: {
            backPressed();
            break;
        }
        case IDialogConstants.NEXT_ID: {
            nextPressed();
            break;
        }
        case IDialogConstants.FINISH_ID: {
            finishPressed();
            break;
        }
        default: {
            // Nothing to do
            // The Cancel button has a listener which calls cancelPressed directly
        }
        }
        
    }

    @objid ("d65816a8-910f-11e0-9de7-002564c97630")
    @Override
    protected void cancelPressed() {
        if (this.activeRunningOperations <= 0) {
            // Close the dialog. The check whether the dialog can be
            // closed or not is done in <code>okToClose</code>.
            // This ensures that the check is also evaluated when the user
            // presses the window's close button.
            setReturnCode(Window.CANCEL);
            close();
        } else {
            this.cancelButton.setEnabled(false);
        }
        
    }

    @objid ("d6583db6-910f-11e0-9de7-002564c97630")
    @Override
    public boolean close() {
        if (okToClose()) {
            return hardClose();
        }
        return false;
    }

    /**
     * Allow the wizard's pages to pre-create their page controls. This allows the wizard dialog to open to the correct size.
     */
    @objid ("d659eb69-910f-11e0-9de7-002564c97630")
    private void createPageControls() {
        // Allow the wizard pages to precreate their page controls
        // This allows the wizard to open to the correct size
        this.wizard.createPageControls(this.pageContainer);
        // Ensure that all of the created pages are initially not visible
        IWizardPage[] pages = this.wizard.getPages();
        for (int i = 0; i < pages.length; i++) {
            IWizardPage page = pages[i];
            if (page.getControl() != null) {
                page.getControl().setVisible(false);
            }
        }
        
    }

    /**
     * The Finish button has been pressed.
     */
    @objid ("d65a6097-910f-11e0-9de7-002564c97630")
    protected void finishPressed() {
        // Wizards are added to the nested wizards list in setWizard.
        // This means that the current wizard is always the last wizard in the
        // list.
        // Note that we first call the current wizard directly (to give it a
        // chance to
        // abort, do work, and save state) then call the remaining n-1 wizards
        // in the
        // list (to save state).
        if (this.wizard.performFinish()) {
            // Call perform finish on outer wizards in the nested chain
            // (to allow them to save state for example)
            for (int i = 0; i < this.nestedWizards.size() - 1; i++) {
                (this.nestedWizards.get(i)).performFinish();
            }
            // Hard close the dialog.
            setReturnCode(Window.OK);
            hardClose();
        }
        
    }

    /**
     * Closes this window.
     * @return <code>true</code> if the window is (or was already) closed, and <code>false</code> if it is still open
     */
    @objid ("d65afcd5-910f-11e0-9de7-002564c97630")
    private boolean hardClose() {
        // inform wizards
        for (int i = 0; i < this.createdWizards.size(); i++) {
            IWizard createdWizard = this.createdWizards.get(i);
            createdWizard.dispose();
            // Remove this dialog as a parent from the managed wizard.
            // Note that we do this after calling dispose as the wizard or
            // its pages may need access to the container during
            // dispose code
            createdWizard.setContainer(null);
        }
        // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=202534
        // disposing the wizards could cause the image currently set in
        // this dialog to be disposed. A subsequent repaint event during
        // close would then fail. To prevent this case, we null out the image.
        setTitleLeftImage(null);
        return super.close();
    }

    /**
     * The Help button has been pressed.
     */
    @objid ("d65b23e5-910f-11e0-9de7-002564c97630")
    protected void helpPressed() {
        if (this.currentPage != null) {
            this.currentPage.performHelp();
        }
        
    }

    /**
     * The Next button has been pressed.
     */
    @objid ("d65b4af5-910f-11e0-9de7-002564c97630")
    protected void nextPressed() {
        IWizardPage page = this.currentPage.getNextPage();
        if (page == null) {
            // something must have happened getting the next page
            return;
        }
        
        // show the next page
        showPage(page);
        
    }

    /**
     * Checks whether it is alright to close this wizard dialog and performed standard cancel processing. If there is a long running operation in progress, this method posts an alert message saying that the wizard cannot be closed.
     * @return <code>true</code> if it is alright to close this dialog, and <code>false</code> if it is not
     */
    @objid ("d65b7208-910f-11e0-9de7-002564c97630")
    private boolean okToClose() {
        if (this.activeRunningOperations > 0) {
            synchronized (this) {
                this.windowClosingDialog = createWizardClosingDialog();
                this.windowClosingDialog.open();
                this.windowClosingDialog = null;
            }
            return false;
        }
        return this.wizard.performCancel();
    }

    /**
     * Restores the enabled/disabled state of the wizard dialog's buttons and the tree of controls for the currently showing page.
     * @see #saveUIState
     * @param state a map containing the saved state as returned by <code>saveUIState</code>
     */
    @objid ("d65be738-910f-11e0-9de7-002564c97630")
    private void restoreUIState(final Map<String, Object> state) {
        restoreEnableState(this.backButton, state, "back"); //$NON-NLS-1$
        restoreEnableState(this.nextButton, state, "next"); //$NON-NLS-1$
        restoreEnableState(this.finishButton, state, "finish"); //$NON-NLS-1$
        restoreEnableState(this.cancelButton, state, "cancel"); //$NON-NLS-1$
        restoreEnableState(this.helpButton, state, "help"); //$NON-NLS-1$
        Object pageValue = state.get("page"); //$NON-NLS-1$
        if (pageValue != null) {
            ((ControlEnableState) pageValue).restore();
        }
        
    }

    /**
     * Captures and returns the enabled/disabled state of the wizard dialog's buttons and the tree of controls for the currently showing page. All these controls are disabled in the process, with the possible exception of the Cancel button.
     * @see #restoreUIState
     * @param keepCancelEnabled <code>true</code> if the Cancel button should remain enabled, and <code>false</code> if it should be disabled
     * @return a map containing the saved state suitable for restoring later with <code>restoreUIState</code>
     */
    @objid ("d65c837a-910f-11e0-9de7-002564c97630")
    private Map<String, Object> saveUIState(final boolean keepCancelEnabled) {
        Map<String, Object> savedState = new HashMap<>(10);
        saveEnableStateAndSet(this.backButton, savedState, "back", false); //$NON-NLS-1$
        saveEnableStateAndSet(this.nextButton, savedState, "next", false); //$NON-NLS-1$
        saveEnableStateAndSet(this.finishButton, savedState, "finish", false); //$NON-NLS-1$
        saveEnableStateAndSet(this.cancelButton, savedState, "cancel", keepCancelEnabled); //$NON-NLS-1$
        saveEnableStateAndSet(this.helpButton, savedState, "help", false); //$NON-NLS-1$
        if (this.currentPage != null) {
            savedState.put("page", ControlEnableState.disable(this.currentPage.getControl())); //$NON-NLS-1$
        }
        return savedState;
    }

    /**
     * Sets the minimum page size used for the pages.
     * @see #setMinimumPageSize(Point)
     * @param minWidth the minimum page width
     * @param minHeight the minimum page height
     */
    @objid ("d65cf8a5-910f-11e0-9de7-002564c97630")
    public void setMinimumPageSize(final int minWidth, final int minHeight) {
        Assert.isTrue(minWidth >= 0 && minHeight >= 0);
        this.pageContainerLayout.minimumWidth = minWidth;
        this.pageContainerLayout.minimumHeight = minHeight;
        
    }

    /**
     * Sets the size of all pages. The given size takes precedence over computed sizes.
     * @see #setPageSize(Point)
     * @param width the page width
     * @param height the page height
     */
    @objid ("d65d46c6-910f-11e0-9de7-002564c97630")
    public void setPageSize(final int width, final int height) {
        this.pageWidth = width;
        this.pageHeight = height;
        
    }

    /**
     * Shows the starting page of the wizard.
     */
    @objid ("d65e0a16-910f-11e0-9de7-002564c97630")
    private void showStartingPage() {
        this.currentPage = this.wizard.getStartingPage();
        if (this.currentPage == null) {
            // something must have happened getting the page
            return;
        }
        // ensure the page control has been created
        if (this.currentPage.getControl() == null) {
            this.currentPage.createControl(this.pageContainer);
            // the page is responsible for ensuring the created control is
            // accessible via getControl.
            Assert.isNotNull(this.currentPage.getControl());
            // we do not need to update the size since the call
            // to initialize bounds has not been made yet.
        }
        // make the new page visible
        this.currentPage.setVisible(true);
        // update the dialog controls
        update();
        
    }

    /**
     * A long running operation triggered through the wizard was stopped either by user input or by normal end. Hides the progress monitor and restores the enable state wizard's buttons and controls.
     * @see #aboutToStart
     * @param savedState the saved UI state as returned by <code>aboutToStart</code>
     */
    @objid ("d65e3125-910f-11e0-9de7-002564c97630")
    private void stopped(final Object savedState) {
        if (getShell() != null && !getShell().isDisposed()) {
            if (this.wizard.needsProgressMonitor()) {
                this.progressMonitorPart.setVisible(false);
                this.progressMonitorPart.removeFromCancelComponent(this.cancelButton);
            }
            @SuppressWarnings ("unchecked")
            Map<String, Object> state = (Map<String, Object>) savedState;
            restoreUIState(state);
            setDisplayCursor(null);
            if (this.useCustomProgressMonitorPart) {
                this.cancelButton.addSelectionListener(this.cancelListener);
                this.cancelButton.setCursor(null);
                this.arrowCursor.dispose();
                this.arrowCursor = null;
            }
            this.waitCursor.dispose();
            this.waitCursor = null;
            Control focusControl = (Control) state.get(ModelioWizardDialog.FOCUS_CONTROL);
            if (focusControl != null && !focusControl.isDisposed()) {
                focusControl.setFocus();
            }
        }
        
    }

    /**
     * Updates this dialog's controls to reflect the current page.
     */
    @objid ("d65e5835-910f-11e0-9de7-002564c97630")
    protected void update() {
        // Update the window title
        updateWindowTitle();
        // Update the title bar
        updateTitleBar();
        // Update the buttons
        updateButtons();
        
        // Fires the page change event
        firePageChanged(new PageChangedEvent(this, getCurrentPage()));
        
    }

    @objid ("d65e7f45-910f-11e0-9de7-002564c97630")
    @Override
    public void updateButtons() {
        boolean canFlipToNextPage = false;
        boolean canFinish = this.wizard.canFinish();
        if (this.backButton != null) {
            this.backButton.setEnabled(this.currentPage.getPreviousPage() != null);
        }
        if (this.nextButton != null) {
            canFlipToNextPage = this.currentPage.canFlipToNextPage();
            this.nextButton.setEnabled(canFlipToNextPage);
        }
        this.finishButton.setEnabled(canFinish);
        // finish is default unless it is disabled and next is enabled
        if (canFlipToNextPage && !canFinish) {
            getShell().setDefaultButton(this.nextButton);
        } else {
            getShell().setDefaultButton(this.finishButton);
        }
        
    }

    /**
     * Update the message line with the page's description.
     * <p>
     * A description is shown only if there is no message or error message.
     * </p>
     */
    @objid ("d65ea655-910f-11e0-9de7-002564c97630")
    private void updateDescriptionMessage() {
        this.pageDescription = this.currentPage.getDescription();
        setMessage(this.pageDescription);
        
    }

    @objid ("d65ecd65-910f-11e0-9de7-002564c97630")
    @Override
    public void updateMessage() {
        if (this.currentPage == null) {
            return;
        }
        
        String pageMessage = this.currentPage.getMessage();
        if (pageMessage != null && this.currentPage instanceof IMessageProvider) {
            this.pageMessageType = ((IMessageProvider) this.currentPage).getMessageType();
        } else {
            this.pageMessageType = IMessageProvider.NONE;
        }
        if (pageMessage == null) {
            setMessage(this.pageDescription);
        } else {
            setMessage(pageMessage, this.pageMessageType);
        }
        setErrorMessage(this.currentPage.getErrorMessage());
        
    }

    /**
     * Changes the shell size to the given size, ensuring that it is no larger than the display bounds.
     * @param width the shell width
     * @param height the shell height
     */
    @objid ("d65ef475-910f-11e0-9de7-002564c97630")
    private void setShellSize(final int width, final int height) {
        Rectangle size = getShell().getBounds();
        size.height = height;
        size.width = width;
        getShell().setBounds(getConstrainedShellBounds(size));
        
    }

    @objid ("d65f90b5-910f-11e0-9de7-002564c97630")
    @Override
    public void updateSize() {
        updateSize(this.currentPage);
    }

    @objid ("d66005e6-910f-11e0-9de7-002564c97630")
    @Override
    public void updateTitleBar() {
        String s = null;
        if (this.currentPage != null) {
            s = this.currentPage.getTitle();
        }
        if (s == null) {
            s = ""; //$NON-NLS-1$
        }
        setTitle(s);
        if (this.currentPage != null) {
            // setTitleLeftImage(this.currentPage.getImage());
            updateDescriptionMessage();
        }
        updateMessage();
        
    }

    @objid ("d6602cf5-910f-11e0-9de7-002564c97630")
    @Override
    public void updateWindowTitle() {
        if (getShell() == null) {
            // Not created yet
            return;
        }
        String title = this.wizard.getWindowTitle();
        if (title == null) {
            title = ""; //$NON-NLS-1$
        }
        getShell().setText(title);
        
    }

    @objid ("d6605405-910f-11e0-9de7-002564c97630")
    @Override
    public Object getSelectedPage() {
        return getCurrentPage();
    }

    @objid ("d66201b5-910f-11e0-9de7-002564c97630")
    @Override
    public void init() {
        ImageDescriptor imageDescriptor = UI.getImageDescriptor("images/headerleft110x50.png");
        Image image = imageDescriptor.createImage();
        setTitleLeftImage(image);
        
    }

    /**
     * Creates a new wizard dialog for the given wizard.
     * @param parentShell the parent shell
     * @param newWizard the wizard this dialog is working on
     */
    @objid ("bc1825af-120f-11e2-b5c6-002564c97630")
    public  ModelioWizardDialog(final Shell parentShell, final IWizard newWizard) {
        super(parentShell);
        setShellStyle(SWT.CLOSE | SWT.MAX | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE | getDefaultOrientation());
        setWizard(newWizard);
        // since VAJava can't initialize an instance var with an anonymous
        // class outside a constructor we do it here:
        this.cancelListener = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                cancelPressed();
            }
        };
        
    }

    /**
     * Calculates the difference in size between the given page and the page container. A larger page results in a positive delta.
     * @param page the page
     * @return the size difference encoded as a <code>new Point(deltaWidth,deltaHeight)</code>
     */
    @objid ("bc1a870a-120f-11e2-b5c6-002564c97630")
    private Point calculatePageSizeDelta(final IWizardPage page) {
        Control pageControl = page.getControl();
        if (pageControl == null) {
            // control not created yet
            return new Point(0, 0);
        }
        Point contentSize = pageControl.computeSize(SWT.DEFAULT, SWT.DEFAULT,
                true);
        Rectangle rect = this.pageContainerLayout.getClientArea(this.pageContainer);
        Point containerSize = new Point(rect.width, rect.height);
        return new Point(Math.max(0, contentSize.x - containerSize.x), Math.max(0, contentSize.y - containerSize.y));
    }

    @objid ("bc1a8711-120f-11e2-b5c6-002564c97630")
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        // Register help listener on the shell
        newShell.addHelpListener(new HelpListener() {
            @Override
            public void helpRequested(HelpEvent event) {
                // call perform help on the current page
                if (ModelioWizardDialog.this.currentPage != null) {
                    ModelioWizardDialog.this.currentPage.performHelp();
                }
            }
        });
        
    }

    @objid ("bc1a871c-120f-11e2-b5c6-002564c97630")
    @Override
    protected void setButtonLayoutData(final Button button) {
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
        
        // On large fonts this can make this dialog huge
        widthHint = Math.min(widthHint, button.getDisplay().getBounds().width / 5);
        Point minSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        data.widthHint = Math.max(widthHint, minSize.x);
        
        button.setLayoutData(data);
        
    }

    /**
     * Creates the Cancel button for this wizard dialog. Creates a standard (<code>SWT.PUSH</code>) button and registers for its selection events. Note that the number of columns in the button bar composite is incremented. The Cancel button is created
     * specially to give it a removeable listener.
     * @param parent the parent button bar
     * @return the new Cancel button
     */
    @objid ("bc1a8721-120f-11e2-b5c6-002564c97630")
    private Button createCancelButton(final Composite parent) {
        // increment the number of columns in the button bar
        ((GridLayout) parent.getLayout()).numColumns++;
        Button button = new Button(parent, SWT.PUSH);
        button.setText(IDialogConstants.CANCEL_LABEL);
        setButtonLayoutData(button);
        button.setFont(parent.getFont());
        button.setData(Integer.valueOf(IDialogConstants.CANCEL_ID));
        button.addSelectionListener(this.cancelListener);
        return button;
    }

    /**
     * Return the cancel button if the id is a the cancel id.
     * @param id the button id
     * @return the button corresponding to the button id
     */
    @objid ("bc1a8728-120f-11e2-b5c6-002564c97630")
    @Override
    protected Button getButton(final int id) {
        if (id == IDialogConstants.CANCEL_ID) {
            return this.cancelButton;
        }
        return super.getButton(id);
    }

    /**
     * The <code>WizardDialog</code> implementation of this <code>Window</code> method calls call <code>IWizard.addPages</code> to allow the current wizard to add extra pages, then <code>super.createContents</code> to create the controls. It then calls
     * <code>IWizard.createPageControls</code> to allow the wizard to pre-create their page controls prior to opening, so that the wizard opens to the correct size. And finally it shows the first page.
     */
    @objid ("bc1a8730-120f-11e2-b5c6-002564c97630")
    @Override
    protected Control createContents(final Composite parent) {
        // Allow the wizard to add pages to itself
        // Need to call this now so page count is correct
        // for determining if next/previous buttons are needed
        this.wizard.addPages();
        Control contents = super.createContents(parent);
        // Allow the wizard pages to precreate their page controls
        createPageControls();
        // Show the first page
        showStartingPage();
        return contents;
    }

    @objid ("bc1ce86f-120f-11e2-b5c6-002564c97630")
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        // Build the Page container
        this.pageContainer = createPageContainer(composite);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = this.pageWidth;
        gd.heightHint = this.pageHeight;
        this.pageContainer.setLayoutData(gd);
        this.pageContainer.setFont(parent.getFont());
        
        // Insert a progress monitor
        this.progressMonitorPart = createProgressMonitorPart(composite, new GridLayout());
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        if (!this.wizard.needsProgressMonitor()) {
            gridData.exclude = true;
        }
        this.progressMonitorPart.setLayoutData(gridData);
        this.progressMonitorPart.setVisible(false);
        // Build the separator line
        Label separator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
        separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        applyDialogFont(this.progressMonitorPart);
        return composite;
    }

    /**
     * Hook method for subclasses to create a custom progress monitor part.
     * <p>
     * The default implementation creates a progress monitor with a stop button will be created.
     * </p>
     * @param composite the parent composite
     * @param pmlayout the layout
     * @return ProgressMonitorPart the progress monitor part
     */
    @objid ("bc1ce876-120f-11e2-b5c6-002564c97630")
    protected ProgressMonitorPart createProgressMonitorPart(final Composite composite, final GridLayout pmlayout) {
        this.useCustomProgressMonitorPart = false;
        return new ProgressMonitorPart(composite, pmlayout, true);
    }

    /**
     * Creates the container that holds all pages.
     * @param parent
     * @return Composite
     */
    @objid ("bc1ce87f-120f-11e2-b5c6-002564c97630")
    private Composite createPageContainer(final Composite parent) {
        Composite result = new Composite(parent, SWT.NULL);
        result.setLayout(this.pageContainerLayout);
        return result;
    }

    /**
     * Creates the Previous and Next buttons for this wizard dialog. Creates standard (<code>SWT.PUSH</code>) buttons and registers for their selection events. Note that the number of columns in the button bar composite is incremented. These buttons are
     * created specially to prevent any space between them.
     * @param parent the parent button bar
     * @return a composite containing the new buttons
     */
    @objid ("bc1ce886-120f-11e2-b5c6-002564c97630")
    private Composite createPreviousAndNextButtons(final Composite parent) {
        // increment the number of columns in the button bar
        ((GridLayout) parent.getLayout()).numColumns++;
        Composite composite = new Composite(parent, SWT.NONE);
        // create a layout with spacing and margins appropriate for the font
        // size.
        GridLayout layout = new GridLayout();
        layout.numColumns = 0; // will be incremented by createButton
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 0;
        composite.setLayout(layout);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_CENTER);
        composite.setLayoutData(data);
        composite.setFont(parent.getFont());
        this.backButton = createButton(composite, IDialogConstants.BACK_ID, IDialogConstants.BACK_LABEL, false);
        this.nextButton = createButton(composite, IDialogConstants.NEXT_ID, IDialogConstants.NEXT_LABEL, false);
        return composite;
    }

    /**
     * Creates and return a new wizard closing dialog without opening it.
     * @return MessageDalog
     */
    @objid ("bc1ce88d-120f-11e2-b5c6-002564c97630")
    private MessageDialog createWizardClosingDialog() {
        MessageDialog result = new MessageDialog(getShell(),
                JFaceResources.getString("WizardClosingDialog.title"), //$NON-NLS-1$
                null,
                JFaceResources.getString("WizardClosingDialog.message"), //$NON-NLS-1$
                MessageDialog.QUESTION,
                new String[] { IDialogConstants.OK_LABEL }, 0) {
            @Override
            protected int getShellStyle() {
                return super.getShellStyle() | SWT.SHEET;
            }
        };
        return result;
    }

    @objid ("bc1ce892-120f-11e2-b5c6-002564c97630")
    @Override
    public IWizardPage getCurrentPage() {
        return this.currentPage;
    }

    /**
     * Returns the progress monitor for this wizard dialog (if it has one).
     * @return the progress monitor, or <code>null</code> if this wizard dialog does not have one
     */
    @objid ("bc1f49ca-120f-11e2-b5c6-002564c97630")
    protected IProgressMonitor getProgressMonitor() {
        return this.progressMonitorPart;
    }

    /**
     * Returns the wizard this dialog is currently displaying.
     * @return the current wizard
     */
    @objid ("bc1f49cf-120f-11e2-b5c6-002564c97630")
    protected IWizard getWizard() {
        return this.wizard;
    }

    /**
     * Notifies page changing listeners and returns result of page changing processing to the sender.
     * @param eventType
     * @return <code>true</code> if page changing listener completes successfully, <code>false</code> otherwise
     */
    @objid ("bc1f49d4-120f-11e2-b5c6-002564c97630")
    private boolean doPageChanging(final IWizardPage targetPage) {
        PageChangingEvent e = new PageChangingEvent(this, getCurrentPage(),
                targetPage);
        firePageChanging(e);
        // Prevent navigation if necessary
        return e.doit;
    }

    /**
     * Restores the enabled/disabled state of the given control.
     * @see #saveEnableStateAndSet
     * @param w the control
     * @param h the map (key type: <code>String</code>, element type: <code>Boolean</code>)
     * @param key the key
     */
    @objid ("bc1f49db-120f-11e2-b5c6-002564c97630")
    private void restoreEnableState(final Control w, final Map<String, Object> h, final String key) {
        if (w != null) {
            Boolean b = (Boolean) h.get(key);
            if (b != null) {
                w.setEnabled(b.booleanValue());
            }
        }
        
    }

    /**
     * This implementation of IRunnableContext#run(boolean, boolean, IRunnableWithProgress) blocks until the runnable has been run, regardless of the value of <code>fork</code>. It is recommended that <code>fork</code> is set to true in most cases. If
     * <code>fork</code> is set to <code>false</code>, the runnable will run in the UI thread and it is the runnable's responsibility to call <code>Display.readAndDispatch()</code> to ensure UI responsiveness.
     * 
     * UI state is saved prior to executing the long-running operation and is restored after the long-running operation completes executing. Any attempt to change the UI state of the wizard in the long-running operation will be nullified when original UI
     * state is restored.
     */
    @objid ("bc1f49e4-120f-11e2-b5c6-002564c97630")
    @Override
    public void run(final boolean fork, final boolean cancelable, final IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException {
        // The operation can only be canceled if it is executed in a separate
        // thread.
        // Otherwise the UI is blocked anyway.
        Object state = null;
        if (this.activeRunningOperations == 0) {
            state = aboutToStart(fork && cancelable);
        }
        this.activeRunningOperations++;
        try {
            if (!fork) {
                this.lockedUI = true;
            }
            ModalContext.run(runnable, fork, getProgressMonitor(), getShell().getDisplay());
            this.lockedUI = false;
        } finally {
            // explicitly invoke done() on our progress monitor so that its
            // label does not spill over to the next invocation, see bug 271530
            if (getProgressMonitor() != null) {
                getProgressMonitor().done();
            }
            // Stop if this is the last one
            if (state != null) {
                this.timeWhenLastJobFinished = System.currentTimeMillis();
                stopped(state);
            }
            this.activeRunningOperations--;
        }
        
    }

    /**
     * Saves the enabled/disabled state of the given control in the given map, which must be modifiable.
     * @see #restoreEnableState(Control, Map, String)
     * @param w the control, or <code>null</code> if none
     * @param h the map (key type: <code>String</code>, element type: <code>Boolean</code>)
     * @param key the key
     * @param enabled <code>true</code> to enable the control, and <code>false</code> to disable it
     */
    @objid ("bc21ab2c-120f-11e2-b5c6-002564c97630")
    private void saveEnableStateAndSet(final Control w, final Map<String, Object> h, final String key, final boolean enabled) {
        if (w != null) {
            h.put(key, w.getEnabled() ? Boolean.TRUE : Boolean.FALSE);
            w.setEnabled(enabled);
        }
        
    }

    /**
     * Sets the given cursor for all shells currently active for this window's display.
     * @param c the cursor
     */
    @objid ("bc21ab3b-120f-11e2-b5c6-002564c97630")
    private void setDisplayCursor(final Cursor c) {
        Shell[] shells = getShell().getDisplay().getShells();
        for (int i = 0; i < shells.length; i++) {
            shells[i].setCursor(c);
        }
        
    }

    /**
     * Sets the minimum page size used for the pages.
     * @see #setMinimumPageSize(int,int)
     * @param size the page size encoded as <code>new Point(width,height)</code>
     */
    @objid ("bc21ab40-120f-11e2-b5c6-002564c97630")
    public void setMinimumPageSize(final Point size) {
        setMinimumPageSize(size.x, size.y);
    }

    /**
     * Sets the size of all pages. The given size takes precedence over computed sizes.
     * @see #setPageSize(int,int)
     * @param size the page size encoded as <code>new Point(width,height)</code>
     */
    @objid ("bc21ab45-120f-11e2-b5c6-002564c97630")
    public void setPageSize(final Point size) {
        setPageSize(size.x, size.y);
    }

    /**
     * Sets the wizard this dialog is currently displaying.
     * @param newWizard the wizard
     */
    @objid ("bc21ab4a-120f-11e2-b5c6-002564c97630")
    protected void setWizard(final IWizard newWizard) {
        this.wizard = newWizard;
        this.wizard.setContainer(this);
        if (!this.createdWizards.contains(this.wizard)) {
            this.createdWizards.add(this.wizard);
            // New wizard so just add it to the end of our nested list
            this.nestedWizards.add(this.wizard);
            if (this.pageContainer != null) {
                // Dialog is already open
                // Allow the wizard pages to precreate their page controls
                // This allows the wizard to open to the correct size
                createPageControls();
                // Ensure the dialog is large enough for the wizard
                updateSizeForWizard(this.wizard);
                this.pageContainer.layout(true);
            }
        } else {
            // We have already seen this wizard, if it is the previous wizard
            // on the nested list then we assume we have gone back and remove
            // the last wizard from the list
            int size = this.nestedWizards.size();
            if (size >= 2 && this.nestedWizards.get(size - 2) == this.wizard) {
                this.nestedWizards.remove(size - 1);
            } else {
                // Assume we are going forward to revisit a wizard
                this.nestedWizards.add(this.wizard);
            }
        }
        
    }

    @objid ("bc21ab4f-120f-11e2-b5c6-002564c97630")
    @Override
    public void showPage(final IWizardPage page) {
        if (page == null || page == this.currentPage) {
            return;
        }
        
        if (!this.isMovingToPreviousPage) {
            // remember my previous page.
            page.setPreviousPage(this.currentPage);
        } else {
            this.isMovingToPreviousPage = false;
        }
        
        // If page changing evaluation unsuccessful, do not change the page
        if (!doPageChanging(page)) {
            return;
        }
        
        // Update for the new page in a busy cursor if possible
        if (getContents() == null) {
            updateForPage(page);
        } else {
            final IWizardPage finalPage = page;
            BusyIndicator.showWhile(getContents().getDisplay(), new Runnable() {
                @Override
                public void run() {
                    updateForPage(finalPage);
                }
            });
        }
        
    }

    /**
     * Update the receiver for the new page.
     * @param page the currentlt editer wizard page.
     */
    @objid ("bc21ab54-120f-11e2-b5c6-002564c97630")
    void updateForPage(final IWizardPage page) {
        // ensure this page belongs to the current wizard
        if (this.wizard != page.getWizard()) {
            setWizard(page.getWizard());
        }
        // ensure that page control has been created
        // (this allows lazy page control creation)
        if (page.getControl() == null) {
            page.createControl(this.pageContainer);
            // the page is responsible for ensuring the created control is
            // accessible via getControl.
            Assert.isNotNull(page.getControl(), JFaceResources.format(
                    JFaceResources.getString("WizardDialog.missingSetControl"), //$NON-NLS-1$
                    new Object[] { page.getName() }));
            // ensure the dialog is large enough for this page
            updateSize(page);
        }
        // make the new page visible
        IWizardPage oldPage = this.currentPage;
        this.currentPage = page;
        
        this.currentPage.setVisible(true);
        if (oldPage != null) {
            oldPage.setVisible(false);
        }
        // update the dialog controls
        update();
        
    }

    /**
     * Computes the correct dialog size for the current page and resizes its shell if necessary. Also causes the container to refresh its layout.
     * @param page the wizard page to use to resize the dialog
     * @since 2.0
     */
    @objid ("bc240c8a-120f-11e2-b5c6-002564c97630")
    protected void updateSize(final IWizardPage page) {
        if (page == null || page.getControl() == null) {
            return;
        }
        updateSizeForPage(page);
        this.pageContainerLayout.layoutPage(page.getControl());
        
    }

    /**
     * Computes the correct dialog size for the given page and resizes its shell if necessary.
     * @param page the wizard page
     */
    @objid ("bc240c8f-120f-11e2-b5c6-002564c97630")
    private void updateSizeForPage(final IWizardPage page) {
        // ensure the page container is large enough
        Point delta = calculatePageSizeDelta(page);
        if (delta.x > 0 || delta.y > 0) {
            // increase the size of the shell
            Shell shell = getShell();
            Point shellSize = shell.getSize();
            setShellSize(shellSize.x + delta.x, shellSize.y + delta.y);
            constrainShellSize();
        }
        
    }

    /**
     * Computes the correct dialog size for the given wizard and resizes its shell if necessary.
     * @param sizingWizard the wizard
     */
    @objid ("bc240c94-120f-11e2-b5c6-002564c97630")
    private void updateSizeForWizard(final IWizard sizingWizard) {
        Point delta = new Point(0, 0);
        IWizardPage[] pages = sizingWizard.getPages();
        for (int i = 0; i < pages.length; i++) {
            // ensure the page container is large enough
            Point pageDelta = calculatePageSizeDelta(pages[i]);
            delta.x = Math.max(delta.x, pageDelta.x);
            delta.y = Math.max(delta.y, pageDelta.y);
        }
        if (delta.x > 0 || delta.y > 0) {
            // increase the size of the shell
            Shell shell = getShell();
            Point shellSize = shell.getSize();
            setShellSize(shellSize.x + delta.x, shellSize.y + delta.y);
        }
        
    }

    @objid ("bc240c9a-120f-11e2-b5c6-002564c97630")
    @Override
    public void addPageChangedListener(final IPageChangedListener listener) {
        this.pageChangedListeners.add(listener);
    }

    @objid ("bc240c9f-120f-11e2-b5c6-002564c97630")
    @Override
    public void removePageChangedListener(final IPageChangedListener listener) {
        this.pageChangedListeners.remove(listener);
    }

    /**
     * Notifies any selection changed listeners that the selected page has changed. Only listeners registered at the time this method is called are notified.
     * @see IPageChangedListener#pageChanged
     * 
     * @since 3.1
     * @param event a selection changed event
     */
    @objid ("bc266dec-120f-11e2-b5c6-002564c97630")
    protected void firePageChanged(final PageChangedEvent event) {
        Object[] listeners = this.pageChangedListeners.getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            final IPageChangedListener l = (IPageChangedListener) listeners[i];
            SafeRunnable.run(new SafeRunnable() {
                @Override
                public void run() {
                    l.pageChanged(event);
                }
            });
        }
        
    }

    /**
     * Adds a listener for page changes to the list of page changing listeners registered for this dialog. Has no effect if an identical listener is already registered.
     * @param listener a page changing listener
     * @since 3.3
     */
    @objid ("bc266df1-120f-11e2-b5c6-002564c97630")
    public void addPageChangingListener(final IPageChangingListener listener) {
        this.pageChangingListeners.add(listener);
    }

    /**
     * Removes the provided page changing listener from the list of page changing listeners registered for the dialog.
     * @param listener a page changing listener
     * @since 3.3
     */
    @objid ("bc266df6-120f-11e2-b5c6-002564c97630")
    public void removePageChangingListener(final IPageChangingListener listener) {
        this.pageChangingListeners.remove(listener);
    }

    /**
     * Notifies any page changing listeners that the currently selected dialog page is changing. Only listeners registered at the time this method is called are notified.
     * @see IPageChangingListener#handlePageChanging(PageChangingEvent)
     * @since 3.3
     * @param event a selection changing event
     */
    @objid ("bc266dfb-120f-11e2-b5c6-002564c97630")
    protected void firePageChanging(final PageChangingEvent event) {
        Object[] listeners = this.pageChangingListeners.getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            final IPageChangingListener l = (IPageChangingListener) listeners[i];
            SafeRunnable.run(new SafeRunnable() {
                @Override
                public void run() {
                    l.handlePageChanging(event);
                }
            });
        }
        
    }

    /**
     * Creates the buttons for this dialog's button bar.
     * <p>
     * The <code>WizardDialog</code> implementation of this framework method prevents the parent composite's columns from being made equal width in order to remove the margin between the Back and Next buttons.
     * </p>
     * @param parent the parent composite to contain the buttons
     */
    @objid ("bc266e00-120f-11e2-b5c6-002564c97630")
    @Override
    protected void addButtonsInButtonBar(final Composite parent) {
        ((GridLayout) parent.getLayout()).makeColumnsEqualWidth = false;
        if (this.wizard.isHelpAvailable()) {
            this.helpButton = createButton(parent, IDialogConstants.HELP_ID, IDialogConstants.HELP_LABEL, false);
        }
        if (this.wizard.needsPreviousAndNextButtons()) {
            createPreviousAndNextButtons(parent);
        }
        this.finishButton = createButton(parent, IDialogConstants.FINISH_ID, IDialogConstants.FINISH_LABEL, true);
        this.cancelButton = createCancelButton(parent);
        
        if (parent.getDisplay().getDismissalAlignment() == SWT.RIGHT) {
            // Make the default button the right-most button.
            // See also special code in org.eclipse.jface.dialogs.Dialog#initializeBounds()
            this.finishButton.moveBelow(null);
        }
        
    }

    @objid ("bc266e05-120f-11e2-b5c6-002564c97630")
    @Override
    public Control createContentArea(final Composite parent) {
        return parent;
    }

    /**
     * A layout for a container which includes several pages, like a notebook, wizard, or preference dialog. The size computed by this layout is the maximum width and height of all pages currently inserted into the container.
     */
    @objid ("d6561ad7-910f-11e0-9de7-002564c97630")
    protected static class PageContainerFillLayout extends Layout {
        /**
         * The margin width; <code>5</code> pixels by default.
         */
        @objid ("d65641e5-910f-11e0-9de7-002564c97630")
        public int marginWidth = 5;

        /**
         * The margin height; <code>5</code> pixels by default.
         */
        @objid ("d65641e7-910f-11e0-9de7-002564c97630")
        public int marginHeight = 5;

        /**
         * The minimum width; <code>0</code> pixels by default.
         */
        @objid ("d65641e9-910f-11e0-9de7-002564c97630")
        public int minimumWidth = 0;

        /**
         * The minimum height; <code>0</code> pixels by default.
         */
        @objid ("d65668f5-910f-11e0-9de7-002564c97630")
        public int minimumHeight = 0;

        /**
         * Creates new layout object.
         * @param mw the margin width
         * @param mh the margin height
         * @param minW the minimum width
         * @param minH the minimum height
         */
        @objid ("d65668f7-910f-11e0-9de7-002564c97630")
        public  PageContainerFillLayout(final int mw, final int mh, final int minW, final int minH) {
            this.marginWidth = mw;
            this.marginHeight = mh;
            this.minimumWidth = minW;
            this.minimumHeight = minH;
            
        }

        @objid ("bc266e0c-120f-11e2-b5c6-002564c97630")
        @Override
        public Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean force) {
            if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
                return new Point(wHint, hHint);
            }
            Point result = null;
            Control[] children = composite.getChildren();
            if (children.length > 0) {
                result = new Point(0, 0);
                for (int i = 0; i < children.length; i++) {
                    Point cp = children[i].computeSize(wHint, hHint, force);
                    result.x = Math.max(result.x, cp.x);
                    result.y = Math.max(result.y, cp.y);
                }
                result.x = result.x + 2 * this.marginWidth;
                result.y = result.y + 2 * this.marginHeight;
            } else {
                Rectangle rect = composite.getClientArea();
                result = new Point(rect.width, rect.height);
            }
            result.x = Math.max(result.x, this.minimumWidth);
            result.y = Math.max(result.y, this.minimumHeight);
            if (wHint != SWT.DEFAULT) {
                result.x = wHint;
            }
            if (hHint != SWT.DEFAULT) {
                result.y = hHint;
            }
            return result;
        }

        /**
         * Returns the client area for the given composite according to this layout.
         * @param c the composite
         * @return the client area rectangle
         */
        @objid ("bc266e19-120f-11e2-b5c6-002564c97630")
        public Rectangle getClientArea(final Composite c) {
            Rectangle rect = c.getClientArea();
            rect.x = rect.x + this.marginWidth;
            rect.y = rect.y + this.marginHeight;
            rect.width = rect.width - 2 * this.marginWidth;
            rect.height = rect.height - 2 * this.marginHeight;
            return rect;
        }

        @objid ("bc28cf4d-120f-11e2-b5c6-002564c97630")
        @Override
        public void layout(final Composite composite, final boolean force) {
            Rectangle rect = getClientArea(composite);
            Control[] children = composite.getChildren();
            for (int i = 0; i < children.length; i++) {
                children[i].setBounds(rect);
            }
            
        }

        /**
         * Lays outs the page according to this layout.
         * @param w the control
         */
        @objid ("bc28cf54-120f-11e2-b5c6-002564c97630")
        public void layoutPage(final Control w) {
            w.setBounds(getClientArea(w.getParent()));
        }

        /**
         * Sets the location of the page so that its origin is in the upper left corner.
         * @param w the control
         */
        @objid ("bc28cf59-120f-11e2-b5c6-002564c97630")
        public void setPageLocation(final Control w) {
            w.setLocation(this.marginWidth, this.marginHeight);
        }

    }

    @objid ("7afdb355-975f-11e0-bb39-002564c97630")
    class MWDProgressMonitorPart extends ProgressMonitorPart {
        @objid ("7afe0176-975f-11e0-bb39-002564c97630")
        private String currentTask = null;

        @objid ("7afe2887-975f-11e0-bb39-002564c97630")
        @Override
        public void clearBlocked() {
            super.clearBlocked();
            if (!ModelioWizardDialog.this.lockedUI) {
                getBlockedHandler().clearBlocked();
            }
            
        }

        @objid ("7afe288b-975f-11e0-bb39-002564c97630")
        @Override
        public void beginTask(final String name, final int totalWork) {
            super.beginTask(name, totalWork);
            this.currentTask = name;
            
        }

        @objid ("7afe76a9-975f-11e0-bb39-002564c97630")
        @Override
        public void setTaskName(final String name) {
            super.setTaskName(name);
            this.currentTask = name;
            
        }

        @objid ("7afe76ae-975f-11e0-bb39-002564c97630")
        @Override
        public void subTask(final String name) {
            super.subTask(name);
            // If we haven't got anything yet use this value for more
            // context
            if (this.currentTask == null) {
                this.currentTask = name;
            }
            
        }

        @objid ("bc28cf5f-120f-11e2-b5c6-002564c97630")
        public  MWDProgressMonitorPart(final Composite parent, final Layout layout, final boolean createStopButton) {
            super(parent, layout, createStopButton);
        }

        @objid ("bc28cf67-120f-11e2-b5c6-002564c97630")
        @Override
        public void setBlocked(final IStatus reason) {
            super.setBlocked(reason);
            if (!ModelioWizardDialog.this.lockedUI) {
                getBlockedHandler().showBlocked(getShell(), this, reason,
                        this.currentTask);
            }
            
        }

    }

}

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.platform.ui.panel.IPanelProvider;

/**
 * Modelio dialog based on a {@link IPanelProvider}.
 * <p>
 * By default the dialog has only a "Close" button
 * <p>
 * Usage:
 * <ul>
 * <li>Call {@link #create(Shell, IPanelProvider)} to get a builder.
 * <li>Call {@link Builder} methods to customize your dialog.
 * <li>The dialog will have only a "Close" button unless you call one of {@link Builder#withButton(int, String, boolean)}, {@link Builder#withCancelButton(boolean)} or {@link Builder#withOkButton(boolean)}
 * <li>Finish with {@link Builder#build()} to create your dialog.
 * </ul>
 * 
 * @since 5.2
 */
@objid ("9e87acf0-5fd2-46f1-8d2f-f779f80d00e4")
public final class ModelioPanelDialog extends ModelioDialog {
    @objid ("2b8e37a1-1fc5-4ecd-97f9-1e6bb2848f15")
    private final Builder descriptor;

    @objid ("8f073323-d0bb-4fcc-9cb0-9ba5e528d85b")
    private final Map<Integer, ButtonAction> buttonActions = new HashMap<>();

    @objid ("5810e841-61ea-4574-8742-be5d4155f3b0")
    public static Builder create(IPanelProvider panel) {
        return new Builder(panel);
    }

    /**
     * This getter is use to retrieve a dialog button by its id.<br/>
     * Return only the buttons defined by {@link Builder.withButton()} methods.<br/>
     * 
     * Typical use: enabling/disabling depending on the dialog contents status.
     * @param id the id of the button to look for
     * @return the button for the ID or <code>null</code>
     */
    @objid ("906adb92-f515-4d49-8e9b-c91e1a9018c6")
    @Override
    public Button getButton(int id) {
        return super.getButton(id);
    }

    /**
     * Private constructor.
     * <p>
     * Use {@link #create(IPanelProvider)} to create an edition dialog.
     * @param descriptor the descriptor.
     */
    @objid ("85e9ec0a-d4fc-443d-9ad9-1175e1619028")
    private  ModelioPanelDialog(Shell parentShell, Builder descriptor) {
        super(parentShell);
        this.descriptor = descriptor;
        setBlockOnOpen(this.descriptor.blockOnOpen);
        
    }

    @objid ("9279c792-6f27-4c02-9418-d9dab8f9fcb0")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        if (this.descriptor.buttons.isEmpty()) {
            createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
        } else {
            for (ButtonData buttonData : this.descriptor.buttons) {
                createButton(parent, buttonData.id, buttonData.label, buttonData.isDefault);
                if (buttonData.action != null) {
                    this.buttonActions.put(buttonData.id, buttonData.action);
                }
            }
        }
        
    }

    @objid ("2c7875d0-a461-439c-a8bc-db2cd550c930")
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        String s = (this.descriptor.shellTitle == null) ? this.descriptor.shellTitle : this.descriptor.headerTitle;
        newShell.setText(s != null ? s : "");
        
    }

    @objid ("f55fb86b-8afd-4c19-bbac-1dd1b8e72791")
    @Override
    protected Control createContentArea(Composite parent) {
        Control panel = (Control) this.descriptor.panel.createPanel(parent);
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        panel.addTraverseListener(new TraverseListener() {
            @Override
            public void keyTraversed(TraverseEvent event) {
                if ((event.character == SWT.CR) || (event.character == SWT.KEYPAD_CR) || (event.character == SWT.ESC)) {
                    event.doit = false;
                }
            }
        });
        return panel;
    }

    /**
     * Return the help topic from descriptor if present otherwise as a fallback return the panel help topic.
     */
    @objid ("7a47b67f-7e55-49b7-a19e-61887cc2e442")
    @Override
    protected String getHelpId() {
        return (this.descriptor.helpTopic != null) ? this.descriptor.helpTopic : this.descriptor.panel.getHelpTopic();
    }

    @objid ("983eac48-7cf8-4ebc-a350-52b4dffef9fa")
    @Override
    protected void init() {
        setTitle(this.descriptor.headerTitle);
        setMessage(this.descriptor.message);
        if (this.descriptor.panelInput != null) {
            this.descriptor.panel.setInput(this.descriptor.panelInput);
        }
        
        if (this.descriptor.onOpenAction != null) {
            this.descriptor.onOpenAction.accept(this);
        }
        
    }

    @objid ("2f506ad8-3bf4-4455-9c18-e77cae8f9526")
    @Override
    protected void buttonPressed(int buttonId) {
        ButtonAction action = this.buttonActions.get(buttonId);
        if (action != null) {
            if (!action.apply(this)) {
                // Direct exit
                return;
            }
        }
        super.buttonPressed(buttonId);
        
    }

    @objid ("2f53b203-6f72-4f4a-8953-9fba1b3a01ae")
    @Override
    protected Point getInitialSize() {
        Point is = this.descriptor.initialSize;
        return (is == null) ? super.getInitialSize() : is;
    }

    @objid ("d680ed31-cc3c-4127-acf2-389fe8198358")
    public IPanelProvider getPanel() {
        return this.descriptor.panel;
    }

    /**
     * Dialog descriptor.
     * <p>
     * Call withXxx() methods to setup the dialog then call {@link #build()}.
     */
    @objid ("a13c6610-76c6-44af-a849-1df6539df005")
    @SuppressWarnings ("hiding")
    public static class Builder {
        @objid ("63e5e6f1-b5a2-4e2b-ad54-e499d8da8449")
        private String shellTitle;

        @objid ("580a1785-a1ca-49cc-bfb8-3ab78afa4e1d")
        private String headerTitle;

        @objid ("441c3a57-a9c7-4eb8-b137-6c18c0bea1bc")
        private String message;

        @objid ("034ab452-aec4-438d-8675-338344e16a6b")
        private String helpTopic;

        @objid ("f78b1b88-c65a-44ca-b155-e6d9df3f9a34")
        private boolean blockOnOpen = true;

        @objid ("7e25b690-a673-4bd9-a569-bb819040b6a8")
        private Point initialSize;

        @objid ("0d3a1b40-d4ef-4971-8652-bfbe6ce5f4f2")
        private List<ButtonData> buttons = new ArrayList<>(3);

        @objid ("14145f5e-7619-42cf-af33-489f9a09da39")
        private Consumer<ModelioPanelDialog> onOpenAction;

        @objid ("c30ffc2d-a11c-47fc-80b9-edd703c484a9")
        private IPanelProvider panel;

        @objid ("e6dcd5e3-0de4-4c86-bd67-a1249633bce8")
        private Object panelInput;

        @objid ("bc974334-820c-4dd1-ab1b-85304ca3aa8e")
        public  Builder(IPanelProvider panel) {
            this.panel = Objects.requireNonNull(panel);
        }

        /**
         * Build the dialog.
         * <p>
         * The dialog is not yet opened, its SWT widget don't exist until you call {@link ModelioPanelDialog#open()}.
         * @return the built dialog.
         */
        @objid ("98d62852-c0c4-432d-b563-9c0c87d963c3")
        public ModelioPanelDialog build(Shell parentShell) {
            Objects.requireNonNull(this.panel, "panel not specified");
            
            if (parentShell == null) {
                Display display = Display.getCurrent();
                if (display == null) {
                    SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS); // throws SWTException
                    throw new IllegalStateException(); // dead code just to avoid compiler warning
                }
                return new ModelioPanelDialog(display.getActiveShell(), this);
            } else {
                return new ModelioPanelDialog(parentShell, this);
            }
            
        }

        /**
         * Define the header message for the dialog.
         * @param message the header message
         * @return this builder for chaining calls
         */
        @objid ("39160e95-b09a-41d2-adff-20c278245e9e")
        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Define the shell title for the dialog.
         * @param shellTitle the title for the dialog {@link Shell}
         * @return this builder for chaining calls
         */
        @objid ("91ba980b-ba5e-4d56-9f4a-d99b07fe82cd")
        public Builder withShellTitle(String shellTitle) {
            this.shellTitle = Objects.requireNonNull(shellTitle);
            return this;
        }

        /**
         * Define the header title of the dialog.
         * @return this builder for chaining calls
         */
        @objid ("a84566e5-1e32-48c1-a562-504ec1f4f1fe")
        public Builder withTitle(String title) {
            this.headerTitle = title;
            return this;
        }

        /**
         * Add a button the the dialog bottom buttons.
         * @see IDialogConstants IDialogConstants constants for button labels and ids.
         * @param id the button id from {@link IDialogConstants}.
         * @param label the button label.
         * @param action an optional action run when clicking the button. If the action returns false, normal button processing is stopped which may prevent the dialog from being closed.
         * @param isDefault whether it is a the default button
         * @return this builder for chaining calls
         */
        @objid ("35d2ce58-6227-46f0-bec0-303ccb832a78")
        public Builder withButton(int id, String label, ButtonAction action, boolean isDefault) {
            this.buttons.add(new ButtonData(id, label, action, isDefault));
            return this;
        }

        /**
         * Add the OK button with an action to run on click.
         * @param action an optional action run when clicking the button. If the action returns false, normal button processing is stopped which may prevent the dialog from being closed.
         * @param isDefault whether it is the default button
         * @return this builder for chaining calls
         */
        @objid ("59686e3a-bd11-451d-ab68-5ef32b1c5835")
        public Builder withOkButton(ButtonAction action, boolean isDefault) {
            return withButton(IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, action, isDefault);
        }

        /**
         * Add the cancel button.
         * @param isDefault whether it is the default button
         * @return this builder for chaining calls
         */
        @objid ("fa0aa0e7-d7c2-4342-8d85-6a85ce616b34")
        public Builder withCancelButton(ButtonAction action, boolean isDefault) {
            return withButton(IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, action, isDefault);
        }

        /**
         * Add a hooked Consumer to be called when the dialog is opened.<br/>
         * Several open actions may be added, however do not rely on their addition ordering.
         * @param action the code to call
         * @return this builder for chaining calls
         */
        @objid ("0a5795b0-4a6b-434e-9544-353acb09034f")
        public Builder withOnOpenAction(Consumer<ModelioPanelDialog> action) {
            this.onOpenAction = action;
            return this;
        }

        /**
         * Set the dialog as modal or not and whether open() should block until dialog closes.
         * @see {@link ModelioDialog#setBlockOnOpen(boolean)}
         * @param modal       whether the dialog is modal (true) or modeless (false)
         * @param blockOnOpen whether open() should block until the dialog is closed.
         * @return this builder for chaining calls
         */
        @objid ("6fa4d27a-a77f-4882-b889-4ddeb98452a8")
        public Builder withBlockOnOpen(boolean blockOnOpen) {
            this.blockOnOpen = blockOnOpen;
            return this;
        }

        /**
         * @return this builder for chaining calls
         */
        @objid ("dd735703-5f11-4fc6-a63b-8d934655f6a1")
        public Builder withInitialSize(int w, int h) {
            this.initialSize = new Point(w, h);
            return this;
        }

        /**
         * @return this builder for chaining calls
         */
        @objid ("557b910e-524a-4ae4-8a93-2afcc9bf5189")
        public Builder withHelpTopic(String helpTopic) {
            this.helpTopic = helpTopic;
            return this;
        }

        @objid ("9e2e6c9f-a96a-4dbe-9357-55e47927028b")
        public Builder withPanelInput(Object input) {
            this.panelInput = input;
            return this;
        }

    }

    @objid ("331623b7-fa5c-4a39-a0a4-d7bec022ec1e")
    private static class ButtonData {
        @objid ("709890f5-864b-400f-9b51-96175426ccf6")
        public final int id;

        @objid ("1457d160-4bf0-4770-8640-7520d914c411")
        public final String label;

        @objid ("2cb69150-1dd7-48e6-9ec4-5c467cae4437")
        public final boolean isDefault;

        @objid ("c76c42ac-116b-4e8b-a92f-286d56ee96a6")
        public ButtonAction action;

        @objid ("3fceca20-310b-4932-a111-bcce7c6df2f6")
        public  ButtonData(int id, String label, ButtonAction action, boolean isDefault) {
            this.id = id;
            this.label = label;
            this.action = action;
            this.isDefault = isDefault;
            
        }

    }

    /**
     * Function called on button click.
     * <p>
     * If the function returns false, normal button processing is stopped which may prevent the dialog from being closed.
     */
    @objid ("b1edbfc5-6ab0-4db1-8546-309f6765be44")
    public interface ButtonAction extends Function<ModelioPanelDialog, Boolean> {}
    

}

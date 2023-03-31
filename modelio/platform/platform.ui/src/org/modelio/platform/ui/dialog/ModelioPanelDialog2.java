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
@objid ("2ce625df-46b9-4026-b977-ff4ba52da92b")
public final class ModelioPanelDialog2 extends ModelioDialog2 {
    @objid ("1a14c9af-e24d-4afd-b038-94022a844391")
    private final Builder descriptor;

    @objid ("5a373e25-144f-40ea-8beb-f97040e2e712")
    private final Map<Integer, ButtonAction> buttonActions = new HashMap<>();

    @objid ("612c76a0-eaf0-409a-a60f-4068a4726b83")
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
    @objid ("975038ac-2dfb-430e-963c-f864592aadeb")
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
    @objid ("3167a430-4e00-46ea-99a1-c08cdb024d60")
    private  ModelioPanelDialog2(Shell parentShell, Builder descriptor) {
        super(parentShell);
        this.descriptor = descriptor;
        setBlockOnOpen(this.descriptor.blockOnOpen);
        
    }

    @objid ("36f94a9a-e611-450f-a08e-d8217d47fec5")
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

    @objid ("6a6ed79e-1d27-4ace-a72a-8748a3d92998")
    @Override
    protected Control createContentArea(Composite parent) {
        Control panel = (Control) this.descriptor.panel.createPanel(parent);
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        panel.setBackground(parent.getBackground());
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
    @objid ("e4d3553d-a258-45bc-b6af-6a25cb79d105")
    @Override
    protected String getHelpId() {
        return (this.descriptor.helpTopic != null) ? this.descriptor.helpTopic : this.descriptor.panel.getHelpTopic();
    }

    @objid ("07043deb-7e5e-4276-80db-885793798492")
    @Override
    protected void init() {
        setTitle(this.descriptor.headerTitle);
        
        
        if (this.descriptor.panelInput != null) {
            this.descriptor.panel.setInput(this.descriptor.panelInput);
        }
        if (this.descriptor.onOpenAction != null) {
            this.descriptor.onOpenAction.accept(this);
        }
        
    }

    @objid ("83cfbb83-6035-4ca9-a0b1-f80c25de39f2")
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

    @objid ("09ee8cea-a2d5-4148-88a4-14dd61724676")
    @Override
    protected Point getInitialSize() {
        Point is = this.descriptor.initialSize;
        return (is == null) ? super.getInitialSize() : is;
    }

    @objid ("7b6d9fbc-fe2a-4426-884f-c154def598f2")
    public IPanelProvider getPanel() {
        return this.descriptor.panel;
    }

    /**
     * Dialog descriptor.
     * <p>
     * Call withXxx() methods to setup the dialog then call {@link #build()}.
     */
    @objid ("8f8f3622-e630-49b8-baf9-ee3946febcbb")
    @SuppressWarnings ("hiding")
    public static class Builder {
        @objid ("a7697cba-74ac-4648-ae6e-cfc1d9d420dc")
        private String headerTitle;

        @objid ("6be60ddd-99c3-4909-9c5e-46d650b16789")
        private String helpTopic;

        @objid ("917b661e-3e9c-491b-8c65-ce24beb94d33")
        private boolean blockOnOpen = true;

        @objid ("a56b0bb8-3399-4f36-821d-40afc314e327")
        private Point initialSize;

        @objid ("2a409f1c-57e3-42b6-ae8b-21e6f1ce2d8a")
        private List<ButtonData> buttons = new ArrayList<>(3);

        @objid ("a097a27a-a652-457e-a700-d27f38529ba8")
        private Consumer<ModelioPanelDialog2> onOpenAction;

        @objid ("2c383b26-ca7d-4407-a863-c050e711249f")
        private IPanelProvider panel;

        @objid ("8394b340-0642-427b-bdcd-f950d6a6f964")
        private Object panelInput;

        @objid ("8a3778a7-b794-4fc3-ad9f-aa529a3f2a0a")
        public  Builder(IPanelProvider panel) {
            this.panel = Objects.requireNonNull(panel);
        }

        /**
         * Build the dialog.
         * <p>
         * The dialog is not yet opened, its SWT widget don't exist until you call {@link ModelioPanelDialog#open()}.
         * @return the built dialog.
         */
        @objid ("9c0e6ad4-d0b5-42fb-bc65-a3145e721d6a")
        public ModelioPanelDialog2 build(Shell parentShell) {
            Objects.requireNonNull(this.panel, "panel not specified");
            
            if (parentShell == null) {
                Display display = Display.getCurrent();
                if (display == null) {
                    SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS); // throws SWTException
                    throw new IllegalStateException(); // dead code just to avoid compiler warning
                }
                return new ModelioPanelDialog2(display.getActiveShell(), this);
            } else {
                return new ModelioPanelDialog2(parentShell, this);
            }
            
        }

        /**
         * Define the header title of the dialog.
         * @return this builder for chaining calls
         */
        @objid ("efd396e3-1b4a-4afd-beab-971862ec7ee0")
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
        @objid ("8038628a-6207-4f25-8238-d3ed9cc480a9")
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
        @objid ("02510b33-b419-4c96-854b-8e3d2342386c")
        public Builder withOkButton(ButtonAction action, boolean isDefault) {
            return withButton(IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, action, isDefault);
        }

        /**
         * Add the cancel button.
         * @param isDefault whether it is the default button
         * @return this builder for chaining calls
         */
        @objid ("8674fb8b-8bca-4e93-8853-d1c917f82827")
        public Builder withCancelButton(ButtonAction action, boolean isDefault) {
            return withButton(IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, action, isDefault);
        }

        /**
         * Add a hooked Consumer to be called when the dialog is opened.<br/>
         * Several open actions may be added, however do not rely on their addition ordering.
         * @param action the code to call
         * @return this builder for chaining calls
         */
        @objid ("56e466c1-8860-4ae1-8bc0-2c8bbc6a8050")
        public Builder withOnOpenAction(Consumer<ModelioPanelDialog2> action) {
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
        @objid ("19a92856-f23a-407a-92bd-46ea23bfd755")
        public Builder withBlockOnOpen(boolean blockOnOpen) {
            this.blockOnOpen = blockOnOpen;
            return this;
        }

        /**
         * @return this builder for chaining calls
         */
        @objid ("d12f863c-a1d8-4f13-ad4b-c3dec52680df")
        public Builder withInitialSize(int w, int h) {
            this.initialSize = new Point(w, h);
            return this;
        }

        /**
         * @return this builder for chaining calls
         */
        @objid ("4107c9a9-c142-4aa2-9a04-78dc552ccd97")
        public Builder withHelpTopic(String helpTopic) {
            this.helpTopic = helpTopic;
            return this;
        }

        @objid ("fdcd1e8d-0b34-4aae-b13d-ec82a00b4eea")
        public Builder withPanelInput(Object input) {
            this.panelInput = input;
            return this;
        }

    }

    @objid ("9b385d91-e422-407a-9646-edfaf6dc5856")
    private static class ButtonData {
        @objid ("61de304a-aea2-4caa-9961-6ca5e97dab90")
        public final int id;

        @objid ("29d4b587-3768-421d-9e83-086860071c12")
        public final String label;

        @objid ("1e97f44a-dc9c-4022-802b-3365cb43a9f2")
        public final boolean isDefault;

        @objid ("4cda6f3e-af99-4ff0-b780-6606a7ddb832")
        public ButtonAction action;

        @objid ("676eb35c-b14b-4957-9544-c63504fc4f86")
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
    @objid ("e515ff6a-640e-4caf-818e-d9a103287293")
    public interface ButtonAction extends Function<ModelioPanelDialog2, Boolean> {}
    

}

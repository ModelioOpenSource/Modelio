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

package org.modelio.api.ui.form;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.modelio.api.modelio.model.IImageService;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.event.IModelChangeEvent;
import org.modelio.api.modelio.model.event.IModelChangeListener;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.ui.form.fields.IField;
import org.modelio.api.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.ui.panel.IPanelListener;
import org.modelio.platform.ui.panel.IPanelProvider;

/**
 * An {@link IPanelProvider} implementation displaying a form for the selected model element.
 * <p>
 * Forms are build using an {@link IFieldFactory}, and may be split into several pages. See {@link FormFieldPage}.
 * </p>
 * Usage:
 * <ol>
 * <li>make a subclass of {@link AbstractFieldFactory},
 * <li>implement {@link IFieldFactory#createFields(FormToolkit, Composite, ModelElement, FormFieldPage)} and {@link IFieldFactory#createFieldPages(ModelElement)}
 * <li>Instantiate a panel with {@link #ElementFormPanel(IModuleContext, IFieldFactory)}.
 * <li>call {@link #createPanel(Composite)}
 * </ol>
 * <p>
 * By default the model is updated immediately when a field is changed. Since Modelio Valkyrie 3.8 This behavior may be disabled with {@link #setAutoApply(boolean)} . <br>
 * Panels listeners are always fired with the changed {@link IField} as 'data' when a field value changes.
 * 
 * @see FormFieldPage
 * @see AbstractFieldFactory
 */
@objid ("b678b466-7054-4e19-a92c-6dc92438b268")
public class ElementFormPanel implements IPanelProvider {
    /**
     * Whether the field will apply their value when leaving it.
     * 
     * @since Valkyrie 3.8
     */
    @objid ("897f7450-f38d-43e4-bb84-5db52afea3ba")
    private boolean autoApply;

    @objid ("5e876d18-15e3-4685-86dc-13460b5c4552")
    private boolean headerVisible = true;

    /**
     * Last selected tab in any ElementFormPanel.
     * <p>
     * Used to select the same tab when a panel is recreated ?
     */
    @objid ("1d2a7aa5-a1bb-441b-bccb-fe5c5541367d")
    private static String selectedTab;

    @objid ("f912a341-4b8e-4b5d-b17f-8e7bb86ae75a")
    private final IFieldFactory fieldFactory;

    @objid ("b80ae420-c5a6-446a-bf19-9426d935ab7f")
    private final List<IField> fields = new ArrayList<>();

    @objid ("3dc1a9ec-108b-4ac2-a6d4-4f462dda024f")
    private Listener formUpdater;

    @objid ("171c2067-7fe0-410b-99e1-ebc851e31ac7")
    private ModelElement input;

    @objid ("7e3ec53b-e79b-463d-a265-5b1da7662bbc")
    private final IModelChangeListener modelListener;

    @objid ("290696e5-b12a-4686-8f51-70d728963f7b")
    private final IModuleContext moduleContext;

    @objid ("5c531f53-f600-43f1-9241-3d3c2df363ed")
    private final List<IPanelListener> panelListeners = new CopyOnWriteArrayList<>();

    @objid ("a513611b-0dc1-4124-80f1-dbaaaed64463")
    private ScrolledForm scrolledForm;

    @objid ("f9be1718-9673-44c2-83f4-e77fc289dc76")
    private FormToolkit toolkit;

    /**
     * Build a new instance of {@link ElementFormPanel}.
     * <p>
     * The fields will apply their value immediately on leaving.
     * 
     * @param moduleContext a module context to get modelio services and sessuin from.
     * @param fieldFactory the factory to create form fields with.
     * @since 3.8 : a module context is now needed.
     */
    @objid ("91e5b885-b238-45db-ac3b-78ddfa5b154c")
    public ElementFormPanel(IModuleContext moduleContext, IFieldFactory fieldFactory) {
        this.moduleContext = Objects.requireNonNull(moduleContext);
        this.fieldFactory = Objects.requireNonNull(fieldFactory);
        this.autoApply = true;
        
        this.modelListener = this::onModelChanged;
    }

    @objid ("d0e03d50-cfc5-492d-b207-fb67e641d333")
    @Override
    public void addListener(IPanelListener l) {
        this.panelListeners.add(l);
    }

    /**
     * Save all fields to the model.
     * <p>
     * To be used when {@link #setAutoApply(boolean)} was called with false.
     * <p>
     * May throw a runtime exception if one field has an invalid value.
     * @since 3.8 Valkyrie
     */
    @objid ("094abfa2-2f02-404a-aaec-5932a842db74")
    public void apply() {
        for (IField f : this.fields) {
            f.apply();
        }
    }

    /**
     * Tells whether {@link #apply()} may be called safely.
     * 
     * @return true only if all fields have valid values.
     * @since 3.8 Valkyrie
     */
    @objid ("7ccee658-f6b2-4d2b-9e8e-baaa0bd05b70")
    public boolean canApply() {
        for (IField f : this.fields) {
            if (f.getValidationError() != null) {
                return false;
            }
        }
        return true;
    }

    @objid ("314d6838-87aa-4e03-913f-a4b6fb0d188c")
    @Override
    public final Composite createPanel(Composite parent) {
        this.toolkit = new FormToolkit(parent.getDisplay());
        this.toolkit.setBorderStyle(SWT.BORDER);
        this.scrolledForm = this.toolkit.createScrolledForm(parent);
        
        GridLayout layout = new GridLayout(1, false);
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 1;
        layout.marginTop = 12;
        layout.marginBottom = 4;
        layout.marginLeft = 2;
        layout.marginRight = 2;
        this.scrolledForm.getBody().setLayout(layout);
        
        final IModelingSession modelingSession = this.moduleContext.getModelingSession();
        this.scrolledForm.addDisposeListener(e -> {
            modelingSession.removeModelListener(this.modelListener);
        
            this.fields.clear();
        
            // Don't forget to dispose() the toolkit when disposing the panel.
            this.toolkit.dispose();
            this.toolkit = null;
        });
        
        modelingSession.addModelListener(this.modelListener);
        return this.scrolledForm;
    }

    @objid ("868b258e-9be8-4028-8648-5494d1cd8d55")
    @Override
    public final void dispose() {
        this.scrolledForm.dispose();
        this.scrolledForm = null;
        // The remaining is to be made in the dispose listener, see 'createPanel'
    }

    /**
     * Get the first element in the selection that matches the given type
     * @param <T> the required type
     * 
     * @param selection the selection object
     * @param cls the required type class
     * @return the first matching element or null.
     * @deprecated Since 3.8 use {@link SelectionHelper#getFirst(org.eclipse.jface.viewers.ISelection, Class)}
     */
    @objid ("8cabc527-37ee-4e4e-ae2a-7c3647cd9344")
    @Deprecated
    public static <T> T getFirst(final IStructuredSelection selection, Class<T> cls) {
        return SelectionHelper.getFirst(selection, cls);
    }

    @objid ("5090fb12-80af-4b27-8402-15de6fd1156a")
    @Override
    public final String getHelpTopic() {
        return null;
    }

    @objid ("5f361cbb-25b1-4418-9277-e81d3cca3395")
    @Override
    public final Object getInput() {
        return this.input;
    }

    @objid ("1bc9f6cc-c120-4728-8379-f6ba556cb4eb")
    @Override
    public final Composite getPanel() {
        return this.scrolledForm;
    }

    /**
     * @since 3.7.1
     * 
     * @return whether the form header is displayed.
     */
    @objid ("3d73403e-0e2d-484a-a1db-5ba064247c88")
    public boolean isHeaderVisible() {
        return this.headerVisible;
    }

    @objid ("18ed1212-56d5-4c64-971b-45405e7092f2")
    @Override
    public boolean isRelevantFor(Object obj) {
        return obj instanceof ModelElement;
    }

    @objid ("76253dcb-ebac-4ead-a9d6-1442321ac2da")
    @Override
    public void removeListener(IPanelListener l) {
        this.panelListeners.remove(l);
    }

    /**
     * Set whether the field will apply their value when leaving it.
     * <p>
     * If called with false {@link #apply()} must be called in order to apply the field values.
     * 
     * @param autoApply true to apply values immediately, false to manually apply them.
     * @return this instance
     */
    @objid ("fc48c12e-caea-45e6-a31d-28bea31baa30")
    public ElementFormPanel setAutoApply(boolean autoApply) {
        this.autoApply = autoApply;
        return this;
    }

    /**
     * Display or hide the form header to display the edited element name or gain place.
     * 
     * @param headerVisible whether the form header is displayed.
     * @since 3.7.1
     */
    @objid ("2d70873e-2fff-4e84-a9ee-2a6af69e31a3")
    public void setHeaderVisible(boolean headerVisible) {
        this.headerVisible = headerVisible;
    }

    /**
     * Set the panel input.
     * <p>
     * Supported inputs are:
     * <ul>
     * <li>a {@link ModelElement}
     * <li>a {@link Collection} containing one {@link ModelElement}
     * <li>a {@link IStructuredSelection} containing one {@link ModelElement}
     * </ul>
     * If the model element is not valid (deleted) or not {@link #isRelevantFor(Object) relevant} for the field provider the panel is emptied.
     * <p>
     * Since Modelio 3.8 passing any other input type will result in <code>IllegalArgumentException</code> being thrown.
     */
    @objid ("741dd247-4cbf-45fd-9555-53ad05e391ea")
    @Override
    public final void setInput(Object input) {
        // Bad input cases: clear all
        ModelElement newInput = null;
        if (input instanceof ModelElement) {
            // Input is a single model element
            newInput = (ModelElement) input;
        } else if (input instanceof Collection) {
            final Collection<?> coll = (Collection<?>) input;
            if (coll.size() == 1 && coll.iterator().next() instanceof ModelElement) {
                // Input is a list containing one model element
                newInput = (ModelElement) coll.iterator().next();
            }
        } else if (input instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) input;
            ModelElement first = SelectionHelper.getFirst(selection, ModelElement.class);
        
            if (selection.size() == 1 && first != null) {
                newInput = first;
            }
        } else if (input != null) {
            // The input type is not supported
            throw new IllegalArgumentException(String.format("Not supported input: %s", input));
        }
        
        if (newInput == null || !newInput.isValid() || !isRelevantFor(newInput)) {
            // Input is not valid
            newInput = null;
        }
        
        // Delay for update if not visible because it
        // is really expensive and slows GUI.
        // Exception: force update the first time because SWT.Show event is not fired and
        // we are not notified at all when the form become visible.
        final ModelElement effectiveInput = newInput;
        this.scrolledForm.getDisplay().asyncExec(() -> {
            if (this.scrolledForm == null || this.scrolledForm.isDisposed()) {
                return;
            }
        
            if (this.scrolledForm.isVisible() || this.fields.isEmpty()) {
                updateFormFromInput(effectiveInput);
            } else {
                delayUpdateForm(effectiveInput);
            }
        });
    }

    /**
     * Create a complete form for an element.
     * <p/>
     * Fields should be filled after calling this method.
     * 
     * @param parent a widget which will be the parent of the new field instance (cannot be null)
     * @param formInput the element to build the form for.
     */
    @objid ("1a552669-47bf-4bdf-b457-da249a0eee85")
    protected void createFormFields(Composite parent, ModelElement formInput) {
        List<FormFieldPage> pages = this.fieldFactory.createFieldPages(formInput);
        if (pages == null || pages.isEmpty()) {
            this.fields.addAll(this.fieldFactory.createFields(this.toolkit, parent, formInput, null));
            for (IField f : this.fields) {
                ensureFieldLayout(f);
            }
        } else {
            TabFolder folder = new TabFolder(parent, SWT.NONE);
            folder.setBackground(parent.getBackground());
        
            folder.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    TabItem tabItem = folder.getSelection()[0];
                    if (tabItem != null && !"".equals(tabItem.getText())) {
                        ElementFormPanel.selectedTab = tabItem.getText();
                    }
                }
            });
        
            folder.setLayoutData(new GridData(GridData.FILL_BOTH));
            for (FormFieldPage page : pages) {
                Composite tab = new Composite(folder, SWT.NONE);
                tab.setLayout(new GridLayout(1, false));
                tab.setBackground(parent.getBackground());
        
                TabItem item = new TabItem(folder, SWT.NONE);
                item.setControl(tab);
                item.setText(page.getLabel());
                item.setImage(page.getImage());
        
                if (page.getLabel().equals(ElementFormPanel.selectedTab)) {
                    folder.setSelection(item);
                }
        
                this.fields.addAll(this.fieldFactory.createFields(this.toolkit, tab, formInput, page));
            }
        }
        
        // Install field change listener that will auto apply the value if in auto apply mode
        // and fire panel listeners.
        installFieldListeners();
    }

    /**
     * Ensure the {@link IField field} has a {@link GridData} as layout data, creating one in the orher case.
     * 
     * @param aField a form field
     */
    @objid ("6cb323a0-5622-4e99-bee6-2a9b7e8ef304")
    protected void ensureFieldLayout(IField aField) {
        Composite composite = aField.getComposite();
        if (!(composite.getLayoutData() instanceof GridData)) {
            assert composite.getParent().getLayout() instanceof GridLayout : String.format("%s has %s as layout.", composite.getParent(), composite.getParent().getLayout());
        
            final GridData ld_name = new GridData(SWT.FILL, SWT.CENTER, true, false);
            ld_name.widthHint = 600;
            composite.setLayoutData(ld_name);
        }
    }

    /**
     * {@link IModelChangeListener} callback called on model change event.
     * <p>
     * Refresh and reset fields from the model. This method could be redefined by sub classes.
     * 
     * @param session the modeling session
     * @param event the event.
     */
    @objid ("2f2af85f-57b9-4b50-a7d2-7504154bb0b9")
    protected void onModelChanged(IModelingSession session, IModelChangeEvent event) {
        final ScrolledForm localScrolledForm = this.scrolledForm;
        if (localScrolledForm != null && !localScrolledForm.isDisposed()) {
            try {
                localScrolledForm.getDisplay().asyncExec(() -> {
                    updateFormFromInput(this.input);
                });
            } catch (SWTException e) {
                // Note : this method may be called by a concurrent thread even while the panel is being disposed
                // so any test already made may be obsolete
                if (e.code == SWT.ERROR_DEVICE_DISPOSED || e.code == SWT.ERROR_WIDGET_DISPOSED) {
                    return;
                }
        
                throw e;
            }
        }
    }

    /**
     * Add a listener on the property page updating its content as soon as the {@link SWT#Show} event is triggered.
     * 
     * @param formInput the element to use as input for the form.
     */
    @objid ("6f41848a-92a5-4800-9a9d-d23c44f77d6d")
    private void delayUpdateForm(final ModelElement formInput) {
        // Remove existing listener
        if (this.formUpdater != null) {
            this.scrolledForm.removeListener(SWT.Show, this.formUpdater);
        }
        
        this.formUpdater = new Listener() {
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void handleEvent(Event event) {
                ElementFormPanel.this.scrolledForm.removeListener(SWT.Show, this);
                updateFormFromInput(formInput);
            }
        };
        
        this.scrolledForm.addListener(SWT.Show, this.formUpdater);
    }

    @objid ("226c833c-b29c-4d9e-b4e7-86110c3340fa")
    private void installFieldListeners() {
        // Install field change listener that will auto apply the value if in auto apply mode
        // and fire panel listeners.
        PropertyChangeListener listener = (ev) -> {
            IField f = (IField) ev.getSource();
            String err = f.getValidationError();
        
            if (this.autoApply) {
                // Save the field value in the model if it is valid.
                // If invalid, reset the field to the value in model.
                if (err == null) {
                    f.apply();
                } else {
                    f.refresh();
                }
            }
        
            if (err == null) {
                this.scrolledForm.getMessageManager().removeMessages(f.getControl());
            } else {
                this.scrolledForm.getMessageManager().addMessage(f, err, null, IMessageProvider.ERROR, f.getControl());
            }
        
            // Fire panel listeners in all cases
            for (IPanelListener l : this.panelListeners) {
                l.dataChanged(f, this.autoApply);
            }
        };
        
        for (IField f : this.fields) {
            f.addPropertyChangeListener(listener);
        }
    }

    /**
     * Refresh the value displayed by all form fields.
     */
    @objid ("346b5440-5d5e-4d4a-bf91-9890f64d85dd")
    private void updateFormFields() {
        for (IField field : this.fields) {
            field.refresh();
        }
    }

    /**
     * Update the form's contents.
     * 
     * @param formInput the element to use as input for the form.
     */
    @objid ("1650ca22-065a-4869-9cb8-59a1c2ea5c3d")
    private void updateFormFromInput(ModelElement formInput) {
        if (this.scrolledForm == null || this.scrolledForm.isDisposed()) {
            return;
        }
        
        this.scrolledForm.setRedraw(false);
        this.scrolledForm.setLayoutDeferred(true);
        
        if (!Objects.equals(formInput, this.input)) {
            // Clear the form: all must be rebuilt because data model is hard linked to previous input
            for (Control c : this.scrolledForm.getBody().getChildren()) {
                c.dispose();
            }
            this.fields.clear();
        }
        
        // Update current input
        this.input = formInput;
        
        if (formInput != null) {
            // Update form header
            if (isHeaderVisible()) {
                this.scrolledForm.setText(formInput.getName());
        
                IImageService imageService = this.moduleContext.getModelioServices().getImageService();
                this.scrolledForm.setImage(imageService.getIcon(formInput, null));
            } else {
                this.scrolledForm.setText(null);
                this.scrolledForm.setImage(null);
            }
        
            // Create fields only the first time this input is selected
            if (this.fields.isEmpty()) {
                createFormFields(this.scrolledForm.getBody(), formInput);
        
                this.scrolledForm.reflow(true);
            }
        
            // Refresh fields
            updateFormFields();
        } else {
            // No input: Empty form header
            if (isHeaderVisible()) {
                this.scrolledForm.setText("");
                this.scrolledForm.setImage(null);
            }
        }
        
        // Force form's redraw
        this.scrolledForm.setLayoutDeferred(false);
        this.scrolledForm.setRedraw(true);
    }

}

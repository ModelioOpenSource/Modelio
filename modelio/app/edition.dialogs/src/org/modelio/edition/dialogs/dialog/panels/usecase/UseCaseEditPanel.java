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

package org.modelio.edition.dialogs.dialog.panels.usecase;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.ui.form.fields.IField;
import org.modelio.api.ui.form.fields.NoteField;
import org.modelio.api.ui.form.fields.StringField;
import org.modelio.api.ui.form.fields.TextField;
import org.modelio.api.ui.form.models.IFormFieldData;
import org.modelio.api.ui.form.models.IFormFieldType;
import org.modelio.api.ui.form.models.NoteFieldData;
import org.modelio.app.core.project.ICurrentProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

/**
 * Edition panel for 'UseCase' objects
 */
@objid ("04d5f182-9bb6-473e-8d93-95895625bce0")
@Creatable
public class UseCaseEditPanel implements IPanelProvider {
    @objid ("4218a791-0455-460e-b7b2-caa789568ce2")
    private TextField constraints;

    @objid ("32d8d0d5-583b-483d-b125-81c146737259")
    private NoteField description;

    @objid ("38533ba1-0f5d-402a-b058-f71c29cfef3e")
    private TextField exceptions;

    @objid ("0ceb19c2-02b6-4c04-9923-0706cfd7685d")
    private ScrolledForm form;

    @objid ("3a0be853-9837-493e-bff8-31cd5590ea1c")
    private StringField name;

    @objid ("5164c99c-4e58-402d-ab37-e00006c9c862")
    private TextField nonFuncConstraints;

    @objid ("127a7d02-ff65-4999-8f7b-e157db2f2513")
    private TextField postConditions;

    @objid ("29067837-f9b9-47e5-a4f2-a45d1e0c55d7")
    private TextField preConditions;

    @objid ("79a4ec8d-0769-4006-a59a-3461eb9467a8")
    private ICurrentProjectService projectService;

    @objid ("230d35dc-48e8-406b-8863-3f689fbea1e4")
    private FormToolkit toolkit;

    @objid ("52956f7c-9fc8-4f4a-98d8-d2d2ebd7ed50")
    private UseCase uc;

    @objid ("ad6bd846-846d-4536-9ce7-4a151bf721af")
    private IModuleContext genericModuleContext;

    @objid ("5fd97c26-46fb-4b50-9066-f9a471d1b222")
    protected List<IField> fields = new ArrayList<>();

    @objid ("957f5db5-9913-4ed5-9248-4bd233333671")
    @Override
    public Control createPanel(Composite parent) {
        // The top level scrolled form
        this.toolkit = new FormToolkit(parent.getDisplay());
        this.toolkit.setBorderStyle(SWT.BORDER);
        this.form = this.toolkit.createScrolledForm(parent);
        
        // The top level scrolled form layout
        final GridLayout layout = new GridLayout(1, true);
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 2;
        layout.marginTop = 12;
        layout.marginBottom = 4;
        layout.marginLeft = 2;
        layout.marginRight = 2;
        this.form.getBody().setLayout(layout);
        return this.form;
    }

    @objid ("6545515c-14d8-4193-ac18-ced9d773b6d3")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("0ce23963-a508-48fd-8d76-adccbadf3d11")
    @Override
    public String getHelpTopic() {
        return EditionDialogs.I18N.getString("EditElementDialog.UseCase.HELP_TOPIC");
    }

    @objid ("4cf11283-ce99-4b8d-b6a1-a075813bf600")
    @Override
    public Object getInput() {
        return this.uc;
    }

    @objid ("3e7bd893-5dba-4e2a-940d-346aaf38db2e")
    @Override
    public Object getPanel() {
        return this.form;
    }

    @objid ("15ec2353-cc75-4dcc-a0e6-0be97de0b5bc")
    @Override
    public boolean isRelevantFor(Object obj) {
        if (obj instanceof ISelection) {
            return SelectionHelper.getFirst((ISelection) obj, UseCase.class) != null;
        } else {
            return obj instanceof UseCase;
        }
    }

    @objid ("2891b980-cddb-420c-8674-7f9d7e0add5f")
    @Override
    public void setInput(Object input) {
        UseCase newInput;
        if (input instanceof UseCase) {
            newInput = (UseCase) input;
        } else if (input instanceof ISelection) {
            newInput = SelectionHelper.getFirst((ISelection) input, UseCase.class);
        } else {
            newInput = null;
        }
        
        // Bad input, clear all
        if (newInput == null) {
            this.uc = null;
            return;
        }
        if (!newInput.isValid()) {
            this.uc = null;
            return;
        }
        
        // Input is a valid UseCase
        // Create fields only if first time, or fields are already disposed
        final boolean firstTime = (this.uc == null);
        final boolean isDisposed = (this.name != null && this.name.getControl().isDisposed());
        this.uc = newInput;
        
        if (firstTime || isDisposed) {
            createFormFields(this.form.getBody());
            // this.form.getShell().pack(true);
            PropertyChangeListener listener = (ev) -> {
                IField f = (IField) ev.getSource();
                String err = f.getValidationError() ;
            
                // Save the field value in the model if it is valid.
                // If invalid, reset the field to the value in model.
                if (err == null) {
                    f.apply();
                } else {
                    f.refresh();
                }
                
                if (err == null) {
                    this.form.getMessageManager().removeMessages(f.getControl());
                } else {
                    this.form.getMessageManager().addMessage(f, err, null, IMessageProvider.ERROR, f.getControl());
                }
            };
            
            for (IField f : this.fields) {
                f.addPropertyChangeListener(listener);
            }
        }
        
        this.form.setText(this.uc.getName());
        
        // update();
    }

    @objid ("5e03ab04-4672-4d24-a76d-0ea6de3478a8")
    void createFormFields(Composite comp) {
        // The fields
        this.fields.clear();
        
        // Name
        IFormFieldData data = new UseCaseFieldData(this.uc, UseCaseFieldData.FieldID.NAME);
        this.name = new StringField(this.toolkit, comp, data);
        this.name.getComposite().setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        this.name.setHelpText(EditionDialogs.I18N.getString("UseCaseEditPanel.ucName.help"));
        this.name.setVertical(false);
        this.fields.add(this.name);
        
        final CTabFolder folder = new CTabFolder(comp, SWT.BORDER | SWT.TOP);
        folder.setTabHeight(-1);
        final GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.verticalIndent = 8;
        folder.setLayoutData(gd);
        
        // Description
        final CTabItem tab1 = new CTabItem(folder, SWT.NONE);
        tab1.setText(EditionDialogs.I18N.getString("UseCaseEditPanel.UseCase.tab"));
        
        this.description = new NoteField(this.toolkit, folder, 
                new NoteFieldData(this.genericModuleContext.getModelingSession(), this.uc, "ModelerModule", "description"));
        
        final GridData ld_description = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_description.widthHint = 600;
        this.description.getComposite().setLayoutData(ld_description);
        this.description.setHelpText(EditionDialogs.I18N.getString("UseCaseEditPanel.ucDescription.help"));
        this.description.setVertical(true);
        this.fields.add(this.description);
        
        tab1.setControl(this.description.getComposite());
        
        // Exceptions
        final CTabItem tab2 = new CTabItem(folder, SWT.NONE);
        tab2.setText(EditionDialogs.I18N.getString("UseCaseEditPanel.Exceptions.tab"));
        
        // Intermediate composites are needed to avoid layout issues when one or several fields have a lot of text...
        final Composite exceptionGroup = new Composite(folder, SWT.NONE);
        GridLayout gl = new GridLayout();
        gl.horizontalSpacing = 0;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        gl.verticalSpacing = 0;
        exceptionGroup.setLayout(gl);
        
        final GridData ld_exceptionGroup = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_exceptionGroup.widthHint = 600;
        exceptionGroup.setLayoutData(ld_exceptionGroup);
        
        // exceptions field
        data = new UseCaseFieldData(this.uc, UseCaseFieldData.FieldID.EXCEPTIONS);
        this.exceptions = new TextField(this.toolkit, exceptionGroup, data);
        final GridData ld_exceptions = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_exceptions.widthHint = 600;
        this.exceptions.getComposite().setLayoutData(ld_exceptions);
        this.exceptions.setHelpText(EditionDialogs.I18N.getString("UseCaseEditPanel.ucExceptions.help"));
        this.exceptions.setVertical(true);
        this.fields.add(this.exceptions);
        
        tab2.setControl(exceptionGroup);
        
        // Conditions
        final CTabItem tab3 = new CTabItem(folder, SWT.NONE);
        tab3.setText(EditionDialogs.I18N.getString("UseCaseEditPanel.Conditions.tab"));
        
        final Composite conditionsGroup = new Composite(folder, SWT.NONE);
        GridLayout gl2 = new GridLayout();
        gl2.horizontalSpacing = 0;
        gl2.marginHeight = 0;
        gl2.marginWidth = 0;
        gl2.verticalSpacing = 0;
        conditionsGroup.setLayout(gl2);
        
        final GridData ld_conditionsGroup = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_conditionsGroup.widthHint = 600;
        conditionsGroup.setLayoutData(ld_conditionsGroup);
        
        // pre-conditions field
        data = new UseCaseFieldData(this.uc, UseCaseFieldData.FieldID.PRECOND);
        this.preConditions = new TextField(this.toolkit, conditionsGroup, data);
        final GridData ld_preConditions = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_preConditions.widthHint = 600;
        this.preConditions.getComposite().setLayoutData(ld_preConditions);
        this.preConditions.setVertical(true);
        this.preConditions.setHelpText(EditionDialogs.I18N.getString("UseCaseEditPanel.ucPreConditions.help"));
        this.fields.add(this.preConditions);
        
        // post-conditions field
        data = new UseCaseFieldData(this.uc, UseCaseFieldData.FieldID.POSTCOND);
        this.postConditions = new TextField(this.toolkit, conditionsGroup, data);
        final GridData ld_postConditions = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_postConditions.widthHint = 600;
        this.postConditions.getComposite().setLayoutData(ld_postConditions);
        this.postConditions.setHelpText(EditionDialogs.I18N.getString("UseCaseEditPanel.ucPostConditions.help"));
        this.postConditions.setVertical(true);
        this.fields.add(this.postConditions);
        
        tab3.setControl(conditionsGroup);
        
        // Constraints
        final CTabItem tab4 = new CTabItem(folder, SWT.NONE);
        tab4.setText(EditionDialogs.I18N.getString("UseCaseEditPanel.Constraints.tab"));
        
        final Composite constraintsGroup = new Composite(folder, SWT.NONE);
        gl2 = new GridLayout();
        gl2.horizontalSpacing = 0;
        gl2.marginHeight = 0;
        gl2.marginWidth = 0;
        gl2.verticalSpacing = 0;
        constraintsGroup.setLayout(gl2);
        
        final GridData ld_constraintsGroup = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_constraintsGroup.widthHint = 600;
        constraintsGroup.setLayoutData(ld_constraintsGroup);
        
        // functional constraints field
        data = new UseCaseFieldData(this.uc, UseCaseFieldData.FieldID.CONSTRAINTS);
        this.constraints = new TextField(this.toolkit, constraintsGroup, data);
        final GridData ld_constraints = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_constraints.widthHint = 600;
        this.constraints.getComposite().setLayoutData(ld_constraints);
        this.constraints.setHelpText(EditionDialogs.I18N.getString("UseCaseEditPanel.ucConstraints.help"));
        this.constraints.setVertical(true);
        this.fields.add(this.constraints);
        
        // non-functional constraints field
        data = new UseCaseFieldData(this.uc, UseCaseFieldData.FieldID.NFCONSTRAINTS);
        this.nonFuncConstraints = new TextField(this.toolkit, constraintsGroup, data);
        final GridData ld_nonFuncConstraints = new GridData(SWT.FILL, SWT.FILL, true, true);
        ld_nonFuncConstraints.widthHint = 600;
        this.nonFuncConstraints.getComposite().setLayoutData(ld_nonFuncConstraints);
        this.nonFuncConstraints.setHelpText(EditionDialogs.I18N.getString("UseCaseEditPanel.ucNonFunctionalConstraints.help"));
        this.nonFuncConstraints.setVertical(true);
        this.fields.add(this.nonFuncConstraints);
        tab4.setControl(constraintsGroup);
        
        folder.setSelection(tab1);
        
        this.form.reflow(true);
        // this.form.layout(true, true);
    }

    @objid ("70457223-567d-483e-baea-09645de35596")
    void update() {
        if (this.uc != null && this.uc.isValid()) {
            this.name.getModel().setValue(this.uc.getName());
            this.description.getModel().setValue(this.uc.getNoteContent("ModelerModule", "description"));
            this.preConditions.getModel().setValue(this.uc.getNoteContent("ModelerModule", "precondition"));
            this.postConditions.getModel().setValue(this.uc.getNoteContent("ModelerModule", "postcondition"));
            this.exceptions.getModel().setValue(this.uc.getNoteContent("ModelerModule", "exception"));
            this.constraints.getModel().setValue(this.uc.getNoteContent("ModelerModule", "constraint"));
            this.nonFuncConstraints.getModel().setValue(this.uc.getNoteContent("ModelerModule", "non-functional constraint"));
        } else {
            this.name.getModel().setValue("");
            this.description.getModel().setValue("");
            this.preConditions.getModel().setValue("");
            this.postConditions.getModel().setValue("");
            this.exceptions.getModel().setValue("");
            this.constraints.getModel().setValue("");
            this.nonFuncConstraints.getModel().setValue("");
        }
        setReadOnly();
    }

    @objid ("5d7cc02e-9cd2-4fba-8b52-daeb04394873")
    private void setReadOnly() {
        final boolean editable = this.uc != null && this.uc.isModifiable();
        this.name.setEditable(editable);
        this.description.setEditable(editable);
        this.preConditions.setEditable(editable);
        this.postConditions.setEditable(editable);
        this.exceptions.setEditable(editable);
        this.constraints.setEditable(editable);
        this.nonFuncConstraints.setEditable(editable);
    }

    /**
     * E4 Constructor.
     * @param genericModulecontext a generic module context
     * 
     * @param projectService the project service
     */
    @objid ("0d778af1-ea9a-4637-9d18-1413cf2018f5")
    @PostConstruct
    void postConstruct(ICurrentProjectService projectService, IModuleContext genericModuleContext) {
        this.projectService = projectService;
        this.genericModuleContext = genericModuleContext;
    }

    @objid ("7f68bbb7-2451-4d96-9ddd-4c6ff66e06e1")
    private static class UseCaseFieldData implements IFormFieldData {
        @objid ("6b2df50f-6f73-4e17-ad71-f29d1a5ad480")
        private final FieldID fid;

        @objid ("58aad66d-08f8-4e56-ae9f-5bf91280b63d")
        private final UseCase uc;

        @objid ("6541a1cf-f92d-4dcf-ab16-316e6a714e5f")
        public UseCaseFieldData(UseCase uc, FieldID fid) {
            this.uc = uc;
            this.fid = fid;
        }

        @objid ("10c16a17-f479-4e2f-96a0-199dfcf43bc0")
        @Override
        public void setValue(Object value) {
            if (this.uc == null || this.uc.isModifiable() == false) {
                return;
            }
            
            try (ITransaction t = CoreSession.getSession(this.uc).getTransactionSupport().createTransaction("")) {
                switch (this.fid) {
                case NAME:
                    this.uc.setName(value.toString());
                    break;
                case CONSTRAINTS:
                    this.uc.putNoteContent("ModelerModule", "constraint", value.toString());
                    break;
                case DESCRIPTION:
                    this.uc.putNoteContent("ModelerModule", "description", value.toString());
                    break;
                case EXCEPTIONS:
                    this.uc.putNoteContent("ModelerModule", "exception", value.toString());
                    break;
                case NFCONSTRAINTS:
                    this.uc.putNoteContent("ModelerModule", "non-functional constraint", value.toString());
                    break;
                case POSTCOND:
                    this.uc.putNoteContent("ModelerModule", "postcondition", value.toString());
                    break;
                case PRECOND:
                    this.uc.putNoteContent("ModelerModule", "precondition", value.toString());
                    break;
                default:
                    break;
            
                }
                t.commit();
            } catch (final ExtensionNotFoundException e) {
                EditionDialogs.LOG.error(e);
            }
        }

        @objid ("f6b0a2c3-20ae-4fe9-9147-4bf791dac8af")
        @Override
        public Object getValue() {
            switch (this.fid) {
            case NAME:
                return this.uc != null ? this.uc.getName() : "";
            case CONSTRAINTS:
                return this.uc.getNoteContent("ModelerModule", "constraint");
            case DESCRIPTION:
                return this.uc.getNoteContent("ModelerModule", "description");
            case EXCEPTIONS:
                return this.uc.getNoteContent("ModelerModule", "exception");
            case NFCONSTRAINTS:
                return this.uc.getNoteContent("ModelerModule", "non-functional constraint");
            case POSTCOND:
                return this.uc.getNoteContent("ModelerModule", "postcondition");
            case PRECOND:
                return this.uc.getNoteContent("ModelerModule", "precondition");
            default:
                return this.fid.toString();
            }
        }

        @objid ("86f6a895-cf77-4f11-aca8-ac3d15e91239")
        @Override
        public String getName() {
            switch (this.fid) {
            case CONSTRAINTS:
                return EditionDialogs.I18N.getString("UseCaseEditPanel.ucConstraints");
            case DESCRIPTION:
                return EditionDialogs.I18N.getString("UseCaseEditPanel.ucDescription");
            case EXCEPTIONS:
                return EditionDialogs.I18N.getString("UseCaseEditPanel.ucExceptions");
            case NAME:
                return EditionDialogs.I18N.getString("UseCaseEditPanel.ucName");
            case NFCONSTRAINTS:
                return EditionDialogs.I18N.getString("UseCaseEditPanel.ucNonFunctionalConstraints");
            case POSTCOND:
                return EditionDialogs.I18N.getString("UseCaseEditPanel.ucPostConditions");
            case PRECOND:
                return EditionDialogs.I18N.getString("UseCaseEditPanel.ucPreConditions");
            default:
                return this.fid.toString();
            
            }
        }

        @objid ("7df9713a-7ae9-4f6d-8c1f-9508cca4e825")
        @Override
        public IFormFieldType getType() {
            return new IFormFieldType() {
                                        @Override
                                        public String getName() {
                                            return UseCaseFieldData.this.fid.toString();
                                        }
                        
                                        @Override
                                        public Object[] getEnumeratedValues() {
                                            return null;
                                        }
                        
                                        @Override
                                        public boolean isValidValue(String value) {
                                            return value != null;
                                        }
                                    };
        }

//
        @objid ("f2bc0b0b-f7f3-4460-b5ea-e13b84693292")
        enum FieldID {
            NAME,
            DESCRIPTION,
            PRECOND,
            POSTCOND,
            EXCEPTIONS,
            CONSTRAINTS,
            NFCONSTRAINTS;
        }

    }

}

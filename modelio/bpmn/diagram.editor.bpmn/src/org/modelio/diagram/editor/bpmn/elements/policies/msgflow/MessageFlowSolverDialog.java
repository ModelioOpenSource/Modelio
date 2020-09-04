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

package org.modelio.diagram.editor.bpmn.elements.policies.msgflow;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
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
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.swt.labelprovider.UniversalLabelProvider2;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.ui.dialog.ModelioDialog;

@objid ("122b4fb9-9f6e-4f0a-8319-46a3b48466db")
public class MessageFlowSolverDialog extends ModelioDialog {
    @objid ("409df218-8b84-4ce5-ae32-202006e9dbd8")
    private Controller controller;

    @objid ("e0c0b9f6-55bb-4911-a807-249ad53d0e47")
    public MessageFlowSolverDialog(Shell parentShell, MessageFlowSolverDataModel data) {
        super(parentShell);
        this.controller = new Controller(data);
    }

    @objid ("c8da0599-4b3d-47a3-b3ba-f9f361949526")
    @Override
    public Control createContentArea(Composite parent) {
        return this.controller.createUi(parent);
    }

    @objid ("37169418-2860-464b-93ba-6b30e761a147")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    @objid ("3d7e89ca-ee2c-484c-be74-84fc6d84047d")
    @Override
    public void init() {
        getShell().setText(DiagramEditorBpmn.I18N.getString("MessageFlowSolverDialog.title"));
        setTitle(DiagramEditorBpmn.I18N.getString("MessageFlowSolverDialog.title"));
        setMessage(DiagramEditorBpmn.I18N.getString("MessageFlowSolverDialog.message"));
        
        Shell parentShell = getShell().getParent().getShell();
        Point shellLocation = parentShell.getLocation();
        getShell().setSize(600, 500);
        getShell().setLocation(shellLocation.x + 300, shellLocation.y + 300);
        setLogoImage(null);
    }

    @objid ("6370cf06-8f98-451a-ba0e-01ce40119a07")
    private static class Ui {
        @objid ("27848d7d-2423-4603-ba01-9ec4023e5e1d")
        private Composite composite;

        @objid ("5942035b-615a-4631-8ffd-bc8ee4ef9cb3")
        private Controller controller;

        @objid ("e9647737-d4f2-4eaf-84b7-08c239e7cb9d")
        private TreeViewer sourcesViewer;

        @objid ("1fe66ce9-bf33-4b46-82ae-051b47e23e5a")
        private TreeViewer targetsViewer;

        @objid ("7552a98d-ac8b-43d0-80c5-e1b37b50f56a")
        private Label targetsTitle;

        @objid ("570c5afe-ba0c-4c41-adb6-0ecd45579376")
        private Label sourcesTitle;

        @objid ("205ab691-0804-4f0a-9698-fbb757fb4def")
        private Text nameText;

        @objid ("9969b24b-ca14-40a1-a45b-5b89d2d6c7b8")
        public Ui(Controller controller) {
            this.controller = controller;
        }

        @objid ("5e9ce2e2-39d7-4b82-acf8-61f5de6b6008")
        public Composite create(Composite parent) {
            this.composite = new Composite(parent, SWT.NONE);
            GridData gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
            this.composite.setLayoutData(gd);
            this.composite.setFont(parent.getFont());
            
            this.composite.setLayout(new FormLayout());
            
            Composite preview = createNameChooser(this.composite);
            Composite sourceChooser = createSourceChooser(this.composite);
            Composite targetChooser = createTargetChooser(this.composite);
            
            FormData fd = new FormData();
            fd.left = new FormAttachment(0, 4);
            fd.right = new FormAttachment(100, -4);
            fd.top = new FormAttachment(0, 4);
            preview.setLayoutData(fd);
            
            FormData fd1 = new FormData();
            fd1.left = new FormAttachment(0, 4);
            fd1.right = new FormAttachment(50, 0);
            fd1.top = new FormAttachment(preview, 4);
            fd1.bottom = new FormAttachment(100, -4);
            sourceChooser.setLayoutData(fd1);
            
            FormData fd2 = new FormData();
            fd2.left = new FormAttachment(sourceChooser, 4);
            fd2.right = new FormAttachment(100, -4);
            fd2.top = new FormAttachment(preview, 4);
            fd2.bottom = new FormAttachment(100, -4);
            targetChooser.setLayoutData(fd2);
            return this.composite;
        }

        @objid ("00b3a9aa-067e-49ac-aee6-7bcf4ca13778")
        private Composite createNameChooser(Composite parent) {
            Composite c = new Composite(parent, SWT.BORDER);
            
            GridLayout layout = new GridLayout(2, false);
            layout.marginHeight = 4;
            layout.marginWidth = 4;
            c.setLayout(layout);
            
            Label nameLabel= new Label(c, SWT.NONE);
            nameLabel.setText(DiagramEditorBpmn.I18N.getString("MessageFlowSolverDialog.name.label"));
            nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
            
            this.nameText = new Text(c, SWT.BORDER);
            this.nameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            
            
            this.nameText.addModifyListener(new ModifyListener() {
                
                @Override
                public void modifyText(ModifyEvent e) {
                    Ui.this.controller.onNameChange(((Text)e.widget).getText());
                }
            });
            return c;
        }

        @objid ("cdfaea29-1213-4185-8932-aad9d9e48be2")
        private Composite createSourceChooser(Composite parent) {
            Composite c = new Composite(parent, SWT.BORDER);
            c.setLayout(new GridLayout());
            this.sourcesTitle = new Label(c, SWT.NONE);
            
            this.sourcesViewer = new TreeViewer(c, SWT.SINGLE|SWT.FULL_SELECTION);
            this.sourcesViewer.setContentProvider(new ITreeContentProvider() {
                @Override
                public Object[] getElements(Object inputElement) {
                    return ArrayContentProvider.getInstance().getElements(inputElement);
                }
                @Override
                public Object[] getChildren(Object parentElement) {
                    return null;
                }
                @Override
                public Object getParent(Object element) {
                    return null;
                }
                @Override
                public boolean hasChildren(Object element) {
                    return false;
                }
            });
            
            this.sourcesViewer.setLabelProvider(new UniversalLabelProvider2());
            this.sourcesViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            this.sourcesViewer.getTree().setToolTipText(DiagramEditorBpmn.I18N.getMessage("MessageFlowSolverDialog.sources.tooltip"));
            this.sourcesViewer.addSelectionChangedListener(new ISelectionChangedListener() {
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    Ui.this.controller.onSelectSource(event.getSelection());
                }
            });
            return c;
        }

        @objid ("5b0ad22b-cc25-4eba-b235-3236599cb5de")
        private Composite createTargetChooser(Composite parent) {
            Composite c = new Composite(parent, SWT.BORDER);
            c.setLayout(new GridLayout());
            
            
            this.targetsTitle = new Label(c, SWT.NONE);
            
            
            this.targetsViewer = new TreeViewer(c, SWT.SINGLE|SWT.FULL_SELECTION);
            this.targetsViewer.setContentProvider(new ITreeContentProvider() {
                @Override
                public Object[] getElements(Object inputElement) {
                    return ArrayContentProvider.getInstance().getElements(inputElement);
                }
                @Override
                public Object[] getChildren(Object parentElement) {
                    return null;
                }
                @Override
                public Object getParent(Object element) {
                    return null;
                }
                @Override
                public boolean hasChildren(Object element) {
                    return false;
                }
            });
            
            this.targetsViewer.setLabelProvider(new UniversalLabelProvider2());
            
            this.targetsViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            this.targetsViewer.getTree().setToolTipText(DiagramEditorBpmn.I18N.getMessage("MessageFlowSolverDialog.targets.tooltip"));
            this.targetsViewer.addSelectionChangedListener(new ISelectionChangedListener() {
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    Ui.this.controller.onSelectTarget(event.getSelection());
                }
            });
            return c;
        }

        @objid ("c04afa89-b338-4ede-bd65-7ffcd2e93120")
        public Composite getComposite() {
            return this.composite;
        }

        @objid ("081a3671-f273-4ebb-8e3d-fb7d0322f57f")
        public void updateName(String messageFlowName) {
            this.nameText.setText(messageFlowName);
        }

        @objid ("257a01c3-5dbf-4aaf-8645-9bd27a4bdd04")
        public void updateSources(BpmnBaseElement sourcesProvider, List<BpmnBaseElement> sources, BpmnBaseElement selected) {
            this.sourcesTitle.setText(DiagramEditorBpmn.I18N.getMessage("MessageFlowSolverDialog.sourcesTitle.label", sourcesProvider.getName()));
            this.sourcesViewer.setInput(sources);
            this.sourcesViewer.setSelection(new StructuredSelection(selected));
        }

        @objid ("a45933b9-9175-479f-94fc-4a9884dbd837")
        public void updateTargets(BpmnBaseElement targetsProvider, List<BpmnBaseElement> targets, BpmnBaseElement selected) {
            this.targetsTitle.setText(DiagramEditorBpmn.I18N.getMessage("MessageFlowSolverDialog.targetsTitle.label", targetsProvider.getName()));
            this.targetsViewer.setInput(targets);
            this.targetsViewer.setSelection(new StructuredSelection(selected));
        }

    }

    @objid ("bf60c521-1f41-412b-8253-fb4909d1d637")
    private static class Controller {
        @objid ("95af05dd-7858-4f4f-9df6-dda379ea9c17")
        private Ui ui;

        @objid ("297486d1-dbbc-4fec-9ae7-5194563ce575")
        private MessageFlowSolverDataModel data;

        @objid ("bb121a77-117f-4180-8998-7db8cb7dd1e6")
        public Controller(MessageFlowSolverDataModel data) {
            this.data = data;
        }

        @objid ("66897334-7444-4bd6-9e5a-8706e616873b")
        public Composite createUi(Composite parent) {
            this.ui = new Ui(this);
            this.ui.create(parent);
            updateUi();
            return this.ui.getComposite();
        }

        @objid ("2999966b-1593-47f8-98d5-3d06894a4caf")
        private void updateUi() {
            this.ui.updateName(this.data.getMessageFlowName());
            this.ui.updateSources(this.data.getSourcesProvider(), this.data.getSources(), this.data.getSelectedSource());
            this.ui.updateTargets(this.data.getTargetsProvider(), this.data.getTargets(), this.data.getSelectedTarget());
        }

        /**
         * Called by Ui when the user changes the target selection
         * @param selection the new target selection
         */
        @objid ("9e0b6d06-af4f-4607-9852-cc227ee5d420")
        public void onSelectTarget(ISelection selection) {
            this.data.setSelectedTarget(SelectionHelper.getFirst(selection, BpmnBaseElement.class));
        }

        /**
         * Called by Ui when the user changes the source selection
         * @param selection the new source selection
         */
        @objid ("91ed068c-68a2-4290-9f69-d86d8e711a37")
        public void onSelectSource(ISelection selection) {
            this.data.setSelectedSource(SelectionHelper.getFirst(selection, BpmnBaseElement.class));
        }

        @objid ("06e1d400-02ee-4aa2-9e73-11837708a841")
        public void onNameChange(String s) {
            this.data.setMessageFlowName(s);
        }

    }

}
